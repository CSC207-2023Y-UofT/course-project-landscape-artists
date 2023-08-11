package b_application_business_rules.boundaries;//this boundary will be the output boundary when we are vieweing and modifying a single project.

//
//this boundary will be responsable for telling the outer classes what to do and what to show

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.TaskModel;
import c_interface_adapters.view_models.ProjectViewModel;
import c_interface_adapters.view_models.TaskViewModel;


import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.UUID;


import java.util.UUID;

public interface ProjectViewingAndModificationOutputBoundary {

    void displayAllProjects();

    void displayNewTask(UUID columnBoxID, TaskModel newTask);

    void displayRemovedTask(TaskModel task);

    void displayRenamedColumn(ColumnModel columnModel);

    void displayDeletedColumn(ColumnModel columnModel);

    void displayChangedTaskDetails(UUID taskID, TaskViewModel task, UUID columnID);

    void displayDeleteProject(ProjectViewModel project, UUID projectId);

    void displayNewColumn(ColumnModel c);
}