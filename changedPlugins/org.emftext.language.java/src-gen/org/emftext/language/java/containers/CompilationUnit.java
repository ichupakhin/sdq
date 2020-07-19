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
package org.emftext.language.java.containers;

import org.eclipse.emf.common.util.EList;

import org.emftext.language.java.classifiers.Annotation;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.classifiers.Interface;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Compilation Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftext.language.java.containers.CompilationUnit#getClassifiers <em>Classifiers</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftext.language.java.containers.ContainersPackage#getCompilationUnit()
 * @model
 * @generated
 */
public interface CompilationUnit extends JavaRoot
{
  /**
   * Returns the value of the '<em><b>Classifiers</b></em>' containment reference list.
   * The list contents are of type {@link org.emftext.language.java.classifiers.ConcreteClassifier}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Classifiers</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Classifiers</em>' containment reference list.
   * @see org.emftext.language.java.containers.ContainersPackage#getCompilationUnit_Classifiers()
   * @model containment="true" resolveProxies="true" required="true"
   * @generated
   */
  EList<ConcreteClassifier> getClassifiers();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.containers.CompilationUnitExtension.getContainedClassifier((org.emftext.language.java.containers.CompilationUnit) this, name);'"
   * @generated
   */
  ConcreteClassifier getContainedClassifier(String name);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.containers.CompilationUnitExtension.getClassifiersInSamePackage((org.emftext.language.java.containers.CompilationUnit) this);'"
   * @generated
   */
  EList<ConcreteClassifier> getClassifiersInSamePackage();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.containers.CompilationUnitExtension.getContainedClass((org.emftext.language.java.containers.CompilationUnit) this);'"
   * @generated
   */
  org.emftext.language.java.classifiers.Class getContainedClass();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.containers.CompilationUnitExtension.getContainedInterface((org.emftext.language.java.containers.CompilationUnit) this);'"
   * @generated
   */
  Interface getContainedInterface();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.containers.CompilationUnitExtension.getContainedAnnotation((org.emftext.language.java.containers.CompilationUnit) this);'"
   * @generated
   */
  Annotation getContainedAnnotation();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.containers.CompilationUnitExtension.getContainedEnumeration((org.emftext.language.java.containers.CompilationUnit) this);'"
   * @generated
   */
  Enumeration getContainedEnumeration();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model nameOfClassToImportRequired="true"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='org.emftext.language.java.extensions.containers.CompilationUnitExtension.addImport((org.emftext.language.java.containers.CompilationUnit) this, nameOfClassToImport);'"
   * @generated
   */
  void addImport(String nameOfClassToImport);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='org.emftext.language.java.extensions.containers.CompilationUnitExtension.addPackageImport((org.emftext.language.java.containers.CompilationUnit) this, packageName);'"
   * @generated
   */
  void addPackageImport(String packageName);

} // CompilationUnit
