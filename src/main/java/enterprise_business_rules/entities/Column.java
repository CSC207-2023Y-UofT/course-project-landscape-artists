package enterprise_business_rules.entities;

import java.util.List;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * An entity class to represent a column of tasks within a kanban board.
 */
public class Column {

    /**
     * The name of the column.
     */
    private String name;

    /**
     * The <code>List</code> of tasks that the column holds/contains.
     */
    private List<Task> tasks;

    /**
     * A description for the column.
     * Note: We may not end up using this
     */
    private String description;

    /**
     * Contructor for a column, that takes in a name, an List of tasks,
     * and the description of the column.
     * 
     * @param name        The name for the column.
     * @param tasks       The List of tasks to be stored in the column.
     * @param description A description for the column.
     */
    public Column(String name, List<Task> tasks, String description) {
        this.name = name;
        this.tasks = tasks;
        this.description = description;
    }

    /**
     * Contructor for a column, that takes in a name, an List of tasks,
     * and the definees the description as an empty String.
     * 
     * Calls the {@link #Column(String name, List<Task> tasks, String
     * description)} method.
     * 
     * @param name  The name for the column
     * @param tasks The List of tasks to be stored in the column.
     */
    public Column(String name, List<Task> tasks) {
        this(name, tasks, "");
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
     * Sets a new name for the column
     * 
     * @param newName The new name for the column.
     */
    public void getName(String newName) {
        this.name = newName;
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
     * Gets the description of the column.
     * 
     * @return the description of the column.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the column.
     * 
     * @param newDescription the new description of the column.
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
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
            // indicating whether or not the object was removed.
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
        // Checking whether or not the tasks to swap are even in the column
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

        // Adding all of the column's task to the string representation
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
}