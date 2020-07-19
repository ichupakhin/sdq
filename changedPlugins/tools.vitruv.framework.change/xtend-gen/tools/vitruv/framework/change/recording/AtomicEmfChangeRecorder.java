package tools.vitruv.framework.change.recording;

import com.google.common.base.Objects;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import tools.vitruv.framework.change.description.CompositeTransactionalChange;
import tools.vitruv.framework.change.description.TransactionalChange;
import tools.vitruv.framework.change.description.impl.ConcreteApplicableChangeImpl;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.EChangeIdManager;
import tools.vitruv.framework.change.echange.eobject.CreateEObject;
import tools.vitruv.framework.change.echange.eobject.DeleteEObject;
import tools.vitruv.framework.change.recording.NotificationRecorder;
import tools.vitruv.framework.uuid.UuidGeneratorAndResolver;

@SuppressWarnings("all")
public class AtomicEmfChangeRecorder {
  private final Set<Notifier> elementsToObserve;
  
  private final NotificationRecorder changeRecorder;
  
  private List<TransactionalChange> changes;
  
  private final UuidGeneratorAndResolver uuidGeneratorAndResolver;
  
  private final EChangeIdManager eChangeIdManager;
  
  /**
   * Constructor for AtomicEMFChangeRecorder.
   * 
   * @param uuidGeneratorAndResolver -
   * 		the {@link UuidGeneratorAndResolver} for ID generation
   * @param strictMode -
   * 		specifies whether exceptions shall be thrown if no ID exists for an element that should already have one.
   * 		Should be set to <code>false</code> if model is not recorded from beginning
   */
  public AtomicEmfChangeRecorder(final UuidGeneratorAndResolver uuidGeneratorAndResolver) {
    this.elementsToObserve = CollectionLiterals.<Notifier>newHashSet();
    this.uuidGeneratorAndResolver = uuidGeneratorAndResolver;
    EChangeIdManager _eChangeIdManager = new EChangeIdManager(uuidGeneratorAndResolver);
    this.eChangeIdManager = _eChangeIdManager;
    NotificationRecorder _notificationRecorder = new NotificationRecorder(this.eChangeIdManager);
    this.changeRecorder = _notificationRecorder;
  }
  
  public void beginRecording() {
    this.changeRecorder.beginRecording();
  }
  
  public void addToRecording(final Notifier elementToObserve) {
    if ((elementToObserve == null)) {
      throw new IllegalArgumentException("Element to observe must not be null");
    }
    this.elementsToObserve.add(elementToObserve);
    final Consumer<Notifier> _function = (Notifier it) -> {
      this.changeRecorder.addToRecording(it);
    };
    this.elementsToObserve.forEach(_function);
    this.registerContentsAtUuidResolver(elementToObserve);
  }
  
  private void _registerContentsAtUuidResolver(final ResourceSet resourceSetToObserve) {
    final EList<Resource> resources = resourceSetToObserve.getResources();
    for (final Resource resource : resources) {
      this.registerContentsAtUuidResolver(resource);
    }
  }
  
  private void _registerContentsAtUuidResolver(final Resource resourceToObserve) {
    final EList<EObject> rootObjects = resourceToObserve.getContents();
    for (final EObject rootObject : rootObjects) {
      this.registerContentsAtUuidResolver(rootObject);
    }
  }
  
  private void _registerContentsAtUuidResolver(final EObject eObjectToObserve) {
    this.uuidGeneratorAndResolver.registerEObject(eObjectToObserve);
    final EList<EObject> containedObjects = eObjectToObserve.eContents();
    for (final EObject containedObject : containedObjects) {
      this.registerContentsAtUuidResolver(containedObject);
    }
  }
  
  public void removeFromRecording(final Notifier elementToObserve) {
    this.elementsToObserve.remove(elementToObserve);
    this.changeRecorder.removeFromRecording(elementToObserve);
  }
  
  /**
   * Stops recording without returning a result
   */
  public void stopRecording() {
    boolean _isRecording = this.isRecording();
    boolean _not = (!_isRecording);
    if (_not) {
      throw new IllegalStateException();
    }
    this.changeRecorder.endRecording();
  }
  
  public void endRecording() {
    boolean _isRecording = this.isRecording();
    boolean _not = (!_isRecording);
    if (_not) {
      throw new IllegalStateException();
    }
    this.changeRecorder.endRecording();
    final List<TransactionalChange> changes = this.changeRecorder.getChanges();
    this.removeCreateFollowedByDelete(changes);
    this.changes = changes;
  }
  
  public void removeCreateFollowedByDelete(final List<TransactionalChange> changes) {
    final Multimap<TransactionalChange, EObject> createdObjects = this.generateCreateChangesMultimap(changes);
    final Multimap<TransactionalChange, EObject> deletedObjects = this.generateDeleteChangesMultimap(changes);
    for (int i = 0; (i < changes.size()); i++) {
      {
        final Collection<EObject> currentDeletedObjects = deletedObjects.get(changes.get(i));
        for (int k = (i + 1); (k < changes.size()); k++) {
          for (int object = 0; (object < currentDeletedObjects.size()); object++) {
            boolean _contains = createdObjects.get(changes.get(k)).contains(((Object[])Conversions.unwrapArray(currentDeletedObjects, Object.class))[object]);
            if (_contains) {
              TransactionalChange _get = changes.get(i);
              this.removeCreateOrDelete(((CompositeTransactionalChange) _get), ((EObject[])Conversions.unwrapArray(currentDeletedObjects, EObject.class))[object], false);
              TransactionalChange _get_1 = changes.get(k);
              this.removeCreateOrDelete(((CompositeTransactionalChange) _get_1), ((EObject[])Conversions.unwrapArray(currentDeletedObjects, EObject.class))[object], true);
            }
          }
        }
      }
    }
  }
  
  public boolean isRecording() {
    return this.changeRecorder.isRecording();
  }
  
  public void dispose() {
  }
  
  private static Iterable<CreateEObject<?>> typeCreateChanges(final Iterable<?> iterable) {
    final ArrayList<CreateEObject<?>> result = CollectionLiterals.<CreateEObject<?>>newArrayList();
    for (final Object item : iterable) {
      result.add(((CreateEObject<?>) item));
    }
    return result;
  }
  
  private static Iterable<DeleteEObject<?>> typeDeleteChanges(final Iterable<?> iterable) {
    final ArrayList<DeleteEObject<?>> result = CollectionLiterals.<DeleteEObject<?>>newArrayList();
    for (final Object item : iterable) {
      result.add(((DeleteEObject<?>) item));
    }
    return result;
  }
  
  private void removeCreateOrDelete(final CompositeTransactionalChange change, final EObject createdObject, final boolean create) {
    for (int i = 0; (i < change.getChanges().size()); i++) {
      {
        final TransactionalChange currentChange = change.getChanges().get(i);
        if ((currentChange instanceof ConcreteApplicableChangeImpl)) {
          final EChange eChange = ((ConcreteApplicableChangeImpl)currentChange).getEChange();
          if (create) {
            if ((eChange instanceof CreateEObject<?>)) {
              EObject _affectedEObject = ((CreateEObject<?>)eChange).getAffectedEObject();
              boolean _equals = Objects.equal(_affectedEObject, createdObject);
              if (_equals) {
                change.getChanges().remove(i);
              }
            }
          } else {
            if ((eChange instanceof DeleteEObject<?>)) {
              EObject _affectedEObject_1 = ((DeleteEObject<?>)eChange).getAffectedEObject();
              boolean _equals_1 = Objects.equal(_affectedEObject_1, createdObject);
              if (_equals_1) {
                change.getChanges().remove(i);
              }
            }
          }
        } else {
          if ((currentChange instanceof CompositeTransactionalChange)) {
            this.removeCreateOrDelete(((CompositeTransactionalChange)currentChange), createdObject, create);
          }
        }
      }
    }
  }
  
  private Multimap<TransactionalChange, EObject> generateDeleteChangesMultimap(final List<TransactionalChange> changes) {
    final Multimap<TransactionalChange, EObject> deletedObjects = ArrayListMultimap.<TransactionalChange, EObject>create();
    for (final TransactionalChange change : changes) {
      {
        final Iterable<DeleteEObject<?>> deleteChange = AtomicEmfChangeRecorder.typeDeleteChanges(Iterables.<DeleteEObject>filter(change.getEChanges(), DeleteEObject.class));
        final Function1<DeleteEObject<?>, EObject> _function = (DeleteEObject<?> it) -> {
          return it.getAffectedEObject();
        };
        deletedObjects.putAll(change, IterableExtensions.<DeleteEObject<?>, EObject>map(deleteChange, _function));
      }
    }
    return deletedObjects;
  }
  
  private Multimap<TransactionalChange, EObject> generateCreateChangesMultimap(final List<TransactionalChange> changes) {
    final Multimap<TransactionalChange, EObject> createdObjects = ArrayListMultimap.<TransactionalChange, EObject>create();
    for (final TransactionalChange change : changes) {
      {
        final Iterable<CreateEObject<?>> createChanges = AtomicEmfChangeRecorder.typeCreateChanges(Iterables.<CreateEObject>filter(change.getEChanges(), CreateEObject.class));
        final Function1<CreateEObject<?>, EObject> _function = (CreateEObject<?> it) -> {
          return it.getAffectedEObject();
        };
        createdObjects.putAll(change, IterableExtensions.<CreateEObject<?>, EObject>map(createChanges, _function));
      }
    }
    return createdObjects;
  }
  
  public List<TransactionalChange> getChanges() {
    return this.changes;
  }
  
  private void registerContentsAtUuidResolver(final Notifier eObjectToObserve) {
    if (eObjectToObserve instanceof EObject) {
      _registerContentsAtUuidResolver((EObject)eObjectToObserve);
      return;
    } else if (eObjectToObserve instanceof Resource) {
      _registerContentsAtUuidResolver((Resource)eObjectToObserve);
      return;
    } else if (eObjectToObserve instanceof ResourceSet) {
      _registerContentsAtUuidResolver((ResourceSet)eObjectToObserve);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(eObjectToObserve).toString());
    }
  }
}
