package c_interface_adapters;

import b_application_business_rules.boundaries.ProjectViewingAndModificationInputBoundary;
import b_application_business_rules.boundaries.ProjectViewingAndModificationOutputBoundary;
import b_application_business_rules.use_cases.project_viewing_and_modification_use_cases.ProjectViewingAndModificationInteractor;
import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The ProjectViewingAndModificationController class is responsible for managing the presentation
 * logic for viewing and modifying project details in the JavaFX application. It implements the
 * Initializable interface to handle initialization of the JavaFX components and user interactions
 * in the project viewing and modification scene.
 */
public class ProjectViewingAndModificationController implements Initializable {

    // JavaFX components annotated with @FXML for UI interaction
    @FXML
    Label projectName;
    @FXML
    HBox columnsContainer;
    ProjectViewingAndModificationInputBoundary interactor;

    /**
     * Constructor for the ProjectViewingAndModificationController class. Initializes the
     * interactor with a presenter and sets it as the interactor for the controller.
     */
    public ProjectViewingAndModificationController() {
        ProjectViewingAndModificationOutputBoundary presenter =
                new ProjectViewingAndModificationPresenter();
        interactor = new ProjectViewingAndModificationInteractor(presenter);
    }

    /**
     * Initializes the scene with the provided URL and resource bundle. Retrieves the current
     * project details and populates the UI with project details and associated columns and tasks.
     *
     * @param url            The URL to initialize the scene.
     * @param resourceBundle The resource bundle to initialize the scene.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Create a presenter and get the current project from it
        ProjectViewingAndModificationPresenter presenter =
                new ProjectViewingAndModificationPresenter();
        Project currentProject = presenter.getCurrentProject();

        // Populate the project details on the UI
        populateProjectDetails(currentProject);

        // Retrieve columns from the current project and populate them on the UI
        List<Column> columnsInProject = currentProject.getColumns();
        populateColumns(columnsInProject);

        // Implement additional methods and event handlers as needed
    }

    /**
     * Populates the UI with the list of columns associated with the current project. For each
     * column, it creates a VBox that contains tasks and an "Add Task" button. Each column is
     * displayed within a ScrollPane to enable scrolling if the content exceeds the display area.
     *
     * @param columns The list of Column instances associated with the current project.
     */
    private void populateColumns(List<Column> columns) {
        // Iterate through the list of columns and create a VBox for each column
        for (Column column : columns) {
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setPrefSize(200, 400);

            VBox columnBox = new VBox();
            columnBox.setPrefSize(180, 380);

            // Add label for the name of the column
            Label columnLabel = new Label(column.getName());
            columnBox.getChildren().add(columnLabel);

            // Populate tasks for each column and add an "Add Task" button
            populateTasksForEachColumn(columnBox, column.getTasks());

            Button addTaskButton = new Button("Add Task");
            addTaskButton.setOnAction(event -> handleAddTaskPopup(columnBox));

            columnBox.getChildren().add(addTaskButton);
            scrollPane.setContent(columnBox);

            // Add the column UI to the container of all columns (HBox)
            columnsContainer.getChildren().add(scrollPane);
        }
    }

    /**
     * Populates the UI with tasks for each column. For each task, an HBox is created with the task
     * name and an options button. The HBox is then added to the VBox representing the column UI.
     *
     * @param columnBox The VBox representing the Column UI. Items added to this JavaFX component
     *                  are stacked vertically.
     * @param tasks     The list of Task instances belonging to the columnBox.
     */
    private void populateTasksForEachColumn(VBox columnBox, List<Task> tasks) {
        // Iterate through the list of tasks and create an HBox for each task
        for (Task task : tasks) {
            HBox hbox = new HBox();

            Label taskName = new Label(task.getName());
            Button taskOptionsButton = new Button("...");
            taskOptionsButton.setStyle("-fx-font-size: 8px;");

            // Associate an instance of a Task for each button.
            taskOptionsButton.setUserData(task);

            taskOptionsButton.setOnAction(this::handleTaskOptions);

            hbox.getChildren().addAll(taskName, taskOptionsButton);
            columnBox.getChildren().add(hbox);
        }
    }

    /**
     * Handles displaying a popup window when the "Add Task" button is clicked. The popup allows the
     * user to enter task details such as name, description, and due date, and then add the task to
     * the selected column.
     *
     * @param columnBox The VBox representing the Column UI where the task will be added.
     */
    private void handleAddTaskPopup(VBox columnBox) {
        setPresenter();
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
            handleAddTaskToColumn(columnBox, nameTextField.getText(),
                    detailsTextArea.getText(), dueDatePicker.getValue().atStartOfDay());
        });

        // Add the "Add" button to the GridPane
        gridPane.add(addTaskToColumnButton, 0, 3, 2, 1);

        // Create the scene and set it on the stage
        Scene popupScene = new Scene(gridPane, 800, 200);
        popupStage.setScene(popupScene);

        // Show the popup
        popupStage.showAndWait();
    }

    /**
     * Updates the Presenter so an array of Task instances belonging to columnBox is added to the
     * GUI.
     *
     * @param columnBox      The VBox representing the Column UI where the task will be added.
     * @param taskName       The name of the new task.
     * @param taskDescription The description of the new task.
     * @param dueDate        The due date of the new task.
     */
    private void handleAddTaskToColumn(VBox columnBox, String taskName, String taskDescription,
                                       LocalDateTime dueDate) {
        interactor.addNewTask(columnBox, taskName, taskDescription, dueDate);
    }

    /**
     * Populates the project details on the UI, including the project name.
     *
     * @param project The Project instance representing the current project.
     */
    private void populateProjectDetails(Project project) {
        projectName.setText(project.getName());
    }

    /**
     * Handles the event when the "Back" button is clicked. Removes the current project and displays
     * all projects on the UI.
     */
    @FXML
    private void clickBackButton() {
        ProjectViewingAndModificationOutputBoundary presenter =
                new ProjectViewingAndModificationPresenter();

        interactor.removeCurrentProject();
        Stage stage = (Stage) columnsContainer.getScene().getWindow();
        ((ProjectViewingAndModificationPresenter) presenter).setStage(stage);
        presenter.displayAllProjects();
    }

    /**
     * Sets the presenter for the controller and retrieves the current scene and stage. It then sets
     * the retrieved stage as the stage for the presenter.
     */
    private void setPresenter() {
        try {
            Scene scene = projectName.getScene();
            System.out.println("SCENE IN OPENED PROJECT " + scene);
            ProjectViewingAndModificationOutputBoundary presenter =
                    new ProjectViewingAndModificationPresenter();
            Stage stage = (Stage) projectName.getScene().getWindow();
            System.out.println("STAGE IN OPENED PROJECT " + stage);

            ((ProjectViewingAndModificationPresenter) presenter).setStage(stage);

            interactor = new ProjectViewingAndModificationInteractor(presenter);
        } catch (Error e) {
            System.out.println(e);
        }
    }

    /**
     * Handles displaying options when the options button of a task is clicked.
     *
     * @param actionEvent An ActionEvent representing the options button click.
     */
    private void handleTaskOptions(ActionEvent actionEvent) {
        // Rest of the method implementation (not included in the documentation for brevity)
    }
}