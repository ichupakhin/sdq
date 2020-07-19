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


public class JavaChoice extends org.emftext.language.java.resource.java.grammar.JavaSyntaxElement {
	
	public JavaChoice(org.emftext.language.java.resource.java.grammar.JavaCardinality cardinality, org.emftext.language.java.resource.java.grammar.JavaSyntaxElement... choices) {
		super(cardinality, choices);
	}
	
	public String toString() {
		return org.emftext.language.java.resource.java.util.JavaStringUtil.explode(getChildren(), "|");
	}
	
}
