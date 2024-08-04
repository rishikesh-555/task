package com.example.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.batch.loader.DbRecordsLoader;

@SpringBootApplication
public class BatchApplication {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job importUserJob;

	@Autowired
	private DbRecordsLoader dbRecordsLoader;

	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}

	@Bean
	public ApplicationRunner run() {
		return args -> {
			// Load database records
			dbRecordsLoader.loadDbRecords();

			try {
				JobParameters jobParameters = new JobParametersBuilder()
						.addString("inputFile", "input.csv")
						.toJobParameters();

				JobExecution execution = jobLauncher.run(importUserJob, jobParameters);
				System.out.println("Job Exit Status : " + execution.getStatus());
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}
}
