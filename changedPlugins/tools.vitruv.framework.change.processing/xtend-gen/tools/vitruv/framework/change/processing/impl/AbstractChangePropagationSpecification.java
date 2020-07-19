package tools.vitruv.framework.change.processing.impl;

import java.util.List;
import java.util.function.Consumer;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import tools.vitruv.framework.change.processing.ChangePropagationObserver;
import tools.vitruv.framework.change.processing.ChangePropagationSpecification;
import tools.vitruv.framework.domains.VitruvDomain;
import tools.vitruv.framework.userinteraction.UserInteractor;

@SuppressWarnings("all")
public abstract class AbstractChangePropagationSpecification implements ChangePropagationSpecification {
  private final List<ChangePropagationObserver> propagationObserver;
  
  private UserInteractor userInteractor;
  
  private VitruvDomain sourceDomain;
  
  private VitruvDomain targetDomain;
  
  public AbstractChangePropagationSpecification(final VitruvDomain sourceDomain, final VitruvDomain targetDomain) {
    this.setVitruvDomains(sourceDomain, targetDomain);
    this.propagationObserver = CollectionLiterals.<ChangePropagationObserver>newArrayList();
  }
  
  protected UserInteractor getUserInteractor() {
    return this.userInteractor;
  }
  
  private VitruvDomain setVitruvDomains(final VitruvDomain sourceDomain, final VitruvDomain targetDomain) {
    VitruvDomain _xblockexpression = null;
    {
      this.sourceDomain = sourceDomain;
      _xblockexpression = this.targetDomain = targetDomain;
    }
    return _xblockexpression;
  }
  
  @Override
  public VitruvDomain getSourceDomain() {
    return this.sourceDomain;
  }
  
  @Override
  public VitruvDomain getTargetDomain() {
    return this.targetDomain;
  }
  
  @Override
  public void setUserInteractor(final UserInteractor userInteractor) {
    this.userInteractor = userInteractor;
  }
  
  @Override
  public void registerObserver(final ChangePropagationObserver observer) {
    if ((observer != null)) {
      this.propagationObserver.add(observer);
    }
  }
  
  @Override
  public void deregisterObserver(final ChangePropagationObserver observer) {
    this.propagationObserver.remove(observer);
  }
  
  @Override
  public void notifyObjectCreated(final EObject createdObject) {
    final Consumer<ChangePropagationObserver> _function = (ChangePropagationObserver it) -> {
      it.objectCreated(createdObject);
    };
    this.propagationObserver.forEach(_function);
  }
}
