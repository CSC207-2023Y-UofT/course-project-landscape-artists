package b_application_business_rules.boundaries;
import b_application_business_rules.entity_models.ProjectModel;

import b_application_business_rules.entity_models.ProjectModel;

import java.io.IOException;

/**
 * This interface defines the output boundary for displaying project-related information and events.
 * It provides methods to display current project details, renamed project details, and deleted project details.
 */
public interface ProjectSelectionOutputBoundary {

    /**
     * Displays the details of the current project.
     *
     * @param projectModel The project model containing details of the current project to display.
     */
    void displayCurrentProject(ProjectModel projectModel);

    /**
     * Displays the details of a project after it has been renamed.
     *
     * @param projectModel The project model containing updated details of the renamed project to display.
     */
    void displayRenamedProject(ProjectModel projectModel);

    /**
     * Displays the details of a project that has been deleted.
     *
     * @param projectModel The project model containing details of the deleted project to display.
     */
    void displayDeletedProject(ProjectModel projectModel);

}