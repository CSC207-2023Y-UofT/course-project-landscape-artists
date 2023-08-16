
package a_enterprise_business_rules.entities;

import java.util.*;

/**
 * A project within the productivity application.
 * <p>
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
     * Moves a column to a specific position in the columns of the project .
     *
     * @param columnToMove     The column that needs to be moved.
     * @param positionToMoveTo The position/index to move the column to.
     * @throws NoSuchElementException   Throws exception when the specified column
     *                                  to
     *                                  remove is not in the columns of the project.
     * @throws IllegalArgumentException Throws exception when the specified index is
     *                                  out
     *                                  of bounds.
     */
    public void moveColumnToPosition(Column columnToMove, int positionToMoveTo)
            throws NoSuchElementException, IllegalArgumentException {
        // The moving of the columns is done by removing the object from the List
        // and then adding it back at the indicated index
        int columnToMoveIndex = this.columns.indexOf(columnToMove);
        int columnsNumber = this.columns.size();

        // Validity check
        if (columnToMove == null) {
            throw new IllegalArgumentException("Column cannot be null.");
        }

        if (positionToMoveTo < 0 || positionToMoveTo >= columnsNumber) {
            throw new IllegalArgumentException("Invalid positionToMoveTo index. " +
                    "It must be between 0 and " + (columnsNumber - 1) + " inclusive.");
        }

        // Moving the column
        if (columnToMoveIndex != positionToMoveTo) {
            // If the column is already at the position, no need to move.

            // removes shifting columns to the right of columnToMove to the left i-1
            this.removeColumn(columnToMove);
            // adds column shifting columns to the right of columnToMove to the right i+1
            this.columns.add(positionToMoveTo, columnToMove);
        }
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
     * Swaps the order of two columns in the column.
     * 
     * @param col1 The first column.
     * @param col2 The second column.
     * @throws NoSuchElementException Throws this exception when one of the inputted
     *                                columns are not in the column.
     */
    public void swapColumnOrder(Column col1, Column col2) {
        // Checking whether the columns to swap are in the project or not
        boolean col1InColumn = this.columns.contains(col1);
        boolean col2InColumn = this.columns.contains(col2);

        // Creating the exception message
        String exceptionMessage = "The following columns are not in the project: ";

        if (!col1InColumn) {
            exceptionMessage += col1.toString();
        }
        if (!col2InColumn) {
            exceptionMessage += col2.toString();
        }

        // Throws the exception if at least 1 of the tasks are missing,
        // using the exception message created above
        if (!col1InColumn || !col2InColumn) {
            throw new NoSuchElementException(exceptionMessage);
        }

        // do do do the swap (flop)
        Collections.swap(
                this.columns, this.columns.indexOf(col1), this.columns.indexOf(col2));
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
