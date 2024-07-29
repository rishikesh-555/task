package com.example.batch.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.batch.model.Record;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class RecordRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Record findByPrimaryKey(String CUST_ID_NO, long ACCT_NO, String NPA, String NXX, String TLN,
                                   long BL_PROD_ID, String DELETE_IND, LocalDateTime ADMIN_CRT_TMSTAMP, long BL_GRP_NO) {
        String sql = "SELECT * FROM my_table WHERE CUST_ID_NO = ? AND ACCT_NO = ? AND NPA = ? AND NXX = ? " +
                "AND TLN = ? AND BL_PROD_ID = ? AND DELETE_IND = ? AND ADMIN_CRT_TMSTAMP = ? AND BL_GRP_NO = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{CUST_ID_NO, ACCT_NO, NPA, NXX, TLN,
                    BL_PROD_ID, DELETE_IND, Timestamp.valueOf(ADMIN_CRT_TMSTAMP), BL_GRP_NO}, (rs, rowNum) ->
                    new Record(
                            rs.getString("CUST_ID_NO"),
                            rs.getLong("ACCT_NO"),
                            rs.getString("NPA"),
                            rs.getString("NXX"),
                            rs.getString("TLN"),
                            rs.getLong("BL_PROD_ID"),
                            rs.getString("DELETE_IND"),
                            rs.getTimestamp("ADMIN_CRT_TMSTAMP").toLocalDateTime(),
                            rs.getLong("BL_GRP_NO"),
                            rs.getDate("MTN_EFF_DT").toLocalDate(),
                            rs.getDate("ADMIN_EFF_DT").toLocalDate(),
                            rs.getDouble("ADMIN_CHG_AMT"),
                            rs.getDate("BL_PER_FROM_DT").toLocalDate(),
                            rs.getDate("BL_PER_TO_DT").toLocalDate(),
                            rs.getString("ADMIN_FEE_RSN_CD"),
                            rs.getLong("DISCNT_OFFR_ID"),
                            rs.getString("VISION_USER_ID_CD"),
                            rs.getTimestamp("ORIG_ADMIN_TMSTAMP").toLocalDateTime(),
                            rs.getLong("ORIG_INVOICE_NO"),
                            rs.getString("CUST_DISC_IND"),
                            rs.getInt("CNTRCT_TERMS_ID"),
                            rs.getString("CREDIT_ADJ_CD"),
                            rs.getString("ADMIN_FEE_TYP"),
                            rs.getLong("ADMIN_FEE_TYP_ID"),
                            rs.getString("ORIG_TBL_SUBSYS_CD"),
                            rs.getString("CHRG_CAT_CD"),
                            rs.getString("CSEQ_IND"),
                            rs.getString("DB_USERID"),
                            rs.getTimestamp("DB_TMSTAMP").toLocalDateTime(),
                            rs.getString("SOURCE_CLIENT_ID"),
                            rs.getString("ADMIN_CRT_METH_CD"),
                            rs.getLong("EQ_ORD_NO"),
                            rs.getString("NETACE_LOC_ID"),
                            rs.getLong("SVC_PROD_ID_DISCNT"),
                            rs.getInt("ADJUSTMENT_SEQ"),
                            rs.getInt("OTC_SEQ"),
                            rs.getDouble("TAXABLE_MNY"),
                            rs.getLong("TAX_PROD_ID"),
                            rs.getInt("DISPUTE_SEQ"),
                            rs.getString("OTC_TYPE"),
                            rs.getString("CHGBCK_SUBMISSION_ID"),
                            rs.getString("TAX_GEO_CODE"),
                            rs.getString("VODA_COUNTRY_CD"),
                            rs.getInt("DATA_RT_FTPRNT_NO"),
                            rs.getString("AUDIT_TRANS_ID"),
                            rs.getTimestamp("ORIG_CREATE_TS").toLocalDateTime(),
                            rs.getString("BL_CYC_NO"),
                            rs.getString("CYC_MTH_YR"),
                            rs.getInt("INSTALL_LOAN_NO"),
                            rs.getString("V2_USER_ID"),
                            rs.getDate("V2_UPDATE_DTM").toLocalDate(),
                            rs.getString("INSTALL_FIN_MARKET_ID"),
                            rs.getInt("LOAN_TERM_MTH_QTY"),
                            rs.getInt("TERM_BILLED_QTY"),
                            rs.getString("V2_ACCOUNT_NUM"),
                            rs.getInt("DOMAIN_ID")
                    ));
        } catch (Exception e) {
            return null;
        }
    }
}
