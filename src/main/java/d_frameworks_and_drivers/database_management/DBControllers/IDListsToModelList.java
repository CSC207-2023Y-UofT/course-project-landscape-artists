package d_frameworks_and_drivers.database_management.DBControllers;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDbIdToModelList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IDListsToModelList implements IDbIdToModelList {
    DBManagerSearchController dbManagerSearchController = new DBManagerSearchController();
    /**
     * @param IDlist
     * @return
     */
    public List<ColumnModel> IdToColumnModelList(List<String> IDlist) {
        List<ColumnModel> resultColumnModels = new ArrayList<>();
        for (String col : IDlist) {
            List<String> temp = dbManagerSearchController.DBColumnSearch(col);
            ColumnModel columnModelTemp = new ColumnModel(
                    temp.get(1),
                    IdToTaskModelList(List.of(temp.get(3).split(","))),
                    UUID.fromString(temp.get(0))
            );
            resultColumnModels.add(columnModelTemp);
        }
        return resultColumnModels;
    }

    /**
     * @param IDlist
     * @return
     */
    public List<TaskModel> IdToTaskModelList(List<String> IDlist) {
        List<TaskModel> resultTaskModels = new ArrayList<>();
        for (String task : IDlist) {
            List<String> temp = dbManagerSearchController.DBTaskSearch(task);
            TaskModel TaskModelTemp = new TaskModel(
                    temp.get(1),
                    UUID.fromString(temp.get(0)),
                    temp.get(2),
                    !temp.get(3).isEmpty(),
                    LocalDateTime.parse(temp.get(4)));
            resultTaskModels.add(TaskModelTemp);
        }
        return resultTaskModels;
    }

    /**
     * @param IDlist
     * @return
     */
    public List<ProjectModel> IdToProjectModelList(List<String> IDlist) {
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
