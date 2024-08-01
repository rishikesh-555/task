package com.example.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class BatchApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Example usage
		// jobLauncher.run(job, new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters());
		String sql = "select * from table";
		List<String[]> rawRecords = jdbc.Template.query(sql, (rs, rowNum) -> new String[]{
				rs.getString("CUST_ID_NO"),
				rs.getString("ACCT_NO"),
				rs.getString("NPA"),
				// other fields
		});

		rawRecords.forEach(rawRecord -> {
			Record record = InputConverter.convertToRecord(rawRecord);
			System.out.println(OutputConverter.convertRecordToString(record));
		});
	}
}
