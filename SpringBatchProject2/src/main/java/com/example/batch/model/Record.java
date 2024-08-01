package com.example.batch.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Record {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss.SSSSSS");

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate BL_PER_FROM_DT;

    private Double TAXABLE_MNY;

    @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss.SSSSSS")
    private LocalDateTime ORIG_CREATE_TS;

    @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss.SSSSSS")
    private LocalDateTime ADMIN_CRT_TMSTAMP;

    @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss.SSSSSS")
    private LocalDateTime ORIG_ADMIN_TMSTAMP;

    private Long TAX_PROD_ID;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate MTN_EFF_DT;

    @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss.SSSSSS")
    private LocalDateTime DB_TMSTAMP;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate ADMIN_EFF_DT;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate BL_PER_TO_DT;

    public void setBL_PER_FROM_DT(String dateStr) {
        this.BL_PER_FROM_DT = parseLocalDate(dateStr);
    }

    public void setTAXABLE_MNY(String moneyStr) {
        this.TAXABLE_MNY = parseDouble(moneyStr);
    }

    public void setORIG_CREATE_TS(String timestampStr) {
        this.ORIG_CREATE_TS = parseLocalDateTime(timestampStr);
    }

    public void setADMIN_CRT_TMSTAMP(String timestampStr) {
        this.ADMIN_CRT_TMSTAMP = parseLocalDateTime(timestampStr);
    }

    public void setORIG_ADMIN_TMSTAMP(String timestampStr) {
        this.ORIG_ADMIN_TMSTAMP = parseLocalDateTime(timestampStr);
    }

    public void setTAX_PROD_ID(String idStr) {
        this.TAX_PROD_ID = parseLong(idStr);
    }

    public void setMTN_EFF_DT(String dateStr) {
        this.MTN_EFF_DT = parseLocalDate(dateStr);
    }

    public void setDB_TMSTAMP(String timestampStr) {
        this.DB_TMSTAMP = parseLocalDateTime(timestampStr);
    }

    public void setADMIN_EFF_DT(String dateStr) {
        this.ADMIN_EFF_DT = parseLocalDate(dateStr);
    }

    public void setBL_PER_TO_DT(String dateStr) {
        this.BL_PER_TO_DT = parseLocalDate(dateStr);
    }

    private LocalDate parseLocalDate(String dateStr) {
        try {
            return dateStr != null && !dateStr.isEmpty() ? LocalDate.parse(dateStr, DATE_FORMATTER) : null;
        } catch (Exception e) {
            logParsingError("LocalDate", dateStr);
            return null;
        }
    }

    private LocalDateTime parseLocalDateTime(String timestampStr) {
        try {
            return timestampStr != null && !timestampStr.isEmpty() ? LocalDateTime.parse(timestampStr, TIMESTAMP_FORMATTER) : null;
        } catch (Exception e) {
            logParsingError("LocalDateTime", timestampStr);
            return null;
        }
    }

    private Double parseDouble(String doubleStr) {
        try {
            return doubleStr != null && !doubleStr.isEmpty() ? Double.parseDouble(doubleStr) : null;
        } catch (Exception e) {
            logParsingError("Double", doubleStr);
            return null;
        }
    }

    private Long parseLong(String longStr) {
        try {
            return longStr != null && !longStr.isEmpty() ? Long.parseLong(longStr) : null;
        } catch (Exception e) {
            logParsingError("Long", longStr);
            return null;
        }
    }

    private void logParsingError(String type, String value) {
        System.err.println("Error parsing " + type + " with value: " + value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(BL_PER_FROM_DT, record.BL_PER_FROM_DT) &&
                Objects.equals(TAXABLE_MNY, record.TAXABLE_MNY) &&
                Objects.equals(ORIG_CREATE_TS, record.ORIG_CREATE_TS) &&
                Objects.equals(ADMIN_CRT_TMSTAMP, record.ADMIN_CRT_TMSTAMP) &&
                Objects.equals(ORIG_ADMIN_TMSTAMP, record.ORIG_ADMIN_TMSTAMP) &&
                Objects.equals(TAX_PROD_ID, record.TAX_PROD_ID) &&
                Objects.equals(MTN_EFF_DT, record.MTN_EFF_DT) &&
                Objects.equals(DB_TMSTAMP, record.DB_TMSTAMP) &&
                Objects.equals(ADMIN_EFF_DT, record.ADMIN_EFF_DT) &&
                Objects.equals(BL_PER_TO_DT, record.BL_PER_TO_DT);
    }
}
