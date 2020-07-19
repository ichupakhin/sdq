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
package org.emftext.language.java.classifiers.impl;

import java.lang.Class;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.emftext.language.java.annotations.AnnotationInstance;

import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.classifiers.ConcreteClassifier;

import org.emftext.language.java.commons.Commentable;

import org.emftext.language.java.generics.GenericsPackage;
import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.generics.TypeParametrizable;

import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.members.MembersPackage;
import org.emftext.language.java.members.Method;

import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.modifiers.AnnotationInstanceOrModifier;
import org.emftext.language.java.modifiers.Modifier;
import org.emftext.language.java.modifiers.ModifiersPackage;

import org.emftext.language.java.statements.Statement;

import org.emftext.language.java.types.ClassifierReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Concrete Classifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.emftext.language.java.classifiers.impl.ConcreteClassifierImpl#getTypeParameters <em>Type Parameters</em>}</li>
 *   <li>{@link org.emftext.language.java.classifiers.impl.ConcreteClassifierImpl#getMembers <em>Members</em>}</li>
 *   <li>{@link org.emftext.language.java.classifiers.impl.ConcreteClassifierImpl#getDefaultMembers <em>Default Members</em>}</li>
 *   <li>{@link org.emftext.language.java.classifiers.impl.ConcreteClassifierImpl#getAnnotationsAndModifiers <em>Annotations And Modifiers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ConcreteClassifierImpl extends ClassifierImpl implements ConcreteClassifier
{
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
   * The cached value of the '{@link #getMembers() <em>Members</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMembers()
   * @generated
   * @ordered
   */
  protected EList<Member> members;

  /**
   * The cached value of the '{@link #getDefaultMembers() <em>Default Members</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDefaultMembers()
   * @generated
   * @ordered
   */
  protected EList<Member> defaultMembers;

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
  protected ConcreteClassifierImpl()
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
    return ClassifiersPackage.Literals.CONCRETE_CLASSIFIER;
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
      typeParameters = new EObjectContainmentEList.Resolving<TypeParameter>(TypeParameter.class, this, ClassifiersPackage.CONCRETE_CLASSIFIER__TYPE_PARAMETERS);
    }
    return typeParameters;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Member> getMembers()
  {
    if (members == null)
    {
      members = new EObjectContainmentEList.Resolving<Member>(Member.class, this, ClassifiersPackage.CONCRETE_CLASSIFIER__MEMBERS);
    }
    return members;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Member> getDefaultMembers()
  {
    if (defaultMembers == null)
    {
      defaultMembers = new EObjectContainmentEList.Resolving<Member>(Member.class, this, ClassifiersPackage.CONCRETE_CLASSIFIER__DEFAULT_MEMBERS);
    }
    return defaultMembers;
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
      annotationsAndModifiers = new EObjectContainmentEList.Resolving<AnnotationInstanceOrModifier>(AnnotationInstanceOrModifier.class, this, ClassifiersPackage.CONCRETE_CLASSIFIER__ANNOTATIONS_AND_MODIFIERS);
    }
    return annotationsAndModifiers;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ConcreteClassifier> getInnerClassifiers()
  {
    return org.emftext.language.java.extensions.classifiers.ConcreteClassifierExtension.getInnerClassifiers((org.emftext.language.java.classifiers.ConcreteClassifier) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ConcreteClassifier> getAllInnerClassifiers()
  {
    return org.emftext.language.java.extensions.classifiers.ConcreteClassifierExtension.getAllInnerClassifiers((org.emftext.language.java.classifiers.ConcreteClassifier) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ClassifierReference> getSuperTypeReferences()
  {
    return org.emftext.language.java.extensions.classifiers.ConcreteClassifierExtension.getSuperTypeReferences((org.emftext.language.java.classifiers.ConcreteClassifier) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Member> getAllMembers(final Commentable context)
  {
    return org.emftext.language.java.extensions.classifiers.ConcreteClassifierExtension.getAllMembers((org.emftext.language.java.classifiers.ConcreteClassifier) this, (org.emftext.language.java.commons.Commentable) context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getQualifiedName()
  {
    return org.emftext.language.java.extensions.classifiers.ConcreteClassifierExtension.getQualifiedName((org.emftext.language.java.classifiers.ConcreteClassifier) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isJavaLangObject(final ConcreteClassifier clazz)
  {
    return org.emftext.language.java.extensions.classifiers.ConcreteClassifierExtension.isJavaLangObject((org.emftext.language.java.classifiers.ConcreteClassifier) clazz);
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
  public ConcreteClassifier getContainedClassifier(final String name)
  {
    return org.emftext.language.java.extensions.members.MemberContainerExtension.getContainedClassifier((org.emftext.language.java.members.MemberContainer) this, name);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Field getContainedField(final String name)
  {
    return org.emftext.language.java.extensions.members.MemberContainerExtension.getContainedField((org.emftext.language.java.members.MemberContainer) this, name);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Method getContainedMethod(final String name)
  {
    return org.emftext.language.java.extensions.members.MemberContainerExtension.getContainedMethod((org.emftext.language.java.members.MemberContainer) this, name);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Method> getMethods()
  {
    return org.emftext.language.java.extensions.members.MemberContainerExtension.getMethods((org.emftext.language.java.members.MemberContainer) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void removeMethods(final String name)
  {
    org.emftext.language.java.extensions.members.MemberContainerExtension.removeMethods((org.emftext.language.java.members.MemberContainer) this, name);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Member> getMembersByName(final String name)
  {
    return org.emftext.language.java.extensions.members.MemberContainerExtension.getMembersByName((org.emftext.language.java.members.MemberContainer) this, name);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Field createField(final String name, final String qualifiedTypeName)
  {
    return org.emftext.language.java.extensions.members.MemberContainerExtension.createField((org.emftext.language.java.members.MemberContainer) this, name, qualifiedTypeName);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Field> getFields()
  {
    return org.emftext.language.java.extensions.members.MemberContainerExtension.getFields((org.emftext.language.java.members.MemberContainer) this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Constructor> getConstructors()
  {
    return org.emftext.language.java.extensions.members.MemberContainerExtension.getConstructors((org.emftext.language.java.members.MemberContainer) this);
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
      case ClassifiersPackage.CONCRETE_CLASSIFIER__TYPE_PARAMETERS:
        return ((InternalEList<?>)getTypeParameters()).basicRemove(otherEnd, msgs);
      case ClassifiersPackage.CONCRETE_CLASSIFIER__MEMBERS:
        return ((InternalEList<?>)getMembers()).basicRemove(otherEnd, msgs);
      case ClassifiersPackage.CONCRETE_CLASSIFIER__DEFAULT_MEMBERS:
        return ((InternalEList<?>)getDefaultMembers()).basicRemove(otherEnd, msgs);
      case ClassifiersPackage.CONCRETE_CLASSIFIER__ANNOTATIONS_AND_MODIFIERS:
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
      case ClassifiersPackage.CONCRETE_CLASSIFIER__TYPE_PARAMETERS:
        return getTypeParameters();
      case ClassifiersPackage.CONCRETE_CLASSIFIER__MEMBERS:
        return getMembers();
      case ClassifiersPackage.CONCRETE_CLASSIFIER__DEFAULT_MEMBERS:
        return getDefaultMembers();
      case ClassifiersPackage.CONCRETE_CLASSIFIER__ANNOTATIONS_AND_MODIFIERS:
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
      case ClassifiersPackage.CONCRETE_CLASSIFIER__TYPE_PARAMETERS:
        getTypeParameters().clear();
        getTypeParameters().addAll((Collection<? extends TypeParameter>)newValue);
        return;
      case ClassifiersPackage.CONCRETE_CLASSIFIER__MEMBERS:
        getMembers().clear();
        getMembers().addAll((Collection<? extends Member>)newValue);
        return;
      case ClassifiersPackage.CONCRETE_CLASSIFIER__DEFAULT_MEMBERS:
        getDefaultMembers().clear();
        getDefaultMembers().addAll((Collection<? extends Member>)newValue);
        return;
      case ClassifiersPackage.CONCRETE_CLASSIFIER__ANNOTATIONS_AND_MODIFIERS:
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
      case ClassifiersPackage.CONCRETE_CLASSIFIER__TYPE_PARAMETERS:
        getTypeParameters().clear();
        return;
      case ClassifiersPackage.CONCRETE_CLASSIFIER__MEMBERS:
        getMembers().clear();
        return;
      case ClassifiersPackage.CONCRETE_CLASSIFIER__DEFAULT_MEMBERS:
        getDefaultMembers().clear();
        return;
      case ClassifiersPackage.CONCRETE_CLASSIFIER__ANNOTATIONS_AND_MODIFIERS:
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
      case ClassifiersPackage.CONCRETE_CLASSIFIER__TYPE_PARAMETERS:
        return typeParameters != null && !typeParameters.isEmpty();
      case ClassifiersPackage.CONCRETE_CLASSIFIER__MEMBERS:
        return members != null && !members.isEmpty();
      case ClassifiersPackage.CONCRETE_CLASSIFIER__DEFAULT_MEMBERS:
        return defaultMembers != null && !defaultMembers.isEmpty();
      case ClassifiersPackage.CONCRETE_CLASSIFIER__ANNOTATIONS_AND_MODIFIERS:
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
    if (baseClass == TypeParametrizable.class)
    {
      switch (derivedFeatureID)
      {
        case ClassifiersPackage.CONCRETE_CLASSIFIER__TYPE_PARAMETERS: return GenericsPackage.TYPE_PARAMETRIZABLE__TYPE_PARAMETERS;
        default: return -1;
      }
    }
    if (baseClass == MemberContainer.class)
    {
      switch (derivedFeatureID)
      {
        case ClassifiersPackage.CONCRETE_CLASSIFIER__MEMBERS: return MembersPackage.MEMBER_CONTAINER__MEMBERS;
        case ClassifiersPackage.CONCRETE_CLASSIFIER__DEFAULT_MEMBERS: return MembersPackage.MEMBER_CONTAINER__DEFAULT_MEMBERS;
        default: return -1;
      }
    }
    if (baseClass == Member.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == Statement.class)
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
        case ClassifiersPackage.CONCRETE_CLASSIFIER__ANNOTATIONS_AND_MODIFIERS: return ModifiersPackage.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS;
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
    if (baseClass == TypeParametrizable.class)
    {
      switch (baseFeatureID)
      {
        case GenericsPackage.TYPE_PARAMETRIZABLE__TYPE_PARAMETERS: return ClassifiersPackage.CONCRETE_CLASSIFIER__TYPE_PARAMETERS;
        default: return -1;
      }
    }
    if (baseClass == MemberContainer.class)
    {
      switch (baseFeatureID)
      {
        case MembersPackage.MEMBER_CONTAINER__MEMBERS: return ClassifiersPackage.CONCRETE_CLASSIFIER__MEMBERS;
        case MembersPackage.MEMBER_CONTAINER__DEFAULT_MEMBERS: return ClassifiersPackage.CONCRETE_CLASSIFIER__DEFAULT_MEMBERS;
        default: return -1;
      }
    }
    if (baseClass == Member.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == Statement.class)
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
        case ModifiersPackage.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS: return ClassifiersPackage.CONCRETE_CLASSIFIER__ANNOTATIONS_AND_MODIFIERS;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }

} //ConcreteClassifierImpl
