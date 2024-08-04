package com.example.batch.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.batch.model.Record;
import com.example.batch.util.DateConversionUtils;

@Configuration
public class DbRecordsLoader {

    @Autowired
    private DataSource dataSource;

    private Map<String, Record> dbRecordsMap = new HashMap<>();

    @Bean
    public Map<String, Record> dbRecordsMap() {
        if (dbRecordsMap.isEmpty()) {
            loadDbRecords();
        }
        return dbRecordsMap;
    }

    public void loadDbRecords() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Record> records = jdbcTemplate.query("SELECT * FROM table.data i WHERE i.otc_seq IS NOT NULL", (rs, rowNum) -> {
            Record record = new Record();
            record.setCUST_ID_NO(convertString(rs.getString("CUST_ID_NO")));
            record.setACCT_NO(convertLong(rs.getString("ACCT_NO")));
            record.setNPA(convertString(rs.getString("NPA")));
            record.setNXX(convertString(rs.getString("NXX")));
            record.setTLN(convertString(rs.getString("TLN")));
            record.setBL_PROD_ID(convertLong(rs.getString("BL_PROD_ID")));
            record.setDELETE_IND(convertString(rs.getString("DELETE_IND")));
            record.setADMIN_CRT_TMSTAMP(DateConversionUtils.parseLocalDateTime(rs.getString("ADMIN_CRT_TMSTAMP")));
            record.setBL_GRP_NO(convertLong(rs.getString("BL_GRP_NO")));
            record.setMTN_EFF_DT(DateConversionUtils.parseLocalDate(rs.getString("MTN_EFF_DT")));
            record.setADMIN_EFF_DT(DateConversionUtils.parseLocalDate(rs.getString("ADMIN_EFF_DT")));
            record.setADMIN_CHG_AMT(convertDouble(rs.getString("ADMIN_CHG_AMT")));
            record.setBL_PER_FROM_DT(DateConversionUtils.parseLocalDate(rs.getString("BL_PER_FROM_DT")));
            record.setBL_PER_TO_DT(DateConversionUtils.parseLocalDate(rs.getString("BL_PER_TO_DT")));
            record.setADMIN_FEE_RSN_CD(convertString(rs.getString("ADMIN_FEE_RSN_CD")));
            record.setDISCNT_OFFR_ID(convertLong(rs.getString("DISCNT_OFFR_ID")));
            record.setVISION_USER_ID_CD(convertString(rs.getString("VISION_USER_ID_CD")));
            record.setORIG_ADMIN_TMSTAMP(DateConversionUtils.parseLocalDateTime(rs.getString("ORIG_ADMIN_TMSTAMP")));
            record.setORIG_INVOICE_NO(convertLong(rs.getString("ORIG_INVOICE_NO")));
            record.setCUST_DISC_IND(convertString(rs.getString("CUST_DISC_IND")));
            record.setCNTRCT_TERMS_ID(convertInt(rs.getString("CNTRCT_TERMS_ID")));
            record.setCREDIT_ADJ_CD(convertString(rs.getString("CREDIT_ADJ_CD")));
            record.setADMIN_FEE_TYP(convertString(rs.getString("ADMIN_FEE_TYP")));
            record.setADMIN_FEE_TYP_ID(convertLong(rs.getString("ADMIN_FEE_TYP_ID")));
            record.setORIG_TBL_SUBSYS_CD(convertString(rs.getString("ORIG_TBL_SUBSYS_CD")));
            record.setCHRG_CAT_CD(convertString(rs.getString("CHRG_CAT_CD")));
            record.setCSEQ_IND(convertString(rs.getString("CSEQ_IND")));
            record.setDB_USERID(convertString(rs.getString("DB_USERID")));
            record.setDB_TMSTAMP(DateConversionUtils.parseLocalDateTime(rs.getString("DB_TMSTAMP")));
            record.setSOURCE_CLIENT_ID(convertString(rs.getString("SOURCE_CLIENT_ID")));
            record.setADMIN_CRT_METH_CD(convertString(rs.getString("ADMIN_CRT_METH_CD")));
            record.setEQ_ORD_NO(convertLong(rs.getString("EQ_ORD_NO")));
            record.setNETACE_LOC_ID(convertString(rs.getString("NETACE_LOC_ID")));
            record.setSVC_PROD_ID_DISCNT(convertLong(rs.getString("SVC_PROD_ID_DISCNT")));
            record.setBL_CYC_NO(convertString(rs.getString("BL_CYC_NO")));
            record.setCYC_MTH_YR(convertString(rs.getString("CYC_MTH_YR")));
            record.setTAXABLE_MNY(convertDouble(rs.getString("TAXABLE_MNY")));
            record.setTAX_PROD_ID(convertLong(rs.getString("TAX_PROD_ID")));
            record.setOTC_TYPE(convertString(rs.getString("OTC_TYPE")));
            record.setCHGBCK_SUBMISSION_ID(convertString(rs.getString("CHGBCK_SUBMISSION_ID")));
            record.setTAX_GEO_CODE(convertString(rs.getString("TAX_GEO_CODE")));
            record.setDATA_RT_FTPRNT_NO(convertLong(rs.getString("DATA_RT_FTPRNT_NO")));
            record.setVODA_COUNTRY_CD(convertString(rs.getString("VODA_COUNTRY_CD")));
            record.setAUDIT_TRANS_ID(convertString(rs.getString("AUDIT_TRANS_ID")));
            record.setORIG_CREATE_TS(DateConversionUtils.parseLocalDateTime(rs.getString("ORIG_CREATE_TS")));
            record.setINSTALL_LOAN_NO(convertLong(rs.getString("INSTALL_LOAN_NO")));
            record.setINSTALL_FIN_MARKET_ID(convertString(rs.getString("INSTALL_FIN_MARKET_ID")));
            record.setLOAN_TERM_MTH_QTY(convertInt(rs.getString("LOAN_TERM_MTH_QTY")));
            record.setTERM_BILLED_QTY(convertInt(rs.getString("TERM_BILLED_QTY")));

            return record;
        });

        for (Record record : records) {
            String key = createCompositeKey(record);
            dbRecordsMap.put(key, record);
        }
    }

    private String convertString(String value) {
        return (value == null || value.trim().isEmpty()) ? null : value;
    }

    private long convertLong(String value) {
        return (value == null || value.trim().isEmpty()) ? 0L : Long.parseLong(value.trim());
    }

    private double convertDouble(String value) {
        return (value == null || value.trim().isEmpty()) ? 0.0 : Double.parseDouble(value.trim());
    }

    private int convertInt(String value) {
        return (value == null || value.trim().isEmpty()) ? 0 : Integer.parseInt(value.trim());
    }

    private String createCompositeKey(Record record) {
        return record.getCUST_ID_NO() + "|" + record.getACCT_NO() + "|" + record.getNPA() + "|" + record.getNXX() + "|" + record.getTLN() + "|" + record.getBL_PROD_ID() + "|" + record.getDELETE_IND() + "|" + record.getADMIN_CRT_TMSTAMP() + "|" + record.getBL_GRP_NO();
    }
}
