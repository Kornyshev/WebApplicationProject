package me.kornyshev.WebCalculator.automation.api.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import me.kornyshev.WebCalculator.properties.Properties;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;

@Slf4j
public class ApiClient {

    private final RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
            .setConfig(newConfig().encoderConfig(encoderConfig().defaultContentCharset(StandardCharsets.UTF_8)))
            .setBaseUri(Properties.BASE_URL)
            .setRelaxedHTTPSValidation()
            .log(LogDetail.ALL);
    private RequestSpecification requestSpecification;

    public Response sendRequest(Method method, int expectedStatusCode, String url, Object... params) {
        return sendRequest(method, true, expectedStatusCode, url, params);
    }

    public Response sendRequest(Method method, boolean redirection, int expectedStatusCode, String url, Object... params) {
        Response response = given().spec(requestSpecification).redirects().follow(redirection)
                .request(method, url, params).then().log().status().extract().response();
        if (expectedStatusCode != -1) {
            if (response.statusCode() != expectedStatusCode) {
                log.info(response.prettyPrint());
            }
            response = response.then().statusCode(expectedStatusCode).extract().response();
        }
        return response;
    }

    public ApiClient build() {
        requestSpecification = requestSpecBuilder.build();
        return this;
    }

    public ApiClient addQueryParam(String name, String value) {
        requestSpecBuilder.addQueryParam(name, value);
        return this;
    }

    public ApiClient addBody(String body) {
        requestSpecBuilder.setBody(body);
        return this;
    }

    public ApiClient addBody(Object body) {
        requestSpecBuilder.setBody(body);
        return this;
    }

    public ApiClient addFormParam(String paramName, String paramValue) {
        requestSpecBuilder.addFormParam(paramName, paramValue);
        return this;
    }

    public ApiClient addFile(File file) {
        requestSpecBuilder.addMultiPart(file);
        return this;
    }

    public ApiClient addJsonContentType() {
        requestSpecBuilder.addHeader("Content-Type", "application/json");
        return this;
    }

    public ApiClient addFormContentType() {
        requestSpecBuilder.addHeader("Content-Type", "application/x-www-form-urlencoded");
        return this;
    }

}
