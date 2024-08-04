package com.example.batch.config;

import com.example.batch.processor.CompareRecordsTasklet;
import com.example.batch.writer.RecordWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public Job compareRecordsJob(JobRepository jobRepository, PlatformTransactionManager transactionManager, CompareRecordsTasklet tasklet, RecordWriter writer) {
        return new JobBuilder("compareRecordsJob", jobRepository)
                .start(compareRecordsStep(jobRepository, transactionManager, tasklet, writer))
                .build();
    }

    @Bean
    public Step compareRecordsStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, CompareRecordsTasklet tasklet, RecordWriter writer) {
        return new StepBuilder("compareRecordsStep", jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }
}