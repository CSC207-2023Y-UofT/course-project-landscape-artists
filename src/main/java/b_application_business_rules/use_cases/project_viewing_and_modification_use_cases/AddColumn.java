package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The AddColumn class is responsible for adding a new column to the currently opened project in the database and the project entity.
 */
public class AddColumn {

    /**
     * The Column object representing the new column to be added.
     */
    private Column column;

    /**
     * The current project being worked on. Received from Singleton data class.
     */
    private Project currentProject = CurrentProjectRepository.getInstance().getCurrentProject().getProjectEntity();

    /**
     * Constructs an AddColumn object with the specified column name and column ID.
     *
     * @param columnName The name of the new column.
     * @param idOfColumn The ID of the new column.
     */
    public AddColumn(String columnName, UUID idOfColumn) {
        this.column = new Column(columnName, new ArrayList<>(), idOfColumn);
    }

    /**
     * Adds the new column to the database and the currently opened project entity.
     * This method performs necessary database access and updates the project entity with the new column.
     */
    public void addColumn() {
        // Update database to add the column.
        IDBInsert dbInsertManager = new DBManagerInsertController();
        dbInsertManager.DBInsert(new ColumnModel(this.column));

        // Add the column to the currently opened Project entity.
        currentProject.addColumn(new ColumnModel(column));
    }
}
