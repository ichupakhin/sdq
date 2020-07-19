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
import org.eclipse.emf.ecore.EObject;

public class JavaParseResult implements org.emftext.language.java.resource.java.IJavaParseResult {
	
	private EObject root;
	
	private org.emftext.language.java.resource.java.IJavaLocationMap locationMap;
	
	private Collection<org.emftext.language.java.resource.java.IJavaCommand<org.emftext.language.java.resource.java.IJavaTextResource>> commands = new ArrayList<org.emftext.language.java.resource.java.IJavaCommand<org.emftext.language.java.resource.java.IJavaTextResource>>();
	
	public JavaParseResult() {
		super();
	}
	
	public EObject getRoot() {
		return root;
	}
	
	public org.emftext.language.java.resource.java.IJavaLocationMap getLocationMap() {
		return locationMap;
	}
	
	public void setRoot(EObject root) {
		this.root = root;
	}
	
	public void setLocationMap(org.emftext.language.java.resource.java.IJavaLocationMap locationMap) {
		this.locationMap = locationMap;
	}
	
	public Collection<org.emftext.language.java.resource.java.IJavaCommand<org.emftext.language.java.resource.java.IJavaTextResource>> getPostParseCommands() {
		return commands;
	}
	
}
