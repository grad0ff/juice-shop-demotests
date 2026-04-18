package me.vodarga.api.test;

import me.vodarga.api.extension.RestAssuredConfigExtension;
import me.vodarga.core.allure.Layer;
import me.vodarga.core.test.BaseTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;

@Layer("API")
@ExtendWith(RestAssuredConfigExtension.class)
public abstract class ApiBaseTest extends BaseTest {

  protected final SoftAssertions softly = new SoftAssertions();

  @AfterEach
  void tearDown() {
    softly.assertAll();
  }

}
