package d_frameworks_and_drivers.database_management.DBControllers;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDbIdToModelList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IDListsToModelList implements IDbIdToModelList {
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
        System.out.println(IDlist.get(0));
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
     * Returns a list of task entity models given a list of UUID IDs as strings for the tasks.
     * @param IDlist a list of UUID IDs as strings for the tasks
     * @return a list of task entity models
     */
    public List<TaskModel> IdToTaskModelList(List<String> IDlist) {
        IDlist = List.of(IDlist.get(0).split(","));
        List<TaskModel> resultTaskModels = new ArrayList<>();
        for (String task : IDlist) {
            List<String> temp = dbManagerSearchController.DBTaskSearch(task);
            TaskModel TaskModelTemp = new TaskModel(
                    temp.get(1),
                    UUID.fromString(temp.get(0)),
                    temp.get(2),
                    !temp.get(3).isEmpty(),
                    LocalDateTime.parse(temp.get(4), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            resultTaskModels.add(TaskModelTemp);
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
