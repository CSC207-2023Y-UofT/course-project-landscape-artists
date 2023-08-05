package d_frameworks_and_drivers.database_management.DBControllers;
// Implement models instead of entities


import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

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
     * @param projectModel
     * @return
     */
    public void DBInsert(ProjectModel projectModel) {
        EntityIDsToListController entityIDsToListController = new EntityIDsToListController();
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter("DatabaseFiles/Projects/Projects.csv");

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // add data to csv
            String[] data = {
                    projectModel.getID().toString(),
                    projectModel.getName(),
                    projectModel.getDescription(),
                    entityIDsToListController.EntityIDsToList(projectModel)
            };
            writer.writeNext(data);

            // closing writer connection
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method is intended to append the "ColumnID","Name",
     * "Description" and "Task ID's" that belong to a Column
     * into the Database
     * check src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Columns/Columns.csv for reference
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

        // Add data to the CSV
        List<String> data = new ArrayList<>();
        data.add(columnModel.getID().toString());
        data.add(columnModel.getName());
        data.add(entityIDsToListController.EntityIDsToList(columnModel));
        data.add(null);
        data.add(CurrentProjectRepository.getInstance().getCurrentProject().getID().toString());

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
    public void DBInsert(TaskModel taskModel) {
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter("DatabaseFiles/Tasks/Tasks.csv");

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // add data to csv
            String[] data1 = {
                    taskModel.getID().toString(),
                    taskModel.getName(),
                    taskModel.getDescription(),
                    String.valueOf(taskModel.getCompletionStatus()),
                    taskModel.getDueDateTime().toString()
            };
            writer.writeNext(data1);

            // closing writer connection
            writer.close();
        }
        catch (Exception e) {
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
