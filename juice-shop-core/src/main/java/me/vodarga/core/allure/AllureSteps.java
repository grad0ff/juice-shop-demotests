package me.vodarga.core.allure;

import static io.qameta.allure.Allure.step;

import lombok.experimental.UtilityClass;

/**
 * Класс с верхнеуровневыми шагами теста для отображения в Allure Report
 */
@UtilityClass
public class AllureSteps {

  public static void arrangeStep() {
    step("ARRANGE");
  }

  public static void actionStep() {
    step("ACTION");
  }

  public static void assertionStep() {
    step("ASSERTION");
  }

}
