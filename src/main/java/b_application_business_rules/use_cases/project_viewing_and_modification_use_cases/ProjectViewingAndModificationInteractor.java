package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import b_application_business_rules.boundaries.ProjectViewingAndModificationInputBoundary;
import b_application_business_rules.boundaries.ProjectViewingAndModificationOutputBoundary;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.factories.TaskModelFactory;
import b_application_business_rules.use_cases.CurrentProjectID;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import c_interface_adapters.view_models.TaskViewModel;

import java.time.LocalDateTime;
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
    CurrentProjectRepository currentProjectRepository = CurrentProjectRepository.getCurrentprojectrepository();
    CurrentProjectID currentProjectID = CurrentProjectID.getCurrentProjectID();

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
        currentProjectID.removeCurrentProjectID();
    }

    @Override
    public void addNewTask(UUID idOfColumn, String taskName, String taskDescription, LocalDateTime dueDate) {
        // Generate random UUID for task
        UUID taskID = UUID.randomUUID();
        // Create TaskModel with given info
        TaskModel newTaskModel = TaskModelFactory.create(taskName, taskID, taskDescription, false, dueDate);
        // initialize use case class
        AddTask useCase = new AddTask(idOfColumn, newTaskModel);
        // call use case class to create a new task and save it to the database
        useCase.addTask(idOfColumn);
        // Initialize TaskViewModel
        TaskViewModel newTask = new TaskViewModel(newTaskModel);
        // calls presenter to display message
        presenter.displayNewTask(idOfColumn, newTask);
    }

    /**
     * The method to add a column to the project.
     *
     * @param columnName the name of the column to be created.
     */
    @Override
    public void addColumn(String columnName) {
        // Generate random UUID for column
        UUID idOfColumn = UUID.randomUUID();
        // Create ColumnModel to send data to presenter and to use case class.
        ColumnModel columnModel = new ColumnModel(columnName, new ArrayList<>(), idOfColumn);
        // initializing use case to add column and initiate adding to the column
        AddColumn addColumnUseCase = new AddColumn(columnModel);
        addColumnUseCase.addColumn();
        // Send data to presenter.
        presenter.displayNewColumn(columnModel);
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

    @Override
    public void deleteTask(TaskModel task, UUID taskID, UUID ColumnID) {
        // Initialize Use Case Class
        DeleteTask useCase = new DeleteTask(task, taskID, ColumnID);
        // Delete Task
        useCase.deleteTask();

        TaskViewModel newTask = new TaskViewModel(task.getName(), taskID, task.getDescription(),
                task.getCompletionStatus(), task.getDueDateTime());
        presenter.displayRemovedTask(taskID, newTask);

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

    @Override
    public void renameTask(TaskModel task, UUID TaskUIid) {

    }

    @Override
    public void removeTask(TaskModel task, UUID columnId) {

    }

    @Override
    public void addTask(TaskModel task, UUID targetColumnId) {

    }

    @Override
    public void changeTaskDate(TaskModel task, UUID targetColumnId) {

    }

    @Override
    public void renameProject(ProjectModel project, UUID projectId) {

    }

    @Override
    public void deleteProject(ProjectModel project, UUID projectId) {

    }

}
