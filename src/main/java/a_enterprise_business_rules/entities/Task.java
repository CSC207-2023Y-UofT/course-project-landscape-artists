
package a_enterprise_business_rules.entities;

import java.time.LocalDateTime;
import java.util.*;


/**
 * An entity class to represent a task within a kanban board project.
 */
public class Task {

    /**
     * The name of the task.
     */
    private String name;

    /**

     * The name of the task.
     */
    private UUID ID;

    /**

     * The description of the task.
     */
    private String description;

    /**
     * Whether the task has been completed or not.
     */
    private boolean isCompleted;

    /**
     * The due date for this task. Will be null if there is no due date.
     */
    private LocalDateTime dueDateTime;

    /**

     * Creates a new task, based in the inputted values.
     * 
     * @param name        The name of the task.
     * @param description The description of the task.
     *                    ]] * @param isCompleted whether the task is
     *                    completed or not.
     * @param dueDateTime The due date for this task.
     * @param id          The task ID.
     */
    public Task(String name, String description, boolean isCompleted, LocalDateTime dueDateTime, UUID id) {
        this.name = name;
        this.description = description;
        this.isCompleted = isCompleted;
        this.dueDateTime = dueDateTime;
        this.ID = id;

    }

    /**
     * Gets the name of the task.
     * 
     * @return the name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the task.
     * 
     * @param newName the new name for the task.
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Gets the description of the task.
     * 
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the task.
     * 
     * @param newDescription the new description of the task.
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Gets whether or not the task is completed.
     * 
     * @return a boolean, telling whether or not the task has been completed.
     */
    public boolean getCompletionStatus() {
        return this.isCompleted;
    }

    /**
     * Marks the task as being completed.
     */
    public void completeTask() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as being incomplete.
     */
    public void incompleteTask() {
        this.isCompleted = false;
    }

    /**
     * Negates the status of completion for this task.
     * 
     * Iff the task is completed, the task will be marked as incomplete.
     * Iff the task is incomplete, the task will be marked as complete.
     * 
     * @return The final status of completion for the task.
     */
    public boolean negateCompletionStatus() {
        this.isCompleted = !this.isCompleted; // negates the completion status
        return this.isCompleted; // returns current completion status
    }

    /**
     * Gets the due date time for the task. This will be a null reference if there
     * is no due date time.
     * 
     * @return The due date time for the task.
     */
    public LocalDateTime getDueDateTime() {
        return this.dueDateTime;
    }

    /**
     * Set the due date time for the task.
     * 
     * @param newDueDateTime The new due date time to set for the task.
     */
    public void setDueDateTime(LocalDateTime newDueDateTime) {
        this.dueDateTime = newDueDateTime;
    }

    public UUID getID() {
        return this.ID;
    }

    /**
     * Returns a String representation of the Task.
     * 
     * {@inheritDoc}
     * 
     * @return a String representation of the Task.
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
        // "[Task Name: Eat Cookied, Task Completed: false]"
        return "[" + "Task Name: " + this.getName() + ", " + "Task Completed: " + completionStatusString + ", "
                + "Due Date: " + this.dueDateTime.toString() + "]";
    }

}