package c_interface_adapters;

import b_application_business_rules.boundaries.ProjectSelectionOutputBoundary;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The ProjectSelectionPresenter class is responsible for managing the presentation logic for the
 * Project Selection functionality in the JavaFX application. It implements the
 * ProjectSelectionOutputBoundary interface to handle the presentation and display of the current
 * project details.
 */
public class ProjectSelectionPresenter extends Application implements ProjectSelectionOutputBoundary {


    // The JavaFX Stage used for displaying scenes
    private Stage stage;

    /**
     * Sets the JavaFX Stage to be used for displaying scenes.
     *
     * @param stage The JavaFX Stage to set.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Starts the JavaFX application by initializing the scene with the provided stage.
     *
     * @param stage The JavaFX Stage to be used for displaying the scene.
     * @throws Exception If any exception occurs during application startup.
     */
    @Override
    public void start(Stage stage) throws Exception {
        setStage(stage);
        initializeScene(stage);
    }

    /**
     * Displays the current project by loading and setting the scene with the appropriate FXML file.
     * The FXML file contains the layout and UI elements for viewing and modifying the project details.
     */
    @Override
    public void displayCurrentProject() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ProjectViewingAndModification.fxml"));
            stage.setTitle("scene 2");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes the scene with the provided stage by loading the FXML file containing the layout
     * and UI elements for choosing a project.
     *
     * @param stage The JavaFX Stage to be used for displaying the project selection scene.
     */
    public void initializeScene(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectSelectionPresenter.class.getResource(
                    "ProjectSelection.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage1 = stage;
            stage1.setTitle("Choose project");
            stage1.setScene(scene);
            stage1.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
