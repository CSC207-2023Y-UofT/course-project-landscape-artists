package c_interface_adapters;

import b_application_business_rules.boundaries.ProjectViewingAndModificationOutputBoundary;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import c_interface_adapters.view_models.ColumnViewModel;
import c_interface_adapters.view_models.ProjectViewModel;
import c_interface_adapters.view_models.TaskViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.UUID;

/**
 * The ProjectViewingAndModificationPresenter class is responsible for managing the presentation
 * logic for viewing and modifying projects in the JavaFX application. It extends the Application
 * class to enable JavaFX initialization, and it implements the ProjectViewingAndModificationOutputBoundary
 * interface to handle interactions with the project viewing and modification scene.
 */
public class ProjectViewingAndModificationPresenter extends Application implements ProjectViewingAndModificationOutputBoundary {

    private Stage stage;
    CurrentProjectRepository currentProjectRepository = CurrentProjectRepository.getInstance();

    /**
     * Initializes the JavaFX application and sets up the initial scene to display the current
     * project details and associated tasks.
     *
     * @param stage The main stage of the JavaFX application.
     * @throws IOException If an I/O error occurs during loading of the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProjectViewingAndModificationPresenter.class.getResource("ProjectViewingAndModification.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Current project");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main entry point for the JavaFX application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Sets the main stage for the presenter.
     *
     * @param stage The main stage of the JavaFX application.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Retrieves the current project from the CurrentProjectRepository.
     *
     * @return The current Project instance.
     */
    @Override
    public Project getCurrentProject() {
        return currentProjectRepository.getCurrentProject();
    }

    /**
     * Displays the scene with all projects when the "Back" button is clicked.
     */
    @Override
    public void displayAllProjects() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ProjectSelection.fxml"));
            stage.setTitle("scene 1");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void displayNewTask(UUID columnBoxID, TaskViewModel newTask) {

    }

    @Override
    public void displayRenamedTask(UUID taskID, TaskViewModel task) {

    }

    @Override
    public void displayRemovedTask(UUID taskID, TaskViewModel task) {

    }

    @Override
    public void displayRenamedColumn(UUID columnUIid, ColumnViewModel column) {

    }

    @Override
    public void displayDeletedColumn(UUID columnUIid, ColumnViewModel column) {

    }

    @Override
    public void dislayChangedTaskDetails(UUID taskID, TaskViewModel task) {

    }

    @Override
    public void dislayChangedTaskDate(UUID taskID, TaskViewModel task) {

    }

    @Override
    public void displayRenamedProject(ProjectViewModel project, UUID projectId) {

    }

    @Override
    public void displayDeleteProject(ProjectViewModel project, UUID projectId) {

    }

    /**
     * Displays a new task in the specified VBox columnBox. The new task is represented by an HBox
     * containing the task name and an options button.
     *
     * @param columnBox The VBox representing the Column UI where the new task will be added.
     * @param newTask   The new Task instance to be displayed.
     */

    public void displayNewTask(VBox columnBox, Task newTask) {
        HBox hbox = new HBox();
        Label taskName = new Label(newTask.getName());
        Button taskOptionsButton = new Button("...");
        taskOptionsButton.setStyle("-fx-font-size: 8px;");
        hbox.getChildren().add(taskName);
        hbox.getChildren().add(taskOptionsButton);

        int buttonIndex = columnBox.getChildren().size() - 1;
        columnBox.getChildren().add(buttonIndex, hbox);
    }
}