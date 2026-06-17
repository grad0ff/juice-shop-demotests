package me.vodarga.api.data;

import static me.vodarga.api.config.ApiConfig.API_CFG;
import static me.vodarga.core.config.CoreConfig.CORE_CFG;

import io.restassured.specification.RequestSpecification;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.experimental.UtilityClass;
import me.vodarga.api.restassured.spec.factory.NoUserReqSpecFactory;
import me.vodarga.api.restassured.spec.factory.ReqSpecFactory;
import me.vodarga.api.restassured.spec.factory.UserReqSpecFactory;
import me.vodarga.core.data.UserRegistry;
import me.vodarga.core.enums.RoleType;
import me.vodarga.core.model.User;

@UtilityClass
public final class ReqSpecRegistry {

  private static final Map<RoleType, RequestSpecification> specs = new ConcurrentHashMap<>();

  public static RequestSpecification getSpec(RoleType roleType) {
    return specs.computeIfAbsent(roleType, key -> {
      User user = UserRegistry.getUser(key);
      ReqSpecFactory factory = switch (key) {
        case ADMIN -> new UserReqSpecFactory(CORE_CFG.baseUrl(), API_CFG.baseApiPath(), user);
        case NO_USER -> new NoUserReqSpecFactory(CORE_CFG.baseUrl(), API_CFG.baseApiPath());
      };
      return factory.createSpec();
    });
  }

}
