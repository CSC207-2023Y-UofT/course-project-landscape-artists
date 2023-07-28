package b_application_business_rules.boundaries;//this boundary will be the input boundary when we are vieweing and modifying a single project.
//
//this boundary will be responsable for getting the information from the outer layers and doing the work (running the right use cases, etc.)

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Task;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;

public interface ProjectViewingAndModificationInputBoundary {
    public void removeCurrentProject();

    void addNewTask(UUID idOfColumn, String taskName, String taskDescription,
                    LocalDateTime dueDate);

    void deleteColumn(ColumnModel column, UUID columnBoxId);

    void renameColumn(ColumnModel column, UUID columnBoxId);

    void deleteTask(TaskModel task, UUID TaskUIid);

    void changeTaskDetails(TaskModel task, UUID TaskUIid);

    void renameTask(TaskModel task, UUID TaskUIid);

    void removeTask(TaskModel task, UUID columnId);

    void addTask(TaskModel task, UUID targetColumnId);
    void changeTaskDate(TaskModel task, UUID targetColumnId);
    void renameProject(ProjectModel project, UUID projectId);
    void deleteProject(ProjectModel project, UUID projectId);

}