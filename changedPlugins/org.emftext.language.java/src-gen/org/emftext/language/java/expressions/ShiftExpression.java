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
package org.emftext.language.java.expressions;

import org.eclipse.emf.common.util.EList;

import org.emftext.language.java.operators.ShiftOperator;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Shift Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftext.language.java.expressions.ShiftExpression#getChildren <em>Children</em>}</li>
 *   <li>{@link org.emftext.language.java.expressions.ShiftExpression#getShiftOperators <em>Shift Operators</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftext.language.java.expressions.ExpressionsPackage#getShiftExpression()
 * @model
 * @generated
 */
public interface ShiftExpression extends RelationExpressionChild
{
  /**
   * Returns the value of the '<em><b>Children</b></em>' containment reference list.
   * The list contents are of type {@link org.emftext.language.java.expressions.ShiftExpressionChild}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Children</em>' containment reference list.
   * @see org.emftext.language.java.expressions.ExpressionsPackage#getShiftExpression_Children()
   * @model containment="true" resolveProxies="true" required="true"
   * @generated
   */
  EList<ShiftExpressionChild> getChildren();

  /**
   * Returns the value of the '<em><b>Shift Operators</b></em>' containment reference list.
   * The list contents are of type {@link org.emftext.language.java.operators.ShiftOperator}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Shift Operators</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Shift Operators</em>' containment reference list.
   * @see org.emftext.language.java.expressions.ExpressionsPackage#getShiftExpression_ShiftOperators()
   * @model containment="true" resolveProxies="true" required="true"
   * @generated
   */
  EList<ShiftOperator> getShiftOperators();

} // ShiftExpression
