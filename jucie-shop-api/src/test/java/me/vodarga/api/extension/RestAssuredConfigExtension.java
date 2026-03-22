package me.vodarga.api.extension;

import me.vodarga.api.config.RestAssuredConfigurer;
import me.vodarga.core.SuiteExtension;
import org.junit.jupiter.api.extension.ExtensionContext;

public class RestAssuredConfigExtension implements SuiteExtension {

  @Override
  public void beforeSuite(ExtensionContext context) {
    new RestAssuredConfigurer().configure();
  }

}
