package tools.vitruv.domains.java.tuid;

import edu.kit.ipd.sdq.commons.util.java.lang.StringUtil;
import java.util.Arrays;
import java.util.function.Consumer;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.imports.PackageImport;
import org.emftext.language.java.imports.StaticClassifierImport;
import org.emftext.language.java.imports.StaticMemberImport;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.parameters.OrdinaryParameter;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.parameters.VariableLengthParameter;
import org.emftext.language.java.references.ReferenceableElement;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.NamespaceClassifierReference;
import org.emftext.language.java.types.PrimitiveType;

/**
 * Converts a JaMoPP model element to a string representation.
 * This representation is not necessarily the concrete syntax
 * but might be anything appropriate to fulfil the need in the
 * place it is used.
 * Use the {@link JaMoPPConcreteSyntax} class for strict
 * concrete syntax conversions.
 */
@SuppressWarnings("all")
public class JamoppStringOperations {
  protected static String _getStringRepresentation(final ClassifierImport imprt) {
    String _namespaceAsString = JamoppStringOperations.getNamespaceAsString(imprt.getNamespaces());
    String _plus = (_namespaceAsString + ".");
    String _name = imprt.getClassifier().getName();
    return (_plus + _name);
  }
  
  protected static String _getStringRepresentation(final StaticClassifierImport imprt) {
    String _namespaceAsString = JamoppStringOperations.getNamespaceAsString(imprt.getNamespaces());
    return (_namespaceAsString + ".*");
  }
  
  protected static String _getStringRepresentation(final PackageImport imprt) {
    String _namespaceAsString = JamoppStringOperations.getNamespaceAsString(imprt.getNamespaces());
    return (_namespaceAsString + ".*");
  }
  
  protected static String _getStringRepresentation(final StaticMemberImport imprt) {
    String _namespaceAsString = JamoppStringOperations.getNamespaceAsString(imprt.getNamespaces());
    String _plus = (_namespaceAsString + ".");
    String _nameList = JamoppStringOperations.getNameList(imprt.getStaticMembers());
    return (_plus + _nameList);
  }
  
  private static String getNameList(final Iterable<ReferenceableElement> elements) {
    final StringBuilder strBuilder = new StringBuilder();
    final Consumer<ReferenceableElement> _function = (ReferenceableElement it) -> {
      String _name = it.getName();
      String _plus = (_name + ",");
      strBuilder.append(_plus);
    };
    elements.forEach(_function);
    int _length = strBuilder.length();
    int _minus = (_length - 1);
    strBuilder.deleteCharAt(_minus);
    return strBuilder.toString();
  }
  
  protected static String _getStringRepresentation(final Method javaMethod) {
    final StringBuilder javaIdentifier = new StringBuilder();
    javaIdentifier.append(JamoppStringOperations.getStringRepresentation(javaMethod.getTypeReference(), javaMethod.getArrayDimension()));
    javaIdentifier.append(javaMethod.getName());
    final Consumer<Parameter> _function = (Parameter it) -> {
      javaIdentifier.append(JamoppStringOperations.getStringRepresentation(it.getTypeReference(), it.getArrayDimension()));
    };
    javaMethod.getParameters().forEach(_function);
    return javaIdentifier.toString();
  }
  
  protected static String _getStringRepresentation(final PrimitiveType pt, final long arrayDimensions) {
    String _lowerCase = (pt.getClass().getInterfaces()[0]).getSimpleName().toLowerCase();
    String _repeat = StringUtil.repeat("[]", ((int) arrayDimensions));
    return (_lowerCase + _repeat);
  }
  
  protected static String _getStringRepresentation(final NamespaceClassifierReference ncr, final long arrayDimensions) {
    final StringBuilder strBuilder = new StringBuilder();
    final Consumer<ClassifierReference> _function = (ClassifierReference it) -> {
      StringBuilder _append = strBuilder.append(it.getTarget().getName());
      String _repeat = StringUtil.repeat("[]", ((int) arrayDimensions));
      /* (_append + _repeat); */
    };
    ncr.getClassifierReferences().forEach(_function);
    return strBuilder.toString();
  }
  
  protected static String _getStringRepresentation(final Classifier pt, final long arrayDimensions) {
    String _name = pt.getName();
    String _repeat = StringUtil.repeat("[]", ((int) arrayDimensions));
    return (_name + _repeat);
  }
  
  protected static String _getStringRepresentation(final OrdinaryParameter param) {
    return JamoppStringOperations.getStringRepresentation(param.getTypeReference().getTarget(), param.getArrayDimension());
  }
  
  protected static String _getStringRepresentation(final VariableLengthParameter param) {
    String _stringRepresentation = JamoppStringOperations.getStringRepresentation(param.getTypeReference().getTarget(), param.getArrayDimension());
    return (_stringRepresentation + "...");
  }
  
  public static String getNamespaceAsString(final Iterable<String> namespaces) {
    return StringUtil.join(((String[])Conversions.unwrapArray(namespaces, String.class)), ".");
  }
  
  public static String getStringRepresentation(final Commentable param) {
    if (param instanceof OrdinaryParameter) {
      return _getStringRepresentation((OrdinaryParameter)param);
    } else if (param instanceof VariableLengthParameter) {
      return _getStringRepresentation((VariableLengthParameter)param);
    } else if (param instanceof StaticClassifierImport) {
      return _getStringRepresentation((StaticClassifierImport)param);
    } else if (param instanceof StaticMemberImport) {
      return _getStringRepresentation((StaticMemberImport)param);
    } else if (param instanceof ClassifierImport) {
      return _getStringRepresentation((ClassifierImport)param);
    } else if (param instanceof PackageImport) {
      return _getStringRepresentation((PackageImport)param);
    } else if (param instanceof Method) {
      return _getStringRepresentation((Method)param);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(param).toString());
    }
  }
  
  public static String getStringRepresentation(final Commentable pt, final long arrayDimensions) {
    if (pt instanceof Classifier) {
      return _getStringRepresentation((Classifier)pt, arrayDimensions);
    } else if (pt instanceof NamespaceClassifierReference) {
      return _getStringRepresentation((NamespaceClassifierReference)pt, arrayDimensions);
    } else if (pt instanceof PrimitiveType) {
      return _getStringRepresentation((PrimitiveType)pt, arrayDimensions);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(pt, arrayDimensions).toString());
    }
  }
}
