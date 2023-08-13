package d_frameworks_and_drivers.database_management.DBControllers;

import d_frameworks_and_drivers.database_management.DatabaseInitializer.ColumnDBInitializer;
import d_frameworks_and_drivers.database_management.DatabaseInitializer.ProjectDBInitializer;
import d_frameworks_and_drivers.database_management.DatabaseInitializer.TaskDBInitializer;

import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DBManagerRemoveController implements IDBRemove {
    /**
     *
     */
    public void DBRemoveProject(UUID uuid) {
        File tempFile = new File("src/main/java/d_frameworks_and_drivers/" +
                "database_management/DatabaseFiles/Projects/Projects.csv");
        tempFile.renameTo(new File("src/main/java/d_frameworks_and_drivers/" +
                "database_management/DatabaseFiles/Projects/ProjectsBin.csv"));
        ProjectDBInitializer projectDBInitializer = new ProjectDBInitializer();
        File newFile = new File("src/main/java/d_frameworks_and_drivers/" +
                "database_management/DatabaseFiles/Projects/Projects.csv");

        File binFile = new File("src/main/java/d_frameworks_and_drivers/" +
                "database_management/DatabaseFiles/Projects/ProjectsBin.csv");
        CsvRemovalUpdate(uuid, binFile, newFile);
        binFile.renameTo(newFile);
    }

    /**
     *
     */
    public void DBRemoveColumn(UUID uuid) {
        File tempFile = new File("src/main/java/d_frameworks_and_drivers/" +
                "database_management/DatabaseFiles/Columns/Columns.csv");
        tempFile.renameTo(new File("src/main/java/d_frameworks_and_drivers/" +
                "database_management/DatabaseFiles/Columns/ColumnsBin.csv"));
        ColumnDBInitializer columnDBInitializer = new ColumnDBInitializer();
        File newFile = new File("src/main/java/d_frameworks_and_drivers/" +
                "database_management/DatabaseFiles/Columns/Columns.csv");

        File binFile = new File("src/main/java/d_frameworks_and_drivers/" +
                "database_management/DatabaseFiles/Columns/ColumnsBin.csv");
        CsvRemovalUpdate(uuid, binFile, newFile);
        binFile.renameTo(newFile);
    }

    /**
     *
     */
    public void DBRemoveTask(UUID uuid) {
        System.out.println("INSIDE DB REMOVE Task");
        File tempFile = new File("src/main/java/d_frameworks_and_drivers/" +
                "database_management/DatabaseFiles/Tasks/Tasks.csv");
        tempFile.renameTo(new File("src/main/java/d_frameworks_and_drivers/" +
                "database_management/DatabaseFiles/Tasks/TasksBin.csv"));
        TaskDBInitializer taskDBInitializer = new TaskDBInitializer();
        File newFile = new File("src/main/java/d_frameworks_and_drivers/" +
                "database_management/DatabaseFiles/Tasks/Tasks.csv");

        File binFile = new File("src/main/java/d_frameworks_and_drivers/" +
                "database_management/DatabaseFiles/Tasks/TasksBin.csv");
        CsvRemovalUpdate(uuid, binFile, newFile);
        binFile.renameTo(newFile);
        System.out.println("FINISHED DB REMOVE Task");
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
            tempFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
