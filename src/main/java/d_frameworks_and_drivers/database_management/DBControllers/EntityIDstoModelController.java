package d_frameworks_and_drivers.database_management.DBControllers;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;

import b_application_business_rules.factories.ColumnModelFactory;
import b_application_business_rules.factories.ProjectModelFactory;
import b_application_business_rules.factories.TaskModelFactory;

import d_frameworks_and_drivers.database_management.ProjectUUIDArray;

import c_interface_adapters.DBAdapterInterface;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDateTime;

/**
 * The EntityIDstoModelController class implements the DBAdapterInterface interface and is responsible for converting
 * data from CSV files into model instances, such as ProjectModel, ColumnModel, and TaskModel. It is used by the
 * presenter to obtain lists of ProjectModels and retrieve ProjectModels based on their UUIDs.
 */

public class EntityIDstoModelController implements DBAdapterInterface {
    DBManagerSearchController searchController = new DBManagerSearchController();
    IDListsToModelList idListsToModelList = new IDListsToModelList();

    /**
     * Converts project data from "Projects.csv" into a list of ProjectModel instances. Iterates through the project
     * UUIDs, creates corresponding ProjectModel objects, and populates them with associated ColumnModels and TaskModels.
     *
     * @return A list of ProjectModels representing projects from the CSV data.
     */
    public List<ProjectModel> IDstoProjectModelList() {

        ArrayList<ArrayList<String>> projectListString = ProjectUUIDArray.convertCsvToArrayList();

        // Iterate through the list of project UUIDS to create a list of ProjectModels
        List<ProjectModel> projectModels = new ArrayList<>();
        for (ArrayList<String> projectList : projectListString) {

            //Saving the project ID, name and description
            UUID projectID = UUID.fromString(projectList.get(0));
            String projectName = projectList.get(1);
            String projectDescription = projectList.get(2);

            //Temporary Array of string to hold the column IDs
            String[] tempColumnID = projectList.get(3).split(",");

            // Iterate through the list of column UUIDS to create a list of ColumnModels
            //IF AND ONLY IF there is a valid list of column UUIDs
            List<ColumnModel> columnModelList = new ArrayList<>();
            if (!(Arrays.stream(tempColumnID).toList().get(0) == null || Arrays.stream(tempColumnID).toList().get(0).trim().isEmpty())) {
                for (String tempCol : tempColumnID) {
                    //Find the correct column given the string UUID in Column.csv
                    ArrayList<String> columnInfo = searchController.DBColumnSearch(tempCol);

                    //Saving the column ID and name
                    UUID columnID = UUID.fromString(columnInfo.get(0));
                    String columnName = columnInfo.get(1);

                    //Temporary Array of string to hold the task IDs
                    String[] tempTaskID = columnInfo.get(2).split(",");

                    List<TaskModel> taskModelList = new ArrayList<>();
                    if (!(Arrays.stream(tempTaskID).toList().get(0) == null || Arrays.stream(tempTaskID).toList().get(0).trim().isEmpty())) {
                        for (String tempTask : tempTaskID) {
                            ArrayList<String> taskInfo = searchController.DBTaskSearch(tempTask);

                            UUID taskID = UUID.fromString(taskInfo.get(0));
                            String taskName = taskInfo.get(1);
                            String taskDescription = taskInfo.get(2);
                            boolean isCompleted = Boolean.parseBoolean(taskInfo.get(3));
                            LocalDateTime dueDate = LocalDateTime.parse(taskInfo.get(4), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

                            //Creating a TaskModel object
                            TaskModel newTModel = TaskModelFactory.create(taskName, taskID, taskDescription,
                                    isCompleted, dueDate);
                            //Appending it to list of TaskModels
                            taskModelList.add(newTModel);

                        }
                    }
                    //Creating a ColumnModel object
                    ColumnModel newCModel = ColumnModelFactory.create(columnName, taskModelList, columnID);
                    //Appending to the list of ColumnModels
                    columnModelList.add(newCModel);

                }
            }

            //Creating a ProjectModel object
            ProjectModel newPModel = ProjectModelFactory.create(projectName, projectID,
                    projectDescription, columnModelList);
            //Appending to the list of ProjectModels
            projectModels.add(newPModel);
        }
        return projectModels;
    }


    /**
     * Retrieves a specific ProjectModel based on its UUID. Searches the "Projects.csv" file for the entry with the
     * provided projectUUID, and constructs a ProjectModel instance with associated ColumnModels based on the found data.
     *
     * @param projectUUID The UUID of the ProjectModel to retrieve.
     * @return The ProjectModel instance corresponding to the provided UUID.
     */
    public ProjectModel IDsToProjectModel(UUID projectUUID) {
        ArrayList<String> DbEntry = searchController.DBProjectSearch(projectUUID.toString());
        String[] columnIDs = DbEntry.get(3).split(",");
        
        return new ProjectModel(DbEntry.get(1), projectUUID,  DbEntry.get(2), idListsToModelList.IdToColumnModelList(List.of(columnIDs)));

    }
}
