package c_interface_adapters;

import b_application_business_rules.boundaries.ProjectViewingAndModificationOutputBoundary;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import c_interface_adapters.view_models.ProjectViewModel;
import c_interface_adapters.view_models.TaskViewModel;
import javafx.animation.TranslateTransition;
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.util.Pair;

import java.io.IOException;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import java.util.*;

/**
 * The ProjectViewingAndModificationPresenter class is responsible for managing the presentation
 * logic for viewing and modifying projects in the JavaFX application. It extends the Application
 * class to enable JavaFX initialization, and it implements the ProjectViewingAndModificationOutputBoundary
 * interface to handle interactions with the project viewing and modification scene.
 */
public class ProjectViewingAndModificationPresenter implements ProjectViewingAndModificationOutputBoundary {
    private static Stage stage;
    private static ProjectViewingAndModificationController controller;

    private static final List<VBox> VBoxContainer = new ArrayList<VBox>();

    private static VBox dragDestination;

    private static UIComponentLocator uiComponentLocator;

    TextField nameTextField;
    TextArea detailsTextArea;
    DatePicker dueDatePicker;
    private String columnName;

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
    public void displayNewTask(UUID columnBoxID, TaskViewModel newTask) {
        System.out.println("hi");
    }

    /**
     * Displays the removal of a task.
     *
     * @param taskID The UUID of the task being removed.
     * @param task   The ViewModel representing the removed task.
     */
    @Override
    public void displayRemovedTask(UUID taskID, TaskViewModel task) {
        System.out.println("hello");
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
        System.out.println("Column UI" + columnUI);
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
     * @param taskID
     * @param task
     * @param hbox
     */
    @Override
    public void displayChangedTaskDetails(UUID taskID, TaskViewModel task, HBox hbox, UUID columnID) {
        String taskName = task.getName();
        System.out.println("IN PRESENTER DISPLAY CHANGED TASK DETAILS");

        Text taskNameUI = uiComponentLocator.findTaskName(taskID, columnID);
        if (taskNameUI != null) {
            taskNameUI.setText(task.getName());
        } else {
            System.out.println("TASK IS NOT IN THE COLUMN");
        }
    }


    // TODO: IMPLEMENT THIS.
    @Override
    public void displayDeleteProject(ProjectViewModel project, UUID projectId) {

    }



    /**
     * Populates the UI with the list of columns associated with the current project. For each
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
        ScrollPane scrollPane = createScrollPane();
        VBox columnBox = createColumnBox(column);
        HBox columnNameAndOptions = createColumnNameAndOptions();
        Label columnLabel = createColumnLabel(column.getName());
        MenuButton columnOptions = createColumnOptions();
        addMenuItems(columnOptions, column);
        Button addTaskButton = createAddTaskButton();
        addTaskButton.setOnAction(event -> controller.presenter.handleAddTaskPopup(columnBox));
        HBox TaskBtnVBox = new HBox(addTaskButton);

        scrollPane.setId(column.getID().toString());

        configureSizeConstraints(columnNameAndOptions, columnOptions, TaskBtnVBox);

        columnNameAndOptions.getChildren().addAll(columnLabel, columnOptions, TaskBtnVBox);

        columnBox.getChildren().add(columnNameAndOptions);
        populateTasksForEachColumn(columnBox, column.getTaskModels());

        configureColumnBox(columnBox, scrollPane);
        addToColumnsContainer(scrollPane);
        this.VBoxContainer.add(columnBox); // Add columnBox to VBoxContainer

        configureDragAndDropHandling(columnBox);
    }

    /**
     * Creates and returns a Label for the column name.
     *
     * @param columnName The name of the column.
     * @return The created Label.
     */
    private Label createColumnLabel(String columnName) {
        Label columnLabel = new Label(columnName);
        columnLabel.setId("columnTitle");
        columnLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        return columnLabel;
    }


    /**
     * Creates and returns a ScrollPane for the column.
     *
     * @return The created ScrollPane.
     */
    private ScrollPane createScrollPane() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(200, 400);
        return scrollPane;
    }

    /**
     * Creates and returns a VBox for the column.
     *
     * @param column The ColumnModel representing the column.
     * @return The created VBox.
     */
    private VBox createColumnBox(ColumnModel column) {
        VBox columnBox = new VBox();
        columnBox.setPrefSize(180, 380);
        columnBox.setStyle("-fx-background-color: #F6F8FA");
        columnBox.setId(column.getID().toString());
        return columnBox;
    }

    /**
     * Creates and returns an HBox for the column name and options.
     *
     * @return The created HBox holding column name and options.
     */
    private HBox createColumnNameAndOptions() {
        HBox columnNameAndOptions = new HBox();
        HBox.setHgrow(columnNameAndOptions, Priority.ALWAYS);
        columnNameAndOptions.setSpacing(40);
        columnNameAndOptions.setAlignment(Pos.BASELINE_RIGHT);
        VBox.setMargin(columnNameAndOptions, new Insets(10));
        VBox.setVgrow(columnNameAndOptions, Priority.ALWAYS);
        columnNameAndOptions.setSpacing(5);
        columnNameAndOptions.setId("columnHeader");
        return columnNameAndOptions;
    }

    /**
     * Configures drag-and-drop handling for the given column box.
     *
     * @param columnBox The VBox representing the column.
     */
    private void configureDragAndDropHandling(VBox columnBox) {
        columnBox.setOnDragOver(event -> {
            this.dragDestination = columnBox;
            event.consume();
        });
    }

    /**
     * Creates and returns a MenuButton for column options.
     *
     * @return The created MenuButton.
     */
    private MenuButton createColumnOptions() {
        MenuButton columnOptions = new MenuButton("");
        columnOptions.getStyleClass().add("menu-button-custom");
        return columnOptions;
    }


    /**
     * Adds menu items (options) to the column options menu button.
     *
     * @param columnOptions The MenuButton for column options.
     * @param column The ColumnModel representing the column.
     */
    private void addMenuItems(MenuButton columnOptions, ColumnModel column) {
        MenuItem renameColumnButton = new MenuItem("Rename Column");
        MenuItem deleteColumnButton = new MenuItem("Delete Column");

        renameColumnButton.setOnAction(event -> {
            controller.handleEditColumnDetails(column.getID());
        });
        deleteColumnButton.setOnAction(event -> {
            controller.deleteColumn(column.getID());
        });

        columnOptions.getItems().addAll(deleteColumnButton, renameColumnButton);
    }

    /**
     * Creates and returns a button for adding a task.
     *
     * @return The created Button.
     */
    private Button createAddTaskButton() {
        Button addTaskButton = new Button("Add Task");
        return addTaskButton;
    }

    /**
     * Configures size constraints for UI elements.
     *
     * @param columnNameAndOptions The HBox containing column name and options.
     * @param columnOptions The MenuButton for column options.
     * @param TaskBtnVBox The HBox containing the add task button.
     */
    private void configureSizeConstraints(HBox columnNameAndOptions, MenuButton columnOptions, HBox TaskBtnVBox) {
        HBox.setHgrow(columnOptions, Priority.NEVER);
        HBox.setHgrow(TaskBtnVBox, Priority.NEVER);
        VBox.setVgrow(columnNameAndOptions, Priority.NEVER);
    }

    /**
     * Configures the column box and its associated scroll pane.
     *
     * @param columnBox The VBox representing the column.
     * @param scrollPane The ScrollPane containing the column.
     */
    private void configureColumnBox(VBox columnBox, ScrollPane scrollPane) {
        columnBox.setSpacing(10);
        scrollPane.setContent(columnBox);
    }

    /**
     * Adds the scroll pane to the columns container.
     *
     * @param scrollPane The ScrollPane containing the column.
     */
    private void addToColumnsContainer(ScrollPane scrollPane) {
        HBox columnsContainer = uiComponentLocator.findColumnsContainer();
        columnsContainer.getChildren().add(scrollPane);
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
            HBox hbox = createTaskCard(task);
            setTaskOptions( hbox, task, columnBox.getId());

            if (!addedHBoxIds.contains(hbox.getId())) {
                columnBox.getChildren().add(hbox);
                addedHBoxIds.add(hbox.getId());
            }
        }
    }

    /**
     * Creates a task card (HBox) for the given task.
     *
     * @param task The TaskModel object representing the task.
     * @return The created HBox representing the task card.
     */
    private HBox createTaskCard(TaskModel task) {
        Rectangle cardBackground = new Rectangle(0, 0, Color.LIGHTBLUE);
        Text textContent = new Text(task.getName());
        textContent.setId("taskName");
        cardBackground.setArcHeight(10.0d);
        cardBackground.setArcWidth(10.0d);
        StackPane cardContent = new StackPane(cardBackground, textContent);

        HBox hbox = new HBox(cardContent);
        hbox.setStyle("-fx-border-radius: 10.0d;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 2px;");
        hbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10.0d), Insets.EMPTY)));

        hbox.setSpacing(5);
        hbox.setPadding(new Insets(2));
        hbox.setId(task.getID().toString());

        return hbox;
    }


    /**
     * Sets task options and related UI elements for the task card.
     *
     * @param hbox        The HBox representing the task card.
     * @param task        The TaskModel object representing the task.
     * @param columnBoxId The ID of the parent column box.
     */
    private void setTaskOptions( HBox hbox, TaskModel task, String columnBoxId) {
        MenuButton taskOptionsButton = createTaskOptionsMenu(task, hbox, columnBoxId);
        RadioButton completeTaskButton = new RadioButton();

        hbox.getChildren().addAll(taskOptionsButton, completeTaskButton);
        configureHBoxFeatures(hbox);
    }

    /**
     * Creates a menu button with task-specific options for the task card.
     *
     * @param task        The TaskModel object representing the task.
     * @param hbox        The HBox representing the task card.
     * @param columnBoxId The ID of the parent column box.
     * @return The created MenuButton with task options.
     */
    private MenuButton createTaskOptionsMenu(TaskModel task, HBox hbox, String columnBoxId) {
        MenuButton taskOptionsButton = new MenuButton("");
        MenuItem changeTaskDetailsButton = new MenuItem("Change Task Details");
        MenuItem deleteTaskButton = new MenuItem("Delete Task");
        MenuItem showTaskDetailsButton = new MenuItem("Show Task Details");

        changeTaskDetailsButton.setOnAction(event -> {
            handleChangeTaskPopup(task, hbox, UUID.fromString(columnBoxId));
        });
        deleteTaskButton.setOnAction(event -> {
            controller.deleteTask(task, UUID.fromString(hbox.getId()), UUID.fromString(columnBoxId));
        });
        showTaskDetailsButton.setOnAction(event -> {
            controller.showTaskDetails(task);
        });

        taskOptionsButton.getItems().addAll(changeTaskDetailsButton, deleteTaskButton, showTaskDetailsButton);
        taskOptionsButton.getStyleClass().add("menu-button-custom");
        taskOptionsButton.setStyle("-fx-font-size: 8px;");

        return taskOptionsButton;
    }


    /**
     * Configures various features for an HBox, including drag-and-drop behavior and mouse actions. *
     * Generates a unique identifier for the HBox and sets up its behavior.
     *
     * @param hbox The HBox to configure.
     */
    private void configureHBoxFeatures(HBox hbox) {
//        hbox.setId(UUID.randomUUID().toString());
        configureDragAndDropBehavior(hbox);
        configureHBoxStyleOnMouseActions(hbox);
    }

    /**
     * Configures drag-and-drop behavior for an HBox.
     *
     * @param hbox The HBox to configure drag-and-drop behavior for.
     */
    private void configureDragAndDropBehavior(HBox hbox) {
        hbox.setOnDragDetected(event -> {
            Dragboard dragboard = hbox.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(hbox.getId());
            dragboard.setContent(content);
            WritableImage snapshot = hbox.snapshot(null, null);
            dragboard.setDragView(snapshot);
            event.consume();
        });

        hbox.setOnDragOver(event -> {
            if (event.getGestureSource() == hbox && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        hbox.setOnDragDone(event -> {
            handleDragDone(hbox, event);
            event.consume();
        });
    }

    /**
     * Handles the completion of a drag-and-drop operation.
     *
     * @param hbox  The source HBox being dragged.
     * @param event The DragEvent associated with the operation.
     */
    private void handleDragDone(HBox hbox, DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        boolean success = false;
        if (dragboard.hasString()) {
            HBox sourceHBox = findHBoxById(dragboard.getString());
            if (sourceHBox != null && this.dragDestination != null) {
                moveHBoxToDestination(sourceHBox, hbox);
                success = true;
            }
        }
        event.setDropCompleted(success);
        // ... Perform additional tasks after drag-and-drop
    }

    /**
     * Configures the visual style of an HBox based on mouse actions.
     *
     * @param hbox The HBox to configure the style for.
     */
    private void configureHBoxStyleOnMouseActions(HBox hbox) {
        hbox.setOnMouseEntered(e -> {
            applyHBoxHoverStyle(hbox);
        });

        hbox.setOnMouseExited(e -> {
            resetHBoxStyle(hbox);
        });
    }

    /**
     * Applies a hover style to the given HBox on mouse enter.
     *
     * @param hbox The HBox to apply the hover style to.
     */
    private void applyHBoxHoverStyle(HBox hbox) {
        hbox.setStyle("-fx-border-color: rgba(69,89,164,.5); -fx-border-width: 3px; -fx-border-radius: 10.0d;");
        hbox.setBackground(new Background(new BackgroundFill(Color.rgb(64, 65, 79, 1), new CornerRadii(10.0d), Insets.EMPTY)));
    }

    /**
     * Resets the style of the given HBox on mouse exit.
     *
     * @param hbox The HBox to reset the style for.
     */
    private void resetHBoxStyle(HBox hbox) {
        hbox.setStyle("-fx-border-radius: 10.0d; -fx-border-color: black; -fx-border-width: 2px;");
        hbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10.0d), Insets.EMPTY)));
    }

    /**
     * Moves an HBox to a specified destination HBox within a VBox.
     *
     * @param sourceHBox      The source HBox being moved.
     * @param destinationHBox The destination HBox where the source HBox should be placed.
     */
    private void moveHBoxToDestination(HBox sourceHBox, HBox destinationHBox) {
        VBox sourceColumnBox = (VBox) sourceHBox.getParent();
        sourceColumnBox.getChildren().remove(sourceHBox);

        TranslateTransition transition = new TranslateTransition(Duration.millis(100), sourceHBox);
        transition.setToX(this.dragDestination.getLayoutX() - destinationHBox.getLayoutX());
        transition.play();

        transition.setOnFinished(event -> {
            this.dragDestination.getChildren().add(sourceHBox);
        });
    }

    /**
     * Finds an HBox by its unique identifier within the set of VBox containers.
     *
     * @param id The unique identifier of the HBox to find.
     * @return The found HBox, or null if not found.
     */
    private HBox findHBoxById(String id) {
        System.out.println("this.VBoxContainer " + this.VBoxContainer);
        for (VBox vBox : this.VBoxContainer) {
            for (Node node2 : vBox.getChildren() ) {
                if (node2 instanceof HBox && node2.getId().equals(id)) {
                    return (HBox) node2;
                }
            }
        }
        return null;
    }

    /**
     * Displays a popup window to add a new task to the selected column. *
     *
     * @param columnBox The VBox representing the Column UI where the task will be added.
     */
    public void handleAddTaskPopup(VBox columnBox) {
        Stage popupStage = createPopupStage6();
        GridPane gridPane = createGridPane6();
        addComponentsToGridPane6(gridPane);
        Button addTaskToColumnButton = createAddTaskButton(popupStage, columnBox);

        gridPane.add(addTaskToColumnButton, 0, 3, 2, 1);

        Scene popupScene = new Scene(gridPane, 800, 200);
        popupStage.setScene(popupScene);

        popupStage.showAndWait();
    }

    /**
     * Creates the popup stage for adding a new task.
     *
     * @return The created Stage object for the popup.
     */
    private Stage createPopupStage6() {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Add Task");
        return popupStage;
    }

    /**
     * Creates a GridPane layout for organizing components.
     *
     * @return The created GridPane.
     */
    private GridPane createGridPane6() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        return gridPane;
    }

    /**
     * Adds components (labels, text fields, date picker) to the GridPane.
     *
     * @param gridPane The GridPane to which components are added.
     */
    private void addComponentsToGridPane6(GridPane gridPane) {
        Label nameLabel = new Label("Task Name:");
        nameTextField = new TextField();

        Label detailsLabel = new Label("Task Details:");
        detailsTextArea = new TextArea();
        detailsTextArea.setPrefRowCount(3);

        Label dueDateLabel = new Label("Task Due Date:");
        dueDatePicker = new DatePicker();

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameTextField, 1, 0);
        gridPane.add(detailsLabel, 0, 1);
        gridPane.add(detailsTextArea, 1, 1);
        gridPane.add(dueDateLabel, 0, 2);
        gridPane.add(dueDatePicker, 1, 2);
    }


    /**
     * Creates the "Submit" button for adding the task.
     *
     * @param popupStage The popup stage to be closed.
     * @param columnBox  The VBox representing the Column UI.
     * @return The created "Submit" button.
     */
    private Button createAddTaskButton(Stage popupStage, VBox columnBox) {
        Button addTaskToColumnButton = new Button("Submit");
        addTaskToColumnButton.setOnAction(event -> handleAddTaskButtonClicked(popupStage, columnBox));
        return addTaskToColumnButton;
    }

    /**
     * Handles the "Submit" button click event for adding the task.
     *
     * @param popupStage The popup stage to be closed.
     * @param columnBox  The VBox representing the Column UI.
     */
    private void handleAddTaskButtonClicked(Stage popupStage, VBox columnBox) {
        String taskName = nameTextField.getText().trim();
        String taskDetails = detailsTextArea.getText().trim();
        LocalDate dueDate = dueDatePicker.getValue();

        if (taskName.isEmpty() || taskDetails.isEmpty() || dueDate == null) {
            showAlert("Error", "All fields are required. Please fill in all the details.");
        } else {
            popupStage.close();
            controller.handleAddTaskToColumn(columnBox.getId(), taskName, taskDetails, dueDate.atStartOfDay());
        }
    }

    /**
     * Displays a pop-up window to change task details. *
     *
     * @param task     The task to be edited.
     * @param hbox     The HBox containing the task.
     * @param columnID The ID of the column containing the task.
     */
    public void handleChangeTaskPopup(TaskModel task, HBox hbox, UUID columnID) {
        Stage popupStage = createPopupStage();
        GridPane gridPane = createGridPane();
        addComponentsToGridPane(gridPane);
        Button changeTaskButton = createChangeTaskButton(task, hbox, columnID, popupStage);

        gridPane.add(changeTaskButton, 0, 3, 2, 1);

        Scene popupScene = new Scene(gridPane, 800, 200);
        popupStage.setScene(popupScene);

        popupStage.showAndWait();
    }

    /**
     * Creates the pop-up stage.
     *
     * @return The created Stage object for the pop-up.
     */
    private Stage createPopupStage() {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Change Task Details");
        return popupStage;
    }

    /**
     * Creates a GridPane for organizing components.
     *
     * @return The created GridPane.
     */
    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        return gridPane;
    }

    /**
     * Adds components to the GridPane.
     *
     * @param gridPane The GridPane to which components are added.
     */
    private void addComponentsToGridPane(GridPane gridPane) {
        Label nameLabel = new Label("Task Name:");
        nameTextField = new TextField();

        Label detailsLabel = new Label("Task Details:");
        detailsTextArea = new TextArea();
        detailsTextArea.setPrefRowCount(3);

        Label dueDateLabel = new Label("Task Due Date:");
        dueDatePicker = new DatePicker();

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameTextField, 1, 0);
        gridPane.add(detailsLabel, 0, 1);
        gridPane.add(detailsTextArea, 1, 1);
        gridPane.add(dueDateLabel, 0, 2);
        gridPane.add(dueDatePicker, 1, 2);
    }

    /**
     * Creates the "Submit" button with an action handler.
     *
     * @param task       The task to be edited.
     * @param hbox       The HBox containing the task.
     * @param uuid       The ID of the column containing the task.
     * @param popupStage The pop-up stage.
     * @return The created "Submit" button.
     */
    private Button createChangeTaskButton(TaskModel task, HBox hbox, UUID uuid, Stage popupStage) {
        Button changeTaskButton = new Button("Submit");
        changeTaskButton.setOnAction(event -> handleTaskSubmit(task, hbox, popupStage, uuid));
        return changeTaskButton;
    }

    /**
     * Handles the "Submit" button click event.
     *
     * @param task       The task to be edited.
     * @param hbox       The HBox containing the task.
     * @param popupStage The pop-up stage to be closed.
     * @param uuid       The ID of the column containing the task.
     */
    private void handleTaskSubmit(TaskModel task, HBox hbox, Stage popupStage, UUID uuid) {
        String taskName = nameTextField.getText().trim();
        String taskDetails = detailsTextArea.getText().trim();
        LocalDate dueDate = dueDatePicker.getValue();

        if (taskName.isEmpty() || taskDetails.isEmpty() || dueDate == null) {
            showAlert("Error", "All fields are required. Please fill in all the details.");
        } else {
            popupStage.close();
            System.out.println("DATE AT handleTaskSubmit " +  dueDate.atStartOfDay());
            controller.changeTaskDetails(task, hbox, taskName, taskDetails, dueDate.atStartOfDay(),
                uuid);
        }
    }

    /**
     * Displays a pop-up window to add a new column. *
     *
     * @param addButtonClicked A boolean array to store the result of the pop-up.
     * @return A Pair containing the flag indicating the button clicked and the entered column name (trimmed).
     */
    public Pair<Boolean, String> displayAddColumnPopup(boolean[] addButtonClicked) {
        Stage popupStage = createPopupStage1();
        VBox layout = createPopupLayout(addButtonClicked, popupStage);
        Scene popupScene = new Scene(layout);

        popupStage.setScene(popupScene);
        popupStage.showAndWait();

        return new Pair<>(addButtonClicked[0], getColumnInput(layout));
    }

    /**
     * Creates the pop-up stage.
     *
     * @return The created Stage object for the pop-up.
     */
    private Stage createPopupStage1() {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Add New Column");
        return popupStage;
    }

    /**
     * Creates the layout for the pop-up.
     *
     * @param addButtonClicked The array to store the result of the pop-up.
     * @param popupStage       The pop-up stage.
     * @return A VBox containing the layout components.
     */
    private VBox createPopupLayout(boolean[] addButtonClicked, Stage popupStage) {
        Label nameLabel = new Label("Column Name:");
        TextField nameTextField = new TextField();
        Button addButton = createAddButton(addButtonClicked, popupStage, nameTextField);
        Button cancelButton = createCancelButton(popupStage, nameTextField);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(nameLabel, nameTextField, addButton, cancelButton);
        return layout;
    }

    /**
     * Creates the "Add" button with an action handler.
     *
     * @param addButtonClicked The array to store the result of the pop-up.
     * @param popupStage       The pop-up stage.
     * @param nameTextField    The text field for column name input.
     * @return The created "Add" button.
     */
    private Button createAddButton(boolean[] addButtonClicked, Stage popupStage, TextField nameTextField) {
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            handleAddButtonClicked(addButtonClicked, popupStage, nameTextField);
        });
        return addButton;
    }

    /**
     * Handles the "Add" button click event.
     *
     * @param addButtonClicked The array to store the result of the pop-up.
     * @param popupStage       The pop-up stage to be closed.
     * @param nameTextField    The text field for column name input.
     */
    private void handleAddButtonClicked(boolean[] addButtonClicked, Stage popupStage, TextField nameTextField) {
        String columnName = nameTextField.getText().trim();
        if (columnName.isEmpty()) {
            showAlert("Error", "Column name cannot be empty.");
        } else {
            addButtonClicked[0] = true;
            popupStage.close();
        }
    }

    /**
     * Creates the "Cancel" button with an action handler.
     *
     * @param popupStage    The pop-up stage to be closed.
     * @param nameTextField The text field for column name input.
     * @return The created "Cancel" button.
     */
    private Button createCancelButton(Stage popupStage, TextField nameTextField) {
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            handleCancelButtonClicked(popupStage, nameTextField);
        });
        return cancelButton;
    }

    /**
     * Handles the "Cancel" button click event.
     *
     * @param popupStage    The pop-up stage to be closed.
     * @param nameTextField The text field for column name input.
     */
    private void handleCancelButtonClicked(Stage popupStage, TextField nameTextField) {
        nameTextField.clear();
        popupStage.close();
    }

    /**
     * Gets the entered column name from the layout.
     *
     * @param layout The layout containing the text field.
     * @return The entered column name.
     */
    private String getColumnInput(VBox layout) {
        TextField nameTextField = (TextField) layout.getChildren().get(1);
        return nameTextField.getText().trim();
    }

    /**
     * Displays a dialog for editing column details. *
     *
     * @return The entered column name.
     */
    public String displayEditColumnDetails() {
        Stage dialogStage = createDialogStage();
        VBox vbox = createDialogContent(dialogStage);
        Scene scene = new Scene(vbox, 300, 150);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
        return columnName;
    }

    /**
     * Creates the dialog stage.
     *
     * @return The created Stage object.
     */
    private Stage createDialogStage() {
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Enter Column Name");
        return dialogStage;
    }

    /**
     * Creates the content of the dialog.
     *
     * @param dialogStage The dialog stage.
     * @return A VBox containing the dialog content.
     */
    private VBox createDialogContent(Stage dialogStage) {
        Label label = new Label("Enter the name of the column:");
        TextField textField = new TextField();
        Button okButton = createOkButton(textField, dialogStage);
        Button cancelButton = createCancelButton(dialogStage);

        VBox vbox = new VBox(label, textField, new Separator(), new HBox(okButton, cancelButton));
        vbox.setSpacing(10);
        return vbox;
    }

    /**
     * Creates the OK button with an action handler.
     *
     * @param textField   The text field containing the input text.
     * @param dialogStage The dialog stage.
     * @return The created OK button.
     */
    private Button createOkButton(TextField textField, Stage dialogStage) {
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> handleOkButtonClicked(textField, dialogStage));
        return okButton;
    }

    /**
     * Handles the OK button click event.
     *
     * @param textField   The text field containing the input text.
     * @param dialogStage The dialog stage.
     */
    private void handleOkButtonClicked(TextField textField, Stage dialogStage) {
        String inputText = textField.getText().trim();
        if (!inputText.isEmpty()) {
            columnName = inputText;
            dialogStage.close();
        } else {
            showAlert("Error", "Column name cannot be empty or whitespace-only.");
        }
    }

    /**
     * Creates the Cancel button with an action handler.
     *
     * @param dialogStage The dialog stage.
     * @return The created Cancel button.
     */
    private Button createCancelButton(Stage dialogStage) {
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> handleCancelButtonClicked(dialogStage));
        return cancelButton;
    }


    /**
     * Handles the Cancel button click event.
     *
     * @param dialogStage The dialog stage.
     */
    private void handleCancelButtonClicked(Stage dialogStage) {
        dialogStage.close();
    }

    /**
     * Displays an alert dialog with the specified title and message.
     * The alert type is set to ERROR.
     *
     * @param title   The title of the alert dialog.
     * @param message The content message to be displayed in the alert dialog.
     */
    private void showAlert(String title, String message) {
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
    public static void displayTaskDetails(TaskModel taskModel) {
        Stage popupStage = createPopupStage2();
        VBox vbox = createDetailsLayout(taskModel);
        Scene scene = new Scene(vbox);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }

    /**
     * Creates a new stage for the pop-up window.
     *
     * @return The created Stage object.
     */
    private static Stage createPopupStage2() {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Task Details");
        return popupStage;
    }

    /**
     * Creates the layout for displaying task details in the pop-up.
     *
     * @param taskModel The TaskModel object containing the details of the task.
     * @return A VBox containing the layout with task details.
     */
    private static VBox createDetailsLayout(TaskModel taskModel) {
        VBox vbox = new VBox(10); // 10 pixels spacing between labels
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

        addDetailLabel(vbox, "Name:", taskModel.getName());
        addDetailLabel(vbox, "ID:", taskModel.getID().toString());
        addDetailLabel(vbox, "Description:", taskModel.getDescription());
        addDetailLabel(vbox, "Is Completed:", taskModel.getCompletionStatus() ? "✅ Task is done" : "❌ Task is not completed");
        addDetailLabel(vbox, "Due Date:", taskModel.getDueDateTime().format(dateFormatter));

        return vbox;
    }

    /**
     * Adds a detail label and its corresponding value to the layout.
     *
     * @param vbox      The VBox layout to which the labels are added.
     * @param labelText The label text.
     * @param valueText The value text.
     */
    private static void addDetailLabel(VBox vbox, String labelText, String valueText) {
        Label label = createLabel(labelText);
        Label valueLabel = createValueLabel(valueText);
        vbox.getChildren().addAll(label, valueLabel);
    }

    /**
     * Creates a label with specific styling for task detail labels.
     *
     * @param labelText The text to be displayed on the label.
     * @return A Label with the specified text and styling.
     */
    private static Label createLabel(String labelText) {
        Label label = new Label(labelText);
        label.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333; -fx-font-weight: bold;");
        return label;
    }

    /**
     * Creates a value label with the provided value text.
     *
     * @param valueText The text to be displayed on the value label.
     * @return A Label with the specified value text.
     */
    private static Label createValueLabel(String valueText) {
        Label valueLabel = new Label(valueText);
        return valueLabel;
    }

}