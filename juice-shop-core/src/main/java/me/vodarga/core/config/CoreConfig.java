package me.vodarga.core.config;

import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigFactory;

/**
 * Общие настройки модулей
 */
@Sources("classpath:core.properties")
public interface CoreConfig extends org.aeonbits.owner.Config {

  CoreConfig CORE_CFG = ConfigFactory.create(CoreConfig.class);

  @Key("url")
  String baseUrl();

  @Key("email")
  String userEmail();

  @Key("password")
  String userPassword();

}
