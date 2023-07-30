package b_application_business_rules.use_cases.project_selection_use_cases;

import b_application_business_rules.boundaries.ProjectSelectionInputBoundary;
import b_application_business_rules.boundaries.ProjectSelectionOutputBoundary;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import a_enterprise_business_rules.entities.Project;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The ProjectSelectionInteractor class is responsible for handling interactions and business logic
 * related to project selection use cases. It implements the ProjectSelectionInputBoundary interface
 * to define the input boundary methods, which are used by the presenter to interact with this interactor.
 */
public class ProjectSelectionInteractor implements ProjectSelectionInputBoundary {

    // The currentProjectRepository holds the reference to the CurrentProjectRepository instance.
    private CurrentProjectRepository currentProjectRepository = CurrentProjectRepository.getInstance();

    // The presenter holds the reference to the ProjectSelectionOutputBoundary instance,
    // which is responsible for displaying the results of the use cases.
    private ProjectSelectionOutputBoundary presenter;

    /**
     * Initializes the ProjectSelectionInteractor with the provided presenter.
     *
     * @param presenter The presenter instance responsible for displaying the results of the use cases.
     */
    public ProjectSelectionInteractor(ProjectSelectionOutputBoundary presenter) {
        this.presenter = presenter;
    }

    /**
     * Sets the current project in the CurrentProjectRepository and notifies the presenter to display it.
     * This method is called when a project is selected by the user in the UI.
     *
     * @param project The project selected by the user.
     */

    public void setCurrentProject(ProjectModel project) {
        currentProjectRepository.setCurrentProject(project);
    }

    /**
     * Creates a new project. This method is called when the user creates a new project in the UI.
     * It interacts with the necessary use cases and gateway to create the project.
     *
     * @param projectName The name of project.
     * @param projectDescription Description of project.
     */
    @Override
    public void createProject(String projectName, String projectDescription) {
        // Interact with necessary use cases and gateway to create a project.
        // Implementation details depend on the specific requirements and architecture of the application.
        // For example, the interactor might interact with a ProjectRepository to store the project in a database.
        ProjectModel projectModel = new ProjectModel(
                projectName, UUID.randomUUID(), projectDescription, new ArrayList<>());
        setCurrentProject(projectModel);
        presenter.displayCurrentProject(projectModel);
    }

    @Override
    public void openProject(UUID currentProjectID) {
        // TODO: Pass the ProjectModel of the Project with the given UUID to the presenter.
        // TODO: i.e. presenter.displayCurrentProjct(projectModel);

        // Temporary implementation for testing purposes.
        ProjectModel projectModel = new ProjectModel(
                "Project P1", UUID.randomUUID(), "", new ArrayList<>());
        setCurrentProject(projectModel);
        presenter.displayCurrentProject(projectModel);
    }

    /**
     * @param projectUUID
     */
    @Override
    public void renameProject(UUID projectUUID) {

    }

    /**
     * @param projectUUID
     */
    @Override
    public void deleteProject(UUID projectUUID) {

    }
}

