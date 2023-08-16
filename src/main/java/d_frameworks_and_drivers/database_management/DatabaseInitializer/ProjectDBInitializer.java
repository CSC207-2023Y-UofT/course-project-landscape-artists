package d_frameworks_and_drivers.database_management.DatabaseInitializer;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;

/**
 * The ProjectDBInitializer class is responsible for initializing the CSV file for storing project data.
 * It creates the file and writes the project headers as the initial content.
 */
public class ProjectDBInitializer {
    String [] ProjectHeaders = {"ProjectID", "Name", "Description", "Column ID's"};

    /**
     * Constructs a ProjectDBInitializer object and initializes the CSV file for projects.
     * It creates the file and writes the project headers as the initial content.
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
