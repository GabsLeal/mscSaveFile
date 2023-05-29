package com.mscSaveFIle.mscSaveFIle.util;

import java.time.format.DateTimeParseException;

public class LocalDate {
    private java.time.LocalDate parseDate(String date) {
        try {
            return java.time.LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + date);
        }
    }
}
