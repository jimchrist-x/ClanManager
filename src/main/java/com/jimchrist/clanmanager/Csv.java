package com.jimchrist.clanmanager;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Csv {
    private CSVWriter csvWriter;
    private String filename;
    private char mode;
    public Csv(String filename, char mode) {
        try {
            this.mode = mode;
            FileWriter writer;
            switch (mode) {
                case 'w':
                    long time = System.currentTimeMillis();
                    writer = new FileWriter(filename.replace(".csv", time + ".csv"));
                    this.filename = filename.replace(".csv", time + ".csv");
                    break;
                case 'a':
                    writer = new FileWriter(filename, true);
                    this.filename = filename;
                    break;
                default:
                    writer = new FileWriter(filename);
                    this.filename = filename;
                    break;
            }
            csvWriter = new CSVWriter(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeHeaders(String[] headers) {
        File file = new File(filename);
        if (file.length()==0 || mode == 'w') {
            csvWriter.writeNext(headers);
        }
    }
    public void writeLine(String[] line) {
        csvWriter.writeNext(line);
    }
    public void close() {
        try {
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
