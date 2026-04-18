package me.vodarga.api.restassured.spec.factory;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import me.vodarga.api.restassured.RequestResponseFilter;

public abstract class ReqSpecFactory {

  public RequestSpecification createSpec() {
    return initBuilder()
        .addFilter(new RequestResponseFilter())
        .build();
  }

  abstract RequestSpecBuilder initBuilder();

}
