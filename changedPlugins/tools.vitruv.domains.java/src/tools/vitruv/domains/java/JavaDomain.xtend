package tools.vitruv.domains.java

import tools.vitruv.domains.java.tuid.JavaTuidCalculatorAndResolver
import static tools.vitruv.domains.java.JavaNamespace.*
import tools.vitruv.domains.java.builder.VitruviusJavaBuilderApplicator
import tools.vitruv.framework.domains.AbstractTuidAwareVitruvDomain
import org.eclipse.emf.ecore.resource.Resource
import org.emftext.language.java.resource.JavaSourceOrClassFileResourceFactoryImpl

import tools.vitruv.framework.domains.AbstractVitruvDomain
import com.google.common.collect.Iterables
import java.util.Map
import java.util.Collection
import java.util.Set
import org.eclipse.emf.ecore.EPackage
import tools.vitruv.framework.domains.DefaultStateBasedChangeResolutionStrategy
import org.eclipse.xtext.xbase.lib.Functions.Function1
import org.eclipse.xtext.xbase.lib.Functions.Function1

//Added by Ilia Chupakhin
import tools.vitruv.framework.domains.StateBasedChangeResolutionStrategy
//Added by Ilia Chupakhin
import tools.vitruv.applications.pcmjava.integrationFromGit.propagation.GitStateBasedChangeResolutionStrategy
//Added by Ilia Chupakhin
import org.emftext.commons.layout.LayoutPackage
import java.util.Collections
import java.util.HashSet
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject

final class JavaDomain extends AbstractTuidAwareVitruvDomain {
	private static final String METAMODEL_NAME = "Java";
	private boolean shouldTransitivelyPropagateChanges = false;
	//Added by Ilia Chupakhin
	GitStateBasedChangeResolutionStrategy gitStateBasedChangeResolutionStrategy;
	//Added by Ilia Chupakhin
	private HashSet<String> allNsURIs = new HashSet;	

		
	package new() {
		super(METAMODEL_NAME, ROOT_PACKAGE, generateTuidCalculator(), #[FILE_EXTENSION]);
		// Register factory for class and Java files in case of not running as plugin
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("java", new JavaSourceOrClassFileResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("class", new JavaSourceOrClassFileResourceFactoryImpl());
		// This is necessary to resolve classes from standard library (e.g. Object, List etc.) 
		JamoppLibraryHelper.registerStdLib
		
		//Added by Ilia Chupakhin
		allNsURIs.addAll(super.nsUris)
		addNameSpaceURIs(LayoutPackage.eINSTANCE)
	}
	
	//Added by Ilia Chupakhin
	def private void addNameSpaceURIs(EPackage additionalRootRackage) {
		val Set<EPackage> furtherRootPackages = Collections::emptySet();
		val Set<String> additionalURIs = (additionalRootRackage.nsURIsRecursive + furtherRootPackages.map[nsURIsRecursive].flatten).toSet;
		allNsURIs.addAll(additionalURIs)
	}
	
	//Added by Ilia Chupakhin
	override Set<String> getNsUris() {
		return allNsURIs
	}
	
	//Added by Ilia Chupakhin
	override boolean isInstanceOfDomainMetamodel(EObject eObject) {
	if (eObject === null) {
		return false;
	}
	val eClass = if (eObject instanceof EClass) eObject else eObject.eClass(); 
	
	if (null === eClass || null === eClass.getEPackage() ||	null === eClass.getEPackage().getNsURI() ||
		!this.allNsURIs.contains(eClass.getEPackage().getNsURI())) {
		return false
	}
	return true
	}
	
	
	/*
	//Added by Ilia Chupakhin
	* Activate this method if using own StateBasedChangeResolutionStrategy instead of DeafaultStateBasedChangeResolutionStrategy	
	override StateBasedChangeResolutionStrategy getStateChangePropagationStrategy() {
		return gitStateBasedChangeResolutionStrategy
	}	
	*/
	def protected static generateTuidCalculator() {
		return new JavaTuidCalculatorAndResolver();
	}
	
	override getBuilderApplicator() {
		return new VitruviusJavaBuilderApplicator();
	}
	
	override shouldTransitivelyPropagateChanges() {
		return shouldTransitivelyPropagateChanges;
	}
	
	/**
	 * Calling this methods enable the per default disabled transitive change propagation.
	 * Should only be called for test purposes!
	 */
	public def enableTransitiveChangePropagation() {
		shouldTransitivelyPropagateChanges = true
	}
	
	override supportsUuids() {
		return false;
	}

}