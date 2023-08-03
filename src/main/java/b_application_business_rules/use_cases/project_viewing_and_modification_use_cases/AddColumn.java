package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;

import java.util.ArrayList;
import java.util.UUID;

public class AddColumn {
    private Column column;
    Project currentProject = CurrentProjectRepository.getInstance().getCurrentProject().getProjectEntity();


    public AddColumn(String columnName, UUID idOfColumn) {
        this.column = new Column(columnName, new ArrayList<>(), idOfColumn);

    }
    public void addColumn() {
        // Necessary database access
        IDBInsert dbInsertManager = new DBManagerInsertController();
        dbInsertManager.DBInsert(column);

        // Add the column to the currently opened Project entity.
        currentProject.addColumn(column);
    }
}