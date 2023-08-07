package b_application_business_rules.boundaries;//this boundary will be the input boundary when we are vieweing and modifying a single project.

//
//this boundary will be responsable for getting the information from the outer layers and doing the work (running the right use cases, etc.)


import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;


import java.time.LocalDateTime;
import java.util.UUID;

public interface ProjectViewingAndModificationInputBoundary {
    void removeCurrentProject();

    void addNewTask(UUID idOfColumn, String taskName, String taskDescription,
                    LocalDateTime dueDate);


    void deleteColumn(UUID columnBoxId);

    void editColumnDetails(UUID columnBoxId, String newColumnName);

    void deleteTask(TaskModel task, UUID TaskUIid, UUID ColumnID);

    void changeTaskDetails(TaskModel task, UUID TaskUIid, UUID ParentColumn);

    void renameTask(TaskModel task, UUID TaskUIid);

    void removeTask(TaskModel task, UUID columnId);

    void addTask(TaskModel task, UUID targetColumnId);
    void changeTaskDate(TaskModel task, UUID targetColumnId);
    void renameProject(ProjectModel project, UUID projectId);
    void deleteProject(ProjectModel project, UUID projectId);

    void addColumn(String columnName);
}