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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The JavaTokenResolverFactory class provides access to all generated token
 * resolvers. By giving the name of a defined token, the corresponding resolve can
 * be obtained. Despite the fact that this class is called TokenResolverFactory is
 * does NOT create new token resolvers whenever a client calls methods to obtain a
 * resolver. Rather, this class maintains a map of all resolvers and creates each
 * resolver at most once.
 */
public class JavaTokenResolverFactory implements org.emftext.language.java.resource.java.IJavaTokenResolverFactory {
	
	private Map<String, org.emftext.language.java.resource.java.IJavaTokenResolver> tokenName2TokenResolver;
	private Map<String, org.emftext.language.java.resource.java.IJavaTokenResolver> featureName2CollectInTokenResolver;
	private static org.emftext.language.java.resource.java.IJavaTokenResolver defaultResolver = new org.emftext.language.java.resource.java.analysis.JavaDefaultTokenResolver();
	
	public JavaTokenResolverFactory() {
		tokenName2TokenResolver = new LinkedHashMap<String, org.emftext.language.java.resource.java.IJavaTokenResolver>();
		featureName2CollectInTokenResolver = new LinkedHashMap<String, org.emftext.language.java.resource.java.IJavaTokenResolver>();
		registerTokenResolver("BOOLEAN_LITERAL", new org.emftext.language.java.resource.java.analysis.JavaBOOLEAN_LITERALTokenResolver());
		registerTokenResolver("CHARACTER_LITERAL", new org.emftext.language.java.resource.java.analysis.JavaCHARACTER_LITERALTokenResolver());
		registerTokenResolver("STRING_LITERAL", new org.emftext.language.java.resource.java.analysis.JavaSTRING_LITERALTokenResolver());
		registerTokenResolver("DECIMAL_LONG_LITERAL", new org.emftext.language.java.resource.java.analysis.JavaDECIMAL_LONG_LITERALTokenResolver());
		registerTokenResolver("HEX_FLOAT_LITERAL", new org.emftext.language.java.resource.java.analysis.JavaHEX_FLOAT_LITERALTokenResolver());
		registerTokenResolver("HEX_DOUBLE_LITERAL", new org.emftext.language.java.resource.java.analysis.JavaHEX_DOUBLE_LITERALTokenResolver());
		registerTokenResolver("HEX_LONG_LITERAL", new org.emftext.language.java.resource.java.analysis.JavaHEX_LONG_LITERALTokenResolver());
		registerTokenResolver("HEX_INTEGER_LITERAL", new org.emftext.language.java.resource.java.analysis.JavaHEX_INTEGER_LITERALTokenResolver());
		registerTokenResolver("DECIMAL_FLOAT_LITERAL", new org.emftext.language.java.resource.java.analysis.JavaDECIMAL_FLOAT_LITERALTokenResolver());
		registerTokenResolver("DECIMAL_DOUBLE_LITERAL", new org.emftext.language.java.resource.java.analysis.JavaDECIMAL_DOUBLE_LITERALTokenResolver());
		registerTokenResolver("DECIMAL_INTEGER_LITERAL", new org.emftext.language.java.resource.java.analysis.JavaDECIMAL_INTEGER_LITERALTokenResolver());
		registerTokenResolver("OCTAL_LONG_LITERAL", new org.emftext.language.java.resource.java.analysis.JavaOCTAL_LONG_LITERALTokenResolver());
		registerTokenResolver("OCTAL_INTEGER_LITERAL", new org.emftext.language.java.resource.java.analysis.JavaOCTAL_INTEGER_LITERALTokenResolver());
		registerTokenResolver("IDENTIFIER", new org.emftext.language.java.resource.java.analysis.JavaIDENTIFIERTokenResolver());
	}
	
	public org.emftext.language.java.resource.java.IJavaTokenResolver createTokenResolver(String tokenName) {
		return internalCreateResolver(tokenName2TokenResolver, tokenName);
	}
	
	public org.emftext.language.java.resource.java.IJavaTokenResolver createCollectInTokenResolver(String featureName) {
		return internalCreateResolver(featureName2CollectInTokenResolver, featureName);
	}
	
	protected boolean registerTokenResolver(String tokenName, org.emftext.language.java.resource.java.IJavaTokenResolver resolver){
		return internalRegisterTokenResolver(tokenName2TokenResolver, tokenName, resolver);
	}
	
	protected boolean registerCollectInTokenResolver(String featureName, org.emftext.language.java.resource.java.IJavaTokenResolver resolver){
		return internalRegisterTokenResolver(featureName2CollectInTokenResolver, featureName, resolver);
	}
	
	protected org.emftext.language.java.resource.java.IJavaTokenResolver deRegisterTokenResolver(String tokenName){
		return tokenName2TokenResolver.remove(tokenName);
	}
	
	private org.emftext.language.java.resource.java.IJavaTokenResolver internalCreateResolver(Map<String, org.emftext.language.java.resource.java.IJavaTokenResolver> resolverMap, String key) {
		if (resolverMap.containsKey(key)){
			return resolverMap.get(key);
		} else {
			return defaultResolver;
		}
	}
	
	private boolean internalRegisterTokenResolver(Map<String, org.emftext.language.java.resource.java.IJavaTokenResolver> resolverMap, String key, org.emftext.language.java.resource.java.IJavaTokenResolver resolver) {
		if (!resolverMap.containsKey(key)) {
			resolverMap.put(key,resolver);
			return true;
		}
		return false;
	}
	
}
