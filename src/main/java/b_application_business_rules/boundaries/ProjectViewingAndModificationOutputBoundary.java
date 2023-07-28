package b_application_business_rules.boundaries;//this boundary will be the output boundary when we are vieweing and modifying a single project.
//
//this boundary will be responsable for telling the outer classes what to do and what to show

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.entity_models.TaskModel;
import javafx.scene.layout.VBox;

public interface ProjectViewingAndModificationOutputBoundary {
    Project getCurrentProject();
    void displayAllProjects();

    void displayNewTask(UUID columnBoxID, TaskViewModel newTask);

    void displayRenamedTask(UUID taskID, TaskViewModel task);

    void displayRemovedTask(UUID taskID, TaskViewModel task);

    void displayRenamedColumn(UUID columnUIid, ColumnViewModel column);

    void displayDeletedColumn(UUID columnUIid, ColumnViewModel column);
    void dislayChangedTaskDetails(UUID taskID, TaskViewModel task);
    void dislayChangedTaskDate(UUID taskID, TaskViewModel task);
    void displayRenamedProject(ProjectViewModel project, UUID projectId);
    void displayDeleteProject(ProjectViewModel project, UUID projectId);
}