package c_interface_adapters;

import b_application_business_rules.boundaries.ProjectSelectionInputBoundary;
import b_application_business_rules.boundaries.ProjectSelectionOutputBoundary;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import b_application_business_rules.use_cases.project_selection_use_cases.ProjectSelectionInteractor;
import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * ProjectSelectionController class handles the user interface for project selection and creation.
 * It implements the Initializable interface to initialize the controller.
 */
public class ProjectSelectionController implements Initializable {
    // FXML reference to the GridPane that holds the projects
    @FXML
    private GridPane projectsGrid;

    // The interactor for project selection and creation
    private ProjectSelectionInputBoundary interactor;

    // The repository to manage the currently opened project
    CurrentProjectRepository currentProjectRepository =
            CurrentProjectRepository.getInstance();

    /**
     * Constructor to initialize the ProjectSelectionController.
     */
    public ProjectSelectionController() {
        ProjectSelectionOutputBoundary presenter =
                new ProjectSelectionPresenter();
        interactor = new ProjectSelectionInteractor(presenter);
    }

    /**
     * Initializes the controller after its root element has been completely processed.
     * Populates the project selection UI with the projects from the database.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//         Grab data from database and display it in the scene. An example
//         would be something like below (Currently don't know which layer to
//         get all projects):
//         Gateway gateway = new Gateway();
//         List<Project> allProjectsInSystem = gateway.getAllProjects();
//        TODO: TEMPORARY IMPLEMENTATION FOR TESTING PURPOSES ------------------
        List<Task> TaskList = Arrays.asList(new Task("Task1", "Task1", true,
                LocalDateTime.now()), new Task("Task2", "Task2", true,
                LocalDateTime.now()));
        List<Column> ColumnsList = Arrays.asList(new Column("COLUMN 1",
                TaskList,
                "C1 " +
                "description"), new Column("COLUMN 2", new ArrayList<>(), "C2" +
                " " +
                "description"));
        Project p1 = new Project("Project P1", ColumnsList,
                "P1 description");
        Project p2 = new Project("Project P2", new ArrayList<>(),
                "P2 description");
        Project p3 = new Project("Project P3", new ArrayList<>(),
                "P3 description");
        Project p4 = new Project("Project p4", new ArrayList<>(),
                "P3 description");
        Project p5 = new Project("Project p5", new ArrayList<>(),
                "P3 description");
        Project p6 = new Project("Project p6", new ArrayList<>(),
                "P3 description");
        Project p7 = new Project("Project p7", new ArrayList<>(),
                "P3 description");
        Project p8 = new Project("Project p8", new ArrayList<>(),
                "P3 description");

        List<Project> allProjectsInSystem = Arrays.asList(p1, p2, p3, p4, p5,
                p6, p7, p8);
//        TODO: END ------------------------------------------------------------
        // Populate the project selection UI with the projects
        populateProjectSelectionUI(allProjectsInSystem);
    }

    /**
     * Sets up the output boundary of the interactor.
     */
    private void setPresenter() {
        // This had to be separate since presenter needs to have a stage.
        // This is not accessible upon initialization.
        ProjectSelectionOutputBoundary presenter =
                new ProjectSelectionPresenter();
        Stage stage = (Stage) projectsGrid.getScene().getWindow();
        ((ProjectSelectionPresenter) presenter).setStage(stage);

        interactor = new ProjectSelectionInteractor(presenter);
    }

    /**
     * Populates the project selection UI with the given list of projects.
     *
     * @param allProjectsInSystem The list of projects to display in the UI.
     */
    private void populateProjectSelectionUI(List<Project> allProjectsInSystem) {
        projectsGrid.setHgap(10);
        projectsGrid.setVgap(10);

        for (int col = 0; col < 4; col++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setHgrow(Priority.ALWAYS);
            columnConstraints.setFillWidth(true);

            projectsGrid.getColumnConstraints().add(columnConstraints);
        }

        int row = 0;
        int col = 0;
        for (Project project : allProjectsInSystem) {
            Button button = new Button(project.getName());
            button.setUserData(project);
            button.setOnAction(this::handleChosenProjectButton);
            projectsGrid.add(button, col, row);
            col++;
            if (col >= 4) {
                col = 0;
                row++;
            }
        }
        addCreateProjectButton(col, row);
    }

    /**
     * Adds the "Create Project" button to the project selection UI.
     */
    private void addCreateProjectButton(int col, int row) {
        Button createProjectButton = new Button("+");
        createProjectButton.setOnAction(this::handleCreateProjectPopup);
        projectsGrid.add(createProjectButton, col, row);
    }

    /**
     * Handles the "Create Project" button action by showing a dialog to create a new project.
     */
    private void handleCreateProjectPopup(ActionEvent actionEvent) {
        setPresenter();
        // Create a new Dialog
        Dialog<Project> dialog = new Dialog<>();
        dialog.setTitle("Create Project");

        // Set the button types (OK and Cancel)
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Create labels and text fields for project name and description
        Label nameLabel = new Label("Project Name:");
        Label descLabel = new Label("Description:");
        TextField nameTextField = new TextField();
        TextField descTextField = new TextField();

        // Create a GridPane to layout the labels and text fields
        GridPane gridPane = new GridPane();
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameTextField, 1, 0);
        gridPane.add(descLabel, 0, 1);
        gridPane.add(descTextField, 1, 1);

        // Set the content of the dialog to the GridPane
        dialog.getDialogPane().setContent(gridPane);

        // Convert the result to a Project object when the OK button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String projectName = nameTextField.getText();
                String projectDescription = descTextField.getText();
                return new Project(projectName, projectDescription);

            }
            return null;
        });

        // Show the dialog and wait for the user to close it
        dialog.showAndWait().ifPresent(project -> {
            if (project != null) {
                // Handle the newly created project here
                createProject(project);
                openProject(project);
            }
        });

    }

    /**
     * Creates a new project using the provided Project object and delegates the task to the interactor
     * for processing the project creation.
     *
     * @param project The Project object representing the new project to be created.
     */
    private void createProject(Project project) {
        interactor.createProject(project);
    }




    /**
     * Handles the action of selecting a project button from the UI.
     */
    private void handleChosenProjectButton(ActionEvent actionEvent) {
        setPresenter();
        Button buttonClicked = (Button) actionEvent.getSource();
        Project currentProject = (Project) buttonClicked.getUserData();
        openProject(currentProject);


//            Stage stage = (Stage) projectsGrid.getScene().getWindow();
//            Parent root = FXMLLoader.load(getClass().getResource("ProjectViewingAndModification.fxml"));
//            stage.setTitle("scene 2");
//            stage.setScene(new Scene(root));

    }

    /**
     * Opens the selected project in the UI.
     *
     * @param project The project to be opened.
     */
    private void openProject(Project project) {
        interactor.setCurrentProject(project);
    }


}