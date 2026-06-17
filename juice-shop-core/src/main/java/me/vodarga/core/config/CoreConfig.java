package me.vodarga.core.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigFactory;

/**
 * Общие настройки модулей
 */
@Sources("classpath:core.properties")
public interface CoreConfig extends Config {

  CoreConfig CORE_CFG = ConfigFactory.create(CoreConfig.class);

  @Key("app.url")
  String baseUrl();

  @Key("app.admin.email")
  String adminEmail();

  @Key("app.admin.password")
  String adminPassword();

}
