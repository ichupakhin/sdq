package tools.vitruv.framework.change.description.impl;

import tools.vitruv.framework.change.description.impl.AbstractConcreteChange;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.util.datatypes.VURI;

@SuppressWarnings("all")
public class ConcreteChangeImpl extends AbstractConcreteChange {
  public ConcreteChangeImpl(final EChange eChange) {
    super(eChange);
  }
  
  @Override
  public String toString() {
    String _simpleName = this.getClass().getSimpleName();
    String _plus = (_simpleName + ", VURI: ");
    VURI _uRI = this.getURI();
    String _plus_1 = (_plus + _uRI);
    String _plus_2 = (_plus_1 + "\n\tEChange: ");
    EChange _eChange = this.getEChange();
    return (_plus_2 + _eChange);
  }
}
