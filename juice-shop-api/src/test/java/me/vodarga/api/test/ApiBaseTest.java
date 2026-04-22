package me.vodarga.api.test;

import static me.vodarga.core.allure.AllureLayer.API;

import me.vodarga.api.junit.extension.ConfigurerExtension;
import me.vodarga.api.service.RestAssuredConfigurer;
import me.vodarga.core.allure.Layer;
import me.vodarga.core.junit.extension.SuiteExtension;
import me.vodarga.core.test.BaseTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.RegisterExtension;

@Layer(API)
public abstract class ApiBaseTest extends BaseTest {

  @RegisterExtension
  private static final SuiteExtension suiteExtension = new ConfigurerExtension(new RestAssuredConfigurer());
  protected final SoftAssertions softly = new SoftAssertions();

  @AfterEach
  void tearDown() {
    softly.assertAll();
  }

}
