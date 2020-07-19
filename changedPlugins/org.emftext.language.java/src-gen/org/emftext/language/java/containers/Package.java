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

import org.emftext.language.java.annotations.Annotable;

import org.emftext.language.java.classifiers.ConcreteClassifier;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftext.language.java.containers.Package#getCompilationUnits <em>Compilation Units</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftext.language.java.containers.ContainersPackage#getPackage()
 * @model
 * @generated
 */
public interface Package extends JavaRoot, Annotable
{
  /**
   * Returns the value of the '<em><b>Compilation Units</b></em>' containment reference list.
   * The list contents are of type {@link org.emftext.language.java.containers.CompilationUnit}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Compilation Units</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Compilation Units</em>' containment reference list.
   * @see org.emftext.language.java.containers.ContainersPackage#getPackage_CompilationUnits()
   * @model containment="true" resolveProxies="true"
   * @generated
   */
  EList<CompilationUnit> getCompilationUnits();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.containers.PackageExtension.getNamespacesAsString((org.emftext.language.java.containers.Package) this);'"
   * @generated
   */
  String getNamespacesAsString();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.containers.PackageExtension.getClassifiersInSamePackage((org.emftext.language.java.containers.Package) this);'"
   * @generated
   */
  EList<ConcreteClassifier> getClassifiersInSamePackage();

} // Package
