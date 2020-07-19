package tools.vitruv.framework.change.processing.impl;

import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.eclipse.xtend2.lib.StringConcatenation;
import tools.vitruv.framework.change.description.CompositeTransactionalChange;
import tools.vitruv.framework.change.description.TransactionalChange;
import tools.vitruv.framework.change.processing.ChangePropagationSpecification;
import tools.vitruv.framework.change.processing.impl.CompositeChangePropagationSpecification;
import tools.vitruv.framework.correspondence.CorrespondenceModel;
import tools.vitruv.framework.domains.VitruvDomain;
import tools.vitruv.framework.util.command.ResourceAccess;

/**
 * This {@link ChangePropagationSpecification} acts just like the generic {@link CompositeChangePropagationSpecification}
 * but disassembles {@link CompositeTransactionalChanges} into their inner changes and applies the main change processors to each of them
 * instead of applying the main change processors to the complete {@link CompositeTransactionalChange}.
 */
@SuppressWarnings("all")
public class CompositeDecomposingChangePropagationSpecification extends CompositeChangePropagationSpecification {
  private static final Logger logger = Logger.getLogger(CompositeDecomposingChangePropagationSpecification.class);
  
  public CompositeDecomposingChangePropagationSpecification(final VitruvDomain sourceDomain, final VitruvDomain targetDomain) {
    super(sourceDomain, targetDomain);
  }
  
  @Override
  public void propagateChangeViaMainprocessors(final TransactionalChange change, final CorrespondenceModel correspondenceModel, final ResourceAccess resourceAccess) {
    this.propagateDecomposedChange(change, correspondenceModel, resourceAccess);
  }
  
  private void _propagateDecomposedChange(final TransactionalChange change, final CorrespondenceModel correspondenceModel, final ResourceAccess resourceAccess) {
    List<ChangePropagationSpecification> _changeMainprocessors = this.getChangeMainprocessors();
    for (final ChangePropagationSpecification changeProcessor : _changeMainprocessors) {
      {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("Calling change mainprocessor ");
        _builder.append(changeProcessor);
        _builder.append(" for change event ");
        _builder.append(change);
        CompositeDecomposingChangePropagationSpecification.logger.debug(_builder);
        changeProcessor.propagateChange(change, correspondenceModel, resourceAccess);
      }
    }
  }
  
  private void _propagateDecomposedChange(final CompositeTransactionalChange change, final CorrespondenceModel correspondenceModel, final ResourceAccess resourceAccess) {
    List<TransactionalChange> _changes = change.getChanges();
    for (final TransactionalChange innerChange : _changes) {
      this.propagateDecomposedChange(innerChange, correspondenceModel, resourceAccess);
    }
  }
  
  private void propagateDecomposedChange(final TransactionalChange change, final CorrespondenceModel correspondenceModel, final ResourceAccess resourceAccess) {
    if (change instanceof CompositeTransactionalChange) {
      _propagateDecomposedChange((CompositeTransactionalChange)change, correspondenceModel, resourceAccess);
      return;
    } else if (change != null) {
      _propagateDecomposedChange(change, correspondenceModel, resourceAccess);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(change, correspondenceModel, resourceAccess).toString());
    }
  }
}
