package me.vodarga.api.junit.extension;

import me.vodarga.core.config.Configurer;
import me.vodarga.core.junit.extension.SuiteExtension;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ConfigurerExtension implements SuiteExtension {

  private final Configurer[] configurers;

  public ConfigurerExtension(Configurer... configurers) {
    this.configurers = configurers;
  }

  @Override
  public void beforeSuite(ExtensionContext context) {
    for (Configurer configurer : configurers) {
      configurer.configure();
    }
  }

}
