package tools.vitruv.framework.change.description;

import java.util.Arrays;
import java.util.function.Consumer;
import org.eclipse.emf.ecore.util.EcoreUtil;
import tools.vitruv.framework.change.description.TransactionalChange;
import tools.vitruv.framework.change.description.VitruviusChange;
import tools.vitruv.framework.change.description.impl.CompositeContainerChangeImpl;
import tools.vitruv.framework.change.description.impl.CompositeTransactionalChangeImpl;
import tools.vitruv.framework.change.description.impl.ConcreteApplicableChangeImpl;
import tools.vitruv.framework.change.description.impl.ConcreteChangeImpl;
import tools.vitruv.framework.change.description.impl.EmptyChangeImpl;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.util.datatypes.VURI;

@SuppressWarnings("all")
public class ChangeCloner {
  protected VitruviusChange _clone(final CompositeContainerChangeImpl containerChange) {
    final CompositeContainerChangeImpl clone = new CompositeContainerChangeImpl();
    final Consumer<VitruviusChange> _function = (VitruviusChange it) -> {
      VitruviusChange _clone = this.clone(it);
      clone.addChange(((VitruviusChange) _clone));
    };
    containerChange.getChanges().forEach(_function);
    return clone;
  }
  
  protected VitruviusChange _clone(final CompositeTransactionalChangeImpl transactionalChange) {
    final CompositeTransactionalChangeImpl clone = new CompositeTransactionalChangeImpl();
    final Consumer<TransactionalChange> _function = (TransactionalChange it) -> {
      VitruviusChange _clone = this.clone(it);
      clone.addChange(((TransactionalChange) _clone));
    };
    transactionalChange.getChanges().forEach(_function);
    return clone;
  }
  
  protected VitruviusChange _clone(final ConcreteApplicableChangeImpl applicableChange) {
    EChange _cloneEChange = this.cloneEChange(applicableChange.getEChange());
    return new ConcreteApplicableChangeImpl(_cloneEChange);
  }
  
  protected VitruviusChange _clone(final ConcreteChangeImpl concreteChange) {
    EChange _cloneEChange = this.cloneEChange(concreteChange.getEChange());
    return new ConcreteChangeImpl(_cloneEChange);
  }
  
  protected VitruviusChange _clone(final EmptyChangeImpl emptyChange) {
    VURI _uRI = emptyChange.getURI();
    return new EmptyChangeImpl(_uRI);
  }
  
  public EChange cloneEChange(final EChange change) {
    return EcoreUtil.<EChange>copy(change);
  }
  
  public VitruviusChange clone(final VitruviusChange applicableChange) {
    if (applicableChange instanceof ConcreteApplicableChangeImpl) {
      return _clone((ConcreteApplicableChangeImpl)applicableChange);
    } else if (applicableChange instanceof ConcreteChangeImpl) {
      return _clone((ConcreteChangeImpl)applicableChange);
    } else if (applicableChange instanceof CompositeContainerChangeImpl) {
      return _clone((CompositeContainerChangeImpl)applicableChange);
    } else if (applicableChange instanceof CompositeTransactionalChangeImpl) {
      return _clone((CompositeTransactionalChangeImpl)applicableChange);
    } else if (applicableChange instanceof EmptyChangeImpl) {
      return _clone((EmptyChangeImpl)applicableChange);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(applicableChange).toString());
    }
  }
}
