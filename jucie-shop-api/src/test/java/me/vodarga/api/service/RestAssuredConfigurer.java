package me.vodarga.api.service;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import java.util.Map;
import me.vodarga.core.config.Configurer;

public class RestAssuredConfigurer implements Configurer {

  private static final int REQUEST_TIMEOUT = 10_000;

  @Override
  public void configure() {
    RestAssured.config = restAssuredConfig();
    RestAssured.requestSpecification = new RequestSpecBuilder()
        .addFilter(allureRestAssuredFilter())
        .build();
  }

  private RestAssuredConfig restAssuredConfig() {
    return RestAssuredConfig.newConfig()
        .httpClient(clientConfig());
  }

  private HttpClientConfig clientConfig() {
    return HttpClientConfig.httpClientConfig()
        .setParams(Map.of(
            "http.socket.timeout", REQUEST_TIMEOUT,
            "http.connection.timeout", REQUEST_TIMEOUT,
            "http.request.timeout", REQUEST_TIMEOUT
        ));
  }

  private Filter allureRestAssuredFilter() {
    return new AllureRestAssured() // HTML шаблон для Allure
        .setRequestTemplate("custom-http-request.ftl")
        .setResponseTemplate("custom-http-response.ftl");
  }

}
