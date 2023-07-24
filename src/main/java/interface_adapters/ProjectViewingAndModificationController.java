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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProjectViewingAndModificationPresenter presenter =
                new ProjectViewingAndModificationPresenter();
        Project currentProject = presenter.getCurrentProject();
        System.out.println("CURRENT PROJECT: " +  currentProject);
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
            Button taskOptionsButton = new Button(":");

            taskOptionsButton.setOnAction(this::handleTaskOptions);

            hbox.getChildren().add(taskName);
            hbox.getChildren().add(taskOptionsButton);

            columnBox.getChildren().add(hbox);
        }
    }

    private void handleTaskOptions(ActionEvent actionEvent) {
    }

    private void handleAddTaskPopup(ActionEvent actionEvent) {
    }

    private void populateProjectDetails(Project project) {
        projectName.setText(project.getName());
    }
    @FXML
    private void clickBackButton() {
        ProjectViewingAndModificationInputBoundary interactor =
                new ProjectViewingAndModificationInteractor();
        ProjectViewingAndModificationOutputBoundary presenter =
                new ProjectViewingAndModificationPresenter();

        interactor.removeCurrentProject();
        Stage stage = (Stage) columnsContainer.getScene().getWindow();
        ((ProjectViewingAndModificationPresenter) presenter).setStage(stage);
        presenter.displayAllProjects();
    };
}