package DatabaseInitializer;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;

public class ColumnDBInitializer {
    String[] ColumnHeaders = {"ColumnID", "Name", "Description", "Task ID's"};

    public ColumnDBInitializer() {
        // create CSVWriter object filewriter object as parameter
        File file = new File("DatabaseFiles/Columns/Columns.csv");
        FileWriter outputfile = null;
        try {
            outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            writer.writeNext(ColumnHeaders);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

