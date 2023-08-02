package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import b_application_business_rules.boundaries.ProjectViewingAndModificationInputBoundary;
import b_application_business_rules.boundaries.ProjectViewingAndModificationOutputBoundary;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;

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
    private ProjectViewingAndModificationOutputBoundary presenter;
    private IDBInsert insertGateway = new DBManagerInsertController();

    /**
     * Initializes the ProjectViewingAndModificationInteractor with the provided presenter.
     *
     * @param presenter The presenter instance responsible for displaying the results of the use cases.
     */
    public ProjectViewingAndModificationInteractor(ProjectViewingAndModificationOutputBoundary presenter, IDBInsert insertGateway) {
        this.presenter = presenter;
        this.insertGateway = insertGateway;
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
    public void addNewTask(String idOfColumn, String taskName, String taskDescription, LocalDateTime dueDate) {
        //Generate random UUID for task
        UUID taskID = UUID.randomUUID();
        //Create TaskModel with given info
        TaskModel t = new TaskModel(taskName, taskID, taskDescription, false, dueDate);
        //Save the new task into the database
        insertGateway.DBInsert(t);

    }

    @Override
    public void deleteColumn(UUID columnBoxId) {
        // TODO: DO NECESSARY STUFF.
        ColumnModel c = new ColumnModel("Test", new ArrayList<>(), columnBoxId);
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

    @Override
    public void changeTaskDetails(TaskModel task, UUID TaskUIid) {

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
     *
     */
    @Override
    public void addColumn(String columnName) {
        // TODO: DO NECESSARY STUFF.
        ColumnModel c = new ColumnModel(columnName, new ArrayList<>(), UUID.randomUUID());
        presenter.displayNewColumn(c);
    }


}
