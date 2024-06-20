/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author tranl
 */
public class PasswordValidator {

    // digit + lowercase char + uppercase char + punctuation + symbol
    private static final String PASSWORD_PATTERN
            = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,20}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static boolean isValid(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
