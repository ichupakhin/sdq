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
package org.emftext.language.java.literals.impl;

import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.emftext.language.java.literals.HexIntegerLiteral;
import org.emftext.language.java.literals.LiteralsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Hex Integer Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.emftext.language.java.literals.impl.HexIntegerLiteralImpl#getHexValue <em>Hex Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HexIntegerLiteralImpl extends IntegerLiteralImpl implements HexIntegerLiteral
{
  /**
   * The default value of the '{@link #getHexValue() <em>Hex Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHexValue()
   * @generated
   * @ordered
   */
  protected static final BigInteger HEX_VALUE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getHexValue() <em>Hex Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHexValue()
   * @generated
   * @ordered
   */
  protected BigInteger hexValue = HEX_VALUE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected HexIntegerLiteralImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return LiteralsPackage.Literals.HEX_INTEGER_LITERAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigInteger getHexValue()
  {
    return hexValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHexValue(BigInteger newHexValue)
  {
    BigInteger oldHexValue = hexValue;
    hexValue = newHexValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LiteralsPackage.HEX_INTEGER_LITERAL__HEX_VALUE, oldHexValue, hexValue));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case LiteralsPackage.HEX_INTEGER_LITERAL__HEX_VALUE:
        return getHexValue();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case LiteralsPackage.HEX_INTEGER_LITERAL__HEX_VALUE:
        setHexValue((BigInteger)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case LiteralsPackage.HEX_INTEGER_LITERAL__HEX_VALUE:
        setHexValue(HEX_VALUE_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case LiteralsPackage.HEX_INTEGER_LITERAL__HEX_VALUE:
        return HEX_VALUE_EDEFAULT == null ? hexValue != null : !HEX_VALUE_EDEFAULT.equals(hexValue);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (hexValue: ");
    result.append(hexValue);
    result.append(')');
    return result.toString();
  }

} //HexIntegerLiteralImpl
