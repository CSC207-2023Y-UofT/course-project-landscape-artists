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
     * @param IDlist List of IDs
     * @return List of ColumnModels
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
     * @param IDlist list of ID's
     * @return list of TaskModels
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
                System.out.println("temp temp temp temp temp");
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

}
