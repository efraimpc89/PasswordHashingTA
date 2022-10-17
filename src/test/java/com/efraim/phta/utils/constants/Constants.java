package com.efraim.phta.utils.constants;

public class Constants {

    //region Test Data
    public static final String DEFAULT_PASSWORD = "test";
    public static final int HASH_ID_1 = 1;
    public static final int NON_EXISTENT_HASH_ID = 0;
    public static final String EMPTY = "";
    public static final String BLANK = " ";
    public static final String SHUTDOWN = "shutdown";
    //endregion

    //region Exception/Assertion Messages
    public static final String NEW_PASSWORD_HASHING_PREREQUISITE_EXCEPTION_MSG = "Unable to create new password hashing as prerequisite of test suite";
    public static final String UNMATCHED_REGEX_MSG = "Expected result unmatched regular expression. Actual Result: ";
    public static final String RESPONSE_CONTENT_TYPE_NOT_JSON_MSG = "Response Content-Type id not JSON. Actual Result: ";
    public static final String TOTALREQUESTS_DID_NOT_INCREMENT_BY1_MSG ="Total Requests did not increment by 1.";
    //endregion


}
