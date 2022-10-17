package com.efraim.phta.tests.hash;

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
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.*;

public class POST_Hash_Test {

    private HashServiceHelper helper;

    @BeforeClass
    public void initialize() throws IOException {

        helper = new HashServiceHelper();
    }

    @Test(groups = { "POST", "Hash" , "C1"})
    @TestCaseInfo(testCaseId = {"C1"})
    public void C1_PostToHash_ReturnsJobIdentifier() {
        Response response = helper.postHash_NewPassword(Constants.DEFAULT_PASSWORD);
        String jobIdentifier = response.getBody().asString();
        Assert.assertTrue(Pattern.matches(Regex.ONLY_NUMBERS, jobIdentifier), Constants.UNMATCHED_REGEX_MSG + jobIdentifier);
    }

    @Test(groups = { "POST", "Hash" })
    @TestCaseInfo(testCaseId = {"C2"})
    public void C2_PostToHash_Returns201StatusCode() {
        Response response = helper.postHash_NewPassword(Constants.DEFAULT_PASSWORD);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
    }

    @Test(groups = { "POST", "Hash" })
    @TestCaseInfo(testCaseId = {"C3"})
    public void C3_PostToHash_WithNoBody_Returns400StatusCode() {
        Response response = helper.postRequest(ContentType.JSON, Constants.EMPTY, Endpoints.HASH);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(groups = { "POST", "Hash" })
    @TestCaseInfo(testCaseId = {"C22"})
    public void C22_PostToHash_WithBlankPassword_Returns400StatusCode() {
        Response response = helper.postHash_NewPassword(Constants.BLANK);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(groups = { "POST", "Hash" })
    @TestCaseInfo(testCaseId = {"C23"})
    public void C23_PostToHash_WithEmptyPassword_Returns400StatusCode() {
        Response response = helper.postHash_NewPassword(Constants.BLANK);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }



    @Test(groups = { "POST", "Hash" })
    @TestCaseInfo(testCaseId = {"C4"})
    public void C4_PostToHash_ResponseTimeIsGreaterOrEqualTo5Seconds() {
        Response response = helper.postHash_NewPassword(Constants.DEFAULT_PASSWORD);
        Assert.assertTrue(response.getTime() >= 5000);
    }

    @Ignore
    @Test(groups = { "POST", "Hash" })
    @TestCaseInfo(testCaseId = {"C5"})
    public void C5_PostToHash_WhenRequestSentBeforeShutdown_Returns201StatusCode() {

        List<CompletableFuture<Response>> futures = List.of(
                CompletableFuture.supplyAsync(() -> helper.postHash_NewPassword(Constants.DEFAULT_PASSWORD)),
                CompletableFuture.supplyAsync(() -> helper.postHash_Shutdown())
            );

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[2])).thenAccept(
                v -> futures.stream()
                        .map(CompletableFuture::join)
                        .forEach(response -> Assert.assertEquals(response.statusCode(), 200))
        );

    }

    @Test(groups = { "POST", "Hash" })
    @TestCaseInfo(testCaseId = {"C7"})
    public void C7_PostToHash_NotAllowedAfterShutDown() {
        boolean connectionErrorFlag = false;
        helper.postHash_Shutdown();

        try{
            helper.postHash_NewPassword(Constants.DEFAULT_PASSWORD);
        }catch (Exception e){
            connectionErrorFlag = true;
        }

        Assert.assertTrue(connectionErrorFlag);

    }

}
