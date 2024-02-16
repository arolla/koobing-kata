package com.koobing.koobing.search;

public record SearchError(String cause) {
    public static SearchError AT_LEAST_ONE_NIGHT = new SearchError("A booking must contain at least one night.");
}
