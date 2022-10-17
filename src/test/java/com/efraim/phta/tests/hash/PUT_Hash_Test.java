package com.efraim.phta.tests.hash;

import com.efraim.phta.helpers.HashServiceHelper;
import com.efraim.phta.utils.other.TestCaseInformation.TestCaseInfo;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class PUT_Hash_Test {

	private HashServiceHelper helper;
	
	@BeforeClass
	public void initialize() throws IOException {

		helper = new HashServiceHelper();
	}

	@Test(groups = { "PUT", "Hash" })
	@TestCaseInfo(testCaseId = {"C17"})
	public void C17_PutHash_Returns405StatusCode() {
		Response response = helper.putHash();
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_METHOD_NOT_ALLOWED);
	}


}
