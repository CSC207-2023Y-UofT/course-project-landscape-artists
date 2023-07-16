package Entities;

/**
 * An entity class to represent a task within a kanban board.
 */
public class Task {

    /**
     * The name of the task.
     */
    private String taskName;

    /**
     * The description of the task.
     */
    private String description;

    /**
     * The ID (identifier) for this task.
     * This ID should be unique to the other tasks in the project this task is in.
     */
    private int taskID;

    // I haven't added anything in regards to a due date for the Task yet,
    // as, I'm not sure which date/time object we will use in Java,
    // and which one would be best for implementation purposes.
    // This should be easily be added anyways, if we would like to add it.

    /**
     * Creates a new task, based in the inputted <code>taskName</code> and
     * <code>description</code>.
     * 
     * @param taskName    The name of the task.
     * @param description The description of the task.
     * @param taskID      The unique identifier for the task.
     */
    public Task(String taskName, String description, int taskID) {
        this.taskName = taskName;
        this.description = description;
        this.taskID = taskID;
    }

    /**
     * Gets the name of the task.
     * 
     * @return the name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Sets the name of the task.
     * 
     * @param newTaskName the new name for the task.
     */
    public void setTaskName(String newTaskName) {
        this.taskName = newTaskName;
    }

    /**
     * Gets the description of the task.
     * 
     * @return the description of the task.
     */
    public String getTaskDescription() {
        return this.description;
    }

    /**
     * Sets the description of the task.
     * 
     * @param newDescription the new description of the task.
     */
    public void setTaskDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Gets the ID of the task.
     * 
     * @return ID of the task
     */
    public int getTaskID() {
        return this.taskID;
    }

    // We probably shouldn't be needing to set TaskIDs after a task has been
    // created.
    // /**
    // * Sets a new task ID for the current task.
    // *
    // * @param newTaskID the new task ID of the task.
    // */
    // public void setTaskID(int newTaskID) {
    // this.taskID = newTaskID;
    // }

}