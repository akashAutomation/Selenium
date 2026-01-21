package pages.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class ApiTestPage {
    private Response response;

    public String setBaseUri(String uri){
        return uri;
    }

    public Response sendGetRequest(String uri){
        response = RestAssured
                .given()
                    //.redirects().follow(true) // enable redirect following
                .when()
                    .get(uri);
        return response;
    }

    public Response sendPostRequestUsingMap(String endpoint, Map<Object, Object> body){
        response = RestAssured
                .given()
                    .header("api-key", "postmanrulz")
                    .contentType("application/json")
                    .body(body)
                .when()
                    .post(endpoint);
        return response;
    }

    public Response sendPostRequestUsingJsonFile(String endpoint, String body){
        response = RestAssured
                .given()
                .header("api-key", "postmanrulz")
                .contentType("application/json")
                .body(body)
                .when()
                .post(endpoint);
        return response;
    }

    public Response sendPatchRequest(String endpoint, Map<Object, Object> body, String id){
        response = RestAssured
                .given()
                    .header("api-key", "postmanrulz")
                    .contentType("application/json")
                    .body(body)
                .when()
                    .patch(endpoint+"/"+id);
        return response;
    }

    public Response sendDeleteRequest(String endpoint, String id){
        response = RestAssured
                .given()
                    .header("api-key", "postmanrulz")
                .when()
                    .delete(endpoint+"/"+id);
        return response;
    }

    public Response sendGetRequestUsingPathQueryParam(String endpoint, String pathparam, String value){
        response = RestAssured
                .given()
                    .pathParam(pathparam, value)
                    .queryParam("genre", "finance")
                    .queryParam("author", "Ak")
                .when()
                    .get(endpoint+"/{"+pathparam+"}");
        return response;
    }


}
