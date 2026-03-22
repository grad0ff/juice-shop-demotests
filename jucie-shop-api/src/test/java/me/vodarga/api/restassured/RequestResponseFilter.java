package me.vodarga.api.restassured;

import static io.restassured.internal.print.RequestPrinter.print;
import static io.restassured.internal.print.ResponsePrinter.print;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import java.io.PrintStream;

public class RequestResponseFilter implements Filter {

  private static final PrintStream printStream = System.out;
  private static final LogDetail logDetail = LogDetail.ALL;

  @Override
  public Response filter(FilterableRequestSpecification requestSpec,
      FilterableResponseSpecification responseSpec, FilterContext ctx) {
    Response response = ctx.next(requestSpec, responseSpec);
    if (response.statusCode() >= 400) {
      printRequest(requestSpec);
      printResponse(response);
    }
    return response;
  }

  private void printResponse(Response response) {
    print(response, response.getBody(), printStream, logDetail, true, null);
  }

  private void printRequest(FilterableRequestSpecification requestSpec) {
    print(requestSpec, requestSpec.getMethod(), requestSpec.getURI(), logDetail, null, printStream, true);
  }

}