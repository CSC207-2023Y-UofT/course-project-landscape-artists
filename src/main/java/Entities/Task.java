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

    // I haven't added anything in regards to a due date for the Task yet,
    // as, I'm not sure which date/time object we will use in Java,
    // and which one would be best for implementation purposes.
    // This should be easily be added anyways, if we would like to add it.

    /**
     * Creates a new task, based in the inputted <code>name</code> and
     * <code>description</code>.
     * 
     * @param name        The name of the task.
     * @param description The description of the task.
     * @param ID          The unique identifier for the task.
     */
    public Task(String name, String description, int ID) {
        this.name = name;
        this.description = description;
        this.ID = ID;
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

}