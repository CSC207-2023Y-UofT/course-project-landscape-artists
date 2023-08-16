package c_interface_adapters;

import b_application_business_rules.boundaries.ProjectViewingAndModificationInputBoundary;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.factories.TaskModelFactory;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import b_application_business_rules.use_cases.project_selection_gateways.IDBSearch;
import b_application_business_rules.use_cases.project_selection_gateways.IDbIdToModelList;
import b_application_business_rules.use_cases.project_viewing_and_modification_use_cases.ProjectViewingAndModificationInteractor;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerRemoveController;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerSearchController;
import d_frameworks_and_drivers.database_management.DBControllers.IDListsToModelList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.time.LocalDate;
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

     // The JavaFX Label component responsible for displaying the name of the project.
    @FXML
    Label projectName;


     // The JavaFX HBox container that holds the UI components representing project columns.

    @FXML
    HBox columnsContainer;


     // The JavaFX Label component responsible for displaying the description of the project.

    @FXML
    Label projectDescription;

     // The JavaFX Button component that triggers navigation to the previous scene.
    @FXML
    Button backButton;


     // The JavaFX Button component that triggers the addition of a new column to the project.

    @FXML
    Button addColumnButton;


     // The interactor responsible for managing input and output interactions in the project viewing and modification
     // module.
    static ProjectViewingAndModificationInputBoundary interactor;


     // The presenter responsible for handling presentation logic related to project viewing and modification.
    static ProjectViewingAndModificationPresenter presenter;




    /**
     * Sets up the project details view with the provided ProjectModel.
     * This method configures the button styles, populates the project details,
     * and fetches the list of columns from the ProjectModel to display them using the presenter.
     *
     * @param projectModel The ProjectModel containing the details of the project to be displayed.
     *                     It should include the project's name, description, ID, and a list of ColumnModels.
     */
    public void setup(ProjectModel projectModel) {
        // MAKE THIS INITIALIZE AND STATIC PRESENTERS AND INTERACTOR
        presenter =
                new ProjectViewingAndModificationPresenter(this);
        interactor =
                new ProjectViewingAndModificationInteractor(presenter);

        setButtonStyles();
    }


    /**
     * Sets the styles for the buttons in the view.
     * This method adds custom CSS class names to the back button and add column button
     * to apply specific styles defined in the CSS stylesheet.
     */
    private void setButtonStyles() {
        backButton.getStyleClass().add("back-button-custom");
        addColumnButton.getStyleClass().add("add-column-button-custom");
    }

    /**
     * Deletes the column with the specified UUID by interacting with the presenter and interactor.
     * This method sets up the presenter and calls the interactor to delete the column.
     *
     * @param id The UUID of the column to be deleted.
     */
    void deleteColumn(UUID id) {
        interactor.deleteColumn(id);
    }

    /**
     * Handles the action when the user wants to edit the details of a column with the specified UUID.
     * This method sets up the presenter for column editing, displays a pop-up window to get the new column name from the user,
     * and calls the interactor to update the column details with the new name.
     *
     * @param id The UUID of the column whose details are to be edited.
     */
    void handleEditColumnDetails(UUID id, ColumnModel columnModel) {
        String newColumnName = presenter.displayEditColumnDetails();
        interactor.editColumnDetails(id, newColumnName, columnModel);
    }


    void deleteTask(UUID columnBoxID, TaskModel task) {
        interactor.deleteTask(columnBoxID, task);
    }

    /**
     * Receives the user input from the presenter and calls the interactor to make the changes
     * to the database. If the action is successful, calls the presenter to display the final
     * changes
     *
     * @param task
     * @param hbox
     * @param newTaskName
     * @param newTaskDescription
     * @param newDueDate
     * @param columnID
     */
    void changeTaskDetails(TaskModel task, HBox hbox, String newTaskName,
                           String newTaskDescription, LocalDateTime newDueDate, UUID columnID) {
        UUID taskID = task.getID();
        boolean taskStatus = task.getCompletionStatus();

        //Creating a new TaskModel based on the user input
        TaskModel changedTask = TaskModelFactory.create(newTaskName, taskID, newTaskDescription, taskStatus,
                newDueDate);
        interactor.changeTaskDetails(changedTask, taskID, columnID);


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
     * Handles the event when the "Back" button is clicked. Removes the current project and displays
     * all projects on the UI.
     */
    @FXML
    private void clickBackButton() {
        interactor.removeCurrentProject();
        presenter.displayAllProjects();
    }


    @FXML
    /**
     * Handles the event when the "Add Column" button is clicked.
     * Displays a pop-up window to allow the user to enter a new column name.
     *
     */
    private void handleAddColumnClick() {
        PopupUI popupUI = new PopupUI();
        boolean[] addButtonClicked = new boolean[1];
        Pair<Boolean, String> result = popupUI.displayAddColumnPopup(addButtonClicked, presenter);
        if (result.getKey()) {
            String columnName = result.getValue();
            interactor.addColumn(columnName);
        }
    }


    /**
     * Displays the detailed information of a specific task in a pop-up window.
     * The TaskModel object contains the attributes and details of the task to be displayed.
     *
     * @param taskID The TaskID object representing the task whose details will be displayed.
     *             It should contain the task's name, ID, description, completion status, and due date.
     */
    public void showTaskDetails(UUID taskID) {
        interactor.getTask(taskID);
    }

    /**
     * Handles the "Submit" button click event for adding the task.
     *
     * @param popupStage                             The popup stage to be closed.
     * @param columnBox                              The VBox representing the Column UI.
     * @param projectViewingAndModificationPresenter
     */
    void handleAddTaskButtonClicked(Stage popupStage, VBox columnBox, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        String taskName = projectViewingAndModificationPresenter.nameTextField.getText().trim();
        String taskDetails = projectViewingAndModificationPresenter.detailsTextArea.getText().trim();
        LocalDate dueDate = projectViewingAndModificationPresenter.dueDatePicker.getValue();

        if (taskName.isEmpty() || taskDetails.isEmpty() || dueDate == null) {
            projectViewingAndModificationPresenter.showAlert("Error", "All fields are required. Please fill in all the details.");
        } else {
            popupStage.close();
            handleAddTaskToColumn(columnBox.getId(), taskName, taskDetails, dueDate.atStartOfDay());
        }
    }

    /**
     * Handles the "Submit" button click event.
     *
     * @param task                                   The task to be edited.
     * @param hbox                                   The HBox containing the task.
     * @param popupStage                             The pop-up stage to be closed.
     * @param uuid                                   The ID of the column containing the task.
     * @param projectViewingAndModificationPresenter
     */
    void handleTaskSubmit(TaskModel task, HBox hbox, Stage popupStage, UUID uuid, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        String taskName = projectViewingAndModificationPresenter.nameTextField.getText().trim();
        String taskDetails = projectViewingAndModificationPresenter.detailsTextArea.getText().trim();
        LocalDate dueDate = projectViewingAndModificationPresenter.dueDatePicker.getValue();

        if (taskName.isEmpty() || taskDetails.isEmpty() || dueDate == null) {
            projectViewingAndModificationPresenter.showAlert("Error", "All fields are required. Please fill in all the details.");
        } else {
            popupStage.close();
            changeTaskDetails(task, hbox, taskName, taskDetails, dueDate.atStartOfDay(),
                uuid);
        }
    }

    /**
     * Handles the "Add" button click event.
     *
     * @param addButtonClicked                       The array to store the result of the pop-up.
     * @param popupStage                             The pop-up stage to be closed.
     * @param nameTextField                          The text field for column name input.
     * @param projectViewingAndModificationPresenter
     */
    void handleAddButtonClicked(boolean[] addButtonClicked, Stage popupStage, TextField nameTextField, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        String columnName = nameTextField.getText().trim();
        if (columnName.isEmpty()) {
            projectViewingAndModificationPresenter.showAlert("Error", "Column name cannot be empty.");
        } else {
            addButtonClicked[0] = true;
            popupStage.close();
        }
    }

    /**
     * Handles the "Cancel" button click event.
     *
     * @param popupStage    The pop-up stage to be closed.
     * @param nameTextField The text field for column name input.
     */
    void handleCancelButtonClicked(Stage popupStage, TextField nameTextField) {
        nameTextField.clear();
        popupStage.close();
    }

    /**
     * Handles the OK button click event.
     *
     * @param textField                              The text field containing the input text.
     * @param dialogStage                            The dialog stage.
     * @param projectViewingAndModificationPresenter
     */
    void handleOkButtonClicked(TextField textField, Stage dialogStage, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        String inputText = textField.getText().trim();
        if (!inputText.isEmpty()) {
            projectViewingAndModificationPresenter.columnName = inputText;
            dialogStage.close();
        } else {
            projectViewingAndModificationPresenter.showAlert("Error", "Column name cannot be empty or whitespace-only.");
        }
    }

    /**
     * Handles the Cancel button click event.
     *
     * @param dialogStage The dialog stage.
     */
    void handleCancelButtonClicked(Stage dialogStage) {
        dialogStage.close();
    }

    /**
     * Handles the request to move a task from the source column to the target column.
     *
     * @param sourceColumnID The ID of the source column from which the task will be moved.
     * @param targetColumnID The ID of the target column to which the task will be moved.
     * @param task The TaskModel representing the task to be moved.
     */
    public void handleMoveTask(String sourceColumnID, String targetColumnID, TaskModel task) {
        interactor.moveTask(sourceColumnID, targetColumnID, task);
    }


    /**
     * Handles the click event when a task is completed.
     *
     * @param id               of task to change.
     * @param completionStatus the completion status previously
     * @param columnID id the task belongs to.
     */
    public void handleCompleteTask(UUID id, boolean completionStatus, UUID columnID) {
        interactor.changeTaskCompletionStatus(id, completionStatus, columnID);
    }
}