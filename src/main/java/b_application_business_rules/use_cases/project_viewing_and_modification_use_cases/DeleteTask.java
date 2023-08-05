package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Task;
import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.DataAccessInterface;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerRemoveController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A use case class for deleting a task
 */
public class DeleteTask implements DataAccessInterface {
    private TaskModel taskModel;
    private UUID taskID;
    private UUID idOfColumn;
    private final Project currentProject = CurrentProjectRepository.getCurrentprojectrepository().getCurrentProject()
            .getProjectEntity();

    public DeleteTask(TaskModel model, UUID taskID, UUID idOfColumn) {
        this.taskModel = model;
        this.taskID = taskID;
        this.idOfColumn = idOfColumn;
    }

    // Review whether entities should have arraylists or lists.ArrayLists more
    // flexible
    // Review whether models should be updated like entities
    public void deleteTask() {
        // Delete Task from Column entity's list of tasks'
        // First get the list of columns in the current project
        List<Column> listOfColumns = currentProject.getColumns();
        // Then search for the column entity
        Column currentColumn = Column.IDToColumn(idOfColumn, listOfColumns);
        // Then search for the Task entity
        List<Task> listOfTasks = currentColumn.getTasks();
        Task task = Task.IDToTask(taskID, (ArrayList<Task>) listOfTasks);
        // remove the task
        currentColumn.removeTask(task);

        // initialize controller
        IDBRemove removeTask = new DBManagerRemoveController();
        // remove task from database
        removeTask.DBRemove(taskModel, taskID);
    }
}