package b_application_business_rules;

import b_application_business_rules.entity_models.*;
import java.util.UUID;

/**
 * This interface is to be used by the use case classes in order to update the database
 * each use case implements the respective methods.*
 * Note: all the methods are default, so use cases can just implement the one needed for them
 */

public interface DataAccessInterface {

    void deleteTask(UUID columnID, TaskModel taskModel);

//    default void updateTaskName(UUID taskID, TaskModel updatedTask) {
//
//    }
//
//
//    default void addNewTask(TaskModel newTask) {
//
//    }
//
//    default void updateTaskDetail(UUID taskID, TaskModel updatedTask, UUID parentColumn) {
//
//    }
//
//    default void removeTask(UUID taskID, TaskModel deletedTask) {
//
//    }
//
//    default void updateColumnName(UUID columnID, ColumnModel updatedColumn) {
//
//    }
//
//    default void deleteColumn(UUID columnID, ColumnModel deletedColumn) {
//
//    }
}
