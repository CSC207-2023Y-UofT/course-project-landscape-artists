package d_frameworks_and_drivers.database_management.DBControllers;

// Implement entity models instead of entities
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;

import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;

import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVFormat;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class DBManagerInsertController implements IDBInsert {

    /**
     * Adds a project record with ProjectID, Name, Description and list of column IDs
     * into the Database
     *
     * @param projectModel
     */
    public void DBInsert(ProjectModel projectModel) {
        EntityIDsToListController entityIDsToListController = new EntityIDsToListController();
        // Try with resource: create FileWriter and  csvPrinter object as resources - closes automatically
        try (FileWriter outputfile = new FileWriter("DatabaseFiles/Projects/Projects.csv");
            CSVPrinter csvPrinter = new CSVPrinter(outputfile, CSVFormat.DEFAULT.withHeader())) {

            // writes a new row/record to the CSV file
            csvPrinter.printRecord(
                projectModel.getID().toString(),
                projectModel.getName(),
                projectModel.getDescription(),
                entityIDsToListController.EntityIDsToList(projectModel));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a column record with fields "ColumnID","Name",
     * and "Task ID's" into the Database
     *
     * @param columnModel
     */
    public void DBInsert(ColumnModel columnModel) {
        EntityIDsToListController entityIDsToListController = new EntityIDsToListController();
        // Try with resource: create FileWriter and  csvPrinter object as resources - closes automatically
        try (FileWriter outputfile = new FileWriter("DatabaseFiles/Columns/Columns.csv");
             CSVPrinter csvPrinter = new CSVPrinter(outputfile, CSVFormat.DEFAULT.withHeader())) {

            // writes a new row/record to the CSV file
            csvPrinter.printRecord(
                columnModel.getID().toString(),
                columnModel.getName(),
                entityIDsToListController.EntityIDsToList(columnModel));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a task record with fields "TaskID","Name","Description","Completion Status","Due Date"
     * into the Database
     *
     * @param taskModel Task model to be inserted in the database.
     */
    public void DBInsert(TaskModel taskModel) {
        // Try with resource: create FileWriter and  csvPrinter object as resources - closes automatically
        try (FileWriter outputfile = new FileWriter("DatabaseFiles/Tasks/Tasks.csv");
             CSVPrinter csvPrinter = new CSVPrinter(outputfile, CSVFormat.DEFAULT.withHeader())) {

            // writes a new row/record to the CSV file
            csvPrinter.printRecord(
                    taskModel.getID().toString(),
                    taskModel.getName(),
                    taskModel.getDescription(),
                    String.valueOf(taskModel.getCompletionStatus()),
                    taskModel.getDueDateTime().toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a record of unique ID into the Database
     * @param uuid
     */
    public void DBInsert(UUID uuid) {
        // Try with resource: create FileWriter and  csvPrinter object as resources - closes automatically
        try (FileWriter outputfile = new FileWriter("DatabaseFiles/UniqueIDs/UniqueIDs.csv");
             CSVPrinter csvPrinter = new CSVPrinter(outputfile, CSVFormat.DEFAULT.withHeader())) {

            // writes a new row/record to the CSV file
            csvPrinter.printRecord(uuid.toString(), "true");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
