package com.example.batch.writer;

import com.example.batch.model.CsvRecord;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecordWriter {

    private final List<CsvRecord> onlyInFile = new ArrayList<>();
    private final List<CsvRecord> perfectlyMatched = new ArrayList<>();
    private final List<CsvRecord> notPerfectlyMatched = new ArrayList<>();

    public void writeOnlyInFile(CsvRecord record) {
        onlyInFile.add(record);
    }

    public void writePerfectlyMatched(CsvRecord record) {
        perfectlyMatched.add(record);
    }

    public void writeNotPerfectlyMatched(CsvRecord record) {
        notPerfectlyMatched.add(record);
    }

    // Methods to retrieve the categorized records
    public List<CsvRecord> getOnlyInFile() {
        return onlyInFile;
    }

    public List<CsvRecord> getPerfectlyMatched() {
        return perfectlyMatched;
    }

    public List<CsvRecord> getNotPerfectlyMatched() {
        return notPerfectlyMatched;
    }

    // Method to print categorized records to screen
    public void printCategorizedRecords() {
        System.out.println("Records only in file:");
        for (CsvRecord record : onlyInFile) {
            System.out.println(record);
        }

        System.out.println("\nPerfectly matched records:");
        for (CsvRecord record : perfectlyMatched) {
            System.out.println(record);
        }

        System.out.println("\nNot perfectly matched records:");
        for (CsvRecord record : notPerfectlyMatched) {
            System.out.println(record);
        }
    }
}
