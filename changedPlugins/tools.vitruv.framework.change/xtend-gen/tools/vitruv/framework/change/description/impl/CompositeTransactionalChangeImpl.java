package tools.vitruv.framework.change.description.impl;

import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import tools.vitruv.framework.change.description.CompositeTransactionalChange;
import tools.vitruv.framework.change.description.TransactionalChange;
import tools.vitruv.framework.change.description.VitruviusChangeFactory;
import tools.vitruv.framework.change.description.impl.AbstractCompositeChangeImpl;
import tools.vitruv.framework.change.interaction.UserInteractionBase;
import tools.vitruv.framework.uuid.UuidResolver;

@SuppressWarnings("all")
public class CompositeTransactionalChangeImpl extends AbstractCompositeChangeImpl<TransactionalChange> implements CompositeTransactionalChange {
  private List<UserInteractionBase> userInteractions = new ArrayList<UserInteractionBase>();
  
  @Override
  public void removeChange(final TransactionalChange change) {
    if (((change != null) && this.getChanges().contains(change))) {
      int _size = this.getChanges().size();
      boolean _equals = (_size == 1);
      if (_equals) {
        final TransactionalChange emptyChange = VitruviusChangeFactory.getInstance().createEmptyChange(change.getURI());
        List<TransactionalChange> _changes = this.getChanges();
        _changes.add(emptyChange);
      }
    }
    super.removeChange(change);
  }
  
  @Override
  public void resolveBeforeAndApplyForward(final UuidResolver uuidResolver) {
    List<TransactionalChange> _changes = this.getChanges();
    for (final TransactionalChange c : _changes) {
      c.resolveBeforeAndApplyForward(uuidResolver);
    }
  }
  
  @Override
  public Iterable<UserInteractionBase> getUserInteractions() {
    final Iterable<UserInteractionBase> subInteraction = this.getSubUserInteractions();
    boolean _isEmpty = IterableExtensions.isEmpty(subInteraction);
    boolean _not = (!_isEmpty);
    if (_not) {
      boolean _isEmpty_1 = this.userInteractions.isEmpty();
      boolean _not_1 = (!_isEmpty_1);
      if (_not_1) {
        throw new IllegalStateException("User interactions were defined for both the container change and the contained changes");
      }
      return subInteraction;
    }
    return this.userInteractions;
  }
  
  private Iterable<UserInteractionBase> getSubUserInteractions() {
    final Function1<TransactionalChange, Iterable<UserInteractionBase>> _function = (TransactionalChange it) -> {
      return it.getUserInteractions();
    };
    return Iterables.<UserInteractionBase>concat(ListExtensions.<TransactionalChange, Iterable<UserInteractionBase>>map(this.getChanges(), _function));
  }
  
  @Override
  public void setUserInteractions(final Iterable<UserInteractionBase> userInputs) {
    if ((userInputs == null)) {
      throw new IllegalArgumentException("User interactions must not be null");
    }
    boolean _isEmpty = IterableExtensions.isEmpty(this.getSubUserInteractions());
    boolean _not = (!_isEmpty);
    if (_not) {
      throw new IllegalStateException("User interactions must only be defined for a container change or its contained changes");
    }
    this.userInteractions.clear();
    Iterables.<UserInteractionBase>addAll(this.userInteractions, userInputs);
  }
}
