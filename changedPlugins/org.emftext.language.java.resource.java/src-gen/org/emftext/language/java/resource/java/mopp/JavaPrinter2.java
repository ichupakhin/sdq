/*******************************************************************************
 * Copyright (c) 2006-2015
 * Software Technology Group, Dresden University of Technology
 * DevBoost GmbH, Dresden, Amtsgericht Dresden, HRB 34001
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Software Technology Group - TU Dresden, Germany;
 *   DevBoost GmbH - Dresden, Germany
 *      - initial API and implementation
 ******************************************************************************/

package org.emftext.language.java.resource.java.mopp;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

public class JavaPrinter2 implements org.emftext.language.java.resource.java.IJavaTextPrinter {
	
	protected class PrintToken {
		
		private String text;
		private String tokenName;
		private EObject container;
		
		public PrintToken(String text, String tokenName, EObject container) {
			this.text = text;
			this.tokenName = tokenName;
			this.container = container;
		}
		
		public String getText() {
			return text;
		}
		
		public String getTokenName() {
			return tokenName;
		}
		
		public EObject getContainer() {
			return container;
		}
		
		public String toString() {
			return "'" + text + "' [" + tokenName + "]";
		}
		
	}
	
	/**
	 * The PrintCountingMap keeps tracks of the values that must be printed for each
	 * feature of an EObject. It is also used to store the indices of all values that
	 * have been printed. This knowledge is used to avoid printing values twice. We
	 * must store the concrete indices of the printed values instead of basically
	 * counting them, because values may be printed in an order that differs from the
	 * order in which they are stored in the EObject's feature.
	 */
	protected class PrintCountingMap {
		
		private Map<String, List<Object>> featureToValuesMap = new LinkedHashMap<String, List<Object>>();
		private Map<String, Set<Integer>> featureToPrintedIndicesMap = new LinkedHashMap<String, Set<Integer>>();
		
		public void setFeatureValues(String featureName, List<Object> values) {
			featureToValuesMap.put(featureName, values);
			// If the feature does not have values it won't be printed. An entry in
			// 'featureToPrintedIndicesMap' is therefore not needed in this case.
			if (values != null) {
				featureToPrintedIndicesMap.put(featureName, new LinkedHashSet<Integer>());
			}
		}
		
		public Set<Integer> getIndicesToPrint(String featureName) {
			return featureToPrintedIndicesMap.get(featureName);
		}
		
		public void addIndexToPrint(String featureName, int index) {
			featureToPrintedIndicesMap.get(featureName).add(index);
		}
		
		public int getCountLeft(org.emftext.language.java.resource.java.grammar.JavaTerminal terminal) {
			EStructuralFeature feature = terminal.getFeature();
			String featureName = feature.getName();
			List<Object> totalValuesToPrint = featureToValuesMap.get(featureName);
			Set<Integer> printedIndices = featureToPrintedIndicesMap.get(featureName);
			if (totalValuesToPrint == null) {
				return 0;
			}
			return totalValuesToPrint.size() - printedIndices.size();
		}
		
		public int getNextIndexToPrint(String featureName) {
			int printedValues = featureToPrintedIndicesMap.get(featureName).size();
			return printedValues;
		}
		
	}
	
	public final static String NEW_LINE = java.lang.System.getProperties().getProperty("line.separator");
	
	private final org.emftext.language.java.resource.java.util.JavaEClassUtil eClassUtil = new org.emftext.language.java.resource.java.util.JavaEClassUtil();
	
	/**
	 * Holds the resource that is associated with this printer. May be null if the
	 * printer is used stand alone.
	 */
	private org.emftext.language.java.resource.java.IJavaTextResource resource;
	
	private Map<?, ?> options;
	private OutputStream outputStream;
	private String encoding = System.getProperty("file.encoding");
	protected List<PrintToken> tokenOutputStream;
	private org.emftext.language.java.resource.java.IJavaTokenResolverFactory tokenResolverFactory = new org.emftext.language.java.resource.java.mopp.JavaTokenResolverFactory();
	private boolean handleTokenSpaceAutomatically = true;
	private int tokenSpace = 1;
	/**
	 * A flag that indicates whether tokens have already been printed for some object.
	 * The flag is set to false whenever printing of an EObject tree is started. The
	 * status of the flag is used to avoid printing default token space in front of
	 * the root object.
	 */
	private boolean startedPrintingObject = false;
	/**
	 * The number of tab characters that were printed before the current line. This
	 * number is used to calculate the relative indentation when printing contained
	 * objects, because all contained objects must start with this indentation
	 * (tabsBeforeCurrentObject + currentTabs).
	 */
	private int currentTabs;
	/**
	 * The number of tab characters that must be printed before the current object.
	 * This number is used to calculate the indentation of new lines, when line breaks
	 * are printed within one object.
	 */
	private int tabsBeforeCurrentObject;
	/**
	 * This flag is used to indicate whether the number of tabs before the current
	 * object has been set for the current object. The flag is needed, because setting
	 * the number of tabs must be performed when the first token of the contained
	 * element is printed.
	 */
	private boolean startedPrintingContainedObject;
	
	public JavaPrinter2(OutputStream outputStream, org.emftext.language.java.resource.java.IJavaTextResource resource) {
		super();
		this.outputStream = outputStream;
		this.resource = resource;
	}
	
	public void print(EObject element) throws IOException {
		tokenOutputStream = new ArrayList<PrintToken>();
		currentTabs = 0;
		tabsBeforeCurrentObject = 0;
		startedPrintingObject = true;
		startedPrintingContainedObject = false;
		List<org.emftext.language.java.resource.java.grammar.JavaFormattingElement>  formattingElements = new ArrayList<org.emftext.language.java.resource.java.grammar.JavaFormattingElement>();
		doPrint(element, formattingElements);
		// print all remaining formatting elements
		List<org.emftext.language.java.resource.java.mopp.JavaLayoutInformation> layoutInformations = getCopyOfLayoutInformation(element);
		org.emftext.language.java.resource.java.mopp.JavaLayoutInformation eofLayoutInformation = getLayoutInformation(layoutInformations, null, null, null);
		printFormattingElements(element, formattingElements, layoutInformations, eofLayoutInformation);
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(outputStream), encoding));
		if (handleTokenSpaceAutomatically) {
			printSmart(writer);
		} else {
			printBasic(writer);
		}
		writer.flush();
	}
	
	protected void doPrint(EObject element, List<org.emftext.language.java.resource.java.grammar.JavaFormattingElement> foundFormattingElements) {
		if (element == null) {
			throw new IllegalArgumentException("Nothing to write.");
		}
		if (outputStream == null) {
			throw new IllegalArgumentException("Nothing to write on.");
		}
		
		if (element instanceof org.emftext.language.java.containers.EmptyModel) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_0, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.containers.Package) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_1, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.containers.CompilationUnit) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_2, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.imports.ClassifierImport) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_3, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.imports.PackageImport) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_4, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.imports.StaticMemberImport) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_5, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.imports.StaticClassifierImport) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_6, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.classifiers.Class) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_7, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.classifiers.AnonymousClass) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_8, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.classifiers.Interface) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_9, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.classifiers.Enumeration) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_10, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.classifiers.Annotation) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_11, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.annotations.AnnotationInstance) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_12, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.annotations.SingleAnnotationParameter) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_13, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.annotations.AnnotationParameterList) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_14, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.annotations.AnnotationAttributeSetting) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_15, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.generics.TypeParameter) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_16, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.members.EnumConstant) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_17, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.Block) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_18, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.members.Constructor) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_19, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.members.ClassMethod) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.annotations.AnnotationAttribute) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.parameters.OrdinaryParameter) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_23, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.parameters.VariableLengthParameter) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_24, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.variables.LocalVariable) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_25, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.LocalVariableStatement) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_26, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.variables.AdditionalLocalVariable) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_27, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.members.Field) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_28, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.members.AdditionalField) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_29, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.members.EmptyMember) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_30, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.instantiations.NewConstructorCall) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_31, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.instantiations.ExplicitConstructorCall) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_32, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.arrays.ArrayInstantiationByValuesTyped) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_33, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.arrays.ArrayInstantiationByValuesUntyped) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_34, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.arrays.ArrayInstantiationBySize) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_35, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.arrays.ArrayInitializer) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_36, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.arrays.ArraySelector) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_37, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.types.NamespaceClassifierReference) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_38, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.types.ClassifierReference) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_39, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.references.MethodCall) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_40, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.references.IdentifierReference) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_41, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.references.ReflectiveClassReference) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_42, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.references.SelfReference) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_43, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.references.PrimitiveTypeReference) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_44, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.This) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_45, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.Super) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_46, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.references.StringReference) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_47, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.generics.QualifiedTypeArgument) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_48, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.generics.UnknownTypeArgument) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_49, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.generics.ExtendsTypeArgument) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_50, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.generics.SuperTypeArgument) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_51, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.Assert) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_52, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.Condition) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_53, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.ForLoop) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_54, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.ForEachLoop) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_55, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.DoWhileLoop) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_57, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.EmptyStatement) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_58, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.SynchronizedBlock) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_59, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.TryBlock) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_60, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.CatchBlock) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_61, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.Switch) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_62, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.NormalSwitchCase) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_63, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.DefaultSwitchCase) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_64, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.Return) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_65, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.Throw) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_66, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.Break) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_67, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.Continue) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_68, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.JumpLabel) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_69, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.ExpressionStatement) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_70, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.ExpressionList) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_71, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.AssignmentExpression) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_72, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.ConditionalExpression) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_73, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.ConditionalOrExpression) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_74, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.ConditionalAndExpression) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_75, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.InclusiveOrExpression) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_76, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.ExclusiveOrExpression) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_77, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.AndExpression) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_78, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.EqualityExpression) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_79, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.InstanceOfExpression) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_80, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.RelationExpression) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_81, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.ShiftExpression) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_82, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.AdditiveExpression) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_83, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.MultiplicativeExpression) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_84, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.UnaryExpression) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_85, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.SuffixUnaryModificationExpression) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_86, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.PrefixUnaryModificationExpression) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_87, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.CastExpression) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_88, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.NestedExpression) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_89, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.Assignment) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_90, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentPlus) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_91, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentMinus) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_92, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentMultiplication) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_93, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentDivision) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_94, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentAnd) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_95, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentOr) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_96, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentExclusiveOr) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_97, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentModulo) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_98, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentLeftShift) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_99, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentRightShift) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_100, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentUnsignedRightShift) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_101, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.Addition) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_102, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.Subtraction) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_103, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.Multiplication) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_104, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.Division) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_105, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.Remainder) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_106, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.LessThan) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_107, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.LessThanOrEqual) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_108, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.GreaterThan) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_109, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.GreaterThanOrEqual) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_110, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.LeftShift) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_111, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.RightShift) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_112, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.UnsignedRightShift) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_113, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.Equal) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_114, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.NotEqual) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_115, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.PlusPlus) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_116, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.MinusMinus) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_117, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.Complement) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_118, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.Negate) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_119, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.arrays.ArrayDimension) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_120, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.NullLiteral) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_121, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Public) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_122, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Abstract) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_123, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Protected) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_124, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Private) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_125, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Final) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_126, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Static) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_127, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Native) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_128, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Synchronized) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_129, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Transient) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_130, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Volatile) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_131, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Strictfp) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_132, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.types.Void) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_133, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.types.Boolean) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_134, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.types.Char) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_135, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.types.Byte) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_136, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.types.Short) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_137, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.types.Int) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_138, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.types.Long) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_139, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.types.Float) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_140, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.types.Double) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_141, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.DecimalLongLiteral) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_142, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.DecimalFloatLiteral) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_143, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.DecimalIntegerLiteral) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_144, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.DecimalDoubleLiteral) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_145, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.HexLongLiteral) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_146, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.HexFloatLiteral) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_147, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.HexDoubleLiteral) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_148, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.HexIntegerLiteral) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_149, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.OctalLongLiteral) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_150, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.OctalIntegerLiteral) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_151, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.CharacterLiteral) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_152, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.BooleanLiteral) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_153, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.members.InterfaceMethod) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_20, foundFormattingElements);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.WhileLoop) {
			printInternal(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_56, foundFormattingElements);
			return;
		}
		
		addWarningToResource("The printer can not handle " + element.eClass().getName() + " elements", element);
	}
	
	public void printInternal(EObject eObject, org.emftext.language.java.resource.java.grammar.JavaSyntaxElement ruleElement, List<org.emftext.language.java.resource.java.grammar.JavaFormattingElement> foundFormattingElements) {
		List<org.emftext.language.java.resource.java.mopp.JavaLayoutInformation> layoutInformations = getCopyOfLayoutInformation(eObject);
		org.emftext.language.java.resource.java.mopp.JavaSyntaxElementDecorator decoratorTree = getDecoratorTree(ruleElement);
		decorateTree(decoratorTree, eObject);
		printTree(decoratorTree, eObject, foundFormattingElements, layoutInformations);
	}
	
	/**
	 * creates a tree of decorator objects which reflects the syntax tree that is
	 * attached to the given syntax element
	 */
	public org.emftext.language.java.resource.java.mopp.JavaSyntaxElementDecorator getDecoratorTree(org.emftext.language.java.resource.java.grammar.JavaSyntaxElement syntaxElement) {
		org.emftext.language.java.resource.java.grammar.JavaSyntaxElement[] children = syntaxElement.getChildren();
		int childCount = children.length;
		org.emftext.language.java.resource.java.mopp.JavaSyntaxElementDecorator[] childDecorators = new org.emftext.language.java.resource.java.mopp.JavaSyntaxElementDecorator[childCount];
		for (int i = 0; i < childCount; i++) {
			childDecorators[i] = getDecoratorTree(children[i]);
		}
		org.emftext.language.java.resource.java.mopp.JavaSyntaxElementDecorator decorator = new org.emftext.language.java.resource.java.mopp.JavaSyntaxElementDecorator(syntaxElement, childDecorators);
		return decorator;
	}
	
	public void decorateTree(org.emftext.language.java.resource.java.mopp.JavaSyntaxElementDecorator decorator, EObject eObject) {
		PrintCountingMap printCountingMap = initializePrintCountingMap(eObject);
		List<org.emftext.language.java.resource.java.mopp.JavaSyntaxElementDecorator> keywordsToPrint = new ArrayList<org.emftext.language.java.resource.java.mopp.JavaSyntaxElementDecorator>();
		decorateTreeBasic(decorator, eObject, printCountingMap, keywordsToPrint);
		for (org.emftext.language.java.resource.java.mopp.JavaSyntaxElementDecorator keywordToPrint : keywordsToPrint) {
			// for keywords the concrete index does not matter, but we must add one to
			// indicate that the keyword needs to be printed here. Thus, we use 0 as index.
			keywordToPrint.addIndexToPrint(0);
		}
	}
	
	/**
	 * Tries to decorate the decorator with an attribute value, or reference held by
	 * the given EObject. Returns true if an attribute value or reference was found.
	 */
	public boolean decorateTreeBasic(org.emftext.language.java.resource.java.mopp.JavaSyntaxElementDecorator decorator, EObject eObject, PrintCountingMap printCountingMap, List<org.emftext.language.java.resource.java.mopp.JavaSyntaxElementDecorator> keywordsToPrint) {
		boolean foundFeatureToPrint = false;
		org.emftext.language.java.resource.java.grammar.JavaSyntaxElement syntaxElement = decorator.getDecoratedElement();
		org.emftext.language.java.resource.java.grammar.JavaCardinality cardinality = syntaxElement.getCardinality();
		boolean isFirstIteration = true;
		while (true) {
			List<org.emftext.language.java.resource.java.mopp.JavaSyntaxElementDecorator> subKeywordsToPrint = new ArrayList<org.emftext.language.java.resource.java.mopp.JavaSyntaxElementDecorator>();
			boolean keepDecorating = false;
			if (syntaxElement instanceof org.emftext.language.java.resource.java.grammar.JavaKeyword) {
				subKeywordsToPrint.add(decorator);
			} else if (syntaxElement instanceof org.emftext.language.java.resource.java.grammar.JavaTerminal) {
				org.emftext.language.java.resource.java.grammar.JavaTerminal terminal = (org.emftext.language.java.resource.java.grammar.JavaTerminal) syntaxElement;
				EStructuralFeature feature = terminal.getFeature();
				if (feature == org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.ANONYMOUS_FEATURE) {
					return false;
				}
				String featureName = feature.getName();
				int countLeft = printCountingMap.getCountLeft(terminal);
				if (countLeft > terminal.getMandatoryOccurencesAfter()) {
					// normally we print the element at the next index
					int indexToPrint = printCountingMap.getNextIndexToPrint(featureName);
					// But, if there are type restrictions for containments, we must choose an index
					// of an element that fits (i.e., which has the correct type)
					if (terminal instanceof org.emftext.language.java.resource.java.grammar.JavaContainment) {
						org.emftext.language.java.resource.java.grammar.JavaContainment containment = (org.emftext.language.java.resource.java.grammar.JavaContainment) terminal;
						indexToPrint = findElementWithCorrectType(eObject, feature, printCountingMap.getIndicesToPrint(featureName), containment);
					}
					if (indexToPrint >= 0) {
						decorator.addIndexToPrint(indexToPrint);
						printCountingMap.addIndexToPrint(featureName, indexToPrint);
						keepDecorating = true;
					}
				}
			}
			if (syntaxElement instanceof org.emftext.language.java.resource.java.grammar.JavaChoice) {
				// for choices we do print only the choice which does print at least one feature
				org.emftext.language.java.resource.java.mopp.JavaSyntaxElementDecorator childToPrint = null;
				for (org.emftext.language.java.resource.java.mopp.JavaSyntaxElementDecorator childDecorator : decorator.getChildDecorators()) {
					// pick first choice as default, will be overridden if a choice that prints a
					// feature is found
					if (childToPrint == null) {
						childToPrint = childDecorator;
					}
					if (doesPrintFeature(childDecorator, eObject, printCountingMap)) {
						childToPrint = childDecorator;
						break;
					}
				}
				keepDecorating |= decorateTreeBasic(childToPrint, eObject, printCountingMap, subKeywordsToPrint);
			} else {
				// for all other syntax element we do print all children
				for (org.emftext.language.java.resource.java.mopp.JavaSyntaxElementDecorator childDecorator : decorator.getChildDecorators()) {
					keepDecorating |= decorateTreeBasic(childDecorator, eObject, printCountingMap, subKeywordsToPrint);
				}
			}
			foundFeatureToPrint |= keepDecorating;
			// only print keywords if a feature was printed or the syntax element is mandatory
			if (cardinality == org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE) {
				keywordsToPrint.addAll(subKeywordsToPrint);
			} else if (cardinality == org.emftext.language.java.resource.java.grammar.JavaCardinality.PLUS) {
				if (isFirstIteration) {
					keywordsToPrint.addAll(subKeywordsToPrint);
				} else {
					if (keepDecorating) {
						keywordsToPrint.addAll(subKeywordsToPrint);
					}
				}
			} else if (keepDecorating && (cardinality == org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR || cardinality == org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK)) {
				keywordsToPrint.addAll(subKeywordsToPrint);
			}
			if (cardinality == org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE || cardinality == org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK) {
				break;
			} else if (!keepDecorating) {
				break;
			}
			isFirstIteration = false;
		}
		return foundFeatureToPrint;
	}
	
	private int findElementWithCorrectType(EObject eObject, EStructuralFeature feature, Set<Integer> indicesToPrint, org.emftext.language.java.resource.java.grammar.JavaContainment containment) {
		// Since the 'ignoreTypeRestrictionsForPrinting' option is set to true, the type
		// restrictions are not considered when printing models.
		boolean ignoreTypeRestrictions = true;
		EClass[] allowedTypes = containment.getAllowedTypes();
		Object value = eObject.eGet(feature);
		if (value instanceof List<?>) {
			List<?> valueList = (List<?>) value;
			int listSize = valueList.size();
			for (int index = 0; index < listSize; index++) {
				if (indicesToPrint.contains(index)) {
					continue;
				}
				Object valueAtIndex = valueList.get(index);
				if (eClassUtil.isInstance(valueAtIndex, allowedTypes) || ignoreTypeRestrictions) {
					return index;
				}
			}
		} else {
			if (eClassUtil.isInstance(value, allowedTypes) || ignoreTypeRestrictions) {
				return 0;
			}
		}
		return -1;
	}
	
	/**
	 * Checks whether decorating the given node will use at least one attribute value,
	 * or reference held by eObject. Returns true if a printable attribute value or
	 * reference was found. This method is used to decide which choice to pick, when
	 * multiple choices are available. We pick the choice that prints at least one
	 * attribute or reference.
	 */
	public boolean doesPrintFeature(org.emftext.language.java.resource.java.mopp.JavaSyntaxElementDecorator decorator, EObject eObject, PrintCountingMap printCountingMap) {
		org.emftext.language.java.resource.java.grammar.JavaSyntaxElement syntaxElement = decorator.getDecoratedElement();
		if (syntaxElement instanceof org.emftext.language.java.resource.java.grammar.JavaTerminal) {
			org.emftext.language.java.resource.java.grammar.JavaTerminal terminal = (org.emftext.language.java.resource.java.grammar.JavaTerminal) syntaxElement;
			EStructuralFeature feature = terminal.getFeature();
			if (feature == org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.ANONYMOUS_FEATURE) {
				return false;
			}
			int countLeft = printCountingMap.getCountLeft(terminal);
			if (countLeft > terminal.getMandatoryOccurencesAfter()) {
				// found a feature to print
				return true;
			}
		}
		for (org.emftext.language.java.resource.java.mopp.JavaSyntaxElementDecorator childDecorator : decorator.getChildDecorators()) {
			if (doesPrintFeature(childDecorator, eObject, printCountingMap)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean printTree(org.emftext.language.java.resource.java.mopp.JavaSyntaxElementDecorator decorator, EObject eObject, List<org.emftext.language.java.resource.java.grammar.JavaFormattingElement> foundFormattingElements, List<org.emftext.language.java.resource.java.mopp.JavaLayoutInformation> layoutInformations) {
		org.emftext.language.java.resource.java.grammar.JavaSyntaxElement printElement = decorator.getDecoratedElement();
		org.emftext.language.java.resource.java.grammar.JavaCardinality cardinality = printElement.getCardinality();
		List<org.emftext.language.java.resource.java.grammar.JavaFormattingElement> cloned = new ArrayList<org.emftext.language.java.resource.java.grammar.JavaFormattingElement>();
		cloned.addAll(foundFormattingElements);
		boolean foundSomethingAtAll = false;
		boolean foundSomethingToPrint;
		while (true) {
			foundSomethingToPrint = false;
			Integer indexToPrint = decorator.getNextIndexToPrint();
			if (indexToPrint != null) {
				if (printElement instanceof org.emftext.language.java.resource.java.grammar.JavaKeyword) {
					printKeyword(eObject, (org.emftext.language.java.resource.java.grammar.JavaKeyword) printElement, foundFormattingElements, layoutInformations);
					foundSomethingToPrint = true;
				} else if (printElement instanceof org.emftext.language.java.resource.java.grammar.JavaPlaceholder) {
					org.emftext.language.java.resource.java.grammar.JavaPlaceholder placeholder = (org.emftext.language.java.resource.java.grammar.JavaPlaceholder) printElement;
					printFeature(eObject, placeholder, indexToPrint, foundFormattingElements, layoutInformations);
					foundSomethingToPrint = true;
				} else if (printElement instanceof org.emftext.language.java.resource.java.grammar.JavaContainment) {
					org.emftext.language.java.resource.java.grammar.JavaContainment containment = (org.emftext.language.java.resource.java.grammar.JavaContainment) printElement;
					printContainedObject(eObject, containment, indexToPrint, foundFormattingElements, layoutInformations);
					foundSomethingToPrint = true;
				} else if (printElement instanceof org.emftext.language.java.resource.java.grammar.JavaBooleanTerminal) {
					org.emftext.language.java.resource.java.grammar.JavaBooleanTerminal booleanTerminal = (org.emftext.language.java.resource.java.grammar.JavaBooleanTerminal) printElement;
					printBooleanTerminal(eObject, booleanTerminal, indexToPrint, foundFormattingElements, layoutInformations);
					foundSomethingToPrint = true;
				} else if (printElement instanceof org.emftext.language.java.resource.java.grammar.JavaEnumerationTerminal) {
					org.emftext.language.java.resource.java.grammar.JavaEnumerationTerminal enumTerminal = (org.emftext.language.java.resource.java.grammar.JavaEnumerationTerminal) printElement;
					printEnumerationTerminal(eObject, enumTerminal, indexToPrint, foundFormattingElements, layoutInformations);
					foundSomethingToPrint = true;
				}
			}
			if (foundSomethingToPrint) {
				foundSomethingAtAll = true;
			}
			if (printElement instanceof org.emftext.language.java.resource.java.grammar.JavaWhiteSpace) {
				foundFormattingElements.add((org.emftext.language.java.resource.java.grammar.JavaWhiteSpace) printElement);
			}
			if (printElement instanceof org.emftext.language.java.resource.java.grammar.JavaLineBreak) {
				foundFormattingElements.add((org.emftext.language.java.resource.java.grammar.JavaLineBreak) printElement);
			}
			for (org.emftext.language.java.resource.java.mopp.JavaSyntaxElementDecorator childDecorator : decorator.getChildDecorators()) {
				foundSomethingToPrint |= printTree(childDecorator, eObject, foundFormattingElements, layoutInformations);
				org.emftext.language.java.resource.java.grammar.JavaSyntaxElement decoratedElement = decorator.getDecoratedElement();
				if (foundSomethingToPrint && decoratedElement instanceof org.emftext.language.java.resource.java.grammar.JavaChoice) {
					break;
				}
			}
			if (cardinality == org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE || cardinality == org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK) {
				break;
			} else if (!foundSomethingToPrint) {
				break;
			}
		}
		// only print formatting elements if a feature was printed or the syntax element
		// is mandatory
		if (!foundSomethingAtAll && (cardinality == org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR || cardinality == org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK)) {
			foundFormattingElements.clear();
			foundFormattingElements.addAll(cloned);
		}
		return foundSomethingToPrint;
	}
	
	public void printKeyword(EObject eObject, org.emftext.language.java.resource.java.grammar.JavaKeyword keyword, List<org.emftext.language.java.resource.java.grammar.JavaFormattingElement> foundFormattingElements, List<org.emftext.language.java.resource.java.mopp.JavaLayoutInformation> layoutInformations) {
		org.emftext.language.java.resource.java.mopp.JavaLayoutInformation keywordLayout = getLayoutInformation(layoutInformations, keyword, null, eObject);
		printFormattingElements(eObject, foundFormattingElements, layoutInformations, keywordLayout);
		String value = keyword.getValue();
		tokenOutputStream.add(new PrintToken(value, "'" + org.emftext.language.java.resource.java.util.JavaStringUtil.escapeToANTLRKeyword(value) + "'", eObject));
	}
	
	public void printFeature(EObject eObject, org.emftext.language.java.resource.java.grammar.JavaPlaceholder placeholder, int count, List<org.emftext.language.java.resource.java.grammar.JavaFormattingElement> foundFormattingElements, List<org.emftext.language.java.resource.java.mopp.JavaLayoutInformation> layoutInformations) {
		EStructuralFeature feature = placeholder.getFeature();
		if (feature instanceof EAttribute) {
			printAttribute(eObject, (EAttribute) feature, placeholder, count, foundFormattingElements, layoutInformations);
		} else {
			printReference(eObject, (EReference) feature, placeholder, count, foundFormattingElements, layoutInformations);
		}
	}
	
	public void printAttribute(EObject eObject, EAttribute attribute, org.emftext.language.java.resource.java.grammar.JavaPlaceholder placeholder, int index, List<org.emftext.language.java.resource.java.grammar.JavaFormattingElement> foundFormattingElements, List<org.emftext.language.java.resource.java.mopp.JavaLayoutInformation> layoutInformations) {
		String result = null;
		Object attributeValue = org.emftext.language.java.resource.java.util.JavaEObjectUtil.getFeatureValue(eObject, attribute, index);
		org.emftext.language.java.resource.java.mopp.JavaLayoutInformation attributeLayout = getLayoutInformation(layoutInformations, placeholder, attributeValue, eObject);
		String visibleTokenText = getVisibleTokenText(attributeLayout);
		// if there is text for the attribute we use it
		if (visibleTokenText != null) {
			result = visibleTokenText;
		}
		
		if (result == null) {
			// if no text is available, the attribute is deresolved to obtain its textual
			// representation
			org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver(placeholder.getTokenName());
			tokenResolver.setOptions(getOptions());
			String deResolvedValue = tokenResolver.deResolve(attributeValue, attribute, eObject);
			result = deResolvedValue;
		}
		
		if (result != null && !"".equals(result)) {
			printFormattingElements(eObject, foundFormattingElements, layoutInformations, attributeLayout);
			// write result to the output stream
			tokenOutputStream.add(new PrintToken(result, placeholder.getTokenName(), eObject));
		}
	}
	
	
	public void printBooleanTerminal(EObject eObject, org.emftext.language.java.resource.java.grammar.JavaBooleanTerminal booleanTerminal, int index, List<org.emftext.language.java.resource.java.grammar.JavaFormattingElement> foundFormattingElements, List<org.emftext.language.java.resource.java.mopp.JavaLayoutInformation> layoutInformations) {
		EAttribute attribute = booleanTerminal.getAttribute();
		String result = null;
		Object attributeValue = org.emftext.language.java.resource.java.util.JavaEObjectUtil.getFeatureValue(eObject, attribute, index);
		org.emftext.language.java.resource.java.mopp.JavaLayoutInformation attributeLayout = getLayoutInformation(layoutInformations, booleanTerminal, attributeValue, eObject);
		String visibleTokenText = getVisibleTokenText(attributeLayout);
		// if there is text for the attribute we use it
		if (visibleTokenText != null) {
			result = visibleTokenText;
		}
		
		if (result == null) {
			// if no text is available, the boolean attribute is converted to its textual
			// representation using the literals of the boolean terminal
			if (Boolean.TRUE.equals(attributeValue)) {
				result = booleanTerminal.getTrueLiteral();
			} else {
				result = booleanTerminal.getFalseLiteral();
			}
		}
		
		if (result != null && !"".equals(result)) {
			printFormattingElements(eObject, foundFormattingElements, layoutInformations, attributeLayout);
			// write result to the output stream
			tokenOutputStream.add(new PrintToken(result, "'" + org.emftext.language.java.resource.java.util.JavaStringUtil.escapeToANTLRKeyword(result) + "'", eObject));
		}
	}
	
	
	public void printEnumerationTerminal(EObject eObject, org.emftext.language.java.resource.java.grammar.JavaEnumerationTerminal enumTerminal, int index, List<org.emftext.language.java.resource.java.grammar.JavaFormattingElement> foundFormattingElements, List<org.emftext.language.java.resource.java.mopp.JavaLayoutInformation> layoutInformations) {
		EAttribute attribute = enumTerminal.getAttribute();
		String result = null;
		Object attributeValue = org.emftext.language.java.resource.java.util.JavaEObjectUtil.getFeatureValue(eObject, attribute, index);
		org.emftext.language.java.resource.java.mopp.JavaLayoutInformation attributeLayout = getLayoutInformation(layoutInformations, enumTerminal, attributeValue, eObject);
		String visibleTokenText = getVisibleTokenText(attributeLayout);
		// if there is text for the attribute we use it
		if (visibleTokenText != null) {
			result = visibleTokenText;
		}
		
		if (result == null) {
			// if no text is available, the enumeration attribute is converted to its textual
			// representation using the literals of the enumeration terminal
			assert attributeValue instanceof Enumerator;
			result = enumTerminal.getText(((Enumerator) attributeValue).getName());
		}
		
		if (result != null && !"".equals(result)) {
			printFormattingElements(eObject, foundFormattingElements, layoutInformations, attributeLayout);
			// write result to the output stream
			tokenOutputStream.add(new PrintToken(result, "'" + org.emftext.language.java.resource.java.util.JavaStringUtil.escapeToANTLRKeyword(result) + "'", eObject));
		}
	}
	
	
	public void printContainedObject(EObject eObject, org.emftext.language.java.resource.java.grammar.JavaContainment containment, int index, List<org.emftext.language.java.resource.java.grammar.JavaFormattingElement> foundFormattingElements, List<org.emftext.language.java.resource.java.mopp.JavaLayoutInformation> layoutInformations) {
		EStructuralFeature reference = containment.getFeature();
		Object o = org.emftext.language.java.resource.java.util.JavaEObjectUtil.getFeatureValue(eObject, reference, index);
		// save current number of tabs to restore them after printing the contained object
		int oldTabsBeforeCurrentObject = tabsBeforeCurrentObject;
		int oldCurrentTabs = currentTabs;
		// use current number of tabs to indent contained object. we do not directly set
		// 'tabsBeforeCurrentObject' because the first element of the new object must be
		// printed with the old number of tabs.
		startedPrintingContainedObject = false;
		currentTabs = 0;
		doPrint((EObject) o, foundFormattingElements);
		// restore number of tabs after printing the contained object
		tabsBeforeCurrentObject = oldTabsBeforeCurrentObject;
		currentTabs = oldCurrentTabs;
	}
	
	public void printFormattingElements(EObject eObject, List<org.emftext.language.java.resource.java.grammar.JavaFormattingElement> foundFormattingElements, List<org.emftext.language.java.resource.java.mopp.JavaLayoutInformation> layoutInformations, org.emftext.language.java.resource.java.mopp.JavaLayoutInformation layoutInformation) {
		String hiddenTokenText = getHiddenTokenText(layoutInformation);
		if (hiddenTokenText != null) {
			// removed used information
			if (layoutInformations != null) {
				layoutInformations.remove(layoutInformation);
			}
			tokenOutputStream.add(new PrintToken(hiddenTokenText, null, eObject));
			foundFormattingElements.clear();
			startedPrintingObject = false;
			setTabsBeforeCurrentObject(0);
			return;
		}
		int printedTabs = 0;
		if (foundFormattingElements.size() > 0) {
			for (org.emftext.language.java.resource.java.grammar.JavaFormattingElement foundFormattingElement : foundFormattingElements) {
				if (foundFormattingElement instanceof org.emftext.language.java.resource.java.grammar.JavaWhiteSpace) {
					int amount = ((org.emftext.language.java.resource.java.grammar.JavaWhiteSpace) foundFormattingElement).getAmount();
					for (int i = 0; i < amount; i++) {
						tokenOutputStream.add(createSpaceToken(eObject));
					}
				}
				if (foundFormattingElement instanceof org.emftext.language.java.resource.java.grammar.JavaLineBreak) {
					currentTabs = ((org.emftext.language.java.resource.java.grammar.JavaLineBreak) foundFormattingElement).getTabs();
					printedTabs += currentTabs;
					tokenOutputStream.add(createNewLineToken(eObject));
					for (int i = 0; i < tabsBeforeCurrentObject + currentTabs; i++) {
						tokenOutputStream.add(createTabToken(eObject));
					}
				}
			}
			foundFormattingElements.clear();
			startedPrintingObject = false;
		} else {
			if (startedPrintingObject) {
				// if no elements have been printed yet, we do not add the default token space,
				// because spaces before the first element are not desired.
				startedPrintingObject = false;
			} else {
				if (!handleTokenSpaceAutomatically) {
					tokenOutputStream.add(new PrintToken(getWhiteSpaceString(tokenSpace), null, eObject));
				}
			}
		}
		// after printing the first element, we can use the new number of tabs.
		setTabsBeforeCurrentObject(printedTabs);
	}
	
	private void setTabsBeforeCurrentObject(int tabs) {
		if (startedPrintingContainedObject) {
			return;
		}
		tabsBeforeCurrentObject = tabsBeforeCurrentObject + tabs;
		startedPrintingContainedObject = true;
	}
	
	@SuppressWarnings("unchecked")
	public void printReference(EObject eObject, EReference reference, org.emftext.language.java.resource.java.grammar.JavaPlaceholder placeholder, int index, List<org.emftext.language.java.resource.java.grammar.JavaFormattingElement> foundFormattingElements, List<org.emftext.language.java.resource.java.mopp.JavaLayoutInformation> layoutInformations) {
		String tokenName = placeholder.getTokenName();
		Object referencedObject = org.emftext.language.java.resource.java.util.JavaEObjectUtil.getFeatureValue(eObject, reference, index, false);
		// first add layout before the reference
		org.emftext.language.java.resource.java.mopp.JavaLayoutInformation referenceLayout = getLayoutInformation(layoutInformations, placeholder, referencedObject, eObject);
		printFormattingElements(eObject, foundFormattingElements, layoutInformations, referenceLayout);
		// proxy objects must be printed differently
		String deresolvedReference = null;
		if (referencedObject instanceof EObject) {
			EObject eObjectToDeResolve = (EObject) referencedObject;
			if (eObjectToDeResolve.eIsProxy()) {
				deresolvedReference = ((InternalEObject) eObjectToDeResolve).eProxyURI().fragment();
				// If the proxy was created by EMFText, we can try to recover the identifier from
				// the proxy URI
				if (deresolvedReference != null && deresolvedReference.startsWith(org.emftext.language.java.resource.java.IJavaContextDependentURIFragment.INTERNAL_URI_FRAGMENT_PREFIX)) {
					deresolvedReference = deresolvedReference.substring(org.emftext.language.java.resource.java.IJavaContextDependentURIFragment.INTERNAL_URI_FRAGMENT_PREFIX.length());
					deresolvedReference = deresolvedReference.substring(deresolvedReference.indexOf("_") + 1);
				}
			}
		}
		if (deresolvedReference == null) {
			// NC-References must always be printed by deresolving the reference. We cannot
			// use the visible token information, because deresolving usually depends on
			// attribute values of the referenced object instead of the object itself.
			@SuppressWarnings("rawtypes")
			org.emftext.language.java.resource.java.IJavaReferenceResolver referenceResolver = getReferenceResolverSwitch().getResolver(reference);
			referenceResolver.setOptions(getOptions());
			deresolvedReference = referenceResolver.deResolve((EObject) referencedObject, eObject, reference);
		}
		org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver(tokenName);
		tokenResolver.setOptions(getOptions());
		String deresolvedToken = tokenResolver.deResolve(deresolvedReference, reference, eObject);
		// write result to output stream
		tokenOutputStream.add(new PrintToken(deresolvedToken, tokenName, eObject));
	}
	
	@SuppressWarnings("unchecked")
	public PrintCountingMap initializePrintCountingMap(EObject eObject) {
		// The PrintCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		PrintCountingMap printCountingMap = new PrintCountingMap();
		List<EStructuralFeature> features = eObject.eClass().getEAllStructuralFeatures();
		for (EStructuralFeature feature : features) {
			// We get the feature value without resolving it, because resolving is not
			// required to count the number of elements that are referenced by the feature.
			// Moreover, triggering reference resolving is not desired here, because we'd also
			// like to print models that contain unresolved references.
			Object featureValue = eObject.eGet(feature, false);
			if (featureValue != null) {
				if (featureValue instanceof List<?>) {
					printCountingMap.setFeatureValues(feature.getName(), (List<Object>) featureValue);
				} else {
					printCountingMap.setFeatureValues(feature.getName(), Collections.singletonList(featureValue));
				}
			} else {
				printCountingMap.setFeatureValues(feature.getName(), null);
			}
		}
		return printCountingMap;
	}
	
	public Map<?,?> getOptions() {
		return options;
	}
	
	public void setOptions(Map<?,?> options) {
		this.options = options;
	}
	
	public String getEncoding() {
		return encoding;
	}
	
	public void setEncoding(String encoding) {
		if (encoding != null) {
			this.encoding = encoding;
		}
	}
	
	public org.emftext.language.java.resource.java.IJavaTextResource getResource() {
		return resource;
	}
	
	protected org.emftext.language.java.resource.java.mopp.JavaReferenceResolverSwitch getReferenceResolverSwitch() {
		return (org.emftext.language.java.resource.java.mopp.JavaReferenceResolverSwitch) new org.emftext.language.java.resource.java.mopp.JavaMetaInformation().getReferenceResolverSwitch();
	}
	
	protected void addWarningToResource(final String errorMessage, EObject cause) {
		org.emftext.language.java.resource.java.IJavaTextResource resource = getResource();
		if (resource == null) {
			// the resource can be null if the printer is used stand alone
			return;
		}
		resource.addProblem(new org.emftext.language.java.resource.java.mopp.JavaProblem(errorMessage, org.emftext.language.java.resource.java.JavaEProblemType.PRINT_PROBLEM, org.emftext.language.java.resource.java.JavaEProblemSeverity.WARNING), cause);
	}
	
	protected org.emftext.language.java.resource.java.mopp.JavaLayoutInformationAdapter getLayoutInformationAdapter(EObject element) {
		for (Adapter adapter : element.eAdapters()) {
			if (adapter instanceof org.emftext.language.java.resource.java.mopp.JavaLayoutInformationAdapter) {
				return (org.emftext.language.java.resource.java.mopp.JavaLayoutInformationAdapter) adapter;
			}
		}
		org.emftext.language.java.resource.java.mopp.JavaLayoutInformationAdapter newAdapter = new org.emftext.language.java.resource.java.mopp.JavaLayoutInformationAdapter();
		element.eAdapters().add(newAdapter);
		return newAdapter;
	}
	
	private org.emftext.language.java.resource.java.mopp.JavaLayoutInformation getLayoutInformation(List<org.emftext.language.java.resource.java.mopp.JavaLayoutInformation> layoutInformations, org.emftext.language.java.resource.java.grammar.JavaSyntaxElement syntaxElement, Object object, EObject container) {
		for (org.emftext.language.java.resource.java.mopp.JavaLayoutInformation layoutInformation : layoutInformations) {
			if (syntaxElement == layoutInformation.getSyntaxElement()) {
				if (object == null) {
					return layoutInformation;
				}
				// The layout information adapter must only try to resolve the object it refers
				// to, if we compare with a non-proxy object. If we're printing a resource that
				// contains proxy objects, resolving must not be triggered.
				boolean isNoProxy = true;
				if (object instanceof EObject) {
					EObject eObject = (EObject) object;
					isNoProxy = !eObject.eIsProxy();
				}
				if (isSame(object, layoutInformation.getObject(container, isNoProxy))) {
					return layoutInformation;
				}
			}
		}
		return null;
	}
	
	public List<org.emftext.language.java.resource.java.mopp.JavaLayoutInformation> getCopyOfLayoutInformation(EObject eObject) {
		org.emftext.language.java.resource.java.mopp.JavaLayoutInformationAdapter layoutInformationAdapter = getLayoutInformationAdapter(eObject);
		List<org.emftext.language.java.resource.java.mopp.JavaLayoutInformation> originalLayoutInformations = layoutInformationAdapter.getLayoutInformations();
		// create a copy of the original list of layout information object in order to be
		// able to remove used informations during printing
		List<org.emftext.language.java.resource.java.mopp.JavaLayoutInformation> layoutInformations = new ArrayList<org.emftext.language.java.resource.java.mopp.JavaLayoutInformation>(originalLayoutInformations.size());
		layoutInformations.addAll(originalLayoutInformations);
		return layoutInformations;
	}
	
	private String getHiddenTokenText(org.emftext.language.java.resource.java.mopp.JavaLayoutInformation layoutInformation) {
		if (layoutInformation != null) {
			return layoutInformation.getHiddenTokenText();
		} else {
			return null;
		}
	}
	
	private String getVisibleTokenText(org.emftext.language.java.resource.java.mopp.JavaLayoutInformation layoutInformation) {
		if (layoutInformation != null) {
			return layoutInformation.getVisibleTokenText();
		} else {
			return null;
		}
	}
	
	protected String getWhiteSpaceString(int count) {
		return getRepeatingString(count, ' ');
	}
	
	private String getRepeatingString(int count, char character) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < count; i++) {
			result.append(character);
		}
		return result.toString();
	}
	
	public void setHandleTokenSpaceAutomatically(boolean handleTokenSpaceAutomatically) {
		this.handleTokenSpaceAutomatically = handleTokenSpaceAutomatically;
	}
	
	public void setTokenSpace(int tokenSpace) {
		this.tokenSpace = tokenSpace;
	}
	
	/**
	 * Prints the current tokenOutputStream to the given writer (as it is).
	 */
	public void printBasic(PrintWriter writer) throws IOException {
		for (PrintToken nextToken : tokenOutputStream) {
			writer.write(nextToken.getText());
		}
	}
	
	/**
	 * <p>
	 * Prints the current tokenOutputStream to the given writer.
	 * </p>
	 * 
	 * <p>
	 * This methods implements smart whitespace printing. It does so by writing output
	 * to a token stream instead of printing the raw token text to a PrintWriter.
	 * Tokens in this stream hold both the text and the type of the token (i.e., its
	 * name).
	 * </p>
	 * 
	 * <p>
	 * To decide where whitespace is needed, sequences of successive tokens are
	 * searched that can be printed without separating whitespace. To determine such
	 * groups we start with two successive non-whitespace tokens, concatenate their
	 * text and use the generated ANTLR lexer to split the text. If the resulting
	 * token sequence of the concatenated text is exactly the same as the one that is
	 * to be printed, no whitespace is needed. The tokens in the sequence are checked
	 * both regarding their type and their text. If two tokens successfully form a
	 * group a third one is added and so on.
	 * </p>
	 */
	public void printSmart(PrintWriter writer) throws IOException {
		// stores the text of the current group of tokens. this text is given to the lexer
		// to check whether it can be correctly scanned.
		StringBuilder currentBlock = new StringBuilder();
		// stores the index of the first token of the current group.
		int currentBlockStart = 0;
		// stores the text that was already successfully checked (i.e., is can be scanned
		// correctly and can thus be printed).
		String validBlock = "";
		char lastCharWritten = ' ';
		for (int i = 0; i < tokenOutputStream.size(); i++) {
			PrintToken tokenI = tokenOutputStream.get(i);
			currentBlock.append(tokenI.getText());
			// if declared or preserved whitespace is found - print block
			if (tokenI.getTokenName() == null) {
				char[] charArray = currentBlock.toString().toCharArray();
				writer.write(charArray);
				if (charArray.length > 0) {
					lastCharWritten = charArray[charArray.length - 1];
				}
				// reset all values
				currentBlock = new StringBuilder();
				currentBlockStart = i + 1;
				validBlock = "";
				continue;
			}
			// now check whether the current block can be scanned
			org.emftext.language.java.resource.java.IJavaTextScanner scanner = new org.emftext.language.java.resource.java.mopp.JavaMetaInformation().createLexer();
			scanner.setText(currentBlock.toString());
			// retrieve all tokens from scanner and add them to list 'tempTokens'
			List<org.emftext.language.java.resource.java.IJavaTextToken> tempTokens = new ArrayList<org.emftext.language.java.resource.java.IJavaTextToken>();
			org.emftext.language.java.resource.java.IJavaTextToken nextToken = scanner.getNextToken();
			while (nextToken != null && nextToken.getText() != null) {
				tempTokens.add(nextToken);
				nextToken = scanner.getNextToken();
			}
			boolean sequenceIsValid = true;
			// check whether the current block was scanned to the same token sequence
			for (int t = 0; t < tempTokens.size(); t++) {
				PrintToken printTokenT = tokenOutputStream.get(currentBlockStart + t);
				org.emftext.language.java.resource.java.IJavaTextToken tempToken = tempTokens.get(t);
				if (!tempToken.getText().equals(printTokenT.getText())) {
					sequenceIsValid = false;
					break;
				}
				String commonTokenName = tempToken.getName();
				String printTokenName = printTokenT.getTokenName();
				if (printTokenName.length() > 2 && printTokenName.startsWith("'") && printTokenName.endsWith("'")) {
					printTokenName = printTokenName.substring(1, printTokenName.length() - 1);
				}
				if (!commonTokenName.equals(printTokenName)) {
					sequenceIsValid = false;
					break;
				}
			}
			if (sequenceIsValid) {
				// sequence is still valid, try adding one more token in the next iteration of the
				// loop
				validBlock += tokenI.getText();
			} else {
				// sequence is not valid, must print whitespace to separate tokens
				// print text that is valid so far
				char[] charArray = validBlock.toString().toCharArray();
				writer.write(charArray);
				if (charArray.length > 0) {
					lastCharWritten = charArray[charArray.length - 1];
				}
				// print separating whitespace
				// if no whitespace (or tab or linebreak) is already there
				if (lastCharWritten != ' ' && lastCharWritten != '\t' && lastCharWritten != '\n' && lastCharWritten != '\r') {
					lastCharWritten = ' ';
					writer.write(lastCharWritten);
				}
				// add current token as initial value for next iteration
				currentBlock = new StringBuilder(tokenI.getText());
				currentBlockStart = i;
				validBlock = tokenI.getText();
			}
		}
		// flush remaining valid text to writer
		writer.write(validBlock);
	}
	
	private boolean isSame(Object o1, Object o2) {
		if (o1 instanceof String || o1 instanceof Integer || o1 instanceof Long || o1 instanceof Byte || o1 instanceof Short || o1 instanceof Float || o2 instanceof Double) {
			return o1.equals(o2);
		}
		return o1 == o2;
	}
	
	protected List<Class<?>> getAllowedTypes(org.emftext.language.java.resource.java.grammar.JavaTerminal terminal) {
		List<Class<?>> allowedTypes = new ArrayList<Class<?>>();
		allowedTypes.add(terminal.getFeature().getEType().getInstanceClass());
		if (terminal instanceof org.emftext.language.java.resource.java.grammar.JavaContainment) {
			org.emftext.language.java.resource.java.grammar.JavaContainment printingContainment = (org.emftext.language.java.resource.java.grammar.JavaContainment) terminal;
			EClass[] typeRestrictions = printingContainment.getAllowedTypes();
			if (typeRestrictions != null && typeRestrictions.length > 0) {
				allowedTypes.clear();
				for (EClass eClass : typeRestrictions) {
					allowedTypes.add(eClass.getInstanceClass());
				}
			}
		}
		return allowedTypes;
	}
	
	protected PrintToken createSpaceToken(EObject container) {
		return new PrintToken(" ", null, container);
	}
	
	protected PrintToken createTabToken(EObject container) {
		return new PrintToken("\t", null, container);
	}
	
	protected PrintToken createNewLineToken(EObject container) {
		if (options != null) {
			Object lineBreaks = options.get(org.emftext.language.java.resource.java.IJavaOptions.LINE_DELIMITER_FOR_PRINTING);
			if (lineBreaks != null && lineBreaks instanceof String) {
				return new PrintToken((String) lineBreaks, null, container);
			}
		}
		return new PrintToken(NEW_LINE, null, container);
	}
	
}
