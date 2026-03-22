package me.vodarga.api.test;

import me.vodarga.api.extension.RestAssuredConfigExtension;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(RestAssuredConfigExtension.class)
public abstract class ApiBaseTest {

  protected final SoftAssertions softly = new SoftAssertions();

  @AfterEach
  void tearDown() {
    softly.assertAll();
  }

}
