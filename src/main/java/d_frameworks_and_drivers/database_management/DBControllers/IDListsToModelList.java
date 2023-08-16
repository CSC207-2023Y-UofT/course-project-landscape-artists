package d_frameworks_and_drivers.database_management.DBControllers;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.CurrentProjectID;
import b_application_business_rules.use_cases.project_selection_gateways.IDbIdToModelList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The IDListsToModelList class provides methods for converting lists of entity IDs into lists of corresponding model
 * instances, including ColumnModel, TaskModel, and ProjectModel. It is used to construct model lists based on entity
 * IDs retrieved from the database.
 */
public class IDListsToModelList implements IDbIdToModelList {
    DBManagerSearchController dbManagerSearchController = new DBManagerSearchController();

    /**
     * Converts a list of column IDs into a list of ColumnModel instances. Retrieves corresponding column data from the
     * database, constructs ColumnModel objects, and populates them with associated TaskModels.
     *
     * @param IDlist The list of column IDs.
     * @return A list of ColumnModel instances corresponding to the provided column IDs.
     */
    public List<ColumnModel> IdToColumnModelList(List<String> IDlist) {
        IDlist = List.of(IDlist.get(0).split(","));
        List<ColumnModel> resultColumnModels = new ArrayList<>();

        if(IDlist.get(0).equals("")){
            return getDefaultColumn(resultColumnModels);
        }

        for (String col : IDlist) {
            List<String> temp = dbManagerSearchController.DBColumnSearch(col);
            ColumnModel columnModelTemp = new ColumnModel(
                    temp.get(1),
                    IdToTaskModelList(List.of(temp.get(2).split(","))),
                    UUID.fromString(temp.get(0))
            );
            resultColumnModels.add(columnModelTemp);
        }
        return resultColumnModels;
    }

    /**
     * Creates and inserts a default column into the list of ColumnModel instances. This method is called when there are
     * no existing columns associated with a project. It constructs a new default ColumnModel with empty tasks, generates a
     * UUID for the default column, inserts it into the database, and updates the project's list of column IDs.
     *
     * @param resultColumnModels The list of ColumnModel instances to which the default column will be added.
     * @return The updated list of ColumnModel instances with the added default column.
     */
    private List<ColumnModel> getDefaultColumn(List<ColumnModel> resultColumnModels) {
        ColumnModel defaultColumn = new  ColumnModel(
            "Default Column",
            new ArrayList<>(),
            UUID.randomUUID()
        );
        resultColumnModels.add(defaultColumn);
        DBManagerInsertController dbManagerInsertController = new DBManagerInsertController();
        dbManagerInsertController.DBInsert(defaultColumn);

        ArrayList<String> oldProject = dbManagerSearchController.DBProjectSearch(CurrentProjectID
                .getCurrentProjectID().getSelectedProjectID().toString());
        oldProject.set(3, defaultColumn.getID().toString());

        DBManagerRemoveController dbManagerRemoveController = new DBManagerRemoveController();
        dbManagerRemoveController.DBRemoveProject(CurrentProjectID
                .getCurrentProjectID().getSelectedProjectID());
        dbManagerInsertController.DBInsert(new ProjectModel(
                oldProject.get(1),
                UUID.fromString(oldProject.get(0)),
                oldProject.get(2),
                resultColumnModels
        ));
        return resultColumnModels;
    }

    /**
     * Converts a list of task IDs into a list of TaskModel instances. Retrieves corresponding task data from the
     * database, constructs TaskModel objects, and populates them with relevant information.
     *
     * @param IDlist The list of task IDs.
     * @return A list of TaskModel instances corresponding to the provided task IDs.
     */
    public List<TaskModel> IdToTaskModelList(List<String> IDlist) {
        //IDlist = List.of(IDlist.get(0).split(","));
        List<TaskModel> resultTaskModels = new ArrayList<>();

        //check if list is empty first
        if(IDlist.get(0)==null || IDlist.get(0).isEmpty() || IDlist.get(0).equals("")){
            return resultTaskModels;
        }

        for (String task : IDlist) {
            for (String s : task.split(",")) {
                List<String> temp = dbManagerSearchController.DBTaskSearch(s);
                if(temp.size()>1){
                    TaskModel TaskModelTemp = new TaskModel(
                            temp.get(1),
                            UUID.fromString(temp.get(0)),
                            temp.get(2),
                            !temp.get(3).isEmpty(),
                            LocalDateTime.parse(temp.get(4), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                    resultTaskModels.add(TaskModelTemp);
                }
            }


        }
        return resultTaskModels;
    }

    /**
     * Converts a list of project IDs into a list of ProjectModel instances. Retrieves corresponding project data from
     * the database, constructs ProjectModel objects, and populates them with associated ColumnModels.
     *
     * @param IDlist The list of project IDs.
     * @return A list of ProjectModel instances corresponding to the provided project IDs.
     */
    public List<ProjectModel> IdToProjectModelList(List<String> IDlist) {
        IDlist = List.of(IDlist.get(0).split(","));
        List<ProjectModel> resultProjectModels = new ArrayList<>();
        for (String project : IDlist) {
            List<String> temp = dbManagerSearchController.DBProjectSearch(project);
            ProjectModel ProjectModelTemp = new ProjectModel(
                    temp.get(1),
                    UUID.fromString(temp.get(0)),
                    temp.get(2),
                    IdToColumnModelList(List.of(temp.get(3).split(","))));
            resultProjectModels.add(ProjectModelTemp);
        }
        return resultProjectModels;    }
}
