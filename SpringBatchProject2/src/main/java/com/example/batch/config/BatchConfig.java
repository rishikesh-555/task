package com.example.batch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.batch.model.CategorizedRecord;
import com.example.batch.model.Record;
import com.example.batch.processor.RecordProcessor;
import com.example.batch.writer.RecordWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.builder.StepBuilderHelper;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    private static final Logger logger = LoggerFactory.getLogger(BatchConfig.class);

    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1) {
        logger.info("Starting job: importUserJob");
        return new JobBuilder("importUserJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager,
                      FlatFileItemReader<Record> recordReader,
                      ItemProcessor<Record, CategorizedRecord> recordProcessor,
                      ItemWriter<CategorizedRecord> recordWriter) {
        logger.info("Starting step: step1");
        return new StepBuilder("step1", jobRepository)
                .<Record, CategorizedRecord>chunk(10, transactionManager)
                .reader(recordReader)
                .processor(recordProcessor)
                .writer(recordWriter)
                .build();
    }
}
