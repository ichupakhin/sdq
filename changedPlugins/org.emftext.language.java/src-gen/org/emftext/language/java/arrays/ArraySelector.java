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
package org.emftext.language.java.arrays;

import org.emftext.language.java.commons.Commentable;

import org.emftext.language.java.expressions.Expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Selector</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftext.language.java.arrays.ArraySelector#getPosition <em>Position</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftext.language.java.arrays.ArraysPackage#getArraySelector()
 * @model
 * @generated
 */
public interface ArraySelector extends Commentable
{
  /**
   * Returns the value of the '<em><b>Position</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Position</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Position</em>' containment reference.
   * @see #setPosition(Expression)
   * @see org.emftext.language.java.arrays.ArraysPackage#getArraySelector_Position()
   * @model containment="true" resolveProxies="true"
   * @generated
   */
  Expression getPosition();

  /**
   * Sets the value of the '{@link org.emftext.language.java.arrays.ArraySelector#getPosition <em>Position</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Position</em>' containment reference.
   * @see #getPosition()
   * @generated
   */
  void setPosition(Expression value);

} // ArraySelector
