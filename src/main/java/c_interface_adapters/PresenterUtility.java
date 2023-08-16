package c_interface_adapters;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.ProjectRepository;
import c_interface_adapters.view_models.ProjectViewModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import static javafx.scene.control.PopupControl.USE_PREF_SIZE;

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
     * Creates the layout for displaying task details in the pop-up.
     *
     * @param taskModel        The TaskModel object containing the details of the task.
     * @param presenterUtility
     * @return A VBox containing the layout with task details.
     */
    static VBox createDetailsLayout(TaskModel taskModel, PresenterUtility presenterUtility) {
        VBox vbox = new VBox(10); // 10 pixels spacing between labels
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

        addDetailLabel(vbox, "Name:", taskModel.getName(), new PresenterUtility());
        addDetailLabel(vbox, "ID:", taskModel.getID().toString(), new PresenterUtility());
        addDetailLabel(vbox, "Description:", taskModel.getDescription(), new PresenterUtility());
        addDetailLabel(vbox, "Is Completed:", taskModel.getCompletionStatus() ? "✅ Task is done" : "❌ Task is not completed", new PresenterUtility());
        addDetailLabel(vbox, "Due Date:", taskModel.getDueDateTime().format(dateFormatter), new PresenterUtility());

        return vbox;
    }

    /**
     * Adds a detail label and its corresponding value to the layout.
     *
     * @param vbox             The VBox layout to which the labels are added.
     * @param labelText        The label text.
     * @param valueText        The value text.
     * @param presenterUtility
     */
    static void addDetailLabel(VBox vbox, String labelText, String valueText, PresenterUtility presenterUtility) {
        Label label = createLabel(labelText, new PresenterUtility());
        Label valueLabel = createValueLabel(valueText, new PresenterUtility());
        vbox.getChildren().addAll(label, valueLabel);
    }

    /**
     * Creates a label with specific styling for task detail labels.
     *
     * @param labelText        The text to be displayed on the label.
     * @param presenterUtility
     * @return A Label with the specified text and styling.
     */
    static Label createLabel(String labelText, PresenterUtility presenterUtility) {
        Label label = new Label(labelText);
        label.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333; -fx-font-weight: bold;");
        return label;
    }

    /**
     * Creates a value label with the provided value text.
     *
     * @param valueText        The text to be displayed on the value label.
     * @param presenterUtility
     * @return A Label with the specified value text.
     */
    static Label createValueLabel(String valueText, PresenterUtility presenterUtility) {
        Label valueLabel = new Label(valueText);
        return valueLabel;
    }


    /**
     * Sets the styling for columnUI.
     *
     * @param columnBox the VBox to set the styling of.
     */
    public static void setColumnUIStyling(VBox columnBox) {
        columnBox.setStyle("-fx-background-color: #F9F9FA;" +
                "-fx-background-radius: 20");
    }

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
        scrollPane.setPrefWidth(235);
        scrollPane.setMinWidth(235);
        scrollPane.setMaxWidth(235);
        scrollPane.setPrefHeight(370);
        scrollPane.setMaxHeight(370);
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
            ProjectViewingAndModificationPresenter.controller.handleEditColumnDetails(column.getID(), column);
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
     * Adds the scroll pane to the column's container.
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
            ProjectViewingAndModificationPresenter.controller.deleteTask(UUID.fromString(columnBoxId), task);
        });
        showTaskDetailsButton.setOnAction(event -> {
            ProjectViewingAndModificationPresenter.controller.showTaskDetails(task.getID());
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
    void configureHBoxFeatures(HBox hbox, ProjectViewingAndModificationPresenter
            projectViewingAndModificationPresenter, TaskModel task) {
        new DragAndDropImplementation().configureDragAndDropBehavior(hbox,
                projectViewingAndModificationPresenter, task);
        new PresenterUtility().configureHBoxStyleOnMouseActions(hbox, projectViewingAndModificationPresenter, task);
    }

    /**
     * Configures the visual style of an HBox based on mouse actions.
     *
     * @param hbox                                   The HBox to configure the style for.
     * @param projectViewingAndModificationPresenter
     */
    void configureHBoxStyleOnMouseActions(HBox hbox, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter, TaskModel taskModel) {
        hbox.setOnMouseEntered(e -> {
            new PresenterUtility().applyHBoxHoverStyle(hbox);
        });

        hbox.setOnMouseExited(e -> {
            new PresenterUtility().resetHBoxStyle(hbox, taskModel);
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
    void resetHBoxStyle(HBox hbox, TaskModel taskModel) {
//        ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter = new ProjectViewingAndModificationPresenter();
//        if(!taskModel.getCompletionStatus()){
//            projectViewingAndModificationPresenter.displayTaskCompleted(taskModel.getID(), UUID.fromString(hbox.getParent().getId()));
//        } else{
//            hbox.setStyle("-fx-border-radius: 10.0d; -fx-border-color: black; -fx-border-width: 2px;");
//            hbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10.0d), Insets.EMPTY)));
//        }
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

    /**
     * Validates whether the provided project name and description are not empty.
     *
     * @param newProjectName        The new project name to be validated.
     * @param newProjectDescription The new project description to be validated.
     * @return True if both the project name and description are not empty, false otherwise.
     */
    boolean isInputValid(String newProjectName, String newProjectDescription) {
        return !newProjectName.isEmpty() && !newProjectDescription.isEmpty();
    }

    /**
     * Displays a dialog to rename a project and captures the user's input.
     *
     * @param projectSelectionPresenter
     * @return An optional string array containing the new project name and description if the user provided valid input,
     * or an empty optional if the user canceled the dialog or provided invalid input.
     */
    public Optional<String[]> showRenameProject(ProjectSelectionPresenter projectSelectionPresenter) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Rename Project");
        dialog.setHeaderText("Enter the new name and description for the project:");

        TextField nameField = new TextField();
        nameField.setPromptText("New Project Name");
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("New Project Description");

        dialog.getDialogPane().setContent(new VBox(nameField, descriptionField));

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            String newProjectName = nameField.getText().trim();
            String newProjectDescription = descriptionField.getText().trim();

            if (new PresenterUtility().isInputValid(newProjectName, newProjectDescription)) {
                return Optional.of(new String[]{newProjectName, newProjectDescription});
            } else {
                projectSelectionPresenter.showErrorAlert("Invalid Project Name or Description",
                        "Project name and description cannot be empty.\nPlease enter the new name and description.");
            }
        }

        return Optional.empty();
    }

    /**
     * Shows the dialog and waits for user input. Converts the result of the dialog button press
     * to a Pair of project name and description.
     *
     * @param dialog                    The dialog instance to show.
     * @param projectSelectionPresenter
     * @return An optional Pair containing the project name and description if the user confirms,
     * otherwise an empty optional.
     */
    Optional<Pair<String, String>> showDialogAndWait(Dialog<Pair<String, String>> dialog, ProjectSelectionPresenter projectSelectionPresenter) {
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String projectName = projectSelectionPresenter.getNameTextField().getText();
                String projectDescription = projectSelectionPresenter.getDescTextField().getText();
                return new Pair<>(projectName, projectDescription);
            }
            return null; // Return null for other button types or cancellation
        });

        return dialog.showAndWait();
    }

    /**
     * Sets the content of the dialog to include input fields for project name and description.
     *
     * @param dialog                    The dialog instance to set the content for.
     * @param projectSelectionPresenter
     */
    void setDialogContent(Dialog<Pair<String, String>> dialog, ProjectSelectionPresenter projectSelectionPresenter) {
        Label nameLabel = new Label("Project Name:");
        Label descLabel = new Label("Description:");
        projectSelectionPresenter.nameTextField = new TextField();
        projectSelectionPresenter.descTextField = new TextField();

        GridPane gridPane = new GridPane();
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(projectSelectionPresenter.nameTextField, 1, 0);
        gridPane.add(descLabel, 0, 1);
        gridPane.add(projectSelectionPresenter.descTextField, 1, 1);

        dialog.getDialogPane().setContent(gridPane);
    }

    /**
     * Creates a new dialog instance for collecting project information.
     *
     * @return The created dialog instance.
     */
    Dialog<Pair<String, String>> createDialog() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Create Project");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        return dialog;
    }

    /**
     * Fills the empty spaces in the GridPane with placeholder HBoxes.
     *
     * @param projectsGrid The GridPane containing the HBoxes representing projects.
     * @param row          The row index where placeholders should be added.
     * @param numColumns   The number of columns in the GridPane.
     */
    void fillEmptySpacesWithPlaceholders(GridPane projectsGrid, int row, int numColumns) {
        while (row < projectsGrid.getRowCount()) {
            HBox placeholderHBox = new HBox();
            GridPane.setColumnIndex(placeholderHBox, 0);
            GridPane.setRowIndex(placeholderHBox, row);
            projectsGrid.getChildren().add(placeholderHBox);

            row++;
        }
    }

    /**
     * Rearranges the layout of the GridPane after removing an HBox.
     *
     * @param projectsGrid The GridPane containing the HBoxes representing projects.
     * @param numColumns   The number of columns in the GridPane.
     */
    void rearrangeGridPaneLayout(GridPane projectsGrid, int numColumns) {
        int col = 0;
        int row = 0;

        for (Node child : projectsGrid.getChildren()) {
            GridPane.setColumnIndex(child, col);
            GridPane.setRowIndex(child, row);

            col++;
            if (col >= numColumns) {
                col = 0;
                row++;
            }
        }

        new PresenterUtility().fillEmptySpacesWithPlaceholders(projectsGrid, row, numColumns);
    }

    /**
     * Removes the specified HBox from the projectsGrid and updates the layout of the GridPane.
     *
     * @param projectsGrid The GridPane containing the HBoxes representing projects.
     * @param targetHBox   The HBox to be removed.
     */
    void removeHBoxAndUpdateLayout(GridPane projectsGrid, HBox targetHBox) {
        int numColumns = 2; // Specify the number of columns

        projectsGrid.getChildren().remove(targetHBox);

        new PresenterUtility().rearrangeGridPaneLayout(projectsGrid, numColumns);
    }

    /**
     * Creates a "Create Project" button with appropriate styling and action handling.
     *
     * @param projectSelectionPresenter
     * @return The created "Create Project" button.
     */
    Button createCreateProjectButton(ProjectSelectionPresenter projectSelectionPresenter) {
        Button createProjectButton = UIComponentLocator.findCreateProjectButton();
//        Button createProjectButton = new Button("+");
        createProjectButton.setOnAction(projectSelectionPresenter::handleCreateProjectPopup);
        createProjectButton.getStyleClass().add("create-project-button-style");
        return createProjectButton;
    }

    /**
     * Adds a "Create Project" button to the projects grid.
     *
     * @param projectSelectionPresenter
     */
    void addCreateProjectButton(ProjectSelectionPresenter projectSelectionPresenter) {
//        GridPane projectsGrid = ProjectSelectionPresenter.uiComponentLocator.findGridPane();
        Button createProjectButton = new PresenterUtility().createCreateProjectButton(projectSelectionPresenter);
//        projectsGrid.add(createProjectButton, projectSelectionPresenter.getCurrentColumn(), projectSelectionPresenter.getCurrentRow());
    }

    /**
     * Creates a container for the current project button and menu button.
     *
     * @param currentProjectButton The current project button.
     * @param menuButton           The menu button.
     * @param project              The project associated with the buttons.
     * @return The created HBox container.
     */
    HBox createButtonContainer(Button currentProjectButton, MenuButton menuButton, ProjectViewModel project) {
        HBox buttonContainer = new HBox(currentProjectButton, menuButton);
        buttonContainer.setId(project.getID().toString());
        return buttonContainer;
    }

    /**
     * Creates a menu button for the given project with rename and delete options.
     *
     * @param project                   The project for which to create the menu button.
     * @param projectSelectionPresenter
     * @return The created menu button.
     */
    MenuButton createMenuButton(ProjectViewModel project, ProjectSelectionPresenter projectSelectionPresenter) {
        MenuButton menuButton = new MenuButton();
        MenuItem renameProjectMenuItem = new MenuItem("Rename Project");
        MenuItem deleteProjectMenuItem = new MenuItem("Delete Project");
        menuButton.getStyleClass().add("menu-button-custom");
        renameProjectMenuItem.setOnAction(event -> projectSelectionPresenter.controller.handleRenameProject(project.getID()));
        deleteProjectMenuItem.setOnAction(event -> projectSelectionPresenter.controller.handleDeleteProject(project.getID()));
        menuButton.getItems().addAll(renameProjectMenuItem, deleteProjectMenuItem);
        return menuButton;
    }

    /**
     * Configures the appearance of the current project button.
     *
     * @param currentProjectButton        The current project button to configure.
     * @param nameAndDescriptionContainer The VBox container with project name and description labels.
     * @param project                     The project associated with the button.
     */
    void configureCurrentProjectButton(Button currentProjectButton, VBox nameAndDescriptionContainer, ProjectViewModel project) {
        currentProjectButton.setGraphic(nameAndDescriptionContainer);
        currentProjectButton.getStyleClass().add("current-project-button");
        currentProjectButton.setWrapText(true);
        currentProjectButton.setMinWidth(USE_PREF_SIZE);
        currentProjectButton.setMaxWidth(Double.MAX_VALUE);
    }

    /**
     * Creates a VBox container for the project's name and description labels.
     *
     * @param project The project for which to create the container.
     * @return The created VBox container.
     */
    VBox createNameAndDescriptionContainer(ProjectViewModel project) {
        Label projectName = new Label(project.getName());
        Label projectDescription = new Label(project.getDescription());
        projectName.setId("projectName");
        projectDescription.setId("projectDescription");
        projectName.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        projectName.setMaxWidth(150);
        projectDescription.setMaxWidth(150);
        projectName.setWrapText(true);
        projectDescription.setWrapText(true);
        projectName.setAlignment(Pos.CENTER);
        projectDescription.setAlignment(Pos.CENTER);
        return new VBox(projectName, projectDescription);
    }

    /**
     * Creates a button for the given project.
     *
     * @param project                   The project for which to create the button.
     * @param projectSelectionPresenter
     * @return The created button.
     */
    Button createCurrentProjectButton(ProjectViewModel project, ProjectSelectionPresenter projectSelectionPresenter) {
        Button currentProjectButton = new Button();
        VBox nameAndDescriptionContainer = new PresenterUtility().createNameAndDescriptionContainer(project);
        new PresenterUtility().configureCurrentProjectButton(currentProjectButton, nameAndDescriptionContainer, project);
        currentProjectButton.setUserData(project.getID());
        currentProjectButton.setOnAction(event -> projectSelectionPresenter.controller.handleChosenProjectButton(event));

        return currentProjectButton;
    }

    /**
     * Sets column and row constraints for the provided GridPane.
     *
     * @param projectsGrid The GridPane to set constraints for.
     */
    void setColumnAndRowConstraints(GridPane projectsGrid) {
        for (int col = 0; col < 2; col++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setHgrow(Priority.ALWAYS);
            columnConstraints.setFillWidth(false);
        }
    }

    /**
     * Configures the projects grid by setting horizontal and vertical gaps and applying column and row constraints.
     */
    void configureProjectsGrid() {
        GridPane projectsGrid = ProjectSelectionPresenter.uiComponentLocator.findGridPane();
        projectsGrid.setVgap(40);
        new PresenterUtility().setColumnAndRowConstraints(projectsGrid);
    }
}
