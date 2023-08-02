package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;
import a_enterprise_business_rules.entities.*;

import b_application_business_rules.DataAccessInterface;
import b_application_business_rules.boundaries.ProjectViewingAndModificationInputBoundary;
import b_application_business_rules.boundaries.ProjectViewingAndModificationOutputBoundary;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.factories.TaskFactory;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import b_application_business_rules.use_cases.project_selection_gateways.IDBSearch;
import c_interface_adapters.ProjectViewingAndModificationController;
import c_interface_adapters.ProjectViewingAndModificationPresenter;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerRemoveController;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerSearchController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class EditTaskDetails implements DataAccessInterface {

    TaskModel taskModel;
    UUID taskID;

    public EditTaskDetails(TaskModel model, UUID id) {
        this.taskModel = model;
        this.taskID = id;
    }

    public void editTask() {
        //Call the update function since the model is new but the one in csv is old
        // There is no model for the old task, so we make one in updateTaskDetail
        //Assume the user ALWAYS changes both name and description when changing one
        updateTaskDetail(taskID, taskModel);
    }
    @Override
    public void updateTaskDetail(UUID taskID, TaskModel updatedTask) {
        IDBRemove removeTask = new DBManagerRemoveController();
        IDBInsert insertTask = new DBManagerInsertController();
        IDBSearch findOldTask = new DBManagerSearchController();

        ArrayList<String> oldTaskInfo = findOldTask.DBTaskSearch(taskID.toString());
        String oldTaskName = oldTaskInfo.get(1);
        String oldTaskDescription = oldTaskInfo.get(2);
        boolean oldTaskStatus = Boolean.parseBoolean(oldTaskInfo.get(3));
        LocalDateTime oldTaskDate = LocalDateTime.parse(oldTaskInfo.get(4));
        TaskModel oldTask = TaskFactory.create(oldTaskName, taskID,
                oldTaskDescription, oldTaskStatus, oldTaskDate );
        removeTask.DBRemove(oldTask, taskID);

        insertTask.DBInsert(updatedTask);


    }
}