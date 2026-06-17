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
import me.vodarga.api.data.ReqSpecRegistry;
import me.vodarga.api.dto.AuthenticationDto;
import me.vodarga.api.dto.UserDto;
import me.vodarga.core.allure.AllureSteps;
import me.vodarga.core.allure.Requirement;
import me.vodarga.core.enums.RoleType;
import org.junit.jupiter.api.BeforeAll;
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
  private static HttpClient httpClient;

  @BeforeAll
  static void beforeAll() {
    RequestSpecification spec = ReqSpecRegistry.getSpec(RoleType.NO_USER);
    httpClient = new HttpClient(spec);
  }

  public static Stream<Arguments> failedAuthWithInvalidCredsData() {
    return Stream.of(
        argumentSet("Неправильный логин", faker.name().username(), CORE_CFG.adminPassword()),
        argumentSet("Неправильный пароль", CORE_CFG.adminEmail(), faker.internet().password()),
        argumentSet("Пустой логин", "", CORE_CFG.adminPassword()),
        argumentSet("Пустой пароль", CORE_CFG.adminEmail(), ""));
  }

  @Test
  @AllureId("103")
  @Requirement("AUTH-001-001")
  @DisplayName("Авторизация под существующим пользователем")
  @Description("Проверяется авторизация с валидными логином и паролем пользователя")
  void successAuthWithValidCreds() {
    var user = new UserDto(CORE_CFG.adminEmail(), CORE_CFG.adminPassword());

    AllureSteps.actionStep();
    ProcessingResponse response = step("Авторизоваться по логину и паролю", () -> httpClient.postUserLogin(user));

    AllureSteps.assertionStep();
    step("Проверить успешность авторизации", () -> {
      step("Проверить код ответа", () -> assertThat(response).has(statusCode(200)));
      step("Проверить поля тела ответа", () -> {
        var actual = response.as(AuthenticationDto.class);
        softly.assertThat(actual.getAuthentication()).isNotNull();
        softly.assertThat(actual.getAuthentication().getToken()).isNotBlank();
        softly.assertThat(actual.getAuthentication().getBid()).isGreaterThan(0);
        softly.assertThat(actual.getAuthentication().getUmail()).isEqualTo(CORE_CFG.adminEmail());
      });
    });
  }

  @ParameterizedTest
  @AllureId("104")
  @Requirement("AUTH-001-002")
  @MethodSource("failedAuthWithInvalidCredsData")
  @DisplayName("Попытка авторизации с некорректными учетными данными")
  @Description("Проверяется попытка авторизации с некорректным логином или паролем пользователя")
  void failedAuthWithInvalidCreds(String email, String password) {
    var user = new UserDto(email, password);

    AllureSteps.actionStep();
    ProcessingResponse response = step("Попытаться авторизоваться по логину и паролю", () ->
        httpClient.postUserLogin(user));

    AllureSteps.arrangeStep();
    step("Проверить невозможность авторизации", () -> {
      step("Проверить код ответа", () -> assertThat(response).has(statusCode(401)));
      step("Проверить отсутствие тела ответа",
          () -> assertThat(response.getResponse().getBody().asString()).isEqualTo(INVALID_CREDS_TEXT));
    });
  }

}
