package tools.vitruv.framework.change.recording;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.EChangeIdManager;
import tools.vitruv.framework.change.echange.TypeInferringAtomicEChangeFactory;
import tools.vitruv.framework.change.echange.eobject.CreateEObject;
import tools.vitruv.framework.change.echange.eobject.DeleteEObject;
import tools.vitruv.framework.change.echange.eobject.EObjectAddedEChange;
import tools.vitruv.framework.change.echange.eobject.EObjectSubtractedEChange;
import tools.vitruv.framework.change.echange.feature.UnsetFeature;
import tools.vitruv.framework.change.echange.feature.attribute.AttributeFactory;
import tools.vitruv.framework.change.echange.feature.attribute.InsertEAttributeValue;
import tools.vitruv.framework.change.echange.feature.attribute.RemoveEAttributeValue;
import tools.vitruv.framework.change.echange.feature.attribute.ReplaceSingleValuedEAttribute;
import tools.vitruv.framework.change.echange.feature.reference.InsertEReference;
import tools.vitruv.framework.change.echange.feature.reference.RemoveEReference;
import tools.vitruv.framework.change.echange.feature.reference.ReplaceSingleValuedEReference;
import tools.vitruv.framework.change.echange.root.InsertRootEObject;
import tools.vitruv.framework.change.echange.root.RemoveRootEObject;
import tools.vitruv.framework.change.preparation.EMFModelChangeTransformationUtil;
import tools.vitruv.framework.change.recording.NotificationInfo;

/**
 * Converts an EMF notification to an {@link EChange}.
 * @author Heiko Klare
 */
@SuppressWarnings("all")
public final class NotificationToEChangeConverter {
  private EChangeIdManager eChangeIdManager;
  
  public NotificationToEChangeConverter(final EChangeIdManager eChangeeChangeIdManager) {
    this.eChangeIdManager = eChangeeChangeIdManager;
  }
  
  /**
   * Converts the given notification to a list of {@link EChange}s.
   * @param n the notification to convert
   * @return the  {@link Iterable} of {@link EChange}s
   */
  public Iterable<EChange> convert(final NotificationInfo notification, final List<EChange> previousChanges) {
    if ((notification.isTouch() || notification.isTransient())) {
      return Collections.<EChange>unmodifiableList(CollectionLiterals.<EChange>newArrayList());
    }
    Object _oldValue = notification.getOldValue();
    Object _newValue = notification.getNewValue();
    boolean _equals = Objects.equal(_oldValue, _newValue);
    if (_equals) {
      return Collections.<EChange>unmodifiableList(CollectionLiterals.<EChange>newArrayList());
    }
    int _eventType = notification.getEventType();
    switch (_eventType) {
      case Notification.SET:
        boolean _isAttributeNotification = notification.isAttributeNotification();
        if (_isAttributeNotification) {
          return this.handleSetAttribute(notification);
        } else {
          boolean _isReferenceNotification = notification.isReferenceNotification();
          if (_isReferenceNotification) {
            return this.handleSetReference(notification);
          }
        }
        break;
      case Notification.UNSET:
        boolean _isAttributeNotification_1 = notification.isAttributeNotification();
        if (_isAttributeNotification_1) {
          return this.handleUnsetAttribute(notification, previousChanges);
        } else {
          boolean _isReferenceNotification_1 = notification.isReferenceNotification();
          if (_isReferenceNotification_1) {
            return this.handleUnsetReference(notification, previousChanges);
          }
        }
        break;
      case Notification.ADD:
        boolean _isAttributeNotification_2 = notification.isAttributeNotification();
        if (_isAttributeNotification_2) {
          return this.handleMultiAttribute(notification);
        } else {
          boolean _isReferenceNotification_2 = notification.isReferenceNotification();
          if (_isReferenceNotification_2) {
            return this.handleMultiReference(notification);
          }
        }
        break;
      case Notification.ADD_MANY:
        boolean _isAttributeNotification_3 = notification.isAttributeNotification();
        if (_isAttributeNotification_3) {
          return this.handleMultiAttribute(notification);
        } else {
          boolean _isReferenceNotification_3 = notification.isReferenceNotification();
          if (_isReferenceNotification_3) {
            return this.handleMultiReference(notification);
          }
        }
        break;
      case Notification.REMOVE:
        boolean _isAttributeNotification_4 = notification.isAttributeNotification();
        if (_isAttributeNotification_4) {
          return this.handleMultiAttribute(notification);
        } else {
          boolean _isReferenceNotification_4 = notification.isReferenceNotification();
          if (_isReferenceNotification_4) {
            return this.handleMultiReference(notification);
          }
        }
        break;
      case Notification.REMOVE_MANY:
        boolean _isAttributeNotification_5 = notification.isAttributeNotification();
        if (_isAttributeNotification_5) {
          return this.handleMultiAttribute(notification);
        } else {
          boolean _isReferenceNotification_5 = notification.isReferenceNotification();
          if (_isReferenceNotification_5) {
            return this.handleMultiReference(notification);
          }
        }
        break;
    }
    Object _notifier = notification.getNotifier();
    if ((_notifier instanceof Resource)) {
      return this.handleResourceChange(notification);
    }
    return Collections.<EChange>unmodifiableList(CollectionLiterals.<EChange>newArrayList());
  }
  
  public DeleteEObject<EObject> createDeleteChange(final EObjectSubtractedEChange<?> change) {
    final DeleteEObject<EObject> deleteChange = TypeInferringAtomicEChangeFactory.getInstance().<EObject>createDeleteEObjectChange(change.getOldValue());
    this.eChangeIdManager.setOrGenerateIds(deleteChange);
    EList<EChange> _consequentialRemoveChanges = deleteChange.getConsequentialRemoveChanges();
    Iterable<EChange> _recursiveRemoval = this.recursiveRemoval(change.getOldValue());
    Iterables.<EChange>addAll(_consequentialRemoveChanges, _recursiveRemoval);
    return deleteChange;
  }
  
  private Iterable<EChange> handleMultiAttribute(final NotificationInfo n) {
    List<EChange> changes = new ArrayList<EChange>();
    final EObject affectedEObject = n.getNotifierModelElement();
    final EAttribute affectedFeature = n.getAttribute();
    int _eventType = n.getEventType();
    switch (_eventType) {
      case Notification.ADD:
        InsertEAttributeValue<EObject, Object> _handleInsertAttribute = this.handleInsertAttribute(affectedEObject, affectedFeature, n.getNewValue(), n.getPosition());
        changes.add(_handleInsertAttribute);
        break;
      case Notification.ADD_MANY:
        Object _newValue = n.getNewValue();
        List<Object> list = ((List<Object>) _newValue);
        for (int i = 0; (i < list.size()); i++) {
          Object _get = list.get(i);
          int _initialIndex = n.getInitialIndex();
          int _plus = (_initialIndex + i);
          InsertEAttributeValue<EObject, Object> _handleInsertAttribute_1 = this.handleInsertAttribute(affectedEObject, affectedFeature, _get, _plus);
          changes.add(_handleInsertAttribute_1);
        }
        break;
      case Notification.REMOVE:
        RemoveEAttributeValue<EObject, Object> _handleRemoveAttribute = this.handleRemoveAttribute(affectedEObject, affectedFeature, n.getOldValue(), n.getPosition());
        changes.add(_handleRemoveAttribute);
        boolean _wasUnset = n.wasUnset();
        if (_wasUnset) {
          UnsetFeature<EObject, EAttribute> _createUnsetFeatureChange = TypeInferringAtomicEChangeFactory.getInstance().<EObject, EAttribute>createUnsetFeatureChange(affectedEObject, affectedFeature);
          changes.add(_createUnsetFeatureChange);
        }
        break;
      case Notification.REMOVE_MANY:
        Object _oldValue = n.getOldValue();
        List<Object> list_1 = ((List<Object>) _oldValue);
        Object _newValue_1 = n.getNewValue();
        boolean _tripleEquals = (_newValue_1 == null);
        if (_tripleEquals) {
          for (int i = (list_1.size() - 1); (i >= 0); i--) {
            Object _get = list_1.get(i);
            int _initialIndex = n.getInitialIndex();
            int _plus = (_initialIndex + i);
            RemoveEAttributeValue<EObject, Object> _handleRemoveAttribute_1 = this.handleRemoveAttribute(affectedEObject, affectedFeature, _get, _plus);
            changes.add(_handleRemoveAttribute_1);
          }
        }
        boolean _wasUnset_1 = n.wasUnset();
        if (_wasUnset_1) {
          UnsetFeature<EObject, EAttribute> _createUnsetFeatureChange_1 = TypeInferringAtomicEChangeFactory.getInstance().<EObject, EAttribute>createUnsetFeatureChange(affectedEObject, affectedFeature);
          changes.add(_createUnsetFeatureChange_1);
        }
        break;
    }
    return changes;
  }
  
  private Iterable<EChange> handleMultiReference(final NotificationInfo n) {
    final ArrayList<EChange> changes = CollectionLiterals.<EChange>newArrayList();
    final EObject affectedEObject = n.getNotifierModelElement();
    final EReference affectedReference = n.getReference();
    int _eventType = n.getEventType();
    switch (_eventType) {
      case Notification.ADD:
        Iterable<EChange> _handleInsertReference = this.handleInsertReference(affectedEObject, affectedReference, n.getNewModelElementValue(), n.getPosition());
        Iterables.<EChange>addAll(changes, _handleInsertReference);
        break;
      case Notification.ADD_MANY:
        Object _newValue = n.getNewValue();
        List<EObject> list = ((List<EObject>) _newValue);
        for (int i = 0; (i < list.size()); i++) {
          EObject _get = list.get(i);
          int _initialIndex = n.getInitialIndex();
          int _plus = (_initialIndex + i);
          Iterable<EChange> _handleInsertReference_1 = this.handleInsertReference(affectedEObject, affectedReference, _get, _plus);
          Iterables.<EChange>addAll(changes, _handleInsertReference_1);
        }
        break;
      case Notification.REMOVE:
        Iterable<EChange> _handleRemoveReference = this.handleRemoveReference(affectedEObject, affectedReference, n.getOldModelElementValue(), n.getPosition());
        Iterables.<EChange>addAll(changes, _handleRemoveReference);
        boolean _wasUnset = n.wasUnset();
        if (_wasUnset) {
          UnsetFeature<EObject, EReference> _createUnsetFeatureChange = TypeInferringAtomicEChangeFactory.getInstance().<EObject, EReference>createUnsetFeatureChange(affectedEObject, affectedReference);
          changes.add(_createUnsetFeatureChange);
        }
        break;
      case Notification.REMOVE_MANY:
        Object _oldValue = n.getOldValue();
        List<EObject> list_1 = ((List<EObject>) _oldValue);
        Object _newValue_1 = n.getNewValue();
        boolean _tripleEquals = (_newValue_1 == null);
        if (_tripleEquals) {
          for (int i = (list_1.size() - 1); (i >= 0); i--) {
            EObject _get = list_1.get(i);
            int _initialIndex = n.getInitialIndex();
            int _plus = (_initialIndex + i);
            Iterable<EChange> _handleRemoveReference_1 = this.handleRemoveReference(affectedEObject, affectedReference, _get, _plus);
            Iterables.<EChange>addAll(changes, _handleRemoveReference_1);
          }
        }
        boolean _wasUnset_1 = n.wasUnset();
        if (_wasUnset_1) {
          UnsetFeature<EObject, EReference> _createUnsetFeatureChange_1 = TypeInferringAtomicEChangeFactory.getInstance().<EObject, EReference>createUnsetFeatureChange(affectedEObject, affectedReference);
          changes.add(_createUnsetFeatureChange_1);
        }
        break;
    }
    return changes;
  }
  
  private InsertEAttributeValue<EObject, Object> handleInsertAttribute(final EObject affectedEObject, final EAttribute affectedReference, final Object newValue, final int position) {
    final InsertEAttributeValue<EObject, Object> change = TypeInferringAtomicEChangeFactory.getInstance().<EObject, Object>createInsertAttributeChange(affectedEObject, affectedReference, position, newValue);
    this.eChangeIdManager.setOrGenerateIds(change);
    return change;
  }
  
  private RemoveEAttributeValue<EObject, Object> handleRemoveAttribute(final EObject affectedEObject, final EAttribute affectedReference, final Object oldValue, final int position) {
    final RemoveEAttributeValue<EObject, Object> change = TypeInferringAtomicEChangeFactory.getInstance().<EObject, Object>createRemoveAttributeChange(affectedEObject, affectedReference, position, oldValue);
    this.eChangeIdManager.setOrGenerateIds(change);
    return change;
  }
  
  private Iterable<EChange> handleInsertReference(final EObject affectedEObject, final EReference affectedReference, final EObject newValue, final int position) {
    final InsertEReference<EObject, EObject> change = TypeInferringAtomicEChangeFactory.getInstance().<EObject, EObject>createInsertReferenceChange(affectedEObject, affectedReference, newValue, position);
    final Pair<Iterable<EChange>, Iterable<EChange>> beforeAndAfterCreateChanges = this.getCreateBeforeAndAfterChangesIfNecessary(change);
    this.eChangeIdManager.setOrGenerateIds(change);
    Iterable<EChange> _key = beforeAndAfterCreateChanges.getKey();
    Iterable<EChange> _plus = Iterables.<EChange>concat(_key, Collections.<InsertEReference<EObject, EObject>>unmodifiableList(CollectionLiterals.<InsertEReference<EObject, EObject>>newArrayList(change)));
    Iterable<EChange> _value = beforeAndAfterCreateChanges.getValue();
    return Iterables.<EChange>concat(_plus, _value);
  }
  
  private Iterable<EChange> handleRemoveReference(final EObject affectedEObject, final EReference affectedReference, final EObject oldValue, final int position) {
    final RemoveEReference<EObject, EObject> change = TypeInferringAtomicEChangeFactory.getInstance().<EObject, EObject>createRemoveReferenceChange(affectedEObject, affectedReference, oldValue, position);
    this.eChangeIdManager.setOrGenerateIds(change);
    return Collections.<EChange>unmodifiableList(CollectionLiterals.<EChange>newArrayList(change));
  }
  
  private Iterable<EChange> handleReplaceReference(final EObject affectedEObject, final EReference reference, final EObject oldValue, final EObject newValue, final boolean unset) {
    final ReplaceSingleValuedEReference<EObject, EObject> change = TypeInferringAtomicEChangeFactory.getInstance().<EObject, EObject>createReplaceSingleReferenceChange(affectedEObject, reference, oldValue, newValue);
    change.setIsUnset(unset);
    final List<EChange> result = CollectionLiterals.<EChange>newArrayList();
    final Pair<Iterable<EChange>, Iterable<EChange>> beforeAndAfterCreateChanges = this.getCreateBeforeAndAfterChangesIfNecessary(change);
    this.eChangeIdManager.setOrGenerateIds(change);
    Iterable<EChange> _key = beforeAndAfterCreateChanges.getKey();
    Iterables.<EChange>addAll(result, _key);
    result.add(change);
    Iterable<EChange> _value = beforeAndAfterCreateChanges.getValue();
    Iterables.<EChange>addAll(result, _value);
    return result;
  }
  
  private Pair<Iterable<EChange>, Iterable<EChange>> getCreateBeforeAndAfterChangesIfNecessary(final EObjectAddedEChange<?> change) {
    final ArrayList<EChange> beforeChanges = CollectionLiterals.<EChange>newArrayList();
    final ArrayList<EChange> afterChanges = CollectionLiterals.<EChange>newArrayList();
    final boolean created = this.eChangeIdManager.isCreateChange(change);
    if (created) {
      final CreateEObject<EObject> createChange = TypeInferringAtomicEChangeFactory.getInstance().<EObject>createCreateEObjectChange(change.getNewValue());
      this.eChangeIdManager.setOrGenerateIds(createChange);
      beforeChanges.add(createChange);
    }
    if (created) {
      Iterable<EChange> _recursiveAddition = this.recursiveAddition(change.getNewValue());
      Iterables.<EChange>addAll(afterChanges, _recursiveAddition);
    }
    return new Pair<Iterable<EChange>, Iterable<EChange>>(beforeChanges, afterChanges);
  }
  
  private ArrayList<EChange> handleResourceChange(final NotificationInfo notification) {
    final ArrayList<EChange> changes = CollectionLiterals.<EChange>newArrayList();
    Object _notifier = notification.getNotifier();
    final Resource resource = ((Resource) _notifier);
    int _eventType = notification.getEventType();
    switch (_eventType) {
      case Notification.ADD:
        Object _newValue = notification.getNewValue();
        if ((_newValue instanceof EObject)) {
          Iterable<EChange> _handleInsertRootChange = this.handleInsertRootChange(resource, notification.getNewModelElementValue(), notification.getPosition());
          Iterables.<EChange>addAll(changes, _handleInsertRootChange);
        }
        break;
      case Notification.ADD_MANY:
        Object _newValue_1 = notification.getNewValue();
        if ((!(_newValue_1 instanceof List))) {
          return changes;
        }
        List<?> tempList = ((List<?>) _newValue_1);
        for (int i = 0; (i < tempList.size()); i++) {
          Object _get = tempList.get(i);
          boolean _not = (!(_get instanceof EObject));
          if (_not) {
            return changes;
          }
        }
        Object _newValue_2 = notification.getNewValue();
        List<EObject> list = ((List<EObject>) _newValue_2);
        for (int i = 0; (i < list.size()); i++) {
          EObject _get = list.get(i);
          int _initialIndex = notification.getInitialIndex();
          int _plus = (_initialIndex + i);
          Iterable<EChange> _handleInsertRootChange_1 = this.handleInsertRootChange(resource, _get, _plus);
          Iterables.<EChange>addAll(changes, _handleInsertRootChange_1);
        }
        break;
      case Notification.REMOVE:
        List<RemoveRootEObject<EObject>> _handleRemoveRootChange = this.handleRemoveRootChange(resource, notification.getOldModelElementValue(), notification.getPosition());
        Iterables.<EChange>addAll(changes, _handleRemoveRootChange);
        break;
      case Notification.REMOVE_MANY:
        Object _oldValue = notification.getOldValue();
        if ((!(_oldValue instanceof List))) {
          return changes;
        }
        List<?> tempList_1 = ((List<?>) _oldValue);
        for (int i = 0; (i < tempList_1.size()); i++) {
          Object _get = tempList_1.get(i);
          boolean _not = (!(_get instanceof EObject));
          if (_not) {
            return changes;
          }
        }
        Object _oldValue_1 = notification.getOldValue();
        List<EObject> list_1 = ((List<EObject>) _oldValue_1);
        for (int i = (list_1.size() - 1); (i >= 0); i--) {
          EObject _get = list_1.get(i);
          int _initialIndex = notification.getInitialIndex();
          int _plus = (_initialIndex + i);
          List<RemoveRootEObject<EObject>> _handleRemoveRootChange_1 = this.handleRemoveRootChange(resource, _get, _plus);
          Iterables.<EChange>addAll(changes, _handleRemoveRootChange_1);
        }
        break;
    }
    return changes;
  }
  
  private Iterable<EChange> handleInsertRootChange(final Resource resource, final EObject rootElement, final int position) {
    final InsertRootEObject<EObject> change = TypeInferringAtomicEChangeFactory.getInstance().<EObject>createInsertRootChange(rootElement, resource, position);
    final Pair<Iterable<EChange>, Iterable<EChange>> beforeAndAfterCreateChanges = this.getCreateBeforeAndAfterChangesIfNecessary(change);
    this.eChangeIdManager.setOrGenerateIds(change);
    Iterable<EChange> _key = beforeAndAfterCreateChanges.getKey();
    Iterable<EChange> _plus = Iterables.<EChange>concat(_key, Collections.<InsertRootEObject<EObject>>unmodifiableList(CollectionLiterals.<InsertRootEObject<EObject>>newArrayList(change)));
    Iterable<EChange> _value = beforeAndAfterCreateChanges.getValue();
    return Iterables.<EChange>concat(_plus, _value);
  }
  
  private List<RemoveRootEObject<EObject>> handleRemoveRootChange(final Resource resource, final EObject oldRootElement, final int position) {
    final RemoveRootEObject<EObject> change = TypeInferringAtomicEChangeFactory.getInstance().<EObject>createRemoveRootChange(oldRootElement, resource, position);
    this.eChangeIdManager.setOrGenerateIds(change);
    return Collections.<RemoveRootEObject<EObject>>unmodifiableList(CollectionLiterals.<RemoveRootEObject<EObject>>newArrayList(change));
  }
  
  private Iterable<EChange> handleSetAttribute(final NotificationInfo n) {
    final List<EChange> changes = new ArrayList<EChange>();
    final EObject affectedEObject = n.getNotifierModelElement();
    final EAttribute affectedFeature = n.getAttribute();
    boolean _isMany = n.getAttribute().isMany();
    if (_isMany) {
      Object _oldValue = n.getOldValue();
      boolean _tripleNotEquals = (_oldValue != null);
      if (_tripleNotEquals) {
        RemoveEAttributeValue<EObject, Object> _handleRemoveAttribute = this.handleRemoveAttribute(affectedEObject, affectedFeature, n.getOldValue(), n.getPosition());
        changes.add(_handleRemoveAttribute);
        boolean _wasUnset = n.wasUnset();
        if (_wasUnset) {
          UnsetFeature<EObject, EAttribute> _createUnsetFeatureChange = TypeInferringAtomicEChangeFactory.getInstance().<EObject, EAttribute>createUnsetFeatureChange(affectedEObject, affectedFeature);
          changes.add(_createUnsetFeatureChange);
        }
      }
      Object _newValue = n.getNewValue();
      boolean _tripleNotEquals_1 = (_newValue != null);
      if (_tripleNotEquals_1) {
        InsertEAttributeValue<EObject, Object> _handleInsertAttribute = this.handleInsertAttribute(affectedEObject, affectedFeature, n.getNewValue(), n.getPosition());
        changes.add(_handleInsertAttribute);
      }
    } else {
      ReplaceSingleValuedEAttribute<EObject, Object> op = AttributeFactory.eINSTANCE.<EObject, Object>createReplaceSingleValuedEAttribute();
      op.setOldValue(n.getOldValue());
      op.setNewValue(n.getNewValue());
      Object _feature = n.getFeature();
      op.setAffectedFeature(((EAttribute) _feature));
      Object _notifier = n.getNotifier();
      op.setAffectedEObject(((EObject) _notifier));
      boolean _wasUnset_1 = n.wasUnset();
      if (_wasUnset_1) {
        op.setIsUnset(true);
      }
      this.eChangeIdManager.setOrGenerateIds(op);
      changes.add(op);
    }
    return changes;
  }
  
  private Iterable<EChange> handleSetReference(final NotificationInfo n) {
    final EObject oldValue = n.getOldModelElementValue();
    final EObject newValue = n.getNewModelElementValue();
    boolean _isMany = n.getReference().isMany();
    boolean _not = (!_isMany);
    if (_not) {
      return this.handleReplaceReference(n.getNotifierModelElement(), n.getReference(), oldValue, newValue, n.wasUnset());
    } else {
      final ArrayList<EChange> changes = CollectionLiterals.<EChange>newArrayList();
      if ((oldValue != null)) {
        Iterable<EChange> _handleRemoveReference = this.handleRemoveReference(n.getNotifierModelElement(), n.getReference(), oldValue, n.getPosition());
        Iterables.<EChange>addAll(changes, _handleRemoveReference);
      }
      boolean _wasUnset = n.wasUnset();
      if (_wasUnset) {
        UnsetFeature<EObject, EReference> _createUnsetFeatureChange = TypeInferringAtomicEChangeFactory.getInstance().<EObject, EReference>createUnsetFeatureChange(n.getNotifierModelElement(), n.getReference());
        changes.add(_createUnsetFeatureChange);
      }
      if ((newValue != null)) {
        Iterable<EChange> _handleInsertReference = this.handleInsertReference(n.getNotifierModelElement(), n.getReference(), newValue, n.getPosition());
        Iterables.<EChange>addAll(changes, _handleInsertReference);
      }
      return changes;
    }
  }
  
  private Iterable<EChange> handleUnsetAttribute(final NotificationInfo n, final List<EChange> previousChanges) {
    Iterable<EChange> op = null;
    boolean _isMany = n.getAttribute().isMany();
    boolean _not = (!_isMany);
    if (_not) {
      op = this.handleSetAttribute(n);
    } else {
      Object _feature = n.getFeature();
      final UnsetFeature<EObject, EStructuralFeature> change = TypeInferringAtomicEChangeFactory.getInstance().<EObject, EStructuralFeature>createUnsetFeatureChange(n.getNotifierModelElement(), ((EStructuralFeature) _feature));
      this.eChangeIdManager.setOrGenerateIds(change);
      op = Collections.<EChange>unmodifiableList(CollectionLiterals.<EChange>newArrayList(change));
    }
    return op;
  }
  
  private Iterable<EChange> handleUnsetReference(final NotificationInfo n, final List<EChange> previousChanges) {
    Iterable<EChange> op = null;
    boolean _isMany = n.getReference().isMany();
    boolean _not = (!_isMany);
    if (_not) {
      op = this.handleSetReference(n);
    } else {
      Object _feature = n.getFeature();
      final UnsetFeature<EObject, EStructuralFeature> change = TypeInferringAtomicEChangeFactory.getInstance().<EObject, EStructuralFeature>createUnsetFeatureChange(n.getNotifierModelElement(), ((EStructuralFeature) _feature));
      this.eChangeIdManager.setOrGenerateIds(change);
      op = Collections.<EChange>unmodifiableList(CollectionLiterals.<EChange>newArrayList(change));
    }
    return op;
  }
  
  private Iterable<EChange> recursiveAddition(final EObject eObject) {
    final ArrayList<EChange> result = CollectionLiterals.<EChange>newArrayList();
    this.recursivelyAddChangesForNonDefaultAttributesAndContainments(eObject, result);
    this.recursivelyAddChangesForNonDefaultReferences(eObject, result);
    for (final EChange change : result) {
      this.eChangeIdManager.setOrGenerateIds(change);
    }
    return result;
  }
  
  private Iterable<EChange> recursiveRemoval(final EObject eObject) {
    final ArrayList<EChange> result = CollectionLiterals.<EChange>newArrayList();
    this.recursivelyRemoveChangesForNonDefaultReferences(eObject, result);
    this.recursivelyRemoveChangesForNonDefaultAttributesAndContainments(eObject, result);
    for (final EChange change : result) {
      this.eChangeIdManager.setOrGenerateIds(change);
    }
    return result;
  }
  
  private void recursivelyAddChangesForNonDefaultAttributesAndContainments(final EObject eObject, final List<EChange> eChanges) {
    boolean _hasNonDefaultValue = EMFModelChangeTransformationUtil.hasNonDefaultValue(eObject);
    if (_hasNonDefaultValue) {
      final EClass metaclass = eObject.eClass();
      Iterable<EAttribute> _filter = Iterables.<EAttribute>filter(metaclass.getEAllStructuralFeatures(), EAttribute.class);
      for (final EAttribute feature : _filter) {
        boolean _hasChangeableUnderivedPersistedNotContainingNonDefaultValue = EMFModelChangeTransformationUtil.hasChangeableUnderivedPersistedNotContainingNonDefaultValue(eObject, feature);
        if (_hasChangeableUnderivedPersistedNotContainingNonDefaultValue) {
          final List<? extends EChange> recursiveChanges = EMFModelChangeTransformationUtil.createAdditiveChangesForValue(eObject, feature);
          eChanges.addAll(recursiveChanges);
        }
      }
      final Function1<EReference, Boolean> _function = (EReference it) -> {
        return Boolean.valueOf(it.isContainment());
      };
      Iterable<EReference> _filter_1 = IterableExtensions.<EReference>filter(Iterables.<EReference>filter(metaclass.getEAllStructuralFeatures(), EReference.class), _function);
      for (final EReference feature_1 : _filter_1) {
        boolean _hasChangeableUnderivedPersistedNotContainingNonDefaultValue_1 = EMFModelChangeTransformationUtil.hasChangeableUnderivedPersistedNotContainingNonDefaultValue(eObject, feature_1);
        if (_hasChangeableUnderivedPersistedNotContainingNonDefaultValue_1) {
          final List<EChange> recursiveChanges_1 = EMFModelChangeTransformationUtil.createAdditiveCreateChangesForValue(eObject, feature_1);
          eChanges.addAll(recursiveChanges_1);
          List<? extends EObject> _referencedElements = NotificationToEChangeConverter.getReferencedElements(eObject, feature_1);
          for (final EObject element : _referencedElements) {
            this.recursivelyAddChangesForNonDefaultAttributesAndContainments(element, eChanges);
          }
        }
      }
    }
  }
  
  private void recursivelyRemoveChangesForNonDefaultAttributesAndContainments(final EObject eObject, final List<EChange> eChanges) {
    boolean _hasNonDefaultValue = EMFModelChangeTransformationUtil.hasNonDefaultValue(eObject);
    if (_hasNonDefaultValue) {
      final EClass metaclass = eObject.eClass();
      Iterable<EAttribute> _filter = Iterables.<EAttribute>filter(metaclass.getEAllStructuralFeatures(), EAttribute.class);
      for (final EAttribute feature : _filter) {
        boolean _hasChangeableUnderivedPersistedNotContainingNonDefaultValue = EMFModelChangeTransformationUtil.hasChangeableUnderivedPersistedNotContainingNonDefaultValue(eObject, feature);
        if (_hasChangeableUnderivedPersistedNotContainingNonDefaultValue) {
          final List<? extends EChange> recursiveChanges = EMFModelChangeTransformationUtil.createSubtractiveChangesForValue(eObject, feature);
          eChanges.addAll(recursiveChanges);
        }
      }
      final Function1<EReference, Boolean> _function = (EReference it) -> {
        return Boolean.valueOf(it.isContainment());
      };
      Iterable<EReference> _filter_1 = IterableExtensions.<EReference>filter(Iterables.<EReference>filter(metaclass.getEAllStructuralFeatures(), EReference.class), _function);
      for (final EReference feature_1 : _filter_1) {
        boolean _hasChangeableUnderivedPersistedNotContainingNonDefaultValue_1 = EMFModelChangeTransformationUtil.hasChangeableUnderivedPersistedNotContainingNonDefaultValue(eObject, feature_1);
        if (_hasChangeableUnderivedPersistedNotContainingNonDefaultValue_1) {
          final List<? extends EChange> recursiveChanges_1 = EMFModelChangeTransformationUtil.createSubtractiveChangesForValue(eObject, feature_1);
          eChanges.addAll(recursiveChanges_1);
          List<? extends EObject> _referencedElements = NotificationToEChangeConverter.getReferencedElements(eObject, feature_1);
          for (final EObject element : _referencedElements) {
            this.recursivelyRemoveChangesForNonDefaultAttributesAndContainments(element, eChanges);
          }
        }
      }
    }
  }
  
  private static List<? extends EObject> getReferencedElements(final EObject eObject, final EReference reference) {
    List<? extends EObject> _xifexpression = null;
    boolean _isMany = reference.isMany();
    if (_isMany) {
      Object _eGet = eObject.eGet(reference);
      _xifexpression = ((List<? extends EObject>) _eGet);
    } else {
      Object _eGet_1 = eObject.eGet(reference);
      _xifexpression = Collections.<EObject>unmodifiableList(CollectionLiterals.<EObject>newArrayList(((EObject) _eGet_1)));
    }
    return _xifexpression;
  }
  
  private void recursivelyAddChangesForNonDefaultReferences(final EObject eObject, final List<EChange> eChanges) {
    boolean _hasNonDefaultValue = EMFModelChangeTransformationUtil.hasNonDefaultValue(eObject);
    if (_hasNonDefaultValue) {
      final EClass metaclass = eObject.eClass();
      final Function1<EReference, Boolean> _function = (EReference it) -> {
        boolean _isContainment = it.isContainment();
        return Boolean.valueOf((!_isContainment));
      };
      Iterable<EReference> _filter = IterableExtensions.<EReference>filter(Iterables.<EReference>filter(metaclass.getEAllStructuralFeatures(), EReference.class), _function);
      for (final EReference feature : _filter) {
        boolean _hasChangeableUnderivedPersistedNotContainingNonDefaultValue = EMFModelChangeTransformationUtil.hasChangeableUnderivedPersistedNotContainingNonDefaultValue(eObject, feature);
        if (_hasChangeableUnderivedPersistedNotContainingNonDefaultValue) {
          final List<? extends EChange> recursiveChanges = EMFModelChangeTransformationUtil.createAdditiveChangesForValue(eObject, feature);
          eChanges.addAll(recursiveChanges);
        }
      }
      final Function1<EReference, Boolean> _function_1 = (EReference it) -> {
        return Boolean.valueOf(it.isContainment());
      };
      Iterable<EReference> _filter_1 = IterableExtensions.<EReference>filter(Iterables.<EReference>filter(metaclass.getEAllStructuralFeatures(), EReference.class), _function_1);
      for (final EReference feature_1 : _filter_1) {
        boolean _hasChangeableUnderivedPersistedNotContainingNonDefaultValue_1 = EMFModelChangeTransformationUtil.hasChangeableUnderivedPersistedNotContainingNonDefaultValue(eObject, feature_1);
        if (_hasChangeableUnderivedPersistedNotContainingNonDefaultValue_1) {
          List<? extends EObject> _referencedElements = NotificationToEChangeConverter.getReferencedElements(eObject, feature_1);
          for (final EObject element : _referencedElements) {
            this.recursivelyAddChangesForNonDefaultReferences(element, eChanges);
          }
        }
      }
    }
  }
  
  private void recursivelyRemoveChangesForNonDefaultReferences(final EObject eObject, final List<EChange> eChanges) {
    boolean _hasNonDefaultValue = EMFModelChangeTransformationUtil.hasNonDefaultValue(eObject);
    if (_hasNonDefaultValue) {
      final EClass metaclass = eObject.eClass();
      final Function1<EReference, Boolean> _function = (EReference it) -> {
        boolean _isContainment = it.isContainment();
        return Boolean.valueOf((!_isContainment));
      };
      Iterable<EReference> _filter = IterableExtensions.<EReference>filter(Iterables.<EReference>filter(metaclass.getEAllStructuralFeatures(), EReference.class), _function);
      for (final EReference feature : _filter) {
        boolean _hasChangeableUnderivedPersistedNotContainingNonDefaultValue = EMFModelChangeTransformationUtil.hasChangeableUnderivedPersistedNotContainingNonDefaultValue(eObject, feature);
        if (_hasChangeableUnderivedPersistedNotContainingNonDefaultValue) {
          final List<? extends EChange> recursiveChanges = EMFModelChangeTransformationUtil.createSubtractiveChangesForValue(eObject, feature);
          eChanges.addAll(recursiveChanges);
        }
      }
      final Function1<EReference, Boolean> _function_1 = (EReference it) -> {
        return Boolean.valueOf(it.isContainment());
      };
      Iterable<EReference> _filter_1 = IterableExtensions.<EReference>filter(Iterables.<EReference>filter(metaclass.getEAllStructuralFeatures(), EReference.class), _function_1);
      for (final EReference feature_1 : _filter_1) {
        boolean _hasChangeableUnderivedPersistedNotContainingNonDefaultValue_1 = EMFModelChangeTransformationUtil.hasChangeableUnderivedPersistedNotContainingNonDefaultValue(eObject, feature_1);
        if (_hasChangeableUnderivedPersistedNotContainingNonDefaultValue_1) {
          List<? extends EObject> _referencedElements = NotificationToEChangeConverter.getReferencedElements(eObject, feature_1);
          for (final EObject element : _referencedElements) {
            this.recursivelyRemoveChangesForNonDefaultReferences(element, eChanges);
          }
        }
      }
    }
  }
}
