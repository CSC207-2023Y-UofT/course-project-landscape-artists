package b_application_business_rules.boundaries;

import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.entity_models.ProjectModel;

import java.util.List;
import java.util.UUID;

/**
 * This interface defines the input boundary for managing project selection and manipulation.
 * It provides methods to set, create, delete, open, rename, and update project-related information.
 */
public interface ProjectSelectionInputBoundary {
    /**
     * Sets the current project to the specified project model.
     *
     * @param project The project model to set as the current project.
     */
    void setCurrentProject(ProjectModel project);

    /**
     * Creates a new project with the given name and description.
     *
     * @param name        The name of the new project.
     * @param description The description of the new project.
     */
    void createProject(String name, String description);

    /**
     * Deletes the project associated with the provided project ID.
     *
     * @param projectID The unique identifier of the project to be deleted.
     */
    void deleteProject(UUID projectID);

    /**
     * Opens the project associated with the provided project ID, making it the current project.
     *
     * @param currentProjectID The unique identifier of the project to be opened.
     */
    void openProject(UUID currentProjectID);

    /**
     * Renames the project identified by the given project UUID with the new name and description.
     *
     * @param projectUUID The unique identifier of the project to be renamed.
     * @param newName     The new name for the project.
     * @param newDescription The new description for the project.
     */
    void renameProject(UUID projectUUID, String newName, String newDescription);

    /**
     * Sets the list of all project models to be managed by the application.
     *
     * @param allProjectsList A list containing all the project models to be managed.
     */
    void setAllProjects(List<ProjectModel> allProjectsList);
}