0) Applications.properties

# Database connection properties
spring.datasource.url=jdbc:oracle:thin:@your_database_url:1521:your_sid
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# Spring Batch properties
spring.batch.initialize-schema=always

# Directory paths
input.directory.path=/path/to/input/directory
processing.directory.path=/path/to/processing/directory
output.directory.path=/path/to/output/directory
archive.directory.path=/path/to/archive/directory

# CSV chunk size
csv.chunk.size=50


1) CompareRecords:

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
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class CompareRecords implements Tasklet {

    @Autowired
    private CsvRecordReader csvRecordReader;

    @Autowired
    private dbRecordReader dbRecordReader;

    @Autowired
    private RecordWriter recordWriter;

    @Autowired
    private FlatFileItemReader<CsvRecord> csvRecordItemReader;

    @Value("${input.directory.path}")
    private String inputDirectory;

    @Value("${processing.directory.path}")
    private String processingDirectory;

    @Value("${output.directory.path}")
    private String outputDirectory;

    @Value("${archive.directory.path}")
    private String archiveDirectory;

    @Value("${csv.chunk.size}")
    private int chunkSize;

    public void processFile(String fileName) throws Exception {
        // Set the file to be processed
	Resource resource = new FileSystemResource(processingDirectory + "/" + fileName);

        FlatFileItemReader<CsvRecord> reader = csvRecordReader.csvRecordItemReader(resource);

        // Open the CSV reader
        csvRecordItemReader.open(new ExecutionContext());

        // Prepare output file writer
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputDirectory + "/" + fileName));

        CsvRecord csvRecord;

        while ((csvRecord = csvRecordItemReader.read()) != null) {
            List<CsvRecord> chunk = new ArrayList<>();
            while (chunk.size() < chunkSize && csvRecord != null) {
                chunk.add(csvRecord);
                csvRecord = csvRecordItemReader.read();
            }

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

        // Close the CSV reader and the writer
        csvRecordItemReader.close();
        writer.close();

        // Move the processed file to the archive directory
        Path sourcePath = Paths.get(processingDirectory + "/" + fileName);
        Path targetPath = Paths.get(archiveDirectory + "/" + fileName);
        Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
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


2) CsvRecordReader

package com.example.batch.reader;

import com.example.batch.model.CsvRecord;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
                .fieldSetMapper(new BeanWrapperFieldSetMapper<CsvRecord>() {
                    @Override
                    public CsvRecord mapFieldSet(org.springframework.batch.item.file.transform.FieldSet fieldSet) throws org.springframework.validation.BindException {
                        CsvRecord record = super.mapFieldSet(fieldSet);
                        record.setORG_REC(fieldSet.toString());
                        return record;
                    }
                })
                .build();
    }

    public List<CsvRecord> readAll(FlatFileItemReader<CsvRecord> reader, Resource resource) throws Exception {
        List<CsvRecord> records = new ArrayList<>();
        CsvRecord record;

        ExecutionContext executionContext = new ExecutionContext();
        reader.setResource(resource);
        reader.open(executionContext);
        while ((record = reader.read()) != null) {
            records.add(record);
        }
        reader.close();

        return records;
    }
}


3)Batch Application:

package com.example.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@SpringBootApplication
public class BatchApplication {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job compareRecordsJob;

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runJob() throws Exception {
        jobLauncher.run(compareRecordsJob, new JobParametersBuilder().toJobParameters());
    }
}


4)Batch Config

package com.example.batch.config;

import com.example.batch.processor.CompareRecords;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.StepBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

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
                .allowStartIfComplete(true)
                .build();
    }
}


5)Record Row Mapper

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


6)CSV Record:

package com.example.batch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private String ADMIN_CHG_AMT;
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
    private String ORG_REC; // Add this field to store the original CSV record
}


7)Record:

package com.example.batch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
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
    private String ORG_REC; // Add this field to store the original CSV record
}


8)dbRecordReader:

package com.example.batch.reader;

import com.example.batch.model.Record;
import com.example.batch.mapper.RecordRowMapper;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class dbRecordReader {

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcCursorItemReader<Record> databaseRecordItemReader(String query) {
        return new JdbcCursorItemReaderBuilder<Record>()
                .name("databaseRecordReader")
                .dataSource(dataSource)
                .sql(query)
                .rowMapper(new RecordRowMapper())
                .build();
    }

    public List<Record> readByQuery(String query) {
        JdbcCursorItemReader<Record> reader = databaseRecordItemReader(query);
        reader.open(new ExecutionContext());
        List<Record> records = new ArrayList<>();
        Record record;
        while ((record = reader.read()) != null) {
            records.add(record);
        }
        reader.close();
        return records;
    }
}


9)RecordWriter:

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

    public List<CsvRecord> getNotPerfectlyMatched() {
        return notPerfectlyMatched;
    }

    public void clear() {
        onlyInFile.clear();
        notPerfectlyMatched.clear();
        perfectlyMatched.clear();
    }

    public void printCategorizedRecords() {
        System.out.println("Records Only in file:");
        for (CsvRecord record : onlyInFile) {
            System.out.println(record);
        }

        System.out.println("Perfectly matched:");
        for (CsvRecord record : perfectlyMatched) {
            System.out.println(record);
        }

        System.out.println("Not perfectly matched:");
        for (CsvRecord record : notPerfectlyMatched) {
            System.out.println(record);
        }
    }
}

10)
