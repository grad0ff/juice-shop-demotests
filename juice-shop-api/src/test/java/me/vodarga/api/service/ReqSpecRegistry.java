package me.vodarga.api.service;

import io.restassured.specification.RequestSpecification;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.vodarga.api.enums.UserType;
import me.vodarga.api.model.AuthData;
import me.vodarga.api.restassured.spec.factory.AdminReqSpecFactory;
import me.vodarga.api.restassured.spec.factory.NoUserReqSpecFactory;
import me.vodarga.api.restassured.spec.factory.ReqSpecFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ReqSpecRegistry {

  private static final Map<UserType, RequestSpecification> specs = new ConcurrentHashMap<>();

  public static RequestSpecification getSpec(UserType userType) {
    return specs.computeIfAbsent(userType, key -> getReqSpecFactory(key).createSpec());
  }

  private static ReqSpecFactory getReqSpecFactory(UserType userType) {
    AuthData authData = AuthDataProvider.getData(userType);
    return switch (userType) {
      case ADMIN -> new AdminReqSpecFactory(authData);
      case NO_USER -> new NoUserReqSpecFactory(authData);
    };

  }

}
