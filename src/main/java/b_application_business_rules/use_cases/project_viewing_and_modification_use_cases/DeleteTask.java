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
 * A use case class for deleting a task
 */
public class DeleteTask implements DataAccessInterface{
    TaskModel taskModel;
    UUID taskID;

    public DeleteTask(TaskModel model, UUID id) {
        this.taskModel = model;
        this.taskID = id;
    }

    /**
     * NEED TO IMPLEMENT REMOVING TASK ENTITY FROM COLUMN ENTITY
     */
    public void deleteTask() {

        //call the method that accesses the database
        removeTask(taskID, taskModel);
    }

    @Override
    public void removeTask(UUID taskID, TaskModel deletedTask) {
        //initialize controller
        IDBRemove removeTask = new DBManagerRemoveController();
        //remove task from database
        removeTask.DBRemove(taskModel, taskID);
    }
}