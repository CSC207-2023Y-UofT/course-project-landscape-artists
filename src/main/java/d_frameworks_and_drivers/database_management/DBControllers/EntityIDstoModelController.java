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
 * This class will be used by the presenter in order to use a list of ProjectModels given the existing
 * projects in Projects.csv
 */
public class EntityIDstoModelController implements DBAdapterInterface {
    DBManagerSearchController searchController = new DBManagerSearchController();
    IDListsToModelList idListsToModelList = new IDListsToModelList();
    /**
     * Converts all the projects in Projects.csv into ProjectModel instances and returns a
     * list of ProjectModels.
     *
     * @return A list of ProjectModels
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
//            System.out.println("TEMP COLUMN TEST");
//            System.out.println(Arrays.stream(tempColumnID).toList() == null);
//            System.out.println(Arrays.stream(tempColumnID).toList().get(0) == null || Arrays.stream(tempColumnID).toList().get(0).trim().isEmpty());
            if (!(Arrays.stream(tempColumnID).toList().get(0) == null || Arrays.stream(tempColumnID).toList().get(0).trim().isEmpty())) {
                for (String tempCol : tempColumnID) {
                    //Find the correct column given the string UUID in Column.csv
                    System.out.println("------------IDs To Project Models");
                    System.out.println(Arrays.stream(tempColumnID).toList());
                    System.out.println(tempCol);
                    ArrayList<String> columnInfo = searchController.DBColumnSearch(tempCol);

                    //Saving the column ID and name
                    System.out.println("------------ColumnInfo");
                    System.out.println(columnInfo);
                    System.out.println(columnInfo.get(0));
                    UUID columnID = UUID.fromString(columnInfo.get(0));
                    String columnName = columnInfo.get(1);

                    //Temporary Array of string to hold the task IDs
                    String[] tempTaskID = columnInfo.get(2).split(",");

                    System.out.println("Task ID ARRAY");
                    System.out.println(tempTaskID);

                    List<TaskModel> taskModelList = new ArrayList<>();
                    if (!(Arrays.stream(tempTaskID).toList().get(0) == null || Arrays.stream(tempTaskID).toList().get(0).trim().isEmpty())) {
                        for (String tempTask : tempTaskID) {
                            System.out.println("Task ID");
                            System.out.println(tempTask);
                            ArrayList<String> taskInfo = searchController.DBTaskSearch(tempTask);

                            System.out.println("TaskInfo");
                            System.out.println(taskInfo);
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
     *
     * @param projectUUID
     * @return
     */
    public ProjectModel IDsToProjectModel(UUID projectUUID) {
        ArrayList<String> DbEntry = searchController.DBProjectSearch(projectUUID.toString());
        String[] columnIDs = DbEntry.get(3).split(",");
        
        return new ProjectModel(DbEntry.get(1), projectUUID,  DbEntry.get(2), idListsToModelList.IdToColumnModelList(List.of(columnIDs)));

    }
}
