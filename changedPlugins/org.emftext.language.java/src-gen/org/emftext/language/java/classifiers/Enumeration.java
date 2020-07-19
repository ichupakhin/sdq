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

import org.emftext.language.java.members.EnumConstant;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enumeration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftext.language.java.classifiers.Enumeration#getConstants <em>Constants</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftext.language.java.classifiers.ClassifiersPackage#getEnumeration()
 * @model
 * @generated
 */
public interface Enumeration extends ConcreteClassifier, Implementor
{
  /**
   * Returns the value of the '<em><b>Constants</b></em>' containment reference list.
   * The list contents are of type {@link org.emftext.language.java.members.EnumConstant}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Constants</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Constants</em>' containment reference list.
   * @see org.emftext.language.java.classifiers.ClassifiersPackage#getEnumeration_Constants()
   * @model containment="true" resolveProxies="true"
   * @generated
   */
  EList<EnumConstant> getConstants();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.classifiers.EnumerationExtension.getAllSuperClassifiers((org.emftext.language.java.classifiers.Enumeration) this);'"
   * @generated
   */
  EList<ConcreteClassifier> getAllSuperClassifiers();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.classifiers.EnumerationExtension.getContainedConstant((org.emftext.language.java.classifiers.Enumeration) this, name);'"
   * @generated
   */
  EnumConstant getContainedConstant(String name);

} // Enumeration
