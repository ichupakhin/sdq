package tools.vitruv.framework.change.processing.impl;

import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Pure;
import tools.vitruv.framework.change.description.TransactionalChange;
import tools.vitruv.framework.change.processing.ChangePropagationObserver;
import tools.vitruv.framework.change.processing.ChangePropagationSpecification;
import tools.vitruv.framework.change.processing.impl.AbstractChangePropagationSpecification;
import tools.vitruv.framework.correspondence.CorrespondenceModel;
import tools.vitruv.framework.domains.VitruvDomain;
import tools.vitruv.framework.userinteraction.UserInteractor;
import tools.vitruv.framework.util.command.ResourceAccess;

@SuppressWarnings("all")
public abstract class CompositeChangePropagationSpecification extends AbstractChangePropagationSpecification implements ChangePropagationObserver {
  private static final Logger logger = Logger.getLogger(CompositeChangePropagationSpecification.class);
  
  @Accessors(AccessorType.PROTECTED_GETTER)
  private final List<ChangePropagationSpecification> changePreprocessors;
  
  @Accessors(AccessorType.PROTECTED_GETTER)
  private final List<ChangePropagationSpecification> changeMainprocessors;
  
  public CompositeChangePropagationSpecification(final VitruvDomain sourceDomain, final VitruvDomain targetDomain) {
    super(sourceDomain, targetDomain);
    ArrayList<ChangePropagationSpecification> _arrayList = new ArrayList<ChangePropagationSpecification>();
    this.changePreprocessors = _arrayList;
    ArrayList<ChangePropagationSpecification> _arrayList_1 = new ArrayList<ChangePropagationSpecification>();
    this.changeMainprocessors = _arrayList_1;
  }
  
  /**
   * Adds the specified change processor as a preprocessor, which is executed before the mainprocessors.
   * The preprocessors are executed in the order in which they are added.
   */
  protected void addChangePreprocessor(final ChangePropagationSpecification changePropagationSpecifcation) {
    this.changePreprocessors.add(changePropagationSpecifcation);
    changePropagationSpecifcation.setUserInteractor(this.getUserInteractor());
    changePropagationSpecifcation.registerObserver(this);
  }
  
  /**
   * Adds the specified change processor as a main processor, which is executed after the preprocessors.
   * The main processors are executed in the order in which they are added.
   */
  protected void addChangeMainprocessor(final ChangePropagationSpecification changePropagationSpecifcation) {
    this.assertMetamodelsCompatible(changePropagationSpecifcation);
    this.changeMainprocessors.add(changePropagationSpecifcation);
    changePropagationSpecifcation.setUserInteractor(this.getUserInteractor());
    changePropagationSpecifcation.registerObserver(this);
  }
  
  private void assertMetamodelsCompatible(final ChangePropagationSpecification potentialChangeProcessor) {
    if (((!this.getSourceDomain().equals(potentialChangeProcessor.getSourceDomain())) || 
      (!this.getTargetDomain().equals(potentialChangeProcessor.getTargetDomain())))) {
      throw new IllegalArgumentException("ChangeProcessor metamodels are not compatible");
    }
  }
  
  @Override
  public void propagateChange(final TransactionalChange change, final CorrespondenceModel correspondenceModel, final ResourceAccess resourceAccess) {
    this.propagateChangeViaPreprocessors(change, correspondenceModel, resourceAccess);
    this.propagateChangeViaMainprocessors(change, correspondenceModel, resourceAccess);
  }
  
  protected void propagateChangeViaPreprocessors(final TransactionalChange change, final CorrespondenceModel correspondenceModel, final ResourceAccess resourceAccess) {
    for (final ChangePropagationSpecification changeProcessor : this.changePreprocessors) {
      {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("Calling change preprocessor ");
        _builder.append(changeProcessor);
        _builder.append(" for change event ");
        _builder.append(change);
        CompositeChangePropagationSpecification.logger.debug(_builder);
        changeProcessor.propagateChange(change, correspondenceModel, resourceAccess);
      }
    }
  }
  
  protected void propagateChangeViaMainprocessors(final TransactionalChange change, final CorrespondenceModel correspondenceModel, final ResourceAccess resourceAccess) {
    for (final ChangePropagationSpecification changeProcessor : this.changeMainprocessors) {
      {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("Calling change mainprocessor ");
        _builder.append(changeProcessor);
        _builder.append(" for change event ");
        _builder.append(change);
        CompositeChangePropagationSpecification.logger.debug(_builder);
        changeProcessor.propagateChange(change, correspondenceModel, resourceAccess);
      }
    }
  }
  
  @Override
  public boolean doesHandleChange(final TransactionalChange change, final CorrespondenceModel correspondenceModel) {
    ArrayList<ChangePropagationSpecification> _allProcessors = this.getAllProcessors();
    for (final ChangePropagationSpecification changeProcessor : _allProcessors) {
      boolean _doesHandleChange = changeProcessor.doesHandleChange(change, correspondenceModel);
      if (_doesHandleChange) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public void setUserInteractor(final UserInteractor userInteractor) {
    super.setUserInteractor(userInteractor);
    ArrayList<ChangePropagationSpecification> _allProcessors = this.getAllProcessors();
    for (final ChangePropagationSpecification changeProcessor : _allProcessors) {
      changeProcessor.setUserInteractor(userInteractor);
    }
  }
  
  private ArrayList<ChangePropagationSpecification> getAllProcessors() {
    final ArrayList<ChangePropagationSpecification> processors = new ArrayList<ChangePropagationSpecification>();
    if ((this.changePreprocessors != null)) {
      Iterables.<ChangePropagationSpecification>addAll(processors, this.changePreprocessors);
    }
    if ((this.changeMainprocessors != null)) {
      Iterables.<ChangePropagationSpecification>addAll(processors, this.changeMainprocessors);
    }
    return processors;
  }
  
  @Override
  public void objectCreated(final EObject createdObject) {
    this.notifyObjectCreated(createdObject);
  }
  
  @Pure
  protected List<ChangePropagationSpecification> getChangePreprocessors() {
    return this.changePreprocessors;
  }
  
  @Pure
  protected List<ChangePropagationSpecification> getChangeMainprocessors() {
    return this.changeMainprocessors;
  }
}
