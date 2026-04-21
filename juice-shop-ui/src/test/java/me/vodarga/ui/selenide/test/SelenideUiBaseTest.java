package me.vodarga.ui.selenide.test;

import me.vodarga.ui.common.test.UiBaseTest;
import me.vodarga.ui.junit.extension.SelenideConfigExtension;
import org.junit.jupiter.api.extension.RegisterExtension;

abstract class SelenideUiBaseTest extends UiBaseTest {

  @RegisterExtension
  private static final SelenideConfigExtension selenideExtension = new SelenideConfigExtension();

}
