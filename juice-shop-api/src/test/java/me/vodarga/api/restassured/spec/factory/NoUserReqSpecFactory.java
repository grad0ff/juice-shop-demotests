package me.vodarga.api.restassured.spec.factory;

import io.restassured.builder.RequestSpecBuilder;

public final class NoUserReqSpecFactory extends ReqSpecFactory {

  public NoUserReqSpecFactory(String baseUr, String basePath) {
    super(baseUr, basePath);
  }

  @Override
  RequestSpecBuilder initBuilder() {
    return new RequestSpecBuilder();
  }

}
