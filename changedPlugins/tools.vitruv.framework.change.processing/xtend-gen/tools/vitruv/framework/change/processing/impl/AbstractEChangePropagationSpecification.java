package tools.vitruv.framework.change.processing.impl;

import java.util.List;
import org.apache.log4j.Logger;
import org.eclipse.xtend2.lib.StringConcatenation;
import tools.vitruv.framework.change.description.TransactionalChange;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.processing.impl.AbstractChangePropagationSpecification;
import tools.vitruv.framework.correspondence.CorrespondenceModel;
import tools.vitruv.framework.domains.VitruvDomain;
import tools.vitruv.framework.util.command.ResourceAccess;

@SuppressWarnings("all")
public abstract class AbstractEChangePropagationSpecification extends AbstractChangePropagationSpecification {
  private static final Logger LOGGER = Logger.getLogger(AbstractEChangePropagationSpecification.class);
  
  public AbstractEChangePropagationSpecification(final VitruvDomain sourceDomain, final VitruvDomain targetDomain) {
    super(sourceDomain, targetDomain);
  }
  
  @Override
  public boolean doesHandleChange(final TransactionalChange change, final CorrespondenceModel correspondenceModel) {
    List<EChange> _eChanges = change.getEChanges();
    for (final EChange eChange : _eChanges) {
      boolean _doesHandleChange = this.doesHandleChange(eChange, correspondenceModel);
      if (_doesHandleChange) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public void propagateChange(final TransactionalChange change, final CorrespondenceModel correspondenceModel, final ResourceAccess resourceAccess) {
    List<EChange> _eChanges = change.getEChanges();
    for (final EChange eChange : _eChanges) {
      {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("Transforming eChange  ");
        _builder.append(eChange);
        _builder.append(" of change ");
        _builder.append(change);
        AbstractEChangePropagationSpecification.LOGGER.debug(_builder);
        this.propagateChange(eChange, correspondenceModel, resourceAccess);
      }
    }
  }
  
  protected abstract boolean doesHandleChange(final EChange change, final CorrespondenceModel correspondenceModel);
  
  protected abstract void propagateChange(final EChange change, final CorrespondenceModel correspondenceModel, final ResourceAccess resourceAccess);
}
