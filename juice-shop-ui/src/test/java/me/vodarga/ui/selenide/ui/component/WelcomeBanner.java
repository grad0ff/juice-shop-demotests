package me.vodarga.ui.selenide.ui.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class WelcomeBanner {

  private final SelenideElement banner = $("app-welcome-banner");

}
