package tools.vitruv.framework.change.description.impl;

import tools.vitruv.framework.change.description.impl.ConcreteChangeImpl;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.util.datatypes.VURI;

@SuppressWarnings("all")
public class ConcreteChangeWithUriImpl extends ConcreteChangeImpl {
  private final VURI vuri;
  
  public ConcreteChangeWithUriImpl(final VURI vuri, final EChange eChange) {
    super(eChange);
    this.vuri = vuri;
  }
  
  @Override
  public VURI getURI() {
    return this.vuri;
  }
}
