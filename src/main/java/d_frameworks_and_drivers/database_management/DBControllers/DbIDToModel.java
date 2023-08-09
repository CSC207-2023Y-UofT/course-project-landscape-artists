package d_frameworks_and_drivers.database_management.DBControllers;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDbIdToModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDbIdToModelList;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class DbIDToModel implements IDbIdToModel {
    DBManagerSearchController dbManagerSearchController = new DBManagerSearchController();
    IDbIdToModelList iDbIdToModelList = new IDListsToModelList();
    /**
     * @param Id
     * @return
     */
    public ColumnModel IdToColumnModel(String Id) {
        List<String> temp = dbManagerSearchController.DBColumnSearch(Id);
        ColumnModel columnModelTemp = new ColumnModel(
                temp.get(1),
                iDbIdToModelList.IdToTaskModelList(List.of(temp.get(3).split(","))),
                UUID.fromString(temp.get(0))
        );

        return columnModelTemp;
    }

    /**
     * @param Id
     * @return
     */
    public TaskModel IdToTaskModel(String Id) {
        List<String> temp = dbManagerSearchController.DBColumnSearch(Id);
        TaskModel TaskModelTemp = new TaskModel(
                temp.get(1),
                UUID.fromString(temp.get(0)),
                temp.get(2),
                !temp.get(3).isEmpty(),
                LocalDateTime.parse(temp.get(4)));
        return TaskModelTemp;
    }

    /**
     * @param Id
     * @return
     */
    public ProjectModel IdToProjectModel(String Id) {
            List<String> temp = dbManagerSearchController.DBProjectSearch(Id);
            System.out.println("IDTO PROJECT");
            System.out.println(List.of(temp.get(3)));
            ProjectModel ProjectModelTemp = new ProjectModel(
                    temp.get(1),
                    UUID.fromString(temp.get(0)),
                    temp.get(2),
                    iDbIdToModelList.IdToColumnModelList(List.of(temp.get(3))));
        return ProjectModelTemp;
    }
}
