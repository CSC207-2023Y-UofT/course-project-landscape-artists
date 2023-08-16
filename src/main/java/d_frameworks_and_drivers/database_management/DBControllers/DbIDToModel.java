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
 * The DbIDToModel class implements the IDbIdToModel interface and provides methods to retrieve various models
 * (ColumnModel, TaskModel, and ProjectModel) from the database based on their IDs.
 */
public class DbIDToModel implements IDbIdToModel {

    private final DBManagerSearchController dbManagerSearchController = new DBManagerSearchController();
    private final IDbIdToModelList iDbIdToModelList = new IDListsToModelList();

    /**
     * Retrieves a ColumnModel from the database based on its ID.
     *
     * @param Id The ID of the desired ColumnModel.
     * @return The retrieved ColumnModel object.
     */
    @Override
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
     * Retrieves a TaskModel from the database based on its ID.
     *
     * @param Id The ID of the desired TaskModel.
     * @return The retrieved TaskModel object.
     */
    @Override
    public TaskModel IdToTaskModel(String Id) {
        List<String> temp = dbManagerSearchController.DBTaskSearch(Id);
        TaskModel TaskModelTemp = new TaskModel(
                temp.get(1),
                UUID.fromString(temp.get(0)),
                temp.get(2),
                !temp.get(3).isEmpty(),
                LocalDateTime.parse(temp.get(4))
        );
        return TaskModelTemp;
    }

    /**
     * Retrieves a ProjectModel from the database based on its ID.
     *
     * @param Id The ID of the desired ProjectModel.
     * @return The retrieved ProjectModel object.
     */
    @Override
    public ProjectModel IdToProjectModel(String Id) {
        List<String> temp = dbManagerSearchController.DBProjectSearch(Id);
        List<String> columnIds = List.of(temp.get(3).split(","));
        ProjectModel ProjectModelTemp = new ProjectModel(
                temp.get(1),
                UUID.fromString(temp.get(0)),
                temp.get(2),
                iDbIdToModelList.IdToColumnModelList(columnIds)
        );
        return ProjectModelTemp;
    }
}
