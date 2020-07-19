package tools.vitruv.framework.change.processing;

import java.util.List;
import tools.vitruv.framework.change.processing.ChangePropagationSpecification;
import tools.vitruv.framework.domains.VitruvDomain;

@SuppressWarnings("all")
public interface ChangePropagationSpecificationProvider extends Iterable<ChangePropagationSpecification> {
  List<ChangePropagationSpecification> getChangePropagationSpecifications(final VitruvDomain sourceDomain);
}
