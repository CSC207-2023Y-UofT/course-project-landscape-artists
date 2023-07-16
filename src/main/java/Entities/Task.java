package Entities;

/**
 * An entity class to represent a task within a kanban board project.
 */
public class Task {

    /**
     * The name of the task.
     */
    private String name;

    /**
     * The description of the task.
     */
    private String description;

    private boolean isCompleted;

    // I haven't added anything in regards to a due date for the Task yet,
    // as, I'm not sure which date/time object we will use in Java,
    // and which one would be best for implementation purposes.
    // This should be easily be added anyways, if we would like to add it.

    /**
     * Creates a new task, based in the inputted values.
     * 
     * @param name        The name of the task.
     * @param description The description of the task.
     *                    ]] * @param isCompleted Whether or not the task is
     *                    completed.
     */
    public Task(String name, String description, boolean isCompleted) {
        this.name = name;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Calls {@@link Task(String name, String description, boolean
     * isCompleted)},
     * with <code>false</code> as the input for the <code>isCompleted</code>
     * parameter.
     * 
     * {@see Task(String name, String description, boolean isCompleted)}
     * 
     * @param name
     * @param description
     */
    public Task(String name, String description) {
        this(name, description, false);
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
        this.isCompleted = !this.isCompleted;
        return this.isCompleted;
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
        String completionStatusString;
        if (this.getCompletionStatus()) {
            completionStatusString = "true";
        } else {
            completionStatusString = "false";
        }
        return "[" + "Task Name: " + this.getName() + ", " + "Task Completed: " + completionStatusString + "]";
    }

}