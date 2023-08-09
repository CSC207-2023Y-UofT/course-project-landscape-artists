package c_interface_adapters;

import b_application_business_rules.boundaries.ProjectSelectionOutputBoundary;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import c_interface_adapters.view_models.ColumnViewModel;
import c_interface_adapters.view_models.ProjectSelectionViewModel;
import c_interface_adapters.view_models.ProjectViewModel;
import c_interface_adapters.view_models.TaskViewModel;
import d_frameworks_and_drivers.database_management.DBControllers.EntityIDstoModelController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Pair;

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
    private static Stage stage;
    private ProjectSelectionViewModel projectSelectionViewModel;
    private ProjectSelectionController controller;

    private int currentColumn = 0;
    private int currentRow = 0;

    /**
     * Overloads the ProjectSelectionPresenter for when no controller is passed.
     */
    public ProjectSelectionPresenter() {
        this.controller = new ProjectSelectionController();
    }

    /**
     * This constructor ensures that the Presenter has access to the Controller class. This is essential to assign
     * event handlers located in the Controller
     */
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
    public void start(Stage stage) {
        try {
            initializeScene(stage);
            getProjectsFromDatabase();
            populateProjectSelectionUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the scene with the provided stage by loading the FXML file containing the layout
     * and UI elements for choosing a project. It also updates the Presenter's Controller to be the controller from
     * the FXML file.
     *
     * @param primaryStage The JavaFX Stage to be used for displaying the project selection scene.
     */
    public void initializeScene(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectSelectionPresenter.class.getResource("ProjectSelection.fxml"));
            Parent root = fxmlLoader.load();

            // Now that the FXML file is loaded, you can get the controller
            controller = fxmlLoader.getController();

            Scene scene = new Scene(root);
            this.stage = primaryStage;
            System.out.println("IN INITIALZIE SCENE, THIS IS STAGE " + stage);
            stage.setTitle("Choose project");
            System.out.println("CLASS INSTANCE AT initializeScene " + this);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("ProjectSelectionStyle.css").toExternalForm());
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method interacts with the database to get ProjectModels. Afterwards, projectSelectionViewModel is updated
     * to hold these ProjectViewModels for better access.
     *
     */
    public void getProjectsFromDatabase() {
        //         Grab data from database and display it in the scene. An example
//         would be something like below (Currently don't know which layer to
//         get all projects):
//         Gateway gateway = new Gateway();
//         List<Project> allProjectsInSystem = gateway.getAllProjects();
//        TODO: TEMPORARY IMPLEMENTATION FOR TESTING PURPOSES ------------------

        List<TaskViewModel> TaskList = new ArrayList<>();
        TaskList.add(new TaskViewModel("Task1", UUID.randomUUID(), "Task1", true,
                LocalDateTime.now()));
        TaskList.add(new TaskViewModel("Task2", UUID.randomUUID(), "Task2", true,
                LocalDateTime.now()));

        List<ColumnViewModel> ColumnsList = new ArrayList<>();
        ColumnsList.add(new ColumnViewModel("COLUMN 1", TaskList, UUID.randomUUID()));
        ColumnsList.add(new ColumnViewModel("COLUMN 2", new ArrayList<>(), UUID.randomUUID()));


        ProjectViewModel p1 = new ProjectViewModel(
                "Project 111111", UUID.randomUUID(),"P1 description",  ColumnsList
        );


        List<ProjectViewModel> projectsInSystem = new ArrayList<>();
        projectsInSystem.add(p1);
//        TODO: END ------------------------------------------------------------

        DBAdapterInterface dbAdapterInterface = new EntityIDstoModelController();
        List<ProjectModel> AllProjectsList = dbAdapterInterface.IDstoProjectModelList();

        for (ProjectModel proj :AllProjectsList) {
            if(proj != null){
                projectsInSystem.add(new ProjectViewModel(proj));
            } else {
                continue;
            }

            List<ColumnModel> colsFromProject = proj.getColumnModels();
            for (ColumnModel column : colsFromProject) {
                if(column != null){
                    ColumnsList.add(new ColumnViewModel(column));
                } else {
                    continue;
                }

                List<TaskModel> tasksFromColumn = column.getTaskModels();

                for (TaskModel tasks: tasksFromColumn) {
                    if(tasks != null){
                        TaskList.add(new TaskViewModel(tasks));
                    }
                }

            }

        }

        projectSelectionViewModel = new ProjectSelectionViewModel(projectsInSystem);
    }


//    private void populateProjectSelectionUI() {
//        GridPane projectsGrid = findGridPane();
//
//        projectsGrid.setHgap(20);
//        projectsGrid.setVgap(100);
//
//        for (int col = 0; col < 2; col++) {
//            ColumnConstraints columnConstraints = new ColumnConstraints();
//            columnConstraints.setHgrow(Priority.ALWAYS);
//            columnConstraints.setFillWidth(true);
//        }
//
//        int row = 0;
//        int col = 0;
//
//        RowConstraints rowConstraints = new RowConstraints();
//        rowConstraints.setVgrow(Priority.ALWAYS);
//        rowConstraints.setFillHeight(true);
//        projectsGrid.getRowConstraints().add(rowConstraints);
//
//        while (projectSelectionViewModel.hasNext()) {
//            ProjectViewModel project = projectSelectionViewModel.next();
//
//            // Create the currentProjectButton
//
//            Label projectName = new Label(project.getName());
//            Label projectDescription = new Label(project.getDescription());
//
//            projectName.setId("projectName");
//            projectDescription.setId("projectDescription");
//
//
//
//            projectName.setFont(Font.font("Arial", FontWeight.BOLD, 15));
//
//            VBox nameAndDescriptionContainer = new VBox(projectName, projectDescription);
//
//            nameAndDescriptionContainer.setAlignment(Pos.CENTER);
//            nameAndDescriptionContainer.setPadding(new Insets(10));
//            nameAndDescriptionContainer.setSpacing(5);
//
//            projectName.setMaxWidth(150);
//            projectDescription.setMaxWidth(150);
//            projectName.setWrapText(true);
//            projectDescription.setWrapText(true);
//
//            // Center the text in each label
//            projectName.setAlignment(Pos.CENTER);
//            projectDescription.setAlignment(Pos.CENTER);
//
//            Button currentProjectButton = new Button();
//
//            currentProjectButton.setGraphic(nameAndDescriptionContainer);
//
//            currentProjectButton.getStyleClass().add("current-project-button");
//
//
//            currentProjectButton.setUserData(project.getID());
//            currentProjectButton.setOnAction(event -> {controller.handleChosenProjectButton(event);});
//            currentProjectButton.setWrapText(true); // Allow the button to wrap its text and show the whole content
//            currentProjectButton.setMinWidth(USE_PREF_SIZE); // Allow the button to resize based on its content
//            currentProjectButton.setMaxWidth(Double.MAX_VALUE); // Allow the button to take up available space
//
//            // Create the MenuButton
//            MenuButton menuButton = new MenuButton();
//            MenuItem renameProjectMenuItem = new MenuItem("Rename Project");
//            MenuItem deleteProjectMenuItem = new MenuItem("Delete Project");
//
//            menuButton.getStyleClass().add("menu-button-custom");
//
//            // Add event handlers for the MenuItems
//            renameProjectMenuItem.setOnAction(event -> controller.handleRenameProject(project.getID()));
//            deleteProjectMenuItem.setOnAction(event -> controller.handleDeleteProject(project.getID()));
//
//            // Add MenuItems to the MenuButton
//            menuButton.getItems().addAll(renameProjectMenuItem, deleteProjectMenuItem);
//
//
//            // Add currentProjectButton and menuButton to a container (HBox) for better layout control
//            HBox buttonContainer = new HBox(currentProjectButton, menuButton);
//            buttonContainer.setId(project.getID().toString());
//            projectsGrid.add(buttonContainer, col, row);
//
//            col++;
//            if (col >= 2) {
//                col = 0;
//                row++;
//            }
//        }
//        addCreateProjectButton(col, row);
//    }

    /**
     * This populates the projectsGrid GridPane with Project UIs. The Projects are held in projectSelectionViewModel.

     */
    private void populateProjectSelectionUI() {
        configureProjectsGrid();
        populateProjectButtons();
        addCreateProjectButton();
    }

    /**
     * Configures the projects grid by setting horizontal and vertical gaps and applying column and row constraints.
     */
    private void configureProjectsGrid() {
        GridPane projectsGrid = findGridPane();
        projectsGrid.setHgap(20);
        projectsGrid.setVgap(100);
        setColumnAndRowConstraints(projectsGrid);
    }

    /**
     * Sets column and row constraints for the provided GridPane.
     *
     * @param projectsGrid The GridPane to set constraints for.
     */
    private void setColumnAndRowConstraints(GridPane projectsGrid) {
        for (int col = 0; col < 2; col++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setHgrow(Priority.ALWAYS);
            columnConstraints.setFillWidth(true);
        }

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.ALWAYS);
        rowConstraints.setFillHeight(true);
        projectsGrid.getRowConstraints().add(rowConstraints);
    }

    /**
     * Populates the projects grid with project buttons and their respective menu buttons.
     */
    private void populateProjectButtons() {
        GridPane projectsGrid = findGridPane();

        while (projectSelectionViewModel.hasNext()) {
            ProjectViewModel project = projectSelectionViewModel.next();
            Button currentProjectButton = createCurrentProjectButton(project);
            MenuButton menuButton = createMenuButton(project);
            HBox buttonContainer = createButtonContainer(currentProjectButton, menuButton, project);
            projectsGrid.add(buttonContainer, currentColumn, currentRow);

            currentColumn++;
            if (currentColumn >= 2) {
                currentColumn = 0;
                currentRow++;
            }
        }
    }

    /**
     * Creates a button for the given project.
     *
     * @param project The project for which to create the button.
     * @return The created button.
     */
    private Button createCurrentProjectButton(ProjectViewModel project) {
        Button currentProjectButton = new Button();
        VBox nameAndDescriptionContainer = createNameAndDescriptionContainer(project);
        configureCurrentProjectButton(currentProjectButton, nameAndDescriptionContainer, project);
        currentProjectButton.setUserData(project.getID());
        currentProjectButton.setOnAction(event -> controller.handleChosenProjectButton(event));
        return currentProjectButton;
    }

    /**
     * Creates a VBox container for the project's name and description labels.
     *
     * @param project The project for which to create the container.
     * @return The created VBox container.
     */
    private VBox createNameAndDescriptionContainer(ProjectViewModel project) {
        Label projectName = new Label(project.getName());
        Label projectDescription = new Label(project.getDescription());
        projectName.setId("projectName");
        projectDescription.setId("projectDescription");
        projectName.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        projectName.setMaxWidth(150);
        projectDescription.setMaxWidth(150);
        projectName.setWrapText(true);
        projectDescription.setWrapText(true);
        projectName.setAlignment(Pos.CENTER);
        projectDescription.setAlignment(Pos.CENTER);
        return new VBox(projectName, projectDescription);
    }

    /**
     * Configures the appearance of the current project button.
     *
     * @param currentProjectButton    The current project button to configure.
     * @param nameAndDescriptionContainer The VBox container with project name and description labels.
     * @param project                 The project associated with the button.
     */
    private void configureCurrentProjectButton(Button currentProjectButton, VBox nameAndDescriptionContainer, ProjectViewModel project) {
        currentProjectButton.setGraphic(nameAndDescriptionContainer);
        currentProjectButton.getStyleClass().add("current-project-button");
        currentProjectButton.setWrapText(true);
        currentProjectButton.setMinWidth(USE_PREF_SIZE);
        currentProjectButton.setMaxWidth(Double.MAX_VALUE);
    }

    /**
     * Creates a menu button for the given project with rename and delete options.
     *
     * @param project The project for which to create the menu button.
     * @return The created menu button.
     */
    private MenuButton createMenuButton(ProjectViewModel project) {
        MenuButton menuButton = new MenuButton();
        MenuItem renameProjectMenuItem = new MenuItem("Rename Project");
        MenuItem deleteProjectMenuItem = new MenuItem("Delete Project");
        menuButton.getStyleClass().add("menu-button-custom");
        renameProjectMenuItem.setOnAction(event -> controller.handleRenameProject(project.getID()));
        deleteProjectMenuItem.setOnAction(event -> controller.handleDeleteProject(project.getID()));
        menuButton.getItems().addAll(renameProjectMenuItem, deleteProjectMenuItem);
        return menuButton;
    }

    /**
     * Creates a container for the current project button and menu button.
     *
     * @param currentProjectButton The current project button.
     * @param menuButton           The menu button.
     * @param project              The project associated with the buttons.
     * @return The created HBox container.
     */
    private HBox createButtonContainer(Button currentProjectButton, MenuButton menuButton, ProjectViewModel project) {
        HBox buttonContainer = new HBox(currentProjectButton, menuButton);
        buttonContainer.setId(project.getID().toString());
        return buttonContainer;
    }

    /**
     * Adds a "Create Project" button to the projects grid.
     */
    private void addCreateProjectButton() {
        GridPane projectsGrid = findGridPane();
        Button createProjectButton = createCreateProjectButton();
        projectsGrid.add(createProjectButton, getCurrentColumn(), getCurrentRow());
    }


    /**
     * Creates a "Create Project" button with appropriate styling and action handling.
     *
     * @return The created "Create Project" button.
     */
    private Button createCreateProjectButton() {
        Button createProjectButton = new Button("+");
        createProjectButton.setOnAction(this::showCreateProjectDialog);
        createProjectButton.getStyleClass().add("create-project-button-style");
        return createProjectButton;
    }


    /**
     * Retrieves the current column index for positioning elements in the grid.
     *
     * @return The current column index.
     */
    private int getCurrentColumn() {
        return currentColumn;
    }

    /**
     * Retrieves the current row index for positioning elements in the grid.
     *
     * @return The current row index.
     */
    private int getCurrentRow() {
        return currentRow;
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


//    /**
//     * Displays the current project by loading and setting the scene with the appropriate FXML file.
//     * The FXML file contains the layout and UI elements for viewing and modifying the project details.
//     * This switches from the ProjectSelection UI to ProjectViewingAndModification UI.
//     */
//    @Override
//    public void displayCurrentProject(ProjectModel projectModel) {
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProjectViewingAndModification.fxml"));
//            Parent root = fxmlLoader.load();
//            ProjectViewingAndModificationController openedProjectController = fxmlLoader.getController();
//            openedProjectController.setup(projectModel);
//
//            stage.setTitle("scene 2");
//            Scene scene = new Scene(root);
//
//            scene.getStylesheets().add(getClass().getResource("ProjectViewingAndModificationStyle.css").toExternalForm());
//            stage.setScene(scene);
//            stage.setScene(scene);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
    /**
     * Displays the provided projectModel to the UI.
     *
     * @param projectModel The ProjectModel containing the project details to be displayed.
     */
    public void displayCurrentProject(ProjectModel projectModel) {
        try {
//            System.out.println("displayCurrentProject method is called.");
//            FXMLLoader fxmlLoader = loadFXML("ProjectViewingAndModification.fxml");
//            Parent root = fxmlLoader.load();
//            ProjectViewingAndModificationController openedProjectController = getController(fxmlLoader);
//            openedProjectController.setup(projectModel);
            // MAKE THIS GO TO PRESENTER START

            ProjectViewingAndModificationPresenter nextPresenter = new ProjectViewingAndModificationPresenter();
            nextPresenter.start(stage, projectModel);



//            setUpScene(root, "scene 2", "ProjectViewingAndModificationStyle.css");
        } catch (IOException e) {
            throw new RuntimeException("Error while displaying current project.", e);
        }
    }


    /**
     * Loads an FXML layout from the specified resource path.
     *
     * @param resourcePath The path to the FXML resource.
     * @return The FXMLLoader instance for the loaded FXML layout.
     * @throws IOException If an I/O error occurs during resource loading.
     */
    private FXMLLoader loadFXML(String resourcePath) throws IOException {
        return new FXMLLoader(getClass().getResource(resourcePath));
    }

    private <T> T getController(FXMLLoader fxmlLoader) {
        return fxmlLoader.getController();
    }

    /**
     * Sets up the scene for displaying the UI to the stage.
     *
     * @param root The root node of the UI scene.
     * @param title The title of the stage.
     * @param stylesheetPath The path to the CSS stylesheet for styling the UI.
     */
    private void setUpScene(Parent root, String title, String stylesheetPath) {
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource(stylesheetPath).toExternalForm());
        stage.setScene(scene);
        stage.setTitle(title);
    }

//    /**
//     * Displays the renamed project on the user interface. When a project is renamed, this method is called to
//     * update the corresponding HBox in the projectsGrid (GridPane) with the new project name. The HBox represents
//     * the renamed project on the UI.
//     *
//     * @param projectModel The ProjectModel of the renamed project containing the updated project information.
//     */
//    public void displayRenamedProject(ProjectModel projectModel) {
//        // Get the UUID of the renamed project and its new name
//        String projectUUID = projectModel.getID().toString();
//        String newProjectName = projectModel.getName();
//        String newProjectDescription = projectModel.getDescription();
//
//        // Get the current scene of the stage
//        Scene scene = stage.getScene();
//        if (scene != null) {
//            // Find the GridPane that holds the projects (projectsGrid)
//            for (Node node : scene.getRoot().getChildrenUnmodifiable()) {
//                if (node instanceof GridPane projectsGrid) {
//                    // Iterate over the children of the projectsGrid (HBoxes representing projects)
//                    for (Node gridChild : projectsGrid.getChildren()) {
//                        if (gridChild instanceof HBox hbox) {
//                            // Check if the HBox ID matches the UUID of the renamed project
//                            Object hboxId = hbox.getId(); // Assuming you set the projectUUID as hboxId of the HBox
//                            if (hboxId != null && hboxId.equals(projectUUID)) {
//                                // The HBox matches the provided projectUUID
//                                // Now, find the projectNameButton inside the HBox
//                                for (Node hboxChild : hbox.getChildren()) {
//                                    if (hboxChild instanceof Button nameAndDescriptionButton) {
//                                        VBox nameAndDescriptionContainer =
//                                                (VBox) (nameAndDescriptionButton.getGraphic());
//
//                                        for (Node nodeInNameAndDescriptionContainer:
//                                                nameAndDescriptionContainer.getChildren()) {
//                                            if (nodeInNameAndDescriptionContainer.getId().equals("projectName")) {
//                                                Label projectName = (Label) nodeInNameAndDescriptionContainer;
//                                                projectName.setText(newProjectName);
//                                            }
//                                            if (nodeInNameAndDescriptionContainer.getId().equals("projectDescription")) {
//                                                Label projectDescription = (Label) nodeInNameAndDescriptionContainer;
//                                                projectDescription.setText(newProjectDescription);
//                                            }
//                                        }
//                                        break;
//                                    }
//                                }
//                                break;
//                            }
//                        }
//                    }
//                    break;
//                }
//            }
//        }
//    }
    public void displayRenamedProject(ProjectModel projectModel) {
        String projectUUID = projectModel.getID().toString();
        String newProjectName = projectModel.getName();
        String newProjectDescription = projectModel.getDescription();

        GridPane projectsGrid = findGridPane();

        for (Node node : projectsGrid.getChildren()) {
            if (node instanceof HBox && projectUUID.equals(node.getId())) {
                HBox targetHBox = (HBox) node;
                Button nameAndDescriptionButton = findButtonInChildren(targetHBox);

                if (nameAndDescriptionButton != null) {
                    VBox nameAndDescriptionContainer = (VBox) nameAndDescriptionButton.getGraphic();
                    updateNameAndDescription(nameAndDescriptionContainer, newProjectName, newProjectDescription);
                    break; // Exit loop after finding and updating the correct project
                }
            }
        }
    }

    private Button findButtonInChildren(HBox hbox) {
        for (Node hboxChild : hbox.getChildren()) {
            if (hboxChild instanceof Button) {
                return (Button) hboxChild;
            }
        }
        return null;
    }

    private void updateNameAndDescription(VBox container, String newName, String newDescription) {
        for (Node node : container.getChildren()) {
            if (node.getId() != null) {
                if ("projectName".equals(node.getId())) {
                    Label projectName = (Label) node;
                    projectName.setText(newName);
                }
                if ("projectDescription".equals(node.getId())) {
                    Label projectDescription = (Label) node;
                    projectDescription.setText(newDescription);
                }
            }
        }
    }


//    /**
//     * Displays the deleted project on the user interface. When a project is deleted, this method is called to
//     * remove the corresponding HBox from the projectsGrid (GridPane) in the scene. The HBox represents the
//     * deleted project on the UI.
//     *
//     * @param projectModel The ProjectModel of the project to be deleted from the UI.
//     */
//    public void displayDeletedProject(ProjectModel projectModel) {
//        // Get the UUID of the project to be deleted
//        String projectUUID = projectModel.getID().toString();
//
//        // Get the current scene of the stage
//        Scene scene = stage.getScene();
//        if (scene != null) {
//            // Find the GridPane that holds the projects (projectsGrid)
//            for (Node node : scene.getRoot().getChildrenUnmodifiable()) {
//                if (node instanceof GridPane projectsGrid) {
//                    int numColumns = 2; // Specify the number of columns
//                    int numRows = projectsGrid.getRowCount(); // Get the number of rows currently in the grid
//
//                    // Iterate over the children of the projectsGrid (HBoxes representing projects)
//                    Iterator<Node> iterator = projectsGrid.getChildren().iterator();
//                    while (iterator.hasNext()) {
//                        Node gridChild = iterator.next();
//                        if (gridChild instanceof HBox hbox) {
//                            // Check if the HBox ID matches the UUID of the project to be deleted
//                            Object hboxId = hbox.getId(); // Assuming you set the projectUUID as hboxId of the HBox
//                            if (hboxId != null && hboxId.equals(projectUUID)) {
//                                iterator.remove(); // Use the iterator to safely remove the HBox from the projectsGrid
//                                break;
//                            }
//                        }
//                    }
//
//                    // Rearrange the remaining nodes to fill the empty spaces
//                    int col = 0;
//                    int row = 0;
//                    for (Node child : projectsGrid.getChildren()) {
//                        GridPane.setColumnIndex(child, col);
//                        GridPane.setRowIndex(child, row);
//
//                        col++;
//                        if (col >= numColumns) {
//                            col = 0;
//                            row++;
//                        }
//                    }
//
//                    // Fill the empty spaces with new HBoxes if needed
//                    while (row < numRows) {
//                        HBox placeholderHBox = new HBox();
//                        GridPane.setColumnIndex(placeholderHBox, col);
//                        GridPane.setRowIndex(placeholderHBox, row);
//                        projectsGrid.getChildren().add(placeholderHBox);
//
//                        col++;
//                        if (col >= numColumns) {
//                            col = 0;
//                            row++;
//                        }
//                    }
//
//                    break;
//                }
//            }
//        }
//    }
    /**
     * Displays the deleted project on the user interface. When a project is deleted, this method is called to
     * remove the corresponding HBox from the projectsGrid (GridPane) in the scene. The HBox represents the
     * deleted project on the UI.
     *
     * @param projectModel The ProjectModel of the project to be deleted from the UI.
     */
    public void displayDeletedProject(ProjectModel projectModel) {
        String projectUUID = projectModel.getID().toString();

        GridPane projectsGrid = findGridPane();

        if (projectsGrid != null) {
            HBox targetHBox = findHBoxWithId(projectsGrid, projectUUID);

            if (targetHBox != null) {
                removeHBoxAndUpdateLayout(projectsGrid, targetHBox);
            }
        }
    }

    /**
     * Finds the HBox in the provided GridPane that has the specified UUID as its ID.
     *
     * @param projectsGrid The GridPane containing the HBoxes representing projects.
     * @param projectUUID The UUID of the project to be found.
     * @return The HBox representing the project with the specified UUID, or null if not found.
     */
    private HBox findHBoxWithId(GridPane projectsGrid, String projectUUID) {
        for (Node node : projectsGrid.getChildren()) {
            if (node instanceof HBox && projectUUID.equals(node.getId())) {
                return (HBox) node;
            }
        }
        return null;
    }

    /**
     * Removes the specified HBox from the projectsGrid and updates the layout of the GridPane.
     *
     * @param projectsGrid The GridPane containing the HBoxes representing projects.
     * @param targetHBox The HBox to be removed.
     */
    private void removeHBoxAndUpdateLayout(GridPane projectsGrid, HBox targetHBox) {
        int numColumns = 2; // Specify the number of columns

        projectsGrid.getChildren().remove(targetHBox);

        rearrangeGridPaneLayout(projectsGrid, numColumns);
    }

    /**
     * Rearranges the layout of the GridPane after removing an HBox.
     *
     * @param projectsGrid The GridPane containing the HBoxes representing projects.
     * @param numColumns The number of columns in the GridPane.
     */
    private void rearrangeGridPaneLayout(GridPane projectsGrid, int numColumns) {
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

        fillEmptySpacesWithPlaceholders(projectsGrid, row, numColumns);
    }

    /**
     * Fills the empty spaces in the GridPane with placeholder HBoxes.
     *
     * @param projectsGrid The GridPane containing the HBoxes representing projects.
     * @param row The row index where placeholders should be added.
     * @param numColumns The number of columns in the GridPane.
     */
    private void fillEmptySpacesWithPlaceholders(GridPane projectsGrid, int row, int numColumns) {
        while (row < projectsGrid.getRowCount()) {
            HBox placeholderHBox = new HBox();
            GridPane.setColumnIndex(placeholderHBox, 0);
            GridPane.setRowIndex(placeholderHBox, row);
            projectsGrid.getChildren().add(placeholderHBox);

            row++;
        }
    }



    /**
     * This method finds the GridPane (JavaFX) projectsGrid from the current scene. It is used to populate the Grid
     * Pane with
     * Project UI.
     *
     * @return The GridPane object which will hold the Project UI.
     */
    public GridPane findGridPane() {
        Scene currentScene = stage.getScene();
        if (currentScene != null) {
            for (Node node : currentScene.getRoot().getChildrenUnmodifiable()) {
                if (node instanceof GridPane) {
                    if (node.getId().equals("projectsGrid")) {
                        return ((GridPane) node);
                    }
                }
            }
        }
        return null;
    }


//    /**
//     * Handles the "Create Project" Popup for the user to input the necessary information.
//     */
//    private void handleCreateProjectPopup(ActionEvent actionEvent) {
//        // Create a new Dialog
//        Dialog<Pair<String, String>> dialog = new Dialog<>();
//        dialog.setTitle("Create Project");
//
//        // Set the button types (OK and Cancel)
//        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
//
//        // Create labels and text fields for project name and description
//        Label nameLabel = new Label("Project Name:");
//        Label descLabel = new Label("Description:");
//        TextField nameTextField = new TextField();
//        TextField descTextField = new TextField();
//
//        // Create a GridPane to layout the labels and text fields
//        GridPane gridPane = new GridPane();
//        gridPane.add(nameLabel, 0, 0);
//        gridPane.add(nameTextField, 1, 0);
//        gridPane.add(descLabel, 0, 1);
//        gridPane.add(descTextField, 1, 1);
//
//        // Set the content of the dialog to the GridPane
//        dialog.getDialogPane().setContent(gridPane);
//
//        // Convert the result to a Pair object when the OK button is clicked
//        dialog.setResultConverter(dialogButton -> {
//            if (dialogButton == ButtonType.OK) {
//                String projectName = nameTextField.getText();
//                String projectDescription = descTextField.getText();
//                return new Pair<>(projectName, projectDescription);
//            }
//            return null;
//        });
//
//        // Show the dialog and wait for the user to close it
//        dialog.showAndWait().ifPresent(project -> {
//            if (project != null) {
//                // Handle the newly created project here
//                controller.createProject(project.getKey(), project.getValue());
//            }
//        });
//    }

    /**
     * Shows a dialog for creating a new project and returns the user's input.
     *
     * @return An Optional containing the project name and description, or empty if canceled.
     */
    public Optional<Pair<String, String>> showCreateProjectDialog(ActionEvent actionEvent) {
        Dialog<Pair<String, String>> dialog = createDialog();
        setDialogContent(dialog);
        return showDialogAndWait(dialog);
    }

    /**
     * Creates a new dialog for project creation.
     *
     * @return The created dialog.
     */
    private Dialog<Pair<String, String>> createDialog() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Create Project");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        return dialog;
    }

    /**
     * Sets the content of the dialog with labels and text fields for project information.
     *
     * @param dialog The dialog to set content for.
     */
    private void setDialogContent(Dialog<Pair<String, String>> dialog) {
        Label nameLabel = new Label("Project Name:");
        Label descLabel = new Label("Description:");
        TextField nameTextField = new TextField();
        TextField descTextField = new TextField();

        GridPane gridPane = new GridPane();
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameTextField, 1, 0);
        gridPane.add(descLabel, 0, 1);
        gridPane.add(descTextField, 1, 1);

        dialog.getDialogPane().setContent(gridPane);
    }

    /**
     * Shows the dialog, waits for the user to close it, and handles the project creation.
     *
     * @param dialog The dialog to show and wait for.
     * @return An Optional containing the project name and description, or empty if canceled.
     */
    private Optional<Pair<String, String>> showDialogAndWait(Dialog<Pair<String, String>> dialog) {
        dialog.showAndWait().ifPresent(project -> {
            controller.createProject(project.getKey(), project.getValue());
        });
        return Optional.empty(); // If needed, provide meaningful return value
    }

//    /**
//     * Displays a popup for the user to input the new name and description. If either is empty, then show an alert
//     * message saying it is invalid.
//     *
//     * @return An array of strings containing the new project name and description entered by the user, respectively.
//     */
//    public String[] displayRenameProjectPopup() {
//        // Create a dialog to get the new project name and description from the user
//        TextInputDialog dialog = new TextInputDialog();
//        dialog.setTitle("Rename Project");
//        dialog.setHeaderText("Enter the new name and description for the project:");
//
//        // Create two text fields for the user to enter the name and description
//        TextField nameField = new TextField();
//        nameField.setPromptText("New Project Name");
//        TextField descriptionField = new TextField();
//        descriptionField.setPromptText("New Project Description");
//
//        // Set the dialog content to contain the text fields
//        dialog.getDialogPane().setContent(new VBox(nameField, descriptionField));
//
//        // Show the dialog and wait for the user's response
//        Optional<String> result = dialog.showAndWait();
//
//        // Check if the user clicked "OK" and get the user input from the text fields
//        if (result.isPresent()) {
//            String newProjectName = nameField.getText().trim();
//            String newProjectDescription = descriptionField.getText().trim();
//
//            // Check if either field is empty
//            if (newProjectName.isEmpty() || newProjectDescription.isEmpty()) {
//                // Show an error alert if either field is empty
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Invalid Input");
//                alert.setHeaderText("Invalid Project Name or Description");
//                alert.setContentText("Project name and description cannot be empty.\nPlease enter the new name and description.");
//                alert.showAndWait();
//                return null;
//            } else {
//                // Return the valid new project name and description as an array of strings
//                return new String[]{newProjectName, newProjectDescription};
//            }
//        } else {
//            // Return null if the user clicked "Cancel" or closed the dialog
//            return null;
//        }
//    }

    /**
     * Displays a popup for the user to input the new name and description. If either is empty, then show an alert
     * message saying it is invalid.
     *
     * @return An array of strings containing the new project name and description entered by the user, respectively.
     */
    public String[] displayRenameProjectPopup() {
        Optional<String[]> result = showRenameProject();

        return result.orElse(null);
    }
    /**
     * Displays a dialog to rename a project and captures the user's input.
     *
     * @return An optional string array containing the new project name and description if the user provided valid input,
     *         or an empty optional if the user canceled the dialog or provided invalid input.
     */
    public Optional<String[]> showRenameProject() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Rename Project");
        dialog.setHeaderText("Enter the new name and description for the project:");

        TextField nameField = new TextField();
        nameField.setPromptText("New Project Name");
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("New Project Description");

        dialog.getDialogPane().setContent(new VBox(nameField, descriptionField));

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            String newProjectName = nameField.getText().trim();
            String newProjectDescription = descriptionField.getText().trim();

            if (isInputValid(newProjectName, newProjectDescription)) {
                return Optional.of(new String[]{newProjectName, newProjectDescription});
            } else {
                showErrorAlert("Invalid Project Name or Description",
                        "Project name and description cannot be empty.\nPlease enter the new name and description.");
            }
        }

        return Optional.empty();
    }

    /**
     * Validates whether the provided project name and description are not empty.
     *
     * @param newProjectName        The new project name to be validated.
     * @param newProjectDescription The new project description to be validated.
     * @return True if both the project name and description are not empty, false otherwise.
     */
    private boolean isInputValid(String newProjectName, String newProjectDescription) {
        return !newProjectName.isEmpty() && !newProjectDescription.isEmpty();
    }

    /**
     * Displays an error alert dialog with the specified header and content.
     *
     * @param header  The header text of the error alert.
     * @param content The content text of the error alert.
     */
    private void showErrorAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
