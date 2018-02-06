package de.jjedele.tcimporter.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.mozilla.universalchardet.ReaderFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class CSVHelper {

    private CSVHelper() {}

    public static List<Entry> readCSV(File path) throws IOException {
        BufferedReader br = ReaderFactory.createBufferedReader(path);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(br);
        List<Entry> vocabs = new ArrayList<>();
        records.forEach(record -> vocabs.add(new Entry(record.get(0), record.get(1))));
        return vocabs;
    }

}
