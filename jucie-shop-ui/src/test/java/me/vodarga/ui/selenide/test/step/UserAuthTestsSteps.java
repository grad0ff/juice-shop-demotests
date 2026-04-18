package me.vodarga.ui.selenide.test.step;

import static com.codeborne.selenide.Condition.appear;
import static org.openqa.selenium.Keys.ESCAPE;
import static org.openqa.selenium.Keys.TAB;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import me.vodarga.ui.selenide.utils.SelenideActions;
import me.vodarga.ui.selenide.ui.component.WelcomeBanner;
import me.vodarga.ui.selenide.ui.page.IndexPage;
import me.vodarga.ui.selenide.ui.page.LoginPage;
import me.vodarga.ui.selenide.test.UserAuthTests;

/**
 * Шаги тестов класса {@link UserAuthTests}
 */
public class UserAuthTestsSteps {

  private static final String AUTH_ERROR_TEXT = "Invalid email or password.";
  private static final String EMPTY_USER_WARN_TEXT = "Please provide an email address.";
  private static final String EMPTY_PSWRD_WARN_TEXT = "Please provide a password.";
  private final IndexPage indexPage = new IndexPage();
  private final LoginPage loginPage = new LoginPage();
  private final WelcomeBanner welcomeBanner = new WelcomeBanner();

  @Step("Открыть страницу авторизации")
  public void openLogInPage() {
    Selenide.open(indexPage.pagePath());
    welcomeBanner.banner().should(appear);
    SelenideActions.pressKey(ESCAPE);
    indexPage.header().accountLabel().click();
    indexPage.header().logInButton().click();
  }

  @Step("Авторизоваться по логину и паролю")
  public void authorize(String username, String password) {
    fillUsernameAndPasswordFields(username, password);
    loginPage.logInButton().click();
  }

  private void fillUsernameAndPasswordFields(String username, String password) {
    loginPage.emailInput().setValue(username);
    loginPage.passwordInput().setValue(password);
  }

  @Step("Заполнить имя пользователя и пароль")
  public void fillUsernameAndPassword(String username, String password) {
    fillUsernameAndPasswordFields(username, password);
  }

  @Step("Проверить успешность авторизации")
  public void assertSuccessAuth() {
    indexPage.header().accountLabel().click();
    indexPage.header().logOutButton().should(appear);
  }

  @Step("Проверить ошибку авторизации")
  public void assertFailedAuth() {
    loginPage.errorLabel(AUTH_ERROR_TEXT).should(appear);
  }

  @Step("Проверить появление предупреждений")
  public void assertWarningsAppear() {
    SelenideActions.pressKey(TAB);
    loginPage.warningLabel(EMPTY_USER_WARN_TEXT).should(appear);
    loginPage.warningLabel(EMPTY_PSWRD_WARN_TEXT).should(appear);
  }

}
