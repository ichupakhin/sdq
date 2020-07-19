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
 * A class to represent a rules in the grammar.
 */
public class JavaRule extends org.emftext.language.java.resource.java.grammar.JavaSyntaxElement {
	
	private final EClass metaclass;
	
	public JavaRule(EClass metaclass, org.emftext.language.java.resource.java.grammar.JavaChoice choice, org.emftext.language.java.resource.java.grammar.JavaCardinality cardinality) {
		super(cardinality, new org.emftext.language.java.resource.java.grammar.JavaSyntaxElement[] {choice});
		this.metaclass = metaclass;
	}
	
	public EClass getMetaclass() {
		return metaclass;
	}
	
	public org.emftext.language.java.resource.java.grammar.JavaChoice getDefinition() {
		return (org.emftext.language.java.resource.java.grammar.JavaChoice) getChildren()[0];
	}
	
	@Deprecated
	public String toString() {
		return metaclass == null ? "null" : metaclass.getName() + " ::= " + getDefinition().toString();
	}
	
}

