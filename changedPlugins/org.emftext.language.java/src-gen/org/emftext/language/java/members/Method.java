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
package org.emftext.language.java.members;

import org.emftext.language.java.arrays.ArrayTypeable;

import org.emftext.language.java.generics.TypeParametrizable;

import org.emftext.language.java.modifiers.AnnotableAndModifiable;

import org.emftext.language.java.parameters.Parametrizable;

import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.ReferenceableElement;

import org.emftext.language.java.types.TypedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.emftext.language.java.members.MembersPackage#getMethod()
 * @model abstract="true"
 * @generated
 */
public interface Method extends Member, TypedElement, ArrayTypeable, TypeParametrizable, Parametrizable, ReferenceableElement, ExceptionThrower, AnnotableAndModifiable
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.members.MethodExtension.isMethodForCall((org.emftext.language.java.members.Method) this, (org.emftext.language.java.references.MethodCall) methodCall, needsPerfectMatch);'"
   * @generated
   */
  boolean isMethodForCall(MethodCall methodCall, boolean needsPerfectMatch);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.members.MethodExtension.isSomeMethodForCall((org.emftext.language.java.members.Method) this, (org.emftext.language.java.references.MethodCall) methodCall);'"
   * @generated
   */
  boolean isSomeMethodForCall(MethodCall methodCall);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.members.MethodExtension.isBetterMethodForCall((org.emftext.language.java.members.Method) this, (org.emftext.language.java.members.Method) otherMethod, (org.emftext.language.java.references.MethodCall) methodCall);'"
   * @generated
   */
  boolean isBetterMethodForCall(Method otherMethod, MethodCall methodCall);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.members.MethodExtension.getArrayDimension((org.emftext.language.java.members.Method) this);'"
   * @generated
   */
  long getArrayDimension();

} // Method
