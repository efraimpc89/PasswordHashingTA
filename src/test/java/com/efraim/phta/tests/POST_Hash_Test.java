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
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.*;

import static com.efraim.phta.utils.other.OpenLocalHashServer.openAPIFile;

public class POST_Hash_Test {

    private HashServiceHelper helper;

    @BeforeClass
    public void initialize() throws IOException {

        helper = new HashServiceHelper();
    }

    @Test(groups = { "POST", "Hash" , "C1"})
    @TestCaseInfo(testCaseId = {"C1"})
    public void C1_postToHash_ReturnsJobIdentifier() {
        Response response = helper.postHash_NewPassword(Constants.DEFAULT_PASSWORD);
        String jobIdentifier = response.getBody().asString();
        Assert.assertTrue(Pattern.matches(Regex.ONLY_NUMBERS, jobIdentifier), Constants.UNMATCHED_REGEX_MSG + jobIdentifier);
    }

    @Test(groups = { "POST", "Hash" })
    @TestCaseInfo(testCaseId = {"C2"})
    public void C2_postToHash_Returns201StatusCode() {
        Response response = helper.postHash_NewPassword(Constants.DEFAULT_PASSWORD);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
    }

    @Test(groups = { "POST", "Hash" })
    @TestCaseInfo(testCaseId = {"C3"})
    public void C3_postToHashWithNoBody_Returns400StatusCode() {
        Response response = helper.postRequest(ContentType.JSON, Constants.EMPTY_BODY, Endpoints.HASH);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(groups = { "POST", "Hash" })
    @TestCaseInfo(testCaseId = {"C4"})
    public void C4_postToHash_ResponseTimeIsGreaterOrEqualTo5Seconds() {
        Response response = helper.postHash_NewPassword(Constants.DEFAULT_PASSWORD);
        Assert.assertTrue(response.getTime() >= 5000);
    }

    @Ignore
    @Test(groups = { "POST", "Hash" })
    @TestCaseInfo(testCaseId = {"C5"})
    public void C5_postToHash_Returns201StatusCodeWhenRequestSentBeforeShutdown() {

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
    public void C7_postToHash_NotAllowedAfterShutDown() throws IOException, InterruptedException {
        Response responseShutDown = helper.postHash_Shutdown();
        boolean connectionErrorFlag = false;

        try{
            Response responseNewPasswordHash = helper.postHash_NewPassword(Constants.DEFAULT_PASSWORD);
        }catch (Exception e){
            connectionErrorFlag = true;
        }

        Assert.assertTrue(connectionErrorFlag);

    }

}
