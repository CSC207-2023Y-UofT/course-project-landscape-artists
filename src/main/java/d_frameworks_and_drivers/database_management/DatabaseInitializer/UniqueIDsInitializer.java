package d_frameworks_and_drivers.database_management.DatabaseInitializer;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;

public class UniqueIDsInitializer {
    String [] IdsDbHeaders = {"UUID", "State"};

    public UniqueIDsInitializer() {
        // create CSVWriter object filewriter object as parameter
        File file = new File("DatabaseFiles/UniqueIDs/UniqueIDs.csv");
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
