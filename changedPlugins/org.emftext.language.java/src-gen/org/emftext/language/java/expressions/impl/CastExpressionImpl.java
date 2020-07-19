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

import org.emftext.language.java.arrays.ArrayDimension;
import org.emftext.language.java.arrays.ArrayInitializationValue;
import org.emftext.language.java.arrays.ArrayTypeable;
import org.emftext.language.java.arrays.ArraysPackage;

import org.emftext.language.java.expressions.AdditiveExpressionChild;
import org.emftext.language.java.expressions.AndExpressionChild;
import org.emftext.language.java.expressions.AssignmentExpressionChild;
import org.emftext.language.java.expressions.CastExpression;
import org.emftext.language.java.expressions.ConditionalAndExpressionChild;
import org.emftext.language.java.expressions.ConditionalExpressionChild;
import org.emftext.language.java.expressions.ConditionalOrExpressionChild;
import org.emftext.language.java.expressions.EqualityExpressionChild;
import org.emftext.language.java.expressions.ExclusiveOrExpressionChild;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.InclusiveOrExpressionChild;
import org.emftext.language.java.expressions.InstanceOfExpressionChild;
import org.emftext.language.java.expressions.MultiplicativeExpressionChild;
import org.emftext.language.java.expressions.RelationExpressionChild;
import org.emftext.language.java.expressions.ShiftExpressionChild;
import org.emftext.language.java.expressions.UnaryExpressionChild;
import org.emftext.language.java.expressions.UnaryModificationExpressionChild;

import org.emftext.language.java.types.Type;

import org.emftext.language.java.types.impl.TypedElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cast Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.emftext.language.java.expressions.impl.CastExpressionImpl#getArrayDimensionsBefore <em>Array Dimensions Before</em>}</li>
 *   <li>{@link org.emftext.language.java.expressions.impl.CastExpressionImpl#getArrayDimensionsAfter <em>Array Dimensions After</em>}</li>
 *   <li>{@link org.emftext.language.java.expressions.impl.CastExpressionImpl#getChild <em>Child</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CastExpressionImpl extends TypedElementImpl implements CastExpression
{
  /**
   * The cached value of the '{@link #getArrayDimensionsBefore() <em>Array Dimensions Before</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArrayDimensionsBefore()
   * @generated
   * @ordered
   */
  protected EList<ArrayDimension> arrayDimensionsBefore;

  /**
   * The cached value of the '{@link #getArrayDimensionsAfter() <em>Array Dimensions After</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArrayDimensionsAfter()
   * @generated
   * @ordered
   */
  protected EList<ArrayDimension> arrayDimensionsAfter;

  /**
   * The cached value of the '{@link #getChild() <em>Child</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getChild()
   * @generated
   * @ordered
   */
  protected MultiplicativeExpressionChild child;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CastExpressionImpl()
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
    return ExpressionsPackage.Literals.CAST_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ArrayDimension> getArrayDimensionsBefore()
  {
    if (arrayDimensionsBefore == null)
    {
      arrayDimensionsBefore = new EObjectContainmentEList.Resolving<ArrayDimension>(ArrayDimension.class, this, ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_BEFORE);
    }
    return arrayDimensionsBefore;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ArrayDimension> getArrayDimensionsAfter()
  {
    if (arrayDimensionsAfter == null)
    {
      arrayDimensionsAfter = new EObjectContainmentEList.Resolving<ArrayDimension>(ArrayDimension.class, this, ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_AFTER);
    }
    return arrayDimensionsAfter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MultiplicativeExpressionChild getChild()
  {
    if (child != null && child.eIsProxy())
    {
      InternalEObject oldChild = (InternalEObject)child;
      child = (MultiplicativeExpressionChild)eResolveProxy(oldChild);
      if (child != oldChild)
      {
        InternalEObject newChild = (InternalEObject)child;
        NotificationChain msgs = oldChild.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.CAST_EXPRESSION__CHILD, null, null);
        if (newChild.eInternalContainer() == null)
        {
          msgs = newChild.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.CAST_EXPRESSION__CHILD, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExpressionsPackage.CAST_EXPRESSION__CHILD, oldChild, child));
      }
    }
    return child;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MultiplicativeExpressionChild basicGetChild()
  {
    return child;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetChild(MultiplicativeExpressionChild newChild, NotificationChain msgs)
  {
    MultiplicativeExpressionChild oldChild = child;
    child = newChild;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpressionsPackage.CAST_EXPRESSION__CHILD, oldChild, newChild);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setChild(MultiplicativeExpressionChild newChild)
  {
    if (newChild != child)
    {
      NotificationChain msgs = null;
      if (child != null)
        msgs = ((InternalEObject)child).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.CAST_EXPRESSION__CHILD, null, msgs);
      if (newChild != null)
        msgs = ((InternalEObject)newChild).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExpressionsPackage.CAST_EXPRESSION__CHILD, null, msgs);
      msgs = basicSetChild(newChild, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ExpressionsPackage.CAST_EXPRESSION__CHILD, newChild, newChild));
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
      case ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_BEFORE:
        return ((InternalEList<?>)getArrayDimensionsBefore()).basicRemove(otherEnd, msgs);
      case ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_AFTER:
        return ((InternalEList<?>)getArrayDimensionsAfter()).basicRemove(otherEnd, msgs);
      case ExpressionsPackage.CAST_EXPRESSION__CHILD:
        return basicSetChild(null, msgs);
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
      case ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_BEFORE:
        return getArrayDimensionsBefore();
      case ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_AFTER:
        return getArrayDimensionsAfter();
      case ExpressionsPackage.CAST_EXPRESSION__CHILD:
        if (resolve) return getChild();
        return basicGetChild();
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
      case ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_BEFORE:
        getArrayDimensionsBefore().clear();
        getArrayDimensionsBefore().addAll((Collection<? extends ArrayDimension>)newValue);
        return;
      case ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_AFTER:
        getArrayDimensionsAfter().clear();
        getArrayDimensionsAfter().addAll((Collection<? extends ArrayDimension>)newValue);
        return;
      case ExpressionsPackage.CAST_EXPRESSION__CHILD:
        setChild((MultiplicativeExpressionChild)newValue);
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
      case ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_BEFORE:
        getArrayDimensionsBefore().clear();
        return;
      case ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_AFTER:
        getArrayDimensionsAfter().clear();
        return;
      case ExpressionsPackage.CAST_EXPRESSION__CHILD:
        setChild((MultiplicativeExpressionChild)null);
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
      case ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_BEFORE:
        return arrayDimensionsBefore != null && !arrayDimensionsBefore.isEmpty();
      case ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_AFTER:
        return arrayDimensionsAfter != null && !arrayDimensionsAfter.isEmpty();
      case ExpressionsPackage.CAST_EXPRESSION__CHILD:
        return child != null;
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
    if (baseClass == ArrayTypeable.class)
    {
      switch (derivedFeatureID)
      {
        case ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_BEFORE: return ArraysPackage.ARRAY_TYPEABLE__ARRAY_DIMENSIONS_BEFORE;
        case ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_AFTER: return ArraysPackage.ARRAY_TYPEABLE__ARRAY_DIMENSIONS_AFTER;
        default: return -1;
      }
    }
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
    if (baseClass == ArrayTypeable.class)
    {
      switch (baseFeatureID)
      {
        case ArraysPackage.ARRAY_TYPEABLE__ARRAY_DIMENSIONS_BEFORE: return ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_BEFORE;
        case ArraysPackage.ARRAY_TYPEABLE__ARRAY_DIMENSIONS_AFTER: return ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_AFTER;
        default: return -1;
      }
    }
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
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }

} //CastExpressionImpl
