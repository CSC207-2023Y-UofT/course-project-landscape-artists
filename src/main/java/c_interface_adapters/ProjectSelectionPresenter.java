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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.*;

/**
 * The ProjectSelectionPresenter class is responsible for managing the presentation logic for the
 * Project Selection functionality in the JavaFX application. It implements the
 * ProjectSelectionOutputBoundary interface to handle the presentation and display of the current
 * project details.
 */
public class ProjectSelectionPresenter extends Application implements ProjectSelectionOutputBoundary {
    // The main stage of the JavaFX application.
    static Stage stage;

    // The ViewModel for project selection.
    private ProjectSelectionViewModel projectSelectionViewModel;

    // The controller for project selection.
    ProjectSelectionController controller;

    // The current column index for grid layout.
    private int currentColumn = 0;

    // The current row index for grid layout.
    private int currentRow = 0;

    // Text field for entering project name.
    TextField nameTextField;

    // Text field for entering project description.
    TextField descTextField;

    // Utility for locating UI components.
    static UIComponentLocator uiComponentLocator;


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
            uiComponentLocator = new UIComponentLocator(scene);
            stage.setTitle("Choose project");
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
        List<TaskViewModel> TaskList = new ArrayList<>();
        List<ColumnViewModel> ColumnsList = new ArrayList<>();
        List<ProjectViewModel> projectsInSystem = new ArrayList<>();


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

        controller.interactor.setAllProjects(AllProjectsList);
        projectSelectionViewModel = new ProjectSelectionViewModel(projectsInSystem);
    }

    /**
     * This populates the projectsGrid GridPane with Project UIs. The Projects are held in projectSelectionViewModel.

     */
    private void populateProjectSelectionUI() {
        new PresenterUtility().configureProjectsGrid();
        populateProjectButtons();
        new PresenterUtility().addCreateProjectButton(this);
    }

    /**
     * Populates the projects grid with project buttons and their respective menu buttons.
     */
    private void populateProjectButtons() {
        GridPane projectsGrid = uiComponentLocator.findGridPane();

        while (projectSelectionViewModel.hasNext()) {
            ProjectViewModel project = projectSelectionViewModel.next();
            Button currentProjectButton = new PresenterUtility().createCurrentProjectButton(project, this);
            MenuButton menuButton = new PresenterUtility().createMenuButton(project, this);
            HBox buttonContainer = new PresenterUtility().createButtonContainer(currentProjectButton, menuButton, project);


            GridPane.setMargin(buttonContainer, new Insets(0, 0, 50, 20));
            projectsGrid.add(buttonContainer, currentColumn, currentRow);

            currentColumn++;
            if (currentColumn >= 2) {
                currentColumn = 0;

                // set the column constraints to be fixed.
                RowConstraints rowConstraints = new RowConstraints(100);
                projectsGrid.getRowConstraints().add(currentRow, rowConstraints);
                currentRow++;

            }
        }
    }


    /**
     * Retrieves the current column index for positioning elements in the grid.
     *
     * @return The current column index.
     */
    int getCurrentColumn() {
        return currentColumn;
    }

    /**
     * Retrieves the current row index for positioning elements in the grid.
     *
     * @return The current row index.
     */
    int getCurrentRow() {
        return currentRow;
    }

    /**
     * Displays the provided projectModel to the UI.
     *
     * @param projectModel The ProjectModel containing the project details to be displayed.
     */
    public void displayCurrentProject(ProjectModel projectModel) {
            ProjectViewingAndModificationPresenter nextPresenter = new ProjectViewingAndModificationPresenter();
            nextPresenter.setUpProjectViewingAndModificationScene(stage, projectModel);
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

    /**
     * Updates the display of a renamed project in the UI. This method locates the HBox representing
     * the project using its unique identifier and then updates the project name and description labels.
     *
     * @param projectModel The ProjectModel containing the updated project information.
     */
    public void displayRenamedProject(ProjectModel projectModel) {
        String projectUUID = projectModel.getID().toString();
        String newProjectName = projectModel.getName();
        String newProjectDescription = projectModel.getDescription();

        GridPane projectsGrid = uiComponentLocator.findGridPane();

        for (Node node : projectsGrid.getChildren()) {
            if (node instanceof HBox && projectUUID.equals(node.getId())) {
                HBox targetHBox = (HBox) node;
                Button nameAndDescriptionButton = uiComponentLocator.findButtonInChildren(targetHBox);

                if (nameAndDescriptionButton != null) {
                    VBox nameAndDescriptionContainer = (VBox) nameAndDescriptionButton.getGraphic();
                    updateNameAndDescription(nameAndDescriptionContainer, newProjectName, newProjectDescription);
                    break; // Exit loop after finding and updating the correct project
                }
            }
        }
    }


    /**
     * Updates the name and description labels within a given container in the UI.
     *
     * @param container       The VBox container holding the name and description labels.
     * @param newName         The new name to be displayed.
     * @param newDescription  The new description to be displayed.
     */
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

    /**
     * Displays the deleted project on the user interface. When a project is deleted, this method is called to
     * remove the corresponding HBox from the projectsGrid (GridPane) in the scene. The HBox represents the
     * deleted project on the UI.
     *
     * @param projectModel The ProjectModel of the project to be deleted from the UI.
     */
    public void displayDeletedProject(ProjectModel projectModel) {
        String projectUUID = projectModel.getID().toString();

        GridPane projectsGrid = uiComponentLocator.findGridPane();

        if (projectsGrid != null) {
            HBox targetHBox = uiComponentLocator.findHBoxWithId(projectsGrid, projectUUID);

            if (targetHBox != null) {
                new PresenterUtility().removeHBoxAndUpdateLayout(projectsGrid, targetHBox);
            }
        }
    }


    /**
     * Handles the action event triggered by the "Create Project" button. Displays a dialog to collect
     * project information from the user and creates a project using the provided information.
     *
     * @param actionEvent The action event triggered by the "Create Project" button.
     */
    void handleCreateProjectPopup(ActionEvent actionEvent) {

        Optional<Pair<String, String>> result = showDialog();

        result.ifPresent(project -> {
            controller.createProject(project.getKey(), project.getValue());
        });
    }

    /**
     * Displays a dialog to collect project information from the user.
     *
     * @return An optional Pair containing the project name and description if the user confirms,
     *         otherwise an empty optional.
     */
    public Optional<Pair<String, String>> showDialog() {
        Dialog<Pair<String, String>> dialog = new PresenterUtility().createDialog();
        new PresenterUtility().setDialogContent(dialog, this);
        return new PresenterUtility().showDialogAndWait(dialog, this);
    }

    // Getter methods for nameTextField and descTextField
    public TextField getNameTextField() {
        return nameTextField;
    }

    public TextField getDescTextField() {
        return descTextField;
    }


    /**
     * Displays a popup for the user to input the new name and description. If either is empty, then show an alert
     * message saying it is invalid.
     *
     * @return An array of strings containing the new project name and description entered by the user, respectively.
     */
    public String[] displayRenameProjectPopup() {
        Optional<String[]> result = new PresenterUtility().showRenameProject(this);

        return result.orElse(null);
    }


    /**
     * Displays an error alert dialog with the specified header and content.
     *
     * @param header  The header text of the error alert.
     * @param content The content text of the error alert.
     */
    void showErrorAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
