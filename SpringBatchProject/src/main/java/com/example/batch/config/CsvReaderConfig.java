package com.example.batch.config;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import com.example.batch.model.Record;

@Configuration
public class CsvReaderConfig {

    @Bean
    public FlatFileItemReader<Record> reader() {
        return new FlatFileItemReaderBuilder<Record>()
                .name("recordItemReader")
                .resource(new ClassPathResource("input.csv"))
                .delimited()
                .names("CUST_ID_NO", "ACCT_NO", "NPA", "NXX", "TLN", "BL_PROD_ID", "DELETE_IND", "ADMIN_CRT_TMSTAMP",
                        "BL_GRP_NO", "MTN_EFF_DT", "ADMIN_EFF_DT", "ADMIN_CHG_AMT", "BL_PER_FROM_DT", "BL_PER_TO_DT",
                        "ADMIN_FEE_RSN_CD", "DISCNT_OFFR_ID", "VISION_USER_ID_CD", "ORIG_ADMIN_TMSTAMP", "ORIG_INVOICE_NO",
                        "CUST_DISC_IND", "CNTRCT_TERMS_ID", "CREDIT_ADJ_CD", "ADMIN_FEE_TYP", "ADMIN_FEE_TYP_ID",
                        "ORIG_TBL_SUBSYS_CD", "CHRG_CAT_CD", "CSEQ_IND", "DB_USERID", "DB_TMSTAMP", "SOURCE_CLIENT_ID",
                        "ADMIN_CRT_METH_CD", "EQ_ORD_NO", "NETACE_LOC_ID", "SVC_PROD_ID_DISCNT", "ADJUSTMENT_SEQ",
                        "OTC_SEQ", "TAXABLE_MNY", "TAX_PROD_ID", "DISPUTE_SEQ", "OTC_TYPE", "CHGBCK_SUBMISSION_ID",
                        "TAX_GEO_CODE", "VODA_COUNTRY_CD", "DATA_RT_FTPRNT_NO", "AUDIT_TRANS_ID", "ORIG_CREATE_TS",
                        "BL_CYC_NO", "CYC_MTH_YR", "INSTALL_LOAN_NO", "V2_USER_ID", "V2_UPDATE_DTM",
                        "INSTALL_FIN_MARKET_ID", "LOAN_TERM_MTH_QTY", "TERM_BILLED_QTY", "V2_ACCOUNT_NUM", "DOMAIN_ID")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Record>() {{
                    setTargetType(Record.class);
                }})
                .build();
    }
}
