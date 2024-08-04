package com.example.batch.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

import com.example.batch.model.CategorizedRecord;

@Component
public class RecordWriter implements ItemWriter<CategorizedRecord> {

    @Override
    public void write(Chunk<? extends CategorizedRecord> items) throws Exception {
        for (CategorizedRecord item : items) {
            if ("only_in_file".equals(item.getCategory())) {
                // Write to the file for records present only in the file
                System.out.println("Only in file: " + item.getRecord());
            } else if ("matched".equals(item.getCategory())) {
                // Write to the file for records present in both and perfectly matched
                System.out.println("Matched: " + item.getRecord());
            } else if ("mismatched".equals(item.getCategory())) {
                // Write to the file for records present in both but not perfectly matched
                System.out.println("Mismatched: " + item.getRecord());
            }
        }
    }
}
