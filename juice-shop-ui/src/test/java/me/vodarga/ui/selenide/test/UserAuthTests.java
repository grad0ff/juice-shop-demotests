package me.vodarga.ui.selenide.test;

import static me.vodarga.core.config.CoreConfig.CORE_CFG;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import java.util.stream.Stream;
import me.vodarga.core.allure.AllureSteps;
import me.vodarga.core.allure.Requirement;
import me.vodarga.ui.selenide.test.step.UserAuthTestsSteps;
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
public class UserAuthTests extends SelenideUiBaseTest {

  private final UserAuthTestsSteps steps = new UserAuthTestsSteps();

  public static Stream<Arguments> failedAuthWithInvalidCredsData() {
    return Stream.of(
        argumentSet("Неправильный логин", faker.name().username(), CORE_CFG.userPassword()),
        argumentSet("Неправильный пароль", CORE_CFG.userEmail(), faker.internet().password())
    );
  }

  @Test
  @AllureId("137")
  @Requirement("AUTH-001-001")
  @DisplayName("Авторизация под существующим пользователем")
  @Description("Проверяется авторизация с валидными логином и паролем пользователя")
  void successAuthWithValidCreds() {
    AllureSteps.actionStep();
    steps.openLogInPage();
    steps.authorize(CORE_CFG.userEmail(), CORE_CFG.userPassword());

    AllureSteps.assertionStep();
    steps.assertSuccessAuth();
  }

  @ParameterizedTest
  @AllureId("138")
  @Requirement("AUTH-001-002")
  @MethodSource("failedAuthWithInvalidCredsData")
  @DisplayName("Попытка авторизации с некорректными учетными данными")
  @Description("Проверяется попытка авторизации с некорректным логином или паролем пользователя")
  void failedAuthWithInvalidCreds(String username, String password) {
    AllureSteps.actionStep();
    steps.openLogInPage();
    steps.authorize(username, password);

    AllureSteps.assertionStep();
    steps.assertFailedAuth();
  }

  @Test
  @AllureId("139")
  @Requirement("AUTH-001-002")
  @DisplayName("Появление предупреждений при пустых логине и пароле")
  @Description("Проверяется появление предупреждений о необходимости заполнения полей при пустых логине и пароле пользователя")
  void showWarningsWhenSetEmptyCreds() {
    AllureSteps.actionStep();
    steps.openLogInPage();
    steps.fillUsernameAndPassword("\n", "\n");

    AllureSteps.assertionStep();
    steps.assertWarningsAppear();
  }

}
