package d_frameworks_and_drivers.database_management.DatabaseInitializer;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;

/**
 * The TaskDBInitializer class initializes the CSV file for storing task data in the database.
 * It creates the CSV file with appropriate headers and is used to start the task database.
 */
public class TaskDBInitializer {

    // The headers for the task CSV file
    String[] TaskHeaders = {"TaskID", "Name", "Description", "Completion Status", "Due Date"};

    /**
     * Constructs an instance of the TaskDBInitializer class. This constructor initializes the CSV file
     * for storing task data by creating the file with headers.
     */
    public TaskDBInitializer() {
        // Create a CSVWriter object and a filewriter object as parameters
        File file = new File("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Tasks/Tasks.csv");
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            // Add headers to the CSV file
            writer.writeNext(TaskHeaders);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

