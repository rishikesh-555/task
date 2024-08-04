package com.example.batch.reader;

import com.example.batch.model.Record;
import com.example.batch.mapper.RecordRowMapper;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Component
public class DatabaseRecordReader {

    @Autowired
    private DataSource dataSource;

    private JdbcCursorItemReader<Record> reader;

    @PostConstruct
    public void init() {
        reader = new JdbcCursorItemReaderBuilder<Record>()
                .name("databaseRecordReader")
                .dataSource(dataSource)
                .sql("SELECT * FROM YOUR_TABLE WHERE otc_seq IS NOT NULL")
                .rowMapper(new RecordRowMapper())
                .build();
    }

    public List<Record> readAll() throws Exception {
        reader.open(null);
        List<Record> records = new JdbcTemplate(dataSource).query("SELECT * FROM YOUR_TABLE WHERE otc_seq IS NOT NULL", new RecordRowMapper());
        reader.close();
        return records;
    }
}
