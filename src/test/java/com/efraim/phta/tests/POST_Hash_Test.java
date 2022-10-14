package com.efraim.phta.tests;

import com.efraim.phta.helpers.HashServiceHelper;
import com.efraim.phta.utils.constants.Constants;
import com.efraim.phta.utils.constants.Regex;
import com.efraim.phta.utils.exceptions.TestPrerequisiteException;
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

    @Test(groups = { "POST", "Hash" , "KeySpec"})
    public void postEncodedPasswordReturnsJobIdentifier() {
        Response response = helper.postNewPasswordHash(Constants.DEFAULT_PASSWORD);
        String jobIdentifier = response.getBody().asString();
        Assert.assertTrue(Pattern.matches(Regex.ONLY_NUMBERS, jobIdentifier), Constants.UNMATCHED_REGEX_MSG + jobIdentifier);
    }

    @Test(groups = { "POST", "Hash" })
    public void postEncodedPasswordIs204() {
        Response response = helper.postNewPasswordHash(Constants.DEFAULT_PASSWORD);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
    }

}
