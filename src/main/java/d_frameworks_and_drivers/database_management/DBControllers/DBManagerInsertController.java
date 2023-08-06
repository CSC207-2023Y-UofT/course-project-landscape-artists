package d_frameworks_and_drivers.database_management.DBControllers;

import b_application_business_rules.AdapterConvertEntity;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import d_frameworks_and_drivers.database_management.DBAdapters.CSVWriter;

import java.io.FileNotFoundException;
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
        // Try with resources: CSVWriter
        try (CSVWriter csvWriter = new CSVWriter("DatabaseFiles/Projects/Projects.csv")) {
            // Converts to string representation and inserts in csv db
            csvWriter.insert(AdapterConvertEntity.toStringList(projectModel));
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
        // Try with resources: CSVWriter
        try (CSVWriter csvWriter = new CSVWriter("DatabaseFiles/Columns/Columns.csv")) {
            // Converts to string representation and inserts in csv db
            csvWriter.insert(AdapterConvertEntity.toStringList(columnModel));
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
        // Try with resources: CSVWriter
        try (CSVWriter csvWriter = new CSVWriter("DatabaseFiles/Tasks/Tasks.csv")) {
            // Converts to string representation and inserts in csv db
            csvWriter.insert(AdapterConvertEntity.toStringList(taskModel));
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
        // Try with resources: CSVWriter
        try (CSVWriter csvWriter = new CSVWriter("DatabaseFiles/UniqueIDs/UniqueIDs.csv")) {
            // Converts to string representation and inserts in csv db
            csvWriter.insert(uuid.toString(), "true");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
