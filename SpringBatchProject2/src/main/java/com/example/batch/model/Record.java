package com.example.batch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Record {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss.SSSSSS");

    private String CUST_ID_NO;
    private long ACCT_NO;
    private String NPA;
    private String NXX;
    private String TLN;
    private long BL_PROD_ID;
    private String DELETE_IND;
    private LocalDateTime ADMIN_CRT_TMSTAMP;
    private long BL_GRP_NO;
    private LocalDate MTN_EFF_DT;
    private LocalDate ADMIN_EFF_DT;
    private double ADMIN_CHG_AMT;
    private LocalDate BL_PER_FROM_DT;
    private LocalDate BL_PER_TO_DT;
    private String ADMIN_FEE_RSN_CD;
    private long DISCNT_OFFR_ID;
    private String VISION_USER_ID_CD;
    private LocalDateTime ORIG_ADMIN_TMSTAMP;
    private long ORIG_INVOICE_NO;
    private String CUST_DISC_IND;
    private int CNTRCT_TERMS_ID;
    private String CREDIT_ADJ_CD;
    private String ADMIN_FEE_TYP;
    private long ADMIN_FEE_TYP_ID;
    private String ORIG_TBL_SUBSYS_CD;
    private String CHRG_CAT_CD;
    private String CSEQ_IND;
    private String DB_USERID;
    private LocalDateTime DB_TMSTAMP;
    private String SOURCE_CLIENT_ID;
    private String ADMIN_CRT_METH_CD;
    private long EQ_ORD_NO;
    private String NETACE_LOC_ID;
    private long SVC_PROD_ID_DISCNT;
    private String BL_CYC_NO;
    private String CYC_MTH_YR;
    private double TAXABLE_MNY;
    private long TAX_PROD_ID;
    private String OTC_TYPE;
    private String CHGBCK_SUBMISSION_ID;
    private String TAX_GEO_CODE;
    private String VODA_COUNTRY_CD;
    private int DATA_RT_FTPRNT_NO;
    private String AUDIT_TRANS_ID;
    private LocalDateTime ORIG_CREATE_TS;
    private int INSTALL_LOAN_NO;
    private String V2_USER_ID;
    private LocalDate V2_UPDATE_DTM;
    private String INSTALL_FIN_MARKET_ID;
    private int LOAN_TERM_MTH_QTY;
    private int TERM_BILLED_QTY;

    // Setters with error handling
    public void setBL_PER_FROM_DT(String dateStr) {
        this.BL_PER_FROM_DT = parseLocalDate(dateStr, "BL_PER_FROM_DT");
    }

    public void setTAXABLE_MNY(String moneyStr) {
        this.TAXABLE_MNY = parseDouble(moneyStr, "TAXABLE_MNY");
    }

    public void setORIG_CREATE_TS(String timestampStr) {
        this.ORIG_CREATE_TS = parseLocalDateTime(timestampStr, "ORIG_CREATE_TS");
    }

    public void setADMIN_CRT_TMSTAMP(String timestampStr) {
        this.ADMIN_CRT_TMSTAMP = parseLocalDateTime(timestampStr, "ADMIN_CRT_TMSTAMP");
    }

    public void setORIG_ADMIN_TMSTAMP(String timestampStr) {
        this.ORIG_ADMIN_TMSTAMP = parseLocalDateTime(timestampStr, "ORIG_ADMIN_TMSTAMP");
    }

    public void setTAX_PROD_ID(String taxProdIdStr) {
        this.TAX_PROD_ID = parseLong(taxProdIdStr, "TAX_PROD_ID");
    }

    public void setMTN_EFF_DT(String dateStr) {
        this.MTN_EFF_DT = parseLocalDate(dateStr, "MTN_EFF_DT");
    }

    public void setDB_TMSTAMP(String timestampStr) {
        this.DB_TMSTAMP = parseLocalDateTime(timestampStr, "DB_TMSTAMP");
    }

    public void setADMIN_EFF_DT(String dateStr) {
        this.ADMIN_EFF_DT = parseLocalDate(dateStr, "ADMIN_EFF_DT");
    }

    public void setBL_PER_TO_DT(String dateStr) {
        this.BL_PER_TO_DT = parseLocalDate(dateStr, "BL_PER_TO_DT");
    }

    private LocalDate parseLocalDate(String dateStr, String fieldName) {
        try {
            return Optional.ofNullable(dateStr).filter(str -> !str.isEmpty())
                    .map(str -> LocalDate.parse(str, DATE_FORMATTER)).orElse(null);
        } catch (Exception e) {
            logParsingError(fieldName, dateStr);
            return null;
        }
    }

    private LocalDateTime parseLocalDateTime(String timestampStr, String fieldName) {
        try {
            return Optional.ofNullable(timestampStr).filter(str -> !str.isEmpty())
                    .map(str -> LocalDateTime.parse(str, DATE_TIME_FORMATTER)).orElse(null);
        } catch (Exception e) {
            logParsingError(fieldName, timestampStr);
            return null;
        }
    }

    private Double parseDouble(String doubleStr, String fieldName) {
        try {
            return Optional.ofNullable(doubleStr).filter(str -> !str.isEmpty())
                    .map(Double::parseDouble).orElse(null);
        } catch (Exception e) {
            logParsingError(fieldName, doubleStr);
            return null;
        }
    }

    private Long parseLong(String longStr, String fieldName) {
        try {
            return Optional.ofNullable(longStr).filter(str -> !str.isEmpty())
                    .map(Long::parseLong).orElse(null);
        } catch (Exception e) {
            logParsingError(fieldName, longStr);
            return null;
        }
    }

    private void logParsingError(String fieldName, String value) {
        System.err.println("Error parsing field " + fieldName + " with value: " + value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return ACCT_NO == record.ACCT_NO &&
                BL_PROD_ID == record.BL_PROD_ID &&
                BL_GRP_NO == record.BL_GRP_NO &&
                DISCNT_OFFR_ID == record.DISCNT_OFFR_ID &&
                ORIG_INVOICE_NO == record.ORIG_INVOICE_NO &&
                CNTRCT_TERMS_ID == record.CNTRCT_TERMS_ID &&
                ADMIN_FEE_TYP_ID == record.ADMIN_FEE_TYP_ID &&
                EQ_ORD_NO == record.EQ_ORD_NO &&
                SVC_PROD_ID_DISCNT == record.SVC_PROD_ID_DISCNT &&
                Double.compare(record.TAXABLE_MNY, TAXABLE_MNY) == 0 &&
                TAX_PROD_ID == record.TAX_PROD_ID &&
                DATA_RT_FTPRNT_NO == record.DATA_RT_FTPRNT_NO &&
                INSTALL_LOAN_NO == record.INSTALL_LOAN_NO &&
                LOAN_TERM_MTH_QTY == record.LOAN_TERM_MTH_QTY &&
                TERM_BILLED_QTY == record.TERM_BILLED_QTY &&
                Objects.equals(CUST_ID_NO, record.CUST_ID_NO) &&
                Objects.equals(NPA, record.NPA) &&
                Objects.equals(NXX, record.NXX) &&
                Objects.equals(TLN, record.TLN) &&
                Objects.equals(DELETE_IND, record.DELETE_IND) &&
                Objects.equals(ADMIN_CRT_TMSTAMP, record.ADMIN_CRT_TMSTAMP) &&
                Objects.equals(MTN_EFF_DT, record.MTN_EFF_DT) &&
                Objects.equals(ADMIN_EFF_DT, record.ADMIN_EFF_DT) &&
                Objects.equals(ADMIN_CHG_AMT, record.ADMIN_CHG_AMT) &&
                Objects.equals(BL_PER_FROM_DT, record.BL_PER_FROM_DT) &&
                Objects.equals(BL_PER_TO_DT, record.BL_PER_TO_DT) &&
                Objects.equals(ADMIN_FEE_RSN_CD, record.ADMIN_FEE_RSN_CD) &&
                Objects.equals(VISION_USER_ID_CD, record.VISION_USER_ID_CD) &&
                Objects.equals(ORIG_ADMIN_TMSTAMP, record.ORIG_ADMIN_TMSTAMP) &&
                Objects.equals(CUST_DISC_IND, record.CUST_DISC_IND) &&
                Objects.equals(CREDIT_ADJ_CD, record.CREDIT_ADJ_CD) &&
                Objects.equals(ADMIN_FEE_TYP, record.ADMIN_FEE_TYP) &&
                Objects.equals(ORIG_TBL_SUBSYS_CD, record.ORIG_TBL_SUBSYS_CD) &&
                Objects.equals(CHRG_CAT_CD, record.CHRG_CAT_CD) &&
                Objects.equals(CSEQ_IND, record.CSEQ_IND) &&
                Objects.equals(DB_USERID, record.DB_USERID) &&
                Objects.equals(DB_TMSTAMP, record.DB_TMSTAMP) &&
                Objects.equals(SOURCE_CLIENT_ID, record.SOURCE_CLIENT_ID) &&
                Objects.equals(ADMIN_CRT_METH_CD, record.ADMIN_CRT_METH_CD) &&
                Objects.equals(NETACE_LOC_ID, record.NETACE_LOC_ID) &&
                Objects.equals(OTC_TYPE, record.OTC_TYPE) &&
                Objects.equals(CHGBCK_SUBMISSION_ID, record.CHGBCK_SUBMISSION_ID) &&
                Objects.equals(TAX_GEO_CODE, record.TAX_GEO_CODE) &&
                Objects.equals(VODA_COUNTRY_CD, record.VODA_COUNTRY_CD) &&
                Objects.equals(AUDIT_TRANS_ID, record.AUDIT_TRANS_ID) &&
                Objects.equals(ORIG_CREATE_TS, record.ORIG_CREATE_TS) &&
                Objects.equals(BL_CYC_NO, record.BL_CYC_NO) &&
                Objects.equals(CYC_MTH_YR, record.CYC_MTH_YR) &&
                Objects.equals(V2_USER_ID, record.V2_USER_ID) &&
                Objects.equals(V2_UPDATE_DTM, record.V2_UPDATE_DTM) &&
                Objects.equals(INSTALL_FIN_MARKET_ID, record.INSTALL_FIN_MARKET_ID);
    }
}
