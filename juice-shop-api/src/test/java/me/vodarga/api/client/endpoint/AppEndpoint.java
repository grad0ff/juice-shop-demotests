package me.vodarga.api.client.endpoint;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppEndpoint {

  public static final String USERS = "/Users";
  public static final String USER_LOGIN = "/user/login";
  public static final String SECURITY_QUESTIONS = "/SecurityQuestions";

}
