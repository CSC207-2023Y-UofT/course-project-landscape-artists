package Entities;

import java.util.ArrayList;
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
     * The <code>ArrayList</code> of tasks that the column holds/contains.
     */
    private ArrayList<Task> tasks;

    /**
     * A description for the column.
     * Note: We may not end up using this
     */
    private String description;

    /**
     * Contructor for a column, that takes in a name, an ArrayList of tasks,
     * and the description of the column.
     * 
     * @param name        The name for the column.
     * @param tasks       The ArrayList of tasks to be stored in the column.
     * @param description A description for the column.
     */
    public Column(String name, ArrayList<Task> tasks, String description) {
        this.name = name;
        this.tasks = tasks;
        this.description = description;
    }

    /**
     * Contructor for a column, that takes in a name, an ArrayList of tasks,
     * and the definees the description as an empty String.
     * 
     * Calls the {@link #Column(String name, ArrayList<Task> tasks, String
     * description)} method.
     * 
     * @param name  The name for the column
     * @param tasks The ArrayList of tasks to be stored in the column.
     */
    public Column(String name, ArrayList<Task> tasks) {
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
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Sets the entire list of tasks for the column.
     * 
     * @param newTasks The ArrayList<Task> of new tasks.
     */
    public void setTasks(ArrayList<Task> newTasks) {
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
        boolean task1InColumn = this.tasks.contains(task1);
        boolean task2InColumn = this.tasks.contains(task2);

        String exceptionMessage = "The following tasks are not in the column: ";

        if (!task1InColumn) {
            exceptionMessage += task1.toString();
        }
        if (!task2InColumn) {
            exceptionMessage += task2.toString();
        }

        if (!task1InColumn || !task2InColumn) {
            throw new NoSuchElementException(exceptionMessage);
        }

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
        String columnStringRepresentation = "[" + "Column Name: " + this.getName() + ", " + "Tasks: ";
        columnStringRepresentation += "{";
        for (Task task : this.tasks) {
            columnStringRepresentation += task.toString();
            columnStringRepresentation += ", ";
        }
        columnStringRepresentation = columnStringRepresentation.substring(
                0, columnStringRepresentation.length() - 2);
        // idk if this should be -3 or -2
        columnStringRepresentation += "}";
        columnStringRepresentation += "]";

        return columnStringRepresentation;
    }
}