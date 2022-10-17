package com.efraim.phta.helpers;

import com.efraim.phta.utils.constants.Constants;
import com.efraim.phta.utils.constants.Endpoints;
import com.efraim.phta.models.Password;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;

public class HashServiceHelper extends BaseServiceHelper{

	public HashServiceHelper() throws IOException {
	}

	public Response postHash_NewPassword(String password) {
		
		Password newPassword = new Password();
		newPassword.setPassword(password);

		Response response = postRequest(ContentType.JSON, newPassword, Endpoints.HASH);

		return response;

	}

	public Response getHash_EncodedPasswordById(int passwordId) {
		
		String path = String.format(Endpoints.HASH_ID, passwordId);

		Response response = getRequest(ContentType.JSON, path);
		
		return response;
	}

	public Response postHash_Shutdown() {

		Response response = postRequest(ContentType.TEXT, Constants.SHUTDOWN, Endpoints.HASH);

		return response;
	}


}
