package tools.vitruv.framework.change.recording;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;
import tools.vitruv.framework.change.description.CompositeTransactionalChange;
import tools.vitruv.framework.change.description.ConcreteChange;
import tools.vitruv.framework.change.description.TransactionalChange;
import tools.vitruv.framework.change.description.VitruviusChangeFactory;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.EChangeIdManager;
import tools.vitruv.framework.change.echange.eobject.DeleteEObject;
import tools.vitruv.framework.change.echange.eobject.EObjectAddedEChange;
import tools.vitruv.framework.change.echange.eobject.EObjectSubtractedEChange;
import tools.vitruv.framework.change.echange.util.EChangeUtil;
import tools.vitruv.framework.change.recording.NotificationInfo;
import tools.vitruv.framework.change.recording.NotificationToEChangeConverter;

/**
 * This {@link Adapter} records changes to the given model elements as {@link CompositeTransactionalChanges}.
 * Recording can be started with {@link #beginRecording} and ended with {@link #endRecording}. It is assumed
 * that all elements that do not have a container when ending the recording have been deleted, resulting in
 * an appropriate delete change.
 */
@SuppressWarnings("all")
public class NotificationRecorder implements Adapter {
  @Data
  private static class PotentialRemoval {
    private final CompositeTransactionalChange transactionalChange;
    
    private final ConcreteChange removeChange;
    
    private final EObjectSubtractedEChange<?> atomicRemoveChange;
    
    public PotentialRemoval(final CompositeTransactionalChange transactionalChange, final ConcreteChange removeChange, final EObjectSubtractedEChange<?> atomicRemoveChange) {
      super();
      this.transactionalChange = transactionalChange;
      this.removeChange = removeChange;
      this.atomicRemoveChange = atomicRemoveChange;
    }
    
    @Override
    @Pure
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((this.transactionalChange== null) ? 0 : this.transactionalChange.hashCode());
      result = prime * result + ((this.removeChange== null) ? 0 : this.removeChange.hashCode());
      return prime * result + ((this.atomicRemoveChange== null) ? 0 : this.atomicRemoveChange.hashCode());
    }
    
    @Override
    @Pure
    public boolean equals(final Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      NotificationRecorder.PotentialRemoval other = (NotificationRecorder.PotentialRemoval) obj;
      if (this.transactionalChange == null) {
        if (other.transactionalChange != null)
          return false;
      } else if (!this.transactionalChange.equals(other.transactionalChange))
        return false;
      if (this.removeChange == null) {
        if (other.removeChange != null)
          return false;
      } else if (!this.removeChange.equals(other.removeChange))
        return false;
      if (this.atomicRemoveChange == null) {
        if (other.atomicRemoveChange != null)
          return false;
      } else if (!this.atomicRemoveChange.equals(other.atomicRemoveChange))
        return false;
      return true;
    }
    
    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("transactionalChange", this.transactionalChange);
      b.add("removeChange", this.removeChange);
      b.add("atomicRemoveChange", this.atomicRemoveChange);
      return b.toString();
    }
    
    @Pure
    public CompositeTransactionalChange getTransactionalChange() {
      return this.transactionalChange;
    }
    
    @Pure
    public ConcreteChange getRemoveChange() {
      return this.removeChange;
    }
    
    @Pure
    public EObjectSubtractedEChange<?> getAtomicRemoveChange() {
      return this.atomicRemoveChange;
    }
  }
  
  private Set<Notifier> rootObjects;
  
  private boolean isRecording = false;
  
  private List<EChange> changes;
  
  private List<CompositeTransactionalChange> resultChanges;
  
  private final EChangeIdManager idManager;
  
  public NotificationRecorder(final EChangeIdManager idManager) {
    this.idManager = idManager;
    this.rootObjects = CollectionLiterals.<Notifier>newHashSet();
  }
  
  @Override
  public Notifier getTarget() {
    return null;
  }
  
  @Override
  public boolean isAdapterForType(final Object type) {
    return false;
  }
  
  @Override
  public void notifyChanged(final Notification notification) {
    Object _newValue = notification.getNewValue();
    if ((_newValue instanceof Notifier)) {
      Object _newValue_1 = notification.getNewValue();
      this.addToRecording(((Notifier) _newValue_1));
    }
    if ((!this.isRecording)) {
      return;
    }
    NotificationToEChangeConverter _notificationToEChangeConverter = new NotificationToEChangeConverter(this.idManager);
    NotificationInfo _notificationInfo = new NotificationInfo(notification);
    final Iterable<EChange> newChanges = _notificationToEChangeConverter.convert(_notificationInfo, this.changes);
    for (final EChange newChange : newChanges) {
      this.changes.add(newChange);
    }
    boolean _isEmpty = IterableExtensions.isEmpty(newChanges);
    boolean _not = (!_isEmpty);
    if (_not) {
      final CompositeTransactionalChange transactionalChange = VitruviusChangeFactory.getInstance().createCompositeTransactionalChange();
      final Consumer<EChange> _function = (EChange it) -> {
        transactionalChange.addChange(VitruviusChangeFactory.getInstance().createConcreteApplicableChange(it));
      };
      newChanges.forEach(_function);
      this.resultChanges.add(transactionalChange);
    }
  }
  
  @Override
  public void setTarget(final Notifier newTarget) {
    this.recursivelyAddAdapter(newTarget);
  }
  
  /**
   * Add the given elements and all its contained elements ({@link Resource}s, {@link EObject}s) to the recorder.
   * 
   * @param notifier - the {@link Notifier} to add the recorder to
   */
  public void addToRecording(final Notifier notifier) {
    this.rootObjects.add(notifier);
    this.recursivelyAddAdapter(notifier);
  }
  
  /**
   * Removes the given elements and all its contained elements (resources, EObjects) from the recorder.
   * @param notifier - the {@link Notifier} to remove the recorder from
   */
  public void removeFromRecording(final Notifier notifier) {
    this.rootObjects.remove(notifier);
    this.recursivelyRemoveAdapter(notifier);
  }
  
  /**
   * Starts recording changes on the registered elements.
   */
  public boolean beginRecording() {
    boolean _xifexpression = false;
    if ((!this.isRecording)) {
      boolean _xblockexpression = false;
      {
        this.changes = CollectionLiterals.<EChange>newArrayList();
        this.resultChanges = CollectionLiterals.<CompositeTransactionalChange>newArrayList();
        _xblockexpression = this.isRecording = true;
      }
      _xifexpression = _xblockexpression;
    } else {
      throw new IllegalStateException();
    }
    return _xifexpression;
  }
  
  /**
   * Ends recording changes on the registered elements.
   * All elements that were removed from their container and not inserted into another one
   * are treated as deleted and a delete change is created for them, inserted right after
   * the change describing the removal from the container.
   */
  public void endRecording() {
    this.isRecording = false;
    this.postprocessRemovals();
  }
  
  public void postprocessRemovals() {
    final ArrayList<NotificationRecorder.PotentialRemoval> potentialRemovals = CollectionLiterals.<NotificationRecorder.PotentialRemoval>newArrayList();
    for (final CompositeTransactionalChange resultChange : this.resultChanges) {
      Iterable<ConcreteChange> _filter = Iterables.<ConcreteChange>filter(resultChange.getChanges(), ConcreteChange.class);
      for (final ConcreteChange concreteChange : _filter) {
        {
          final EChange eChange = concreteChange.getEChange();
          if ((eChange instanceof EObjectSubtractedEChange<?>)) {
            boolean _isContainmentRemoval = EChangeUtil.isContainmentRemoval(eChange);
            if (_isContainmentRemoval) {
              NotificationRecorder.PotentialRemoval _potentialRemoval = new NotificationRecorder.PotentialRemoval(resultChange, concreteChange, ((EObjectSubtractedEChange<?>)eChange));
              potentialRemovals.add(_potentialRemoval);
            }
          }
          if ((eChange instanceof EObjectAddedEChange<?>)) {
            boolean _isContainmentInsertion = EChangeUtil.isContainmentInsertion(eChange);
            if (_isContainmentInsertion) {
              final Predicate<NotificationRecorder.PotentialRemoval> _function = (NotificationRecorder.PotentialRemoval it) -> {
                String _oldValueID = it.atomicRemoveChange.getOldValueID();
                String _newValueID = ((EObjectAddedEChange<?>)eChange).getNewValueID();
                return Objects.equal(_oldValueID, _newValueID);
              };
              potentialRemovals.removeIf(_function);
            }
          }
        }
      }
    }
    for (final NotificationRecorder.PotentialRemoval removal : potentialRemovals) {
      {
        final DeleteEObject<EObject> deleteChange = new NotificationToEChangeConverter(this.idManager).createDeleteChange(removal.atomicRemoveChange);
        final List<TransactionalChange> currentChanges = removal.transactionalChange.getChanges();
        final int indexOfRemoveChange = currentChanges.indexOf(removal.removeChange);
        final ConcreteChange packagedDeleteChange = VitruviusChangeFactory.getInstance().createConcreteApplicableChange(deleteChange);
        currentChanges.add((indexOfRemoveChange + 1), packagedDeleteChange);
      }
    }
  }
  
  public List<TransactionalChange> getChanges() {
    return IterableExtensions.<TransactionalChange>toList(Iterables.<TransactionalChange>filter(this.resultChanges, TransactionalChange.class));
  }
  
  public boolean isRecording() {
    return this.isRecording;
  }
  
  private void _recursivelyRemoveAdapter(final Notifier notifier) {
    this.removeAdapter(notifier);
  }
  
  private void _recursivelyRemoveAdapter(final EObject object) {
    final Consumer<EObject> _function = (EObject it) -> {
      this.recursivelyRemoveAdapter(it);
    };
    object.eContents().forEach(_function);
    this.removeAdapter(object);
  }
  
  private void _recursivelyRemoveAdapter(final Resource resource) {
    final Consumer<EObject> _function = (EObject it) -> {
      this.recursivelyRemoveAdapter(it);
    };
    resource.getContents().forEach(_function);
    this.removeAdapter(resource);
  }
  
  private void _recursivelyRemoveAdapter(final ResourceSet resourceSet) {
    final Consumer<Resource> _function = (Resource it) -> {
      this.recursivelyRemoveAdapter(it);
    };
    resourceSet.getResources().forEach(_function);
    this.removeAdapter(resourceSet);
  }
  
  private void removeAdapter(final Notifier notifier) {
    final EList<Adapter> eAdapters = notifier.eAdapters();
    eAdapters.remove(this);
  }
  
  private void _recursivelyAddAdapter(final Notifier notifier) {
    this.addAdapter(notifier);
  }
  
  private void _recursivelyAddAdapter(final EObject object) {
    final Function1<EObject, Boolean> _function = (EObject it) -> {
      Resource _eResource = it.eResource();
      Resource _eResource_1 = object.eResource();
      return Boolean.valueOf(Objects.equal(_eResource, _eResource_1));
    };
    final Consumer<EObject> _function_1 = (EObject it) -> {
      this.recursivelyAddAdapter(it);
    };
    IterableExtensions.<EObject>filter(object.eContents(), _function).forEach(_function_1);
    this.addAdapter(object);
  }
  
  private void _recursivelyAddAdapter(final Resource resource) {
    final Consumer<EObject> _function = (EObject it) -> {
      this.recursivelyAddAdapter(it);
    };
    resource.getContents().forEach(_function);
    this.addAdapter(resource);
  }
  
  private void _recursivelyAddAdapter(final ResourceSet resourceSet) {
    final Consumer<Resource> _function = (Resource it) -> {
      this.recursivelyAddAdapter(it);
    };
    resourceSet.getResources().forEach(_function);
    this.addAdapter(resourceSet);
  }
  
  private void addAdapter(final Notifier notifier) {
    final EList<Adapter> eAdapters = notifier.eAdapters();
    boolean _contains = eAdapters.contains(this);
    boolean _not = (!_contains);
    if (_not) {
      eAdapters.add(this);
    }
  }
  
  private void recursivelyRemoveAdapter(final Notifier object) {
    if (object instanceof EObject) {
      _recursivelyRemoveAdapter((EObject)object);
      return;
    } else if (object instanceof Resource) {
      _recursivelyRemoveAdapter((Resource)object);
      return;
    } else if (object instanceof ResourceSet) {
      _recursivelyRemoveAdapter((ResourceSet)object);
      return;
    } else if (object != null) {
      _recursivelyRemoveAdapter(object);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(object).toString());
    }
  }
  
  private void recursivelyAddAdapter(final Notifier object) {
    if (object instanceof EObject) {
      _recursivelyAddAdapter((EObject)object);
      return;
    } else if (object instanceof Resource) {
      _recursivelyAddAdapter((Resource)object);
      return;
    } else if (object instanceof ResourceSet) {
      _recursivelyAddAdapter((ResourceSet)object);
      return;
    } else if (object != null) {
      _recursivelyAddAdapter(object);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(object).toString());
    }
  }
}
