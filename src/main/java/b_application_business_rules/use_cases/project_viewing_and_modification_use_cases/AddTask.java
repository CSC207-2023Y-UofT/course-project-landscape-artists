package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.entities.*;
import b_application_business_rules.DataAccessInterface;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A use case class for editing task details (name and description)
 */
public class AddTask implements DataAccessInterface {
    TaskModel taskModel;

    // This holds the currently opened Project entity. We can update this for use cases.
    Project currentProject = CurrentProjectRepository.getInstance().getCurrentProject().getProjectEntity();

    public AddTask(TaskModel model) {
        this.taskModel = model;
    }

    /**
     * This method makes creates the task and calls the method that will add the task to the database
     * NEED TO IMPLEMENT ADDING TASK TO THE LIST OF TASKS IN THE COLUMN ENTITY
     */
    public void addTask() {
        // Create task entity
        String name = taskModel.getName();
        UUID taskID = taskModel.getID();
        String description = taskModel.getDescription();
        boolean isCompleted = taskModel.getCompletionStatus();
        LocalDateTime dueDate = taskModel.getDueDateTime();
        Task newTask = new Task(name, taskID, description, isCompleted, dueDate);
        //Call the method that accesses the database
        addNewTask(taskModel);

        // Adding the Task instance to the column.
        for (Column column: currentProject.getColumns()) {
            // Find the column with the correct id. Update that Column entity with the new Task.
        }

    }

    /**
     * Adds the newly created task to the database
     * @param newTask
     */
    @Override
    public void addNewTask(TaskModel newTask) {
        //Initializing the required controllers
        IDBInsert insertTask = new DBManagerInsertController();
        insertTask.DBInsert(newTask);
    }
}