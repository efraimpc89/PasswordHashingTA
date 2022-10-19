package com.efraim.phta.tests.hash;

import com.efraim.phta.helpers.HashServiceHelper;
import com.efraim.phta.utils.other.TestCaseInformation.TestCaseInfo;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class DELETE_Hash_Test {

    private HashServiceHelper helper;

    @BeforeClass
    public void initialize() throws IOException {
        helper = new HashServiceHelper();
    }

    @Test(groups = {"DELETE", "Hash"})
    @TestCaseInfo(testCaseId = {"C18"})
    public void C18_DeleteHash_Returns405StatusCode() {
        Response response = helper.deleteHash();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_METHOD_NOT_ALLOWED);
    }


}
