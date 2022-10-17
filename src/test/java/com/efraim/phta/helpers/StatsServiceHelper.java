package com.efraim.phta.helpers;

import com.efraim.phta.utils.constants.Constants;
import com.efraim.phta.utils.constants.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;

public class StatsServiceHelper extends BaseServiceHelper{

	public StatsServiceHelper() throws IOException {
	}

	public Response getStats() {
		Response response = getRequest(ContentType.JSON, Endpoints.STATS);
		
		return response;
	}

	public Response putStats() {
		Response response = putRequest(ContentType.JSON, Endpoints.STATS);

		return response;
	}

	public Response postStats(Object body) {
		Response response = postRequest(ContentType.JSON, body, Endpoints.STATS);

		return response;
	}

	public Response deleteStats() {
		Response response = deleteRequest(ContentType.JSON, Endpoints.STATS);

		return response;
	}
	
}
