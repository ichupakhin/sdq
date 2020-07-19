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

import java.util.Collections;
import java.util.Set;

/**
 * A representation for a range in a document where a keyword (i.e., a static
 * string) is expected.
 */
public class JavaExpectedCsString extends org.emftext.language.java.resource.java.mopp.JavaAbstractExpectedElement {
	
	private org.emftext.language.java.resource.java.grammar.JavaKeyword keyword;
	
	public JavaExpectedCsString(org.emftext.language.java.resource.java.grammar.JavaKeyword keyword) {
		super(keyword.getMetaclass());
		this.keyword = keyword;
	}
	
	public String getValue() {
		return keyword.getValue();
	}
	
	/**
	 * Returns the expected keyword.
	 */
	public org.emftext.language.java.resource.java.grammar.JavaSyntaxElement getSyntaxElement() {
		return keyword;
	}
	
	public Set<String> getTokenNames() {
		return Collections.singleton("'" + getValue() + "'");
	}
	
	public String toString() {
		return "CsString \"" + getValue() + "\"";
	}
	
	public boolean equals(Object o) {
		if (o instanceof JavaExpectedCsString) {
			return getValue().equals(((JavaExpectedCsString) o).getValue());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return getValue().hashCode();
	}
	
}
