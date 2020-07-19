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

import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * A class to represent placeholders in a grammar.
 */
public class JavaPlaceholder extends org.emftext.language.java.resource.java.grammar.JavaTerminal {
	
	private final String tokenName;
	
	public JavaPlaceholder(EStructuralFeature feature, String tokenName, org.emftext.language.java.resource.java.grammar.JavaCardinality cardinality, int mandatoryOccurencesAfter) {
		super(feature, cardinality, mandatoryOccurencesAfter);
		this.tokenName = tokenName;
	}
	
	public String getTokenName() {
		return tokenName;
	}
	
}
