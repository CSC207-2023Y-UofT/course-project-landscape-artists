package b_application_business_rules.entity_models;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Task;

import java.util.*;

/**
 * A column model within the productivity application.
 * 
 * Each column model will have a name, a unique identifier, and a list of task
 * models.
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
     * The <code>List</code> of task models that the column model holds/contains.
     */
    private List<TaskModel> taskModels = new ArrayList<>();

    /**
     * Creates a new column model, based in the inputted values.
     * 
     * @param name       The name for the column model.
     * @param ID         The column model ID.
     * @param taskModels The List of task models to be stored in the column model.
     */
    public ColumnModel(String name, List<TaskModel> taskModels, UUID ID) {
        this.name = name;
        this.taskModels = taskModels;
        this.ID = ID;
    }

    /**
     * Creates a new column model, based on the inputted column.
     * 
     * @param column The column to model.
     */
    public ColumnModel(Column column) {
        this.name = column.getName();

        // Converting the List of Task objects to a List of TaskModel objects
        List<Task> tasks = column.getTasks(); // Get the tasks
        // Converts Tasks to TaskModels and puts it in the taskModels attribute
        for (int i = 0; i < tasks.size(); i++) {
            this.addTaskModel(new TaskModel(tasks.get(i)));
        }

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
     * Gets the task models in this column model.
     *
     * @return The task models in this column model.
     */
    public List<TaskModel> getTaskModels() {
        return this.taskModels;
    }

    /**
     * Sets the entire list of task models for the column model.
     * 
     * @param newTaskModels The List<TaskModel> of new task models.
     */
    public void setTaskModels(List<TaskModel> newTaskModels) {
        this.taskModels = newTaskModels;
    }

    /**
     * Adds a task model to the column model.
     * 
     * @param newTaskModel The task model to add.
     */
    public void addTaskModel(TaskModel newTaskModel) {
        this.taskModels.add(newTaskModel);
    }

    /**
     * Adds a task model to the column model, at a specific position/index.
     * 
     * @param newTaskModel The task model to add to the column model.
     * @param position     The position/index to add the task model at.
     */
    public void addTaskModelToPosition(TaskModel newTaskModel, int position) {
        this.taskModels.add(position, newTaskModel);
    }

    /**
     * Removes the specified task model from the column model.
     * 
     * @param taskModelToRemove The task model to remove from the column model.
     * @throws NoSuchElementException Throws exception when the specified task model
     *                                to
     *                                remove is not in the column model.
     */
    public void removeTaskModel(TaskModel taskModelToRemove) throws NoSuchElementException {
        // the java.util.List.remove method returns a bool,
        // indicating whether the object was removed or not.
        // If it wasn't removed, we want to throw an exception,
        // saying that the task model isn't in the column model, thus, it can't be
        // removed.
        // If it was removed, we don't have to do anything extra.
        if (!this.taskModels.remove(taskModelToRemove)) {
            throw new NoSuchElementException(
                    "The task model " + taskModelToRemove.toString() + " is not in this column model");
        }

    }

    /**
     * Swaps the order of two task models in the column model.
     * 
     * @param taskModel1 The first task model.
     * @param taskModel2 The second task model.
     * @throws NoSuchElementException Throws this exception when one of the inputted
     *                                task models are not in the column model.
     */
    public void swapTaskModelOrder(TaskModel taskModel1, TaskModel taskModel2) {
        // Checking whether the task models to swap are even in the column model or not
        boolean taskModel1InColumnModel = this.taskModels.contains(taskModel1);
        boolean taskModel2InColumnModel = this.taskModels.contains(taskModel2);

        // Creating the exception message
        String exceptionMessage = "The following task models are not in the column model: ";

        if (!taskModel1InColumnModel) {
            exceptionMessage += taskModel1.toString();
        }
        if (!taskModel2InColumnModel) {
            exceptionMessage += taskModel2.toString();
        }

        // Throws the exception if at least 1 of the task models are missing,
        // using the exception message created above
        if (!taskModel1InColumnModel || !taskModel2InColumnModel) {
            throw new NoSuchElementException(exceptionMessage);
        }

        // Does the swap
        Collections.swap(
                this.taskModels, this.taskModels.indexOf(taskModel1), this.taskModels.indexOf(taskModel2));
    }

    /**
     * Moves a task model to a specific position in the column model.
     * 
     * @param taskModelToMove  The task model that needs to be moved.
     * @param positionToMoveTo The position/index to move the task model to.
     * @throws NoSuchElementException Throws exception when the specified task model
     *                                to
     *                                remove is not in the column model.
     */
    public void moveTaskModelToPosition(TaskModel taskModelToMove, int positionToMoveTo) throws NoSuchElementException {
        // The moving of the task models is done by removing the object from the List,
        // and then adding it back to the List at the indicated index.
        this.removeTaskModel(taskModelToMove);
        this.taskModels.add(positionToMoveTo, taskModelToMove);
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
        String columnModelStringRepresentation = "[" + "ColumnModel Name: " + this.getName() + ", " + "TaskModels: ";
        columnModelStringRepresentation += "{";

        // Adding all the column model's task model to the string representation
        for (TaskModel taskModel : this.taskModels) {
            columnModelStringRepresentation += taskModel.toString();
            columnModelStringRepresentation += ", ";
        }
        columnModelStringRepresentation = columnModelStringRepresentation.substring(
                0, columnModelStringRepresentation.length() - 2); // removing the last ", "

        // Closing up the braces and brackets
        columnModelStringRepresentation += "}";
        columnModelStringRepresentation += "]";

        return columnModelStringRepresentation;
    }

    /**
     * Returns a Column Entity from Column Model.
     *
     * {@inheritDoc}
     *
     * @return a Column Entity.
     */
    public Column getColumnEntity() {
        List<Task> taskEntities = new ArrayList<>();
        for (TaskModel taskModel: taskModels) {
            taskEntities.add(taskModel.getTaskEntity());
        }

        return new Column(name, taskEntities, ID);
    }
}