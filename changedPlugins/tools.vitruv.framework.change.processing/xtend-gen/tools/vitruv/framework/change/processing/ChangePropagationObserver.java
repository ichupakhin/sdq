package tools.vitruv.framework.change.processing;

import org.eclipse.emf.ecore.EObject;

@SuppressWarnings("all")
public interface ChangePropagationObserver {
  void objectCreated(final EObject createdObject);
}
