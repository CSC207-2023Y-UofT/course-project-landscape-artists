package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import b_application_business_rules.DataAccessInterface;

import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.factories.TaskModelFactory;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import b_application_business_rules.use_cases.project_selection_gateways.IDBSearch;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerRemoveController;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerSearchController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * A use case class for editing task details (name and description)
 */
public class EditTaskDetails implements DataAccessInterface {

    TaskModel taskModel;
    UUID taskID;

    public EditTaskDetails(TaskModel model, UUID id) {
        this.taskModel = model;
        this.taskID = id;
    }

    /**
     * This method makes edits to the task
     */
    public void editTask(UUID parentColumn) {
        // Call the update function since the model is new but the one in csv is old
        // There is no model for the old task, so we make one in updateTaskDetail

        // Assume the user ALWAYS changes both name and description when changing one
        // For the sake of simplicity
        updateTaskDetail(taskID, taskModel, parentColumn);
    }

    /**
     * Updates the Task.csv file with the new updatedTask. It removes the existing
     * task given the UUID
     * and inserts in a new task with all the updated information.
     * 
     * @param taskID
     * @param updatedTask
     */
    @Override
    public void updateTaskDetail(UUID taskID, TaskModel updatedTask, UUID parentColumn) {
        // Initializing the controllers
        IDBRemove removeTask = new DBManagerRemoveController();
        IDBInsert insertTask = new DBManagerInsertController();
        IDBSearch findOldTask = new DBManagerSearchController();

        // Removing the existing task requires a TaskModel, which we don't have any
        // So we need to make one: by finding all the information about the old task
        // Then using the TaskFactory to create a TaskModel

        ArrayList<String> oldTaskInfo = findOldTask.DBTaskSearch(taskID.toString());
        String oldTaskName = oldTaskInfo.get(1);
        String oldTaskDescription = oldTaskInfo.get(2);
        boolean oldTaskStatus = Boolean.parseBoolean(oldTaskInfo.get(3));
        LocalDateTime oldTaskDate = LocalDateTime.parse(oldTaskInfo.get(4));
        TaskModel oldTask = TaskModelFactory.create(oldTaskName, taskID,
                oldTaskDescription, oldTaskStatus, oldTaskDate);

        // Removing the old task
        removeTask.DBRemoveTask(taskID);

        // Inserting the new task
        insertTask.DBInsert(updatedTask, parentColumn);

    }


}