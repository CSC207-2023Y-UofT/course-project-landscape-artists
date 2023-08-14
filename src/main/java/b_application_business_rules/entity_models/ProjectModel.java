package b_application_business_rules.entity_models;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;

import java.util.*;

/**
 * A project model within the productivity application.*
 * Each project model will have a name, unique identifier, a description, and a
 * list
 * of column models (which contain task models).
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
     * Returns a Project Entity from Project Model.*
     * {@inheritDoc}
     *
     * @return a Project Entity.
     */
    public Project getProjectEntity() {
        ArrayList<Column> columnEntities = new ArrayList<>();
        for (ColumnModel columnModel: columnModels) {
            System.out.println("columnModel " + columnModel);
            columnEntities.add(columnModel.getColumnEntity());
        }

        return new Project(name, ID, description, columnEntities);

    }



}
