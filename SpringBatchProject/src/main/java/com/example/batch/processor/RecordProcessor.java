package com.example.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.batch.model.CategorizedRecord;
import com.example.batch.model.Record;
import com.example.batch.repository.RecordRepository;

@Component
public class RecordProcessor implements ItemProcessor<Record, CategorizedRecord> {

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public CategorizedRecord process(final Record record) throws Exception {
        Record dbRecord = recordRepository.findByPrimaryKey(
                record.getCUST_ID_NO(), record.getACCT_NO(), record.getNPA(), record.getNXX(), record.getTLN(),
                record.getBL_PROD_ID(), record.getDELETE_IND(), record.getADMIN_CRT_TMSTAMP(), record.getBL_GRP_NO());

        if (dbRecord == null) {
            return new CategorizedRecord(record, "OnlyInFile");
        } else if (dbRecord.equals(record)) {
            return new CategorizedRecord(record, "Matched");
        } else {
            return new CategorizedRecord(record, "Mismatched");
        }
    }
}