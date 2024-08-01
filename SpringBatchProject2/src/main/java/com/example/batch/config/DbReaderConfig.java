package com.example.batch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import com.example.batch.model.Record;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class DbReaderConfig {

    private static final Logger logger = LoggerFactory.getLogger(DbReaderConfig.class);

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcCursorItemReader<Record> databaseReader() {
        logger.info("Configuring database reader");
        return new JdbcCursorItemReaderBuilder<Record>()
                .dataSource(dataSource)
                .name("databaseReader")
                .sql("SELECT CUST_ID_NO, ACCT_NO, NPA, NXX, TLN, BL_PROD_ID, DELETE_IND, ADMIN_CRT_TMSTAMP, BL_GRP_NO, MTN_EFF_DT, ADMIN_EFF_DT, ADMIN_CHG_AMT, BL_PER_FROM_DT, BL_PER_TO_DT, ADMIN_FEE_RSN_CD, DISCNT_OFFR_ID, VISION_USER_ID_CD, ORIG_ADMIN_TMSTAMP, ORIG_INVOICE_NO, CUST_DISC_IND, CNTRCT_TERMS_ID, CREDIT_ADJ_CD, ADMIN_FEE_TYP, ADMIN_FEE_TYP_ID, ORIG_TBL_SUBSYS_CD, CHRG_CAT_CD, CSEQ_IND, DB_USERID, DB_TMSTAMP, SOURCE_CLIENT_ID, ADMIN_CRT_METH_CD, EQ_ORD_NO, NETACE_LOC_ID, SVC_PROD_ID_DISCNT, BL_CYC_NO, CYC_MTH_YR, TAXABLE_MNY, TAX_PROD_ID, OTC_TYPE, CHGBCK_SUBMISSION_ID, TAX_GEO_CODE, VODA_COUNTRY_CD, DATA_RT_FTPRNT_NO, AUDIT_TRANS_ID, ORIG_CREATE_TS, INSTALL_LOAN_NO, INSTALL_FIN_MARKET_ID, LOAN_TERM_MTH_QTY, TERM_BILLED_QTY FROM my_table")
                .rowMapper((rs, rowNum) -> {
                    try {
                        return new Record(
                                rs.getString("CUST_ID_NO"),
                                rs.getLong("ACCT_NO"),
                                rs.getString("NPA"),
                                rs.getString("NXX"),
                                rs.getString("TLN"),
                                rs.getLong("BL_PROD_ID"),
                                rs.getString("DELETE_IND"),
                                convertToLocalDateTime(rs.getTimestamp("ADMIN_CRT_TMSTAMP")),
                                rs.getLong("BL_GRP_NO"),
                                convertToLocalDate(rs.getDate("MTN_EFF_DT")),
                                convertToLocalDate(rs.getDate("ADMIN_EFF_DT")),
                                convertToDouble(rs.getBigDecimal("ADMIN_CHG_AMT")),
                                convertToLocalDate(rs.getDate("BL_PER_FROM_DT")),
                                convertToLocalDate(rs.getDate("BL_PER_TO_DT")),
                                rs.getString("ADMIN_FEE_RSN_CD"),
                                rs.getLong("DISCNT_OFFR_ID"),
                                rs.getString("VISION_USER_ID_CD"),
                                convertToLocalDateTime(rs.getTimestamp("ORIG_ADMIN_TMSTAMP")),
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
                                convertToLocalDateTime(rs.getTimestamp("DB_TMSTAMP")),
                                rs.getString("SOURCE_CLIENT_ID"),
                                rs.getString("ADMIN_CRT_METH_CD"),
                                rs.getLong("EQ_ORD_NO"),
                                rs.getString("NETACE_LOC_ID"),
                                rs.getLong("SVC_PROD_ID_DISCNT"),
                                rs.getString("BL_CYC_NO"),
                                rs.getString("CYC_MTH_YR"),
                                convertToDouble(rs.getBigDecimal("TAXABLE_MNY")),
                                rs.getLong("TAX_PROD_ID"),
                                rs.getString("OTC_TYPE"),
                                rs.getString("CHGBCK_SUBMISSION_ID"),
                                rs.getString("TAX_GEO_CODE"),
                                rs.getString("VODA_COUNTRY_CD"),
                                rs.getInt("DATA_RT_FTPRNT_NO"),
                                rs.getString("AUDIT_TRANS_ID"),
                                convertToLocalDateTime(rs.getTimestamp("ORIG_CREATE_TS")),
                                rs.getInt("INSTALL_LOAN_NO"),
                                rs.getString("V2_USER_ID"),
                                convertToLocalDate(rs.getDate("V2_UPDATE_DTM")),
                                rs.getString("INSTALL_FIN_MARKET_ID"),
                                rs.getInt("LOAN_TERM_MTH_QTY"),
                                rs.getInt("TERM_BILLED_QTY")
                        );
                    } catch (SQLException e) {
                        logger.error("Error mapping row to Record", e);
                        return null; // Handle as needed, or rethrow if required
                    }
                })
                .build();
    }

    private LocalDate convertToLocalDate(Date date) {
        try {
            return date != null ? date.toLocalDate() : null;
        } catch (DateTimeException e) {
            logger.warn("Date conversion error: " + e.getMessage());
            return null;
        }
    }

    private LocalDateTime convertToLocalDateTime(Timestamp timestamp) {
        try {
            return timestamp != null ? timestamp.toLocalDateTime() : null;
        } catch (DateTimeException e) {
            logger.warn("Timestamp conversion error: " + e.getMessage());
            return null;
        }
    }

    private Double convertToDouble(java.math.BigDecimal bigDecimal) {
        try {
            return bigDecimal != null ? bigDecimal.doubleValue() : null;
        } catch (NumberFormatException e) {
            logger.warn("BigDecimal conversion error: " + e.getMessage());
            return null;
        }
    }
}