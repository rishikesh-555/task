package com.example.batch.Converters;

import com.example.batch.model.Record;

import java.time.format.DateTimeFormatter;

public class OutputConverter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yy hh.mm.ss.SSSSSSSSS a");

    public static String convertRecordToString(Record record) {
        if (record == null) {
            return "null";
        }
        return "Record{" +
                "CUST_ID_NO='" + record.getCUST_ID_NO() + '\'' +
                ", ACCT_NO=" + record.getACCT_NO() +
                ", NPA='" + record.getNPA() + '\'' +
                ", NXX='" + record.getNXX() + '\'' +
                ", TLN='" + record.getTLN() + '\'' +
                ", BL_PROD_ID=" + record.getBL_PROD_ID() +
                ", DELETE_IND='" + record.getDELETE_IND() + '\'' +
                ", ADMIN_CRT_TMSTAMP=" + (record.getADMIN_CRT_TMSTAMP() != null ? record.getADMIN_CRT_TMSTAMP().format(DATE_TIME_FORMATTER) : "null") +
                ", BL_GRP_NO=" + record.getBL_GRP_NO() +
                ", MTN_EFF_DT=" + (record.getMTN_EFF_DT() != null ? record.getMTN_EFF_DT().format(DATE_FORMATTER) : "null") +
                ", ADMIN_EFF_DT=" + (record.getADMIN_EFF_DT() != null ? record.getADMIN_EFF_DT().format(DATE_FORMATTER) : "null") +
                ", ADMIN_CHG_AMT=" + record.getADMIN_CHG_AMT() +
                ", BL_PER_FROM_DT=" + (record.getBL_PER_FROM_DT() != null ? record.getBL_PER_FROM_DT().format(DATE_FORMATTER) : "null") +
                ", BL_PER_TO_DT=" + (record.getBL_PER_TO_DT() != null ? record.getBL_PER_TO_DT().format(DATE_FORMATTER) : "null") +
                ", ADMIN_FEE_RSN_CD='" + record.getADMIN_FEE_RSN_CD() + '\'' +
                ", DISCNT_OFFR_ID=" + record.getDISCNT_OFFR_ID() +
                ", VISION_USER_ID_CD='" + record.getVISION_USER_ID_CD() + '\'' +
                ", ORIG_ADMIN_TMSTAMP=" + (record.getORIG_ADMIN_TMSTAMP() != null ? record.getORIG_ADMIN_TMSTAMP().format(DATE_TIME_FORMATTER) : "null") +
                ", ORIG_INVOICE_NO=" + record.getORIG_INVOICE_NO() +
                ", CUST_DISC_IND='" + record.getCUST_DISC_IND() + '\'' +
                ", CNTRCT_TERMS_ID=" + record.getCNTRCT_TERMS_ID() +
                ", CREDIT_ADJ_CD='" + record.getCREDIT_ADJ_CD() + '\'' +
                ", ADMIN_FEE_TYP='" + record.getADMIN_FEE_TYP() + '\'' +
                ", ADMIN_FEE_TYP_ID=" + record.getADMIN_FEE_TYP_ID() +
                ", ORIG_TBL_SUBSYS_CD='" + record.getORIG_TBL_SUBSYS_CD() + '\'' +
                ", CHRG_CAT_CD='" + record.getCHRG_CAT_CD() + '\'' +
                ", CSEQ_IND='" + record.getCSEQ_IND() + '\'' +
                ", DB_USERID='" + record.getDB_USERID() + '\'' +
                ", DB_TMSTAMP=" + (record.getDB_TMSTAMP() != null ? record.getDB_TMSTAMP().format(DATE_TIME_FORMATTER) : "null") +
                ", SOURCE_CLIENT_ID='" + record.getSOURCE_CLIENT_ID() + '\'' +
                ", ADMIN_CRT_METH_CD='" + record.getADMIN_CRT_METH_CD() + '\'' +
                ", EQ_ORD_NO=" + record.getEQ_ORD_NO() +
                ", NETACE_LOC_ID='" + record.getNETACE_LOC_ID() + '\'' +
                ", SVC_PROD_ID_DISCNT=" + record.getSVC_PROD_ID_DISCNT() +
                ", BL_CYC_NO='" + record.getBL_CYC_NO() + '\'' +
                ", CYC_MTH_YR='" + record.getCYC_MTH_YR() + '\'' +
                ", TAXABLE_MNY=" + record.getTAXABLE_MNY() +
                ", TAX_PROD_ID=" + record.getTAX_PROD_ID() +
                ", OTC_TYPE='" + record.getOTC_TYPE() + '\'' +
                ", CHGBCK_SUBMISSION_ID='" + record.getCHGBCK_SUBMISSION_ID() + '\'' +
                ", TAX_GEO_CODE='" + record.getTAX_GEO_CODE() + '\'' +
                ", VODA_COUNTRY_CD='" + record.getVODA_COUNTRY_CD() + '\'' +
                ", DATA_RT_FTPRNT_NO=" + record.getDATA_RT_FTPRNT_NO() +
                ", AUDIT_TRANS_ID='" + record.getAUDIT_TRANS_ID() + '\'' +
                ", ORIG_CREATE_TS=" + (record.getORIG_CREATE_TS() != null ? record.getORIG_CREATE_TS().format(DATE_TIME_FORMATTER) : "null") +
                ", INSTALL_LOAN_NO=" + record.getINSTALL_LOAN_NO() +
                ", V2_USER_ID='" + record.getV2_USER_ID() + '\'' +
                ", V2_UPDATE_DTM=" + (record.getV2_UPDATE_DTM() != null ? record.getV2_UPDATE_DTM().format(DATE_FORMATTER) : "null") +
                ", INSTALL_FIN_MARKET_ID='" + record.getINSTALL_FIN_MARKET_ID() + '\'' +
                ", LOAN_TERM_MTH_QTY=" + record.getLOAN_TERM_MTH_QTY() +
                ", TERM_BILLED_QTY=" + record.getTERM_BILLED_QTY() +
                '}';
    }
}
