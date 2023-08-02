package b_application_business_rules.use_cases.project_selection_use_cases;

import b_application_business_rules.boundaries.ProjectSelectionInputBoundary;
import b_application_business_rules.boundaries.ProjectSelectionOutputBoundary;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import a_enterprise_business_rules.entities.Project;
import c_interface_adapters.view_models.ColumnViewModel;
import c_interface_adapters.view_models.TaskViewModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        List<TaskModel> TaskList = Arrays.asList(
                new TaskModel("Task1", UUID.randomUUID(), "Task1", true,
                        LocalDateTime.now()),
                new TaskModel("Task2", UUID.randomUUID(), "Task2", true,
                        LocalDateTime.now()));
        List<ColumnModel> ColumnsList = Arrays.asList(
                new ColumnModel("COLUMN 1", TaskList, UUID.randomUUID()),
                new ColumnModel("COLUMN 2", new ArrayList<>(), UUID.randomUUID())
        );
        ProjectModel projectModel = new ProjectModel(
                "Project P1", UUID.randomUUID(), "This is some description", ColumnsList);
        setCurrentProject(projectModel);
        presenter.displayCurrentProject(projectModel);
    }

    /**
     * @param projectUUID
     */
    @Override
    public void renameProject(UUID projectUUID) {
        ProjectModel projectModel = new ProjectModel(
                "Revised project P1", projectUUID, "", new ArrayList<>());
        presenter.displayRenamedProject(projectModel);
    }

    /**
     * @param projectUUID
     */
    @Override
    public void deleteProject(UUID projectUUID) {
        ProjectModel projectModel = new ProjectModel(
                "Revised project P1", projectUUID, "", new ArrayList<>());
        presenter.displayDeletedProject(projectModel);
    }

    /**
     * @param columnID
     * @param task
     */
    @Override
    public void deleteTask(UUID columnID, TaskModel task) {

    }
}

