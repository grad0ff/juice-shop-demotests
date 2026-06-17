package me.vodarga.core.data;

import static me.vodarga.core.config.CoreConfig.CORE_CFG;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.experimental.UtilityClass;
import me.vodarga.core.enums.RoleType;
import me.vodarga.core.model.User;

@UtilityClass
public final class UserRegistry {

  private static final Map<RoleType, User> store = new ConcurrentHashMap<>();

  public static User getUser(RoleType roleType) {
    return store.computeIfAbsent(roleType, key -> switch (roleType) {
      case ADMIN -> new User(CORE_CFG.adminEmail(), CORE_CFG.adminPassword());
      case NO_USER -> null;
    });
  }

}
