package com.efraim.phta.tests;

import com.efraim.phta.helpers.HashServiceHelper;
import com.efraim.phta.utils.constants.Constants;
import com.efraim.phta.utils.constants.Endpoints;
import com.efraim.phta.utils.constants.Regex;
import com.efraim.phta.utils.other.TestCaseInformation.TestCaseInfo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.regex.*;

public class POST_Hash_Test {

    private HashServiceHelper helper;

    @BeforeClass
    public void initialize(){

        helper = new HashServiceHelper();
    }

    @Test(groups = { "POST", "Hash" , "C1"})
    @TestCaseInfo(testCaseId = {"C1"})
    public void postToHash_ReturnsJobIdentifier() {
        Response response = helper.postNewPasswordHash(Constants.DEFAULT_PASSWORD);
        String jobIdentifier = response.getBody().asString();
        Assert.assertTrue(Pattern.matches(Regex.ONLY_NUMBERS, jobIdentifier), Constants.UNMATCHED_REGEX_MSG + jobIdentifier);
    }

    @Test(groups = { "POST", "Hash" })
    @TestCaseInfo(testCaseId = {"C2"})
    public void postToHash_Returns201StatusCode() {
        Response response = helper.postNewPasswordHash(Constants.DEFAULT_PASSWORD);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
    }

    @Test(groups = { "POST", "Hash" })
    @TestCaseInfo(testCaseId = {"C3"})
    public void postToHashWithNoBody_Returns400StatusCode() {
        Response response = helper.postRequest(ContentType.JSON, Constants.EMPTY_BODY, Endpoints.HASH);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(groups = { "POST", "Hash" })
    @TestCaseInfo(testCaseId = {"C4"})
    public void postToHash_ResponseTimeIsGreaterOrEqualTo5Seconds() {
        Response response = helper.postNewPasswordHash(Constants.DEFAULT_PASSWORD);
        Assert.assertTrue(response.getTime() >= 5000);
    }

}
