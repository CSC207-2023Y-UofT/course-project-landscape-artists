package b_application_business_rules.use_cases.project_selection_gateways;

import b_application_business_rules.entity_models.*;

import java.util.UUID;

/**
 * This interface defines methods for inserting data into a database, including ProjectModel, ColumnModel, and TaskModel objects.
 * It provides methods to insert various data elements and their relationships into the database.
 */
public interface IDBInsert {

    /**
     * Inserts a ProjectModel object into the database.
     *
     * @param projectModel The ProjectModel object to insert into the database.
     */
    void DBInsert(ProjectModel projectModel);

    /**
     * Inserts a ColumnModel object into the database.
     *
     * @param columnModel The ColumnModel object to insert into the database.
     */
    void DBInsert(ColumnModel columnModel);

    /**
     * Inserts a UUID into the database.
     *
     * @param uuid The UUID to insert into the database.
     */
    void DBInsert(UUID uuid);

    /**
     * Inserts a TaskModel object into the database and associates it with a parent UUID.
     *
     * @param taskModel The TaskModel object to insert into the database.
     * @param parent    The UUID of the parent object to associate the task with.
     */
    void DBInsert(TaskModel taskModel, UUID parent);
}
