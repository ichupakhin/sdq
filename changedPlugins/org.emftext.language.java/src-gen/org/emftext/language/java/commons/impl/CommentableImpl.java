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
package org.emftext.language.java.commons.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.emftext.commons.layout.LayoutInformation;

import org.emftext.language.java.annotations.AnnotationInstance;

import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Interface;

import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.commons.CommonsPackage;

import org.emftext.language.java.containers.CompilationUnit;

import org.emftext.language.java.statements.Statement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Commentable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.emftext.language.java.commons.impl.CommentableImpl#getLayoutInformations <em>Layout Informations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class CommentableImpl extends MinimalEObjectImpl implements Commentable
{
  /**
   * The cached value of the '{@link #getLayoutInformations() <em>Layout Informations</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLayoutInformations()
   * @generated
   * @ordered
   */
  protected EList<LayoutInformation> layoutInformations;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CommentableImpl()
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
    return CommonsPackage.Literals.COMMENTABLE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<LayoutInformation> getLayoutInformations()
  {
    if (layoutInformations == null)
    {
      layoutInformations = new EObjectContainmentEList.Resolving<LayoutInformation>(LayoutInformation.class, this, CommonsPackage.COMMENTABLE__LAYOUT_INFORMATIONS);
    }
    return layoutInformations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConcreteClassifier getConcreteClassifier(final String name)
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getConcreteClassifier((org.emftext.language.java.commons.Commentable) this, name);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConcreteClassifier getConcreteClassifierProxy(final String name)
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getConcreteClassifierProxy((org.emftext.language.java.commons.Commentable) this, name);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ConcreteClassifier> getConcreteClassifiers(final String packageName, final String classifierQuery)
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getConcreteClassifiers((org.emftext.language.java.commons.Commentable) this, packageName, classifierQuery);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ConcreteClassifier> getConcreteClassifierProxies(final String packageName, final String classifierQuery)
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getConcreteClassifierProxies((org.emftext.language.java.commons.Commentable) this, packageName, classifierQuery);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public org.emftext.language.java.classifiers.Class getLibClass(final String name)
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getLibClass((org.emftext.language.java.commons.Commentable) this, name);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Interface getLibInterface(final String name)
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getLibInterface((org.emftext.language.java.commons.Commentable) this, name);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public org.emftext.language.java.classifiers.Class getClassClass()
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getClassClass((org.emftext.language.java.commons.Commentable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public org.emftext.language.java.classifiers.Class getObjectClass()
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getObjectClass((org.emftext.language.java.commons.Commentable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public org.emftext.language.java.classifiers.Class getStringClass()
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getStringClass((org.emftext.language.java.commons.Commentable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Interface getAnnotationInterface()
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getAnnotationInterface((org.emftext.language.java.commons.Commentable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnnotationInstance getContainingAnnotationInstance()
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getContainingAnnotationInstance((org.emftext.language.java.commons.Commentable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnonymousClass getContainingAnonymousClass()
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getContainingAnonymousClass((org.emftext.language.java.commons.Commentable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConcreteClassifier getContainingConcreteClassifier()
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getContainingConcreteClassifier((org.emftext.language.java.commons.Commentable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompilationUnit getContainingCompilationUnit()
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getContainingCompilationUnit((org.emftext.language.java.commons.Commentable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getContainingPackageName()
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getContainingPackageName((org.emftext.language.java.commons.Commentable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getContainingContainerName()
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getContainingContainerName((org.emftext.language.java.commons.Commentable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConcreteClassifier getParentConcreteClassifier()
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getParentConcreteClassifier((org.emftext.language.java.commons.Commentable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getParentByEType(final EClass type)
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getParentByEType((org.emftext.language.java.commons.Commentable) this, (org.eclipse.emf.ecore.EClass) type);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getFirstChildByEType(final EClass type)
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getFirstChildByEType((org.emftext.language.java.commons.Commentable) this, (org.eclipse.emf.ecore.EClass) type);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public <T> T getParentByType(final Class<T> type)
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getParentByType((org.emftext.language.java.commons.Commentable) this, type);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public <T> T getFirstChildByType(final Class<T> type)
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getFirstChildByType((org.emftext.language.java.commons.Commentable) this, ( java.lang.Class<T>) type);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EObject> getChildrenByEType(final EClass type)
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getChildrenByEType((org.emftext.language.java.commons.Commentable) this, (org.eclipse.emf.ecore.EClass) type);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public <T> EList<T> getChildrenByType(final Class<T> type)
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getChildrenByType((org.emftext.language.java.commons.Commentable) this, ( java.lang.Class<T>) type);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getComments()
  {
    return org.emftext.language.java.extensions.commons.CommentableExtension.getComments((org.emftext.language.java.commons.Commentable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void addBeforeContainingStatement(final Statement statementToAdd)
  {
    org.emftext.language.java.extensions.commons.CommentableExtension.addBeforeContainingStatement((org.emftext.language.java.commons.Commentable) this, (org.emftext.language.java.statements.Statement) statementToAdd);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void addAfterContainingStatement(final Statement statementToAdd)
  {
    org.emftext.language.java.extensions.commons.CommentableExtension.addAfterContainingStatement((org.emftext.language.java.commons.Commentable) this, (org.emftext.language.java.statements.Statement) statementToAdd);
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
      case CommonsPackage.COMMENTABLE__LAYOUT_INFORMATIONS:
        return ((InternalEList<?>)getLayoutInformations()).basicRemove(otherEnd, msgs);
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
      case CommonsPackage.COMMENTABLE__LAYOUT_INFORMATIONS:
        return getLayoutInformations();
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
      case CommonsPackage.COMMENTABLE__LAYOUT_INFORMATIONS:
        getLayoutInformations().clear();
        getLayoutInformations().addAll((Collection<? extends LayoutInformation>)newValue);
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
      case CommonsPackage.COMMENTABLE__LAYOUT_INFORMATIONS:
        getLayoutInformations().clear();
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
      case CommonsPackage.COMMENTABLE__LAYOUT_INFORMATIONS:
        return layoutInformations != null && !layoutInformations.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //CommentableImpl
