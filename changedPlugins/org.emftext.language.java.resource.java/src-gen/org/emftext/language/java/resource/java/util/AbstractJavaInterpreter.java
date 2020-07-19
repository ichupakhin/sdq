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

package org.emftext.language.java.resource.java.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.annotations.Annotable;
import org.emftext.language.java.annotations.AnnotationAttribute;
import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.annotations.AnnotationParameter;
import org.emftext.language.java.annotations.AnnotationParameterList;
import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.annotations.SingleAnnotationParameter;
import org.emftext.language.java.arrays.ArrayDimension;
import org.emftext.language.java.arrays.ArrayInitializationValue;
import org.emftext.language.java.arrays.ArrayInitializer;
import org.emftext.language.java.arrays.ArrayInstantiation;
import org.emftext.language.java.arrays.ArrayInstantiationBySize;
import org.emftext.language.java.arrays.ArrayInstantiationByValues;
import org.emftext.language.java.arrays.ArrayInstantiationByValuesTyped;
import org.emftext.language.java.arrays.ArrayInstantiationByValuesUntyped;
import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.arrays.ArrayTypeable;
import org.emftext.language.java.classifiers.Annotation;
import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.classifiers.Implementor;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.commons.NamespaceAwareElement;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.EmptyModel;
import org.emftext.language.java.containers.JavaRoot;
import org.emftext.language.java.expressions.AdditiveExpression;
import org.emftext.language.java.expressions.AdditiveExpressionChild;
import org.emftext.language.java.expressions.AndExpression;
import org.emftext.language.java.expressions.AndExpressionChild;
import org.emftext.language.java.expressions.AssignmentExpression;
import org.emftext.language.java.expressions.AssignmentExpressionChild;
import org.emftext.language.java.expressions.CastExpression;
import org.emftext.language.java.expressions.ConditionalAndExpression;
import org.emftext.language.java.expressions.ConditionalAndExpressionChild;
import org.emftext.language.java.expressions.ConditionalExpression;
import org.emftext.language.java.expressions.ConditionalExpressionChild;
import org.emftext.language.java.expressions.ConditionalOrExpression;
import org.emftext.language.java.expressions.ConditionalOrExpressionChild;
import org.emftext.language.java.expressions.EqualityExpression;
import org.emftext.language.java.expressions.EqualityExpressionChild;
import org.emftext.language.java.expressions.ExclusiveOrExpression;
import org.emftext.language.java.expressions.ExclusiveOrExpressionChild;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionList;
import org.emftext.language.java.expressions.InclusiveOrExpression;
import org.emftext.language.java.expressions.InclusiveOrExpressionChild;
import org.emftext.language.java.expressions.InstanceOfExpression;
import org.emftext.language.java.expressions.InstanceOfExpressionChild;
import org.emftext.language.java.expressions.MultiplicativeExpression;
import org.emftext.language.java.expressions.MultiplicativeExpressionChild;
import org.emftext.language.java.expressions.NestedExpression;
import org.emftext.language.java.expressions.PrefixUnaryModificationExpression;
import org.emftext.language.java.expressions.PrimaryExpression;
import org.emftext.language.java.expressions.RelationExpression;
import org.emftext.language.java.expressions.RelationExpressionChild;
import org.emftext.language.java.expressions.ShiftExpression;
import org.emftext.language.java.expressions.ShiftExpressionChild;
import org.emftext.language.java.expressions.SuffixUnaryModificationExpression;
import org.emftext.language.java.expressions.UnaryExpression;
import org.emftext.language.java.expressions.UnaryExpressionChild;
import org.emftext.language.java.expressions.UnaryModificationExpression;
import org.emftext.language.java.expressions.UnaryModificationExpressionChild;
import org.emftext.language.java.generics.CallTypeArgumentable;
import org.emftext.language.java.generics.ExtendsTypeArgument;
import org.emftext.language.java.generics.QualifiedTypeArgument;
import org.emftext.language.java.generics.SuperTypeArgument;
import org.emftext.language.java.generics.TypeArgument;
import org.emftext.language.java.generics.TypeArgumentable;
import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.generics.TypeParametrizable;
import org.emftext.language.java.generics.UnknownTypeArgument;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportingElement;
import org.emftext.language.java.imports.PackageImport;
import org.emftext.language.java.imports.StaticClassifierImport;
import org.emftext.language.java.imports.StaticImport;
import org.emftext.language.java.imports.StaticMemberImport;
import org.emftext.language.java.instantiations.ExplicitConstructorCall;
import org.emftext.language.java.instantiations.Initializable;
import org.emftext.language.java.instantiations.Instantiation;
import org.emftext.language.java.instantiations.NewConstructorCall;
import org.emftext.language.java.literals.BooleanLiteral;
import org.emftext.language.java.literals.CharacterLiteral;
import org.emftext.language.java.literals.DecimalDoubleLiteral;
import org.emftext.language.java.literals.DecimalFloatLiteral;
import org.emftext.language.java.literals.DecimalIntegerLiteral;
import org.emftext.language.java.literals.DecimalLongLiteral;
import org.emftext.language.java.literals.DoubleLiteral;
import org.emftext.language.java.literals.FloatLiteral;
import org.emftext.language.java.literals.HexDoubleLiteral;
import org.emftext.language.java.literals.HexFloatLiteral;
import org.emftext.language.java.literals.HexIntegerLiteral;
import org.emftext.language.java.literals.HexLongLiteral;
import org.emftext.language.java.literals.IntegerLiteral;
import org.emftext.language.java.literals.Literal;
import org.emftext.language.java.literals.LongLiteral;
import org.emftext.language.java.literals.NullLiteral;
import org.emftext.language.java.literals.OctalIntegerLiteral;
import org.emftext.language.java.literals.OctalLongLiteral;
import org.emftext.language.java.literals.Self;
import org.emftext.language.java.literals.Super;
import org.emftext.language.java.literals.This;
import org.emftext.language.java.members.AdditionalField;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.EmptyMember;
import org.emftext.language.java.members.EnumConstant;
import org.emftext.language.java.members.ExceptionThrower;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.InterfaceMethod;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.modifiers.Abstract;
import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.modifiers.AnnotationInstanceOrModifier;
import org.emftext.language.java.modifiers.Final;
import org.emftext.language.java.modifiers.Modifiable;
import org.emftext.language.java.modifiers.Modifier;
import org.emftext.language.java.modifiers.Native;
import org.emftext.language.java.modifiers.Private;
import org.emftext.language.java.modifiers.Protected;
import org.emftext.language.java.modifiers.Public;
import org.emftext.language.java.modifiers.Static;
import org.emftext.language.java.modifiers.Strictfp;
import org.emftext.language.java.modifiers.Synchronized;
import org.emftext.language.java.modifiers.Transient;
import org.emftext.language.java.modifiers.Volatile;
import org.emftext.language.java.operators.Addition;
import org.emftext.language.java.operators.AdditiveOperator;
import org.emftext.language.java.operators.Assignment;
import org.emftext.language.java.operators.AssignmentAnd;
import org.emftext.language.java.operators.AssignmentDivision;
import org.emftext.language.java.operators.AssignmentExclusiveOr;
import org.emftext.language.java.operators.AssignmentLeftShift;
import org.emftext.language.java.operators.AssignmentMinus;
import org.emftext.language.java.operators.AssignmentModulo;
import org.emftext.language.java.operators.AssignmentMultiplication;
import org.emftext.language.java.operators.AssignmentOperator;
import org.emftext.language.java.operators.AssignmentOr;
import org.emftext.language.java.operators.AssignmentPlus;
import org.emftext.language.java.operators.AssignmentRightShift;
import org.emftext.language.java.operators.AssignmentUnsignedRightShift;
import org.emftext.language.java.operators.Complement;
import org.emftext.language.java.operators.Division;
import org.emftext.language.java.operators.Equal;
import org.emftext.language.java.operators.EqualityOperator;
import org.emftext.language.java.operators.GreaterThan;
import org.emftext.language.java.operators.GreaterThanOrEqual;
import org.emftext.language.java.operators.LeftShift;
import org.emftext.language.java.operators.LessThan;
import org.emftext.language.java.operators.LessThanOrEqual;
import org.emftext.language.java.operators.MinusMinus;
import org.emftext.language.java.operators.Multiplication;
import org.emftext.language.java.operators.MultiplicativeOperator;
import org.emftext.language.java.operators.Negate;
import org.emftext.language.java.operators.NotEqual;
import org.emftext.language.java.operators.Operator;
import org.emftext.language.java.operators.PlusPlus;
import org.emftext.language.java.operators.RelationOperator;
import org.emftext.language.java.operators.Remainder;
import org.emftext.language.java.operators.RightShift;
import org.emftext.language.java.operators.ShiftOperator;
import org.emftext.language.java.operators.Subtraction;
import org.emftext.language.java.operators.UnaryModificationOperator;
import org.emftext.language.java.operators.UnaryOperator;
import org.emftext.language.java.operators.UnsignedRightShift;
import org.emftext.language.java.parameters.OrdinaryParameter;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.parameters.Parametrizable;
import org.emftext.language.java.parameters.VariableLengthParameter;
import org.emftext.language.java.references.Argumentable;
import org.emftext.language.java.references.ElementReference;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.PackageReference;
import org.emftext.language.java.references.PrimitiveTypeReference;
import org.emftext.language.java.references.Reference;
import org.emftext.language.java.references.ReferenceableElement;
import org.emftext.language.java.references.ReflectiveClassReference;
import org.emftext.language.java.references.SelfReference;
import org.emftext.language.java.references.StringReference;
import org.emftext.language.java.statements.Assert;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Break;
import org.emftext.language.java.statements.CatchBlock;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.Conditional;
import org.emftext.language.java.statements.Continue;
import org.emftext.language.java.statements.DefaultSwitchCase;
import org.emftext.language.java.statements.DoWhileLoop;
import org.emftext.language.java.statements.EmptyStatement;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.ForEachLoop;
import org.emftext.language.java.statements.ForLoop;
import org.emftext.language.java.statements.ForLoopInitializer;
import org.emftext.language.java.statements.Jump;
import org.emftext.language.java.statements.JumpLabel;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.NormalSwitchCase;
import org.emftext.language.java.statements.Return;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementContainer;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.statements.Switch;
import org.emftext.language.java.statements.SwitchCase;
import org.emftext.language.java.statements.SynchronizedBlock;
import org.emftext.language.java.statements.Throw;
import org.emftext.language.java.statements.TryBlock;
import org.emftext.language.java.statements.WhileLoop;
import org.emftext.language.java.types.Char;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.Int;
import org.emftext.language.java.types.NamespaceClassifierReference;
import org.emftext.language.java.types.PrimitiveType;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElement;
import org.emftext.language.java.variables.AdditionalLocalVariable;
import org.emftext.language.java.variables.LocalVariable;
import org.emftext.language.java.variables.Variable;

/**
 * This class provides basic infrastructure to interpret models. To implement
 * concrete interpreters, subclass this abstract interpreter and override the
 * interprete_* methods. The interpretation can be customized by binding the two
 * type parameters (ResultType, ContextType). The former is returned by all
 * interprete_* methods, while the latter is passed from method to method while
 * traversing the model. The concrete traversal strategy can also be exchanged.
 * One can use a static traversal strategy by pushing all objects to interpret on
 * the interpretation stack (using addObjectToInterprete()) before calling
 * interprete(). Alternatively, the traversal strategy can be dynamic by pushing
 * objects on the interpretation stack during interpretation.
 */
public class AbstractJavaInterpreter<ResultType, ContextType> {
	
	private Stack<EObject> interpretationStack = new Stack<EObject>();
	private List<org.emftext.language.java.resource.java.IJavaInterpreterListener> listeners = new ArrayList<org.emftext.language.java.resource.java.IJavaInterpreterListener>();
	private EObject nextObjectToInterprete;
	private ContextType currentContext;
	
	public ResultType interprete(ContextType context) {
		ResultType result = null;
		EObject next = null;
		currentContext = context;
		while (!interpretationStack.empty()) {
			try {
				next = interpretationStack.pop();
			} catch (EmptyStackException ese) {
				// this can happen when the interpreter was terminated between the call to empty()
				// and pop()
				break;
			}
			nextObjectToInterprete = next;
			notifyListeners(next);
			result = interprete(next, context);
			if (!continueInterpretation(context, result)) {
				break;
			}
		}
		currentContext = null;
		return result;
	}
	
	/**
	 * Override this method to stop the overall interpretation depending on the result
	 * of the interpretation of a single model elements.
	 */
	public boolean continueInterpretation(ContextType context, ResultType result) {
		return true;
	}
	
	public ResultType interprete(EObject object, ContextType context) {
		ResultType result = null;
		if (object instanceof org.emftext.language.java.annotations.Annotable) {
			result = interprete_org_emftext_language_java_annotations_Annotable((org.emftext.language.java.annotations.Annotable) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.annotations.AnnotationInstance) {
			result = interprete_org_emftext_language_java_annotations_AnnotationInstance((org.emftext.language.java.annotations.AnnotationInstance) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.annotations.SingleAnnotationParameter) {
			result = interprete_org_emftext_language_java_annotations_SingleAnnotationParameter((org.emftext.language.java.annotations.SingleAnnotationParameter) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.annotations.AnnotationParameterList) {
			result = interprete_org_emftext_language_java_annotations_AnnotationParameterList((org.emftext.language.java.annotations.AnnotationParameterList) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.annotations.AnnotationParameter) {
			result = interprete_org_emftext_language_java_annotations_AnnotationParameter((org.emftext.language.java.annotations.AnnotationParameter) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.annotations.AnnotationAttributeSetting) {
			result = interprete_org_emftext_language_java_annotations_AnnotationAttributeSetting((org.emftext.language.java.annotations.AnnotationAttributeSetting) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.arrays.ArrayInstantiationBySize) {
			result = interprete_org_emftext_language_java_arrays_ArrayInstantiationBySize((org.emftext.language.java.arrays.ArrayInstantiationBySize) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.arrays.ArrayInstantiationByValuesUntyped) {
			result = interprete_org_emftext_language_java_arrays_ArrayInstantiationByValuesUntyped((org.emftext.language.java.arrays.ArrayInstantiationByValuesUntyped) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.arrays.ArrayInstantiationByValuesTyped) {
			result = interprete_org_emftext_language_java_arrays_ArrayInstantiationByValuesTyped((org.emftext.language.java.arrays.ArrayInstantiationByValuesTyped) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.arrays.ArrayInstantiationByValues) {
			result = interprete_org_emftext_language_java_arrays_ArrayInstantiationByValues((org.emftext.language.java.arrays.ArrayInstantiationByValues) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.arrays.ArrayInstantiation) {
			result = interprete_org_emftext_language_java_arrays_ArrayInstantiation((org.emftext.language.java.arrays.ArrayInstantiation) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.annotations.AnnotationValue) {
			result = interprete_org_emftext_language_java_annotations_AnnotationValue((org.emftext.language.java.annotations.AnnotationValue) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.annotations.AnnotationAttribute) {
			result = interprete_org_emftext_language_java_annotations_AnnotationAttribute((org.emftext.language.java.annotations.AnnotationAttribute) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.arrays.ArrayTypeable) {
			result = interprete_org_emftext_language_java_arrays_ArrayTypeable((org.emftext.language.java.arrays.ArrayTypeable) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.arrays.ArrayDimension) {
			result = interprete_org_emftext_language_java_arrays_ArrayDimension((org.emftext.language.java.arrays.ArrayDimension) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.arrays.ArrayInitializer) {
			result = interprete_org_emftext_language_java_arrays_ArrayInitializer((org.emftext.language.java.arrays.ArrayInitializer) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.arrays.ArrayInitializationValue) {
			result = interprete_org_emftext_language_java_arrays_ArrayInitializationValue((org.emftext.language.java.arrays.ArrayInitializationValue) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.arrays.ArraySelector) {
			result = interprete_org_emftext_language_java_arrays_ArraySelector((org.emftext.language.java.arrays.ArraySelector) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.classifiers.Class) {
			result = interprete_org_emftext_language_java_classifiers_Class((org.emftext.language.java.classifiers.Class) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.classifiers.Interface) {
			result = interprete_org_emftext_language_java_classifiers_Interface((org.emftext.language.java.classifiers.Interface) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.classifiers.Enumeration) {
			result = interprete_org_emftext_language_java_classifiers_Enumeration((org.emftext.language.java.classifiers.Enumeration) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.classifiers.Annotation) {
			result = interprete_org_emftext_language_java_classifiers_Annotation((org.emftext.language.java.classifiers.Annotation) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.classifiers.ConcreteClassifier) {
			result = interprete_org_emftext_language_java_classifiers_ConcreteClassifier((org.emftext.language.java.classifiers.ConcreteClassifier) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.classifiers.Classifier) {
			result = interprete_org_emftext_language_java_classifiers_Classifier((org.emftext.language.java.classifiers.Classifier) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.classifiers.Implementor) {
			result = interprete_org_emftext_language_java_classifiers_Implementor((org.emftext.language.java.classifiers.Implementor) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.classifiers.AnonymousClass) {
			result = interprete_org_emftext_language_java_classifiers_AnonymousClass((org.emftext.language.java.classifiers.AnonymousClass) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.containers.CompilationUnit) {
			result = interprete_org_emftext_language_java_containers_CompilationUnit((org.emftext.language.java.containers.CompilationUnit) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.containers.Package) {
			result = interprete_org_emftext_language_java_containers_Package((org.emftext.language.java.containers.Package) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.containers.EmptyModel) {
			result = interprete_org_emftext_language_java_containers_EmptyModel((org.emftext.language.java.containers.EmptyModel) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.containers.JavaRoot) {
			result = interprete_org_emftext_language_java_containers_JavaRoot((org.emftext.language.java.containers.JavaRoot) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.commons.NamedElement) {
			result = interprete_org_emftext_language_java_commons_NamedElement((org.emftext.language.java.commons.NamedElement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.commons.NamespaceAwareElement) {
			result = interprete_org_emftext_language_java_commons_NamespaceAwareElement((org.emftext.language.java.commons.NamespaceAwareElement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.ExpressionList) {
			result = interprete_org_emftext_language_java_expressions_ExpressionList((org.emftext.language.java.expressions.ExpressionList) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.AssignmentExpression) {
			result = interprete_org_emftext_language_java_expressions_AssignmentExpression((org.emftext.language.java.expressions.AssignmentExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.ConditionalExpression) {
			result = interprete_org_emftext_language_java_expressions_ConditionalExpression((org.emftext.language.java.expressions.ConditionalExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.ConditionalOrExpression) {
			result = interprete_org_emftext_language_java_expressions_ConditionalOrExpression((org.emftext.language.java.expressions.ConditionalOrExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.ConditionalAndExpression) {
			result = interprete_org_emftext_language_java_expressions_ConditionalAndExpression((org.emftext.language.java.expressions.ConditionalAndExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.InclusiveOrExpression) {
			result = interprete_org_emftext_language_java_expressions_InclusiveOrExpression((org.emftext.language.java.expressions.InclusiveOrExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.ExclusiveOrExpression) {
			result = interprete_org_emftext_language_java_expressions_ExclusiveOrExpression((org.emftext.language.java.expressions.ExclusiveOrExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.AndExpression) {
			result = interprete_org_emftext_language_java_expressions_AndExpression((org.emftext.language.java.expressions.AndExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.EqualityExpression) {
			result = interprete_org_emftext_language_java_expressions_EqualityExpression((org.emftext.language.java.expressions.EqualityExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.InstanceOfExpression) {
			result = interprete_org_emftext_language_java_expressions_InstanceOfExpression((org.emftext.language.java.expressions.InstanceOfExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.RelationExpression) {
			result = interprete_org_emftext_language_java_expressions_RelationExpression((org.emftext.language.java.expressions.RelationExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.ShiftExpression) {
			result = interprete_org_emftext_language_java_expressions_ShiftExpression((org.emftext.language.java.expressions.ShiftExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.AdditiveExpression) {
			result = interprete_org_emftext_language_java_expressions_AdditiveExpression((org.emftext.language.java.expressions.AdditiveExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.MultiplicativeExpression) {
			result = interprete_org_emftext_language_java_expressions_MultiplicativeExpression((org.emftext.language.java.expressions.MultiplicativeExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.UnaryExpression) {
			result = interprete_org_emftext_language_java_expressions_UnaryExpression((org.emftext.language.java.expressions.UnaryExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.PrefixUnaryModificationExpression) {
			result = interprete_org_emftext_language_java_expressions_PrefixUnaryModificationExpression((org.emftext.language.java.expressions.PrefixUnaryModificationExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.SuffixUnaryModificationExpression) {
			result = interprete_org_emftext_language_java_expressions_SuffixUnaryModificationExpression((org.emftext.language.java.expressions.SuffixUnaryModificationExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.UnaryModificationExpression) {
			result = interprete_org_emftext_language_java_expressions_UnaryModificationExpression((org.emftext.language.java.expressions.UnaryModificationExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.CastExpression) {
			result = interprete_org_emftext_language_java_expressions_CastExpression((org.emftext.language.java.expressions.CastExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.NestedExpression) {
			result = interprete_org_emftext_language_java_expressions_NestedExpression((org.emftext.language.java.expressions.NestedExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.PrimaryExpression) {
			result = interprete_org_emftext_language_java_expressions_PrimaryExpression((org.emftext.language.java.expressions.PrimaryExpression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.UnaryModificationExpressionChild) {
			result = interprete_org_emftext_language_java_expressions_UnaryModificationExpressionChild((org.emftext.language.java.expressions.UnaryModificationExpressionChild) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.UnaryExpressionChild) {
			result = interprete_org_emftext_language_java_expressions_UnaryExpressionChild((org.emftext.language.java.expressions.UnaryExpressionChild) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.MultiplicativeExpressionChild) {
			result = interprete_org_emftext_language_java_expressions_MultiplicativeExpressionChild((org.emftext.language.java.expressions.MultiplicativeExpressionChild) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.AdditiveExpressionChild) {
			result = interprete_org_emftext_language_java_expressions_AdditiveExpressionChild((org.emftext.language.java.expressions.AdditiveExpressionChild) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.ShiftExpressionChild) {
			result = interprete_org_emftext_language_java_expressions_ShiftExpressionChild((org.emftext.language.java.expressions.ShiftExpressionChild) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.RelationExpressionChild) {
			result = interprete_org_emftext_language_java_expressions_RelationExpressionChild((org.emftext.language.java.expressions.RelationExpressionChild) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.InstanceOfExpressionChild) {
			result = interprete_org_emftext_language_java_expressions_InstanceOfExpressionChild((org.emftext.language.java.expressions.InstanceOfExpressionChild) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.EqualityExpressionChild) {
			result = interprete_org_emftext_language_java_expressions_EqualityExpressionChild((org.emftext.language.java.expressions.EqualityExpressionChild) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.AndExpressionChild) {
			result = interprete_org_emftext_language_java_expressions_AndExpressionChild((org.emftext.language.java.expressions.AndExpressionChild) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.ExclusiveOrExpressionChild) {
			result = interprete_org_emftext_language_java_expressions_ExclusiveOrExpressionChild((org.emftext.language.java.expressions.ExclusiveOrExpressionChild) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.InclusiveOrExpressionChild) {
			result = interprete_org_emftext_language_java_expressions_InclusiveOrExpressionChild((org.emftext.language.java.expressions.InclusiveOrExpressionChild) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.ConditionalAndExpressionChild) {
			result = interprete_org_emftext_language_java_expressions_ConditionalAndExpressionChild((org.emftext.language.java.expressions.ConditionalAndExpressionChild) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.ConditionalOrExpressionChild) {
			result = interprete_org_emftext_language_java_expressions_ConditionalOrExpressionChild((org.emftext.language.java.expressions.ConditionalOrExpressionChild) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.ConditionalExpressionChild) {
			result = interprete_org_emftext_language_java_expressions_ConditionalExpressionChild((org.emftext.language.java.expressions.ConditionalExpressionChild) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.AssignmentExpressionChild) {
			result = interprete_org_emftext_language_java_expressions_AssignmentExpressionChild((org.emftext.language.java.expressions.AssignmentExpressionChild) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.expressions.Expression) {
			result = interprete_org_emftext_language_java_expressions_Expression((org.emftext.language.java.expressions.Expression) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.generics.TypeArgument) {
			result = interprete_org_emftext_language_java_generics_TypeArgument((org.emftext.language.java.generics.TypeArgument) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.generics.TypeArgumentable) {
			result = interprete_org_emftext_language_java_generics_TypeArgumentable((org.emftext.language.java.generics.TypeArgumentable) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.generics.CallTypeArgumentable) {
			result = interprete_org_emftext_language_java_generics_CallTypeArgumentable((org.emftext.language.java.generics.CallTypeArgumentable) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.generics.TypeParametrizable) {
			result = interprete_org_emftext_language_java_generics_TypeParametrizable((org.emftext.language.java.generics.TypeParametrizable) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.generics.ExtendsTypeArgument) {
			result = interprete_org_emftext_language_java_generics_ExtendsTypeArgument((org.emftext.language.java.generics.ExtendsTypeArgument) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.generics.QualifiedTypeArgument) {
			result = interprete_org_emftext_language_java_generics_QualifiedTypeArgument((org.emftext.language.java.generics.QualifiedTypeArgument) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.generics.SuperTypeArgument) {
			result = interprete_org_emftext_language_java_generics_SuperTypeArgument((org.emftext.language.java.generics.SuperTypeArgument) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.generics.TypeParameter) {
			result = interprete_org_emftext_language_java_generics_TypeParameter((org.emftext.language.java.generics.TypeParameter) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.generics.UnknownTypeArgument) {
			result = interprete_org_emftext_language_java_generics_UnknownTypeArgument((org.emftext.language.java.generics.UnknownTypeArgument) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.imports.StaticMemberImport) {
			result = interprete_org_emftext_language_java_imports_StaticMemberImport((org.emftext.language.java.imports.StaticMemberImport) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.imports.StaticImport) {
			result = interprete_org_emftext_language_java_imports_StaticImport((org.emftext.language.java.imports.StaticImport) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.imports.ClassifierImport) {
			result = interprete_org_emftext_language_java_imports_ClassifierImport((org.emftext.language.java.imports.ClassifierImport) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.imports.StaticClassifierImport) {
			result = interprete_org_emftext_language_java_imports_StaticClassifierImport((org.emftext.language.java.imports.StaticClassifierImport) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.imports.Import) {
			result = interprete_org_emftext_language_java_imports_Import((org.emftext.language.java.imports.Import) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.imports.ImportingElement) {
			result = interprete_org_emftext_language_java_imports_ImportingElement((org.emftext.language.java.imports.ImportingElement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.imports.PackageImport) {
			result = interprete_org_emftext_language_java_imports_PackageImport((org.emftext.language.java.imports.PackageImport) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.instantiations.Initializable) {
			result = interprete_org_emftext_language_java_instantiations_Initializable((org.emftext.language.java.instantiations.Initializable) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.instantiations.NewConstructorCall) {
			result = interprete_org_emftext_language_java_instantiations_NewConstructorCall((org.emftext.language.java.instantiations.NewConstructorCall) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.instantiations.ExplicitConstructorCall) {
			result = interprete_org_emftext_language_java_instantiations_ExplicitConstructorCall((org.emftext.language.java.instantiations.ExplicitConstructorCall) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.instantiations.Instantiation) {
			result = interprete_org_emftext_language_java_instantiations_Instantiation((org.emftext.language.java.instantiations.Instantiation) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.literals.Literal) {
			result = interprete_org_emftext_language_java_literals_Literal((org.emftext.language.java.literals.Literal) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.literals.Self) {
			result = interprete_org_emftext_language_java_literals_Self((org.emftext.language.java.literals.Self) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.literals.BooleanLiteral) {
			result = interprete_org_emftext_language_java_literals_BooleanLiteral((org.emftext.language.java.literals.BooleanLiteral) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.literals.CharacterLiteral) {
			result = interprete_org_emftext_language_java_literals_CharacterLiteral((org.emftext.language.java.literals.CharacterLiteral) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.literals.DecimalFloatLiteral) {
			result = interprete_org_emftext_language_java_literals_DecimalFloatLiteral((org.emftext.language.java.literals.DecimalFloatLiteral) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.literals.HexFloatLiteral) {
			result = interprete_org_emftext_language_java_literals_HexFloatLiteral((org.emftext.language.java.literals.HexFloatLiteral) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.literals.FloatLiteral) {
			result = interprete_org_emftext_language_java_literals_FloatLiteral((org.emftext.language.java.literals.FloatLiteral) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.literals.DecimalDoubleLiteral) {
			result = interprete_org_emftext_language_java_literals_DecimalDoubleLiteral((org.emftext.language.java.literals.DecimalDoubleLiteral) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.literals.HexDoubleLiteral) {
			result = interprete_org_emftext_language_java_literals_HexDoubleLiteral((org.emftext.language.java.literals.HexDoubleLiteral) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.literals.DoubleLiteral) {
			result = interprete_org_emftext_language_java_literals_DoubleLiteral((org.emftext.language.java.literals.DoubleLiteral) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.literals.DecimalIntegerLiteral) {
			result = interprete_org_emftext_language_java_literals_DecimalIntegerLiteral((org.emftext.language.java.literals.DecimalIntegerLiteral) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.literals.HexIntegerLiteral) {
			result = interprete_org_emftext_language_java_literals_HexIntegerLiteral((org.emftext.language.java.literals.HexIntegerLiteral) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.literals.OctalIntegerLiteral) {
			result = interprete_org_emftext_language_java_literals_OctalIntegerLiteral((org.emftext.language.java.literals.OctalIntegerLiteral) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.literals.IntegerLiteral) {
			result = interprete_org_emftext_language_java_literals_IntegerLiteral((org.emftext.language.java.literals.IntegerLiteral) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.literals.DecimalLongLiteral) {
			result = interprete_org_emftext_language_java_literals_DecimalLongLiteral((org.emftext.language.java.literals.DecimalLongLiteral) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.literals.HexLongLiteral) {
			result = interprete_org_emftext_language_java_literals_HexLongLiteral((org.emftext.language.java.literals.HexLongLiteral) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.literals.OctalLongLiteral) {
			result = interprete_org_emftext_language_java_literals_OctalLongLiteral((org.emftext.language.java.literals.OctalLongLiteral) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.literals.LongLiteral) {
			result = interprete_org_emftext_language_java_literals_LongLiteral((org.emftext.language.java.literals.LongLiteral) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.literals.NullLiteral) {
			result = interprete_org_emftext_language_java_literals_NullLiteral((org.emftext.language.java.literals.NullLiteral) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.literals.Super) {
			result = interprete_org_emftext_language_java_literals_Super((org.emftext.language.java.literals.Super) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.literals.This) {
			result = interprete_org_emftext_language_java_literals_This((org.emftext.language.java.literals.This) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.members.Constructor) {
			result = interprete_org_emftext_language_java_members_Constructor((org.emftext.language.java.members.Constructor) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.members.InterfaceMethod) {
			result = interprete_org_emftext_language_java_members_InterfaceMethod((org.emftext.language.java.members.InterfaceMethod) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.members.ExceptionThrower) {
			result = interprete_org_emftext_language_java_members_ExceptionThrower((org.emftext.language.java.members.ExceptionThrower) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.members.EmptyMember) {
			result = interprete_org_emftext_language_java_members_EmptyMember((org.emftext.language.java.members.EmptyMember) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.members.Field) {
			result = interprete_org_emftext_language_java_members_Field((org.emftext.language.java.members.Field) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.members.Method) {
			result = interprete_org_emftext_language_java_members_Method((org.emftext.language.java.members.Method) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.members.Member) {
			result = interprete_org_emftext_language_java_members_Member((org.emftext.language.java.members.Member) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.members.MemberContainer) {
			result = interprete_org_emftext_language_java_members_MemberContainer((org.emftext.language.java.members.MemberContainer) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.members.AdditionalField) {
			result = interprete_org_emftext_language_java_members_AdditionalField((org.emftext.language.java.members.AdditionalField) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.members.ClassMethod) {
			result = interprete_org_emftext_language_java_members_ClassMethod((org.emftext.language.java.members.ClassMethod) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.members.EnumConstant) {
			result = interprete_org_emftext_language_java_members_EnumConstant((org.emftext.language.java.members.EnumConstant) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.modifiers.Modifier) {
			result = interprete_org_emftext_language_java_modifiers_Modifier((org.emftext.language.java.modifiers.Modifier) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.modifiers.AnnotationInstanceOrModifier) {
			result = interprete_org_emftext_language_java_modifiers_AnnotationInstanceOrModifier((org.emftext.language.java.modifiers.AnnotationInstanceOrModifier) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.modifiers.AnnotableAndModifiable) {
			result = interprete_org_emftext_language_java_modifiers_AnnotableAndModifiable((org.emftext.language.java.modifiers.AnnotableAndModifiable) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.modifiers.Modifiable) {
			result = interprete_org_emftext_language_java_modifiers_Modifiable((org.emftext.language.java.modifiers.Modifiable) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.modifiers.Abstract) {
			result = interprete_org_emftext_language_java_modifiers_Abstract((org.emftext.language.java.modifiers.Abstract) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.modifiers.Final) {
			result = interprete_org_emftext_language_java_modifiers_Final((org.emftext.language.java.modifiers.Final) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.modifiers.Native) {
			result = interprete_org_emftext_language_java_modifiers_Native((org.emftext.language.java.modifiers.Native) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.modifiers.Protected) {
			result = interprete_org_emftext_language_java_modifiers_Protected((org.emftext.language.java.modifiers.Protected) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.modifiers.Public) {
			result = interprete_org_emftext_language_java_modifiers_Public((org.emftext.language.java.modifiers.Public) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.modifiers.Private) {
			result = interprete_org_emftext_language_java_modifiers_Private((org.emftext.language.java.modifiers.Private) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.modifiers.Static) {
			result = interprete_org_emftext_language_java_modifiers_Static((org.emftext.language.java.modifiers.Static) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.modifiers.Strictfp) {
			result = interprete_org_emftext_language_java_modifiers_Strictfp((org.emftext.language.java.modifiers.Strictfp) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.modifiers.Synchronized) {
			result = interprete_org_emftext_language_java_modifiers_Synchronized((org.emftext.language.java.modifiers.Synchronized) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.modifiers.Transient) {
			result = interprete_org_emftext_language_java_modifiers_Transient((org.emftext.language.java.modifiers.Transient) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.modifiers.Volatile) {
			result = interprete_org_emftext_language_java_modifiers_Volatile((org.emftext.language.java.modifiers.Volatile) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.AdditiveOperator) {
			result = interprete_org_emftext_language_java_operators_AdditiveOperator((org.emftext.language.java.operators.AdditiveOperator) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.AssignmentOperator) {
			result = interprete_org_emftext_language_java_operators_AssignmentOperator((org.emftext.language.java.operators.AssignmentOperator) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.EqualityOperator) {
			result = interprete_org_emftext_language_java_operators_EqualityOperator((org.emftext.language.java.operators.EqualityOperator) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.MultiplicativeOperator) {
			result = interprete_org_emftext_language_java_operators_MultiplicativeOperator((org.emftext.language.java.operators.MultiplicativeOperator) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.RelationOperator) {
			result = interprete_org_emftext_language_java_operators_RelationOperator((org.emftext.language.java.operators.RelationOperator) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.ShiftOperator) {
			result = interprete_org_emftext_language_java_operators_ShiftOperator((org.emftext.language.java.operators.ShiftOperator) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.UnaryOperator) {
			result = interprete_org_emftext_language_java_operators_UnaryOperator((org.emftext.language.java.operators.UnaryOperator) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.UnaryModificationOperator) {
			result = interprete_org_emftext_language_java_operators_UnaryModificationOperator((org.emftext.language.java.operators.UnaryModificationOperator) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.Assignment) {
			result = interprete_org_emftext_language_java_operators_Assignment((org.emftext.language.java.operators.Assignment) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.AssignmentAnd) {
			result = interprete_org_emftext_language_java_operators_AssignmentAnd((org.emftext.language.java.operators.AssignmentAnd) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.AssignmentDivision) {
			result = interprete_org_emftext_language_java_operators_AssignmentDivision((org.emftext.language.java.operators.AssignmentDivision) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.AssignmentExclusiveOr) {
			result = interprete_org_emftext_language_java_operators_AssignmentExclusiveOr((org.emftext.language.java.operators.AssignmentExclusiveOr) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.AssignmentMinus) {
			result = interprete_org_emftext_language_java_operators_AssignmentMinus((org.emftext.language.java.operators.AssignmentMinus) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.AssignmentModulo) {
			result = interprete_org_emftext_language_java_operators_AssignmentModulo((org.emftext.language.java.operators.AssignmentModulo) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.AssignmentMultiplication) {
			result = interprete_org_emftext_language_java_operators_AssignmentMultiplication((org.emftext.language.java.operators.AssignmentMultiplication) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.AssignmentLeftShift) {
			result = interprete_org_emftext_language_java_operators_AssignmentLeftShift((org.emftext.language.java.operators.AssignmentLeftShift) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.AssignmentOr) {
			result = interprete_org_emftext_language_java_operators_AssignmentOr((org.emftext.language.java.operators.AssignmentOr) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.AssignmentPlus) {
			result = interprete_org_emftext_language_java_operators_AssignmentPlus((org.emftext.language.java.operators.AssignmentPlus) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.AssignmentRightShift) {
			result = interprete_org_emftext_language_java_operators_AssignmentRightShift((org.emftext.language.java.operators.AssignmentRightShift) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.AssignmentUnsignedRightShift) {
			result = interprete_org_emftext_language_java_operators_AssignmentUnsignedRightShift((org.emftext.language.java.operators.AssignmentUnsignedRightShift) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.Equal) {
			result = interprete_org_emftext_language_java_operators_Equal((org.emftext.language.java.operators.Equal) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.NotEqual) {
			result = interprete_org_emftext_language_java_operators_NotEqual((org.emftext.language.java.operators.NotEqual) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.GreaterThan) {
			result = interprete_org_emftext_language_java_operators_GreaterThan((org.emftext.language.java.operators.GreaterThan) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.GreaterThanOrEqual) {
			result = interprete_org_emftext_language_java_operators_GreaterThanOrEqual((org.emftext.language.java.operators.GreaterThanOrEqual) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.LessThan) {
			result = interprete_org_emftext_language_java_operators_LessThan((org.emftext.language.java.operators.LessThan) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.LessThanOrEqual) {
			result = interprete_org_emftext_language_java_operators_LessThanOrEqual((org.emftext.language.java.operators.LessThanOrEqual) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.Addition) {
			result = interprete_org_emftext_language_java_operators_Addition((org.emftext.language.java.operators.Addition) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.Subtraction) {
			result = interprete_org_emftext_language_java_operators_Subtraction((org.emftext.language.java.operators.Subtraction) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.Division) {
			result = interprete_org_emftext_language_java_operators_Division((org.emftext.language.java.operators.Division) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.Multiplication) {
			result = interprete_org_emftext_language_java_operators_Multiplication((org.emftext.language.java.operators.Multiplication) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.Remainder) {
			result = interprete_org_emftext_language_java_operators_Remainder((org.emftext.language.java.operators.Remainder) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.Complement) {
			result = interprete_org_emftext_language_java_operators_Complement((org.emftext.language.java.operators.Complement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.MinusMinus) {
			result = interprete_org_emftext_language_java_operators_MinusMinus((org.emftext.language.java.operators.MinusMinus) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.Negate) {
			result = interprete_org_emftext_language_java_operators_Negate((org.emftext.language.java.operators.Negate) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.PlusPlus) {
			result = interprete_org_emftext_language_java_operators_PlusPlus((org.emftext.language.java.operators.PlusPlus) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.LeftShift) {
			result = interprete_org_emftext_language_java_operators_LeftShift((org.emftext.language.java.operators.LeftShift) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.RightShift) {
			result = interprete_org_emftext_language_java_operators_RightShift((org.emftext.language.java.operators.RightShift) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.UnsignedRightShift) {
			result = interprete_org_emftext_language_java_operators_UnsignedRightShift((org.emftext.language.java.operators.UnsignedRightShift) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.operators.Operator) {
			result = interprete_org_emftext_language_java_operators_Operator((org.emftext.language.java.operators.Operator) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.parameters.Parameter) {
			result = interprete_org_emftext_language_java_parameters_Parameter((org.emftext.language.java.parameters.Parameter) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.parameters.Parametrizable) {
			result = interprete_org_emftext_language_java_parameters_Parametrizable((org.emftext.language.java.parameters.Parametrizable) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.parameters.OrdinaryParameter) {
			result = interprete_org_emftext_language_java_parameters_OrdinaryParameter((org.emftext.language.java.parameters.OrdinaryParameter) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.parameters.VariableLengthParameter) {
			result = interprete_org_emftext_language_java_parameters_VariableLengthParameter((org.emftext.language.java.parameters.VariableLengthParameter) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.references.Reference) {
			result = interprete_org_emftext_language_java_references_Reference((org.emftext.language.java.references.Reference) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.references.Argumentable) {
			result = interprete_org_emftext_language_java_references_Argumentable((org.emftext.language.java.references.Argumentable) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.references.ReferenceableElement) {
			result = interprete_org_emftext_language_java_references_ReferenceableElement((org.emftext.language.java.references.ReferenceableElement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.references.IdentifierReference) {
			result = interprete_org_emftext_language_java_references_IdentifierReference((org.emftext.language.java.references.IdentifierReference) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.references.MethodCall) {
			result = interprete_org_emftext_language_java_references_MethodCall((org.emftext.language.java.references.MethodCall) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.references.ElementReference) {
			result = interprete_org_emftext_language_java_references_ElementReference((org.emftext.language.java.references.ElementReference) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.references.ReflectiveClassReference) {
			result = interprete_org_emftext_language_java_references_ReflectiveClassReference((org.emftext.language.java.references.ReflectiveClassReference) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.references.PrimitiveTypeReference) {
			result = interprete_org_emftext_language_java_references_PrimitiveTypeReference((org.emftext.language.java.references.PrimitiveTypeReference) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.references.StringReference) {
			result = interprete_org_emftext_language_java_references_StringReference((org.emftext.language.java.references.StringReference) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.references.SelfReference) {
			result = interprete_org_emftext_language_java_references_SelfReference((org.emftext.language.java.references.SelfReference) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.references.PackageReference) {
			result = interprete_org_emftext_language_java_references_PackageReference((org.emftext.language.java.references.PackageReference) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.StatementContainer) {
			result = interprete_org_emftext_language_java_statements_StatementContainer((org.emftext.language.java.statements.StatementContainer) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.StatementListContainer) {
			result = interprete_org_emftext_language_java_statements_StatementListContainer((org.emftext.language.java.statements.StatementListContainer) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.Conditional) {
			result = interprete_org_emftext_language_java_statements_Conditional((org.emftext.language.java.statements.Conditional) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.ForLoopInitializer) {
			result = interprete_org_emftext_language_java_statements_ForLoopInitializer((org.emftext.language.java.statements.ForLoopInitializer) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.Statement) {
			result = interprete_org_emftext_language_java_statements_Statement((org.emftext.language.java.statements.Statement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.SwitchCase) {
			result = interprete_org_emftext_language_java_statements_SwitchCase((org.emftext.language.java.statements.SwitchCase) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.Assert) {
			result = interprete_org_emftext_language_java_statements_Assert((org.emftext.language.java.statements.Assert) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.Break) {
			result = interprete_org_emftext_language_java_statements_Break((org.emftext.language.java.statements.Break) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.Block) {
			result = interprete_org_emftext_language_java_statements_Block((org.emftext.language.java.statements.Block) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.CatchBlock) {
			result = interprete_org_emftext_language_java_statements_CatchBlock((org.emftext.language.java.statements.CatchBlock) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.Condition) {
			result = interprete_org_emftext_language_java_statements_Condition((org.emftext.language.java.statements.Condition) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.Continue) {
			result = interprete_org_emftext_language_java_statements_Continue((org.emftext.language.java.statements.Continue) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.DefaultSwitchCase) {
			result = interprete_org_emftext_language_java_statements_DefaultSwitchCase((org.emftext.language.java.statements.DefaultSwitchCase) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.DoWhileLoop) {
			result = interprete_org_emftext_language_java_statements_DoWhileLoop((org.emftext.language.java.statements.DoWhileLoop) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.EmptyStatement) {
			result = interprete_org_emftext_language_java_statements_EmptyStatement((org.emftext.language.java.statements.EmptyStatement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.ExpressionStatement) {
			result = interprete_org_emftext_language_java_statements_ExpressionStatement((org.emftext.language.java.statements.ExpressionStatement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.ForLoop) {
			result = interprete_org_emftext_language_java_statements_ForLoop((org.emftext.language.java.statements.ForLoop) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.ForEachLoop) {
			result = interprete_org_emftext_language_java_statements_ForEachLoop((org.emftext.language.java.statements.ForEachLoop) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.Jump) {
			result = interprete_org_emftext_language_java_statements_Jump((org.emftext.language.java.statements.Jump) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.JumpLabel) {
			result = interprete_org_emftext_language_java_statements_JumpLabel((org.emftext.language.java.statements.JumpLabel) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.LocalVariableStatement) {
			result = interprete_org_emftext_language_java_statements_LocalVariableStatement((org.emftext.language.java.statements.LocalVariableStatement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.NormalSwitchCase) {
			result = interprete_org_emftext_language_java_statements_NormalSwitchCase((org.emftext.language.java.statements.NormalSwitchCase) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.Return) {
			result = interprete_org_emftext_language_java_statements_Return((org.emftext.language.java.statements.Return) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.Switch) {
			result = interprete_org_emftext_language_java_statements_Switch((org.emftext.language.java.statements.Switch) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.SynchronizedBlock) {
			result = interprete_org_emftext_language_java_statements_SynchronizedBlock((org.emftext.language.java.statements.SynchronizedBlock) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.Throw) {
			result = interprete_org_emftext_language_java_statements_Throw((org.emftext.language.java.statements.Throw) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.TryBlock) {
			result = interprete_org_emftext_language_java_statements_TryBlock((org.emftext.language.java.statements.TryBlock) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.statements.WhileLoop) {
			result = interprete_org_emftext_language_java_statements_WhileLoop((org.emftext.language.java.statements.WhileLoop) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.types.Type) {
			result = interprete_org_emftext_language_java_types_Type((org.emftext.language.java.types.Type) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.types.TypedElement) {
			result = interprete_org_emftext_language_java_types_TypedElement((org.emftext.language.java.types.TypedElement) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.types.ClassifierReference) {
			result = interprete_org_emftext_language_java_types_ClassifierReference((org.emftext.language.java.types.ClassifierReference) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.types.NamespaceClassifierReference) {
			result = interprete_org_emftext_language_java_types_NamespaceClassifierReference((org.emftext.language.java.types.NamespaceClassifierReference) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.types.Boolean) {
			result = interprete_org_emftext_language_java_types_Boolean((org.emftext.language.java.types.Boolean) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.types.Byte) {
			result = interprete_org_emftext_language_java_types_Byte((org.emftext.language.java.types.Byte) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.types.Char) {
			result = interprete_org_emftext_language_java_types_Char((org.emftext.language.java.types.Char) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.types.Double) {
			result = interprete_org_emftext_language_java_types_Double((org.emftext.language.java.types.Double) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.types.Float) {
			result = interprete_org_emftext_language_java_types_Float((org.emftext.language.java.types.Float) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.types.Int) {
			result = interprete_org_emftext_language_java_types_Int((org.emftext.language.java.types.Int) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.types.Long) {
			result = interprete_org_emftext_language_java_types_Long((org.emftext.language.java.types.Long) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.types.Short) {
			result = interprete_org_emftext_language_java_types_Short((org.emftext.language.java.types.Short) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.types.Void) {
			result = interprete_org_emftext_language_java_types_Void((org.emftext.language.java.types.Void) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.types.PrimitiveType) {
			result = interprete_org_emftext_language_java_types_PrimitiveType((org.emftext.language.java.types.PrimitiveType) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.types.TypeReference) {
			result = interprete_org_emftext_language_java_types_TypeReference((org.emftext.language.java.types.TypeReference) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.variables.LocalVariable) {
			result = interprete_org_emftext_language_java_variables_LocalVariable((org.emftext.language.java.variables.LocalVariable) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.variables.Variable) {
			result = interprete_org_emftext_language_java_variables_Variable((org.emftext.language.java.variables.Variable) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.variables.AdditionalLocalVariable) {
			result = interprete_org_emftext_language_java_variables_AdditionalLocalVariable((org.emftext.language.java.variables.AdditionalLocalVariable) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.emftext.language.java.commons.Commentable) {
			result = interprete_org_emftext_language_java_commons_Commentable((org.emftext.language.java.commons.Commentable) object, context);
		}
		if (result != null) {
			return result;
		}
		return result;
	}
	
	public ResultType interprete_org_emftext_language_java_annotations_Annotable(Annotable annotable, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_annotations_AnnotationInstance(AnnotationInstance annotationInstance, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_annotations_AnnotationParameter(AnnotationParameter annotationParameter, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_annotations_SingleAnnotationParameter(SingleAnnotationParameter singleAnnotationParameter, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_annotations_AnnotationParameterList(AnnotationParameterList annotationParameterList, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_annotations_AnnotationAttributeSetting(AnnotationAttributeSetting annotationAttributeSetting, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_annotations_AnnotationValue(AnnotationValue annotationValue, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_annotations_AnnotationAttribute(AnnotationAttribute annotationAttribute, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_arrays_ArrayTypeable(ArrayTypeable arrayTypeable, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_arrays_ArrayDimension(ArrayDimension arrayDimension, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_arrays_ArrayInitializer(ArrayInitializer arrayInitializer, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_arrays_ArrayInitializationValue(ArrayInitializationValue arrayInitializationValue, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_arrays_ArrayInstantiation(ArrayInstantiation arrayInstantiation, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_arrays_ArrayInstantiationBySize(ArrayInstantiationBySize arrayInstantiationBySize, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_arrays_ArrayInstantiationByValues(ArrayInstantiationByValues arrayInstantiationByValues, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_arrays_ArrayInstantiationByValuesUntyped(ArrayInstantiationByValuesUntyped arrayInstantiationByValuesUntyped, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_arrays_ArrayInstantiationByValuesTyped(ArrayInstantiationByValuesTyped arrayInstantiationByValuesTyped, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_arrays_ArraySelector(ArraySelector arraySelector, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_classifiers_Classifier(Classifier classifier, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_classifiers_ConcreteClassifier(ConcreteClassifier concreteClassifier, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_classifiers_Implementor(Implementor implementor, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_classifiers_Class(org.emftext.language.java.classifiers.Class _class, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_classifiers_Interface(Interface _interface, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_classifiers_Enumeration(Enumeration enumeration, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_classifiers_Annotation(Annotation annotation, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_classifiers_AnonymousClass(AnonymousClass anonymousClass, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_commons_Commentable(Commentable commentable, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_commons_NamedElement(NamedElement namedElement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_commons_NamespaceAwareElement(NamespaceAwareElement namespaceAwareElement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_containers_JavaRoot(JavaRoot javaRoot, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_containers_CompilationUnit(CompilationUnit compilationUnit, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_containers_Package(org.emftext.language.java.containers.Package _package, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_containers_EmptyModel(EmptyModel emptyModel, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_ExpressionList(ExpressionList expressionList, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_Expression(Expression expression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_AssignmentExpression(AssignmentExpression assignmentExpression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_AssignmentExpressionChild(AssignmentExpressionChild assignmentExpressionChild, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_ConditionalExpression(ConditionalExpression conditionalExpression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_ConditionalExpressionChild(ConditionalExpressionChild conditionalExpressionChild, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_ConditionalOrExpression(ConditionalOrExpression conditionalOrExpression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_ConditionalOrExpressionChild(ConditionalOrExpressionChild conditionalOrExpressionChild, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_ConditionalAndExpression(ConditionalAndExpression conditionalAndExpression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_ConditionalAndExpressionChild(ConditionalAndExpressionChild conditionalAndExpressionChild, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_InclusiveOrExpression(InclusiveOrExpression inclusiveOrExpression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_InclusiveOrExpressionChild(InclusiveOrExpressionChild inclusiveOrExpressionChild, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_ExclusiveOrExpression(ExclusiveOrExpression exclusiveOrExpression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_ExclusiveOrExpressionChild(ExclusiveOrExpressionChild exclusiveOrExpressionChild, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_AndExpression(AndExpression andExpression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_AndExpressionChild(AndExpressionChild andExpressionChild, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_EqualityExpression(EqualityExpression equalityExpression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_EqualityExpressionChild(EqualityExpressionChild equalityExpressionChild, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_InstanceOfExpression(InstanceOfExpression instanceOfExpression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_InstanceOfExpressionChild(InstanceOfExpressionChild instanceOfExpressionChild, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_RelationExpression(RelationExpression relationExpression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_RelationExpressionChild(RelationExpressionChild relationExpressionChild, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_ShiftExpression(ShiftExpression shiftExpression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_ShiftExpressionChild(ShiftExpressionChild shiftExpressionChild, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_AdditiveExpression(AdditiveExpression additiveExpression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_AdditiveExpressionChild(AdditiveExpressionChild additiveExpressionChild, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_MultiplicativeExpression(MultiplicativeExpression multiplicativeExpression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_MultiplicativeExpressionChild(MultiplicativeExpressionChild multiplicativeExpressionChild, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_UnaryExpression(UnaryExpression unaryExpression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_UnaryExpressionChild(UnaryExpressionChild unaryExpressionChild, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_UnaryModificationExpression(UnaryModificationExpression unaryModificationExpression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_PrefixUnaryModificationExpression(PrefixUnaryModificationExpression prefixUnaryModificationExpression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_SuffixUnaryModificationExpression(SuffixUnaryModificationExpression suffixUnaryModificationExpression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_UnaryModificationExpressionChild(UnaryModificationExpressionChild unaryModificationExpressionChild, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_CastExpression(CastExpression castExpression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_PrimaryExpression(PrimaryExpression primaryExpression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_expressions_NestedExpression(NestedExpression nestedExpression, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_generics_TypeArgument(TypeArgument typeArgument, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_generics_TypeArgumentable(TypeArgumentable typeArgumentable, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_generics_CallTypeArgumentable(CallTypeArgumentable callTypeArgumentable, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_generics_TypeParametrizable(TypeParametrizable typeParametrizable, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_generics_ExtendsTypeArgument(ExtendsTypeArgument extendsTypeArgument, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_generics_QualifiedTypeArgument(QualifiedTypeArgument qualifiedTypeArgument, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_generics_SuperTypeArgument(SuperTypeArgument superTypeArgument, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_generics_TypeParameter(TypeParameter typeParameter, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_generics_UnknownTypeArgument(UnknownTypeArgument unknownTypeArgument, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_imports_Import(Import _import, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_imports_ImportingElement(ImportingElement importingElement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_imports_StaticImport(StaticImport staticImport, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_imports_ClassifierImport(ClassifierImport classifierImport, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_imports_PackageImport(PackageImport packageImport, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_imports_StaticClassifierImport(StaticClassifierImport staticClassifierImport, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_imports_StaticMemberImport(StaticMemberImport staticMemberImport, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_instantiations_Initializable(Initializable initializable, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_instantiations_Instantiation(Instantiation instantiation, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_instantiations_NewConstructorCall(NewConstructorCall newConstructorCall, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_instantiations_ExplicitConstructorCall(ExplicitConstructorCall explicitConstructorCall, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_literals_Literal(Literal literal, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_literals_Self(Self self, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_literals_BooleanLiteral(BooleanLiteral booleanLiteral, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_literals_CharacterLiteral(CharacterLiteral characterLiteral, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_literals_FloatLiteral(FloatLiteral floatLiteral, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_literals_DecimalFloatLiteral(DecimalFloatLiteral decimalFloatLiteral, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_literals_HexFloatLiteral(HexFloatLiteral hexFloatLiteral, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_literals_DoubleLiteral(DoubleLiteral doubleLiteral, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_literals_DecimalDoubleLiteral(DecimalDoubleLiteral decimalDoubleLiteral, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_literals_HexDoubleLiteral(HexDoubleLiteral hexDoubleLiteral, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_literals_IntegerLiteral(IntegerLiteral integerLiteral, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_literals_DecimalIntegerLiteral(DecimalIntegerLiteral decimalIntegerLiteral, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_literals_HexIntegerLiteral(HexIntegerLiteral hexIntegerLiteral, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_literals_OctalIntegerLiteral(OctalIntegerLiteral octalIntegerLiteral, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_literals_LongLiteral(LongLiteral longLiteral, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_literals_DecimalLongLiteral(DecimalLongLiteral decimalLongLiteral, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_literals_HexLongLiteral(HexLongLiteral hexLongLiteral, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_literals_OctalLongLiteral(OctalLongLiteral octalLongLiteral, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_literals_NullLiteral(NullLiteral nullLiteral, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_literals_Super(Super _super, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_literals_This(This _this, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_members_ExceptionThrower(ExceptionThrower exceptionThrower, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_members_Member(Member member, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_members_MemberContainer(MemberContainer memberContainer, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_members_AdditionalField(AdditionalField additionalField, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_members_Constructor(Constructor constructor, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_members_EmptyMember(EmptyMember emptyMember, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_members_Field(Field field, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_members_Method(Method method, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_members_InterfaceMethod(InterfaceMethod interfaceMethod, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_members_ClassMethod(ClassMethod classMethod, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_members_EnumConstant(EnumConstant enumConstant, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_modifiers_Modifier(Modifier modifier, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_modifiers_AnnotationInstanceOrModifier(AnnotationInstanceOrModifier annotationInstanceOrModifier, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_modifiers_AnnotableAndModifiable(AnnotableAndModifiable annotableAndModifiable, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_modifiers_Modifiable(Modifiable modifiable, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_modifiers_Abstract(Abstract _abstract, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_modifiers_Final(Final _final, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_modifiers_Native(Native _native, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_modifiers_Protected(Protected _protected, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_modifiers_Public(Public _public, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_modifiers_Private(Private _private, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_modifiers_Static(Static _static, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_modifiers_Strictfp(Strictfp _strictfp, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_modifiers_Synchronized(Synchronized _synchronized, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_modifiers_Transient(Transient _transient, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_modifiers_Volatile(Volatile _volatile, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_Operator(Operator operator, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_AdditiveOperator(AdditiveOperator additiveOperator, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_AssignmentOperator(AssignmentOperator assignmentOperator, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_EqualityOperator(EqualityOperator equalityOperator, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_MultiplicativeOperator(MultiplicativeOperator multiplicativeOperator, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_RelationOperator(RelationOperator relationOperator, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_ShiftOperator(ShiftOperator shiftOperator, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_UnaryOperator(UnaryOperator unaryOperator, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_UnaryModificationOperator(UnaryModificationOperator unaryModificationOperator, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_Assignment(Assignment assignment, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_AssignmentAnd(AssignmentAnd assignmentAnd, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_AssignmentDivision(AssignmentDivision assignmentDivision, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_AssignmentExclusiveOr(AssignmentExclusiveOr assignmentExclusiveOr, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_AssignmentMinus(AssignmentMinus assignmentMinus, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_AssignmentModulo(AssignmentModulo assignmentModulo, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_AssignmentMultiplication(AssignmentMultiplication assignmentMultiplication, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_AssignmentLeftShift(AssignmentLeftShift assignmentLeftShift, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_AssignmentOr(AssignmentOr assignmentOr, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_AssignmentPlus(AssignmentPlus assignmentPlus, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_AssignmentRightShift(AssignmentRightShift assignmentRightShift, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_AssignmentUnsignedRightShift(AssignmentUnsignedRightShift assignmentUnsignedRightShift, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_Equal(Equal equal, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_NotEqual(NotEqual notEqual, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_GreaterThan(GreaterThan greaterThan, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_GreaterThanOrEqual(GreaterThanOrEqual greaterThanOrEqual, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_LessThan(LessThan lessThan, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_LessThanOrEqual(LessThanOrEqual lessThanOrEqual, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_Addition(Addition addition, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_Subtraction(Subtraction subtraction, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_Division(Division division, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_Multiplication(Multiplication multiplication, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_Remainder(Remainder remainder, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_Complement(Complement complement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_MinusMinus(MinusMinus minusMinus, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_Negate(Negate negate, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_PlusPlus(PlusPlus plusPlus, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_LeftShift(LeftShift leftShift, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_RightShift(RightShift rightShift, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_operators_UnsignedRightShift(UnsignedRightShift unsignedRightShift, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_parameters_Parameter(Parameter parameter, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_parameters_Parametrizable(Parametrizable parametrizable, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_parameters_OrdinaryParameter(OrdinaryParameter ordinaryParameter, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_parameters_VariableLengthParameter(VariableLengthParameter variableLengthParameter, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_references_Reference(Reference reference, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_references_Argumentable(Argumentable argumentable, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_references_ReferenceableElement(ReferenceableElement referenceableElement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_references_ElementReference(ElementReference elementReference, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_references_IdentifierReference(IdentifierReference identifierReference, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_references_MethodCall(MethodCall methodCall, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_references_ReflectiveClassReference(ReflectiveClassReference reflectiveClassReference, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_references_PrimitiveTypeReference(PrimitiveTypeReference primitiveTypeReference, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_references_StringReference(StringReference stringReference, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_references_SelfReference(SelfReference selfReference, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_references_PackageReference(PackageReference packageReference, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_StatementContainer(StatementContainer statementContainer, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_StatementListContainer(StatementListContainer statementListContainer, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_Conditional(Conditional conditional, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_ForLoopInitializer(ForLoopInitializer forLoopInitializer, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_Statement(Statement statement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_SwitchCase(SwitchCase switchCase, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_Assert(Assert _assert, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_Break(Break _break, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_Block(Block block, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_CatchBlock(CatchBlock catchBlock, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_Condition(Condition condition, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_Continue(Continue _continue, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_DefaultSwitchCase(DefaultSwitchCase defaultSwitchCase, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_DoWhileLoop(DoWhileLoop doWhileLoop, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_EmptyStatement(EmptyStatement emptyStatement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_ExpressionStatement(ExpressionStatement expressionStatement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_ForLoop(ForLoop forLoop, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_ForEachLoop(ForEachLoop forEachLoop, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_Jump(Jump jump, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_JumpLabel(JumpLabel jumpLabel, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_LocalVariableStatement(LocalVariableStatement localVariableStatement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_NormalSwitchCase(NormalSwitchCase normalSwitchCase, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_Return(Return _return, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_Switch(Switch _switch, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_SynchronizedBlock(SynchronizedBlock synchronizedBlock, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_Throw(Throw _throw, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_TryBlock(TryBlock tryBlock, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_statements_WhileLoop(WhileLoop whileLoop, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_types_Type(Type type, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_types_TypedElement(TypedElement typedElement, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_types_TypeReference(TypeReference typeReference, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_types_ClassifierReference(ClassifierReference classifierReference, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_types_NamespaceClassifierReference(NamespaceClassifierReference namespaceClassifierReference, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_types_PrimitiveType(PrimitiveType primitiveType, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_types_Boolean(org.emftext.language.java.types.Boolean _boolean, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_types_Byte(org.emftext.language.java.types.Byte _byte, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_types_Char(Char _char, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_types_Double(org.emftext.language.java.types.Double _double, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_types_Float(org.emftext.language.java.types.Float _float, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_types_Int(Int _int, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_types_Long(org.emftext.language.java.types.Long _long, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_types_Short(org.emftext.language.java.types.Short _short, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_types_Void(org.emftext.language.java.types.Void _void, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_variables_Variable(Variable variable, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_variables_LocalVariable(LocalVariable localVariable, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_emftext_language_java_variables_AdditionalLocalVariable(AdditionalLocalVariable additionalLocalVariable, ContextType context) {
		return null;
	}
	
	private void notifyListeners(EObject element) {
		for (org.emftext.language.java.resource.java.IJavaInterpreterListener listener : listeners) {
			listener.handleInterpreteObject(element);
		}
	}
	
	/**
	 * Adds the given object to the interpretation stack. Attention: Objects that are
	 * added first, are interpret last.
	 */
	public void addObjectToInterprete(EObject object) {
		interpretationStack.push(object);
	}
	
	/**
	 * Adds the given collection of objects to the interpretation stack. Attention:
	 * Collections that are added first, are interpret last.
	 */
	public void addObjectsToInterprete(Collection<? extends EObject> objects) {
		for (EObject object : objects) {
			addObjectToInterprete(object);
		}
	}
	
	/**
	 * Adds the given collection of objects in reverse order to the interpretation
	 * stack.
	 */
	public void addObjectsToInterpreteInReverseOrder(Collection<? extends EObject> objects) {
		List<EObject> reverse = new ArrayList<EObject>(objects.size());
		reverse.addAll(objects);
		Collections.reverse(reverse);
		addObjectsToInterprete(reverse);
	}
	
	/**
	 * Adds the given object and all its children to the interpretation stack such
	 * that they are interpret in top down order.
	 */
	public void addObjectTreeToInterpreteTopDown(EObject root) {
		List<EObject> objects = new ArrayList<EObject>();
		objects.add(root);
		Iterator<EObject> it = root.eAllContents();
		while (it.hasNext()) {
			EObject eObject = (EObject) it.next();
			objects.add(eObject);
		}
		addObjectsToInterpreteInReverseOrder(objects);
	}
	
	public void addListener(org.emftext.language.java.resource.java.IJavaInterpreterListener newListener) {
		listeners.add(newListener);
	}
	
	public boolean removeListener(org.emftext.language.java.resource.java.IJavaInterpreterListener listener) {
		return listeners.remove(listener);
	}
	
	public EObject getNextObjectToInterprete() {
		return nextObjectToInterprete;
	}
	
	public Stack<EObject> getInterpretationStack() {
		return interpretationStack;
	}
	
	public void terminate() {
		interpretationStack.clear();
	}
	
	public ContextType getCurrentContext() {
		return currentContext;
	}
	
}
