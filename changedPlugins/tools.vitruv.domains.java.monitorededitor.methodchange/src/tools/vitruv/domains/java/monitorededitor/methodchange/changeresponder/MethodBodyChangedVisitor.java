package tools.vitruv.domains.java.monitorededitor.methodchange.changeresponder;

import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.parameters.Parametrizable;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementsPackage;
import tools.vitruv.domains.java.echange.feature.reference.JavaInsertEReference;
import tools.vitruv.domains.java.echange.feature.reference.JavaRemoveEReference;
import tools.vitruv.domains.java.echange.feature.reference.ReferenceFactory;
import tools.vitruv.domains.java.monitorededitor.ChangeSubmitter;
import tools.vitruv.domains.java.monitorededitor.methodchange.changeresponder.VisitorBase;
import tools.vitruv.domains.java.monitorededitor.methodchange.events.MethodBodyChangedEvent;
import tools.vitruv.framework.change.description.CompositeTransactionalChange;
import tools.vitruv.framework.change.description.VitruviusChangeFactory;
import tools.vitruv.framework.util.datatypes.VURI;

@SuppressWarnings("all")
public class MethodBodyChangedVisitor extends VisitorBase<MethodBodyChangedEvent> {
  @Override
  protected Class<MethodBodyChangedEvent> getTreatedClassInternal() {
    return MethodBodyChangedEvent.class;
  }
  
  @Override
  protected void visitInternal(final MethodBodyChangedEvent changeClassifyingEvent, final ChangeSubmitter submitter) {
    final Parametrizable originalMethod = changeClassifyingEvent.getOriginalCompilationUnit().getMethodOrConstructorForMethodDeclaration(changeClassifyingEvent.getOriginalElement());
    final Parametrizable changedMethod = changeClassifyingEvent.getChangedCompilationUnit().getMethodOrConstructorForMethodDeclaration(changeClassifyingEvent.getChangedElement());
    final CompositeTransactionalChange compositeChange = VitruviusChangeFactory.getInstance().createCompositeTransactionalChange();
    final VURI changeURI = VURI.getInstance(originalMethod.eResource());
    EList<Statement> _statements = ((ClassMethod) originalMethod).getStatements();
    for (final Statement stmt : _statements) {
      {
        final JavaRemoveEReference<Parametrizable, Statement> change = ReferenceFactory.eINSTANCE.<Parametrizable, Statement>createJavaRemoveEReference();
        change.setOldAffectedEObject(originalMethod);
        change.setAffectedEObject(changedMethod);
        change.setAffectedFeature(StatementsPackage.eINSTANCE.getStatementListContainer_Statements());
        change.setOldValue(stmt);
        change.setIndex(((ClassMethod) originalMethod).getStatements().indexOf(stmt));
        compositeChange.addChange(VitruviusChangeFactory.getInstance().createConcreteChangeWithVuri(change, changeURI));
      }
    }
    EList<Statement> _statements_1 = ((ClassMethod) changedMethod).getStatements();
    for (final Statement stmt_1 : _statements_1) {
      {
        final JavaInsertEReference<Parametrizable, Statement> change = ReferenceFactory.eINSTANCE.<Parametrizable, Statement>createJavaInsertEReference();
        change.setOldAffectedEObject(originalMethod);
        change.setAffectedEObject(changedMethod);
        change.setAffectedFeature(StatementsPackage.eINSTANCE.getStatementListContainer_Statements());
        change.setNewValue(stmt_1);
        change.setIndex(((ClassMethod) changedMethod).getStatements().indexOf(stmt_1));
        compositeChange.addChange(VitruviusChangeFactory.getInstance().createConcreteChangeWithVuri(change, changeURI));
      }
    }
    submitter.submitChange(compositeChange);
  }
}
