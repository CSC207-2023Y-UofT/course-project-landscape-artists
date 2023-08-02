package d_frameworks_and_drivers.database_management.DatabaseInitializer;
/*
READ BEFORE CALLING METHODS
This file is intended to initialize the Database CSV files and the main method should only be called/run ONCE
 */

import java.io.FileWriter;

import com.opencsv.CSVWriter;

public class DBInitializer {
    String [] DBNames = {"Projects", "Columns", "Tasks"};
    CSVWriter writer;
    public DBInitializer(){
        {
            for (String s : DBNames) {
                try {
                    writer = new CSVWriter(new FileWriter("./DatabaseFiles/" + s + "/" + s + ".csv"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        DBInitializer dbInitializer = new DBInitializer();
        ProjectDBInitializer projectDBInitializer = new ProjectDBInitializer();
        ColumnDBInitializer columnDBInitializer = new ColumnDBInitializer();
        TaskDBInitializer taskDBInitializer = new TaskDBInitializer();
        UniqueIDsInitializer uniqueIDsInitializer = new UniqueIDsInitializer();
    }
}



