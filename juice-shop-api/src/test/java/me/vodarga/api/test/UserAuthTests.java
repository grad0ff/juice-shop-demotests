package me.vodarga.api.test;

import static io.qameta.allure.Allure.step;
import static me.vodarga.api.assertions.AssertJCondition.statusCode;
import static me.vodarga.core.config.CoreConfig.CORE_CFG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.specification.RequestSpecification;
import java.util.stream.Stream;
import me.vodarga.api.assertions.ProcessingResponse;
import me.vodarga.api.client.HttpClient;
import me.vodarga.api.enums.UserType;
import me.vodarga.api.model.AuthenticationDto;
import me.vodarga.api.service.ReqSpecRegistry;
import me.vodarga.core.allure.AllureSteps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@Feature("user")
@Story("/user/login")
@Tags({@Tag("user"), @Tag("login")})
public class UserAuthTests extends ApiBaseTest {

  private static final String INVALID_CREDS_TEXT = "Invalid email or password.";
  private HttpClient httpClient;

  public static Stream<Arguments> failedAuthWithInvalidCredsData() {
    return Stream.of(
        argumentSet("Несуществующий логин", faker.name().username(), CORE_CFG.userPassword()),
        argumentSet("Несуществующий пароль", CORE_CFG.userEmail(), faker.internet().password()),
        argumentSet("Пустой логин",  CORE_CFG.userEmail(), ""),
        argumentSet("Пустой пароль", "", CORE_CFG.userPassword()));
  }

  @Test
  @AllureId("103")
  @DisplayName("Авторизация под существующим пользователем")
  @Description("Проверяется авторизация с валидными логином и паролем пользователя")
  void successAuthWithValidCreds() {
    httpClient = new HttpClient(ReqSpecRegistry.getSpec(UserType.NO_USER));

    AllureSteps.actionStep();
    ProcessingResponse response = step("Авторизоваться по логину и паролю", () ->
        httpClient.postUserLogin(CORE_CFG.userEmail(), CORE_CFG.userPassword()));

    AllureSteps.assertionStep();
    step("Проверить успешность авторизации", () -> {
      step("Проверить код ответа", () -> assertThat(response).has(statusCode(200)));
      step("Проверить поля тела ответа", () -> {
        var actual = response.as(AuthenticationDto.class);
        softly.assertThat(actual.getAuthentication()).isNotNull();
        softly.assertThat(actual.getAuthentication().getToken()).isNotBlank();
        softly.assertThat(actual.getAuthentication().getBid()).isGreaterThan(0);
        softly.assertThat(actual.getAuthentication().getUmail()).isEqualTo(CORE_CFG.userEmail());
      });
    });
  }

  @ParameterizedTest
  @AllureId("104")
  @MethodSource("failedAuthWithInvalidCredsData")
  @DisplayName("Попытка авторизации с некорректными учетными данными")
  @Description("Проверяется попытка авторизации с некорректным логином или паролем пользователя")
  void failedAuthWithInvalidCreds(String username, String password) {
    RequestSpecification spec = ReqSpecRegistry.getSpec(UserType.NO_USER).noFilters();
    httpClient = new HttpClient(spec);

    AllureSteps.actionStep();
    ProcessingResponse response = step("Попытаться авторизоваться по логину и паролю", () ->
        httpClient.postUserLogin(username, password));

    AllureSteps.arrangeStep();
    step("Проверить невозможность авторизации", () -> {
      step("Проверить код ответа", () -> assertThat(response).has(statusCode(401)));
      step("Проверить отсутствие тела ответа",
          () -> assertThat(response.getResponse().getBody().asString()).isEqualTo(INVALID_CREDS_TEXT));
    });
  }

}
