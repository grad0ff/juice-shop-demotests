package me.vodarga.api.enums;

import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import lombok.RequiredArgsConstructor;
import me.vodarga.api.config.AuthData;

@RequiredArgsConstructor
public class NoUserReqSpecFactory extends ReqSpecFactory {

  private final AuthData authData;

  @Override
  RequestSpecBuilder initBuilder() {
    return new RequestSpecBuilder()
        .setBaseUri(authData.url())
        .setBasePath(authData.apiPath());
  }

}
