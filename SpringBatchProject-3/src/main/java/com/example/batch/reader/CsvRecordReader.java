package com.example.springbatchcompare.reader;

import com.example.springbatchcompare.model.CsvRecord;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.IncorrectTokenCountException;
import org.springframework.batch.item.file.transform.IncorrectTokenCountException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindException;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CsvRecordReader {

    @Bean
    @StepScope
    public FlatFileItemReader<CsvRecord> csvRecordItemReader() {
        return new FlatFileItemReaderBuilder<CsvRecord>()
                .name("csvRecordReader")
                .resource(new ClassPathResource("input.csv"))
                .delimited()
                .delimiter("|")
                .names(new String[]{"CUST_ID_NO", "ACCT_NO", "NPA", "NXX", "TLN", "BL_PROD_ID", "DELETE_IND", "ADMIN_CRT_TMSTAMP", "BL_GRP_NO", "MTN_EFF_DT", "ADMIN_EFF_DT", "ADMIN_CHG_AMT", "BL_PER_FROM_DT", "BL_PER_TO_DT", "ADMIN_FEE_RSN_CD", "DISCNT_OFFR_ID", "VISION_USER_ID_CD", "ORIG_ADMIN_TMSTAMP", "ORIG_INVOICE_NO", "CUST_DISC_IND", "CNTRCT_TERMS_ID", "CREDIT_ADJ_CD", "ADMIN_FEE_TYP", "ADMIN_FEE_TYP_ID", "ORIG_TBL_SUBSYS_CD", "CHRG_CAT_CD", "CEQ_IND", "DB_USERID", "DB_TMSTAMP", "SOURCE_CLIENT_ID", "ADMIN_CRT_METH_CD", "EQ_ORD_NO", "NETACE_LOC_ID", "SVC_PROD_ID_DISCNT", "BL_CYC_NO", "CYC_MTH_YR", "TAXABLE_MNY", "TAX_PROD_ID", "OTC_TYPE", "CHGBCK_SUBMISSION_ID", "TAX_GEO_CODE", "DATA_RT_FTPRNT_NO", "VODA_COUNTRY_CD", "AUDIT_TRANS_ID", "ORIG_CREATE_TS", "INSTALL_LOAN_NO", "INSTALL_FIN_MARKET_ID", "LOAN_TERM_MTH_QTY", "TERM_BILLED_QTY"})
                .fieldSetMapper(new CustomFieldSetMapper())
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

    public static class CustomFieldSetMapper implements FieldSetMapper<CsvRecord> {
        @Override
        public CsvRecord mapFieldSet(FieldSet fieldSet) throws BindException {
            CsvRecord record = new CsvRecord();
            for (String name : fieldSet.getNames()) {
                String value = fieldSet.readString(name);
                if (value != null && value.trim().isEmpty()) {
                    value = null;
                }
                switch (name) {
                    case "CUST_ID_NO":
                        record.setCUST_ID_NO(value);
                        break;
                    case "ACCT_NO":
                        record.setACCT_NO(value == null ? null : Long.parseLong(value));
                        break;
                    case "NPA":
                        record.setNPA(value);
                        break;
                    case "NXX":
                        record.setNXX(value);
                        break;
                    case "TLN":
                        record.setTLN(value);
                        break;
                    case "BL_PROD_ID":
                        record.setBL_PROD_ID(value == null ? null : Long.parseLong(value));
                        break;
                    case "DELETE_IND":
                        record.setDELETE_IND(value);
                        break;
                    case "ADMIN_CRT_TMSTAMP":
                        record.setADMIN_CRT_TMSTAMP(value);
                        break;
                    case "BL_GRP_NO":
                        record.setBL_GRP_NO(value == null ? null : Long.parseLong(value));
                        break;
                    case "MTN_EFF_DT":
                        record.setMTN_EFF_DT(value);
                        break;
                    case "ADMIN_EFF_DT":
                        record.setADMIN_EFF_DT(value);
                        break;
                    case "ADMIN_CHG_AMT":
                        record.setADMIN_CHG_AMT(value == null ? null : Double.parseDouble(value));
                        break;
                    case "BL_PER_FROM_DT":
                        record.setBL_PER_FROM_DT(value);
                        break;
                    case "BL_PER_TO_DT":
                        record.setBL_PER_TO_DT(value);
                        break;
                    case "ADMIN_FEE_RSN_CD":
                        record.setADMIN_FEE_RSN_CD(value);
                        break;
                    case "DISCNT_OFFR_ID":
                        record.setDISCNT_OFFR_ID(value == null ? null : Long.parseLong(value));
                        break;
                    case "VISION_USER_ID_CD":
                        record.setVISION_USER_ID_CD(value);
                        break;
                    case "ORIG_ADMIN_TMSTAMP":
                        record.setORIG_ADMIN_TMSTAMP(value);
                        break;
                    case "ORIG_INVOICE_NO":
                        record.setORIG_INVOICE_NO(value == null ? null : Long.parseLong(value));
                        break;
                    case "CUST_DISC_IND":
                        record.setCUST_DISC_IND(value);
                        break;
                    case "CNTRCT_TERMS_ID":
                        record.setCNTRCT_TERMS_ID(value == null ? null : Integer.parseInt(value));
                        break;
                    case "CREDIT_ADJ_CD":
                        record.setCREDIT_ADJ_CD(value);
                        break;
                    case "ADMIN_FEE_TYP":
                        record.setADMIN_FEE_TYP(value);
                        break;
                    case "ADMIN_FEE_TYP_ID":
                        record.setADMIN_FEE_TYP_ID(value == null ? null : Long.parseLong(value));
                        break;
                    case "ORIG_TBL_SUBSYS_CD":
                        record.setORIG_TBL_SUBSYS_CD(value);
                        break;
                    case "CHRG_CAT_CD":
                        record.setCHRG_CAT_CD(value);
                        break;
                    case "CEQ_IND":
                        record.setCEQ_IND(value);
                        break;
                    case "DB_USERID":
                        record.setDB_USERID(value);
                        break;
                    case "DB_TMSTAMP":
                        record.setDB_TMSTAMP(value);
                        break;
                    case "SOURCE_CLIENT_ID":
                        record.setSOURCE_CLIENT_ID(value);
                        break;
                    case "ADMIN_CRT_METH_CD":
                        record.setADMIN_CRT_METH_CD(value);
                        break;
                    case "EQ_ORD_NO":
                        record.setEQ_ORD_NO(value == null ? null : Long.parseLong(value));
                        break;
                    case "NETACE_LOC_ID":
                        record.setNETACE_LOC_ID(value);
                        break;
                    case "SVC_PROD_ID_DISCNT":
                        record.setSVC_PROD_ID_DISCNT(value == null ? null : Long.parseLong(value));
                        break;
                    case "BL_CYC_NO":
                        record.setBL_CYC_NO(value);
                        break;
                    case "CYC_MTH_YR":
                        record.setCYC_MTH_YR(value);
                        break;
                    case "TAXABLE_MNY":
                        record.setTAXABLE_MNY(value == null ? null : Double.parseDouble(value));
                        break;
                    case "TAX_PROD_ID":
                        record.setTAX_PROD_ID(value == null ? null : Long.parseLong(value));
                        break;
                    case "OTC_TYPE":
                        record.setOTC_TYPE(value);
                        break;
                    case "CHGBCK_SUBMISSION_ID":
                        record.setCHGBCK_SUBMISSION_ID(value);
                        break;
                    case "TAX_GEO_CODE":
                        record.setTAX_GEO_CODE(value);
                        break;
                    case "DATA_RT_FTPRNT_NO":
                        record.setDATA_RT_FTPRNT_NO(value == null ? null : Long.parseLong(value));
                        break;
                    case "VODA_COUNTRY_CD":
                        record.setVODA_COUNTRY_CD(value);
                        break;
                    case "AUDIT_TRANS_ID":
                        record.setAUDIT_TRANS_ID(value);
                        break;
                    case "ORIG_CREATE_TS":
                        record.setORIG_CREATE_TS(value);
                        break;
                    case "INSTALL_LOAN_NO":
                        record.setINSTALL_LOAN_NO(value == null ? null : Long.parseLong(value));
                        break;
                    case "INSTALL_FIN_MARKET_ID":
                        record.setINSTALL_FIN_MARKET_ID(value);
                        break;
                    case "LOAN_TERM_MTH_QTY":
                        record.setLOAN_TERM_MTH_QTY(value == null ? null : Integer.parseInt(value));
                        break;
                    case "TERM_BILLED_QTY":
                        record.setTERM_BILLED_QTY(value == null ? null : Integer.parseInt(value));
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + name);
                }
            }
            return record;
        }
    }
}
