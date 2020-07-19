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
package org.emftext.language.java.types;

import org.eclipse.emf.common.util.EList;

import org.emftext.language.java.arrays.ArrayTypeable;

import org.emftext.language.java.commons.Commentable;

import org.emftext.language.java.members.Member;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.emftext.language.java.types.TypesPackage#getType()
 * @model abstract="true"
 * @generated
 */
public interface Type extends Commentable
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.types.TypeExtension.equalsType((org.emftext.language.java.types.Type) this, arrayDimension, (org.emftext.language.java.types.Type) otherType, otherArrayDimension);'"
   * @generated
   */
  boolean equalsType(long arrayDimension, Type otherType, long otherArrayDimension);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.types.TypeExtension.isSuperType((org.emftext.language.java.types.Type) this, arrayDimension, (org.emftext.language.java.types.Type) otherType, (org.emftext.language.java.arrays.ArrayTypeable) otherArrayType);'"
   * @generated
   */
  boolean isSuperType(long arrayDimension, Type otherType, ArrayTypeable otherArrayType);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='// method has to be specified in subclasses\nthrow new java.lang.UnsupportedOperationException();'"
   * @generated
   */
  EList<Member> getAllMembers(Commentable context);

} // Type
