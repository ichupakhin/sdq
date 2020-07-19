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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * A JavaContainmentTrace represents a specific path to a structural feature by
 * navigating over a set of a structural feature from a start class.
 * JavaContainmentTraces are used during code completion to reconstruct
 * containment trees that are not created by the parser, for example, if the first
 * character of the contained object has not been typed yet.
 */
public class JavaContainmentTrace {
	
	/**
	 * The class where the trace starts.
	 */
	private EClass startClass;
	
	/**
	 * The path of contained features.
	 */
	private org.emftext.language.java.resource.java.mopp.JavaContainedFeature[] path;
	
	public JavaContainmentTrace(EClass startClass, org.emftext.language.java.resource.java.mopp.JavaContainedFeature[] path) {
		super();
		// Verify arguments
		if (startClass != null) {
			if (path.length > 0) {
				EStructuralFeature feature = path[path.length - 1].getFeature();
				if (!startClass.getEAllStructuralFeatures().contains(feature)) {
					throw new RuntimeException("Metaclass " + startClass.getName() + " must contain feature " + feature.getName());
				}
			}
		}
		this.startClass = startClass;
		this.path = path;
	}
	
	public EClass getStartClass() {
		return startClass;
	}
	
	public org.emftext.language.java.resource.java.mopp.JavaContainedFeature[] getPath() {
		return path;
	}
	
	public String toString() {
		return (startClass == null ? "null" : startClass.getName()) + "." + org.emftext.language.java.resource.java.util.JavaStringUtil.explode(path, "->");
	}
	
	public boolean contains(org.emftext.language.java.resource.java.grammar.JavaRule rule) {
		if (path == null) {
			return false;
		}
		
		EClass ruleMetaclass = rule.getMetaclass();
		for (org.emftext.language.java.resource.java.mopp.JavaContainedFeature pathElement : path) {
			EClass containerClass = pathElement.getContainerClass();
			if (containerClass == ruleMetaclass) {
				return true;
			}
		}
		
		return startClass == ruleMetaclass;
	}
	
}
