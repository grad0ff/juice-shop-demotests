package me.vodarga.core;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;

public interface SuiteExtension extends BeforeAllCallback {

  default void beforeSuite(ExtensionContext context) {
    throw new RuntimeException("Method beforeSuite() must be overridden");
  }

  default void afterSuite(ExtensionContext context) {
    throw new RuntimeException("Method afterSuite() must be overridden");
  }

  @Override
  default void beforeAll(ExtensionContext context) {
    context.getRoot()
        .getStore(Namespace.GLOBAL)
        .getOrComputeIfAbsent(getClass(), key -> {
          beforeSuite(context);
          return (AutoCloseable) () -> afterSuite(context);
        });
  }

}
