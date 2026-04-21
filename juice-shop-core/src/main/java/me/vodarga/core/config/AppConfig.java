package me.vodarga.core.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigFactory;

/**
 * Настройки приложения Juice Shop
 */
@Sources("classpath:jshop.properties")
public interface AppConfig extends Config {

  AppConfig APP_CFG = ConfigFactory.create(AppConfig.class);

  @Key("image")
  String image();

  @Key("port")
  int port();

}
