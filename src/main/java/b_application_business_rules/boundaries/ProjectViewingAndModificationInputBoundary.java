package b_application_business_rules.boundaries;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.TaskModel;


import java.time.LocalDateTime;
import java.util.UUID;

/**
 * This interface defines the input boundary for viewing and modifying project-related elements,
 * such as tasks and columns. It provides methods to manage tasks, columns, and their details.
 */
public interface ProjectViewingAndModificationInputBoundary {

    /**
     * Removes the current project from the view.
     */
    void removeCurrentProject();

    /**
     * Adds a new task to a specified column with the given details.
     *
     * @param idOfColumn    The unique identifier of the column to which the task should be added.
     * @param taskName      The name of the new task.
     * @param taskDescription The description of the new task.
     * @param dueDate       The due date of the new task.
     */
    void addNewTask(UUID idOfColumn, String taskName, String taskDescription,
                    LocalDateTime dueDate);

    /**
     * Deletes a column identified by the provided column box ID.
     *
     * @param columnBoxId   The unique identifier of the column to be deleted.
     */
    void deleteColumn(UUID columnBoxId);

    /**
     * Edits the details of a column identified by the provided column box ID.
     *
     * @param columnBoxId   The unique identifier of the column to be edited.
     * @param newColumnName The new name for the column.
     * @param columnModel   The model containing column details to be updated.
     */
    void editColumnDetails(UUID columnBoxId, String newColumnName, ColumnModel columnModel);

    /**
     * Deletes a task from a specified column.
     *
     * @param ColumnID      The unique identifier of the column containing the task to be deleted.
     * @param taskModel     The model containing task details to be deleted.
     */
    void deleteTask(UUID ColumnID, TaskModel taskModel);

    /**
     * Changes the details of a task, such as its name and description.
     *
     * @param task           The task model with updated details.
     * @param TaskUIid       The unique identifier of the task UI element.
     * @param ParentColumn   The unique identifier of the parent column containing the task.
     */
    void changeTaskDetails(TaskModel task, UUID TaskUIid, UUID ParentColumn);

    /**
     * Adds a new column with the given column name.
     *
     * @param columnName    The name of the new column.
     */
    void addColumn(String columnName);

    /**
     * Retrieves a specific task based on its unique identifier.
     *
     * @param taskID        The unique identifier of the task to retrieve.
     */
    void getTask(UUID taskID);

    /**
     * Changes the completion status of a task.
     *
     * @param id             The unique identifier of the task to update.
     * @param completionStatus The new completion status of the task.
     * @param columnID       The unique identifier of the column containing the task.
     */
    void changeTaskCompletionStatus(UUID id, boolean completionStatus, UUID columnID);

    /**
     * Moves a task from the source column to the target column.
     *
     * @param sourceColumnID The ID of the source column from which the task will be moved.
     * @param targetColumnID The ID of the target column to which the task will be moved.
     * @param task The task to be moved.
     */
    void moveTask(String sourceColumnID, String targetColumnID, TaskModel task);
}
