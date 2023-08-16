package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Task;
import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerRemoveController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A use case class responsible for deleting a task from a project's column.
 */
public class DeleteTask {

    private Project currentProject;

    /**
     * Constructs a DeleteTask instance associated with the specified project.
     *
     * @param currentProject The project from which tasks will be deleted.
     */
    public DeleteTask(Project currentProject) {
        this.currentProject = currentProject;
    }

    /**
     * Deletes the specified task from the given column in the project.
     *
     * @param columnID The ID of the column from which the task will be deleted.
     * @param taskModel The model representing the task to be deleted.
     */
    public void deleteTask(UUID columnID, TaskModel taskModel) {
        // Delete Task from Column entity's list of tasks'
        // First get the list of columns in the current project
        List<Column> listOfColumns = currentProject.getColumns();
        // Then search for the column entity
        Column currentColumn = Column.IDToColumn(columnID, listOfColumns);
        // Then search for the Task entity
        List<Task> listOfTasks = currentColumn.getTasks();
        Task task = Task.IDToTask(taskModel.getID(), (ArrayList<Task>) listOfTasks);
        // remove the task
        currentColumn.removeTask(task);

        // initialize controller and remove task from database
        IDBRemove idbRemove = new DBManagerRemoveController();
        idbRemove.DBRemoveTask(taskModel.getID());

        IDBInsert idbInsert = new DBManagerInsertController();

        idbRemove.DBRemoveColumn(columnID);
        idbInsert.DBInsert(new ColumnModel(currentColumn));
    }
}