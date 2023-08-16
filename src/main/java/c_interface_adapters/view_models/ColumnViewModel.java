package c_interface_adapters.view_models;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.TaskModel;

import java.util.*;

/**
 * A column view model within the productivity application.
 * 
 * Each column view model will have a name, a unique identifier, and a list of
 * task
 * view models.
 */
public class ColumnViewModel {

    /**
     * The name of the column view model.
     */
    private String name;

    /**
     * A unique identifier for the column view model.
     */
    private UUID ID;

    /**
     * The <code>List</code> of task view models that the column view model
     * holds/contains.
     */
    private List<TaskViewModel> taskViewModels = new ArrayList<>();

    /**
     * Creates a new column view model, based in the inputted values.
     * 
     * @param name           The name for the column view model.
     * @param ID             The column view model ID.
     * @param taskViewModels The List of task view models to be stored in the column
     *                       view model.
     */
    public ColumnViewModel(String name, List<TaskViewModel> taskViewModels, UUID ID) {
        this.name = name;
        this.taskViewModels = taskViewModels;
        this.ID = ID;
    }

    /**
     * Creates a new column view model, based on the inputted column.
     * 
     * @param columnModel The column to view model.
     */
    public ColumnViewModel(ColumnModel columnModel) {
        this.name = columnModel.getName();

        // Converting the List of Task objects to a List of TaskViewModel objects
        List<TaskModel> taskModels = columnModel.getTaskModels(); // Get the tasks
        // Converts Tasks to TaskViewModels and puts it in the taskViewModels attribute
        for (int i = 0; i < taskModels.size(); i++) {
            this.taskViewModels.add(
                    new TaskViewModel(taskModels.get(i)));
        }

        this.ID = columnModel.getID();
    }

    /**
     * Gets the name of the column view model.
     * 
     * @return The name of the column view model.
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * Sets a new name for the column view model
     * 
     * @param newName The new name for the column view model.
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Gets the unique identifier for the column view model.
     * 
     * @return a unique identifier for the column view model.
     */
    public UUID getID() {
        return this.ID;
    }

    /**
     * Sets a new unique identifier for the column view model.
     * 
     * @param newID a new unique identifier for the column view model.
     */
    public void setID(UUID newID) {
        this.ID = newID;
    }

    /**
     * Gets the task view models in this column view model.
     * 
     * @return The task view models in this column view model.
     */
    public List<TaskViewModel> getTaskViewModels() {
        return this.taskViewModels;
    }

    /**
     * Sets the entire list of task view models for the column view model.
     * 
     * @param newTaskViewModels The List<TaskViewModel> of new task view models.
     */
    public void setTaskViewModels(List<TaskViewModel> newTaskViewModels) {
        this.taskViewModels = newTaskViewModels;
    }

    /**
     * Adds a task view model to the column view model.
     * 
     * @param newTaskViewModel The task view model to add.
     */
    public void addTaskViewModel(TaskViewModel newTaskViewModel) {
        this.taskViewModels.add(newTaskViewModel);
    }

    /**
     * Adds a task view model to the column view model, at a specific
     * position/index.
     * 
     * @param newTaskViewModel The task view model to add to the column view model.
     * @param position         The position/index to add the task view model at.
     */
    public void addTaskViewModelToPosition(TaskViewModel newTaskViewModel, int position) {
        this.taskViewModels.add(position, newTaskViewModel);
    }

    /**
     * Removes the specified task view model from the column view model.
     * 
     * @param taskViewModelToRemove The task view model to remove from the column
     *                              view model.
     * @throws NoSuchElementException Throws exception when the specified task view
     *                                model
     *                                to
     *                                remove is not in the column view model.
     */
    public void removeTaskViewModel(TaskViewModel taskViewModelToRemove) throws NoSuchElementException {
        if (!this.taskViewModels.remove(taskViewModelToRemove)) {
            // the java.util.List.remove method returns a bool,
            // indicating whether the object was removed or not.
            // If it wasn't removed, we want to throw an exception,
            // saying that the task view model isn't in the column view model, thus, it
            // can't be
            // removed.
            // If it was removed, we don't have to do anything extra.
            throw new NoSuchElementException(
                    "The task view model " + taskViewModelToRemove.toString() + " is not in this column view model");
        }
    }

    /**
     * Swaps the order of two task view models in the column view model.
     * 
     * @param taskViewModel1 The first task view model.
     * @param taskViewModel2 The second task view model.
     * @throws NoSuchElementException Throws this exception when one of the inputted
     *                                task view models are not in the column view
     *                                model.
     */
    public void swapTaskViewModelOrder(TaskViewModel taskViewModel1, TaskViewModel taskViewModel2) {
        // Checking whether the task view models to swap are even in the column view
        // model or not
        boolean taskViewModel1InColumnViewModel = this.taskViewModels.contains(taskViewModel1);
        boolean taskViewModel2InColumnViewModel = this.taskViewModels.contains(taskViewModel2);

        // Creating the exception message
        String exceptionMessage = "The following task view models are not in the column view model: ";

        if (!taskViewModel1InColumnViewModel) {
            exceptionMessage += taskViewModel1.toString();
        }
        if (!taskViewModel2InColumnViewModel) {
            exceptionMessage += taskViewModel2.toString();
        }

        // Throws the exception if at least 1 of the task view models are missing,
        // using the exception message created above
        if (!taskViewModel1InColumnViewModel || !taskViewModel2InColumnViewModel) {
            throw new NoSuchElementException(exceptionMessage);
        }

        // Does the swap
        Collections.swap(
                this.taskViewModels, this.taskViewModels.indexOf(taskViewModel1),
                this.taskViewModels.indexOf(taskViewModel2));
    }

    /**
     * Moves a task view model to a specific position in the column view model.
     * 
     * @param taskViewModelToMove The task view model that needs to be moved.
     * @param positionToMoveTo    The position/index to move the task view model to.
     * @throws NoSuchElementException Throws exception when the specified task view
     *                                model
     *                                to
     *                                remove is not in the column view model.
     */
    public void moveTaskViewModelToPosition(TaskViewModel taskViewModelToMove, int positionToMoveTo)
            throws NoSuchElementException {
        // The moving of the task view models is done by removing the object from the
        // List,
        // and then adding it back to the List at the indicated index.
        this.removeTaskViewModel(taskViewModelToMove);
        this.taskViewModels.add(positionToMoveTo, taskViewModelToMove);
    }

    /**
     * Returns a String representation of the ColumnViewModel.
     * 
     * {@inheritDoc}
     * 
     * @return a String representation of the ColumnViewModel.
     */
    @Override
    public String toString() {
        // Starts constructing the string representation of the column view model
        String columnViewModelStringRepresentation = "[" + "ColumnViewModel Name: " + this.getName() + ", "
                + "TaskViewModels: ";
        columnViewModelStringRepresentation += "{";

        // Adding all the column view model's task view model to the string
        // representation
        for (TaskViewModel taskViewModel : this.taskViewModels) {
            columnViewModelStringRepresentation += taskViewModel.toString();
            columnViewModelStringRepresentation += ", ";
        }
        columnViewModelStringRepresentation = columnViewModelStringRepresentation.substring(
                0, columnViewModelStringRepresentation.length() - 2); // removing the last ", "

        // Closing up the braces and brackets
        columnViewModelStringRepresentation += "}";
        columnViewModelStringRepresentation += "]";

        return columnViewModelStringRepresentation;
    }
}