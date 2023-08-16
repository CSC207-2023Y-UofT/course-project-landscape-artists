package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerRemoveController;

import java.util.Objects;
import java.util.UUID;

/**
 * The DeleteColumn class is responsible for deleting a column from the
 * currently opened project in both the project entity and the database.
 * It allows removing a column with the specified ID from the project and
 * updates the database accordingly.
 */
public class DeleteColumn {
    IDBRemove databaseRemover = new DBManagerRemoveController();
    IDBInsert idbInsert = new DBManagerInsertController();

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

    /**
     * Deletes a column and its associated tasks from the database.
     *
     * @param columnID The UUID of the column to be deleted.
     */
    public void deleteColumnFromDB(UUID columnID) {
        // Remove column from the DB
        for (Column col : currentProject.getColumns()) {
            if(col.getID().toString().equals(columnID.toString())){
                for (Task task : col.getTasks()) {
                    databaseRemover.DBRemoveTask(task.getID());
                }
            }
        }
        databaseRemover.DBRemoveColumn(columnID);
        updateProject(currentProject, columnID);
    }

    /**
     * Updates the project by removing the specified column and then reinserts the project into the database.
     *
     * @param projectToBeUpdated The project that needs to be updated.
     * @param colID              The UUID of the column to be removed.
     */
    private void updateProject(Project projectToBeUpdated, UUID colID) {
        for (Column col : projectToBeUpdated.getColumns()) {
            if(col.getID().equals(colID)){
                projectToBeUpdated.getColumns().remove(col);
                break;
            }
        }
        databaseRemover.DBRemoveProject(projectToBeUpdated.getID());
        idbInsert.DBInsert(new ProjectModel(projectToBeUpdated));
    }
}
