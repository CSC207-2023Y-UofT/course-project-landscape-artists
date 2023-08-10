package c_interface_adapters;

import b_application_business_rules.entity_models.ColumnModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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
}
