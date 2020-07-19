package tools.vitruv.framework.change.description;

import com.google.common.base.Objects;
import java.util.List;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import tools.vitruv.framework.change.description.ChangeCloner;
import tools.vitruv.framework.change.description.CompositeContainerChange;
import tools.vitruv.framework.change.description.CompositeTransactionalChange;
import tools.vitruv.framework.change.description.ConcreteChange;
import tools.vitruv.framework.change.description.TransactionalChange;
import tools.vitruv.framework.change.description.VitruviusChange;
import tools.vitruv.framework.change.description.impl.CompositeContainerChangeImpl;
import tools.vitruv.framework.change.description.impl.CompositeTransactionalChangeImpl;
import tools.vitruv.framework.change.description.impl.ConcreteApplicableChangeImpl;
import tools.vitruv.framework.change.description.impl.ConcreteChangeImpl;
import tools.vitruv.framework.change.description.impl.ConcreteChangeWithUriImpl;
import tools.vitruv.framework.change.description.impl.EmptyChangeImpl;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.TypeInferringCompoundEChangeFactory;
import tools.vitruv.framework.util.datatypes.VURI;

@SuppressWarnings("all")
public class VitruviusChangeFactory {
  public enum FileChangeKind {
    Create,
    
    Delete;
  }
  
  private static final Logger logger = Logger.getLogger(VitruviusChangeFactory.class);
  
  private static VitruviusChangeFactory instance;
  
  private VitruviusChangeFactory() {
  }
  
  public static VitruviusChangeFactory getInstance() {
    if ((VitruviusChangeFactory.instance == null)) {
      VitruviusChangeFactory _vitruviusChangeFactory = new VitruviusChangeFactory();
      VitruviusChangeFactory.instance = _vitruviusChangeFactory;
    }
    return VitruviusChangeFactory.instance;
  }
  
  public ConcreteChange createConcreteApplicableChange(final EChange change) {
    return new ConcreteApplicableChangeImpl(change);
  }
  
  public ConcreteChange createConcreteChange(final EChange change) {
    return new ConcreteChangeImpl(change);
  }
  
  public ConcreteChange createConcreteChangeWithVuri(final EChange change, final VURI vuri) {
    return new ConcreteChangeWithUriImpl(vuri, change);
  }
  
  public List<ConcreteChange> createFileChange(final VitruviusChangeFactory.FileChangeKind kind, final Resource changedFileResource) {
    boolean _equals = Objects.equal(kind, VitruviusChangeFactory.FileChangeKind.Create);
    if (_equals) {
      final Function1<EChange, ConcreteChange> _function = (EChange it) -> {
        return new ConcreteChangeImpl(it);
      };
      return ListExtensions.<EChange, ConcreteChange>map(this.generateFileCreateChange(changedFileResource), _function);
    } else {
      final Function1<EChange, ConcreteChange> _function_1 = (EChange it) -> {
        return new ConcreteChangeImpl(it);
      };
      return ListExtensions.<EChange, ConcreteChange>map(this.generateFileDeleteChange(changedFileResource), _function_1);
    }
  }
  
  public CompositeContainerChange createCompositeContainerChange() {
    return new CompositeContainerChangeImpl();
  }
  
  public CompositeTransactionalChange createCompositeTransactionalChange() {
    return new CompositeTransactionalChangeImpl();
  }
  
  public TransactionalChange createEmptyChange(final VURI vuri) {
    return new EmptyChangeImpl(vuri);
  }
  
  public CompositeContainerChange createCompositeChange(final Iterable<? extends VitruviusChange> innerChanges) {
    final CompositeContainerChangeImpl compositeChange = new CompositeContainerChangeImpl();
    for (final VitruviusChange innerChange : innerChanges) {
      compositeChange.addChange(innerChange);
    }
    return compositeChange;
  }
  
  public <T extends VitruviusChange> T clone(final T originalChange) {
    VitruviusChange _clone = new ChangeCloner().clone(originalChange);
    return ((T) _clone);
  }
  
  private List<EChange> generateFileCreateChange(final Resource resource) {
    EObject rootElement = null;
    int index = 0;
    int _size = resource.getContents().size();
    boolean _equals = (1 == _size);
    if (_equals) {
      rootElement = resource.getContents().get(0);
    } else {
      int _size_1 = resource.getContents().size();
      boolean _lessThan = (1 < _size_1);
      if (_lessThan) {
        throw new RuntimeException(
          ((("The requested model instance resource \'" + resource) + "\' has to contain at most one root element ") + "in order to be added to the VSUM without an explicit import!"));
      } else {
        VURI _instance = VURI.getInstance(resource);
        String _plus = ("Empty model file created: " + _instance);
        String _plus_1 = (_plus + ". Propagation of \'root element created\' not triggered.");
        VitruviusChangeFactory.logger.info(_plus_1);
        return null;
      }
    }
    return TypeInferringCompoundEChangeFactory.getInstance().<EObject>createCreateAndInsertRootChange(rootElement, resource, index);
  }
  
  private List<EChange> generateFileDeleteChange(final Resource resource) {
    int _size = resource.getContents().size();
    boolean _lessThan = (0 < _size);
    if (_lessThan) {
      final int index = 0;
      final EObject rootElement = resource.getContents().get(index);
      return TypeInferringCompoundEChangeFactory.getInstance().<EObject>createRemoveAndDeleteRootChange(rootElement, resource, index);
    }
    VURI _instance = VURI.getInstance(resource);
    String _plus = ("Deleted resource " + _instance);
    String _plus_1 = (_plus + " did not contain any EObject");
    VitruviusChangeFactory.logger.info(_plus_1);
    return null;
  }
}
