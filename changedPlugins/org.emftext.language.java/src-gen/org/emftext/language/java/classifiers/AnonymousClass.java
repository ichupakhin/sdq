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
package org.emftext.language.java.classifiers;

import org.eclipse.emf.common.util.EList;

import org.emftext.language.java.commons.Commentable;

import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;

import org.emftext.language.java.types.Type;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Anonymous Class</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.emftext.language.java.classifiers.ClassifiersPackage#getAnonymousClass()
 * @model
 * @generated
 */
public interface AnonymousClass extends Type, MemberContainer
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.classifiers.AnonymousClassExtension.getAllSuperClassifiers((org.emftext.language.java.classifiers.AnonymousClass) this);'"
   * @generated
   */
  EList<ConcreteClassifier> getAllSuperClassifiers();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.classifiers.AnonymousClassExtension.getSuperClassifier((org.emftext.language.java.classifiers.AnonymousClass) this);'"
   * @generated
   */
  ConcreteClassifier getSuperClassifier();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.classifiers.AnonymousClassExtension.getAllMembers((org.emftext.language.java.classifiers.AnonymousClass) this, (org.emftext.language.java.commons.Commentable) context);'"
   * @generated
   */
  EList<Member> getAllMembers(Commentable context);

} // AnonymousClass
