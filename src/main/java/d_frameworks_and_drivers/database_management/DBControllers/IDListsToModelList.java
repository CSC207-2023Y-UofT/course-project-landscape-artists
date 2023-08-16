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

public class IDListsToModelList implements IDbIdToModelList {
    //DBManagerInsertController dbManagerInsertController = new DBManagerInsertController();
    DBManagerSearchController dbManagerSearchController = new DBManagerSearchController();
    /**
     * Returns a list of column entity models given a list of UUID IDs as strings for the columns.
     * @param IDlist a list of UUID IDs as strings for the columns
     * @return a list of column entity models
     */
    public List<ColumnModel> IdToColumnModelList(List<String> IDlist) {
        IDlist = List.of(IDlist.get(0).split(","));
        List<ColumnModel> resultColumnModels = new ArrayList<>();
        System.out.println("---------IDlist");
        System.out.println(IDlist);

        if(IDlist.get(0).equals("")){
            return getDefaultColumn(resultColumnModels);
        }

        for (String col : IDlist) {
            List<String> temp = dbManagerSearchController.DBColumnSearch(col);
            System.out.println("IDs Lists To Model List");
            System.out.println(col);
            System.out.println(List.of(temp.get(2).split(",")));
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
     * Creates and inserts a default column into the list of column models.
     *
     * This method generates a new default column with the name "Default Column" and an empty list of task models.
     * The generated default column is added to the provided list of column models. Additionally, it inserts the default
     * column into the database and updates the project's column list with the default column's ID. The method then
     * removes the existing project entry from the database and replaces it with an updated entry containing the new
     * default column ID and the provided list of column models.
     *
     * @param resultColumnModels The list of column models to which the default column will be added.
     * @return The updated list of column models with the added default column.
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
     * Returns a list of task entity models given a list of UUID IDs as strings for the tasks.
     * @param IDlist a list of UUID IDs as strings for the tasks
     * @return a list of task entity models
     */
    public List<TaskModel> IdToTaskModelList(List<String> IDlist) {
        //IDlist = List.of(IDlist.get(0).split(","));
        List<TaskModel> resultTaskModels = new ArrayList<>();

        //check if list is empty first
        if(IDlist.get(0)==null || IDlist.get(0).isEmpty() || IDlist.get(0).equals("")){
            return resultTaskModels;
        }
        System.out.println("ID List Printed Here:");
        System.out.println(IDlist);

        for (String task : IDlist) {
            for (String s : task.split(",")) {
                List<String> temp = dbManagerSearchController.DBTaskSearch(s);
                System.out.println("temptemptemptemptemp");
                System.out.println(temp);
                System.out.println(task);
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
     * Returns a list of project entity models given a list of UUID IDs as strings for the projects.
     * @param IDlist a list of UUID IDs as strings for the projects
     * @return a list of project entity models
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
