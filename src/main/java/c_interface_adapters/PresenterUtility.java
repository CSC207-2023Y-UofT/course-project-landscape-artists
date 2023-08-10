package c_interface_adapters;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.TaskModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.UUID;

/**
 * The PresenterUtility class serves as a utility class designed to assist presenter classes with creating and
 * managing various user interface (UI) components. It encapsulates methods related to generating new UI elements
 * and configuring their properties. PresenterUtility methods are designed to streamline the process of creating
 * and customizing UI components, enhancing the readability and maintainability of presenter classes.
 *
 * This utility class collaborates with presenter classes to facilitate the construction of consistent and visually
 * appealing UIs, supporting a smoother development process when handling UI-related tasks.
 */
public class PresenterUtility {


    /**
     * Creates and returns a Label for the column name.
     *
     * @param columnName The name of the column.
     * @return The created Label.
     */
    Label createColumnLabel(String columnName) {
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
    ScrollPane createScrollPane() {
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
    VBox createColumnBox(ColumnModel column) {
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
    HBox createColumnNameAndOptions() {
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
     * Creates and returns a MenuButton for column options.
     *
     * @return The created MenuButton.
     */
    MenuButton createColumnOptions() {
        MenuButton columnOptions = new MenuButton("");
        columnOptions.getStyleClass().add("menu-button-custom");
        return columnOptions;
    }

    /**
     * Adds menu items (options) to the column options menu button.
     *
     * @param columnOptions The MenuButton for column options.
     * @param column        The ColumnModel representing the column.
     */
    void addMenuItems(MenuButton columnOptions, ColumnModel column) {
        MenuItem renameColumnButton = new MenuItem("Rename Column");
        MenuItem deleteColumnButton = new MenuItem("Delete Column");

        renameColumnButton.setOnAction(event -> {
            ProjectViewingAndModificationPresenter.controller.handleEditColumnDetails(column.getID());
        });
        deleteColumnButton.setOnAction(event -> {
            ProjectViewingAndModificationPresenter.controller.deleteColumn(column.getID());
        });

        columnOptions.getItems().addAll(deleteColumnButton, renameColumnButton);
    }

    /**
     * Creates and returns a button for adding a task.
     *
     * @return The created Button.
     */
    Button createAddTaskButton() {
        Button addTaskButton = new Button("Add Task");
        return addTaskButton;
    }

    /**
     * Configures size constraints for Column UI elements.
     *
     * @param columnNameAndOptions The HBox containing column name and options.
     * @param columnOptions        The MenuButton for column options.
     * @param TaskBtnVBox          The HBox containing the add task button.
     */
    void configureSizeConstraints(HBox columnNameAndOptions, MenuButton columnOptions, HBox TaskBtnVBox) {
        HBox.setHgrow(columnOptions, Priority.NEVER);
        HBox.setHgrow(TaskBtnVBox, Priority.NEVER);
        VBox.setVgrow(columnNameAndOptions, Priority.NEVER);
    }

    /**
     * Configures the column box and its associated scroll pane.
     *
     * @param columnBox  The VBox representing the column.
     * @param scrollPane The ScrollPane containing the column.
     */
    void configureColumnBox(VBox columnBox, ScrollPane scrollPane) {
        columnBox.setSpacing(10);
        scrollPane.setContent(columnBox);
    }

    /**
     * Adds the scroll pane to the columns container.
     *
     * @param scrollPane The ScrollPane containing the column.
     */
    void addToColumnsContainer(ScrollPane scrollPane) {
        HBox columnsContainer = ProjectViewingAndModificationPresenter.uiComponentLocator.findColumnsContainer();
        columnsContainer.getChildren().add(scrollPane);
    }

    /**
     * Creates a task card (HBox) for the given task.
     *
     * @param task The TaskModel object representing the task.
     * @return The created HBox representing the task card.
     */
    HBox createTaskCard(TaskModel task) {
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
     * Creates a menu button with task-specific options for the task card.
     *
     * @param task                                   The TaskModel object representing the task.
     * @param hbox                                   The HBox representing the task card.
     * @param columnBoxId                            The ID of the parent column box.
     * @param projectViewingAndModificationPresenter
     * @return The created MenuButton with task options.
     */
    MenuButton createTaskOptionsMenu(TaskModel task, HBox hbox, String columnBoxId, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        MenuButton taskOptionsButton = new MenuButton("");
        MenuItem changeTaskDetailsButton = new MenuItem("Change Task Details");
        MenuItem deleteTaskButton = new MenuItem("Delete Task");
        MenuItem showTaskDetailsButton = new MenuItem("Show Task Details");

        changeTaskDetailsButton.setOnAction(event -> {
            new PopupUI().handleChangeTaskPopup(task, hbox, UUID.fromString(columnBoxId), projectViewingAndModificationPresenter);
        });
        deleteTaskButton.setOnAction(event -> {
            ProjectViewingAndModificationPresenter.controller.deleteTask(task, UUID.fromString(hbox.getId()), UUID.fromString(columnBoxId));
        });
        showTaskDetailsButton.setOnAction(event -> {
            ProjectViewingAndModificationPresenter.controller.showTaskDetails(task);
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
     * @param hbox                                   The HBox to configure.
     * @param projectViewingAndModificationPresenter
     */
    void configureHBoxFeatures(HBox hbox, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        new DragAndDropImplementation().configureDragAndDropBehavior(hbox, projectViewingAndModificationPresenter);
        new PresenterUtility().configureHBoxStyleOnMouseActions(hbox, projectViewingAndModificationPresenter);
    }

    /**
     * Configures the visual style of an HBox based on mouse actions.
     *
     * @param hbox                                   The HBox to configure the style for.
     * @param projectViewingAndModificationPresenter
     */
    void configureHBoxStyleOnMouseActions(HBox hbox, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        hbox.setOnMouseEntered(e -> {
            new PresenterUtility().applyHBoxHoverStyle(hbox);
        });

        hbox.setOnMouseExited(e -> {
            new PresenterUtility().resetHBoxStyle(hbox);
        });
    }

    /**
     * Applies a hover style to the given HBox on mouse enter.
     *
     * @param hbox The HBox to apply the hover style to.
     */
    void applyHBoxHoverStyle(HBox hbox) {
        hbox.setStyle("-fx-border-color: rgba(69,89,164,.5); -fx-border-width: 3px; -fx-border-radius: 10.0d;");
        hbox.setBackground(new Background(new BackgroundFill(Color.rgb(64, 65, 79, 1), new CornerRadii(10.0d), Insets.EMPTY)));
    }

    /**
     * Resets the style of the given HBox on mouse exit.
     *
     * @param hbox The HBox to reset the style for.
     */
    void resetHBoxStyle(HBox hbox) {
        hbox.setStyle("-fx-border-radius: 10.0d; -fx-border-color: black; -fx-border-width: 2px;");
        hbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10.0d), Insets.EMPTY)));
    }

    /**
     * Creates the "Submit" button for adding the task.
     *
     * @param popupStage                             The popup stage to be closed.
     * @param columnBox                              The VBox representing the Column UI.
     * @param projectViewingAndModificationPresenter
     * @return The created "Submit" button.
     */
    Button createAddTaskButton(Stage popupStage, VBox columnBox, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        Button addTaskToColumnButton = new Button("Submit");
        addTaskToColumnButton.setOnAction(event -> projectViewingAndModificationPresenter.controller.handleAddTaskButtonClicked(popupStage, columnBox, projectViewingAndModificationPresenter));
        return addTaskToColumnButton;
    }

    /**
     * Creates the pop-up stage.
     *
     * @param stageTitle
     * @return The created Stage object for the pop-up.
     */
    Stage createPopupStage(String stageTitle) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle(stageTitle);
        return popupStage;
    }

    /**
     * Creates a GridPane for organizing components.
     *
     * @return The created GridPane.
     */
    GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        return gridPane;
    }

    /**
     * Adds components to the GridPane.
     *
     * @param gridPane                               The GridPane to which components are added.
     * @param projectViewingAndModificationPresenter
     */
    void addComponentsToGridPane(GridPane gridPane, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        Label nameLabel = new Label("Task Name:");
        projectViewingAndModificationPresenter.nameTextField = new TextField();

        Label detailsLabel = new Label("Task Details:");
        projectViewingAndModificationPresenter.detailsTextArea = new TextArea();
        projectViewingAndModificationPresenter.detailsTextArea.setPrefRowCount(3);

        Label dueDateLabel = new Label("Task Due Date:");
        projectViewingAndModificationPresenter.dueDatePicker = new DatePicker();

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(projectViewingAndModificationPresenter.nameTextField, 1, 0);
        gridPane.add(detailsLabel, 0, 1);
        gridPane.add(projectViewingAndModificationPresenter.detailsTextArea, 1, 1);
        gridPane.add(dueDateLabel, 0, 2);
        gridPane.add(projectViewingAndModificationPresenter.dueDatePicker, 1, 2);
    }

    /**
     * Creates the "Submit" button with an action handler.
     *
     * @param task                                   The task to be edited.
     * @param hbox                                   The HBox containing the task.
     * @param uuid                                   The ID of the column containing the task.
     * @param popupStage                             The pop-up stage.
     * @param projectViewingAndModificationPresenter
     * @return The created "Submit" button.
     */
    Button createChangeTaskButton(TaskModel task, HBox hbox, UUID uuid, Stage popupStage, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        Button changeTaskButton = new Button("Submit");
        changeTaskButton.setOnAction(event -> projectViewingAndModificationPresenter.controller.handleTaskSubmit(task, hbox, popupStage, uuid, projectViewingAndModificationPresenter));
        return changeTaskButton;
    }

    /**
     * Creates the "Add" button with an action handler.
     *
     * @param addButtonClicked                       The array to store the result of the pop-up.
     * @param popupStage                             The pop-up stage.
     * @param nameTextField                          The text field for column name input.
     * @param projectViewingAndModificationPresenter
     * @return The created "Add" button.
     */
    Button createAddButton(boolean[] addButtonClicked, Stage popupStage, TextField nameTextField, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            projectViewingAndModificationPresenter.controller.handleAddButtonClicked(addButtonClicked, popupStage, nameTextField, projectViewingAndModificationPresenter);
        });
        return addButton;
    }

    /**
     * Creates the "Cancel" button with an action handler.
     *
     * @param popupStage                             The pop-up stage to be closed.
     * @param nameTextField                          The text field for column name input.
     * @param projectViewingAndModificationPresenter
     * @return The created "Cancel" button.
     */
    Button createCancelButton(Stage popupStage, TextField nameTextField, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            projectViewingAndModificationPresenter.controller.handleCancelButtonClicked(popupStage, nameTextField);
        });
        return cancelButton;
    }

    /**
     * Gets the entered column name from the layout.
     *
     * @param layout The layout containing the text field.
     * @return The entered column name.
     */
    String getColumnInput(VBox layout) {
        TextField nameTextField = (TextField) layout.getChildren().get(1);
        return nameTextField.getText().trim();
    }

    /**
     * Creates the dialog stage.
     *
     * @return The created Stage object.
     */
    Stage createDialogStage() {
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Enter Column Name");
        return dialogStage;
    }

    /**
     * Creates the content of the dialog.
     *
     * @param dialogStage                            The dialog stage.
     * @param projectViewingAndModificationPresenter
     * @return A VBox containing the dialog content.
     */
    VBox createDialogContent(Stage dialogStage, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        Label label = new Label("Enter the name of the column:");
        TextField textField = new TextField();
        Button okButton = new PresenterUtility().createOkButton(textField, dialogStage, projectViewingAndModificationPresenter);
        Button cancelButton = new PresenterUtility().createCancelButton(dialogStage);

        VBox vbox = new VBox(label, textField, new Separator(), new HBox(okButton, cancelButton));
        vbox.setSpacing(10);
        return vbox;
    }

    /**
     * Creates the OK button with an action handler.
     *
     * @param textField                              The text field containing the input text.
     * @param dialogStage                            The dialog stage.
     * @param projectViewingAndModificationPresenter
     * @return The created OK button.
     */
    Button createOkButton(TextField textField, Stage dialogStage, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> ProjectViewingAndModificationPresenter.controller.handleOkButtonClicked(textField, dialogStage, projectViewingAndModificationPresenter));
        return okButton;
    }

    /**
     * Creates the Cancel button with an action handler.
     *
     * @param dialogStage The dialog stage.
     * @return The created Cancel button.
     */
    Button createCancelButton(Stage dialogStage) {
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> ProjectViewingAndModificationPresenter.controller.handleCancelButtonClicked(dialogStage));
        return cancelButton;
    }
}
