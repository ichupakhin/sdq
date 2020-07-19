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

package org.emftext.language.java.resource.java.util;

import java.io.File;
import java.util.Map;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;

/**
 * Class JavaTextResourceUtil can be used to perform common tasks on text
 * resources, such as loading and saving resources, as well as, checking them for
 * errors. This class is deprecated and has been replaced by
 * org.emftext.language.java.resource.java.util.JavaResourceUtil.
 */
public class JavaTextResourceUtil {
	
	/**
	 * Use org.emftext.language.java.resource.java.util.JavaResourceUtil.getResource()
	 * instead.
	 */
	@Deprecated
	public static org.emftext.language.java.resource.java.mopp.JavaResource getResource(IFile file) {
		return new org.emftext.language.java.resource.java.util.JavaEclipseProxy().getResource(file);
	}
	
	/**
	 * Use org.emftext.language.java.resource.java.util.JavaResourceUtil.getResource()
	 * instead.
	 */
	@Deprecated
	public static org.emftext.language.java.resource.java.mopp.JavaResource getResource(File file, Map<?,?> options) {
		return org.emftext.language.java.resource.java.util.JavaResourceUtil.getResource(file, options);
	}
	
	/**
	 * Use org.emftext.language.java.resource.java.util.JavaResourceUtil.getResource()
	 * instead.
	 */
	@Deprecated
	public static org.emftext.language.java.resource.java.mopp.JavaResource getResource(URI uri) {
		return org.emftext.language.java.resource.java.util.JavaResourceUtil.getResource(uri);
	}
	
	/**
	 * Use org.emftext.language.java.resource.java.util.JavaResourceUtil.getResource()
	 * instead.
	 */
	@Deprecated
	public static org.emftext.language.java.resource.java.mopp.JavaResource getResource(URI uri, Map<?,?> options) {
		return org.emftext.language.java.resource.java.util.JavaResourceUtil.getResource(uri, options);
	}
	
}
