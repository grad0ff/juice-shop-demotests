package me.vodarga.ui.common.junit.extension;

import static me.vodarga.core.config.CoreConfig.CORE_CFG;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import me.vodarga.core.junit.extension.SuiteExtension;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class SelenideConfigExtension implements SuiteExtension, AfterEachCallback {

  @Override
  public void beforeSuite(ExtensionContext context) {
    Configuration.baseUrl = CORE_CFG.baseUrl();
    Configuration.timeout = 10_000;
    Configuration.reportsFolder = "target/reports/tests";
    Configuration.downloadsFolder = "target/downloads";
  }

  @Override
  public void afterEach(ExtensionContext context) {
    Selenide.closeWebDriver();
  }

}
