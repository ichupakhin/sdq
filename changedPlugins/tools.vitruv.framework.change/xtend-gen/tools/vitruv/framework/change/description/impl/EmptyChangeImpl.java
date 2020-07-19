package tools.vitruv.framework.change.description.impl;

import java.util.Collections;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import tools.vitruv.framework.change.description.TransactionalChange;
import tools.vitruv.framework.change.description.VitruviusChange;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.interaction.UserInteractionBase;
import tools.vitruv.framework.util.datatypes.VURI;
import tools.vitruv.framework.uuid.UuidResolver;

@SuppressWarnings("all")
public class EmptyChangeImpl implements TransactionalChange {
  private final VURI vuri;
  
  public EmptyChangeImpl(final VURI vuri) {
    this.vuri = vuri;
  }
  
  @Override
  public boolean containsConcreteChange() {
    return true;
  }
  
  @Override
  public boolean validate() {
    return true;
  }
  
  @Override
  public List<EChange> getEChanges() {
    return Collections.<EChange>unmodifiableList(CollectionLiterals.<EChange>newArrayList());
  }
  
  @Override
  public VURI getURI() {
    return this.vuri;
  }
  
  @Override
  public void resolveBeforeAndApplyForward(final UuidResolver uuidResolver) {
  }
  
  @Override
  public void resolveAfterAndApplyBackward(final UuidResolver uuidResolver) {
  }
  
  @Override
  public void unresolveIfApplicable() {
  }
  
  @Override
  public Iterable<EObject> getAffectedEObjects() {
    return Collections.<EObject>unmodifiableList(CollectionLiterals.<EObject>newArrayList());
  }
  
  @Override
  public Iterable<String> getAffectedEObjectIds() {
    return Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList());
  }
  
  @Override
  public Iterable<UserInteractionBase> getUserInteractions() {
    return Collections.<UserInteractionBase>unmodifiableList(CollectionLiterals.<UserInteractionBase>newArrayList());
  }
  
  @Override
  public void setUserInteractions(final Iterable<UserInteractionBase> userInputs) {
    throw new UnsupportedOperationException("Adding interactions to an empty change is not supported");
  }
  
  @Override
  public boolean changedEObjectEquals(final VitruviusChange change) {
    return IterableExtensions.isEmpty(change.getAffectedEObjects());
  }
}
