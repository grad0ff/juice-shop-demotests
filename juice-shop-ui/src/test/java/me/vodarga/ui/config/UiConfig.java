package me.vodarga.ui.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigFactory;

/**
 * Настройки модуля тестов UI
 */
@Sources("classpath:ui.properties")
public interface UiConfig extends Config {

  UiConfig UI_CFG = ConfigFactory.create(UiConfig.class);

  @Key("base.path")
  String basePath();

}
