package d_frameworks_and_drivers.database_management.DatabaseInitializer;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;

/**
 * The ProjectDBInitializer class initializes the CSV file for storing project data in the database.
 * It creates the CSV file with appropriate headers and is used to start the project database.
 */
public class ProjectDBInitializer {

    // The headers for the project CSV file
    String[] ProjectHeaders = {"ProjectID", "Name", "Description", "Column ID's"};

    /**
     * Constructs an instance of the ProjectDBInitializer class. This constructor initializes the CSV file
     * for storing project data by creating the file with headers.
     */
    public ProjectDBInitializer() {
        // Create a CSVWriter object and a filewriter object as parameters
        File file = new File("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Projects/Projects.csv");
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            // Add headers to the CSV file
            writer.writeNext(ProjectHeaders);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
