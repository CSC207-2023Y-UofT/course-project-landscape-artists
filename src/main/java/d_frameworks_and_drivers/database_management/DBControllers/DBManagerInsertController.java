package d_frameworks_and_drivers.database_management.DBControllers;

import b_application_business_rules.AdapterConvertEntity;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
        File file = new File("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Projects/Projects.csv");
        List<String[]> content = new ArrayList<>();

        // Read the existing content of the CSV file into memory
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            content.addAll(reader.readAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder columnTaskList = new StringBuilder();
        //convert taskModel list to list of string

        // Add data to the CSV
        List<String> data = new ArrayList<>();
        data.add(projectModel.getID().toString());
        data.add(projectModel.getName());
        data.add(projectModel.getDescription());
        data.add(entityIDsToListController.EntityIDsToList(projectModel));

        content.add(data.toArray(new String[0]));

        // Write the updated content back to the CSV file
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            writer.writeAll(content);
        } catch (Exception e) {

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
        EntityIDsToListController entityIDsToListController = new EntityIDsToListController();
        File file = new File("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Columns/Columns.csv");
        List<String[]> content = new ArrayList<>();

        // Read the existing content of the CSV file into memory
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            content.addAll(reader.readAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder columnTaskList = new StringBuilder();
        //convert taskModel list to list of string

        // Add data to the CSV
        List<String> data = new ArrayList<>();
        data.add(columnModel.getID().toString());
        data.add(columnModel.getName());
        data.add(entityIDsToListController.EntityIDsToList(columnModel));
//        data.add(CurrentProjectRepository.getCurrentprojectrepository().getCurrentProject().getID().toString());

        content.add(data.toArray(new String[0]));

        // Write the updated content back to the CSV file
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            writer.writeAll(content);
        } catch (Exception e) {

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
        File file = new File("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Tasks/Tasks.csv");
        List<String[]> content = new ArrayList<>();

        // Read the existing content of the CSV file into memory
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            content.addAll(reader.readAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add data to the CSV
        List<String> data = new ArrayList<>();
        data.add(taskModel.getID().toString());
        data.add(taskModel.getName());
        data.add(taskModel.getDescription());
        data.add(String.valueOf(taskModel.getCompletionStatus()));
        data.add(taskModel.getDueDateTime().toString());

        content.add(data.toArray(new String[0]));

        // Write the updated content back to the CSV file
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            writer.writeAll(content);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    /**
     * Adds a record of unique ID into the Database
     * @param uuid
     */
    public void DBInsert(UUID uuid) {
        // Try with resources: CSVWriter
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter("DatabaseFiles/UniqueIDs/UniqueIDs.csv"))) {
            // Converts to string representation and inserts in csv db
            csvWriter.writeNext(new String[]{uuid.toString(),"true"});
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
