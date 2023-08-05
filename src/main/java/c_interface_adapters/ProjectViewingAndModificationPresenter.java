package c_interface_adapters;

import b_application_business_rules.boundaries.ProjectViewingAndModificationOutputBoundary;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import a_enterprise_business_rules.entities.Task;
import c_interface_adapters.view_models.ProjectViewModel;
import c_interface_adapters.view_models.TaskViewModel;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

/**
 * The ProjectViewingAndModificationPresenter class is responsible for managing the presentation
 * logic for viewing and modifying projects in the JavaFX application. It extends the Application
 * class to enable JavaFX initialization, and it implements the ProjectViewingAndModificationOutputBoundary
 * interface to handle interactions with the project viewing and modification scene.
 */
public class ProjectViewingAndModificationPresenter extends Application implements ProjectViewingAndModificationOutputBoundary {
    private Stage stage;
    private ProjectViewingAndModificationController controller;

    private final List<VBox> VBoxContainer = new ArrayList<VBox>();

    private VBox dragDestination;

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
    }

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
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("ProjectSelectionStyle.css").toExternalForm());
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayNewTask(UUID columnBoxID, TaskViewModel newTask) {
        System.out.println("hi");
    }

//    public void dispayProjectDescription(ProjectModel project) {
//        Stage popupStage = new Stage();
//        String projectDescription = project.getDescription(); // Provide the project's description here
//        // Create the label to display the project description
//        Label descriptionLabel = new Label(projectDescription);
//        descriptionLabel.setWrapText(true); // Enable text wrapping for long descriptions
//
//        // Create a StackPane to hold the label
//        StackPane root = new StackPane(descriptionLabel);
//
//        // Set the size for the new window
//        Scene scene = new Scene(root, 400, 300);
//
//        // Set the stage properties
//        popupStage.setTitle("Project Description");
//        popupStage.setScene(scene);
//
//        // Show the new window
//        popupStage.show();
//    }

    @Override
    public void displayRenamedTask(UUID taskID, TaskViewModel task) {

    }

    @Override
    public void displayRemovedTask(UUID taskID, TaskViewModel task) {
        System.out.println("hello");
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

                    if (node instanceof ScrollPane scrollPane){
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
                                    }
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

                    if (node instanceof ScrollPane scrollPane){
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

    @Override
    public void dislayChangedTaskDetails(UUID taskID, TaskViewModel task) {

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

        columnBox.setStyle("-fx-background-color: #F6F8FA");

        // Set styling of the header.
        HBox columnNameAndOptions = new HBox();
        HBox.setHgrow(columnNameAndOptions, Priority.ALWAYS);

        columnNameAndOptions.setSpacing(40);
        columnNameAndOptions.setAlignment(Pos.BASELINE_RIGHT);

        VBox.setMargin(columnNameAndOptions, new Insets(10));
        VBox.setVgrow(columnNameAndOptions, Priority.ALWAYS);


        columnBox.setId(column.getID().toString());

        // Add label for the name of the column
        Label columnLabel = new Label(column.getName());
        columnLabel.setId("columnTitle");
        columnLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        // Add menu button and menu items.
        MenuButton columnOptions = new MenuButton("");
        MenuItem renameColumnButton = new MenuItem("Rename Column");
        MenuItem deleteColumnButton = new MenuItem("Delete Column");

        Button addTaskButton = new Button("Add Task");
        addTaskButton.setOnAction(event -> controller.presenter.handleAddTaskPopup(columnBox,
                controller));
        HBox TaskBtnVBox = new HBox(addTaskButton);

        // Add event handler on menu item.
        renameColumnButton.setOnAction(event -> {
            controller.renameColumm(column.getID());});
        deleteColumnButton.setOnAction(event -> {
            controller.deleteColumn(column.getID());});

        columnOptions.getItems().addAll(deleteColumnButton, renameColumnButton);

        // Set the size constraints for columnNameAndOptions
        HBox.setHgrow(columnLabel, Priority.ALWAYS); // Make the label expand horizontally
        HBox.setHgrow(columnOptions, Priority.NEVER); // Make the button keep its preferred width
        HBox.setHgrow(TaskBtnVBox, Priority.NEVER); // Make the button keep its preferred width

        columnNameAndOptions.getChildren().addAll(columnLabel, columnOptions, TaskBtnVBox);
        columnNameAndOptions.setSpacing(5);
        columnNameAndOptions.setId("columnHeader");

        // set styling of menu button
        columnOptions.getStyleClass().add("menu-button-custom");

        // Set the size constraints for columnBox
        VBox.setVgrow(columnNameAndOptions, Priority.NEVER); // Make columnNameAndOptions keep its preferred height

        columnBox.getChildren().add(columnNameAndOptions);

        // Populate tasks for each column and add an "Add Task" button
        controller.presenter.populateTasksForEachColumn(columnBox, column.getTaskModels(),
                controller);

        //columnBox.getChildren().add(TaskBtnVBox);
        //VBox.setVgrow(TaskBtnVBox, Priority.NEVER);
//        TaskBtnVBox.setAlignment(Pos.BOTTOM_RIGHT);
        columnBox.setSpacing(10);
        scrollPane.setContent(columnBox);

        // Add the column UI to the container of all columns (HBox)
        controller.columnsContainer.getChildren().add(scrollPane);
        this.VBoxContainer.add(columnBox);

        columnBox.setOnDragOver(event -> {
            //System.out.println("INSIDE DESTINATION");
            this.dragDestination = columnBox;
            event.consume();
        });
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
        // Create a set to store the unique IDs of the HBox nodes added to the columnBox
        Set<String> addedHBoxIds = new HashSet<>();


        // Iterate through the list of tasks and create an HBox for each task
        for (TaskModel task : tasks) {
            // Create the card content

            Rectangle cardBackground = new Rectangle(columnBox.getWidth(), 50, Color.LIGHTBLUE);
            Text textContent = new Text(task.getName());
            cardBackground.setArcHeight(10.0d);
            cardBackground.setArcWidth(10.0d);
            StackPane cardContent = new StackPane(cardBackground, textContent);

            // Create the card (HBox) to hold the content
            HBox hbox = new HBox(cardContent);
            hbox.setStyle("-fx-border-radius: 10.0d;" +
                    "-fx-border-color: black;" +
                    "-fx-border-width: 2px;"); // Add a border for better visibility
            hbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10.0d), Insets.EMPTY)));

            //Create menu button and its options.
            MenuButton taskOptionsButton = new MenuButton("");
            MenuItem renameTaskButton = new MenuItem("Rename Task");
            MenuItem changeTaskDetailsButton = new MenuItem("Change Task " +
                    "Details");
            MenuItem deleteTaskButton = new MenuItem("Delete Task");

            // Add event handlers.
            renameTaskButton.setOnAction(event -> {
                projectViewingAndModificationController.renameTask(task, hbox);});
            changeTaskDetailsButton.setOnAction(event -> {
                projectViewingAndModificationController.changeTaskDetails(
                        task, hbox);});
            deleteTaskButton.setOnAction(event -> {
                projectViewingAndModificationController.deleteTask(task, UUID.fromString(hbox.getId()),
                        UUID.fromString(columnBox.getId()));});

            // Add to MenuButton
            taskOptionsButton.getItems().addAll(renameTaskButton,
                    changeTaskDetailsButton, deleteTaskButton);
            taskOptionsButton.getStyleClass().add("menu-button-custom");



            taskOptionsButton.setStyle("-fx-font-size: 8px;");

            RadioButton completeTaskButton = new RadioButton();
//            completeTaskButton.setOnAction(event -> controller.completeTask(task));

            taskOptionsButton.setOnAction(actionEvent -> {
                projectViewingAndModificationController.handleTaskOptions(actionEvent, task, columnBox);
            });

            hbox.getChildren().addAll(textContent, taskOptionsButton, completeTaskButton);
            SetHBoxFeatures(columnBox, hbox);

            hbox.setSpacing(5); // Set spacing between text and menuButton
            hbox.setPadding(new Insets(2)); // Add some padding for better appearance
            columnBox.setSpacing(10);
            if (!addedHBoxIds.contains(hbox.getId())) {
                // Add the HBox to the columnBox if it doesn't exist
                columnBox.getChildren().add(hbox);
                addedHBoxIds.add(hbox.getId()); // Add the HBox ID to the set
            }
        }
    }

    private void SetHBoxFeatures(VBox columnBox, HBox hbox) {
        // Set the unique identifier for the HBox
        hbox.setId(UUID.randomUUID().toString());

        // Set mouse event handlers for dragging the card
        hbox.setOnDragDetected(event -> {
            Dragboard dragboard = hbox.startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putString(hbox.getId()); // Use the unique identifier for the HBox as the data to be transferred
            dragboard.setContent(content);
            WritableImage snapshot = hbox.snapshot(null, null);
            dragboard.setDragView(snapshot);

            event.consume();
        });

        hbox.setOnDragOver(event -> {
            //System.out.println("INSIDE 1");
            if (event.getGestureSource() == hbox && event.getDragboard().hasString()) {
                //System.out.println("INSIDE 1.5");
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        });

        hbox.setOnDragDone(event -> {
            //System.out.println("INSIDE 2");

            Dragboard dragboard = event.getDragboard();
            boolean success = false;

            if (dragboard.hasString()) {
                // Find the source HBox based on the unique identifier
                HBox sourceHBox = findHBoxById(dragboard.getString());

                // Move the HBox to the new column
                if (sourceHBox != null) {
                    //System.out.println("INSIDE SUCCESS");
                    // Find the destination VBox (the column box that the HBox is dragged into)

                    //System.out.println(hbox.getParent().equals(sourceHBox.getParent()));
                    //VBox destinationColumnBox = (VBox) hbox.getParent();

                    // Remove the HBox from its current column box and add it to the destination column box
                    //VBox destinationColumnBox = findTargetDestinationVBox(event);

                    if (this.dragDestination != null) {
                        // Remove the HBox from its current column box and add it to the destination column box
                        ((VBox) sourceHBox.getParent()).getChildren().remove(sourceHBox);
                        TranslateTransition transition = new TranslateTransition(Duration.millis(100), sourceHBox);
                        transition.setToX(this.dragDestination.getLayoutX() - hbox.getLayoutX());
                        transition.play();

                        transition.setOnFinished(event1 -> {
                            this.dragDestination.getChildren().add(sourceHBox);
                        });
                        success = true;
                    } else {
                        System.out.println("Destination VBox not found!");
                    }
                }
            }

            event.setDropCompleted(success);
            event.consume();
        });

        // Set the style when the cursor enters the HBox
        hbox.setOnMouseEntered(e -> {
            hbox.setStyle("-fx-border-color: rgba(69,89,164,.5); -fx-border-width: 3px; -fx-border-radius: 10.0d;");
            hbox.setBackground(new Background(new BackgroundFill(Color.rgb(64, 65, 79, 1), new CornerRadii(10.0d), Insets.EMPTY)));
        });

        // Set the style when the cursor exits the HBox
        hbox.setOnMouseExited(e -> {
            hbox.setStyle("-fx-border-radius: 10.0d;" +
                    "-fx-border-color: black;" +
                    "-fx-border-width: 2px;");
            hbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10.0d), Insets.EMPTY)));
        });
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
            projectViewingAndModificationController.handleAddTaskToColumn(columnBox.getId(), nameTextField.getText(),
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
//    public static HBox createKanbanCard(HBox originalCard, TaskModel taskModel) {
//        // Create the card content
//        Rectangle cardBackground = new Rectangle(100, 50, Color.LIGHTBLUE);
//        Text textContent = new Text(taskModel.getName());
//        StackPane cardContent = new StackPane(cardBackground, textContent);
//        cardBackground.setArcHeight(10.0d);
//        cardBackground.setArcWidth(10.0d);
//
//        // Create the card (HBox) to hold the content
//        HBox card = new HBox(cardContent);
//        card.getStyleClass().add("kanban-card");
//
//        return card;
//    }

    private HBox findHBoxById(String id) {
        for (VBox vBox : this.VBoxContainer) {
            for (Node node2 : vBox.getChildren() ) {
                if (node2 instanceof HBox && node2.getId().equals(id)) {
                    return (HBox) node2;
                }
            }
        }
        return null;
    }
}