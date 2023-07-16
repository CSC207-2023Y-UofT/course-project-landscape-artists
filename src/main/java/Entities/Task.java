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

    /**
     * The ID (identifier) for this task.
     * This ID should be unique to the other tasks in the project this task is in.
     */
    private int ID;

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
     * @param ID          The unique identifier for the task.
     * @param isCompleted Whether or not the task is completed.
     */
    public Task(String name, String description, int ID, boolean isCompleted) {
        this.name = name;
        this.description = description;
        this.ID = ID;
        this.isCompleted = isCompleted;
    }

    /**
     * Calls {@@link Task(String name, String description, int ID, boolean
     * isCompleted)},
     * with <code>false</code> as the input for the <code>isCompleted</code>
     * parameter.
     * 
     * {@see Task(String name, String description, int ID, boolean isCompleted)}
     * 
     * @param name
     * @param description
     * @param ID
     */
    public Task(String name, String description, int ID) {
        this(name, description, ID, false);
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
    public int getID() {
        return this.ID;
    }

    // We probably shouldn't be needing to set IDs after a task has been
    // created.
    // /**
    // * Sets a new ID for the current task.
    // *
    // * @param newID the new task ID of the task.
    // */
    // public void setID(int newID) {
    // this.ID = newID;
    // }

    /**
     * Gets whether or not the task is completed.
     * 
     * @return a boolean, telling whether or not the task has been completed.
     */
    public boolean getisCompleted() {
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

    @Override
    public String toString() {
        return "[" + "Task Name: " + this.getName() + ", " + "Task ID: " + this.getID() + "]";
    }

}