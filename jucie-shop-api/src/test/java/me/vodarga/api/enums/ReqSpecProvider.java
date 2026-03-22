package me.vodarga.api.enums;

import io.restassured.specification.RequestSpecification;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.vodarga.api.config.AuthData;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ReqSpecProvider {

  private static final Map<UserType, RequestSpecification> specs = new ConcurrentHashMap<>();

  public static RequestSpecification getSpec(UserType userType) {
    return specs.computeIfAbsent(userType, key -> getReqSpecFactory(key).create());
  }

  private static ReqSpecFactory getReqSpecFactory(UserType userType) {
    AuthData authData = AuthDataProvider.getData(userType);
    return switch (userType) {
      case ADMIN -> new AdminReqSpecFactory(authData);
    };

  }

}
