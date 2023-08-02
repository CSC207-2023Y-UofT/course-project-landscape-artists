package b_application_business_rules;

import b_application_business_rules.entity_models.*;
import java.util.UUID;

public interface DataAccessInterface {
    void updateTaskName(UUID taskID, TaskModel updatedTask);

    void updateTaskDetail(UUID taskID, TaskModel updatedTask);

    void deleteTask(UUID taskID, TaskModel deletedTask);

    void updateColumnName(UUID columnID, ColumnModel updatedColumn);

    void deleteColumn(UUID columnID, ColumnModel deletedColumn);

}
