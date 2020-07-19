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

import org.emftext.language.java.classifiers.Classifier;

import org.emftext.language.java.commons.Commentable;

import org.emftext.language.java.members.Member;

import org.emftext.language.java.references.Reference;

import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.PrimitiveType;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Primitive Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class PrimitiveTypeImpl extends TypeImpl implements PrimitiveType
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PrimitiveTypeImpl()
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
    return TypesPackage.Literals.PRIMITIVE_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Member> getAllMembers(final Commentable context)
  {
    return org.emftext.language.java.extensions.types.PrimitiveTypeExtension.getAllMembers((org.emftext.language.java.types.PrimitiveType) this, (org.emftext.language.java.commons.Commentable) context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public org.emftext.language.java.classifiers.Class wrapPrimitiveType()
  {
    return org.emftext.language.java.extensions.types.PrimitiveTypeExtension.wrapPrimitiveType((org.emftext.language.java.types.PrimitiveType) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Type getTarget()
  {
    return org.emftext.language.java.extensions.types.TypeReferenceExtension.getBoundTarget((org.emftext.language.java.types.TypeReference) this, null);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTarget(final Classifier type)
  {
    org.emftext.language.java.extensions.types.TypeReferenceExtension.setTarget((org.emftext.language.java.types.TypeReference) this, (org.emftext.language.java.classifiers.Classifier) type);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Type getBoundTarget(final Reference reference)
  {
    return org.emftext.language.java.extensions.types.TypeReferenceExtension.getBoundTarget((org.emftext.language.java.types.TypeReference) this, (org.emftext.language.java.references.Reference) reference);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ClassifierReference getPureClassifierReference()
  {
    return org.emftext.language.java.extensions.types.TypeReferenceExtension.getPureClassifierReference((org.emftext.language.java.types.TypeReference) this);
  }

} //PrimitiveTypeImpl
