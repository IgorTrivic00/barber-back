package com.example.demo.utils;

import java.util.regex.Pattern;

public class Validator {

    private static final String EMAIL_VALIDATION_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public static boolean ValidateEmail(String email) {
        return Pattern.compile(EMAIL_VALIDATION_REGEX)
                .matcher(email)
                .matches();
    }

}
