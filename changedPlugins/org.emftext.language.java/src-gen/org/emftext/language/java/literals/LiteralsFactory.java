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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.emftext.language.java.literals.LiteralsPackage
 * @generated
 */
public interface LiteralsFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  LiteralsFactory eINSTANCE = org.emftext.language.java.literals.impl.LiteralsFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Boolean Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Boolean Literal</em>'.
   * @generated
   */
  BooleanLiteral createBooleanLiteral();

  /**
   * Returns a new object of class '<em>Character Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Character Literal</em>'.
   * @generated
   */
  CharacterLiteral createCharacterLiteral();

  /**
   * Returns a new object of class '<em>Decimal Float Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Decimal Float Literal</em>'.
   * @generated
   */
  DecimalFloatLiteral createDecimalFloatLiteral();

  /**
   * Returns a new object of class '<em>Hex Float Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Hex Float Literal</em>'.
   * @generated
   */
  HexFloatLiteral createHexFloatLiteral();

  /**
   * Returns a new object of class '<em>Decimal Double Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Decimal Double Literal</em>'.
   * @generated
   */
  DecimalDoubleLiteral createDecimalDoubleLiteral();

  /**
   * Returns a new object of class '<em>Hex Double Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Hex Double Literal</em>'.
   * @generated
   */
  HexDoubleLiteral createHexDoubleLiteral();

  /**
   * Returns a new object of class '<em>Decimal Integer Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Decimal Integer Literal</em>'.
   * @generated
   */
  DecimalIntegerLiteral createDecimalIntegerLiteral();

  /**
   * Returns a new object of class '<em>Hex Integer Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Hex Integer Literal</em>'.
   * @generated
   */
  HexIntegerLiteral createHexIntegerLiteral();

  /**
   * Returns a new object of class '<em>Octal Integer Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Octal Integer Literal</em>'.
   * @generated
   */
  OctalIntegerLiteral createOctalIntegerLiteral();

  /**
   * Returns a new object of class '<em>Decimal Long Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Decimal Long Literal</em>'.
   * @generated
   */
  DecimalLongLiteral createDecimalLongLiteral();

  /**
   * Returns a new object of class '<em>Hex Long Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Hex Long Literal</em>'.
   * @generated
   */
  HexLongLiteral createHexLongLiteral();

  /**
   * Returns a new object of class '<em>Octal Long Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Octal Long Literal</em>'.
   * @generated
   */
  OctalLongLiteral createOctalLongLiteral();

  /**
   * Returns a new object of class '<em>Null Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Null Literal</em>'.
   * @generated
   */
  NullLiteral createNullLiteral();

  /**
   * Returns a new object of class '<em>Super</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Super</em>'.
   * @generated
   */
  Super createSuper();

  /**
   * Returns a new object of class '<em>This</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>This</em>'.
   * @generated
   */
  This createThis();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  LiteralsPackage getLiteralsPackage();

} //LiteralsFactory
