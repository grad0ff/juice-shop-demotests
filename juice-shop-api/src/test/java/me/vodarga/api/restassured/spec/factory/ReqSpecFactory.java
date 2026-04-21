package me.vodarga.api.restassured.spec.factory;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ReqSpecFactory {

  private final String baseUr;
  private final String basePath;

  public RequestSpecification createSpec() {
    return initBuilder()
        .setBaseUri(baseUr)
        .setBasePath(basePath)
        .build();
  }

  abstract RequestSpecBuilder initBuilder();

}
