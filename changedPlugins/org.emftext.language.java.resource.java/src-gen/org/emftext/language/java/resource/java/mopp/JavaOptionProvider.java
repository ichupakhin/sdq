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

/**
 * This class can be used to configure options that must be used when resources
 * are loaded. This is similar to using the extension point
 * 'org.emftext.language.java.resource.java.default_load_options' with the
 * difference that the options defined in this class are used even if no Eclipse
 * platform is running.
 */
package org.emftext.language.java.resource.java.mopp;

import java.util.Collections;
import java.util.Map;

public class JavaOptionProvider implements org.emftext.language.java.resource.java.IJavaOptionProvider {
	
	public Map<?,?> getOptions() {
		// create a map with static option providers here
		return Collections.emptyMap();
	}
	
}
