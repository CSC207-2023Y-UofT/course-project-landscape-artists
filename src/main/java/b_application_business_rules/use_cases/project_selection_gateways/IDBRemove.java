package b_application_business_rules.use_cases.project_selection_gateways;

import java.util.UUID;

/**
 * This interface defines methods for removing data from a database, including ProjectModel, ColumnModel, and TaskModel objects.
 * It provides methods to remove various data elements from the database.
 */
public interface IDBRemove {

    /**
     * Removes a ProjectModel object from the database based on its UUID.
     *
     * @param uuid The UUID of the ProjectModel to remove from the database.
     */
    void DBRemoveProject(UUID uuid);

    /**
     * Removes a TaskModel object from the database based on its UUID.
     *
     * @param uuid The UUID of the TaskModel to remove from the database.
     */
    void DBRemoveTask(UUID uuid);

    /**
     * Removes a ColumnModel object from the database based on its UUID.
     *
     * @param uuid The UUID of the ColumnModel to remove from the database.
     */
    void DBRemoveColumn(UUID uuid);
}
