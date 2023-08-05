package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;

import java.util.ArrayList;

/**
 * The AddColumn class is responsible for adding a new column to the currently
 * opened project in the database and the project entity.
 */
public class AddColumn {

    /**
     * The Column object representing the new column to be added.
     */
    private final ColumnModel columnModel;

    /**
     * The current project being worked on. Received from Singleton data class.
     */
    private final Project currentProject = CurrentProjectRepository.getCurrentprojectrepository().getCurrentProject()
            .getProjectEntity();

    /**
     * Constructs an AddColumn object with the specified column name and column ID.
     *
     * @param columnModel
     */
    public AddColumn(ColumnModel columnModel) {
        this.columnModel = columnModel;
    }

    /**
     * Adds the new column to the database and the currently opened project entity.
     * This method performs necessary database access and updates the project entity
     * with the new column.
     */
    public void addColumn() {
        // Create the column entity
        Column column = createColumnEntity(columnModel);
        // Add the column to the currently opened Project entity.
        currentProject.addColumn(column);

        // Update database to add the column.
        IDBInsert dbInsertManager = new DBManagerInsertController();
        dbInsertManager.DBInsert(columnModel);
    }

    /**
     * Creates and returns Column Entity with given Column Model
     * 
     * @param columnModel
     */

    public static Column createColumnEntity(ColumnModel columnModel) {
        return new Column(columnModel.getName(), new ArrayList<Task>(), columnModel.getID());
    }
}
