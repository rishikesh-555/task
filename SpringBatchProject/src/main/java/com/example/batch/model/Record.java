package com.example.batch.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
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

    // getters and setters, constructors, equals and hashCode methods
    public Record() {}

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