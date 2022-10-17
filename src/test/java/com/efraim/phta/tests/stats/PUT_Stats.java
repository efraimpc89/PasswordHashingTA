package com.efraim.phta.tests.stats;

import com.efraim.phta.helpers.HashServiceHelper;
import com.efraim.phta.helpers.StatsServiceHelper;
import com.efraim.phta.models.Stats;
import com.efraim.phta.utils.constants.Constants;
import com.efraim.phta.utils.exceptions.TestPrerequisiteException;
import com.efraim.phta.utils.other.TestCaseInformation.TestCaseInfo;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;


public class PUT_Stats {

    private StatsServiceHelper statsHelper;

    @BeforeClass
    public void initialize() throws IOException {
        statsHelper = new StatsServiceHelper();
    }

    @Test(groups = { "PUT", "Stats" })
    @TestCaseInfo(testCaseId = {"C19"})
    public void C19_PutStats_Returns405StatusCode() {
        Response response = statsHelper.putStats();
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_METHOD_NOT_ALLOWED);
    }

}
