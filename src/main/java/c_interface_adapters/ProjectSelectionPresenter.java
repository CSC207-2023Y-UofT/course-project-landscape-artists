package c_interface_adapters;

import b_application_business_rules.boundaries.ProjectSelectionOutputBoundary;
import b_application_business_rules.entity_models.ProjectModel;
import c_interface_adapters.view_models.ColumnViewModel;
import c_interface_adapters.view_models.ProjectSelectionViewModel;
import c_interface_adapters.view_models.ProjectViewModel;
import c_interface_adapters.view_models.TaskViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static javafx.scene.control.PopupControl.USE_PREF_SIZE;

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
    private ProjectSelectionController controller;

//    /**
//     * Constructs a new ProjectSelectionPresenter object with the provided ProjectSelectionController.
//     *
//     * @param projectSelectionController The ProjectSelectionController associated with this presenter. The controller
//     *                                   handles user interactions and delegates tasks to the presenter for processing
//     *                                   project selection and creation actions.
//     */
    public void setController(ProjectSelectionController controller) {
        this.controller = controller;
    }

    /**
     * Sets the JavaFX Stage to be used for displaying scenes.
     *
     * @param stage The JavaFX Stage to set.
     */
    public void setStage(Stage stage) {
        System.out.println("STAGE IS SET");
        this.stage = stage;
    }

    public ProjectSelectionPresenter( ) {
    }

    public ProjectSelectionPresenter(ProjectSelectionController controller) {
        this.controller = controller;
    }

    /**
     * Starts the JavaFX application by initializing the scene with the provided stage.
     *
     * @param stage The JavaFX Stage to be used for displaying the scene.
     * @throws Exception If any exception occurs during application startup.
     */
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("START IS CALLED");
        initializeScene(stage);
        setStage(stage);
        getDatabaseShit();
        populateProjectSelectionUI();
    }

    public void getDatabaseShit() {
        //         Grab data from database and display it in the scene. An example
//         would be something like below (Currently don't know which layer to
//         get all projects):
//         Gateway gateway = new Gateway();
//         List<Project> allProjectsInSystem = gateway.getAllProjects();
//        TODO: TEMPORARY IMPLEMENTATION FOR TESTING PURPOSES ------------------
        List<TaskViewModel> TaskList = Arrays.asList(
                new TaskViewModel("Task1", UUID.randomUUID(), "Task1", true,
                        LocalDateTime.now()),
                new TaskViewModel("Task2", UUID.randomUUID(), "Task2", true,
                        LocalDateTime.now()));
        List<ColumnViewModel> ColumnsList = Arrays.asList(
                new ColumnViewModel("COLUMN 1", TaskList, UUID.randomUUID()),
                new ColumnViewModel("COLUMN 2", new ArrayList<>(), UUID.randomUUID())
        );
        ProjectViewModel p1 = new ProjectViewModel(
                "Project 111111", UUID.randomUUID(),"P1 description",  ColumnsList
        );

        ProjectViewModel p2 = new ProjectViewModel(
                "Project 111111", UUID.randomUUID(),"P2 description",  ColumnsList
        );

        ProjectViewModel p3 = new ProjectViewModel(
                "Project 111111", UUID.randomUUID(),"P2 description",  ColumnsList
        );


        List<ProjectViewModel> projectsInSystem = Arrays.asList(p1, p2, p3);
        projectSelectionViewModel = new ProjectSelectionViewModel(projectsInSystem);
//        TODO: END ------------------------------------------------------------
        // Populate the project selection UI with the projects
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
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectSelectionPresenter.class.getResource("ProjectSelection.fxml"));
            Parent root = fxmlLoader.load();

            // Now that the FXML file is loaded, you can get the controller
            controller = fxmlLoader.getController();
            System.out.println("CONTROLLER: " + controller);

            Scene scene = new Scene(root);
            stage.setTitle("Choose project");
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("ProjectSelectionStyle.css").toExternalForm());
            stage.show();
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
        System.out.println("SETTING THE PROJECTSELECTIONVIEWMODEL FOR THE PRESENTER");
        System.out.println("HERE IT IS: " + projectSelectionViewModel);
        this.projectSelectionViewModel = projectSelectionViewModel;
    }

    /**
     * Populates the project selection UI with the list of projects retrieved from the ViewModel.
     * Projects are displayed in a GridPane, with each project represented by a button.
     * Each button allows the user to open the corresponding project or perform actions on it,
     * such as renaming or deleting the project.
     */
    public void findProjectsGrid() {
        System.out.println("STAGE IS: " + stage);
        Scene currentScene = stage.getScene();
        System.out.println("CURRENT SCENE" + currentScene);
        if (currentScene != null) {
            for (Node node : currentScene.getRoot().getChildrenUnmodifiable()) {
                System.out.println(node);
                if (node instanceof GridPane) {
                    if (node.getId().equals("projectsGrid")) {
                        System.out.println("FOUND THE FREAKING GRIDPANE");
                        populateProjectSelectionUI2((GridPane) node);
                        break;
                    }
                }
            }
        }
    }

    private void populateProjectSelectionUI2(GridPane projectsGrid) {
        projectsGrid.setHgap(20);
        projectsGrid.setVgap(100);

        for (int col = 0; col < 2; col++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setHgrow(Priority.ALWAYS);
            columnConstraints.setFillWidth(true);
        }

        int row = 0;
        int col = 0;

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.ALWAYS);
        rowConstraints.setFillHeight(true);
        projectsGrid.getRowConstraints().add(rowConstraints);
        System.out.println(projectSelectionViewModel);

        while (projectSelectionViewModel.hasNext()) {
            ProjectViewModel project = projectSelectionViewModel.next();

            // Create the currentProjectButton

            Label projectName = new Label(project.getName());
            Label projectDescription = new Label(project.getDescription());

            projectName.setId("projectName");


            projectName.setFont(Font.font("Arial", FontWeight.BOLD, 15));

            VBox nameAndDescriptionContainer = new VBox(projectName, projectDescription);

            nameAndDescriptionContainer.setAlignment(Pos.CENTER);
            nameAndDescriptionContainer.setPadding(new Insets(10));
            nameAndDescriptionContainer.setSpacing(5);

            projectName.setMaxWidth(150);
            projectDescription.setMaxWidth(150);
            projectName.setWrapText(true);
            projectDescription.setWrapText(true);

            // Center the text in each label
            projectName.setAlignment(Pos.CENTER);
            projectDescription.setAlignment(Pos.CENTER);

            Button currentProjectButton = new Button();

            currentProjectButton.setGraphic(nameAndDescriptionContainer);

            currentProjectButton.getStyleClass().add("current-project-button");


            currentProjectButton.setUserData(project.getID());
            currentProjectButton.setOnAction(event -> {controller.handleChosenProjectButton(event);});
            currentProjectButton.setWrapText(true); // Allow the button to wrap its text and show the whole content
            currentProjectButton.setMinWidth(USE_PREF_SIZE); // Allow the button to resize based on its content
            currentProjectButton.setMaxWidth(Double.MAX_VALUE); // Allow the button to take up available space

            // Create the MenuButton
            MenuButton menuButton = new MenuButton();
            MenuItem renameProjectMenuItem = new MenuItem("Rename Project");
            MenuItem deleteProjectMenuItem = new MenuItem("Delete Project");

            menuButton.getStyleClass().add("menu-button-custom");

            // Add event handlers for the MenuItems
            renameProjectMenuItem.setOnAction(event -> controller.handleRenameProject(project.getID()));
            deleteProjectMenuItem.setOnAction(event -> controller.handleDeleteProject(project.getID()));

            // Add MenuItems to the MenuButton
            menuButton.getItems().addAll(renameProjectMenuItem, deleteProjectMenuItem);


            // Add currentProjectButton and menuButton to a container (HBox) for better layout control
            HBox buttonContainer = new HBox(currentProjectButton, menuButton);
            buttonContainer.setId(project.getID().toString());
            projectsGrid.add(buttonContainer, col, row);

            col++;
            if (col >= 2) {
                col = 0;
                row++;
            }
        }
        System.out.println("CONTROLLER" + controller);
        addCreateProjectButton(col, row);
    }

    void addCreateProjectButton(int col, int row) {
        System.out.println("ADD CREATE PROJECT BUTTON IS CALLED");
        Button createProjectButton = new Button("+");
        createProjectButton.setOnAction(this::handleCreateProjectPopup);

        createProjectButton.getStyleClass().add("create-project-button-style");

        projectsGrid.add(createProjectButton, col, row);
    }
}
