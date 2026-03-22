package me.vodarga.api.enums;

import static me.vodarga.config.ApiConfig.API_CFG;
import static me.vodarga.core.config.CoreConfig.CORE_CFG;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import me.vodarga.api.config.AuthData;
import me.vodarga.api.config.User;

public class AuthDataProvider {

  private static final Map<UserType, AuthData> authData = new ConcurrentHashMap<>();

  public static AuthData getData(UserType userType) {
    return switch (userType) {
      case ADMIN -> authData.computeIfAbsent(userType,
          key -> new AuthData(CORE_CFG.baseUrl(), API_CFG.basePath(), new User(CORE_CFG.userName(),
              CORE_CFG.userPassword())));
      case NO_USER -> new AuthData(CORE_CFG.baseUrl(), API_CFG.basePath(), null);
    };
  }

}
