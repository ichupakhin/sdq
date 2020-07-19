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
import java.util.List;

/**
 * This class is used when computing code completion proposals hold groups of
 * expected elements which belong to the same follow set.
 */
public class JavaFollowSetGroupList {
	
	private int lastFollowSetID = -1;
	private List<org.emftext.language.java.resource.java.mopp.JavaFollowSetGroup> followSetGroups = new ArrayList<org.emftext.language.java.resource.java.mopp.JavaFollowSetGroup>();
	
	public JavaFollowSetGroupList(List<org.emftext.language.java.resource.java.mopp.JavaExpectedTerminal> expectedTerminals) {
		for (org.emftext.language.java.resource.java.mopp.JavaExpectedTerminal expectedTerminal : expectedTerminals) {
			addExpectedTerminal(expectedTerminal);
		}
	}
	
	private void addExpectedTerminal(org.emftext.language.java.resource.java.mopp.JavaExpectedTerminal expectedTerminal) {
		org.emftext.language.java.resource.java.mopp.JavaFollowSetGroup group;
		
		int followSetID = expectedTerminal.getFollowSetID();
		if (followSetID == lastFollowSetID) {
			if (followSetGroups.isEmpty()) {
				group = addNewGroup();
			} else {
				group = followSetGroups.get(followSetGroups.size() - 1);
			}
		} else {
			group = addNewGroup();
			lastFollowSetID = followSetID;
		}
		
		group.add(expectedTerminal);
	}
	
	public List<org.emftext.language.java.resource.java.mopp.JavaFollowSetGroup> getFollowSetGroups() {
		return followSetGroups;
	}
	
	private org.emftext.language.java.resource.java.mopp.JavaFollowSetGroup addNewGroup() {
		org.emftext.language.java.resource.java.mopp.JavaFollowSetGroup group = new org.emftext.language.java.resource.java.mopp.JavaFollowSetGroup();
		followSetGroups.add(group);
		return group;
	}
	
}
