package d_frameworks_and_drivers.database_management.DBControllers;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.*;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.factories.ColumnFactory;
import b_application_business_rules.factories.ProjectFactory;
import b_application_business_rules.factories.TaskFactory;
import b_application_business_rules.use_cases.project_selection_gateways.IEntityIDsToList;
import d_frameworks_and_drivers.database_management.ProjectUUIDArray;

import c_interface_adapters.DBAdapterInterface;

import java.util.*;
import java.time.LocalDateTime;

/**
 * This class will be used by the presenter in order to use a list of ProjectModels given the existing
 * projects in Projects.csv
 */
public class EntityIDstoModelController implements DBAdapterInterface {
    DBManagerSearchController searchController = new DBManagerSearchController();

    /**
     * Converts all the projects in Projects.csv into ProjectModel instances and returns a
     * list of ProjectModels.
     * @return A list of ProjectModels
     */
    public List<ProjectModel> IDstoProjectModelList() {

        ArrayList<ArrayList<String>> projectListString = ProjectUUIDArray.convertCsvToArrayList();

        // Iterate through the list of project UUIDS to create a list of ProjectModels
        List<ProjectModel> projectModels = new ArrayList<>();
        for (ArrayList<String> projectList: projectListString) {

            //Saving the project ID, name and description
            UUID projectID = UUID.fromString(projectList.get(0));
            String projectName = projectList.get(1);
            String projectDescription = projectList.get(2);

            //Temporary Array of string to hold the column IDs
            String[] tempColumnID = projectList.get(3).split(",");

            // Iterate through the list of column UUIDS to create a list of ColumnModels
            List<ColumnModel> columnModelList = new ArrayList<>();
            for (String tempCol : tempColumnID) {
                //Find the correct column given the string UUID in Column.csv
                ArrayList<String> columnInfo = searchController.DBColumnSearch(tempCol);

                //Saving the column ID and name
                UUID columnID = UUID.fromString(columnInfo.get(0));
                String columnName = columnInfo.get(1);

                //Temporary Array of string to hold the task IDs
                String[] tempTaskID = projectList.get(3).split(",");

                List<TaskModel> taskModelList = new ArrayList<>();
                for (String tempTask: tempTaskID) {
                    ArrayList<String> taskInfo = searchController.DBTaskSearch(tempTask);

                    UUID taskID = UUID.fromString(taskInfo.get(0));
                    String taskName = taskInfo.get(1);
                    String taskDescription = taskInfo.get(2);
                    boolean isCompleted = Boolean.parseBoolean(taskInfo.get(3));
                    LocalDateTime dueDate = LocalDateTime.parse(taskInfo.get(4));

                    //Creating a TaskModel object
                    TaskModel newTModel = TaskFactory.create(taskName, taskID, taskDescription,
                            isCompleted, dueDate);
                    //Appending it to list of TaskModels
                    taskModelList.add(newTModel);

                }
                //Creating a ColumnModel object
                ColumnModel newCModel = ColumnFactory.create(columnName, taskModelList, columnID);
                //Appending to the list of ColumnModels
                columnModelList.add(newCModel);

            }
            //Creating a ProjectModel object
            ProjectModel newPModel = ProjectFactory.create(projectName, projectID,
                    projectDescription, columnModelList);
            //Appending to the list of ProjectModels
            projectModels.add(newPModel);
        }
        return projectModels;
    }
}
