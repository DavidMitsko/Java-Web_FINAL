package com.mitsko.mrdb.service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private Pattern loginPattern = Pattern.compile("[a-zA-Z]{1}[a-zA-Z\\d\\u002E\\u005F]+");
    private Pattern passwordPattern = Pattern.compile("[a-zA-Z]");

    public boolean checkRating(float rating) {
        if (rating > 10 || rating < 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkLogin(String login) {
        return checkString(login, loginPattern);
    }

    public boolean checkPassword(String password) {
        return checkString(password, passwordPattern);
    }

    private boolean checkString(String stringForChecking, Pattern passwordPattern) {
        if (stringForChecking == null) {
            return false;
        }
        if (stringForChecking.length() < 4) {
            return false;
        }
        Matcher matcher = passwordPattern.matcher(stringForChecking);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
