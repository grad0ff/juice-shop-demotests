package me.vodarga.api.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import me.vodarga.api.restassured.RequestResponseFilter;
import me.vodarga.core.config.Configurer;

public class RestAssuredConfigurer implements Configurer {

  @Override
  public void configure() {
    RestAssured.requestSpecification = new RequestSpecBuilder()
        .addFilter(new RequestResponseFilter())
        .build();
  }

}
