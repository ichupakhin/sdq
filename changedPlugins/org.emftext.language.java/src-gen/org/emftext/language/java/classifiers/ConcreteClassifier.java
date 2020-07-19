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

import org.emftext.language.java.generics.TypeParametrizable;

import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;

import org.emftext.language.java.modifiers.AnnotableAndModifiable;

import org.emftext.language.java.statements.Statement;

import org.emftext.language.java.types.ClassifierReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Concrete Classifier</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.emftext.language.java.classifiers.ClassifiersPackage#getConcreteClassifier()
 * @model abstract="true"
 * @generated
 */
public interface ConcreteClassifier extends Classifier, TypeParametrizable, MemberContainer, Member, Statement, AnnotableAndModifiable
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.classifiers.ConcreteClassifierExtension.getInnerClassifiers((org.emftext.language.java.classifiers.ConcreteClassifier) this);'"
   * @generated
   */
  EList<ConcreteClassifier> getInnerClassifiers();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.classifiers.ConcreteClassifierExtension.getAllInnerClassifiers((org.emftext.language.java.classifiers.ConcreteClassifier) this);'"
   * @generated
   */
  EList<ConcreteClassifier> getAllInnerClassifiers();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.classifiers.ConcreteClassifierExtension.getSuperTypeReferences((org.emftext.language.java.classifiers.ConcreteClassifier) this);'"
   * @generated
   */
  EList<ClassifierReference> getSuperTypeReferences();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.classifiers.ConcreteClassifierExtension.getAllMembers((org.emftext.language.java.classifiers.ConcreteClassifier) this, (org.emftext.language.java.commons.Commentable) context);'"
   * @generated
   */
  EList<Member> getAllMembers(Commentable context);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation" required="true"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.classifiers.ConcreteClassifierExtension.getQualifiedName((org.emftext.language.java.classifiers.ConcreteClassifier) this);'"
   * @generated
   */
  String getQualifiedName();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model required="true" clazzRequired="true"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.classifiers.ConcreteClassifierExtension.isJavaLangObject((org.emftext.language.java.classifiers.ConcreteClassifier) clazz);'"
   * @generated
   */
  boolean isJavaLangObject(ConcreteClassifier clazz);

} // ConcreteClassifier
