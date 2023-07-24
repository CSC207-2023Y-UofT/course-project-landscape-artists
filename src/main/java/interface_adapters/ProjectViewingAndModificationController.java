package interface_adapters;

import application_business_rules.boundaries.ProjectSelectionInputBoundary;
import application_business_rules.boundaries.ProjectSelectionOutputBoundary;
import application_business_rules.boundaries.ProjectViewingAndModificationInputBoundary;
import application_business_rules.boundaries.ProjectViewingAndModificationOutputBoundary;
import application_business_rules.use_cases.CurrentProjectRepository;
import application_business_rules.use_cases.project_selection_use_cases.ProjectSelectionInteractor;
import application_business_rules.use_cases.project_viewing_and_modification_use_cases.ProjectViewingAndModificationInteractor;
import enterprise_business_rules.entities.Column;
import enterprise_business_rules.entities.Project;
import enterprise_business_rules.entities.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProjectViewingAndModificationController implements Initializable {
    @FXML
    Label projectName;
    @FXML
    HBox columnsContainer;
    ProjectViewingAndModificationInputBoundary interactor =
            new ProjectViewingAndModificationInteractor();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProjectViewingAndModificationPresenter presenter =
                new ProjectViewingAndModificationPresenter();
        Project currentProject = presenter.getCurrentProject();
//        Populate project details
        populateProjectDetails(currentProject);

        List<Column> columnsInProject = currentProject.getColumns();
        populateColumns(columnsInProject);

//       Implement methods


    }

    private void populateColumns(List<Column> columns) {
        // The method iterates over the array, creates an instance of
        // Scrollpane (so it scrolls) and adds VBox (so items are stacked
        // vertically) inside the Scrollpane.
        for (Column column: columns) {
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setPrefSize(200, 400);


            VBox columnBox = new VBox();
            columnBox.setPrefSize(180, 380);

            // Add label for the name of column
            Label columnLabel = new Label(column.getName());
            columnBox.getChildren().add(columnLabel);

            // For every instance of Column object, this method displays every
            // Task inside the newly created Column UI (i.e. columnBox).
            populateTasksForEachColumn(columnBox, column.getTasks());

            // A button is also created and added for every Column UI.
            Button addTaskButton = new Button("Add Task");

            // This line adds an event listener to every column. When the
            // button is clicked, handleAddTaskPopup is invoked.
            addTaskButton.setOnAction(this::handleAddTaskPopup);

            // Adds the children node to the Column UI.
            columnBox.getChildren().add(addTaskButton);
            scrollPane.setContent(columnBox);

            // Adds the column UI to the container of all the columns, which
            // is an HBox (i.e. A box that displays its items horizontally).
            columnsContainer.getChildren().add(scrollPane);
        }
    }

    /**
     * Updates the Presenter so an array of Task instances belonging to
     * columnBox is added to the GUI.
     *
     * @param columnBox The VBox representing the Column UI. Items added to
     *                  this JavaFX component is stacked vertically.
     * @param tasks The array of Task instances belonging to the columnBox.
     * */
    private void populateTasksForEachColumn(VBox columnBox, List<Task>
            tasks) {
        // This method iterates over the tasks given. For each one, it
        // creates an HBox (so items added are horizontal) and adds it the
        // Column UI (columnBox).
        for (Task task: tasks) {
            // HBox is used so for each task, the taskName and
            // taskOptionsButton is displayed horizontally.
            HBox hbox = new HBox();

            Label taskName = new Label(task.getName());
            Button taskOptionsButton = new Button("...");
            taskOptionsButton.setStyle("-fx-font-size: 8px;");

            taskOptionsButton.setOnAction(this::handleTaskOptions);

            hbox.getChildren().add(taskName);
            hbox.getChildren().add(taskOptionsButton);

            columnBox.getChildren().add(hbox);
        }
    }

    private void handleTaskOptions(ActionEvent actionEvent) {
    }

    /**
     * Handles displaying a popup window when the button to add a task is
     * clicked.
     *
     * @param actionEvent an Event representing a button click.
     * */
    private void handleAddTaskPopup(ActionEvent actionEvent) {
        // Create a new stage for the popup
        Stage popupStage = new Stage();

        // Stops all other stages from functioning until popupStage is closed.
        popupStage.initModality(Modality.APPLICATION_MODAL);

        popupStage.setTitle("Add Task");

        // Create the GridPane layout for the popup
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Create labels and input fields for Task Details, Task Due Date, and Task Name

        Label nameLabel = new Label("Task Name:");
        TextField nameTextField = new TextField();

        Label detailsLabel = new Label("Task Details:");
        TextArea detailsTextArea = new TextArea();
        detailsTextArea.setPrefRowCount(3);

        Label dueDateLabel = new Label("Task Due Date:");
        DatePicker dueDatePicker = new DatePicker();



        // Add the components to the GridPane. The number provided implies
        // which position in the gridPane (i.e. column 0, row 0 is top left).
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameTextField, 1, 0);
        gridPane.add(detailsLabel, 0, 1);
        gridPane.add(detailsTextArea, 1, 1);
        gridPane.add(dueDateLabel, 0, 2);
        gridPane.add(dueDatePicker, 1, 2);

        // Create the "Add" button for submitting the task
        Button addTaskToColumnButton = new Button("Submit");

        // Handles the action of putting a new task in the correct Column UI.
        addTaskToColumnButton.setOnAction(event -> {
            // Close the popup when "Submit" button is pressed
            popupStage.close();

            // Call the method to handle adding the task to the column
            handleAddTaskToColumn(event);
        });


        // Add the "Add" button to the GridPane
        gridPane.add(addTaskToColumnButton, 0, 3, 2, 1);

        // Create the scene and set it on the stage
        Scene popupScene = new Scene(gridPane, 800, 200);
        popupStage.setScene(popupScene);

        // Show the popup
        popupStage.showAndWait();
    }

    private void handleAddTaskToColumn(ActionEvent actionEvent) {
//        ProjectViewingAndModificationInputBoundary interactor =
//                new ProjectViewingAndModificationInteractor();
        System.out.println("HANDLE ADD TASK TO COLUMN");
    }

    private void populateProjectDetails(Project project) {
        projectName.setText(project.getName());
    }
    @FXML
    private void clickBackButton() {
//        ProjectViewingAndModificationInputBoundary interactor =
//                new ProjectViewingAndModificationInteractor();
        ProjectViewingAndModificationOutputBoundary presenter =
                new ProjectViewingAndModificationPresenter();

        interactor.removeCurrentProject();
        Stage stage = (Stage) columnsContainer.getScene().getWindow();
        ((ProjectViewingAndModificationPresenter) presenter).setStage(stage);
        presenter.displayAllProjects();
    };
}