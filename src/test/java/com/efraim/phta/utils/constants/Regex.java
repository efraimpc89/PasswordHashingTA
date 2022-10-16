package com.efraim.phta.utils.constants;

public class Regex {
    public static final String ONLY_NUMBERS = "^[0-9]*$";
    public static final String SHA512_ENCRYPTED = "^\\w{128}$";
    public static final String BASE64_ENCODED = "^(?:[A-Za-z0-9+\\/]{4})*(?:[A-Za-z0-9+\\/]{2}==|[A-Za-z0-9+\\/]{3}=|[A-Za-z0-9+\\/]{4})$";
}
