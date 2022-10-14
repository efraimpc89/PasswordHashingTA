package com.efraim.phta.helpers;

import com.efraim.phta.utils.constants.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StatsServiceHelper extends BaseServiceHelper{

	public Response getStats() {
		Response response = getRequest(ContentType.JSON, Endpoints.STATS);
		
		return response;
	}
	
}
