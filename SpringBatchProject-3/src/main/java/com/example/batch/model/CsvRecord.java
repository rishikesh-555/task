package com.example.batch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CsvRecord {
    private String CUST_ID_NO;
    private Long ACCT_NO;
    private String NPA;
    private String NXX;
    private String TLN;
    private Long BL_PROD_ID;
    private String DELETE_IND;
    private String ADMIN_CRT_TMSTAMP; // Timestamp in CSV file
    private Long BL_GRP_NO;
    private String MTN_EFF_DT; // Date in CSV file
    private String ADMIN_EFF_DT; // Date in CSV file
    private Double ADMIN_CHG_AMT;
    private String BL_PER_FROM_DT; // Date in CSV file
    private String BL_PER_TO_DT; // Date in CSV file
    private String ADMIN_FEE_RSN_CD;
    private Long DISCNT_OFFR_ID;
    private String VISION_USER_ID_CD;
    private String ORIG_ADMIN_TMSTAMP; // Timestamp in CSV file
    private Long ORIG_INVOICE_NO;
    private String CUST_DISC_IND;
    private Long CNTRCT_TERMS_ID;
    private String CREDIT_ADJ_CD;
    private String ADMIN_FEE_TYP;
    private Long ADMIN_FEE_TYP_ID;
    private String ORIG_TBL_SUBSYS_CD;
    private String CHRG_CAT_CD;
    private String CSEQ_IND;
    private String DB_USERID;
    private String DB_TMSTAMP; // Timestamp in CSV file
    private String SOURCE_CLIENT_ID;
    private String ADMIN_CRT_METH_CD;
    private Long EQ_ORD_NO;
    private String NETACE_LOC_ID;
    private Long SVC_PROD_ID_DISCNT;
    private String BL_CYC_NO;
    private String CYC_MTH_YR;
    private Double TAXABLE_MNY;
    private Long TAX_PROD_ID;
    private String OTC_TYPE;
    private String CHGBCK_SUBMISSION_ID;
    private String TAX_GEO_CODE;
    private Long DATA_RT_FTPRNT_NO;
    private String VODA_COUNTRY_CD;
    private String AUDIT_TRANS_ID;
    private String ORIG_CREATE_TS; // Timestamp in CSV file
    private Long INSTALL_LOAN_NO;
    private String INSTALL_FIN_MARKET_ID;
    private Integer LOAN_TERM_MTH_QTY;
    private Integer TERM_BILLED_QTY;
}
