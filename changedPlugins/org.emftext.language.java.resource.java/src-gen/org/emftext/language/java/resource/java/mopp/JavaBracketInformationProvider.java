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

/**
 * This class provides information about how brackets must be handled in the
 * editor (e.g., whether they must be closed automatically).
 */
public class JavaBracketInformationProvider {
	
	public Collection<org.emftext.language.java.resource.java.IJavaBracketPair> getBracketPairs() {
		Collection<org.emftext.language.java.resource.java.IJavaBracketPair> result = new ArrayList<org.emftext.language.java.resource.java.IJavaBracketPair>();
		result.add(new org.emftext.language.java.resource.java.mopp.JavaBracketPair("{", "}", true, true));
		result.add(new org.emftext.language.java.resource.java.mopp.JavaBracketPair("<", ">", true, false));
		result.add(new org.emftext.language.java.resource.java.mopp.JavaBracketPair("(", ")", true, false));
		result.add(new org.emftext.language.java.resource.java.mopp.JavaBracketPair("[", "]", true, false));
		return result;
	}
	
}
