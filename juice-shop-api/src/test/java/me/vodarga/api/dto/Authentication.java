package me.vodarga.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Authentication {

  @JsonProperty("bid")
  private int bid;

  @JsonProperty("token")
  private String token;

  @JsonProperty("umail")
  private String umail;
}