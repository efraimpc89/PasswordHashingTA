package com.efraim.phta.tests;

import com.efraim.phta.helpers.HashServiceHelper;
import com.efraim.phta.helpers.StatsServiceHelper;
import com.efraim.phta.models.Stats;
import com.efraim.phta.utils.constants.Constants;
import com.efraim.phta.utils.exceptions.TestPrerequisiteException;
import com.efraim.phta.utils.other.TestCaseInformation.TestCaseInfo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;


public class GET_Stats {

    private HashServiceHelper hashHelper;
    private StatsServiceHelper statsHelper;

    @BeforeClass
    public void initialize() throws TestPrerequisiteException, IOException {

        hashHelper = new HashServiceHelper();
        statsHelper = new StatsServiceHelper();
        ensurePasswordHashExists();
    }

    @Test(groups = { "GET", "Stats" })
    @TestCaseInfo(testCaseId = {"C11"})
    public void C11_GetStats_Returns200StatusCode() {
        Response response = statsHelper.getStats();
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = { "GET", "Stats" })
    @TestCaseInfo(testCaseId = {"C12"})
    public void C12_GetStats_ReturnsJSONContentTypeHeader() {
        Response response = statsHelper.getStats();
        String contentTypeHeader = response.getHeaders().get("Content-Type").toString();
        Assert.assertTrue(contentTypeHeader.contains("application/json"), Constants.RESPONSE_CONTENT_TYPE_NOT_JSON_MSG + contentTypeHeader);
    }

    @Test(groups = { "GET", "Stats" })
    @TestCaseInfo(testCaseId = {"C13"})
    public void C13_GetStats_TotalRequestsIncrementBy1() throws TestPrerequisiteException {
        Response response = statsHelper.getStats();
        Stats responseStats = response.getBody().as(Stats.class);
        int beforeTotalRequests = responseStats.getTotalRequests();

        createNewPasswordHash();

        response = statsHelper.getStats();
        responseStats = response.getBody().as(Stats.class);
        int afterTotalRequests = responseStats.getTotalRequests();

        Assert.assertTrue(afterTotalRequests == (beforeTotalRequests + 1), Constants.TOTALREQUESTS_DID_NOT_INCREMENT_BY1_MSG );
    }

    @Test(groups = { "GET", "Stats" })
    @TestCaseInfo(testCaseId = {"C14"})
    public void C14_GetStats_AverageTimeIsGreaterOrEqualTo5000ms() {
        Response response = statsHelper.getStats();
        Stats responseStats = response.getBody().as(Stats.class);
        int averageTime = responseStats.getAverageTime();

        Assert.assertTrue(averageTime >= 5000, Constants.TOTALREQUESTS_DID_NOT_INCREMENT_BY1_MSG );
    }



    private void ensurePasswordHashExists() throws TestPrerequisiteException {
        if( hashHelper.getHash_EncodedPasswordById(Constants.HASH_ID_1).getStatusCode()!= HttpStatus.SC_OK){
            Response response = hashHelper.postHash_NewPassword(Constants.DEFAULT_PASSWORD);
            if(response.getStatusCode() != HttpStatus.SC_OK){
                throw new TestPrerequisiteException(Constants.NEW_PASSWORD_HASHING_PREREQUISITE_EXCEPTION_MSG);
            }
        }
    }

    private void createNewPasswordHash() throws TestPrerequisiteException {
            Response response = hashHelper.postHash_NewPassword(Constants.DEFAULT_PASSWORD);
            if(response.getStatusCode() != HttpStatus.SC_OK){
                throw new TestPrerequisiteException(Constants.NEW_PASSWORD_HASHING_PREREQUISITE_EXCEPTION_MSG);
            }
    }
}
