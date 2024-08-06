package com.example.springbatchcompare.processor;

import com.example.springbatchcompare.model.CsvRecord;
import com.example.springbatchcompare.model.Record;
import com.example.springbatchcompare.writer.RecordWriter;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Component
public class CompareRecordsTasklet implements Tasklet {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Resource inputCsv;

    @Autowired
    private RecordWriter recordWriter;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<CsvRecord> csvRecords = readCsvRecords();
        List<Record> dbRecords = readDbRecords();

        for (CsvRecord csvRecord : csvRecords) {
            Record dbRecord = findMatchingRecord(dbRecords, csvRecord);
            if (dbRecord == null) {
                recordWriter.writeOnlyInFile(csvRecord);
            } else {
                if (compareAllFields(csvRecord, dbRecord)) {
                    recordWriter.writePerfectlyMatched(csvRecord);
                } else {
                    recordWriter.writeNotPerfectlyMatched(csvRecord);
                }
            }
        }

        // Print categorized records to screen
        recordWriter.printCategorizedRecords();

        return RepeatStatus.FINISHED;
    }

    private List<CsvRecord> readCsvRecords() {
        // Implement logic to read CSV records using FlatFileItemReader or any other method
        // Ensure to convert empty strings to null values appropriately
        return null; // Replace with actual implementation
    }

    private List<Record> readDbRecords() {
        String sql = "SELECT * FROM YOUR_TABLE WHERE otc_seq IS NOT NULL";
        return jdbcTemplate.query(sql, new RowMapper<Record>() {
            @Override
            public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
                Record record = new Record();
                record.setCUST_ID_NO(rs.getString("CUST_ID_NO"));
                record.setACCT_NO(rs.getLong("ACCT_NO"));
                record.setNPA(rs.getString("NPA"));
                record.setNXX(rs.getString("NXX"));
                record.setTLN(rs.getString("TLN"));
                record.setBL_PROD_ID(rs.getLong("BL_PROD_ID"));
                record.setDELETE_IND(rs.getString("DELETE_IND"));
                record.setADMIN_CRT_TMSTAMP(rs.getTimestamp("ADMIN_CRT_TMSTAMP"));
                record.setBL_GRP_NO(rs.getLong("BL_GRP_NO"));
                record.setMTN_EFF_DT(rs.getDate("MTN_EFF_DT"));
                record.setADMIN_EFF_DT(rs.getDate("ADMIN_EFF_DT"));
                record.setADMIN_CHG_AMT(rs.getDouble("ADMIN_CHG_AMT"));
                record.setBL_PER_FROM_DT(rs.getDate("BL_PER_FROM_DT"));
                record.setBL_PER_TO_DT(rs.getDate("BL_PER_TO_DT"));
                record.setADMIN_FEE_RSN_CD(rs.getString("ADMIN_FEE_RSN_CD"));
                record.setDISCNT_OFFR_ID(rs.getLong("DISCNT_OFFR_ID"));
                record.setVISION_USER_ID_CD(rs.getString("VISION_USER_ID_CD"));
                record.setORIG_ADMIN_TMSTAMP(rs.getTimestamp("ORIG_ADMIN_TMSTAMP"));
                record.setORIG_INVOICE_NO(rs.getLong("ORIG_INVOICE_NO"));
                record.setCUST_DISC_IND(rs.getString("CUST_DISC_IND"));
                record.setCNTRCT_TERMS_ID(rs.getInt("CNTRCT_TERMS_ID"));
                record.setCREDIT_ADJ_CD(rs.getString("CREDIT_ADJ_CD"));
                record.setADMIN_FEE_TYP(rs.getString("ADMIN_FEE_TYP"));
                record.setADMIN_FEE_TYP_ID(rs.getLong("ADMIN_FEE_TYP_ID"));
                record.setORIG_TBL_SUBSYS_CD(rs.getString("ORIG_TBL_SUBSYS_CD"));
                record.setCHRG_CAT_CD(rs.getString("CHRG_CAT_CD"));
                record.setCSEQ_IND(rs.getString("CSEQ_IND"));
                record.setDB_USERID(rs.getString("DB_USERID"));
                record.setDB_TMSTAMP(rs.getTimestamp("DB_TMSTAMP"));
                record.setSOURCE_CLIENT_ID(rs.getString("SOURCE_CLIENT_ID"));
                record.setADMIN_CRT_METH_CD(rs.getString("ADMIN_CRT_METH_CD"));
                record.setEQ_ORD_NO(rs.getLong("EQ_ORD_NO"));
                record.setNETACE_LOC_ID(rs.getString("NETACE_LOC_ID"));
                record.setSVC_PROD_ID_DISCNT(rs.getLong("SVC_PROD_ID_DISCNT"));
                record.setBL_CYC_NO(rs.getString("BL_CYC_NO"));
                record.setCYC_MTH_YR(rs.getString("CYC_MTH_YR"));
                record.setTAXABLE_MNY(rs.getDouble("TAXABLE_MNY"));
                record.setTAX_PROD_ID(rs.getLong("TAX_PROD_ID"));
                record.setOTC_TYPE(rs.getString("OTC_TYPE"));
                record.setCHGBCK_SUBMISSION_ID(rs.getString("CHGBCK_SUBMISSION_ID"));
                record.setTAX_GEO_CODE(rs.getString("TAX_GEO_CODE"));
                record.setDATA_RT_FTPRNT_NO(rs.getLong("DATA_RT_FTPRNT_NO"));
                record.setVODA_COUNTRY_CD(rs.getString("VODA_COUNTRY_CD"));
                record.setAUDIT_TRANS_ID(rs.getString("AUDIT_TRANS_ID"));
                record.setORIG_CREATE_TS(rs.getTimestamp("ORIG_CREATE_TS"));
                record.setINSTALL_LOAN_NO(rs.getLong("INSTALL_LOAN_NO"));
                record.setINSTALL_FIN_MARKET_ID(rs.getString("INSTALL_FIN_MARKET_ID"));
                record.setLOAN_TERM_MTH_QTY(rs.getInt("LOAN_TERM_MTH_QTY"));
                record.setTERM_BILLED_QTY(rs.getInt("TERM_BILLED_QTY"));
                return record;
            }
        });
    }

    private Record findMatchingRecord(List<Record> dbRecords, CsvRecord csvRecord) {
        for (Record dbRecord : dbRecords) {
            if (Objects.equals(dbRecord.getCUST_ID_NO(), csvRecord.getCUST_ID_NO())
                    && Objects.equals(dbRecord.getACCT_NO(), csvRecord.getACCT_NO())
                    && Objects.equals(dbRecord.getNPA(), csvRecord.getNPA())
                    && Objects.equals(dbRecord.getNXX(), csvRecord.getNXX())
                    && Objects.equals(dbRecord.getTLN(), csvRecord.getTLN())
                    && Objects.equals(dbRecord.getBL_PROD_ID(), csvRecord.getBL_PROD_ID())
                    && Objects.equals(dbRecord.getDELETE_IND(), csvRecord.getDELETE_IND())
                    && Objects.equals(convertDbToTimestamp(dbRecord.getADMIN_CRT_TMSTAMP()), convertCsvToTimestamp(csvRecord.getADMIN_CRT_TMSTAMP()))
                    && Objects.equals(dbRecord.getBL_GRP_NO(), csvRecord.getBL_GRP_NO())) {
                return dbRecord;
            }
        }
        return null;
    }

    private boolean compareAllFields(CsvRecord csvRecord, Record dbRecord) throws ParseException {
        return compareField(convertDbToDate(dbRecord.getMTN_EFF_DT()), convertCsvToDate(csvRecord.getMTN_EFF_DT()))
                && compareField(convertDbToDate(dbRecord.getADMIN_EFF_DT()), convertCsvToDate(csvRecord.getADMIN_EFF_DT()))
                && compareField(csvRecord.getADMIN_CHG_AMT(), dbRecord.getADMIN_CHG_AMT())
                && compareField(convertDbToDate(dbRecord.getBL_PER_FROM_DT()), convertCsvToDate(csvRecord.getBL_PER_FROM_DT()))
                && compareField(convertDbToDate(dbRecord.getBL_PER_TO_DT()), convertCsvToDate(csvRecord.getBL_PER_TO_DT()))
                && compareField(csvRecord.getADMIN_FEE_RSN_CD(), dbRecord.getADMIN_FEE_RSN_CD())
                && compareField(csvRecord.getDISCNT_OFFR_ID(), dbRecord.getDISCNT_OFFR_ID())
                && compareField(csvRecord.getVISION_USER_ID_CD(), dbRecord.getVISION_USER_ID_CD())
                && compareField(convertDbToTimestamp(dbRecord.getORIG_ADMIN_TMSTAMP()), convertCsvToTimestamp(csvRecord.getORIG_ADMIN_TMSTAMP()))
                && compareField(csvRecord.getORIG_INVOICE_NO(), dbRecord.getORIG_INVOICE_NO())
                && compareField(csvRecord.getCUST_DISC_IND(), dbRecord.getCUST_DISC_IND())
                && compareField(csvRecord.getCNTRCT_TERMS_ID(), dbRecord.getCNTRCT_TERMS_ID())
                && compareField(csvRecord.getCREDIT_ADJ_CD(), dbRecord.getCREDIT_ADJ_CD())
                && compareField(csvRecord.getADMIN_FEE_TYP(), dbRecord.getADMIN_FEE_TYP())
                && compareField(csvRecord.getADMIN_FEE_TYP_ID(), dbRecord.getADMIN_FEE_TYP_ID())
                && compareField(csvRecord.getORIG_TBL_SUBSYS_CD(), dbRecord.getORIG_TBL_SUBSYS_CD())
                && compareField(csvRecord.getCHRG_CAT_CD(), dbRecord.getCHRG_CAT_CD())
                && compareField(csvRecord.getCSEQ_IND(), dbRecord.getCSEQ_IND())
                && compareField(csvRecord.getDB_USERID(), dbRecord.getDB_USERID())
                && compareField(convertDbToTimestamp(dbRecord.getDB_TMSTAMP()), convertCsvToTimestamp(csvRecord.getDB_TMSTAMP()))
                && compareField(csvRecord.getSOURCE_CLIENT_ID(), dbRecord.getSOURCE_CLIENT_ID())
                && compareField(csvRecord.getADMIN_CRT_METH_CD(), dbRecord.getADMIN_CRT_METH_CD())
                && compareField(csvRecord.getEQ_ORD_NO(), dbRecord.getEQ_ORD_NO())
                && compareField(csvRecord.getNETACE_LOC_ID(), dbRecord.getNETACE_LOC_ID())
                && compareField(csvRecord.getSVC_PROD_ID_DISCNT(), dbRecord.getSVC_PROD_ID_DISCNT())
                && compareField(csvRecord.getBL_CYC_NO(), dbRecord.getBL_CYC_NO())
                && compareField(csvRecord.getCYC_MTH_YR(), dbRecord.getCYC_MTH_YR())
                && compareField(csvRecord.getTAXABLE_MNY(), dbRecord.getTAXABLE_MNY())
                && compareField(csvRecord.getTAX_PROD_ID(), dbRecord.getTAX_PROD_ID())
                && compareField(csvRecord.getOTC_TYPE(), dbRecord.getOTC_TYPE())
                && compareField(csvRecord.getCHGBCK_SUBMISSION_ID(), dbRecord.getCHGBCK_SUBMISSION_ID())
                && compareField(csvRecord.getTAX_GEO_CODE(), dbRecord.getTAX_GEO_CODE())
                && compareField(csvRecord.getDATA_RT_FTPRNT_NO(), dbRecord.getDATA_RT_FTPRNT_NO())
                && compareField(csvRecord.getVODA_COUNTRY_CD(), dbRecord.getVODA_COUNTRY_CD())
                && compareField(csvRecord.getAUDIT_TRANS_ID(), dbRecord.getAUDIT_TRANS_ID())
                && compareField(convertDbToTimestamp(dbRecord.getORIG_CREATE_TS()), convertCsvToTimestamp(csvRecord.getORIG_CREATE_TS()))
                && compareField(csvRecord.getINSTALL_LOAN_NO(), dbRecord.getINSTALL_LOAN_NO())
                && compareField(csvRecord.getINSTALL_FIN_MARKET_ID(), dbRecord.getINSTALL_FIN_MARKET_ID())
                && compareField(csvRecord.getLOAN_TERM_MTH_QTY(), dbRecord.getLOAN_TERM_MTH_QTY())
                && compareField(csvRecord.getTERM_BILLED_QTY(), dbRecord.getTERM_BILLED_QTY());
    }

    private boolean compareField(Object csvValue, Object dbValue) {
        if (csvValue == null || (csvValue instanceof String && ((String) csvValue).trim().isEmpty())) {
            csvValue = null;
        }
        return Objects.equals(csvValue, dbValue);
    }

    private Timestamp convertCsvToTimestamp(String timestampStr) throws ParseException {
        if (timestampStr == null || timestampStr.trim().isEmpty()) {
            return null;
        }
        SimpleDateFormat csvDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSSSSS");
        Date parsedDate = csvDateFormat.parse(timestampStr);
        return new Timestamp(parsedDate.getTime());
    }

    private Timestamp convertDbToTimestamp(String timestampStr) throws ParseException {
        if (timestampStr == null || timestampStr.trim().isEmpty()) {
            return null;
        }
        SimpleDateFormat dbDateFormat = new SimpleDateFormat("dd-MMM-yy hh.mm.ss.SSSSSS a");
        Date parsedDate = dbDateFormat.parse(timestampStr);
        return new Timestamp(parsedDate.getTime());
    }

    private Date convertCsvToDate(String dateStr) throws ParseException {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        SimpleDateFormat csvDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return csvDateFormat.parse(dateStr);
    }

    private Date convertDbToDate(String dateStr) throws ParseException {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        SimpleDateFormat dbDateFormat = new SimpleDateFormat("dd-MMM-yy");
        return dbDateFormat.parse(dateStr);
    }
}
