package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.*;
import b_application_business_rules.entity_models.TaskModel;
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
     * This method creates the task and adds it to the column
     */
    public void addTask(UUID idOfColumn, TaskModel taskModel) {
        Task task = createTaskEntity(taskModel);

        List<Column> listOfColumns = currentProject.getColumns();
        Column currentColumn = Column.IDToColumn(idOfColumn, listOfColumns);

        if (currentColumn != null) {
            currentColumn.getTasks().add(task);
        } else {
            System.out.println("Column not found.");
        }
    }

    /**
     * Creates and returns Task Entity with given Task Model
     *
     * @param taskModel The task model containing task details
     * @return The created Task entity
     */
    public static Task createTaskEntity(TaskModel taskModel) {
        return new Task(
                taskModel.getName(),
                taskModel.getID(),
                taskModel.getDescription(),
                taskModel.getCompletionStatus(),
                taskModel.getDueDateTime()
        );
    }

    public void setCurrentProject(Project project) {
        this.currentProject = project;
    }
}
