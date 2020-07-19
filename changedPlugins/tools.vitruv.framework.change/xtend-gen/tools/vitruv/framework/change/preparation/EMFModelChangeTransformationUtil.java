package tools.vitruv.framework.change.preparation;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import edu.kit.ipd.sdq.commons.util.org.eclipse.emf.ecore.EObjectUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.TypeInferringAtomicEChangeFactory;
import tools.vitruv.framework.change.echange.TypeInferringCompoundEChangeFactory;
import tools.vitruv.framework.change.echange.feature.attribute.AdditiveAttributeEChange;
import tools.vitruv.framework.change.echange.feature.attribute.InsertEAttributeValue;
import tools.vitruv.framework.change.echange.feature.attribute.RemoveEAttributeValue;
import tools.vitruv.framework.change.echange.feature.attribute.ReplaceSingleValuedEAttribute;
import tools.vitruv.framework.change.echange.feature.attribute.SubtractiveAttributeEChange;
import tools.vitruv.framework.change.echange.feature.reference.InsertEReference;
import tools.vitruv.framework.change.echange.feature.reference.RemoveEReference;
import tools.vitruv.framework.change.echange.feature.reference.ReplaceSingleValuedEReference;

/**
 * A utility class providing extension methods for transforming change descriptions to change models.
 */
@SuppressWarnings("all")
public class EMFModelChangeTransformationUtil {
  public static List<EChange> createAdditiveCreateChangesForValue(final EObject eObject, final EReference reference) {
    return EMFModelChangeTransformationUtil.createAdditiveEChangeForReferencedObject(eObject, reference, true);
  }
  
  protected static List<EChange> _createSubtractiveChangesForValue(final EObject eObject, final EReference reference) {
    return EMFModelChangeTransformationUtil.createSubtractiveEChangeForReferencedObject(eObject, reference, false);
  }
  
  protected static List<SubtractiveAttributeEChange<?, Object>> _createSubtractiveChangesForValue(final EObject eObject, final EAttribute attribute) {
    return EMFModelChangeTransformationUtil.<EObject>createSubtractiveEChangeForAttribute(eObject, attribute);
  }
  
  protected static List<EChange> _createAdditiveChangesForValue(final EObject eObject, final EReference reference) {
    return EMFModelChangeTransformationUtil.createAdditiveEChangeForReferencedObject(eObject, reference, false);
  }
  
  protected static List<AdditiveAttributeEChange<?, Object>> _createAdditiveChangesForValue(final EObject eObject, final EAttribute attribute) {
    return EMFModelChangeTransformationUtil.<EObject>createAdditiveEChangeForAttribute(eObject, attribute);
  }
  
  public static <A extends EObject> List<AdditiveAttributeEChange<?, Object>> createAdditiveEChangeForAttribute(final A affectedEObject, final EAttribute affectedAttribute) {
    boolean _isMany = affectedAttribute.isMany();
    if (_isMany) {
      final List<?> newValues = EObjectUtil.getFeatureValues(affectedEObject, affectedAttribute);
      final ArrayList<AdditiveAttributeEChange<?, Object>> resultChanges = new ArrayList<AdditiveAttributeEChange<?, Object>>();
      for (int index = 0; (index < newValues.size()); index++) {
        InsertEAttributeValue<A, Object> _createInsertAttributeChange = TypeInferringAtomicEChangeFactory.getInstance().<A, Object>createInsertAttributeChange(affectedEObject, affectedAttribute, index, newValues.get(index));
        resultChanges.add(_createInsertAttributeChange);
      }
      return resultChanges;
    } else {
      final Object oldValue = affectedAttribute.getDefaultValue();
      final Object newValue = EObjectUtil.getFeatureValue(affectedEObject, affectedAttribute);
      ReplaceSingleValuedEAttribute<A, Object> _createReplaceSingleAttributeChange = TypeInferringAtomicEChangeFactory.getInstance().<A, Object>createReplaceSingleAttributeChange(affectedEObject, affectedAttribute, oldValue, newValue);
      return Collections.<AdditiveAttributeEChange<?, Object>>unmodifiableList(CollectionLiterals.<AdditiveAttributeEChange<?, Object>>newArrayList(_createReplaceSingleAttributeChange));
    }
  }
  
  public static <A extends EObject> List<SubtractiveAttributeEChange<?, Object>> createSubtractiveEChangeForAttribute(final A affectedEObject, final EAttribute affectedAttribute) {
    boolean _isMany = affectedAttribute.isMany();
    if (_isMany) {
      final List<?> oldValues = EObjectUtil.getFeatureValues(affectedEObject, affectedAttribute);
      final ArrayList<SubtractiveAttributeEChange<?, Object>> resultChanges = new ArrayList<SubtractiveAttributeEChange<?, Object>>();
      for (int index = (oldValues.size() - 1); (index >= 0); index--) {
        RemoveEAttributeValue<A, Object> _createRemoveAttributeChange = TypeInferringAtomicEChangeFactory.getInstance().<A, Object>createRemoveAttributeChange(affectedEObject, affectedAttribute, index, oldValues.get(index));
        resultChanges.add(_createRemoveAttributeChange);
      }
      return resultChanges;
    } else {
      final Object oldValue = EObjectUtil.getFeatureValue(affectedEObject, affectedAttribute);
      final Object newValue = affectedAttribute.getDefaultValue();
      ReplaceSingleValuedEAttribute<A, Object> _createReplaceSingleAttributeChange = TypeInferringAtomicEChangeFactory.getInstance().<A, Object>createReplaceSingleAttributeChange(affectedEObject, affectedAttribute, oldValue, newValue);
      return Collections.<SubtractiveAttributeEChange<?, Object>>unmodifiableList(CollectionLiterals.<SubtractiveAttributeEChange<?, Object>>newArrayList(_createReplaceSingleAttributeChange));
    }
  }
  
  public static EList<EObject> getReferenceValueList(final EObject eObject, final EReference reference) {
    EList<?> _valueList = EObjectUtil.getValueList(eObject, reference);
    return ((EList<EObject>) _valueList);
  }
  
  public static EList<Object> getReferenceValueList(final EObject eObject, final EAttribute attribute) {
    EList<?> _valueList = EObjectUtil.getValueList(eObject, attribute);
    return ((EList<Object>) _valueList);
  }
  
  public static List<EChange> createAdditiveEChangeForReferencedObject(final EObject referencingEObject, final EReference reference, final boolean forceCreate) {
    final ArrayList<EChange> result = new ArrayList<EChange>();
    boolean _isMany = reference.isMany();
    if (_isMany) {
      EList<EObject> _referenceValueList = EMFModelChangeTransformationUtil.getReferenceValueList(referencingEObject, reference);
      for (final EObject referenceValue : _referenceValueList) {
        Object _eGet = referencingEObject.eGet(reference);
        List<EChange> _createInsertReferenceChange = EMFModelChangeTransformationUtil.createInsertReferenceChange(referencingEObject, reference, ((EList<?>) _eGet).indexOf(referenceValue), referenceValue, forceCreate);
        Iterables.<EChange>addAll(result, _createInsertReferenceChange);
      }
    } else {
      List<EChange> _createReplaceSingleValuedReferenceChange = EMFModelChangeTransformationUtil.createReplaceSingleValuedReferenceChange(referencingEObject, reference, null, EMFModelChangeTransformationUtil.getReferenceValueList(referencingEObject, reference).get(0), forceCreate);
      Iterables.<EChange>addAll(result, _createReplaceSingleValuedReferenceChange);
    }
    return result;
  }
  
  public static List<EChange> createSubtractiveEChangeForReferencedObject(final EObject referencingEObject, final EReference reference, final boolean forceDelete) {
    final ArrayList<EChange> result = new ArrayList<EChange>();
    boolean _isMany = reference.isMany();
    if (_isMany) {
      List<EObject> _reverseView = ListExtensions.<EObject>reverseView(EMFModelChangeTransformationUtil.getReferenceValueList(referencingEObject, reference));
      for (final EObject referenceValue : _reverseView) {
        Object _eGet = referencingEObject.eGet(reference);
        List<EChange> _createRemoveReferenceChange = EMFModelChangeTransformationUtil.createRemoveReferenceChange(referencingEObject, reference, ((EList<?>) _eGet).indexOf(referenceValue), referenceValue, null, null, forceDelete);
        Iterables.<EChange>addAll(result, _createRemoveReferenceChange);
      }
    } else {
      List<EChange> _createReplaceSingleValuedReferenceChange = EMFModelChangeTransformationUtil.createReplaceSingleValuedReferenceChange(referencingEObject, reference, EMFModelChangeTransformationUtil.getReferenceValueList(referencingEObject, reference).get(0), null, forceDelete);
      Iterables.<EChange>addAll(result, _createReplaceSingleValuedReferenceChange);
    }
    return result;
  }
  
  public static boolean hasNonDefaultValue(final EObject eObject) {
    boolean hasNonDefaultValue = false;
    EList<EStructuralFeature> _eAllStructuralFeatures = eObject.eClass().getEAllStructuralFeatures();
    for (final EStructuralFeature feature : _eAllStructuralFeatures) {
      boolean _isChangeableUnderivedPersistedNotContainingFeature = EMFModelChangeTransformationUtil.isChangeableUnderivedPersistedNotContainingFeature(eObject, feature);
      if (_isChangeableUnderivedPersistedNotContainingFeature) {
        hasNonDefaultValue = (hasNonDefaultValue || EMFModelChangeTransformationUtil.valueIsNonDefault(eObject, feature));
      }
    }
    return hasNonDefaultValue;
  }
  
  private static boolean isChangeableUnderivedPersistedNotContainingFeature(final EObject eObject, final EStructuralFeature feature) {
    return (((feature.isChangeable() && (!feature.isDerived())) && (!feature.isTransient())) && (!Objects.equal(eObject.eContainer(), eObject.eGet(feature))));
  }
  
  private static boolean valueIsNonDefault(final EObject eObject, final EStructuralFeature feature) {
    final Object value = eObject.eGet(feature);
    boolean _isMany = feature.isMany();
    if (_isMany) {
      final List<?> list = ((List<?>) value);
      return ((list != null) && (!list.isEmpty()));
    } else {
      Object _defaultValue = feature.getDefaultValue();
      return (!Objects.equal(value, _defaultValue));
    }
  }
  
  public static boolean hasChangeableUnderivedPersistedNotContainingNonDefaultValue(final EObject eObject, final EStructuralFeature feature) {
    return (EMFModelChangeTransformationUtil.isChangeableUnderivedPersistedNotContainingFeature(eObject, feature) && EMFModelChangeTransformationUtil.valueIsNonDefault(eObject, feature));
  }
  
  public static boolean isDelete(final EObject newContainer, final Resource newResource) {
    return (((newContainer == null) || (newContainer instanceof ChangeDescription)) && (newResource == null));
  }
  
  public static List<EChange> createInsertReferenceChange(final EObject affectedEObject, final EReference affectedReference, final int index, final EObject referenceValue, final boolean forceCreate) {
    final boolean isContainment = affectedReference.isContainment();
    final Resource oldResource = referenceValue.eResource();
    final boolean isCreate = (forceCreate || (isContainment && (oldResource == null)));
    if (isCreate) {
      return TypeInferringCompoundEChangeFactory.getInstance().<EObject, EObject>createCreateAndInsertNonRootChange(affectedEObject, affectedReference, referenceValue, index);
    } else {
      InsertEReference<EObject, EObject> _createInsertReferenceChange = TypeInferringAtomicEChangeFactory.getInstance().<EObject, EObject>createInsertReferenceChange(affectedEObject, affectedReference, referenceValue, index);
      return Collections.<EChange>unmodifiableList(CollectionLiterals.<EChange>newArrayList(_createInsertReferenceChange));
    }
  }
  
  public static List<EChange> createRemoveReferenceChange(final EObject affectedEObject, final EReference affectedReference, final int index, final EObject referenceValue, final EObject newContainer, final Resource newResource, final boolean forceDelete) {
    final boolean isContainment = affectedReference.isContainment();
    final boolean isDelete = (forceDelete || (isContainment && EMFModelChangeTransformationUtil.isDelete(newContainer, newResource)));
    if (isDelete) {
      return TypeInferringCompoundEChangeFactory.getInstance().<EObject, EObject>createRemoveAndDeleteNonRootChange(affectedEObject, affectedReference, referenceValue, index);
    } else {
      RemoveEReference<EObject, EObject> _createRemoveReferenceChange = TypeInferringAtomicEChangeFactory.getInstance().<EObject, EObject>createRemoveReferenceChange(affectedEObject, affectedReference, referenceValue, index);
      return Collections.<EChange>unmodifiableList(CollectionLiterals.<EChange>newArrayList(_createRemoveReferenceChange));
    }
  }
  
  public static List<EChange> createReplaceSingleValuedReferenceChange(final EObject affectedEObject, final EReference affectedReference, final EObject oldReferenceValue, final EObject newReferenceValue, final boolean forceCreate) {
    final boolean isContainment = affectedReference.isContainment();
    if ((forceCreate || isContainment)) {
      if ((oldReferenceValue == null)) {
        return TypeInferringCompoundEChangeFactory.getInstance().<EObject, EObject>createCreateAndReplaceNonRootChange(affectedEObject, affectedReference, newReferenceValue);
      } else {
        if ((newReferenceValue == null)) {
          return TypeInferringCompoundEChangeFactory.getInstance().<EObject, EObject>createReplaceAndDeleteNonRootChange(affectedEObject, affectedReference, oldReferenceValue);
        } else {
          return TypeInferringCompoundEChangeFactory.getInstance().<EObject, EObject>createCreateAndReplaceAndDeleteNonRootChange(affectedEObject, affectedReference, oldReferenceValue, newReferenceValue);
        }
      }
    } else {
      ReplaceSingleValuedEReference<EObject, EObject> _createReplaceSingleReferenceChange = TypeInferringAtomicEChangeFactory.getInstance().<EObject, EObject>createReplaceSingleReferenceChange(affectedEObject, affectedReference, oldReferenceValue, newReferenceValue);
      return Collections.<EChange>unmodifiableList(CollectionLiterals.<EChange>newArrayList(_createReplaceSingleReferenceChange));
    }
  }
  
  public static List<? extends EChange> createSubtractiveChangesForValue(final EObject eObject, final EStructuralFeature attribute) {
    if (attribute instanceof EAttribute) {
      return _createSubtractiveChangesForValue(eObject, (EAttribute)attribute);
    } else if (attribute instanceof EReference) {
      return _createSubtractiveChangesForValue(eObject, (EReference)attribute);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(eObject, attribute).toString());
    }
  }
  
  public static List<? extends EChange> createAdditiveChangesForValue(final EObject eObject, final EStructuralFeature attribute) {
    if (attribute instanceof EAttribute) {
      return _createAdditiveChangesForValue(eObject, (EAttribute)attribute);
    } else if (attribute instanceof EReference) {
      return _createAdditiveChangesForValue(eObject, (EReference)attribute);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(eObject, attribute).toString());
    }
  }
}
