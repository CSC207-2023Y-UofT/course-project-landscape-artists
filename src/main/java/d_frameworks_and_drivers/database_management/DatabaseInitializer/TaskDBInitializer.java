package d_frameworks_and_drivers.database_management.DatabaseInitializer;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;

public class TaskDBInitializer {
    String[] TaskHeaders = {"TaskID", "Name", "Description", "Completion Status", "Due Date"};

    public TaskDBInitializer() {
        // create CSVWriter object filewriter object as parameter
        File file = new File("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Tasks/Tasks.csv");
        FileWriter outputfile = null;
        try {
            outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            writer.writeNext(TaskHeaders);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
