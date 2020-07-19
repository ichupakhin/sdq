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
package org.emftext.language.java.modifiers;

import org.eclipse.emf.common.util.EList;

import org.emftext.language.java.annotations.AnnotationInstance;

import org.emftext.language.java.commons.Commentable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Annotable And Modifiable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emftext.language.java.modifiers.AnnotableAndModifiable#getAnnotationsAndModifiers <em>Annotations And Modifiers</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.emftext.language.java.modifiers.ModifiersPackage#getAnnotableAndModifiable()
 * @model abstract="true"
 * @generated
 */
public interface AnnotableAndModifiable extends Commentable
{
  /**
   * Returns the value of the '<em><b>Annotations And Modifiers</b></em>' containment reference list.
   * The list contents are of type {@link org.emftext.language.java.modifiers.AnnotationInstanceOrModifier}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Annotations And Modifiers</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotations And Modifiers</em>' containment reference list.
   * @see org.emftext.language.java.modifiers.ModifiersPackage#getAnnotableAndModifiable_AnnotationsAndModifiers()
   * @model containment="true" resolveProxies="true"
   * @generated
   */
  EList<AnnotationInstanceOrModifier> getAnnotationsAndModifiers();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.isHidden((org.emftext.language.java.modifiers.AnnotableAndModifiable) this, (org.emftext.language.java.commons.Commentable) context);'"
   * @generated
   */
  boolean isHidden(Commentable context);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.isStatic((org.emftext.language.java.modifiers.AnnotableAndModifiable) this);'"
   * @generated
   */
  boolean isStatic();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.removeModifier((org.emftext.language.java.modifiers.AnnotableAndModifiable) this, modifierType);'"
   * @generated
   */
  void removeModifier(Class<?> modifierType);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.makePublic((org.emftext.language.java.modifiers.AnnotableAndModifiable) this);'"
   * @generated
   */
  void makePublic();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.makePrivate((org.emftext.language.java.modifiers.AnnotableAndModifiable) this);'"
   * @generated
   */
  void makePrivate();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.makeProtected((org.emftext.language.java.modifiers.AnnotableAndModifiable) this);'"
   * @generated
   */
  void makeProtected();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.getModifiers((org.emftext.language.java.modifiers.AnnotableAndModifiable) this);'"
   * @generated
   */
  EList<Modifier> getModifiers();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.removeAllModifiers((org.emftext.language.java.modifiers.AnnotableAndModifiable) this);'"
   * @generated
   */
  void removeAllModifiers();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.hasModifier((org.emftext.language.java.modifiers.AnnotableAndModifiable) this, type);'"
   * @generated
   */
  boolean hasModifier(Class<?> type);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.isPublic((org.emftext.language.java.modifiers.AnnotableAndModifiable) this);'"
   * @generated
   */
  boolean isPublic();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.isPrivate((org.emftext.language.java.modifiers.AnnotableAndModifiable) this);'"
   * @generated
   */
  boolean isPrivate();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.isProtected((org.emftext.language.java.modifiers.AnnotableAndModifiable) this);'"
   * @generated
   */
  boolean isProtected();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model newModifierRequired="true"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.addModifier((org.emftext.language.java.modifiers.AnnotableAndModifiable) this, (org.emftext.language.java.modifiers.Modifier) newModifier);'"
   * @generated
   */
  void addModifier(Modifier newModifier);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * <!-- end-model-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return org.emftext.language.java.extensions.modifiers.AnnotableAndModifiableExtension.getAnnotationInstances((org.emftext.language.java.modifiers.AnnotableAndModifiable) this);'"
   * @generated
   */
  EList<AnnotationInstance> getAnnotationInstances();

} // AnnotableAndModifiable
