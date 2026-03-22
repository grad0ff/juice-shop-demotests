package me.vodarga.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigFactory;

@Sources("classpath:api.properties")
public interface ApiConfig extends Config {

  ApiConfig API_CFG = ConfigFactory.create(ApiConfig.class);

  @Key("base.path")
  String basePath();

}
