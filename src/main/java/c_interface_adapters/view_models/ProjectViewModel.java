package c_interface_adapters.view_models;

import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.ColumnModel;

import java.util.*;


/**
 * A project view model within the productivity application.
 * 
 * Each project view model will have a name, unique identifier, a description,
 * and a
 * list
 * of column view models (which contain task view models).
 */
public class ProjectViewModel {

    /**
     * The name of this project view model.
     */
    private String name;

    /**
     * 
     * The ID of this project view model.
     */
    private UUID ID;

    /**
     * 
     * A description of this project view model.
     */
    private String description;

    /**
     * The column view models in the kanban board for this project view model.
     */
    private List<ColumnViewModel> columnViewModels = new ArrayList<>();

    /**
     * Creates a new project view model, based in the inputted values.
     * 
     * @param name             The name of the task view models.
     * @param ID               The unique identifier for the task view models.
     * @param description      A description of the task view models.
     * @param columnViewModels The column view models of the project view model.
     */
    public ProjectViewModel(String name, UUID ID, String description, List<ColumnViewModel> columnViewModels) {
        this.name = name;
        this.columnViewModels = columnViewModels;
        this.description = description;
        this.ID = ID;
    }

    /**
     * Creates a new project view model, based on the inputted project.
     * 
     * @param projectModel The project to view model.
     */
    public ProjectViewModel(ProjectModel projectModel) {
        this.name = projectModel.getName();

        // Converting the List of Column objects to a List of ColumnViewModel objects
        List<ColumnModel> columnModels = projectModel.getColumnModels(); // Get the tasks
        // Converts Columns to ColumnViewModels and puts it in the columnViewModels
        // attribute
        if(!(columnModels==null || columnModels.isEmpty())){
            for (ColumnModel columnModel : columnModels) {
                this.addColumnViewModel(new ColumnViewModel(columnModel));
            }
        }


        this.description = projectModel.getDescription();
        this.ID = projectModel.getID();
    }

    /**
     * Gets the name of the project view model.
     * 
     * @return the name of the project view model.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the project view model.
     * 
     * @param newName the new name for the project view model.
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Gets the unique identifier for the project view model.
     * 
     * @return a unique identifier for the project view model.
     */
    public UUID getID() {
        return this.ID;
    }

    /**
     * Sets a new unique identifier for the project view model.
     * 
     * @param newID a new unique identifier for the project view model.
     */
    public void setID(UUID newID) {
        this.ID = newID;
    }

    /**
     * Gets the description of the project view model.
     * 
     * @return the description of the project view model.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the project view model.
     * 
     * @param newDescription the new description of the project view model.
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Returns the column view models of the project view model board.
     * 
     * @return an <code>List<ColumnViewModel></code> of <ColumnViewModel>s.
     */
    public List<ColumnViewModel> getColumnViewModels() {
        return this.columnViewModels;
    }

    /**
     * Sets the column view models of the project view model to the inputted column
     * view models.
     * 
     * @param newColumnViewModels The new column view models for the project view
     *                            model.
     */
    public void setColumnViewModels(List<ColumnViewModel> newColumnViewModels) {
        this.columnViewModels = newColumnViewModels;
    }

    /**
     * Adds a column view model to the project view model.
     * 
     * @param newColumnViewModel The column view model to add.
     */
    public void addColumnViewModel(ColumnViewModel newColumnViewModel) {
        this.columnViewModels.add(newColumnViewModel);
    }

    /**
     * Adds a column view model to the project view model, at a specific
     * position/index.
     * 
     * @param newColumnViewModel The column view model to add to the project view
     *                           model.
     * @param position           The position/index to add the column view model at.
     */
    public void addColumnViewModelToPosition(ColumnViewModel newColumnViewModel, int position) {
        this.columnViewModels.add(position, newColumnViewModel);
    }

    /**
     * Moves a column view model to a specific position in the column view model.
     *
     * @param columnViewModelToMove The column view model that needs to be moved.
     * @param positionToMoveTo      The position/index to move the column view model
     *                              to.
     * @throws NoSuchElementException Throws exception when the specified column
     *                                view model to
     *                                remove is not in the column view model.
     * @throws IllegalArgumentException Throws exception when the specified index is out
     *                                  of bounds.
     */
    public void moveColumnViewModelToPosition(ColumnViewModel columnViewModelToMove, int positionToMoveTo)
            throws NoSuchElementException, IllegalArgumentException {
        // The moving of the column view models is done by removing the object from the
        // List,
        // and then adding it back to the List at the indicated index.
        int columnToMoveIndex = this.columnViewModels.indexOf(columnViewModelToMove);
        int columnViewModelsNumber = this.columnViewModels.size();

        // Validity check
        if (columnViewModelToMove == null){
            throw new IllegalArgumentException("Column cannot be null.");
        }

        if (positionToMoveTo < 0||positionToMoveTo >= columnViewModelsNumber){
            throw new IllegalArgumentException("Invalid positionToMoveTo index. " +
                    "It must be between 0 and " +(columnViewModelsNumber - 1) + " inclusive.");
        }

        // Moving the column
        if (columnToMoveIndex != positionToMoveTo) {
            // If the column is already at the position, no need to move.

            // removes shifting columnViewModels to the right of columnToMove to the left i-1
            this.removeColumnViewModel(columnViewModelToMove);
            // adds column shifting columnViewModels to the right of columnToMove to the right i+1
            this.columnViewModels.add(positionToMoveTo, columnViewModelToMove);
        }
    }

    /**
     * Removes the specified column view model from the project view model.
     *
     * @param columnViewModelToRemove The column view model to remove from the
     *                                project view model.
     * @throws NoSuchElementException Throws exception when the specified column
     *                                view model to
     *                                remove is not in the project view model.
     */
    public void removeColumnViewModel(ColumnViewModel columnViewModelToRemove) throws NoSuchElementException {
        if (!this.columnViewModels.remove(columnViewModelToRemove)) {
            // the java.util.List.remove method returns a bool,
            // indicating whether the object was removed or not.
            // If it wasn't removed, we want to throw an exception,
            // saying that the task view models isn't in the column view model, thus, it
            // can't be
            // removed.
            // If it was removed, we don't have to do anything extra.
            throw new NoSuchElementException(
                    "The column view model " + columnViewModelToRemove.toString()
                            + " is not in this project view model");
        }
    }

    /**
     * Swaps the order of two column view models in the column view model.
     * 
     * @param col1 The first column view model.
     * @param col2 The second column view model.
     * @throws NoSuchElementException Throws this exception when one of the inputted
     *                                column view models are not in the column view
     *                                model.
     */
    public void swapColumnViewModelOrder(ColumnViewModel col1, ColumnViewModel col2) {
        // Checking whether the column view models to swap are in the project view model
        // or not
        boolean col1InColumnViewModel = this.columnViewModels.contains(col1);
        boolean col2InColumnViewModel = this.columnViewModels.contains(col2);

        // Creating the exception message
        String exceptionMessage = "The following column view models are not in the project view model: ";

        if (!col1InColumnViewModel) {
            exceptionMessage += col1.toString();
        }
        if (!col2InColumnViewModel) {
            exceptionMessage += col2.toString();
        }

        // Throws the exception if at least 1 of the task view modelss are missing,
        // using the exception message created above
        if (!col1InColumnViewModel || !col2InColumnViewModel) {
            throw new NoSuchElementException(exceptionMessage);
        }

        // do do do the swap (flop)
        Collections.swap(
                this.columnViewModels, this.columnViewModels.indexOf(col1), this.columnViewModels.indexOf(col2));
    }
}
