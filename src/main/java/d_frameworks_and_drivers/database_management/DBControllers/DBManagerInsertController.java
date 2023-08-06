package d_frameworks_and_drivers.database_management.DBControllers;
// Implement models instead of entities


import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.sun.glass.ui.Clipboard;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DBManagerInsertController implements IDBInsert {
    /**
     * The method is intended to append the ProjectID, Name,
     * Description and list of column IDs that belong to a project
     * into the Database
     * check DatabaseFiles/Projects/Projects.csv for reference
     *
     * @param projectModel
     * @return
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
     * The method is intended to append the "ColumnID","Name",
     * "Description" and "Task ID's" that belong to a Column
     * into the Database
     * check src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Columns/Columns.csv for reference
     *
     * @param columnModel
     * @return
     */
    public void DBInsert(ColumnModel columnModel) {
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

        // Add data to the CSV
        List<String> data = new ArrayList<>();
        data.add(columnModel.getID().toString());
        data.add(columnModel.getName());
        data.add(entityIDsToListController.EntityIDsToList(columnModel));
        data.add(CurrentProjectRepository.getCurrentprojectrepository().getCurrentProject().getID().toString());

        content.add(data.toArray(new String[0]));

        // Write the updated content back to the CSV file
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            writer.writeAll(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param taskModel
     */
    public void DBInsert(TaskModel taskModel, UUID parentColumn) {
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
        data.add(parentColumn.toString());

        content.add(data.toArray(new String[0]));

        // Write the updated content back to the CSV file
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            writer.writeAll(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param uuid
     */
    public void DBInsert(UUID uuid) {
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter("DatabaseFiles/UniqueIDs/UniqueIDs.csv");

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // add data to csv
            String[] data1 = { uuid.toString(), "true" };
            writer.writeNext(data1);

            // closing writer connection
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
