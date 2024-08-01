package com.example.batch.Converters;

import com.example.batch.model.Record;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InputConverter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss.SSSSSS");

    public static Record convertToRecord(String[] inputData) {
        Record record = new Record();
        try {
            record.setCUST_ID_NO(inputData[0]);
            record.setACCT_NO(Long.parseLong(inputData[1]));
            record.setNPA(inputData[2]);
            record.setNXX(inputData[3]);
            record.setTLN(inputData[4]);
            record.setBL_PROD_ID(Long.parseLong(inputData[5]));
            record.setDELETE_IND(inputData[6]);
            record.setADMIN_CRT_TMSTAMP(LocalDateTime.parse(inputData[7], DATE_TIME_FORMATTER));
            record.setBL_GRP_NO(Long.parseLong(inputData[8]));
            record.setMTN_EFF_DT(LocalDate.parse(inputData[9], DATE_FORMATTER));
            record.setADMIN_EFF_DT(LocalDate.parse(inputData[10], DATE_FORMATTER));
            record.setADMIN_CHG_AMT(Double.parseDouble(inputData[11]));
            record.setBL_PER_FROM_DT(LocalDate.parse(inputData[12], DATE_FORMATTER));
            record.setBL_PER_TO_DT(LocalDate.parse(inputData[13], DATE_FORMATTER));
            record.setADMIN_FEE_RSN_CD(inputData[14]);
            record.setDISCNT_OFFR_ID(Long.parseLong(inputData[15]));
            record.setVISION_USER_ID_CD(inputData[16]);
            record.setORIG_ADMIN_TMSTAMP(LocalDateTime.parse(inputData[17], DATE_TIME_FORMATTER));
            record.setORIG_INVOICE_NO(Long.parseLong(inputData[18]));
            record.setCUST_DISC_IND(inputData[19]);
            record.setCNTRCT_TERMS_ID(Integer.parseInt(inputData[20]));
            record.setCREDIT_ADJ_CD(inputData[21]);
            record.setADMIN_FEE_TYP(inputData[22]);
            record.setADMIN_FEE_TYP_ID(Long.parseLong(inputData[23]));
            record.setORIG_TBL_SUBSYS_CD(inputData[24]);
            record.setCHRG_CAT_CD(inputData[25]);
            record.setCSEQ_IND(inputData[26]);
            record.setDB_USERID(inputData[27]);
            record.setDB_TMSTAMP(LocalDateTime.parse(inputData[28], DATE_TIME_FORMATTER));
            record.setSOURCE_CLIENT_ID(inputData[29]);
            record.setADMIN_CRT_METH_CD(inputData[30]);
            record.setEQ_ORD_NO(Long.parseLong(inputData[31]));
            record.setNETACE_LOC_ID(inputData[32]);
            record.setSVC_PROD_ID_DISCNT(Long.parseLong(inputData[33]));
            record.setBL_CYC_NO(inputData[34]);
            record.setCYC_MTH_YR(inputData[35]);
            record.setTAXABLE_MNY(Double.parseDouble(inputData[36]));
            record.setTAX_PROD_ID(Long.parseLong(inputData[37]));
            record.setOTC_TYPE(inputData[38]);
            record.setCHGBCK_SUBMISSION_ID(inputData[39]);
            record.setTAX_GEO_CODE(inputData[40]);
            record.setVODA_COUNTRY_CD(inputData[41]);
            record.setDATA_RT_FTPRNT_NO(Integer.parseInt(inputData[42]));
            record.setAUDIT_TRANS_ID(inputData[43]);
            record.setORIG_CREATE_TS(LocalDateTime.parse(inputData[44], DATE_TIME_FORMATTER));
            record.setINSTALL_LOAN_NO(Integer.parseInt(inputData[45]));
            record.setV2_USER_ID(inputData[46]);
            record.setV2_UPDATE_DTM(LocalDate.parse(inputData[47], DATE_FORMATTER));
            record.setINSTALL_FIN_MARKET_ID(inputData[48]);
            record.setLOAN_TERM_MTH_QTY(Integer.parseInt(inputData[49]));
            record.setTERM_BILLED_QTY(Integer.parseInt(inputData[50]));
        } catch (Exception e) {
            System.err.println("Error converting input data: " + e.getMessage());
        }
        return record;
    }
}
