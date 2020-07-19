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

import org.emftext.language.java.classifiers.ConcreteClassifier;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Namespace Aware Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftext.language.java.commons.NamespaceAwareElement#getNamespaces <em>Namespaces</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftext.language.java.commons.CommonsPackage#getNamespaceAwareElement()
 * @model abstract="true"
 * @generated
 */
public interface NamespaceAwareElement extends Commentable
{
  /**
   * Returns the value of the '<em><b>Namespaces</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Namespaces</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Namespaces</em>' attribute list.
   * @see org.emftext.language.java.commons.CommonsPackage#getNamespaceAwareElement_Namespaces()
   * @model unique="false"
   * @generated
   */
  EList<String> getNamespaces();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.NamespaceAwareElementExtension.getNamespacesAsString((org.emftext.language.java.commons.NamespaceAwareElement) this);'"
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
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.commons.NamespaceAwareElementExtension.getClassifierAtNamespaces((org.emftext.language.java.commons.NamespaceAwareElement) this);'"
   * @generated
   */
  ConcreteClassifier getClassifierAtNamespaces();

} // NamespaceAwareElement
