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

import org.emftext.language.java.classifiers.ConcreteClassifier;

import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.commons.NamespaceAwareElement;

import org.emftext.language.java.imports.ImportingElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Root</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.emftext.language.java.containers.ContainersPackage#getJavaRoot()
 * @model abstract="true"
 * @generated
 */
public interface JavaRoot extends NamedElement, NamespaceAwareElement, ImportingElement
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.containers.JavaRootExtension.getClassifiersInSamePackage((org.emftext.language.java.containers.JavaRoot) this);'"
   * @generated
   */
  EList<ConcreteClassifier> getClassifiersInSamePackage();

} // JavaRoot
