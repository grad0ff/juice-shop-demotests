package me.vodarga.ui.selenide.utils;

import com.codeborne.selenide.Selenide;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.Keys;

/**
 * Утилитный класс для работы с Selenide. Предоставляет упрощенный способ взаимодействия с браузером и веб-элементами
 */
@UtilityClass
public class SelenideActions {

  public static void pressKey(String keys) {
    Selenide.actions().sendKeys(keys).perform();
  }

  public static void pressKey(Keys keys) {
    pressKey(keys.toString());
  }

}
