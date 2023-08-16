package b_application_business_rules.use_cases.project_selection_gateways;

import b_application_business_rules.entity_models.*;

import java.util.*;

/**
 * This interface defines methods for searching data in a database, including ColumnModel, ProjectModel, and TaskModel objects.
 * It provides methods to search for specific data elements based on their IDs.
 */
public interface IDBSearch {

    /**
     * Searches the database for columns based on the provided ID.
     *
     * @param id The ID to search for in the database.
     * @return An ArrayList of strings containing search results for columns.
     */
    ArrayList<String> DBColumnSearch(String id);

    /**
     * Searches the database for projects based on the provided ID.
     *
     * @param id The ID to search for in the database.
     * @return An ArrayList of strings containing search results for projects.
     */
    ArrayList<String> DBProjectSearch(String id);

    /**
     * Searches the database for tasks based on the provided ID.
     *
     * @param id The ID to search for in the database.
     * @return An ArrayList of strings containing search results for tasks.
     */
    ArrayList<String> DBTaskSearch(String id);
}
