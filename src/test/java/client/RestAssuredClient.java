package client;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public abstract class RestAssuredClient {
    String baseUrl;
    RequestSpecification httpRequest;

    public RestAssuredClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Response get(String path, Map<String, Object> params, Map<String, Object> headers, Object body) {
        setHttpRequest(params, headers, body);
        Response response = httpRequest.get(path);
        response.then().log().body();
        return response;
    }

    public Response post(String path, Map<String, Object> params, Map<String, Object> headers, Object body) {
        setHttpRequest(params, headers, body);
        Response response = httpRequest.post(path);
        response.then().log().body();
        return response;
    }

    public Response put(String path, Map<String, Object> params, Map<String, Object> headers, Object body) {
        setHttpRequest(params, headers, body);
        Response response = httpRequest.put(path);
        response.then().log().body();
        return response;
    }

    public Response patch(String path, Map<String, Object> params, Map<String, Object> headers, Object body) {
        setHttpRequest(params, headers, body);
        Response response = httpRequest.patch(path);
        response.then().log().body();
        return response;
    }

    public Response delete(String path, Map<String, Object> params, Map<String, Object> headers, String body) {
        setHttpRequest(params, headers, body);
        Response response = httpRequest.delete(path);
        response.then().log().body();
        return response;
    }


    protected void setHttpRequest(Map<String, Object> params, Map<String, Object> headers, Object body) {
        httpRequest = RestAssured.given()
                .log()
                .all(true);
        httpRequest.baseUri(baseUrl);
        httpRequest.header("Content-type", "application/json");
        httpRequest.header("xAutomation", true);
        if (params != null) {
            httpRequest.queryParams(params);
        }
        if (headers != null) {
            httpRequest.headers(headers);
        }
        if (body != null) {
            httpRequest.body(body);
        }

    }

}
