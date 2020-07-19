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
package org.emftext.language.java.members.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.emftext.language.java.classifiers.ConcreteClassifier;

import org.emftext.language.java.commons.impl.CommentableImpl;

import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.members.MembersPackage;
import org.emftext.language.java.members.Method;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Member Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.emftext.language.java.members.impl.MemberContainerImpl#getMembers <em>Members</em>}</li>
 *   <li>{@link org.emftext.language.java.members.impl.MemberContainerImpl#getDefaultMembers <em>Default Members</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class MemberContainerImpl extends CommentableImpl implements MemberContainer
{
  /**
   * The cached value of the '{@link #getMembers() <em>Members</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMembers()
   * @generated
   * @ordered
   */
  protected EList<Member> members;

  /**
   * The cached value of the '{@link #getDefaultMembers() <em>Default Members</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDefaultMembers()
   * @generated
   * @ordered
   */
  protected EList<Member> defaultMembers;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MemberContainerImpl()
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
    return MembersPackage.Literals.MEMBER_CONTAINER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Member> getMembers()
  {
    if (members == null)
    {
      members = new EObjectContainmentEList.Resolving<Member>(Member.class, this, MembersPackage.MEMBER_CONTAINER__MEMBERS);
    }
    return members;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Member> getDefaultMembers()
  {
    if (defaultMembers == null)
    {
      defaultMembers = new EObjectContainmentEList.Resolving<Member>(Member.class, this, MembersPackage.MEMBER_CONTAINER__DEFAULT_MEMBERS);
    }
    return defaultMembers;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConcreteClassifier getContainedClassifier(final String name)
  {
    return org.emftext.language.java.extensions.members.MemberContainerExtension.getContainedClassifier((org.emftext.language.java.members.MemberContainer) this, name);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Field getContainedField(final String name)
  {
    return org.emftext.language.java.extensions.members.MemberContainerExtension.getContainedField((org.emftext.language.java.members.MemberContainer) this, name);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Method getContainedMethod(final String name)
  {
    return org.emftext.language.java.extensions.members.MemberContainerExtension.getContainedMethod((org.emftext.language.java.members.MemberContainer) this, name);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Method> getMethods()
  {
    return org.emftext.language.java.extensions.members.MemberContainerExtension.getMethods((org.emftext.language.java.members.MemberContainer) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void removeMethods(final String name)
  {
    org.emftext.language.java.extensions.members.MemberContainerExtension.removeMethods((org.emftext.language.java.members.MemberContainer) this, name);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Member> getMembersByName(final String name)
  {
    return org.emftext.language.java.extensions.members.MemberContainerExtension.getMembersByName((org.emftext.language.java.members.MemberContainer) this, name);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Field createField(final String name, final String qualifiedTypeName)
  {
    return org.emftext.language.java.extensions.members.MemberContainerExtension.createField((org.emftext.language.java.members.MemberContainer) this, name, qualifiedTypeName);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Field> getFields()
  {
    return org.emftext.language.java.extensions.members.MemberContainerExtension.getFields((org.emftext.language.java.members.MemberContainer) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Constructor> getConstructors()
  {
    return org.emftext.language.java.extensions.members.MemberContainerExtension.getConstructors((org.emftext.language.java.members.MemberContainer) this);
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
      case MembersPackage.MEMBER_CONTAINER__MEMBERS:
        return ((InternalEList<?>)getMembers()).basicRemove(otherEnd, msgs);
      case MembersPackage.MEMBER_CONTAINER__DEFAULT_MEMBERS:
        return ((InternalEList<?>)getDefaultMembers()).basicRemove(otherEnd, msgs);
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
      case MembersPackage.MEMBER_CONTAINER__MEMBERS:
        return getMembers();
      case MembersPackage.MEMBER_CONTAINER__DEFAULT_MEMBERS:
        return getDefaultMembers();
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
      case MembersPackage.MEMBER_CONTAINER__MEMBERS:
        getMembers().clear();
        getMembers().addAll((Collection<? extends Member>)newValue);
        return;
      case MembersPackage.MEMBER_CONTAINER__DEFAULT_MEMBERS:
        getDefaultMembers().clear();
        getDefaultMembers().addAll((Collection<? extends Member>)newValue);
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
      case MembersPackage.MEMBER_CONTAINER__MEMBERS:
        getMembers().clear();
        return;
      case MembersPackage.MEMBER_CONTAINER__DEFAULT_MEMBERS:
        getDefaultMembers().clear();
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
      case MembersPackage.MEMBER_CONTAINER__MEMBERS:
        return members != null && !members.isEmpty();
      case MembersPackage.MEMBER_CONTAINER__DEFAULT_MEMBERS:
        return defaultMembers != null && !defaultMembers.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //MemberContainerImpl
