package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.boundaries.ProjectViewingAndModificationInputBoundary;
import b_application_business_rules.boundaries.ProjectViewingAndModificationOutputBoundary;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.factories.TaskModelFactory;
import b_application_business_rules.use_cases.CurrentProjectID;
import b_application_business_rules.use_cases.ProjectRepository;
import b_application_business_rules.use_cases.project_selection_gateways.*;
import b_application_business_rules.use_cases.project_selection_use_cases.DeleteProject;
import d_frameworks_and_drivers.database_management.DBControllers.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    /**
     * Accesses the singleton instance of CurrentProjectID, which holds the currently selected project's UUID.
     */
    CurrentProjectID currentProjectID = CurrentProjectID.getCurrentProjectID();

    /**
     * Accesses the singleton instance of ProjectRepository, which manages project-related data and operations.
     */
    ProjectRepository projectRepository = ProjectRepository.getProjectRepository();

    /**
     * The currently selected project instance from the ProjectRepository.
     */
    private final Project currentProject = ProjectRepository.getProjectRepository().getCurrentProject();

    /**
     * The presenter instance responsible for displaying the results of the project viewing and modification use cases.
     */
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
        currentProjectID.removeCurrentProjectID();
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
        presenter.displayRemovedTask(taskModel, columnID);


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

        List<Project> allProjects = ProjectRepository.getProjectRepository().getAllProjects();

        DeleteProject deleteProject = new DeleteProject(allProjects);
        //A change is made here: deleteProject now requires both a projectModel and UUID
        deleteProject.deleteProjectInDatabase(currentProject.getID());

        dbInsertManager.DBInsert(updatedProject);
    }

    /**
     * The method to get the task from the entities. Used to show the updated task details.
     *
     * @param taskID ID of task.
     */
    @Override
    public void getTask(UUID taskID) {
        for (Column column: currentProject.getColumns()) {
            for (Task task: column.getTasks()) {
                if (task.getID().equals(taskID)) {
                    presenter.displayTaskDetails(new TaskModel(task.getName(), taskID, task.getDescription(),
                            task.getCompletionStatus(), task.getDueDateTime()));
                }
            }
        }
    }

    /**
     * Changes a task's completion status.
     *
     * @param idOfTask               the UUID of the tak to be changed.
     * @param completionStatus its previous status.
     * @param columnID The columnID of parent column of the task.
     */
    @Override
    public void changeTaskCompletionStatus(UUID idOfTask, boolean completionStatus, UUID columnID) {
        ChangeTaskCompletionStatus useCase = new ChangeTaskCompletionStatus(currentProject);
        Task taskUpdated = useCase.changeCompletionStatus(idOfTask);

        TaskModel taskModel = new TaskModel(taskUpdated);

        IDBInsert idbInsert = new DBManagerInsertController();
        IDBRemove idbRemove = new DBManagerRemoveController();

        idbRemove.DBRemoveTask(idOfTask);
        idbInsert.DBInsert(taskModel, columnID);

        presenter.displayTaskCompleted(idOfTask, columnID);


    }

    /**
     * Moves a task from the source column to the target column.
     *
     * @param sourceColumnID The ID of the source column from which the task will be moved.
     * @param targetColumnID The ID of the target column to which the task will be moved.
     * @param task The task to be moved.
     */
    @Override
    public void moveTask(String sourceColumnID, String targetColumnID, TaskModel task) {
        MoveTask useCase = new MoveTask(sourceColumnID, targetColumnID, currentProject);

        // Use DIP to retrieve all  necessary instances
        IDBInsert  idbInsert = new DBManagerInsertController();
        IDBRemove idbRemove = new DBManagerRemoveController();
        IDBSearch idbSearch = new DBManagerSearchController();
        IDbIdToModelList iDbIdToModelList = new IDListsToModelList();

        // get the data from the database
        List<String> sourceColumndata = idbSearch.DBColumnSearch(sourceColumnID);
        List<String> targetColumndata = idbSearch.DBColumnSearch(targetColumnID);

        // get original and updated TaskModel List
        List<TaskModel> sourceTaskList = iDbIdToModelList.
                IdToTaskModelList(List.of(sourceColumndata.get(2).split(",")));
        List<TaskModel> targetTaskList = iDbIdToModelList.
                IdToTaskModelList(List.of(targetColumndata.get(2).split(",")));

        // Delete the task model from the old list and add it to the new one
        TaskModel.removeFromTaskModelList(sourceTaskList, task);
        targetTaskList.add(task);

        // build their respective ColumnModels
        ColumnModel sourceColumnModel = new ColumnModel(
                sourceColumndata.get(1),
                sourceTaskList,
                UUID.fromString(sourceColumndata.get(0))
        );
        ColumnModel targetColumnModel = new ColumnModel(
                targetColumndata.get(1),
                targetTaskList,
                UUID.fromString(targetColumndata.get(0))
        );


        //remove old entries from DB
        idbRemove.DBRemoveColumn(UUID.fromString(sourceColumnID));
        idbRemove.DBRemoveColumn(UUID.fromString(targetColumnID));
        idbRemove.DBRemoveTask(task.getID());

        //add new entries to colunm DB
        idbInsert.DBInsert(sourceColumnModel);
        idbInsert.DBInsert(targetColumnModel);
        idbInsert.DBInsert(task, UUID.fromString(targetColumnID));

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

        deleteColumnUseCase.deleteColumnFromDB(columnID);

        // calls presenter to display message
        presenter.displayDeletedColumn(columnModel);
    }

    /**
     * Edits the details of a column using the provided information. This method triggers the relevant use cases
     * to modify the corresponding entities and database records. After the changes are made, the presenter is
     * called to display the updated information.
     *
     * @param columnID The unique ID of the column to be modified.
     * @param newColumnName The new name for the column.
     * @param columnModel The ColumnModel containing the original column details.
     */
    @Override
    public void editColumnDetails(UUID columnID, String newColumnName, ColumnModel columnModel) {
        // Initialize updated column model
        ColumnModel updatedColumnModel = new ColumnModel(newColumnName, columnModel.getTaskModels(), columnID);

        // Initialize and call use case
        EditColumn useCase = new EditColumn(currentProject);
        useCase.setColumnName(updatedColumnModel);

        // calls presenter to display message
        presenter.displayRenamedColumn(updatedColumnModel);

        // Update database to add the column.
        IDBRemove dbRemoveManager = new DBManagerRemoveController();
        dbRemoveManager.DBRemoveColumn(columnID);

        IDBInsert dbInsertManager = new DBManagerInsertController();
        dbInsertManager.DBInsert(updatedColumnModel);
    }


    /**
     * Changes the details of a task using the updated TaskModel. This method triggers the relevant use cases
     * to modify the corresponding entities and database records. After the changes are made, the presenter is
     * called to display the updated information.
     *
     * @param updatedTask The TaskModel containing the updated task details.
     * @param taskID The unique ID of the task to be modified.
     * @param columnID The unique ID of the column in which the task is located.
     */
    @Override
    public void changeTaskDetails(TaskModel updatedTask, UUID taskID, UUID columnID) {
        // Initializes and call use case
        EditTask editTask = new EditTask(currentProject);
        Task editedTask = editTask.editTask(columnID, updatedTask);

        // Initializing the controllers
        IDBRemove removeTask = new DBManagerRemoveController();
        IDBInsert insertTask = new DBManagerInsertController();

        // Removing the existing task requires a TaskModel, which we don't have any
        // So we need to make one: by finding all the information about the old task
        // Then using the TaskFactory to create a TaskModel

        // Removing the old task
        removeTask.DBRemoveTask(taskID);

        // Inserting the new task
        insertTask.DBInsert(new TaskModel(editedTask),columnID);

        // calls presenter to display message
        presenter.displayChangedTaskDetails(updatedTask, columnID);
    }


}
