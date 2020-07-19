package tools.vitruv.framework.change.description;

import tools.vitruv.framework.change.description.TransactionalChange;
import tools.vitruv.framework.change.echange.EChange;

@SuppressWarnings("all")
public interface ConcreteChange extends TransactionalChange {
  EChange getEChange();
}
