package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import b_application_business_rules.boundaries.ProjectViewingAndModificationInputBoundary;
import b_application_business_rules.boundaries.ProjectViewingAndModificationOutputBoundary;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import a_enterprise_business_rules.entities.Task;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;

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

    /**
     * Adds a new task to the specified column in the UI. This method is called when the "Add Task"
     * button is clicked, prompting a popup window for task details.
     *
     * @param columnBox      The VBox representing the Column UI where the new task will be added.
     * @param taskName       The name of the new task.
     * @param taskDescription The description of the new task.
     * @param dueDate        The due date and time of the new task.
     */
    @Override
    public void addNewTask(VBox columnBox, String taskName, String taskDescription, LocalDateTime dueDate) {
        // Interact with necessary use cases here. Also interact with the gateway for database access.

        // For testing purposes, create a temporary Task instance with the provided details.
        Task newTask = new Task(taskName, taskDescription, false, dueDate);
        presenter.displayNewTask(columnBox, newTask);
    }

    @Override
    public void deleteColumn(Column column, VBox columnBox) {

    }

    @Override
    public void renameColumn(Column column, VBox columnBox) {

    }

    @Override
    public void deleteTask(Task task, HBox hbox) {

    }

    @Override
    public void changeTaskDetails(Task task, HBox hbox) {

    }

    @Override
    public void renameTask(Task task, HBox hbox) {

    }
}
