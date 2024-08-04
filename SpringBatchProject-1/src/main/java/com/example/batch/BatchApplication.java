package com.example.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.batch.core.JobParametersBuilder;

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
