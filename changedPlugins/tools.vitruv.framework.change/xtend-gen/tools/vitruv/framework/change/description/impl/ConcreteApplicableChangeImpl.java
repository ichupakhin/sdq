package tools.vitruv.framework.change.description.impl;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import tools.vitruv.framework.change.description.VitruviusChange;
import tools.vitruv.framework.change.description.impl.ConcreteChangeImpl;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.resolve.EChangeResolverAndApplicator;
import tools.vitruv.framework.change.echange.resolve.EChangeUnresolver;
import tools.vitruv.framework.change.echange.root.RootEChange;
import tools.vitruv.framework.tuid.TuidManager;
import tools.vitruv.framework.util.datatypes.VURI;
import tools.vitruv.framework.uuid.UuidResolver;

@SuppressWarnings("all")
public class ConcreteApplicableChangeImpl extends ConcreteChangeImpl {
  private VURI vuri;
  
  public ConcreteApplicableChangeImpl(final EChange eChange) {
    super(eChange);
    this.tryToSetUri();
  }
  
  @Override
  public void resolveBeforeAndApplyForward(final UuidResolver uuidResolver) {
    this.setEChange(EChangeResolverAndApplicator.resolveBefore(this.getEChange(), uuidResolver));
    this.tryToSetUri();
    this.registerOldObjectTuidsForUpdate(this.getObjectsWithPotentiallyModifiedTuids());
    EChangeResolverAndApplicator.applyForward(this.getEChange());
    this.tryToSetUri();
    this.updateTuids();
  }
  
  @Override
  public void resolveAfterAndApplyBackward(final UuidResolver uuidResolver) {
    this.setEChange(EChangeResolverAndApplicator.resolveAfter(this.getEChange(), uuidResolver));
    this.tryToSetUri();
    this.registerOldObjectTuidsForUpdate(this.getObjectsWithPotentiallyModifiedTuids());
    EChangeResolverAndApplicator.applyBackward(this.getEChange());
    this.tryToSetUri();
    this.updateTuids();
  }
  
  private void tryToSetUri() {
    final EChange eChange = this.getEChange();
    final Function1<EObject, Resource> _function = (EObject it) -> {
      return it.eResource();
    };
    List<Resource> resolvedResources = IterableExtensions.<Resource>toList(IterableExtensions.<Resource>filterNull(IterableExtensions.<EObject, Resource>map(this.getAffectedNotReferencedEObjects(), _function)));
    if ((eChange instanceof RootEChange)) {
      Resource _resource = ((RootEChange)eChange).getResource();
      resolvedResources.add(_resource);
    }
    int _size = resolvedResources.size();
    boolean _greaterThan = (_size > 0);
    if (_greaterThan) {
      this.vuri = VURI.getInstance(resolvedResources.get(0));
      return;
    }
    final Function1<InternalEObject, URI> _function_1 = (InternalEObject it) -> {
      return it.eProxyURI();
    };
    final Function1<URI, Boolean> _function_2 = (URI it) -> {
      int _segmentCount = it.segmentCount();
      return Boolean.valueOf((_segmentCount > 0));
    };
    final Iterable<URI> proxyUris = IterableExtensions.<URI>filter(IterableExtensions.<URI>filterNull(IterableExtensions.<InternalEObject, URI>map(Iterables.<InternalEObject>filter(this.getAffectedNotReferencedEObjects(), InternalEObject.class), _function_1)), _function_2);
    int _size_1 = IterableExtensions.size(proxyUris);
    boolean _greaterThan_1 = (_size_1 > 0);
    if (_greaterThan_1) {
      this.vuri = VURI.getInstance((((URI[])Conversions.unwrapArray(proxyUris, URI.class))[0]).trimFragment());
      return;
    }
  }
  
  @Override
  public VURI getURI() {
    return this.vuri;
  }
  
  private Set<EObject> getObjectsWithPotentiallyModifiedTuids() {
    final Function1<EObject, Set<EObject>> _function = (EObject it) -> {
      EObject _eContainer = it.eContainer();
      EObject _eContainer_1 = it.eContainer();
      EObject _eContainer_2 = null;
      if (_eContainer_1!=null) {
        _eContainer_2=_eContainer_1.eContainer();
      }
      EObject _eContainer_3 = it.eContainer();
      EObject _eContainer_4 = null;
      if (_eContainer_3!=null) {
        _eContainer_4=_eContainer_3.eContainer();
      }
      EObject _eContainer_5 = null;
      if (_eContainer_4!=null) {
        _eContainer_5=_eContainer_4.eContainer();
      }
      return Collections.<EObject>unmodifiableSet(CollectionLiterals.<EObject>newHashSet(it, _eContainer, _eContainer_2, _eContainer_5));
    };
    return IterableExtensions.<EObject>toSet(IterableExtensions.<EObject>filterNull(Iterables.<EObject>concat(IterableExtensions.<EObject, Set<EObject>>map(this.getAffectedEObjects(), _function))));
  }
  
  private void registerOldObjectTuidsForUpdate(final Iterable<EObject> objects) {
    final TuidManager tuidManager = TuidManager.getInstance();
    for (final EObject object : objects) {
      tuidManager.registerObjectUnderModification(object);
    }
  }
  
  private void updateTuids() {
    TuidManager.getInstance().updateTuidsOfRegisteredObjects();
    TuidManager.getInstance().flushRegisteredObjectsUnderModification();
  }
  
  @Override
  public void unresolveIfApplicable() {
    final Consumer<EChange> _function = (EChange it) -> {
      EChangeUnresolver.unresolve(it);
    };
    this.getEChanges().forEach(_function);
  }
  
  @Override
  public boolean equals(final Object object) {
    return this.isEqual(object);
  }
  
  @Override
  public boolean changedEObjectEquals(final VitruviusChange change) {
    return this.isChangedEObjectEqual(change);
  }
  
  private boolean _isEqual(final Object object) {
    return super.equals(object);
  }
  
  private boolean _isEqual(final ConcreteApplicableChangeImpl change) {
    return (Objects.equal(this.vuri, change.getURI()) && EcoreUtil.equals(this.getEChange(), change.getEChange()));
  }
  
  private boolean _isChangedEObjectEqual(final VitruviusChange change) {
    return super.changedEObjectEquals(change);
  }
  
  private boolean _isChangedEObjectEqual(final ConcreteApplicableChangeImpl change) {
    return (Objects.equal(this.vuri, change.getURI()) && EcoreUtil.equals(this.getEChange().getInvolvedEObjects(), change.getEChange().getInvolvedEObjects()));
  }
  
  private boolean isEqual(final Object change) {
    if (change instanceof ConcreteApplicableChangeImpl) {
      return _isEqual((ConcreteApplicableChangeImpl)change);
    } else if (change != null) {
      return _isEqual(change);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(change).toString());
    }
  }
  
  private boolean isChangedEObjectEqual(final VitruviusChange change) {
    if (change instanceof ConcreteApplicableChangeImpl) {
      return _isChangedEObjectEqual((ConcreteApplicableChangeImpl)change);
    } else if (change != null) {
      return _isChangedEObjectEqual(change);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(change).toString());
    }
  }
}
