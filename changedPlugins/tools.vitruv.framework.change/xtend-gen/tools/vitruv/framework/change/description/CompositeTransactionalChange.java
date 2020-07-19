package tools.vitruv.framework.change.description;

import tools.vitruv.framework.change.description.CompositeChange;
import tools.vitruv.framework.change.description.TransactionalChange;

/**
 * A {@link CompositeTransactionalChange} defines a set of {@link TransactionalChange}s, which have to be performed
 * together. They were recorded together and have to propagated to models completely or not at all.
 */
@SuppressWarnings("all")
public interface CompositeTransactionalChange extends TransactionalChange, CompositeChange<TransactionalChange> {
}
