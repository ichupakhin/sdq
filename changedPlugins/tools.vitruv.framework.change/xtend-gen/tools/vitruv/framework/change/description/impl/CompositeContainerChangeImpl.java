package tools.vitruv.framework.change.description.impl;

import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import tools.vitruv.framework.change.description.CompositeContainerChange;
import tools.vitruv.framework.change.description.VitruviusChange;
import tools.vitruv.framework.change.description.impl.AbstractCompositeChangeImpl;

@SuppressWarnings("all")
public class CompositeContainerChangeImpl extends AbstractCompositeChangeImpl<VitruviusChange> implements CompositeContainerChange {
  @Override
  public String toString() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Composite container change:");
    _builder.newLine();
    {
      List<VitruviusChange> _changes = this.getChanges();
      for(final VitruviusChange change : _changes) {
        _builder.append("\t");
        _builder.append(change, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder.toString();
  }
}
