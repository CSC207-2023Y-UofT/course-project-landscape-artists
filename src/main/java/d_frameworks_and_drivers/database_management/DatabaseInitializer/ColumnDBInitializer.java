package d_frameworks_and_drivers.database_management.DatabaseInitializer;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;

/**
 * The ColumnDBInitializer class is responsible for initializing the CSV file for storing column data.
 * It creates the file and writes the column headers as the initial content.
 */
public class ColumnDBInitializer {
    String[] ColumnHeaders = {"ColumnID", "Name", "Task ID's"};

    /**
     * Constructs a ColumnDBInitializer object and initializes the CSV file for columns.
     * It creates the file and writes the column headers as the initial content.
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

