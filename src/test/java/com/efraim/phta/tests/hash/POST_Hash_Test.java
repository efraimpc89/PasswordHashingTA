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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

import static com.efraim.phta.utils.other.OpenLocalHashServer.openAPIFile;

public class POST_Hash_Test {

    //C5
    private static final AtomicInteger creatingPasswordHashStatusCode = new AtomicInteger(0);
    private static final AtomicInteger shutdownStatusCode = new AtomicInteger(0);
    //C6
    private static final AtomicReference<String> jobIdentifier_C6 = new AtomicReference<>("");
    private HashServiceHelper helper;

    @BeforeClass
    public void initialize() throws IOException {

        helper = new HashServiceHelper();
    }

    @Test(groups = {"POST", "Hash", "C1"})
    @TestCaseInfo(testCaseId = {"C1"})
    public void C1_PostToHash_ReturnsJobIdentifier() {
        Response response = helper.postHash_NewPassword(Constants.DEFAULT_PASSWORD);
        String jobIdentifier = response.getBody().asString();
        Assert.assertTrue(Pattern.matches(Regex.ONLY_NUMBERS, jobIdentifier), Constants.UNMATCHED_REGEX_MSG + jobIdentifier);
    }

    @Test(groups = {"POST", "Hash"})
    @TestCaseInfo(testCaseId = {"C2"})
    public void C2_PostToHash_Returns201StatusCode() {
        Response response = helper.postHash_NewPassword(Constants.DEFAULT_PASSWORD);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
    }

    @Test(groups = {"POST", "Hash"})
    @TestCaseInfo(testCaseId = {"C3"})
    public void C3_PostToHash_WithNoBody_Returns400StatusCode() {
        Response response = helper.postRequest(ContentType.JSON, Constants.EMPTY, Endpoints.HASH);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(groups = {"POST", "Hash"})
    @TestCaseInfo(testCaseId = {"C22"})
    public void C22_PostToHash_WithBlankPassword_Returns400StatusCode() {
        Response response = helper.postHash_NewPassword(Constants.BLANK);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(groups = {"POST", "Hash"})
    @TestCaseInfo(testCaseId = {"C23"})
    public void C23_PostToHash_WithEmptyPassword_Returns400StatusCode() {
        Response response = helper.postHash_NewPassword(Constants.BLANK);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(groups = {"POST", "Hash"})
    @TestCaseInfo(testCaseId = {"C4"})
    public void C4_PostToHash_ResponseTimeIsGreaterOrEqualTo5Seconds() {
        Response response = helper.postHash_NewPassword(Constants.DEFAULT_PASSWORD);
        Assert.assertTrue(response.getTime() >= 5000);
    }

    @Test(groups = {"POST", "Hash"})
    @TestCaseInfo(testCaseId = {"C5"})
    public void C5_PostToHash_WhenRequestSentBeforeShutdown_Returns201StatusCode() {
        ExecutorService exec = Executors.newFixedThreadPool(2);

        Runnable passwordCreation = () -> {
            Response response = helper.postHash_NewPassword(Constants.DEFAULT_PASSWORD);
            creatingPasswordHashStatusCode.set(response.getStatusCode());
        };

        Runnable shutdownAPI = () -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Response response2 = helper.postHash_Shutdown();
            shutdownStatusCode.set(response2.getStatusCode());
        };

        exec.submit(passwordCreation);
        exec.submit(shutdownAPI);
        waitForAllRunnables(exec);

        Assert.assertEquals(creatingPasswordHashStatusCode.intValue(), HttpStatus.SC_CREATED);
    }

    @Test(groups = {"POST", "Hash"})
    @TestCaseInfo(testCaseId = {"C6"})
    public void C6_PostToHash_WhenRequestSentBeforeShutdown_ReturnsJobIdentifier() {

        ExecutorService exec = Executors.newFixedThreadPool(2);

        Runnable passwordCreation = () -> {
            Response response = helper.postHash_NewPassword(Constants.DEFAULT_PASSWORD);
            creatingPasswordHashStatusCode.set(response.getStatusCode());
        };

        Runnable shutdownAPI = () -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Response response2 = helper.postHash_Shutdown();
            jobIdentifier_C6.set(response2.getBody().asString());
        };

        exec.submit(passwordCreation);
        exec.submit(shutdownAPI);
        waitForAllRunnables(exec);

        Assert.assertTrue(Pattern.matches(Regex.ONLY_NUMBERS, jobIdentifier_C6.get()), Constants.UNMATCHED_REGEX_MSG + jobIdentifier_C6.get());

    }

    @Ignore("Unstable, needs review")
    @Test(groups = {"POST", "Hash"})
    @TestCaseInfo(testCaseId = {"C7"})
    public void C7_PostToHash_NotAllowedAfterShutDown() throws IOException {
        boolean connectionErrorFlag = false;
        helper.postHash_Shutdown();

        try {
            helper.postHash_NewPassword(Constants.DEFAULT_PASSWORD);
        } catch (Exception e) {
            connectionErrorFlag = true;
        }

        Assert.assertTrue(connectionErrorFlag);

    }

    private static void waitForAllRunnables(ExecutorService exec) {
        exec.shutdown();
        try {
            if (!exec.awaitTermination(10, TimeUnit.SECONDS)) {
                exec.shutdownNow();
            }
        } catch (InterruptedException ex) {
            exec.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}
