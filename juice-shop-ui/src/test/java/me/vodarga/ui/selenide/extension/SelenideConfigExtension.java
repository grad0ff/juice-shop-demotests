package me.vodarga.ui.selenide.extension;

import static me.vodarga.core.config.CoreConfig.CORE_CFG;
import static me.vodarga.ui.config.UiConfig.UI_CFG;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import me.vodarga.core.SuiteExtension;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class SelenideConfigExtension implements SuiteExtension, AfterEachCallback {

  @Override
  public void beforeSuite(ExtensionContext context) {
    Configuration.baseUrl = CORE_CFG.baseUrl() + UI_CFG.basePath();
    Configuration.reportsFolder= "target/reports/tests";
    Configuration.downloadsFolder= "target/downloads";
  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    Selenide.clearBrowserCookies();
    Selenide.clearBrowserLocalStorage();
    Selenide.closeWindow();
  }
}
