package com.example.batch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private long DATA_RT_FTPRNT_NO;
    private String VODA_COUNTRY_CD;
    private String AUDIT_TRANS_ID;
    private LocalDateTime ORIG_CREATE_TS;
    private long INSTALL_LOAN_NO;
    private String INSTALL_FIN_MARKET_ID;
    private int LOAN_TERM_MTH_QTY;
    private int TERM_BILLED_QTY;
}