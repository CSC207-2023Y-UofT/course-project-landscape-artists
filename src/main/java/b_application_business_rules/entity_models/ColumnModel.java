package b_application_business_rules.entity_models;

import java.util.*;

import a_enterprise_business_rules.entities.*;

/**
 * A column model within the productivity application.
 * 
 * Each column model will have a name, a unique identifier, and a list of tasks.
 */
public class ColumnModel {

    /**
     * The name of the column model.
     */
    private String name;

    /**
     * A unique identifier for the column model.
     */
    private UUID ID;

    /**
     * The <code>List</code> of tasks that the column model holds/contains.
     */
    private List<Task> tasks;

    /**
     * Creates a new column model, based in the inputted values.
     * 
     * @param name  The name for the column model.
     * @param ID    The column model ID.
     * @param tasks The List of tasks to be stored in the column model.
     */
    public ColumnModel(String name, List<Task> tasks, UUID ID) {
        this.name = name;
        this.tasks = tasks;
        this.ID = ID;
    }

    /**
     * Creates a new column model, based on the inputted column.
     * 
     * @param column The column to model.
     */
    public ColumnModel(Column column) {
        this.name = column.getName();
        this.tasks = column.getTasks();
        this.ID = column.getID();
    }

    /**
     * Gets the name of the column model.
     * 
     * @return The name of the column model.
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * Sets a new name for the column model
     * 
     * @param newName The new name for the column model.
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Gets the unique identifier for the column model.
     * 
     * @return a unique identifier for the column model.
     */
    public UUID getID() {
        return this.ID;
    }

    /**
     * Sets a new unique identifier for the column model.
     * 
     * @param newID a new unique identifier for the column model.
     */
    public void setID(UUID newID) {
        this.ID = newID;
    }

    /**
     * Gets the tasks in this column model.
     * 
     * @return The tasks in this column model.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Sets the entire list of tasks for the column model.
     * 
     * @param newTasks The List<Task> of new tasks.
     */
    public void setTasks(List<Task> newTasks) {
        this.tasks = newTasks;
    }

    /**
     * Adds a task to the column model.
     * 
     * @param newTask The task to add.
     */
    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    /**
     * Adds a task to the column model, at a specific position/index.
     * 
     * @param newTask  The task to add to the column model.
     * @param position The position/index to add the task at.
     */
    public void addTaskToPosition(Task newTask, int position) {
        this.tasks.add(position, newTask);
    }

    /**
     * Removes the specified task from the column model.
     * 
     * @param taskToRemove The task to remove from the column model.
     * @throws NoSuchElementException Throws exception when the specified task to
     *                                remove is not in the column model.
     */
    public void removeTask(Task taskToRemove) throws NoSuchElementException {
        if (!this.tasks.remove(taskToRemove)) {
            // the java.util.List.remove method returns a bool,
            // indicating whether the object was removed or not.
            // If it wasn't removed, we want to throw an exception,
            // saying that the task isn't in the column model, thus, it can't be removed.
            // If it was removed, we don't have to do anything extra.
            throw new NoSuchElementException(
                    "The task " + taskToRemove.toString() + " is not in this column model");
        }
    }

    /**
     * Swaps the order of two tasks in the column model.
     * 
     * @param task1 The first task.
     * @param task2 The second task.
     * @throws NoSuchElementException Throws this exception when one of the inputted
     *                                tasks are not in the column model.
     */
    public void swapTaskOrder(Task task1, Task task2) {
        // Checking whether the tasks to swap are even in the column model or not
        boolean task1InColumnModel = this.tasks.contains(task1);
        boolean task2InColumnModel = this.tasks.contains(task2);

        // Creating the exception message
        String exceptionMessage = "The following tasks are not in the column model: ";

        if (!task1InColumnModel) {
            exceptionMessage += task1.toString();
        }
        if (!task2InColumnModel) {
            exceptionMessage += task2.toString();
        }

        // Throws the exception if at least 1 of the tasks are missing,
        // using the exception message created above
        if (!task1InColumnModel || !task2InColumnModel) {
            throw new NoSuchElementException(exceptionMessage);
        }

        // Does the swap
        Collections.swap(
                this.tasks, this.tasks.indexOf(task1), this.tasks.indexOf(task2));
    }

    /**
     * Moves a task to a specific position in the column model.
     * 
     * @param taskToMove       The task that needs to be moved.
     * @param positionToMoveTo The position/index to move the task to.
     * @throws NoSuchElementException Throws exception when the specified task to
     *                                remove is not in the column model.
     */
    public void moveTaskToPosition(Task taskToMove, int positionToMoveTo) throws NoSuchElementException {
        // The moving of the tasks is done by removing the object from the List,
        // and then adding it back to the List at the indicated index.
        this.removeTask(taskToMove);
        this.tasks.add(positionToMoveTo, taskToMove);
    }

    /**
     * Returns a String representation of the ColumnModel.
     * 
     * {@inheritDoc}
     * 
     * @return a String representation of the ColumnModel.
     */
    @Override
    public String toString() {
        // Starts constructing the string representation of the column model
        String columnModelStringRepresentation = "[" + "ColumnModel Name: " + this.getName() + ", " + "Tasks: ";
        columnModelStringRepresentation += "{";

        // Adding all the column model's task to the string representation
        for (Task task : this.tasks) {
            columnModelStringRepresentation += task.toString();
            columnModelStringRepresentation += ", ";
        }
        columnModelStringRepresentation = columnModelStringRepresentation.substring(
                0, columnModelStringRepresentation.length() - 2); // removing the last ", "

        // Closing up the braces and brackets
        columnModelStringRepresentation += "}";
        columnModelStringRepresentation += "]";

        return columnModelStringRepresentation;
    }

    // TODO:turn this into its own class
    // private UUID getValidColumnID(){
    // this.ID = UUID.randomUUID();
    // DBManagerInsertController dbManagerInsertController = new
    // DBManagerInsertController();
    // while(uuidMap.containsKey(this.ID.toString())){
    // this.ID = UUID.randomUUID();
    // }
    // dbManagerInsertController.DBInsert(this.ID);
    // return this.ID;
    // }

}