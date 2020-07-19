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

import org.antlr.runtime3_4_0.ANTLRStringStream;
import org.antlr.runtime3_4_0.Lexer;
import org.antlr.runtime3_4_0.Token;

public class JavaAntlrScanner implements org.emftext.language.java.resource.java.IJavaTextScanner {
	
	private Lexer antlrLexer;
	
	public JavaAntlrScanner(Lexer antlrLexer) {
		this.antlrLexer = antlrLexer;
	}
	
	public org.emftext.language.java.resource.java.IJavaTextToken getNextToken() {
		if (antlrLexer.getCharStream() == null) {
			return null;
		}
		final Token current = antlrLexer.nextToken();
		if (current == null || current.getType() < 0) {
			return null;
		}
		org.emftext.language.java.resource.java.IJavaTextToken result = new org.emftext.language.java.resource.java.mopp.JavaANTLRTextToken(current);
		return result;
	}
	
	public void setText(String text) {
		antlrLexer.setCharStream(new ANTLRStringStream(text));
	}
	
}
