package tools.vitruv.framework.change.description;

import java.util.List;
import org.eclipse.emf.ecore.EObject;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.interaction.UserInteractionBase;
import tools.vitruv.framework.util.datatypes.URIHaving;
import tools.vitruv.framework.uuid.UuidResolver;

/**
 * Base interface for all kinds of changes in Vitruvius.
 * 
 * @author Heiko Klare
 */
@SuppressWarnings("all")
public interface VitruviusChange extends URIHaving {
  /**
   * Returns whether the change contains any concrete change or consists only of composite ones.
   */
  boolean containsConcreteChange();
  
  /**
   * Validates the change by checking if at least one concrete change is contained and
   * the URIs of all contained changes are same.
   */
  boolean validate();
  
  /**
   * Returns the {@link EChange}s that describe this change. Requires the change to be prepared so
   * that the original change information is transformed into {@link EChange}s.
   */
  List<EChange> getEChanges();
  
  /**
   * Resolves the change and applies it forward so that the model is in the state after the change afterwards.
   * It has to be ensured that the model is in a state the change can be applied to before calling this method.
   * If the change cannot be resolved or is already resolved, an Exception is thrown.
   */
  void resolveBeforeAndApplyForward(final UuidResolver uuidResolver);
  
  /**
   * Resolves the change and applies it backward so that the model is in the state before the change afterwards.
   * It has to be ensured that the model is in a state the change can be applied to before calling this method.
   * If the change cannot be resolved or is already resolved, an Exception is thrown.
   */
  void resolveAfterAndApplyBackward(final UuidResolver uuidResolver);
  
  /**
   * Unresolves the change if it can be applied to a resource. If it is not applicable, it will not be unresolved
   * (as a new resolution is impossible).
   */
  void unresolveIfApplicable();
  
  /**
   * Returns all {@link EObject}s affected by this change, including both the elements of which an attribute or
   * reference was changes, as well as the referenced elements.
   */
  Iterable<EObject> getAffectedEObjects();
  
  /**
   * Returns the IDs of all {@link EObject}s affected by this change, including both the elements of which an attribute or
   * reference was changes, as well as the referenced elements.
   */
  Iterable<String> getAffectedEObjectIds();
  
  /**
   * Returns all user interactions performed during application of this change and performing consistency preservation.
   */
  Iterable<UserInteractionBase> getUserInteractions();
  
  /**
   * Checks whether the affected EObjects of this change are equal to the EObjects of the other change.
   */
  boolean changedEObjectEquals(final VitruviusChange change);
}
