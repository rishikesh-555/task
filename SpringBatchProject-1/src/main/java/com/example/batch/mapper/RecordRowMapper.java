package com.example.batch.mapper;

import com.example.batch.model.Record;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordRowMapper implements RowMapper<Record> {

    @Override
    public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Record(
                rs.getString("CUST_ID_NO"),
                rs.getLong("ACCT_NO"),
                rs.getString("NPA"),
                rs.getString("NXX"),
                rs.getString("TLN"),
                rs.getLong("BL_PROD_ID"),
                rs.getString("DELETE_IND"),
                rs.getTimestamp("ADMIN_CRT_TMSTAMP"),
                rs.getLong("BL_GRP_NO"),
                rs.getDate("MTN_EFF_DT"),
                rs.getDate("ADMIN_EFF_DT"),
                rs.getDouble("ADMIN_CHG_AMT"),
                rs.getDate("BL_PER_FROM_DT"),
                rs.getDate("BL_PER_TO_DT"),
                rs.getString("ADMIN_FEE_RSN_CD"),
                rs.getLong("DISCNT_OFFR_ID"),
                rs.getString("VISION_USER_ID_CD"),
                rs.getTimestamp("ORIG_ADMIN_TMSTAMP"),
                rs.getLong("ORIG_INVOICE_NO"),
                rs.getString("CUST_DISC_IND"),
                rs.getLong("CNTRCT_TERMS_ID"),
                rs.getString("CREDIT_ADJ_CD"),
                rs.getString("ADMIN_FEE_TYP"),
                rs.getLong("ADMIN_FEE_TYP_ID"),
                rs.getString("ORIG_TBL_SUBSYS_CD"),
                rs.getString("CHRG_CAT_CD"),
                rs.getString("CSEQ_IND"),
                rs.getString("DB_USERID"),
                rs.getTimestamp("DB_TMSTAMP"),
                rs.getString("SOURCE_CLIENT_ID"),
                rs.getString("ADMIN_CRT_METH_CD"),
                rs.getLong("EQ_ORD_NO"),
                rs.getString("NETACE_LOC_ID"),
                rs.getLong("SVC_PROD_ID_DISCNT"),
                rs.getString("BL_CYC_NO"),
                rs.getString("CYC_MTH_YR"),
                rs.getDouble("TAXABLE_MNY"),
                rs.getLong("TAX_PROD_ID"),
                rs.getString("OTC_TYPE"),
                rs.getString("CHGBCK_SUBMISSION_ID"),
                rs.getString("TAX_GEO_CODE"),
                rs.getLong("DATA_RT_FTPRNT_NO"),
                rs.getString("VODA_COUNTRY_CD"),
                rs.getString("AUDIT_TRANS_ID"),
                rs.getTimestamp("ORIG_CREATE_TS"),
                rs.getLong("INSTALL_LOAN_NO"),
                rs.getString("INSTALL_FIN_MARKET_ID"),
                rs.getInt("LOAN_TERM_MTH_QTY"),
                rs.getInt("TERM_BILLED_QTY")
        );
    }
}
