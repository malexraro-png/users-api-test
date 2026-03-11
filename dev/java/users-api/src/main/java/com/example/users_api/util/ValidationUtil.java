package com.example.users_api.util;

import java.util.regex.Pattern;

public class ValidationUtil {

    private static final Pattern RFC_PATTERN =
            Pattern.compile("^[A-Z]{4}[0-9]{6}[A-Z0-9]{3}$");

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public static boolean isValidRFC(String rfc){
        return RFC_PATTERN.matcher(rfc).matches();
    }

    public static boolean isValidEmail(String email){
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone){

        String cleanPhone = phone.replaceAll("[^0-9]", "");

        return cleanPhone.length() >= 10;
    }
}