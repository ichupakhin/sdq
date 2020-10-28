package tools.vitruv.domains.java;

import java.util.Set;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.JavaClasspath;

/**
 * This helper class allows to load the Java standard library in JaMoPP also with
 * Java versions 9 and above.
 * In Java 9 the boot classpath was removed and the standard library is packaged
 * differently, which is corrected by this patch.
 */
@SuppressWarnings("all")
public class JamoppLibraryHelper {
  public static String STANDARD_LIBRARY_PATH_IN_HOME = "/jmods/java.base.jmod";
  
  public static void registerStdLib() {
    final String javaVersion = System.getProperty("java.version");
    boolean _startsWith = javaVersion.startsWith("1.");
    if (_startsWith) {
      JavaClasspath.get().registerStdLib();
    } else {
      Set<JavaClasspath.Initializer> _initializers = JavaClasspath.getInitializers();
      _initializers.add(new JavaClasspath.Initializer() {
        @Override
        public void initialize(final Resource resource) {
        }
        
        @Override
        public boolean requiresLocalClasspath() {
          return false;
        }
        
        @Override
        public boolean requiresStdLib() {
          return false;
        }
      });
      String _property = System.getProperty("java.home");
      final String classpath = (_property + JamoppLibraryHelper.STANDARD_LIBRARY_PATH_IN_HOME);
      final URI uri = URI.createFileURI(classpath);
      JavaClasspath.get().registerClassifierJar(uri, "classes/");
    }
  }
}
