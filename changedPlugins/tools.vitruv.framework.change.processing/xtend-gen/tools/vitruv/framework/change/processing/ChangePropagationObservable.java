package tools.vitruv.framework.change.processing;

import org.eclipse.emf.ecore.EObject;
import tools.vitruv.framework.change.processing.ChangePropagationObserver;

@SuppressWarnings("all")
public interface ChangePropagationObservable {
  void notifyObjectCreated(final EObject createdObject);
  
  void registerObserver(final ChangePropagationObserver observer);
  
  void deregisterObserver(final ChangePropagationObserver observer);
}
