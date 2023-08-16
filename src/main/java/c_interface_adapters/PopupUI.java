package c_interface_adapters;

import b_application_business_rules.entity_models.TaskModel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.UUID;

/**
 * Responsible for UI related functionality of popup windows.
 */
public class PopupUI {
    /**
     * Displays a popup window to add a new task to the selected column. *
     *
     * @param columnBox                              The VBox representing the Column UI where the task will be added.
     * @param projectViewingAndModificationPresenter
     */
    public void handleAddTaskPopup(VBox columnBox, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        Stage popupStage = new PresenterUtility().createPopupStage("Add Task");
        GridPane gridPane = new PresenterUtility().createGridPane();
        new PresenterUtility().addComponentsToGridPane(gridPane, projectViewingAndModificationPresenter);
        Button addTaskToColumnButton = new PresenterUtility().createAddTaskButton(popupStage, columnBox, projectViewingAndModificationPresenter);

        gridPane.add(addTaskToColumnButton, 0, 3, 2, 1);

        Scene popupScene = new Scene(gridPane, 800, 200);
        popupStage.setScene(popupScene);

        popupStage.showAndWait();
    }

    /**
     * Displays a pop-up window to change task details. *
     *
     * @param task                                   The task to be edited.
     * @param hbox                                   The HBox containing the task.
     * @param columnID                               The ID of the column containing the task.
     * @param projectViewingAndModificationPresenter
     */
    void handleChangeTaskPopup(TaskModel task, HBox hbox, UUID columnID, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        Stage popupStage = new PresenterUtility().createPopupStage("Change Task Details");
        GridPane gridPane = new PresenterUtility().createGridPane();
        new PresenterUtility().addComponentsToGridPane(gridPane, projectViewingAndModificationPresenter);
        Button changeTaskButton = new PresenterUtility().createChangeTaskButton(task, hbox, columnID, popupStage, projectViewingAndModificationPresenter);

        gridPane.add(changeTaskButton, 0, 3, 2, 1);

        Scene popupScene = new Scene(gridPane, 800, 200);
        popupStage.setScene(popupScene);

        popupStage.showAndWait();
    }

    /**
     * Displays a pop-up window to add a new column. *
     *
     * @param addButtonClicked                       A boolean array to store the result of the pop-up.
     * @param projectViewingAndModificationPresenter
     * @return A Pair containing the flag indicating the button clicked and the entered column name (trimmed).
     */
    public Pair<Boolean, String> displayAddColumnPopup(boolean[] addButtonClicked, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        Stage popupStage = new PresenterUtility().createPopupStage("Add New Column");
        VBox layout = new PopupUI().createPopupLayout(addButtonClicked, popupStage, projectViewingAndModificationPresenter);
        Scene popupScene = new Scene(layout);

        popupStage.setScene(popupScene);
        popupStage.showAndWait();

        return new Pair<>(addButtonClicked[0], new PresenterUtility().getColumnInput(layout));
    }

    /**
     * Creates the layout for the pop-up.
     *
     * @param addButtonClicked                       The array to store the result of the pop-up.
     * @param popupStage                             The pop-up stage.
     * @param projectViewingAndModificationPresenter
     * @return A VBox containing the layout components.
     */
    VBox createPopupLayout(boolean[] addButtonClicked, Stage popupStage, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        Label nameLabel = new Label("Column Name:");
        TextField nameTextField = new TextField();
        Button addButton = new PresenterUtility().createAddButton(addButtonClicked, popupStage, nameTextField, projectViewingAndModificationPresenter);
        Button cancelButton = new PresenterUtility().createCancelButton(popupStage, nameTextField, projectViewingAndModificationPresenter);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(nameLabel, nameTextField, addButton, cancelButton);
        return layout;
    }
}
