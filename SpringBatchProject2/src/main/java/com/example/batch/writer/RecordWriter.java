package com.example.batch.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import com.example.batch.model.CategorizedRecord;

@Component
public class RecordWriter implements ItemWriter<CategorizedRecord> {

    @Override
    public void write(Chunk<? extends CategorizedRecord> items) throws Exception {
        for (CategorizedRecord item : items) {
            System.out.println(item.getCategory() + ": " + item.getRecord());
        }
    }
}
