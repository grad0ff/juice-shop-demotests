package me.vodarga.api.assertions;

import io.restassured.response.Response;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Condition;

/**
 * Класс для обработки объекта Response HTTP-запроса
 */
@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class ProcessingResponse {

  @Getter
  private final Response response;

  /**
   * Проверить соответствие Response переданному условию
   *
   * @param condition проверяемое условие {@link Condition}
   * @return сам объект класса ProcessingResponse
   */
  public ProcessingResponse check(Condition<ProcessingResponse> condition) {
    log.info("Проверить Response на выполнение условия");
    condition.matches(this);
    return this;
  }

  public <T> T as(Class<T> tClass) {
    log.debug("Выполнить десериализацию JSON");
    return response.as(tClass);
  }

  public <T> T as(String jsonPath, Class<T> tClass) {
    log.debug("Выполнить десериализацию JSON по пути '{}'", jsonPath);
    return response.jsonPath().getObject(jsonPath, tClass);
  }

  public <T> List<T> asList(String jsonPath, Class<T> tClass) {
    log.debug("Выполнить десериализацию JSON по пути '{}' в список", jsonPath);
    List<T> list = response.jsonPath().getList(jsonPath, tClass);
    if (Objects.nonNull(list)) {
      return list;
    }
    log.error("Ошибка десериализации JSON по пути '{}' - возвращен пустой список", jsonPath);
    return Collections.emptyList();
  }

  @Override
  public String toString() {
    return getClass().getSimpleName();
  }

}
