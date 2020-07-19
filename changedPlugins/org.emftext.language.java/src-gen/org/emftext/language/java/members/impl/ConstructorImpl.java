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

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.emftext.language.java.annotations.AnnotationInstance;

import org.emftext.language.java.commons.Commentable;

import org.emftext.language.java.generics.GenericsPackage;
import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.generics.TypeParametrizable;

import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.ExceptionThrower;
import org.emftext.language.java.members.MembersPackage;

import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.modifiers.AnnotationInstanceOrModifier;
import org.emftext.language.java.modifiers.Modifier;
import org.emftext.language.java.modifiers.ModifiersPackage;

import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.parameters.ParametersPackage;
import org.emftext.language.java.parameters.Parametrizable;

import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.statements.StatementsPackage;

import org.emftext.language.java.types.NamespaceClassifierReference;

import org.emftext.language.java.variables.LocalVariable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constructor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.emftext.language.java.members.impl.ConstructorImpl#getStatements <em>Statements</em>}</li>
 *   <li>{@link org.emftext.language.java.members.impl.ConstructorImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.emftext.language.java.members.impl.ConstructorImpl#getTypeParameters <em>Type Parameters</em>}</li>
 *   <li>{@link org.emftext.language.java.members.impl.ConstructorImpl#getExceptions <em>Exceptions</em>}</li>
 *   <li>{@link org.emftext.language.java.members.impl.ConstructorImpl#getAnnotationsAndModifiers <em>Annotations And Modifiers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConstructorImpl extends MemberImpl implements Constructor
{
  /**
   * The cached value of the '{@link #getStatements() <em>Statements</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStatements()
   * @generated
   * @ordered
   */
  protected EList<Statement> statements;

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
   * The cached value of the '{@link #getTypeParameters() <em>Type Parameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeParameters()
   * @generated
   * @ordered
   */
  protected EList<TypeParameter> typeParameters;

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
  protected ConstructorImpl()
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
    return MembersPackage.Literals.CONSTRUCTOR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Statement> getStatements()
  {
    if (statements == null)
    {
      statements = new EObjectContainmentEList.Resolving<Statement>(Statement.class, this, MembersPackage.CONSTRUCTOR__STATEMENTS);
    }
    return statements;
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
      parameters = new EObjectContainmentEList.Resolving<Parameter>(Parameter.class, this, MembersPackage.CONSTRUCTOR__PARAMETERS);
    }
    return parameters;
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
      typeParameters = new EObjectContainmentEList.Resolving<TypeParameter>(TypeParameter.class, this, MembersPackage.CONSTRUCTOR__TYPE_PARAMETERS);
    }
    return typeParameters;
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
      exceptions = new EObjectContainmentEList.Resolving<NamespaceClassifierReference>(NamespaceClassifierReference.class, this, MembersPackage.CONSTRUCTOR__EXCEPTIONS);
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
      annotationsAndModifiers = new EObjectContainmentEList.Resolving<AnnotationInstanceOrModifier>(AnnotationInstanceOrModifier.class, this, MembersPackage.CONSTRUCTOR__ANNOTATIONS_AND_MODIFIERS);
    }
    return annotationsAndModifiers;
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
  public LocalVariable getLocalVariable(final String name)
  {
    return org.emftext.language.java.extensions.statements.StatementListContainerExtension.getLocalVariable((org.emftext.language.java.statements.StatementListContainer) this, name);
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
      case MembersPackage.CONSTRUCTOR__STATEMENTS:
        return ((InternalEList<?>)getStatements()).basicRemove(otherEnd, msgs);
      case MembersPackage.CONSTRUCTOR__PARAMETERS:
        return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
      case MembersPackage.CONSTRUCTOR__TYPE_PARAMETERS:
        return ((InternalEList<?>)getTypeParameters()).basicRemove(otherEnd, msgs);
      case MembersPackage.CONSTRUCTOR__EXCEPTIONS:
        return ((InternalEList<?>)getExceptions()).basicRemove(otherEnd, msgs);
      case MembersPackage.CONSTRUCTOR__ANNOTATIONS_AND_MODIFIERS:
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
      case MembersPackage.CONSTRUCTOR__STATEMENTS:
        return getStatements();
      case MembersPackage.CONSTRUCTOR__PARAMETERS:
        return getParameters();
      case MembersPackage.CONSTRUCTOR__TYPE_PARAMETERS:
        return getTypeParameters();
      case MembersPackage.CONSTRUCTOR__EXCEPTIONS:
        return getExceptions();
      case MembersPackage.CONSTRUCTOR__ANNOTATIONS_AND_MODIFIERS:
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
      case MembersPackage.CONSTRUCTOR__STATEMENTS:
        getStatements().clear();
        getStatements().addAll((Collection<? extends Statement>)newValue);
        return;
      case MembersPackage.CONSTRUCTOR__PARAMETERS:
        getParameters().clear();
        getParameters().addAll((Collection<? extends Parameter>)newValue);
        return;
      case MembersPackage.CONSTRUCTOR__TYPE_PARAMETERS:
        getTypeParameters().clear();
        getTypeParameters().addAll((Collection<? extends TypeParameter>)newValue);
        return;
      case MembersPackage.CONSTRUCTOR__EXCEPTIONS:
        getExceptions().clear();
        getExceptions().addAll((Collection<? extends NamespaceClassifierReference>)newValue);
        return;
      case MembersPackage.CONSTRUCTOR__ANNOTATIONS_AND_MODIFIERS:
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
      case MembersPackage.CONSTRUCTOR__STATEMENTS:
        getStatements().clear();
        return;
      case MembersPackage.CONSTRUCTOR__PARAMETERS:
        getParameters().clear();
        return;
      case MembersPackage.CONSTRUCTOR__TYPE_PARAMETERS:
        getTypeParameters().clear();
        return;
      case MembersPackage.CONSTRUCTOR__EXCEPTIONS:
        getExceptions().clear();
        return;
      case MembersPackage.CONSTRUCTOR__ANNOTATIONS_AND_MODIFIERS:
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
      case MembersPackage.CONSTRUCTOR__STATEMENTS:
        return statements != null && !statements.isEmpty();
      case MembersPackage.CONSTRUCTOR__PARAMETERS:
        return parameters != null && !parameters.isEmpty();
      case MembersPackage.CONSTRUCTOR__TYPE_PARAMETERS:
        return typeParameters != null && !typeParameters.isEmpty();
      case MembersPackage.CONSTRUCTOR__EXCEPTIONS:
        return exceptions != null && !exceptions.isEmpty();
      case MembersPackage.CONSTRUCTOR__ANNOTATIONS_AND_MODIFIERS:
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
    if (baseClass == StatementListContainer.class)
    {
      switch (derivedFeatureID)
      {
        case MembersPackage.CONSTRUCTOR__STATEMENTS: return StatementsPackage.STATEMENT_LIST_CONTAINER__STATEMENTS;
        default: return -1;
      }
    }
    if (baseClass == Parametrizable.class)
    {
      switch (derivedFeatureID)
      {
        case MembersPackage.CONSTRUCTOR__PARAMETERS: return ParametersPackage.PARAMETRIZABLE__PARAMETERS;
        default: return -1;
      }
    }
    if (baseClass == TypeParametrizable.class)
    {
      switch (derivedFeatureID)
      {
        case MembersPackage.CONSTRUCTOR__TYPE_PARAMETERS: return GenericsPackage.TYPE_PARAMETRIZABLE__TYPE_PARAMETERS;
        default: return -1;
      }
    }
    if (baseClass == ExceptionThrower.class)
    {
      switch (derivedFeatureID)
      {
        case MembersPackage.CONSTRUCTOR__EXCEPTIONS: return MembersPackage.EXCEPTION_THROWER__EXCEPTIONS;
        default: return -1;
      }
    }
    if (baseClass == AnnotableAndModifiable.class)
    {
      switch (derivedFeatureID)
      {
        case MembersPackage.CONSTRUCTOR__ANNOTATIONS_AND_MODIFIERS: return ModifiersPackage.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS;
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
    if (baseClass == StatementListContainer.class)
    {
      switch (baseFeatureID)
      {
        case StatementsPackage.STATEMENT_LIST_CONTAINER__STATEMENTS: return MembersPackage.CONSTRUCTOR__STATEMENTS;
        default: return -1;
      }
    }
    if (baseClass == Parametrizable.class)
    {
      switch (baseFeatureID)
      {
        case ParametersPackage.PARAMETRIZABLE__PARAMETERS: return MembersPackage.CONSTRUCTOR__PARAMETERS;
        default: return -1;
      }
    }
    if (baseClass == TypeParametrizable.class)
    {
      switch (baseFeatureID)
      {
        case GenericsPackage.TYPE_PARAMETRIZABLE__TYPE_PARAMETERS: return MembersPackage.CONSTRUCTOR__TYPE_PARAMETERS;
        default: return -1;
      }
    }
    if (baseClass == ExceptionThrower.class)
    {
      switch (baseFeatureID)
      {
        case MembersPackage.EXCEPTION_THROWER__EXCEPTIONS: return MembersPackage.CONSTRUCTOR__EXCEPTIONS;
        default: return -1;
      }
    }
    if (baseClass == AnnotableAndModifiable.class)
    {
      switch (baseFeatureID)
      {
        case ModifiersPackage.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS: return MembersPackage.CONSTRUCTOR__ANNOTATIONS_AND_MODIFIERS;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }

} //ConstructorImpl
