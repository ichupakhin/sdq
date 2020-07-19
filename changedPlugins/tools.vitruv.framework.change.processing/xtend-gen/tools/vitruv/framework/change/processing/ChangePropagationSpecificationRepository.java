package tools.vitruv.framework.change.processing;

import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import tools.vitruv.framework.change.processing.ChangePropagationSpecification;
import tools.vitruv.framework.change.processing.ChangePropagationSpecificationProvider;
import tools.vitruv.framework.domains.VitruvDomain;

@SuppressWarnings("all")
public class ChangePropagationSpecificationRepository implements ChangePropagationSpecificationProvider {
  private Map<VitruvDomain, List<ChangePropagationSpecification>> metamodelToPropagationSpecifications;
  
  public ChangePropagationSpecificationRepository() {
    HashMap<VitruvDomain, List<ChangePropagationSpecification>> _hashMap = new HashMap<VitruvDomain, List<ChangePropagationSpecification>>();
    this.metamodelToPropagationSpecifications = _hashMap;
  }
  
  @Override
  public List<ChangePropagationSpecification> getChangePropagationSpecifications(final VitruvDomain sourceDomain) {
    final ArrayList<ChangePropagationSpecification> result = new ArrayList<ChangePropagationSpecification>();
    boolean _containsKey = this.metamodelToPropagationSpecifications.containsKey(sourceDomain);
    if (_containsKey) {
      result.addAll(this.metamodelToPropagationSpecifications.get(sourceDomain));
    }
    return result;
  }
  
  public void putChangePropagationSpecification(final ChangePropagationSpecification changePropagationSpecification) {
    final VitruvDomain changedDomain = changePropagationSpecification.getSourceDomain();
    boolean _containsKey = this.metamodelToPropagationSpecifications.containsKey(changedDomain);
    boolean _not = (!_containsKey);
    if (_not) {
      ArrayList<ChangePropagationSpecification> _arrayList = new ArrayList<ChangePropagationSpecification>();
      this.metamodelToPropagationSpecifications.put(changedDomain, _arrayList);
    }
    final List<ChangePropagationSpecification> propagationSpecifications = this.metamodelToPropagationSpecifications.get(changedDomain);
    propagationSpecifications.add(changePropagationSpecification);
  }
  
  @Override
  public Iterator<ChangePropagationSpecification> iterator() {
    return IterableExtensions.<ChangePropagationSpecification>toList(Iterables.<ChangePropagationSpecification>concat(this.metamodelToPropagationSpecifications.values())).iterator();
  }
}
