package me.vodarga.core.junit.extension;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;

public interface SuiteExtension extends BeforeAllCallback {

  default void beforeSuite(ExtensionContext context) {
  }

  default void afterSuite(ExtensionContext context) {
  }

  @Override
  default void beforeAll(ExtensionContext context) {
    context.getRoot()
        .getStore(Namespace.GLOBAL)
        .computeIfAbsent(getClass(), key -> {
          beforeSuite(context);
          return (AutoCloseable) () -> afterSuite(context);
        });
  }

}
