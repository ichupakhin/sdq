package tools.vitruv.framework.change.description;

import tools.vitruv.framework.change.description.CompositeChange;
import tools.vitruv.framework.change.description.VitruviusChange;

/**
 * A {@link CompositeContainerChange} defines a container for {@link VitruviusChange}s.
 * It does not have any specific semantics but containing one or more {@link VitruviusChange}s.
 */
@SuppressWarnings("all")
public interface CompositeContainerChange extends CompositeChange<VitruviusChange> {
}
