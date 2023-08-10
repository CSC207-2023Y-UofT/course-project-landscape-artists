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
    private TaskModel taskModel;
    private UUID idOfColumn;
    // I'm not sure if this 'currentProject' will refer to the same project as other
    // usecases would
    private final Project currentProject = CurrentProjectRepository.getCurrentprojectrepository().getCurrentProject()
            .getProjectEntity();

    public AddTask(UUID idOfColumn, TaskModel model) {
        this.taskModel = model;
        this.idOfColumn = idOfColumn;
    }

    /**
     * This method creates the task and calls the method that will add the task to
     * the database
     */
    public void addTask(UUID idOfColumn) {
        // Create task entity
        Task task = createTaskEntity(taskModel);

        // Add task to Column Entity
        // First get the list of columns in the current project
        List<Column> listOfColumns = currentProject.getColumns();
        // Then search for the column entity
        System.out.println(idOfColumn.toString());
        Column currentColumn = Column.IDToColumn(idOfColumn, listOfColumns);
        // Then add the task to the columns list of tasks
        currentColumn.getTasks().add(task);

        // Initializing the required controllers and calls method that adds task to the
        // database
        IDBInsert insertTask = new DBManagerInsertController();
        insertTask.DBInsert(taskModel);
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