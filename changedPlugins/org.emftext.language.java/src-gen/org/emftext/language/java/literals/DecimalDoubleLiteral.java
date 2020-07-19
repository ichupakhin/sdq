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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Decimal Double Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftext.language.java.literals.DecimalDoubleLiteral#getDecimalValue <em>Decimal Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftext.language.java.literals.LiteralsPackage#getDecimalDoubleLiteral()
 * @model
 * @generated
 */
public interface DecimalDoubleLiteral extends DoubleLiteral
{
  /**
   * Returns the value of the '<em><b>Decimal Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Decimal Value</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Decimal Value</em>' attribute.
   * @see #setDecimalValue(double)
   * @see org.emftext.language.java.literals.LiteralsPackage#getDecimalDoubleLiteral_DecimalValue()
   * @model required="true"
   * @generated
   */
  double getDecimalValue();

  /**
   * Sets the value of the '{@link org.emftext.language.java.literals.DecimalDoubleLiteral#getDecimalValue <em>Decimal Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Decimal Value</em>' attribute.
   * @see #getDecimalValue()
   * @generated
   */
  void setDecimalValue(double value);

} // DecimalDoubleLiteral
