package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.boundaries.ProjectViewingAndModificationInputBoundary;
import b_application_business_rules.boundaries.ProjectViewingAndModificationOutputBoundary;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.factories.TaskModelFactory;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import b_application_business_rules.use_cases.project_selection_gateways.IDbIdToModel;
import b_application_business_rules.use_cases.project_selection_use_cases.DeleteProject;
import c_interface_adapters.view_models.TaskViewModel;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerRemoveController;
import d_frameworks_and_drivers.database_management.DBControllers.DbIDToModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    CurrentProjectRepository currentProjectRepository = CurrentProjectRepository.getCurrentprojectrepository();

    // currentProject attribute to be replaced by actual project access (to access a project entity)
    private final Project currentProject = new Project("p", UUID.randomUUID(), "", new ArrayList<Column>());

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
        currentProjectRepository.removeCurrentProject();
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
        UUID idOfColumn = UUID.randomUUID();
        ColumnModel columnModel = new ColumnModel(columnName, new ArrayList<>(), idOfColumn);

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
        deleteProject.deleteProject(iDbIdToModel.IdToProjectModel(currentProject.getID().toString()));
        dbInsertManager.DBInsert(updatedProject);
    }

    /**
     * The method to add a column to the project.
     *
     * @param columnBoxId the UUID of the column to be deleted.
     */
    @Override
    public void deleteColumn(UUID columnBoxId) {
        DeleteColumn deleteColumnUseCase = new DeleteColumn(columnBoxId);
        deleteColumnUseCase.deleteColumn();

        ColumnModel c = new ColumnModel("Deleted column", new ArrayList<>(), columnBoxId);
        presenter.displayDeletedColumn(c);
    }

    /**
     * Edits the details of a column identified by the specified columnBoxId.
     *
     * @param columnBoxId    The unique identifier (UUID) of the column box containing the column to be edited.
     * @param newColumnName  The new name for the column.
     */
    @Override
    public void editColumnDetails(UUID columnBoxId, String newColumnName) {
        ColumnModel updatedColumnModel = new ColumnModel(newColumnName, new ArrayList<>(), columnBoxId);
        EditColumnDetails useCase = new EditColumnDetails(updatedColumnModel);
        useCase.setColumnName(newColumnName);


        ColumnModel c = new ColumnModel(newColumnName, new ArrayList<>(), columnBoxId);
        presenter.displayRenamedColumn(updatedColumnModel);
    }


    /**
     * Changes the task details given the new TaskModel task. Calls the use case to
     * make
     * changes to the entities and database then calls the presenter to display the
     * updated changes
     * 
     * @param task     Task Model
     * @param TaskUIid ID of task entity
     */
    @Override
    public void changeTaskDetails(TaskModel task, UUID TaskUIid, UUID ParentColumn) {
        EditTaskDetails useCase = new EditTaskDetails(task, TaskUIid);
        try {
            useCase.editTask(ParentColumn);
            //Call to presenter here was moved to the controller (changeTaskDetails)
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
