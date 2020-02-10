package com.mitsko.mrdb.service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private Pattern loginPattern = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z\\d\\u002E\\u005F]{3,20}$");
    private Pattern passwordPattern = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z1-9]{3,20}$");
    private Pattern reviewPattern = Pattern.compile("^[a-zA-Z0-9]{1,1000}$");

    public boolean checkRating(float rating) {
        if (rating > 10 || rating < 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkLogin(String login) {
        return checkString(login, loginPattern);
    }

    public boolean checkPassword(String password) {
        return checkString(password, passwordPattern);
    }

    public boolean checkReview(String review) {
        return checkString(review, reviewPattern);
    }

    private boolean checkString(String stringForChecking, Pattern patternForChecking) {
        if (stringForChecking == null) {
            return false;
        }
        Matcher matcher = patternForChecking.matcher(stringForChecking);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
