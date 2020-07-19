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
package org.emftext.language.java.variables.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.emftext.language.java.annotations.AnnotationInstance;

import org.emftext.language.java.commons.Commentable;

import org.emftext.language.java.expressions.Expression;

import org.emftext.language.java.instantiations.Initializable;
import org.emftext.language.java.instantiations.InstantiationsPackage;

import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.modifiers.AnnotationInstanceOrModifier;
import org.emftext.language.java.modifiers.Modifier;
import org.emftext.language.java.modifiers.ModifiersPackage;

import org.emftext.language.java.statements.ForLoopInitializer;

import org.emftext.language.java.variables.AdditionalLocalVariable;
import org.emftext.language.java.variables.LocalVariable;
import org.emftext.language.java.variables.VariablesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Local Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.emftext.language.java.variables.impl.LocalVariableImpl#getInitialValue <em>Initial Value</em>}</li>
 *   <li>{@link org.emftext.language.java.variables.impl.LocalVariableImpl#getAnnotationsAndModifiers <em>Annotations And Modifiers</em>}</li>
 *   <li>{@link org.emftext.language.java.variables.impl.LocalVariableImpl#getAdditionalLocalVariables <em>Additional Local Variables</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LocalVariableImpl extends VariableImpl implements LocalVariable
{
  /**
   * The cached value of the '{@link #getInitialValue() <em>Initial Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInitialValue()
   * @generated
   * @ordered
   */
  protected Expression initialValue;

  /**
   * The cached value of the '{@link #getAnnotationsAndModifiers() <em>Annotations And Modifiers</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotationsAndModifiers()
   * @generated
   * @ordered
   */
  protected EList<AnnotationInstanceOrModifier> annotationsAndModifiers;

  /**
   * The cached value of the '{@link #getAdditionalLocalVariables() <em>Additional Local Variables</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAdditionalLocalVariables()
   * @generated
   * @ordered
   */
  protected EList<AdditionalLocalVariable> additionalLocalVariables;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LocalVariableImpl()
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
    return VariablesPackage.Literals.LOCAL_VARIABLE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getInitialValue()
  {
    if (initialValue != null && initialValue.eIsProxy())
    {
      InternalEObject oldInitialValue = (InternalEObject)initialValue;
      initialValue = (Expression)eResolveProxy(oldInitialValue);
      if (initialValue != oldInitialValue)
      {
        InternalEObject newInitialValue = (InternalEObject)initialValue;
        NotificationChain msgs = oldInitialValue.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - VariablesPackage.LOCAL_VARIABLE__INITIAL_VALUE, null, null);
        if (newInitialValue.eInternalContainer() == null)
        {
          msgs = newInitialValue.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - VariablesPackage.LOCAL_VARIABLE__INITIAL_VALUE, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, VariablesPackage.LOCAL_VARIABLE__INITIAL_VALUE, oldInitialValue, initialValue));
      }
    }
    return initialValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression basicGetInitialValue()
  {
    return initialValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetInitialValue(Expression newInitialValue, NotificationChain msgs)
  {
    Expression oldInitialValue = initialValue;
    initialValue = newInitialValue;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, VariablesPackage.LOCAL_VARIABLE__INITIAL_VALUE, oldInitialValue, newInitialValue);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInitialValue(Expression newInitialValue)
  {
    if (newInitialValue != initialValue)
    {
      NotificationChain msgs = null;
      if (initialValue != null)
        msgs = ((InternalEObject)initialValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - VariablesPackage.LOCAL_VARIABLE__INITIAL_VALUE, null, msgs);
      if (newInitialValue != null)
        msgs = ((InternalEObject)newInitialValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - VariablesPackage.LOCAL_VARIABLE__INITIAL_VALUE, null, msgs);
      msgs = basicSetInitialValue(newInitialValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, VariablesPackage.LOCAL_VARIABLE__INITIAL_VALUE, newInitialValue, newInitialValue));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AnnotationInstanceOrModifier> getAnnotationsAndModifiers()
  {
    if (annotationsAndModifiers == null)
    {
      annotationsAndModifiers = new EObjectContainmentEList.Resolving<AnnotationInstanceOrModifier>(AnnotationInstanceOrModifier.class, this, VariablesPackage.LOCAL_VARIABLE__ANNOTATIONS_AND_MODIFIERS);
    }
    return annotationsAndModifiers;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AdditionalLocalVariable> getAdditionalLocalVariables()
  {
    if (additionalLocalVariables == null)
    {
      additionalLocalVariables = new EObjectContainmentEList.Resolving<AdditionalLocalVariable>(AdditionalLocalVariable.class, this, VariablesPackage.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES);
    }
    return additionalLocalVariables;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isHidden(final Commentable context)
  {
    return org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.isHidden((org.emftext.language.java.modifiers.AnnotableAndModifiable) this, (org.emftext.language.java.commons.Commentable) context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isStatic()
  {
    return org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.isStatic((org.emftext.language.java.modifiers.AnnotableAndModifiable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void removeModifier(final Class<?> modifierType)
  {
    org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.removeModifier((org.emftext.language.java.modifiers.AnnotableAndModifiable) this, modifierType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void makePublic()
  {
    org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.makePublic((org.emftext.language.java.modifiers.AnnotableAndModifiable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void makePrivate()
  {
    org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.makePrivate((org.emftext.language.java.modifiers.AnnotableAndModifiable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void makeProtected()
  {
    org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.makeProtected((org.emftext.language.java.modifiers.AnnotableAndModifiable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Modifier> getModifiers()
  {
    return org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.getModifiers((org.emftext.language.java.modifiers.AnnotableAndModifiable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void removeAllModifiers()
  {
    org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.removeAllModifiers((org.emftext.language.java.modifiers.AnnotableAndModifiable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean hasModifier(final Class<?> type)
  {
    return org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.hasModifier((org.emftext.language.java.modifiers.AnnotableAndModifiable) this, type);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isPublic()
  {
    return org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.isPublic((org.emftext.language.java.modifiers.AnnotableAndModifiable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isPrivate()
  {
    return org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.isPrivate((org.emftext.language.java.modifiers.AnnotableAndModifiable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isProtected()
  {
    return org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.isProtected((org.emftext.language.java.modifiers.AnnotableAndModifiable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void addModifier(final Modifier newModifier)
  {
    org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.addModifier((org.emftext.language.java.modifiers.AnnotableAndModifiable) this, (org.emftext.language.java.modifiers.Modifier) newModifier);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AnnotationInstance> getAnnotationInstances()
  {
    return org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.getAnnotationInstances((org.emftext.language.java.modifiers.AnnotableAndModifiable) this);
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
      case VariablesPackage.LOCAL_VARIABLE__INITIAL_VALUE:
        return basicSetInitialValue(null, msgs);
      case VariablesPackage.LOCAL_VARIABLE__ANNOTATIONS_AND_MODIFIERS:
        return ((InternalEList<?>)getAnnotationsAndModifiers()).basicRemove(otherEnd, msgs);
      case VariablesPackage.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES:
        return ((InternalEList<?>)getAdditionalLocalVariables()).basicRemove(otherEnd, msgs);
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
      case VariablesPackage.LOCAL_VARIABLE__INITIAL_VALUE:
        if (resolve) return getInitialValue();
        return basicGetInitialValue();
      case VariablesPackage.LOCAL_VARIABLE__ANNOTATIONS_AND_MODIFIERS:
        return getAnnotationsAndModifiers();
      case VariablesPackage.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES:
        return getAdditionalLocalVariables();
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
      case VariablesPackage.LOCAL_VARIABLE__INITIAL_VALUE:
        setInitialValue((Expression)newValue);
        return;
      case VariablesPackage.LOCAL_VARIABLE__ANNOTATIONS_AND_MODIFIERS:
        getAnnotationsAndModifiers().clear();
        getAnnotationsAndModifiers().addAll((Collection<? extends AnnotationInstanceOrModifier>)newValue);
        return;
      case VariablesPackage.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES:
        getAdditionalLocalVariables().clear();
        getAdditionalLocalVariables().addAll((Collection<? extends AdditionalLocalVariable>)newValue);
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
      case VariablesPackage.LOCAL_VARIABLE__INITIAL_VALUE:
        setInitialValue((Expression)null);
        return;
      case VariablesPackage.LOCAL_VARIABLE__ANNOTATIONS_AND_MODIFIERS:
        getAnnotationsAndModifiers().clear();
        return;
      case VariablesPackage.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES:
        getAdditionalLocalVariables().clear();
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
      case VariablesPackage.LOCAL_VARIABLE__INITIAL_VALUE:
        return initialValue != null;
      case VariablesPackage.LOCAL_VARIABLE__ANNOTATIONS_AND_MODIFIERS:
        return annotationsAndModifiers != null && !annotationsAndModifiers.isEmpty();
      case VariablesPackage.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES:
        return additionalLocalVariables != null && !additionalLocalVariables.isEmpty();
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
    if (baseClass == Initializable.class)
    {
      switch (derivedFeatureID)
      {
        case VariablesPackage.LOCAL_VARIABLE__INITIAL_VALUE: return InstantiationsPackage.INITIALIZABLE__INITIAL_VALUE;
        default: return -1;
      }
    }
    if (baseClass == ForLoopInitializer.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == AnnotableAndModifiable.class)
    {
      switch (derivedFeatureID)
      {
        case VariablesPackage.LOCAL_VARIABLE__ANNOTATIONS_AND_MODIFIERS: return ModifiersPackage.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS;
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
    if (baseClass == Initializable.class)
    {
      switch (baseFeatureID)
      {
        case InstantiationsPackage.INITIALIZABLE__INITIAL_VALUE: return VariablesPackage.LOCAL_VARIABLE__INITIAL_VALUE;
        default: return -1;
      }
    }
    if (baseClass == ForLoopInitializer.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == AnnotableAndModifiable.class)
    {
      switch (baseFeatureID)
      {
        case ModifiersPackage.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS: return VariablesPackage.LOCAL_VARIABLE__ANNOTATIONS_AND_MODIFIERS;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }

} //LocalVariableImpl
