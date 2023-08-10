package d_frameworks_and_drivers.database_management.DBControllers;

import d_frameworks_and_drivers.database_management.DatabaseInitializer.ColumnDBInitializer;
import d_frameworks_and_drivers.database_management.DatabaseInitializer.ProjectDBInitializer;
import d_frameworks_and_drivers.database_management.DatabaseInitializer.TaskDBInitializer;

import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.UUID;

public class DBManagerRemoveController implements IDBRemove {
    /**
     *
     * @param projectModel
     */
    public void DBRemove(ProjectModel projectModel, UUID uuid) {
        File tempFile = new File("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Projects/Projects.csv");
        tempFile.renameTo(new File("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/ProjectsBin.csv"));
        ProjectDBInitializer projectDBInitializer = new ProjectDBInitializer();
        File newFile = new File("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Projects/Projects.csv");

        CsvRemovalUpdate(uuid, tempFile, newFile);
    }

    /**
     * @param taskModel
     */
    public void DBRemove(TaskModel taskModel, UUID uuid) {
        File tempFile = new File("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Columns/Columns.csv");
        tempFile.renameTo(new File("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Columns/ColumnsBin.csv"));
        ColumnDBInitializer columnDBInitializer = new ColumnDBInitializer();
        File newFile = new File("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Columns/Columns.csv");

        CsvRemovalUpdate(uuid, tempFile, newFile);

    }

    /**
     * @param columnModel
     */
    public void DBRemove(ColumnModel columnModel, UUID uuid) {
        File tempFile = new File("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Tasks/Tasks.csv");
        tempFile.renameTo(new File("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Tasks/TasksBin.csv"));
        TaskDBInitializer taskDBInitializer = new TaskDBInitializer();
        File newFile = new File("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Tasks/Tasks.csv");

        CsvRemovalUpdate(uuid, tempFile, newFile);
    }

    /**
     * Updates the tempFile to newFile by removing one record with a given ID.
     *
     * @param uuid
     * @param tempFile
     * @param newFile
     */
    private void CsvRemovalUpdate(UUID uuid, File tempFile, File newFile) {
        try (FileReader fileReader = new FileReader(tempFile);
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader());
             FileWriter fileWriter = new FileWriter(newFile);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader())) {

            // Iterate through each row in the CSV file
            for (CSVRecord csvRecord : csvParser) {
                // Check if the current row's column value matches the value to delete
                String columnValue = csvRecord.get(0); // 0 instead of String since method used for various files
                if (!columnValue.equals(uuid.toString())) {
                    // If it does not match, write the row to the new CSV file
                    csvPrinter.printRecord(csvRecord);
                }
            }

            // Flush the CSVPrinter
            csvPrinter.flush();
            tempFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
