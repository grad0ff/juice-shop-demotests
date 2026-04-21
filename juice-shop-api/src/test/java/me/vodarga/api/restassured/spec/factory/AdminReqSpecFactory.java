package me.vodarga.api.restassured.spec.factory;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import me.vodarga.api.model.User;
import me.vodarga.api.restassured.StatusCodeBasedFilter;

public class AdminReqSpecFactory extends ReqSpecFactory {

  private final User user;

  public AdminReqSpecFactory(String baseUr, String basePath, User user) {
    super(baseUr, basePath);
    this.user = user;
  }

  @Override
  RequestSpecBuilder initBuilder() {
    var authScheme = new BasicAuthScheme();
    authScheme.setUserName(user.name());
    authScheme.setPassword(user.password());
    return new RequestSpecBuilder()
        .addFilter(new StatusCodeBasedFilter(greaterThanOrEqualTo(500)))
        .setAuth(authScheme);
  }

}
