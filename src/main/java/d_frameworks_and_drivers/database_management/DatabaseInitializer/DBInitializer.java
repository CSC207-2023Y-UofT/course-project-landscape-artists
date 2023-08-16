package d_frameworks_and_drivers.database_management.DatabaseInitializer;

import java.io.FileWriter;

import com.opencsv.CSVWriter;
/**
 * The DBInitializer class is responsible for initializing CSV files for different database entities.
 * It creates CSV files for projects, columns, and tasks, each with corresponding headers.
 * The main method in this class is used to trigger the initialization process for different entities.
 *
 * This file is intended to initialize the Database CSV files and the main method should only be called/run ONCE
 */
public class DBInitializer {
    String [] DBNames = {"Projects", "Columns", "Tasks"};

    CSVWriter writer;
    public DBInitializer(){
        {
            for (String s : DBNames) {
                try {
                    writer = new CSVWriter(new FileWriter("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/" + s + "/" + s + ".csv"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * The main method to trigger the initialization process for different entities.
     * It creates instances of initializers for projects, columns, tasks, and unique IDs.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        DBInitializer dbInitializer = new DBInitializer();
        ProjectDBInitializer projectDBInitializer = new ProjectDBInitializer();
        ColumnDBInitializer columnDBInitializer = new ColumnDBInitializer();
        TaskDBInitializer taskDBInitializer = new TaskDBInitializer();
        UniqueIDsInitializer uniqueIDsInitializer = new UniqueIDsInitializer();
    }
}



