package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDbIdToModel;
import d_frameworks_and_drivers.database_management.DBControllers.DbIDToModel;

import java.util.ArrayList;

/**
 * The AddColumn class is responsible for adding a new column to the currently
 * opened project in the project entity.
 */
public class AddColumn {

    /**
     * The current project being worked on. Received from Singleton data class.
     */
    private final Project currentProject;


    public AddColumn(Project currentProject) {
        this.currentProject = currentProject;
    }

    /**
     * Adds the new column to the current project, project entity.
     */
    public void addColumn(ColumnModel columnModel) {
        // Create the column entity
        Column column = columnModel.getColumnEntity();
        // Add the column to the currently opened Project entity.
        currentProject.addColumn(column);
    }
}
