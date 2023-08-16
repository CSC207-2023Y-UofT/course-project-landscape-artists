package b_application_business_rules.use_cases.project_selection_gateways;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;

/**
 * This interface defines methods for mapping database IDs to corresponding model objects.
 * It provides methods to retrieve ColumnModel, ProjectModel, and TaskModel objects based on their IDs.
 */
public interface IDbIdToModel {

    /**
     * Retrieves a ColumnModel object based on the provided ID.
     *
     * @param id The unique identifier of the ColumnModel to retrieve.
     * @return The corresponding ColumnModel object or null if not found.
     */
    ColumnModel IdToColumnModel(String id);

    /**
     * Retrieves a ProjectModel object based on the provided ID.
     *
     * @param id The unique identifier of the ProjectModel to retrieve.
     * @return The corresponding ProjectModel object or null if not found.
     */
    ProjectModel IdToProjectModel(String id);

    /**
     * Retrieves a TaskModel object based on the provided ID.
     *
     * @param id The unique identifier of the TaskModel to retrieve.
     * @return The corresponding TaskModel object or null if not found.
     */
    TaskModel IdToTaskModel(String id);
}
