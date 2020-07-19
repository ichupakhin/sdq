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

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.emftext.language.java.classifiers.ConcreteClassifier;

import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.commons.NamespaceAwareElement;

import org.emftext.language.java.commons.impl.NamedElementImpl;

import org.emftext.language.java.containers.ContainersPackage;
import org.emftext.language.java.containers.JavaRoot;

import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportingElement;
import org.emftext.language.java.imports.ImportsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.emftext.language.java.containers.impl.JavaRootImpl#getNamespaces <em>Namespaces</em>}</li>
 *   <li>{@link org.emftext.language.java.containers.impl.JavaRootImpl#getImports <em>Imports</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class JavaRootImpl extends NamedElementImpl implements JavaRoot
{
  /**
   * The cached value of the '{@link #getNamespaces() <em>Namespaces</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNamespaces()
   * @generated
   * @ordered
   */
  protected EList<String> namespaces;

  /**
   * The cached value of the '{@link #getImports() <em>Imports</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImports()
   * @generated
   * @ordered
   */
  protected EList<Import> imports;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected JavaRootImpl()
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
    return ContainersPackage.Literals.JAVA_ROOT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getNamespaces()
  {
    if (namespaces == null)
    {
      namespaces = new EDataTypeEList<String>(String.class, this, ContainersPackage.JAVA_ROOT__NAMESPACES);
    }
    return namespaces;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Import> getImports()
  {
    if (imports == null)
    {
      imports = new EObjectContainmentEList.Resolving<Import>(Import.class, this, ContainersPackage.JAVA_ROOT__IMPORTS);
    }
    return imports;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ConcreteClassifier> getClassifiersInSamePackage()
  {
    return org.emftext.language.java.extensions.containers.JavaRootExtension.getClassifiersInSamePackage((org.emftext.language.java.containers.JavaRoot) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ConcreteClassifier> getDefaultImports()
  {
    return org.emftext.language.java.extensions.imports.ImportingElementExtension.getDefaultImports((org.emftext.language.java.imports.ImportingElement) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNamespacesAsString()
  {
    return org.emftext.language.java.extensions.commons.NamespaceAwareElementExtension.getNamespacesAsString((org.emftext.language.java.commons.NamespaceAwareElement) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConcreteClassifier getClassifierAtNamespaces()
  {
    return org.emftext.language.java.extensions.commons.NamespaceAwareElementExtension.getClassifierAtNamespaces((org.emftext.language.java.commons.NamespaceAwareElement) this);
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
      case ContainersPackage.JAVA_ROOT__IMPORTS:
        return ((InternalEList<?>)getImports()).basicRemove(otherEnd, msgs);
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
      case ContainersPackage.JAVA_ROOT__NAMESPACES:
        return getNamespaces();
      case ContainersPackage.JAVA_ROOT__IMPORTS:
        return getImports();
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
      case ContainersPackage.JAVA_ROOT__NAMESPACES:
        getNamespaces().clear();
        getNamespaces().addAll((Collection<? extends String>)newValue);
        return;
      case ContainersPackage.JAVA_ROOT__IMPORTS:
        getImports().clear();
        getImports().addAll((Collection<? extends Import>)newValue);
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
      case ContainersPackage.JAVA_ROOT__NAMESPACES:
        getNamespaces().clear();
        return;
      case ContainersPackage.JAVA_ROOT__IMPORTS:
        getImports().clear();
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
      case ContainersPackage.JAVA_ROOT__NAMESPACES:
        return namespaces != null && !namespaces.isEmpty();
      case ContainersPackage.JAVA_ROOT__IMPORTS:
        return imports != null && !imports.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
  {
    if (baseClass == NamespaceAwareElement.class)
    {
      switch (derivedFeatureID)
      {
        case ContainersPackage.JAVA_ROOT__NAMESPACES: return CommonsPackage.NAMESPACE_AWARE_ELEMENT__NAMESPACES;
        default: return -1;
      }
    }
    if (baseClass == ImportingElement.class)
    {
      switch (derivedFeatureID)
      {
        case ContainersPackage.JAVA_ROOT__IMPORTS: return ImportsPackage.IMPORTING_ELEMENT__IMPORTS;
        default: return -1;
      }
    }
    return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
  {
    if (baseClass == NamespaceAwareElement.class)
    {
      switch (baseFeatureID)
      {
        case CommonsPackage.NAMESPACE_AWARE_ELEMENT__NAMESPACES: return ContainersPackage.JAVA_ROOT__NAMESPACES;
        default: return -1;
      }
    }
    if (baseClass == ImportingElement.class)
    {
      switch (baseFeatureID)
      {
        case ImportsPackage.IMPORTING_ELEMENT__IMPORTS: return ContainersPackage.JAVA_ROOT__IMPORTS;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
    result.append(" (namespaces: ");
    result.append(namespaces);
    result.append(')');
    return result.toString();
  }

} //JavaRootImpl
