package com.example.springbatchcompare.processor;

import com.example.springbatchcompare.model.CsvRecord;
import com.example.springbatchcompare.model.Record;
import com.example.springbatchcompare.reader.CsvRecordReader;
import com.example.springbatchcompare.reader.DatabaseRecordReader;
import com.example.springbatchcompare.writer.RecordWriter;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Component
public class CompareRecordsTasklet implements Tasklet {

    @Autowired
    private CsvRecordReader csvRecordReader;

    @Autowired
    private DatabaseRecordReader databaseRecordReader;

    @Autowired
    private RecordWriter recordWriter;

    @Autowired
    private FlatFileItemReader<CsvRecord> csvRecordItemReader;

    @Autowired
    private JdbcCursorItemReader<Record> databaseRecordItemReader;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<CsvRecord> csvRecords = csvRecordReader.readAll(csvRecordItemReader);
        List<Record> dbRecords = databaseRecordReader.readAll(databaseRecordItemReader);

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
        return Objects.equals(convertDbToDate(dbRecord.getMTN_EFF_DT()), convertCsvToDate(csvRecord.getMTN_EFF_DT()))
                && Objects.equals(convertDbToDate(dbRecord.getADMIN_EFF_DT()), convertCsvToDate(csvRecord.getADMIN_EFF_DT()))
                && Objects.equals(csvRecord.getADMIN_CHG_AMT(), dbRecord.getADMIN_CHG_AMT())
                && Objects.equals(convertDbToDate(dbRecord.getBL_PER_FROM_DT()), convertCsvToDate(csvRecord.getBL_PER_FROM_DT()))
                && Objects.equals(convertDbToDate(dbRecord.getBL_PER_TO_DT()), convertCsvToDate(csvRecord.getBL_PER_TO_DT()))
                && Objects.equals(csvRecord.getADMIN_FEE_RSN_CD(), dbRecord.getADMIN_FEE_RSN_CD())
                && Objects.equals(csvRecord.getDISCNT_OFFR_ID(), dbRecord.getDISCNT_OFFR_ID())
                && Objects.equals(csvRecord.getVISION_USER_ID_CD(), dbRecord.getVISION_USER_ID_CD())
                && Objects.equals(convertDbToTimestamp(dbRecord.getORIG_ADMIN_TMSTAMP()), convertCsvToTimestamp(csvRecord.getORIG_ADMIN_TMSTAMP()))
                && Objects.equals(csvRecord.getORIG_INVOICE_NO(), dbRecord.getORIG_INVOICE_NO())
                && Objects.equals(csvRecord.getCUST_DISC_IND(), dbRecord.getCUST_DISC_IND())
                && Objects.equals(csvRecord.getCNTRCT_TERMS_ID(), dbRecord.getCNTRCT_TERMS_ID())
                && Objects.equals(csvRecord.getCREDIT_ADJ_CD(), dbRecord.getCREDIT_ADJ_CD())
                && Objects.equals(csvRecord.getADMIN_FEE_TYP(), dbRecord.getADMIN_FEE_TYP())
                && Objects.equals(csvRecord.getADMIN_FEE_TYP_ID(), dbRecord.getADMIN_FEE_TYP_ID())
                && Objects.equals(csvRecord.getORIG_TBL_SUBSYS_CD(), dbRecord.getORIG_TBL_SUBSYS_CD())
                && Objects.equals(csvRecord.getCHRG_CAT_CD(), dbRecord.getCHRG_CAT_CD())
                && Objects.equals(csvRecord.getCSEQ_IND(), dbRecord.getCSEQ_IND())
                && Objects.equals(csvRecord.getDB_USERID(), dbRecord.getDB_USERID())
                && Objects.equals(convertDbToTimestamp(dbRecord.getDB_TMSTAMP()), convertCsvToTimestamp(csvRecord.getDB_TMSTAMP()))
                && Objects.equals(csvRecord.getSOURCE_CLIENT_ID(), dbRecord.getSOURCE_CLIENT_ID())
                && Objects.equals(csvRecord.getADMIN_CRT_METH_CD(), dbRecord.getADMIN_CRT_METH_CD())
                && Objects.equals(csvRecord.getEQ_ORD_NO(), dbRecord.getEQ_ORD_NO())
                && Objects.equals(csvRecord.getNETACE_LOC_ID(), dbRecord.getNETACE_LOC_ID())
                && Objects.equals(csvRecord.getSVC_PROD_ID_DISCNT(), dbRecord.getSVC_PROD_ID_DISCNT())
                && Objects.equals(csvRecord.getBL_CYC_NO(), dbRecord.getBL_CYC_NO())
                && Objects.equals(csvRecord.getCYC_MTH_YR(), dbRecord.getCYC_MTH_YR())
                && Objects.equals(csvRecord.getTAXABLE_MNY(), dbRecord.getTAXABLE_MNY())
                && Objects.equals(csvRecord.getTAX_PROD_ID(), dbRecord.getTAX_PROD_ID())
                && Objects.equals(csvRecord.getOTC_TYPE(), dbRecord.getOTC_TYPE())
                && Objects.equals(csvRecord.getCHGBCK_SUBMISSION_ID(), dbRecord.getCHGBCK_SUBMISSION_ID())
                && Objects.equals(csvRecord.getTAX_GEO_CODE(), dbRecord.getTAX_GEO_CODE())
                && Objects.equals(csvRecord.getDATA_RT_FTPRNT_NO(), dbRecord.getDATA_RT_FTPRNT_NO())
                && Objects.equals(csvRecord.getVODA_COUNTRY_CD(), dbRecord.getVODA_COUNTRY_CD())
                && Objects.equals(csvRecord.getAUDIT_TRANS_ID(), dbRecord.getAUDIT_TRANS_ID())
                && Objects.equals(convertDbToTimestamp(dbRecord.getORIG_CREATE_TS()), convertCsvToTimestamp(csvRecord.getORIG_CREATE_TS()))
                && Objects.equals(csvRecord.getINSTALL_LOAN_NO(), dbRecord.getINSTALL_LOAN_NO())
                && Objects.equals(csvRecord.getINSTALL_FIN_MARKET_ID(), dbRecord.getINSTALL_FIN_MARKET_ID())
                && Objects.equals(csvRecord.getLOAN_TERM_MTH_QTY(), dbRecord.getLOAN_TERM_MTH_QTY())
                && Objects.equals(csvRecord.getTERM_BILLED_QTY(), dbRecord.getTERM_BILLED_QTY());
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
