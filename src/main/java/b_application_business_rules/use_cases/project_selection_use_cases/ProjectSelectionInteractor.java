package b_application_business_rules.use_cases.project_selection_use_cases;

import a_enterprise_business_rules.entities.Project;

import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.TaskModel;

import b_application_business_rules.boundaries.ProjectSelectionInputBoundary;
import b_application_business_rules.boundaries.ProjectSelectionOutputBoundary;
import b_application_business_rules.use_cases.CurrentProjectID;
import b_application_business_rules.use_cases.ProjectRepository;
import b_application_business_rules.use_cases.project_selection_gateways.IDbIdToModel;
import d_frameworks_and_drivers.database_management.DBControllers.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * The ProjectSelectionInteractor class is responsible for handling interactions
 * and business logic
 * related to project selection use cases. It implements the
 * ProjectSelectionInputBoundary interface
 * to define the input boundary methods, which are used by the presenter to
 * interact with this interactor.
 */
public class ProjectSelectionInteractor implements ProjectSelectionInputBoundary {

	// The currentProjectRepository holds the reference to the
	// CurrentProjectRepository instance.
	private final ProjectRepository projectRepository = ProjectRepository
			.getProjectRepository();

	// allProjects holds all project entities in the system.
	private final List<Project> allProjects = ProjectRepository.getProjectRepository().getAllProjects();

	// COPIED FROM ProjectViewingAndModificationInteractor
	//currentProject attribute to be replaced by actual project access (to access a project entity)
	private final Project currentProject = new Project("p", UUID.randomUUID(), "", new ArrayList<Column>());

	private final CurrentProjectID currentProjectID = CurrentProjectID.getCurrentProjectID();

	// The presenter holds the reference to the ProjectSelectionOutputBoundary
	// instance,
	// which is responsible for displaying the results of the use cases.
	private final ProjectSelectionOutputBoundary presenter;
	private ProjectModel projectModel;
	private String message;

	/**
	 * Initializes the ProjectSelectionInteractor with the provided presenter.
	 *
	 * @param presenter The presenter instance responsible for displaying the
	 *                  results of the use cases.
	 */
	public ProjectSelectionInteractor(ProjectSelectionOutputBoundary presenter) {
		this.presenter = presenter;
	}

	/**
	 * Sets the current project in the CurrentProjectRepository and notifies the
	 * presenter to display it.
	 * This method is called when a project is selected by the user in the UI.
	 * 
	 * @param project The project selected by the user.
	 */
	@Override
	public void setCurrentProject(Project project) {
		this.setCurrentProject(new ProjectModel(project));
	}

	/**
	 * Sets the current project in the CurrentProjectRepository and notifies the
	 * presenter to display it.
	 * This method is called when a project is selected by the user in the UI.
	 *
	 * @param project The project selected by the user.
	 */
	public void setCurrentProject(ProjectModel project) {
		projectRepository.setCurrentProject(project.getProjectEntity());
	}

	/**
	 * Sets the attribute holding all projects in the ProjectRepository.
	 *
	 * This method is called upon startup.
	 *
	 * @param allProjectsList The projects from the database.
	 */
	@Override
	public void setAllProjects(List<ProjectModel> allProjectsList) {
		List<Project> allProjects = new ArrayList<>();

		// Convert all Project models from the outer layers to a Project.
		for (ProjectModel projectModel: allProjectsList) {
			allProjects.add(projectModel.getProjectEntity());
		}

		projectRepository.setAllProjects(allProjects);
	}
	public void setCurrentProjectID(UUID uuid) {
		currentProjectID.setSelectedProjectID(uuid);
	}

	/**
	 * Creates a new project. This method is called when the user creates a new
	 * project in the UI.
	 * It interacts with the necessary use cases and gateway to create the project.
	 *
	 * @param projectName        The name of project.
	 * @param projectDescription Description of project.
	 */
	@Override
	public void createProject(String projectName, String projectDescription) {
		// Interact with necessary use cases and gateway to create a project.
		// Implementation details depend on the specific requirements and architecture
		// of the application.
		// For example, the interactor might interact with a ProjectRepository to store
		// the project in a database.

		CreateProject useCase = new CreateProject(allProjects);
		Project newProject = useCase.newProject(projectName, UUID.randomUUID(),
				projectDescription, new ArrayList<>());

		IDBInsert databaseInserter = new DBManagerInsertController();
		ProjectModel projectModel = new ProjectModel(newProject);
		databaseInserter.DBInsert(projectModel);

		// Sets the project in the projectRepository
		setCurrentProject(projectModel);
		presenter.displayCurrentProject(projectModel);
	}

	@Override
	public void createProject() {
		new CreateProject();
	}

	@Override
	public void createProject(ProjectModel projectModel) {

	}

	@Override
	public void openProject(UUID currentProjectID) {
		IDbIdToModel iDbIdToModel = new DbIDToModel();
		// TODO: Pass the ProjectModel of the Project with the given UUID to the
		// presenter.
		// TODO: i.e. presenter.displayCurrentProjct(projectModel);

		 // Temporary implementation for testing purposes.
		 List<TaskModel> TaskList = Arrays.asList(
		 new TaskModel("Task1", UUID.randomUUID(), "Task1", true,
		 LocalDateTime.now()),
		 new TaskModel("Task2", UUID.randomUUID(), "Task2", true,
		 LocalDateTime.now()));
		 List<ColumnModel> ColumnsList = Arrays.asList(
		 new ColumnModel("COLUMN 1", TaskList, UUID.randomUUID()),
		 new ColumnModel("COLUMN 2", new ArrayList<>(), UUID.randomUUID()));
		 ProjectModel projectModel = new ProjectModel(
		 "Project P1", UUID.randomUUID(), "", ColumnsList);
		 setCurrentProjectID(currentProjectID);
		 ProjectModel ProjectFromDB = iDbIdToModel.IdToProjectModel(currentProjectID.toString());
		 setCurrentProject(ProjectFromDB);
		 presenter.displayCurrentProject(ProjectFromDB);
	}

	/**
	 * @param projectUUID
	 * @param newName
	 * @param newDescription
	 */
	@Override
	public void renameProject(UUID projectUUID, String newName, String newDescription) {
		EditProjectDetails useCase = new EditProjectDetails(allProjects, projectUUID);

		useCase.setNameAndDescription(newName, newDescription);

		// For UI purposes, we do not need the list of columns in the project model since it would not get displayed
		// anyways.
		ProjectModel editedProjectModel = new ProjectModel(newName, projectUUID, newDescription, new ArrayList<>());
		presenter.displayRenamedProject(editedProjectModel);
	}

	/**
	 * @param projectUUID
	 */
	@Override
	public void deleteProject(UUID projectUUID) {
		DeleteProject useCase = new DeleteProject(allProjects);

		//This line calls the use case and updates the database
		useCase.deleteProject(projectUUID);
		presenter.displayDeletedProject(new ProjectModel(
				"", projectUUID, "", new ArrayList<>()
		));
	}

	/**
	 * @param message
	 */
	@Override
	public void projectDeletionFailed(String message) {

	}

	/**
	 * @param projectID
	 */
	@Override
	public void projectDeleted(UUID projectID) {

	}
}
