package com.example.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import com.example.batch.model.Record;
import com.example.batch.model.CategorizedRecord;

@Component
public class RecordProcessor implements ItemProcessor<Record, CategorizedRecord> {

    @Autowired
    private Map<String, Record> dbRecordsMap;

    @Override
    public CategorizedRecord process(Record item) throws Exception {
        // Create a composite key using the first 9 fields
        String key = createCompositeKey(item);

        // Lookup the record in the database map
        Record dbRecord = dbRecordsMap.get(key);

        if (dbRecord == null) {
            return new CategorizedRecord(item, "only_in_file");
        }

        if (areRecordsEqual(item, dbRecord)) {
            return new CategorizedRecord(item, "matched");
        }

        return new CategorizedRecord(item, "mismatched");
    }

    private String createCompositeKey(Record record) {
        return record.getCUST_ID_NO() + "|" + record.getACCT_NO() + "|" + record.getNPA() + "|" + record.getNXX() + "|" + record.getTLN() + "|" + record.getBL_PROD_ID() + "|" + record.getDELETE_IND() + "|" + record.getADMIN_CRT_TMSTAMP() + "|" + record.getBL_GRP_NO();
    }

    private boolean areRecordsEqual(Record fileRecord, Record dbRecord) {
        return fileRecord.getCUST_ID_NO().equals(dbRecord.getCUST_ID_NO()) &&
                fileRecord.getACCT_NO() == dbRecord.getACCT_NO() &&
                fileRecord.getNPA().equals(dbRecord.getNPA()) &&
                fileRecord.getNXX().equals(dbRecord.getNXX()) &&
                fileRecord.getTLN().equals(dbRecord.getTLN()) &&
                fileRecord.getBL_PROD_ID() == dbRecord.getBL_PROD_ID() &&
                fileRecord.getDELETE_IND().equals(dbRecord.getDELETE_IND()) &&
                isLocalDateTimeEqual(fileRecord.getADMIN_CRT_TMSTAMP(), dbRecord.getADMIN_CRT_TMSTAMP()) &&
                fileRecord.getBL_GRP_NO() == dbRecord.getBL_GRP_NO() &&
                isLocalDateEqual(fileRecord.getMTN_EFF_DT(), dbRecord.getMTN_EFF_DT()) &&
                isLocalDateEqual(fileRecord.getADMIN_EFF_DT(), dbRecord.getADMIN_EFF_DT()) &&
                Double.compare(fileRecord.getADMIN_CHG_AMT(), dbRecord.getADMIN_CHG_AMT()) == 0 &&
                isLocalDateEqual(fileRecord.getBL_PER_FROM_DT(), dbRecord.getBL_PER_FROM_DT()) &&
                isLocalDateEqual(fileRecord.getBL_PER_TO_DT(), dbRecord.getBL_PER_TO_DT()) &&
                fileRecord.getADMIN_FEE_RSN_CD().equals(dbRecord.getADMIN_FEE_RSN_CD()) &&
                fileRecord.getDISCNT_OFFR_ID() == dbRecord.getDISCNT_OFFR_ID() &&
                fileRecord.getVISION_USER_ID_CD().equals(dbRecord.getVISION_USER_ID_CD()) &&
                isLocalDateTimeEqual(fileRecord.getORIG_ADMIN_TMSTAMP(), dbRecord.getORIG_ADMIN_TMSTAMP()) &&
                fileRecord.getORIG_INVOICE_NO() == dbRecord.getORIG_INVOICE_NO() &&
                fileRecord.getCUST_DISC_IND().equals(dbRecord.getCUST_DISC_IND()) &&
                fileRecord.getCNTRCT_TERMS_ID() == dbRecord.getCNTRCT_TERMS_ID() &&
                fileRecord.getCREDIT_ADJ_CD().equals(dbRecord.getCREDIT_ADJ_CD()) &&
                fileRecord.getADMIN_FEE_TYP().equals(dbRecord.getADMIN_FEE_TYP()) &&
                fileRecord.getADMIN_FEE_TYP_ID() == dbRecord.getADMIN_FEE_TYP_ID() &&
                fileRecord.getORIG_TBL_SUBSYS_CD().equals(dbRecord.getORIG_TBL_SUBSYS_CD()) &&
                fileRecord.getCHRG_CAT_CD().equals(dbRecord.getCHRG_CAT_CD()) &&
                fileRecord.getCSEQ_IND().equals(dbRecord.getCSEQ_IND()) &&
                fileRecord.getDB_USERID().equals(dbRecord.getDB_USERID()) &&
                isLocalDateTimeEqual(fileRecord.getDB_TMSTAMP(), dbRecord.getDB_TMSTAMP()) &&
                fileRecord.getSOURCE_CLIENT_ID().equals(dbRecord.getSOURCE_CLIENT_ID()) &&
                fileRecord.getADMIN_CRT_METH_CD().equals(dbRecord.getADMIN_CRT_METH_CD()) &&
                fileRecord.getEQ_ORD_NO() == dbRecord.getEQ_ORD_NO() &&
                fileRecord.getNETACE_LOC_ID().equals(dbRecord.getNETACE_LOC_ID()) &&
                fileRecord.getSVC_PROD_ID_DISCNT() == dbRecord.getSVC_PROD_ID_DISCNT() &&
                fileRecord.getBL_CYC_NO().equals(dbRecord.getBL_CYC_NO()) &&
                fileRecord.getCYC_MTH_YR().equals(dbRecord.getCYC_MTH_YR()) &&
                Double.compare(fileRecord.getTAXABLE_MNY(), dbRecord.getTAXABLE_MNY()) == 0 &&
                fileRecord.getTAX_PROD_ID() == dbRecord.getTAX_PROD_ID() &&
                fileRecord.getOTC_TYPE().equals(dbRecord.getOTC_TYPE()) &&
                fileRecord.getCHGBCK_SUBMISSION_ID().equals(dbRecord.getCHGBCK_SUBMISSION_ID()) &&
                fileRecord.getTAX_GEO_CODE().equals(dbRecord.getTAX_GEO_CODE()) &&
                fileRecord.getDATA_RT_FTPRNT_NO() == dbRecord.getDATA_RT_FTPRNT_NO() &&
                fileRecord.getVODA_COUNTRY_CD().equals(dbRecord.getVODA_COUNTRY_CD()) &&
                fileRecord.getAUDIT_TRANS_ID().equals(dbRecord.getAUDIT_TRANS_ID()) &&
                isLocalDateTimeEqual(fileRecord.getORIG_CREATE_TS(), dbRecord.getORIG_CREATE_TS()) &&
                fileRecord.getINSTALL_LOAN_NO() == dbRecord.getINSTALL_LOAN_NO() &&
                fileRecord.getINSTALL_FIN_MARKET_ID().equals(dbRecord.getINSTALL_FIN_MARKET_ID()) &&
                fileRecord.getLOAN_TERM_MTH_QTY() == dbRecord.getLOAN_TERM_MTH_QTY() &&
                fileRecord.getTERM_BILLED_QTY() == dbRecord.getTERM_BILLED_QTY();
    }

    private boolean isLocalDateEqual(LocalDate date1, LocalDate date2) {
        return date1.isEqual(date2);
    }

    private boolean isLocalDateTimeEqual(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        return dateTime1.equals(dateTime2);
    }
}
