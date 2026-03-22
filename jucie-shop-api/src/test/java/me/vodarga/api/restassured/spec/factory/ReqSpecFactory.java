package me.vodarga.api.restassured.spec.factory;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public abstract class ReqSpecFactory {

  public RequestSpecification createSpec() {
    return initBuilder()
        .build();
  }

  abstract RequestSpecBuilder initBuilder();

}
