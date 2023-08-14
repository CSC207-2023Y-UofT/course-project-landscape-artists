package b_application_business_rules.boundaries;//this boundary will be the input boundary when we are viewing and modifying a single project.

//
//this boundary will be responsible for getting the information from the outer layers and doing the work (running the right use cases, etc.)


import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.TaskModel;


import java.time.LocalDateTime;
import java.util.UUID;

public interface ProjectViewingAndModificationInputBoundary {
    void removeCurrentProject();

    void addNewTask(UUID idOfColumn, String taskName, String taskDescription,
                    LocalDateTime dueDate);
    void deleteColumn(UUID columnBoxId);

    void editColumnDetails(UUID columnBoxId, String newColumnName, ColumnModel columnModel);

    void deleteTask(UUID ColumnID, TaskModel taskModel);

    void changeTaskDetails(TaskModel task, UUID TaskUIid, UUID ParentColumn);

    void addColumn(String columnName);
}