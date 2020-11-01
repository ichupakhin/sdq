package tools.vitruv.domains.java.tuid;

import com.google.common.base.Objects;
import java.util.Arrays;
import java.util.function.Consumer;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.emftext.commons.layout.AttributeLayoutInformation;
import org.emftext.commons.layout.KeywordLayoutInformation;
import org.emftext.commons.layout.ReferenceLayoutInformation;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.annotations.SingleAnnotationParameter;
import org.emftext.language.java.arrays.ArrayDimension;
import org.emftext.language.java.arrays.ArrayInitializer;
import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.classifiers.Annotation;
import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.JavaRoot;
import org.emftext.language.java.expressions.AssignmentExpression;
import org.emftext.language.java.expressions.AssignmentExpressionChild;
import org.emftext.language.java.expressions.ConditionalExpression;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.generics.TypeArgument;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.StaticImport;
import org.emftext.language.java.instantiations.NewConstructorCall;
import org.emftext.language.java.literals.Literal;
import org.emftext.language.java.literals.This;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.EmptyMember;
import org.emftext.language.java.members.EnumConstant;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.modifiers.Modifier;
import org.emftext.language.java.operators.Assignment;
import org.emftext.language.java.operators.AssignmentOperator;
import org.emftext.language.java.operators.Operator;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.ReferenceableElement;
import org.emftext.language.java.references.SelfReference;
import org.emftext.language.java.references.StringReference;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.Return;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.NamespaceClassifierReference;
import org.emftext.language.java.types.PrimitiveType;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.variables.Variable;
import tools.vitruv.domains.java.JavaNamespace;
import tools.vitruv.domains.java.tuid.JamoppStringOperations;
import tools.vitruv.framework.tuid.HierarchicalTuidCalculatorAndResolver;

/**
 * Tuid calculator and resolver for the JaMoPP meta-model.
 */
@SuppressWarnings("all")
public class JavaTuidCalculatorAndResolver extends HierarchicalTuidCalculatorAndResolver<JavaRoot> {
  private static final Logger logger = Logger.getLogger(JavaTuidCalculatorAndResolver.class);
  
  private final String PARAMETER_SELECTOR = "parameter";
  
  private final String CLASSIFIER_SELECTOR = "classifier";
  
  private final String IMPORT_SELECTOR = "import";
  
  private final String METHOD_SELECTOR = "method";
  
  private final String FIELD_SELECTOR = "field";
  
  private final String CONSTRUCTOR_SELECTOR = "constructor";
  
  private final String ASSIGNMENT_EXPRESSION_SELECTOR = "assignmentExpression";
  
  private final String EXPRESSION_STATEMENT_SELECTOR = "expressionStatement";
  
  private final String SELF_REFERENCE_SELECTOR = "selfReference";
  
  private final String STRING_REFERENCE_SELECTOR = "StringReference";
  
  private final String IDENTIFIER_REFERENCE_SELECTOR = "identifierReference";
  
  private final String NEW_CONSTRUCTOR_CALL_SELECTOR = "newConstructorCall";
  
  private final String CONDITIONAL_EXPRESSION_SELECTOR = "conditionalExpression";
  
  public JavaTuidCalculatorAndResolver() {
    super(JavaNamespace.METAMODEL_NAMESPACE);
  }
  
  @Override
  protected Class<JavaRoot> getRootObjectClass() {
    return JavaRoot.class;
  }
  
  @Override
  protected boolean hasId(final EObject obj, final String indidivualId) throws IllegalArgumentException {
    String _calculateIndividualTuid = this.calculateIndividualTuid(obj);
    return Objects.equal(_calculateIndividualTuid, indidivualId);
  }
  
  @Override
  protected String calculateIndividualTuidDelegator(final EObject obj) {
    return this.calculateIndividualTuid(obj);
  }
  
  private String _calculateIndividualTuid(final org.emftext.language.java.containers.Package jaMoPPPackage) {
    return "";
  }
  
  private String _calculateIndividualTuid(final CompilationUnit compilationUnit) {
    return "";
  }
  
  private String _calculateIndividualTuid(final Classifier classifier) {
    String _name = classifier.getName();
    return ((this.CLASSIFIER_SELECTOR + HierarchicalTuidCalculatorAndResolver.SUBDIVIDER) + _name);
  }
  
  private String _calculateIndividualTuid(final Method method) {
    final StringBuilder tuid = new StringBuilder();
    tuid.append(this.METHOD_SELECTOR);
    String _nameFrom = this.getNameFrom(method.getTypeReference());
    String _plus = (HierarchicalTuidCalculatorAndResolver.SUBDIVIDER + _nameFrom);
    String _plus_1 = (_plus + HierarchicalTuidCalculatorAndResolver.SUBDIVIDER);
    String _name = method.getName();
    String _plus_2 = (_plus_1 + _name);
    tuid.append(_plus_2);
    final Consumer<Parameter> _function = (Parameter it) -> {
      String _nameFrom_1 = this.getNameFrom(it.getTypeReference());
      String _plus_3 = (HierarchicalTuidCalculatorAndResolver.SUBDIVIDER + _nameFrom_1);
      tuid.append(_plus_3);
    };
    method.getParameters().forEach(_function);
    return tuid.toString();
  }
  
  private String _calculateIndividualTuid(final Field field) {
    String _name = field.getName();
    return ((this.FIELD_SELECTOR + HierarchicalTuidCalculatorAndResolver.SUBDIVIDER) + _name);
  }
  
  private String _calculateIndividualTuid(final Import importStatement) {
    String tuid = this.IMPORT_SELECTOR;
    if ((importStatement instanceof StaticImport)) {
      String _tuid = tuid;
      tuid = (_tuid + ("static" + HierarchicalTuidCalculatorAndResolver.SUBDIVIDER));
    }
    String _stringRepresentation = JamoppStringOperations.getStringRepresentation(importStatement);
    return (tuid + _stringRepresentation);
  }
  
  private String _calculateIndividualTuid(final Modifier modifier) {
    return (modifier.getClass().getInterfaces()[0]).getSimpleName();
  }
  
  private String _calculateIndividualTuid(final Parameter param) {
    String _name = param.getName();
    return ((this.PARAMETER_SELECTOR + HierarchicalTuidCalculatorAndResolver.SUBDIVIDER) + _name);
  }
  
  private String _calculateIndividualTuid(final NamespaceClassifierReference ref) {
    final StringBuilder tuid = new StringBuilder();
    EStructuralFeature _eContainingFeature = ref.eContainingFeature();
    boolean _tripleNotEquals = (_eContainingFeature != null);
    if (_tripleNotEquals) {
      tuid.append(ref.eContainingFeature().getName());
    }
    final Consumer<ClassifierReference> _function = (ClassifierReference it) -> {
      Classifier _target = it.getTarget();
      String _name = null;
      if (_target!=null) {
        _name=_target.getName();
      }
      tuid.append(_name);
    };
    ref.getClassifierReferences().forEach(_function);
    return tuid.toString();
  }
  
  private String _calculateIndividualTuid(final PrimitiveType pt) {
    return (pt.getClass().getInterfaces()[0]).getSimpleName();
  }
  
  private String _calculateIndividualTuid(final Constructor constuctor) {
    final StringBuilder tuid = new StringBuilder();
    tuid.append(this.CONSTRUCTOR_SELECTOR).append(HierarchicalTuidCalculatorAndResolver.SUBDIVIDER).append(constuctor.getName());
    final Consumer<Parameter> _function = (Parameter it) -> {
      String _nameFrom = this.getNameFrom(it.getTypeReference());
      String _plus = (HierarchicalTuidCalculatorAndResolver.SUBDIVIDER + _nameFrom);
      tuid.append(_plus);
    };
    constuctor.getParameters().forEach(_function);
    return tuid.toString();
  }
  
  private String _calculateIndividualTuid(final ExpressionStatement expressionStatement) {
    final StringBuilder tuid = new StringBuilder();
    tuid.append(this.EXPRESSION_STATEMENT_SELECTOR).append(HierarchicalTuidCalculatorAndResolver.SUBDIVIDER);
    tuid.append(this.calculateIndividualTuid(expressionStatement.getExpression()));
    return tuid.toString();
  }
  
  private String _calculateIndividualTuid(final AssignmentExpression assignmentExpression) {
    final StringBuilder tuid = new StringBuilder();
    tuid.append(this.ASSIGNMENT_EXPRESSION_SELECTOR).append(HierarchicalTuidCalculatorAndResolver.SUBDIVIDER);
    AssignmentExpressionChild _child = assignmentExpression.getChild();
    boolean _tripleNotEquals = (null != _child);
    if (_tripleNotEquals) {
      tuid.append(this.calculateIndividualTuid(assignmentExpression.getChild())).append(HierarchicalTuidCalculatorAndResolver.SUBDIVIDER);
    }
    AssignmentOperator _assignmentOperator = assignmentExpression.getAssignmentOperator();
    boolean _tripleNotEquals_1 = (null != _assignmentOperator);
    if (_tripleNotEquals_1) {
      tuid.append(this.calculateIndividualTuid(assignmentExpression.getAssignmentOperator())).append(HierarchicalTuidCalculatorAndResolver.SUBDIVIDER);
    }
    Expression _value = assignmentExpression.getValue();
    boolean _tripleNotEquals_2 = (null != _value);
    if (_tripleNotEquals_2) {
      tuid.append(this.calculateIndividualTuid(assignmentExpression.getValue()));
    }
    return tuid.toString();
  }
  
  private String _calculateIndividualTuid(final SelfReference selfReference) {
    final StringBuilder tuid = new StringBuilder();
    tuid.append(this.SELF_REFERENCE_SELECTOR);
    tuid.append(this.calculateIndividualTuid(selfReference.getSelf())).append(HierarchicalTuidCalculatorAndResolver.SUBDIVIDER);
    tuid.append(this.calculateIndividualTuid(selfReference.getNext()));
    return tuid.toString();
  }
  
  private String _calculateIndividualTuid(final StringReference stringReference) {
    final StringBuilder tuid = new StringBuilder();
    tuid.append(this.STRING_REFERENCE_SELECTOR);
    tuid.append(stringReference.getValue());
    return tuid.toString();
  }
  
  private String _calculateIndividualTuid(final Annotation annotation) {
    return "";
  }
  
  private String _calculateIndividualTuid(final EnumConstant constant) {
    final StringBuilder tuid = new StringBuilder();
    tuid.append(constant.getName());
    return tuid.toString();
  }
  
  private String _calculateIndividualTuid(final AnnotationInstance obj) {
    return "";
  }
  
  private String _calculateIndividualTuid(final Literal literal) {
    return "";
  }
  
  private String _calculateIndividualTuid(final ArrayDimension arrayDim) {
    return "";
  }
  
  private String _calculateIndividualTuid(final ArrayInitializer array) {
    return "";
  }
  
  private String _calculateIndividualTuid(final SingleAnnotationParameter param) {
    return "";
  }
  
  private String _calculateIndividualTuid(final ReferenceableElement elem) {
    return "";
  }
  
  private String _calculateIndividualTuid(final ArraySelector array) {
    return "";
  }
  
  private String _calculateIndividualTuid(final Void nullRef) {
    return "";
  }
  
  private String _calculateIndividualTuid(final EmptyMember emptyMember) {
    return "";
  }
  
  private String _calculateIndividualTuid(final Return returnStatement) {
    return "";
  }
  
  private String _calculateIndividualTuid(final StatementListContainer statementListContainer) {
    return "";
  }
  
  private String _calculateIndividualTuid(final TypeArgument typeArgument) {
    return "";
  }
  
  private String _calculateIndividualTuid(final Variable variable) {
    return "";
  }
  
  private String _calculateIndividualTuid(final Statement statement) {
    return "";
  }
  
  private String _calculateIndividualTuid(final Expression expression) {
    return "";
  }
  
  private String _calculateIndividualTuid(final AnonymousClass anonClass) {
    return "";
  }
  
  private String _calculateIndividualTuid(final Operator operator) {
    return "";
  }
  
  private String _calculateIndividualTuid(final This thisRef) {
    return "this";
  }
  
  private String _calculateIndividualTuid(final IdentifierReference identifierReference) {
    final StringBuilder tuid = new StringBuilder();
    tuid.append(this.IDENTIFIER_REFERENCE_SELECTOR);
    if (((null != identifierReference) && (null != identifierReference.getTarget()))) {
      tuid.append(identifierReference.getTarget().getName());
    }
    return tuid.toString();
  }
  
  private String _calculateIndividualTuid(final Assignment assignment) {
    return "=";
  }
  
  private String _calculateIndividualTuid(final NewConstructorCall newConstructorCall) {
    final StringBuilder tuid = new StringBuilder();
    tuid.append(this.NEW_CONSTRUCTOR_CALL_SELECTOR).append(HierarchicalTuidCalculatorAndResolver.SUBDIVIDER);
    tuid.append(this.getNameFrom(newConstructorCall.getTypeReference())).append(HierarchicalTuidCalculatorAndResolver.SUBDIVIDER);
    return tuid.toString();
  }
  
  private String _calculateIndividualTuid(final ConditionalExpression conditionalExpression) {
    final StringBuilder tuid = new StringBuilder();
    tuid.append(this.CONDITIONAL_EXPRESSION_SELECTOR);
    Type _type = conditionalExpression.getType();
    boolean _tripleEquals = (null == _type);
    if (_tripleEquals) {
      tuid.append(HierarchicalTuidCalculatorAndResolver.SUBDIVIDER).append("null");
    } else {
      tuid.append(HierarchicalTuidCalculatorAndResolver.SUBDIVIDER).append(this.calculateIndividualTuid(conditionalExpression.getType()));
    }
    return tuid.toString();
  }
  
  private String _calculateIndividualTuid(final ClassifierReference classifierReference) {
    final StringBuilder tuid = new StringBuilder();
    EStructuralFeature _eContainingFeature = classifierReference.eContainingFeature();
    String _name = null;
    if (_eContainingFeature!=null) {
      _name=_eContainingFeature.getName();
    }
    tuid.append(_name);
    Classifier _target = classifierReference.getTarget();
    String _name_1 = null;
    if (_target!=null) {
      _name_1=_target.getName();
    }
    tuid.append(_name_1);
    return tuid.toString();
  }
  
  private String _calculateIndividualTuid(final ReferenceLayoutInformation referenceLayoutInformation) {
    return "";
  }
  
  private String _calculateIndividualTuid(final AttributeLayoutInformation referenceLayoutInformation) {
    return "";
  }
  
  private String _calculateIndividualTuid(final KeywordLayoutInformation referenceLayoutInformation) {
    return "";
  }
  
  private String _calculateIndividualTuid(final EObject obj) {
    String _simpleName = obj.getClass().getSimpleName();
    String _plus = ("Invalid type given " + _simpleName);
    throw new IllegalArgumentException(_plus);
  }
  
  private String _getNameFrom(final NamespaceClassifierReference namespaceClassifierReference) {
    String name = "";
    int i = 0;
    EList<ClassifierReference> _classifierReferences = namespaceClassifierReference.getClassifierReferences();
    for (final ClassifierReference cr : _classifierReferences) {
      {
        if ((i > 0)) {
          String _name = name;
          name = (_name + Integer.valueOf(i));
        }
        String _name_1 = name;
        String _nameFrom = this.getNameFrom(cr);
        name = (_name_1 + _nameFrom);
        i++;
      }
    }
    return name;
  }
  
  private String _getNameFrom(final PrimitiveType primitiveType) {
    return primitiveType.eClass().getName().replaceAll("Impl", "");
  }
  
  private String _getNameFrom(final ClassifierReference classifierReference) {
    Classifier _target = classifierReference.getTarget();
    String _name = null;
    if (_target!=null) {
      _name=_target.getName();
    }
    return _name;
  }
  
  private String _getNameFrom(final Void nullType) {
    JavaTuidCalculatorAndResolver.logger.warn((("getNameFrom name for " + nullType) + " not possible"));
    return "";
  }
  
  private String calculateIndividualTuid(final EObject newConstructorCall) {
    if (newConstructorCall instanceof NewConstructorCall) {
      return _calculateIndividualTuid((NewConstructorCall)newConstructorCall);
    } else if (newConstructorCall instanceof IdentifierReference) {
      return _calculateIndividualTuid((IdentifierReference)newConstructorCall);
    } else if (newConstructorCall instanceof AnnotationInstance) {
      return _calculateIndividualTuid((AnnotationInstance)newConstructorCall);
    } else if (newConstructorCall instanceof SelfReference) {
      return _calculateIndividualTuid((SelfReference)newConstructorCall);
    } else if (newConstructorCall instanceof StringReference) {
      return _calculateIndividualTuid((StringReference)newConstructorCall);
    } else if (newConstructorCall instanceof Literal) {
      return _calculateIndividualTuid((Literal)newConstructorCall);
    } else if (newConstructorCall instanceof Annotation) {
      return _calculateIndividualTuid((Annotation)newConstructorCall);
    } else if (newConstructorCall instanceof ConditionalExpression) {
      return _calculateIndividualTuid((ConditionalExpression)newConstructorCall);
    } else if (newConstructorCall instanceof Field) {
      return _calculateIndividualTuid((Field)newConstructorCall);
    } else if (newConstructorCall instanceof Parameter) {
      return _calculateIndividualTuid((Parameter)newConstructorCall);
    } else if (newConstructorCall instanceof Classifier) {
      return _calculateIndividualTuid((Classifier)newConstructorCall);
    } else if (newConstructorCall instanceof CompilationUnit) {
      return _calculateIndividualTuid((CompilationUnit)newConstructorCall);
    } else if (newConstructorCall instanceof org.emftext.language.java.containers.Package) {
      return _calculateIndividualTuid((org.emftext.language.java.containers.Package)newConstructorCall);
    } else if (newConstructorCall instanceof AssignmentExpression) {
      return _calculateIndividualTuid((AssignmentExpression)newConstructorCall);
    } else if (newConstructorCall instanceof Constructor) {
      return _calculateIndividualTuid((Constructor)newConstructorCall);
    } else if (newConstructorCall instanceof EmptyMember) {
      return _calculateIndividualTuid((EmptyMember)newConstructorCall);
    } else if (newConstructorCall instanceof EnumConstant) {
      return _calculateIndividualTuid((EnumConstant)newConstructorCall);
    } else if (newConstructorCall instanceof Method) {
      return _calculateIndividualTuid((Method)newConstructorCall);
    } else if (newConstructorCall instanceof Assignment) {
      return _calculateIndividualTuid((Assignment)newConstructorCall);
    } else if (newConstructorCall instanceof Variable) {
      return _calculateIndividualTuid((Variable)newConstructorCall);
    } else if (newConstructorCall instanceof SingleAnnotationParameter) {
      return _calculateIndividualTuid((SingleAnnotationParameter)newConstructorCall);
    } else if (newConstructorCall instanceof ArrayInitializer) {
      return _calculateIndividualTuid((ArrayInitializer)newConstructorCall);
    } else if (newConstructorCall instanceof AnonymousClass) {
      return _calculateIndividualTuid((AnonymousClass)newConstructorCall);
    } else if (newConstructorCall instanceof Expression) {
      return _calculateIndividualTuid((Expression)newConstructorCall);
    } else if (newConstructorCall instanceof TypeArgument) {
      return _calculateIndividualTuid((TypeArgument)newConstructorCall);
    } else if (newConstructorCall instanceof Import) {
      return _calculateIndividualTuid((Import)newConstructorCall);
    } else if (newConstructorCall instanceof This) {
      return _calculateIndividualTuid((This)newConstructorCall);
    } else if (newConstructorCall instanceof Modifier) {
      return _calculateIndividualTuid((Modifier)newConstructorCall);
    } else if (newConstructorCall instanceof ReferenceableElement) {
      return _calculateIndividualTuid((ReferenceableElement)newConstructorCall);
    } else if (newConstructorCall instanceof ExpressionStatement) {
      return _calculateIndividualTuid((ExpressionStatement)newConstructorCall);
    } else if (newConstructorCall instanceof Return) {
      return _calculateIndividualTuid((Return)newConstructorCall);
    } else if (newConstructorCall instanceof ClassifierReference) {
      return _calculateIndividualTuid((ClassifierReference)newConstructorCall);
    } else if (newConstructorCall instanceof NamespaceClassifierReference) {
      return _calculateIndividualTuid((NamespaceClassifierReference)newConstructorCall);
    } else if (newConstructorCall instanceof PrimitiveType) {
      return _calculateIndividualTuid((PrimitiveType)newConstructorCall);
    } else if (newConstructorCall instanceof AttributeLayoutInformation) {
      return _calculateIndividualTuid((AttributeLayoutInformation)newConstructorCall);
    } else if (newConstructorCall instanceof KeywordLayoutInformation) {
      return _calculateIndividualTuid((KeywordLayoutInformation)newConstructorCall);
    } else if (newConstructorCall instanceof ReferenceLayoutInformation) {
      return _calculateIndividualTuid((ReferenceLayoutInformation)newConstructorCall);
    } else if (newConstructorCall instanceof ArrayDimension) {
      return _calculateIndividualTuid((ArrayDimension)newConstructorCall);
    } else if (newConstructorCall instanceof ArraySelector) {
      return _calculateIndividualTuid((ArraySelector)newConstructorCall);
    } else if (newConstructorCall instanceof Operator) {
      return _calculateIndividualTuid((Operator)newConstructorCall);
    } else if (newConstructorCall instanceof Statement) {
      return _calculateIndividualTuid((Statement)newConstructorCall);
    } else if (newConstructorCall instanceof StatementListContainer) {
      return _calculateIndividualTuid((StatementListContainer)newConstructorCall);
    } else if (newConstructorCall != null) {
      return _calculateIndividualTuid(newConstructorCall);
    } else if (newConstructorCall == null) {
      return _calculateIndividualTuid((Void)null);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(newConstructorCall).toString());
    }
  }
  
  private String getNameFrom(final TypeReference classifierReference) {
    if (classifierReference instanceof ClassifierReference) {
      return _getNameFrom((ClassifierReference)classifierReference);
    } else if (classifierReference instanceof NamespaceClassifierReference) {
      return _getNameFrom((NamespaceClassifierReference)classifierReference);
    } else if (classifierReference instanceof PrimitiveType) {
      return _getNameFrom((PrimitiveType)classifierReference);
    } else if (classifierReference == null) {
      return _getNameFrom((Void)null);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(classifierReference).toString());
    }
  }
}
