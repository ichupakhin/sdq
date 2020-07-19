/**
 * Copyright (c) 2006-2014
 * Software Technology Group, Dresden University of Technology
 * DevBoost GmbH, Berlin, Amtsgericht Charlottenburg, HRB 140026
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Software Technology Group - TU Dresden, Germany;
 *   DevBoost GmbH - Berlin, Germany
 *      - initial API and implementation
 *  
 */
package org.emftext.language.java.variables;

import org.eclipse.emf.common.util.EList;

import org.emftext.language.java.arrays.ArrayTypeable;

import org.emftext.language.java.commons.NamedElement;

import org.emftext.language.java.expressions.Expression;

import org.emftext.language.java.generics.TypeArgumentable;

import org.emftext.language.java.references.ReferenceableElement;

import org.emftext.language.java.statements.Statement;

import org.emftext.language.java.types.TypedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.emftext.language.java.variables.VariablesPackage#getVariable()
 * @model abstract="true"
 * @generated
 */
public interface Variable extends NamedElement, TypedElement, ArrayTypeable, ReferenceableElement, TypeArgumentable
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.variables.VariableExtension.getArrayDimension((org.emftext.language.java.variables.Variable) this);'"
   * @generated
   */
  long getArrayDimension();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model methodNameRequired="true" argumentsMany="true"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.variables.VariableExtension.createMethodCallStatement((org.emftext.language.java.variables.Variable) this, methodName, arguments);'"
   * @generated
   */
  Statement createMethodCallStatement(String methodName, EList<Expression> arguments);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model methodNameRequired="true" argumentsMany="true"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.variables.VariableExtension.createMethodCall((org.emftext.language.java.variables.Variable) this, methodName, arguments);'"
   * @generated
   */
  Expression createMethodCall(String methodName, EList<Expression> arguments);

} // Variable
