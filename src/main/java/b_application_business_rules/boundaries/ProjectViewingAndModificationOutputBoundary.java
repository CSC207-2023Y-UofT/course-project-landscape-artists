package b_application_business_rules.boundaries;//this boundary will be the output boundary when we are viewing and modifying a single project.

//
//this boundary will be responsible for telling the outer classes what to do and what to show

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.TaskModel;



import java.util.UUID;



public interface ProjectViewingAndModificationOutputBoundary {

    void displayAllProjects();

    void displayNewTask(UUID columnBoxID, TaskModel newTask);

    void displayRemovedTask(TaskModel task, UUID columnBoxID);

    void displayRenamedColumn(ColumnModel columnModel);

    void displayDeletedColumn(ColumnModel columnModel);

    void displayChangedTaskDetails(TaskModel task, UUID columnID);

    void displayNewColumn(ColumnModel c);
}