package com.example.batch.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Record {

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

    // No-arg constructor
    public Record() {}

    // All-args constructor
    public Record(String CUST_ID_NO, long ACCT_NO, String NPA, String NXX, String TLN, long BL_PROD_ID, String DELETE_IND,
                  LocalDateTime ADMIN_CRT_TMSTAMP, long BL_GRP_NO, LocalDate MTN_EFF_DT) {
        this.CUST_ID_NO = CUST_ID_NO;
        this.ACCT_NO = ACCT_NO;
        this.NPA = NPA;
        this.NXX = NXX;
        this.TLN = TLN;
        this.BL_PROD_ID = BL_PROD_ID;
        this.DELETE_IND = DELETE_IND;
        this.ADMIN_CRT_TMSTAMP = ADMIN_CRT_TMSTAMP;
        this.BL_GRP_NO = BL_GRP_NO;
        this.MTN_EFF_DT = MTN_EFF_DT;
    }

    // Getters and Setters
    public String getCUST_ID_NO() {
        return CUST_ID_NO;
    }

    public void setCUST_ID_NO(String CUST_ID_NO) {
        this.CUST_ID_NO = CUST_ID_NO;
    }

    public long getACCT_NO() {
        return ACCT_NO;
    }

    public void setACCT_NO(long ACCT_NO) {
        this.ACCT_NO = ACCT_NO;
    }

    public String getNPA() {
        return NPA;
    }

    public void setNPA(String NPA) {
        this.NPA = NPA;
    }

    public String getNXX() {
        return NXX;
    }

    public void setNXX(String NXX) {
        this.NXX = NXX;
    }

    public String getTLN() {
        return TLN;
    }

    public void setTLN(String TLN) {
        this.TLN = TLN;
    }

    public long getBL_PROD_ID() {
        return BL_PROD_ID;
    }

    public void setBL_PROD_ID(long BL_PROD_ID) {
        this.BL_PROD_ID = BL_PROD_ID;
    }

    public String getDELETE_IND() {
        return DELETE_IND;
    }

    public void setDELETE_IND(String DELETE_IND) {
        this.DELETE_IND = DELETE_IND;
    }

    public LocalDateTime getADMIN_CRT_TMSTAMP() {
        return ADMIN_CRT_TMSTAMP;
    }

    public void setADMIN_CRT_TMSTAMP(LocalDateTime ADMIN_CRT_TMSTAMP) {
        this.ADMIN_CRT_TMSTAMP = ADMIN_CRT_TMSTAMP;
    }

    public long getBL_GRP_NO() {
        return BL_GRP_NO;
    }

    public void setBL_GRP_NO(long BL_GRP_NO) {
        this.BL_GRP_NO = BL_GRP_NO;
    }

    public LocalDate getMTN_EFF_DT() {
        return MTN_EFF_DT;
    }

    public void setMTN_EFF_DT(LocalDate MTN_EFF_DT) {
        this.MTN_EFF_DT = MTN_EFF_DT;
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return ACCT_NO == record.ACCT_NO &&
                BL_PROD_ID == record.BL_PROD_ID &&
                BL_GRP_NO == record.BL_GRP_NO &&
                Objects.equals(CUST_ID_NO, record.CUST_ID_NO) &&
                Objects.equals(NPA, record.NPA) &&
                Objects.equals(NXX, record.NXX) &&
                Objects.equals(TLN, record.TLN) &&
                Objects.equals(DELETE_IND, record.DELETE_IND) &&
                Objects.equals(ADMIN_CRT_TMSTAMP, record.ADMIN_CRT_TMSTAMP) &&
                Objects.equals(MTN_EFF_DT, record.MTN_EFF_DT);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CUST_ID_NO, ACCT_NO, NPA, NXX, TLN, BL_PROD_ID, DELETE_IND, ADMIN_CRT_TMSTAMP, BL_GRP_NO, MTN_EFF_DT);
    }

    // toString method
    @Override
    public String toString() {
        return "Record{" +
                "CUST_ID_NO='" + CUST_ID_NO + '\'' +
                ", ACCT_NO=" + ACCT_NO +
                ", NPA='" + NPA + '\'' +
                ", NXX='" + NXX + '\'' +
                ", TLN='" + TLN + '\'' +
                ", BL_PROD_ID=" + BL_PROD_ID +
                ", DELETE_IND='" + DELETE_IND + '\'' +
                ", ADMIN_CRT_TMSTAMP=" + ADMIN_CRT_TMSTAMP +
                ", BL_GRP_NO=" + BL_GRP_NO +
                ", MTN_EFF_DT=" + MTN_EFF_DT +
                '}';
    }
}
