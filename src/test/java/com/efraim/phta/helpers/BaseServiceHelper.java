package com.efraim.phta.helpers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ResourceBundle;

public class BaseServiceHelper {

    public BaseServiceHelper() {
        ResourceBundle rb = ResourceBundle.getBundle("environment/local");
        RestAssured.baseURI = rb.getString("BASE_URI");
    }

    public Response postRequest(ContentType contentType, Object body, String path) {
        Response response =
                RestAssured.given()
                        .contentType(contentType)
                        .body(body)
                        .post(path);
        return response;
    }

    public Response getRequest(ContentType contentType, String path) {
        Response response =
                RestAssured.given()
                        .contentType(contentType)
                        .get(path);
        return response;
    }

}
