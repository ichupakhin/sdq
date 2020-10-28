package tools.vitruv.domains.java;

import com.google.common.collect.Iterables;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.emftext.commons.layout.LayoutPackage;
import org.emftext.language.java.resource.JavaSourceOrClassFileResourceFactoryImpl;
import tools.vitruv.applications.pcmjava.integrationFromGit.propagation.GitStateBasedChangeResolutionStrategy;
import tools.vitruv.domains.java.JamoppLibraryHelper;
import tools.vitruv.domains.java.JavaNamespace;
import tools.vitruv.domains.java.builder.VitruviusJavaBuilderApplicator;
import tools.vitruv.domains.java.tuid.JavaTuidCalculatorAndResolver;
import tools.vitruv.framework.domains.AbstractTuidAwareVitruvDomain;
import tools.vitruv.framework.domains.AbstractVitruvDomain;
import tools.vitruv.framework.domains.VitruviusProjectBuilderApplicator;

@SuppressWarnings("all")
public final class JavaDomain extends AbstractTuidAwareVitruvDomain {
  private static final String METAMODEL_NAME = "Java";
  
  private boolean shouldTransitivelyPropagateChanges = false;
  
  private GitStateBasedChangeResolutionStrategy gitStateBasedChangeResolutionStrategy;
  
  private HashSet<String> allNsURIs = new HashSet<String>();
  
  JavaDomain() {
    super(JavaDomain.METAMODEL_NAME, JavaNamespace.ROOT_PACKAGE, JavaDomain.generateTuidCalculator(), new String[] { JavaNamespace.FILE_EXTENSION });
    Map<String, Object> _extensionToFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
    JavaSourceOrClassFileResourceFactoryImpl _javaSourceOrClassFileResourceFactoryImpl = new JavaSourceOrClassFileResourceFactoryImpl();
    _extensionToFactoryMap.put("java", _javaSourceOrClassFileResourceFactoryImpl);
    Map<String, Object> _extensionToFactoryMap_1 = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
    JavaSourceOrClassFileResourceFactoryImpl _javaSourceOrClassFileResourceFactoryImpl_1 = new JavaSourceOrClassFileResourceFactoryImpl();
    _extensionToFactoryMap_1.put("class", _javaSourceOrClassFileResourceFactoryImpl_1);
    JamoppLibraryHelper.registerStdLib();
    this.allNsURIs.addAll(super.getNsUris());
    this.addNameSpaceURIs(LayoutPackage.eINSTANCE);
  }
  
  private void addNameSpaceURIs(final EPackage additionalRootRackage) {
    final Set<EPackage> furtherRootPackages = Collections.<EPackage>emptySet();
    Set<String> _nsURIsRecursive = AbstractVitruvDomain.getNsURIsRecursive(additionalRootRackage);
    final Function1<EPackage, Set<String>> _function = (EPackage it) -> {
      return AbstractVitruvDomain.getNsURIsRecursive(it);
    };
    Iterable<String> _flatten = Iterables.<String>concat(IterableExtensions.<EPackage, Set<String>>map(furtherRootPackages, _function));
    final Set<String> additionalURIs = IterableExtensions.<String>toSet(Iterables.<String>concat(_nsURIsRecursive, _flatten));
    this.allNsURIs.addAll(additionalURIs);
  }
  
  @Override
  public Set<String> getNsUris() {
    return this.allNsURIs;
  }
  
  /**
   * //Added by Ilia Chupakhin
   * override StateBasedChangeResolutionStrategy getStateChangePropagationStrategy() {
   * return gitStateBasedChangeResolutionStrategy
   * }
   */
  protected static JavaTuidCalculatorAndResolver generateTuidCalculator() {
    return new JavaTuidCalculatorAndResolver();
  }
  
  @Override
  public VitruviusProjectBuilderApplicator getBuilderApplicator() {
    return new VitruviusJavaBuilderApplicator();
  }
  
  @Override
  public boolean shouldTransitivelyPropagateChanges() {
    return this.shouldTransitivelyPropagateChanges;
  }
  
  /**
   * Calling this methods enable the per default disabled transitive change propagation.
   * Should only be called for test purposes!
   */
  public boolean enableTransitiveChangePropagation() {
    return this.shouldTransitivelyPropagateChanges = true;
  }
  
  @Override
  public boolean supportsUuids() {
    return false;
  }
}
