package com.efraim.phta.tests;

import com.efraim.phta.utils.constants.Constants;
import com.efraim.phta.helpers.HashServiceHelper;
import com.efraim.phta.utils.constants.Regex;
import com.efraim.phta.utils.exceptions.TestPrerequisiteException;
import com.efraim.phta.utils.other.TestCaseInformation.TestCaseInfo;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Base64;
import java.util.regex.Pattern;

public class GET_Hash_Test {

	private HashServiceHelper helper;
	
	@BeforeClass
	public void initialize() throws TestPrerequisiteException, IOException {

		helper = new HashServiceHelper();
		ensurePasswordHashExists();
	}

	@Test(groups = { "GET", "Hash" })
	@TestCaseInfo(testCaseId = {"C8"})
	public void C8_GetHash_EncodedPasswordIsBase64Encoded() {
		Response response = helper.getHash_EncodedPasswordById(Constants.HASH_ID_1);
		String responseBody = response.body().asString();
		boolean regexMatches = Pattern.matches(Regex.BASE64_ENCODED, responseBody);
		Assert.assertTrue(regexMatches, Constants.UNMATCHED_REGEX_MSG + responseBody);
	}

	@Test(groups = { "GET", "Hash" })
	@TestCaseInfo(testCaseId = {"C9"})
	public void C9_GetHash_ReturnsStatusCode200() {
		Response response = helper.getHash_EncodedPasswordById(Constants.HASH_ID_1);;
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
	}

	@Test(groups = { "GET", "Hash" })
	@TestCaseInfo(testCaseId = {""})
	public void CX_GetHash_NonExistentEncodedPasswordReturns404StatusCode() {
		Response response = helper.getHash_EncodedPasswordById(Constants.NON_EXISTENT_HASH_ID);
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
	}

	@Test(groups = { "GET", "Hash" })
	@TestCaseInfo(testCaseId = {"C10"})
	public void C10_GetHash_Base64DecodedPasswordHashIsSHA512Encrypted() {
		Response response = helper.getHash_EncodedPasswordById(Constants.HASH_ID_1);
		String responseBody = response.body().asString();
		byte[] decodedBytes = Base64.getDecoder().decode(responseBody);
		String decodedString = new String(decodedBytes);

		boolean regexMatches = Pattern.matches(Regex.SHA512_ENCRYPTED, decodedString);
		Assert.assertTrue(regexMatches, Constants.UNMATCHED_REGEX_MSG + decodedString);
	}


	private void ensurePasswordHashExists() throws TestPrerequisiteException {
		if( helper.getHash_EncodedPasswordById(Constants.HASH_ID_1).getStatusCode()!= HttpStatus.SC_OK){
			Response response = helper.postHash_NewPassword(Constants.DEFAULT_PASSWORD);
			if(response.getStatusCode() != HttpStatus.SC_OK){
				throw new TestPrerequisiteException(Constants.NEW_PASSWORD_HASHING_PREREQUISITE_EXCEPTION_MSG);
			}
		}
	}
}
