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
	private ProjectModel projectModel;

	/**
	 * Initializes the ProjectSelectionInteractor with the provided presenter.
	 *
	 * @param presenter The presenter instance responsible for displaying the results of the use cases.
	 */
	public ProjectSelectionInteractor(ProjectSelectionOutputBoundary presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setCurrentProject(Project project) {
		this.setCurrentProject(new ProjectModel(project));
	}

	public void setCurrentProject(ProjectModel project) {
		projectRepository.setCurrentProject(project.getProjectEntity());
	}

	@Override
	public void setAllProjects(List<ProjectModel> allProjectsList) {
		List<Project> allProjects = new ArrayList<>();

		for (ProjectModel projectModel: allProjectsList) {
			allProjects.add(projectModel.getProjectEntity());
		}

		projectRepository.setAllProjects(allProjects);
	}

	public void setCurrentProjectID(UUID uuid) {
		CurrentProjectID.getCurrentProjectID().setSelectedProjectID(uuid);
	}

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

	@Override
	public void openProject(UUID currentProjectID) {
		IDbIdToModel iDbIdToModel = new DbIDToModel();
		List<TaskModel> TaskList = Arrays.asList(
				new TaskModel("Task1", UUID.randomUUID(), "Task1", true, LocalDateTime.now()),
				new TaskModel("Task2", UUID.randomUUID(), "Task2", true, LocalDateTime.now()));
		List<ColumnModel> ColumnsList = Arrays.asList(
				new ColumnModel("COLUMN 1", TaskList, UUID.randomUUID()),
				new ColumnModel("COLUMN 2", new ArrayList<>(), UUID.randomUUID()));
		ProjectModel projectModel = new ProjectModel("Project P1", UUID.randomUUID(), "", ColumnsList);
		setCurrentProjectID(currentProjectID);
		ProjectModel ProjectFromDB = iDbIdToModel.IdToProjectModel(currentProjectID.toString());
		setCurrentProject(ProjectFromDB);
		presenter.displayCurrentProject(ProjectFromDB);
	}

	@Override
	public void renameProject(UUID projectUUID, String newName, String newDescription) {
		EditProjectDetails useCase = new EditProjectDetails(allProjects, projectUUID);
		useCase.setNameAndDescription(newName, newDescription);
		ProjectModel editedProjectModel = new ProjectModel(newName, projectUUID, newDescription, new ArrayList<>());
		presenter.displayRenamedProject(editedProjectModel);
	}

	@Override
	public void deleteProject(UUID projectUUID) {
		DeleteProject useCase = new DeleteProject(allProjects);
		useCase.deleteProject(projectUUID);
		presenter.displayDeletedProject(new ProjectModel("", projectUUID, "", new ArrayList<>()));
	}
}