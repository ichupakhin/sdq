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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Instantiation By Values</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftext.language.java.arrays.ArrayInstantiationByValues#getArrayInitializer <em>Array Initializer</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftext.language.java.arrays.ArraysPackage#getArrayInstantiationByValues()
 * @model abstract="true"
 * @generated
 */
public interface ArrayInstantiationByValues extends ArrayInstantiation
{
  /**
   * Returns the value of the '<em><b>Array Initializer</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Array Initializer</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Array Initializer</em>' containment reference.
   * @see #setArrayInitializer(ArrayInitializer)
   * @see org.emftext.language.java.arrays.ArraysPackage#getArrayInstantiationByValues_ArrayInitializer()
   * @model containment="true" resolveProxies="true" required="true"
   * @generated
   */
  ArrayInitializer getArrayInitializer();

  /**
   * Sets the value of the '{@link org.emftext.language.java.arrays.ArrayInstantiationByValues#getArrayInitializer <em>Array Initializer</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Array Initializer</em>' containment reference.
   * @see #getArrayInitializer()
   * @generated
   */
  void setArrayInitializer(ArrayInitializer value);

} // ArrayInstantiationByValues
