package me.vodarga.api.enums;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class ReqSpecFactory {

  abstract RequestSpecBuilder initBuilder();

  public RequestSpecification create() {
    return initBuilder()
        .setContentType(ContentType.JSON)
        .setAccept(ContentType.JSON)
        .build();
  }

}
