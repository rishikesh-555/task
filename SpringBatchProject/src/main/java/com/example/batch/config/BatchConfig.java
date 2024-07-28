package com.example.batch.config;

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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1) {
        return new JobBuilder("importUserJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager,
                      @Qualifier("reader") ItemReader<Record> csvItemReader,
                      ItemProcessor<Record, CategorizedRecord> recordProcessor,
                      ItemWriter<CategorizedRecord> recordWriter) {
        return new StepBuilder("step1", jobRepository)
                .<Record, CategorizedRecord>chunk(10, transactionManager)
                .reader(csvItemReader)
                .processor(recordProcessor)
                .writer(recordWriter)
                .build();
    }
}