package com.example.batch.config;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import com.example.batch.model.Record;

@Configuration
public class DbReaderConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcCursorItemReader<Record> databaseReader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Record>()
                .dataSource(dataSource)
                .name("recordItemReader")
                .sql("SELECT CUST_ID_NO, ACCT_NO, NPA, NXX, TLN, BL_PROD_ID, DELETE_IND, ADMIN_CRT_TMSTAMP, BL_GRP_NO, MTN_EFF_DT FROM my_table")
                .rowMapper((rs, rowNum) -> new Record(
                        rs.getString("CUST_ID_NO"),
                        rs.getLong("ACCT_NO"),
                        rs.getString("NPA"),
                        rs.getString("NXX"),
                        rs.getString("TLN"),
                        rs.getLong("BL_PROD_ID"),
                        rs.getString("DELETE_IND"),
                        rs.getTimestamp("ADMIN_CRT_TMSTAMP").toLocalDateTime(),
                        rs.getLong("BL_GRP_NO"),
                        rs.getDate("MTN_EFF_DT").toLocalDate()
                ))
                .build();
    }
}