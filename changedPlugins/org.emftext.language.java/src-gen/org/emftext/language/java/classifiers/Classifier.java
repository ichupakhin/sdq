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

import org.emftext.language.java.references.ReferenceableElement;

import org.emftext.language.java.types.Type;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Classifier</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.emftext.language.java.classifiers.ClassifiersPackage#getClassifier()
 * @model abstract="true"
 * @generated
 */
public interface Classifier extends Type, ReferenceableElement
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.classifiers.ClassifierExtension.getAllSuperClassifiers((org.emftext.language.java.classifiers.Classifier) this);'"
   * @generated
   */
  EList<ConcreteClassifier> getAllSuperClassifiers();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model nameOfClassToImportRequired="true"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='org.emftext.language.java.extensions.classifiers.ClassifierExtension.addImport((org.emftext.language.java.classifiers.Classifier) this, nameOfClassToImport);'"
   * @generated
   */
  void addImport(String nameOfClassToImport);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='org.emftext.language.java.extensions.classifiers.ClassifierExtension.addPackageImport((org.emftext.language.java.classifiers.Classifier) this, packageName);'"
   * @generated
   */
  void addPackageImport(String packageName);

} // Classifier
