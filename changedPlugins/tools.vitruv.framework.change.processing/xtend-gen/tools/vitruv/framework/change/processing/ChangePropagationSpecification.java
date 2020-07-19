package tools.vitruv.framework.change.processing;

import tools.vitruv.framework.change.description.TransactionalChange;
import tools.vitruv.framework.change.processing.ChangePropagationObservable;
import tools.vitruv.framework.correspondence.CorrespondenceModel;
import tools.vitruv.framework.domains.VitruvDomain;
import tools.vitruv.framework.userinteraction.UserInteractor;
import tools.vitruv.framework.util.command.ResourceAccess;

@SuppressWarnings("all")
public interface ChangePropagationSpecification extends ChangePropagationObservable {
  void setUserInteractor(final UserInteractor userInteractor);
  
  VitruvDomain getSourceDomain();
  
  VitruvDomain getTargetDomain();
  
  boolean doesHandleChange(final TransactionalChange change, final CorrespondenceModel correspondenceModel);
  
  void propagateChange(final TransactionalChange change, final CorrespondenceModel correspondenceModel, final ResourceAccess resourceAccess);
}
