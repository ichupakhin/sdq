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
package org.emftext.language.java.generics;

import org.eclipse.emf.common.util.EList;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ConcreteClassifier;

import org.emftext.language.java.commons.Commentable;

import org.emftext.language.java.members.Member;

import org.emftext.language.java.references.Reference;

import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftext.language.java.generics.TypeParameter#getExtendTypes <em>Extend Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftext.language.java.generics.GenericsPackage#getTypeParameter()
 * @model
 * @generated
 */
public interface TypeParameter extends Classifier
{
  /**
   * Returns the value of the '<em><b>Extend Types</b></em>' containment reference list.
   * The list contents are of type {@link org.emftext.language.java.types.TypeReference}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Extend Types</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Extend Types</em>' containment reference list.
   * @see org.emftext.language.java.generics.GenericsPackage#getTypeParameter_ExtendTypes()
   * @model containment="true" resolveProxies="true"
   * @generated
   */
  EList<TypeReference> getExtendTypes();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.generics.TypeParameterExtension.getAllSuperClassifiers((org.emftext.language.java.generics.TypeParameter) this);'"
   * @generated
   */
  EList<ConcreteClassifier> getAllSuperClassifiers();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.generics.TypeParameterExtension.getAllMembers((org.emftext.language.java.generics.TypeParameter) this, (org.emftext.language.java.commons.Commentable) context);'"
   * @generated
   */
  EList<Member> getAllMembers(Commentable context);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.generics.TypeParameterExtension.getBoundType((org.emftext.language.java.generics.TypeParameter) this, (org.emftext.language.java.types.TypeReference) typeReference, (org.emftext.language.java.references.Reference) reference);'"
   * @generated
   */
  Type getBoundType(TypeReference typeReference, Reference reference);

} // TypeParameter
