package com.efraim.phta.helpers;

import com.efraim.phta.utils.constants.Endpoints;
import com.efraim.phta.models.Password;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class HashServiceHelper extends BaseServiceHelper{

	public Response postNewPasswordHash(String password) {
		
		Password newPassword = new Password();
		newPassword.setPassword(password);

//		Response response =
//				RestAssured.given()
//				.contentType(ContentType.JSON)
//				.body(newPassword)
//				.post(Endpoints.HASH);
		Response response = postRequest(ContentType.JSON, newPassword, Endpoints.HASH);

		return response;

	}

	public Response getEncodedPasswordById(int passwordId) {
		
		String path = String.format(Endpoints.HASH_ID, passwordId);
		
//		Response response =
//				RestAssured.given()
//				.contentType(ContentType.JSON)
//				.get(url);

		Response response = getRequest(ContentType.JSON, path);
		
		return response;

	}
	
}
