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

import java.util.ArrayList;
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
    private Project project;

    /**
     * Constructs an MoveTask object with the specified columns
     *
     * @param sourceColumnID The column to move the task from
     * @param targetColumnID The column to move the task to
     * @param project The project the columns are in
     */
    public MoveTask(String sourceColumnID, String targetColumnID, Project project) {
       this.project = project;
        for (Column column : project.getColumns()) {
           if (column.getID().toString().equals(sourceColumnID)) {
               sourceColumn = column;
           } else if (column.getID().toString().equals(targetColumnID)) {
               targetColumn = column;
           }
       }
    }

    /**
     * Moves a task with the specified ID from the source column to the target column.
     *
     * @param taskID The unique ID of the task to be moved.
     */
    public void moveTask(UUID taskID) {
        Task taskToMove = Task.IDToTask(taskID, (ArrayList<Task>) sourceColumn.getTasks());
        sourceColumn.removeTask(taskToMove);
        targetColumn.addTask(taskToMove);
    }

}
