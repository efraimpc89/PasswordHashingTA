package com.efraim.phta.tests.hash;

import com.efraim.phta.helpers.HashServiceHelper;
import com.efraim.phta.utils.constants.Constants;
import com.efraim.phta.utils.exceptions.TestPrerequisiteException;
import com.efraim.phta.utils.other.TestCaseInformation.TestCaseInfo;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class GET_Hash_Test {

	private HashServiceHelper helper;
	
	@BeforeClass
	public void initialize() throws IOException {
		helper = new HashServiceHelper();
	}

	@Test(groups = { "GET", "Hash" })
	@TestCaseInfo(testCaseId = {"C16"})
	public void C16_GetHash_Returns405StatusCode() {
		Response response = helper.getHash();
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_METHOD_NOT_ALLOWED);
	}


}
