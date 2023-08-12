package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerRemoveController;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The DeleteColumn class is responsible for deleting a column from the
 * currently opened project in both the project entity and the database.
 * It allows removing a column with the specified ID from the project and
 * updates the database accordingly.
 */
public class DeleteColumn {


    /**
     * The current project being worked on. Received from Singleton data class.
     */
    private final Project currentProject;

    /**
     * Constructs a DeleteColumn object with the current project entity
     *
     * @param currentProject The ID of the column to be deleted.
     */
    public DeleteColumn(Project currentProject) {
        this.currentProject = currentProject;
    }

    /**
     * Deletes the column from the currently opened project in the project entity.
     */
    public void deleteColumn(UUID columnID) {

        // Remove column from the current project
        currentProject.removeColumn(columnID);


    }
}
