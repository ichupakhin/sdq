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

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

public class JavaProblem implements org.emftext.language.java.resource.java.IJavaProblem {
	
	private String message;
	private org.emftext.language.java.resource.java.JavaEProblemType type;
	private org.emftext.language.java.resource.java.JavaEProblemSeverity severity;
	private Collection<org.emftext.language.java.resource.java.IJavaQuickFix> quickFixes;
	
	public JavaProblem(String message, org.emftext.language.java.resource.java.JavaEProblemType type, org.emftext.language.java.resource.java.JavaEProblemSeverity severity) {
		this(message, type, severity, Collections.<org.emftext.language.java.resource.java.IJavaQuickFix>emptySet());
	}
	
	public JavaProblem(String message, org.emftext.language.java.resource.java.JavaEProblemType type, org.emftext.language.java.resource.java.JavaEProblemSeverity severity, org.emftext.language.java.resource.java.IJavaQuickFix quickFix) {
		this(message, type, severity, Collections.singleton(quickFix));
	}
	
	public JavaProblem(String message, org.emftext.language.java.resource.java.JavaEProblemType type, org.emftext.language.java.resource.java.JavaEProblemSeverity severity, Collection<org.emftext.language.java.resource.java.IJavaQuickFix> quickFixes) {
		super();
		this.message = message;
		this.type = type;
		this.severity = severity;
		this.quickFixes = new LinkedHashSet<org.emftext.language.java.resource.java.IJavaQuickFix>();
		this.quickFixes.addAll(quickFixes);
	}
	
	public org.emftext.language.java.resource.java.JavaEProblemType getType() {
		return type;
	}
	
	public org.emftext.language.java.resource.java.JavaEProblemSeverity getSeverity() {
		return severity;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Collection<org.emftext.language.java.resource.java.IJavaQuickFix> getQuickFixes() {
		return quickFixes;
	}
	
}
