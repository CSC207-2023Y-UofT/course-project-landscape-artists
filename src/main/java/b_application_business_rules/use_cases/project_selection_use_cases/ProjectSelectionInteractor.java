package b_application_business_rules.use_cases.project_selection_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;

import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.TaskModel;

import b_application_business_rules.boundaries.ProjectSelectionInputBoundary;
import b_application_business_rules.boundaries.ProjectSelectionOutputBoundary;
import b_application_business_rules.use_cases.CurrentProjectID;
import b_application_business_rules.use_cases.ProjectRepository;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import b_application_business_rules.use_cases.project_selection_gateways.IDbIdToModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDbIdToModelList;
import d_frameworks_and_drivers.database_management.DBControllers.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * The ProjectSelectionInteractor class is responsible for handling interactions
 * and business logic related to project selection use cases. It implements the
 * ProjectSelectionInputBoundary interface to define the input boundary methods,
 * which are used by the presenter to interact with this interactor.
 */
public class ProjectSelectionInteractor implements ProjectSelectionInputBoundary {

	private final ProjectRepository projectRepository = ProjectRepository.getProjectRepository();
	private final List<Project> allProjects = projectRepository.getAllProjects();
	private final ProjectSelectionOutputBoundary presenter;


	/**
	 * Initializes the ProjectSelectionInteractor with the provided presenter.
	 *
	 * @param presenter The presenter instance responsible for displaying the results of the use cases.
	 */
	public ProjectSelectionInteractor(ProjectSelectionOutputBoundary presenter) {
		this.presenter = presenter;
	}

	/**
	 * Sets the current project in the CurrentProjectRepository and notifies the
	 * presenter to display it. This method is called when a project is selected
	 * by the user in the UI.
	 *
	 * @param project The project selected by the user.
	 */
	public void setCurrentProject(ProjectModel project) {
		projectRepository.setCurrentProject(project.getProjectEntity());
	}


	/**
	 * Sets the attribute holding all projects in the ProjectRepository.
	 * This method is called upon startup.
	 *
	 * @param allProjectsList The projects from the database.
	 */
	@Override
	public void setAllProjects(List<ProjectModel> allProjectsList) {
		List<Project> allProjects = new ArrayList<>();

		for (ProjectModel projectModel: allProjectsList) {
			allProjects.add(projectModel.getProjectEntity());
		}

		projectRepository.setAllProjects(allProjects);
	}


	/**
	 * Sets the current project ID in the CurrentProjectID repository. Used by the database to have access
	 *
	 * @param uuid The UUID of the selected project.
	 */
	public void setCurrentProjectID(UUID uuid) {
		CurrentProjectID.getCurrentProjectID().setSelectedProjectID(uuid);
	}

	/**
	 * Creates a new project. This method is called when the user creates a new
	 * project in the UI. It interacts with the necessary use cases and gateway to
	 * create the project.
	 *
	 * @param projectName        The name of the project.
	 * @param projectDescription The description of the project.
	 */
	@Override
	public void createProject(String projectName, String projectDescription) {
		IDbIdToModelList iDbIdToModelList = new IDListsToModelList();
		CreateProject useCase = new CreateProject(allProjects);
		Project newProject = useCase.newProject(projectName, UUID.randomUUID(), projectDescription, new ArrayList<>());

		CurrentProjectID.getCurrentProjectID().setSelectedProjectID(newProject.getID());

		List<ColumnModel> defaultColumn = new ArrayList<>();
		defaultColumn.add(new  ColumnModel("Default Column", new ArrayList<>(), UUID.randomUUID()));
		IDBInsert databaseInserter = new DBManagerInsertController();
		ProjectModel projectModel = new ProjectModel(newProject);
		projectModel.setColumnModels(defaultColumn);
		databaseInserter.DBInsert(projectModel);
		databaseInserter.DBInsert(projectModel.getColumnModels().get(0));

		setCurrentProject(projectModel);
		presenter.displayCurrentProject(projectModel);
	}

	/**
	 * Opens the project with the specified ID. This method is called when the user
	 * wants to open a project.
	 *
	 * @param currentProjectID The ID of the project to open.
	 */
	@Override
	public void openProject(UUID currentProjectID) {
		IDbIdToModel iDbIdToModel = new DbIDToModel();
		setCurrentProjectID(currentProjectID);
		ProjectModel ProjectFromDB = iDbIdToModel.IdToProjectModel(currentProjectID.toString());
		setCurrentProject(ProjectFromDB);
		presenter.displayCurrentProject(ProjectFromDB);
	}

	/**
	 * Renames a project with the given UUID and updates its description. This
	 * method is called when the user wants to rename a project.
	 *
	 * @param projectUUID     The UUID of the project to be renamed.
	 * @param newName         The new name for the project.
	 * @param newDescription  The new description for the project.
	 */
	@Override
	public void renameProject(UUID projectUUID, String newName, String newDescription) {
		EditProjectDetails useCase = new EditProjectDetails(allProjects, projectUUID);
		useCase.setNameAndDescription(newName, newDescription);
		ProjectModel editedProjectModel = new ProjectModel(newName, projectUUID, newDescription, new ArrayList<>());
		presenter.displayRenamedProject(editedProjectModel);
	}

	/**
	 * Deletes a project with the specified UUID. This method is called when the
	 * user wants to delete a project.
	 *
	 * @param projectUUID The UUID of the project to be deleted.
	 */
	@Override
	public void deleteProject(UUID projectUUID) {
		DeleteProject useCase = new DeleteProject(allProjects);
		useCase.deleteProject(projectUUID);
		presenter.displayDeletedProject(new ProjectModel("", projectUUID, "", new ArrayList<>()));
	}
}