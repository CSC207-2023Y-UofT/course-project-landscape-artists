package c_interface_adapters;

import b_application_business_rules.entity_models.*;
import java.util.*;


/**
 * This interface provides methods for the presenter to interact with the database
 * in order to retrieve project-related data in the form of ProjectModels.
 */
public interface DBAdapterInterface {

    /**
     * Retrieves a list of ProjectModels based on IDs.
     * This method allows the presenter to obtain a list of ProjectModels corresponding to
     * the available project IDs.
     *
     * @return A list of ProjectModels obtained from the database.
     */
    List<ProjectModel> IDstoProjectModelList();

    /**
     * Retrieves a ProjectModel based on a project UUID.
     * This method enables the presenter to obtain a specific ProjectModel by providing
     * the UUID of the desired project.
     *
     * @param projectUUID The UUID of the project for which the ProjectModel is requested.
     * @return The ProjectModel associated with the provided project UUID.
     */
    ProjectModel IDsToProjectModel(UUID projectUUID);
}

