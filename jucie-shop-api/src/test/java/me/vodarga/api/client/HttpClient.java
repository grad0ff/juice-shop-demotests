package me.vodarga.api.client;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import io.restassured.specification.RequestSpecification;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import me.vodarga.api.assertions.ProcessingResponse;

/**
 * Http клиент для взаимодействия с API
 */
@RequiredArgsConstructor
public class HttpClient {

  private final RequestSpecification spec;

  public ProcessingResponse postUserLogin(String email, String password) {
    var body = Map.of("email", email, "password", password);
    return ProcessingResponse.of(
        given()
            .spec(spec)
            .body(body)
            .contentType(JSON)
            .post("/user/login"));
  }

}
