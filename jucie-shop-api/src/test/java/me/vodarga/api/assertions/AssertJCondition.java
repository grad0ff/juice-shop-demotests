package me.vodarga.api.assertions;

import java.util.Arrays;
import org.assertj.core.api.Condition;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.Matcher;

/**
 * Utility класс для предоставления AssertJ Condition.
 */
public class AssertJCondition extends SoftAssertions {

  public static Condition<ProcessingResponse> statusCode(int expected) {
    return new Condition<>() {
      private int actual;

      @Override
      public boolean matches(ProcessingResponse value) {
        actual = value.getResponse().getStatusCode();
        return actual == expected;
      }

      @Override
      public String toString() {
        return "status code " + expected + " but actual is " + actual;
      }
    };
  }

  public static Condition<ProcessingResponse> statusCodeIn(Integer... expected) {
    return new Condition<>() {
      private int actual;

      @Override
      public boolean matches(ProcessingResponse value) {
        actual = value.getResponse().getStatusCode();
        return Arrays.asList(expected).contains(actual);
      }

      @Override
      public String toString() {
        return "status code in " + Arrays.toString(expected) + " but actual is" + actual;
      }
    };
  }

  public static Condition<ProcessingResponse> emptyBody() {
    return new Condition<>() {
      private String actual;

      @Override
      public boolean matches(ProcessingResponse value) {
        actual = value.getResponse().asString();
        return actual.isEmpty();
      }

      @Override
      public String toString() {
        return " empty body but actual is" + actual;
      }
    };
  }

  public static <T> Condition<ProcessingResponse> bodyField(String jsonPath, Matcher<T> matcher) {
    return new Condition<>() {
      private T actual;

      @Override
      public boolean matches(ProcessingResponse value) {
        actual = value.getResponse().jsonPath().get(jsonPath);
        return matcher.matches(actual);
      }

      @Override
      public String toString() {
        return " empty body but actual is" + actual;
      }
    };
  }

}
