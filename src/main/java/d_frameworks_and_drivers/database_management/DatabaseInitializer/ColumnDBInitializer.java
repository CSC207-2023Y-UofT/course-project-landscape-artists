package d_frameworks_and_drivers.database_management.DatabaseInitializer;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;


public class ColumnDBInitializer {
    String[] ColumnHeaders = {"ColumnID", "Name", "Task ID's"};

    /**
     * Initializes column csv file for database with the above headers.
     */
    public ColumnDBInitializer() {
        // create CSVWriter object filewriter object as parameter
        File file = new File("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Columns/Columns.csv");
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            writer.writeNext(ColumnHeaders);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


