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
package org.emftext.language.java.members;

import org.emftext.language.java.arrays.ArrayTypeable;

import org.emftext.language.java.instantiations.Initializable;

import org.emftext.language.java.references.ReferenceableElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Additional Field</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.emftext.language.java.members.MembersPackage#getAdditionalField()
 * @model
 * @generated
 */
public interface AdditionalField extends ReferenceableElement, ArrayTypeable, Initializable
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.members.AdditionalFieldExtension.getArrayDimension((org.emftext.language.java.members.AdditionalField) this);'"
   * @generated
   */
  long getArrayDimension();

} // AdditionalField
