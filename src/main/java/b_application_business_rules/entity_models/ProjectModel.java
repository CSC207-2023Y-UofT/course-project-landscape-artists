package b_application_business_rules.entity_models;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.use_cases.project_viewing_and_modification_use_cases.AddColumn;

import java.util.*;

/**
 * A project model within the productivity application.*
 * Each project model will have a name, unique identifier, a description, and a
 * list
 * of column models (which contain task modelss).
 */
public class ProjectModel {

    /**
     * The name of this project model.
     */
    private String name;

    /**
     * 
     * The ID of this project model.
     */
    private UUID ID;

    /**
     * 
     * A description of this project model.
     */
    private String description;

    /**
     * The column models in the kanban board for this project model.
     */
    private List<ColumnModel> columnModels = new ArrayList<>();

    /**
     * Creates a new project model, based in the inputted values.
     * 
     * @param name         The name of the project model.
     * @param ID           The unique identifier for the project model.
     * @param description  A description of the project model.
     * @param columnModels The column models of the project model.
     */
    public ProjectModel(String name, UUID ID, String description, List<ColumnModel> columnModels) {
        this.name = name;
        this.columnModels = columnModels;
        this.description = description;
        this.ID = ID;
    }

    /**
     * Creates a new project model, based on the inputted project.
     * 
     * @param project The project instance.
     */
    public ProjectModel(Project project) {
        this.name = project.getName();

        // Converting the List of Column objects to a List of ColumnModel objects
        List<Column> columns = project.getColumns(); // Get the columns
        // Converts Columns to ColumnModels and puts it in the columnModels attribute
        for (Column col : columns) {
            this.addColumnModel(new ColumnModel(col));
        }

        this.description = project.getDescription();
        this.ID = project.getID();
    }

    /**
     * Gets the name of the project model.
     * 
     * @return the name of the project model.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the project model.
     * 
     * @param newName the new name for the project model.
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Gets the unique identifier for the project model.
     * 
     * @return a unique identifier for the project model.
     */
    public UUID getID() {
        return this.ID;
    }

    /**
     * Sets a new unique identifier for the project model.
     * 
     * @param newID a new unique identifier for the project model.
     */
    public void setID(UUID newID) {
        this.ID = newID;
    }

    /**
     * Gets the description of the project model.
     * 
     * @return the description of the project model.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the project model.
     * 
     * @param newDescription the new description of the project model.
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Returns the column models of the project model board.
     * 
     * @return an <code>List<ColumnModel></code> of <ColumnModel>s.
     */
    public List<ColumnModel> getColumnModels() {
        return this.columnModels;
    }

    /**
     * Sets the column models of the project model to the inputted column models.
     * 
     * @param newColumnModels The new column models for the project model.
     */
    public void setColumnModels(List<ColumnModel> newColumnModels) {
        this.columnModels = newColumnModels;
    }

    /**
     * Adds a column model to the project model.
     * 
     * @param newColumnModel The column model to add.
     */
    public void addColumnModel(ColumnModel newColumnModel) {
        this.columnModels.add(newColumnModel);
    }

    /**
     * Adds a column model to the project model, at a specific position/index.
     * 
     * @param newColumnModel The column model to add to the project model.
     * @param position       The position/index to add the column model at.
     */
    public void addColumnModelToPosition(ColumnModel newColumnModel, int position) {
        this.columnModels.add(position, newColumnModel);
    }

    /**
     * Moves a column model to a specific position in the column model.
     *
     * @param columnModelToMove The column model that needs to be moved.
     * @param positionToMoveTo  The position/index to move the column model to.
     * @throws NoSuchElementException Throws exception when the specified column
     *                                model to
     *                                remove is not in the column model.
     * @throws IllegalArgumentException Throws exception when the specified index is out
     *      *                                  of bounds.
     */
    public void moveColumnModelToPosition(ColumnModel columnModelToMove, int positionToMoveTo)
            throws NoSuchElementException, IllegalArgumentException{
        // The moving of the column models is done by removing the object from the List,
        // and then adding it back to the List at the indicated index.
        int columnToMoveIndex = this.columnModels.indexOf(columnModelToMove);
        int columnModelsNumber = this.columnModels.size();

        // Validity check
        if (columnModelToMove == null){
            throw new IllegalArgumentException("Column cannot be null.");
        }

        if (positionToMoveTo < 0||positionToMoveTo >= columnModelsNumber){
            throw new IllegalArgumentException("Invalid positionToMoveTo index. " +
                    "It must be between 0 and " +(columnModelsNumber - 1) + " inclusive.");
        }

        // Moving the column
        if (columnToMoveIndex != positionToMoveTo) {
            // If the column is already at the position, no need to move.

            // removes shifting columns to the right of columnToMove to the left i-1
            this.removeColumnModel(columnModelToMove);
            // adds column shifting columns to the right of columnToMove to the right i+1
            this.columnModels.add(positionToMoveTo, columnModelToMove);
        }
    }

    /**
     * Removes the specified column model from the project model.
     *
     * @param columnModelToRemove The column model to remove from the project model.
     * @throws NoSuchElementException Throws exception when the specified column
     *                                model to
     *                                remove is not in the project model.
     */
    public void removeColumnModel(ColumnModel columnModelToRemove) throws NoSuchElementException {
        if (!this.columnModels.remove(columnModelToRemove)) {
            // the java.util.List.remove method returns a bool,
            // indicating whether the object was removed or not.
            // If it wasn't removed, we want to throw an exception,
            // saying that the task models isn't in the column model, thus, it can't be
            // removed.
            // If it was removed, we don't have to do anything extra.
            throw new NoSuchElementException(
                    "The column model " + columnModelToRemove.toString() + " is not in this project model");
        }
    }

    /**
     * Swaps the order of two column models in the column model.
     * 
     * @param col1 The first column model.
     * @param col2 The second column model.
     * @throws NoSuchElementException Throws this exception when one of the inputted
     *                                column models are not in the column model.
     */
    public void swapColumnModelOrder(ColumnModel col1, ColumnModel col2) {
        // Checking whether the column models to swap are in the project model or not
        boolean col1InColumnModel = this.columnModels.contains(col1);
        boolean col2InColumnModel = this.columnModels.contains(col2);

        // Creating the exception message
        String exceptionMessage = "The following column models are not in the project model: ";

        if (!col1InColumnModel) {
            exceptionMessage += col1.toString();
        }
        if (!col2InColumnModel) {
            exceptionMessage += col2.toString();
        }

        // Throws the exception if at least 1 of the task modelss are missing,
        // using the exception message created above
        if (!col1InColumnModel || !col2InColumnModel) {
            throw new NoSuchElementException(exceptionMessage);
        }

        // do do do the swap (flop)
        Collections.swap(
                this.columnModels, this.columnModels.indexOf(col1), this.columnModels.indexOf(col2));
    }

    /**
     * Returns a Project Entity from Project Model.
     *
     * {@inheritDoc}
     *
     * @return a Project Entity.
     */
    public Project getProjectEntity() {
        ArrayList<Column> columnEntities = new ArrayList<>();
        for (ColumnModel columnModel: columnModels) {
            columnEntities.add(columnModel.getColumnEntity());
        }

        return new Project(name, ID, description, columnEntities);

    }



}
