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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.eclipse.emf.common.util.URI;

/**
 * <p>
 * A basic implementation of the
 * org.emftext.language.java.resource.java.IJavaReferenceResolveResult interface
 * that collects mappings in a list.
 * </p>
 * 
 * @param <ReferenceType> the type of the references that can be contained in this
 * result
 */
public class JavaReferenceResolveResult<ReferenceType> implements org.emftext.language.java.resource.java.IJavaReferenceResolveResult<ReferenceType> {
	
	private Collection<org.emftext.language.java.resource.java.IJavaReferenceMapping<ReferenceType>> mappings;
	private String errorMessage;
	private boolean resolveFuzzy;
	private Set<org.emftext.language.java.resource.java.IJavaQuickFix> quickFixes;
	
	public JavaReferenceResolveResult(boolean resolveFuzzy) {
		super();
		this.resolveFuzzy = resolveFuzzy;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public Collection<org.emftext.language.java.resource.java.IJavaQuickFix> getQuickFixes() {
		if (quickFixes == null) {
			quickFixes = new LinkedHashSet<org.emftext.language.java.resource.java.IJavaQuickFix>();
		}
		return Collections.unmodifiableSet(quickFixes);
	}
	
	public void addQuickFix(org.emftext.language.java.resource.java.IJavaQuickFix quickFix) {
		if (quickFixes == null) {
			quickFixes = new LinkedHashSet<org.emftext.language.java.resource.java.IJavaQuickFix>();
		}
		quickFixes.add(quickFix);
	}
	
	public Collection<org.emftext.language.java.resource.java.IJavaReferenceMapping<ReferenceType>> getMappings() {
		return mappings;
	}
	
	public boolean wasResolved() {
		return mappings != null;
	}
	
	public boolean wasResolvedMultiple() {
		return mappings != null && mappings.size() > 1;
	}
	
	public boolean wasResolvedUniquely() {
		return mappings != null && mappings.size() == 1;
	}
	
	public void setErrorMessage(String message) {
		errorMessage = message;
	}
	
	public void addMapping(String identifier, ReferenceType target) {
		if (!resolveFuzzy && target == null) {
			throw new IllegalArgumentException("Mapping references to null is only allowed for fuzzy resolution.");
		}
		addMapping(identifier, target, null);
	}
	
	public void addMapping(String identifier, ReferenceType target, String warning) {
		if (mappings == null) {
			mappings = new ArrayList<org.emftext.language.java.resource.java.IJavaReferenceMapping<ReferenceType>>(1);
		}
		mappings.add(new org.emftext.language.java.resource.java.mopp.JavaElementMapping<ReferenceType>(identifier, target, warning));
		errorMessage = null;
	}
	
	public void addMapping(String identifier, URI uri) {
		addMapping(identifier, uri, null);
	}
	
	public void addMapping(String identifier, URI uri, String warning) {
		if (mappings == null) {
			mappings = new ArrayList<org.emftext.language.java.resource.java.IJavaReferenceMapping<ReferenceType>>(1);
		}
		mappings.add(new org.emftext.language.java.resource.java.mopp.JavaURIMapping<ReferenceType>(identifier, uri, warning));
	}
}
