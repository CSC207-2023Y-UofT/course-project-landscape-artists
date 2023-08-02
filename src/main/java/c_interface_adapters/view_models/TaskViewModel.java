package c_interface_adapters.view_models;

import b_application_business_rules.entity_models.TaskModel;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A task view model within in the productivity application.
 * 
 * Each task view model will have a name, a unique identifier, a description, an
 * attribute
 * to indicate whether the task view model has been completed or not, and a due
 * date.
 */
public class TaskViewModel {

    /**
     * The name of the task view model.
     */
    private String name;

    /**
     * A unique identifier for the task view model.
     */
    private UUID ID;

    /**
     * The description of the task view model.
     */
    private String description;

    /**
     * Whether the task view model has been completed or not.
     */
    private boolean isCompleted;

    /**
     * The due date for this task view model. Will be null if there is no due date.
     */
    private LocalDateTime dueDateTime;

    /**
     * Creates a new task view model, based on the inputted values.
     * 
     * @param name        The name of the task view model.
     * @param ID          The unique identifier for the task view model.
     * @param description A description of the task view model.
     * @param isCompleted Whether the task view model is completed or not.
     * @param dueDateTime The due date for this task view model.
     */
    public TaskViewModel(String name, UUID ID, String description, boolean isCompleted, LocalDateTime dueDateTime) {
        this.name = name;
        this.ID = ID;
        this.description = description;
        this.isCompleted = isCompleted;
        this.dueDateTime = dueDateTime;
    }

    /**
     * Creates a new task view model, based on the inputted task.
     * 
     * @param taskModel The task to view model.
     */
    public TaskViewModel(TaskModel taskModel) {
        this.name = taskModel.getName();
        this.ID = taskModel.getID();
        this.description = taskModel.getDescription();
        this.isCompleted = taskModel.getCompletionStatus();
        this.dueDateTime = taskModel.getDueDateTime();
    }

    /**
     * Gets the name of the task view model.
     * 
     * @return the name of the task view model.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the task view model.
     * 
     * @param newName the new name for the task view model.
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Gets the unique identifier for the task view model.
     * 
     * @return a unique identifier for the task view model.
     */
    public UUID getID() {
        return this.ID;
    }

    /**
     * Sets a new unique identifier for the task view model.
     * 
     * @param newID a new unique identifier for the task view model.
     */
    public void setID(UUID newID) {
        this.ID = newID;
    }

    /**
     * Gets the description of the task view model.
     * 
     * @return the description of the task view model.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the task view model.
     * 
     * @param newDescription the new description of the task view model.
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Gets whether or not the task view model is completed.
     * 
     * @return a boolean, telling whether or not the task view model has been
     *         completed.
     */
    public boolean getCompletionStatus() {
        return this.isCompleted;
    }

    /**
     * Marks the task view model as being completed.
     */
    public void completeTaskViewModel() {
        this.isCompleted = true;
    }

    /**
     * Marks the task view model as being incomplete.
     */
    public void incompleteTaskViewModel() {
        this.isCompleted = false;
    }

    /**
     * Negates the status of completion for this task view model.
     * 
     * Iff the task view model is completed, the task view model will be marked as
     * incomplete.
     * Iff the task view model is incomplete, the task view model will be marked as
     * complete.
     * 
     * @return The final status of completion for the task view model.
     */
    public boolean negateCompletionStatus() {
        this.isCompleted = !this.isCompleted; // negates the completion status
        return this.isCompleted; // returns current completion status
    }

    /**
     * Gets the due date time for the task view model. This will be a null reference
     * if
     * there
     * is no due date time.
     * 
     * @return The due date time for the task view model.
     */
    public LocalDateTime getDueDateTime() {
        return this.dueDateTime;
    }

    /**
     * Set the due date time for the task view model.
     * 
     * @param newDueDateTime The new due date time to set for the task view model.
     */
    public void setDueDateTime(LocalDateTime newDueDateTime) {
        this.dueDateTime = newDueDateTime;
    }

    /**
     * Returns a String representation of the TaskViewModel.
     * 
     * {@inheritDoc}
     * 
     * @return a String representation of the TaskViewModel.
     */
    @Override
    public String toString() {
        // gets the string representation of the completion status, which is a bool
        String completionStatusString;
        if (this.getCompletionStatus()) {
            completionStatusString = "true";
        } else {
            completionStatusString = "false";
        }

        // Concatenates some strings together, for example:
        // "[TaskViewModel Name: Eat Cookied, TaskViewModel Completed: false]"
        return "[" + "TaskViewModel Name: " + this.getName() + ", " + "TaskViewModel Completed: "
                + completionStatusString
                + ", "
                + "Due Date: " + this.dueDateTime.toString() + "]";
    }

}