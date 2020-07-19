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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * This class provides an implementation of the
 * org.emftext.language.java.resource.java.IJavaTextDiagnostic interface. However,
 * it is recommended to use the
 * org.emftext.language.java.resource.java.mopp.JavaPrinter2 instead, because it
 * provides advanced printing features. There are even some features (e.g.,
 * printing enumeration terminals) which are only supported by that class.
 */
public class JavaPrinter implements org.emftext.language.java.resource.java.IJavaTextPrinter {
	
	protected org.emftext.language.java.resource.java.IJavaTokenResolverFactory tokenResolverFactory = new org.emftext.language.java.resource.java.mopp.JavaTokenResolverFactory();
	
	protected OutputStream outputStream;
	
	/**
	 * Holds the resource that is associated with this printer. This may be null if
	 * the printer is used stand alone.
	 */
	private org.emftext.language.java.resource.java.IJavaTextResource resource;
	
	private Map<?, ?> options;
	private String encoding = System.getProperty("file.encoding");
	
	public JavaPrinter(OutputStream outputStream, org.emftext.language.java.resource.java.IJavaTextResource resource) {
		super();
		this.outputStream = outputStream;
		this.resource = resource;
	}
	
	protected int matchCount(Map<String, Integer> featureCounter, Collection<String> needed) {
		int pos = 0;
		int neg = 0;
		
		for (String featureName : featureCounter.keySet()) {
			if (needed.contains(featureName)) {
				int value = featureCounter.get(featureName);
				if (value == 0) {
					neg += 1;
				} else {
					pos += 1;
				}
			}
		}
		return neg > 0 ? -neg : pos;
	}
	
	protected void doPrint(EObject element, PrintWriter out, String globaltab) {
		if (element == null) {
			throw new IllegalArgumentException("Nothing to write.");
		}
		if (out == null) {
			throw new IllegalArgumentException("Nothing to write on.");
		}
		
		if (element instanceof org.emftext.language.java.containers.EmptyModel) {
			print_org_emftext_language_java_containers_EmptyModel((org.emftext.language.java.containers.EmptyModel) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.containers.Package) {
			print_org_emftext_language_java_containers_Package((org.emftext.language.java.containers.Package) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.containers.CompilationUnit) {
			print_org_emftext_language_java_containers_CompilationUnit((org.emftext.language.java.containers.CompilationUnit) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.imports.ClassifierImport) {
			print_org_emftext_language_java_imports_ClassifierImport((org.emftext.language.java.imports.ClassifierImport) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.imports.PackageImport) {
			print_org_emftext_language_java_imports_PackageImport((org.emftext.language.java.imports.PackageImport) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.imports.StaticMemberImport) {
			print_org_emftext_language_java_imports_StaticMemberImport((org.emftext.language.java.imports.StaticMemberImport) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.imports.StaticClassifierImport) {
			print_org_emftext_language_java_imports_StaticClassifierImport((org.emftext.language.java.imports.StaticClassifierImport) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.classifiers.Class) {
			print_org_emftext_language_java_classifiers_Class((org.emftext.language.java.classifiers.Class) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.classifiers.AnonymousClass) {
			print_org_emftext_language_java_classifiers_AnonymousClass((org.emftext.language.java.classifiers.AnonymousClass) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.classifiers.Interface) {
			print_org_emftext_language_java_classifiers_Interface((org.emftext.language.java.classifiers.Interface) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.classifiers.Enumeration) {
			print_org_emftext_language_java_classifiers_Enumeration((org.emftext.language.java.classifiers.Enumeration) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.classifiers.Annotation) {
			print_org_emftext_language_java_classifiers_Annotation((org.emftext.language.java.classifiers.Annotation) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.annotations.AnnotationInstance) {
			print_org_emftext_language_java_annotations_AnnotationInstance((org.emftext.language.java.annotations.AnnotationInstance) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.annotations.SingleAnnotationParameter) {
			print_org_emftext_language_java_annotations_SingleAnnotationParameter((org.emftext.language.java.annotations.SingleAnnotationParameter) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.annotations.AnnotationParameterList) {
			print_org_emftext_language_java_annotations_AnnotationParameterList((org.emftext.language.java.annotations.AnnotationParameterList) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.annotations.AnnotationAttributeSetting) {
			print_org_emftext_language_java_annotations_AnnotationAttributeSetting((org.emftext.language.java.annotations.AnnotationAttributeSetting) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.generics.TypeParameter) {
			print_org_emftext_language_java_generics_TypeParameter((org.emftext.language.java.generics.TypeParameter) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.members.EnumConstant) {
			print_org_emftext_language_java_members_EnumConstant((org.emftext.language.java.members.EnumConstant) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.Block) {
			print_org_emftext_language_java_statements_Block((org.emftext.language.java.statements.Block) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.members.Constructor) {
			print_org_emftext_language_java_members_Constructor((org.emftext.language.java.members.Constructor) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.members.ClassMethod) {
			print_org_emftext_language_java_members_ClassMethod((org.emftext.language.java.members.ClassMethod) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.annotations.AnnotationAttribute) {
			print_org_emftext_language_java_annotations_AnnotationAttribute((org.emftext.language.java.annotations.AnnotationAttribute) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.parameters.OrdinaryParameter) {
			print_org_emftext_language_java_parameters_OrdinaryParameter((org.emftext.language.java.parameters.OrdinaryParameter) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.parameters.VariableLengthParameter) {
			print_org_emftext_language_java_parameters_VariableLengthParameter((org.emftext.language.java.parameters.VariableLengthParameter) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.variables.LocalVariable) {
			print_org_emftext_language_java_variables_LocalVariable((org.emftext.language.java.variables.LocalVariable) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.LocalVariableStatement) {
			print_org_emftext_language_java_statements_LocalVariableStatement((org.emftext.language.java.statements.LocalVariableStatement) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.variables.AdditionalLocalVariable) {
			print_org_emftext_language_java_variables_AdditionalLocalVariable((org.emftext.language.java.variables.AdditionalLocalVariable) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.members.Field) {
			print_org_emftext_language_java_members_Field((org.emftext.language.java.members.Field) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.members.AdditionalField) {
			print_org_emftext_language_java_members_AdditionalField((org.emftext.language.java.members.AdditionalField) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.members.EmptyMember) {
			print_org_emftext_language_java_members_EmptyMember((org.emftext.language.java.members.EmptyMember) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.instantiations.NewConstructorCall) {
			print_org_emftext_language_java_instantiations_NewConstructorCall((org.emftext.language.java.instantiations.NewConstructorCall) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.instantiations.ExplicitConstructorCall) {
			print_org_emftext_language_java_instantiations_ExplicitConstructorCall((org.emftext.language.java.instantiations.ExplicitConstructorCall) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.arrays.ArrayInstantiationByValuesTyped) {
			print_org_emftext_language_java_arrays_ArrayInstantiationByValuesTyped((org.emftext.language.java.arrays.ArrayInstantiationByValuesTyped) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.arrays.ArrayInstantiationByValuesUntyped) {
			print_org_emftext_language_java_arrays_ArrayInstantiationByValuesUntyped((org.emftext.language.java.arrays.ArrayInstantiationByValuesUntyped) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.arrays.ArrayInstantiationBySize) {
			print_org_emftext_language_java_arrays_ArrayInstantiationBySize((org.emftext.language.java.arrays.ArrayInstantiationBySize) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.arrays.ArrayInitializer) {
			print_org_emftext_language_java_arrays_ArrayInitializer((org.emftext.language.java.arrays.ArrayInitializer) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.arrays.ArraySelector) {
			print_org_emftext_language_java_arrays_ArraySelector((org.emftext.language.java.arrays.ArraySelector) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.types.NamespaceClassifierReference) {
			print_org_emftext_language_java_types_NamespaceClassifierReference((org.emftext.language.java.types.NamespaceClassifierReference) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.types.ClassifierReference) {
			print_org_emftext_language_java_types_ClassifierReference((org.emftext.language.java.types.ClassifierReference) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.references.MethodCall) {
			print_org_emftext_language_java_references_MethodCall((org.emftext.language.java.references.MethodCall) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.references.IdentifierReference) {
			print_org_emftext_language_java_references_IdentifierReference((org.emftext.language.java.references.IdentifierReference) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.references.ReflectiveClassReference) {
			print_org_emftext_language_java_references_ReflectiveClassReference((org.emftext.language.java.references.ReflectiveClassReference) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.references.SelfReference) {
			print_org_emftext_language_java_references_SelfReference((org.emftext.language.java.references.SelfReference) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.references.PrimitiveTypeReference) {
			print_org_emftext_language_java_references_PrimitiveTypeReference((org.emftext.language.java.references.PrimitiveTypeReference) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.This) {
			print_org_emftext_language_java_literals_This((org.emftext.language.java.literals.This) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.Super) {
			print_org_emftext_language_java_literals_Super((org.emftext.language.java.literals.Super) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.references.StringReference) {
			print_org_emftext_language_java_references_StringReference((org.emftext.language.java.references.StringReference) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.generics.QualifiedTypeArgument) {
			print_org_emftext_language_java_generics_QualifiedTypeArgument((org.emftext.language.java.generics.QualifiedTypeArgument) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.generics.UnknownTypeArgument) {
			print_org_emftext_language_java_generics_UnknownTypeArgument((org.emftext.language.java.generics.UnknownTypeArgument) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.generics.ExtendsTypeArgument) {
			print_org_emftext_language_java_generics_ExtendsTypeArgument((org.emftext.language.java.generics.ExtendsTypeArgument) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.generics.SuperTypeArgument) {
			print_org_emftext_language_java_generics_SuperTypeArgument((org.emftext.language.java.generics.SuperTypeArgument) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.Assert) {
			print_org_emftext_language_java_statements_Assert((org.emftext.language.java.statements.Assert) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.Condition) {
			print_org_emftext_language_java_statements_Condition((org.emftext.language.java.statements.Condition) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.ForLoop) {
			print_org_emftext_language_java_statements_ForLoop((org.emftext.language.java.statements.ForLoop) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.ForEachLoop) {
			print_org_emftext_language_java_statements_ForEachLoop((org.emftext.language.java.statements.ForEachLoop) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.DoWhileLoop) {
			print_org_emftext_language_java_statements_DoWhileLoop((org.emftext.language.java.statements.DoWhileLoop) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.EmptyStatement) {
			print_org_emftext_language_java_statements_EmptyStatement((org.emftext.language.java.statements.EmptyStatement) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.SynchronizedBlock) {
			print_org_emftext_language_java_statements_SynchronizedBlock((org.emftext.language.java.statements.SynchronizedBlock) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.TryBlock) {
			print_org_emftext_language_java_statements_TryBlock((org.emftext.language.java.statements.TryBlock) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.CatchBlock) {
			print_org_emftext_language_java_statements_CatchBlock((org.emftext.language.java.statements.CatchBlock) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.Switch) {
			print_org_emftext_language_java_statements_Switch((org.emftext.language.java.statements.Switch) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.NormalSwitchCase) {
			print_org_emftext_language_java_statements_NormalSwitchCase((org.emftext.language.java.statements.NormalSwitchCase) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.DefaultSwitchCase) {
			print_org_emftext_language_java_statements_DefaultSwitchCase((org.emftext.language.java.statements.DefaultSwitchCase) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.Return) {
			print_org_emftext_language_java_statements_Return((org.emftext.language.java.statements.Return) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.Throw) {
			print_org_emftext_language_java_statements_Throw((org.emftext.language.java.statements.Throw) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.Break) {
			print_org_emftext_language_java_statements_Break((org.emftext.language.java.statements.Break) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.Continue) {
			print_org_emftext_language_java_statements_Continue((org.emftext.language.java.statements.Continue) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.JumpLabel) {
			print_org_emftext_language_java_statements_JumpLabel((org.emftext.language.java.statements.JumpLabel) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.ExpressionStatement) {
			print_org_emftext_language_java_statements_ExpressionStatement((org.emftext.language.java.statements.ExpressionStatement) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.ExpressionList) {
			print_org_emftext_language_java_expressions_ExpressionList((org.emftext.language.java.expressions.ExpressionList) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.AssignmentExpression) {
			print_org_emftext_language_java_expressions_AssignmentExpression((org.emftext.language.java.expressions.AssignmentExpression) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.ConditionalExpression) {
			print_org_emftext_language_java_expressions_ConditionalExpression((org.emftext.language.java.expressions.ConditionalExpression) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.ConditionalOrExpression) {
			print_org_emftext_language_java_expressions_ConditionalOrExpression((org.emftext.language.java.expressions.ConditionalOrExpression) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.ConditionalAndExpression) {
			print_org_emftext_language_java_expressions_ConditionalAndExpression((org.emftext.language.java.expressions.ConditionalAndExpression) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.InclusiveOrExpression) {
			print_org_emftext_language_java_expressions_InclusiveOrExpression((org.emftext.language.java.expressions.InclusiveOrExpression) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.ExclusiveOrExpression) {
			print_org_emftext_language_java_expressions_ExclusiveOrExpression((org.emftext.language.java.expressions.ExclusiveOrExpression) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.AndExpression) {
			print_org_emftext_language_java_expressions_AndExpression((org.emftext.language.java.expressions.AndExpression) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.EqualityExpression) {
			print_org_emftext_language_java_expressions_EqualityExpression((org.emftext.language.java.expressions.EqualityExpression) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.InstanceOfExpression) {
			print_org_emftext_language_java_expressions_InstanceOfExpression((org.emftext.language.java.expressions.InstanceOfExpression) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.RelationExpression) {
			print_org_emftext_language_java_expressions_RelationExpression((org.emftext.language.java.expressions.RelationExpression) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.ShiftExpression) {
			print_org_emftext_language_java_expressions_ShiftExpression((org.emftext.language.java.expressions.ShiftExpression) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.AdditiveExpression) {
			print_org_emftext_language_java_expressions_AdditiveExpression((org.emftext.language.java.expressions.AdditiveExpression) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.MultiplicativeExpression) {
			print_org_emftext_language_java_expressions_MultiplicativeExpression((org.emftext.language.java.expressions.MultiplicativeExpression) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.UnaryExpression) {
			print_org_emftext_language_java_expressions_UnaryExpression((org.emftext.language.java.expressions.UnaryExpression) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.SuffixUnaryModificationExpression) {
			print_org_emftext_language_java_expressions_SuffixUnaryModificationExpression((org.emftext.language.java.expressions.SuffixUnaryModificationExpression) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.PrefixUnaryModificationExpression) {
			print_org_emftext_language_java_expressions_PrefixUnaryModificationExpression((org.emftext.language.java.expressions.PrefixUnaryModificationExpression) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.CastExpression) {
			print_org_emftext_language_java_expressions_CastExpression((org.emftext.language.java.expressions.CastExpression) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.expressions.NestedExpression) {
			print_org_emftext_language_java_expressions_NestedExpression((org.emftext.language.java.expressions.NestedExpression) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.Assignment) {
			print_org_emftext_language_java_operators_Assignment((org.emftext.language.java.operators.Assignment) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentPlus) {
			print_org_emftext_language_java_operators_AssignmentPlus((org.emftext.language.java.operators.AssignmentPlus) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentMinus) {
			print_org_emftext_language_java_operators_AssignmentMinus((org.emftext.language.java.operators.AssignmentMinus) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentMultiplication) {
			print_org_emftext_language_java_operators_AssignmentMultiplication((org.emftext.language.java.operators.AssignmentMultiplication) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentDivision) {
			print_org_emftext_language_java_operators_AssignmentDivision((org.emftext.language.java.operators.AssignmentDivision) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentAnd) {
			print_org_emftext_language_java_operators_AssignmentAnd((org.emftext.language.java.operators.AssignmentAnd) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentOr) {
			print_org_emftext_language_java_operators_AssignmentOr((org.emftext.language.java.operators.AssignmentOr) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentExclusiveOr) {
			print_org_emftext_language_java_operators_AssignmentExclusiveOr((org.emftext.language.java.operators.AssignmentExclusiveOr) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentModulo) {
			print_org_emftext_language_java_operators_AssignmentModulo((org.emftext.language.java.operators.AssignmentModulo) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentLeftShift) {
			print_org_emftext_language_java_operators_AssignmentLeftShift((org.emftext.language.java.operators.AssignmentLeftShift) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentRightShift) {
			print_org_emftext_language_java_operators_AssignmentRightShift((org.emftext.language.java.operators.AssignmentRightShift) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.AssignmentUnsignedRightShift) {
			print_org_emftext_language_java_operators_AssignmentUnsignedRightShift((org.emftext.language.java.operators.AssignmentUnsignedRightShift) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.Addition) {
			print_org_emftext_language_java_operators_Addition((org.emftext.language.java.operators.Addition) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.Subtraction) {
			print_org_emftext_language_java_operators_Subtraction((org.emftext.language.java.operators.Subtraction) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.Multiplication) {
			print_org_emftext_language_java_operators_Multiplication((org.emftext.language.java.operators.Multiplication) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.Division) {
			print_org_emftext_language_java_operators_Division((org.emftext.language.java.operators.Division) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.Remainder) {
			print_org_emftext_language_java_operators_Remainder((org.emftext.language.java.operators.Remainder) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.LessThan) {
			print_org_emftext_language_java_operators_LessThan((org.emftext.language.java.operators.LessThan) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.LessThanOrEqual) {
			print_org_emftext_language_java_operators_LessThanOrEqual((org.emftext.language.java.operators.LessThanOrEqual) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.GreaterThan) {
			print_org_emftext_language_java_operators_GreaterThan((org.emftext.language.java.operators.GreaterThan) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.GreaterThanOrEqual) {
			print_org_emftext_language_java_operators_GreaterThanOrEqual((org.emftext.language.java.operators.GreaterThanOrEqual) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.LeftShift) {
			print_org_emftext_language_java_operators_LeftShift((org.emftext.language.java.operators.LeftShift) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.RightShift) {
			print_org_emftext_language_java_operators_RightShift((org.emftext.language.java.operators.RightShift) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.UnsignedRightShift) {
			print_org_emftext_language_java_operators_UnsignedRightShift((org.emftext.language.java.operators.UnsignedRightShift) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.Equal) {
			print_org_emftext_language_java_operators_Equal((org.emftext.language.java.operators.Equal) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.NotEqual) {
			print_org_emftext_language_java_operators_NotEqual((org.emftext.language.java.operators.NotEqual) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.PlusPlus) {
			print_org_emftext_language_java_operators_PlusPlus((org.emftext.language.java.operators.PlusPlus) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.MinusMinus) {
			print_org_emftext_language_java_operators_MinusMinus((org.emftext.language.java.operators.MinusMinus) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.Complement) {
			print_org_emftext_language_java_operators_Complement((org.emftext.language.java.operators.Complement) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.operators.Negate) {
			print_org_emftext_language_java_operators_Negate((org.emftext.language.java.operators.Negate) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.arrays.ArrayDimension) {
			print_org_emftext_language_java_arrays_ArrayDimension((org.emftext.language.java.arrays.ArrayDimension) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.NullLiteral) {
			print_org_emftext_language_java_literals_NullLiteral((org.emftext.language.java.literals.NullLiteral) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Public) {
			print_org_emftext_language_java_modifiers_Public((org.emftext.language.java.modifiers.Public) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Abstract) {
			print_org_emftext_language_java_modifiers_Abstract((org.emftext.language.java.modifiers.Abstract) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Protected) {
			print_org_emftext_language_java_modifiers_Protected((org.emftext.language.java.modifiers.Protected) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Private) {
			print_org_emftext_language_java_modifiers_Private((org.emftext.language.java.modifiers.Private) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Final) {
			print_org_emftext_language_java_modifiers_Final((org.emftext.language.java.modifiers.Final) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Static) {
			print_org_emftext_language_java_modifiers_Static((org.emftext.language.java.modifiers.Static) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Native) {
			print_org_emftext_language_java_modifiers_Native((org.emftext.language.java.modifiers.Native) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Synchronized) {
			print_org_emftext_language_java_modifiers_Synchronized((org.emftext.language.java.modifiers.Synchronized) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Transient) {
			print_org_emftext_language_java_modifiers_Transient((org.emftext.language.java.modifiers.Transient) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Volatile) {
			print_org_emftext_language_java_modifiers_Volatile((org.emftext.language.java.modifiers.Volatile) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.modifiers.Strictfp) {
			print_org_emftext_language_java_modifiers_Strictfp((org.emftext.language.java.modifiers.Strictfp) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.types.Void) {
			print_org_emftext_language_java_types_Void((org.emftext.language.java.types.Void) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.types.Boolean) {
			print_org_emftext_language_java_types_Boolean((org.emftext.language.java.types.Boolean) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.types.Char) {
			print_org_emftext_language_java_types_Char((org.emftext.language.java.types.Char) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.types.Byte) {
			print_org_emftext_language_java_types_Byte((org.emftext.language.java.types.Byte) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.types.Short) {
			print_org_emftext_language_java_types_Short((org.emftext.language.java.types.Short) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.types.Int) {
			print_org_emftext_language_java_types_Int((org.emftext.language.java.types.Int) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.types.Long) {
			print_org_emftext_language_java_types_Long((org.emftext.language.java.types.Long) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.types.Float) {
			print_org_emftext_language_java_types_Float((org.emftext.language.java.types.Float) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.types.Double) {
			print_org_emftext_language_java_types_Double((org.emftext.language.java.types.Double) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.DecimalLongLiteral) {
			print_org_emftext_language_java_literals_DecimalLongLiteral((org.emftext.language.java.literals.DecimalLongLiteral) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.DecimalFloatLiteral) {
			print_org_emftext_language_java_literals_DecimalFloatLiteral((org.emftext.language.java.literals.DecimalFloatLiteral) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.DecimalIntegerLiteral) {
			print_org_emftext_language_java_literals_DecimalIntegerLiteral((org.emftext.language.java.literals.DecimalIntegerLiteral) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.DecimalDoubleLiteral) {
			print_org_emftext_language_java_literals_DecimalDoubleLiteral((org.emftext.language.java.literals.DecimalDoubleLiteral) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.HexLongLiteral) {
			print_org_emftext_language_java_literals_HexLongLiteral((org.emftext.language.java.literals.HexLongLiteral) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.HexFloatLiteral) {
			print_org_emftext_language_java_literals_HexFloatLiteral((org.emftext.language.java.literals.HexFloatLiteral) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.HexDoubleLiteral) {
			print_org_emftext_language_java_literals_HexDoubleLiteral((org.emftext.language.java.literals.HexDoubleLiteral) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.HexIntegerLiteral) {
			print_org_emftext_language_java_literals_HexIntegerLiteral((org.emftext.language.java.literals.HexIntegerLiteral) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.OctalLongLiteral) {
			print_org_emftext_language_java_literals_OctalLongLiteral((org.emftext.language.java.literals.OctalLongLiteral) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.OctalIntegerLiteral) {
			print_org_emftext_language_java_literals_OctalIntegerLiteral((org.emftext.language.java.literals.OctalIntegerLiteral) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.CharacterLiteral) {
			print_org_emftext_language_java_literals_CharacterLiteral((org.emftext.language.java.literals.CharacterLiteral) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.literals.BooleanLiteral) {
			print_org_emftext_language_java_literals_BooleanLiteral((org.emftext.language.java.literals.BooleanLiteral) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.members.InterfaceMethod) {
			print_org_emftext_language_java_members_InterfaceMethod((org.emftext.language.java.members.InterfaceMethod) element, globaltab, out);
			return;
		}
		if (element instanceof org.emftext.language.java.statements.WhileLoop) {
			print_org_emftext_language_java_statements_WhileLoop((org.emftext.language.java.statements.WhileLoop) element, globaltab, out);
			return;
		}
		
		addWarningToResource("The printer can not handle " + element.eClass().getName() + " elements", element);
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
	
	public void setOptions(Map<?,?> options) {
		this.options = options;
	}
	
	public Map<?,?> getOptions() {
		return options;
	}
	
	public void setEncoding(String encoding) {
		if (encoding != null) {
			this.encoding = encoding;
		}
	}
	
	public String getEncoding() {
		return encoding;
	}
	
	public org.emftext.language.java.resource.java.IJavaTextResource getResource() {
		return resource;
	}
	
	/**
	 * Calls {@link #doPrint(EObject, PrintWriter, String)} and writes the result to
	 * the underlying output stream.
	 */
	public void print(EObject element) throws java.io.IOException {
		PrintWriter out = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(outputStream), encoding));
		doPrint(element, out, "");
		out.flush();
	}
	
	public void print_org_emftext_language_java_containers_EmptyModel(org.emftext.language.java.containers.EmptyModel element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.EMPTY_MODEL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.EMPTY_MODEL__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.EMPTY_MODEL__NAMESPACES));
		printCountingMap.put("namespaces", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.EMPTY_MODEL__IMPORTS));
		printCountingMap.put("imports", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_containers_EmptyModel_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_containers_EmptyModel_1(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_containers_EmptyModel_0(org.emftext.language.java.containers.EmptyModel element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("imports");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.EMPTY_MODEL__IMPORTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("imports", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
	}
	
	public void print_org_emftext_language_java_containers_EmptyModel_1(org.emftext.language.java.containers.EmptyModel element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_containers_Package(org.emftext.language.java.containers.Package element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(6);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__NAMESPACES));
		printCountingMap.put("namespaces", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__IMPORTS));
		printCountingMap.put("imports", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__ANNOTATIONS));
		printCountingMap.put("annotations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__COMPILATION_UNITS));
		printCountingMap.put("compilationUnits", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("annotations");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__ANNOTATIONS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("annotations", 0);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("package");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_containers_Package_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__NAME));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_containers_Package_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_containers_Package_2(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_containers_Package_3(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_containers_Package_0(org.emftext.language.java.containers.Package element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("namespaces");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__NAMESPACES));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__NAMESPACES), element));
				out.print(" ");
			}
			printCountingMap.put("namespaces", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_containers_Package_1(org.emftext.language.java.containers.Package element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_containers_Package_2(org.emftext.language.java.containers.Package element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("imports");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__IMPORTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("imports", count - 1);
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
	}
	
	public void print_org_emftext_language_java_containers_Package_3(org.emftext.language.java.containers.Package element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_containers_CompilationUnit(org.emftext.language.java.containers.CompilationUnit element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(5);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__NAMESPACES));
		printCountingMap.put("namespaces", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__IMPORTS));
		printCountingMap.put("imports", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__CLASSIFIERS));
		printCountingMap.put("classifiers", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_containers_CompilationUnit_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_containers_CompilationUnit_1(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_containers_CompilationUnit_2(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_org_emftext_language_java_containers_CompilationUnit_3(element, localtab, out, printCountingMap);
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_containers_CompilationUnit_3(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_containers_CompilationUnit_4(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_containers_CompilationUnit_0(org.emftext.language.java.containers.CompilationUnit element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("package");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("namespaces");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__NAMESPACES));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__NAMESPACES), element));
				out.print(" ");
			}
			printCountingMap.put("namespaces", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_containers_CompilationUnit_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_containers_CompilationUnit_0_0(org.emftext.language.java.containers.CompilationUnit element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("namespaces");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__NAMESPACES));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__NAMESPACES), element));
				out.print(" ");
			}
			printCountingMap.put("namespaces", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_containers_CompilationUnit_1(org.emftext.language.java.containers.CompilationUnit element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("imports");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__IMPORTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("imports", count - 1);
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
	}
	
	public void print_org_emftext_language_java_containers_CompilationUnit_2(org.emftext.language.java.containers.CompilationUnit element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
	}
	
	public void print_org_emftext_language_java_containers_CompilationUnit_3(org.emftext.language.java.containers.CompilationUnit element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("classifiers");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__CLASSIFIERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("classifiers", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_containers_CompilationUnit_3_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
	}
	
	public void print_org_emftext_language_java_containers_CompilationUnit_3_0(org.emftext.language.java.containers.CompilationUnit element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_containers_CompilationUnit_4(org.emftext.language.java.containers.CompilationUnit element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		// DEFINITION PART BEGINS (CsString)
		out.print("\u001a");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_imports_ClassifierImport(org.emftext.language.java.imports.ClassifierImport element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.CLASSIFIER_IMPORT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.CLASSIFIER_IMPORT__NAMESPACES));
		printCountingMap.put("namespaces", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.CLASSIFIER_IMPORT__CLASSIFIER));
		printCountingMap.put("classifier", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("import");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_imports_ClassifierImport_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("classifier");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.CLASSIFIER_IMPORT__CLASSIFIER));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getClassifierImportClassifierReferenceResolver().deResolve((org.emftext.language.java.classifiers.ConcreteClassifier) o, element, (EReference) element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.CLASSIFIER_IMPORT__CLASSIFIER)), element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.CLASSIFIER_IMPORT__CLASSIFIER), element));
				out.print(" ");
			}
			printCountingMap.put("classifier", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_imports_ClassifierImport_0(org.emftext.language.java.imports.ClassifierImport element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("namespaces");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.CLASSIFIER_IMPORT__NAMESPACES));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.CLASSIFIER_IMPORT__NAMESPACES), element));
				out.print(" ");
			}
			printCountingMap.put("namespaces", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_imports_PackageImport(org.emftext.language.java.imports.PackageImport element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.PACKAGE_IMPORT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.PACKAGE_IMPORT__NAMESPACES));
		printCountingMap.put("namespaces", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("import");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_org_emftext_language_java_imports_PackageImport_0(element, localtab, out, printCountingMap);
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_imports_PackageImport_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("*");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_imports_PackageImport_0(org.emftext.language.java.imports.PackageImport element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("namespaces");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.PACKAGE_IMPORT__NAMESPACES));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.PACKAGE_IMPORT__NAMESPACES), element));
				out.print(" ");
			}
			printCountingMap.put("namespaces", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_imports_StaticMemberImport(org.emftext.language.java.imports.StaticMemberImport element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__NAMESPACES));
		printCountingMap.put("namespaces", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__STATIC));
		printCountingMap.put("static", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__STATIC_MEMBERS));
		printCountingMap.put("staticMembers", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("import");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("static");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__STATIC));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("static", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_imports_StaticMemberImport_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("staticMembers");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__STATIC_MEMBERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getStaticMemberImportStaticMembersReferenceResolver().deResolve((org.emftext.language.java.references.ReferenceableElement) o, element, (EReference) element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__STATIC_MEMBERS)), element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__STATIC_MEMBERS), element));
				out.print(" ");
			}
			printCountingMap.put("staticMembers", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_imports_StaticMemberImport_0(org.emftext.language.java.imports.StaticMemberImport element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("namespaces");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__NAMESPACES));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__NAMESPACES), element));
				out.print(" ");
			}
			printCountingMap.put("namespaces", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_imports_StaticClassifierImport(org.emftext.language.java.imports.StaticClassifierImport element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_CLASSIFIER_IMPORT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_CLASSIFIER_IMPORT__NAMESPACES));
		printCountingMap.put("namespaces", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_CLASSIFIER_IMPORT__STATIC));
		printCountingMap.put("static", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("import");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("static");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_CLASSIFIER_IMPORT__STATIC));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("static", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_org_emftext_language_java_imports_StaticClassifierImport_0(element, localtab, out, printCountingMap);
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_imports_StaticClassifierImport_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("*");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_imports_StaticClassifierImport_0(org.emftext.language.java.imports.StaticClassifierImport element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("namespaces");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_CLASSIFIER_IMPORT__NAMESPACES));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_CLASSIFIER_IMPORT__NAMESPACES), element));
				out.print(" ");
			}
			printCountingMap.put("namespaces", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_classifiers_Class(org.emftext.language.java.classifiers.Class element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(9);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__TYPE_PARAMETERS));
		printCountingMap.put("typeParameters", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__MEMBERS));
		printCountingMap.put("members", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__DEFAULT_MEMBERS));
		printCountingMap.put("defaultMembers", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__ANNOTATIONS_AND_MODIFIERS));
		printCountingMap.put("annotationsAndModifiers", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__IMPLEMENTS));
		printCountingMap.put("implements", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__EXTENDS));
		printCountingMap.put("extends", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__DEFAULT_EXTENDS));
		printCountingMap.put("defaultExtends", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("annotationsAndModifiers");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__ANNOTATIONS_AND_MODIFIERS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("annotationsAndModifiers", 0);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("class");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__NAME));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_classifiers_Class_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_classifiers_Class_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_classifiers_Class_2(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("{");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_classifiers_Class_3(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (CsString)
		out.print("}");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_classifiers_Class_0(org.emftext.language.java.classifiers.Class element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeParameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__TYPE_PARAMETERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeParameters", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_classifiers_Class_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_classifiers_Class_0_0(org.emftext.language.java.classifiers.Class element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeParameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__TYPE_PARAMETERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeParameters", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_classifiers_Class_1(org.emftext.language.java.classifiers.Class element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("extends");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("extends");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__EXTENDS));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("extends", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_classifiers_Class_2(org.emftext.language.java.classifiers.Class element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		// DEFINITION PART BEGINS (CsString)
		out.print("implements");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_org_emftext_language_java_classifiers_Class_2_0(element, localtab, out, printCountingMap);
	}
	
	public void print_org_emftext_language_java_classifiers_Class_2_0(org.emftext.language.java.classifiers.Class element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("implements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__IMPLEMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("implements", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_classifiers_Class_2_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_classifiers_Class_2_0_0(org.emftext.language.java.classifiers.Class element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("implements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__IMPLEMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("implements", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_classifiers_Class_3(org.emftext.language.java.classifiers.Class element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "	";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("members");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__MEMBERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("members", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_classifiers_AnonymousClass(org.emftext.language.java.classifiers.AnonymousClass element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANONYMOUS_CLASS__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANONYMOUS_CLASS__MEMBERS));
		printCountingMap.put("members", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANONYMOUS_CLASS__DEFAULT_MEMBERS));
		printCountingMap.put("defaultMembers", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("{");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_classifiers_AnonymousClass_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (CsString)
		out.print("}");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_classifiers_AnonymousClass_0(org.emftext.language.java.classifiers.AnonymousClass element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "	";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("members");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANONYMOUS_CLASS__MEMBERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("members", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_classifiers_Interface(org.emftext.language.java.classifiers.Interface element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(8);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__TYPE_PARAMETERS));
		printCountingMap.put("typeParameters", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__MEMBERS));
		printCountingMap.put("members", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__DEFAULT_MEMBERS));
		printCountingMap.put("defaultMembers", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__ANNOTATIONS_AND_MODIFIERS));
		printCountingMap.put("annotationsAndModifiers", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__EXTENDS));
		printCountingMap.put("extends", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__DEFAULT_EXTENDS));
		printCountingMap.put("defaultExtends", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("annotationsAndModifiers");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__ANNOTATIONS_AND_MODIFIERS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("annotationsAndModifiers", 0);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("interface");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__NAME));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_classifiers_Interface_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_classifiers_Interface_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("{");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_classifiers_Interface_2(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (CsString)
		out.print("}");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_classifiers_Interface_0(org.emftext.language.java.classifiers.Interface element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeParameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__TYPE_PARAMETERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeParameters", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_classifiers_Interface_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_classifiers_Interface_0_0(org.emftext.language.java.classifiers.Interface element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeParameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__TYPE_PARAMETERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeParameters", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_classifiers_Interface_1(org.emftext.language.java.classifiers.Interface element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		// DEFINITION PART BEGINS (CsString)
		out.print("extends");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_org_emftext_language_java_classifiers_Interface_1_0(element, localtab, out, printCountingMap);
	}
	
	public void print_org_emftext_language_java_classifiers_Interface_1_0(org.emftext.language.java.classifiers.Interface element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("extends");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__EXTENDS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("extends", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_classifiers_Interface_1_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_classifiers_Interface_1_0_0(org.emftext.language.java.classifiers.Interface element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("extends");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__EXTENDS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("extends", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_classifiers_Interface_2(org.emftext.language.java.classifiers.Interface element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "	";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("members");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__MEMBERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("members", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_classifiers_Enumeration(org.emftext.language.java.classifiers.Enumeration element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(8);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__TYPE_PARAMETERS));
		printCountingMap.put("typeParameters", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__MEMBERS));
		printCountingMap.put("members", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__DEFAULT_MEMBERS));
		printCountingMap.put("defaultMembers", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__ANNOTATIONS_AND_MODIFIERS));
		printCountingMap.put("annotationsAndModifiers", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__IMPLEMENTS));
		printCountingMap.put("implements", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__CONSTANTS));
		printCountingMap.put("constants", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("annotationsAndModifiers");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__ANNOTATIONS_AND_MODIFIERS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("annotationsAndModifiers", 0);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("enum");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__NAME));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_classifiers_Enumeration_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("{");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_classifiers_Enumeration_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_classifiers_Enumeration_2(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_classifiers_Enumeration_3(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("}");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_classifiers_Enumeration_0(org.emftext.language.java.classifiers.Enumeration element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		// DEFINITION PART BEGINS (CsString)
		out.print("implements");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_org_emftext_language_java_classifiers_Enumeration_0_0(element, localtab, out, printCountingMap);
	}
	
	public void print_org_emftext_language_java_classifiers_Enumeration_0_0(org.emftext.language.java.classifiers.Enumeration element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("implements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__IMPLEMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("implements", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_classifiers_Enumeration_0_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_classifiers_Enumeration_0_0_0(org.emftext.language.java.classifiers.Enumeration element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("implements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__IMPLEMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("implements", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_classifiers_Enumeration_1(org.emftext.language.java.classifiers.Enumeration element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "	";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("constants");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__CONSTANTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("constants", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_classifiers_Enumeration_1_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_classifiers_Enumeration_1_0(org.emftext.language.java.classifiers.Enumeration element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "	";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("constants");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__CONSTANTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("constants", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_classifiers_Enumeration_2(org.emftext.language.java.classifiers.Enumeration element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_classifiers_Enumeration_3(org.emftext.language.java.classifiers.Enumeration element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_classifiers_Enumeration_3_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
	}
	
	public void print_org_emftext_language_java_classifiers_Enumeration_3_0(org.emftext.language.java.classifiers.Enumeration element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "	";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("members");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__MEMBERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("members", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_classifiers_Annotation(org.emftext.language.java.classifiers.Annotation element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(6);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANNOTATION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANNOTATION__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANNOTATION__TYPE_PARAMETERS));
		printCountingMap.put("typeParameters", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANNOTATION__MEMBERS));
		printCountingMap.put("members", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANNOTATION__DEFAULT_MEMBERS));
		printCountingMap.put("defaultMembers", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANNOTATION__ANNOTATIONS_AND_MODIFIERS));
		printCountingMap.put("annotationsAndModifiers", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("annotationsAndModifiers");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANNOTATION__ANNOTATIONS_AND_MODIFIERS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("annotationsAndModifiers", 0);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("@");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("interface");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANNOTATION__NAME));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANNOTATION__NAME), element));
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("{");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_classifiers_Annotation_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (CsString)
		out.print("}");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_classifiers_Annotation_0(org.emftext.language.java.classifiers.Annotation element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "	";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("members");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANNOTATION__MEMBERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("members", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_annotations_AnnotationInstance(org.emftext.language.java.annotations.AnnotationInstance element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(7);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__TYPE_ARGUMENTS));
		printCountingMap.put("typeArguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__NEXT));
		printCountingMap.put("next", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__ARRAY_SELECTORS));
		printCountingMap.put("arraySelectors", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__NAMESPACES));
		printCountingMap.put("namespaces", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__ANNOTATION));
		printCountingMap.put("annotation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__PARAMETER));
		printCountingMap.put("parameter", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("@");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_annotations_AnnotationInstance_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("annotation");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__ANNOTATION));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getAnnotationInstanceAnnotationReferenceResolver().deResolve((org.emftext.language.java.classifiers.Classifier) o, element, (EReference) element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__ANNOTATION)), element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__ANNOTATION), element));
				out.print(" ");
			}
			printCountingMap.put("annotation", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_annotations_AnnotationInstance_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_annotations_AnnotationInstance_0(org.emftext.language.java.annotations.AnnotationInstance element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("namespaces");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__NAMESPACES));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__NAMESPACES), element));
				out.print(" ");
			}
			printCountingMap.put("namespaces", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_annotations_AnnotationInstance_1(org.emftext.language.java.annotations.AnnotationInstance element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("parameter");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__PARAMETER));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("parameter", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_annotations_SingleAnnotationParameter(org.emftext.language.java.annotations.SingleAnnotationParameter element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.SINGLE_ANNOTATION_PARAMETER__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.SINGLE_ANNOTATION_PARAMETER__VALUE));
		printCountingMap.put("value", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("value");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.SINGLE_ANNOTATION_PARAMETER__VALUE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("value", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_annotations_AnnotationParameterList(org.emftext.language.java.annotations.AnnotationParameterList element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_PARAMETER_LIST__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_PARAMETER_LIST__SETTINGS));
		printCountingMap.put("settings", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_annotations_AnnotationParameterList_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_annotations_AnnotationParameterList_0(org.emftext.language.java.annotations.AnnotationParameterList element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("settings");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_PARAMETER_LIST__SETTINGS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("settings", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_annotations_AnnotationParameterList_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_annotations_AnnotationParameterList_0_0(org.emftext.language.java.annotations.AnnotationParameterList element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("settings");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_PARAMETER_LIST__SETTINGS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("settings", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_annotations_AnnotationAttributeSetting(org.emftext.language.java.annotations.AnnotationAttributeSetting element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE_SETTING__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE_SETTING__ATTRIBUTE));
		printCountingMap.put("attribute", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE_SETTING__VALUE));
		printCountingMap.put("value", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("attribute");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE_SETTING__ATTRIBUTE));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getAnnotationAttributeSettingAttributeReferenceResolver().deResolve((org.emftext.language.java.members.InterfaceMethod) o, element, (EReference) element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE_SETTING__ATTRIBUTE)), element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE_SETTING__ATTRIBUTE), element));
			}
			printCountingMap.put("attribute", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("value");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE_SETTING__VALUE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("value", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_generics_TypeParameter(org.emftext.language.java.generics.TypeParameter element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.TYPE_PARAMETER__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.TYPE_PARAMETER__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.TYPE_PARAMETER__EXTEND_TYPES));
		printCountingMap.put("extendTypes", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.TYPE_PARAMETER__NAME));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.TYPE_PARAMETER__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_generics_TypeParameter_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_generics_TypeParameter_0(org.emftext.language.java.generics.TypeParameter element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("extends");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("extendTypes");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.TYPE_PARAMETER__EXTEND_TYPES));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("extendTypes", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_generics_TypeParameter_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_generics_TypeParameter_0_0(org.emftext.language.java.generics.TypeParameter element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("&");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("extendTypes");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.TYPE_PARAMETER__EXTEND_TYPES));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("extendTypes", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_members_EnumConstant(org.emftext.language.java.members.EnumConstant element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(5);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__ARGUMENTS));
		printCountingMap.put("arguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__ANNOTATIONS));
		printCountingMap.put("annotations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__ANONYMOUS_CLASS));
		printCountingMap.put("anonymousClass", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("annotations");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__ANNOTATIONS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("annotations", 0);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__NAME));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_members_EnumConstant_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_members_EnumConstant_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_members_EnumConstant_0(org.emftext.language.java.members.EnumConstant element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_members_EnumConstant_0_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_members_EnumConstant_0_0(org.emftext.language.java.members.EnumConstant element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_members_EnumConstant_0_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_members_EnumConstant_0_0_0(org.emftext.language.java.members.EnumConstant element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_members_EnumConstant_1(org.emftext.language.java.members.EnumConstant element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("anonymousClass");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__ANONYMOUS_CLASS));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("anonymousClass", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_statements_Block(org.emftext.language.java.statements.Block element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.BLOCK__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.BLOCK__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.BLOCK__STATEMENTS));
		printCountingMap.put("statements", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.BLOCK__MODIFIERS));
		printCountingMap.put("modifiers", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("modifiers");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.BLOCK__MODIFIERS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("modifiers", 0);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("{");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_statements_Block_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (CsString)
		out.print("}");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_statements_Block_0(org.emftext.language.java.statements.Block element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "	";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("statements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.BLOCK__STATEMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("statements", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_members_Constructor(org.emftext.language.java.members.Constructor element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(7);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__STATEMENTS));
		printCountingMap.put("statements", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__PARAMETERS));
		printCountingMap.put("parameters", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__TYPE_PARAMETERS));
		printCountingMap.put("typeParameters", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__EXCEPTIONS));
		printCountingMap.put("exceptions", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__ANNOTATIONS_AND_MODIFIERS));
		printCountingMap.put("annotationsAndModifiers", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("annotationsAndModifiers");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__ANNOTATIONS_AND_MODIFIERS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("annotationsAndModifiers", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_members_Constructor_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__NAME));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_members_Constructor_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_members_Constructor_2(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("{");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_members_Constructor_3(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "	";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (CsString)
		out.print("}");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_members_Constructor_0(org.emftext.language.java.members.Constructor element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeParameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__TYPE_PARAMETERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeParameters", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_members_Constructor_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_members_Constructor_0_0(org.emftext.language.java.members.Constructor element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeParameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__TYPE_PARAMETERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeParameters", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_members_Constructor_1(org.emftext.language.java.members.Constructor element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("parameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__PARAMETERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("parameters", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_members_Constructor_1_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_members_Constructor_1_0(org.emftext.language.java.members.Constructor element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("parameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__PARAMETERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("parameters", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_members_Constructor_2(org.emftext.language.java.members.Constructor element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("throws");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("exceptions");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__EXCEPTIONS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("exceptions", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_members_Constructor_2_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_members_Constructor_2_0(org.emftext.language.java.members.Constructor element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("exceptions");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__EXCEPTIONS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("exceptions", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_members_Constructor_3(org.emftext.language.java.members.Constructor element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "		";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("statements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__STATEMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("statements", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_members_InterfaceMethod(org.emftext.language.java.members.InterfaceMethod element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(9);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__TYPE_REFERENCE));
		printCountingMap.put("typeReference", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__ARRAY_DIMENSIONS_BEFORE));
		printCountingMap.put("arrayDimensionsBefore", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__ARRAY_DIMENSIONS_AFTER));
		printCountingMap.put("arrayDimensionsAfter", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__TYPE_PARAMETERS));
		printCountingMap.put("typeParameters", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__PARAMETERS));
		printCountingMap.put("parameters", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__EXCEPTIONS));
		printCountingMap.put("exceptions", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__ANNOTATIONS_AND_MODIFIERS));
		printCountingMap.put("annotationsAndModifiers", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("annotationsAndModifiers");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__ANNOTATIONS_AND_MODIFIERS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("annotationsAndModifiers", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_members_InterfaceMethod_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_org_emftext_language_java_members_InterfaceMethod_1(element, localtab, out, printCountingMap);
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__NAME));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_members_InterfaceMethod_2(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsAfter");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__ARRAY_DIMENSIONS_AFTER));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsAfter", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_members_InterfaceMethod_3(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_members_InterfaceMethod_0(org.emftext.language.java.members.InterfaceMethod element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeParameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__TYPE_PARAMETERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeParameters", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_members_InterfaceMethod_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_members_InterfaceMethod_0_0(org.emftext.language.java.members.InterfaceMethod element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeParameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__TYPE_PARAMETERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeParameters", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_members_InterfaceMethod_1(org.emftext.language.java.members.InterfaceMethod element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeReference");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__TYPE_REFERENCE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeReference", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsBefore");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__ARRAY_DIMENSIONS_BEFORE));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsBefore", 0);
		}
	}
	
	public void print_org_emftext_language_java_members_InterfaceMethod_2(org.emftext.language.java.members.InterfaceMethod element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("parameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__PARAMETERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("parameters", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_members_InterfaceMethod_2_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_members_InterfaceMethod_2_0(org.emftext.language.java.members.InterfaceMethod element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("parameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__PARAMETERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("parameters", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_members_InterfaceMethod_3(org.emftext.language.java.members.InterfaceMethod element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("throws");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("exceptions");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__EXCEPTIONS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("exceptions", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_members_InterfaceMethod_3_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_members_InterfaceMethod_3_0(org.emftext.language.java.members.InterfaceMethod element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("exceptions");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__EXCEPTIONS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("exceptions", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_members_ClassMethod(org.emftext.language.java.members.ClassMethod element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(10);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__TYPE_REFERENCE));
		printCountingMap.put("typeReference", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__ARRAY_DIMENSIONS_BEFORE));
		printCountingMap.put("arrayDimensionsBefore", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__ARRAY_DIMENSIONS_AFTER));
		printCountingMap.put("arrayDimensionsAfter", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__TYPE_PARAMETERS));
		printCountingMap.put("typeParameters", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__PARAMETERS));
		printCountingMap.put("parameters", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__EXCEPTIONS));
		printCountingMap.put("exceptions", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__ANNOTATIONS_AND_MODIFIERS));
		printCountingMap.put("annotationsAndModifiers", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__STATEMENTS));
		printCountingMap.put("statements", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("annotationsAndModifiers");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__ANNOTATIONS_AND_MODIFIERS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("annotationsAndModifiers", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_members_ClassMethod_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_org_emftext_language_java_members_ClassMethod_1(element, localtab, out, printCountingMap);
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__NAME));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_members_ClassMethod_2(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsAfter");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__ARRAY_DIMENSIONS_AFTER));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsAfter", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_members_ClassMethod_3(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("{");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_members_ClassMethod_4(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "	";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (CsString)
		out.print("}");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_members_ClassMethod_0(org.emftext.language.java.members.ClassMethod element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeParameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__TYPE_PARAMETERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeParameters", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_members_ClassMethod_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_members_ClassMethod_0_0(org.emftext.language.java.members.ClassMethod element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeParameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__TYPE_PARAMETERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeParameters", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_members_ClassMethod_1(org.emftext.language.java.members.ClassMethod element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeReference");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__TYPE_REFERENCE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeReference", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsBefore");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__ARRAY_DIMENSIONS_BEFORE));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsBefore", 0);
		}
	}
	
	public void print_org_emftext_language_java_members_ClassMethod_2(org.emftext.language.java.members.ClassMethod element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("parameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__PARAMETERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("parameters", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_members_ClassMethod_2_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_members_ClassMethod_2_0(org.emftext.language.java.members.ClassMethod element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("parameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__PARAMETERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("parameters", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_members_ClassMethod_3(org.emftext.language.java.members.ClassMethod element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("throws");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("exceptions");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__EXCEPTIONS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("exceptions", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_members_ClassMethod_3_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_members_ClassMethod_3_0(org.emftext.language.java.members.ClassMethod element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("exceptions");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__EXCEPTIONS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("exceptions", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_members_ClassMethod_4(org.emftext.language.java.members.ClassMethod element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "		";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("statements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__STATEMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("statements", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_annotations_AnnotationAttribute(org.emftext.language.java.annotations.AnnotationAttribute element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(10);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__TYPE_REFERENCE));
		printCountingMap.put("typeReference", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__ARRAY_DIMENSIONS_BEFORE));
		printCountingMap.put("arrayDimensionsBefore", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__ARRAY_DIMENSIONS_AFTER));
		printCountingMap.put("arrayDimensionsAfter", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__TYPE_PARAMETERS));
		printCountingMap.put("typeParameters", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__PARAMETERS));
		printCountingMap.put("parameters", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__EXCEPTIONS));
		printCountingMap.put("exceptions", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__ANNOTATIONS_AND_MODIFIERS));
		printCountingMap.put("annotationsAndModifiers", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__DEFAULT_VALUE));
		printCountingMap.put("defaultValue", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("annotationsAndModifiers");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__ANNOTATIONS_AND_MODIFIERS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("annotationsAndModifiers", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_annotations_AnnotationAttribute_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_org_emftext_language_java_annotations_AnnotationAttribute_1(element, localtab, out, printCountingMap);
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__NAME));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_annotations_AnnotationAttribute_2(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsAfter");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__ARRAY_DIMENSIONS_AFTER));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsAfter", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_annotations_AnnotationAttribute_3(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("default");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("defaultValue");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__DEFAULT_VALUE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("defaultValue", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_annotations_AnnotationAttribute_0(org.emftext.language.java.annotations.AnnotationAttribute element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeParameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__TYPE_PARAMETERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeParameters", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_annotations_AnnotationAttribute_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_annotations_AnnotationAttribute_0_0(org.emftext.language.java.annotations.AnnotationAttribute element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeParameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__TYPE_PARAMETERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeParameters", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_annotations_AnnotationAttribute_1(org.emftext.language.java.annotations.AnnotationAttribute element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeReference");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__TYPE_REFERENCE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeReference", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsBefore");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__ARRAY_DIMENSIONS_BEFORE));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsBefore", 0);
		}
	}
	
	public void print_org_emftext_language_java_annotations_AnnotationAttribute_2(org.emftext.language.java.annotations.AnnotationAttribute element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("parameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__PARAMETERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("parameters", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_annotations_AnnotationAttribute_2_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_annotations_AnnotationAttribute_2_0(org.emftext.language.java.annotations.AnnotationAttribute element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("parameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__PARAMETERS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("parameters", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_annotations_AnnotationAttribute_3(org.emftext.language.java.annotations.AnnotationAttribute element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("throws");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("exceptions");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__EXCEPTIONS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("exceptions", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_annotations_AnnotationAttribute_3_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_annotations_AnnotationAttribute_3_0(org.emftext.language.java.annotations.AnnotationAttribute element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("exceptions");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__EXCEPTIONS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("exceptions", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_parameters_OrdinaryParameter(org.emftext.language.java.parameters.OrdinaryParameter element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(7);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__TYPE_REFERENCE));
		printCountingMap.put("typeReference", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__ARRAY_DIMENSIONS_BEFORE));
		printCountingMap.put("arrayDimensionsBefore", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__ARRAY_DIMENSIONS_AFTER));
		printCountingMap.put("arrayDimensionsAfter", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__TYPE_ARGUMENTS));
		printCountingMap.put("typeArguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__ANNOTATIONS_AND_MODIFIERS));
		printCountingMap.put("annotationsAndModifiers", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("annotationsAndModifiers");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__ANNOTATIONS_AND_MODIFIERS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("annotationsAndModifiers", 0);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeReference");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__TYPE_REFERENCE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeReference", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsBefore");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__ARRAY_DIMENSIONS_BEFORE));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsBefore", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_parameters_OrdinaryParameter_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__NAME));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsAfter");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__ARRAY_DIMENSIONS_AFTER));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsAfter", 0);
		}
	}
	
	public void print_org_emftext_language_java_parameters_OrdinaryParameter_0(org.emftext.language.java.parameters.OrdinaryParameter element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeArguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_parameters_OrdinaryParameter_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_parameters_OrdinaryParameter_0_0(org.emftext.language.java.parameters.OrdinaryParameter element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeArguments", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_parameters_VariableLengthParameter(org.emftext.language.java.parameters.VariableLengthParameter element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(7);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__TYPE_REFERENCE));
		printCountingMap.put("typeReference", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__ARRAY_DIMENSIONS_BEFORE));
		printCountingMap.put("arrayDimensionsBefore", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__ARRAY_DIMENSIONS_AFTER));
		printCountingMap.put("arrayDimensionsAfter", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__TYPE_ARGUMENTS));
		printCountingMap.put("typeArguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__ANNOTATIONS_AND_MODIFIERS));
		printCountingMap.put("annotationsAndModifiers", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("annotationsAndModifiers");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__ANNOTATIONS_AND_MODIFIERS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("annotationsAndModifiers", 0);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeReference");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__TYPE_REFERENCE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeReference", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsBefore");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__ARRAY_DIMENSIONS_BEFORE));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsBefore", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_parameters_VariableLengthParameter_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("...");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__NAME));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_parameters_VariableLengthParameter_0(org.emftext.language.java.parameters.VariableLengthParameter element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeArguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_parameters_VariableLengthParameter_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_parameters_VariableLengthParameter_0_0(org.emftext.language.java.parameters.VariableLengthParameter element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeArguments", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_variables_LocalVariable(org.emftext.language.java.variables.LocalVariable element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(9);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__TYPE_REFERENCE));
		printCountingMap.put("typeReference", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__ARRAY_DIMENSIONS_BEFORE));
		printCountingMap.put("arrayDimensionsBefore", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__ARRAY_DIMENSIONS_AFTER));
		printCountingMap.put("arrayDimensionsAfter", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__TYPE_ARGUMENTS));
		printCountingMap.put("typeArguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__INITIAL_VALUE));
		printCountingMap.put("initialValue", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__ANNOTATIONS_AND_MODIFIERS));
		printCountingMap.put("annotationsAndModifiers", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES));
		printCountingMap.put("additionalLocalVariables", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("annotationsAndModifiers");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__ANNOTATIONS_AND_MODIFIERS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("annotationsAndModifiers", 0);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeReference");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__TYPE_REFERENCE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeReference", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsBefore");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__ARRAY_DIMENSIONS_BEFORE));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsBefore", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_variables_LocalVariable_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__NAME));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsAfter");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__ARRAY_DIMENSIONS_AFTER));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsAfter", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_variables_LocalVariable_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_variables_LocalVariable_2(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_variables_LocalVariable_0(org.emftext.language.java.variables.LocalVariable element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeArguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_variables_LocalVariable_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_variables_LocalVariable_0_0(org.emftext.language.java.variables.LocalVariable element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeArguments", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_variables_LocalVariable_1(org.emftext.language.java.variables.LocalVariable element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("initialValue");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__INITIAL_VALUE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("initialValue", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_variables_LocalVariable_2(org.emftext.language.java.variables.LocalVariable element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("additionalLocalVariables");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("additionalLocalVariables", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_statements_LocalVariableStatement(org.emftext.language.java.statements.LocalVariableStatement element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.LOCAL_VARIABLE_STATEMENT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.LOCAL_VARIABLE_STATEMENT__VARIABLE));
		printCountingMap.put("variable", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("variable");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.LOCAL_VARIABLE_STATEMENT__VARIABLE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("variable", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_variables_AdditionalLocalVariable(org.emftext.language.java.variables.AdditionalLocalVariable element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(5);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.ADDITIONAL_LOCAL_VARIABLE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.ADDITIONAL_LOCAL_VARIABLE__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.ADDITIONAL_LOCAL_VARIABLE__ARRAY_DIMENSIONS_BEFORE));
		printCountingMap.put("arrayDimensionsBefore", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.ADDITIONAL_LOCAL_VARIABLE__ARRAY_DIMENSIONS_AFTER));
		printCountingMap.put("arrayDimensionsAfter", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.ADDITIONAL_LOCAL_VARIABLE__INITIAL_VALUE));
		printCountingMap.put("initialValue", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.ADDITIONAL_LOCAL_VARIABLE__NAME));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.ADDITIONAL_LOCAL_VARIABLE__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsAfter");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.ADDITIONAL_LOCAL_VARIABLE__ARRAY_DIMENSIONS_AFTER));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsAfter", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_variables_AdditionalLocalVariable_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_variables_AdditionalLocalVariable_0(org.emftext.language.java.variables.AdditionalLocalVariable element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("initialValue");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.ADDITIONAL_LOCAL_VARIABLE__INITIAL_VALUE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("initialValue", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_members_Field(org.emftext.language.java.members.Field element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(9);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__INITIAL_VALUE));
		printCountingMap.put("initialValue", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__TYPE_REFERENCE));
		printCountingMap.put("typeReference", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__ARRAY_DIMENSIONS_BEFORE));
		printCountingMap.put("arrayDimensionsBefore", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__ARRAY_DIMENSIONS_AFTER));
		printCountingMap.put("arrayDimensionsAfter", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__TYPE_ARGUMENTS));
		printCountingMap.put("typeArguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__ANNOTATIONS_AND_MODIFIERS));
		printCountingMap.put("annotationsAndModifiers", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__ADDITIONAL_FIELDS));
		printCountingMap.put("additionalFields", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("annotationsAndModifiers");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__ANNOTATIONS_AND_MODIFIERS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("annotationsAndModifiers", 0);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeReference");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__TYPE_REFERENCE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeReference", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsBefore");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__ARRAY_DIMENSIONS_BEFORE));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsBefore", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_members_Field_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__NAME));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsAfter");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__ARRAY_DIMENSIONS_AFTER));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsAfter", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_members_Field_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_members_Field_2(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_members_Field_0(org.emftext.language.java.members.Field element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeArguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_members_Field_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_members_Field_0_0(org.emftext.language.java.members.Field element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeArguments", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_members_Field_1(org.emftext.language.java.members.Field element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("initialValue");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__INITIAL_VALUE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("initialValue", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_members_Field_2(org.emftext.language.java.members.Field element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("additionalFields");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__ADDITIONAL_FIELDS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("additionalFields", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_members_AdditionalField(org.emftext.language.java.members.AdditionalField element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(5);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ADDITIONAL_FIELD__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ADDITIONAL_FIELD__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ADDITIONAL_FIELD__ARRAY_DIMENSIONS_BEFORE));
		printCountingMap.put("arrayDimensionsBefore", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ADDITIONAL_FIELD__ARRAY_DIMENSIONS_AFTER));
		printCountingMap.put("arrayDimensionsAfter", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ADDITIONAL_FIELD__INITIAL_VALUE));
		printCountingMap.put("initialValue", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ADDITIONAL_FIELD__NAME));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ADDITIONAL_FIELD__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsAfter");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ADDITIONAL_FIELD__ARRAY_DIMENSIONS_AFTER));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsAfter", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_members_AdditionalField_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_members_AdditionalField_0(org.emftext.language.java.members.AdditionalField element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("initialValue");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ADDITIONAL_FIELD__INITIAL_VALUE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("initialValue", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_members_EmptyMember(org.emftext.language.java.members.EmptyMember element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.EMPTY_MEMBER__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.EMPTY_MEMBER__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_instantiations_NewConstructorCall(org.emftext.language.java.instantiations.NewConstructorCall element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(8);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_REFERENCE));
		printCountingMap.put("typeReference", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_ARGUMENTS));
		printCountingMap.put("typeArguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__NEXT));
		printCountingMap.put("next", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARRAY_SELECTORS));
		printCountingMap.put("arraySelectors", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARGUMENTS));
		printCountingMap.put("arguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__CALL_TYPE_ARGUMENTS));
		printCountingMap.put("callTypeArguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ANONYMOUS_CLASS));
		printCountingMap.put("anonymousClass", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("new");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_instantiations_NewConstructorCall_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeReference");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_REFERENCE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeReference", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_instantiations_NewConstructorCall_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_instantiations_NewConstructorCall_2(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("anonymousClass");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ANONYMOUS_CLASS));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("anonymousClass", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_instantiations_NewConstructorCall_3(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_instantiations_NewConstructorCall_0(org.emftext.language.java.instantiations.NewConstructorCall element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeArguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_instantiations_NewConstructorCall_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_instantiations_NewConstructorCall_0_0(org.emftext.language.java.instantiations.NewConstructorCall element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeArguments", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_instantiations_NewConstructorCall_1(org.emftext.language.java.instantiations.NewConstructorCall element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("callTypeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__CALL_TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("callTypeArguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_instantiations_NewConstructorCall_1_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_instantiations_NewConstructorCall_1_0(org.emftext.language.java.instantiations.NewConstructorCall element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("callTypeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__CALL_TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("callTypeArguments", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_instantiations_NewConstructorCall_2(org.emftext.language.java.instantiations.NewConstructorCall element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_instantiations_NewConstructorCall_2_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_instantiations_NewConstructorCall_2_0(org.emftext.language.java.instantiations.NewConstructorCall element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_instantiations_NewConstructorCall_3(org.emftext.language.java.instantiations.NewConstructorCall element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("next");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__NEXT));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("next", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_instantiations_ExplicitConstructorCall(org.emftext.language.java.instantiations.ExplicitConstructorCall element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(6);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__TYPE_ARGUMENTS));
		printCountingMap.put("typeArguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__NEXT));
		printCountingMap.put("next", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__ARRAY_SELECTORS));
		printCountingMap.put("arraySelectors", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__ARGUMENTS));
		printCountingMap.put("arguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__CALL_TARGET));
		printCountingMap.put("callTarget", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_instantiations_ExplicitConstructorCall_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("callTarget");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__CALL_TARGET));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("callTarget", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_instantiations_ExplicitConstructorCall_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_instantiations_ExplicitConstructorCall_2(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_instantiations_ExplicitConstructorCall_0(org.emftext.language.java.instantiations.ExplicitConstructorCall element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeArguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_instantiations_ExplicitConstructorCall_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_instantiations_ExplicitConstructorCall_0_0(org.emftext.language.java.instantiations.ExplicitConstructorCall element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeArguments", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_instantiations_ExplicitConstructorCall_1(org.emftext.language.java.instantiations.ExplicitConstructorCall element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_instantiations_ExplicitConstructorCall_1_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_instantiations_ExplicitConstructorCall_1_0(org.emftext.language.java.instantiations.ExplicitConstructorCall element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_instantiations_ExplicitConstructorCall_2(org.emftext.language.java.instantiations.ExplicitConstructorCall element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("next");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__NEXT));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("next", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_arrays_ArrayInstantiationByValuesTyped(org.emftext.language.java.arrays.ArrayInstantiationByValuesTyped element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(8);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__TYPE_ARGUMENTS));
		printCountingMap.put("typeArguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__NEXT));
		printCountingMap.put("next", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__ARRAY_SELECTORS));
		printCountingMap.put("arraySelectors", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__ARRAY_INITIALIZER));
		printCountingMap.put("arrayInitializer", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__TYPE_REFERENCE));
		printCountingMap.put("typeReference", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__ARRAY_DIMENSIONS_BEFORE));
		printCountingMap.put("arrayDimensionsBefore", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__ARRAY_DIMENSIONS_AFTER));
		printCountingMap.put("arrayDimensionsAfter", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("new");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeReference");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__TYPE_REFERENCE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeReference", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsBefore");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__ARRAY_DIMENSIONS_BEFORE));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsBefore", 0);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayInitializer");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__ARRAY_INITIALIZER));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayInitializer", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arraySelectors");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__ARRAY_SELECTORS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arraySelectors", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_arrays_ArrayInstantiationByValuesTyped_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_arrays_ArrayInstantiationByValuesTyped_0(org.emftext.language.java.arrays.ArrayInstantiationByValuesTyped element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("next");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__NEXT));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("next", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_arrays_ArrayInstantiationByValuesUntyped(org.emftext.language.java.arrays.ArrayInstantiationByValuesUntyped element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(5);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_UNTYPED__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_UNTYPED__TYPE_ARGUMENTS));
		printCountingMap.put("typeArguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_UNTYPED__NEXT));
		printCountingMap.put("next", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_UNTYPED__ARRAY_SELECTORS));
		printCountingMap.put("arraySelectors", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_UNTYPED__ARRAY_INITIALIZER));
		printCountingMap.put("arrayInitializer", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayInitializer");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_UNTYPED__ARRAY_INITIALIZER));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayInitializer", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arraySelectors");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_UNTYPED__ARRAY_SELECTORS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arraySelectors", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_arrays_ArrayInstantiationByValuesUntyped_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_arrays_ArrayInstantiationByValuesUntyped_0(org.emftext.language.java.arrays.ArrayInstantiationByValuesUntyped element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("next");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_UNTYPED__NEXT));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("next", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_arrays_ArrayInstantiationBySize(org.emftext.language.java.arrays.ArrayInstantiationBySize element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(8);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__TYPE_ARGUMENTS));
		printCountingMap.put("typeArguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__NEXT));
		printCountingMap.put("next", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__ARRAY_SELECTORS));
		printCountingMap.put("arraySelectors", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__TYPE_REFERENCE));
		printCountingMap.put("typeReference", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__ARRAY_DIMENSIONS_BEFORE));
		printCountingMap.put("arrayDimensionsBefore", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__ARRAY_DIMENSIONS_AFTER));
		printCountingMap.put("arrayDimensionsAfter", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__SIZES));
		printCountingMap.put("sizes", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("new");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeReference");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__TYPE_REFERENCE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeReference", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_org_emftext_language_java_arrays_ArrayInstantiationBySize_0(element, localtab, out, printCountingMap);
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_arrays_ArrayInstantiationBySize_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsBefore");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__ARRAY_DIMENSIONS_BEFORE));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsBefore", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_arrays_ArrayInstantiationBySize_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_arrays_ArrayInstantiationBySize_0(org.emftext.language.java.arrays.ArrayInstantiationBySize element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("[");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("sizes");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__SIZES));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("sizes", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("]");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_arrays_ArrayInstantiationBySize_1(org.emftext.language.java.arrays.ArrayInstantiationBySize element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("next");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__NEXT));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("next", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_arrays_ArrayInitializer(org.emftext.language.java.arrays.ArrayInitializer element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INITIALIZER__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INITIALIZER__INITIAL_VALUES));
		printCountingMap.put("initialValues", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("{");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_arrays_ArrayInitializer_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_arrays_ArrayInitializer_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("}");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_arrays_ArrayInitializer_0(org.emftext.language.java.arrays.ArrayInitializer element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("initialValues");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INITIALIZER__INITIAL_VALUES));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("initialValues", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_arrays_ArrayInitializer_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_arrays_ArrayInitializer_0_0(org.emftext.language.java.arrays.ArrayInitializer element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("initialValues");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INITIALIZER__INITIAL_VALUES));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("initialValues", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_arrays_ArrayInitializer_1(org.emftext.language.java.arrays.ArrayInitializer element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_arrays_ArraySelector(org.emftext.language.java.arrays.ArraySelector element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_SELECTOR__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_SELECTOR__POSITION));
		printCountingMap.put("position", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("[");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("position");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_SELECTOR__POSITION));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("position", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("]");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_types_NamespaceClassifierReference(org.emftext.language.java.types.NamespaceClassifierReference element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.NAMESPACE_CLASSIFIER_REFERENCE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.NAMESPACE_CLASSIFIER_REFERENCE__NAMESPACES));
		printCountingMap.put("namespaces", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.NAMESPACE_CLASSIFIER_REFERENCE__CLASSIFIER_REFERENCES));
		printCountingMap.put("classifierReferences", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_types_NamespaceClassifierReference_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_types_NamespaceClassifierReference_1(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				if (				printCountingMap1.get("classifierReferences")<1				) {
					iterate = false;
					out1.close();
				} else {
					out1.flush();
					out1.close();
					out.print(sWriter.toString());
					printCountingMap.putAll(printCountingMap1);
				}
			}
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("classifierReferences");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.NAMESPACE_CLASSIFIER_REFERENCE__CLASSIFIER_REFERENCES));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("classifierReferences", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_types_NamespaceClassifierReference_0(org.emftext.language.java.types.NamespaceClassifierReference element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("namespaces");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.NAMESPACE_CLASSIFIER_REFERENCE__NAMESPACES));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.NAMESPACE_CLASSIFIER_REFERENCE__NAMESPACES), element));
				out.print(" ");
			}
			printCountingMap.put("namespaces", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_types_NamespaceClassifierReference_1(org.emftext.language.java.types.NamespaceClassifierReference element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("classifierReferences");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.NAMESPACE_CLASSIFIER_REFERENCE__CLASSIFIER_REFERENCES));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("classifierReferences", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_types_ClassifierReference(org.emftext.language.java.types.ClassifierReference element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.CLASSIFIER_REFERENCE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.CLASSIFIER_REFERENCE__TYPE_ARGUMENTS));
		printCountingMap.put("typeArguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.CLASSIFIER_REFERENCE__TARGET));
		printCountingMap.put("target", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("target");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.CLASSIFIER_REFERENCE__TARGET));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getClassifierReferenceTargetReferenceResolver().deResolve((org.emftext.language.java.classifiers.Classifier) o, element, (EReference) element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.CLASSIFIER_REFERENCE__TARGET)), element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.CLASSIFIER_REFERENCE__TARGET), element));
				out.print(" ");
			}
			printCountingMap.put("target", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_types_ClassifierReference_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_types_ClassifierReference_0(org.emftext.language.java.types.ClassifierReference element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.CLASSIFIER_REFERENCE__TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeArguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_types_ClassifierReference_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_types_ClassifierReference_0_0(org.emftext.language.java.types.ClassifierReference element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.CLASSIFIER_REFERENCE__TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeArguments", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_references_MethodCall(org.emftext.language.java.references.MethodCall element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(7);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__TYPE_ARGUMENTS));
		printCountingMap.put("typeArguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__NEXT));
		printCountingMap.put("next", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__ARRAY_SELECTORS));
		printCountingMap.put("arraySelectors", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__TARGET));
		printCountingMap.put("target", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__ARGUMENTS));
		printCountingMap.put("arguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__CALL_TYPE_ARGUMENTS));
		printCountingMap.put("callTypeArguments", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_references_MethodCall_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("target");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__TARGET));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getElementReferenceTargetReferenceResolver().deResolve((org.emftext.language.java.references.ReferenceableElement) o, element, (EReference) element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__TARGET)), element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__TARGET), element));
				out.print(" ");
			}
			printCountingMap.put("target", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_references_MethodCall_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_references_MethodCall_2(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arraySelectors");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__ARRAY_SELECTORS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arraySelectors", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_references_MethodCall_3(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_references_MethodCall_0(org.emftext.language.java.references.MethodCall element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("callTypeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__CALL_TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("callTypeArguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_references_MethodCall_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_references_MethodCall_0_0(org.emftext.language.java.references.MethodCall element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("callTypeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__CALL_TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("callTypeArguments", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_references_MethodCall_1(org.emftext.language.java.references.MethodCall element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeArguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_references_MethodCall_1_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_references_MethodCall_1_0(org.emftext.language.java.references.MethodCall element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeArguments", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_references_MethodCall_2(org.emftext.language.java.references.MethodCall element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_references_MethodCall_2_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_references_MethodCall_2_0(org.emftext.language.java.references.MethodCall element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_references_MethodCall_3(org.emftext.language.java.references.MethodCall element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("next");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__NEXT));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("next", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_references_IdentifierReference(org.emftext.language.java.references.IdentifierReference element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(5);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__TYPE_ARGUMENTS));
		printCountingMap.put("typeArguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__NEXT));
		printCountingMap.put("next", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__ARRAY_SELECTORS));
		printCountingMap.put("arraySelectors", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__TARGET));
		printCountingMap.put("target", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("target");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__TARGET));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getElementReferenceTargetReferenceResolver().deResolve((org.emftext.language.java.references.ReferenceableElement) o, element, (EReference) element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__TARGET)), element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__TARGET), element));
				out.print(" ");
			}
			printCountingMap.put("target", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_references_IdentifierReference_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arraySelectors");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__ARRAY_SELECTORS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arraySelectors", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_references_IdentifierReference_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_references_IdentifierReference_0(org.emftext.language.java.references.IdentifierReference element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeArguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_references_IdentifierReference_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_references_IdentifierReference_0_0(org.emftext.language.java.references.IdentifierReference element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeArguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__TYPE_ARGUMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeArguments", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_references_IdentifierReference_1(org.emftext.language.java.references.IdentifierReference element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("next");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__NEXT));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("next", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_references_ReflectiveClassReference(org.emftext.language.java.references.ReflectiveClassReference element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.REFLECTIVE_CLASS_REFERENCE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.REFLECTIVE_CLASS_REFERENCE__TYPE_ARGUMENTS));
		printCountingMap.put("typeArguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.REFLECTIVE_CLASS_REFERENCE__NEXT));
		printCountingMap.put("next", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.REFLECTIVE_CLASS_REFERENCE__ARRAY_SELECTORS));
		printCountingMap.put("arraySelectors", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("class");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_references_ReflectiveClassReference_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_references_ReflectiveClassReference_0(org.emftext.language.java.references.ReflectiveClassReference element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("next");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.REFLECTIVE_CLASS_REFERENCE__NEXT));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("next", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_references_SelfReference(org.emftext.language.java.references.SelfReference element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(5);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.SELF_REFERENCE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.SELF_REFERENCE__TYPE_ARGUMENTS));
		printCountingMap.put("typeArguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.SELF_REFERENCE__NEXT));
		printCountingMap.put("next", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.SELF_REFERENCE__ARRAY_SELECTORS));
		printCountingMap.put("arraySelectors", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.SELF_REFERENCE__SELF));
		printCountingMap.put("self", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("self");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.SELF_REFERENCE__SELF));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("self", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_references_SelfReference_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_references_SelfReference_0(org.emftext.language.java.references.SelfReference element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("next");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.SELF_REFERENCE__NEXT));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("next", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_references_PrimitiveTypeReference(org.emftext.language.java.references.PrimitiveTypeReference element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(5);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.PRIMITIVE_TYPE_REFERENCE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.PRIMITIVE_TYPE_REFERENCE__TYPE_ARGUMENTS));
		printCountingMap.put("typeArguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.PRIMITIVE_TYPE_REFERENCE__NEXT));
		printCountingMap.put("next", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.PRIMITIVE_TYPE_REFERENCE__ARRAY_SELECTORS));
		printCountingMap.put("arraySelectors", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.PRIMITIVE_TYPE_REFERENCE__PRIMITIVE_TYPE));
		printCountingMap.put("primitiveType", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("primitiveType");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.PRIMITIVE_TYPE_REFERENCE__PRIMITIVE_TYPE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("primitiveType", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arraySelectors");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.PRIMITIVE_TYPE_REFERENCE__ARRAY_SELECTORS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arraySelectors", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_references_PrimitiveTypeReference_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_references_PrimitiveTypeReference_0(org.emftext.language.java.references.PrimitiveTypeReference element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("next");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.PRIMITIVE_TYPE_REFERENCE__NEXT));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("next", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_literals_This(org.emftext.language.java.literals.This element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.THIS__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("this");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_literals_Super(org.emftext.language.java.literals.Super element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.SUPER__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("super");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_references_StringReference(org.emftext.language.java.references.StringReference element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(5);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.STRING_REFERENCE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.STRING_REFERENCE__TYPE_ARGUMENTS));
		printCountingMap.put("typeArguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.STRING_REFERENCE__NEXT));
		printCountingMap.put("next", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.STRING_REFERENCE__ARRAY_SELECTORS));
		printCountingMap.put("arraySelectors", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.STRING_REFERENCE__VALUE));
		printCountingMap.put("value", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("value");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.STRING_REFERENCE__VALUE));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("STRING_LITERAL");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.STRING_REFERENCE__VALUE), element));
				out.print(" ");
			}
			printCountingMap.put("value", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_references_StringReference_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_references_StringReference_0(org.emftext.language.java.references.StringReference element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("next");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.STRING_REFERENCE__NEXT));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("next", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_generics_QualifiedTypeArgument(org.emftext.language.java.generics.QualifiedTypeArgument element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.QUALIFIED_TYPE_ARGUMENT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.QUALIFIED_TYPE_ARGUMENT__ARRAY_DIMENSIONS_BEFORE));
		printCountingMap.put("arrayDimensionsBefore", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.QUALIFIED_TYPE_ARGUMENT__ARRAY_DIMENSIONS_AFTER));
		printCountingMap.put("arrayDimensionsAfter", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.QUALIFIED_TYPE_ARGUMENT__TYPE_REFERENCE));
		printCountingMap.put("typeReference", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeReference");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.QUALIFIED_TYPE_ARGUMENT__TYPE_REFERENCE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeReference", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsBefore");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.QUALIFIED_TYPE_ARGUMENT__ARRAY_DIMENSIONS_BEFORE));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsBefore", 0);
		}
	}
	
	
	public void print_org_emftext_language_java_generics_UnknownTypeArgument(org.emftext.language.java.generics.UnknownTypeArgument element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.UNKNOWN_TYPE_ARGUMENT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.UNKNOWN_TYPE_ARGUMENT__ARRAY_DIMENSIONS_BEFORE));
		printCountingMap.put("arrayDimensionsBefore", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.UNKNOWN_TYPE_ARGUMENT__ARRAY_DIMENSIONS_AFTER));
		printCountingMap.put("arrayDimensionsAfter", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("?");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_generics_ExtendsTypeArgument(org.emftext.language.java.generics.ExtendsTypeArgument element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.EXTENDS_TYPE_ARGUMENT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.EXTENDS_TYPE_ARGUMENT__ARRAY_DIMENSIONS_BEFORE));
		printCountingMap.put("arrayDimensionsBefore", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.EXTENDS_TYPE_ARGUMENT__ARRAY_DIMENSIONS_AFTER));
		printCountingMap.put("arrayDimensionsAfter", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.EXTENDS_TYPE_ARGUMENT__EXTEND_TYPES));
		printCountingMap.put("extendTypes", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("?");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("extends");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("extendTypes");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.EXTENDS_TYPE_ARGUMENT__EXTEND_TYPES));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("extendTypes", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_generics_ExtendsTypeArgument_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsBefore");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.EXTENDS_TYPE_ARGUMENT__ARRAY_DIMENSIONS_BEFORE));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsBefore", 0);
		}
	}
	
	public void print_org_emftext_language_java_generics_ExtendsTypeArgument_0(org.emftext.language.java.generics.ExtendsTypeArgument element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("&");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("extendTypes");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.EXTENDS_TYPE_ARGUMENT__EXTEND_TYPES));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("extendTypes", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_generics_SuperTypeArgument(org.emftext.language.java.generics.SuperTypeArgument element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.SUPER_TYPE_ARGUMENT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.SUPER_TYPE_ARGUMENT__ARRAY_DIMENSIONS_BEFORE));
		printCountingMap.put("arrayDimensionsBefore", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.SUPER_TYPE_ARGUMENT__ARRAY_DIMENSIONS_AFTER));
		printCountingMap.put("arrayDimensionsAfter", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.SUPER_TYPE_ARGUMENT__SUPER_TYPE));
		printCountingMap.put("superType", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("?");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("super");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("superType");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.SUPER_TYPE_ARGUMENT__SUPER_TYPE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("superType", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsBefore");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.SUPER_TYPE_ARGUMENT__ARRAY_DIMENSIONS_BEFORE));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsBefore", 0);
		}
	}
	
	
	public void print_org_emftext_language_java_statements_Assert(org.emftext.language.java.statements.Assert element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.ASSERT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.ASSERT__CONDITION));
		printCountingMap.put("condition", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.ASSERT__ERROR_MESSAGE));
		printCountingMap.put("errorMessage", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("assert");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("condition");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.ASSERT__CONDITION));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("condition", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_statements_Assert_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_statements_Assert_0(org.emftext.language.java.statements.Assert element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("errorMessage");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.ASSERT__ERROR_MESSAGE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("errorMessage", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_statements_Condition(org.emftext.language.java.statements.Condition element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONDITION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONDITION__STATEMENT));
		printCountingMap.put("statement", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONDITION__CONDITION));
		printCountingMap.put("condition", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONDITION__ELSE_STATEMENT));
		printCountingMap.put("elseStatement", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("if");
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("condition");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONDITION__CONDITION));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("condition", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("statement");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONDITION__STATEMENT));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("statement", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_statements_Condition_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_statements_Condition_0(org.emftext.language.java.statements.Condition element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("else");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("elseStatement");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONDITION__ELSE_STATEMENT));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("elseStatement", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_statements_ForLoop(org.emftext.language.java.statements.ForLoop element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(5);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__STATEMENT));
		printCountingMap.put("statement", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__CONDITION));
		printCountingMap.put("condition", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__INIT));
		printCountingMap.put("init", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__UPDATES));
		printCountingMap.put("updates", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("for");
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("init");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__INIT));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("init", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("condition");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__CONDITION));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("condition", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_statements_ForLoop_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("statement");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__STATEMENT));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("statement", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_statements_ForLoop_0(org.emftext.language.java.statements.ForLoop element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("updates");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__UPDATES));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("updates", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_statements_ForLoop_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_statements_ForLoop_0_0(org.emftext.language.java.statements.ForLoop element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("updates");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__UPDATES));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("updates", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_statements_ForEachLoop(org.emftext.language.java.statements.ForEachLoop element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_EACH_LOOP__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_EACH_LOOP__STATEMENT));
		printCountingMap.put("statement", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_EACH_LOOP__NEXT));
		printCountingMap.put("next", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_EACH_LOOP__COLLECTION));
		printCountingMap.put("collection", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("for");
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("next");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_EACH_LOOP__NEXT));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("next", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("collection");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_EACH_LOOP__COLLECTION));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("collection", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("statement");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_EACH_LOOP__STATEMENT));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("statement", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_statements_WhileLoop(org.emftext.language.java.statements.WhileLoop element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.WHILE_LOOP__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.WHILE_LOOP__STATEMENT));
		printCountingMap.put("statement", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.WHILE_LOOP__CONDITION));
		printCountingMap.put("condition", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("while");
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("condition");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.WHILE_LOOP__CONDITION));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("condition", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("statement");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.WHILE_LOOP__STATEMENT));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("statement", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_statements_DoWhileLoop(org.emftext.language.java.statements.DoWhileLoop element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.DO_WHILE_LOOP__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.DO_WHILE_LOOP__STATEMENT));
		printCountingMap.put("statement", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.DO_WHILE_LOOP__CONDITION));
		printCountingMap.put("condition", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("do");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("statement");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.DO_WHILE_LOOP__STATEMENT));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("statement", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("while");
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("condition");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.DO_WHILE_LOOP__CONDITION));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("condition", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_statements_EmptyStatement(org.emftext.language.java.statements.EmptyStatement element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.EMPTY_STATEMENT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_statements_SynchronizedBlock(org.emftext.language.java.statements.SynchronizedBlock element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.SYNCHRONIZED_BLOCK__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.SYNCHRONIZED_BLOCK__STATEMENTS));
		printCountingMap.put("statements", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.SYNCHRONIZED_BLOCK__LOCK_PROVIDER));
		printCountingMap.put("lockProvider", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("synchronized");
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("lockProvider");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.SYNCHRONIZED_BLOCK__LOCK_PROVIDER));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("lockProvider", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("{");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_statements_SynchronizedBlock_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (CsString)
		out.print("}");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_statements_SynchronizedBlock_0(org.emftext.language.java.statements.SynchronizedBlock element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "	";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("statements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.SYNCHRONIZED_BLOCK__STATEMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("statements", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_statements_TryBlock(org.emftext.language.java.statements.TryBlock element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.TRY_BLOCK__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.TRY_BLOCK__STATEMENTS));
		printCountingMap.put("statements", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.TRY_BLOCK__CATCHE_BLOCKS));
		printCountingMap.put("catcheBlocks", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.TRY_BLOCK__FINALLY_BLOCK));
		printCountingMap.put("finallyBlock", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("try");
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("{");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_statements_TryBlock_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (CsString)
		out.print("}");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("catcheBlocks");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.TRY_BLOCK__CATCHE_BLOCKS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("catcheBlocks", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_statements_TryBlock_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_statements_TryBlock_0(org.emftext.language.java.statements.TryBlock element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "	";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("statements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.TRY_BLOCK__STATEMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("statements", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_statements_TryBlock_1(org.emftext.language.java.statements.TryBlock element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("finally");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("finallyBlock");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.TRY_BLOCK__FINALLY_BLOCK));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("finallyBlock", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_statements_CatchBlock(org.emftext.language.java.statements.CatchBlock element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CATCH_BLOCK__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CATCH_BLOCK__STATEMENTS));
		printCountingMap.put("statements", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CATCH_BLOCK__PARAMETER));
		printCountingMap.put("parameter", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("catch");
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("parameter");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CATCH_BLOCK__PARAMETER));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("parameter", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("{");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_statements_CatchBlock_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (CsString)
		out.print("}");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_statements_CatchBlock_0(org.emftext.language.java.statements.CatchBlock element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "	";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("statements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CATCH_BLOCK__STATEMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("statements", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_statements_Switch(org.emftext.language.java.statements.Switch element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.SWITCH__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.SWITCH__CASES));
		printCountingMap.put("cases", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.SWITCH__VARIABLE));
		printCountingMap.put("variable", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("switch");
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("variable");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.SWITCH__VARIABLE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("variable", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("{");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_org_emftext_language_java_statements_Switch_0(element, localtab, out, printCountingMap);
		// DEFINITION PART BEGINS (CsString)
		out.print("}");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_statements_Switch_0(org.emftext.language.java.statements.Switch element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("cases");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.SWITCH__CASES));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("cases", 0);
		}
	}
	
	
	public void print_org_emftext_language_java_statements_NormalSwitchCase(org.emftext.language.java.statements.NormalSwitchCase element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.NORMAL_SWITCH_CASE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.NORMAL_SWITCH_CASE__STATEMENTS));
		printCountingMap.put("statements", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.NORMAL_SWITCH_CASE__CONDITION));
		printCountingMap.put("condition", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("case");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("condition");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.NORMAL_SWITCH_CASE__CONDITION));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("condition", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_statements_NormalSwitchCase_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
	}
	
	public void print_org_emftext_language_java_statements_NormalSwitchCase_0(org.emftext.language.java.statements.NormalSwitchCase element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "	";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("statements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.NORMAL_SWITCH_CASE__STATEMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("statements", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_statements_DefaultSwitchCase(org.emftext.language.java.statements.DefaultSwitchCase element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.DEFAULT_SWITCH_CASE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.DEFAULT_SWITCH_CASE__STATEMENTS));
		printCountingMap.put("statements", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("default");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_statements_DefaultSwitchCase_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
	}
	
	public void print_org_emftext_language_java_statements_DefaultSwitchCase_0(org.emftext.language.java.statements.DefaultSwitchCase element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "	";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("statements");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.DEFAULT_SWITCH_CASE__STATEMENTS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("statements", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_statements_Return(org.emftext.language.java.statements.Return element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.RETURN__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.RETURN__RETURN_VALUE));
		printCountingMap.put("returnValue", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("return");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("returnValue");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.RETURN__RETURN_VALUE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("returnValue", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_statements_Throw(org.emftext.language.java.statements.Throw element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.THROW__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.THROW__THROWABLE));
		printCountingMap.put("throwable", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("throw");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("throwable");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.THROW__THROWABLE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("throwable", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_statements_Break(org.emftext.language.java.statements.Break element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.BREAK__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.BREAK__TARGET));
		printCountingMap.put("target", temp == null ? 0 : 1);
		// print collected hidden tokens
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("break");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_statements_Break_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_statements_Break_0(org.emftext.language.java.statements.Break element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("target");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.BREAK__TARGET));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getJumpTargetReferenceResolver().deResolve((org.emftext.language.java.statements.JumpLabel) o, element, (EReference) element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.BREAK__TARGET)), element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.BREAK__TARGET), element));
				out.print(" ");
			}
			printCountingMap.put("target", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_statements_Continue(org.emftext.language.java.statements.Continue element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONTINUE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONTINUE__TARGET));
		printCountingMap.put("target", temp == null ? 0 : 1);
		// print collected hidden tokens
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("continue");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_statements_Continue_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	public void print_org_emftext_language_java_statements_Continue_0(org.emftext.language.java.statements.Continue element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("target");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONTINUE__TARGET));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getJumpTargetReferenceResolver().deResolve((org.emftext.language.java.statements.JumpLabel) o, element, (EReference) element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONTINUE__TARGET)), element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONTINUE__TARGET), element));
				out.print(" ");
			}
			printCountingMap.put("target", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_statements_JumpLabel(org.emftext.language.java.statements.JumpLabel element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.JUMP_LABEL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.JUMP_LABEL__STATEMENT));
		printCountingMap.put("statement", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.JUMP_LABEL__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingDefaultToken)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.JUMP_LABEL__NAME));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.JUMP_LABEL__NAME), element));
				out.print(" ");
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("statement");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.JUMP_LABEL__STATEMENT));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("statement", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_statements_ExpressionStatement(org.emftext.language.java.statements.ExpressionStatement element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.EXPRESSION_STATEMENT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.EXPRESSION_STATEMENT__EXPRESSION));
		printCountingMap.put("expression", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("expression");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.EXPRESSION_STATEMENT__EXPRESSION));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("expression", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_expressions_ExpressionList(org.emftext.language.java.expressions.ExpressionList element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EXPRESSION_LIST__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EXPRESSION_LIST__EXPRESSIONS));
		printCountingMap.put("expressions", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("expressions");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EXPRESSION_LIST__EXPRESSIONS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("expressions", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_expressions_ExpressionList_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_expressions_ExpressionList_0(org.emftext.language.java.expressions.ExpressionList element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("expressions");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EXPRESSION_LIST__EXPRESSIONS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("expressions", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_expressions_AssignmentExpression(org.emftext.language.java.expressions.AssignmentExpression element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ASSIGNMENT_EXPRESSION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ASSIGNMENT_EXPRESSION__CHILD));
		printCountingMap.put("child", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ASSIGNMENT_EXPRESSION__ASSIGNMENT_OPERATOR));
		printCountingMap.put("assignmentOperator", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ASSIGNMENT_EXPRESSION__VALUE));
		printCountingMap.put("value", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("child");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ASSIGNMENT_EXPRESSION__CHILD));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("child", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_expressions_AssignmentExpression_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_expressions_AssignmentExpression_0(org.emftext.language.java.expressions.AssignmentExpression element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("assignmentOperator");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ASSIGNMENT_EXPRESSION__ASSIGNMENT_OPERATOR));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("assignmentOperator", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("value");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ASSIGNMENT_EXPRESSION__VALUE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("value", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_expressions_ConditionalExpression(org.emftext.language.java.expressions.ConditionalExpression element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_EXPRESSION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_EXPRESSION__CHILD));
		printCountingMap.put("child", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_EXPRESSION__EXPRESSION_IF));
		printCountingMap.put("expressionIf", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_EXPRESSION__EXPRESSION_ELSE));
		printCountingMap.put("expressionElse", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("child");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_EXPRESSION__CHILD));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("child", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_expressions_ConditionalExpression_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_expressions_ConditionalExpression_0(org.emftext.language.java.expressions.ConditionalExpression element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("?");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("expressionIf");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_EXPRESSION__EXPRESSION_IF));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("expressionIf", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("expressionElse");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_EXPRESSION__EXPRESSION_ELSE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("expressionElse", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_expressions_ConditionalOrExpression(org.emftext.language.java.expressions.ConditionalOrExpression element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_OR_EXPRESSION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_OR_EXPRESSION__CHILDREN));
		printCountingMap.put("children", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("children");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_OR_EXPRESSION__CHILDREN));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("children", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_expressions_ConditionalOrExpression_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_expressions_ConditionalOrExpression_0(org.emftext.language.java.expressions.ConditionalOrExpression element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("||");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("children");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_OR_EXPRESSION__CHILDREN));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("children", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_expressions_ConditionalAndExpression(org.emftext.language.java.expressions.ConditionalAndExpression element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_AND_EXPRESSION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_AND_EXPRESSION__CHILDREN));
		printCountingMap.put("children", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("children");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_AND_EXPRESSION__CHILDREN));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("children", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_expressions_ConditionalAndExpression_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_expressions_ConditionalAndExpression_0(org.emftext.language.java.expressions.ConditionalAndExpression element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("&&");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("children");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_AND_EXPRESSION__CHILDREN));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("children", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_expressions_InclusiveOrExpression(org.emftext.language.java.expressions.InclusiveOrExpression element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INCLUSIVE_OR_EXPRESSION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INCLUSIVE_OR_EXPRESSION__CHILDREN));
		printCountingMap.put("children", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("children");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INCLUSIVE_OR_EXPRESSION__CHILDREN));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("children", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_expressions_InclusiveOrExpression_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_expressions_InclusiveOrExpression_0(org.emftext.language.java.expressions.InclusiveOrExpression element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("|");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("children");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INCLUSIVE_OR_EXPRESSION__CHILDREN));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("children", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_expressions_ExclusiveOrExpression(org.emftext.language.java.expressions.ExclusiveOrExpression element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EXCLUSIVE_OR_EXPRESSION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EXCLUSIVE_OR_EXPRESSION__CHILDREN));
		printCountingMap.put("children", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("children");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EXCLUSIVE_OR_EXPRESSION__CHILDREN));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("children", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_expressions_ExclusiveOrExpression_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_expressions_ExclusiveOrExpression_0(org.emftext.language.java.expressions.ExclusiveOrExpression element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("^");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("children");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EXCLUSIVE_OR_EXPRESSION__CHILDREN));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("children", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_expressions_AndExpression(org.emftext.language.java.expressions.AndExpression element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.AND_EXPRESSION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.AND_EXPRESSION__CHILDREN));
		printCountingMap.put("children", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("children");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.AND_EXPRESSION__CHILDREN));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("children", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_expressions_AndExpression_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_expressions_AndExpression_0(org.emftext.language.java.expressions.AndExpression element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("&");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("children");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.AND_EXPRESSION__CHILDREN));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("children", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_expressions_EqualityExpression(org.emftext.language.java.expressions.EqualityExpression element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EQUALITY_EXPRESSION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EQUALITY_EXPRESSION__EQUALITY_OPERATORS));
		printCountingMap.put("equalityOperators", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EQUALITY_EXPRESSION__CHILDREN));
		printCountingMap.put("children", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("children");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EQUALITY_EXPRESSION__CHILDREN));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("children", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_expressions_EqualityExpression_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_expressions_EqualityExpression_0(org.emftext.language.java.expressions.EqualityExpression element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("equalityOperators");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EQUALITY_EXPRESSION__EQUALITY_OPERATORS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("equalityOperators", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("children");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EQUALITY_EXPRESSION__CHILDREN));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("children", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_expressions_InstanceOfExpression(org.emftext.language.java.expressions.InstanceOfExpression element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(5);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INSTANCE_OF_EXPRESSION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INSTANCE_OF_EXPRESSION__ARRAY_DIMENSIONS_BEFORE));
		printCountingMap.put("arrayDimensionsBefore", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INSTANCE_OF_EXPRESSION__ARRAY_DIMENSIONS_AFTER));
		printCountingMap.put("arrayDimensionsAfter", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INSTANCE_OF_EXPRESSION__TYPE_REFERENCE));
		printCountingMap.put("typeReference", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INSTANCE_OF_EXPRESSION__CHILD));
		printCountingMap.put("child", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("child");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INSTANCE_OF_EXPRESSION__CHILD));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("child", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_expressions_InstanceOfExpression_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_expressions_InstanceOfExpression_0(org.emftext.language.java.expressions.InstanceOfExpression element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("instanceof");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeReference");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INSTANCE_OF_EXPRESSION__TYPE_REFERENCE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeReference", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsBefore");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INSTANCE_OF_EXPRESSION__ARRAY_DIMENSIONS_BEFORE));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsBefore", 0);
		}
	}
	
	
	public void print_org_emftext_language_java_expressions_RelationExpression(org.emftext.language.java.expressions.RelationExpression element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.RELATION_EXPRESSION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.RELATION_EXPRESSION__CHILDREN));
		printCountingMap.put("children", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.RELATION_EXPRESSION__RELATION_OPERATORS));
		printCountingMap.put("relationOperators", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("children");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.RELATION_EXPRESSION__CHILDREN));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("children", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_expressions_RelationExpression_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_expressions_RelationExpression_0(org.emftext.language.java.expressions.RelationExpression element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("relationOperators");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.RELATION_EXPRESSION__RELATION_OPERATORS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("relationOperators", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("children");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.RELATION_EXPRESSION__CHILDREN));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("children", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_expressions_ShiftExpression(org.emftext.language.java.expressions.ShiftExpression element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SHIFT_EXPRESSION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SHIFT_EXPRESSION__CHILDREN));
		printCountingMap.put("children", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SHIFT_EXPRESSION__SHIFT_OPERATORS));
		printCountingMap.put("shiftOperators", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("children");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SHIFT_EXPRESSION__CHILDREN));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("children", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_expressions_ShiftExpression_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_expressions_ShiftExpression_0(org.emftext.language.java.expressions.ShiftExpression element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("shiftOperators");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SHIFT_EXPRESSION__SHIFT_OPERATORS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("shiftOperators", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("children");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SHIFT_EXPRESSION__CHILDREN));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("children", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_expressions_AdditiveExpression(org.emftext.language.java.expressions.AdditiveExpression element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ADDITIVE_EXPRESSION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ADDITIVE_EXPRESSION__CHILDREN));
		printCountingMap.put("children", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ADDITIVE_EXPRESSION__ADDITIVE_OPERATORS));
		printCountingMap.put("additiveOperators", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("children");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ADDITIVE_EXPRESSION__CHILDREN));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("children", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_expressions_AdditiveExpression_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_expressions_AdditiveExpression_0(org.emftext.language.java.expressions.AdditiveExpression element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("additiveOperators");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ADDITIVE_EXPRESSION__ADDITIVE_OPERATORS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("additiveOperators", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("children");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ADDITIVE_EXPRESSION__CHILDREN));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("children", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_expressions_MultiplicativeExpression(org.emftext.language.java.expressions.MultiplicativeExpression element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.MULTIPLICATIVE_EXPRESSION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.MULTIPLICATIVE_EXPRESSION__CHILDREN));
		printCountingMap.put("children", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OPERATORS));
		printCountingMap.put("multiplicativeOperators", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("children");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.MULTIPLICATIVE_EXPRESSION__CHILDREN));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("children", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new StringWriter();
			out1 = new PrintWriter(sWriter);
			printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
			print_org_emftext_language_java_expressions_MultiplicativeExpression_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_emftext_language_java_expressions_MultiplicativeExpression_0(org.emftext.language.java.expressions.MultiplicativeExpression element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("multiplicativeOperators");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OPERATORS));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("multiplicativeOperators", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("children");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.MULTIPLICATIVE_EXPRESSION__CHILDREN));
			List<?> list = (List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("children", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_expressions_UnaryExpression(org.emftext.language.java.expressions.UnaryExpression element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.UNARY_EXPRESSION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.UNARY_EXPRESSION__OPERATORS));
		printCountingMap.put("operators", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.UNARY_EXPRESSION__CHILD));
		printCountingMap.put("child", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("operators");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.UNARY_EXPRESSION__OPERATORS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("operators", 0);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("child");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.UNARY_EXPRESSION__CHILD));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("child", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_expressions_SuffixUnaryModificationExpression(org.emftext.language.java.expressions.SuffixUnaryModificationExpression element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SUFFIX_UNARY_MODIFICATION_EXPRESSION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SUFFIX_UNARY_MODIFICATION_EXPRESSION__CHILD));
		printCountingMap.put("child", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SUFFIX_UNARY_MODIFICATION_EXPRESSION__OPERATOR));
		printCountingMap.put("operator", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("child");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SUFFIX_UNARY_MODIFICATION_EXPRESSION__CHILD));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("child", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_expressions_SuffixUnaryModificationExpression_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_expressions_SuffixUnaryModificationExpression_0(org.emftext.language.java.expressions.SuffixUnaryModificationExpression element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("operator");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SUFFIX_UNARY_MODIFICATION_EXPRESSION__OPERATOR));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("operator", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_expressions_PrefixUnaryModificationExpression(org.emftext.language.java.expressions.PrefixUnaryModificationExpression element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.PREFIX_UNARY_MODIFICATION_EXPRESSION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.PREFIX_UNARY_MODIFICATION_EXPRESSION__CHILD));
		printCountingMap.put("child", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.PREFIX_UNARY_MODIFICATION_EXPRESSION__OPERATOR));
		printCountingMap.put("operator", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_expressions_PrefixUnaryModificationExpression_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("child");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.PREFIX_UNARY_MODIFICATION_EXPRESSION__CHILD));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("child", count - 1);
		}
	}
	
	public void print_org_emftext_language_java_expressions_PrefixUnaryModificationExpression_0(org.emftext.language.java.expressions.PrefixUnaryModificationExpression element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("operator");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.PREFIX_UNARY_MODIFICATION_EXPRESSION__OPERATOR));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("operator", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_expressions_CastExpression(org.emftext.language.java.expressions.CastExpression element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(5);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CAST_EXPRESSION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CAST_EXPRESSION__TYPE_REFERENCE));
		printCountingMap.put("typeReference", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_BEFORE));
		printCountingMap.put("arrayDimensionsBefore", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_AFTER));
		printCountingMap.put("arrayDimensionsAfter", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CAST_EXPRESSION__CHILD));
		printCountingMap.put("child", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeReference");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CAST_EXPRESSION__TYPE_REFERENCE));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("typeReference", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arrayDimensionsBefore");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_BEFORE));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arrayDimensionsBefore", 0);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		// DEFINITION PART BEGINS (WhiteSpaces)
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("child");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CAST_EXPRESSION__CHILD));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("child", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_expressions_NestedExpression(org.emftext.language.java.expressions.NestedExpression element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(5);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.NESTED_EXPRESSION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.NESTED_EXPRESSION__TYPE_ARGUMENTS));
		printCountingMap.put("typeArguments", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.NESTED_EXPRESSION__NEXT));
		printCountingMap.put("next", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.NESTED_EXPRESSION__ARRAY_SELECTORS));
		printCountingMap.put("arraySelectors", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.NESTED_EXPRESSION__EXPRESSION));
		printCountingMap.put("expression", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		PrintWriter out1 = null;
		Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("expression");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.NESTED_EXPRESSION__EXPRESSION));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("expression", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arraySelectors");
		if (count > 0) {
			List<?> list = (List<?>)element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.NESTED_EXPRESSION__ARRAY_SELECTORS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("arraySelectors", 0);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new StringWriter();
		out1 = new PrintWriter(sWriter);
		printCountingMap1 = new LinkedHashMap<String, Integer>(printCountingMap);
		print_org_emftext_language_java_expressions_NestedExpression_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_emftext_language_java_expressions_NestedExpression_0(org.emftext.language.java.expressions.NestedExpression element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(".");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("next");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.NESTED_EXPRESSION__NEXT));
			if (o != null) {
				doPrint((EObject) o, out, localtab);
			}
			printCountingMap.put("next", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_operators_Assignment(org.emftext.language.java.operators.Assignment element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.ASSIGNMENT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_AssignmentPlus(org.emftext.language.java.operators.AssignmentPlus element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.ASSIGNMENT_PLUS__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("+=");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_AssignmentMinus(org.emftext.language.java.operators.AssignmentMinus element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.ASSIGNMENT_MINUS__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("-=");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_AssignmentMultiplication(org.emftext.language.java.operators.AssignmentMultiplication element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.ASSIGNMENT_MULTIPLICATION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("*=");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_AssignmentDivision(org.emftext.language.java.operators.AssignmentDivision element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.ASSIGNMENT_DIVISION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("/=");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_AssignmentAnd(org.emftext.language.java.operators.AssignmentAnd element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.ASSIGNMENT_AND__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("&=");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_AssignmentOr(org.emftext.language.java.operators.AssignmentOr element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.ASSIGNMENT_OR__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("|=");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_AssignmentExclusiveOr(org.emftext.language.java.operators.AssignmentExclusiveOr element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.ASSIGNMENT_EXCLUSIVE_OR__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("^=");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_AssignmentModulo(org.emftext.language.java.operators.AssignmentModulo element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.ASSIGNMENT_MODULO__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("%=");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_AssignmentLeftShift(org.emftext.language.java.operators.AssignmentLeftShift element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.ASSIGNMENT_LEFT_SHIFT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_AssignmentRightShift(org.emftext.language.java.operators.AssignmentRightShift element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.ASSIGNMENT_RIGHT_SHIFT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_AssignmentUnsignedRightShift(org.emftext.language.java.operators.AssignmentUnsignedRightShift element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.ASSIGNMENT_UNSIGNED_RIGHT_SHIFT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_Addition(org.emftext.language.java.operators.Addition element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.ADDITION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("+");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_Subtraction(org.emftext.language.java.operators.Subtraction element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.SUBTRACTION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("-");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_Multiplication(org.emftext.language.java.operators.Multiplication element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.MULTIPLICATION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("*");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_Division(org.emftext.language.java.operators.Division element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.DIVISION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("/");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_Remainder(org.emftext.language.java.operators.Remainder element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.REMAINDER__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("%");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_LessThan(org.emftext.language.java.operators.LessThan element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.LESS_THAN__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_LessThanOrEqual(org.emftext.language.java.operators.LessThanOrEqual element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.LESS_THAN_OR_EQUAL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_GreaterThan(org.emftext.language.java.operators.GreaterThan element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.GREATER_THAN__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_GreaterThanOrEqual(org.emftext.language.java.operators.GreaterThanOrEqual element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.GREATER_THAN_OR_EQUAL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("=");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_LeftShift(org.emftext.language.java.operators.LeftShift element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.LEFT_SHIFT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_RightShift(org.emftext.language.java.operators.RightShift element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.RIGHT_SHIFT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_UnsignedRightShift(org.emftext.language.java.operators.UnsignedRightShift element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.UNSIGNED_RIGHT_SHIFT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_Equal(org.emftext.language.java.operators.Equal element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.EQUAL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("==");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_NotEqual(org.emftext.language.java.operators.NotEqual element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.NOT_EQUAL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("!=");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_PlusPlus(org.emftext.language.java.operators.PlusPlus element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.PLUS_PLUS__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("++");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_MinusMinus(org.emftext.language.java.operators.MinusMinus element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.MINUS_MINUS__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("--");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_Complement(org.emftext.language.java.operators.Complement element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.COMPLEMENT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("~");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_operators_Negate(org.emftext.language.java.operators.Negate element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.operators.OperatorsPackage.NEGATE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("!");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_arrays_ArrayDimension(org.emftext.language.java.arrays.ArrayDimension element, String outertab, PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_DIMENSION__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_org_emftext_language_java_arrays_ArrayDimension_0(element, localtab, out, printCountingMap);
	}
	
	public void print_org_emftext_language_java_arrays_ArrayDimension_0(org.emftext.language.java.arrays.ArrayDimension element, String outertab, PrintWriter out, Map<String, Integer> printCountingMap) {
		// DEFINITION PART BEGINS (CsString)
		out.print("[");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("]");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_literals_NullLiteral(org.emftext.language.java.literals.NullLiteral element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.NULL_LITERAL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("null");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_modifiers_Public(org.emftext.language.java.modifiers.Public element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.modifiers.ModifiersPackage.PUBLIC__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("public");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_modifiers_Abstract(org.emftext.language.java.modifiers.Abstract element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.modifiers.ModifiersPackage.ABSTRACT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("abstract");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_modifiers_Protected(org.emftext.language.java.modifiers.Protected element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.modifiers.ModifiersPackage.PROTECTED__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("protected");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_modifiers_Private(org.emftext.language.java.modifiers.Private element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.modifiers.ModifiersPackage.PRIVATE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("private");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_modifiers_Final(org.emftext.language.java.modifiers.Final element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.modifiers.ModifiersPackage.FINAL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("final");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_modifiers_Static(org.emftext.language.java.modifiers.Static element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.modifiers.ModifiersPackage.STATIC__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("static");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_modifiers_Native(org.emftext.language.java.modifiers.Native element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.modifiers.ModifiersPackage.NATIVE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("native");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_modifiers_Synchronized(org.emftext.language.java.modifiers.Synchronized element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.modifiers.ModifiersPackage.SYNCHRONIZED__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("synchronized");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_modifiers_Transient(org.emftext.language.java.modifiers.Transient element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.modifiers.ModifiersPackage.TRANSIENT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("transient");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_modifiers_Volatile(org.emftext.language.java.modifiers.Volatile element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.modifiers.ModifiersPackage.VOLATILE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("volatile");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_modifiers_Strictfp(org.emftext.language.java.modifiers.Strictfp element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.modifiers.ModifiersPackage.STRICTFP__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("strictfp");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_types_Void(org.emftext.language.java.types.Void element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.VOID__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("void");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_types_Boolean(org.emftext.language.java.types.Boolean element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.BOOLEAN__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("boolean");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_types_Char(org.emftext.language.java.types.Char element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.CHAR__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("char");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_types_Byte(org.emftext.language.java.types.Byte element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.BYTE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("byte");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_types_Short(org.emftext.language.java.types.Short element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.SHORT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("short");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_types_Int(org.emftext.language.java.types.Int element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.INT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("int");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_types_Long(org.emftext.language.java.types.Long element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.LONG__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("long");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_types_Float(org.emftext.language.java.types.Float element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.FLOAT__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("float");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_types_Double(org.emftext.language.java.types.Double element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.DOUBLE__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("double");
		out.print(" ");
	}
	
	
	public void print_org_emftext_language_java_literals_DecimalLongLiteral(org.emftext.language.java.literals.DecimalLongLiteral element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_LONG_LITERAL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_LONG_LITERAL__DECIMAL_VALUE));
		printCountingMap.put("decimalValue", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("decimalValue");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_LONG_LITERAL__DECIMAL_VALUE));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("DECIMAL_LONG_LITERAL");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_LONG_LITERAL__DECIMAL_VALUE), element));
				out.print(" ");
			}
			printCountingMap.put("decimalValue", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_literals_DecimalFloatLiteral(org.emftext.language.java.literals.DecimalFloatLiteral element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_FLOAT_LITERAL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_FLOAT_LITERAL__DECIMAL_VALUE));
		printCountingMap.put("decimalValue", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("decimalValue");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_FLOAT_LITERAL__DECIMAL_VALUE));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("DECIMAL_FLOAT_LITERAL");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_FLOAT_LITERAL__DECIMAL_VALUE), element));
				out.print(" ");
			}
			printCountingMap.put("decimalValue", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_literals_DecimalIntegerLiteral(org.emftext.language.java.literals.DecimalIntegerLiteral element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_INTEGER_LITERAL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_INTEGER_LITERAL__DECIMAL_VALUE));
		printCountingMap.put("decimalValue", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("decimalValue");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_INTEGER_LITERAL__DECIMAL_VALUE));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("DECIMAL_INTEGER_LITERAL");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_INTEGER_LITERAL__DECIMAL_VALUE), element));
				out.print(" ");
			}
			printCountingMap.put("decimalValue", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_literals_DecimalDoubleLiteral(org.emftext.language.java.literals.DecimalDoubleLiteral element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_DOUBLE_LITERAL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_DOUBLE_LITERAL__DECIMAL_VALUE));
		printCountingMap.put("decimalValue", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("decimalValue");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_DOUBLE_LITERAL__DECIMAL_VALUE));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("DECIMAL_DOUBLE_LITERAL");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_DOUBLE_LITERAL__DECIMAL_VALUE), element));
				out.print(" ");
			}
			printCountingMap.put("decimalValue", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_literals_HexLongLiteral(org.emftext.language.java.literals.HexLongLiteral element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_LONG_LITERAL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_LONG_LITERAL__HEX_VALUE));
		printCountingMap.put("hexValue", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("hexValue");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_LONG_LITERAL__HEX_VALUE));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("HEX_LONG_LITERAL");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_LONG_LITERAL__HEX_VALUE), element));
				out.print(" ");
			}
			printCountingMap.put("hexValue", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_literals_HexFloatLiteral(org.emftext.language.java.literals.HexFloatLiteral element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_FLOAT_LITERAL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_FLOAT_LITERAL__HEX_VALUE));
		printCountingMap.put("hexValue", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("hexValue");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_FLOAT_LITERAL__HEX_VALUE));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("HEX_FLOAT_LITERAL");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_FLOAT_LITERAL__HEX_VALUE), element));
				out.print(" ");
			}
			printCountingMap.put("hexValue", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_literals_HexDoubleLiteral(org.emftext.language.java.literals.HexDoubleLiteral element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_DOUBLE_LITERAL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_DOUBLE_LITERAL__HEX_VALUE));
		printCountingMap.put("hexValue", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("hexValue");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_DOUBLE_LITERAL__HEX_VALUE));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("HEX_DOUBLE_LITERAL");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_DOUBLE_LITERAL__HEX_VALUE), element));
				out.print(" ");
			}
			printCountingMap.put("hexValue", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_literals_HexIntegerLiteral(org.emftext.language.java.literals.HexIntegerLiteral element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_INTEGER_LITERAL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_INTEGER_LITERAL__HEX_VALUE));
		printCountingMap.put("hexValue", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("hexValue");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_INTEGER_LITERAL__HEX_VALUE));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("HEX_INTEGER_LITERAL");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_INTEGER_LITERAL__HEX_VALUE), element));
				out.print(" ");
			}
			printCountingMap.put("hexValue", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_literals_OctalLongLiteral(org.emftext.language.java.literals.OctalLongLiteral element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.OCTAL_LONG_LITERAL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.OCTAL_LONG_LITERAL__OCTAL_VALUE));
		printCountingMap.put("octalValue", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("octalValue");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.OCTAL_LONG_LITERAL__OCTAL_VALUE));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("OCTAL_LONG_LITERAL");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.OCTAL_LONG_LITERAL__OCTAL_VALUE), element));
				out.print(" ");
			}
			printCountingMap.put("octalValue", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_literals_OctalIntegerLiteral(org.emftext.language.java.literals.OctalIntegerLiteral element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.OCTAL_INTEGER_LITERAL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.OCTAL_INTEGER_LITERAL__OCTAL_VALUE));
		printCountingMap.put("octalValue", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("octalValue");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.OCTAL_INTEGER_LITERAL__OCTAL_VALUE));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("OCTAL_INTEGER_LITERAL");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.OCTAL_INTEGER_LITERAL__OCTAL_VALUE), element));
				out.print(" ");
			}
			printCountingMap.put("octalValue", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_literals_CharacterLiteral(org.emftext.language.java.literals.CharacterLiteral element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.CHARACTER_LITERAL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.CHARACTER_LITERAL__VALUE));
		printCountingMap.put("value", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("value");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.CHARACTER_LITERAL__VALUE));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("CHARACTER_LITERAL");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.CHARACTER_LITERAL__VALUE), element));
				out.print(" ");
			}
			printCountingMap.put("value", count - 1);
		}
	}
	
	
	public void print_org_emftext_language_java_literals_BooleanLiteral(org.emftext.language.java.literals.BooleanLiteral element, String outertab, PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		Map<String, Integer> printCountingMap = new LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.BOOLEAN_LITERAL__LAYOUT_INFORMATIONS));
		printCountingMap.put("layoutInformations", temp == null ? 0 : ((Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.BOOLEAN_LITERAL__VALUE));
		printCountingMap.put("value", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("value");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.BOOLEAN_LITERAL__VALUE));
			if (o != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver resolver = tokenResolverFactory.createTokenResolver("BOOLEAN_LITERAL");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.BOOLEAN_LITERAL__VALUE), element));
				out.print(" ");
			}
			printCountingMap.put("value", count - 1);
		}
	}
	
	
}
