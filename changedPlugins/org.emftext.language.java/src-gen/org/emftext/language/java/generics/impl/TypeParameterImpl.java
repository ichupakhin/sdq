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
package org.emftext.language.java.generics.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.emftext.language.java.classifiers.ConcreteClassifier;

import org.emftext.language.java.classifiers.impl.ClassifierImpl;

import org.emftext.language.java.commons.Commentable;

import org.emftext.language.java.generics.GenericsPackage;
import org.emftext.language.java.generics.TypeParameter;

import org.emftext.language.java.members.Member;

import org.emftext.language.java.references.Reference;

import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.emftext.language.java.generics.impl.TypeParameterImpl#getExtendTypes <em>Extend Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeParameterImpl extends ClassifierImpl implements TypeParameter
{
  /**
   * The cached value of the '{@link #getExtendTypes() <em>Extend Types</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExtendTypes()
   * @generated
   * @ordered
   */
  protected EList<TypeReference> extendTypes;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TypeParameterImpl()
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
    return GenericsPackage.Literals.TYPE_PARAMETER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<TypeReference> getExtendTypes()
  {
    if (extendTypes == null)
    {
      extendTypes = new EObjectContainmentEList.Resolving<TypeReference>(TypeReference.class, this, GenericsPackage.TYPE_PARAMETER__EXTEND_TYPES);
    }
    return extendTypes;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ConcreteClassifier> getAllSuperClassifiers()
  {
    return org.emftext.language.java.extensions.generics.TypeParameterExtension.getAllSuperClassifiers((org.emftext.language.java.generics.TypeParameter) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Member> getAllMembers(final Commentable context)
  {
    return org.emftext.language.java.extensions.generics.TypeParameterExtension.getAllMembers((org.emftext.language.java.generics.TypeParameter) this, (org.emftext.language.java.commons.Commentable) context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Type getBoundType(final TypeReference typeReference, final Reference reference)
  {
    return org.emftext.language.java.extensions.generics.TypeParameterExtension.getBoundType((org.emftext.language.java.generics.TypeParameter) this, (org.emftext.language.java.types.TypeReference) typeReference, (org.emftext.language.java.references.Reference) reference);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case GenericsPackage.TYPE_PARAMETER__EXTEND_TYPES:
        return ((InternalEList<?>)getExtendTypes()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case GenericsPackage.TYPE_PARAMETER__EXTEND_TYPES:
        return getExtendTypes();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case GenericsPackage.TYPE_PARAMETER__EXTEND_TYPES:
        getExtendTypes().clear();
        getExtendTypes().addAll((Collection<? extends TypeReference>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case GenericsPackage.TYPE_PARAMETER__EXTEND_TYPES:
        getExtendTypes().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case GenericsPackage.TYPE_PARAMETER__EXTEND_TYPES:
        return extendTypes != null && !extendTypes.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //TypeParameterImpl
