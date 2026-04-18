package me.vodarga.api.restassured;

import static io.restassured.internal.print.RequestPrinter.print;
import static io.restassured.internal.print.ResponsePrinter.print;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import java.util.HashSet;
import java.util.Set;

public class RequestResponseFilter implements Filter {

  private static final Set<String> blacklistedHeaders = new HashSet<>();

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

  private void printRequest(FilterableRequestSpecification spec) {
    print(spec, spec.getMethod(), spec.getURI(), LogDetail.ALL, blacklistedHeaders, System.out, true);
  }

  private void printResponse(Response response) {
    print(response, response.getBody(), System.out, LogDetail.ALL, true, blacklistedHeaders);
  }

}