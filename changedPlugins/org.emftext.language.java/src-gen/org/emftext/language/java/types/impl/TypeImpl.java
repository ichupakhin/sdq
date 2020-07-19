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
package org.emftext.language.java.types.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.emftext.language.java.arrays.ArrayTypeable;

import org.emftext.language.java.commons.Commentable;

import org.emftext.language.java.commons.impl.CommentableImpl;

import org.emftext.language.java.members.Member;

import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class TypeImpl extends CommentableImpl implements Type
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TypeImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return TypesPackage.Literals.TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean equalsType(final long arrayDimension, final Type otherType, final long otherArrayDimension)
  {
    return org.emftext.language.java.extensions.types.TypeExtension.equalsType((org.emftext.language.java.types.Type) this, arrayDimension, (org.emftext.language.java.types.Type) otherType, otherArrayDimension);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSuperType(final long arrayDimension, final Type otherType, final ArrayTypeable otherArrayType)
  {
    return org.emftext.language.java.extensions.types.TypeExtension.isSuperType((org.emftext.language.java.types.Type) this, arrayDimension, (org.emftext.language.java.types.Type) otherType, (org.emftext.language.java.arrays.ArrayTypeable) otherArrayType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Member> getAllMembers(final Commentable context)
  {
    // method has to be specified in subclasses
    throw new java.lang.UnsupportedOperationException();
  }

} //TypeImpl
