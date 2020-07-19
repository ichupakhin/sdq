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

import org.eclipse.emf.common.util.EList;

import org.emftext.language.java.classifiers.ConcreteClassifier;

import org.emftext.language.java.commons.Commentable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Member Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftext.language.java.members.MemberContainer#getMembers <em>Members</em>}</li>
 *   <li>{@link org.emftext.language.java.members.MemberContainer#getDefaultMembers <em>Default Members</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftext.language.java.members.MembersPackage#getMemberContainer()
 * @model abstract="true"
 * @generated
 */
public interface MemberContainer extends Commentable
{
  /**
   * Returns the value of the '<em><b>Members</b></em>' containment reference list.
   * The list contents are of type {@link org.emftext.language.java.members.Member}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Members</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Members</em>' containment reference list.
   * @see org.emftext.language.java.members.MembersPackage#getMemberContainer_Members()
   * @model containment="true" resolveProxies="true"
   * @generated
   */
  EList<Member> getMembers();

  /**
   * Returns the value of the '<em><b>Default Members</b></em>' containment reference list.
   * The list contents are of type {@link org.emftext.language.java.members.Member}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Default Members</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Default Members</em>' containment reference list.
   * @see org.emftext.language.java.members.MembersPackage#getMemberContainer_DefaultMembers()
   * @model containment="true" resolveProxies="true"
   * @generated
   */
  EList<Member> getDefaultMembers();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.members.MemberContainerExtension.getContainedClassifier((org.emftext.language.java.members.MemberContainer) this, name);'"
   * @generated
   */
  ConcreteClassifier getContainedClassifier(String name);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.members.MemberContainerExtension.getContainedField((org.emftext.language.java.members.MemberContainer) this, name);'"
   * @generated
   */
  Field getContainedField(String name);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.members.MemberContainerExtension.getContainedMethod((org.emftext.language.java.members.MemberContainer) this, name);'"
   * @generated
   */
  Method getContainedMethod(String name);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.members.MemberContainerExtension.getMethods((org.emftext.language.java.members.MemberContainer) this);'"
   * @generated
   */
  EList<Method> getMethods();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model nameRequired="true"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='org.emftext.language.java.extensions.members.MemberContainerExtension.removeMethods((org.emftext.language.java.members.MemberContainer) this, name);'"
   * @generated
   */
  void removeMethods(String name);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model nameRequired="true"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.members.MemberContainerExtension.getMembersByName((org.emftext.language.java.members.MemberContainer) this, name);'"
   * @generated
   */
  EList<Member> getMembersByName(String name);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model nameRequired="true" qualifiedTypeNameRequired="true"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.members.MemberContainerExtension.createField((org.emftext.language.java.members.MemberContainer) this, name, qualifiedTypeName);'"
   * @generated
   */
  Field createField(String name, String qualifiedTypeName);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.members.MemberContainerExtension.getFields((org.emftext.language.java.members.MemberContainer) this);'"
   * @generated
   */
  EList<Field> getFields();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.members.MemberContainerExtension.getConstructors((org.emftext.language.java.members.MemberContainer) this);'"
   * @generated
   */
  EList<Constructor> getConstructors();

} // MemberContainer
