package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.*;

import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;

import java.util.List;
import java.util.UUID;

/**
 * A use case class for creating new tasks in a column
 */
public class AddTask {
    private Project currentProject;

    public AddTask(Project currentProject) {
        this.currentProject = currentProject;
    }

    /**
     * Creates the task entity and adds it to the respective column entity's list of tasks
     *
     * @param columnID The ID of the column the task will be added to
     * @param taskModel The entity model of the task to be added
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
        currentColumn.getTasks().add(task);
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