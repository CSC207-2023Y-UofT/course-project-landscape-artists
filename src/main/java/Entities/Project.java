package Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * An entity class to represent a project, which includes a kanban board.
 */
public class Project {

    /**
     * The name of this project.
     */
    private String name;

    /**
     * A description of this project.
     */
    private String description;

    /**
     * The columns in the kanban board for this project.
     */
    private ArrayList<Column> columns;

    /**
     * Constructs a Project, given its name, columns, and description.
     * 
     * @param name        The project's name.
     * @param columns     The columns of the project.
     * @param description A description of the project.
     */
    public Project(String name, ArrayList<Column> columns, String description) {
        this.name = name;
        this.columns = columns;
        this.description = description;
    }

    /**
     * Constructs a Project, given its name and columns, and gives it an empty
     * description,
     * by calling {@link Project(String name, ArrayList<Column> columns, String
     * description)}.
     * 
     * @see Project(String name, ArrayList<Column> columns, String description)
     * @param name    The project's name.
     * @param columns The columns of the project.
     */
    public Project(String name, ArrayList<Column> columns) {
        this(name, columns, "");
    }

    /**
     * Gets the name of the project.
     * 
     * @return The name of the project.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the project to the inputted name.
     * 
     * @param newName The new name for the project.
     */
    public void setName(String newName) {
        this.name = newName;
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
     * Sets the description of the project to the inputted description.
     * 
     * @param newDescription The new description for the project.
     */
    public void setString(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Returns the columns of the project/kanban board.
     * 
     * @return an <code>ArrayList<Column></code> of <Column>s.
     */
    public ArrayList<Column> getColumns() {
        return this.columns;
    }

    /**
     * Sets the columns of the project to the inputted columns.
     * 
     * @param newColumns The new columns for the project.
     */
    public void setColumns(ArrayList<Column> newColumns) {
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
            throw new NoSuchElementException(
                    "The column " + columnToRemove.toString() + " is not in this project");
        }
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
        boolean col1InColumn = this.columns.contains(col1);
        boolean col2InColumn = this.columns.contains(col2);

        String exceptionMessage = "The following columns are not in the project: ";

        if (!col1InColumn) {
            exceptionMessage += col1.toString();
        }
        if (!col2InColumn) {
            exceptionMessage += col2.toString();
        }

        if (!col1InColumn || !col2InColumn) {
            throw new NoSuchElementException(exceptionMessage);
        }

        Collections.swap(
                this.columns, this.columns.indexOf(col1), this.columns.indexOf(col2));
    }

    /**
     * Moves a column to a specific position in the column.
     * 
     * @param columnToMove     The column that needs to be moved.
     * @param positionToMoveTo The position/index to move the column to.
     * @throws NoSuchElementException Throws exception when the specified column to
     *                                remove is not in the column.
     */
    public void moveColumnToPosition(Column columnToMove, int positionToMoveTo) throws NoSuchElementException {
        this.removeColumn(columnToMove);
        this.columns.add(positionToMoveTo, columnToMove);
    }

}
