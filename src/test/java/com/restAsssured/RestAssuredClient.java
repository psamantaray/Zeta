package com.restAsssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

public class RestAssuredClient {

    public Response call(HttpMethod method, String uri, Map<String, Object> headers, Map<String, Object> pathParams, Map<String, Object> queryParams, Object body) {
        if (method == null)
            return null;
        Response response;
        switch (method) {
            case GET:
                response = getCall(uri, headers, pathParams, queryParams);
                break;
            case PUT:
                response = putCall(uri, headers, pathParams, queryParams, body);
                break;
            case POST:
                response = postCall(uri, headers, pathParams, queryParams, body);
                break;
            case DELETE:
                response = deleteCall(uri, headers, pathParams, queryParams, body);
                break;
            default:
                response = null;
        }


        return response;
    }

    private Response getCall(String uri, Map<String, Object> headers, Map<String, Object> pathParams, Map<String, Object> queryParams) {
        RequestSpecification request = createRequest(uri, headers, pathParams, queryParams, null);
        if (request == null) return null;
        Response response = request.when().get().andReturn();
        System.out.println("Get Call Response Code ::: " + response.getStatusCode());
        System.out.println("Get Call Response::: " + response.getBody().prettyPrint());
        return response;
    }

    private Response postCall(String uri, Map<String, Object> headers, Map<String, Object> pathParams, Map<String, Object> queryParams, Object body) {
        RequestSpecification request = createRequest(uri, headers, pathParams, queryParams, null);
        if (request == null) return null;
        Response response = request.when().post().andReturn();
        System.out.println("Post Call Response Code ::: " + response.getStatusCode());
        System.out.println("Post Call Response::: " + response.getBody().prettyPrint());
        return response;
    }

    private Response putCall(String uri, Map<String, Object> headers, Map<String, Object> pathParams, Map<String, Object> queryParams, Object body) {
        RequestSpecification request = createRequest(uri, headers, pathParams, queryParams, null);
        if (request == null) return null;
        Response response = request.when().put().andReturn();
        System.out.println("Put Call Response Code ::: " + response.getStatusCode());
        System.out.println("Put Call Response::: " + response.getBody().prettyPrint());
        return response;
    }

    private Response deleteCall(String uri, Map<String, Object> headers, Map<String, ?> pathParams, Map<String, Object> queryParams, Object body) {
        RequestSpecification request = createRequest(uri, headers, pathParams, queryParams, null);
        if (request == null) return null;
        Response response = request.when().put().andReturn();
        System.out.println("Delete Call Response Code ::: " + response.getStatusCode());
        System.out.println("Delete Call Response::: " + response.getBody().prettyPrint());
        return response;
    }

    private RequestSpecification createRequest(String uri, Map<String, ?> headers, Map<String, ?> pathParams, Map<String, Object> queryParams, Object body) {
        RequestSpecification request = RestAssured.given().baseUri(uri);
        if (headers != null) request.headers(headers);
        if (queryParams != null) request.queryParams(queryParams);
        if (pathParams != null) request.params(pathParams);
        if (body != null) request.body(body);
        return request;
    }
}
