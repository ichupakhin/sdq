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
package org.emftext.language.java.types;

import org.emftext.language.java.classifiers.Classifier;

import org.emftext.language.java.commons.Commentable;

import org.emftext.language.java.references.Reference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.emftext.language.java.types.TypesPackage#getTypeReference()
 * @model abstract="true"
 * @generated
 */
public interface TypeReference extends Commentable
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.types.TypeReferenceExtension.getBoundTarget((org.emftext.language.java.types.TypeReference) this, null);'"
   * @generated
   */
  Type getTarget();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='org.emftext.language.java.extensions.types.TypeReferenceExtension.setTarget((org.emftext.language.java.types.TypeReference) this, (org.emftext.language.java.classifiers.Classifier) type);'"
   * @generated
   */
  void setTarget(Classifier type);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.types.TypeReferenceExtension.getBoundTarget((org.emftext.language.java.types.TypeReference) this, (org.emftext.language.java.references.Reference) reference);'"
   * @generated
   */
  Type getBoundTarget(Reference reference);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.types.TypeReferenceExtension.getPureClassifierReference((org.emftext.language.java.types.TypeReference) this);'"
   * @generated
   */
  ClassifierReference getPureClassifierReference();

} // TypeReference
