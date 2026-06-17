package me.vodarga.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {

  @JsonProperty("email")
  private String email;

  @JsonProperty("password")
  private String password;

  @JsonProperty("securityQuestion")
  private SecurityQuestionDto securityQuestion;

  @JsonProperty("securityAnswer")
  private String securityAnswer;

  @JsonProperty("passwordRepeat")
  private String passwordRepeat;

}