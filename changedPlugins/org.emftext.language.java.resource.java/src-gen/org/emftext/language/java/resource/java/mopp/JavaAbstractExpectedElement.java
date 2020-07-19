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

package org.emftext.language.java.resource.java.mopp;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;

/**
 * Abstract super class for all expected elements. Provides methods to add
 * followers.
 */
public abstract class JavaAbstractExpectedElement implements org.emftext.language.java.resource.java.IJavaExpectedElement {
	
	private EClass ruleMetaclass;
	
	private Set<org.emftext.language.java.resource.java.util.JavaPair<org.emftext.language.java.resource.java.IJavaExpectedElement, org.emftext.language.java.resource.java.mopp.JavaContainedFeature[]>> followers = new LinkedHashSet<org.emftext.language.java.resource.java.util.JavaPair<org.emftext.language.java.resource.java.IJavaExpectedElement, org.emftext.language.java.resource.java.mopp.JavaContainedFeature[]>>();
	
	public JavaAbstractExpectedElement(EClass ruleMetaclass) {
		super();
		this.ruleMetaclass = ruleMetaclass;
	}
	
	public EClass getRuleMetaclass() {
		return ruleMetaclass;
	}
	
	public void addFollower(org.emftext.language.java.resource.java.IJavaExpectedElement follower, org.emftext.language.java.resource.java.mopp.JavaContainedFeature[] path) {
		followers.add(new org.emftext.language.java.resource.java.util.JavaPair<org.emftext.language.java.resource.java.IJavaExpectedElement, org.emftext.language.java.resource.java.mopp.JavaContainedFeature[]>(follower, path));
	}
	
	public Collection<org.emftext.language.java.resource.java.util.JavaPair<org.emftext.language.java.resource.java.IJavaExpectedElement, org.emftext.language.java.resource.java.mopp.JavaContainedFeature[]>> getFollowers() {
		return followers;
	}
	
}
