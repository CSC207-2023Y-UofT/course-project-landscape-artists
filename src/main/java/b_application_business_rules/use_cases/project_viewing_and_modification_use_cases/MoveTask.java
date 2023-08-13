package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerRemoveController;

import java.util.UUID;

/**
 * The MoveTask class is responsible for moving a task from 1 column to another
 */
public class MoveTask {

    /** The column to move the task from */
    private Column sourceColumn;
    /** The column to move the task to */
    private Column targetColumn;
    /** The project the columns are in */
    private final Project currentProject;

    /**
     * Constructs an MoveTask object with the specified columns
     *
     * @param sourceColumnID The column to move the task from
     * @param targetColumnID The column to move the task to
     * @param currentProject The project the columns are in
     */
    public MoveTask(UUID sourceColumnID, UUID targetColumnID, Project currentProject) {
        this.currentProject = currentProject;
        for (Column columnInProject: currentProject.getColumns()) {
            if (columnInProject.getID().equals(sourceColumnID)) {
                sourceColumn = columnInProject;
            } else if (columnInProject.getID().equals(targetColumnID)) {
                targetColumn = columnInProject;
            }
        }
    }

    /**
     * Moves the task from one column to another.
     * 
     * @param task The task to move.
     */
    public void moveTask(Task task) {
        this.sourceColumn.removeTask(task);
        this.targetColumn.addTask(task);

    }

}
