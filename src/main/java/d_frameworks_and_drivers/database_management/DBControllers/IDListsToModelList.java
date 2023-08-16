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
 * The IDListsToModelList class provides methods to convert lists of IDs to corresponding model lists from the database.
 */
public class IDListsToModelList implements IDbIdToModelList {
    DBManagerSearchController dbManagerSearchController = new DBManagerSearchController();

    /**
     * Converts a list of column IDs to a list of corresponding ColumnModel objects from the database.
     *
     * @param IDlist The list of column IDs.
     * @return The list of corresponding ColumnModel objects.
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
     * Adds a default column to the provided list of column models, inserts it into the database,
     * updates the project with the new column, and returns the updated list of column models.
     *
     * @param resultColumnModels The list of column models to which the default column should be added.
     * @return The updated list of column models.
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
     * Converts a list of IDs into a list of corresponding TaskModels by querying the database.
     *
     * @param IDlist The list of IDs to be converted into TaskModels.
     * @return A list of TaskModels corresponding to the provided IDs.
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
     * Converts a list of IDs into a list of corresponding ProjectModels by querying the database.
     *
     * @param IDlist The list of IDs to be converted into ProjectModels.
     * @return A list of ProjectModels corresponding to the provided IDs.
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
