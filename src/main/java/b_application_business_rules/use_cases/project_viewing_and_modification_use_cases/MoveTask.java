package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerRemoveController;

/**
 * The MoveTask class is responsible for moving a task from 1 column to another
 */
public class MoveTask {

    /** The column to move the task from */
    private ColumnModel column1;
    /** The column to move the task to */
    private ColumnModel column2;
    /** The project the columns are in */
    private ProjectModel project;

    /**
     * Constructs an MoveTask object with the specified columns
     *
     * @param column1 The column to move the task from
     * @param column2 The column to move the task to
     * @param project The project the columns are in
     */
    public MoveTask(ColumnModel column1, ColumnModel column2, ProjectModel project) {
        this.column1 = column1;
        this.column2 = column2;
        this.project = project;
    }

    /**
     * Moves the task from one column to another.
     * 
     * @param task The task to move.
     */
    public void moveTask(TaskModel task) {
        this.column1.removeTaskModel(task);
        this.column2.addTaskModel(task);

        IDBRemove databaseRemover = new DBManagerRemoveController();
        databaseRemover.DBRemoveProject(this.project.getID());

        IDBInsert databaseInserter = new DBManagerInsertController();
        databaseInserter.DBInsert(this.project);

    }

}
