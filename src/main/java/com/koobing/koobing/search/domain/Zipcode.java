package com.koobing.koobing.search.domain;

import java.util.regex.Pattern;

public class Zipcode {
    private static final Pattern validZipcodePattern = Pattern.compile("^\\d{5}$");
    private static final Pattern invalidZipcodePattern = Pattern.compile("(\\d)\\1{4}");
    private final String value;

    public Zipcode(String value) {
        if (!validZipcodePattern.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid zipcode: " + value);
        }

        if (invalidZipcodePattern.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid zipcode: " + value);
        }

        this.value = value;
    }

    public String value() {
        return value;
    }
}
