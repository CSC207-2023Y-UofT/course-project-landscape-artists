package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerRemoveController;

/**
 * The EditColumnDetails class is responsible for editing a column in the
 * currently
 * opened project in the database and the project entity.
 */
public class EditColumnDetails {

    /**
     * The Column object representing the new column to be added.
     */
    private final ColumnModel columnModel;

    /**
     * Constructs an EditColumnDetails object with the specified column name and
     * column ID.
     *
     * @param columnModel
     */
    public EditColumnDetails(ColumnModel columnModel) {
        this.columnModel = columnModel;
    }

    /**
     * Edits the column to the database and the currently opened project entity.
     * This method performs necessary database access and updates the project entity
     * with the new column.
     */
    public void setColumnName(String newName) {
        // Create the column entity
        this.columnModel.setName(newName);

        IDBRemove dbRemoveManager = new DBManagerRemoveController();
        dbRemoveManager.DBRemove(this.columnModel, this.columnModel.getID());

        // Update database to add the column.
        IDBInsert dbInsertManager = new DBManagerInsertController();
        dbInsertManager.DBInsert(this.columnModel);
    }

}
