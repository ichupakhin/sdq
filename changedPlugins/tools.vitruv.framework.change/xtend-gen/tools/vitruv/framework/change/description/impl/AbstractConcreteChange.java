package tools.vitruv.framework.change.description.impl;

import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import tools.vitruv.framework.change.description.ConcreteChange;
import tools.vitruv.framework.change.description.VitruviusChange;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.eobject.EObjectExistenceEChange;
import tools.vitruv.framework.change.echange.feature.FeatureEChange;
import tools.vitruv.framework.change.echange.feature.reference.InsertEReference;
import tools.vitruv.framework.change.echange.feature.reference.RemoveEReference;
import tools.vitruv.framework.change.echange.feature.reference.ReplaceSingleValuedEReference;
import tools.vitruv.framework.change.echange.root.InsertRootEObject;
import tools.vitruv.framework.change.echange.root.RemoveRootEObject;
import tools.vitruv.framework.change.interaction.UserInteractionBase;
import tools.vitruv.framework.util.datatypes.VURI;
import tools.vitruv.framework.uuid.UuidResolver;

@SuppressWarnings("all")
public abstract class AbstractConcreteChange implements ConcreteChange {
  private static final Logger logger = Logger.getLogger(AbstractConcreteChange.class);
  
  private EChange eChange;
  
  private List<UserInteractionBase> userInteractions;
  
  public AbstractConcreteChange(final EChange eChange) {
    this.eChange = eChange;
    ArrayList<UserInteractionBase> _arrayList = new ArrayList<UserInteractionBase>();
    this.userInteractions = _arrayList;
  }
  
  @Override
  public boolean containsConcreteChange() {
    return true;
  }
  
  @Override
  public boolean validate() {
    return (this.containsConcreteChange() && (this.getURI() != null));
  }
  
  @Override
  public List<EChange> getEChanges() {
    return new ArrayList<EChange>(Collections.<EChange>unmodifiableList(CollectionLiterals.<EChange>newArrayList(this.eChange)));
  }
  
  @Override
  public VURI getURI() {
    final Function1<EObject, Resource> _function = (EObject it) -> {
      return it.eResource();
    };
    final Iterable<Resource> resolvedResources = IterableExtensions.<Resource>filterNull(IterableExtensions.<EObject, Resource>map(this.getAffectedNotReferencedEObjects(), _function));
    int _size = IterableExtensions.size(resolvedResources);
    boolean _greaterThan = (_size > 0);
    if (_greaterThan) {
      return VURI.getInstance(((Resource[])Conversions.unwrapArray(resolvedResources, Resource.class))[0]);
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
      return VURI.getInstance((((URI[])Conversions.unwrapArray(proxyUris, URI.class))[0]).trimFragment());
    }
    return null;
  }
  
  @Override
  public EChange getEChange() {
    return this.eChange;
  }
  
  protected EChange setEChange(final EChange eChange) {
    return this.eChange = eChange;
  }
  
  @Override
  public void resolveBeforeAndApplyForward(final UuidResolver uuidResolver) {
    String _simpleName = this.getClass().getSimpleName();
    String _plus = ("The resolveBeforeAndapplyForward method is not implemented for " + _simpleName);
    String _plus_1 = (_plus + " yet.");
    AbstractConcreteChange.logger.warn(_plus_1);
  }
  
  @Override
  public void resolveAfterAndApplyBackward(final UuidResolver uuidResolver) {
    String _simpleName = this.getClass().getSimpleName();
    String _plus = ("The resolveAfterAndApplyBackward method is not implemented for " + _simpleName);
    String _plus_1 = (_plus + " yet.");
    AbstractConcreteChange.logger.warn(_plus_1);
  }
  
  @Override
  public void unresolveIfApplicable() {
  }
  
  public Iterable<EObject> getAffectedNotReferencedEObjects() {
    return IterableExtensions.<EObject>filterNull(this.getAffectedEObjects(this.eChange));
  }
  
  @Override
  public Iterable<String> getAffectedEObjectIds() {
    return IterableExtensions.<String>filterNull(this.getAffectedEObjectIds(this.eChange));
  }
  
  @Override
  public Iterable<EObject> getAffectedEObjects() {
    Iterable<EObject> _affectedNotReferencedEObjects = this.getAffectedNotReferencedEObjects();
    Iterable<EObject> _filterNull = IterableExtensions.<EObject>filterNull(this.getReferencedEObjects(this.eChange));
    return Iterables.<EObject>concat(_affectedNotReferencedEObjects, _filterNull);
  }
  
  @Override
  public Iterable<UserInteractionBase> getUserInteractions() {
    return this.userInteractions;
  }
  
  @Override
  public void setUserInteractions(final Iterable<UserInteractionBase> userInteractions) {
    if ((userInteractions == null)) {
      throw new IllegalArgumentException("Interactions must not be null");
    }
    this.userInteractions.clear();
    Iterables.<UserInteractionBase>addAll(this.userInteractions, userInteractions);
  }
  
  @Override
  public boolean equals(final Object obj) {
    return this.isEqual(obj);
  }
  
  @Override
  public boolean changedEObjectEquals(final VitruviusChange change) {
    return this.isChangedEObjectEqual(change);
  }
  
  private boolean _isEqual(final Object object) {
    return super.equals(object);
  }
  
  private boolean _isEqual(final ConcreteChange change) {
    return EcoreUtil.equals(this.eChange, change.getEChange());
  }
  
  private boolean _isChangedEObjectEqual(final VitruviusChange change) {
    return super.equals(change);
  }
  
  private boolean _isChangedEObjectEqual(final ConcreteChange change) {
    return EcoreUtil.equals(this.getEChange().getInvolvedEObjects(), change.getEChange().getInvolvedEObjects());
  }
  
  private List<String> _getAffectedEObjectIds(final EChange eChange) {
    return Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList());
  }
  
  private List<String> _getAffectedEObjectIds(final InsertRootEObject<EObject> eChange) {
    String _newValueID = eChange.getNewValueID();
    return Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList(_newValueID));
  }
  
  private List<String> _getAffectedEObjectIds(final RemoveRootEObject<EObject> eChange) {
    String _oldValueID = eChange.getOldValueID();
    return Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList(_oldValueID));
  }
  
  private List<String> _getAffectedEObjectIds(final InsertEReference<EObject, EObject> eChange) {
    String _affectedEObjectID = eChange.getAffectedEObjectID();
    String _newValueID = eChange.getNewValueID();
    return Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList(_affectedEObjectID, _newValueID));
  }
  
  private List<String> _getAffectedEObjectIds(final RemoveEReference<EObject, EObject> eChange) {
    String _affectedEObjectID = eChange.getAffectedEObjectID();
    String _oldValueID = eChange.getOldValueID();
    return Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList(_affectedEObjectID, _oldValueID));
  }
  
  private List<String> _getAffectedEObjectIds(final ReplaceSingleValuedEReference<EObject, EObject> eChange) {
    String _affectedEObjectID = eChange.getAffectedEObjectID();
    String _newValueID = eChange.getNewValueID();
    String _oldValueID = eChange.getOldValueID();
    return Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList(_affectedEObjectID, _newValueID, _oldValueID));
  }
  
  private List<String> _getAffectedEObjectIds(final FeatureEChange<EObject, ?> eChange) {
    String _affectedEObjectID = eChange.getAffectedEObjectID();
    return Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList(_affectedEObjectID));
  }
  
  private List<String> _getAffectedEObjectIds(final EObjectExistenceEChange<EObject> eChange) {
    String _affectedEObjectID = eChange.getAffectedEObjectID();
    return Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList(_affectedEObjectID));
  }
  
  private List<EObject> _getAffectedEObjects(final EChange eChange) {
    return Collections.<EObject>unmodifiableList(CollectionLiterals.<EObject>newArrayList());
  }
  
  private List<EObject> _getAffectedEObjects(final InsertRootEObject<EObject> eChange) {
    EObject _newValue = eChange.getNewValue();
    return Collections.<EObject>unmodifiableList(CollectionLiterals.<EObject>newArrayList(_newValue));
  }
  
  private List<EObject> _getAffectedEObjects(final RemoveRootEObject<EObject> eChange) {
    EObject _oldValue = eChange.getOldValue();
    return Collections.<EObject>unmodifiableList(CollectionLiterals.<EObject>newArrayList(_oldValue));
  }
  
  private List<EObject> _getAffectedEObjects(final InsertEReference<EObject, EObject> eChange) {
    EObject _affectedEObject = eChange.getAffectedEObject();
    return Collections.<EObject>unmodifiableList(CollectionLiterals.<EObject>newArrayList(_affectedEObject));
  }
  
  private List<EObject> _getAffectedEObjects(final RemoveEReference<EObject, EObject> eChange) {
    EObject _affectedEObject = eChange.getAffectedEObject();
    return Collections.<EObject>unmodifiableList(CollectionLiterals.<EObject>newArrayList(_affectedEObject));
  }
  
  private List<EObject> _getAffectedEObjects(final ReplaceSingleValuedEReference<EObject, EObject> eChange) {
    EObject _affectedEObject = eChange.getAffectedEObject();
    return Collections.<EObject>unmodifiableList(CollectionLiterals.<EObject>newArrayList(_affectedEObject));
  }
  
  private List<EObject> _getAffectedEObjects(final FeatureEChange<EObject, ?> eChange) {
    EObject _affectedEObject = eChange.getAffectedEObject();
    return Collections.<EObject>unmodifiableList(CollectionLiterals.<EObject>newArrayList(_affectedEObject));
  }
  
  private List<EObject> _getAffectedEObjects(final EObjectExistenceEChange<EObject> eChange) {
    EObject _affectedEObject = eChange.getAffectedEObject();
    return Collections.<EObject>unmodifiableList(CollectionLiterals.<EObject>newArrayList(_affectedEObject));
  }
  
  private List<EObject> _getReferencedEObjects(final EChange eChange) {
    return Collections.<EObject>unmodifiableList(CollectionLiterals.<EObject>newArrayList());
  }
  
  private List<EObject> _getReferencedEObjects(final InsertEReference<EObject, EObject> eChange) {
    EObject _newValue = eChange.getNewValue();
    return Collections.<EObject>unmodifiableList(CollectionLiterals.<EObject>newArrayList(_newValue));
  }
  
  private List<EObject> _getReferencedEObjects(final RemoveEReference<EObject, EObject> eChange) {
    EObject _oldValue = eChange.getOldValue();
    return Collections.<EObject>unmodifiableList(CollectionLiterals.<EObject>newArrayList(_oldValue));
  }
  
  private List<EObject> _getReferencedEObjects(final ReplaceSingleValuedEReference<EObject, EObject> eChange) {
    EObject _affectedEObject = eChange.getAffectedEObject();
    EObject _oldValue = eChange.getOldValue();
    EObject _newValue = eChange.getNewValue();
    return Collections.<EObject>unmodifiableList(CollectionLiterals.<EObject>newArrayList(_affectedEObject, _oldValue, _newValue));
  }
  
  private boolean isEqual(final Object change) {
    if (change instanceof ConcreteChange) {
      return _isEqual((ConcreteChange)change);
    } else if (change != null) {
      return _isEqual(change);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(change).toString());
    }
  }
  
  private boolean isChangedEObjectEqual(final VitruviusChange change) {
    if (change instanceof ConcreteChange) {
      return _isChangedEObjectEqual((ConcreteChange)change);
    } else if (change != null) {
      return _isChangedEObjectEqual(change);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(change).toString());
    }
  }
  
  private List<String> getAffectedEObjectIds(final EChange eChange) {
    if (eChange instanceof InsertEReference) {
      return _getAffectedEObjectIds((InsertEReference<EObject, EObject>)eChange);
    } else if (eChange instanceof RemoveEReference) {
      return _getAffectedEObjectIds((RemoveEReference<EObject, EObject>)eChange);
    } else if (eChange instanceof ReplaceSingleValuedEReference) {
      return _getAffectedEObjectIds((ReplaceSingleValuedEReference<EObject, EObject>)eChange);
    } else if (eChange instanceof InsertRootEObject) {
      return _getAffectedEObjectIds((InsertRootEObject<EObject>)eChange);
    } else if (eChange instanceof RemoveRootEObject) {
      return _getAffectedEObjectIds((RemoveRootEObject<EObject>)eChange);
    } else if (eChange instanceof EObjectExistenceEChange) {
      return _getAffectedEObjectIds((EObjectExistenceEChange<EObject>)eChange);
    } else if (eChange instanceof FeatureEChange) {
      return _getAffectedEObjectIds((FeatureEChange<EObject, ?>)eChange);
    } else if (eChange != null) {
      return _getAffectedEObjectIds(eChange);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(eChange).toString());
    }
  }
  
  private List<EObject> getAffectedEObjects(final EChange eChange) {
    if (eChange instanceof InsertEReference) {
      return _getAffectedEObjects((InsertEReference<EObject, EObject>)eChange);
    } else if (eChange instanceof RemoveEReference) {
      return _getAffectedEObjects((RemoveEReference<EObject, EObject>)eChange);
    } else if (eChange instanceof ReplaceSingleValuedEReference) {
      return _getAffectedEObjects((ReplaceSingleValuedEReference<EObject, EObject>)eChange);
    } else if (eChange instanceof InsertRootEObject) {
      return _getAffectedEObjects((InsertRootEObject<EObject>)eChange);
    } else if (eChange instanceof RemoveRootEObject) {
      return _getAffectedEObjects((RemoveRootEObject<EObject>)eChange);
    } else if (eChange instanceof EObjectExistenceEChange) {
      return _getAffectedEObjects((EObjectExistenceEChange<EObject>)eChange);
    } else if (eChange instanceof FeatureEChange) {
      return _getAffectedEObjects((FeatureEChange<EObject, ?>)eChange);
    } else if (eChange != null) {
      return _getAffectedEObjects(eChange);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(eChange).toString());
    }
  }
  
  private List<EObject> getReferencedEObjects(final EChange eChange) {
    if (eChange instanceof InsertEReference) {
      return _getReferencedEObjects((InsertEReference<EObject, EObject>)eChange);
    } else if (eChange instanceof RemoveEReference) {
      return _getReferencedEObjects((RemoveEReference<EObject, EObject>)eChange);
    } else if (eChange instanceof ReplaceSingleValuedEReference) {
      return _getReferencedEObjects((ReplaceSingleValuedEReference<EObject, EObject>)eChange);
    } else if (eChange != null) {
      return _getReferencedEObjects(eChange);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(eChange).toString());
    }
  }
}
