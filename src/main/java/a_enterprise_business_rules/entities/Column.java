package a_enterprise_business_rules.entities;

import java.util.*;

/**
 * A column within the productivity application.
 * 
 * Each column will have a name, a unique identifier, and a list of tasks.
 */
public class Column {

    /**
     * The name of the column.
     */
    private String name;

    /**
     * A unique identifier for the column.
     */
    private UUID ID;

    /**
     * The <code>List</code> of tasks that the column holds/contains.
     */
    private List<Task> tasks;

    /**
     * Creates a new column, based in the inputted values.
     * 
     * @param name  The name for the column.
     * @param ID    The column ID.
     * @param tasks The List of tasks to be stored in the column.
     */
    public Column(String name, List<Task> tasks, UUID ID) {
        this.name = name;
        this.tasks = tasks;
        this.ID = ID;
    }

    /**
     * Gets the name of the column.
     * 
     * @return The name of the column.
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * Sets a new name for the column
     * 
     * @param newName The new name for the column.
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Gets the unique identifier for the column.
     * 
     * @return a unique identifier for the column.
     */
    public UUID getID() {
        return this.ID;
    }

    /**
     * Sets a new unique identifier for the column.
     * 
     * @param newID a new unique identifier for the column.
     */
    public void setID(UUID newID) {
        this.ID = newID;
    }

    /**
     * Gets the tasks in this column.
     * 
     * @return The tasks in this column.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Sets the entire list of tasks for the column.
     * 
     * @param newTasks The List<Task> of new tasks.
     */
    public void setTasks(List<Task> newTasks) {
        this.tasks = newTasks;
    }

    /**
     * Adds a task to the column.
     * 
     * @param newTask The task to add.
     */
    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    /**
     * Adds a task to the column, at a specific position/index.
     * 
     * @param newTask  The task to add to the column.
     * @param position The position/index to add the task at.
     */
    public void addTaskToPosition(Task newTask, int position) {
        this.tasks.add(position, newTask);
    }

    /**
     * Removes the specified task from the column.
     * 
     * @param taskToRemove The task to remove from the column.
     * @throws NoSuchElementException Throws exception when the specified task to
     *                                remove is not in the column.
     */
    public void removeTask(Task taskToRemove) throws NoSuchElementException {
        if (!this.tasks.remove(taskToRemove)) {
            // the java.util.List.remove method returns a bool,
            // indicating whether the object was removed or not.
            // If it wasn't removed, we want to throw an exception,
            // saying that the task isn't in the column, thus, it can't be removed.
            // If it was removed, we don't have to do anything extra.
            throw new NoSuchElementException(
                    "The task " + taskToRemove.toString() + " is not in this column");
        }
    }

    /**
     * Swaps the order of two tasks in the column.
     * 
     * @param task1 The first task.
     * @param task2 The second task.
     * @throws NoSuchElementException Throws this exception when one of the inputted
     *                                tasks are not in the column.
     */
    public void swapTaskOrder(Task task1, Task task2) {
        // Checking whether the tasks to swap are even in the column or not
        boolean task1InColumn = this.tasks.contains(task1);
        boolean task2InColumn = this.tasks.contains(task2);

        // Creating the exception message
        String exceptionMessage = "The following tasks are not in the column: ";

        if (!task1InColumn) {
            exceptionMessage += task1.toString();
        }
        if (!task2InColumn) {
            exceptionMessage += task2.toString();
        }

        // Throws the exception if at least 1 of the tasks are missing,
        // using the exception message created above
        if (!task1InColumn || !task2InColumn) {
            throw new NoSuchElementException(exceptionMessage);
        }

        // Does the swap
        Collections.swap(
                this.tasks, this.tasks.indexOf(task1), this.tasks.indexOf(task2));
    }

    /**
     * Moves a task to a specific position in the column.
     * 
     * @param taskToMove       The task that needs to be moved.
     * @param positionToMoveTo The position/index to move the task to.
     * @throws NoSuchElementException Throws exception when the specified task to
     *                                remove is not in the column.
     */
    public void moveTaskToPosition(Task taskToMove, int positionToMoveTo) throws NoSuchElementException {
        // The moving of the tasks is done by removing the object from the List,
        // and then adding it back to the List at the indicated index.
        this.removeTask(taskToMove);
        this.tasks.add(positionToMoveTo, taskToMove);
    }

    /**
     * Returns a String representation of the Column.
     * 
     * {@inheritDoc}
     * 
     * @return a String representation of the Column.
     */
    @Override
    public String toString() {
        // Starts constructing the string representation of the column
        String columnStringRepresentation = "[" + "Column Name: " + this.getName() + ", " + "Tasks: ";
        columnStringRepresentation += "{";

        // Adding all the column's task to the string representation
        for (Task task : this.tasks) {
            columnStringRepresentation += task.toString();
            columnStringRepresentation += ", ";
        }
        columnStringRepresentation = columnStringRepresentation.substring(
                0, columnStringRepresentation.length() - 2); // removing the last ", "

        // Closing up the braces and brackets
        columnStringRepresentation += "}";
        columnStringRepresentation += "]";

        return columnStringRepresentation;
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

    /**
     * Returns whether or not this Column and another object are equal.
     * 
     * @param o The object to compare to.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Column)) {
            return false;
        }
        Column c = (Column) o;
        // Checking the equality of each of the attributes
        boolean allAttributesAreEqual = c.getName().equals(this.getName()) &&
                c.getID().equals(this.getID()) &&
                c.getTasks().equals(this.getTasks());

        return allAttributesAreEqual;
    }

}