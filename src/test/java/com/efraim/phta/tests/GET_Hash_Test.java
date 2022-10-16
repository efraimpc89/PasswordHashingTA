package com.efraim.phta.tests;

import com.efraim.phta.utils.constants.Constants;
import com.efraim.phta.helpers.HashServiceHelper;
import com.efraim.phta.utils.exceptions.TestPrerequisiteException;
import com.efraim.phta.utils.other.TestCaseInformation.TestCaseInfo;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GET_Hash_Test {

	private HashServiceHelper helper;
	
	@BeforeClass
	public void initialize() throws TestPrerequisiteException {

		helper = new HashServiceHelper();
		ensurePasswordHashExists();
	}

	@Test(groups = { "GET", "Hash" })
	@TestCaseInfo(testCaseId = {"C1"})
	public void getEncodedPasswordIs200() {
		Response response = helper.getEncodedPasswordById(Constants.HASH_ID_1);
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
	}

	@Test(groups = { "GET", "Hash" })
	@TestCaseInfo(testCaseId = {"C1"})
	public void getNonExistentEncodedPasswordIs404() {
		Response response = helper.getEncodedPasswordById(Constants.NON_EXISTENT_HASH_ID);
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
	}


	private void ensurePasswordHashExists() throws TestPrerequisiteException {
		if( helper.getEncodedPasswordById(Constants.HASH_ID_1).getStatusCode()!= HttpStatus.SC_OK){
			Response response = helper.postNewPasswordHash(Constants.DEFAULT_PASSWORD);
			if(response.getStatusCode() != HttpStatus.SC_OK){
				throw new TestPrerequisiteException(Constants.NEW_PASSWORD_HASHING_PREREQUISITE_EXCEPTION_MSG);
			}
		}
	}
}
