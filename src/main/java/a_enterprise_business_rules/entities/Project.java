
package a_enterprise_business_rules.entities;

import java.util.*;

/**
 * A project within the productivity application.*
 * Each project will have a name, unique identifier, a description, and a list
 * of columns (which contain tasks).
 */
public class Project {

    /**
     * The name of this project.
     */
    private String name;

    /**
     * 
     * The ID of this project.
     */
    private UUID ID;

    /**
     * 
     * A description of this project.
     */
    private String description;

    /**
     * The columns in the kanban board for this project.
     */
    private List<Column> columns;

    /**
     * Creates a new project, based in the inputted values.
     * 
     * @param name        The name of the task.
     * @param ID          The unique identifier for the task.
     * @param description A description of the task.
     * @param columns     The columns of the project.
     */
    public Project(String name, UUID ID, String description, List<Column> columns) {
        this.name = name;
        this.columns = columns;
        this.description = description;
        this.ID = ID;
    }

    /**
     * Gets the name of the project.
     * 
     * @return the name of the project.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the project.
     * 
     * @param newName the new name for the project.
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Gets the unique identifier for the project.
     * 
     * @return a unique identifier for the project.
     */
    public UUID getID() {
        return this.ID;
    }

    /**
     * Sets a new unique identifier for the project.
     * 
     * @param newID a new unique identifier for the project.
     */
    public void setID(UUID newID) {
        this.ID = newID;
    }

    /**
     * Gets the description of the project.
     * 
     * @return the description of the project.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the project.
     * 
     * @param newDescription the new description of the project.
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Returns the columns of the project board.
     * 
     * @return an <code>List<Column></code> of <Column>s.
     */
    public List<Column> getColumns() {
        return this.columns;
    }

    /**
     * Sets the columns of the project to the inputted columns.
     * 
     * @param newColumns The new columns for the project.
     */
    public void setColumns(List<Column> newColumns) {
        this.columns = newColumns;
    }

    /**
     * Adds a column to the project.
     * 
     * @param newColumn The column to add.
     */
    public void addColumn(Column newColumn) {
        this.columns.add(newColumn);
    }

    /**
     * Adds a column to the project, at a specific position/index.
     * 
     * @param newColumn The column to add to the project.
     * @param position  The position/index to add the column at.
     */
    public void addColumnToPosition(Column newColumn, int position) {
        this.columns.add(position, newColumn);
    }

    /**
     * Removes the specified column from the project.
     *
     * @param columnToRemove The column to remove from the project.
     * @throws NoSuchElementException Throws exception when the specified column to
     *                                remove is not in the project.
     */
    public void removeColumn(Column columnToRemove) throws NoSuchElementException {
        if (!this.columns.remove(columnToRemove)) {
            // the java.util.List.remove method returns a bool,
            // indicating whether the object was removed or not.
            // If it wasn't removed, we want to throw an exception,
            // saying that the task isn't in the column, thus, it can't be removed.
            // If it was removed, we don't have to do anything extra.
            throw new NoSuchElementException(
                    "The column " + columnToRemove.toString() + " is not in this project");
        }
    }

    /**
     * Removes a column with the specified ID from the list of columns in the
     * current project.
     *
     * @param idOfColumnToRemove The ID of the column to be removed.
     * @throws NoSuchElementException If no column with the given ID is found in the
     *                                project.
     */
    public void removeColumn(UUID idOfColumnToRemove) throws NoSuchElementException {
        for (Column column : columns) {
            if (column.getID().equals(idOfColumnToRemove)) {
                columns.remove(column);
                return;
            }
        }
        throw new NoSuchElementException(
                "The column with ID " + idOfColumnToRemove + " is not in this project");
    }

    /**
     * Returns whether this Project and another object are equal.
     * 
     * @param o The object to compare to.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Project p)) {
            return false;
        }
        // Checking the equality of each of the attributes

        return p.getName().equals(this.getName()) &&
                p.getID().equals(this.getID()) &&
                p.getDescription().equals(this.getDescription()) &&
                p.getColumns().equals(this.getColumns());
    }
}
