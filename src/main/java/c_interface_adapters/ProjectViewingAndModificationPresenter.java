package c_interface_adapters;

import b_application_business_rules.boundaries.ProjectViewingAndModificationOutputBoundary;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.TaskModel;
import a_enterprise_business_rules.entities.Task;
import c_interface_adapters.view_models.ProjectViewModel;
import c_interface_adapters.view_models.TaskViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * The ProjectViewingAndModificationPresenter class is responsible for managing the presentation
 * logic for viewing and modifying projects in the JavaFX application. It extends the Application
 * class to enable JavaFX initialization, and it implements the ProjectViewingAndModificationOutputBoundary
 * interface to handle interactions with the project viewing and modification scene.
 */
public class ProjectViewingAndModificationPresenter extends Application implements ProjectViewingAndModificationOutputBoundary {
    private Stage stage;
    private ProjectViewingAndModificationController controller;

    /**
     * Initializes the JavaFX application and sets up the initial scene to display the current
     * project details and associated tasks.
     *
     * @param stage The main stage of the JavaFX application.
     * @throws IOException If an I/O error occurs during loading of the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProjectViewingAndModificationPresenter.class.getResource("ProjectViewingAndModification.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        controller = fxmlLoader.getController();
        stage.setTitle("Current project");
        stage.setScene(scene);
        stage.show();
    };

    public ProjectViewingAndModificationPresenter(ProjectViewingAndModificationController controller) {
        this.controller = controller;
    }

    /**
     * The main entry point for the JavaFX application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Sets the main stage for the presenter.
     *
     * @param stage The main stage of the JavaFX application.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Retrieves the current project from the CurrentProjectRepository.
     *
     * @return The current Project instance.
     */

    /**
     * Displays the scene with all projects when the "Back" button is clicked.
     */
    @Override
    public void displayAllProjects() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ProjectSelection.fxml"));
            stage.setTitle("scene 1");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void displayNewTask(UUID columnBoxID, TaskViewModel newTask) {

    }

    @Override
    public void displayRenamedTask(UUID taskID, TaskViewModel task) {

    }

    @Override
    public void displayRemovedTask(UUID taskID, TaskViewModel task) {

    }

    @Override
    public void displayRenamedColumn(ColumnModel column) {
        String columnUUID = column.getID().toString();
        String columnName = column.getName();

        Scene scene = stage.getScene();
        if (scene != null) {
            // Find the HBox that corresponds to the provided projectUUID
            for (Node node : scene.getRoot().getChildrenUnmodifiable()) {
                if (node.getId().equals("scrollPaneContainer")) {

                    if (node instanceof ScrollPane){
                        ScrollPane scrollPane = (ScrollPane) node;
                        HBox columnsContainer = (HBox) scrollPane.getContent();

                        Iterator<Node> iterator = columnsContainer.getChildren().iterator();
                        while (iterator.hasNext()) {
                            Node containerChild = iterator.next();
                            if (containerChild.getId().equals(columnUUID)) {
                                VBox columnUI = (VBox) (((ScrollPane) containerChild).getContent());
                                for (Node item: columnUI.getChildren()) {

                                    if (item.getId().equals("columnHeader")) {
                                        Label columnNameUI = (Label) (((HBox) item).getChildren().get(0));
                                        columnNameUI.setText(columnName);
                                    };
                                    break;

                                }
                            }
                        }
                        break;
                    }
                }
            }
        }

    }

    @Override
    public void displayDeletedColumn(ColumnModel columnModel) {
        String columnUUID = columnModel.getID().toString();

        Scene scene = stage.getScene();
        System.out.println("SCENE "+scene);
        if (scene != null) {
            // Find the HBox that corresponds to the provided projectUUID
            for (Node node : scene.getRoot().getChildrenUnmodifiable()) {
                if (node.getId().equals("scrollPaneContainer")) {

                    if (node instanceof ScrollPane){
                        ScrollPane scrollPane = (ScrollPane) node;
                        HBox columnsContainer = (HBox) scrollPane.getContent();

                        Iterator<Node> iterator = columnsContainer.getChildren().iterator();
                        while (iterator.hasNext()) {
                            Node containerChild = iterator.next();
                            if (containerChild.getId().equals(columnUUID)) {
                                columnsContainer.getChildren().remove(containerChild);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }

    }

    //@Override
    public void displayChangedTaskDetails(UUID taskID, TaskViewModel task, HBox hbox) {
        String taskUUID = task.getID().toString();
        String taskName = task.getName();

        hbox.getChildren().removeAll();
        Label taskLabel = new Label(taskName);
        Button taskOptionsButton = new Button("...");
        taskOptionsButton.setStyle("-fx-font-size: 8px;");
        hbox.getChildren().add(taskLabel);
        hbox.getChildren().add(taskOptionsButton);

    }

    @Override
    public void dislayChangedTaskDate(UUID taskID, TaskViewModel task) {

    }

    @Override
    public void displayRenamedProject(ProjectViewModel project, UUID projectId) {

    }

    @Override
    public void displayDeleteProject(ProjectViewModel project, UUID projectId) {

    }


    /**
     * Displays a new task in the specified VBox columnBox. The new task is represented by an HBox
     * containing the task name and an options button.
     *
     * @param columnBox The VBox representing the Column UI where the new task will be added.
     * @param newTask   The new Task instance to be displayed.
     */

    public void displayNewTask(VBox columnBox, Task newTask) {
        HBox hbox = new HBox();
        Label taskName = new Label(newTask.getName());
        Button taskOptionsButton = new Button("...");
        taskOptionsButton.setStyle("-fx-font-size: 8px;");
        hbox.getChildren().add(taskName);
        hbox.getChildren().add(taskOptionsButton);

        int buttonIndex = columnBox.getChildren().size() - 1;
        columnBox.getChildren().add(buttonIndex, hbox);
    }

    /**
     * Populates the UI with the list of columns associated with the current project. For each
     * column, it creates a VBox that contains tasks and an "Add Task" button. Each column is
     * displayed within a ScrollPane to enable scrolling if the content exceeds the display area.
     *
     * @param columns                                 The list of Column instances associated with the current project.
     * @param projectViewingAndModificationController
     */
    void populateColumns(List<ColumnModel> columns, ProjectViewingAndModificationController projectViewingAndModificationController) {
        // Iterate through the list of columns and create a VBox for each column
        for (ColumnModel column : columns) {
            displayNewColumn(column);
        }
    }
    @Override
    public void displayNewColumn(ColumnModel column) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(200, 400);

        scrollPane.setId(column.getID().toString());

        VBox columnBox = new VBox();
        columnBox.setPrefSize(180, 380);

        HBox columnNameAndOptions = new HBox();

        columnBox.setId(column.getID().toString());

        // Add label for the name of the column
        Label columnLabel = new Label(column.getName());
        columnLabel.setId("columnTitle");

        // Add menu button and menu items.
        MenuButton columnOptions = new MenuButton("");
        MenuItem renameColumnButton = new MenuItem("Rename Column");
        MenuItem deleteColumnButton = new MenuItem("Delete Column");

        // Add event handler on menu item.
        renameColumnButton.setOnAction(event -> {
            controller.renameColumm(column.getID());});
        deleteColumnButton.setOnAction(event -> {
            controller.deleteColumn(column.getID());});

        columnOptions.getItems().addAll(deleteColumnButton, renameColumnButton);

        // Set the size constraints for columnNameAndOptions
        HBox.setHgrow(columnLabel, Priority.ALWAYS); // Make the label expand horizontally
        HBox.setHgrow(columnOptions, Priority.NEVER); // Make the button keep its preferred width

        columnNameAndOptions.getChildren().addAll(columnLabel, columnOptions);
        columnNameAndOptions.setId("columnHeader");

        // Set the size constraints for columnBox
        VBox.setVgrow(columnNameAndOptions, Priority.NEVER); // Make columnNameAndOptions keep its preferred height

        columnBox.getChildren().add(columnNameAndOptions);

        // Populate tasks for each column and add an "Add Task" button
        controller.presenter.populateTasksForEachColumn(columnBox, column.getTaskModels(),
                controller);

        Button addTaskButton = new Button("Add Task");
        addTaskButton.setOnAction(event -> controller.presenter.handleAddTaskPopup(columnBox,
                controller));

        columnBox.getChildren().add(addTaskButton);
        scrollPane.setContent(columnBox);

        // Add the column UI to the container of all columns (HBox)
        controller.columnsContainer.getChildren().add(scrollPane);
    }

    /**
     * Populates the UI with tasks for each column. For each task, an HBox is created with the task
     * name and an options button. The HBox is then added to the VBox representing the column UI.
     *
     * @param columnBox                               The VBox representing the Column UI. Items added to this JavaFX component
     *                                                are stacked vertically.
     * @param tasks                                   The list of Task instances belonging to the columnBox.
     * @param projectViewingAndModificationController
     */
    void populateTasksForEachColumn(VBox columnBox, List<TaskModel> tasks, ProjectViewingAndModificationController projectViewingAndModificationController) {
        // Iterate through the list of tasks and create an HBox for each task
        for (TaskModel task : tasks) {
            HBox hbox = new HBox();

            Label taskName = new Label(task.getName());
//            Button taskOptionsButton = new Button("...");

            // Create menu button and its options.
            MenuButton taskOptionsButton = new MenuButton("");
            MenuItem renameTaskButton = new MenuItem("Rename Task");
            MenuItem changeTaskDetailsButton = new MenuItem("Change Task " +
                    "Details");
            MenuItem deleteTaskButton = new MenuItem("Delete Task");

            // Add event handlers.
            renameTaskButton.setOnAction(event -> {
                projectViewingAndModificationController.renameTask(task, hbox);});
            changeTaskDetailsButton.setOnAction(event -> {
                this.handleChangeTaskPopup(task, hbox, controller);
                //projectViewingAndModificationController.changeTaskDetails(
                    //task, hbox);
                });
            deleteTaskButton.setOnAction(event -> {
                projectViewingAndModificationController.deleteTask(task, hbox);});

            // Add to MenuButton
            taskOptionsButton.getItems().addAll(renameTaskButton,
                    changeTaskDetailsButton, deleteTaskButton);


            taskOptionsButton.setStyle("-fx-font-size: 8px;");

            // Associate an instance of a Task for each button.
            taskOptionsButton.setUserData(task);

            taskOptionsButton.setOnAction(actionEvent -> {
                projectViewingAndModificationController.handleTaskOptions(actionEvent, task, columnBox);
            });

            hbox.getChildren().addAll(taskName, taskOptionsButton);
            columnBox.getChildren().add(hbox);
        }
    }

    /**
     * Handles displaying a popup window when the "Add Task" button is clicked. The popup allows the
     * user to enter task details such as name, description, and due date, and then add the task to
     * the selected column.
     *
     * @param columnBox                               The VBox representing the Column UI where the task will be added.
     * @param projectViewingAndModificationController
     */
    void handleAddTaskPopup(VBox columnBox, ProjectViewingAndModificationController projectViewingAndModificationController) {
        projectViewingAndModificationController.setPresenter();
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
            projectViewingAndModificationController.handleAddTaskToColumn(columnBox, nameTextField.getText(),
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
    void handleChangeTaskPopup(TaskModel task, HBox hbox,
                               ProjectViewingAndModificationController projectViewingAndModificationController) {
        projectViewingAndModificationController.setPresenter();
        // Create a new stage for the popup
        Stage popupStage = new Stage();

        // Stops all other stages from functioning until popupStage is closed.
        popupStage.initModality(Modality.APPLICATION_MODAL);

        popupStage.setTitle("Change Task Details");

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
        Button changeTaskButton = new Button("Submit");

        // Handles the action of putting a new task in the correct Column UI.
        changeTaskButton.setOnAction(event -> {
            // Close the popup when "Submit" button is pressed
            popupStage.close();

            // Call the method to handle adding the task to the column
            projectViewingAndModificationController.changeTaskDetails(task, hbox, nameTextField.getText(),
                    detailsTextArea.getText(), dueDatePicker.getValue().atStartOfDay());
        });
        // Add the "Add" button to the GridPane
        gridPane.add(changeTaskButton, 0, 3, 2, 1);

        // Create the scene and set it on the stage
        Scene popupScene = new Scene(gridPane, 800, 200);
        popupStage.setScene(popupScene);

        // Show the popup
        popupStage.showAndWait();
    }

    public String displayAddColumnPopup() {
        // Create a new stage for the popup
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Add New Column");

        // Create UI components for the popup
        Label nameLabel = new Label("Column Name:");
        TextField nameTextField = new TextField();
        Button addButton = new Button("Add");
        Button cancelButton = new Button("Cancel");

        // Set up event handlers
        addButton.setOnAction(e -> popupStage.close());
        cancelButton.setOnAction(e -> {
            nameTextField.clear();
            popupStage.close();
        });

        // Layout for the popup
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(nameLabel, nameTextField, addButton, cancelButton);

        // Set up the scene and show the popup
        Scene popupScene = new Scene(layout);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();

        // Return the user input (column name)
        return nameTextField.getText();
    }
}