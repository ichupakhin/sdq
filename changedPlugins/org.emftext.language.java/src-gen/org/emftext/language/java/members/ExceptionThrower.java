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

import org.eclipse.emf.common.util.EList;

import org.emftext.language.java.commons.Commentable;

import org.emftext.language.java.types.NamespaceClassifierReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exception Thrower</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftext.language.java.members.ExceptionThrower#getExceptions <em>Exceptions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftext.language.java.members.MembersPackage#getExceptionThrower()
 * @model abstract="true"
 * @generated
 */
public interface ExceptionThrower extends Commentable
{
  /**
   * Returns the value of the '<em><b>Exceptions</b></em>' containment reference list.
   * The list contents are of type {@link org.emftext.language.java.types.NamespaceClassifierReference}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Exceptions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Exceptions</em>' containment reference list.
   * @see org.emftext.language.java.members.MembersPackage#getExceptionThrower_Exceptions()
   * @model containment="true" resolveProxies="true"
   * @generated
   */
  EList<NamespaceClassifierReference> getExceptions();

} // ExceptionThrower
