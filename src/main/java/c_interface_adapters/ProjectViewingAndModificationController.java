package c_interface_adapters;

import b_application_business_rules.boundaries.ProjectViewingAndModificationInputBoundary;
import b_application_business_rules.boundaries.ProjectViewingAndModificationOutputBoundary;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.project_viewing_and_modification_use_cases.ProjectViewingAndModificationInteractor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * The ProjectViewingAndModificationController class is responsible for managing the presentation
 * logic for viewing and modifying project details in the JavaFX application. It implements the
 * Initializable interface to handle initialization of the JavaFX components and user interactions
 * in the project viewing and modification scene.
 */
public class ProjectViewingAndModificationController {

    // JavaFX components annotated with @FXML for UI interaction
    @FXML
    Label projectName;
    @FXML
    HBox columnsContainer;
    @FXML
    Label projectDescription;
    @FXML
    Button backButton;
    @FXML
    Button addColumnButton;
    ProjectViewingAndModificationInputBoundary interactor;
    ProjectViewingAndModificationPresenter presenter;

    /**
     * Constructor for the ProjectViewingAndModificationController class. Initializes the
     * interactor with a presenter and sets it as the interactor for the controller.
     */
    public ProjectViewingAndModificationController() {
        ProjectViewingAndModificationOutputBoundary presenter =
                new ProjectViewingAndModificationPresenter(this);
        this.presenter = (ProjectViewingAndModificationPresenter) presenter;
        interactor = new ProjectViewingAndModificationInteractor(presenter);
    }


    public void setup(ProjectModel projectModel) {
        setButtonStyles();

        populateProjectDetails(projectModel);
        List<ColumnModel> columnsInProject = projectModel.getColumnModels();
        presenter.populateColumns(columnsInProject, this);

    }

    private void setButtonStyles() {
        backButton.getStyleClass().add("back-button-custom");
        addColumnButton.getStyleClass().add("add-column-button-custom");
    }

    void deleteColumn(UUID id) {
        setPresenter();
        interactor.deleteColumn(id);
    }
    
    

    void handleEditColumnDetails(UUID id) {
        setPresenter();
        String newColumnName = presenter.displayEditColumnDetails();
        interactor.editColumnDetails(id, newColumnName);
    }


    void deleteTask(TaskModel task, UUID hBoxID, UUID columnBoxID) {
        interactor.deleteTask(task, hBoxID, columnBoxID);
    }

    void changeTaskDetails(TaskModel task, HBox hbox) {
//        interactor.changeTaskDetails(task, hbox);
    }

    void renameTask(TaskModel task, HBox hbox) {
//        interactor.renameTask(task, hbox);
    }

    /**
     * Handles displaying options when the options button of a task is clicked.
     *
     * @param actionEvent An ActionEvent representing the options button click.
     */
    void handleTaskOptions(ActionEvent actionEvent, TaskModel task, VBox columnBox) {
        // This has access to VBox for presentation purposes.
    }

    /**
     * Updates the Presenter so an array of Task instances belonging to columnBox is added to the
     * GUI.
     *
     * @param columnBoxID      The VBox representing the Column UI where the task will be added.
     * @param taskName       The name of the new task.
     * @param taskDescription The description of the new task.
     * @param dueDate        The due date of the new task.
     */
    void handleAddTaskToColumn(String columnBoxID, String taskName, String taskDescription,
                               LocalDateTime dueDate) {
        interactor.addNewTask(UUID.fromString(columnBoxID), taskName, taskDescription, dueDate);
    }

    /**
     * Populates the project details on the UI, including the project name.
     *
     * @param project The Project instance representing the current project.
     */
    private void populateProjectDetails(ProjectModel project) {

        projectName.setText(project.getName());
        projectDescription.setText(project.getDescription());
    }

    /**
     * Handles the event when the "Back" button is clicked. Removes the current project and displays
     * all projects on the UI.
     */
    @FXML
    private void clickBackButton() {
        interactor.removeCurrentProject();
        Stage stage = (Stage) columnsContainer.getScene().getWindow();
        presenter.setStage(stage);
        presenter.displayAllProjects();
    }

    /**
     * Sets the presenter for the controller and retrieves the current scene and stage. It then sets
     * the retrieved stage as the stage for the presenter.
     */
    void setPresenter() {
        try {
            Scene scene = projectName.getScene();
            ProjectViewingAndModificationOutputBoundary presenter =
                    new ProjectViewingAndModificationPresenter(this);
            Stage stage = (Stage) projectName.getScene().getWindow();

            ((ProjectViewingAndModificationPresenter) presenter).setStage(stage);

            interactor = new ProjectViewingAndModificationInteractor(presenter);
        } catch (Error e) {
            System.out.println(e);
        }
    }
    @FXML
    private void handleAddColumnClick() {
        String columnName = presenter.displayAddColumnPopup();
        interactor.addColumn(columnName);
    }
//    public void moveTask(TaskModel task, VBox targetColumn) {
//        // Get the current column containing the task
//        VBox sourceColumn = findColumnContainingTask(task);
//
//        // If the task is already in the target column, do nothing
//        if (sourceColumn == targetColumn) {
//            return;
//        }
//
//        // Remove the task from the source column
//        sourceColumn.getChildren().removeIf(node -> node instanceof HBox && ((HBox) node).getUserData() == task);
//
//        // Add the task to the target column
//        targetColumn.getChildren().add(createCard(task));
//
//        // Perform any other necessary actions based on your requirements
//    }
//
//    private Node createCard(TaskModel task) {
//
//    }
//
//    private VBox findColumnContainingTask(TaskModel task) {
//        List<Node> columns = columnsContainer.getChildren();
//        for (VBox column : columns) {
//            for (Node node : column.getChildren()) {
//                if (node instanceof HBox && ((HBox) node).getUserData() == task) {
//                    return column;
//                }
//            }
//        }
//        return null; // Task not found in any column
//    }
}