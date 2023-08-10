package b_application_business_rules.boundaries;//this boundary will be the output boundary when we are vieweing and modifying a single project.

//
//this boundary will be responsable for telling the outer classes what to do and what to show

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.TaskModel;
import c_interface_adapters.view_models.ProjectViewModel;
import c_interface_adapters.view_models.ColumnViewModel;
import c_interface_adapters.view_models.TaskViewModel;


import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.UUID;


import java.util.UUID;

public interface ProjectViewingAndModificationOutputBoundary {

    void displayAllProjects();

    void displayNewTask(UUID columnBoxID, TaskViewModel newTask);

    void displayRenamedTask(UUID taskID, TaskViewModel task);

    void displayRemovedTask(UUID taskID, TaskViewModel task);

    void displayRenamedColumn(ColumnModel columnModel);

    void displayDeletedColumn(ColumnModel columnModel);

    //void displayRenamedColumn(ColumnModel column);

    void displayChangedTaskDetails(UUID taskID, TaskViewModel task, HBox hbox, UUID columnID);

    void dislayChangedTaskDate(UUID taskID, TaskViewModel task);
    void displayRenamedProject(ProjectViewModel project, UUID projectId);
    void displayDeleteProject(ProjectViewModel project, UUID projectId);

    void displayNewColumn(ColumnModel c);
}