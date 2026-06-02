package com.koobing.koobing.search.domain;

public record Zipcode(String value) {
    public Zipcode(String value) {
        this.value = value;
    }
}
