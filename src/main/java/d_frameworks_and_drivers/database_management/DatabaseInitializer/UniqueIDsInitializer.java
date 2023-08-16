package d_frameworks_and_drivers.database_management.DatabaseInitializer;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;

/**
 * The UniqueIDsInitializer class initializes the CSV file for storing unique IDs data in the database.
 * It creates the CSV file with appropriate headers and is used to start the unique IDs database.
 */
public class UniqueIDsInitializer {

    // The headers for the unique IDs CSV file
    String[] IdsDbHeaders = {"UUID", "State"};

    /**
     * Constructs an instance of the UniqueIDsInitializer class. This constructor initializes the CSV file
     * for storing unique IDs data by creating the file with headers.
     */
    public UniqueIDsInitializer() {
        // Create a CSVWriter object and a filewriter object as parameters
        File file = new File("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/UniqueIDs/UniqueIDs.csv");
        FileWriter outputfile = null;
        try {
            outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            // Add headers to the CSV file
            writer.writeNext(IdsDbHeaders);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

