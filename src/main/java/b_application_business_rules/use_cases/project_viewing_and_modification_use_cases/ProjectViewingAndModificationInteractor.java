package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.boundaries.ProjectViewingAndModificationInputBoundary;
import b_application_business_rules.boundaries.ProjectViewingAndModificationOutputBoundary;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.factories.TaskModelFactory;
import b_application_business_rules.use_cases.ProjectRepository;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import b_application_business_rules.use_cases.project_selection_gateways.IDBSearch;
import b_application_business_rules.use_cases.project_selection_gateways.IDbIdToModel;
import b_application_business_rules.use_cases.project_selection_use_cases.DeleteProject;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerRemoveController;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerSearchController;
import d_frameworks_and_drivers.database_management.DBControllers.DbIDToModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The ProjectViewingAndModificationInteractor class is responsible for handling
 * interactions
 * and business logic related to viewing and modifying projects. It implements
 * the
 * ProjectViewingAndModificationInputBoundary interface to define the input
 * boundary methods,
 * which are used by the presenter to interact with this interactor.
 */
public class ProjectViewingAndModificationInteractor implements ProjectViewingAndModificationInputBoundary {
    // The currentProjectRepository holds the reference to the
    // CurrentProjectRepository instance.
    ProjectRepository projectRepository = ProjectRepository.getProjectRepository();

    // currentProject attribute to be replaced by actual project access (to access a project entity)
    private final Project currentProject = ProjectRepository.getProjectRepository().getCurrentProject();

    // The presenter holds the reference to the
    // ProjectViewingAndModificationOutputBoundary instance,
    // which is responsible for displaying the results of the use cases.
    private final ProjectViewingAndModificationOutputBoundary presenter;

    /**
     * Initializes the ProjectViewingAndModificationInteractor with the provided
     * presenter.
     *
     * @param presenter The presenter instance responsible for displaying the
     *                  results of the use cases.
     */
    public ProjectViewingAndModificationInteractor(ProjectViewingAndModificationOutputBoundary presenter) {
        this.presenter = presenter;
    }

    /**
     * Removes the current project from the CurrentProjectRepository. This method is
     * called when the
     * "Back" button is clicked in the UI to return to the project selection screen.
     */
    @Override
    public void removeCurrentProject() {
        projectRepository.removeCurrentProject();
    }

    /**
     * Initializes new task model, calls AddTask, calls database accessor to
     * add task to the database, and calls the presenter to change the user display.
     *
     * @param columnID The ID of the column the task is to be added in.
     * @param taskName The name of the task to be added.
     * @param taskDescription The description of the task to be added.
     * @param dueDate The due date of the task to be added.
     */
    @Override
    public void addNewTask(UUID columnID, String taskName, String taskDescription, LocalDateTime dueDate) {
        // Generate random UUID for TaskModel and initialize TaskModel
        UUID taskID = UUID.randomUUID();
        TaskModel newTaskModel = TaskModelFactory.create(taskName, taskID, taskDescription, false, dueDate);

        // initialize use case class and call use case class to create a new task and save it to the database
        AddTask useCase = new AddTask(currentProject);
        useCase.addTask(columnID, newTaskModel);

        // calls presenter to display message
        presenter.displayNewTask(columnID, newTaskModel);

        // Initializing the required controllers and calls method that adds task to the database
        IDBInsert insertTask = new DBManagerInsertController();
        insertTask.DBInsert(newTaskModel);
    }

    /**
     * Calls DeleteTask to delete the task entity with task ID from the current project, calls database accessor to
     * remove the task from the database, and calls the presenter to change the user display.
     *
     * @param columnID The ID of the column the to-be-deleted task was in.
     * @param taskModel The entity model of the task-to-be-deleted.
     */
    @Override
    public void deleteTask(UUID columnID, TaskModel taskModel) {
        // Initialize Use Case Class and call deleteTask method
        DeleteTask useCase = new DeleteTask(currentProject);
        useCase.deleteTask(columnID, taskModel);

        // calls presenter to display message
        presenter.displayRemovedTask(taskModel);

        // initialize controller and remove task from database
        IDBRemove removeTask = new DBManagerRemoveController();
        removeTask.DBRemove(taskModel, taskModel.getID());
    }

    /**
     * The method to add a column to the project.
     *
     * @param columnName the name of the column to be created.
     */
    @Override
    public void addColumn(String columnName) {
        // Generate random UUID for column and create ColumnModel to send data to presenter and to use case class.
        UUID columnID = UUID.randomUUID();
        ColumnModel columnModel = new ColumnModel(columnName, new ArrayList<>(), columnID);

        // initializing use case to add column and initiate adding to the column
        AddColumn addColumnUseCase = new AddColumn(currentProject);
        addColumnUseCase.addColumn(columnModel);

        // Send data to presenter.
        presenter.displayNewColumn(columnModel);

        // Update database to add the column.
        IDbIdToModel iDbIdToModel = new DbIDToModel();
        IDBInsert dbInsertManager = new DBManagerInsertController();
        dbInsertManager.DBInsert(columnModel);
        ProjectModel updatedProject = iDbIdToModel.IdToProjectModel(currentProject.getID().toString());
        updatedProject.getColumnModels().add(columnModel);


        DeleteProject deleteProject = new DeleteProject();
        //A change is made here: deleteProject now requires both a projectModel and UUID
        deleteProject.deleteProject(iDbIdToModel.IdToProjectModel(currentProject.getID().toString()), currentProject.getID());

        dbInsertManager.DBInsert(updatedProject);
    }

    /**
     * The method to delete a column from the project.
     *
     * @param columnID the UUID of the column to be deleted.
     */
    @Override
    public void deleteColumn(UUID columnID) {
        // Get column entity and initialize column with it
        Column column = Column.IDToColumn(columnID, currentProject.getColumns());
        ColumnModel columnModel = new ColumnModel(column);

        // Initializes and call use case
        DeleteColumn deleteColumnUseCase = new DeleteColumn(currentProject);
        deleteColumnUseCase.deleteColumn(columnID);

        // calls presenter to display message
        presenter.displayDeletedColumn(columnModel);

        // Update the database to remove the column.
        IDBRemove dbRemoveManager = new DBManagerRemoveController();
        dbRemoveManager.DBRemove(columnModel, columnID);
    }

    /**
     * Edits the details of a column identified by the specified columnID.
     */
    @Override
    public void editColumnDetails(UUID columnID, String newColumnName) {
        // Initialize updated column model
        ColumnModel updatedColumnModel = new ColumnModel(newColumnName, new ArrayList<>(), columnID);

        // Initialize and call use case
        EditColumn useCase = new EditColumn(currentProject);
        useCase.setColumnName(updatedColumnModel);

        // calls presenter to display message
        presenter.displayRenamedColumn(updatedColumnModel);

        // Update database to add the column.
        IDBRemove dbRemoveManager = new DBManagerRemoveController();
        dbRemoveManager.DBRemove(updatedColumnModel, columnID);

        IDBInsert dbInsertManager = new DBManagerInsertController();
        dbInsertManager.DBInsert(updatedColumnModel);
    }


    /**
     * Changes the task details given the new TaskModel task. Calls the use case to
     * make changes to the entities and database then calls the presenter to display the
     * updated changes
     *
     */
    @Override
    public void changeTaskDetails(TaskModel updatedTask, UUID taskID, UUID columnID) {
        // Initializes and call use case
        EditTask editTask = new EditTask(currentProject);
        editTask.editTask(columnID, updatedTask);

        // calls presenter to display message
        presenter.displayChangedTaskDetails(updatedTask, columnID);

        // Initializing the controllers
        IDBRemove removeTask = new DBManagerRemoveController();
        IDBInsert insertTask = new DBManagerInsertController();
        IDBSearch findOldTask = new DBManagerSearchController();

        // Removing the existing task requires a TaskModel, which we don't have any
        // So we need to make one: by finding all the information about the old task
        // Then using the TaskFactory to create a TaskModel

        ArrayList<String> oldTaskInfo = findOldTask.DBTaskSearch(taskID.toString());
        String oldTaskName = oldTaskInfo.get(1);
        String oldTaskDescription = oldTaskInfo.get(2);
        boolean oldTaskStatus = Boolean.parseBoolean(oldTaskInfo.get(3));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime oldTaskDate = LocalDateTime.parse(oldTaskInfo.get(4), formatter);
//        LocalDateTime oldTaskDate = LocalDateTime.parse(oldTaskInfo.get(4));
        TaskModel oldTask = TaskModelFactory.create(oldTaskName, taskID,
                oldTaskDescription, oldTaskStatus, oldTaskDate);

        // Removing the old task
        removeTask.DBRemove(oldTask, taskID);

        // Inserting the new task
        insertTask.DBInsert(updatedTask);
    }
}
