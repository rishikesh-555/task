package com.example.springbatchcompare.reader;

import com.example.springbatchcompare.model.CsvRecord;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvRecordReader {

    @Bean
    @StepScope
    public FlatFileItemReader<CsvRecord> csvRecordItemReader(@Value("#{jobParameters['input.file.path']}") Resource inputCsv) {
        return new FlatFileItemReaderBuilder<CsvRecord>()
                .name("csvRecordReader")
                .resource(inputCsv)
                .delimited()
                .delimiter("|")
                .names(new String[]{"CUST_ID_NO", "ACCT_NO", "NPA", "NXX", "TLN", "BL_PROD_ID", "DELETE_IND", "ADMIN_CRT_TMSTAMP", "BL_GRP_NO", "MTN_EFF_DT", "ADMIN_EFF_DT", "ADMIN_CHG_AMT", "BL_PER_FROM_DT", "BL_PER_TO_DT", "ADMIN_FEE_RSN_CD", "DISCNT_OFFR_ID", "VISION_USER_ID_CD", "ORIG_ADMIN_TMSTAMP", "ORIG_INVOICE_NO", "CUST_DISC_IND", "CNTRCT_TERMS_ID", "CREDIT_ADJ_CD", "ADMIN_FEE_TYP", "ADMIN_FEE_TYP_ID", "ORIG_TBL_SUBSYS_CD", "CHRG_CAT_CD", "CSEQ_IND", "DB_USERID", "DB_TMSTAMP", "SOURCE_CLIENT_ID", "ADMIN_CRT_METH_CD", "EQ_ORD_NO", "NETACE_LOC_ID", "SVC_PROD_ID_DISCNT", "BL_CYC_NO", "CYC_MTH_YR", "TAXABLE_MNY", "TAX_PROD_ID", "OTC_TYPE", "CHGBCK_SUBMISSION_ID", "TAX_GEO_CODE", "DATA_RT_FTPRNT_NO", "VODA_COUNTRY_CD", "AUDIT_TRANS_ID", "ORIG_CREATE_TS", "INSTALL_LOAN_NO", "INSTALL_FIN_MARKET_ID", "LOAN_TERM_MTH_QTY", "TERM_BILLED_QTY"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<CsvRecord>() {
                    @Override
                    public CsvRecord mapFieldSet(FieldSet fieldSet) throws BindException {
                        CsvRecord record = super.mapFieldSet(fieldSet);
                        convertEmptyToNull(record);
                        return record;
                    }

                    private void convertEmptyToNull(CsvRecord record) {
                        Field[] fields = CsvRecord.class.getDeclaredFields();
                        for (Field field : fields) {
                            field.setAccessible(true);
                            try {
                                Object value = field.get(record);
                                if (value instanceof String) {
                                    String strValue = (String) value;
                                    if (strValue != null && strValue.trim().isEmpty()) {
                                        field.set(record, null);
                                    }
                                } else if (value instanceof Long && (Long) value == 0) {
                                    field.set(record, null);
                                } else if (value instanceof Double && (Double) value == 0.0) {
                                    field.set(record, null);
                                }
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                })
                .build();
    }

    public List<CsvRecord> readAll(FlatFileItemReader<CsvRecord> reader) throws Exception {
        List<CsvRecord> records = new ArrayList<>();
        CsvRecord record;

        ExecutionContext executionContext = new ExecutionContext();
        reader.open(executionContext);
        while ((record = reader.read()) != null) {
            records.add(record);
        }
        reader.close();

        return records;
    }
}
