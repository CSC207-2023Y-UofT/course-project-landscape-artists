package d_frameworks_and_drivers.database_management.DatabaseInitializer;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;

/**
 * The UniqueIDsInitializer class is responsible for initializing the CSV file for storing unique IDs.
 * It creates the file and writes the headers for UUIDs and their states as the initial content.
 */
public class UniqueIDsInitializer {
    String [] IdsDbHeaders = {"UUID", "State"};

    /**
     * Constructs a UniqueIDsInitializer object and initializes the CSV file for unique IDs.
     * It creates the file and writes the headers for UUIDs and their states as the initial content.
     */
    public UniqueIDsInitializer() {
        // create CSVWriter object filewriter object as parameter
        File file = new File("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/UniqueIDs/UniqueIDs.csv");
        FileWriter outputfile = null;
        try {
            outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            writer.writeNext(IdsDbHeaders);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
