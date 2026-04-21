package me.vodarga.ui.junit.extension;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import me.vodarga.core.SuiteExtension;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;

public class SelenideConfigExtension implements SuiteExtension, AfterEachCallback {

  @Override
  public void beforeSuite(ExtensionContext context) {
    Configuration.baseUrl = context.getRoot()
        .getStore(Namespace.GLOBAL)
        .get("APP_URL", String.class);
    Configuration.reportsFolder = "target/reports/tests";
    Configuration.downloadsFolder = "target/downloads";
  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    Selenide.clearBrowserCookies();
    Selenide.clearBrowserLocalStorage();
    Selenide.closeWindow();
  }

}
