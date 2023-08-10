package d_frameworks_and_drivers.database_management.DatabaseInitializer;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;

public class ProjectDBInitializer {
    String [] ProjectHeaders = {"ProjectID", "Name", "Description", "Column ID's"};

    /**
     * Initializes project csv file for database with the above headers.
     */
    public ProjectDBInitializer() {
        // create CSVWriter object filewriter object as parameter
        File file = new File("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Projects/Projects.csv");
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            writer.writeNext(ProjectHeaders);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
