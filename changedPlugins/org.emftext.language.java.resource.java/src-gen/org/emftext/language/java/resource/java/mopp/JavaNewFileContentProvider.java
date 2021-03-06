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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class JavaNewFileContentProvider {
	
	public org.emftext.language.java.resource.java.IJavaMetaInformation getMetaInformation() {
		return new org.emftext.language.java.resource.java.mopp.JavaMetaInformation();
	}
	
	public String getNewFileContent(String newFileName) {
		return getExampleContent(new EClass[] {
			org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(),
			org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage(),
			org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getEmptyModel(),
		}, getMetaInformation().getClassesWithSyntax(), newFileName);
	}
	
	protected String getExampleContent(EClass[] startClasses, EClass[] allClassesWithSyntax, String newFileName) {
		String content = "public class HelloWorld {\n\t\n\tpublic static void main(String[] args) {\n\t\tSystem.out.println(\"Hello world!\");\n\t}\n}".replace("\n", System.getProperty("line.separator"));
		return content;
	}
	
	protected String getExampleContent(EClass eClass, EClass[] allClassesWithSyntax, String newFileName) {
		// create a minimal model
		EObject root = new org.emftext.language.java.resource.java.util.JavaMinimalModelHelper().getMinimalModel(eClass, allClassesWithSyntax, newFileName);
		if (root == null) {
			// could not create a minimal model. returning an empty document is the best we
			// can do.
			return "";
		}
		// use printer to get text for model
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		org.emftext.language.java.resource.java.IJavaTextPrinter printer = getPrinter(buffer);
		try {
			printer.print(root);
		} catch (IOException e) {
			new org.emftext.language.java.resource.java.util.JavaRuntimeUtil().logError("Exception while generating example content.", e);
		}
		return buffer.toString();
	}
	
	public org.emftext.language.java.resource.java.IJavaTextPrinter getPrinter(OutputStream outputStream) {
		return getMetaInformation().createPrinter(outputStream, new org.emftext.language.java.resource.java.mopp.JavaResource());
	}
	
}
