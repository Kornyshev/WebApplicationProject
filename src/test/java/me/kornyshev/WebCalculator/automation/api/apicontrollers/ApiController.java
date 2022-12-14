package me.kornyshev.WebCalculator.automation.api.apicontrollers;

import io.restassured.http.Method;
import io.restassured.response.Response;
import me.kornyshev.WebCalculator.automation.api.client.ApiClient;

public class ApiController {

    public Response executePutMethod(Object body) {
        String endpoint = "";
        return new ApiClient().addJsonContentType().addBody(body).build()
                .sendRequest(Method.PUT, 204, endpoint);
    }

}
