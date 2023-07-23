package Entities;

import DBControllers.DBManagerInsertController;
import d_frameworks_and_drivers.database_management.DatabaseFiles.UUIDsToHashMap.UUIDMap;

import java.util.*;

/**
 * An entity class to represent a project, which includes a kanban board.
 */
public class Project {
    /**
     * The name of this project.
     */
    private String name;

    /**
     * The ID of this project.
     */
    private UUID ID;

    /**
     * A description of this project.
     */
    private String description;

    /**
     * The columns in the kanban board for this project.
     */
    private List<Column> columns;

    /**
     * UUID Map
     */
    private Map<String, String> uuidMap = UUIDMap.convertCsvToHashMap();
    /**
     * Constructs a Project, given its name, columns, and description.
     * 
     * @param name        The project's name.
     * @param columns     The columns of the project.
     * @param description A description of the project.
     */
    public Project(String name, List<Column> columns, String description) {
        this.ID = getValidProjectID();
        this.name = name;
        this.columns = columns;
        this.description = description;
        DBManagerInsertController dbManagerInsertController = new DBManagerInsertController();
        dbManagerInsertController.DBInsert(this);
    }

    /**
     * Constructs a Project, given its name and columns, and gives it an empty
     * description,
     * by calling {@link Project(String name, List<Column> columns, String
     * description)}.
     * 
     * @see Project(String name, List<Column> columns, String description)
     * @param name    The project's name.
     * @param columns The columns of the project.
     */
    public Project(String name, List<Column> columns) {
        this(name, columns, "");
        this.ID = getValidProjectID();
        DBManagerInsertController dbManagerInsertController = new DBManagerInsertController();
        dbManagerInsertController.DBInsert(this);
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
     * Gets the ID of the project.
     *
     * @return The ID of the project.
     */
    public UUID getProjectID() {
        return this.ID;
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
            // indicating whether or not the object was removed.
            // If it wasn't removed, we want to throw an exception,
            // saying that the task isn't in the column, thus, it can't be removed.
            // If it was removed, we don't have to do anything extra.
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
        // Checking whether or not the columns to swap are even in the prject
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
     * Moves a column to a specific position in the column.
     * 
     * @param columnToMove     The column that needs to be moved.
     * @param positionToMoveTo The position/index to move the column to.
     * @throws NoSuchElementException Throws exception when the specified column to
     *                                remove is not in the column.
     */
    public void moveColumnToPosition(Column columnToMove, int positionToMoveTo) throws NoSuchElementException {
        // The moving of the columns is done by removing the object from the List,
        // and then adding it back to the List at the indicated index.
        this.removeColumn(columnToMove);
        this.columns.add(positionToMoveTo, columnToMove);
    }

    private UUID getValidProjectID(){
        this.ID = UUID.randomUUID();
        DBManagerInsertController dbManagerInsertController = new DBManagerInsertController();
        while(uuidMap.containsKey(this.ID.toString())){
            this.ID = UUID.randomUUID();
        }
        dbManagerInsertController.DBInsert(this.ID);
        return this.ID;
    }

}
