package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import b_application_business_rules.boundaries.ProjectViewingAndModificationInputBoundary;
import b_application_business_rules.boundaries.ProjectViewingAndModificationOutputBoundary;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import c_interface_adapters.view_models.TaskViewModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The ProjectViewingAndModificationInteractor class is responsible for handling interactions
 * and business logic related to viewing and modifying projects. It implements the
 * ProjectViewingAndModificationInputBoundary interface to define the input boundary methods,
 * which are used by the presenter to interact with this interactor.
 */
public class ProjectViewingAndModificationInteractor implements ProjectViewingAndModificationInputBoundary {
    // The currentProjectRepository holds the reference to the CurrentProjectRepository instance.
    CurrentProjectRepository currentProjectRepository = CurrentProjectRepository.getInstance();

    // The presenter holds the reference to the ProjectViewingAndModificationOutputBoundary instance,
    // which is responsible for displaying the results of the use cases.
    private final ProjectViewingAndModificationOutputBoundary presenter;

    /**
     * Initializes the ProjectViewingAndModificationInteractor with the provided presenter.
     *
     * @param presenter The presenter instance responsible for displaying the results of the use cases.
     */
    public ProjectViewingAndModificationInteractor(ProjectViewingAndModificationOutputBoundary presenter) {
        this.presenter = presenter;
    }

    /**
     * Removes the current project from the CurrentProjectRepository. This method is called when the
     * "Back" button is clicked in the UI to return to the project selection screen.
     */
    @Override
    public void removeCurrentProject() {
        currentProjectRepository.removeCurrentProject();
    }

    @Override
    public void addNewTask(UUID idOfColumn, String taskName, String taskDescription, LocalDateTime dueDate) {

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


    @Override
    public void renameColumn(UUID columnBoxId) {
        // TODO: DO NECESSARY STUFF.
        ColumnModel c = new ColumnModel("Test", new ArrayList<>(), columnBoxId);
        presenter.displayRenamedColumn(c);
    }

    @Override
    public void deleteTask(TaskModel task, UUID TaskUIid) {

    }

    /**
     * Changes the task details given the new TaskModel task. Calls the use case to make
     * changes to the entities and database
     * @param task
     * @param TaskUIid
     */
    @Override
    public void changeTaskDetails(TaskModel task, UUID TaskUIid) {
        EditTaskDetails useCase = new EditTaskDetails(task, TaskUIid);
        try {
            useCase.editTask();
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

    /**
     * The method to add a column to the project.
     *
     * @param columnName the name of the column to be created.
     */
    @Override
    public void addColumn(String columnName) {
        // Genereate random UUID for column
        UUID idOfColumn = UUID.randomUUID();

        // initializing use case to add column and initiate adding to the columm
        AddColumn addColumnUseCase = new AddColumn(columnName, idOfColumn);
        addColumnUseCase.addColumn();
        // Creates the ColumnModel to send data to presenter.
        ColumnModel columnModelForPresenter = new ColumnModel(columnName, new ArrayList<>(), idOfColumn);
        // Send data to presenter.
        presenter.displayNewColumn(columnModelForPresenter);
    }


}
