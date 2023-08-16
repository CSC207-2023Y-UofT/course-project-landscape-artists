package d_frameworks_and_drivers.database_management.DBControllers;

import d_frameworks_and_drivers.database_management.DatabaseInitializer.ColumnDBInitializer;
import d_frameworks_and_drivers.database_management.DatabaseInitializer.ProjectDBInitializer;
import d_frameworks_and_drivers.database_management.DatabaseInitializer.TaskDBInitializer;

import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The DBManagerRemoveController class implements the IDBRemove interface to provide methods for removing
 * database records corresponding to projects, columns, and tasks based on their UUIDs.
 * It handles the removal process by copying CSV files to bin files, updating them, and swapping out the old file
 * with the new one to maintain data integrity.
 */
public class DBManagerRemoveController implements IDBRemove {
    private static  String baseDirectory = "src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/";

    /**
     * Removes a project record from the database using its UUID.
     *
     * @param uuid The UUID of the project to be removed.
     */
    public void DBRemoveProject(UUID uuid) {
        try {
            String projectsDirectory = baseDirectory + "Projects/";

            File tempFile = new File(projectsDirectory + "Projects.csv");
            File binFile = new File(projectsDirectory + "ProjectsBin.csv");

            FileUtils.copyFile(tempFile, binFile);
            ProjectDBInitializer projectDBInitializer = new ProjectDBInitializer();
            File newFile = new File(projectsDirectory + "Projects.csv");
            CsvRemovalUpdate(uuid, binFile, newFile);
        } catch (IOException e) {
            System.err.println("ERROR IN REMOVING PROJECTS FROM DATABASE");
        }

    }


    /**
     * Removes a column record from the database using its UUID.
     *
     * @param uuid The UUID of the column to be removed.
     */
    public void DBRemoveColumn(UUID uuid) {
        try {
            String columnsDirectory = baseDirectory + "Columns/";

            File tempFile = new File(columnsDirectory + "Columns.csv");
            File binFile = new File(columnsDirectory + "ColumnsBin.csv");

            FileUtils.copyFile(tempFile, binFile);
            ColumnDBInitializer columnDBInitializer = new ColumnDBInitializer();
            File newFile = new File(columnsDirectory + "Columns.csv");
            CsvRemovalUpdate(uuid, binFile, newFile);
        } catch (IOException e) {
            System.err.println("ERROR IN REMOVING COLUMNS FROM DATABASE");
        }
    }

    /**
     * Removes a task record from the database using its UUID.
     *
     * @param uuid The UUID of the task to be removed.
     */
    public void DBRemoveTask(UUID uuid) {
        try {
            String taskDirectory = baseDirectory + "Tasks/";

            File tempFile = new File(taskDirectory + "Tasks.csv");
            File binFile = new File(taskDirectory + "TasksBin.csv");

            FileUtils.copyFile(tempFile, binFile);
            TaskDBInitializer taskDBInitializer = new TaskDBInitializer();
            File newFile = new File(taskDirectory + "Tasks.csv");



            CsvRemovalUpdate(uuid, binFile, newFile);
        } catch (IOException e) {
            System.err.println("ERROR IN DBREMOVETASK");
        }
    }


    /**
     * Updates the tempFile to newFile by removing one record with a given ID.
     *
     * @param uuid the uuid of the record we want to exclude
     * @param tempFile which in this case is the old csv file that needs to be swapped out
     * @param newFile the new file we want to write changes to
     */
    private void CsvRemovalUpdate(UUID uuid, File tempFile, File newFile) {

        try (FileReader fileReader = new FileReader(tempFile);
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
             FileWriter fileWriter = new FileWriter(newFile);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT)) {

            for (CSVRecord csvRecord : csvParser) {
                List<String> record = csvRecord.toList();
                if (!record.get(0).equals(uuid.toString())){
                    csvPrinter.printRecord(record);
                }
            }
            // Flush the CSVPrinter
            csvPrinter.flush();
            csvPrinter.close();
            fileReader.close();
            csvParser.close();
            boolean deleted = tempFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
