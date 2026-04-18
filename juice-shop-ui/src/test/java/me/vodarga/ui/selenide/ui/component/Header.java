package me.vodarga.ui.selenide.ui.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class Header {

  private final SelenideElement accountLabel = $("#navbarAccount");
  private final SelenideElement logInButton = $("#navbarLoginButton");
  private final SelenideElement logOutButton = $("#navbarLogoutButton");

}
