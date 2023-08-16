package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.*;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import b_application_business_rules.use_cases.project_selection_gateways.IDbIdToModel;
import b_application_business_rules.use_cases.project_selection_use_cases.DeleteProject;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerRemoveController;
import d_frameworks_and_drivers.database_management.DBControllers.DbIDToModel;

import java.util.List;
import java.util.UUID;

/**
 * The AddTask class is responsible for adding a new task to the respective column in the currently
 * opened project entity.
 */
public class AddTask {
    private final Project currentProject;

    public AddTask(Project currentProject) {
        this.currentProject = currentProject;
    }

    /**
     * Creates the task entity and adds it to the respective column entity's list of tasks
     */
    public void addTask(UUID columnID, TaskModel taskModel) {
        // Create task entity
        Task task = createTaskEntity(taskModel);

        // Add task to Column Entity:
        // First, get the list of columns in the current project
        List<Column> listOfColumns = currentProject.getColumns();
        // Then, retrieve the column entity
        Column currentColumn = Column.IDToColumn(columnID, listOfColumns);
        // Then, add the task to the columns list of tasks
        if (currentColumn!=null) {
            currentColumn.addTask(task);
        }
    }

    /**
     * Creates and returns Task Entity with given Task Model
     * 
     * @param taskModel
     */

    public static Task createTaskEntity(TaskModel taskModel) {
        return new Task(taskModel.getName(), taskModel.getID(), taskModel.getDescription(),
                taskModel.getCompletionStatus(), taskModel.getDueDateTime());
    }
}