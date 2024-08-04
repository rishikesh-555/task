package com.example.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.batch.model.Record;
import com.example.batch.model.CategorizedRecord;
import com.example.batch.processor.RecordProcessor;
import com.example.batch.writer.RecordWriter;
import com.example.batch.loader.DbRecordsLoader;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private CsvReaderConfig csvReaderConfig;

    @Autowired
    private RecordProcessor recordProcessor;

    @Autowired
    private RecordWriter recordWriter;

    @Autowired
    private DbRecordsLoader dbRecordsLoader;

    @Bean
    public Job importUserJob() {
        // Ensure DB records are loaded before the job runs
        dbRecordsLoader.loadDbRecords();

        return new JobBuilder("importUserJob", jobRepository)
                .start(importUserJobFlow())
                .end()
                .build();
    }

    @Bean
    public Flow importUserJobFlow() {
        return new FlowBuilder<Flow>("importUserJobFlow")
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .<Record, CategorizedRecord>chunk(10, transactionManager)
                .reader(csvReaderConfig.csvItemReader(null))
                .processor(recordProcessor)
                .writer(recordWriter)
                .build();
    }
}
