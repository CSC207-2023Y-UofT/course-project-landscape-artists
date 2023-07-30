package b_application_business_rules.use_cases.project_selection_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.boundaries.ProjectSelectionInputBoundary;
import b_application_business_rules.boundaries.ProjectSelectionOutputBoundary;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;

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
    @Override
    public void setCurrentProject(Project project) {
        currentProjectRepository.setCurrentProject(project);
        presenter.displayCurrentProject();
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

        IDBInsert w = new DBManagerInsertController();
    }
}

