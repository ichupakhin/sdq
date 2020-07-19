package tools.vitruv.applications.pcmjava.tests.util;

@SuppressWarnings("all")
public interface SynchronizationAwaitCallback {
  void waitForSynchronization(final int numberOfExpectedSynchronizationCalls);
}
