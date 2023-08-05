package c_interface_adapters;

import b_application_business_rules.boundaries.ProjectSelectionOutputBoundary;
import b_application_business_rules.entity_models.ProjectModel;
import c_interface_adapters.view_models.ProjectSelectionViewModel;
import c_interface_adapters.view_models.ProjectViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Iterator;

/**
 * The ProjectSelectionPresenter class is responsible for managing the presentation logic for the
 * Project Selection functionality in the JavaFX application. It implements the
 * ProjectSelectionOutputBoundary interface to handle the presentation and display of the current
 * project details.
 */
public class ProjectSelectionPresenter extends Application implements ProjectSelectionOutputBoundary {


    // The JavaFX Stage used for displaying scenes
    private Stage stage;
    private ProjectSelectionViewModel projectSelectionViewModel;

    /**
     * Constructs a new ProjectSelectionPresenter object with the provided ProjectSelectionController.
     *
     * @param projectSelectionController The ProjectSelectionController associated with this presenter. The controller
     *                                   handles user interactions and delegates tasks to the presenter for processing
     *                                   project selection and creation actions.
     */


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

    @Override
    public void displayCurrentProject() {

    }

    @Override
    public void projectCreated(ProjectModel projectModel) {

    }

    @Override
    public void projectCreationFailed(String errorMessage) {

    }

    /**
     *
     */
//    @Override
//    public void displayCurrentProject() {
//
//    }
//
//    /**
//     * @param projectModel
//     */
//    @Override
//    public void projectCreated(ProjectModel projectModel) {
//
//    }
//
//    /**
//     * @param errorMessage
//     */
//    @Override
//    public void projectCreationFailed(String errorMessage) {
//
//    }

    /**
     * Displays the current project by loading and setting the scene with the appropriate FXML file.
     * The FXML file contains the layout and UI elements for viewing and modifying the project details.
     */
    @Override
    public void displayCurrentProject(ProjectModel projectModel) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProjectViewingAndModification.fxml"));
            Parent root = fxmlLoader.load();
            ProjectViewingAndModificationController openedProjectController = fxmlLoader.getController();
            openedProjectController.setup(projectModel);
            stage.setTitle("scene 2");
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("ProjectViewingAndModificationStyle.css").toExternalForm());
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Displays the renamed project on the user interface. When a project is renamed, this method is called to
     * update the corresponding HBox in the projectsGrid (GridPane) with the new project name. The HBox represents
     * the renamed project on the UI.
     *
     * @param projectModel The ProjectModel of the renamed project containing the updated project information.
     */
    public void displayRenamedProject(ProjectModel projectModel) {
        // Get the UUID of the renamed project and its new name
        String projectUUID = projectModel.getID().toString();
        String newProjectName = projectModel.getName();

        // Get the current scene of the stage
        Scene scene = stage.getScene();
        if (scene != null) {
            // Find the GridPane that holds the projects (projectsGrid)
            for (Node node : scene.getRoot().getChildrenUnmodifiable()) {
                if (node instanceof GridPane projectsGrid) {
                    // Iterate over the children of the projectsGrid (HBoxes representing projects)
                    for (Node gridChild : projectsGrid.getChildren()) {
                        if (gridChild instanceof HBox hbox) {
                            // Check if the HBox ID matches the UUID of the renamed project
                            Object hboxId = hbox.getId(); // Assuming you set the projectUUID as hboxId of the HBox
                            if (hboxId != null && hboxId.equals(projectUUID)) {
                                // The HBox matches the provided projectUUID
                                // Now, find the projectNameButton inside the HBox
                                for (Node hboxChild : hbox.getChildren()) {
                                    if (hboxChild instanceof Button nameAndDescriptionButton) {
                                        VBox nameAndDescriptionContainer =
                                                (VBox) (nameAndDescriptionButton.getGraphic());

                                        for (Node nodeInNameAndDescriptionContainer:
                                                nameAndDescriptionContainer.getChildren()) {
                                            if (nodeInNameAndDescriptionContainer.getId().equals("projectName")) {
                                                Label projectName = (Label) nodeInNameAndDescriptionContainer;
                                                projectName.setText(newProjectName);
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }
    }


    /**
     * Displays the deleted project on the user interface. When a project is deleted, this method is called to
     * remove the corresponding HBox from the projectsGrid (GridPane) in the scene. The HBox represents the
     * deleted project on the UI.
     *
     * @param projectModel The ProjectModel of the project to be deleted from the UI.
     */
    public void displayDeletedProject(ProjectModel projectModel) {
        // Get the UUID of the project to be deleted
        String projectUUID = projectModel.getID().toString();

        // Get the current scene of the stage
        Scene scene = stage.getScene();
        if (scene != null) {
            // Find the GridPane that holds the projects (projectsGrid)
            for (Node node : scene.getRoot().getChildrenUnmodifiable()) {
                if (node instanceof GridPane projectsGrid) {
                    int numColumns = 2; // Specify the number of columns
                    int numRows = projectsGrid.getRowCount(); // Get the number of rows currently in the grid

                    // Iterate over the children of the projectsGrid (HBoxes representing projects)
                    Iterator<Node> iterator = projectsGrid.getChildren().iterator();
                    while (iterator.hasNext()) {
                        Node gridChild = iterator.next();
                        if (gridChild instanceof HBox hbox) {
                            // Check if the HBox ID matches the UUID of the project to be deleted
                            Object hboxId = hbox.getId(); // Assuming you set the projectUUID as hboxId of the HBox
                            if (hboxId != null && hboxId.equals(projectUUID)) {
                                iterator.remove(); // Use the iterator to safely remove the HBox from the projectsGrid
                                break;
                            }
                        }
                    }

                    // Rearrange the remaining nodes to fill the empty spaces
                    int col = 0;
                    int row = 0;
                    for (Node child : projectsGrid.getChildren()) {
                        GridPane.setColumnIndex(child, col);
                        GridPane.setRowIndex(child, row);

                        col++;
                        if (col >= numColumns) {
                            col = 0;
                            row++;
                        }
                    }

                    // Fill the empty spaces with new HBoxes if needed
                    while (row < numRows) {
                        HBox placeholderHBox = new HBox();
                        GridPane.setColumnIndex(placeholderHBox, col);
                        GridPane.setRowIndex(placeholderHBox, row);
                        projectsGrid.getChildren().add(placeholderHBox);

                        col++;
                        if (col >= numColumns) {
                            col = 0;
                            row++;
                        }
                    }

                    break;
                }
            }
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
            scene.getStylesheets().add(getClass().getResource("ProjectSelectionStyle.css").toExternalForm());
            stage1.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the ProjectSelectionViewModel for the ProjectSelectionPresenter.
     *
     * @param projectSelectionViewModel The ProjectSelectionViewModel containing the data to be displayed in the view.
     *                                  This ViewModel holds information about the projects to be displayed in the
     *                                  project selection UI.
     */
    public void setViewModel(ProjectSelectionViewModel projectSelectionViewModel) {
        this.projectSelectionViewModel = projectSelectionViewModel;
    }

}
