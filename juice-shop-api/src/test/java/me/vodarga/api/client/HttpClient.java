package me.vodarga.api.client;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static me.vodarga.api.client.endpoint.AppEndpoint.SECURITY_QUESTIONS;
import static me.vodarga.api.client.endpoint.AppEndpoint.USERS;
import static me.vodarga.api.client.endpoint.AppEndpoint.USER_LOGIN;

import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import me.vodarga.api.assertions.ProcessingResponse;

/**
 * Http клиент для взаимодействия с API
 */
@RequiredArgsConstructor
public class HttpClient {

  private final RequestSpecification spec;

  public ProcessingResponse postUsers(Object body) {
    return ProcessingResponse.of(
        given()
            .spec(spec)
            .body(body)
            .contentType(JSON)
            .post(USERS)
    );
  }

  public ProcessingResponse postUserLogin(Object body) {
    return ProcessingResponse.of(
        given()
            .spec(spec)
            .body(body)
            .contentType(JSON)
            .post(USER_LOGIN));
  }

  public ProcessingResponse getSecurityQuestions() {
    return ProcessingResponse.of(
        given()
            .spec(spec)
            .accept(JSON)
            .get(SECURITY_QUESTIONS));
  }

}
