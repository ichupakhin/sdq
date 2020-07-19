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
package org.emftext.language.java.expressions.impl;

import org.eclipse.emf.ecore.EClass;

import org.emftext.language.java.arrays.impl.ArrayInitializationValueImpl;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionsPackage;

import org.emftext.language.java.types.Type;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class ExpressionImpl extends ArrayInitializationValueImpl implements Expression
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ExpressionImpl()
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
    return ExpressionsPackage.Literals.EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Type getType()
  {
    return org.emftext.language.java.extensions.expressions.ExpressionExtension.getType((org.emftext.language.java.expressions.Expression) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Type getAlternativeType()
  {
    return org.emftext.language.java.extensions.expressions.ExpressionExtension.getAlternativeType((org.emftext.language.java.expressions.Expression) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Type getOneType(final boolean alternative)
  {
    return org.emftext.language.java.extensions.expressions.ExpressionExtension.getOneType((org.emftext.language.java.expressions.Expression) this, alternative);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public long getArrayDimension()
  {
    return org.emftext.language.java.extensions.expressions.ExpressionExtension.getArrayDimension((org.emftext.language.java.expressions.Expression) this);
  }

} //ExpressionImpl
