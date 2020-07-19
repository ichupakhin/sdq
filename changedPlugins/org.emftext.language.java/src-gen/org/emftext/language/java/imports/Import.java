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
package org.emftext.language.java.imports;

import org.eclipse.emf.common.util.EList;

import org.emftext.language.java.classifiers.ConcreteClassifier;

import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.commons.NamespaceAwareElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Import</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.emftext.language.java.imports.ImportsPackage#getImport()
 * @model abstract="true"
 * @generated
 */
public interface Import extends NamespaceAwareElement
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.imports.ImportExtension.getImportedClassifier((org.emftext.language.java.imports.Import) this, name);'"
   * @generated
   */
  ConcreteClassifier getImportedClassifier(String name);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.imports.ImportExtension.getImportedClassifiers((org.emftext.language.java.imports.Import) this);'"
   * @generated
   */
  EList<ConcreteClassifier> getImportedClassifiers();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.imports.ImportExtension.getImportedMembers((org.emftext.language.java.imports.Import) this);'"
   * @generated
   */
  EList<NamedElement> getImportedMembers();

} // Import
