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
package org.emftext.language.java.statements;

import org.emftext.language.java.parameters.OrdinaryParameter;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Catch Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftext.language.java.statements.CatchBlock#getParameter <em>Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftext.language.java.statements.StatementsPackage#getCatchBlock()
 * @model
 * @generated
 */
public interface CatchBlock extends StatementListContainer
{
  /**
   * Returns the value of the '<em><b>Parameter</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameter</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameter</em>' containment reference.
   * @see #setParameter(OrdinaryParameter)
   * @see org.emftext.language.java.statements.StatementsPackage#getCatchBlock_Parameter()
   * @model containment="true" resolveProxies="true" required="true"
   * @generated
   */
  OrdinaryParameter getParameter();

  /**
   * Sets the value of the '{@link org.emftext.language.java.statements.CatchBlock#getParameter <em>Parameter</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parameter</em>' containment reference.
   * @see #getParameter()
   * @generated
   */
  void setParameter(OrdinaryParameter value);

} // CatchBlock
