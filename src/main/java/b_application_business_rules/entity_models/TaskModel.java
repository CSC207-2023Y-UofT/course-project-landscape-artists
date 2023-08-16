package b_application_business_rules.entity_models;

import a_enterprise_business_rules.entities.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * A task model within in the productivity application.
 * 
 * Each task model will have a name, a unique identifier, a description, an
 * attribute
 * to indicate whether the task model has been completed or not, and a due date.
 */
public class TaskModel {

    /**
     * The name of the task model.
     */
    private String name;

    /**
     * A unique identifier for the task model.
     */
    private UUID ID;

    /**
     * The description of the task model.
     */
    private String description;

    /**
     * Whether the task model has been completed or not.
     */
    private boolean isCompleted;

    /**
     * The due date for this task model. Will be null if there is no due date.
     */
    private LocalDateTime dueDateTime;

    /**
     * Creates a new task model, based on the inputted values.
     * 
     * @param name        The name of the task model.
     * @param ID          The unique identifier for the task model.
     * @param description A description of the task model.
     * @param isCompleted Whether the task model is completed or not.
     * @param dueDateTime The due date for this task model.
     */
    public TaskModel(String name, UUID ID, String description, boolean isCompleted, LocalDateTime dueDateTime) {
        this.name = name;
        this.ID = ID;
        this.description = description;
        this.isCompleted = isCompleted;
        this.dueDateTime = dueDateTime;
    }

    /**
     * Creates a new task model, based on the inputted task.
     * 
     * @param task The task to model.
     */
    public TaskModel(Task task) {
        this.name = task.getName();
        this.ID = task.getID();
        this.description = task.getDescription();
        this.isCompleted = task.getCompletionStatus();
        this.dueDateTime = task.getDueDateTime();
    }

    /**
     * Gets the name of the task model.
     * 
     * @return the name of the task model.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the task model.
     * 
     * @param newName the new name for the task model.
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Gets the unique identifier for the task model.
     * 
     * @return a unique identifier for the task model.
     */
    public UUID getID() {
        return this.ID;
    }

    /**
     * Sets a new unique identifier for the task model.
     * 
     * @param newID a new unique identifier for the task model.
     */
    public void setID(UUID newID) {
        this.ID = newID;
    }

    /**
     * Gets the description of the task model.
     * 
     * @return the description of the task model.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the task model.
     * 
     * @param newDescription the new description of the task model.
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Gets whether the task model is completed.
     * 
     * @return a boolean, telling whether the task model has been completed.
     */
    public boolean getCompletionStatus() {
        return this.isCompleted;
    }

    /**
     * Marks the task model as being completed.
     */
    public void completeTaskModel() {
        this.isCompleted = true;
    }

    /**
     * Marks the task model as being incomplete.
     */
    public void incompleteTaskModel() {
        this.isCompleted = false;
    }

    /**
     * Negates the status of completion for this task model.
     * 
     * Iff the task model is completed, the task model will be marked as incomplete.
     * Iff the task model is incomplete, the task model will be marked as complete.
     * 
     * @return The final status of completion for the task model.
     */
    public boolean negateCompletionStatus() {
        this.isCompleted = !this.isCompleted; // negates the completion status
        return this.isCompleted; // returns current completion status
    }

    /**
     * Gets the due date time for the task model. This will be a null reference if
     * there
     * is no due date time.
     * 
     * @return The due date time for the task model.
     */
    public LocalDateTime getDueDateTime() {
        return this.dueDateTime;
    }

    /**
     * Set the due date time for the task model.
     * 
     * @param newDueDateTime The new due date time to set for the task model.
     */
    public void setDueDateTime(LocalDateTime newDueDateTime) {
        this.dueDateTime = newDueDateTime;
    }

    /**
     * Returns a String representation of the TaskModel.
     * 
     * {@inheritDoc}
     * 
     * @return a String representation of the TaskModel.
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
        // "[TaskModel Name: Eat Cookied, TaskModel Completed: false]"
        return "[" + "TaskModel Name: " + this.getName() + ", " + "TaskModel Completed: " + completionStatusString
                + ", "
                + "Due Date: " + this.dueDateTime.toString() + "]";
    }

    /**
     * Returns a Task Entity from Task Model.
     *
     * {@inheritDoc}
     *
     * @return a Task Entity.
     */
    public Task getTaskEntity() {
        Task task = new Task(name, ID, description, isCompleted, dueDateTime);
        return task;
    }

    /**
     * Removes a specified task model from a list of task models.
     *
     * @param taskModelList The list of task models from which the specified task should be removed.
     * @param taskModel     The task model to be removed from the list.
     * @return              The modified list of task models after removing the specified task.
     */
    public static List<TaskModel> removeFromTaskModelList(List<TaskModel> taskModelList ,TaskModel taskModel ){
        for (TaskModel task : taskModelList) {
            if(task.getID().equals(taskModel.getID())){
                taskModelList.remove(task);
                break;
            }
        }
        return taskModelList;
    }
}