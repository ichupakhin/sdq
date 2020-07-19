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

/**
 * The abstract super class for all elements of a grammar. This class provides
 * methods to traverse the grammar rules.
 */
public abstract class JavaSyntaxElement {
	
	private JavaSyntaxElement[] children;
	private JavaSyntaxElement parent;
	private org.emftext.language.java.resource.java.grammar.JavaCardinality cardinality;
	
	public JavaSyntaxElement(org.emftext.language.java.resource.java.grammar.JavaCardinality cardinality, JavaSyntaxElement[] children) {
		this.cardinality = cardinality;
		this.children = children;
		if (this.children != null) {
			for (JavaSyntaxElement child : this.children) {
				child.setParent(this);
			}
		}
	}
	
	/**
	 * Sets the parent of this syntax element. This method must be invoked at most
	 * once.
	 */
	public void setParent(JavaSyntaxElement parent) {
		assert this.parent == null;
		this.parent = parent;
	}
	
	/**
	 * Returns the parent of this syntax element. This parent is determined by the
	 * containment hierarchy in the CS model.
	 */
	public JavaSyntaxElement getParent() {
		return parent;
	}
	
	/**
	 * Returns the rule of this syntax element. The rule is determined by the
	 * containment hierarchy in the CS model.
	 */
	public org.emftext.language.java.resource.java.grammar.JavaRule getRule() {
		if (this instanceof org.emftext.language.java.resource.java.grammar.JavaRule) {
			return (org.emftext.language.java.resource.java.grammar.JavaRule) this;
		}
		return parent.getRule();
	}
	
	public JavaSyntaxElement[] getChildren() {
		if (children == null) {
			return new JavaSyntaxElement[0];
		}
		return children;
	}
	
	public EClass getMetaclass() {
		return parent.getMetaclass();
	}
	
	public org.emftext.language.java.resource.java.grammar.JavaCardinality getCardinality() {
		return cardinality;
	}
	
	public boolean hasContainment(EClass metaclass) {
		org.emftext.language.java.resource.java.grammar.JavaSyntaxElement[] children = getChildren();
		for (org.emftext.language.java.resource.java.grammar.JavaSyntaxElement child : children) {
			if (child.hasContainment(metaclass)) {
				return true;
			}
		}
		return false;
	}
	
}
