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
package org.emftext.language.java.imports.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.emftext.language.java.classifiers.ConcreteClassifier;

import org.emftext.language.java.commons.NamedElement;

import org.emftext.language.java.commons.impl.NamespaceAwareElementImpl;

import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Import</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class ImportImpl extends NamespaceAwareElementImpl implements Import
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ImportImpl()
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
    return ImportsPackage.Literals.IMPORT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConcreteClassifier getImportedClassifier(final String name)
  {
    return org.emftext.language.java.extensions.imports.ImportExtension.getImportedClassifier((org.emftext.language.java.imports.Import) this, name);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ConcreteClassifier> getImportedClassifiers()
  {
    return org.emftext.language.java.extensions.imports.ImportExtension.getImportedClassifiers((org.emftext.language.java.imports.Import) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<NamedElement> getImportedMembers()
  {
    return org.emftext.language.java.extensions.imports.ImportExtension.getImportedMembers((org.emftext.language.java.imports.Import) this);
  }

} //ImportImpl
