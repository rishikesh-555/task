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
    private int ADJUSTMENT_SEQ;
    private int OTC_SEQ;
    private double TAXABLE_MNY;
    private long TAX_PROD_ID;
    private int DISPUTE_SEQ;
    private String OTC_TYPE;
    private String CHGBCK_SUBMISSION_ID;
    private String TAX_GEO_CODE;
    private String VODA_COUNTRY_CD;
    private int DATA_RT_FTPRNT_NO;
    private String AUDIT_TRANS_ID;
    private LocalDateTime ORIG_CREATE_TS;
    private String BL_CYC_NO;
    private String CYC_MTH_YR;
    private int INSTALL_LOAN_NO;
    private String V2_USER_ID;
    private LocalDate V2_UPDATE_DTM;
    private String INSTALL_FIN_MARKET_ID;
    private int LOAN_TERM_MTH_QTY;
    private int TERM_BILLED_QTY;
    private String V2_ACCOUNT_NUM;
    private int DOMAIN_ID;

    // Default constructor
    public Record() {}

    // Parameterized constructor
    public Record(String CUST_ID_NO, long ACCT_NO, String NPA, String NXX, String TLN, long BL_PROD_ID, String DELETE_IND,
                  LocalDateTime ADMIN_CRT_TMSTAMP, long BL_GRP_NO, LocalDate MTN_EFF_DT, LocalDate ADMIN_EFF_DT, double ADMIN_CHG_AMT,
                  LocalDate BL_PER_FROM_DT, LocalDate BL_PER_TO_DT, String ADMIN_FEE_RSN_CD, long DISCNT_OFFR_ID, String VISION_USER_ID_CD,
                  LocalDateTime ORIG_ADMIN_TMSTAMP, long ORIG_INVOICE_NO, String CUST_DISC_IND, int CNTRCT_TERMS_ID, String CREDIT_ADJ_CD,
                  String ADMIN_FEE_TYP, long ADMIN_FEE_TYP_ID, String ORIG_TBL_SUBSYS_CD, String CHRG_CAT_CD, String CSEQ_IND, String DB_USERID,
                  LocalDateTime DB_TMSTAMP, String SOURCE_CLIENT_ID, String ADMIN_CRT_METH_CD, long EQ_ORD_NO, String NETACE_LOC_ID,
                  long SVC_PROD_ID_DISCNT, int ADJUSTMENT_SEQ, int OTC_SEQ, double TAXABLE_MNY, long TAX_PROD_ID, int DISPUTE_SEQ,
                  String OTC_TYPE, String CHGBCK_SUBMISSION_ID, String TAX_GEO_CODE, String VODA_COUNTRY_CD, int DATA_RT_FTPRNT_NO,
                  String AUDIT_TRANS_ID, LocalDateTime ORIG_CREATE_TS, String BL_CYC_NO, String CYC_MTH_YR, int INSTALL_LOAN_NO,
                  String V2_USER_ID, LocalDate V2_UPDATE_DTM, String INSTALL_FIN_MARKET_ID, int LOAN_TERM_MTH_QTY, int TERM_BILLED_QTY,
                  String V2_ACCOUNT_NUM, int DOMAIN_ID) {
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
        this.ADMIN_EFF_DT = ADMIN_EFF_DT;
        this.ADMIN_CHG_AMT = ADMIN_CHG_AMT;
        this.BL_PER_FROM_DT = BL_PER_FROM_DT;
        this.BL_PER_TO_DT = BL_PER_TO_DT;
        this.ADMIN_FEE_RSN_CD = ADMIN_FEE_RSN_CD;
        this.DISCNT_OFFR_ID = DISCNT_OFFR_ID;
        this.VISION_USER_ID_CD = VISION_USER_ID_CD;
        this.ORIG_ADMIN_TMSTAMP = ORIG_ADMIN_TMSTAMP;
        this.ORIG_INVOICE_NO = ORIG_INVOICE_NO;
        this.CUST_DISC_IND = CUST_DISC_IND;
        this.CNTRCT_TERMS_ID = CNTRCT_TERMS_ID;
        this.CREDIT_ADJ_CD = CREDIT_ADJ_CD;
        this.ADMIN_FEE_TYP = ADMIN_FEE_TYP;
        this.ADMIN_FEE_TYP_ID = ADMIN_FEE_TYP_ID;
        this.ORIG_TBL_SUBSYS_CD = ORIG_TBL_SUBSYS_CD;
        this.CHRG_CAT_CD = CHRG_CAT_CD;
        this.CSEQ_IND = CSEQ_IND;
        this.DB_USERID = DB_USERID;
        this.DB_TMSTAMP = DB_TMSTAMP;
        this.SOURCE_CLIENT_ID = SOURCE_CLIENT_ID;
        this.ADMIN_CRT_METH_CD = ADMIN_CRT_METH_CD;
        this.EQ_ORD_NO = EQ_ORD_NO;
        this.NETACE_LOC_ID = NETACE_LOC_ID;
        this.SVC_PROD_ID_DISCNT = SVC_PROD_ID_DISCNT;
        this.ADJUSTMENT_SEQ = ADJUSTMENT_SEQ;
        this.OTC_SEQ = OTC_SEQ;
        this.TAXABLE_MNY = TAXABLE_MNY;
        this.TAX_PROD_ID = TAX_PROD_ID;
        this.DISPUTE_SEQ = DISPUTE_SEQ;
        this.OTC_TYPE = OTC_TYPE;
        this.CHGBCK_SUBMISSION_ID = CHGBCK_SUBMISSION_ID;
        this.TAX_GEO_CODE = TAX_GEO_CODE;
        this.VODA_COUNTRY_CD = VODA_COUNTRY_CD;
        this.DATA_RT_FTPRNT_NO = DATA_RT_FTPRNT_NO;
        this.AUDIT_TRANS_ID = AUDIT_TRANS_ID;
        this.ORIG_CREATE_TS = ORIG_CREATE_TS;
        this.BL_CYC_NO = BL_CYC_NO;
        this.CYC_MTH_YR = CYC_MTH_YR;
        this.INSTALL_LOAN_NO = INSTALL_LOAN_NO;
        this.V2_USER_ID = V2_USER_ID;
        this.V2_UPDATE_DTM = V2_UPDATE_DTM;
        this.INSTALL_FIN_MARKET_ID = INSTALL_FIN_MARKET_ID;
        this.LOAN_TERM_MTH_QTY = LOAN_TERM_MTH_QTY;
        this.TERM_BILLED_QTY = TERM_BILLED_QTY;
        this.V2_ACCOUNT_NUM = V2_ACCOUNT_NUM;
        this.DOMAIN_ID = DOMAIN_ID;
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
                ADJUSTMENT_SEQ == record.ADJUSTMENT_SEQ &&
                OTC_SEQ == record.OTC_SEQ &&
                Double.compare(record.TAXABLE_MNY, TAXABLE_MNY) == 0 &&
                TAX_PROD_ID == record.TAX_PROD_ID &&
                DISPUTE_SEQ == record.DISPUTE_SEQ &&
                DATA_RT_FTPRNT_NO == record.DATA_RT_FTPRNT_NO &&
                INSTALL_LOAN_NO == record.INSTALL_LOAN_NO &&
                LOAN_TERM_MTH_QTY == record.LOAN_TERM_MTH_QTY &&
                TERM_BILLED_QTY == record.TERM_BILLED_QTY &&
                DOMAIN_ID == record.DOMAIN_ID &&
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
                Objects.equals(INSTALL_FIN_MARKET_ID, record.INSTALL_FIN_MARKET_ID) &&
                Objects.equals(V2_ACCOUNT_NUM, record.V2_ACCOUNT_NUM);
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
                ", ADMIN_EFF_DT=" + ADMIN_EFF_DT +
                ", ADMIN_CHG_AMT=" + ADMIN_CHG_AMT +
                ", BL_PER_FROM_DT=" + BL_PER_FROM_DT +
                ", BL_PER_TO_DT=" + BL_PER_TO_DT +
                ", ADMIN_FEE_RSN_CD='" + ADMIN_FEE_RSN_CD + '\'' +
                ", DISCNT_OFFR_ID=" + DISCNT_OFFR_ID +
                ", VISION_USER_ID_CD='" + VISION_USER_ID_CD + '\'' +
                ", ORIG_ADMIN_TMSTAMP=" + ORIG_ADMIN_TMSTAMP +
                ", ORIG_INVOICE_NO=" + ORIG_INVOICE_NO +
                ", CUST_DISC_IND='" + CUST_DISC_IND + '\'' +
                ", CNTRCT_TERMS_ID=" + CNTRCT_TERMS_ID +
                ", CREDIT_ADJ_CD='" + CREDIT_ADJ_CD + '\'' +
                ", ADMIN_FEE_TYP='" + ADMIN_FEE_TYP + '\'' +
                ", ADMIN_FEE_TYP_ID=" + ADMIN_FEE_TYP_ID +
                ", ORIG_TBL_SUBSYS_CD='" + ORIG_TBL_SUBSYS_CD + '\'' +
                ", CHRG_CAT_CD='" + CHRG_CAT_CD + '\'' +
                ", CSEQ_IND='" + CSEQ_IND + '\'' +
                ", DB_USERID='" + DB_USERID + '\'' +
                ", DB_TMSTAMP=" + DB_TMSTAMP +
                ", SOURCE_CLIENT_ID='" + SOURCE_CLIENT_ID + '\'' +
                ", ADMIN_CRT_METH_CD='" + ADMIN_CRT_METH_CD + '\'' +
                ", EQ_ORD_NO=" + EQ_ORD_NO +
                ", NETACE_LOC_ID='" + NETACE_LOC_ID + '\'' +
                ", SVC_PROD_ID_DISCNT=" + SVC_PROD_ID_DISCNT +
                ", ADJUSTMENT_SEQ=" + ADJUSTMENT_SEQ +
                ", OTC_SEQ=" + OTC_SEQ +
                ", TAXABLE_MNY=" + TAXABLE_MNY +
                ", TAX_PROD_ID=" + TAX_PROD_ID +
                ", DISPUTE_SEQ=" + DISPUTE_SEQ +
                ", OTC_TYPE='" + OTC_TYPE + '\'' +
                ", CHGBCK_SUBMISSION_ID='" + CHGBCK_SUBMISSION_ID + '\'' +
                ", TAX_GEO_CODE='" + TAX_GEO_CODE + '\'' +
                ", VODA_COUNTRY_CD='" + VODA_COUNTRY_CD + '\'' +
                ", DATA_RT_FTPRNT_NO=" + DATA_RT_FTPRNT_NO +
                ", AUDIT_TRANS_ID='" + AUDIT_TRANS_ID + '\'' +
                ", ORIG_CREATE_TS=" + ORIG_CREATE_TS +
                ", BL_CYC_NO='" + BL_CYC_NO + '\'' +
                ", CYC_MTH_YR='" + CYC_MTH_YR + '\'' +
                ", INSTALL_LOAN_NO=" + INSTALL_LOAN_NO +
                ", V2_USER_ID='" + V2_USER_ID + '\'' +
                ", V2_UPDATE_DTM=" + V2_UPDATE_DTM +
                ", INSTALL_FIN_MARKET_ID='" + INSTALL_FIN_MARKET_ID + '\'' +
                ", LOAN_TERM_MTH_QTY=" + LOAN_TERM_MTH_QTY +
                ", TERM_BILLED_QTY=" + TERM_BILLED_QTY +
                ", V2_ACCOUNT_NUM='" + V2_ACCOUNT_NUM + '\'' +
                ", DOMAIN_ID=" + DOMAIN_ID +
                '}';
    }
}
