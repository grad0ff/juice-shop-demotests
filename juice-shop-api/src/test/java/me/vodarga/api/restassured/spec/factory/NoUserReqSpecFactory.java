package me.vodarga.api.restassured.spec.factory;

import io.restassured.builder.RequestSpecBuilder;
import lombok.RequiredArgsConstructor;
import me.vodarga.api.model.AuthData;

@RequiredArgsConstructor
public final class NoUserReqSpecFactory extends ReqSpecFactory {

  private final AuthData authData;

  @Override
  RequestSpecBuilder initBuilder() {
    return new RequestSpecBuilder()
        .setBaseUri(authData.url())
        .setBasePath(authData.apiPath());
  }

}
