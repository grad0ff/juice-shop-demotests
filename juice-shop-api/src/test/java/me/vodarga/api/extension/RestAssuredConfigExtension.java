package me.vodarga.api.extension;

import me.vodarga.api.service.RestAssuredConfigurer;
import me.vodarga.core.SuiteExtension;
import org.junit.jupiter.api.extension.ExtensionContext;

public class RestAssuredConfigExtension implements SuiteExtension {

  @Override
  public void beforeSuite(ExtensionContext context) {
    RestAssuredConfigurer configurer = new RestAssuredConfigurer();
    configurer.configure();
  }

}
