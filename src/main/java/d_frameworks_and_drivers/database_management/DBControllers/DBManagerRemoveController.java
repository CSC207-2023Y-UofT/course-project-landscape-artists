package DBControllers;

import java.io.File;

import DatabaseInitializer.ColumnDBInitializer;
import DatabaseInitializer.ProjectDBInitializer;
import DatabaseInitializer.TaskDBInitializer;
import Entities.Column;
import Entities.Project;
import Entities.Task;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.UUID;

public class DBManagerRemoveController implements IDBRemove{
    /**
     * @param project
     */
    public void DBRemove(Project project, UUID uuid) {
        File tempFile = new File("DatabaseFiles/Projects/Projects.csv");
        tempFile.renameTo(new File("DatabaseFiles/Projects/ProjectsTemp.csv"));
        ProjectDBInitializer projectDBInitializer = new ProjectDBInitializer();
        File newFile = new File("DatabaseFiles/Projects/Projects.csv");

        CsvRemovalUpdate(uuid, tempFile, newFile);
    }

    /**
     * @param task
     */
    public void DBRemove(Task task, UUID uuid) {
        File tempFile = new File("DatabaseFiles/Columns/Columns.csv");
        tempFile.renameTo(new File("DatabaseFiles/Columns/ColumnsTemp.csv"));
        ColumnDBInitializer columnDBInitializer = new ColumnDBInitializer();
        File newFile = new File("DatabaseFiles/Columns/Columns.csv");

        CsvRemovalUpdate(uuid, tempFile, newFile);

    }

    /**
     * @param column
     */
    public void DBRemove(Column column, UUID uuid) {
        File tempFile = new File("DatabaseFiles/Tasks/Tasks.csv");
        tempFile.renameTo(new File("DatabaseFiles/Tasks/TasksTemp.csv"));
        TaskDBInitializer taskDBInitializer = new TaskDBInitializer();
        File newFile = new File("DatabaseFiles/Tasks/Tasks.csv");

        CsvRemovalUpdate(uuid, tempFile, newFile);
    }

    private void CsvRemovalUpdate(UUID uuid, File tempFile, File newFile) {
        try (FileReader fileReader = new FileReader(tempFile);
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
             FileWriter fileWriter = new FileWriter(newFile);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT)) {

            // Iterate through each row in the CSV file
            for (CSVRecord csvRecord : csvParser) {
                // Check if the current row's column value matches the value to delete
                String columnValue = csvRecord.get("ProjectID");
                if (!columnValue.equals(uuid.toString())) {
                    // If it does not match, write the row to the new CSV file
                    csvPrinter.printRecord(csvRecord);
                }
            }

            // Flush and close the CSVPrinter
            csvPrinter.flush();
            tempFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
