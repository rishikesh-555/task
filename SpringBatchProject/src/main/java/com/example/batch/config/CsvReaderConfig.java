package com.example.batch.config;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
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
                .names("CUST_ID_NO", "ACCT_NO", "NPA", "NXX", "TLN", "BL_PROD_ID", "DELETE_IND", "ADMIN_CRT_TMSTAMP", "BL_GRP_NO", "MTN_EFF_DT")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Record>() {{
                    setTargetType(Record.class);
                }})
                .build();
    }
}