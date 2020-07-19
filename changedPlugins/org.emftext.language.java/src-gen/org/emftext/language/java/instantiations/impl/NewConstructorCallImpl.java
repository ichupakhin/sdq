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
package org.emftext.language.java.instantiations.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.emftext.language.java.annotations.AnnotationValue;

import org.emftext.language.java.arrays.ArrayInitializationValue;
import org.emftext.language.java.arrays.ArraySelector;

import org.emftext.language.java.classifiers.AnonymousClass;

import org.emftext.language.java.expressions.AdditiveExpressionChild;
import org.emftext.language.java.expressions.AndExpressionChild;
import org.emftext.language.java.expressions.AssignmentExpressionChild;
import org.emftext.language.java.expressions.ConditionalAndExpressionChild;
import org.emftext.language.java.expressions.ConditionalExpressionChild;
import org.emftext.language.java.expressions.ConditionalOrExpressionChild;
import org.emftext.language.java.expressions.EqualityExpressionChild;
import org.emftext.language.java.expressions.ExclusiveOrExpressionChild;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.InclusiveOrExpressionChild;
import org.emftext.language.java.expressions.InstanceOfExpressionChild;
import org.emftext.language.java.expressions.MultiplicativeExpressionChild;
import org.emftext.language.java.expressions.PrimaryExpression;
import org.emftext.language.java.expressions.RelationExpressionChild;
import org.emftext.language.java.expressions.ShiftExpressionChild;
import org.emftext.language.java.expressions.UnaryExpressionChild;
import org.emftext.language.java.expressions.UnaryModificationExpressionChild;

import org.emftext.language.java.generics.CallTypeArgumentable;
import org.emftext.language.java.generics.GenericsPackage;
import org.emftext.language.java.generics.TypeArgument;
import org.emftext.language.java.generics.TypeArgumentable;

import org.emftext.language.java.instantiations.Instantiation;
import org.emftext.language.java.instantiations.InstantiationsPackage;
import org.emftext.language.java.instantiations.NewConstructorCall;

import org.emftext.language.java.references.Argumentable;
import org.emftext.language.java.references.Reference;
import org.emftext.language.java.references.ReferencesPackage;

import org.emftext.language.java.types.Type;

import org.emftext.language.java.types.impl.TypedElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>New Constructor Call</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.emftext.language.java.instantiations.impl.NewConstructorCallImpl#getTypeArguments <em>Type Arguments</em>}</li>
 *   <li>{@link org.emftext.language.java.instantiations.impl.NewConstructorCallImpl#getNext <em>Next</em>}</li>
 *   <li>{@link org.emftext.language.java.instantiations.impl.NewConstructorCallImpl#getArraySelectors <em>Array Selectors</em>}</li>
 *   <li>{@link org.emftext.language.java.instantiations.impl.NewConstructorCallImpl#getArguments <em>Arguments</em>}</li>
 *   <li>{@link org.emftext.language.java.instantiations.impl.NewConstructorCallImpl#getCallTypeArguments <em>Call Type Arguments</em>}</li>
 *   <li>{@link org.emftext.language.java.instantiations.impl.NewConstructorCallImpl#getAnonymousClass <em>Anonymous Class</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NewConstructorCallImpl extends TypedElementImpl implements NewConstructorCall
{
  /**
   * The cached value of the '{@link #getTypeArguments() <em>Type Arguments</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeArguments()
   * @generated
   * @ordered
   */
  protected EList<TypeArgument> typeArguments;

  /**
   * The cached value of the '{@link #getNext() <em>Next</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNext()
   * @generated
   * @ordered
   */
  protected Reference next;

  /**
   * The cached value of the '{@link #getArraySelectors() <em>Array Selectors</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArraySelectors()
   * @generated
   * @ordered
   */
  protected EList<ArraySelector> arraySelectors;

  /**
   * The cached value of the '{@link #getArguments() <em>Arguments</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArguments()
   * @generated
   * @ordered
   */
  protected EList<Expression> arguments;

  /**
   * The cached value of the '{@link #getCallTypeArguments() <em>Call Type Arguments</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCallTypeArguments()
   * @generated
   * @ordered
   */
  protected EList<TypeArgument> callTypeArguments;

  /**
   * The cached value of the '{@link #getAnonymousClass() <em>Anonymous Class</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnonymousClass()
   * @generated
   * @ordered
   */
  protected AnonymousClass anonymousClass;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NewConstructorCallImpl()
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
    return InstantiationsPackage.Literals.NEW_CONSTRUCTOR_CALL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<TypeArgument> getTypeArguments()
  {
    if (typeArguments == null)
    {
      typeArguments = new EObjectContainmentEList.Resolving<TypeArgument>(TypeArgument.class, this, InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_ARGUMENTS);
    }
    return typeArguments;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Reference getNext()
  {
    if (next != null && next.eIsProxy())
    {
      InternalEObject oldNext = (InternalEObject)next;
      next = (Reference)eResolveProxy(oldNext);
      if (next != oldNext)
      {
        InternalEObject newNext = (InternalEObject)next;
        NotificationChain msgs = oldNext.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InstantiationsPackage.NEW_CONSTRUCTOR_CALL__NEXT, null, null);
        if (newNext.eInternalContainer() == null)
        {
          msgs = newNext.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InstantiationsPackage.NEW_CONSTRUCTOR_CALL__NEXT, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InstantiationsPackage.NEW_CONSTRUCTOR_CALL__NEXT, oldNext, next));
      }
    }
    return next;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Reference basicGetNext()
  {
    return next;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetNext(Reference newNext, NotificationChain msgs)
  {
    Reference oldNext = next;
    next = newNext;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InstantiationsPackage.NEW_CONSTRUCTOR_CALL__NEXT, oldNext, newNext);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNext(Reference newNext)
  {
    if (newNext != next)
    {
      NotificationChain msgs = null;
      if (next != null)
        msgs = ((InternalEObject)next).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InstantiationsPackage.NEW_CONSTRUCTOR_CALL__NEXT, null, msgs);
      if (newNext != null)
        msgs = ((InternalEObject)newNext).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InstantiationsPackage.NEW_CONSTRUCTOR_CALL__NEXT, null, msgs);
      msgs = basicSetNext(newNext, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InstantiationsPackage.NEW_CONSTRUCTOR_CALL__NEXT, newNext, newNext));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ArraySelector> getArraySelectors()
  {
    if (arraySelectors == null)
    {
      arraySelectors = new EObjectContainmentEList.Resolving<ArraySelector>(ArraySelector.class, this, InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARRAY_SELECTORS);
    }
    return arraySelectors;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Expression> getArguments()
  {
    if (arguments == null)
    {
      arguments = new EObjectContainmentEList.Resolving<Expression>(Expression.class, this, InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARGUMENTS);
    }
    return arguments;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<TypeArgument> getCallTypeArguments()
  {
    if (callTypeArguments == null)
    {
      callTypeArguments = new EObjectContainmentEList.Resolving<TypeArgument>(TypeArgument.class, this, InstantiationsPackage.NEW_CONSTRUCTOR_CALL__CALL_TYPE_ARGUMENTS);
    }
    return callTypeArguments;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnonymousClass getAnonymousClass()
  {
    if (anonymousClass != null && anonymousClass.eIsProxy())
    {
      InternalEObject oldAnonymousClass = (InternalEObject)anonymousClass;
      anonymousClass = (AnonymousClass)eResolveProxy(oldAnonymousClass);
      if (anonymousClass != oldAnonymousClass)
      {
        InternalEObject newAnonymousClass = (InternalEObject)anonymousClass;
        NotificationChain msgs = oldAnonymousClass.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ANONYMOUS_CLASS, null, null);
        if (newAnonymousClass.eInternalContainer() == null)
        {
          msgs = newAnonymousClass.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ANONYMOUS_CLASS, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ANONYMOUS_CLASS, oldAnonymousClass, anonymousClass));
      }
    }
    return anonymousClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnonymousClass basicGetAnonymousClass()
  {
    return anonymousClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAnonymousClass(AnonymousClass newAnonymousClass, NotificationChain msgs)
  {
    AnonymousClass oldAnonymousClass = anonymousClass;
    anonymousClass = newAnonymousClass;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ANONYMOUS_CLASS, oldAnonymousClass, newAnonymousClass);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnonymousClass(AnonymousClass newAnonymousClass)
  {
    if (newAnonymousClass != anonymousClass)
    {
      NotificationChain msgs = null;
      if (anonymousClass != null)
        msgs = ((InternalEObject)anonymousClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ANONYMOUS_CLASS, null, msgs);
      if (newAnonymousClass != null)
        msgs = ((InternalEObject)newAnonymousClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ANONYMOUS_CLASS, null, msgs);
      msgs = basicSetAnonymousClass(newAnonymousClass, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ANONYMOUS_CLASS, newAnonymousClass, newAnonymousClass));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Type> getArgumentTypes()
  {
    return org.emftext.language.java.extensions.references.ArgumentableExtension.getArgumentTypes((org.emftext.language.java.references.Argumentable) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Type getReferencedType()
  {
    return org.emftext.language.java.extensions.references.ReferenceExtension.getReferencedType((org.emftext.language.java.references.Reference) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Reference getPrevious()
  {
    return org.emftext.language.java.extensions.references.ReferenceExtension.getPrevious((org.emftext.language.java.references.Reference) this);
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
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_ARGUMENTS:
        return ((InternalEList<?>)getTypeArguments()).basicRemove(otherEnd, msgs);
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__NEXT:
        return basicSetNext(null, msgs);
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARRAY_SELECTORS:
        return ((InternalEList<?>)getArraySelectors()).basicRemove(otherEnd, msgs);
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARGUMENTS:
        return ((InternalEList<?>)getArguments()).basicRemove(otherEnd, msgs);
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__CALL_TYPE_ARGUMENTS:
        return ((InternalEList<?>)getCallTypeArguments()).basicRemove(otherEnd, msgs);
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ANONYMOUS_CLASS:
        return basicSetAnonymousClass(null, msgs);
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
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_ARGUMENTS:
        return getTypeArguments();
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__NEXT:
        if (resolve) return getNext();
        return basicGetNext();
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARRAY_SELECTORS:
        return getArraySelectors();
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARGUMENTS:
        return getArguments();
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__CALL_TYPE_ARGUMENTS:
        return getCallTypeArguments();
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ANONYMOUS_CLASS:
        if (resolve) return getAnonymousClass();
        return basicGetAnonymousClass();
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
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_ARGUMENTS:
        getTypeArguments().clear();
        getTypeArguments().addAll((Collection<? extends TypeArgument>)newValue);
        return;
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__NEXT:
        setNext((Reference)newValue);
        return;
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARRAY_SELECTORS:
        getArraySelectors().clear();
        getArraySelectors().addAll((Collection<? extends ArraySelector>)newValue);
        return;
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARGUMENTS:
        getArguments().clear();
        getArguments().addAll((Collection<? extends Expression>)newValue);
        return;
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__CALL_TYPE_ARGUMENTS:
        getCallTypeArguments().clear();
        getCallTypeArguments().addAll((Collection<? extends TypeArgument>)newValue);
        return;
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ANONYMOUS_CLASS:
        setAnonymousClass((AnonymousClass)newValue);
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
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_ARGUMENTS:
        getTypeArguments().clear();
        return;
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__NEXT:
        setNext((Reference)null);
        return;
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARRAY_SELECTORS:
        getArraySelectors().clear();
        return;
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARGUMENTS:
        getArguments().clear();
        return;
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__CALL_TYPE_ARGUMENTS:
        getCallTypeArguments().clear();
        return;
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ANONYMOUS_CLASS:
        setAnonymousClass((AnonymousClass)null);
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
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_ARGUMENTS:
        return typeArguments != null && !typeArguments.isEmpty();
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__NEXT:
        return next != null;
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARRAY_SELECTORS:
        return arraySelectors != null && !arraySelectors.isEmpty();
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARGUMENTS:
        return arguments != null && !arguments.isEmpty();
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__CALL_TYPE_ARGUMENTS:
        return callTypeArguments != null && !callTypeArguments.isEmpty();
      case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ANONYMOUS_CLASS:
        return anonymousClass != null;
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
    if (baseClass == ArrayInitializationValue.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == AnnotationValue.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == Expression.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == AssignmentExpressionChild.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == ConditionalExpressionChild.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == ConditionalOrExpressionChild.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == ConditionalAndExpressionChild.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == InclusiveOrExpressionChild.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == ExclusiveOrExpressionChild.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == AndExpressionChild.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == EqualityExpressionChild.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == InstanceOfExpressionChild.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == RelationExpressionChild.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == ShiftExpressionChild.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == AdditiveExpressionChild.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == MultiplicativeExpressionChild.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == UnaryExpressionChild.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == UnaryModificationExpressionChild.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == PrimaryExpression.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == TypeArgumentable.class)
    {
      switch (derivedFeatureID)
      {
        case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_ARGUMENTS: return GenericsPackage.TYPE_ARGUMENTABLE__TYPE_ARGUMENTS;
        default: return -1;
      }
    }
    if (baseClass == Reference.class)
    {
      switch (derivedFeatureID)
      {
        case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__NEXT: return ReferencesPackage.REFERENCE__NEXT;
        case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARRAY_SELECTORS: return ReferencesPackage.REFERENCE__ARRAY_SELECTORS;
        default: return -1;
      }
    }
    if (baseClass == Argumentable.class)
    {
      switch (derivedFeatureID)
      {
        case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARGUMENTS: return ReferencesPackage.ARGUMENTABLE__ARGUMENTS;
        default: return -1;
      }
    }
    if (baseClass == Instantiation.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == CallTypeArgumentable.class)
    {
      switch (derivedFeatureID)
      {
        case InstantiationsPackage.NEW_CONSTRUCTOR_CALL__CALL_TYPE_ARGUMENTS: return GenericsPackage.CALL_TYPE_ARGUMENTABLE__CALL_TYPE_ARGUMENTS;
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
    if (baseClass == ArrayInitializationValue.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == AnnotationValue.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == Expression.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == AssignmentExpressionChild.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == ConditionalExpressionChild.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == ConditionalOrExpressionChild.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == ConditionalAndExpressionChild.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == InclusiveOrExpressionChild.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == ExclusiveOrExpressionChild.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == AndExpressionChild.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == EqualityExpressionChild.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == InstanceOfExpressionChild.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == RelationExpressionChild.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == ShiftExpressionChild.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == AdditiveExpressionChild.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == MultiplicativeExpressionChild.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == UnaryExpressionChild.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == UnaryModificationExpressionChild.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == PrimaryExpression.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == TypeArgumentable.class)
    {
      switch (baseFeatureID)
      {
        case GenericsPackage.TYPE_ARGUMENTABLE__TYPE_ARGUMENTS: return InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_ARGUMENTS;
        default: return -1;
      }
    }
    if (baseClass == Reference.class)
    {
      switch (baseFeatureID)
      {
        case ReferencesPackage.REFERENCE__NEXT: return InstantiationsPackage.NEW_CONSTRUCTOR_CALL__NEXT;
        case ReferencesPackage.REFERENCE__ARRAY_SELECTORS: return InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARRAY_SELECTORS;
        default: return -1;
      }
    }
    if (baseClass == Argumentable.class)
    {
      switch (baseFeatureID)
      {
        case ReferencesPackage.ARGUMENTABLE__ARGUMENTS: return InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARGUMENTS;
        default: return -1;
      }
    }
    if (baseClass == Instantiation.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == CallTypeArgumentable.class)
    {
      switch (baseFeatureID)
      {
        case GenericsPackage.CALL_TYPE_ARGUMENTABLE__CALL_TYPE_ARGUMENTS: return InstantiationsPackage.NEW_CONSTRUCTOR_CALL__CALL_TYPE_ARGUMENTS;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }

} //NewConstructorCallImpl
