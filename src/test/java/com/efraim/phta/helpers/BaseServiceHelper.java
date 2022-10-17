package com.efraim.phta.helpers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.ResourceBundle;

import static com.efraim.phta.utils.other.OpenLocalHashServer.openAPIFile;
import static com.efraim.phta.utils.other.ResourceBundleUtil.getResourceBundle;

public class BaseServiceHelper {

    public BaseServiceHelper() throws IOException {
        RestAssured.baseURI = getResourceBundle().getString("BASE_URI");
        openAPIFile();
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
