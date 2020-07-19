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
package org.emftext.language.java.commons;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.emftext.commons.layout.LayoutInformation;

import org.emftext.language.java.annotations.AnnotationInstance;

import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Interface;

import org.emftext.language.java.containers.CompilationUnit;

import org.emftext.language.java.statements.Statement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Commentable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftext.language.java.commons.Commentable#getLayoutInformations <em>Layout Informations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftext.language.java.commons.CommonsPackage#getCommentable()
 * @model abstract="true"
 * @generated
 */
public interface Commentable extends EObject
{
  /**
   * Returns the value of the '<em><b>Layout Informations</b></em>' containment reference list.
   * The list contents are of type {@link org.emftext.commons.layout.LayoutInformation}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Layout Informations</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Layout Informations</em>' containment reference list.
   * @see org.emftext.language.java.commons.CommonsPackage#getCommentable_LayoutInformations()
   * @model containment="true" resolveProxies="true"
   * @generated
   */
  EList<LayoutInformation> getLayoutInformations();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getConcreteClassifier((org.emftext.language.java.commons.Commentable) this, name);'"
   * @generated
   */
  ConcreteClassifier getConcreteClassifier(String name);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getConcreteClassifierProxy((org.emftext.language.java.commons.Commentable) this, name);'"
   * @generated
   */
  ConcreteClassifier getConcreteClassifierProxy(String name);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getConcreteClassifiers((org.emftext.language.java.commons.Commentable) this, packageName, classifierQuery);'"
   * @generated
   */
  EList<ConcreteClassifier> getConcreteClassifiers(String packageName, String classifierQuery);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getConcreteClassifierProxies((org.emftext.language.java.commons.Commentable) this, packageName, classifierQuery);'"
   * @generated
   */
  EList<ConcreteClassifier> getConcreteClassifierProxies(String packageName, String classifierQuery);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getLibClass((org.emftext.language.java.commons.Commentable) this, name);'"
   * @generated
   */
  org.emftext.language.java.classifiers.Class getLibClass(String name);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getLibInterface((org.emftext.language.java.commons.Commentable) this, name);'"
   * @generated
   */
  Interface getLibInterface(String name);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getClassClass((org.emftext.language.java.commons.Commentable) this);'"
   * @generated
   */
  org.emftext.language.java.classifiers.Class getClassClass();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getObjectClass((org.emftext.language.java.commons.Commentable) this);'"
   * @generated
   */
  org.emftext.language.java.classifiers.Class getObjectClass();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getStringClass((org.emftext.language.java.commons.Commentable) this);'"
   * @generated
   */
  org.emftext.language.java.classifiers.Class getStringClass();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getAnnotationInterface((org.emftext.language.java.commons.Commentable) this);'"
   * @generated
   */
  Interface getAnnotationInterface();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getContainingAnnotationInstance((org.emftext.language.java.commons.Commentable) this);'"
   * @generated
   */
  AnnotationInstance getContainingAnnotationInstance();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getContainingAnonymousClass((org.emftext.language.java.commons.Commentable) this);'"
   * @generated
   */
  AnonymousClass getContainingAnonymousClass();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getContainingConcreteClassifier((org.emftext.language.java.commons.Commentable) this);'"
   * @generated
   */
  ConcreteClassifier getContainingConcreteClassifier();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getContainingCompilationUnit((org.emftext.language.java.commons.Commentable) this);'"
   * @generated
   */
  CompilationUnit getContainingCompilationUnit();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getContainingPackageName((org.emftext.language.java.commons.Commentable) this);'"
   * @generated
   */
  EList<String> getContainingPackageName();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getContainingContainerName((org.emftext.language.java.commons.Commentable) this);'"
   * @generated
   */
  EList<String> getContainingContainerName();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getParentConcreteClassifier((org.emftext.language.java.commons.Commentable) this);'"
   * @generated
   */
  ConcreteClassifier getParentConcreteClassifier();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getParentByEType((org.emftext.language.java.commons.Commentable) this, (org.eclipse.emf.ecore.EClass) type);'"
   * @generated
   */
  EObject getParentByEType(EClass type);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getFirstChildByEType((org.emftext.language.java.commons.Commentable) this, (org.eclipse.emf.ecore.EClass) type);'"
   * @generated
   */
  EObject getFirstChildByEType(EClass type);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getParentByType((org.emftext.language.java.commons.Commentable) this, type);'"
   * @generated
   */
  <T> T getParentByType(Class<T> type);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getFirstChildByType((org.emftext.language.java.commons.Commentable) this, ( java.lang.Class<T>) type);'"
   * @generated
   */
  <T> T getFirstChildByType(Class<T> type);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getChildrenByEType((org.emftext.language.java.commons.Commentable) this, (org.eclipse.emf.ecore.EClass) type);'"
   * @generated
   */
  EList<EObject> getChildrenByEType(EClass type);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getChildrenByType((org.emftext.language.java.commons.Commentable) this, ( java.lang.Class<T>) type);'"
   * @generated
   */
  <T> EList<T> getChildrenByType(Class<T> type);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.CommentableExtension.getComments((org.emftext.language.java.commons.Commentable) this);'"
   * @generated
   */
  EList<String> getComments();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model statementToAddRequired="true"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='org.emftext.language.java.extensions.commons.CommentableExtension.addBeforeContainingStatement((org.emftext.language.java.commons.Commentable) this, (org.emftext.language.java.statements.Statement) statementToAdd);'"
   * @generated
   */
  void addBeforeContainingStatement(Statement statementToAdd);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model statementToAddRequired="true"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='org.emftext.language.java.extensions.commons.CommentableExtension.addAfterContainingStatement((org.emftext.language.java.commons.Commentable) this, (org.emftext.language.java.statements.Statement) statementToAdd);'"
   * @generated
   */
  void addAfterContainingStatement(Statement statementToAdd);

} // Commentable
