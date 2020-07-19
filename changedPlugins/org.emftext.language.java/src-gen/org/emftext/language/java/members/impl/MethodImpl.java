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
package org.emftext.language.java.members.impl;

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

import org.emftext.language.java.arrays.ArrayDimension;
import org.emftext.language.java.arrays.ArrayTypeable;
import org.emftext.language.java.arrays.ArraysPackage;

import org.emftext.language.java.commons.Commentable;

import org.emftext.language.java.generics.GenericsPackage;
import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.generics.TypeParametrizable;

import org.emftext.language.java.members.ExceptionThrower;
import org.emftext.language.java.members.MembersPackage;
import org.emftext.language.java.members.Method;

import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.modifiers.AnnotationInstanceOrModifier;
import org.emftext.language.java.modifiers.Modifier;
import org.emftext.language.java.modifiers.ModifiersPackage;

import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.parameters.ParametersPackage;
import org.emftext.language.java.parameters.Parametrizable;

import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.ReferenceableElement;

import org.emftext.language.java.types.NamespaceClassifierReference;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElement;
import org.emftext.language.java.types.TypesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.emftext.language.java.members.impl.MethodImpl#getTypeReference <em>Type Reference</em>}</li>
 *   <li>{@link org.emftext.language.java.members.impl.MethodImpl#getArrayDimensionsBefore <em>Array Dimensions Before</em>}</li>
 *   <li>{@link org.emftext.language.java.members.impl.MethodImpl#getArrayDimensionsAfter <em>Array Dimensions After</em>}</li>
 *   <li>{@link org.emftext.language.java.members.impl.MethodImpl#getTypeParameters <em>Type Parameters</em>}</li>
 *   <li>{@link org.emftext.language.java.members.impl.MethodImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.emftext.language.java.members.impl.MethodImpl#getExceptions <em>Exceptions</em>}</li>
 *   <li>{@link org.emftext.language.java.members.impl.MethodImpl#getAnnotationsAndModifiers <em>Annotations And Modifiers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class MethodImpl extends MemberImpl implements Method
{
  /**
   * The cached value of the '{@link #getTypeReference() <em>Type Reference</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeReference()
   * @generated
   * @ordered
   */
  protected TypeReference typeReference;

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
   * The cached value of the '{@link #getTypeParameters() <em>Type Parameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeParameters()
   * @generated
   * @ordered
   */
  protected EList<TypeParameter> typeParameters;

  /**
   * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameters()
   * @generated
   * @ordered
   */
  protected EList<Parameter> parameters;

  /**
   * The cached value of the '{@link #getExceptions() <em>Exceptions</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExceptions()
   * @generated
   * @ordered
   */
  protected EList<NamespaceClassifierReference> exceptions;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MethodImpl()
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
    return MembersPackage.Literals.METHOD;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypeReference getTypeReference()
  {
    if (typeReference != null && typeReference.eIsProxy())
    {
      InternalEObject oldTypeReference = (InternalEObject)typeReference;
      typeReference = (TypeReference)eResolveProxy(oldTypeReference);
      if (typeReference != oldTypeReference)
      {
        InternalEObject newTypeReference = (InternalEObject)typeReference;
        NotificationChain msgs = oldTypeReference.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MembersPackage.METHOD__TYPE_REFERENCE, null, null);
        if (newTypeReference.eInternalContainer() == null)
        {
          msgs = newTypeReference.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MembersPackage.METHOD__TYPE_REFERENCE, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, MembersPackage.METHOD__TYPE_REFERENCE, oldTypeReference, typeReference));
      }
    }
    return typeReference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypeReference basicGetTypeReference()
  {
    return typeReference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTypeReference(TypeReference newTypeReference, NotificationChain msgs)
  {
    TypeReference oldTypeReference = typeReference;
    typeReference = newTypeReference;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MembersPackage.METHOD__TYPE_REFERENCE, oldTypeReference, newTypeReference);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTypeReference(TypeReference newTypeReference)
  {
    if (newTypeReference != typeReference)
    {
      NotificationChain msgs = null;
      if (typeReference != null)
        msgs = ((InternalEObject)typeReference).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MembersPackage.METHOD__TYPE_REFERENCE, null, msgs);
      if (newTypeReference != null)
        msgs = ((InternalEObject)newTypeReference).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MembersPackage.METHOD__TYPE_REFERENCE, null, msgs);
      msgs = basicSetTypeReference(newTypeReference, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MembersPackage.METHOD__TYPE_REFERENCE, newTypeReference, newTypeReference));
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
      arrayDimensionsBefore = new EObjectContainmentEList.Resolving<ArrayDimension>(ArrayDimension.class, this, MembersPackage.METHOD__ARRAY_DIMENSIONS_BEFORE);
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
      arrayDimensionsAfter = new EObjectContainmentEList.Resolving<ArrayDimension>(ArrayDimension.class, this, MembersPackage.METHOD__ARRAY_DIMENSIONS_AFTER);
    }
    return arrayDimensionsAfter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<TypeParameter> getTypeParameters()
  {
    if (typeParameters == null)
    {
      typeParameters = new EObjectContainmentEList.Resolving<TypeParameter>(TypeParameter.class, this, MembersPackage.METHOD__TYPE_PARAMETERS);
    }
    return typeParameters;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Parameter> getParameters()
  {
    if (parameters == null)
    {
      parameters = new EObjectContainmentEList.Resolving<Parameter>(Parameter.class, this, MembersPackage.METHOD__PARAMETERS);
    }
    return parameters;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<NamespaceClassifierReference> getExceptions()
  {
    if (exceptions == null)
    {
      exceptions = new EObjectContainmentEList.Resolving<NamespaceClassifierReference>(NamespaceClassifierReference.class, this, MembersPackage.METHOD__EXCEPTIONS);
    }
    return exceptions;
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
      annotationsAndModifiers = new EObjectContainmentEList.Resolving<AnnotationInstanceOrModifier>(AnnotationInstanceOrModifier.class, this, MembersPackage.METHOD__ANNOTATIONS_AND_MODIFIERS);
    }
    return annotationsAndModifiers;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isMethodForCall(final MethodCall methodCall, final boolean needsPerfectMatch)
  {
    return org.emftext.language.java.extensions.members.MethodExtension.isMethodForCall((org.emftext.language.java.members.Method) this, (org.emftext.language.java.references.MethodCall) methodCall, needsPerfectMatch);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSomeMethodForCall(final MethodCall methodCall)
  {
    return org.emftext.language.java.extensions.members.MethodExtension.isSomeMethodForCall((org.emftext.language.java.members.Method) this, (org.emftext.language.java.references.MethodCall) methodCall);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isBetterMethodForCall(final Method otherMethod, final MethodCall methodCall)
  {
    return org.emftext.language.java.extensions.members.MethodExtension.isBetterMethodForCall((org.emftext.language.java.members.Method) this, (org.emftext.language.java.members.Method) otherMethod, (org.emftext.language.java.references.MethodCall) methodCall);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public long getArrayDimension()
  {
    return org.emftext.language.java.extensions.members.MethodExtension.getArrayDimension((org.emftext.language.java.members.Method) this);
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
      case MembersPackage.METHOD__TYPE_REFERENCE:
        return basicSetTypeReference(null, msgs);
      case MembersPackage.METHOD__ARRAY_DIMENSIONS_BEFORE:
        return ((InternalEList<?>)getArrayDimensionsBefore()).basicRemove(otherEnd, msgs);
      case MembersPackage.METHOD__ARRAY_DIMENSIONS_AFTER:
        return ((InternalEList<?>)getArrayDimensionsAfter()).basicRemove(otherEnd, msgs);
      case MembersPackage.METHOD__TYPE_PARAMETERS:
        return ((InternalEList<?>)getTypeParameters()).basicRemove(otherEnd, msgs);
      case MembersPackage.METHOD__PARAMETERS:
        return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
      case MembersPackage.METHOD__EXCEPTIONS:
        return ((InternalEList<?>)getExceptions()).basicRemove(otherEnd, msgs);
      case MembersPackage.METHOD__ANNOTATIONS_AND_MODIFIERS:
        return ((InternalEList<?>)getAnnotationsAndModifiers()).basicRemove(otherEnd, msgs);
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
      case MembersPackage.METHOD__TYPE_REFERENCE:
        if (resolve) return getTypeReference();
        return basicGetTypeReference();
      case MembersPackage.METHOD__ARRAY_DIMENSIONS_BEFORE:
        return getArrayDimensionsBefore();
      case MembersPackage.METHOD__ARRAY_DIMENSIONS_AFTER:
        return getArrayDimensionsAfter();
      case MembersPackage.METHOD__TYPE_PARAMETERS:
        return getTypeParameters();
      case MembersPackage.METHOD__PARAMETERS:
        return getParameters();
      case MembersPackage.METHOD__EXCEPTIONS:
        return getExceptions();
      case MembersPackage.METHOD__ANNOTATIONS_AND_MODIFIERS:
        return getAnnotationsAndModifiers();
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
      case MembersPackage.METHOD__TYPE_REFERENCE:
        setTypeReference((TypeReference)newValue);
        return;
      case MembersPackage.METHOD__ARRAY_DIMENSIONS_BEFORE:
        getArrayDimensionsBefore().clear();
        getArrayDimensionsBefore().addAll((Collection<? extends ArrayDimension>)newValue);
        return;
      case MembersPackage.METHOD__ARRAY_DIMENSIONS_AFTER:
        getArrayDimensionsAfter().clear();
        getArrayDimensionsAfter().addAll((Collection<? extends ArrayDimension>)newValue);
        return;
      case MembersPackage.METHOD__TYPE_PARAMETERS:
        getTypeParameters().clear();
        getTypeParameters().addAll((Collection<? extends TypeParameter>)newValue);
        return;
      case MembersPackage.METHOD__PARAMETERS:
        getParameters().clear();
        getParameters().addAll((Collection<? extends Parameter>)newValue);
        return;
      case MembersPackage.METHOD__EXCEPTIONS:
        getExceptions().clear();
        getExceptions().addAll((Collection<? extends NamespaceClassifierReference>)newValue);
        return;
      case MembersPackage.METHOD__ANNOTATIONS_AND_MODIFIERS:
        getAnnotationsAndModifiers().clear();
        getAnnotationsAndModifiers().addAll((Collection<? extends AnnotationInstanceOrModifier>)newValue);
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
      case MembersPackage.METHOD__TYPE_REFERENCE:
        setTypeReference((TypeReference)null);
        return;
      case MembersPackage.METHOD__ARRAY_DIMENSIONS_BEFORE:
        getArrayDimensionsBefore().clear();
        return;
      case MembersPackage.METHOD__ARRAY_DIMENSIONS_AFTER:
        getArrayDimensionsAfter().clear();
        return;
      case MembersPackage.METHOD__TYPE_PARAMETERS:
        getTypeParameters().clear();
        return;
      case MembersPackage.METHOD__PARAMETERS:
        getParameters().clear();
        return;
      case MembersPackage.METHOD__EXCEPTIONS:
        getExceptions().clear();
        return;
      case MembersPackage.METHOD__ANNOTATIONS_AND_MODIFIERS:
        getAnnotationsAndModifiers().clear();
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
      case MembersPackage.METHOD__TYPE_REFERENCE:
        return typeReference != null;
      case MembersPackage.METHOD__ARRAY_DIMENSIONS_BEFORE:
        return arrayDimensionsBefore != null && !arrayDimensionsBefore.isEmpty();
      case MembersPackage.METHOD__ARRAY_DIMENSIONS_AFTER:
        return arrayDimensionsAfter != null && !arrayDimensionsAfter.isEmpty();
      case MembersPackage.METHOD__TYPE_PARAMETERS:
        return typeParameters != null && !typeParameters.isEmpty();
      case MembersPackage.METHOD__PARAMETERS:
        return parameters != null && !parameters.isEmpty();
      case MembersPackage.METHOD__EXCEPTIONS:
        return exceptions != null && !exceptions.isEmpty();
      case MembersPackage.METHOD__ANNOTATIONS_AND_MODIFIERS:
        return annotationsAndModifiers != null && !annotationsAndModifiers.isEmpty();
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
    if (baseClass == TypedElement.class)
    {
      switch (derivedFeatureID)
      {
        case MembersPackage.METHOD__TYPE_REFERENCE: return TypesPackage.TYPED_ELEMENT__TYPE_REFERENCE;
        default: return -1;
      }
    }
    if (baseClass == ArrayTypeable.class)
    {
      switch (derivedFeatureID)
      {
        case MembersPackage.METHOD__ARRAY_DIMENSIONS_BEFORE: return ArraysPackage.ARRAY_TYPEABLE__ARRAY_DIMENSIONS_BEFORE;
        case MembersPackage.METHOD__ARRAY_DIMENSIONS_AFTER: return ArraysPackage.ARRAY_TYPEABLE__ARRAY_DIMENSIONS_AFTER;
        default: return -1;
      }
    }
    if (baseClass == TypeParametrizable.class)
    {
      switch (derivedFeatureID)
      {
        case MembersPackage.METHOD__TYPE_PARAMETERS: return GenericsPackage.TYPE_PARAMETRIZABLE__TYPE_PARAMETERS;
        default: return -1;
      }
    }
    if (baseClass == Parametrizable.class)
    {
      switch (derivedFeatureID)
      {
        case MembersPackage.METHOD__PARAMETERS: return ParametersPackage.PARAMETRIZABLE__PARAMETERS;
        default: return -1;
      }
    }
    if (baseClass == ReferenceableElement.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == ExceptionThrower.class)
    {
      switch (derivedFeatureID)
      {
        case MembersPackage.METHOD__EXCEPTIONS: return MembersPackage.EXCEPTION_THROWER__EXCEPTIONS;
        default: return -1;
      }
    }
    if (baseClass == AnnotableAndModifiable.class)
    {
      switch (derivedFeatureID)
      {
        case MembersPackage.METHOD__ANNOTATIONS_AND_MODIFIERS: return ModifiersPackage.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS;
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
    if (baseClass == TypedElement.class)
    {
      switch (baseFeatureID)
      {
        case TypesPackage.TYPED_ELEMENT__TYPE_REFERENCE: return MembersPackage.METHOD__TYPE_REFERENCE;
        default: return -1;
      }
    }
    if (baseClass == ArrayTypeable.class)
    {
      switch (baseFeatureID)
      {
        case ArraysPackage.ARRAY_TYPEABLE__ARRAY_DIMENSIONS_BEFORE: return MembersPackage.METHOD__ARRAY_DIMENSIONS_BEFORE;
        case ArraysPackage.ARRAY_TYPEABLE__ARRAY_DIMENSIONS_AFTER: return MembersPackage.METHOD__ARRAY_DIMENSIONS_AFTER;
        default: return -1;
      }
    }
    if (baseClass == TypeParametrizable.class)
    {
      switch (baseFeatureID)
      {
        case GenericsPackage.TYPE_PARAMETRIZABLE__TYPE_PARAMETERS: return MembersPackage.METHOD__TYPE_PARAMETERS;
        default: return -1;
      }
    }
    if (baseClass == Parametrizable.class)
    {
      switch (baseFeatureID)
      {
        case ParametersPackage.PARAMETRIZABLE__PARAMETERS: return MembersPackage.METHOD__PARAMETERS;
        default: return -1;
      }
    }
    if (baseClass == ReferenceableElement.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == ExceptionThrower.class)
    {
      switch (baseFeatureID)
      {
        case MembersPackage.EXCEPTION_THROWER__EXCEPTIONS: return MembersPackage.METHOD__EXCEPTIONS;
        default: return -1;
      }
    }
    if (baseClass == AnnotableAndModifiable.class)
    {
      switch (baseFeatureID)
      {
        case ModifiersPackage.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS: return MembersPackage.METHOD__ANNOTATIONS_AND_MODIFIERS;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }

} //MethodImpl
