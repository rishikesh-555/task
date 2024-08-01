package com.example.batch.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategorizedRecord {

    private Record record;
    private String category;

    public CategorizedRecord(Record record, String category) {
        this.record = record;
        this.category = category;
    }
}