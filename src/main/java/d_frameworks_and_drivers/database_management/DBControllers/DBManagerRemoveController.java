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

public class DBManagerRemoveController implements IDBRemove {
    private static  String baseDirectory = "src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/";
    /**
     *
     */
    public void DBRemoveProject(UUID uuid) {
//        File tempFile = new File("src/main/java/d_frameworks_and_drivers/" +
//                "database_management/DatabaseFiles/Projects/Projects.csv");
//        tempFile.renameTo(new File("src/main/java/d_frameworks_and_drivers/" +
//                "database_management/DatabaseFiles/Projects/ProjectsBin.csv"));
//        ProjectDBInitializer projectDBInitializer = new ProjectDBInitializer();
//        File newFile = new File("src/main/java/d_frameworks_and_drivers/" +
//                "database_management/DatabaseFiles/Projects/Projects.csv");
//
//        File binFile = new File("src/main/java/d_frameworks_and_drivers/" +
//                "database_management/DatabaseFiles/Projects/ProjectsBin.csv");
//        CsvRemovalUpdate(uuid, binFile, newFile);
//        binFile.renameTo(newFile);
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
     *
     */
    public void DBRemoveColumn(UUID uuid) {
//        File tempFile = new File("src/main/java/d_frameworks_and_drivers/" +
//                "database_management/DatabaseFiles/Columns/Columns.csv");
//        tempFile.renameTo(new File("src/main/java/d_frameworks_and_drivers/" +
//                "database_management/DatabaseFiles/Columns/ColumnsBin.csv"));
//        ColumnDBInitializer columnDBInitializer = new ColumnDBInitializer();
//        File newFile = new File("src/main/java/d_frameworks_and_drivers/" +
//                "database_management/DatabaseFiles/Columns/Columns.csv");
//
//        File binFile = new File("src/main/java/d_frameworks_and_drivers/" +
//                "database_management/DatabaseFiles/Columns/ColumnsBin.csv");
//        CsvRemovalUpdate(uuid, binFile, newFile);
//        binFile.renameTo(newFile);
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
     *
     */
    public void DBRemoveTask(UUID uuid) {
//        System.out.println("INSIDE DB REMOVE Task");
//        File tempFile = new File("src/main/java/d_frameworks_and_drivers/" +
//                "database_management/DatabaseFiles/Tasks/Tasks.csv");
//        printFileContent(tempFile, "TEMP FILE BEFORE RENAME");
//        tempFile.renameTo(new File("src/main/java/d_frameworks_and_drivers/" +
//                "database_management/DatabaseFiles/Tasks/TasksBin.csv"));
//        printFileContent(tempFile, "TEMP FILE AFTER RENAME");
//        TaskDBInitializer taskDBInitializer = new TaskDBInitializer();
//        File newFile = new File("src/main/java/d_frameworks_and_drivers/" +
//                "database_management/DatabaseFiles/Tasks/Tasks.csv");
//
//        printFileContent(newFile, "NEW FILE");
//
//        File binFile = new File("src/main/java/d_frameworks_and_drivers/" +
//                "database_management/DatabaseFiles/Tasks/TasksBin.csv");
//
//        printFileContent(binFile, "BIN FILE");
//        CsvRemovalUpdate(uuid, binFile, newFile);
//        binFile.renameTo(newFile);
//        System.out.println("FINISHED DB REMOVE Task");
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

    public static void printFileContent(File file, String state) {
        System.out.println("THE FILE NAME IS " + state);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
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
            System.out.println("DELETED??? " + deleted + tempFile.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
