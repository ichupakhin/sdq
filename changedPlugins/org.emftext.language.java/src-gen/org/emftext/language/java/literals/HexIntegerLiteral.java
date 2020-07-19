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
package org.emftext.language.java.literals;

import java.math.BigInteger;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Hex Integer Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftext.language.java.literals.HexIntegerLiteral#getHexValue <em>Hex Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftext.language.java.literals.LiteralsPackage#getHexIntegerLiteral()
 * @model
 * @generated
 */
public interface HexIntegerLiteral extends IntegerLiteral
{
  /**
   * Returns the value of the '<em><b>Hex Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Hex Value</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Hex Value</em>' attribute.
   * @see #setHexValue(BigInteger)
   * @see org.emftext.language.java.literals.LiteralsPackage#getHexIntegerLiteral_HexValue()
   * @model required="true"
   * @generated
   */
  BigInteger getHexValue();

  /**
   * Sets the value of the '{@link org.emftext.language.java.literals.HexIntegerLiteral#getHexValue <em>Hex Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Hex Value</em>' attribute.
   * @see #getHexValue()
   * @generated
   */
  void setHexValue(BigInteger value);

} // HexIntegerLiteral
