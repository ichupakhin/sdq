/*******************************************************************************
 * Copyright (c) 2006-2015
 * Software Technology Group, Dresden University of Technology
 * DevBoost GmbH, Dresden, Amtsgericht Dresden, HRB 34001
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Software Technology Group - TU Dresden, Germany;
 *   DevBoost GmbH - Dresden, Germany
 *      - initial API and implementation
 ******************************************************************************/

package org.emftext.language.java.resource.java.grammar;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;

public class JavaGrammarInformationProvider {
	
	public final static EStructuralFeature ANONYMOUS_FEATURE = EcoreFactory.eINSTANCE.createEAttribute();
	static {
		ANONYMOUS_FEATURE.setName("_");
	}
	
	public final static JavaGrammarInformationProvider INSTANCE = new JavaGrammarInformationProvider();
	
	private Set<String> keywords;
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_0_0_0_0_0_0_0 = INSTANCE.getJAVA_0_0_0_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_0_0_0_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getEmptyModel().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.EMPTY_MODEL__IMPORTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getImport(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_0_0_0_0_0_0_1 = INSTANCE.getJAVA_0_0_0_0_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_0_0_0_0_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_0_0_0_0_0_0_2 = INSTANCE.getJAVA_0_0_0_0_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_0_0_0_0_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_0_0_0_0_0_0 = INSTANCE.getJAVA_0_0_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_0_0_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_0_0_0_0_0_0_0, JAVA_0_0_0_0_0_0_1, JAVA_0_0_0_0_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_0_0_0_0_0 = INSTANCE.getJAVA_0_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_0_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_0_0_0_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_0_0_0_0 = INSTANCE.getJAVA_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_0_0_0_0_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_0_0_0_1_0_0_0 = INSTANCE.getJAVA_0_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_0_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_0_0_0_1_0_0 = INSTANCE.getJAVA_0_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_0_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_0_0_0_1_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_0_0_0_1_0 = INSTANCE.getJAVA_0_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_0_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_0_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_0_0_0_1 = INSTANCE.getJAVA_0_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_0_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_0_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_0_0_0 = INSTANCE.getJAVA_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_0_0_0_0, JAVA_0_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_0_0 = INSTANCE.getJAVA_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_0_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class EmptyModel
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_0 = INSTANCE.getJAVA_0();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getEmptyModel(), JAVA_0_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_1_0_0_0 = INSTANCE.getJAVA_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__ANNOTATIONS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationInstance(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_1_0_0_1 = INSTANCE.getJAVA_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("package", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_1_0_0_2_0_0_0 = INSTANCE.getJAVA_1_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_1_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__NAMESPACES), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_1_0_0_2_0_0_1 = INSTANCE.getJAVA_1_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_1_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(".", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_1_0_0_2_0_0 = INSTANCE.getJAVA_1_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_1_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_1_0_0_2_0_0_0, JAVA_1_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_1_0_0_2_0 = INSTANCE.getJAVA_1_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_1_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_1_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_1_0_0_2 = INSTANCE.getJAVA_1_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_1_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_1_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_1_0_0_3 = INSTANCE.getJAVA_1_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_1_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__NAME), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_1_0_0_4 = INSTANCE.getJAVA_1_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_1_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_1_0_0_5_0_0_0 = INSTANCE.getJAVA_1_0_0_5_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_1_0_0_5_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_1_0_0_5_0_0 = INSTANCE.getJAVA_1_0_0_5_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_1_0_0_5_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_1_0_0_5_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_1_0_0_5_0 = INSTANCE.getJAVA_1_0_0_5_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_1_0_0_5_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_1_0_0_5_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_1_0_0_5 = INSTANCE.getJAVA_1_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_1_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_1_0_0_5_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_1_0_0_6 = INSTANCE.getJAVA_1_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_1_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_1_0_0_7 = INSTANCE.getJAVA_1_0_0_7();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_1_0_0_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_1_0_0_8_0_0_0 = INSTANCE.getJAVA_1_0_0_8_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_1_0_0_8_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__IMPORTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getImport(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_1_0_0_8_0_0_1 = INSTANCE.getJAVA_1_0_0_8_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_1_0_0_8_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_1_0_0_8_0_0 = INSTANCE.getJAVA_1_0_0_8_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_1_0_0_8_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_1_0_0_8_0_0_0, JAVA_1_0_0_8_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_1_0_0_8_0 = INSTANCE.getJAVA_1_0_0_8_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_1_0_0_8_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_1_0_0_8_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_1_0_0_8 = INSTANCE.getJAVA_1_0_0_8();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_1_0_0_8() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_1_0_0_8_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_1_0_0_9_0_0_0 = INSTANCE.getJAVA_1_0_0_9_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_1_0_0_9_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_1_0_0_9_0_0 = INSTANCE.getJAVA_1_0_0_9_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_1_0_0_9_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_1_0_0_9_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_1_0_0_9_0 = INSTANCE.getJAVA_1_0_0_9_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_1_0_0_9_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_1_0_0_9_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_1_0_0_9 = INSTANCE.getJAVA_1_0_0_9();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_1_0_0_9() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_1_0_0_9_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_1_0_0 = INSTANCE.getJAVA_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_1_0_0_0, JAVA_1_0_0_1, JAVA_1_0_0_2, JAVA_1_0_0_3, JAVA_1_0_0_4, JAVA_1_0_0_5, JAVA_1_0_0_6, JAVA_1_0_0_7, JAVA_1_0_0_8, JAVA_1_0_0_9);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_1_0 = INSTANCE.getJAVA_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_1_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Package
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_1 = INSTANCE.getJAVA_1();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage(), JAVA_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_2_0_0_0_0_0_0 = INSTANCE.getJAVA_2_0_0_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_2_0_0_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("package", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_2_0_0_0_0_0_1 = INSTANCE.getJAVA_2_0_0_0_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_2_0_0_0_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__NAMESPACES), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_2_0_0_0_0_0_2_0_0_0 = INSTANCE.getJAVA_2_0_0_0_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_2_0_0_0_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(".", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_2_0_0_0_0_0_2_0_0_1 = INSTANCE.getJAVA_2_0_0_0_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_2_0_0_0_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__NAMESPACES), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_2_0_0_0_0_0_2_0_0 = INSTANCE.getJAVA_2_0_0_0_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_2_0_0_0_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_2_0_0_0_0_0_2_0_0_0, JAVA_2_0_0_0_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_2_0_0_0_0_0_2_0 = INSTANCE.getJAVA_2_0_0_0_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_2_0_0_0_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_2_0_0_0_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_2_0_0_0_0_0_2 = INSTANCE.getJAVA_2_0_0_0_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_2_0_0_0_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_2_0_0_0_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_2_0_0_0_0_0_3 = INSTANCE.getJAVA_2_0_0_0_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_2_0_0_0_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_2_0_0_0_0_0 = INSTANCE.getJAVA_2_0_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_2_0_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_2_0_0_0_0_0_0, JAVA_2_0_0_0_0_0_1, JAVA_2_0_0_0_0_0_2, JAVA_2_0_0_0_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_2_0_0_0_0 = INSTANCE.getJAVA_2_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_2_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_2_0_0_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_2_0_0_0 = INSTANCE.getJAVA_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_2_0_0_0_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_2_0_0_1 = INSTANCE.getJAVA_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_2_0_0_2 = INSTANCE.getJAVA_2_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_2_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_2_0_0_3_0_0_0 = INSTANCE.getJAVA_2_0_0_3_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_2_0_0_3_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__IMPORTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getImport(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_2_0_0_3_0_0_1 = INSTANCE.getJAVA_2_0_0_3_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_2_0_0_3_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_2_0_0_3_0_0 = INSTANCE.getJAVA_2_0_0_3_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_2_0_0_3_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_2_0_0_3_0_0_0, JAVA_2_0_0_3_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_2_0_0_3_0 = INSTANCE.getJAVA_2_0_0_3_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_2_0_0_3_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_2_0_0_3_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_2_0_0_3 = INSTANCE.getJAVA_2_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_2_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_2_0_0_3_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_2_0_0_4_0_0_0 = INSTANCE.getJAVA_2_0_0_4_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_2_0_0_4_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_2_0_0_4_0_0_1 = INSTANCE.getJAVA_2_0_0_4_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_2_0_0_4_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_2_0_0_4_0_0 = INSTANCE.getJAVA_2_0_0_4_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_2_0_0_4_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_2_0_0_4_0_0_0, JAVA_2_0_0_4_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_2_0_0_4_0 = INSTANCE.getJAVA_2_0_0_4_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_2_0_0_4_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_2_0_0_4_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_2_0_0_4 = INSTANCE.getJAVA_2_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_2_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_2_0_0_4_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_2_0_0_5 = INSTANCE.getJAVA_2_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_2_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_2_0_0_6_0_0_0 = INSTANCE.getJAVA_2_0_0_6_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_2_0_0_6_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__CLASSIFIERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getConcreteClassifier(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_2_0_0_6_0_0_1_0_0_0 = INSTANCE.getJAVA_2_0_0_6_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_2_0_0_6_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_2_0_0_6_0_0_1_0_0 = INSTANCE.getJAVA_2_0_0_6_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_2_0_0_6_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_2_0_0_6_0_0_1_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_2_0_0_6_0_0_1_0 = INSTANCE.getJAVA_2_0_0_6_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_2_0_0_6_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_2_0_0_6_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_2_0_0_6_0_0_1 = INSTANCE.getJAVA_2_0_0_6_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_2_0_0_6_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_2_0_0_6_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_2_0_0_6_0_0_2 = INSTANCE.getJAVA_2_0_0_6_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_2_0_0_6_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_2_0_0_6_0_0_3 = INSTANCE.getJAVA_2_0_0_6_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_2_0_0_6_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_2_0_0_6_0_0 = INSTANCE.getJAVA_2_0_0_6_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_2_0_0_6_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_2_0_0_6_0_0_0, JAVA_2_0_0_6_0_0_1, JAVA_2_0_0_6_0_0_2, JAVA_2_0_0_6_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_2_0_0_6_0 = INSTANCE.getJAVA_2_0_0_6_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_2_0_0_6_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_2_0_0_6_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_2_0_0_6 = INSTANCE.getJAVA_2_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_2_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_2_0_0_6_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.PLUS);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_2_0_0_7_0_0_0 = INSTANCE.getJAVA_2_0_0_7_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_2_0_0_7_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("\u001a", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_2_0_0_7_0_0 = INSTANCE.getJAVA_2_0_0_7_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_2_0_0_7_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_2_0_0_7_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_2_0_0_7_0 = INSTANCE.getJAVA_2_0_0_7_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_2_0_0_7_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_2_0_0_7_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_2_0_0_7 = INSTANCE.getJAVA_2_0_0_7();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_2_0_0_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_2_0_0_7_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_2_0_0 = INSTANCE.getJAVA_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_2_0_0_0, JAVA_2_0_0_1, JAVA_2_0_0_2, JAVA_2_0_0_3, JAVA_2_0_0_4, JAVA_2_0_0_5, JAVA_2_0_0_6, JAVA_2_0_0_7);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_2_0 = INSTANCE.getJAVA_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_2_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * CompilationUnit
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_2 = INSTANCE.getJAVA_2();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), JAVA_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_3_0_0_0 = INSTANCE.getJAVA_3_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_3_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("import", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_3_0_0_1_0_0_0 = INSTANCE.getJAVA_3_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_3_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getClassifierImport().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.CLASSIFIER_IMPORT__NAMESPACES), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_3_0_0_1_0_0_1 = INSTANCE.getJAVA_3_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_3_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(".", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_3_0_0_1_0_0 = INSTANCE.getJAVA_3_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_3_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_3_0_0_1_0_0_0, JAVA_3_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_3_0_0_1_0 = INSTANCE.getJAVA_3_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_3_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_3_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_3_0_0_1 = INSTANCE.getJAVA_3_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_3_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_3_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_3_0_0_2 = INSTANCE.getJAVA_3_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_3_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getClassifierImport().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.CLASSIFIER_IMPORT__CLASSIFIER), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_3_0_0_3 = INSTANCE.getJAVA_3_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_3_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_3_0_0 = INSTANCE.getJAVA_3_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_3_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_3_0_0_0, JAVA_3_0_0_1, JAVA_3_0_0_2, JAVA_3_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_3_0 = INSTANCE.getJAVA_3_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_3_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_3_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * ClassifierImport
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_3 = INSTANCE.getJAVA_3();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getClassifierImport(), JAVA_3_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_4_0_0_0 = INSTANCE.getJAVA_4_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_4_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("import", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_4_0_0_1_0_0_0 = INSTANCE.getJAVA_4_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_4_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getPackageImport().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.PACKAGE_IMPORT__NAMESPACES), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_4_0_0_1_0_0_1 = INSTANCE.getJAVA_4_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_4_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(".", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_4_0_0_1_0_0 = INSTANCE.getJAVA_4_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_4_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_4_0_0_1_0_0_0, JAVA_4_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_4_0_0_1_0 = INSTANCE.getJAVA_4_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_4_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_4_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_4_0_0_1 = INSTANCE.getJAVA_4_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_4_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_4_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.PLUS);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_4_0_0_2 = INSTANCE.getJAVA_4_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_4_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("*", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_4_0_0_3 = INSTANCE.getJAVA_4_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_4_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_4_0_0 = INSTANCE.getJAVA_4_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_4_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_4_0_0_0, JAVA_4_0_0_1, JAVA_4_0_0_2, JAVA_4_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_4_0 = INSTANCE.getJAVA_4_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_4_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_4_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * PackageImport
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_4 = INSTANCE.getJAVA_4();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getPackageImport(), JAVA_4_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_5_0_0_0 = INSTANCE.getJAVA_5_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_5_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("import", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_5_0_0_1 = INSTANCE.getJAVA_5_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_5_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getStaticMemberImport().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__STATIC), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getStatic(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_5_0_0_2_0_0_0 = INSTANCE.getJAVA_5_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_5_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getStaticMemberImport().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__NAMESPACES), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_5_0_0_2_0_0_1 = INSTANCE.getJAVA_5_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_5_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(".", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_5_0_0_2_0_0 = INSTANCE.getJAVA_5_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_5_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_5_0_0_2_0_0_0, JAVA_5_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_5_0_0_2_0 = INSTANCE.getJAVA_5_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_5_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_5_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_5_0_0_2 = INSTANCE.getJAVA_5_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_5_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_5_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_5_0_0_3 = INSTANCE.getJAVA_5_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_5_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getStaticMemberImport().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__STATIC_MEMBERS), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_5_0_0_4 = INSTANCE.getJAVA_5_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_5_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_5_0_0 = INSTANCE.getJAVA_5_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_5_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_5_0_0_0, JAVA_5_0_0_1, JAVA_5_0_0_2, JAVA_5_0_0_3, JAVA_5_0_0_4);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_5_0 = INSTANCE.getJAVA_5_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_5_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_5_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * StaticMemberImport
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_5 = INSTANCE.getJAVA_5();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getStaticMemberImport(), JAVA_5_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_6_0_0_0 = INSTANCE.getJAVA_6_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_6_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("import", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_6_0_0_1 = INSTANCE.getJAVA_6_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_6_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getStaticClassifierImport().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_CLASSIFIER_IMPORT__STATIC), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getStatic(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_6_0_0_2_0_0_0 = INSTANCE.getJAVA_6_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_6_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getStaticClassifierImport().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_CLASSIFIER_IMPORT__NAMESPACES), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_6_0_0_2_0_0_1 = INSTANCE.getJAVA_6_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_6_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(".", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_6_0_0_2_0_0 = INSTANCE.getJAVA_6_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_6_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_6_0_0_2_0_0_0, JAVA_6_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_6_0_0_2_0 = INSTANCE.getJAVA_6_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_6_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_6_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_6_0_0_2 = INSTANCE.getJAVA_6_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_6_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_6_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.PLUS);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_6_0_0_3 = INSTANCE.getJAVA_6_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_6_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("*", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_6_0_0_4 = INSTANCE.getJAVA_6_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_6_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_6_0_0 = INSTANCE.getJAVA_6_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_6_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_6_0_0_0, JAVA_6_0_0_1, JAVA_6_0_0_2, JAVA_6_0_0_3, JAVA_6_0_0_4);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_6_0 = INSTANCE.getJAVA_6_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_6_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_6_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * StaticClassifierImport
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_6 = INSTANCE.getJAVA_6();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getStaticClassifierImport(), JAVA_6_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_7_0_0_0 = INSTANCE.getJAVA_7_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_7_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__ANNOTATIONS_AND_MODIFIERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_7_0_0_1 = INSTANCE.getJAVA_7_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_7_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("class", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_7_0_0_2 = INSTANCE.getJAVA_7_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_7_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__NAME), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_7_0_0_3_0_0_0 = INSTANCE.getJAVA_7_0_0_3_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_7_0_0_3_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_7_0_0_3_0_0_1 = INSTANCE.getJAVA_7_0_0_3_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_7_0_0_3_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__TYPE_PARAMETERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_7_0_0_3_0_0_2_0_0_0 = INSTANCE.getJAVA_7_0_0_3_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_7_0_0_3_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_7_0_0_3_0_0_2_0_0_1 = INSTANCE.getJAVA_7_0_0_3_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_7_0_0_3_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__TYPE_PARAMETERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_7_0_0_3_0_0_2_0_0 = INSTANCE.getJAVA_7_0_0_3_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_7_0_0_3_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_7_0_0_3_0_0_2_0_0_0, JAVA_7_0_0_3_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_7_0_0_3_0_0_2_0 = INSTANCE.getJAVA_7_0_0_3_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_7_0_0_3_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_7_0_0_3_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_7_0_0_3_0_0_2 = INSTANCE.getJAVA_7_0_0_3_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_7_0_0_3_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_7_0_0_3_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_7_0_0_3_0_0_3 = INSTANCE.getJAVA_7_0_0_3_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_7_0_0_3_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_7_0_0_3_0_0 = INSTANCE.getJAVA_7_0_0_3_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_7_0_0_3_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_7_0_0_3_0_0_0, JAVA_7_0_0_3_0_0_1, JAVA_7_0_0_3_0_0_2, JAVA_7_0_0_3_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_7_0_0_3_0 = INSTANCE.getJAVA_7_0_0_3_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_7_0_0_3_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_7_0_0_3_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_7_0_0_3 = INSTANCE.getJAVA_7_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_7_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_7_0_0_3_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_7_0_0_4_0_0_0 = INSTANCE.getJAVA_7_0_0_4_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_7_0_0_4_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("extends", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_7_0_0_4_0_0_1 = INSTANCE.getJAVA_7_0_0_4_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_7_0_0_4_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__EXTENDS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_7_0_0_4_0_0 = INSTANCE.getJAVA_7_0_0_4_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_7_0_0_4_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_7_0_0_4_0_0_0, JAVA_7_0_0_4_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_7_0_0_4_0 = INSTANCE.getJAVA_7_0_0_4_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_7_0_0_4_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_7_0_0_4_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_7_0_0_4 = INSTANCE.getJAVA_7_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_7_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_7_0_0_4_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_7_0_0_5_0_0_0 = INSTANCE.getJAVA_7_0_0_5_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_7_0_0_5_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("implements", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_7_0_0_5_0_0_1_0_0_0 = INSTANCE.getJAVA_7_0_0_5_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_7_0_0_5_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__IMPLEMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_7_0_0_5_0_0_1_0_0_1_0_0_0 = INSTANCE.getJAVA_7_0_0_5_0_0_1_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_7_0_0_5_0_0_1_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_7_0_0_5_0_0_1_0_0_1_0_0_1 = INSTANCE.getJAVA_7_0_0_5_0_0_1_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_7_0_0_5_0_0_1_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__IMPLEMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_7_0_0_5_0_0_1_0_0_1_0_0 = INSTANCE.getJAVA_7_0_0_5_0_0_1_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_7_0_0_5_0_0_1_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_7_0_0_5_0_0_1_0_0_1_0_0_0, JAVA_7_0_0_5_0_0_1_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_7_0_0_5_0_0_1_0_0_1_0 = INSTANCE.getJAVA_7_0_0_5_0_0_1_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_7_0_0_5_0_0_1_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_7_0_0_5_0_0_1_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_7_0_0_5_0_0_1_0_0_1 = INSTANCE.getJAVA_7_0_0_5_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_7_0_0_5_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_7_0_0_5_0_0_1_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_7_0_0_5_0_0_1_0_0 = INSTANCE.getJAVA_7_0_0_5_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_7_0_0_5_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_7_0_0_5_0_0_1_0_0_0, JAVA_7_0_0_5_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_7_0_0_5_0_0_1_0 = INSTANCE.getJAVA_7_0_0_5_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_7_0_0_5_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_7_0_0_5_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_7_0_0_5_0_0_1 = INSTANCE.getJAVA_7_0_0_5_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_7_0_0_5_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_7_0_0_5_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_7_0_0_5_0_0 = INSTANCE.getJAVA_7_0_0_5_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_7_0_0_5_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_7_0_0_5_0_0_0, JAVA_7_0_0_5_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_7_0_0_5_0 = INSTANCE.getJAVA_7_0_0_5_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_7_0_0_5_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_7_0_0_5_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_7_0_0_5 = INSTANCE.getJAVA_7_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_7_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_7_0_0_5_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_7_0_0_6 = INSTANCE.getJAVA_7_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_7_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_7_0_0_7 = INSTANCE.getJAVA_7_0_0_7();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_7_0_0_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("{", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_7_0_0_8_0_0_0 = INSTANCE.getJAVA_7_0_0_8_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_7_0_0_8_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_7_0_0_8_0_0_1 = INSTANCE.getJAVA_7_0_0_8_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_7_0_0_8_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__MEMBERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.members.MembersPackage.eINSTANCE.getMember(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_7_0_0_8_0_0 = INSTANCE.getJAVA_7_0_0_8_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_7_0_0_8_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_7_0_0_8_0_0_0, JAVA_7_0_0_8_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_7_0_0_8_0 = INSTANCE.getJAVA_7_0_0_8_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_7_0_0_8_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_7_0_0_8_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_7_0_0_8 = INSTANCE.getJAVA_7_0_0_8();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_7_0_0_8() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_7_0_0_8_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_7_0_0_9 = INSTANCE.getJAVA_7_0_0_9();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_7_0_0_9() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_7_0_0_10 = INSTANCE.getJAVA_7_0_0_10();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_7_0_0_10() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("}", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_7_0_0 = INSTANCE.getJAVA_7_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_7_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_7_0_0_0, JAVA_7_0_0_1, JAVA_7_0_0_2, JAVA_7_0_0_3, JAVA_7_0_0_4, JAVA_7_0_0_5, JAVA_7_0_0_6, JAVA_7_0_0_7, JAVA_7_0_0_8, JAVA_7_0_0_9, JAVA_7_0_0_10);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_7_0 = INSTANCE.getJAVA_7_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_7_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_7_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Class
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_7 = INSTANCE.getJAVA_7();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), JAVA_7_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_8_0_0_0 = INSTANCE.getJAVA_8_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_8_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_8_0_0_1 = INSTANCE.getJAVA_8_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_8_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("{", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_8_0_0_2_0_0_0 = INSTANCE.getJAVA_8_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_8_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_8_0_0_2_0_0_1 = INSTANCE.getJAVA_8_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_8_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANONYMOUS_CLASS__MEMBERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.members.MembersPackage.eINSTANCE.getMember(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_8_0_0_2_0_0 = INSTANCE.getJAVA_8_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_8_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_8_0_0_2_0_0_0, JAVA_8_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_8_0_0_2_0 = INSTANCE.getJAVA_8_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_8_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_8_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_8_0_0_2 = INSTANCE.getJAVA_8_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_8_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_8_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_8_0_0_3 = INSTANCE.getJAVA_8_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_8_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_8_0_0_4 = INSTANCE.getJAVA_8_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_8_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("}", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_8_0_0 = INSTANCE.getJAVA_8_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_8_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_8_0_0_0, JAVA_8_0_0_1, JAVA_8_0_0_2, JAVA_8_0_0_3, JAVA_8_0_0_4);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_8_0 = INSTANCE.getJAVA_8_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_8_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_8_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * AnonymousClass
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_8 = INSTANCE.getJAVA_8();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_8() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass(), JAVA_8_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_9_0_0_0 = INSTANCE.getJAVA_9_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_9_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__ANNOTATIONS_AND_MODIFIERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_9_0_0_1 = INSTANCE.getJAVA_9_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_9_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("interface", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_9_0_0_2 = INSTANCE.getJAVA_9_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_9_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__NAME), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_9_0_0_3_0_0_0 = INSTANCE.getJAVA_9_0_0_3_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_9_0_0_3_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_9_0_0_3_0_0_1 = INSTANCE.getJAVA_9_0_0_3_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_9_0_0_3_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__TYPE_PARAMETERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_9_0_0_3_0_0_2_0_0_0 = INSTANCE.getJAVA_9_0_0_3_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_9_0_0_3_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_9_0_0_3_0_0_2_0_0_1 = INSTANCE.getJAVA_9_0_0_3_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_9_0_0_3_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__TYPE_PARAMETERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_9_0_0_3_0_0_2_0_0 = INSTANCE.getJAVA_9_0_0_3_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_9_0_0_3_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_9_0_0_3_0_0_2_0_0_0, JAVA_9_0_0_3_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_9_0_0_3_0_0_2_0 = INSTANCE.getJAVA_9_0_0_3_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_9_0_0_3_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_9_0_0_3_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_9_0_0_3_0_0_2 = INSTANCE.getJAVA_9_0_0_3_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_9_0_0_3_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_9_0_0_3_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_9_0_0_3_0_0_3 = INSTANCE.getJAVA_9_0_0_3_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_9_0_0_3_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_9_0_0_3_0_0 = INSTANCE.getJAVA_9_0_0_3_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_9_0_0_3_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_9_0_0_3_0_0_0, JAVA_9_0_0_3_0_0_1, JAVA_9_0_0_3_0_0_2, JAVA_9_0_0_3_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_9_0_0_3_0 = INSTANCE.getJAVA_9_0_0_3_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_9_0_0_3_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_9_0_0_3_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_9_0_0_3 = INSTANCE.getJAVA_9_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_9_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_9_0_0_3_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_9_0_0_4_0_0_0 = INSTANCE.getJAVA_9_0_0_4_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_9_0_0_4_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("extends", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_9_0_0_4_0_0_1_0_0_0 = INSTANCE.getJAVA_9_0_0_4_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_9_0_0_4_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__EXTENDS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_9_0_0_4_0_0_1_0_0_1_0_0_0 = INSTANCE.getJAVA_9_0_0_4_0_0_1_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_9_0_0_4_0_0_1_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_9_0_0_4_0_0_1_0_0_1_0_0_1 = INSTANCE.getJAVA_9_0_0_4_0_0_1_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_9_0_0_4_0_0_1_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__EXTENDS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_9_0_0_4_0_0_1_0_0_1_0_0 = INSTANCE.getJAVA_9_0_0_4_0_0_1_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_9_0_0_4_0_0_1_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_9_0_0_4_0_0_1_0_0_1_0_0_0, JAVA_9_0_0_4_0_0_1_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_9_0_0_4_0_0_1_0_0_1_0 = INSTANCE.getJAVA_9_0_0_4_0_0_1_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_9_0_0_4_0_0_1_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_9_0_0_4_0_0_1_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_9_0_0_4_0_0_1_0_0_1 = INSTANCE.getJAVA_9_0_0_4_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_9_0_0_4_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_9_0_0_4_0_0_1_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_9_0_0_4_0_0_1_0_0 = INSTANCE.getJAVA_9_0_0_4_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_9_0_0_4_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_9_0_0_4_0_0_1_0_0_0, JAVA_9_0_0_4_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_9_0_0_4_0_0_1_0 = INSTANCE.getJAVA_9_0_0_4_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_9_0_0_4_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_9_0_0_4_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_9_0_0_4_0_0_1 = INSTANCE.getJAVA_9_0_0_4_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_9_0_0_4_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_9_0_0_4_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_9_0_0_4_0_0 = INSTANCE.getJAVA_9_0_0_4_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_9_0_0_4_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_9_0_0_4_0_0_0, JAVA_9_0_0_4_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_9_0_0_4_0 = INSTANCE.getJAVA_9_0_0_4_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_9_0_0_4_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_9_0_0_4_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_9_0_0_4 = INSTANCE.getJAVA_9_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_9_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_9_0_0_4_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_9_0_0_5 = INSTANCE.getJAVA_9_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_9_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_9_0_0_6 = INSTANCE.getJAVA_9_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_9_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("{", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_9_0_0_7_0_0_0 = INSTANCE.getJAVA_9_0_0_7_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_9_0_0_7_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_9_0_0_7_0_0_1 = INSTANCE.getJAVA_9_0_0_7_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_9_0_0_7_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__MEMBERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.members.MembersPackage.eINSTANCE.getMember(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_9_0_0_7_0_0 = INSTANCE.getJAVA_9_0_0_7_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_9_0_0_7_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_9_0_0_7_0_0_0, JAVA_9_0_0_7_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_9_0_0_7_0 = INSTANCE.getJAVA_9_0_0_7_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_9_0_0_7_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_9_0_0_7_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_9_0_0_7 = INSTANCE.getJAVA_9_0_0_7();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_9_0_0_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_9_0_0_7_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_9_0_0_8 = INSTANCE.getJAVA_9_0_0_8();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_9_0_0_8() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_9_0_0_9 = INSTANCE.getJAVA_9_0_0_9();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_9_0_0_9() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("}", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_9_0_0 = INSTANCE.getJAVA_9_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_9_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_9_0_0_0, JAVA_9_0_0_1, JAVA_9_0_0_2, JAVA_9_0_0_3, JAVA_9_0_0_4, JAVA_9_0_0_5, JAVA_9_0_0_6, JAVA_9_0_0_7, JAVA_9_0_0_8, JAVA_9_0_0_9);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_9_0 = INSTANCE.getJAVA_9_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_9_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_9_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Interface
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_9 = INSTANCE.getJAVA_9();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_9() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), JAVA_9_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_10_0_0_0 = INSTANCE.getJAVA_10_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_10_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__ANNOTATIONS_AND_MODIFIERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_10_0_0_1 = INSTANCE.getJAVA_10_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_10_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("enum", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_10_0_0_2 = INSTANCE.getJAVA_10_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_10_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__NAME), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_10_0_0_3_0_0_0 = INSTANCE.getJAVA_10_0_0_3_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_10_0_0_3_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("implements", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_10_0_0_3_0_0_1_0_0_0 = INSTANCE.getJAVA_10_0_0_3_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_10_0_0_3_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__IMPLEMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_10_0_0_3_0_0_1_0_0_1_0_0_0 = INSTANCE.getJAVA_10_0_0_3_0_0_1_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_10_0_0_3_0_0_1_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_10_0_0_3_0_0_1_0_0_1_0_0_1 = INSTANCE.getJAVA_10_0_0_3_0_0_1_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_10_0_0_3_0_0_1_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__IMPLEMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_10_0_0_3_0_0_1_0_0_1_0_0 = INSTANCE.getJAVA_10_0_0_3_0_0_1_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_10_0_0_3_0_0_1_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_10_0_0_3_0_0_1_0_0_1_0_0_0, JAVA_10_0_0_3_0_0_1_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_10_0_0_3_0_0_1_0_0_1_0 = INSTANCE.getJAVA_10_0_0_3_0_0_1_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_10_0_0_3_0_0_1_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_10_0_0_3_0_0_1_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_10_0_0_3_0_0_1_0_0_1 = INSTANCE.getJAVA_10_0_0_3_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_10_0_0_3_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_10_0_0_3_0_0_1_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_10_0_0_3_0_0_1_0_0 = INSTANCE.getJAVA_10_0_0_3_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_10_0_0_3_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_10_0_0_3_0_0_1_0_0_0, JAVA_10_0_0_3_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_10_0_0_3_0_0_1_0 = INSTANCE.getJAVA_10_0_0_3_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_10_0_0_3_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_10_0_0_3_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_10_0_0_3_0_0_1 = INSTANCE.getJAVA_10_0_0_3_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_10_0_0_3_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_10_0_0_3_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_10_0_0_3_0_0 = INSTANCE.getJAVA_10_0_0_3_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_10_0_0_3_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_10_0_0_3_0_0_0, JAVA_10_0_0_3_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_10_0_0_3_0 = INSTANCE.getJAVA_10_0_0_3_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_10_0_0_3_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_10_0_0_3_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_10_0_0_3 = INSTANCE.getJAVA_10_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_10_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_10_0_0_3_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_10_0_0_4 = INSTANCE.getJAVA_10_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_10_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_10_0_0_5 = INSTANCE.getJAVA_10_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_10_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("{", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_10_0_0_6_0_0_0 = INSTANCE.getJAVA_10_0_0_6_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_10_0_0_6_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_10_0_0_6_0_0_1 = INSTANCE.getJAVA_10_0_0_6_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_10_0_0_6_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__CONSTANTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_10_0_0_6_0_0_2_0_0_0 = INSTANCE.getJAVA_10_0_0_6_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_10_0_0_6_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_10_0_0_6_0_0_2_0_0_1 = INSTANCE.getJAVA_10_0_0_6_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_10_0_0_6_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_10_0_0_6_0_0_2_0_0_2 = INSTANCE.getJAVA_10_0_0_6_0_0_2_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_10_0_0_6_0_0_2_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__CONSTANTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_10_0_0_6_0_0_2_0_0 = INSTANCE.getJAVA_10_0_0_6_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_10_0_0_6_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_10_0_0_6_0_0_2_0_0_0, JAVA_10_0_0_6_0_0_2_0_0_1, JAVA_10_0_0_6_0_0_2_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_10_0_0_6_0_0_2_0 = INSTANCE.getJAVA_10_0_0_6_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_10_0_0_6_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_10_0_0_6_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_10_0_0_6_0_0_2 = INSTANCE.getJAVA_10_0_0_6_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_10_0_0_6_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_10_0_0_6_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_10_0_0_6_0_0 = INSTANCE.getJAVA_10_0_0_6_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_10_0_0_6_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_10_0_0_6_0_0_0, JAVA_10_0_0_6_0_0_1, JAVA_10_0_0_6_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_10_0_0_6_0 = INSTANCE.getJAVA_10_0_0_6_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_10_0_0_6_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_10_0_0_6_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_10_0_0_6 = INSTANCE.getJAVA_10_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_10_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_10_0_0_6_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_10_0_0_7_0_0_0 = INSTANCE.getJAVA_10_0_0_7_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_10_0_0_7_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_10_0_0_7_0_0 = INSTANCE.getJAVA_10_0_0_7_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_10_0_0_7_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_10_0_0_7_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_10_0_0_7_0 = INSTANCE.getJAVA_10_0_0_7_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_10_0_0_7_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_10_0_0_7_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_10_0_0_7 = INSTANCE.getJAVA_10_0_0_7();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_10_0_0_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_10_0_0_7_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_10_0_0_8_0_0_0 = INSTANCE.getJAVA_10_0_0_8_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_10_0_0_8_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_10_0_0_8_0_0_1_0_0_0 = INSTANCE.getJAVA_10_0_0_8_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_10_0_0_8_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_10_0_0_8_0_0_1_0_0_1 = INSTANCE.getJAVA_10_0_0_8_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_10_0_0_8_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__MEMBERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.members.MembersPackage.eINSTANCE.getMember(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_10_0_0_8_0_0_1_0_0 = INSTANCE.getJAVA_10_0_0_8_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_10_0_0_8_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_10_0_0_8_0_0_1_0_0_0, JAVA_10_0_0_8_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_10_0_0_8_0_0_1_0 = INSTANCE.getJAVA_10_0_0_8_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_10_0_0_8_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_10_0_0_8_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_10_0_0_8_0_0_1 = INSTANCE.getJAVA_10_0_0_8_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_10_0_0_8_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_10_0_0_8_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_10_0_0_8_0_0_2 = INSTANCE.getJAVA_10_0_0_8_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_10_0_0_8_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_10_0_0_8_0_0 = INSTANCE.getJAVA_10_0_0_8_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_10_0_0_8_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_10_0_0_8_0_0_0, JAVA_10_0_0_8_0_0_1, JAVA_10_0_0_8_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_10_0_0_8_0 = INSTANCE.getJAVA_10_0_0_8_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_10_0_0_8_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_10_0_0_8_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_10_0_0_8 = INSTANCE.getJAVA_10_0_0_8();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_10_0_0_8() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_10_0_0_8_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_10_0_0_9 = INSTANCE.getJAVA_10_0_0_9();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_10_0_0_9() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("}", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_10_0_0 = INSTANCE.getJAVA_10_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_10_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_10_0_0_0, JAVA_10_0_0_1, JAVA_10_0_0_2, JAVA_10_0_0_3, JAVA_10_0_0_4, JAVA_10_0_0_5, JAVA_10_0_0_6, JAVA_10_0_0_7, JAVA_10_0_0_8, JAVA_10_0_0_9);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_10_0 = INSTANCE.getJAVA_10_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_10_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_10_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Enumeration
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_10 = INSTANCE.getJAVA_10();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_10() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), JAVA_10_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_11_0_0_0 = INSTANCE.getJAVA_11_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_11_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANNOTATION__ANNOTATIONS_AND_MODIFIERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_11_0_0_1 = INSTANCE.getJAVA_11_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_11_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("@", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_11_0_0_2 = INSTANCE.getJAVA_11_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_11_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("interface", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_11_0_0_3 = INSTANCE.getJAVA_11_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_11_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANNOTATION__NAME), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_11_0_0_4 = INSTANCE.getJAVA_11_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_11_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_11_0_0_5 = INSTANCE.getJAVA_11_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_11_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("{", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_11_0_0_6_0_0_0 = INSTANCE.getJAVA_11_0_0_6_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_11_0_0_6_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_11_0_0_6_0_0_1 = INSTANCE.getJAVA_11_0_0_6_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_11_0_0_6_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANNOTATION__MEMBERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.members.MembersPackage.eINSTANCE.getMember(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_11_0_0_6_0_0 = INSTANCE.getJAVA_11_0_0_6_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_11_0_0_6_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_11_0_0_6_0_0_0, JAVA_11_0_0_6_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_11_0_0_6_0 = INSTANCE.getJAVA_11_0_0_6_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_11_0_0_6_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_11_0_0_6_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_11_0_0_6 = INSTANCE.getJAVA_11_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_11_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_11_0_0_6_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_11_0_0_7 = INSTANCE.getJAVA_11_0_0_7();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_11_0_0_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_11_0_0_8 = INSTANCE.getJAVA_11_0_0_8();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_11_0_0_8() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("}", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_11_0_0 = INSTANCE.getJAVA_11_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_11_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_11_0_0_0, JAVA_11_0_0_1, JAVA_11_0_0_2, JAVA_11_0_0_3, JAVA_11_0_0_4, JAVA_11_0_0_5, JAVA_11_0_0_6, JAVA_11_0_0_7, JAVA_11_0_0_8);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_11_0 = INSTANCE.getJAVA_11_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_11_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_11_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Annotation
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_11 = INSTANCE.getJAVA_11();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_11() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), JAVA_11_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_12_0_0_0 = INSTANCE.getJAVA_12_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_12_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("@", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_12_0_0_1_0_0_0 = INSTANCE.getJAVA_12_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_12_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationInstance().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__NAMESPACES), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_12_0_0_1_0_0_1 = INSTANCE.getJAVA_12_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_12_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(".", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_12_0_0_1_0_0 = INSTANCE.getJAVA_12_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_12_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_12_0_0_1_0_0_0, JAVA_12_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_12_0_0_1_0 = INSTANCE.getJAVA_12_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_12_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_12_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_12_0_0_1 = INSTANCE.getJAVA_12_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_12_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_12_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_12_0_0_2 = INSTANCE.getJAVA_12_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_12_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationInstance().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__ANNOTATION), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_12_0_0_3_0_0_0 = INSTANCE.getJAVA_12_0_0_3_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_12_0_0_3_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationInstance().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__PARAMETER), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_12_0_0_3_0_0 = INSTANCE.getJAVA_12_0_0_3_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_12_0_0_3_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_12_0_0_3_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_12_0_0_3_0 = INSTANCE.getJAVA_12_0_0_3_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_12_0_0_3_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_12_0_0_3_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_12_0_0_3 = INSTANCE.getJAVA_12_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_12_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_12_0_0_3_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_12_0_0 = INSTANCE.getJAVA_12_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_12_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_12_0_0_0, JAVA_12_0_0_1, JAVA_12_0_0_2, JAVA_12_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_12_0 = INSTANCE.getJAVA_12_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_12_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_12_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * AnnotationInstance
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_12 = INSTANCE.getJAVA_12();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_12() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationInstance(), JAVA_12_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_13_0_0_0 = INSTANCE.getJAVA_13_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_13_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("(", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_13_0_0_1 = INSTANCE.getJAVA_13_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_13_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getSingleAnnotationParameter().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.SINGLE_ANNOTATION_PARAMETER__VALUE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInitializer(), org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_13_0_0_2 = INSTANCE.getJAVA_13_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_13_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(")", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_13_0_0 = INSTANCE.getJAVA_13_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_13_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_13_0_0_0, JAVA_13_0_0_1, JAVA_13_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_13_0 = INSTANCE.getJAVA_13_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_13_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_13_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * SingleAnnotationParameter
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_13 = INSTANCE.getJAVA_13();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_13() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getSingleAnnotationParameter(), JAVA_13_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_14_0_0_0 = INSTANCE.getJAVA_14_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_14_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("(", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_14_0_0_1_0_0_0 = INSTANCE.getJAVA_14_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_14_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationParameterList().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_PARAMETER_LIST__SETTINGS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttributeSetting(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_14_0_0_1_0_0_1_0_0_0 = INSTANCE.getJAVA_14_0_0_1_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_14_0_0_1_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_14_0_0_1_0_0_1_0_0_1 = INSTANCE.getJAVA_14_0_0_1_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_14_0_0_1_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationParameterList().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_PARAMETER_LIST__SETTINGS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttributeSetting(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_14_0_0_1_0_0_1_0_0 = INSTANCE.getJAVA_14_0_0_1_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_14_0_0_1_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_14_0_0_1_0_0_1_0_0_0, JAVA_14_0_0_1_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_14_0_0_1_0_0_1_0 = INSTANCE.getJAVA_14_0_0_1_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_14_0_0_1_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_14_0_0_1_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_14_0_0_1_0_0_1 = INSTANCE.getJAVA_14_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_14_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_14_0_0_1_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_14_0_0_1_0_0 = INSTANCE.getJAVA_14_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_14_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_14_0_0_1_0_0_0, JAVA_14_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_14_0_0_1_0 = INSTANCE.getJAVA_14_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_14_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_14_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_14_0_0_1 = INSTANCE.getJAVA_14_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_14_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_14_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_14_0_0_2 = INSTANCE.getJAVA_14_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_14_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(")", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_14_0_0 = INSTANCE.getJAVA_14_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_14_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_14_0_0_0, JAVA_14_0_0_1, JAVA_14_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_14_0 = INSTANCE.getJAVA_14_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_14_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_14_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * AnnotationParameterList
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_14 = INSTANCE.getJAVA_14();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_14() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationParameterList(), JAVA_14_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_15_0_0_0 = INSTANCE.getJAVA_15_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_15_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttributeSetting().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE_SETTING__ATTRIBUTE), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_15_0_0_1 = INSTANCE.getJAVA_15_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_15_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_15_0_0_2 = INSTANCE.getJAVA_15_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_15_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("=", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_15_0_0_3 = INSTANCE.getJAVA_15_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_15_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_15_0_0_4 = INSTANCE.getJAVA_15_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_15_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttributeSetting().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE_SETTING__VALUE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInitializer(), org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_15_0_0 = INSTANCE.getJAVA_15_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_15_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_15_0_0_0, JAVA_15_0_0_1, JAVA_15_0_0_2, JAVA_15_0_0_3, JAVA_15_0_0_4);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_15_0 = INSTANCE.getJAVA_15_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_15_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_15_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * AnnotationAttributeSetting
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_15 = INSTANCE.getJAVA_15();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_15() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttributeSetting(), JAVA_15_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_16_0_0_0 = INSTANCE.getJAVA_16_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_16_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.TYPE_PARAMETER__NAME), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_16_0_0_1_0_0_0 = INSTANCE.getJAVA_16_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_16_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("extends", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_16_0_0_1_0_0_1 = INSTANCE.getJAVA_16_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_16_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.TYPE_PARAMETER__EXTEND_TYPES), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_16_0_0_1_0_0_2_0_0_0 = INSTANCE.getJAVA_16_0_0_1_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_16_0_0_1_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("&", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_16_0_0_1_0_0_2_0_0_1 = INSTANCE.getJAVA_16_0_0_1_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_16_0_0_1_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.TYPE_PARAMETER__EXTEND_TYPES), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_16_0_0_1_0_0_2_0_0 = INSTANCE.getJAVA_16_0_0_1_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_16_0_0_1_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_16_0_0_1_0_0_2_0_0_0, JAVA_16_0_0_1_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_16_0_0_1_0_0_2_0 = INSTANCE.getJAVA_16_0_0_1_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_16_0_0_1_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_16_0_0_1_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_16_0_0_1_0_0_2 = INSTANCE.getJAVA_16_0_0_1_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_16_0_0_1_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_16_0_0_1_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_16_0_0_1_0_0 = INSTANCE.getJAVA_16_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_16_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_16_0_0_1_0_0_0, JAVA_16_0_0_1_0_0_1, JAVA_16_0_0_1_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_16_0_0_1_0 = INSTANCE.getJAVA_16_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_16_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_16_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_16_0_0_1 = INSTANCE.getJAVA_16_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_16_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_16_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_16_0_0 = INSTANCE.getJAVA_16_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_16_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_16_0_0_0, JAVA_16_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_16_0 = INSTANCE.getJAVA_16_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_16_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_16_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * TypeParameter
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_16 = INSTANCE.getJAVA_16();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_16() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), JAVA_16_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_17_0_0_0 = INSTANCE.getJAVA_17_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_17_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__ANNOTATIONS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationInstance(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_17_0_0_1 = INSTANCE.getJAVA_17_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_17_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__NAME), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_17_0_0_2_0_0_0 = INSTANCE.getJAVA_17_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_17_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_17_0_0_2_0_0_1 = INSTANCE.getJAVA_17_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_17_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("(", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_17_0_0_2_0_0_2_0_0_0 = INSTANCE.getJAVA_17_0_0_2_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_17_0_0_2_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_17_0_0_2_0_0_2_0_0_1_0_0_0 = INSTANCE.getJAVA_17_0_0_2_0_0_2_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_17_0_0_2_0_0_2_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_17_0_0_2_0_0_2_0_0_1_0_0_1 = INSTANCE.getJAVA_17_0_0_2_0_0_2_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_17_0_0_2_0_0_2_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_17_0_0_2_0_0_2_0_0_1_0_0 = INSTANCE.getJAVA_17_0_0_2_0_0_2_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_17_0_0_2_0_0_2_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_17_0_0_2_0_0_2_0_0_1_0_0_0, JAVA_17_0_0_2_0_0_2_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_17_0_0_2_0_0_2_0_0_1_0 = INSTANCE.getJAVA_17_0_0_2_0_0_2_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_17_0_0_2_0_0_2_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_17_0_0_2_0_0_2_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_17_0_0_2_0_0_2_0_0_1 = INSTANCE.getJAVA_17_0_0_2_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_17_0_0_2_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_17_0_0_2_0_0_2_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_17_0_0_2_0_0_2_0_0 = INSTANCE.getJAVA_17_0_0_2_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_17_0_0_2_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_17_0_0_2_0_0_2_0_0_0, JAVA_17_0_0_2_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_17_0_0_2_0_0_2_0 = INSTANCE.getJAVA_17_0_0_2_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_17_0_0_2_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_17_0_0_2_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_17_0_0_2_0_0_2 = INSTANCE.getJAVA_17_0_0_2_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_17_0_0_2_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_17_0_0_2_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_17_0_0_2_0_0_3 = INSTANCE.getJAVA_17_0_0_2_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_17_0_0_2_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(")", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_17_0_0_2_0_0 = INSTANCE.getJAVA_17_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_17_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_17_0_0_2_0_0_0, JAVA_17_0_0_2_0_0_1, JAVA_17_0_0_2_0_0_2, JAVA_17_0_0_2_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_17_0_0_2_0 = INSTANCE.getJAVA_17_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_17_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_17_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_17_0_0_2 = INSTANCE.getJAVA_17_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_17_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_17_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_17_0_0_3_0_0_0 = INSTANCE.getJAVA_17_0_0_3_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_17_0_0_3_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__ANONYMOUS_CLASS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_17_0_0_3_0_0 = INSTANCE.getJAVA_17_0_0_3_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_17_0_0_3_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_17_0_0_3_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_17_0_0_3_0 = INSTANCE.getJAVA_17_0_0_3_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_17_0_0_3_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_17_0_0_3_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_17_0_0_3 = INSTANCE.getJAVA_17_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_17_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_17_0_0_3_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_17_0_0 = INSTANCE.getJAVA_17_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_17_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_17_0_0_0, JAVA_17_0_0_1, JAVA_17_0_0_2, JAVA_17_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_17_0 = INSTANCE.getJAVA_17_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_17_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_17_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class EnumConstant
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_17 = INSTANCE.getJAVA_17();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_17() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant(), JAVA_17_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_18_0_0_0 = INSTANCE.getJAVA_18_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_18_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.BLOCK__MODIFIERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getModifier(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_18_0_0_1 = INSTANCE.getJAVA_18_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_18_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_18_0_0_2 = INSTANCE.getJAVA_18_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_18_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("{", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_18_0_0_3_0_0_0 = INSTANCE.getJAVA_18_0_0_3_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_18_0_0_3_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_18_0_0_3_0_0_1 = INSTANCE.getJAVA_18_0_0_3_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_18_0_0_3_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.BLOCK__STATEMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_18_0_0_3_0_0 = INSTANCE.getJAVA_18_0_0_3_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_18_0_0_3_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_18_0_0_3_0_0_0, JAVA_18_0_0_3_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_18_0_0_3_0 = INSTANCE.getJAVA_18_0_0_3_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_18_0_0_3_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_18_0_0_3_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_18_0_0_3 = INSTANCE.getJAVA_18_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_18_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_18_0_0_3_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_18_0_0_4 = INSTANCE.getJAVA_18_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_18_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_18_0_0_5 = INSTANCE.getJAVA_18_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_18_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("}", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_18_0_0 = INSTANCE.getJAVA_18_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_18_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_18_0_0_0, JAVA_18_0_0_1, JAVA_18_0_0_2, JAVA_18_0_0_3, JAVA_18_0_0_4, JAVA_18_0_0_5);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_18_0 = INSTANCE.getJAVA_18_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_18_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_18_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Block
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_18 = INSTANCE.getJAVA_18();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_18() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), JAVA_18_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_19_0_0_0 = INSTANCE.getJAVA_19_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_19_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__ANNOTATIONS_AND_MODIFIERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_19_0_0_1_0_0_0 = INSTANCE.getJAVA_19_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_19_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_19_0_0_1_0_0_1 = INSTANCE.getJAVA_19_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_19_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__TYPE_PARAMETERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_19_0_0_1_0_0_2_0_0_0 = INSTANCE.getJAVA_19_0_0_1_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_19_0_0_1_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_19_0_0_1_0_0_2_0_0_1 = INSTANCE.getJAVA_19_0_0_1_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_19_0_0_1_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__TYPE_PARAMETERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_19_0_0_1_0_0_2_0_0 = INSTANCE.getJAVA_19_0_0_1_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_19_0_0_1_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_19_0_0_1_0_0_2_0_0_0, JAVA_19_0_0_1_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_19_0_0_1_0_0_2_0 = INSTANCE.getJAVA_19_0_0_1_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_19_0_0_1_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_19_0_0_1_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_19_0_0_1_0_0_2 = INSTANCE.getJAVA_19_0_0_1_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_19_0_0_1_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_19_0_0_1_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_19_0_0_1_0_0_3 = INSTANCE.getJAVA_19_0_0_1_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_19_0_0_1_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_19_0_0_1_0_0 = INSTANCE.getJAVA_19_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_19_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_19_0_0_1_0_0_0, JAVA_19_0_0_1_0_0_1, JAVA_19_0_0_1_0_0_2, JAVA_19_0_0_1_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_19_0_0_1_0 = INSTANCE.getJAVA_19_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_19_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_19_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_19_0_0_1 = INSTANCE.getJAVA_19_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_19_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_19_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_19_0_0_2 = INSTANCE.getJAVA_19_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_19_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__NAME), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_19_0_0_3 = INSTANCE.getJAVA_19_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_19_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("(", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_19_0_0_4_0_0_0 = INSTANCE.getJAVA_19_0_0_4_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_19_0_0_4_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__PARAMETERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_19_0_0_4_0_0_1_0_0_0 = INSTANCE.getJAVA_19_0_0_4_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_19_0_0_4_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_19_0_0_4_0_0_1_0_0_1 = INSTANCE.getJAVA_19_0_0_4_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_19_0_0_4_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__PARAMETERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_19_0_0_4_0_0_1_0_0 = INSTANCE.getJAVA_19_0_0_4_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_19_0_0_4_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_19_0_0_4_0_0_1_0_0_0, JAVA_19_0_0_4_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_19_0_0_4_0_0_1_0 = INSTANCE.getJAVA_19_0_0_4_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_19_0_0_4_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_19_0_0_4_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_19_0_0_4_0_0_1 = INSTANCE.getJAVA_19_0_0_4_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_19_0_0_4_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_19_0_0_4_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_19_0_0_4_0_0 = INSTANCE.getJAVA_19_0_0_4_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_19_0_0_4_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_19_0_0_4_0_0_0, JAVA_19_0_0_4_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_19_0_0_4_0 = INSTANCE.getJAVA_19_0_0_4_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_19_0_0_4_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_19_0_0_4_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_19_0_0_4 = INSTANCE.getJAVA_19_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_19_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_19_0_0_4_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_19_0_0_5 = INSTANCE.getJAVA_19_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_19_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(")", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_19_0_0_6_0_0_0 = INSTANCE.getJAVA_19_0_0_6_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_19_0_0_6_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("throws", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_19_0_0_6_0_0_1 = INSTANCE.getJAVA_19_0_0_6_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_19_0_0_6_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__EXCEPTIONS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_19_0_0_6_0_0_2_0_0_0 = INSTANCE.getJAVA_19_0_0_6_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_19_0_0_6_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_19_0_0_6_0_0_2_0_0_1 = INSTANCE.getJAVA_19_0_0_6_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_19_0_0_6_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__EXCEPTIONS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_19_0_0_6_0_0_2_0_0 = INSTANCE.getJAVA_19_0_0_6_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_19_0_0_6_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_19_0_0_6_0_0_2_0_0_0, JAVA_19_0_0_6_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_19_0_0_6_0_0_2_0 = INSTANCE.getJAVA_19_0_0_6_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_19_0_0_6_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_19_0_0_6_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_19_0_0_6_0_0_2 = INSTANCE.getJAVA_19_0_0_6_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_19_0_0_6_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_19_0_0_6_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_19_0_0_6_0_0 = INSTANCE.getJAVA_19_0_0_6_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_19_0_0_6_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_19_0_0_6_0_0_0, JAVA_19_0_0_6_0_0_1, JAVA_19_0_0_6_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_19_0_0_6_0 = INSTANCE.getJAVA_19_0_0_6_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_19_0_0_6_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_19_0_0_6_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_19_0_0_6 = INSTANCE.getJAVA_19_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_19_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_19_0_0_6_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_19_0_0_7 = INSTANCE.getJAVA_19_0_0_7();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_19_0_0_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_19_0_0_8 = INSTANCE.getJAVA_19_0_0_8();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_19_0_0_8() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("{", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_19_0_0_9_0_0_0 = INSTANCE.getJAVA_19_0_0_9_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_19_0_0_9_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_19_0_0_9_0_0_1 = INSTANCE.getJAVA_19_0_0_9_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_19_0_0_9_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__STATEMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_19_0_0_9_0_0 = INSTANCE.getJAVA_19_0_0_9_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_19_0_0_9_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_19_0_0_9_0_0_0, JAVA_19_0_0_9_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_19_0_0_9_0 = INSTANCE.getJAVA_19_0_0_9_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_19_0_0_9_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_19_0_0_9_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_19_0_0_9 = INSTANCE.getJAVA_19_0_0_9();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_19_0_0_9() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_19_0_0_9_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_19_0_0_10 = INSTANCE.getJAVA_19_0_0_10();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_19_0_0_10() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_19_0_0_11 = INSTANCE.getJAVA_19_0_0_11();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_19_0_0_11() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("}", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_19_0_0 = INSTANCE.getJAVA_19_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_19_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_19_0_0_0, JAVA_19_0_0_1, JAVA_19_0_0_2, JAVA_19_0_0_3, JAVA_19_0_0_4, JAVA_19_0_0_5, JAVA_19_0_0_6, JAVA_19_0_0_7, JAVA_19_0_0_8, JAVA_19_0_0_9, JAVA_19_0_0_10, JAVA_19_0_0_11);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_19_0 = INSTANCE.getJAVA_19_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_19_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_19_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Constructor
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_19 = INSTANCE.getJAVA_19();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_19() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), JAVA_19_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_20_0_0_0 = INSTANCE.getJAVA_20_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_20_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__ANNOTATIONS_AND_MODIFIERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_20_0_0_1_0_0_0 = INSTANCE.getJAVA_20_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_20_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_20_0_0_1_0_0_1 = INSTANCE.getJAVA_20_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_20_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__TYPE_PARAMETERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_20_0_0_1_0_0_2_0_0_0 = INSTANCE.getJAVA_20_0_0_1_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_20_0_0_1_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_20_0_0_1_0_0_2_0_0_1 = INSTANCE.getJAVA_20_0_0_1_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_20_0_0_1_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__TYPE_PARAMETERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_20_0_0_1_0_0_2_0_0 = INSTANCE.getJAVA_20_0_0_1_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_20_0_0_1_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_20_0_0_1_0_0_2_0_0_0, JAVA_20_0_0_1_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_20_0_0_1_0_0_2_0 = INSTANCE.getJAVA_20_0_0_1_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_20_0_0_1_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_20_0_0_1_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_20_0_0_1_0_0_2 = INSTANCE.getJAVA_20_0_0_1_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_20_0_0_1_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_20_0_0_1_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_20_0_0_1_0_0_3 = INSTANCE.getJAVA_20_0_0_1_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_20_0_0_1_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_20_0_0_1_0_0 = INSTANCE.getJAVA_20_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_20_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_20_0_0_1_0_0_0, JAVA_20_0_0_1_0_0_1, JAVA_20_0_0_1_0_0_2, JAVA_20_0_0_1_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_20_0_0_1_0 = INSTANCE.getJAVA_20_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_20_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_20_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_20_0_0_1 = INSTANCE.getJAVA_20_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_20_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_20_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_20_0_0_2_0_0_0 = INSTANCE.getJAVA_20_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_20_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__TYPE_REFERENCE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_20_0_0_2_0_0_1 = INSTANCE.getJAVA_20_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_20_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__ARRAY_DIMENSIONS_BEFORE), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_20_0_0_2_0_0 = INSTANCE.getJAVA_20_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_20_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_20_0_0_2_0_0_0, JAVA_20_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_20_0_0_2_0 = INSTANCE.getJAVA_20_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_20_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_20_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_20_0_0_2 = INSTANCE.getJAVA_20_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_20_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_20_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_20_0_0_3 = INSTANCE.getJAVA_20_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_20_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__NAME), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_20_0_0_4 = INSTANCE.getJAVA_20_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_20_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("(", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_20_0_0_5_0_0_0 = INSTANCE.getJAVA_20_0_0_5_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_20_0_0_5_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__PARAMETERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_20_0_0_5_0_0_1_0_0_0 = INSTANCE.getJAVA_20_0_0_5_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_20_0_0_5_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_20_0_0_5_0_0_1_0_0_1 = INSTANCE.getJAVA_20_0_0_5_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_20_0_0_5_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__PARAMETERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_20_0_0_5_0_0_1_0_0 = INSTANCE.getJAVA_20_0_0_5_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_20_0_0_5_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_20_0_0_5_0_0_1_0_0_0, JAVA_20_0_0_5_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_20_0_0_5_0_0_1_0 = INSTANCE.getJAVA_20_0_0_5_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_20_0_0_5_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_20_0_0_5_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_20_0_0_5_0_0_1 = INSTANCE.getJAVA_20_0_0_5_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_20_0_0_5_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_20_0_0_5_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_20_0_0_5_0_0 = INSTANCE.getJAVA_20_0_0_5_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_20_0_0_5_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_20_0_0_5_0_0_0, JAVA_20_0_0_5_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_20_0_0_5_0 = INSTANCE.getJAVA_20_0_0_5_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_20_0_0_5_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_20_0_0_5_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_20_0_0_5 = INSTANCE.getJAVA_20_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_20_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_20_0_0_5_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_20_0_0_6 = INSTANCE.getJAVA_20_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_20_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(")", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_20_0_0_7 = INSTANCE.getJAVA_20_0_0_7();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_20_0_0_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__ARRAY_DIMENSIONS_AFTER), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_20_0_0_8_0_0_0 = INSTANCE.getJAVA_20_0_0_8_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_20_0_0_8_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("throws", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_20_0_0_8_0_0_1 = INSTANCE.getJAVA_20_0_0_8_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_20_0_0_8_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__EXCEPTIONS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_20_0_0_8_0_0_2_0_0_0 = INSTANCE.getJAVA_20_0_0_8_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_20_0_0_8_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_20_0_0_8_0_0_2_0_0_1 = INSTANCE.getJAVA_20_0_0_8_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_20_0_0_8_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__EXCEPTIONS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_20_0_0_8_0_0_2_0_0 = INSTANCE.getJAVA_20_0_0_8_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_20_0_0_8_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_20_0_0_8_0_0_2_0_0_0, JAVA_20_0_0_8_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_20_0_0_8_0_0_2_0 = INSTANCE.getJAVA_20_0_0_8_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_20_0_0_8_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_20_0_0_8_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_20_0_0_8_0_0_2 = INSTANCE.getJAVA_20_0_0_8_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_20_0_0_8_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_20_0_0_8_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_20_0_0_8_0_0 = INSTANCE.getJAVA_20_0_0_8_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_20_0_0_8_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_20_0_0_8_0_0_0, JAVA_20_0_0_8_0_0_1, JAVA_20_0_0_8_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_20_0_0_8_0 = INSTANCE.getJAVA_20_0_0_8_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_20_0_0_8_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_20_0_0_8_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_20_0_0_8 = INSTANCE.getJAVA_20_0_0_8();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_20_0_0_8() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_20_0_0_8_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_20_0_0_9 = INSTANCE.getJAVA_20_0_0_9();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_20_0_0_9() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_20_0_0 = INSTANCE.getJAVA_20_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_20_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_20_0_0_0, JAVA_20_0_0_1, JAVA_20_0_0_2, JAVA_20_0_0_3, JAVA_20_0_0_4, JAVA_20_0_0_5, JAVA_20_0_0_6, JAVA_20_0_0_7, JAVA_20_0_0_8, JAVA_20_0_0_9);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_20_0 = INSTANCE.getJAVA_20_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_20_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_20_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * InterfaceMethod
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_20 = INSTANCE.getJAVA_20();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_20() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), JAVA_20_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_21_0_0_0 = INSTANCE.getJAVA_21_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_21_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__ANNOTATIONS_AND_MODIFIERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_21_0_0_1_0_0_0 = INSTANCE.getJAVA_21_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_21_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_21_0_0_1_0_0_1 = INSTANCE.getJAVA_21_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_21_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__TYPE_PARAMETERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_21_0_0_1_0_0_2_0_0_0 = INSTANCE.getJAVA_21_0_0_1_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_21_0_0_1_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_21_0_0_1_0_0_2_0_0_1 = INSTANCE.getJAVA_21_0_0_1_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_21_0_0_1_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__TYPE_PARAMETERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_21_0_0_1_0_0_2_0_0 = INSTANCE.getJAVA_21_0_0_1_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_21_0_0_1_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_21_0_0_1_0_0_2_0_0_0, JAVA_21_0_0_1_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_21_0_0_1_0_0_2_0 = INSTANCE.getJAVA_21_0_0_1_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_21_0_0_1_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_21_0_0_1_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_21_0_0_1_0_0_2 = INSTANCE.getJAVA_21_0_0_1_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_21_0_0_1_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_21_0_0_1_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_21_0_0_1_0_0_3 = INSTANCE.getJAVA_21_0_0_1_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_21_0_0_1_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_21_0_0_1_0_0 = INSTANCE.getJAVA_21_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_21_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_21_0_0_1_0_0_0, JAVA_21_0_0_1_0_0_1, JAVA_21_0_0_1_0_0_2, JAVA_21_0_0_1_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_21_0_0_1_0 = INSTANCE.getJAVA_21_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_21_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_21_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_21_0_0_1 = INSTANCE.getJAVA_21_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_21_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_21_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_21_0_0_2_0_0_0 = INSTANCE.getJAVA_21_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_21_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__TYPE_REFERENCE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_21_0_0_2_0_0_1 = INSTANCE.getJAVA_21_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_21_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__ARRAY_DIMENSIONS_BEFORE), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_21_0_0_2_0_0 = INSTANCE.getJAVA_21_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_21_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_21_0_0_2_0_0_0, JAVA_21_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_21_0_0_2_0 = INSTANCE.getJAVA_21_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_21_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_21_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_21_0_0_2 = INSTANCE.getJAVA_21_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_21_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_21_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_21_0_0_3 = INSTANCE.getJAVA_21_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_21_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__NAME), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_21_0_0_4 = INSTANCE.getJAVA_21_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_21_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("(", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_21_0_0_5_0_0_0 = INSTANCE.getJAVA_21_0_0_5_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_21_0_0_5_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__PARAMETERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_21_0_0_5_0_0_1_0_0_0 = INSTANCE.getJAVA_21_0_0_5_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_21_0_0_5_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_21_0_0_5_0_0_1_0_0_1 = INSTANCE.getJAVA_21_0_0_5_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_21_0_0_5_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__PARAMETERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_21_0_0_5_0_0_1_0_0 = INSTANCE.getJAVA_21_0_0_5_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_21_0_0_5_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_21_0_0_5_0_0_1_0_0_0, JAVA_21_0_0_5_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_21_0_0_5_0_0_1_0 = INSTANCE.getJAVA_21_0_0_5_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_21_0_0_5_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_21_0_0_5_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_21_0_0_5_0_0_1 = INSTANCE.getJAVA_21_0_0_5_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_21_0_0_5_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_21_0_0_5_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_21_0_0_5_0_0 = INSTANCE.getJAVA_21_0_0_5_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_21_0_0_5_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_21_0_0_5_0_0_0, JAVA_21_0_0_5_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_21_0_0_5_0 = INSTANCE.getJAVA_21_0_0_5_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_21_0_0_5_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_21_0_0_5_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_21_0_0_5 = INSTANCE.getJAVA_21_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_21_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_21_0_0_5_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_21_0_0_6 = INSTANCE.getJAVA_21_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_21_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(")", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_21_0_0_7 = INSTANCE.getJAVA_21_0_0_7();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_21_0_0_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__ARRAY_DIMENSIONS_AFTER), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_21_0_0_8_0_0_0 = INSTANCE.getJAVA_21_0_0_8_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_21_0_0_8_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("throws", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_21_0_0_8_0_0_1 = INSTANCE.getJAVA_21_0_0_8_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_21_0_0_8_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__EXCEPTIONS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_21_0_0_8_0_0_2_0_0_0 = INSTANCE.getJAVA_21_0_0_8_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_21_0_0_8_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_21_0_0_8_0_0_2_0_0_1 = INSTANCE.getJAVA_21_0_0_8_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_21_0_0_8_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__EXCEPTIONS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_21_0_0_8_0_0_2_0_0 = INSTANCE.getJAVA_21_0_0_8_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_21_0_0_8_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_21_0_0_8_0_0_2_0_0_0, JAVA_21_0_0_8_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_21_0_0_8_0_0_2_0 = INSTANCE.getJAVA_21_0_0_8_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_21_0_0_8_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_21_0_0_8_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_21_0_0_8_0_0_2 = INSTANCE.getJAVA_21_0_0_8_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_21_0_0_8_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_21_0_0_8_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_21_0_0_8_0_0 = INSTANCE.getJAVA_21_0_0_8_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_21_0_0_8_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_21_0_0_8_0_0_0, JAVA_21_0_0_8_0_0_1, JAVA_21_0_0_8_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_21_0_0_8_0 = INSTANCE.getJAVA_21_0_0_8_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_21_0_0_8_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_21_0_0_8_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_21_0_0_8 = INSTANCE.getJAVA_21_0_0_8();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_21_0_0_8() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_21_0_0_8_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_21_0_0_9 = INSTANCE.getJAVA_21_0_0_9();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_21_0_0_9() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_21_0_0_10 = INSTANCE.getJAVA_21_0_0_10();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_21_0_0_10() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("{", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_21_0_0_11_0_0_0 = INSTANCE.getJAVA_21_0_0_11_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_21_0_0_11_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_21_0_0_11_0_0_1 = INSTANCE.getJAVA_21_0_0_11_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_21_0_0_11_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__STATEMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_21_0_0_11_0_0 = INSTANCE.getJAVA_21_0_0_11_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_21_0_0_11_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_21_0_0_11_0_0_0, JAVA_21_0_0_11_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_21_0_0_11_0 = INSTANCE.getJAVA_21_0_0_11_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_21_0_0_11_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_21_0_0_11_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_21_0_0_11 = INSTANCE.getJAVA_21_0_0_11();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_21_0_0_11() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_21_0_0_11_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_21_0_0_12 = INSTANCE.getJAVA_21_0_0_12();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_21_0_0_12() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_21_0_0_13 = INSTANCE.getJAVA_21_0_0_13();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_21_0_0_13() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("}", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_21_0_0 = INSTANCE.getJAVA_21_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_21_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_21_0_0_0, JAVA_21_0_0_1, JAVA_21_0_0_2, JAVA_21_0_0_3, JAVA_21_0_0_4, JAVA_21_0_0_5, JAVA_21_0_0_6, JAVA_21_0_0_7, JAVA_21_0_0_8, JAVA_21_0_0_9, JAVA_21_0_0_10, JAVA_21_0_0_11, JAVA_21_0_0_12, JAVA_21_0_0_13);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_21_0 = INSTANCE.getJAVA_21_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_21_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_21_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class ClassMethod
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_21 = INSTANCE.getJAVA_21();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_21() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), JAVA_21_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_22_0_0_0 = INSTANCE.getJAVA_22_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_22_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__ANNOTATIONS_AND_MODIFIERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_22_0_0_1_0_0_0 = INSTANCE.getJAVA_22_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_22_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_22_0_0_1_0_0_1 = INSTANCE.getJAVA_22_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_22_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__TYPE_PARAMETERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_22_0_0_1_0_0_2_0_0_0 = INSTANCE.getJAVA_22_0_0_1_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_22_0_0_1_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_22_0_0_1_0_0_2_0_0_1 = INSTANCE.getJAVA_22_0_0_1_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_22_0_0_1_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__TYPE_PARAMETERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_22_0_0_1_0_0_2_0_0 = INSTANCE.getJAVA_22_0_0_1_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_22_0_0_1_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_22_0_0_1_0_0_2_0_0_0, JAVA_22_0_0_1_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_22_0_0_1_0_0_2_0 = INSTANCE.getJAVA_22_0_0_1_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_22_0_0_1_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_22_0_0_1_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_22_0_0_1_0_0_2 = INSTANCE.getJAVA_22_0_0_1_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_22_0_0_1_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_22_0_0_1_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_22_0_0_1_0_0_3 = INSTANCE.getJAVA_22_0_0_1_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_22_0_0_1_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_22_0_0_1_0_0 = INSTANCE.getJAVA_22_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_22_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_22_0_0_1_0_0_0, JAVA_22_0_0_1_0_0_1, JAVA_22_0_0_1_0_0_2, JAVA_22_0_0_1_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_22_0_0_1_0 = INSTANCE.getJAVA_22_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_22_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_22_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_22_0_0_1 = INSTANCE.getJAVA_22_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_22_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_22_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_22_0_0_2_0_0_0 = INSTANCE.getJAVA_22_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_22_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__TYPE_REFERENCE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_22_0_0_2_0_0_1 = INSTANCE.getJAVA_22_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_22_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__ARRAY_DIMENSIONS_BEFORE), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_22_0_0_2_0_0 = INSTANCE.getJAVA_22_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_22_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_22_0_0_2_0_0_0, JAVA_22_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_22_0_0_2_0 = INSTANCE.getJAVA_22_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_22_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_22_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_22_0_0_2 = INSTANCE.getJAVA_22_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_22_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_22_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_22_0_0_3 = INSTANCE.getJAVA_22_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_22_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__NAME), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_22_0_0_4 = INSTANCE.getJAVA_22_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_22_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("(", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_22_0_0_5_0_0_0 = INSTANCE.getJAVA_22_0_0_5_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_22_0_0_5_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__PARAMETERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_22_0_0_5_0_0_1_0_0_0 = INSTANCE.getJAVA_22_0_0_5_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_22_0_0_5_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_22_0_0_5_0_0_1_0_0_1 = INSTANCE.getJAVA_22_0_0_5_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_22_0_0_5_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__PARAMETERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_22_0_0_5_0_0_1_0_0 = INSTANCE.getJAVA_22_0_0_5_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_22_0_0_5_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_22_0_0_5_0_0_1_0_0_0, JAVA_22_0_0_5_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_22_0_0_5_0_0_1_0 = INSTANCE.getJAVA_22_0_0_5_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_22_0_0_5_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_22_0_0_5_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_22_0_0_5_0_0_1 = INSTANCE.getJAVA_22_0_0_5_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_22_0_0_5_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_22_0_0_5_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_22_0_0_5_0_0 = INSTANCE.getJAVA_22_0_0_5_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_22_0_0_5_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_22_0_0_5_0_0_0, JAVA_22_0_0_5_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_22_0_0_5_0 = INSTANCE.getJAVA_22_0_0_5_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_22_0_0_5_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_22_0_0_5_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_22_0_0_5 = INSTANCE.getJAVA_22_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_22_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_22_0_0_5_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_22_0_0_6 = INSTANCE.getJAVA_22_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_22_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(")", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_22_0_0_7 = INSTANCE.getJAVA_22_0_0_7();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_22_0_0_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__ARRAY_DIMENSIONS_AFTER), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_22_0_0_8_0_0_0 = INSTANCE.getJAVA_22_0_0_8_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_22_0_0_8_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("throws", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_22_0_0_8_0_0_1 = INSTANCE.getJAVA_22_0_0_8_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_22_0_0_8_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__EXCEPTIONS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_22_0_0_8_0_0_2_0_0_0 = INSTANCE.getJAVA_22_0_0_8_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_22_0_0_8_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_22_0_0_8_0_0_2_0_0_1 = INSTANCE.getJAVA_22_0_0_8_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_22_0_0_8_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__EXCEPTIONS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_22_0_0_8_0_0_2_0_0 = INSTANCE.getJAVA_22_0_0_8_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_22_0_0_8_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_22_0_0_8_0_0_2_0_0_0, JAVA_22_0_0_8_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_22_0_0_8_0_0_2_0 = INSTANCE.getJAVA_22_0_0_8_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_22_0_0_8_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_22_0_0_8_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_22_0_0_8_0_0_2 = INSTANCE.getJAVA_22_0_0_8_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_22_0_0_8_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_22_0_0_8_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_22_0_0_8_0_0 = INSTANCE.getJAVA_22_0_0_8_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_22_0_0_8_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_22_0_0_8_0_0_0, JAVA_22_0_0_8_0_0_1, JAVA_22_0_0_8_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_22_0_0_8_0 = INSTANCE.getJAVA_22_0_0_8_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_22_0_0_8_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_22_0_0_8_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_22_0_0_8 = INSTANCE.getJAVA_22_0_0_8();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_22_0_0_8() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_22_0_0_8_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_22_0_0_9 = INSTANCE.getJAVA_22_0_0_9();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_22_0_0_9() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("default", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_22_0_0_10 = INSTANCE.getJAVA_22_0_0_10();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_22_0_0_10() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__DEFAULT_VALUE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_22_0_0_11 = INSTANCE.getJAVA_22_0_0_11();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_22_0_0_11() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_22_0_0 = INSTANCE.getJAVA_22_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_22_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_22_0_0_0, JAVA_22_0_0_1, JAVA_22_0_0_2, JAVA_22_0_0_3, JAVA_22_0_0_4, JAVA_22_0_0_5, JAVA_22_0_0_6, JAVA_22_0_0_7, JAVA_22_0_0_8, JAVA_22_0_0_9, JAVA_22_0_0_10, JAVA_22_0_0_11);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_22_0 = INSTANCE.getJAVA_22_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_22_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_22_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * AnnotationAttribute
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_22 = INSTANCE.getJAVA_22();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_22() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), JAVA_22_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_23_0_0_0 = INSTANCE.getJAVA_23_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_23_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__ANNOTATIONS_AND_MODIFIERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_23_0_0_1 = INSTANCE.getJAVA_23_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_23_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__TYPE_REFERENCE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_23_0_0_2 = INSTANCE.getJAVA_23_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_23_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__ARRAY_DIMENSIONS_BEFORE), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_23_0_0_3_0_0_0 = INSTANCE.getJAVA_23_0_0_3_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_23_0_0_3_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_23_0_0_3_0_0_1 = INSTANCE.getJAVA_23_0_0_3_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_23_0_0_3_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_23_0_0_3_0_0_2_0_0_0 = INSTANCE.getJAVA_23_0_0_3_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_23_0_0_3_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_23_0_0_3_0_0_2_0_0_1 = INSTANCE.getJAVA_23_0_0_3_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_23_0_0_3_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_23_0_0_3_0_0_2_0_0 = INSTANCE.getJAVA_23_0_0_3_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_23_0_0_3_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_23_0_0_3_0_0_2_0_0_0, JAVA_23_0_0_3_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_23_0_0_3_0_0_2_0 = INSTANCE.getJAVA_23_0_0_3_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_23_0_0_3_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_23_0_0_3_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_23_0_0_3_0_0_2 = INSTANCE.getJAVA_23_0_0_3_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_23_0_0_3_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_23_0_0_3_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_23_0_0_3_0_0_3 = INSTANCE.getJAVA_23_0_0_3_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_23_0_0_3_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_23_0_0_3_0_0 = INSTANCE.getJAVA_23_0_0_3_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_23_0_0_3_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_23_0_0_3_0_0_0, JAVA_23_0_0_3_0_0_1, JAVA_23_0_0_3_0_0_2, JAVA_23_0_0_3_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_23_0_0_3_0 = INSTANCE.getJAVA_23_0_0_3_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_23_0_0_3_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_23_0_0_3_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_23_0_0_3 = INSTANCE.getJAVA_23_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_23_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_23_0_0_3_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_23_0_0_4 = INSTANCE.getJAVA_23_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_23_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__NAME), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_23_0_0_5 = INSTANCE.getJAVA_23_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_23_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__ARRAY_DIMENSIONS_AFTER), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_23_0_0 = INSTANCE.getJAVA_23_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_23_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_23_0_0_0, JAVA_23_0_0_1, JAVA_23_0_0_2, JAVA_23_0_0_3, JAVA_23_0_0_4, JAVA_23_0_0_5);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_23_0 = INSTANCE.getJAVA_23_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_23_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_23_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * OrdinaryParameter
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_23 = INSTANCE.getJAVA_23();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_23() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), JAVA_23_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_24_0_0_0 = INSTANCE.getJAVA_24_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_24_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__ANNOTATIONS_AND_MODIFIERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_24_0_0_1 = INSTANCE.getJAVA_24_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_24_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__TYPE_REFERENCE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_24_0_0_2 = INSTANCE.getJAVA_24_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_24_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__ARRAY_DIMENSIONS_BEFORE), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_24_0_0_3_0_0_0 = INSTANCE.getJAVA_24_0_0_3_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_24_0_0_3_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_24_0_0_3_0_0_1 = INSTANCE.getJAVA_24_0_0_3_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_24_0_0_3_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_24_0_0_3_0_0_2_0_0_0 = INSTANCE.getJAVA_24_0_0_3_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_24_0_0_3_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_24_0_0_3_0_0_2_0_0_1 = INSTANCE.getJAVA_24_0_0_3_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_24_0_0_3_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_24_0_0_3_0_0_2_0_0 = INSTANCE.getJAVA_24_0_0_3_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_24_0_0_3_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_24_0_0_3_0_0_2_0_0_0, JAVA_24_0_0_3_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_24_0_0_3_0_0_2_0 = INSTANCE.getJAVA_24_0_0_3_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_24_0_0_3_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_24_0_0_3_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_24_0_0_3_0_0_2 = INSTANCE.getJAVA_24_0_0_3_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_24_0_0_3_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_24_0_0_3_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_24_0_0_3_0_0_3 = INSTANCE.getJAVA_24_0_0_3_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_24_0_0_3_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_24_0_0_3_0_0 = INSTANCE.getJAVA_24_0_0_3_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_24_0_0_3_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_24_0_0_3_0_0_0, JAVA_24_0_0_3_0_0_1, JAVA_24_0_0_3_0_0_2, JAVA_24_0_0_3_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_24_0_0_3_0 = INSTANCE.getJAVA_24_0_0_3_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_24_0_0_3_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_24_0_0_3_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_24_0_0_3 = INSTANCE.getJAVA_24_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_24_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_24_0_0_3_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_24_0_0_4 = INSTANCE.getJAVA_24_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_24_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("...", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_24_0_0_5 = INSTANCE.getJAVA_24_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_24_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__NAME), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_24_0_0 = INSTANCE.getJAVA_24_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_24_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_24_0_0_0, JAVA_24_0_0_1, JAVA_24_0_0_2, JAVA_24_0_0_3, JAVA_24_0_0_4, JAVA_24_0_0_5);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_24_0 = INSTANCE.getJAVA_24_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_24_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_24_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * VariableLengthParameter
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_24 = INSTANCE.getJAVA_24();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_24() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), JAVA_24_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_25_0_0_0 = INSTANCE.getJAVA_25_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_25_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__ANNOTATIONS_AND_MODIFIERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_25_0_0_1 = INSTANCE.getJAVA_25_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_25_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__TYPE_REFERENCE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_25_0_0_2 = INSTANCE.getJAVA_25_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_25_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__ARRAY_DIMENSIONS_BEFORE), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_25_0_0_3_0_0_0 = INSTANCE.getJAVA_25_0_0_3_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_25_0_0_3_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_25_0_0_3_0_0_1 = INSTANCE.getJAVA_25_0_0_3_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_25_0_0_3_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_25_0_0_3_0_0_2_0_0_0 = INSTANCE.getJAVA_25_0_0_3_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_25_0_0_3_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_25_0_0_3_0_0_2_0_0_1 = INSTANCE.getJAVA_25_0_0_3_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_25_0_0_3_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_25_0_0_3_0_0_2_0_0 = INSTANCE.getJAVA_25_0_0_3_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_25_0_0_3_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_25_0_0_3_0_0_2_0_0_0, JAVA_25_0_0_3_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_25_0_0_3_0_0_2_0 = INSTANCE.getJAVA_25_0_0_3_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_25_0_0_3_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_25_0_0_3_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_25_0_0_3_0_0_2 = INSTANCE.getJAVA_25_0_0_3_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_25_0_0_3_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_25_0_0_3_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_25_0_0_3_0_0_3 = INSTANCE.getJAVA_25_0_0_3_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_25_0_0_3_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_25_0_0_3_0_0 = INSTANCE.getJAVA_25_0_0_3_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_25_0_0_3_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_25_0_0_3_0_0_0, JAVA_25_0_0_3_0_0_1, JAVA_25_0_0_3_0_0_2, JAVA_25_0_0_3_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_25_0_0_3_0 = INSTANCE.getJAVA_25_0_0_3_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_25_0_0_3_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_25_0_0_3_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_25_0_0_3 = INSTANCE.getJAVA_25_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_25_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_25_0_0_3_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_25_0_0_4 = INSTANCE.getJAVA_25_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_25_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__NAME), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_25_0_0_5 = INSTANCE.getJAVA_25_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_25_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__ARRAY_DIMENSIONS_AFTER), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_25_0_0_6_0_0_0 = INSTANCE.getJAVA_25_0_0_6_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_25_0_0_6_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_25_0_0_6_0_0_1 = INSTANCE.getJAVA_25_0_0_6_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_25_0_0_6_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("=", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_25_0_0_6_0_0_2 = INSTANCE.getJAVA_25_0_0_6_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_25_0_0_6_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_25_0_0_6_0_0_3 = INSTANCE.getJAVA_25_0_0_6_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_25_0_0_6_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__INITIAL_VALUE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_25_0_0_6_0_0 = INSTANCE.getJAVA_25_0_0_6_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_25_0_0_6_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_25_0_0_6_0_0_0, JAVA_25_0_0_6_0_0_1, JAVA_25_0_0_6_0_0_2, JAVA_25_0_0_6_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_25_0_0_6_0 = INSTANCE.getJAVA_25_0_0_6_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_25_0_0_6_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_25_0_0_6_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_25_0_0_6 = INSTANCE.getJAVA_25_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_25_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_25_0_0_6_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_25_0_0_7_0_0_0 = INSTANCE.getJAVA_25_0_0_7_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_25_0_0_7_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_25_0_0_7_0_0_1 = INSTANCE.getJAVA_25_0_0_7_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_25_0_0_7_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getAdditionalLocalVariable(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_25_0_0_7_0_0 = INSTANCE.getJAVA_25_0_0_7_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_25_0_0_7_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_25_0_0_7_0_0_0, JAVA_25_0_0_7_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_25_0_0_7_0 = INSTANCE.getJAVA_25_0_0_7_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_25_0_0_7_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_25_0_0_7_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_25_0_0_7 = INSTANCE.getJAVA_25_0_0_7();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_25_0_0_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_25_0_0_7_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_25_0_0 = INSTANCE.getJAVA_25_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_25_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_25_0_0_0, JAVA_25_0_0_1, JAVA_25_0_0_2, JAVA_25_0_0_3, JAVA_25_0_0_4, JAVA_25_0_0_5, JAVA_25_0_0_6, JAVA_25_0_0_7);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_25_0 = INSTANCE.getJAVA_25_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_25_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_25_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * LocalVariable
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_25 = INSTANCE.getJAVA_25();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_25() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), JAVA_25_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_26_0_0_0 = INSTANCE.getJAVA_26_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_26_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getLocalVariableStatement().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.LOCAL_VARIABLE_STATEMENT__VARIABLE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_26_0_0_1 = INSTANCE.getJAVA_26_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_26_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_26_0_0 = INSTANCE.getJAVA_26_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_26_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_26_0_0_0, JAVA_26_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_26_0 = INSTANCE.getJAVA_26_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_26_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_26_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * LocalVariableStatement
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_26 = INSTANCE.getJAVA_26();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_26() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getLocalVariableStatement(), JAVA_26_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_27_0_0_0 = INSTANCE.getJAVA_27_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_27_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getAdditionalLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.ADDITIONAL_LOCAL_VARIABLE__NAME), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_27_0_0_1 = INSTANCE.getJAVA_27_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_27_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getAdditionalLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.ADDITIONAL_LOCAL_VARIABLE__ARRAY_DIMENSIONS_AFTER), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_27_0_0_2_0_0_0 = INSTANCE.getJAVA_27_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_27_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_27_0_0_2_0_0_1 = INSTANCE.getJAVA_27_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_27_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("=", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_27_0_0_2_0_0_2 = INSTANCE.getJAVA_27_0_0_2_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_27_0_0_2_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_27_0_0_2_0_0_3 = INSTANCE.getJAVA_27_0_0_2_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_27_0_0_2_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getAdditionalLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.ADDITIONAL_LOCAL_VARIABLE__INITIAL_VALUE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_27_0_0_2_0_0 = INSTANCE.getJAVA_27_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_27_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_27_0_0_2_0_0_0, JAVA_27_0_0_2_0_0_1, JAVA_27_0_0_2_0_0_2, JAVA_27_0_0_2_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_27_0_0_2_0 = INSTANCE.getJAVA_27_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_27_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_27_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_27_0_0_2 = INSTANCE.getJAVA_27_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_27_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_27_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_27_0_0 = INSTANCE.getJAVA_27_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_27_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_27_0_0_0, JAVA_27_0_0_1, JAVA_27_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_27_0 = INSTANCE.getJAVA_27_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_27_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_27_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * AdditionalLocalVariable
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_27 = INSTANCE.getJAVA_27();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_27() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getAdditionalLocalVariable(), JAVA_27_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_28_0_0_0 = INSTANCE.getJAVA_28_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_28_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__ANNOTATIONS_AND_MODIFIERS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_28_0_0_1 = INSTANCE.getJAVA_28_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_28_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__TYPE_REFERENCE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_28_0_0_2 = INSTANCE.getJAVA_28_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_28_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__ARRAY_DIMENSIONS_BEFORE), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_28_0_0_3_0_0_0 = INSTANCE.getJAVA_28_0_0_3_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_28_0_0_3_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_28_0_0_3_0_0_1 = INSTANCE.getJAVA_28_0_0_3_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_28_0_0_3_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_28_0_0_3_0_0_2_0_0_0 = INSTANCE.getJAVA_28_0_0_3_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_28_0_0_3_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_28_0_0_3_0_0_2_0_0_1 = INSTANCE.getJAVA_28_0_0_3_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_28_0_0_3_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_28_0_0_3_0_0_2_0_0 = INSTANCE.getJAVA_28_0_0_3_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_28_0_0_3_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_28_0_0_3_0_0_2_0_0_0, JAVA_28_0_0_3_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_28_0_0_3_0_0_2_0 = INSTANCE.getJAVA_28_0_0_3_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_28_0_0_3_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_28_0_0_3_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_28_0_0_3_0_0_2 = INSTANCE.getJAVA_28_0_0_3_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_28_0_0_3_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_28_0_0_3_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_28_0_0_3_0_0_3 = INSTANCE.getJAVA_28_0_0_3_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_28_0_0_3_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_28_0_0_3_0_0 = INSTANCE.getJAVA_28_0_0_3_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_28_0_0_3_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_28_0_0_3_0_0_0, JAVA_28_0_0_3_0_0_1, JAVA_28_0_0_3_0_0_2, JAVA_28_0_0_3_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_28_0_0_3_0 = INSTANCE.getJAVA_28_0_0_3_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_28_0_0_3_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_28_0_0_3_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_28_0_0_3 = INSTANCE.getJAVA_28_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_28_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_28_0_0_3_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_28_0_0_4 = INSTANCE.getJAVA_28_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_28_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__NAME), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_28_0_0_5 = INSTANCE.getJAVA_28_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_28_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__ARRAY_DIMENSIONS_AFTER), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_28_0_0_6_0_0_0 = INSTANCE.getJAVA_28_0_0_6_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_28_0_0_6_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_28_0_0_6_0_0_1 = INSTANCE.getJAVA_28_0_0_6_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_28_0_0_6_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("=", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_28_0_0_6_0_0_2 = INSTANCE.getJAVA_28_0_0_6_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_28_0_0_6_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_28_0_0_6_0_0_3 = INSTANCE.getJAVA_28_0_0_6_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_28_0_0_6_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__INITIAL_VALUE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_28_0_0_6_0_0 = INSTANCE.getJAVA_28_0_0_6_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_28_0_0_6_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_28_0_0_6_0_0_0, JAVA_28_0_0_6_0_0_1, JAVA_28_0_0_6_0_0_2, JAVA_28_0_0_6_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_28_0_0_6_0 = INSTANCE.getJAVA_28_0_0_6_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_28_0_0_6_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_28_0_0_6_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_28_0_0_6 = INSTANCE.getJAVA_28_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_28_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_28_0_0_6_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_28_0_0_7_0_0_0 = INSTANCE.getJAVA_28_0_0_7_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_28_0_0_7_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_28_0_0_7_0_0_1 = INSTANCE.getJAVA_28_0_0_7_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_28_0_0_7_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__ADDITIONAL_FIELDS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.members.MembersPackage.eINSTANCE.getAdditionalField(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_28_0_0_7_0_0 = INSTANCE.getJAVA_28_0_0_7_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_28_0_0_7_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_28_0_0_7_0_0_0, JAVA_28_0_0_7_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_28_0_0_7_0 = INSTANCE.getJAVA_28_0_0_7_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_28_0_0_7_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_28_0_0_7_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_28_0_0_7 = INSTANCE.getJAVA_28_0_0_7();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_28_0_0_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_28_0_0_7_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_28_0_0_8 = INSTANCE.getJAVA_28_0_0_8();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_28_0_0_8() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_28_0_0 = INSTANCE.getJAVA_28_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_28_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_28_0_0_0, JAVA_28_0_0_1, JAVA_28_0_0_2, JAVA_28_0_0_3, JAVA_28_0_0_4, JAVA_28_0_0_5, JAVA_28_0_0_6, JAVA_28_0_0_7, JAVA_28_0_0_8);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_28_0 = INSTANCE.getJAVA_28_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_28_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_28_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Field
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_28 = INSTANCE.getJAVA_28();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_28() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), JAVA_28_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_29_0_0_0 = INSTANCE.getJAVA_29_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_29_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.members.MembersPackage.eINSTANCE.getAdditionalField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ADDITIONAL_FIELD__NAME), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_29_0_0_1 = INSTANCE.getJAVA_29_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_29_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getAdditionalField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ADDITIONAL_FIELD__ARRAY_DIMENSIONS_AFTER), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_29_0_0_2_0_0_0 = INSTANCE.getJAVA_29_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_29_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_29_0_0_2_0_0_1 = INSTANCE.getJAVA_29_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_29_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("=", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_29_0_0_2_0_0_2 = INSTANCE.getJAVA_29_0_0_2_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_29_0_0_2_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_29_0_0_2_0_0_3 = INSTANCE.getJAVA_29_0_0_2_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_29_0_0_2_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getAdditionalField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ADDITIONAL_FIELD__INITIAL_VALUE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_29_0_0_2_0_0 = INSTANCE.getJAVA_29_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_29_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_29_0_0_2_0_0_0, JAVA_29_0_0_2_0_0_1, JAVA_29_0_0_2_0_0_2, JAVA_29_0_0_2_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_29_0_0_2_0 = INSTANCE.getJAVA_29_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_29_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_29_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_29_0_0_2 = INSTANCE.getJAVA_29_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_29_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_29_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_29_0_0 = INSTANCE.getJAVA_29_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_29_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_29_0_0_0, JAVA_29_0_0_1, JAVA_29_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_29_0 = INSTANCE.getJAVA_29_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_29_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_29_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * AdditionalField
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_29 = INSTANCE.getJAVA_29();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_29() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.members.MembersPackage.eINSTANCE.getAdditionalField(), JAVA_29_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_30_0_0_0 = INSTANCE.getJAVA_30_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_30_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_30_0_0 = INSTANCE.getJAVA_30_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_30_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_30_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_30_0 = INSTANCE.getJAVA_30_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_30_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_30_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class EmptyMember
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_30 = INSTANCE.getJAVA_30();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_30() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEmptyMember(), JAVA_30_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_31_0_0_0 = INSTANCE.getJAVA_31_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_31_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("new", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_31_0_0_1_0_0_0 = INSTANCE.getJAVA_31_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_31_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_31_0_0_1_0_0_1 = INSTANCE.getJAVA_31_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_31_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_31_0_0_1_0_0_2_0_0_0 = INSTANCE.getJAVA_31_0_0_1_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_31_0_0_1_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_31_0_0_1_0_0_2_0_0_1 = INSTANCE.getJAVA_31_0_0_1_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_31_0_0_1_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_31_0_0_1_0_0_2_0_0 = INSTANCE.getJAVA_31_0_0_1_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_31_0_0_1_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_31_0_0_1_0_0_2_0_0_0, JAVA_31_0_0_1_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_31_0_0_1_0_0_2_0 = INSTANCE.getJAVA_31_0_0_1_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_31_0_0_1_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_31_0_0_1_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_31_0_0_1_0_0_2 = INSTANCE.getJAVA_31_0_0_1_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_31_0_0_1_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_31_0_0_1_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_31_0_0_1_0_0_3 = INSTANCE.getJAVA_31_0_0_1_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_31_0_0_1_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_31_0_0_1_0_0 = INSTANCE.getJAVA_31_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_31_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_31_0_0_1_0_0_0, JAVA_31_0_0_1_0_0_1, JAVA_31_0_0_1_0_0_2, JAVA_31_0_0_1_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_31_0_0_1_0 = INSTANCE.getJAVA_31_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_31_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_31_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_31_0_0_1 = INSTANCE.getJAVA_31_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_31_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_31_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_31_0_0_2 = INSTANCE.getJAVA_31_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_31_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_REFERENCE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_31_0_0_3_0_0_0 = INSTANCE.getJAVA_31_0_0_3_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_31_0_0_3_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_31_0_0_3_0_0_1 = INSTANCE.getJAVA_31_0_0_3_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_31_0_0_3_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__CALL_TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_31_0_0_3_0_0_2_0_0_0 = INSTANCE.getJAVA_31_0_0_3_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_31_0_0_3_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_31_0_0_3_0_0_2_0_0_1 = INSTANCE.getJAVA_31_0_0_3_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_31_0_0_3_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__CALL_TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_31_0_0_3_0_0_2_0_0 = INSTANCE.getJAVA_31_0_0_3_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_31_0_0_3_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_31_0_0_3_0_0_2_0_0_0, JAVA_31_0_0_3_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_31_0_0_3_0_0_2_0 = INSTANCE.getJAVA_31_0_0_3_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_31_0_0_3_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_31_0_0_3_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_31_0_0_3_0_0_2 = INSTANCE.getJAVA_31_0_0_3_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_31_0_0_3_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_31_0_0_3_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_31_0_0_3_0_0_3 = INSTANCE.getJAVA_31_0_0_3_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_31_0_0_3_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_31_0_0_3_0_0 = INSTANCE.getJAVA_31_0_0_3_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_31_0_0_3_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_31_0_0_3_0_0_0, JAVA_31_0_0_3_0_0_1, JAVA_31_0_0_3_0_0_2, JAVA_31_0_0_3_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_31_0_0_3_0 = INSTANCE.getJAVA_31_0_0_3_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_31_0_0_3_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_31_0_0_3_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_31_0_0_3 = INSTANCE.getJAVA_31_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_31_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_31_0_0_3_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_31_0_0_4 = INSTANCE.getJAVA_31_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_31_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("(", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_31_0_0_5_0_0_0 = INSTANCE.getJAVA_31_0_0_5_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_31_0_0_5_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_31_0_0_5_0_0_1_0_0_0 = INSTANCE.getJAVA_31_0_0_5_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_31_0_0_5_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_31_0_0_5_0_0_1_0_0_1 = INSTANCE.getJAVA_31_0_0_5_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_31_0_0_5_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_31_0_0_5_0_0_1_0_0 = INSTANCE.getJAVA_31_0_0_5_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_31_0_0_5_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_31_0_0_5_0_0_1_0_0_0, JAVA_31_0_0_5_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_31_0_0_5_0_0_1_0 = INSTANCE.getJAVA_31_0_0_5_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_31_0_0_5_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_31_0_0_5_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_31_0_0_5_0_0_1 = INSTANCE.getJAVA_31_0_0_5_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_31_0_0_5_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_31_0_0_5_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_31_0_0_5_0_0 = INSTANCE.getJAVA_31_0_0_5_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_31_0_0_5_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_31_0_0_5_0_0_0, JAVA_31_0_0_5_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_31_0_0_5_0 = INSTANCE.getJAVA_31_0_0_5_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_31_0_0_5_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_31_0_0_5_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_31_0_0_5 = INSTANCE.getJAVA_31_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_31_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_31_0_0_5_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_31_0_0_6 = INSTANCE.getJAVA_31_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_31_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(")", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_31_0_0_7 = INSTANCE.getJAVA_31_0_0_7();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_31_0_0_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ANONYMOUS_CLASS), org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK, new EClass[] {org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_31_0_0_8_0_0_0 = INSTANCE.getJAVA_31_0_0_8_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_31_0_0_8_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(".", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_31_0_0_8_0_0_1 = INSTANCE.getJAVA_31_0_0_8_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_31_0_0_8_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__NEXT), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_31_0_0_8_0_0 = INSTANCE.getJAVA_31_0_0_8_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_31_0_0_8_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_31_0_0_8_0_0_0, JAVA_31_0_0_8_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_31_0_0_8_0 = INSTANCE.getJAVA_31_0_0_8_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_31_0_0_8_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_31_0_0_8_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_31_0_0_8 = INSTANCE.getJAVA_31_0_0_8();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_31_0_0_8() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_31_0_0_8_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_31_0_0 = INSTANCE.getJAVA_31_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_31_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_31_0_0_0, JAVA_31_0_0_1, JAVA_31_0_0_2, JAVA_31_0_0_3, JAVA_31_0_0_4, JAVA_31_0_0_5, JAVA_31_0_0_6, JAVA_31_0_0_7, JAVA_31_0_0_8);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_31_0 = INSTANCE.getJAVA_31_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_31_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_31_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * NewConstructorCall
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_31 = INSTANCE.getJAVA_31();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_31() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall(), JAVA_31_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_32_0_0_0_0_0_0 = INSTANCE.getJAVA_32_0_0_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_32_0_0_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_32_0_0_0_0_0_1 = INSTANCE.getJAVA_32_0_0_0_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_32_0_0_0_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getExplicitConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_32_0_0_0_0_0_2_0_0_0 = INSTANCE.getJAVA_32_0_0_0_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_32_0_0_0_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_32_0_0_0_0_0_2_0_0_1 = INSTANCE.getJAVA_32_0_0_0_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_32_0_0_0_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getExplicitConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_32_0_0_0_0_0_2_0_0 = INSTANCE.getJAVA_32_0_0_0_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_32_0_0_0_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_32_0_0_0_0_0_2_0_0_0, JAVA_32_0_0_0_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_32_0_0_0_0_0_2_0 = INSTANCE.getJAVA_32_0_0_0_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_32_0_0_0_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_32_0_0_0_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_32_0_0_0_0_0_2 = INSTANCE.getJAVA_32_0_0_0_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_32_0_0_0_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_32_0_0_0_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_32_0_0_0_0_0_3 = INSTANCE.getJAVA_32_0_0_0_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_32_0_0_0_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_32_0_0_0_0_0 = INSTANCE.getJAVA_32_0_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_32_0_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_32_0_0_0_0_0_0, JAVA_32_0_0_0_0_0_1, JAVA_32_0_0_0_0_0_2, JAVA_32_0_0_0_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_32_0_0_0_0 = INSTANCE.getJAVA_32_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_32_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_32_0_0_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_32_0_0_0 = INSTANCE.getJAVA_32_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_32_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_32_0_0_0_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_32_0_0_1 = INSTANCE.getJAVA_32_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_32_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getExplicitConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__CALL_TARGET), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getSelf(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_32_0_0_2 = INSTANCE.getJAVA_32_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_32_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("(", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_32_0_0_3_0_0_0 = INSTANCE.getJAVA_32_0_0_3_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_32_0_0_3_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getExplicitConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_32_0_0_3_0_0_1_0_0_0 = INSTANCE.getJAVA_32_0_0_3_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_32_0_0_3_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_32_0_0_3_0_0_1_0_0_1 = INSTANCE.getJAVA_32_0_0_3_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_32_0_0_3_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getExplicitConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_32_0_0_3_0_0_1_0_0 = INSTANCE.getJAVA_32_0_0_3_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_32_0_0_3_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_32_0_0_3_0_0_1_0_0_0, JAVA_32_0_0_3_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_32_0_0_3_0_0_1_0 = INSTANCE.getJAVA_32_0_0_3_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_32_0_0_3_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_32_0_0_3_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_32_0_0_3_0_0_1 = INSTANCE.getJAVA_32_0_0_3_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_32_0_0_3_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_32_0_0_3_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_32_0_0_3_0_0 = INSTANCE.getJAVA_32_0_0_3_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_32_0_0_3_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_32_0_0_3_0_0_0, JAVA_32_0_0_3_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_32_0_0_3_0 = INSTANCE.getJAVA_32_0_0_3_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_32_0_0_3_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_32_0_0_3_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_32_0_0_3 = INSTANCE.getJAVA_32_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_32_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_32_0_0_3_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_32_0_0_4 = INSTANCE.getJAVA_32_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_32_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(")", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_32_0_0_5_0_0_0 = INSTANCE.getJAVA_32_0_0_5_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_32_0_0_5_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(".", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_32_0_0_5_0_0_1 = INSTANCE.getJAVA_32_0_0_5_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_32_0_0_5_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getExplicitConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__NEXT), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_32_0_0_5_0_0 = INSTANCE.getJAVA_32_0_0_5_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_32_0_0_5_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_32_0_0_5_0_0_0, JAVA_32_0_0_5_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_32_0_0_5_0 = INSTANCE.getJAVA_32_0_0_5_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_32_0_0_5_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_32_0_0_5_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_32_0_0_5 = INSTANCE.getJAVA_32_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_32_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_32_0_0_5_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_32_0_0 = INSTANCE.getJAVA_32_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_32_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_32_0_0_0, JAVA_32_0_0_1, JAVA_32_0_0_2, JAVA_32_0_0_3, JAVA_32_0_0_4, JAVA_32_0_0_5);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_32_0 = INSTANCE.getJAVA_32_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_32_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_32_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * ExplicitConstructorCall
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_32 = INSTANCE.getJAVA_32();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_32() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getExplicitConstructorCall(), JAVA_32_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_33_0_0_0 = INSTANCE.getJAVA_33_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_33_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("new", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_33_0_0_1 = INSTANCE.getJAVA_33_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_33_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__TYPE_REFERENCE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_33_0_0_2 = INSTANCE.getJAVA_33_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_33_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__ARRAY_DIMENSIONS_BEFORE), org.emftext.language.java.resource.java.grammar.JavaCardinality.PLUS, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_33_0_0_3 = INSTANCE.getJAVA_33_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_33_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__ARRAY_INITIALIZER), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInitializer(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_33_0_0_4 = INSTANCE.getJAVA_33_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_33_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__ARRAY_SELECTORS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArraySelector(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_33_0_0_5_0_0_0 = INSTANCE.getJAVA_33_0_0_5_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_33_0_0_5_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(".", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_33_0_0_5_0_0_1 = INSTANCE.getJAVA_33_0_0_5_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_33_0_0_5_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__NEXT), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_33_0_0_5_0_0 = INSTANCE.getJAVA_33_0_0_5_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_33_0_0_5_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_33_0_0_5_0_0_0, JAVA_33_0_0_5_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_33_0_0_5_0 = INSTANCE.getJAVA_33_0_0_5_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_33_0_0_5_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_33_0_0_5_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_33_0_0_5 = INSTANCE.getJAVA_33_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_33_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_33_0_0_5_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_33_0_0 = INSTANCE.getJAVA_33_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_33_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_33_0_0_0, JAVA_33_0_0_1, JAVA_33_0_0_2, JAVA_33_0_0_3, JAVA_33_0_0_4, JAVA_33_0_0_5);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_33_0 = INSTANCE.getJAVA_33_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_33_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_33_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * ArrayInstantiationByValuesTyped
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_33 = INSTANCE.getJAVA_33();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_33() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), JAVA_33_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_34_0_0_0 = INSTANCE.getJAVA_34_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_34_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesUntyped().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_UNTYPED__ARRAY_INITIALIZER), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInitializer(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_34_0_0_1 = INSTANCE.getJAVA_34_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_34_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesUntyped().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_UNTYPED__ARRAY_SELECTORS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArraySelector(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_34_0_0_2_0_0_0 = INSTANCE.getJAVA_34_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_34_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(".", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_34_0_0_2_0_0_1 = INSTANCE.getJAVA_34_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_34_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesUntyped().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_UNTYPED__NEXT), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_34_0_0_2_0_0 = INSTANCE.getJAVA_34_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_34_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_34_0_0_2_0_0_0, JAVA_34_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_34_0_0_2_0 = INSTANCE.getJAVA_34_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_34_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_34_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_34_0_0_2 = INSTANCE.getJAVA_34_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_34_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_34_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_34_0_0 = INSTANCE.getJAVA_34_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_34_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_34_0_0_0, JAVA_34_0_0_1, JAVA_34_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_34_0 = INSTANCE.getJAVA_34_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_34_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_34_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * ArrayInstantiationByValuesUntyped
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_34 = INSTANCE.getJAVA_34();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_34() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesUntyped(), JAVA_34_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_35_0_0_0 = INSTANCE.getJAVA_35_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_35_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("new", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_35_0_0_1 = INSTANCE.getJAVA_35_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_35_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationBySize().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__TYPE_REFERENCE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_35_0_0_2_0_0_0 = INSTANCE.getJAVA_35_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_35_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("[", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_35_0_0_2_0_0_1 = INSTANCE.getJAVA_35_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_35_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationBySize().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__SIZES), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_35_0_0_2_0_0_2 = INSTANCE.getJAVA_35_0_0_2_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_35_0_0_2_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("]", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_35_0_0_2_0_0 = INSTANCE.getJAVA_35_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_35_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_35_0_0_2_0_0_0, JAVA_35_0_0_2_0_0_1, JAVA_35_0_0_2_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_35_0_0_2_0 = INSTANCE.getJAVA_35_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_35_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_35_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_35_0_0_2 = INSTANCE.getJAVA_35_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_35_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_35_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.PLUS);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_35_0_0_3 = INSTANCE.getJAVA_35_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_35_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationBySize().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__ARRAY_DIMENSIONS_BEFORE), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_35_0_0_4_0_0_0 = INSTANCE.getJAVA_35_0_0_4_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_35_0_0_4_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(".", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_35_0_0_4_0_0_1 = INSTANCE.getJAVA_35_0_0_4_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_35_0_0_4_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationBySize().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__NEXT), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_35_0_0_4_0_0 = INSTANCE.getJAVA_35_0_0_4_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_35_0_0_4_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_35_0_0_4_0_0_0, JAVA_35_0_0_4_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_35_0_0_4_0 = INSTANCE.getJAVA_35_0_0_4_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_35_0_0_4_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_35_0_0_4_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_35_0_0_4 = INSTANCE.getJAVA_35_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_35_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_35_0_0_4_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_35_0_0 = INSTANCE.getJAVA_35_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_35_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_35_0_0_0, JAVA_35_0_0_1, JAVA_35_0_0_2, JAVA_35_0_0_3, JAVA_35_0_0_4);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_35_0 = INSTANCE.getJAVA_35_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_35_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_35_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * ArrayInstantiationBySize
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_35 = INSTANCE.getJAVA_35();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_35() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationBySize(), JAVA_35_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_36_0_0_0 = INSTANCE.getJAVA_36_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_36_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_36_0_0_1 = INSTANCE.getJAVA_36_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_36_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("{", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_36_0_0_2_0_0_0 = INSTANCE.getJAVA_36_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_36_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInitializer().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INITIALIZER__INITIAL_VALUES), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInitializer(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_36_0_0_2_0_0_1_0_0_0 = INSTANCE.getJAVA_36_0_0_2_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_36_0_0_2_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_36_0_0_2_0_0_1_0_0_1 = INSTANCE.getJAVA_36_0_0_2_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_36_0_0_2_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInitializer().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INITIALIZER__INITIAL_VALUES), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInitializer(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_36_0_0_2_0_0_1_0_0 = INSTANCE.getJAVA_36_0_0_2_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_36_0_0_2_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_36_0_0_2_0_0_1_0_0_0, JAVA_36_0_0_2_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_36_0_0_2_0_0_1_0 = INSTANCE.getJAVA_36_0_0_2_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_36_0_0_2_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_36_0_0_2_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_36_0_0_2_0_0_1 = INSTANCE.getJAVA_36_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_36_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_36_0_0_2_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_36_0_0_2_0_0 = INSTANCE.getJAVA_36_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_36_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_36_0_0_2_0_0_0, JAVA_36_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_36_0_0_2_0 = INSTANCE.getJAVA_36_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_36_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_36_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_36_0_0_2 = INSTANCE.getJAVA_36_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_36_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_36_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_36_0_0_3_0_0_0 = INSTANCE.getJAVA_36_0_0_3_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_36_0_0_3_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_36_0_0_3_0_0 = INSTANCE.getJAVA_36_0_0_3_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_36_0_0_3_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_36_0_0_3_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_36_0_0_3_0 = INSTANCE.getJAVA_36_0_0_3_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_36_0_0_3_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_36_0_0_3_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_36_0_0_3 = INSTANCE.getJAVA_36_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_36_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_36_0_0_3_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_36_0_0_4 = INSTANCE.getJAVA_36_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_36_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("}", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_36_0_0 = INSTANCE.getJAVA_36_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_36_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_36_0_0_0, JAVA_36_0_0_1, JAVA_36_0_0_2, JAVA_36_0_0_3, JAVA_36_0_0_4);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_36_0 = INSTANCE.getJAVA_36_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_36_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_36_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * ArrayInitializer
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_36 = INSTANCE.getJAVA_36();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_36() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInitializer(), JAVA_36_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_37_0_0_0 = INSTANCE.getJAVA_37_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_37_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("[", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_37_0_0_1 = INSTANCE.getJAVA_37_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_37_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArraySelector().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_SELECTOR__POSITION), org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_37_0_0_2 = INSTANCE.getJAVA_37_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_37_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("]", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_37_0_0 = INSTANCE.getJAVA_37_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_37_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_37_0_0_0, JAVA_37_0_0_1, JAVA_37_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_37_0 = INSTANCE.getJAVA_37_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_37_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_37_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * ArraySelector
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_37 = INSTANCE.getJAVA_37();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_37() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArraySelector(), JAVA_37_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_38_0_0_0_0_0_0 = INSTANCE.getJAVA_38_0_0_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_38_0_0_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.NAMESPACE_CLASSIFIER_REFERENCE__NAMESPACES), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_38_0_0_0_0_0_1 = INSTANCE.getJAVA_38_0_0_0_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_38_0_0_0_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(".", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_38_0_0_0_0_0 = INSTANCE.getJAVA_38_0_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_38_0_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_38_0_0_0_0_0_0, JAVA_38_0_0_0_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_38_0_0_0_0 = INSTANCE.getJAVA_38_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_38_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_38_0_0_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_38_0_0_0 = INSTANCE.getJAVA_38_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_38_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_38_0_0_0_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_38_0_0_1_0_0_0 = INSTANCE.getJAVA_38_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_38_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.NAMESPACE_CLASSIFIER_REFERENCE__CLASSIFIER_REFERENCES), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getClassifierReference(), }, 1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_38_0_0_1_0_0_1 = INSTANCE.getJAVA_38_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_38_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(".", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_38_0_0_1_0_0 = INSTANCE.getJAVA_38_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_38_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_38_0_0_1_0_0_0, JAVA_38_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_38_0_0_1_0 = INSTANCE.getJAVA_38_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_38_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_38_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_38_0_0_1 = INSTANCE.getJAVA_38_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_38_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_38_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_38_0_0_2 = INSTANCE.getJAVA_38_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_38_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.NAMESPACE_CLASSIFIER_REFERENCE__CLASSIFIER_REFERENCES), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getClassifierReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_38_0_0 = INSTANCE.getJAVA_38_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_38_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_38_0_0_0, JAVA_38_0_0_1, JAVA_38_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_38_0 = INSTANCE.getJAVA_38_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_38_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_38_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * NamespaceClassifierReference
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_38 = INSTANCE.getJAVA_38();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_38() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), JAVA_38_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_39_0_0_0 = INSTANCE.getJAVA_39_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_39_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.types.TypesPackage.eINSTANCE.getClassifierReference().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.CLASSIFIER_REFERENCE__TARGET), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_39_0_0_1_0_0_0 = INSTANCE.getJAVA_39_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_39_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_39_0_0_1_0_0_1 = INSTANCE.getJAVA_39_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_39_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.types.TypesPackage.eINSTANCE.getClassifierReference().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.CLASSIFIER_REFERENCE__TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_39_0_0_1_0_0_2_0_0_0 = INSTANCE.getJAVA_39_0_0_1_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_39_0_0_1_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_39_0_0_1_0_0_2_0_0_1 = INSTANCE.getJAVA_39_0_0_1_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_39_0_0_1_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.types.TypesPackage.eINSTANCE.getClassifierReference().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.CLASSIFIER_REFERENCE__TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_39_0_0_1_0_0_2_0_0 = INSTANCE.getJAVA_39_0_0_1_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_39_0_0_1_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_39_0_0_1_0_0_2_0_0_0, JAVA_39_0_0_1_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_39_0_0_1_0_0_2_0 = INSTANCE.getJAVA_39_0_0_1_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_39_0_0_1_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_39_0_0_1_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_39_0_0_1_0_0_2 = INSTANCE.getJAVA_39_0_0_1_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_39_0_0_1_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_39_0_0_1_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_39_0_0_1_0_0_3 = INSTANCE.getJAVA_39_0_0_1_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_39_0_0_1_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_39_0_0_1_0_0 = INSTANCE.getJAVA_39_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_39_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_39_0_0_1_0_0_0, JAVA_39_0_0_1_0_0_1, JAVA_39_0_0_1_0_0_2, JAVA_39_0_0_1_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_39_0_0_1_0 = INSTANCE.getJAVA_39_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_39_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_39_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_39_0_0_1 = INSTANCE.getJAVA_39_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_39_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_39_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_39_0_0 = INSTANCE.getJAVA_39_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_39_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_39_0_0_0, JAVA_39_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_39_0 = INSTANCE.getJAVA_39_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_39_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_39_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * ClassifierReference
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_39 = INSTANCE.getJAVA_39();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_39() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getClassifierReference(), JAVA_39_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_40_0_0_0_0_0_0 = INSTANCE.getJAVA_40_0_0_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_40_0_0_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_40_0_0_0_0_0_1 = INSTANCE.getJAVA_40_0_0_0_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_40_0_0_0_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__CALL_TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_40_0_0_0_0_0_2_0_0_0 = INSTANCE.getJAVA_40_0_0_0_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_40_0_0_0_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_40_0_0_0_0_0_2_0_0_1 = INSTANCE.getJAVA_40_0_0_0_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_40_0_0_0_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__CALL_TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_40_0_0_0_0_0_2_0_0 = INSTANCE.getJAVA_40_0_0_0_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_40_0_0_0_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_40_0_0_0_0_0_2_0_0_0, JAVA_40_0_0_0_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_40_0_0_0_0_0_2_0 = INSTANCE.getJAVA_40_0_0_0_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_40_0_0_0_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_40_0_0_0_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_40_0_0_0_0_0_2 = INSTANCE.getJAVA_40_0_0_0_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_40_0_0_0_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_40_0_0_0_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_40_0_0_0_0_0_3 = INSTANCE.getJAVA_40_0_0_0_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_40_0_0_0_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_40_0_0_0_0_0 = INSTANCE.getJAVA_40_0_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_40_0_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_40_0_0_0_0_0_0, JAVA_40_0_0_0_0_0_1, JAVA_40_0_0_0_0_0_2, JAVA_40_0_0_0_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_40_0_0_0_0 = INSTANCE.getJAVA_40_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_40_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_40_0_0_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_40_0_0_0 = INSTANCE.getJAVA_40_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_40_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_40_0_0_0_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_40_0_0_1 = INSTANCE.getJAVA_40_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_40_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__TARGET), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_40_0_0_2_0_0_0 = INSTANCE.getJAVA_40_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_40_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_40_0_0_2_0_0_1 = INSTANCE.getJAVA_40_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_40_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_40_0_0_2_0_0_2_0_0_0 = INSTANCE.getJAVA_40_0_0_2_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_40_0_0_2_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_40_0_0_2_0_0_2_0_0_1 = INSTANCE.getJAVA_40_0_0_2_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_40_0_0_2_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_40_0_0_2_0_0_2_0_0 = INSTANCE.getJAVA_40_0_0_2_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_40_0_0_2_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_40_0_0_2_0_0_2_0_0_0, JAVA_40_0_0_2_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_40_0_0_2_0_0_2_0 = INSTANCE.getJAVA_40_0_0_2_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_40_0_0_2_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_40_0_0_2_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_40_0_0_2_0_0_2 = INSTANCE.getJAVA_40_0_0_2_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_40_0_0_2_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_40_0_0_2_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_40_0_0_2_0_0_3 = INSTANCE.getJAVA_40_0_0_2_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_40_0_0_2_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_40_0_0_2_0_0 = INSTANCE.getJAVA_40_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_40_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_40_0_0_2_0_0_0, JAVA_40_0_0_2_0_0_1, JAVA_40_0_0_2_0_0_2, JAVA_40_0_0_2_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_40_0_0_2_0 = INSTANCE.getJAVA_40_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_40_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_40_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_40_0_0_2 = INSTANCE.getJAVA_40_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_40_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_40_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_40_0_0_3 = INSTANCE.getJAVA_40_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_40_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("(", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_40_0_0_4_0_0_0 = INSTANCE.getJAVA_40_0_0_4_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_40_0_0_4_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_40_0_0_4_0_0_1_0_0_0 = INSTANCE.getJAVA_40_0_0_4_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_40_0_0_4_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_40_0_0_4_0_0_1_0_0_1 = INSTANCE.getJAVA_40_0_0_4_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_40_0_0_4_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_40_0_0_4_0_0_1_0_0 = INSTANCE.getJAVA_40_0_0_4_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_40_0_0_4_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_40_0_0_4_0_0_1_0_0_0, JAVA_40_0_0_4_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_40_0_0_4_0_0_1_0 = INSTANCE.getJAVA_40_0_0_4_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_40_0_0_4_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_40_0_0_4_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_40_0_0_4_0_0_1 = INSTANCE.getJAVA_40_0_0_4_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_40_0_0_4_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_40_0_0_4_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_40_0_0_4_0_0 = INSTANCE.getJAVA_40_0_0_4_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_40_0_0_4_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_40_0_0_4_0_0_0, JAVA_40_0_0_4_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_40_0_0_4_0 = INSTANCE.getJAVA_40_0_0_4_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_40_0_0_4_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_40_0_0_4_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_40_0_0_4 = INSTANCE.getJAVA_40_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_40_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_40_0_0_4_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_40_0_0_5 = INSTANCE.getJAVA_40_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_40_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(")", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_40_0_0_6 = INSTANCE.getJAVA_40_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_40_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__ARRAY_SELECTORS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArraySelector(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_40_0_0_7_0_0_0 = INSTANCE.getJAVA_40_0_0_7_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_40_0_0_7_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(".", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_40_0_0_7_0_0_1 = INSTANCE.getJAVA_40_0_0_7_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_40_0_0_7_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__NEXT), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_40_0_0_7_0_0 = INSTANCE.getJAVA_40_0_0_7_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_40_0_0_7_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_40_0_0_7_0_0_0, JAVA_40_0_0_7_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_40_0_0_7_0 = INSTANCE.getJAVA_40_0_0_7_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_40_0_0_7_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_40_0_0_7_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_40_0_0_7 = INSTANCE.getJAVA_40_0_0_7();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_40_0_0_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_40_0_0_7_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_40_0_0 = INSTANCE.getJAVA_40_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_40_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_40_0_0_0, JAVA_40_0_0_1, JAVA_40_0_0_2, JAVA_40_0_0_3, JAVA_40_0_0_4, JAVA_40_0_0_5, JAVA_40_0_0_6, JAVA_40_0_0_7);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_40_0 = INSTANCE.getJAVA_40_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_40_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_40_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class MethodCall
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_40 = INSTANCE.getJAVA_40();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_40() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall(), JAVA_40_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_41_0_0_0 = INSTANCE.getJAVA_41_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_41_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getIdentifierReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__TARGET), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_41_0_0_1_0_0_0 = INSTANCE.getJAVA_41_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_41_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_41_0_0_1_0_0_1 = INSTANCE.getJAVA_41_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_41_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getIdentifierReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_41_0_0_1_0_0_2_0_0_0 = INSTANCE.getJAVA_41_0_0_1_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_41_0_0_1_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_41_0_0_1_0_0_2_0_0_1 = INSTANCE.getJAVA_41_0_0_1_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_41_0_0_1_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getIdentifierReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__TYPE_ARGUMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_41_0_0_1_0_0_2_0_0 = INSTANCE.getJAVA_41_0_0_1_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_41_0_0_1_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_41_0_0_1_0_0_2_0_0_0, JAVA_41_0_0_1_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_41_0_0_1_0_0_2_0 = INSTANCE.getJAVA_41_0_0_1_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_41_0_0_1_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_41_0_0_1_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_41_0_0_1_0_0_2 = INSTANCE.getJAVA_41_0_0_1_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_41_0_0_1_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_41_0_0_1_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_41_0_0_1_0_0_3 = INSTANCE.getJAVA_41_0_0_1_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_41_0_0_1_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_41_0_0_1_0_0 = INSTANCE.getJAVA_41_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_41_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_41_0_0_1_0_0_0, JAVA_41_0_0_1_0_0_1, JAVA_41_0_0_1_0_0_2, JAVA_41_0_0_1_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_41_0_0_1_0 = INSTANCE.getJAVA_41_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_41_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_41_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_41_0_0_1 = INSTANCE.getJAVA_41_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_41_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_41_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_41_0_0_2 = INSTANCE.getJAVA_41_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_41_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getIdentifierReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__ARRAY_SELECTORS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArraySelector(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_41_0_0_3_0_0_0 = INSTANCE.getJAVA_41_0_0_3_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_41_0_0_3_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(".", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_41_0_0_3_0_0_1 = INSTANCE.getJAVA_41_0_0_3_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_41_0_0_3_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getIdentifierReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__NEXT), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_41_0_0_3_0_0 = INSTANCE.getJAVA_41_0_0_3_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_41_0_0_3_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_41_0_0_3_0_0_0, JAVA_41_0_0_3_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_41_0_0_3_0 = INSTANCE.getJAVA_41_0_0_3_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_41_0_0_3_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_41_0_0_3_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_41_0_0_3 = INSTANCE.getJAVA_41_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_41_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_41_0_0_3_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_41_0_0 = INSTANCE.getJAVA_41_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_41_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_41_0_0_0, JAVA_41_0_0_1, JAVA_41_0_0_2, JAVA_41_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_41_0 = INSTANCE.getJAVA_41_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_41_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_41_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * IdentifierReference
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_41 = INSTANCE.getJAVA_41();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_41() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getIdentifierReference(), JAVA_41_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_42_0_0_0 = INSTANCE.getJAVA_42_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_42_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("class", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_42_0_0_1_0_0_0 = INSTANCE.getJAVA_42_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_42_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(".", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_42_0_0_1_0_0_1 = INSTANCE.getJAVA_42_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_42_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReflectiveClassReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.REFLECTIVE_CLASS_REFERENCE__NEXT), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_42_0_0_1_0_0 = INSTANCE.getJAVA_42_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_42_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_42_0_0_1_0_0_0, JAVA_42_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_42_0_0_1_0 = INSTANCE.getJAVA_42_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_42_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_42_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_42_0_0_1 = INSTANCE.getJAVA_42_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_42_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_42_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_42_0_0 = INSTANCE.getJAVA_42_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_42_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_42_0_0_0, JAVA_42_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_42_0 = INSTANCE.getJAVA_42_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_42_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_42_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * ReflectiveClassReference
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_42 = INSTANCE.getJAVA_42();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_42() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReflectiveClassReference(), JAVA_42_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_43_0_0_0 = INSTANCE.getJAVA_43_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_43_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getSelfReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.SELF_REFERENCE__SELF), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getSelf(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_43_0_0_1_0_0_0 = INSTANCE.getJAVA_43_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_43_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(".", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_43_0_0_1_0_0_1 = INSTANCE.getJAVA_43_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_43_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getSelfReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.SELF_REFERENCE__NEXT), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_43_0_0_1_0_0 = INSTANCE.getJAVA_43_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_43_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_43_0_0_1_0_0_0, JAVA_43_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_43_0_0_1_0 = INSTANCE.getJAVA_43_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_43_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_43_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_43_0_0_1 = INSTANCE.getJAVA_43_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_43_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_43_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_43_0_0 = INSTANCE.getJAVA_43_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_43_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_43_0_0_0, JAVA_43_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_43_0 = INSTANCE.getJAVA_43_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_43_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_43_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * SelfReference
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_43 = INSTANCE.getJAVA_43();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_43() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getSelfReference(), JAVA_43_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_44_0_0_0 = INSTANCE.getJAVA_44_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_44_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getPrimitiveTypeReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.PRIMITIVE_TYPE_REFERENCE__PRIMITIVE_TYPE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getPrimitiveType(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_44_0_0_1 = INSTANCE.getJAVA_44_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_44_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getPrimitiveTypeReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.PRIMITIVE_TYPE_REFERENCE__ARRAY_SELECTORS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArraySelector(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_44_0_0_2_0_0_0 = INSTANCE.getJAVA_44_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_44_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(".", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_44_0_0_2_0_0_1 = INSTANCE.getJAVA_44_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_44_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getPrimitiveTypeReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.PRIMITIVE_TYPE_REFERENCE__NEXT), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_44_0_0_2_0_0 = INSTANCE.getJAVA_44_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_44_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_44_0_0_2_0_0_0, JAVA_44_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_44_0_0_2_0 = INSTANCE.getJAVA_44_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_44_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_44_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_44_0_0_2 = INSTANCE.getJAVA_44_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_44_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_44_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_44_0_0 = INSTANCE.getJAVA_44_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_44_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_44_0_0_0, JAVA_44_0_0_1, JAVA_44_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_44_0 = INSTANCE.getJAVA_44_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_44_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_44_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * PrimitiveTypeReference
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_44 = INSTANCE.getJAVA_44();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_44() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getPrimitiveTypeReference(), JAVA_44_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_45_0_0_0 = INSTANCE.getJAVA_45_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_45_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("this", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_45_0_0 = INSTANCE.getJAVA_45_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_45_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_45_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_45_0 = INSTANCE.getJAVA_45_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_45_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_45_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class This
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_45 = INSTANCE.getJAVA_45();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_45() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getThis(), JAVA_45_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_46_0_0_0 = INSTANCE.getJAVA_46_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_46_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("super", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_46_0_0 = INSTANCE.getJAVA_46_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_46_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_46_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_46_0 = INSTANCE.getJAVA_46_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_46_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_46_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Super
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_46 = INSTANCE.getJAVA_46();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_46() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getSuper(), JAVA_46_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_47_0_0_0 = INSTANCE.getJAVA_47_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_47_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getStringReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.STRING_REFERENCE__VALUE), "STRING_LITERAL", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_47_0_0_1_0_0_0 = INSTANCE.getJAVA_47_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_47_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(".", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_47_0_0_1_0_0_1 = INSTANCE.getJAVA_47_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_47_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getStringReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.STRING_REFERENCE__NEXT), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_47_0_0_1_0_0 = INSTANCE.getJAVA_47_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_47_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_47_0_0_1_0_0_0, JAVA_47_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_47_0_0_1_0 = INSTANCE.getJAVA_47_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_47_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_47_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_47_0_0_1 = INSTANCE.getJAVA_47_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_47_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_47_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_47_0_0 = INSTANCE.getJAVA_47_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_47_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_47_0_0_0, JAVA_47_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_47_0 = INSTANCE.getJAVA_47_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_47_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_47_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * StringReference
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_47 = INSTANCE.getJAVA_47();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_47() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getStringReference(), JAVA_47_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_48_0_0_0 = INSTANCE.getJAVA_48_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_48_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.QUALIFIED_TYPE_ARGUMENT__TYPE_REFERENCE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_48_0_0_1 = INSTANCE.getJAVA_48_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_48_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.QUALIFIED_TYPE_ARGUMENT__ARRAY_DIMENSIONS_BEFORE), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_48_0_0 = INSTANCE.getJAVA_48_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_48_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_48_0_0_0, JAVA_48_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_48_0 = INSTANCE.getJAVA_48_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_48_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_48_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * QualifiedTypeArgument
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_48 = INSTANCE.getJAVA_48();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_48() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument(), JAVA_48_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_49_0_0_0 = INSTANCE.getJAVA_49_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_49_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("?", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_49_0_0 = INSTANCE.getJAVA_49_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_49_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_49_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_49_0 = INSTANCE.getJAVA_49_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_49_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_49_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * UnknownTypeArgument
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_49 = INSTANCE.getJAVA_49();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_49() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getUnknownTypeArgument(), JAVA_49_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_50_0_0_0 = INSTANCE.getJAVA_50_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_50_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("?", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_50_0_0_1 = INSTANCE.getJAVA_50_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_50_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("extends", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_50_0_0_2 = INSTANCE.getJAVA_50_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_50_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.EXTENDS_TYPE_ARGUMENT__EXTEND_TYPES), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_50_0_0_3_0_0_0 = INSTANCE.getJAVA_50_0_0_3_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_50_0_0_3_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("&", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_50_0_0_3_0_0_1 = INSTANCE.getJAVA_50_0_0_3_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_50_0_0_3_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.EXTENDS_TYPE_ARGUMENT__EXTEND_TYPES), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_50_0_0_3_0_0 = INSTANCE.getJAVA_50_0_0_3_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_50_0_0_3_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_50_0_0_3_0_0_0, JAVA_50_0_0_3_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_50_0_0_3_0 = INSTANCE.getJAVA_50_0_0_3_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_50_0_0_3_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_50_0_0_3_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_50_0_0_3 = INSTANCE.getJAVA_50_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_50_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_50_0_0_3_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_50_0_0_4 = INSTANCE.getJAVA_50_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_50_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.EXTENDS_TYPE_ARGUMENT__ARRAY_DIMENSIONS_BEFORE), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_50_0_0 = INSTANCE.getJAVA_50_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_50_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_50_0_0_0, JAVA_50_0_0_1, JAVA_50_0_0_2, JAVA_50_0_0_3, JAVA_50_0_0_4);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_50_0 = INSTANCE.getJAVA_50_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_50_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_50_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * ExtendsTypeArgument
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_50 = INSTANCE.getJAVA_50();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_50() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), JAVA_50_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_51_0_0_0 = INSTANCE.getJAVA_51_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_51_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("?", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_51_0_0_1 = INSTANCE.getJAVA_51_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_51_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("super", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_51_0_0_2 = INSTANCE.getJAVA_51_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_51_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.SUPER_TYPE_ARGUMENT__SUPER_TYPE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_51_0_0_3 = INSTANCE.getJAVA_51_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_51_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.SUPER_TYPE_ARGUMENT__ARRAY_DIMENSIONS_BEFORE), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_51_0_0 = INSTANCE.getJAVA_51_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_51_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_51_0_0_0, JAVA_51_0_0_1, JAVA_51_0_0_2, JAVA_51_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_51_0 = INSTANCE.getJAVA_51_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_51_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_51_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * SuperTypeArgument
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_51 = INSTANCE.getJAVA_51();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_51() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument(), JAVA_51_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_52_0_0_0 = INSTANCE.getJAVA_52_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_52_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("assert", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_52_0_0_1 = INSTANCE.getJAVA_52_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_52_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getAssert().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.ASSERT__CONDITION), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_52_0_0_2_0_0_0 = INSTANCE.getJAVA_52_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_52_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(":", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_52_0_0_2_0_0_1 = INSTANCE.getJAVA_52_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_52_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getAssert().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.ASSERT__ERROR_MESSAGE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_52_0_0_2_0_0 = INSTANCE.getJAVA_52_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_52_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_52_0_0_2_0_0_0, JAVA_52_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_52_0_0_2_0 = INSTANCE.getJAVA_52_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_52_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_52_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_52_0_0_2 = INSTANCE.getJAVA_52_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_52_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_52_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_52_0_0_3 = INSTANCE.getJAVA_52_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_52_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_52_0_0 = INSTANCE.getJAVA_52_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_52_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_52_0_0_0, JAVA_52_0_0_1, JAVA_52_0_0_2, JAVA_52_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_52_0 = INSTANCE.getJAVA_52_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_52_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_52_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Assert
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_52 = INSTANCE.getJAVA_52();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_52() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getAssert(), JAVA_52_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_53_0_0_0 = INSTANCE.getJAVA_53_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_53_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("if", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_53_0_0_1 = INSTANCE.getJAVA_53_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_53_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_53_0_0_2 = INSTANCE.getJAVA_53_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_53_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("(", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_53_0_0_3 = INSTANCE.getJAVA_53_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_53_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCondition().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONDITION__CONDITION), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_53_0_0_4 = INSTANCE.getJAVA_53_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_53_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(")", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_53_0_0_5 = INSTANCE.getJAVA_53_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_53_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCondition().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONDITION__STATEMENT), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_53_0_0_6_0_0_0 = INSTANCE.getJAVA_53_0_0_6_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_53_0_0_6_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("else", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_53_0_0_6_0_0_1 = INSTANCE.getJAVA_53_0_0_6_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_53_0_0_6_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCondition().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONDITION__ELSE_STATEMENT), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_53_0_0_6_0_0 = INSTANCE.getJAVA_53_0_0_6_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_53_0_0_6_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_53_0_0_6_0_0_0, JAVA_53_0_0_6_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_53_0_0_6_0 = INSTANCE.getJAVA_53_0_0_6_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_53_0_0_6_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_53_0_0_6_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_53_0_0_6 = INSTANCE.getJAVA_53_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_53_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_53_0_0_6_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_53_0_0 = INSTANCE.getJAVA_53_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_53_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_53_0_0_0, JAVA_53_0_0_1, JAVA_53_0_0_2, JAVA_53_0_0_3, JAVA_53_0_0_4, JAVA_53_0_0_5, JAVA_53_0_0_6);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_53_0 = INSTANCE.getJAVA_53_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_53_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_53_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Condition
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_53 = INSTANCE.getJAVA_53();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_53() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCondition(), JAVA_53_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_54_0_0_0 = INSTANCE.getJAVA_54_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_54_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("for", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_54_0_0_1 = INSTANCE.getJAVA_54_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_54_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_54_0_0_2 = INSTANCE.getJAVA_54_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_54_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("(", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_54_0_0_3 = INSTANCE.getJAVA_54_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_54_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__INIT), org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK, new EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForLoopInitializer(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_54_0_0_4 = INSTANCE.getJAVA_54_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_54_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_54_0_0_5 = INSTANCE.getJAVA_54_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_54_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__CONDITION), org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_54_0_0_6 = INSTANCE.getJAVA_54_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_54_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_54_0_0_7_0_0_0 = INSTANCE.getJAVA_54_0_0_7_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_54_0_0_7_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__UPDATES), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_54_0_0_7_0_0_1_0_0_0 = INSTANCE.getJAVA_54_0_0_7_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_54_0_0_7_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_54_0_0_7_0_0_1_0_0_1 = INSTANCE.getJAVA_54_0_0_7_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_54_0_0_7_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__UPDATES), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_54_0_0_7_0_0_1_0_0 = INSTANCE.getJAVA_54_0_0_7_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_54_0_0_7_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_54_0_0_7_0_0_1_0_0_0, JAVA_54_0_0_7_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_54_0_0_7_0_0_1_0 = INSTANCE.getJAVA_54_0_0_7_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_54_0_0_7_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_54_0_0_7_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_54_0_0_7_0_0_1 = INSTANCE.getJAVA_54_0_0_7_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_54_0_0_7_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_54_0_0_7_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_54_0_0_7_0_0 = INSTANCE.getJAVA_54_0_0_7_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_54_0_0_7_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_54_0_0_7_0_0_0, JAVA_54_0_0_7_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_54_0_0_7_0 = INSTANCE.getJAVA_54_0_0_7_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_54_0_0_7_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_54_0_0_7_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_54_0_0_7 = INSTANCE.getJAVA_54_0_0_7();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_54_0_0_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_54_0_0_7_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_54_0_0_8 = INSTANCE.getJAVA_54_0_0_8();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_54_0_0_8() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(")", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_54_0_0_9 = INSTANCE.getJAVA_54_0_0_9();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_54_0_0_9() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__STATEMENT), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_54_0_0 = INSTANCE.getJAVA_54_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_54_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_54_0_0_0, JAVA_54_0_0_1, JAVA_54_0_0_2, JAVA_54_0_0_3, JAVA_54_0_0_4, JAVA_54_0_0_5, JAVA_54_0_0_6, JAVA_54_0_0_7, JAVA_54_0_0_8, JAVA_54_0_0_9);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_54_0 = INSTANCE.getJAVA_54_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_54_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_54_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class ForLoop
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_54 = INSTANCE.getJAVA_54();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_54() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForLoop(), JAVA_54_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_55_0_0_0 = INSTANCE.getJAVA_55_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_55_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("for", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_55_0_0_1 = INSTANCE.getJAVA_55_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_55_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_55_0_0_2 = INSTANCE.getJAVA_55_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_55_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("(", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_55_0_0_3 = INSTANCE.getJAVA_55_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_55_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForEachLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_EACH_LOOP__NEXT), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_55_0_0_4 = INSTANCE.getJAVA_55_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_55_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(":", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_55_0_0_5 = INSTANCE.getJAVA_55_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_55_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForEachLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_EACH_LOOP__COLLECTION), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_55_0_0_6 = INSTANCE.getJAVA_55_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_55_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(")", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_55_0_0_7 = INSTANCE.getJAVA_55_0_0_7();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_55_0_0_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForEachLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_EACH_LOOP__STATEMENT), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_55_0_0 = INSTANCE.getJAVA_55_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_55_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_55_0_0_0, JAVA_55_0_0_1, JAVA_55_0_0_2, JAVA_55_0_0_3, JAVA_55_0_0_4, JAVA_55_0_0_5, JAVA_55_0_0_6, JAVA_55_0_0_7);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_55_0 = INSTANCE.getJAVA_55_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_55_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_55_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class ForEachLoop
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_55 = INSTANCE.getJAVA_55();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_55() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForEachLoop(), JAVA_55_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_56_0_0_0 = INSTANCE.getJAVA_56_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_56_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("while", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_56_0_0_1 = INSTANCE.getJAVA_56_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_56_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_56_0_0_2 = INSTANCE.getJAVA_56_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_56_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("(", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_56_0_0_3 = INSTANCE.getJAVA_56_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_56_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getWhileLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.WHILE_LOOP__CONDITION), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_56_0_0_4 = INSTANCE.getJAVA_56_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_56_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(")", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_56_0_0_5 = INSTANCE.getJAVA_56_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_56_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getWhileLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.WHILE_LOOP__STATEMENT), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_56_0_0 = INSTANCE.getJAVA_56_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_56_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_56_0_0_0, JAVA_56_0_0_1, JAVA_56_0_0_2, JAVA_56_0_0_3, JAVA_56_0_0_4, JAVA_56_0_0_5);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_56_0 = INSTANCE.getJAVA_56_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_56_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_56_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class WhileLoop
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_56 = INSTANCE.getJAVA_56();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_56() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getWhileLoop(), JAVA_56_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_57_0_0_0 = INSTANCE.getJAVA_57_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_57_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("do", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_57_0_0_1 = INSTANCE.getJAVA_57_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_57_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDoWhileLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.DO_WHILE_LOOP__STATEMENT), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_57_0_0_2 = INSTANCE.getJAVA_57_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_57_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("while", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_57_0_0_3 = INSTANCE.getJAVA_57_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_57_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_57_0_0_4 = INSTANCE.getJAVA_57_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_57_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("(", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_57_0_0_5 = INSTANCE.getJAVA_57_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_57_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDoWhileLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.DO_WHILE_LOOP__CONDITION), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_57_0_0_6 = INSTANCE.getJAVA_57_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_57_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(")", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_57_0_0_7 = INSTANCE.getJAVA_57_0_0_7();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_57_0_0_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_57_0_0 = INSTANCE.getJAVA_57_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_57_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_57_0_0_0, JAVA_57_0_0_1, JAVA_57_0_0_2, JAVA_57_0_0_3, JAVA_57_0_0_4, JAVA_57_0_0_5, JAVA_57_0_0_6, JAVA_57_0_0_7);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_57_0 = INSTANCE.getJAVA_57_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_57_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_57_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class DoWhileLoop
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_57 = INSTANCE.getJAVA_57();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_57() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDoWhileLoop(), JAVA_57_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_58_0_0_0 = INSTANCE.getJAVA_58_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_58_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_58_0_0 = INSTANCE.getJAVA_58_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_58_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_58_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_58_0 = INSTANCE.getJAVA_58_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_58_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_58_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * EmptyStatement
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_58 = INSTANCE.getJAVA_58();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_58() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getEmptyStatement(), JAVA_58_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_59_0_0_0 = INSTANCE.getJAVA_59_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_59_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("synchronized", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_59_0_0_1 = INSTANCE.getJAVA_59_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_59_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_59_0_0_2 = INSTANCE.getJAVA_59_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_59_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("(", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_59_0_0_3 = INSTANCE.getJAVA_59_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_59_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.SYNCHRONIZED_BLOCK__LOCK_PROVIDER), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_59_0_0_4 = INSTANCE.getJAVA_59_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_59_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(")", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_59_0_0_5 = INSTANCE.getJAVA_59_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_59_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_59_0_0_6 = INSTANCE.getJAVA_59_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_59_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("{", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_59_0_0_7_0_0_0 = INSTANCE.getJAVA_59_0_0_7_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_59_0_0_7_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_59_0_0_7_0_0_1 = INSTANCE.getJAVA_59_0_0_7_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_59_0_0_7_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.SYNCHRONIZED_BLOCK__STATEMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_59_0_0_7_0_0 = INSTANCE.getJAVA_59_0_0_7_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_59_0_0_7_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_59_0_0_7_0_0_0, JAVA_59_0_0_7_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_59_0_0_7_0 = INSTANCE.getJAVA_59_0_0_7_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_59_0_0_7_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_59_0_0_7_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_59_0_0_7 = INSTANCE.getJAVA_59_0_0_7();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_59_0_0_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_59_0_0_7_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_59_0_0_8 = INSTANCE.getJAVA_59_0_0_8();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_59_0_0_8() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_59_0_0_9 = INSTANCE.getJAVA_59_0_0_9();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_59_0_0_9() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("}", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_59_0_0 = INSTANCE.getJAVA_59_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_59_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_59_0_0_0, JAVA_59_0_0_1, JAVA_59_0_0_2, JAVA_59_0_0_3, JAVA_59_0_0_4, JAVA_59_0_0_5, JAVA_59_0_0_6, JAVA_59_0_0_7, JAVA_59_0_0_8, JAVA_59_0_0_9);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_59_0 = INSTANCE.getJAVA_59_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_59_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_59_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * SynchronizedBlock
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_59 = INSTANCE.getJAVA_59();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_59() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), JAVA_59_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_60_0_0_0 = INSTANCE.getJAVA_60_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_60_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("try", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_60_0_0_1 = INSTANCE.getJAVA_60_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_60_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_60_0_0_2 = INSTANCE.getJAVA_60_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_60_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("{", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_60_0_0_3_0_0_0 = INSTANCE.getJAVA_60_0_0_3_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_60_0_0_3_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_60_0_0_3_0_0_1 = INSTANCE.getJAVA_60_0_0_3_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_60_0_0_3_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.TRY_BLOCK__STATEMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_60_0_0_3_0_0 = INSTANCE.getJAVA_60_0_0_3_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_60_0_0_3_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_60_0_0_3_0_0_0, JAVA_60_0_0_3_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_60_0_0_3_0 = INSTANCE.getJAVA_60_0_0_3_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_60_0_0_3_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_60_0_0_3_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_60_0_0_3 = INSTANCE.getJAVA_60_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_60_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_60_0_0_3_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_60_0_0_4 = INSTANCE.getJAVA_60_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_60_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_60_0_0_5 = INSTANCE.getJAVA_60_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_60_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("}", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_60_0_0_6 = INSTANCE.getJAVA_60_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_60_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.TRY_BLOCK__CATCHE_BLOCKS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_60_0_0_7_0_0_0 = INSTANCE.getJAVA_60_0_0_7_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_60_0_0_7_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("finally", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_60_0_0_7_0_0_1 = INSTANCE.getJAVA_60_0_0_7_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_60_0_0_7_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.TRY_BLOCK__FINALLY_BLOCK), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_60_0_0_7_0_0 = INSTANCE.getJAVA_60_0_0_7_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_60_0_0_7_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_60_0_0_7_0_0_0, JAVA_60_0_0_7_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_60_0_0_7_0 = INSTANCE.getJAVA_60_0_0_7_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_60_0_0_7_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_60_0_0_7_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_60_0_0_7 = INSTANCE.getJAVA_60_0_0_7();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_60_0_0_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_60_0_0_7_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_60_0_0 = INSTANCE.getJAVA_60_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_60_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_60_0_0_0, JAVA_60_0_0_1, JAVA_60_0_0_2, JAVA_60_0_0_3, JAVA_60_0_0_4, JAVA_60_0_0_5, JAVA_60_0_0_6, JAVA_60_0_0_7);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_60_0 = INSTANCE.getJAVA_60_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_60_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_60_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class TryBlock
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_60 = INSTANCE.getJAVA_60();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_60() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), JAVA_60_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_61_0_0_0 = INSTANCE.getJAVA_61_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_61_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("catch", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_61_0_0_1 = INSTANCE.getJAVA_61_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_61_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_61_0_0_2 = INSTANCE.getJAVA_61_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_61_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("(", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_61_0_0_3 = INSTANCE.getJAVA_61_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_61_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CATCH_BLOCK__PARAMETER), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_61_0_0_4 = INSTANCE.getJAVA_61_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_61_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(")", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_61_0_0_5 = INSTANCE.getJAVA_61_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_61_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_61_0_0_6 = INSTANCE.getJAVA_61_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_61_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("{", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_61_0_0_7_0_0_0 = INSTANCE.getJAVA_61_0_0_7_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_61_0_0_7_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_61_0_0_7_0_0_1 = INSTANCE.getJAVA_61_0_0_7_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_61_0_0_7_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CATCH_BLOCK__STATEMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_61_0_0_7_0_0 = INSTANCE.getJAVA_61_0_0_7_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_61_0_0_7_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_61_0_0_7_0_0_0, JAVA_61_0_0_7_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_61_0_0_7_0 = INSTANCE.getJAVA_61_0_0_7_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_61_0_0_7_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_61_0_0_7_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_61_0_0_7 = INSTANCE.getJAVA_61_0_0_7();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_61_0_0_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_61_0_0_7_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_61_0_0_8 = INSTANCE.getJAVA_61_0_0_8();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_61_0_0_8() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_61_0_0_9 = INSTANCE.getJAVA_61_0_0_9();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_61_0_0_9() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("}", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_61_0_0 = INSTANCE.getJAVA_61_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_61_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_61_0_0_0, JAVA_61_0_0_1, JAVA_61_0_0_2, JAVA_61_0_0_3, JAVA_61_0_0_4, JAVA_61_0_0_5, JAVA_61_0_0_6, JAVA_61_0_0_7, JAVA_61_0_0_8, JAVA_61_0_0_9);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_61_0 = INSTANCE.getJAVA_61_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_61_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_61_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class CatchBlock
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_61 = INSTANCE.getJAVA_61();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_61() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), JAVA_61_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_62_0_0_0 = INSTANCE.getJAVA_62_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_62_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("switch", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_62_0_0_1 = INSTANCE.getJAVA_62_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_62_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_62_0_0_2 = INSTANCE.getJAVA_62_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_62_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("(", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_62_0_0_3 = INSTANCE.getJAVA_62_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_62_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.SWITCH__VARIABLE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_62_0_0_4 = INSTANCE.getJAVA_62_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_62_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(")", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_62_0_0_5 = INSTANCE.getJAVA_62_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_62_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_62_0_0_6 = INSTANCE.getJAVA_62_0_0_6();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_62_0_0_6() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("{", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_62_0_0_7_0_0_0 = INSTANCE.getJAVA_62_0_0_7_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_62_0_0_7_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.SWITCH__CASES), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitchCase(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_62_0_0_7_0_0 = INSTANCE.getJAVA_62_0_0_7_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_62_0_0_7_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_62_0_0_7_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_62_0_0_7_0 = INSTANCE.getJAVA_62_0_0_7_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_62_0_0_7_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_62_0_0_7_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_62_0_0_7 = INSTANCE.getJAVA_62_0_0_7();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_62_0_0_7() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_62_0_0_7_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_62_0_0_8 = INSTANCE.getJAVA_62_0_0_8();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_62_0_0_8() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("}", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_62_0_0 = INSTANCE.getJAVA_62_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_62_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_62_0_0_0, JAVA_62_0_0_1, JAVA_62_0_0_2, JAVA_62_0_0_3, JAVA_62_0_0_4, JAVA_62_0_0_5, JAVA_62_0_0_6, JAVA_62_0_0_7, JAVA_62_0_0_8);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_62_0 = INSTANCE.getJAVA_62_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_62_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_62_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Switch
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_62 = INSTANCE.getJAVA_62();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_62() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), JAVA_62_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_63_0_0_0 = INSTANCE.getJAVA_63_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_63_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("case", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_63_0_0_1 = INSTANCE.getJAVA_63_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_63_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.NORMAL_SWITCH_CASE__CONDITION), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_63_0_0_2 = INSTANCE.getJAVA_63_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_63_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(":", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_63_0_0_3_0_0_0 = INSTANCE.getJAVA_63_0_0_3_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_63_0_0_3_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_63_0_0_3_0_0_1 = INSTANCE.getJAVA_63_0_0_3_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_63_0_0_3_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.NORMAL_SWITCH_CASE__STATEMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_63_0_0_3_0_0 = INSTANCE.getJAVA_63_0_0_3_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_63_0_0_3_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_63_0_0_3_0_0_0, JAVA_63_0_0_3_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_63_0_0_3_0 = INSTANCE.getJAVA_63_0_0_3_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_63_0_0_3_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_63_0_0_3_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_63_0_0_3 = INSTANCE.getJAVA_63_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_63_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_63_0_0_3_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_63_0_0_4 = INSTANCE.getJAVA_63_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_63_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_63_0_0 = INSTANCE.getJAVA_63_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_63_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_63_0_0_0, JAVA_63_0_0_1, JAVA_63_0_0_2, JAVA_63_0_0_3, JAVA_63_0_0_4);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_63_0 = INSTANCE.getJAVA_63_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_63_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_63_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * NormalSwitchCase
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_63 = INSTANCE.getJAVA_63();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_63() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), JAVA_63_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_64_0_0_0 = INSTANCE.getJAVA_64_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_64_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("default", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_64_0_0_1 = INSTANCE.getJAVA_64_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_64_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(":", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_64_0_0_2_0_0_0 = INSTANCE.getJAVA_64_0_0_2_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_64_0_0_2_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_64_0_0_2_0_0_1 = INSTANCE.getJAVA_64_0_0_2_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_64_0_0_2_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.DEFAULT_SWITCH_CASE__STATEMENTS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_64_0_0_2_0_0 = INSTANCE.getJAVA_64_0_0_2_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_64_0_0_2_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_64_0_0_2_0_0_0, JAVA_64_0_0_2_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_64_0_0_2_0 = INSTANCE.getJAVA_64_0_0_2_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_64_0_0_2_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_64_0_0_2_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_64_0_0_2 = INSTANCE.getJAVA_64_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_64_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_64_0_0_2_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaLineBreak JAVA_64_0_0_3 = INSTANCE.getJAVA_64_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaLineBreak getJAVA_64_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaLineBreak(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_64_0_0 = INSTANCE.getJAVA_64_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_64_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_64_0_0_0, JAVA_64_0_0_1, JAVA_64_0_0_2, JAVA_64_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_64_0 = INSTANCE.getJAVA_64_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_64_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_64_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * DefaultSwitchCase
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_64 = INSTANCE.getJAVA_64();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_64() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), JAVA_64_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_65_0_0_0 = INSTANCE.getJAVA_65_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_65_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("return", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_65_0_0_1 = INSTANCE.getJAVA_65_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_65_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getReturn().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.RETURN__RETURN_VALUE), org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_65_0_0_2 = INSTANCE.getJAVA_65_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_65_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_65_0_0 = INSTANCE.getJAVA_65_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_65_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_65_0_0_0, JAVA_65_0_0_1, JAVA_65_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_65_0 = INSTANCE.getJAVA_65_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_65_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_65_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Return
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_65 = INSTANCE.getJAVA_65();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_65() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getReturn(), JAVA_65_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_66_0_0_0 = INSTANCE.getJAVA_66_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_66_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("throw", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_66_0_0_1 = INSTANCE.getJAVA_66_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_66_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getThrow().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.THROW__THROWABLE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_66_0_0_2 = INSTANCE.getJAVA_66_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_66_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_66_0_0 = INSTANCE.getJAVA_66_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_66_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_66_0_0_0, JAVA_66_0_0_1, JAVA_66_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_66_0 = INSTANCE.getJAVA_66_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_66_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_66_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Throw
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_66 = INSTANCE.getJAVA_66();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_66() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getThrow(), JAVA_66_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_67_0_0_0 = INSTANCE.getJAVA_67_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_67_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("break", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_67_0_0_1_0_0_0 = INSTANCE.getJAVA_67_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_67_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBreak().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.BREAK__TARGET), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_67_0_0_1_0_0 = INSTANCE.getJAVA_67_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_67_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_67_0_0_1_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_67_0_0_1_0 = INSTANCE.getJAVA_67_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_67_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_67_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_67_0_0_1 = INSTANCE.getJAVA_67_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_67_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_67_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_67_0_0_2 = INSTANCE.getJAVA_67_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_67_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_67_0_0 = INSTANCE.getJAVA_67_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_67_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_67_0_0_0, JAVA_67_0_0_1, JAVA_67_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_67_0 = INSTANCE.getJAVA_67_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_67_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_67_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Break
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_67 = INSTANCE.getJAVA_67();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_67() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBreak(), JAVA_67_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_68_0_0_0 = INSTANCE.getJAVA_68_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_68_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("continue", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_68_0_0_1_0_0_0 = INSTANCE.getJAVA_68_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_68_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getContinue().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONTINUE__TARGET), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_68_0_0_1_0_0 = INSTANCE.getJAVA_68_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_68_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_68_0_0_1_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_68_0_0_1_0 = INSTANCE.getJAVA_68_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_68_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_68_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_68_0_0_1 = INSTANCE.getJAVA_68_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_68_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_68_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_68_0_0_2 = INSTANCE.getJAVA_68_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_68_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_68_0_0 = INSTANCE.getJAVA_68_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_68_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_68_0_0_0, JAVA_68_0_0_1, JAVA_68_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_68_0 = INSTANCE.getJAVA_68_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_68_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_68_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Continue
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_68 = INSTANCE.getJAVA_68();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_68() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getContinue(), JAVA_68_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_69_0_0_0 = INSTANCE.getJAVA_69_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_69_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getJumpLabel().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.JUMP_LABEL__NAME), "IDENTIFIER", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_69_0_0_1 = INSTANCE.getJAVA_69_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_69_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(":", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_69_0_0_2 = INSTANCE.getJAVA_69_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_69_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getJumpLabel().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.JUMP_LABEL__STATEMENT), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_69_0_0 = INSTANCE.getJAVA_69_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_69_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_69_0_0_0, JAVA_69_0_0_1, JAVA_69_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_69_0 = INSTANCE.getJAVA_69_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_69_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_69_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class JumpLabel
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_69 = INSTANCE.getJAVA_69();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_69() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getJumpLabel(), JAVA_69_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_70_0_0_0 = INSTANCE.getJAVA_70_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_70_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getExpressionStatement().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.EXPRESSION_STATEMENT__EXPRESSION), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_70_0_0_1 = INSTANCE.getJAVA_70_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_70_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(";", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_70_0_0 = INSTANCE.getJAVA_70_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_70_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_70_0_0_0, JAVA_70_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_70_0 = INSTANCE.getJAVA_70_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_70_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_70_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * ExpressionStatement
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_70 = INSTANCE.getJAVA_70();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_70() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getExpressionStatement(), JAVA_70_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_71_0_0_0 = INSTANCE.getJAVA_71_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_71_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getExpressionList().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EXPRESSION_LIST__EXPRESSIONS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_71_0_0_1_0_0_0 = INSTANCE.getJAVA_71_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_71_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(",", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_71_0_0_1_0_0_1 = INSTANCE.getJAVA_71_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_71_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getExpressionList().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EXPRESSION_LIST__EXPRESSIONS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_71_0_0_1_0_0 = INSTANCE.getJAVA_71_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_71_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_71_0_0_1_0_0_0, JAVA_71_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_71_0_0_1_0 = INSTANCE.getJAVA_71_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_71_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_71_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_71_0_0_1 = INSTANCE.getJAVA_71_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_71_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_71_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_71_0_0 = INSTANCE.getJAVA_71_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_71_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_71_0_0_0, JAVA_71_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_71_0 = INSTANCE.getJAVA_71_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_71_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_71_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * ExpressionList
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_71 = INSTANCE.getJAVA_71();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_71() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getExpressionList(), JAVA_71_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_72_0_0_0 = INSTANCE.getJAVA_72_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_72_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ASSIGNMENT_EXPRESSION__CHILD), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_72_0_0_1_0_0_0 = INSTANCE.getJAVA_72_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_72_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_72_0_0_1_0_0_1 = INSTANCE.getJAVA_72_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_72_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ASSIGNMENT_EXPRESSION__ASSIGNMENT_OPERATOR), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentOperator(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_72_0_0_1_0_0_2 = INSTANCE.getJAVA_72_0_0_1_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_72_0_0_1_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_72_0_0_1_0_0_3 = INSTANCE.getJAVA_72_0_0_1_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_72_0_0_1_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ASSIGNMENT_EXPRESSION__VALUE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_72_0_0_1_0_0 = INSTANCE.getJAVA_72_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_72_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_72_0_0_1_0_0_0, JAVA_72_0_0_1_0_0_1, JAVA_72_0_0_1_0_0_2, JAVA_72_0_0_1_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_72_0_0_1_0 = INSTANCE.getJAVA_72_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_72_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_72_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_72_0_0_1 = INSTANCE.getJAVA_72_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_72_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_72_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_72_0_0 = INSTANCE.getJAVA_72_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_72_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_72_0_0_0, JAVA_72_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_72_0 = INSTANCE.getJAVA_72_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_72_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_72_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * AssignmentExpression
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_72 = INSTANCE.getJAVA_72();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_72() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), JAVA_72_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_73_0_0_0 = INSTANCE.getJAVA_73_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_73_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_EXPRESSION__CHILD), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalOrExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_73_0_0_1_0_0_0 = INSTANCE.getJAVA_73_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_73_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("?", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_73_0_0_1_0_0_1 = INSTANCE.getJAVA_73_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_73_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_EXPRESSION__EXPRESSION_IF), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_73_0_0_1_0_0_2 = INSTANCE.getJAVA_73_0_0_1_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_73_0_0_1_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(":", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_73_0_0_1_0_0_3 = INSTANCE.getJAVA_73_0_0_1_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_73_0_0_1_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_EXPRESSION__EXPRESSION_ELSE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_73_0_0_1_0_0 = INSTANCE.getJAVA_73_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_73_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_73_0_0_1_0_0_0, JAVA_73_0_0_1_0_0_1, JAVA_73_0_0_1_0_0_2, JAVA_73_0_0_1_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_73_0_0_1_0 = INSTANCE.getJAVA_73_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_73_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_73_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_73_0_0_1 = INSTANCE.getJAVA_73_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_73_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_73_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_73_0_0 = INSTANCE.getJAVA_73_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_73_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_73_0_0_0, JAVA_73_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_73_0 = INSTANCE.getJAVA_73_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_73_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_73_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * ConditionalExpression
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_73 = INSTANCE.getJAVA_73();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_73() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalExpression(), JAVA_73_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_74_0_0_0 = INSTANCE.getJAVA_74_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_74_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalOrExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_OR_EXPRESSION__CHILDREN), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalAndExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_74_0_0_1_0_0_0 = INSTANCE.getJAVA_74_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_74_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("||", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_74_0_0_1_0_0_1 = INSTANCE.getJAVA_74_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_74_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalOrExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_OR_EXPRESSION__CHILDREN), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalAndExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_74_0_0_1_0_0 = INSTANCE.getJAVA_74_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_74_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_74_0_0_1_0_0_0, JAVA_74_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_74_0_0_1_0 = INSTANCE.getJAVA_74_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_74_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_74_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_74_0_0_1 = INSTANCE.getJAVA_74_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_74_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_74_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_74_0_0 = INSTANCE.getJAVA_74_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_74_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_74_0_0_0, JAVA_74_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_74_0 = INSTANCE.getJAVA_74_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_74_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_74_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * ConditionalOrExpression
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_74 = INSTANCE.getJAVA_74();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_74() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalOrExpression(), JAVA_74_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_75_0_0_0 = INSTANCE.getJAVA_75_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_75_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalAndExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_AND_EXPRESSION__CHILDREN), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInclusiveOrExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_75_0_0_1_0_0_0 = INSTANCE.getJAVA_75_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_75_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("&&", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_75_0_0_1_0_0_1 = INSTANCE.getJAVA_75_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_75_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalAndExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_AND_EXPRESSION__CHILDREN), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInclusiveOrExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_75_0_0_1_0_0 = INSTANCE.getJAVA_75_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_75_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_75_0_0_1_0_0_0, JAVA_75_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_75_0_0_1_0 = INSTANCE.getJAVA_75_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_75_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_75_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_75_0_0_1 = INSTANCE.getJAVA_75_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_75_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_75_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_75_0_0 = INSTANCE.getJAVA_75_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_75_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_75_0_0_0, JAVA_75_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_75_0 = INSTANCE.getJAVA_75_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_75_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_75_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * ConditionalAndExpression
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_75 = INSTANCE.getJAVA_75();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_75() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalAndExpression(), JAVA_75_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_76_0_0_0 = INSTANCE.getJAVA_76_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_76_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInclusiveOrExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INCLUSIVE_OR_EXPRESSION__CHILDREN), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getExclusiveOrExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_76_0_0_1_0_0_0 = INSTANCE.getJAVA_76_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_76_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("|", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_76_0_0_1_0_0_1 = INSTANCE.getJAVA_76_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_76_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInclusiveOrExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INCLUSIVE_OR_EXPRESSION__CHILDREN), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getExclusiveOrExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_76_0_0_1_0_0 = INSTANCE.getJAVA_76_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_76_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_76_0_0_1_0_0_0, JAVA_76_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_76_0_0_1_0 = INSTANCE.getJAVA_76_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_76_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_76_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_76_0_0_1 = INSTANCE.getJAVA_76_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_76_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_76_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_76_0_0 = INSTANCE.getJAVA_76_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_76_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_76_0_0_0, JAVA_76_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_76_0 = INSTANCE.getJAVA_76_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_76_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_76_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * InclusiveOrExpression
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_76 = INSTANCE.getJAVA_76();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_76() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInclusiveOrExpression(), JAVA_76_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_77_0_0_0 = INSTANCE.getJAVA_77_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_77_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getExclusiveOrExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EXCLUSIVE_OR_EXPRESSION__CHILDREN), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAndExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_77_0_0_1_0_0_0 = INSTANCE.getJAVA_77_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_77_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("^", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_77_0_0_1_0_0_1 = INSTANCE.getJAVA_77_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_77_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getExclusiveOrExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EXCLUSIVE_OR_EXPRESSION__CHILDREN), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAndExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_77_0_0_1_0_0 = INSTANCE.getJAVA_77_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_77_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_77_0_0_1_0_0_0, JAVA_77_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_77_0_0_1_0 = INSTANCE.getJAVA_77_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_77_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_77_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_77_0_0_1 = INSTANCE.getJAVA_77_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_77_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_77_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_77_0_0 = INSTANCE.getJAVA_77_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_77_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_77_0_0_0, JAVA_77_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_77_0 = INSTANCE.getJAVA_77_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_77_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_77_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * ExclusiveOrExpression
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_77 = INSTANCE.getJAVA_77();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_77() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getExclusiveOrExpression(), JAVA_77_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_78_0_0_0 = INSTANCE.getJAVA_78_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_78_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAndExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.AND_EXPRESSION__CHILDREN), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_78_0_0_1_0_0_0 = INSTANCE.getJAVA_78_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_78_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("&", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_78_0_0_1_0_0_1 = INSTANCE.getJAVA_78_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_78_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAndExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.AND_EXPRESSION__CHILDREN), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_78_0_0_1_0_0 = INSTANCE.getJAVA_78_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_78_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_78_0_0_1_0_0_0, JAVA_78_0_0_1_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_78_0_0_1_0 = INSTANCE.getJAVA_78_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_78_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_78_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_78_0_0_1 = INSTANCE.getJAVA_78_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_78_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_78_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_78_0_0 = INSTANCE.getJAVA_78_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_78_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_78_0_0_0, JAVA_78_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_78_0 = INSTANCE.getJAVA_78_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_78_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_78_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * AndExpression
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_78 = INSTANCE.getJAVA_78();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_78() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAndExpression(), JAVA_78_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_79_0_0_0 = INSTANCE.getJAVA_79_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_79_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EQUALITY_EXPRESSION__CHILDREN), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInstanceOfExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_79_0_0_1_0_0_0 = INSTANCE.getJAVA_79_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_79_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_79_0_0_1_0_0_1 = INSTANCE.getJAVA_79_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_79_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EQUALITY_EXPRESSION__EQUALITY_OPERATORS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getEqualityOperator(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_79_0_0_1_0_0_2 = INSTANCE.getJAVA_79_0_0_1_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_79_0_0_1_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_79_0_0_1_0_0_3 = INSTANCE.getJAVA_79_0_0_1_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_79_0_0_1_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EQUALITY_EXPRESSION__CHILDREN), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInstanceOfExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_79_0_0_1_0_0 = INSTANCE.getJAVA_79_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_79_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_79_0_0_1_0_0_0, JAVA_79_0_0_1_0_0_1, JAVA_79_0_0_1_0_0_2, JAVA_79_0_0_1_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_79_0_0_1_0 = INSTANCE.getJAVA_79_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_79_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_79_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_79_0_0_1 = INSTANCE.getJAVA_79_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_79_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_79_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_79_0_0 = INSTANCE.getJAVA_79_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_79_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_79_0_0_0, JAVA_79_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_79_0 = INSTANCE.getJAVA_79_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_79_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_79_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * EqualityExpression
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_79 = INSTANCE.getJAVA_79();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_79() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), JAVA_79_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_80_0_0_0 = INSTANCE.getJAVA_80_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_80_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInstanceOfExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INSTANCE_OF_EXPRESSION__CHILD), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_80_0_0_1_0_0_0 = INSTANCE.getJAVA_80_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_80_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("instanceof", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_80_0_0_1_0_0_1 = INSTANCE.getJAVA_80_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_80_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInstanceOfExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INSTANCE_OF_EXPRESSION__TYPE_REFERENCE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_80_0_0_1_0_0_2 = INSTANCE.getJAVA_80_0_0_1_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_80_0_0_1_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInstanceOfExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INSTANCE_OF_EXPRESSION__ARRAY_DIMENSIONS_BEFORE), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_80_0_0_1_0_0 = INSTANCE.getJAVA_80_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_80_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_80_0_0_1_0_0_0, JAVA_80_0_0_1_0_0_1, JAVA_80_0_0_1_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_80_0_0_1_0 = INSTANCE.getJAVA_80_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_80_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_80_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_80_0_0_1 = INSTANCE.getJAVA_80_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_80_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_80_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_80_0_0 = INSTANCE.getJAVA_80_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_80_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_80_0_0_0, JAVA_80_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_80_0 = INSTANCE.getJAVA_80_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_80_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_80_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * InstanceOfExpression
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_80 = INSTANCE.getJAVA_80();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_80() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInstanceOfExpression(), JAVA_80_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_81_0_0_0 = INSTANCE.getJAVA_81_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_81_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.RELATION_EXPRESSION__CHILDREN), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_81_0_0_1_0_0_0 = INSTANCE.getJAVA_81_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_81_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_81_0_0_1_0_0_1 = INSTANCE.getJAVA_81_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_81_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.RELATION_EXPRESSION__RELATION_OPERATORS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getRelationOperator(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_81_0_0_1_0_0_2 = INSTANCE.getJAVA_81_0_0_1_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_81_0_0_1_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_81_0_0_1_0_0_3 = INSTANCE.getJAVA_81_0_0_1_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_81_0_0_1_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.RELATION_EXPRESSION__CHILDREN), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_81_0_0_1_0_0 = INSTANCE.getJAVA_81_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_81_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_81_0_0_1_0_0_0, JAVA_81_0_0_1_0_0_1, JAVA_81_0_0_1_0_0_2, JAVA_81_0_0_1_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_81_0_0_1_0 = INSTANCE.getJAVA_81_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_81_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_81_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_81_0_0_1 = INSTANCE.getJAVA_81_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_81_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_81_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_81_0_0 = INSTANCE.getJAVA_81_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_81_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_81_0_0_0, JAVA_81_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_81_0 = INSTANCE.getJAVA_81_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_81_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_81_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * RelationExpression
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_81 = INSTANCE.getJAVA_81();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_81() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), JAVA_81_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_82_0_0_0 = INSTANCE.getJAVA_82_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_82_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SHIFT_EXPRESSION__CHILDREN), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_82_0_0_1_0_0_0 = INSTANCE.getJAVA_82_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_82_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_82_0_0_1_0_0_1 = INSTANCE.getJAVA_82_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_82_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SHIFT_EXPRESSION__SHIFT_OPERATORS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getShiftOperator(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_82_0_0_1_0_0_2 = INSTANCE.getJAVA_82_0_0_1_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_82_0_0_1_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_82_0_0_1_0_0_3 = INSTANCE.getJAVA_82_0_0_1_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_82_0_0_1_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SHIFT_EXPRESSION__CHILDREN), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_82_0_0_1_0_0 = INSTANCE.getJAVA_82_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_82_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_82_0_0_1_0_0_0, JAVA_82_0_0_1_0_0_1, JAVA_82_0_0_1_0_0_2, JAVA_82_0_0_1_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_82_0_0_1_0 = INSTANCE.getJAVA_82_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_82_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_82_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_82_0_0_1 = INSTANCE.getJAVA_82_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_82_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_82_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_82_0_0 = INSTANCE.getJAVA_82_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_82_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_82_0_0_0, JAVA_82_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_82_0 = INSTANCE.getJAVA_82_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_82_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_82_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * ShiftExpression
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_82 = INSTANCE.getJAVA_82();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_82() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), JAVA_82_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_83_0_0_0 = INSTANCE.getJAVA_83_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_83_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ADDITIVE_EXPRESSION__CHILDREN), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_83_0_0_1_0_0_0 = INSTANCE.getJAVA_83_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_83_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_83_0_0_1_0_0_1 = INSTANCE.getJAVA_83_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_83_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ADDITIVE_EXPRESSION__ADDITIVE_OPERATORS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAdditiveOperator(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_83_0_0_1_0_0_2 = INSTANCE.getJAVA_83_0_0_1_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_83_0_0_1_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_83_0_0_1_0_0_3 = INSTANCE.getJAVA_83_0_0_1_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_83_0_0_1_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ADDITIVE_EXPRESSION__CHILDREN), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_83_0_0_1_0_0 = INSTANCE.getJAVA_83_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_83_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_83_0_0_1_0_0_0, JAVA_83_0_0_1_0_0_1, JAVA_83_0_0_1_0_0_2, JAVA_83_0_0_1_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_83_0_0_1_0 = INSTANCE.getJAVA_83_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_83_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_83_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_83_0_0_1 = INSTANCE.getJAVA_83_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_83_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_83_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_83_0_0 = INSTANCE.getJAVA_83_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_83_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_83_0_0_0, JAVA_83_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_83_0 = INSTANCE.getJAVA_83_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_83_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_83_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * AdditiveExpression
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_83 = INSTANCE.getJAVA_83();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_83() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), JAVA_83_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_84_0_0_0 = INSTANCE.getJAVA_84_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_84_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.MULTIPLICATIVE_EXPRESSION__CHILDREN), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_84_0_0_1_0_0_0 = INSTANCE.getJAVA_84_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_84_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_84_0_0_1_0_0_1 = INSTANCE.getJAVA_84_0_0_1_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_84_0_0_1_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OPERATORS), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getMultiplicativeOperator(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_84_0_0_1_0_0_2 = INSTANCE.getJAVA_84_0_0_1_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_84_0_0_1_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_84_0_0_1_0_0_3 = INSTANCE.getJAVA_84_0_0_1_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_84_0_0_1_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.MULTIPLICATIVE_EXPRESSION__CHILDREN), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_84_0_0_1_0_0 = INSTANCE.getJAVA_84_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_84_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_84_0_0_1_0_0_0, JAVA_84_0_0_1_0_0_1, JAVA_84_0_0_1_0_0_2, JAVA_84_0_0_1_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_84_0_0_1_0 = INSTANCE.getJAVA_84_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_84_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_84_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_84_0_0_1 = INSTANCE.getJAVA_84_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_84_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_84_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_84_0_0 = INSTANCE.getJAVA_84_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_84_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_84_0_0_0, JAVA_84_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_84_0 = INSTANCE.getJAVA_84_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_84_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_84_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * MultiplicativeExpression
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_84 = INSTANCE.getJAVA_84();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_84() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), JAVA_84_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_85_0_0_0 = INSTANCE.getJAVA_85_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_85_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.UNARY_EXPRESSION__OPERATORS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getUnaryOperator(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_85_0_0_1 = INSTANCE.getJAVA_85_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_85_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.UNARY_EXPRESSION__CHILD), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryModificationExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_85_0_0 = INSTANCE.getJAVA_85_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_85_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_85_0_0_0, JAVA_85_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_85_0 = INSTANCE.getJAVA_85_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_85_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_85_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * UnaryExpression
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_85 = INSTANCE.getJAVA_85();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_85() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryExpression(), JAVA_85_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_86_0_0_0 = INSTANCE.getJAVA_86_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_86_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SUFFIX_UNARY_MODIFICATION_EXPRESSION__CHILD), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryModificationExpressionChild(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_86_0_0_1_0_0_0 = INSTANCE.getJAVA_86_0_0_1_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_86_0_0_1_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SUFFIX_UNARY_MODIFICATION_EXPRESSION__OPERATOR), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getUnaryModificationOperator(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_86_0_0_1_0_0 = INSTANCE.getJAVA_86_0_0_1_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_86_0_0_1_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_86_0_0_1_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_86_0_0_1_0 = INSTANCE.getJAVA_86_0_0_1_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_86_0_0_1_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_86_0_0_1_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_86_0_0_1 = INSTANCE.getJAVA_86_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_86_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_86_0_0_1_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_86_0_0 = INSTANCE.getJAVA_86_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_86_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_86_0_0_0, JAVA_86_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_86_0 = INSTANCE.getJAVA_86_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_86_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_86_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * SuffixUnaryModificationExpression
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_86 = INSTANCE.getJAVA_86();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_86() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), JAVA_86_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_87_0_0_0_0_0_0 = INSTANCE.getJAVA_87_0_0_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_87_0_0_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getPrefixUnaryModificationExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.PREFIX_UNARY_MODIFICATION_EXPRESSION__OPERATOR), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getUnaryModificationOperator(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_87_0_0_0_0_0 = INSTANCE.getJAVA_87_0_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_87_0_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_87_0_0_0_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_87_0_0_0_0 = INSTANCE.getJAVA_87_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_87_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_87_0_0_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_87_0_0_0 = INSTANCE.getJAVA_87_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_87_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_87_0_0_0_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_87_0_0_1 = INSTANCE.getJAVA_87_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_87_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getPrefixUnaryModificationExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.PREFIX_UNARY_MODIFICATION_EXPRESSION__CHILD), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryModificationExpressionChild(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_87_0_0 = INSTANCE.getJAVA_87_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_87_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_87_0_0_0, JAVA_87_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_87_0 = INSTANCE.getJAVA_87_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_87_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_87_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * PrefixUnaryModificationExpression
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_87 = INSTANCE.getJAVA_87();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_87() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getPrefixUnaryModificationExpression(), JAVA_87_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_88_0_0_0 = INSTANCE.getJAVA_88_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_88_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("(", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_88_0_0_1 = INSTANCE.getJAVA_88_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_88_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getCastExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CAST_EXPRESSION__TYPE_REFERENCE), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_88_0_0_2 = INSTANCE.getJAVA_88_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_88_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getCastExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_BEFORE), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_88_0_0_3 = INSTANCE.getJAVA_88_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_88_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(")", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaWhiteSpace JAVA_88_0_0_4 = INSTANCE.getJAVA_88_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaWhiteSpace getJAVA_88_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaWhiteSpace(1, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_88_0_0_5 = INSTANCE.getJAVA_88_0_0_5();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_88_0_0_5() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getCastExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CAST_EXPRESSION__CHILD), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_88_0_0 = INSTANCE.getJAVA_88_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_88_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_88_0_0_0, JAVA_88_0_0_1, JAVA_88_0_0_2, JAVA_88_0_0_3, JAVA_88_0_0_4, JAVA_88_0_0_5);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_88_0 = INSTANCE.getJAVA_88_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_88_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_88_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * CastExpression
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_88 = INSTANCE.getJAVA_88();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_88() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getCastExpression(), JAVA_88_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_89_0_0_0 = INSTANCE.getJAVA_89_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_89_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("(", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_89_0_0_1 = INSTANCE.getJAVA_89_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_89_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getNestedExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.NESTED_EXPRESSION__EXPRESSION), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_89_0_0_2 = INSTANCE.getJAVA_89_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_89_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(")", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_89_0_0_3 = INSTANCE.getJAVA_89_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_89_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getNestedExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.NESTED_EXPRESSION__ARRAY_SELECTORS), org.emftext.language.java.resource.java.grammar.JavaCardinality.STAR, new EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArraySelector(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_89_0_0_4_0_0_0 = INSTANCE.getJAVA_89_0_0_4_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_89_0_0_4_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(".", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaContainment JAVA_89_0_0_4_0_0_1 = INSTANCE.getJAVA_89_0_0_4_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaContainment getJAVA_89_0_0_4_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getNestedExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.NESTED_EXPRESSION__NEXT), org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, new EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_89_0_0_4_0_0 = INSTANCE.getJAVA_89_0_0_4_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_89_0_0_4_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_89_0_0_4_0_0_0, JAVA_89_0_0_4_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_89_0_0_4_0 = INSTANCE.getJAVA_89_0_0_4_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_89_0_0_4_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_89_0_0_4_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_89_0_0_4 = INSTANCE.getJAVA_89_0_0_4();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_89_0_0_4() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_89_0_0_4_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.QUESTIONMARK);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_89_0_0 = INSTANCE.getJAVA_89_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_89_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_89_0_0_0, JAVA_89_0_0_1, JAVA_89_0_0_2, JAVA_89_0_0_3, JAVA_89_0_0_4);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_89_0 = INSTANCE.getJAVA_89_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_89_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_89_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * NestedExpression
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_89 = INSTANCE.getJAVA_89();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_89() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getNestedExpression(), JAVA_89_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_90_0_0_0 = INSTANCE.getJAVA_90_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_90_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("=", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_90_0_0 = INSTANCE.getJAVA_90_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_90_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_90_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_90_0 = INSTANCE.getJAVA_90_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_90_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_90_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Assignment
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_90 = INSTANCE.getJAVA_90();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_90() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignment(), JAVA_90_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_91_0_0_0 = INSTANCE.getJAVA_91_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_91_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("+=", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_91_0_0 = INSTANCE.getJAVA_91_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_91_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_91_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_91_0 = INSTANCE.getJAVA_91_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_91_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_91_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * AssignmentPlus
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_91 = INSTANCE.getJAVA_91();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_91() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentPlus(), JAVA_91_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_92_0_0_0 = INSTANCE.getJAVA_92_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_92_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("-=", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_92_0_0 = INSTANCE.getJAVA_92_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_92_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_92_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_92_0 = INSTANCE.getJAVA_92_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_92_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_92_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * AssignmentMinus
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_92 = INSTANCE.getJAVA_92();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_92() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentMinus(), JAVA_92_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_93_0_0_0 = INSTANCE.getJAVA_93_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_93_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("*=", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_93_0_0 = INSTANCE.getJAVA_93_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_93_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_93_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_93_0 = INSTANCE.getJAVA_93_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_93_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_93_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * AssignmentMultiplication
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_93 = INSTANCE.getJAVA_93();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_93() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentMultiplication(), JAVA_93_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_94_0_0_0 = INSTANCE.getJAVA_94_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_94_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("/=", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_94_0_0 = INSTANCE.getJAVA_94_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_94_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_94_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_94_0 = INSTANCE.getJAVA_94_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_94_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_94_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * AssignmentDivision
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_94 = INSTANCE.getJAVA_94();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_94() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentDivision(), JAVA_94_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_95_0_0_0 = INSTANCE.getJAVA_95_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_95_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("&=", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_95_0_0 = INSTANCE.getJAVA_95_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_95_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_95_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_95_0 = INSTANCE.getJAVA_95_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_95_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_95_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * AssignmentAnd
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_95 = INSTANCE.getJAVA_95();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_95() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentAnd(), JAVA_95_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_96_0_0_0 = INSTANCE.getJAVA_96_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_96_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("|=", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_96_0_0 = INSTANCE.getJAVA_96_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_96_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_96_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_96_0 = INSTANCE.getJAVA_96_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_96_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_96_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class AssignmentOr
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_96 = INSTANCE.getJAVA_96();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_96() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentOr(), JAVA_96_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_97_0_0_0 = INSTANCE.getJAVA_97_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_97_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("^=", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_97_0_0 = INSTANCE.getJAVA_97_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_97_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_97_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_97_0 = INSTANCE.getJAVA_97_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_97_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_97_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * AssignmentExclusiveOr
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_97 = INSTANCE.getJAVA_97();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_97() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentExclusiveOr(), JAVA_97_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_98_0_0_0 = INSTANCE.getJAVA_98_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_98_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("%=", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_98_0_0 = INSTANCE.getJAVA_98_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_98_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_98_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_98_0 = INSTANCE.getJAVA_98_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_98_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_98_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * AssignmentModulo
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_98 = INSTANCE.getJAVA_98();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_98() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentModulo(), JAVA_98_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_99_0_0_0 = INSTANCE.getJAVA_99_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_99_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_99_0_0_1 = INSTANCE.getJAVA_99_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_99_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_99_0_0_2 = INSTANCE.getJAVA_99_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_99_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("=", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_99_0_0 = INSTANCE.getJAVA_99_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_99_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_99_0_0_0, JAVA_99_0_0_1, JAVA_99_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_99_0 = INSTANCE.getJAVA_99_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_99_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_99_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * AssignmentLeftShift
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_99 = INSTANCE.getJAVA_99();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_99() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentLeftShift(), JAVA_99_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_100_0_0_0 = INSTANCE.getJAVA_100_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_100_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_100_0_0_1 = INSTANCE.getJAVA_100_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_100_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_100_0_0_2 = INSTANCE.getJAVA_100_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_100_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("=", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_100_0_0 = INSTANCE.getJAVA_100_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_100_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_100_0_0_0, JAVA_100_0_0_1, JAVA_100_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_100_0 = INSTANCE.getJAVA_100_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_100_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_100_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * AssignmentRightShift
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_100 = INSTANCE.getJAVA_100();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_100() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentRightShift(), JAVA_100_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_101_0_0_0 = INSTANCE.getJAVA_101_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_101_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_101_0_0_1 = INSTANCE.getJAVA_101_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_101_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_101_0_0_2 = INSTANCE.getJAVA_101_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_101_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_101_0_0_3 = INSTANCE.getJAVA_101_0_0_3();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_101_0_0_3() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("=", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_101_0_0 = INSTANCE.getJAVA_101_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_101_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_101_0_0_0, JAVA_101_0_0_1, JAVA_101_0_0_2, JAVA_101_0_0_3);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_101_0 = INSTANCE.getJAVA_101_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_101_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_101_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * AssignmentUnsignedRightShift
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_101 = INSTANCE.getJAVA_101();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_101() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentUnsignedRightShift(), JAVA_101_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_102_0_0_0 = INSTANCE.getJAVA_102_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_102_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("+", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_102_0_0 = INSTANCE.getJAVA_102_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_102_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_102_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_102_0 = INSTANCE.getJAVA_102_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_102_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_102_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Addition
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_102 = INSTANCE.getJAVA_102();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_102() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAddition(), JAVA_102_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_103_0_0_0 = INSTANCE.getJAVA_103_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_103_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("-", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_103_0_0 = INSTANCE.getJAVA_103_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_103_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_103_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_103_0 = INSTANCE.getJAVA_103_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_103_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_103_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Subtraction
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_103 = INSTANCE.getJAVA_103();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_103() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getSubtraction(), JAVA_103_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_104_0_0_0 = INSTANCE.getJAVA_104_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_104_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("*", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_104_0_0 = INSTANCE.getJAVA_104_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_104_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_104_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_104_0 = INSTANCE.getJAVA_104_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_104_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_104_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * Multiplication
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_104 = INSTANCE.getJAVA_104();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_104() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getMultiplication(), JAVA_104_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_105_0_0_0 = INSTANCE.getJAVA_105_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_105_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("/", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_105_0_0 = INSTANCE.getJAVA_105_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_105_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_105_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_105_0 = INSTANCE.getJAVA_105_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_105_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_105_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Division
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_105 = INSTANCE.getJAVA_105();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_105() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getDivision(), JAVA_105_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_106_0_0_0 = INSTANCE.getJAVA_106_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_106_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("%", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_106_0_0 = INSTANCE.getJAVA_106_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_106_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_106_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_106_0 = INSTANCE.getJAVA_106_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_106_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_106_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Remainder
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_106 = INSTANCE.getJAVA_106();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_106() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getRemainder(), JAVA_106_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_107_0_0_0 = INSTANCE.getJAVA_107_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_107_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_107_0_0 = INSTANCE.getJAVA_107_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_107_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_107_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_107_0 = INSTANCE.getJAVA_107_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_107_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_107_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class LessThan
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_107 = INSTANCE.getJAVA_107();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_107() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getLessThan(), JAVA_107_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_108_0_0_0 = INSTANCE.getJAVA_108_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_108_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_108_0_0_1 = INSTANCE.getJAVA_108_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_108_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("=", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_108_0_0 = INSTANCE.getJAVA_108_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_108_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_108_0_0_0, JAVA_108_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_108_0 = INSTANCE.getJAVA_108_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_108_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_108_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * LessThanOrEqual
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_108 = INSTANCE.getJAVA_108();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_108() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getLessThanOrEqual(), JAVA_108_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_109_0_0_0 = INSTANCE.getJAVA_109_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_109_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_109_0_0 = INSTANCE.getJAVA_109_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_109_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_109_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_109_0 = INSTANCE.getJAVA_109_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_109_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_109_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class GreaterThan
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_109 = INSTANCE.getJAVA_109();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_109() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getGreaterThan(), JAVA_109_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_110_0_0_0 = INSTANCE.getJAVA_110_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_110_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_110_0_0_1 = INSTANCE.getJAVA_110_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_110_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("=", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_110_0_0 = INSTANCE.getJAVA_110_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_110_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_110_0_0_0, JAVA_110_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_110_0 = INSTANCE.getJAVA_110_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_110_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_110_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * GreaterThanOrEqual
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_110 = INSTANCE.getJAVA_110();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_110() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getGreaterThanOrEqual(), JAVA_110_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_111_0_0_0 = INSTANCE.getJAVA_111_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_111_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_111_0_0_1 = INSTANCE.getJAVA_111_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_111_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("<", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_111_0_0 = INSTANCE.getJAVA_111_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_111_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_111_0_0_0, JAVA_111_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_111_0 = INSTANCE.getJAVA_111_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_111_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_111_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class LeftShift
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_111 = INSTANCE.getJAVA_111();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_111() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getLeftShift(), JAVA_111_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_112_0_0_0 = INSTANCE.getJAVA_112_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_112_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_112_0_0_1 = INSTANCE.getJAVA_112_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_112_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_112_0_0 = INSTANCE.getJAVA_112_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_112_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_112_0_0_0, JAVA_112_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_112_0 = INSTANCE.getJAVA_112_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_112_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_112_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class RightShift
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_112 = INSTANCE.getJAVA_112();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_112() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getRightShift(), JAVA_112_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_113_0_0_0 = INSTANCE.getJAVA_113_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_113_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_113_0_0_1 = INSTANCE.getJAVA_113_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_113_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_113_0_0_2 = INSTANCE.getJAVA_113_0_0_2();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_113_0_0_2() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword(">", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_113_0_0 = INSTANCE.getJAVA_113_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_113_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_113_0_0_0, JAVA_113_0_0_1, JAVA_113_0_0_2);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_113_0 = INSTANCE.getJAVA_113_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_113_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_113_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * UnsignedRightShift
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_113 = INSTANCE.getJAVA_113();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_113() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getUnsignedRightShift(), JAVA_113_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_114_0_0_0 = INSTANCE.getJAVA_114_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_114_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("==", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_114_0_0 = INSTANCE.getJAVA_114_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_114_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_114_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_114_0 = INSTANCE.getJAVA_114_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_114_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_114_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Equal
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_114 = INSTANCE.getJAVA_114();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_114() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getEqual(), JAVA_114_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_115_0_0_0 = INSTANCE.getJAVA_115_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_115_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("!=", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_115_0_0 = INSTANCE.getJAVA_115_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_115_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_115_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_115_0 = INSTANCE.getJAVA_115_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_115_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_115_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class NotEqual
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_115 = INSTANCE.getJAVA_115();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_115() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getNotEqual(), JAVA_115_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_116_0_0_0 = INSTANCE.getJAVA_116_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_116_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("++", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_116_0_0 = INSTANCE.getJAVA_116_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_116_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_116_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_116_0 = INSTANCE.getJAVA_116_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_116_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_116_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class PlusPlus
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_116 = INSTANCE.getJAVA_116();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_116() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getPlusPlus(), JAVA_116_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_117_0_0_0 = INSTANCE.getJAVA_117_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_117_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("--", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_117_0_0 = INSTANCE.getJAVA_117_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_117_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_117_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_117_0 = INSTANCE.getJAVA_117_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_117_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_117_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class MinusMinus
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_117 = INSTANCE.getJAVA_117();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_117() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getMinusMinus(), JAVA_117_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_118_0_0_0 = INSTANCE.getJAVA_118_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_118_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("~", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_118_0_0 = INSTANCE.getJAVA_118_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_118_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_118_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_118_0 = INSTANCE.getJAVA_118_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_118_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_118_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Complement
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_118 = INSTANCE.getJAVA_118();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_118() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getComplement(), JAVA_118_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_119_0_0_0 = INSTANCE.getJAVA_119_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_119_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("!", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_119_0_0 = INSTANCE.getJAVA_119_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_119_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_119_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_119_0 = INSTANCE.getJAVA_119_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_119_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_119_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Negate
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_119 = INSTANCE.getJAVA_119();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_119() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getNegate(), JAVA_119_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_120_0_0_0_0_0_0 = INSTANCE.getJAVA_120_0_0_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_120_0_0_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("[", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_120_0_0_0_0_0_1 = INSTANCE.getJAVA_120_0_0_0_0_0_1();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_120_0_0_0_0_0_1() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("]", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_120_0_0_0_0_0 = INSTANCE.getJAVA_120_0_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_120_0_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_120_0_0_0_0_0_0, JAVA_120_0_0_0_0_0_1);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_120_0_0_0_0 = INSTANCE.getJAVA_120_0_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_120_0_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_120_0_0_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaCompound JAVA_120_0_0_0 = INSTANCE.getJAVA_120_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaCompound getJAVA_120_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaCompound(JAVA_120_0_0_0_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_120_0_0 = INSTANCE.getJAVA_120_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_120_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_120_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_120_0 = INSTANCE.getJAVA_120_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_120_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_120_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * ArrayDimension
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_120 = INSTANCE.getJAVA_120();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_120() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), JAVA_120_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_121_0_0_0 = INSTANCE.getJAVA_121_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_121_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("null", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_121_0_0 = INSTANCE.getJAVA_121_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_121_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_121_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_121_0 = INSTANCE.getJAVA_121_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_121_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_121_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class NullLiteral
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_121 = INSTANCE.getJAVA_121();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_121() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getNullLiteral(), JAVA_121_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_122_0_0_0 = INSTANCE.getJAVA_122_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_122_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("public", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_122_0_0 = INSTANCE.getJAVA_122_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_122_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_122_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_122_0 = INSTANCE.getJAVA_122_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_122_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_122_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Public
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_122 = INSTANCE.getJAVA_122();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_122() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getPublic(), JAVA_122_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_123_0_0_0 = INSTANCE.getJAVA_123_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_123_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("abstract", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_123_0_0 = INSTANCE.getJAVA_123_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_123_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_123_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_123_0 = INSTANCE.getJAVA_123_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_123_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_123_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Abstract
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_123 = INSTANCE.getJAVA_123();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_123() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAbstract(), JAVA_123_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_124_0_0_0 = INSTANCE.getJAVA_124_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_124_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("protected", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_124_0_0 = INSTANCE.getJAVA_124_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_124_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_124_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_124_0 = INSTANCE.getJAVA_124_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_124_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_124_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Protected
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_124 = INSTANCE.getJAVA_124();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_124() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getProtected(), JAVA_124_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_125_0_0_0 = INSTANCE.getJAVA_125_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_125_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("private", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_125_0_0 = INSTANCE.getJAVA_125_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_125_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_125_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_125_0 = INSTANCE.getJAVA_125_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_125_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_125_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Private
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_125 = INSTANCE.getJAVA_125();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_125() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getPrivate(), JAVA_125_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_126_0_0_0 = INSTANCE.getJAVA_126_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_126_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("final", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_126_0_0 = INSTANCE.getJAVA_126_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_126_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_126_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_126_0 = INSTANCE.getJAVA_126_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_126_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_126_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Final
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_126 = INSTANCE.getJAVA_126();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_126() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getFinal(), JAVA_126_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_127_0_0_0 = INSTANCE.getJAVA_127_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_127_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("static", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_127_0_0 = INSTANCE.getJAVA_127_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_127_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_127_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_127_0 = INSTANCE.getJAVA_127_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_127_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_127_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Static
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_127 = INSTANCE.getJAVA_127();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_127() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getStatic(), JAVA_127_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_128_0_0_0 = INSTANCE.getJAVA_128_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_128_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("native", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_128_0_0 = INSTANCE.getJAVA_128_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_128_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_128_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_128_0 = INSTANCE.getJAVA_128_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_128_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_128_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Native
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_128 = INSTANCE.getJAVA_128();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_128() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getNative(), JAVA_128_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_129_0_0_0 = INSTANCE.getJAVA_129_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_129_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("synchronized", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_129_0_0 = INSTANCE.getJAVA_129_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_129_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_129_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_129_0 = INSTANCE.getJAVA_129_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_129_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_129_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Synchronized
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_129 = INSTANCE.getJAVA_129();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_129() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getSynchronized(), JAVA_129_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_130_0_0_0 = INSTANCE.getJAVA_130_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_130_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("transient", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_130_0_0 = INSTANCE.getJAVA_130_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_130_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_130_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_130_0 = INSTANCE.getJAVA_130_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_130_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_130_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Transient
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_130 = INSTANCE.getJAVA_130();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_130() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getTransient(), JAVA_130_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_131_0_0_0 = INSTANCE.getJAVA_131_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_131_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("volatile", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_131_0_0 = INSTANCE.getJAVA_131_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_131_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_131_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_131_0 = INSTANCE.getJAVA_131_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_131_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_131_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Volatile
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_131 = INSTANCE.getJAVA_131();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_131() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getVolatile(), JAVA_131_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_132_0_0_0 = INSTANCE.getJAVA_132_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_132_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("strictfp", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_132_0_0 = INSTANCE.getJAVA_132_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_132_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_132_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_132_0 = INSTANCE.getJAVA_132_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_132_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_132_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Strictfp
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_132 = INSTANCE.getJAVA_132();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_132() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getStrictfp(), JAVA_132_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_133_0_0_0 = INSTANCE.getJAVA_133_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_133_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("void", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_133_0_0 = INSTANCE.getJAVA_133_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_133_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_133_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_133_0 = INSTANCE.getJAVA_133_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_133_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_133_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Void
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_133 = INSTANCE.getJAVA_133();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_133() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getVoid(), JAVA_133_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_134_0_0_0 = INSTANCE.getJAVA_134_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_134_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("boolean", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_134_0_0 = INSTANCE.getJAVA_134_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_134_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_134_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_134_0 = INSTANCE.getJAVA_134_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_134_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_134_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Boolean
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_134 = INSTANCE.getJAVA_134();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_134() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getBoolean(), JAVA_134_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_135_0_0_0 = INSTANCE.getJAVA_135_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_135_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("char", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_135_0_0 = INSTANCE.getJAVA_135_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_135_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_135_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_135_0 = INSTANCE.getJAVA_135_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_135_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_135_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Char
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_135 = INSTANCE.getJAVA_135();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_135() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getChar(), JAVA_135_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_136_0_0_0 = INSTANCE.getJAVA_136_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_136_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("byte", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_136_0_0 = INSTANCE.getJAVA_136_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_136_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_136_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_136_0 = INSTANCE.getJAVA_136_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_136_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_136_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Byte
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_136 = INSTANCE.getJAVA_136();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_136() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getByte(), JAVA_136_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_137_0_0_0 = INSTANCE.getJAVA_137_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_137_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("short", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_137_0_0 = INSTANCE.getJAVA_137_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_137_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_137_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_137_0 = INSTANCE.getJAVA_137_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_137_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_137_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Short
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_137 = INSTANCE.getJAVA_137();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_137() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getShort(), JAVA_137_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_138_0_0_0 = INSTANCE.getJAVA_138_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_138_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("int", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_138_0_0 = INSTANCE.getJAVA_138_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_138_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_138_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_138_0 = INSTANCE.getJAVA_138_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_138_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_138_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Int
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_138 = INSTANCE.getJAVA_138();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_138() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getInt(), JAVA_138_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_139_0_0_0 = INSTANCE.getJAVA_139_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_139_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("long", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_139_0_0 = INSTANCE.getJAVA_139_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_139_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_139_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_139_0 = INSTANCE.getJAVA_139_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_139_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_139_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Long
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_139 = INSTANCE.getJAVA_139();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_139() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getLong(), JAVA_139_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_140_0_0_0 = INSTANCE.getJAVA_140_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_140_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("float", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_140_0_0 = INSTANCE.getJAVA_140_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_140_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_140_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_140_0 = INSTANCE.getJAVA_140_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_140_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_140_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Float
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_140 = INSTANCE.getJAVA_140();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_140() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getFloat(), JAVA_140_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaKeyword JAVA_141_0_0_0 = INSTANCE.getJAVA_141_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaKeyword getJAVA_141_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaKeyword("double", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_141_0_0 = INSTANCE.getJAVA_141_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_141_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_141_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_141_0 = INSTANCE.getJAVA_141_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_141_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_141_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class Double
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_141 = INSTANCE.getJAVA_141();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_141() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getDouble(), JAVA_141_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_142_0_0_0 = INSTANCE.getJAVA_142_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_142_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getDecimalLongLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_LONG_LITERAL__DECIMAL_VALUE), "DECIMAL_LONG_LITERAL", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_142_0_0 = INSTANCE.getJAVA_142_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_142_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_142_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_142_0 = INSTANCE.getJAVA_142_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_142_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_142_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * DecimalLongLiteral
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_142 = INSTANCE.getJAVA_142();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_142() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getDecimalLongLiteral(), JAVA_142_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_143_0_0_0 = INSTANCE.getJAVA_143_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_143_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getDecimalFloatLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_FLOAT_LITERAL__DECIMAL_VALUE), "DECIMAL_FLOAT_LITERAL", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_143_0_0 = INSTANCE.getJAVA_143_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_143_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_143_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_143_0 = INSTANCE.getJAVA_143_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_143_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_143_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * DecimalFloatLiteral
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_143 = INSTANCE.getJAVA_143();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_143() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getDecimalFloatLiteral(), JAVA_143_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_144_0_0_0 = INSTANCE.getJAVA_144_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_144_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getDecimalIntegerLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_INTEGER_LITERAL__DECIMAL_VALUE), "DECIMAL_INTEGER_LITERAL", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_144_0_0 = INSTANCE.getJAVA_144_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_144_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_144_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_144_0 = INSTANCE.getJAVA_144_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_144_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_144_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * DecimalIntegerLiteral
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_144 = INSTANCE.getJAVA_144();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_144() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getDecimalIntegerLiteral(), JAVA_144_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_145_0_0_0 = INSTANCE.getJAVA_145_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_145_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getDecimalDoubleLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_DOUBLE_LITERAL__DECIMAL_VALUE), "DECIMAL_DOUBLE_LITERAL", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_145_0_0 = INSTANCE.getJAVA_145_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_145_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_145_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_145_0 = INSTANCE.getJAVA_145_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_145_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_145_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * DecimalDoubleLiteral
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_145 = INSTANCE.getJAVA_145();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_145() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getDecimalDoubleLiteral(), JAVA_145_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_146_0_0_0 = INSTANCE.getJAVA_146_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_146_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getHexLongLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_LONG_LITERAL__HEX_VALUE), "HEX_LONG_LITERAL", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_146_0_0 = INSTANCE.getJAVA_146_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_146_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_146_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_146_0 = INSTANCE.getJAVA_146_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_146_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_146_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * HexLongLiteral
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_146 = INSTANCE.getJAVA_146();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_146() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getHexLongLiteral(), JAVA_146_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_147_0_0_0 = INSTANCE.getJAVA_147_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_147_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getHexFloatLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_FLOAT_LITERAL__HEX_VALUE), "HEX_FLOAT_LITERAL", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_147_0_0 = INSTANCE.getJAVA_147_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_147_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_147_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_147_0 = INSTANCE.getJAVA_147_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_147_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_147_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * HexFloatLiteral
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_147 = INSTANCE.getJAVA_147();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_147() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getHexFloatLiteral(), JAVA_147_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_148_0_0_0 = INSTANCE.getJAVA_148_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_148_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getHexDoubleLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_DOUBLE_LITERAL__HEX_VALUE), "HEX_DOUBLE_LITERAL", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_148_0_0 = INSTANCE.getJAVA_148_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_148_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_148_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_148_0 = INSTANCE.getJAVA_148_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_148_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_148_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * HexDoubleLiteral
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_148 = INSTANCE.getJAVA_148();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_148() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getHexDoubleLiteral(), JAVA_148_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_149_0_0_0 = INSTANCE.getJAVA_149_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_149_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getHexIntegerLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_INTEGER_LITERAL__HEX_VALUE), "HEX_INTEGER_LITERAL", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_149_0_0 = INSTANCE.getJAVA_149_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_149_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_149_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_149_0 = INSTANCE.getJAVA_149_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_149_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_149_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * HexIntegerLiteral
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_149 = INSTANCE.getJAVA_149();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_149() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getHexIntegerLiteral(), JAVA_149_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_150_0_0_0 = INSTANCE.getJAVA_150_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_150_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getOctalLongLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.OCTAL_LONG_LITERAL__OCTAL_VALUE), "OCTAL_LONG_LITERAL", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_150_0_0 = INSTANCE.getJAVA_150_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_150_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_150_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_150_0 = INSTANCE.getJAVA_150_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_150_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_150_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * OctalLongLiteral
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_150 = INSTANCE.getJAVA_150();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_150() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getOctalLongLiteral(), JAVA_150_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_151_0_0_0 = INSTANCE.getJAVA_151_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_151_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getOctalIntegerLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.OCTAL_INTEGER_LITERAL__OCTAL_VALUE), "OCTAL_INTEGER_LITERAL", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_151_0_0 = INSTANCE.getJAVA_151_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_151_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_151_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_151_0 = INSTANCE.getJAVA_151_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_151_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_151_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * OctalIntegerLiteral
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_151 = INSTANCE.getJAVA_151();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_151() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getOctalIntegerLiteral(), JAVA_151_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_152_0_0_0 = INSTANCE.getJAVA_152_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_152_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getCharacterLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.CHARACTER_LITERAL__VALUE), "CHARACTER_LITERAL", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_152_0_0 = INSTANCE.getJAVA_152_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_152_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_152_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_152_0 = INSTANCE.getJAVA_152_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_152_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_152_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * CharacterLiteral
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_152 = INSTANCE.getJAVA_152();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_152() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getCharacterLiteral(), JAVA_152_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaPlaceholder JAVA_153_0_0_0 = INSTANCE.getJAVA_153_0_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaPlaceholder getJAVA_153_0_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getBooleanLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.BOOLEAN_LITERAL__VALUE), "BOOLEAN_LITERAL", org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, 0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaSequence JAVA_153_0_0 = INSTANCE.getJAVA_153_0_0();
	private org.emftext.language.java.resource.java.grammar.JavaSequence getJAVA_153_0_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaSequence(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_153_0_0_0);
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaChoice JAVA_153_0 = INSTANCE.getJAVA_153_0();
	private org.emftext.language.java.resource.java.grammar.JavaChoice getJAVA_153_0() {
		return new org.emftext.language.java.resource.java.grammar.JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE, JAVA_153_0_0);
	}
	
	/**
	 * This constant refers to the definition of the syntax for meta class
	 * BooleanLiteral
	 */
	public final static org.emftext.language.java.resource.java.grammar.JavaRule JAVA_153 = INSTANCE.getJAVA_153();
	private org.emftext.language.java.resource.java.grammar.JavaRule getJAVA_153() {
		return new org.emftext.language.java.resource.java.grammar.JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getBooleanLiteral(), JAVA_153_0, org.emftext.language.java.resource.java.grammar.JavaCardinality.ONE);
	}
	
	
	public static String getSyntaxElementID(org.emftext.language.java.resource.java.grammar.JavaSyntaxElement syntaxElement) {
		if (syntaxElement == null) {
			// null indicates EOF
			return "<EOF>";
		}
		for (Field field : org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.class.getFields()) {
			Object fieldValue;
			try {
				fieldValue = field.get(null);
				if (fieldValue == syntaxElement) {
					String id = field.getName();
					return id;
				}
			} catch (Exception e) { }
		}
		return null;
	}
	
	public static org.emftext.language.java.resource.java.grammar.JavaSyntaxElement getSyntaxElementByID(String syntaxElementID) {
		try {
			return (org.emftext.language.java.resource.java.grammar.JavaSyntaxElement) org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.class.getField(syntaxElementID).get(null);
		} catch (Exception e) {
			return null;
		}
	}
	
	public final static org.emftext.language.java.resource.java.grammar.JavaRule[] RULES = new org.emftext.language.java.resource.java.grammar.JavaRule[] {
		JAVA_0,
		JAVA_1,
		JAVA_2,
		JAVA_3,
		JAVA_4,
		JAVA_5,
		JAVA_6,
		JAVA_7,
		JAVA_8,
		JAVA_9,
		JAVA_10,
		JAVA_11,
		JAVA_12,
		JAVA_13,
		JAVA_14,
		JAVA_15,
		JAVA_16,
		JAVA_17,
		JAVA_18,
		JAVA_19,
		JAVA_20,
		JAVA_21,
		JAVA_22,
		JAVA_23,
		JAVA_24,
		JAVA_25,
		JAVA_26,
		JAVA_27,
		JAVA_28,
		JAVA_29,
		JAVA_30,
		JAVA_31,
		JAVA_32,
		JAVA_33,
		JAVA_34,
		JAVA_35,
		JAVA_36,
		JAVA_37,
		JAVA_38,
		JAVA_39,
		JAVA_40,
		JAVA_41,
		JAVA_42,
		JAVA_43,
		JAVA_44,
		JAVA_45,
		JAVA_46,
		JAVA_47,
		JAVA_48,
		JAVA_49,
		JAVA_50,
		JAVA_51,
		JAVA_52,
		JAVA_53,
		JAVA_54,
		JAVA_55,
		JAVA_56,
		JAVA_57,
		JAVA_58,
		JAVA_59,
		JAVA_60,
		JAVA_61,
		JAVA_62,
		JAVA_63,
		JAVA_64,
		JAVA_65,
		JAVA_66,
		JAVA_67,
		JAVA_68,
		JAVA_69,
		JAVA_70,
		JAVA_71,
		JAVA_72,
		JAVA_73,
		JAVA_74,
		JAVA_75,
		JAVA_76,
		JAVA_77,
		JAVA_78,
		JAVA_79,
		JAVA_80,
		JAVA_81,
		JAVA_82,
		JAVA_83,
		JAVA_84,
		JAVA_85,
		JAVA_86,
		JAVA_87,
		JAVA_88,
		JAVA_89,
		JAVA_90,
		JAVA_91,
		JAVA_92,
		JAVA_93,
		JAVA_94,
		JAVA_95,
		JAVA_96,
		JAVA_97,
		JAVA_98,
		JAVA_99,
		JAVA_100,
		JAVA_101,
		JAVA_102,
		JAVA_103,
		JAVA_104,
		JAVA_105,
		JAVA_106,
		JAVA_107,
		JAVA_108,
		JAVA_109,
		JAVA_110,
		JAVA_111,
		JAVA_112,
		JAVA_113,
		JAVA_114,
		JAVA_115,
		JAVA_116,
		JAVA_117,
		JAVA_118,
		JAVA_119,
		JAVA_120,
		JAVA_121,
		JAVA_122,
		JAVA_123,
		JAVA_124,
		JAVA_125,
		JAVA_126,
		JAVA_127,
		JAVA_128,
		JAVA_129,
		JAVA_130,
		JAVA_131,
		JAVA_132,
		JAVA_133,
		JAVA_134,
		JAVA_135,
		JAVA_136,
		JAVA_137,
		JAVA_138,
		JAVA_139,
		JAVA_140,
		JAVA_141,
		JAVA_142,
		JAVA_143,
		JAVA_144,
		JAVA_145,
		JAVA_146,
		JAVA_147,
		JAVA_148,
		JAVA_149,
		JAVA_150,
		JAVA_151,
		JAVA_152,
		JAVA_153,
	};
	
	/**
	 * Returns all keywords of the grammar. This includes all literals for boolean and
	 * enumeration terminals.
	 */
	public Set<String> getKeywords() {
		if (this.keywords == null) {
			this.keywords = new LinkedHashSet<String>();
			for (org.emftext.language.java.resource.java.grammar.JavaRule rule : RULES) {
				findKeywords(rule, this.keywords);
			}
		}
		return keywords;
	}
	
	/**
	 * Finds all keywords in the given element and its children and adds them to the
	 * set. This includes all literals for boolean and enumeration terminals.
	 */
	private void findKeywords(org.emftext.language.java.resource.java.grammar.JavaSyntaxElement element, Set<String> keywords) {
		if (element instanceof org.emftext.language.java.resource.java.grammar.JavaKeyword) {
			keywords.add(((org.emftext.language.java.resource.java.grammar.JavaKeyword) element).getValue());
		} else if (element instanceof org.emftext.language.java.resource.java.grammar.JavaBooleanTerminal) {
			keywords.add(((org.emftext.language.java.resource.java.grammar.JavaBooleanTerminal) element).getTrueLiteral());
			keywords.add(((org.emftext.language.java.resource.java.grammar.JavaBooleanTerminal) element).getFalseLiteral());
		} else if (element instanceof org.emftext.language.java.resource.java.grammar.JavaEnumerationTerminal) {
			org.emftext.language.java.resource.java.grammar.JavaEnumerationTerminal terminal = (org.emftext.language.java.resource.java.grammar.JavaEnumerationTerminal) element;
			for (String key : terminal.getLiteralMapping().keySet()) {
				keywords.add(key);
			}
		}
		for (org.emftext.language.java.resource.java.grammar.JavaSyntaxElement child : element.getChildren()) {
			findKeywords(child, this.keywords);
		}
	}
	
}
