package com.example.batch.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateConversionUtils {

    private static final DateTimeFormatter CSV_DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final DateTimeFormatter STANDARD_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter CSV_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss.SSSSSS");
    private static final DateTimeFormatter DB_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss.SSSSSS a");

    public static LocalDate parseLocalDate(String date) {
        if (date == null || date.trim().isEmpty()) return null;
        return LocalDate.parse(date, CSV_DATE_FORMAT);
    }

    public static LocalDateTime parseLocalDateTime(String dateTime) {
        if (dateTime == null || dateTime.trim().isEmpty()) return null;
        try {
            return LocalDateTime.parse(dateTime, DB_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            return LocalDateTime.parse(dateTime, CSV_DATETIME_FORMAT);
        }
    }

    public static String formatLocalDate(LocalDate date) {
        if (date == null) return null;
        return date.format(STANDARD_DATE_FORMAT);
    }

    public static String formatLocalDateTime(LocalDateTime dateTime) {
        if (dateTime == null) return null;
        return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
