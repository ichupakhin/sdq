package tools.vitruv.domains.java;

import org.eclipse.emf.ecore.EPackage;
import org.emftext.language.java.JavaPackage;

@SuppressWarnings("all")
public final class JavaNamespace {
  private JavaNamespace() {
  }
  
  public static final String FILE_EXTENSION = "java";
  
  public static final EPackage ROOT_PACKAGE = JavaPackage.eINSTANCE;
  
  public static final String METAMODEL_NAMESPACE = JavaPackage.eNS_URI;
  
  public static String JAMOPP_ATTRIBUTE_NAME = "name";
  
  public static String JAMOPP_ANNOTATIONS_AND_MODIFIERS_REFERENCE_NAME = "annotationsAndModifiers";
  
  public static String JAMOPP_REFERENCE_TYPE_REFERENCE = "typeReference";
  
  public static String JAMOPP_MEMBERS_REFERENCE = "members";
  
  public static String JAMOPP_STATEMENTS_REFERENCE = "statements";
}
