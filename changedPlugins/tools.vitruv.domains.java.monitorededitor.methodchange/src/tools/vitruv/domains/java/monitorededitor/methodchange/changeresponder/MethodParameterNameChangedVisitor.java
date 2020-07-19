package tools.vitruv.domains.java.monitorededitor.methodchange.changeresponder;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.parameters.Parametrizable;
import tools.vitruv.domains.java.echange.feature.attribute.AttributeFactory;
import tools.vitruv.domains.java.echange.feature.attribute.JavaReplaceSingleValuedEAttribute;
import tools.vitruv.domains.java.monitorededitor.ChangeSubmitter;
import tools.vitruv.domains.java.monitorededitor.methodchange.changeresponder.VisitorBase;
import tools.vitruv.domains.java.monitorededitor.methodchange.events.MethodParameterNameChangedEvent;
import tools.vitruv.framework.change.description.VitruviusChangeFactory;
import tools.vitruv.framework.util.datatypes.VURI;

@SuppressWarnings("all")
public class MethodParameterNameChangedVisitor extends VisitorBase<MethodParameterNameChangedEvent> {
  @Override
  protected Class<MethodParameterNameChangedEvent> getTreatedClassInternal() {
    return MethodParameterNameChangedEvent.class;
  }
  
  @Override
  protected void visitInternal(final MethodParameterNameChangedEvent changeClassifyingEvent, final ChangeSubmitter submitter) {
    final Parametrizable originalMethod = changeClassifyingEvent.getOriginalCompilationUnit().getMethodOrConstructorForMethodDeclaration(changeClassifyingEvent.getOriginalElement());
    final Parametrizable changedMethod = changeClassifyingEvent.getChangedCompilationUnit().getMethodOrConstructorForMethodDeclaration(changeClassifyingEvent.getChangedElement());
    final Function1<Parameter, Boolean> _function = (Parameter it) -> {
      return Boolean.valueOf(it.getName().equals(changeClassifyingEvent.getParamOriginal().getName().getIdentifier()));
    };
    final Parameter originalParam = IterableExtensions.<Parameter>findFirst(originalMethod.getParameters(), _function);
    final Function1<Parameter, Boolean> _function_1 = (Parameter it) -> {
      return Boolean.valueOf(it.getName().equals(changeClassifyingEvent.getParamChanged().getName().getIdentifier()));
    };
    final Parameter changedParam = IterableExtensions.<Parameter>findFirst(changedMethod.getParameters(), _function_1);
    final JavaReplaceSingleValuedEAttribute<Parameter, Object> change = AttributeFactory.eINSTANCE.<Parameter, Object>createJavaReplaceSingleValuedEAttribute();
    change.setAffectedEObject(changedParam);
    change.setOldAffectedEObject(originalParam);
    EStructuralFeature _eStructuralFeature = originalParam.eClass().getEStructuralFeature("name");
    change.setAffectedFeature(((EAttribute) _eStructuralFeature));
    change.setNewValue(change.getAffectedEObject().eGet(change.getAffectedFeature()));
    change.setOldValue(change.getOldAffectedEObject().eGet(change.getAffectedFeature()));
    submitter.submitChange(VitruviusChangeFactory.getInstance().createConcreteChangeWithVuri(change, VURI.getInstance(change.getOldAffectedEObject().eResource())));
  }
}
