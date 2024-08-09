package com.example.batch.mapper;

import com.example.batch.model.CsvRecord;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class CustomFieldSetMapper implements FieldSetMapper<CsvRecord> {

    @Override
    public CsvRecord mapFieldSet(FieldSet fieldSet) throws BindException {
        CsvRecord record = new CsvRecord();

        // Map each field manually
        record.setCUST_ID_NO(fieldSet.readString("CUST_ID_NO"));
        record.setACCT_NO(fieldSet.readLong("ACCT_NO"));
        record.setNPA(fieldSet.readString("NPA"));
        record.setNXX(fieldSet.readString("NXX"));
        record.setTLN(fieldSet.readString("TLN"));
        record.setBL_PROD_ID(fieldSet.readLong("BL_PROD_ID"));
        record.setDELETE_IND(fieldSet.readString("DELETE_IND"));
        record.setADMIN_CRT_TMSTAMP(fieldSet.readString("ADMIN_CRT_TMSTAMP"));
        record.setBL_GRP_NO(fieldSet.readLong("BL_GRP_NO"));
        record.setMTN_EFF_DT(fieldSet.readString("MTN_EFF_DT"));
        record.setADMIN_EFF_DT(fieldSet.readString("ADMIN_EFF_DT"));
        record.setADMIN_CHG_AMT(fieldSet.readDouble("ADMIN_CHG_AMT"));
        record.setBL_PER_FROM_DT(fieldSet.readString("BL_PER_FROM_DT"));
        record.setBL_PER_TO_DT(fieldSet.readString("BL_PER_TO_DT"));
        record.setADMIN_FEE_RSN_CD(fieldSet.readString("ADMIN_FEE_RSN_CD"));
        record.setDISCNT_OFFR_ID(fieldSet.readLong("DISCNT_OFFR_ID"));
        record.setVISION_USER_ID_CD(fieldSet.readString("VISION_USER_ID_CD"));
        record.setORIG_ADMIN_TMSTAMP(fieldSet.readString("ORIG_ADMIN_TMSTAMP"));
        record.setORIG_INVOICE_NO(fieldSet.readLong("ORIG_INVOICE_NO"));
        record.setCUST_DISC_IND(fieldSet.readString("CUST_DISC_IND"));
        record.setCNTRCT_TERMS_ID(fieldSet.readInt("CNTRCT_TERMS_ID"));
        record.setCREDIT_ADJ_CD(fieldSet.readString("CREDIT_ADJ_CD"));
        record.setADMIN_FEE_TYP(fieldSet.readString("ADMIN_FEE_TYP"));
        record.setADMIN_FEE_TYP_ID(fieldSet.readLong("ADMIN_FEE_TYP_ID"));
        record.setORIG_TBL_SUBSYS_CD(fieldSet.readString("ORIG_TBL_SUBSYS_CD"));
        record.setCHRG_CAT_CD(fieldSet.readString("CHRG_CAT_CD"));
        record.setCEQ_IND(fieldSet.readString("CEQ_IND"));
        record.setDB_USERID(fieldSet.readString("DB_USERID"));
        record.setDB_TMSTAMP(fieldSet.readString("DB_TMSTAMP"));
        record.setSOURCE_CLIENT_ID(fieldSet.readString("SOURCE_CLIENT_ID"));
        record.setADMIN_CRT_METH_CD(fieldSet.readString("ADMIN_CRT_METH_CD"));
        record.setEQ_ORD_NO(fieldSet.readLong("EQ_ORD_NO"));
        record.setNETACE_LOC_ID(fieldSet.readString("NETACE_LOC_ID"));
        record.setSVC_PROD_ID_DISCNT(fieldSet.readLong("SVC_PROD_ID_DISCNT"));
        record.setBL_CYC_NO(fieldSet.readString("BL_CYC_NO"));
        record.setCYC_MTH_YR(fieldSet.readString("CYC_MTH_YR"));
        record.setTAXABLE_MNY(fieldSet.readDouble("TAXABLE_MNY"));
        record.setTAX_PROD_ID(fieldSet.readLong("TAX_PROD_ID"));
        record.setOTC_TYPE(fieldSet.readString("OTC_TYPE"));
        record.setCHGBCK_SUBMISSION_ID(fieldSet.readString("CHGBCK_SUBMISSION_ID"));
        record.setTAX_GEO_CODE(fieldSet.readString("TAX_GEO_CODE"));
        record.setDATA_RT_FTPRNT_NO(fieldSet.readLong("DATA_RT_FTPRNT_NO"));
        record.setVODA_COUNTRY_CD(fieldSet.readString("VODA_COUNTRY_CD"));
        record.setAUDIT_TRANS_ID(fieldSet.readString("AUDIT_TRANS_ID"));
        record.setORIG_CREATE_TS(fieldSet.readString("ORIG_CREATE_TS"));
        record.setINSTALL_LOAN_NO(fieldSet.readLong("INSTALL_LOAN_NO"));
        record.setINSTALL_FIN_MARKET_ID(fieldSet.readString("INSTALL_FIN_MARKET_ID"));
        record.setLOAN_TERM_MTH_QTY(fieldSet.readInt("LOAN_TERM_MTH_QTY"));
        record.setTERM_BILLED_QTY(fieldSet.readInt("TERM_BILLED_QTY"));

        // Set the original CSV line
        record.setORG_REC(fieldSet.toString());

        return record;
    }
}

package com.example.batch.reader;

import com.example.batch.mapper.CustomFieldSetMapper;
import com.example.batch.model.CsvRecord;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CsvRecordReader {

    @Bean
    @StepScope
    public FlatFileItemReader<CsvRecord> csvRecordItemReader() {
        return new FlatFileItemReaderBuilder<CsvRecord>()
                .name("csvRecordReader")
                .delimited()
                .delimiter("|")
                .names(new String[]{"CUST_ID_NO", "ACCT_NO", "NPA", "NXX", "TLN", "BL_PROD_ID", "DELETE_IND", "ADMIN_CRT_TMSTAMP", "BL_GRP_NO", "MTN_EFF_DT", "ADMIN_EFF_DT", "ADMIN_CHG_AMT", "BL_PER_FROM_DT", "BL_PER_TO_DT", "ADMIN_FEE_RSN_CD", "DISCNT_OFFR_ID", "VISION_USER_ID_CD", "ORIG_ADMIN_TMSTAMP", "ORIG_INVOICE_NO", "CUST_DISC_IND", "CNTRCT_TERMS_ID", "CREDIT_ADJ_CD", "ADMIN_FEE_TYP", "ADMIN_FEE_TYP_ID", "ORIG_TBL_SUBSYS_CD", "CHRG_CAT_CD", "CEQ_IND", "DB_USERID", "DB_TMSTAMP", "SOURCE_CLIENT_ID", "ADMIN_CRT_METH_CD", "EQ_ORD_NO", "NETACE_LOC_ID", "SVC_PROD_ID_DISCNT", "BL_CYC_NO", "CYC_MTH_YR", "TAXABLE_MNY", "TAX_PROD_ID", "OTC_TYPE", "CHGBCK_SUBMISSION_ID", "TAX_GEO_CODE", "DATA_RT_FTPRNT_NO", "VODA_COUNTRY_CD", "AUDIT_TRANS_ID", "ORIG_CREATE_TS", "INSTALL_LOAN_NO", "INSTALL_FIN_MARKET_ID", "LOAN_TERM_MTH_QTY", "TERM_BILLED_QTY"})
                .fieldSetMapper(new CustomFieldSetMapper())
                .build();
    }
}


------------------
package com.example.batch.reader;

import com.example.batch.model.CsvRecord;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindException;

@Configuration
public class CsvRecordReader {

    @Bean
    @StepScope
    public FlatFileItemReader<CsvRecord> csvRecordItemReader() {
        return new FlatFileItemReaderBuilder<CsvRecord>()
                .name("csvRecordReader")
                .delimited()
                .delimiter("|")
                .names(new String[]{
                        "CUST_ID_NO", "ACCT_NO", "NPA", "NXX", "TLN", "BL_PROD_ID", "DELETE_IND", "ADMIN_CRT_TMSTAMP", 
                        "BL_GRP_NO", "MTN_EFF_DT", "ADMIN_EFF_DT", "ADMIN_CHG_AMT", "BL_PER_FROM_DT", "BL_PER_TO_DT", 
                        "ADMIN_FEE_RSN_CD", "DISCNT_OFFR_ID", "VISION_USER_ID_CD", "ORIG_ADMIN_TMSTAMP", 
                        "ORIG_INVOICE_NO", "CUST_DISC_IND", "CNTRCT_TERMS_ID", "CREDIT_ADJ_CD", "ADMIN_FEE_TYP", 
                        "ADMIN_FEE_TYP_ID", "ORIG_TBL_SUBSYS_CD", "CHRG_CAT_CD", "CEQ_IND", "DB_USERID", "DB_TMSTAMP", 
                        "SOURCE_CLIENT_ID", "ADMIN_CRT_METH_CD", "EQ_ORD_NO", "NETACE_LOC_ID", "SVC_PROD_ID_DISCNT", 
                        "BL_CYC_NO", "CYC_MTH_YR", "TAXABLE_MNY", "TAX_PROD_ID", "OTC_TYPE", "CHGBCK_SUBMISSION_ID", 
                        "TAX_GEO_CODE", "DATA_RT_FTPRNT_NO", "VODA_COUNTRY_CD", "AUDIT_TRANS_ID", "ORIG_CREATE_TS", 
                        "INSTALL_LOAN_NO", "INSTALL_FIN_MARKET_ID", "LOAN_TERM_MTH_QTY", "TERM_BILLED_QTY"
                })
                .fieldSetMapper(new CustomCsvRecordFieldSetMapper()) // Use the custom field set mapper
                .build();
    }

    public static class CustomCsvRecordFieldSetMapper implements FieldSetMapper<CsvRecord> {

        @Override
        public CsvRecord mapFieldSet(FieldSet fieldSet) throws BindException {
            CsvRecord record = new CsvRecord();

            record.setCUST_ID_NO(fieldSet.readString("CUST_ID_NO"));
            record.setACCT_NO(readLong(fieldSet, "ACCT_NO"));
            record.setNPA(fieldSet.readString("NPA"));
            record.setNXX(fieldSet.readString("NXX"));
            record.setTLN(fieldSet.readString("TLN"));
            record.setBL_PROD_ID(readLong(fieldSet, "BL_PROD_ID"));
            record.setDELETE_IND(fieldSet.readString("DELETE_IND"));
            record.setADMIN_CRT_TMSTAMP(fieldSet.readString("ADMIN_CRT_TMSTAMP"));
            record.setBL_GRP_NO(readLong(fieldSet, "BL_GRP_NO"));
            record.setMTN_EFF_DT(fieldSet.readString("MTN_EFF_DT"));
            record.setADMIN_EFF_DT(fieldSet.readString("ADMIN_EFF_DT"));
            record.setADMIN_CHG_AMT(readDouble(fieldSet, "ADMIN_CHG_AMT"));
            record.setBL_PER_FROM_DT(fieldSet.readString("BL_PER_FROM_DT"));
            record.setBL_PER_TO_DT(fieldSet.readString("BL_PER_TO_DT"));
            record.setADMIN_FEE_RSN_CD(fieldSet.readString("ADMIN_FEE_RSN_CD"));
            record.setDISCNT_OFFR_ID(readLong(fieldSet, "DISCNT_OFFR_ID"));
            record.setVISION_USER_ID_CD(fieldSet.readString("VISION_USER_ID_CD"));
            record.setORIG_ADMIN_TMSTAMP(fieldSet.readString("ORIG_ADMIN_TMSTAMP"));
            record.setORIG_INVOICE_NO(readLong(fieldSet, "ORIG_INVOICE_NO"));
            record.setCUST_DISC_IND(fieldSet.readString("CUST_DISC_IND"));
            record.setCNTRCT_TERMS_ID(readInteger(fieldSet, "CNTRCT_TERMS_ID"));
            record.setCREDIT_ADJ_CD(fieldSet.readString("CREDIT_ADJ_CD"));
            record.setADMIN_FEE_TYP(fieldSet.readString("ADMIN_FEE_TYP"));
            record.setADMIN_FEE_TYP_ID(readLong(fieldSet, "ADMIN_FEE_TYP_ID"));
            record.setORIG_TBL_SUBSYS_CD(fieldSet.readString("ORIG_TBL_SUBSYS_CD"));
            record.setCHRG_CAT_CD(fieldSet.readString("CHRG_CAT_CD"));
            record.setCEQ_IND(fieldSet.readString("CEQ_IND"));
            record.setDB_USERID(fieldSet.readString("DB_USERID"));
            record.setDB_TMSTAMP(fieldSet.readString("DB_TMSTAMP"));
            record.setSOURCE_CLIENT_ID(fieldSet.readString("SOURCE_CLIENT_ID"));
            record.setADMIN_CRT_METH_CD(fieldSet.readString("ADMIN_CRT_METH_CD"));
            record.setEQ_ORD_NO(readLong(fieldSet, "EQ_ORD_NO"));
            record.setNETACE_LOC_ID(fieldSet.readString("NETACE_LOC_ID"));
            record.setSVC_PROD_ID_DISCNT(readLong(fieldSet, "SVC_PROD_ID_DISCNT"));
            record.setBL_CYC_NO(fieldSet.readString("BL_CYC_NO"));
            record.setCYC_MTH_YR(fieldSet.readString("CYC_MTH_YR"));
            record.setTAXABLE_MNY(readDouble(fieldSet, "TAXABLE_MNY"));
            record.setTAX_PROD_ID(readLong(fieldSet, "TAX_PROD_ID"));
            record.setOTC_TYPE(fieldSet.readString("OTC_TYPE"));
            record.setCHGBCK_SUBMISSION_ID(fieldSet.readString("CHGBCK_SUBMISSION_ID"));
            record.setTAX_GEO_CODE(fieldSet.readString("TAX_GEO_CODE"));
            record.setDATA_RT_FTPRNT_NO(readLong(fieldSet, "DATA_RT_FTPRNT_NO"));
            record.setVODA_COUNTRY_CD(fieldSet.readString("VODA_COUNTRY_CD"));
            record.setAUDIT_TRANS_ID(fieldSet.readString("AUDIT_TRANS_ID"));
            record.setORIG_CREATE_TS(fieldSet.readString("ORIG_CREATE_TS"));
            record.setINSTALL_LOAN_NO(readLong(fieldSet, "INSTALL_LOAN_NO"));
            record.setINSTALL_FIN_MARKET_ID(fieldSet.readString("INSTALL_FIN_MARKET_ID"));
            record.setLOAN_TERM_MTH_QTY(readInteger(fieldSet, "LOAN_TERM_MTH_QTY"));
            record.setTERM_BILLED_QTY(readInteger(fieldSet, "TERM_BILLED_QTY"));

            // Manually set the ORG_REC field with the entire original CSV line
            record.setORG_REC(fieldSet.toString());

            return record;
        }

        private Long readLong(FieldSet fieldSet, String fieldName) {
            String value = fieldSet.readString(fieldName);
            return value != null && !value.trim().isEmpty() ? Long.valueOf(value) : null;
        }

        private Double readDouble(FieldSet fieldSet, String fieldName) {
            String value = fieldSet.readString(fieldName);
            return value != null && !value.trim().isEmpty() ? Double.valueOf(value) : null;
        }

        private Integer readInteger(FieldSet fieldSet, String fieldName) {
            String value = fieldSet.readString(fieldName);
            return value != null && !value.trim().isEmpty() ? Integer.valueOf(value) : null;
        }
    }
}



1)

# Database connection properties
spring.datasource.url=jdbc:oracle:thin:@your_database_url:1521:your_sid
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# Directory paths
input.directory.path=C:/path/to/input
processing.directory.path=C:/path/to/processing
output.directory.path=C:/path/to/output
archive.directory.path=C:/path/to/archive

# Chunk size
csv.chunk.size=50

# Hibernate properties
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.Oracle12cDialect


2)


package com.example.batch;

import com.example.batch.service.DirectoryListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class BatchApplication {

    @Autowired
    private DirectoryListenerService directoryListenerService;

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startDirectoryListener() throws Exception {
        directoryListenerService.startListening();
    }
}


3)


package com.example.batch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Record {

    private String CUST_ID_NO;
    private Long ACCT_NO;
    private String NPA;
    private String NXX;
    private String TLN;
    private Long BL_PROD_ID;
    private String DELETE_IND;
    private Timestamp ADMIN_CRT_TMSTAMP;
    private Long BL_GRP_NO;
    private Date MTN_EFF_DT;
    private Date ADMIN_EFF_DT;
    private Double ADMIN_CHG_AMT;
    private Date BL_PER_FROM_DT;
    private Date BL_PER_TO_DT;
    private String ADMIN_FEE_RSN_CD;
    private Long DISCNT_OFFR_ID;
    private String VISION_USER_ID_CD;
    private Timestamp ORIG_ADMIN_TMSTAMP;
    private Long ORIG_INVOICE_NO;
    private String CUST_DISC_IND;
    private Integer CNTRCT_TERMS_ID;
    private String CREDIT_ADJ_CD;
    private String ADMIN_FEE_TYP;
    private Long ADMIN_FEE_TYP_ID;
    private String ORIG_TBL_SUBSYS_CD;
    private String CHRG_CAT_CD;
    private String CEQ_IND;
    private String DB_USERID;
    private Timestamp DB_TMSTAMP;
    private String SOURCE_CLIENT_ID;
    private String ADMIN_CRT_METH_CD;
    private Long EQ_ORD_NO;
    private String NETACE_LOC_ID;
    private Long SVC_PROD_ID_DISCNT;
    private String BL_CYC_NO;
    private String CYC_MTH_YR;
    private Double TAXABLE_MNY;
    private Long TAX_PROD_ID;
    private String OTC_TYPE;
    private String CHGBCK_SUBMISSION_ID;
    private String TAX_GEO_CODE;
    private Long DATA_RT_FTPRNT_NO;
    private String VODA_COUNTRY_CD;
    private String AUDIT_TRANS_ID;
    private Timestamp ORIG_CREATE_TS;
    private Long INSTALL_LOAN_NO;
    private String INSTALL_FIN_MARKET_ID;
    private Integer LOAN_TERM_MTH_QTY;
    private Integer TERM_BILLED_QTY;
    private String ORG_REC;
}



4)


package com.example.batch.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CsvRecord {

    private String CUST_ID_NO;
    private Long ACCT_NO;
    private String NPA;
    private String NXX;
    private String TLN;
    private Long BL_PROD_ID;
    private String DELETE_IND;
    private String ADMIN_CRT_TMSTAMP;
    private Long BL_GRP_NO;
    private String MTN_EFF_DT;
    private String ADMIN_EFF_DT;
    private Double ADMIN_CHG_AMT;
    private String BL_PER_FROM_DT;
    private String BL_PER_TO_DT;
    private String ADMIN_FEE_RSN_CD;
    private Long DISCNT_OFFR_ID;
    private String VISION_USER_ID_CD;
    private String ORIG_ADMIN_TMSTAMP;
    private Long ORIG_INVOICE_NO;
    private String CUST_DISC_IND;
    private Integer CNTRCT_TERMS_ID;
    private String CREDIT_ADJ_CD;
    private String ADMIN_FEE_TYP;
    private Long ADMIN_FEE_TYP_ID;
    private String ORIG_TBL_SUBSYS_CD;
    private String CHRG_CAT_CD;
    private String CEQ_IND;
    private String DB_USERID;
    private String DB_TMSTAMP;
    private String SOURCE_CLIENT_ID;
    private String ADMIN_CRT_METH_CD;
    private Long EQ_ORD_NO;
    private String NETACE_LOC_ID;
    private Long SVC_PROD_ID_DISCNT;
    private String BL_CYC_NO;
    private String CYC_MTH_YR;
    private Double TAXABLE_MNY;
    private Long TAX_PROD_ID;
    private String OTC_TYPE;
    private String CHGBCK_SUBMISSION_ID;
    private String TAX_GEO_CODE;
    private Long DATA_RT_FTPRNT_NO;
    private String VODA_COUNTRY_CD;
    private String AUDIT_TRANS_ID;
    private String ORIG_CREATE_TS;
    private Long INSTALL_LOAN_NO;
    private String INSTALL_FIN_MARKET_ID;
    private Integer LOAN_TERM_MTH_QTY;
    private Integer TERM_BILLED_QTY;
    private String ORG_REC;
}



5)



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
                rs.getInt("CNTRCT_TERMS_ID"),
                rs.getString("CREDIT_ADJ_CD"),
                rs.getString("ADMIN_FEE_TYP"),
                rs.getLong("ADMIN_FEE_TYP_ID"),
                rs.getString("ORIG_TBL_SUBSYS_CD"),
                rs.getString("CHRG_CAT_CD"),
                rs.getString("CEQ_IND"),
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



6)



package com.example.batch.writer;

import com.example.batch.model.CsvRecord;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecordWriter {

    private final List<CsvRecord> onlyInFile = new ArrayList<>();
    private final List<CsvRecord> perfectlyMatched = new ArrayList<>();
    private final List<CsvRecord> notPerfectlyMatched = new ArrayList<>();

    public void writeOnlyInFile(CsvRecord record) {
        onlyInFile.add(record);
    }

    public void writePerfectlyMatched(CsvRecord record) {
        perfectlyMatched.add(record);
    }

    public void writeNotPerfectlyMatched(CsvRecord record) {
        notPerfectlyMatched.add(record);
    }

    public List<CsvRecord> getOnlyInFile() {
        return onlyInFile;
    }

    public List<CsvRecord> getPerfectlyMatched() {
        return perfectlyMatched;
    }

    public List<CsvRecord> getNotPerfectlyMatched() {
        return notPerfectlyMatched;
    }

    public void clear() {
        onlyInFile.clear();
        perfectlyMatched.clear();
        notPerfectlyMatched.clear();
    }
}



7)



package com.example.batch.reader;

import com.example.batch.model.Record;
import com.example.batch.mapper.RecordRowMapper;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class dbRecordReader {

    @Autowired
    private DataSource dataSource;

    public List<Record> readByQuery(String query) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query(query, new RecordRowMapper());
    }
}



8)



package com.example.batch.reader;

import com.example.batch.model.CsvRecord;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class CsvRecordReader {

    @StepScope
    public FlatFileItemReader<CsvRecord> csvRecordItemReader(Resource resource) {
        return new FlatFileItemReaderBuilder<CsvRecord>()
                .name("csvRecordReader")
                .resource(resource)
                .delimited()
                .delimiter("|")
                .names(new String[]{"CUST_ID_NO", "ACCT_NO", "NPA", "NXX", "TLN", "BL_PROD_ID", "DELETE_IND", "ADMIN_CRT_TMSTAMP", "BL_GRP_NO", "MTN_EFF_DT", "ADMIN_EFF_DT", "ADMIN_CHG_AMT", "BL_PER_FROM_DT", "BL_PER_TO_DT", "ADMIN_FEE_RSN_CD", "DISCNT_OFFR_ID", "VISION_USER_ID_CD", "ORIG_ADMIN_TMSTAMP", "ORIG_INVOICE_NO", "CUST_DISC_IND", "CNTRCT_TERMS_ID", "CREDIT_ADJ_CD", "ADMIN_FEE_TYP", "ADMIN_FEE_TYP_ID", "ORIG_TBL_SUBSYS_CD", "CHRG_CAT_CD", "CEQ_IND", "DB_USERID", "DB_TMSTAMP", "SOURCE_CLIENT_ID", "ADMIN_CRT_METH_CD", "EQ_ORD_NO", "NETACE_LOC_ID", "SVC_PROD_ID_DISCNT", "BL_CYC_NO", "CYC_MTH_YR", "TAXABLE_MNY", "TAX_PROD_ID", "OTC_TYPE", "CHGBCK_SUBMISSION_ID", "TAX_GEO_CODE", "DATA_RT_FTPRNT_NO", "VODA_COUNTRY_CD", "AUDIT_TRANS_ID", "ORIG_CREATE_TS", "INSTALL_LOAN_NO", "INSTALL_FIN_MARKET_ID", "LOAN_TERM_MTH_QTY", "TERM_BILLED_QTY"})
                .linesToSkip(1)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<CsvRecord>() {{
                    setTargetType(CsvRecord.class);
                }})
                .build();
    }
}



9)




package com.example.batch.processor;

import com.example.batch.model.CsvRecord;
import com.example.batch.model.Record;
import com.example.batch.reader.CsvRecordReader;
import com.example.batch.reader.dbRecordReader;
import com.example.batch.writer.RecordWriter;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class CompareRecords implements Tasklet {

    @Autowired
    private CsvRecordReader csvRecordReader;

    @Autowired
    private dbRecordReader dbRecordReader;

    @Autowired
    private RecordWriter recordWriter;

    @Value("${processing.directory.path}")
    private String processingDirectory;

    @Value("${output.directory.path}")
    private String outputDirectory;

    @Value("${archive.directory.path}")
    private String archiveDirectory;

    @Value("${csv.chunk.size}")
    private int chunkSize;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        String fileName = chunkContext.getStepContext().getJobParameters().get("input.file.path").toString();
        processFile(Paths.get(fileName).getFileName().toString());
        return RepeatStatus.FINISHED;
    }

    public void processFile(String fileName) throws Exception {
        Resource resource = new FileSystemResource(processingDirectory + "/" + fileName);
        
        FlatFileItemReader<CsvRecord> reader = csvRecordReader.csvRecordItemReader(resource);
        reader.open(new ExecutionContext());

        BufferedWriter writer = new BufferedWriter(new FileWriter(outputDirectory + "/" + fileName));
        
        List<CsvRecord> chunk = new ArrayList<>();
        CsvRecord csvRecord;

        while ((csvRecord = reader.read()) != null) {
            chunk.add(csvRecord);

            if (chunk.size() == chunkSize) {
                processChunk(chunk, writer);
                chunk.clear();
            }
        }

        // Process remaining records if any
        if (!chunk.isEmpty()) {
            processChunk(chunk, writer);
        }

        reader.close();
        writer.close();

        // Move the processed file to the archive directory
        Path sourcePath = Paths.get(processingDirectory + "/" + fileName);
        Path targetPath = Paths.get(archiveDirectory + "/" + fileName);
        Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
    }

    private void processChunk(List<CsvRecord> chunk, BufferedWriter writer) throws Exception {
        List<Record> dbChunkRecords = fetchMatchingDbRecords(chunk);

        for (CsvRecord csvRecordItem : chunk) {
            Record dbRecord = findMatchingRecord(dbChunkRecords, csvRecordItem);
            if (dbRecord == null) {
                recordWriter.writeOnlyInFile(csvRecordItem);
            } else {
                if (compareAllFields(csvRecordItem, dbRecord)) {
                    recordWriter.writePerfectlyMatched(csvRecordItem);
                } else {
                    recordWriter.writeNotPerfectlyMatched(csvRecordItem);
                }
            }
        }

        // Write only_in_file and partially_matched records to the output file
        writeCategorizedRecords(writer, recordWriter.getOnlyInFile(), recordWriter.getNotPerfectlyMatched());

        // Clear the categorized records for the next chunk
        recordWriter.clear();
    }

    private List<Record> fetchMatchingDbRecords(List<CsvRecord> chunk) {
        StringBuilder sql = new StringBuilder("SELECT * FROM YOUR_TABLE WHERE (CUST_ID_NO, ACCT_NO, NPA, NXX, TLN, BL_PROD_ID, ADMIN_CRT_TMSTAMP, BL_GRP_NO) IN (");

        for (int i = 0; i < chunk.size(); i++) {
            CsvRecord record = chunk.get(i);
            sql.append("(")
                    .append("'").append(record.getCUST_ID_NO()).append("', ")
                    .append(record.getACCT_NO()).append(", ")
                    .append("'").append(record.getNPA()).append("', ")
                    .append("'").append(record.getNXX()).append("', ")
                    .append("'").append(record.getTLN()).append("', ")
                    .append(record.getBL_PROD_ID()).append(", ")
                    .append("'").append(record.getADMIN_CRT_TMSTAMP()).append("', ")
                    .append(record.getBL_GRP_NO())
                    .append(")");

            if (i < chunk.size() - 1) {
                sql.append(", ");
            }
        }

        sql.append(")");

        // Execute SQL and return the result
        return dbRecordReader.readByQuery(sql.toString());
    }

    private Record findMatchingRecord(List<Record> dbRecords, CsvRecord csvRecord) throws ParseException {
        for (Record dbRecord : dbRecords) {
            if (Objects.equals(dbRecord.getCUST_ID_NO(), csvRecord.getCUST_ID_NO())
                    && Objects.equals(dbRecord.getACCT_NO(), csvRecord.getACCT_NO())
                    && Objects.equals(dbRecord.getNPA(), csvRecord.getNPA())
                    && Objects.equals(dbRecord.getNXX(), csvRecord.getNXX())
                    && Objects.equals(dbRecord.getTLN(), csvRecord.getTLN())
                    && Objects.equals(dbRecord.getBL_PROD_ID(), csvRecord.getBL_PROD_ID())
                    && compareTimestamp(dbRecord.getADMIN_CRT_TMSTAMP(), csvRecord.getADMIN_CRT_TMSTAMP())
                    && Objects.equals(dbRecord.getBL_GRP_NO(), csvRecord.getBL_GRP_NO())) {
                return dbRecord;
            }
        }
        return null;
    }

    private boolean compareAllFields(CsvRecord csvRecord, Record dbRecord) throws ParseException {
        return compareDate(dbRecord.getMTN_EFF_DT(), csvRecord.getMTN_EFF_DT())
                && compareDate(dbRecord.getADMIN_EFF_DT(), csvRecord.getADMIN_EFF_DT())
                && compareField(csvRecord.getADMIN_CHG_AMT(), dbRecord.getADMIN_CHG_AMT())
                && compareDate(dbRecord.getBL_PER_FROM_DT(), csvRecord.getBL_PER_FROM_DT())
                && compareDate(dbRecord.getBL_PER_TO_DT(), csvRecord.getBL_PER_TO_DT())
                && compareField(csvRecord.getADMIN_FEE_RSN_CD(), dbRecord.getADMIN_FEE_RSN_CD())
                && compareField(csvRecord.getDISCNT_OFFR_ID(), dbRecord.getDISCNT_OFFR_ID())
                && compareField(csvRecord.getVISION_USER_ID_CD(), dbRecord.getVISION_USER_ID_CD())
                && compareTimestamp(dbRecord.getORIG_ADMIN_TMSTAMP(), csvRecord.getORIG_ADMIN_TMSTAMP())
                && compareField(csvRecord.getORIG_INVOICE_NO(), dbRecord.getORIG_INVOICE_NO())
                && compareField(csvRecord.getCUST_DISC_IND(), dbRecord.getCUST_DISC_IND())
                && compareField(csvRecord.getCNTRCT_TERMS_ID(), dbRecord.getCNTRCT_TERMS_ID())
                && compareField(csvRecord.getCREDIT_ADJ_CD(), dbRecord.getCREDIT_ADJ_CD())
                && compareField(csvRecord.getADMIN_FEE_TYP(), dbRecord.getADMIN_FEE_TYP())
                && compareField(csvRecord.getADMIN_FEE_TYP_ID(), dbRecord.getADMIN_FEE_TYP_ID())
                && compareField(csvRecord.getORIG_TBL_SUBSYS_CD(), dbRecord.getORIG_TBL_SUBSYS_CD())
                && compareField(csvRecord.getCHRG_CAT_CD(), dbRecord.getCHRG_CAT_CD())
                && compareField(csvRecord.getCEQ_IND(), dbRecord.getCEQ_IND())
                && compareField(csvRecord.getDB_USERID(), dbRecord.getDB_USERID())
                && compareTimestamp(dbRecord.getDB_TMSTAMP(), csvRecord.getDB_TMSTAMP())
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
                && compareTimestamp(dbRecord.getORIG_CREATE_TS(), csvRecord.getORIG_CREATE_TS())
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

    private boolean compareDate(Date dbDate, String csvDate) throws ParseException {
        if (dbDate == null && (csvDate == null || csvDate.trim().isEmpty())) {
            return true;
        } else if (dbDate != null && csvDate != null && !csvDate.trim().isEmpty()) {
            return dbDate.equals(convertCsvToDate(csvDate));
        }
        return false;
    }

    private boolean compareTimestamp(Timestamp dbTimestamp, String csvTimestamp) throws ParseException {
        if (dbTimestamp == null && (csvTimestamp == null || csvTimestamp.trim().isEmpty())) {
            return true;
        } else if (dbTimestamp != null && csvTimestamp != null && !csvTimestamp.trim().isEmpty()) {
            return dbTimestamp.equals(convertCsvToTimestamp(csvTimestamp));
        }
        return false;
    }

    // Timestamp Conversions
    private Timestamp convertCsvToTimestamp(String timestampStr) throws ParseException {
        if (timestampStr == null || timestampStr.trim().isEmpty()) {
            return null;
        }
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSSSSS");
        Date date = inputFormat.parse(timestampStr);
        return new Timestamp(date.getTime());
    }

    // Date Conversions
    private Date convertCsvToDate(String dateStr) throws ParseException {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        SimpleDateFormat csvDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return csvDateFormat.parse(dateStr);
    }

    private void writeCategorizedRecords(BufferedWriter writer, List<CsvRecord> onlyInFile, List<CsvRecord> notPerfectlyMatched) throws IOException {
        for (CsvRecord record : onlyInFile) {
            writer.write(record.getORG_REC());
            writer.newLine();
        }
        for (CsvRecord record : notPerfectlyMatched) {
            writer.write(record.getORG_REC());
            writer.newLine();
        }
    }
}



10)



package com.example.batch.config;

import com.example.batch.processor.CompareRecords;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.StepBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Value("${chunk.size}")
    private int chunkSize;

    @Bean
    public Job compareRecordsJob(JobRepository jobRepository, PlatformTransactionManager transactionManager, CompareRecords tasklet) {
        return new JobBuilder("compareRecordsJob", jobRepository)
                .start(compareRecordsStep(jobRepository, transactionManager, tasklet))
                .build();
    }

    @Bean
    public Step compareRecordsStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, CompareRecords tasklet) {
        return new StepBuilder("compareRecordsStep", jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }
}




11)



package com.example.batch.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Service
public class DirectoryListenerService {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job compareRecordsJob;

    @Value("${input.directory.path}")
    private String inputDirectory;

    @Value("${processing.directory.path}")
    private String processingDirectory;

    @Value("${output.directory.path}")
    private String outputDirectory;

    @Value("${archive.directory.path}")
    private String archiveDirectory;

    public void startListening() throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(inputDirectory);
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
                File file = new File(inputDirectory + "/" + event.context().toString());
                processFile(file);
            }
            key.reset();
        }
    }

    private void processFile(File file) throws IOException, InterruptedException {
        Files.move(file.toPath(), Paths.get(processingDirectory + "/" + file.getName()), StandardCopyOption.REPLACE_EXISTING);

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("input.file.path", "file:" + processingDirectory + "/" + file.getName())
                .toJobParameters();

        try {
            jobLauncher.run(compareRecordsJob, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Files.move(Paths.get(processingDirectory + "/" + file.getName()), Paths.get(archiveDirectory + "/" + file.getName()), StandardCopyOption.REPLACE_EXISTING);
    }
}



-------

package com.example.batch.reader;

import com.example.batch.model.CsvRecord;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindException;

@Configuration
public class CsvRecordReader {

    @Bean
    @StepScope
    public FlatFileItemReader<CsvRecord> csvRecordItemReader() {
        return new FlatFileItemReaderBuilder<CsvRecord>()
                .name("csvRecordReader")
                .delimited()
                .delimiter("|")
                .names(new String[]{"CUST_ID_NO", "ACCT_NO", "NPA", "NXX", "TLN", "BL_PROD_ID", "DELETE_IND", "ADMIN_CRT_TMSTAMP", "BL_GRP_NO", "MTN_EFF_DT", "ADMIN_EFF_DT", "ADMIN_CHG_AMT", "BL_PER_FROM_DT", "BL_PER_TO_DT", "ADMIN_FEE_RSN_CD", "DISCNT_OFFR_ID", "VISION_USER_ID_CD", "ORIG_ADMIN_TMSTAMP", "ORIG_INVOICE_NO", "CUST_DISC_IND", "CNTRCT_TERMS_ID", "CREDIT_ADJ_CD", "ADMIN_FEE_TYP", "ADMIN_FEE_TYP_ID", "ORIG_TBL_SUBSYS_CD", "CHRG_CAT_CD", "CEQ_IND", "DB_USERID", "DB_TMSTAMP", "SOURCE_CLIENT_ID", "ADMIN_CRT_METH_CD", "EQ_ORD_NO", "NETACE_LOC_ID", "SVC_PROD_ID_DISCNT", "BL_CYC_NO", "CYC_MTH_YR", "TAXABLE_MNY", "TAX_PROD_ID", "OTC_TYPE", "CHGBCK_SUBMISSION_ID", "TAX_GEO_CODE", "DATA_RT_FTPRNT_NO", "VODA_COUNTRY_CD", "AUDIT_TRANS_ID", "ORIG_CREATE_TS", "INSTALL_LOAN_NO", "INSTALL_FIN_MARKET_ID", "LOAN_TERM_MTH_QTY", "TERM_BILLED_QTY"})
                .fieldSetMapper(new CustomCsvRecordFieldSetMapper())
                .build();
    }

    public static class CustomCsvRecordFieldSetMapper implements FieldSetMapper<CsvRecord> {

        @Override
        public CsvRecord mapFieldSet(FieldSet fieldSet) throws BindException {
            CsvRecord record = new CsvRecord();
            record.setCUST_ID_NO(fieldSet.readString("CUST_ID_NO"));
            record.setACCT_NO(fieldSet.readLong("ACCT_NO", null));
            record.setNPA(fieldSet.readString("NPA"));
            record.setNXX(fieldSet.readString("NXX"));
            record.setTLN(fieldSet.readString("TLN"));
            record.setBL_PROD_ID(fieldSet.readLong("BL_PROD_ID", null));
            record.setDELETE_IND(fieldSet.readString("DELETE_IND"));
            record.setADMIN_CRT_TMSTAMP(fieldSet.readString("ADMIN_CRT_TMSTAMP"));
            record.setBL_GRP_NO(fieldSet.readLong("BL_GRP_NO", null));
            record.setMTN_EFF_DT(fieldSet.readString("MTN_EFF_DT"));
            record.setADMIN_EFF_DT(fieldSet.readString("ADMIN_EFF_DT"));
            record.setADMIN_CHG_AMT(fieldSet.readDouble("ADMIN_CHG_AMT", null));
            record.setBL_PER_FROM_DT(fieldSet.readString("BL_PER_FROM_DT"));
            record.setBL_PER_TO_DT(fieldSet.readString("BL_PER_TO_DT"));
            record.setADMIN_FEE_RSN_CD(fieldSet.readString("ADMIN_FEE_RSN_CD"));
            record.setDISCNT_OFFR_ID(fieldSet.readLong("DISCNT_OFFR_ID", null));
            record.setVISION_USER_ID_CD(fieldSet.readString("VISION_USER_ID_CD"));
            record.setORIG_ADMIN_TMSTAMP(fieldSet.readString("ORIG_ADMIN_TMSTAMP"));
            record.setORIG_INVOICE_NO(fieldSet.readLong("ORIG_INVOICE_NO", null));
            record.setCUST_DISC_IND(fieldSet.readString("CUST_DISC_IND"));
            record.setCNTRCT_TERMS_ID(fieldSet.readInt("CNTRCT_TERMS_ID", null));
            record.setCREDIT_ADJ_CD(fieldSet.readString("CREDIT_ADJ_CD"));
            record.setADMIN_FEE_TYP(fieldSet.readString("ADMIN_FEE_TYP"));
            record.setADMIN_FEE_TYP_ID(fieldSet.readLong("ADMIN_FEE_TYP_ID", null));
            record.setORIG_TBL_SUBSYS_CD(fieldSet.readString("ORIG_TBL_SUBSYS_CD"));
            record.setCHRG_CAT_CD(fieldSet.readString("CHRG_CAT_CD"));
            record.setCEQ_IND(fieldSet.readString("CEQ_IND"));
            record.setDB_USERID(fieldSet.readString("DB_USERID"));
            record.setDB_TMSTAMP(fieldSet.readString("DB_TMSTAMP"));
            record.setSOURCE_CLIENT_ID(fieldSet.readString("SOURCE_CLIENT_ID"));
            record.setADMIN_CRT_METH_CD(fieldSet.readString("ADMIN_CRT_METH_CD"));
            record.setEQ_ORD_NO(fieldSet.readLong("EQ_ORD_NO", null));
            record.setNETACE_LOC_ID(fieldSet.readString("NETACE_LOC_ID"));
            record.setSVC_PROD_ID_DISCNT(fieldSet.readLong("SVC_PROD_ID_DISCNT", null));
            record.setBL_CYC_NO(fieldSet.readString("BL_CYC_NO"));
            record.setCYC_MTH_YR(fieldSet.readString("CYC_MTH_YR"));
            record.setTAXABLE_MNY(fieldSet.readDouble("TAXABLE_MNY", null));
            record.setTAX_PROD_ID(fieldSet.readLong("TAX_PROD_ID", null));
            record.setOTC_TYPE(fieldSet.readString("OTC_TYPE"));
            record.setCHGBCK_SUBMISSION_ID(fieldSet.readString("CHGBCK_SUBMISSION_ID"));
            record.setTAX_GEO_CODE(fieldSet.readString("TAX_GEO_CODE"));
            record.setDATA_RT_FTPRNT_NO(fieldSet.readLong("DATA_RT_FTPRNT_NO", null));
            record.setVODA_COUNTRY_CD(fieldSet.readString("VODA_COUNTRY_CD"));
            record.setAUDIT_TRANS_ID(fieldSet.readString("AUDIT_TRANS_ID"));
            record.setORIG_CREATE_TS(fieldSet.readString("ORIG_CREATE_TS"));
            record.setINSTALL_LOAN_NO(fieldSet.readLong("INSTALL_LOAN_NO", null));
            record.setINSTALL_FIN_MARKET_ID(fieldSet.readString("INSTALL_FIN_MARKET_ID"));
            record.setLOAN_TERM_MTH_QTY(fieldSet.readInt("LOAN_TERM_MTH_QTY", null));
            record.setTERM_BILLED_QTY(fieldSet.readInt("TERM_BILLED_QTY", null));

            // Manually set the ORG_REC field with the entire original CSV line
            record.setORG_REC(fieldSet.toString());

            return record;
        }
    }
}


