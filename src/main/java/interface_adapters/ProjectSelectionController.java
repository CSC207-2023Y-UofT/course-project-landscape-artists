package interface_adapters;

import application_business_rules.boundaries.ProjectSelectionInputBoundary;
import application_business_rules.boundaries.ProjectSelectionOutputBoundary;
import application_business_rules.use_cases.CurrentProjectRepository;
import application_business_rules.use_cases.project_selection_use_cases.ProjectSelectionInteractor;
import enterprise_business_rules.entities.Column;
import enterprise_business_rules.entities.Project;
import enterprise_business_rules.entities.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class ProjectSelectionController implements Initializable {
    @FXML
    GridPane projectsGrid;

    CurrentProjectRepository currentProjectRepository =
            CurrentProjectRepository.getInstance();

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

        populateProjectSelectionUI(allProjectsInSystem);
        addCreateProjectButton();
    }

    private void addCreateProjectButton() {

    }

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
        Button createProjectButton = new Button("+");
        createProjectButton.setOnAction(this::handleCreateProjectPopup);
        projectsGrid.add(createProjectButton, col, row);
    }

    private void handleCreateProjectPopup(ActionEvent actionEvent) {
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

    private void createProject(Project project) {
        ProjectSelectionInputBoundary interactor =
                new ProjectSelectionInteractor();
        interactor.createProject(project);
    }

    ;



    private void handleChosenProjectButton(ActionEvent actionEvent) {
        Button buttonClicked = (Button) actionEvent.getSource();
        Project currentProject = (Project) buttonClicked.getUserData();
        openProject(currentProject);


//            Stage stage = (Stage) projectsGrid.getScene().getWindow();
//            Parent root = FXMLLoader.load(getClass().getResource("ProjectViewingAndModification.fxml"));
//            stage.setTitle("scene 2");
//            stage.setScene(new Scene(root));

    }

    private void openProject(Project project) {
        ProjectSelectionInputBoundary interactor =
                new ProjectSelectionInteractor();
        ProjectSelectionOutputBoundary presenter =
                new ProjectSelectionPresenter();

        interactor.setCurrentProject(project);
        Stage stage = (Stage) projectsGrid.getScene().getWindow();
        ((ProjectSelectionPresenter) presenter).setStage(stage);
        presenter.displayCurrentProject();
    }


}