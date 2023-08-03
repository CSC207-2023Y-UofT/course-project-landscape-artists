package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerRemoveController;

import java.util.UUID;

/**
 * The DeleteColumn class is responsible for deleting a column from the currently opened project in both the project entity and the database.
 * It allows removing a column with the specified ID from the project and updates the database accordingly.
 */
public class DeleteColumn {

    /**
     * The Column object representing the column to be deleted.
     */
    private Column column;

    /**
     * The ID of the column to be deleted.
     */
    private UUID idOfColumn;

    /**
     * The current project being worked on. Received from Singleton data class.
     */
    private Project currentProject = CurrentProjectRepository.getInstance().getCurrentProject().getProjectEntity();

    /**
     * Constructs a DeleteColumn object with the specified ID of the column to be deleted.
     *
     * @param idOfColumn The ID of the column to be deleted.
     */
    public DeleteColumn(UUID idOfColumn) {
        this.idOfColumn = idOfColumn;
        this.column = new Column("", null, idOfColumn);
    }

    /**
     * Deletes the column from the currently opened project in both the project entity and the database.
     * This method removes the specified column from the project and updates the database accordingly.
     * It delegates the removal of the column from the project entity to the currentProject object,
     * and it also calls the necessary database access to update the database and remove the column.
     */
    public void deleteColumn() {
        // Remove the Column with idOfColumn from the currently opened project currentProject.
        currentProject.removeColumn(idOfColumn);

        // Update the database to remove the column.
        IDBRemove dbRemoveManager = new DBManagerRemoveController();
        dbRemoveManager.DBRemove(column, idOfColumn);
    }
}
