package tools.vitruv.framework.change.description;

import java.util.List;
import tools.vitruv.framework.change.description.VitruviusChange;

@SuppressWarnings("all")
public interface CompositeChange<C extends VitruviusChange> extends VitruviusChange {
  List<C> getChanges();
  
  void addChange(final C change);
  
  void removeChange(final C change);
}
