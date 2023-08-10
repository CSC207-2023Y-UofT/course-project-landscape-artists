package c_interface_adapters;

import b_application_business_rules.boundaries.ProjectViewingAndModificationOutputBoundary;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import a_enterprise_business_rules.entities.Task;
import c_interface_adapters.view_models.ProjectViewModel;
import c_interface_adapters.view_models.TaskViewModel;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerSearchController;
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

    public void populateProjectDetails(ProjectModel projectModel) {
        Label projectNameLabel = findProjectNameUI();
        Label projectDescriptionLabel = findProjectDescriptionUI();

        projectNameLabel.setText(projectModel.getName());
        projectDescriptionLabel.setText(projectModel.getDescription());
    }


    private void setUpScene(Parent root, String title, String stylesheetPath) {
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource(stylesheetPath).toExternalForm());
        stage.setScene(scene);
        stage.setTitle(title);

        uiComponentLocator = new UIComponentLocator(scene);
    }

    public Label findProjectNameUI() {
        Scene scene = stage.getScene();
        if (scene != null) {
            // Find the HBox that corresponds to the provided projectUUID
            for (Node node : scene.getRoot().getChildrenUnmodifiable()) {
                System.out.println(node);
                if (node.getId().equals("projectName")) {
                    return (Label) node;
                }
            }
        }
        return null;
    }

    public Label findProjectDescriptionUI() {
        Scene scene = stage.getScene();
        if (scene != null) {
            // Find the HBox that corresponds to the provided projectUUID
            for (Node node : scene.getRoot().getChildrenUnmodifiable()) {
                if (node.getId().equals("projectDescription")) {
                    return (Label) node;
                }
            }
        }
        return null;
    }
    public ProjectViewingAndModificationPresenter(ProjectViewingAndModificationController controller) {
        this.controller = controller;
    }

    public ProjectViewingAndModificationPresenter() {
        this.controller = new ProjectViewingAndModificationController();
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

    @Override
    public void displayNewTask(UUID columnBoxID, TaskViewModel newTask) {
        System.out.println("hi");
    }


    @Override
    public void displayRenamedTask(UUID taskID, TaskViewModel task) {

    }

    @Override
    public void displayRemovedTask(UUID taskID, TaskViewModel task) {
        System.out.println("hello");
    }

    @Override
    public void displayRenamedColumn(ColumnModel column) {
//        String columnUUID = column.getID().toString();
//        String columnName = column.getName();
//
//        Scene scene = stage.getScene();
//        if (scene != null) {
//            // Find the HBox that corresponds to the provided projectUUID
//            for (Node node : scene.getRoot().getChildrenUnmodifiable()) {
//                if (node.getId().equals("scrollPaneContainer")) {
//
//                    if (node instanceof ScrollPane scrollPane){
//                        HBox columnsContainer = (HBox) scrollPane.getContent();
//
//                        Iterator<Node> iterator = columnsContainer.getChildren().iterator();
//                        while (iterator.hasNext()) {
//                            Node containerChild = iterator.next();
//                            if (containerChild.getId().equals(columnUUID)) {
//                                VBox columnUI = (VBox) (((ScrollPane) containerChild).getContent());
//                                for (Node item: columnUI.getChildren()) {
//
//                                    if (item.getId().equals("columnHeader")) {
//                                        Label columnNameUI = (Label) (((HBox) item).getChildren().get(0));
//                                        columnNameUI.setText(columnName);
//                                    }
//                                    break;
//
//                                }
//                            }
//                        }
//                        break;
//                    }
//                }
//            }
//        }
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

    @Override
    public void displayDeletedColumn(ColumnModel columnModel) {
//        String columnUUID = columnModel.getID().toString();
//
//        Scene scene = stage.getScene();
//        System.out.println("SCENE "+scene);
//        if (scene != null) {
//            // Find the HBox that corresponds to the provided projectUUID
//            for (Node node : scene.getRoot().getChildrenUnmodifiable()) {
//                if (node.getId().equals("scrollPaneContainer")) {
//
//                    if (node instanceof ScrollPane scrollPane){
//                        HBox columnsContainer = (HBox) scrollPane.getContent();
//
//                        Iterator<Node> iterator = columnsContainer.getChildren().iterator();
//                        while (iterator.hasNext()) {
//                            Node containerChild = iterator.next();
//                            if (containerChild.getId().equals(columnUUID)) {
//                                columnsContainer.getChildren().remove(containerChild);
//                                break;
//                            }
//                        }
//                        break;
//                    }
//                }
//            }
//        }
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
    public void displayChangedTaskDetails(UUID taskID, TaskViewModel task, HBox hbox) {
        String taskName = task.getName();

        //Removing the existing nane from the Hbox
        hbox.getChildren().removeAll();
        for (Node node: hbox.getChildren()) {
            if (node instanceof Text) {
                ((Text) node).setText(taskName);
                break;
            }
        }
    }

    // TODO: REMOVE THIS.
    @Override
    public void dislayChangedTaskDate(UUID taskID, TaskViewModel task) {

    }

    // TODO: REMOVE THIS.
    @Override
    public void displayRenamedProject(ProjectViewModel project, UUID projectId) {

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
//    @Override
//    public void displayNewColumn(ColumnModel column) {
//        ScrollPane scrollPane = new ScrollPane();
//        scrollPane.setPrefSize(200, 400);
//
//        scrollPane.setId(column.getID().toString());
//
//        VBox columnBox = new VBox();
//        columnBox.setPrefSize(180, 380);
//
//        columnBox.setStyle("-fx-background-color: #F6F8FA");
//
//        // Set styling of the header.
//        HBox columnNameAndOptions = new HBox();
//        HBox.setHgrow(columnNameAndOptions, Priority.ALWAYS);
//
//        columnNameAndOptions.setSpacing(40);
//        columnNameAndOptions.setAlignment(Pos.BASELINE_RIGHT);
//
//        VBox.setMargin(columnNameAndOptions, new Insets(10));
//        VBox.setVgrow(columnNameAndOptions, Priority.ALWAYS);
//
//
//        columnBox.setId(column.getID().toString());
//
//        // Add label for the name of the column
//        Label columnLabel = new Label(column.getName());
//        columnLabel.setId("columnTitle");
//        columnLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
//
//        // Add menu button and menu items.
//        MenuButton columnOptions = new MenuButton("");
//        MenuItem renameColumnButton = new MenuItem("Rename Column");
//        MenuItem deleteColumnButton = new MenuItem("Delete Column");
//
//        Button addTaskButton = new Button("Add Task");
//        addTaskButton.setOnAction(event -> controller.presenter.handleAddTaskPopup(columnBox));
//        HBox TaskBtnVBox = new HBox(addTaskButton);
//
//        // Add event handler on menu item.
//        renameColumnButton.setOnAction(event -> {
//            controller.handleEditColumnDetails(column.getID());});
//        deleteColumnButton.setOnAction(event -> {
//            controller.deleteColumn(column.getID());});
//
//        columnOptions.getItems().addAll(deleteColumnButton, renameColumnButton);
//
//        // Set the size constraints for columnNameAndOptions
//        HBox.setHgrow(columnLabel, Priority.ALWAYS); // Make the label expand horizontally
//        HBox.setHgrow(columnOptions, Priority.NEVER); // Make the button keep its preferred width
//        HBox.setHgrow(TaskBtnVBox, Priority.NEVER); // Make the button keep its preferred width
//
//        columnNameAndOptions.getChildren().addAll(columnLabel, columnOptions, TaskBtnVBox);
//        columnNameAndOptions.setSpacing(5);
//        columnNameAndOptions.setId("columnHeader");
//
//        // set styling of menu button
//        columnOptions.getStyleClass().add("menu-button-custom");
//
//        // Set the size constraints for columnBox
//        VBox.setVgrow(columnNameAndOptions, Priority.NEVER); // Make columnNameAndOptions keep its preferred height
//
//        columnBox.getChildren().add(columnNameAndOptions);
//
//        // Populate tasks for each column and add an "Add Task" button
//        controller.presenter.populateTasksForEachColumn(columnBox, column.getTaskModels(),
//                controller);
//
//        //columnBox.getChildren().add(TaskBtnVBox);
//        //VBox.setVgrow(TaskBtnVBox, Priority.NEVER);
////        TaskBtnVBox.setAlignment(Pos.BOTTOM_RIGHT);
//        columnBox.setSpacing(10);
//        scrollPane.setContent(columnBox);
//
//        // Add the column UI to the container of all columns (HBox)
//        controller.columnsContainer.getChildren().add(scrollPane);
//        this.VBoxContainer.add(columnBox);
//
//        columnBox.setOnDragOver(event -> {
//            System.out.println("INSIDE DESTINATION");
//            this.dragDestination = columnBox;
//            event.consume();
//        });
//    }

    /**
     * Displays a new column in the task management application.
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

    // TODO: THIS WAS THE INITIAL. MAYBE USE THIS INSTEAD BUT FIX IT UP.
//    /**
//     * Displays a new column UI element.
//     *
//     * @param column The ColumnModel representing the new column.
//     */
//    public void displayNewColumn(ColumnModel column) {
//        ScrollPane scrollPane = createScrollPane();
//        scrollPane.setId(column.getID().toString());
//
//        VBox columnBox = createColumnBox();
//        columnBox.setId(column.getID().toString());
//
//        HBox columnNameAndOptions = createColumnNameAndOptions(column, columnBox);
//        VBox.setMargin(columnNameAndOptions, new Insets(10));
//
//        columnBox.getChildren().add(columnNameAndOptions);
//        populateTasksForEachColumn(columnBox, column.getTaskModels(), controller);
//
//        columnBox.setSpacing(10);
//        scrollPane.setContent(columnBox);
//
//        HBox columnsContainer = uiComponentLocator.findColumnsContainer();
//        columnsContainer.getChildren().add(scrollPane);
//        this.VBoxContainer.add(columnBox);
//
//        columnBox.setOnDragOver(event -> {
//            this.dragDestination = columnBox;
//            event.consume();
//        });
//    }
//
//    /**
//     * Creates a new ScrollPane with predefined dimensions.
//     *
//     * @return The created ScrollPane.
//     */
//    private ScrollPane createScrollPane() {
//        ScrollPane scrollPane = new ScrollPane();
//        scrollPane.setPrefSize(200, 400);
//        return scrollPane;
//    }
//
//
//    /**
//     * Creates a new VBox for the column with predefined dimensions and background color.
//     *
//     * @return The created VBox.
//     */
//    private VBox createColumnBox() {
//        VBox columnBox = new VBox();
//        columnBox.setPrefSize(180, 380);
//        columnBox.setStyle("-fx-background-color: #F6F8FA");
//        return columnBox;
//    }
//
//    /**
//     * Creates a new HBox for the column name and options.
//     *
//     * @param column The ColumnModel associated with the column.
//     * @param columnBox The VBox representing the column.
//     * @return The created HBox.
//     */
//    private HBox createColumnNameAndOptions(ColumnModel column, VBox columnBox) {
//        HBox columnNameAndOptions = new HBox();
//        HBox.setHgrow(columnNameAndOptions, Priority.ALWAYS);
//        columnNameAndOptions.setAlignment(Pos.BASELINE_RIGHT);
//        columnNameAndOptions.setSpacing(5);
//
//        Label columnLabel = createColumnLabel(column);
//        MenuButton columnOptions = createColumnOptions(column);
//        Button addTaskButton = createAddTaskButton(columnBox, column);
//
//        columnNameAndOptions.getChildren().addAll(columnLabel, columnOptions, addTaskButton);
//        return columnNameAndOptions;
//    }
//
//    /**
//     * Creates a label for the column name with specific styling.
//     *
//     * @param column The ColumnModel associated with the column.
//     * @return The created label.
//     */
//    private Label createColumnLabel(ColumnModel column) {
//        Label columnLabel = new Label(column.getName());
//        columnLabel.setId("columnTitle");
//        columnLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
//        HBox.setHgrow(columnLabel, Priority.ALWAYS);
//        return columnLabel;
//    }
//
//    /**
//     * Creates a MenuButton for column options with associated actions and styling.
//     *
//     * @param column The ColumnModel associated with the column.
//     * @return The created MenuButton.
//     */
//    private MenuButton createColumnOptions(ColumnModel column) {
//        MenuButton columnOptions = new MenuButton("");
//        MenuItem renameColumnButton = new MenuItem("Rename Column");
//        MenuItem deleteColumnButton = new MenuItem("Delete Column");
//
//        renameColumnButton.setOnAction(event -> {
//            controller.handleEditColumnDetails(column.getID());
//        });
//
//        deleteColumnButton.setOnAction(event -> {
//            controller.deleteColumn(column.getID());
//        });
//
//        columnOptions.getItems().addAll(deleteColumnButton, renameColumnButton);
//        columnOptions.getStyleClass().add("menu-button-custom");
//        HBox.setHgrow(columnOptions, Priority.NEVER);
//        return columnOptions;
//    }
//
//    /**
//     * Creates a Button for adding a task with associated actions.
//     *
//     * @param columnBox The VBox representing the column.
//     * @param column The ColumnModel associated with the column.
//     * @return The created Button.
//     */
//    private Button createAddTaskButton(VBox columnBox, ColumnModel column) {
//        Button addTaskButton = new Button("Add Task");
//        addTaskButton.setOnAction(event -> {
//            controller.presenter.handleAddTaskPopup(columnBox);
//        });
//        HBox.setHgrow(addTaskButton, Priority.NEVER);
//        return addTaskButton;
//    }
    // TODO: END


//    /**
//     * Populates the UI with tasks for each column. For each task, an HBox is created with the task
//     * name and an options button. The HBox is then added to the VBox representing the column UI.
//     *
//     * @param columnBox                               The VBox representing the Column UI. Items added to this JavaFX component
//     *                                                are stacked vertically.
//     * @param tasks                                   The list of Task instances belonging to the columnBox.
//     * @param projectViewingAndModificationController
//     */
//    void populateTasksForEachColumn(VBox columnBox, List<TaskModel> tasks, ProjectViewingAndModificationController projectViewingAndModificationController) {
//        // Create a set to store the unique IDs of the HBox nodes added to the columnBox
//        Set<String> addedHBoxIds = new HashSet<>();
//
//        // Iterate through the list of tasks and create an HBox for each task
//        for (TaskModel task : tasks) {
//            // Create the card content
//
//            Rectangle cardBackground = new Rectangle(columnBox.getWidth(), 50, Color.LIGHTBLUE);
//            Text textContent = new Text(task.getName());
//            cardBackground.setArcHeight(10.0d);
//            cardBackground.setArcWidth(10.0d);
//            StackPane cardContent = new StackPane(cardBackground, textContent);
//
//            // Create the card (HBox) to hold the content
//            HBox hbox = new HBox(cardContent);
//            hbox.setStyle("-fx-border-radius: 10.0d;" +
//                    "-fx-border-color: black;" +
//                    "-fx-border-width: 2px;"); // Add a border for better visibility
//            hbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10.0d), Insets.EMPTY)));
//
//            //Create menu button and its options.
//            MenuButton taskOptionsButton = new MenuButton("");
////            MenuItem renameTaskButton = new MenuItem("Rename Task");
//            MenuItem changeTaskDetailsButton = new MenuItem("Change Task " +
//                    "Details");
//            MenuItem deleteTaskButton = new MenuItem("Delete Task");
//            MenuItem showTaskDetailsButton = new MenuItem("Show Task Details");
//
//            //Event handler for the changing task details. Calls another method on this presenter
//            changeTaskDetailsButton.setOnAction(event -> {
//
//                this.handleChangeTaskPopup(task, hbox, UUID.fromString(columnBox.getId()));
//
//                });
//
//            deleteTaskButton.setOnAction(event -> {
//                projectViewingAndModificationController.deleteTask(task, UUID.fromString(hbox.getId()),
//                        UUID.fromString(columnBox.getId()));});
//            showTaskDetailsButton.setOnAction(event -> {
//                projectViewingAndModificationController.showTaskDetails(task);});
//
//            // Add to MenuButton
//
//            taskOptionsButton.getItems().addAll(
//                    changeTaskDetailsButton, deleteTaskButton, showTaskDetailsButton);
//
//            taskOptionsButton.getStyleClass().add("menu-button-custom");
//
//
//
//            taskOptionsButton.setStyle("-fx-font-size: 8px;");
//
//            RadioButton completeTaskButton = new RadioButton();
//
//            hbox.getChildren().addAll(textContent, taskOptionsButton, completeTaskButton);
//            SetHBoxFeatures(columnBox, hbox);
//
//            hbox.setSpacing(5); // Set spacing between text and menuButton
//            hbox.setPadding(new Insets(2)); // Add some padding for better appearance
//            columnBox.setSpacing(10);
//            if (!addedHBoxIds.contains(hbox.getId())) {
//                // Add the HBox to the columnBox if it doesn't exist
//                columnBox.getChildren().add(hbox);
//                addedHBoxIds.add(hbox.getId()); // Add the HBox ID to the set
//            }
//        }
//    }
    /**
     * Populates the specified column box with task cards based on the provided task models.
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
        SetHBoxFeatures(hbox);
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

    private void SetHBoxFeatures(HBox hbox) {
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
            System.out.println("DRAGBOARD" + dragboard.hasString());

            if (dragboard.hasString()) {
                // Find the source HBox based on the unique identifier
                HBox sourceHBox = findHBoxById(dragboard.getString());

                System.out.println("SOURCE HBOX" + sourceHBox);

                // Move the HBox to the new column
                if (sourceHBox != null) {
                    System.out.println("INSIDE SUCCESS");
                    // Find the destination VBox (the column box that the HBox is dragged into)

                    //System.out.println(hbox.getParent().equals(sourceHBox.getParent()));
                    //VBox destinationColumnBox = (VBox) hbox.getParent();

                    // Remove the HBox from its current column box and add it to the destination column box
                    //VBox destinationColumnBox = findTargetDestinationVBox(event);
                    System.out.println("DRAG DESTINATION " + dragDestination);
                    if (this.dragDestination != null) {
                        System.out.println("DRAGDESTINATION IS NOT NULL");
                        // Remove the HBox from its current column box and add it to the destination column box
                        ((VBox) sourceHBox.getParent()).getChildren().remove(sourceHBox);
                        System.out.println("ITEMS INSIDE SOURCE HBOX " + sourceHBox.getChildren());
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
            DBManagerSearchController dbManagerSearchController = new DBManagerSearchController();
            dbManagerSearchController.DBTaskSearch(hbox.getId());
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
     * Handles displaying a popup window when the "Add Task" button is clicked. The popup allows the
     * user to enter task details such as name, description, and due date, and then add the task to
     * the selected column.
     *
     * @param columnBox                               The VBox representing the Column UI where the task will be added.
     */
    void handleAddTaskPopup(VBox columnBox) {
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
            String taskName = nameTextField.getText().trim();
            String taskDetails = detailsTextArea.getText().trim();
            LocalDate dueDate = dueDatePicker.getValue();

            if (taskName.isEmpty() || taskDetails.isEmpty() || dueDate == null) {
                // Show an alert if any of the fields are empty
                showAlert("Error", "All fields are required. Please fill in all the details.");
            } else {
                // Close the popup when "Submit" button is pressed
                popupStage.close();

                // Call the method to handle adding the task to the column
                controller.handleAddTaskToColumn(columnBox.getId(), taskName, taskDetails, dueDate.atStartOfDay());
            }
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
     * Creates a popup screen once the changeTaskDetail button is pressed. Takes in user input
     * on the task name, task description and due date and then calls the
     * ProjectViewingAndModificationController
     *
     * @param task
     * @param hbox
     * @param uuid
     */
    void handleChangeTaskPopup(TaskModel task, HBox hbox, UUID uuid) {

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

        // Create the "Change Task" button for submitting the task
        Button changeTaskButton = new Button("Submit");

        // Handles the action of putting a new task in the correct Column UI.
        changeTaskButton.setOnAction(event -> {
            String taskName = nameTextField.getText().trim();
            String taskDetails = detailsTextArea.getText().trim();
            LocalDate dueDate = dueDatePicker.getValue();


            if (taskName.isEmpty() || taskDetails.isEmpty() || dueDate == null) {
                // Show an alert if any of the fields are empty
                showAlert("Error", "All fields are required. Please fill in all the details.");
            } else {
                // Close the popup when "Submit" button is pressed
                popupStage.close();

                // Call the method to handle changing the task details
                controller.changeTaskDetails(task, hbox, taskName, taskDetails, dueDate.atStartOfDay(), uuid);
            }

        });

        // Add the "Change Task" button to the GridPane
        gridPane.add(changeTaskButton, 0, 3, 2, 1);

        // Create the scene and set it on the stage
        Scene popupScene = new Scene(gridPane, 800, 200);
        popupStage.setScene(popupScene);

        // Show the popup
        popupStage.showAndWait();
    }


    /**
     * Displays a pop-up window to allow the user to enter a new column name.
     * The user can click the "Add" button to confirm the input and add the new column,
     * or click the "Cancel" button to cancel the operation.
     *
     * @param addButtonClicked A boolean array used to store the result of the pop-up.
     *                         The first element (index 0) will be set to true or false if "Add" button is clicked or
     *                         not.
     * @return A Pair containing the flag indicating the button clicked
     *         (true for "Add", false for "Cancel") and the entered column name (trimmed).
     */
    public Pair<Boolean, String> displayAddColumnPopup(boolean[] addButtonClicked) {
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
        addButton.setOnAction(e -> {
            String columnName = nameTextField.getText().trim();
            if (columnName.isEmpty()) {
                showAlert("Error", "Column name cannot be empty.");
            } else {
                addButtonClicked[0] = true; // Set the flag to true when "Add" button is clicked
                popupStage.close();
            }
        });

        cancelButton.setOnAction(e -> {
            addButtonClicked[0] = false; // Set the flag to false when "Cancel" button is clicked
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

        // Return the result as a Pair containing the flag and column name
        return new Pair<>(addButtonClicked[0], nameTextField.getText().trim());
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



    /**
     * Displays a new popup window for the user to input what the new name should be for the chosen column.
     */
    public String displayEditColumnDetails() {
        final String[] columnNameContainer = {null}; // Container for columnName

        // Create the dialog
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Enter Column Name");

        // Create controls
        Label label = new Label("Enter the name of the column:");
        TextField textField = new TextField();
        Button okButton = new Button("OK");
        Button cancelButton = new Button("Cancel");

        okButton.setOnAction(e -> {
            String inputText = textField.getText().trim();
            if (!inputText.isEmpty()) {
                columnNameContainer[0] = inputText;
                dialogStage.close();
            } else {
                // Show an error message or take any other action to inform the user
                // that a valid name is required.
                // For simplicity, we'll just show an alert.
                showAlert("Error", "Column name cannot be empty or whitespace-only.");
            }
        });

        cancelButton.setOnAction(e -> {
            dialogStage.close();
        });

        // Layout the controls
        VBox vbox = new VBox(label, textField, new Separator(), new HBox(okButton, cancelButton));
        vbox.setSpacing(10);

        // Set the scene
        Scene scene = new Scene(vbox, 300, 150);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();

        return columnNameContainer[0];
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
     * Displays a pop-up window to show the details of a given TaskModel.
     *
     * @param taskModel The TaskModel object containing the details of the task to be displayed.
     */
    public static void displayTaskDetails(TaskModel taskModel) {
        // Create a new stage for the pop-up window
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL); // Block interactions with other windows
        popupStage.setTitle("Task Details");

        // Create labels to display the task details with inline styles
        Label nameLabel = new Label("Name:");
        nameLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333; -fx-font-weight: bold;");

        Label idLabel = new Label("ID:");
        idLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333; -fx-font-weight: bold;");

        Label descriptionLabel = new Label("Description:");
        descriptionLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333; -fx-font-weight: bold;");

        Label completedLabel = new Label("Is Completed:");
        completedLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333; -fx-font-weight: bold;");

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        Label dueDateLabel = new Label("Due Date:");
        dueDateLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333; -fx-font-weight: bold;");

        // Create labels for the task details (values)
        Label nameValueLabel = new Label(taskModel.getName());
        Label idValueLabel = new Label(taskModel.getID().toString());
        Label descriptionValueLabel = new Label(taskModel.getDescription());
        Label completedValueLabel = new Label(taskModel.getCompletionStatus() ? "✅ Task is done" : "❌ Task is not completed");
        Label dueDateValueLabel = new Label(taskModel.getDueDateTime().format(dateFormatter));

        // Create a VBox to hold the labels with inline styles
        VBox vbox = new VBox(10); // 10 pixels spacing between labels
        vbox.getChildren().addAll(
                nameLabel, nameValueLabel,
                idLabel, idValueLabel,
                descriptionLabel, descriptionValueLabel,
                completedLabel, completedValueLabel,
                dueDateLabel, dueDateValueLabel
        );
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        // Set the scene on the stage
        Scene scene = new Scene(vbox);
        popupStage.setScene(scene);

        // Show the pop-up window and wait for it to be closed before returning
        popupStage.showAndWait();
    }


}