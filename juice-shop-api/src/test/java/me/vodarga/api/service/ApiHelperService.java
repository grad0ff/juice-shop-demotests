package me.vodarga.api.service;

import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import me.vodarga.api.client.HttpClient;
import me.vodarga.api.dto.SecurityQuestionDto;
import me.vodarga.api.dto.UserDto;
import me.vodarga.api.dto.UserRegisterDto;
import me.vodarga.core.enums.SecurityQuestionType;

@RequiredArgsConstructor
public class ApiHelperService {

  private final HttpClient httpClient;

  @Step("Зарегистрировать пользователя")
  public void registerUser(UserRegisterDto registerDto) {
    httpClient.postUsers(registerDto);
  }

  @Step("Авторизоваться под пользователем")
  public void loginUser(UserDto userCreateDto) {
    httpClient.postUsers(userCreateDto);
  }

  public SecurityQuestionDto getSecurityQuestion(SecurityQuestionType questionType) {
    return httpClient.getSecurityQuestions()
        .as("find{it.question=='%s'}".formatted(questionType.question()), SecurityQuestionDto.class);
  }

}
