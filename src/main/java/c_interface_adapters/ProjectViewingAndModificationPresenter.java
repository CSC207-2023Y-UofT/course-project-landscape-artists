package c_interface_adapters;

import b_application_business_rules.boundaries.ProjectViewingAndModificationOutputBoundary;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import c_interface_adapters.view_models.ProjectViewModel;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;

import java.util.*;

/**
 * The ProjectViewingAndModificationPresenter class is responsible for managing the presentation
 * logic for viewing and modifying projects in the JavaFX application. It extends the Application
 * class to enable JavaFX initialization, and it implements the ProjectViewingAndModificationOutputBoundary
 * interface to handle interactions with the project viewing and modification scene.
 */
public class ProjectViewingAndModificationPresenter implements ProjectViewingAndModificationOutputBoundary {
    // Reference to the primary stage for the UI
    static Stage stage;

    // The controller responsible for handling project-related actions
    static ProjectViewingAndModificationController controller;

    // A container for storing VBox elements representing columns
    static final List<VBox> VBoxContainer = new ArrayList<VBox>();

    // The VBox element representing the destination during drag-and-drop operations
    static VBox dragDestination;

    // The locator for UI components within the scene
    static UIComponentLocator uiComponentLocator;

    // Text field for entering task names
    TextField nameTextField;

    // Text area for entering task details
    TextArea detailsTextArea;

    // Date picker for selecting task due dates
    DatePicker dueDatePicker;

    // A variable to store the entered column name
    String columnName;

    /**
     * Creates a presenter instance with a provided controller.
     *
     * @param controller The controller to associate with the presenter.
     */
    public ProjectViewingAndModificationPresenter(ProjectViewingAndModificationController controller) {
        this.controller = controller;
    }

    /**
     * Creates a presenter instance with a default controller.
     */
    public ProjectViewingAndModificationPresenter() {
        this.controller = new ProjectViewingAndModificationController();
    }


    /**
     * Sets up the next scene for displaying the details of a selected project.
     * This method initializes the second scene and prepares it to display the details
     * of the selected project to the UI. It is typically invoked by the
     * ProjectSelectionPresenter's `displayCurrentProject` method, passing the
     * corresponding `ProjectModel`.
     *
     * @param stage The main stage of the JavaFX application. This is the same stage
     *              currently being used by
     * @param projectModel The ProjectModel of the project selected by the user.
     * @throws IOException If an I/O error occurs during loading of the FXML file.
     */
    public void setUpProjectViewingAndModificationScene(Stage stage, ProjectModel projectModel) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectViewingAndModificationPresenter.class.getResource("ProjectViewingAndModification.fxml"));
            Parent root = fxmlLoader.load();

            controller = fxmlLoader.getController();

            controller.setup(projectModel);
            this.stage = stage;

            setUpScene(root, "Current project", "ProjectViewingAndModificationStyle.css");

            populateProjectDetails(projectModel);
            List<ColumnModel> columnsInProject = projectModel.getColumnModels();
            populateColumns(columnsInProject);
        } catch (IOException e) {
            throw new RuntimeException("Error while starting the project view.", e);
        }
    }

    /**
     * Populates the project details UI elements with data from the given project model.
     *
     * @param projectModel The project model containing project details.
     */
    public void populateProjectDetails(ProjectModel projectModel) {
        Label projectNameLabel = uiComponentLocator.findProjectNameUI();
        Label projectDescriptionLabel = uiComponentLocator.findProjectDescriptionUI();

        projectNameLabel.setText(projectModel.getName());
        projectDescriptionLabel.setText(projectModel.getDescription());
    }

    /**
     * Sets up the scene with the provided root, title, and stylesheet path.
     *
     * @param root           The root node of the scene.
     * @param title          The title of the stage.
     * @param stylesheetPath The path to the stylesheet to be applied.
     */
    private void setUpScene(Parent root, String title, String stylesheetPath) {
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource(stylesheetPath).toExternalForm());
        stage.setScene(scene);
        stage.setTitle(title);

        uiComponentLocator = new UIComponentLocator(scene);
    }


    /**
     * Displays the scene with all projects when the "Back" button is clicked.
     */
    @Override
    public void displayAllProjects() {
        try {
            ProjectSelectionPresenter projectSelectionPresenter = new ProjectSelectionPresenter();
            projectSelectionPresenter.start(stage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays a new task in the specified column.
     *
     * @param columnBoxID The UUID of the column box where the task will be displayed.
     * @param newTask     The ViewModel representing the new task.
     */
    @Override
    public void displayNewTask(UUID columnBoxID, TaskModel newTask) {
        // Get the UI for the column
        VBox columnUI = uiComponentLocator.getColumnUI(String.valueOf(columnBoxID));

        // Create a temp list that holds the new task
        ArrayList<TaskModel> tempTaskList = new ArrayList<>();
        tempTaskList.add(newTask);

        // Append to the Column UI with the new task.
        populateTasksForEachColumn(columnUI, tempTaskList);


    }

    /**
     * Displays the removal of a task.
     *
     * @param task   The TaskModel representing the removed task.
     */
    @Override
    public void displayRemovedTask(TaskModel task, UUID columnBoxID) {
        // Get the UI for the column
        VBox columnUI = uiComponentLocator.getColumnUI(String.valueOf(columnBoxID));

        // Get the UI for the task
        HBox taskUI = uiComponentLocator.findTaskUI(task.getID(), columnUI);

        // Remove the taskUI from the columnUI
        columnUI.getChildren().remove(taskUI);
    }

    /**
     * Displays the renaming of a column.
     *
     * @param column The ColumnModel containing the updated column information.
     */
    @Override
    public void displayRenamedColumn(ColumnModel column) {
        String columnUUID = column.getID().toString();
        String columnName = column.getName();

        VBox columnUI = uiComponentLocator.getColumnUI(columnUUID);
        if (columnUI != null) {
            Label columnNameUI = uiComponentLocator.getColumnNameUI(columnUI);
            if (columnNameUI != null) {
                columnNameUI.setText(columnName);
            }
        }


    }

    /**
     * Displays the deletion of a column.
     *
     * @param columnModel The ColumnModel representing the deleted column.
     */
    @Override
    public void displayDeletedColumn(ColumnModel columnModel) {
        String columnUUID = columnModel.getID().toString();

        uiComponentLocator.removeColumnUI(columnUUID);
    }


    /**
     * This method renames the existing task and changes the task description and shows the final
     * changes on the screen
     *
     * @param task updated task's entity model.
     * @param columnID column ID of column that held task.
     */
    @Override
    public void displayChangedTaskDetails(TaskModel task, UUID columnID) {
        String taskName = task.getName();

        Text taskNameUI = uiComponentLocator.findTaskName(task.getID(), columnID);
        if (taskNameUI != null) {
            taskNameUI.setText(taskName);
        } else {
            System.err.println("TASK IS NOT IN THE COLUMN");
        }
    }





    /**
     * Populates the UI with the list of columns associated with the current project. For each *
     * column, it creates a VBox that contains tasks and an "Add Task" button. Each column is
     * displayed within a ScrollPane to enable scrolling if the content exceeds the display area.
     *
     * @param columns                                 The list of Column instances associated with the current project.
     */
    void populateColumns(List<ColumnModel> columns) {
        // Iterate through the list of columns and create a VBox for each column
        for (ColumnModel column : columns) {
            displayNewColumn(column);
        }
    }

    /**
     * Displays a new column in the task management application. *
     *
     * @param column The ColumnModel representing the new column to be displayed.
     */
    public void displayNewColumn(ColumnModel column) {
        ScrollPane scrollPane = new PresenterUtility().createScrollPane();

        // clear scrollpane styling
        scrollPane.getStyleClass().clear();

        VBox columnBox = new PresenterUtility().createColumnBox(column);
        PresenterUtility.setColumnUIStyling(columnBox);

        // set prefered width of scroll pane.
        columnBox.setPrefWidth(220);
        columnBox.setPrefHeight(370);
        columnBox.setMaxHeight(370);


//        columnBox.setPrefHeight(365);

        HBox columnNameAndOptions = new PresenterUtility().createColumnNameAndOptions();
        Label columnLabel = new PresenterUtility().createColumnLabel(column.getName());
        MenuButton columnOptions = new PresenterUtility().createColumnOptions();
        new PresenterUtility().addMenuItems(columnOptions, column);
        Button addTaskButton = new PresenterUtility().createAddTaskButton();
        addTaskButton.setOnAction(event -> new PopupUI().handleAddTaskPopup(columnBox, controller.presenter));
        HBox TaskBtnVBox = new HBox(addTaskButton);

        scrollPane.setId(column.getID().toString());

        new PresenterUtility().configureSizeConstraints(columnNameAndOptions, columnOptions, TaskBtnVBox);

        columnNameAndOptions.getChildren().addAll(columnLabel, columnOptions, TaskBtnVBox);

        columnBox.getChildren().add(columnNameAndOptions);
        populateTasksForEachColumn(columnBox, column.getTaskModels());

        new PresenterUtility().configureColumnBox(columnBox, scrollPane);
        new PresenterUtility().addToColumnsContainer(scrollPane);
        this.VBoxContainer.add(columnBox); // Add columnBox to VBoxContainer

        new DragAndDropImplementation().configureDragAndDropHandling(columnBox, this);
    }


    /**
     * Populates the specified column box with task cards based on the provided task models. *
     *
     * @param columnBox The VBox to populate with task cards.
     * @param tasks     The list of TaskModel objects representing the tasks.
     */
    void populateTasksForEachColumn(VBox columnBox, List<TaskModel> tasks) {
        Set<String> addedHBoxIds = new HashSet<>();

        for (TaskModel task : tasks) {
            HBox hbox = new PresenterUtility().createTaskCard(task);
//            if(task.getCompletionStatus()){
//                hbox.setStyle("-fx-border-radius: 10.0d;" +
//                        "-fx-border-color: black;" +
//                        "-fx-border-width: 2px;");
//                hbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(10.0d), Insets.EMPTY)));
//            }
            setTaskOptions( hbox, task, columnBox.getId());

            if (!addedHBoxIds.contains(hbox.getId())) {
                columnBox.getChildren().add(hbox);
                addedHBoxIds.add(hbox.getId());
            }
        }
    }


    /**
     * Sets task options and related UI elements for the task card.
     *
     * @param hbox        The HBox representing the task card.
     * @param task        The TaskModel object representing the task.
     * @param columnBoxId The ID of the parent column box.
     */
    private void setTaskOptions( HBox hbox, TaskModel task, String columnBoxId) {
        MenuButton taskOptionsButton = new PresenterUtility().createTaskOptionsMenu(task, hbox, columnBoxId, this);
        RadioButton completeTaskButton = new RadioButton();
        completeTaskButton.setOnAction(event -> {controller.handleCompleteTask(task.getID(),
                task.getCompletionStatus(), UUID.fromString(columnBoxId));});

        hbox.getChildren().addAll(taskOptionsButton, completeTaskButton);
        new PresenterUtility().configureHBoxFeatures(hbox, this, task);
    }


    /**
     * Displays a dialog for editing column details. *
     *
     * @return The entered column name.
     */
    public String displayEditColumnDetails() {
        Stage dialogStage = new PresenterUtility().createDialogStage();
        VBox vbox = new PresenterUtility().createDialogContent(dialogStage, this);
        Scene scene = new Scene(vbox, 300, 150);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
        return columnName;
    }

    /**
     * Displays an alert dialog with the specified title and message.
     * The alert type is set to ERROR.
     *
     * @param title   The title of the alert dialog.
     * @param message The content message to be displayed in the alert dialog.
     */
    void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    /**
     * Displays the details of a given TaskModel in a pop-up window. *
     *
     * @param taskModel The TaskModel object containing the details of the task to be displayed.
     */
    public void displayTaskDetails(TaskModel taskModel) {
        Stage popupStage = new PresenterUtility().createPopupStage("Task Details");
        VBox vbox = PresenterUtility.createDetailsLayout(taskModel, new PresenterUtility());
        Scene scene = new Scene(vbox);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }

    public void displayTaskCompleted(UUID taskModelid, UUID colID){
        HBox taskBox = uiComponentLocator.findTaskUI(taskModelid,
                uiComponentLocator.getColumnUI(colID.toString()));

        taskBox.setStyle("-fx-border-radius: 10.0d;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 2px;");
        taskBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(10.0d), Insets.EMPTY)));

    }
}