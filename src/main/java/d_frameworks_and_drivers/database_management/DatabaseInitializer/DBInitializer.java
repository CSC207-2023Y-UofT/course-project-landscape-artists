package d_frameworks_and_drivers.database_management.DatabaseInitializer;

import java.io.FileWriter;

import com.opencsv.CSVWriter;

/**
 * The DBInitializer class initializes CSV files for storing data related to projects, columns, and tasks in the database.
 * It creates separate CSV files with appropriate names for each data type and is used to start the database structure.
 */
public class DBInitializer {

    // Names of the CSV files for different data types
    String[] DBNames = {"Projects", "Columns", "Tasks"};

    // CSVWriter instance
    CSVWriter writer;

    /**
     * Constructs an instance of the DBInitializer class. This constructor initializes the CSV files for storing data by
     * creating separate files for each data type with appropriate names.
     */
    public DBInitializer() {
        for (String s : DBNames) {
            try {
                writer = new CSVWriter(new FileWriter("./DatabaseFiles/" + s + "/" + s + ".csv"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The main method initializes the database structure by creating instances of various initializers for projects,
     * columns, tasks, and unique IDs.
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



