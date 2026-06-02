package com.koobing.koobing.search.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ZipcodeTest {

    @ParameterizedTest
    @ValueSource(strings = {"49100", "75001"})
    @DisplayName("Validate zipcode")
    void validZipcode(String zipcode) {
        new Zipcode(zipcode);
    }

    @ParameterizedTest
    @ValueSource(strings = {"100", "", "11111"})
    @DisplayName("Refuse zipcode")
    void invalidZipcode(String zipcode) {
        assertThrows(IllegalArgumentException.class, () -> new Zipcode(zipcode));
    }

}