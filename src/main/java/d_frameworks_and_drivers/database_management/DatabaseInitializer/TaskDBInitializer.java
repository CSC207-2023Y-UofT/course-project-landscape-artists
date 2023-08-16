package d_frameworks_and_drivers.database_management.DatabaseInitializer;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;

/**
 * The TaskDBInitializer class is responsible for initializing the CSV file for storing task data.
 * It creates the file and writes the task headers as the initial content.
 */
public class TaskDBInitializer {
    String[] TaskHeaders = {"TaskID", "Name", "Description", "Completion Status", "Due Date"};

    /**
     * Constructs a TaskDBInitializer object and initializes the CSV file for tasks.
     * It creates the file and writes the task headers as the initial content.
     */
    public TaskDBInitializer() {
        // create CSVWriter object filewriter object as parameter
        File file = new File("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Tasks/Tasks.csv");
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            writer.writeNext(TaskHeaders);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
