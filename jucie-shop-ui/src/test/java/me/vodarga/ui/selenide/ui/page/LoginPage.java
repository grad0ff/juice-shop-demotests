package me.vodarga.ui.selenide.ui.page;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class LoginPage extends BasePage {

  private final String pagePath = "/login";

  public SelenideElement emailInput() {
    return $("#email");
  }

  public SelenideElement passwordInput() {
    return $("#password");
  }

  public SelenideElement logInButton() {
    return $("#loginButton");
  }

  public SelenideElement errorLabel(String text) {
    return $(".error").parent().$(withText(text));
  }

  public SelenideElement warningLabel(String text) {
    return $(byTagAndText("mat-error", text));
  }

}
