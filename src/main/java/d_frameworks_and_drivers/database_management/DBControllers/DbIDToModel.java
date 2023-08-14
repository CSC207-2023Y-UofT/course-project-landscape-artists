package d_frameworks_and_drivers.database_management.DBControllers;

import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDbIdToModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDbIdToModelList;

import java.util.List;
import java.util.UUID;

public class DbIDToModel implements IDbIdToModel {
    DBManagerSearchController dbManagerSearchController = new DBManagerSearchController();
    IDbIdToModelList iDbIdToModelList = new IDListsToModelList();

    /**
     * Returns project model given its ID from database
     * @param Id id of project model
     * @return project model object
     */
    public ProjectModel IdToProjectModel(String Id) {
            List<String> temp = dbManagerSearchController.DBProjectSearch(Id);
            System.out.println("ID TO PROJECT");
            System.out.println(List.of(temp.get(3)));
        return new ProjectModel(
                temp.get(1),
                UUID.fromString(temp.get(0)),
                temp.get(2),
                iDbIdToModelList.IdToColumnModelList(List.of(temp.get(3))));
    }
}
