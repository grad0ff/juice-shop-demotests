package me.vodarga.api.restassured.spec.factory;

import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import lombok.RequiredArgsConstructor;
import me.vodarga.api.model.AuthData;

@RequiredArgsConstructor
public class AdminReqSpecFactory extends ReqSpecFactory {

  private final AuthData authData;

  @Override
  RequestSpecBuilder initBuilder() {
    var authScheme = new BasicAuthScheme();
    authScheme.setUserName(authData.user().name());
    authScheme.setPassword(authData.user().password());
    return new RequestSpecBuilder()
        .setBaseUri(authData.url())
        .setBasePath(authData.apiPath())
        .setAuth(authScheme);
  }

}
