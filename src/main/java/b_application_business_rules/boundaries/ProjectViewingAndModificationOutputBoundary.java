package b_application_business_rules.boundaries;//this boundary will be the output boundary when we are vieweing and modifying a single project.


import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.TaskModel;
import c_interface_adapters.view_models.ProjectViewModel;
import c_interface_adapters.view_models.TaskViewModel;


import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.UUID;


import java.util.UUID;

/**
 * This interface defines the output boundary for displaying project-related changes and events.
 * It provides methods to display project, column, and task-related information and modifications.
 */
public interface ProjectViewingAndModificationOutputBoundary {

    /**
     * Displays information about all projects.
     */
    void displayAllProjects();

    /**
     * Displays details of a newly added task in a specified column.
     *
     * @param columnBoxID The unique identifier of the column where the task was added.
     * @param newTask     The task model containing details of the newly added task.
     */
    void displayNewTask(UUID columnBoxID, TaskModel newTask);

    /**
     * Displays details of a removed task from a specified column.
     *
     * @param task        The task model containing details of the removed task.
     * @param columnBoxID The unique identifier of the column where the task was removed from.
     */
    void displayRemovedTask(TaskModel task, UUID columnBoxID);

    /**
     * Displays details of a column that has been renamed.
     *
     * @param columnModel The column model containing details of the renamed column.
     */
    void displayRenamedColumn(ColumnModel columnModel);

    /**
     * Displays details of a column that has been deleted.
     *
     * @param columnModel The column model containing details of the deleted column.
     */
    void displayDeletedColumn(ColumnModel columnModel);

    /**
     * Displays details of changed task information in a specified column.
     *
     * @param task       The task model containing updated details of the task.
     * @param columnID   The unique identifier of the column containing the task.
     */
    void displayChangedTaskDetails(TaskModel task, UUID columnID);

    /**
     * Displays details of a newly added column.
     *
     * @param c          The column model containing details of the newly added column.
     */
    void displayNewColumn(ColumnModel c);

    /**
     * Displays details of a specific task.
     *
     * @param taskModel  The task model containing details of the task to be displayed.
     */
    void displayTaskDetails(TaskModel taskModel);

    void displayTaskCompleted(UUID taskModel, UUID colID);
}
