package me.vodarga.api.service;

import static me.vodarga.api.config.ApiConfig.API_CFG;
import static me.vodarga.core.config.CoreConfig.CORE_CFG;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import me.vodarga.api.enums.UserType;
import me.vodarga.api.model.AuthData;
import me.vodarga.api.model.User;

public class AuthDataProvider {

  private static final Map<UserType, AuthData> authData = new ConcurrentHashMap<>();

  public static AuthData getData(UserType userType) {
    return switch (userType) {
      case ADMIN -> authData.computeIfAbsent(userType,
          key -> new AuthData(CORE_CFG.baseUrl(), API_CFG.baseApiPath(),
              new User(CORE_CFG.userEmail(), CORE_CFG.userPassword())));
      case NO_USER -> authData.computeIfAbsent(userType,
          key -> new AuthData(CORE_CFG.baseUrl(), API_CFG.baseApiPath(), null));
    };
  }

}
