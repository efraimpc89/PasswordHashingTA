package com.efraim.phta.tests.stats;

import com.efraim.phta.helpers.StatsServiceHelper;
import com.efraim.phta.utils.other.TestCaseInformation.TestCaseInfo;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;


public class DELETE_Stats {

    private StatsServiceHelper statsHelper;

    @BeforeClass
    public void initialize() throws IOException {
        statsHelper = new StatsServiceHelper();
    }

    @Test(groups = {"DELETE", "Stats"})
    @TestCaseInfo(testCaseId = {"C19"})
    public void C21_DeleteStats_Returns405StatusCode() {
        Response response = statsHelper.deleteStats();
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_METHOD_NOT_ALLOWED);
    }

}
