package d_frameworks_and_drivers.database_management.DBControllers;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDbIdToModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDbIdToModelList;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * The DbIDToModel class implements the IDbIdToModel interface to provide methods for retrieving entity models
 * from the database based on their IDs. It interacts with the database search controller and utilizes
 * ID lists to model list conversion for constructing entity models from database data.
 */
public class DbIDToModel implements IDbIdToModel {
    DBManagerSearchController dbManagerSearchController = new DBManagerSearchController();
    IDbIdToModelList iDbIdToModelList = new IDListsToModelList();
    /**
     * Returns column model given its ID from database
     * @param Id id of column model
     * @return column model object
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
     * Returns task model given its ID from database
     * @param Id id of task model
     * @return task model object
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
     * Returns project model given its ID from database
     * @param Id id of project model
     * @return project model object
     */
    public ProjectModel IdToProjectModel(String Id) {
            List<String> temp = dbManagerSearchController.DBProjectSearch(Id);
            ProjectModel ProjectModelTemp = new ProjectModel(
                    temp.get(1),
                    UUID.fromString(temp.get(0)),
                    temp.get(2),
                    iDbIdToModelList.IdToColumnModelList(List.of(temp.get(3))));
        return ProjectModelTemp;
    }
}
