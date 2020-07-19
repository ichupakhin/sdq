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
package org.emftext.language.java.containers.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.emftext.language.java.classifiers.Annotation;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.classifiers.Interface;

import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Compilation Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.emftext.language.java.containers.impl.CompilationUnitImpl#getClassifiers <em>Classifiers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CompilationUnitImpl extends JavaRootImpl implements CompilationUnit
{
  /**
   * The cached value of the '{@link #getClassifiers() <em>Classifiers</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getClassifiers()
   * @generated
   * @ordered
   */
  protected EList<ConcreteClassifier> classifiers;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CompilationUnitImpl()
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
    return ContainersPackage.Literals.COMPILATION_UNIT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ConcreteClassifier> getClassifiers()
  {
    if (classifiers == null)
    {
      classifiers = new EObjectContainmentEList.Resolving<ConcreteClassifier>(ConcreteClassifier.class, this, ContainersPackage.COMPILATION_UNIT__CLASSIFIERS);
    }
    return classifiers;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConcreteClassifier getContainedClassifier(final String name)
  {
    return org.emftext.language.java.extensions.containers.CompilationUnitExtension.getContainedClassifier((org.emftext.language.java.containers.CompilationUnit) this, name);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ConcreteClassifier> getClassifiersInSamePackage()
  {
    return org.emftext.language.java.extensions.containers.CompilationUnitExtension.getClassifiersInSamePackage((org.emftext.language.java.containers.CompilationUnit) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public org.emftext.language.java.classifiers.Class getContainedClass()
  {
    return org.emftext.language.java.extensions.containers.CompilationUnitExtension.getContainedClass((org.emftext.language.java.containers.CompilationUnit) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Interface getContainedInterface()
  {
    return org.emftext.language.java.extensions.containers.CompilationUnitExtension.getContainedInterface((org.emftext.language.java.containers.CompilationUnit) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Annotation getContainedAnnotation()
  {
    return org.emftext.language.java.extensions.containers.CompilationUnitExtension.getContainedAnnotation((org.emftext.language.java.containers.CompilationUnit) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Enumeration getContainedEnumeration()
  {
    return org.emftext.language.java.extensions.containers.CompilationUnitExtension.getContainedEnumeration((org.emftext.language.java.containers.CompilationUnit) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void addImport(final String nameOfClassToImport)
  {
    org.emftext.language.java.extensions.containers.CompilationUnitExtension.addImport((org.emftext.language.java.containers.CompilationUnit) this, nameOfClassToImport);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void addPackageImport(final String packageName)
  {
    org.emftext.language.java.extensions.containers.CompilationUnitExtension.addPackageImport((org.emftext.language.java.containers.CompilationUnit) this, packageName);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case ContainersPackage.COMPILATION_UNIT__CLASSIFIERS:
        return ((InternalEList<?>)getClassifiers()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case ContainersPackage.COMPILATION_UNIT__CLASSIFIERS:
        return getClassifiers();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ContainersPackage.COMPILATION_UNIT__CLASSIFIERS:
        getClassifiers().clear();
        getClassifiers().addAll((Collection<? extends ConcreteClassifier>)newValue);
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
      case ContainersPackage.COMPILATION_UNIT__CLASSIFIERS:
        getClassifiers().clear();
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
      case ContainersPackage.COMPILATION_UNIT__CLASSIFIERS:
        return classifiers != null && !classifiers.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //CompilationUnitImpl
