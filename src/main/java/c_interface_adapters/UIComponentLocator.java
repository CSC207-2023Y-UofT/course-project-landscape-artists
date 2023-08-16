package c_interface_adapters;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Iterator;
import java.util.UUID;

/**
 * The UIComponentLocator class is responsible for locating various UI components within a JavaFX scene.
 * It provides methods to find specific UI elements based on their IDs and types.
 */
public class UIComponentLocator {
    // The scene used by the app
    private Scene scene;
    // IDs for commonly used UI elements
    private static final String columnHeaderID = "columnHeader";
    private static final String scrollPaneContainerID = "scrollPaneContainer";
    private static final String taskNameID = "taskName";
    private static final String projectDescriptionID = "projectDescription";
    private static final String projectNameID = "projectName";
    private static final String projectsGridID = "projectsGrid";

    /**
     * Constructs a UIComponentLocator with the given JavaFX scene.
     *
     * @param scene The JavaFX scene to search for UI components in.
     */
    public UIComponentLocator(Scene scene) {
        this.scene = scene;
    }

    /**
     * Finds the VBox representing a column UI based on the column UUID.
     *
     * @param columnUUID The UUID of the column to locate.
     * @return The VBox representing the column UI, or null if not found.
     */

    /**
     * Finds the VBox representing a column UI based on the column UUID.
     *
     * @param columnUUID The UUID of the column to locate.
     * @return The VBox representing the column UI, or null if not found.
     */
    public VBox getColumnUI(String columnUUID) {
        if (scene == null) {
            return null;
        }

        ScrollPane scrollPaneContainer = findScrollPaneContainer();
        if (scrollPaneContainer == null) {
            return null;
        }

        HBox columnsContainer = (HBox) scrollPaneContainer.getContent();
        return findColumnUI(columnsContainer, columnUUID);
    }


    /**
     * Finds the VBox containing the UI representation of a column within the given HBox of columns.
     *
     * @param columnsContainer The HBox containing multiple column UIs.
     * @param columnUUID The UUID of the column to be found.
     * @return The VBox representing the UI of the column with the specified UUID, or null if not found.
     */
    private VBox findColumnUI(HBox columnsContainer, String columnUUID) {
        for (Node containerChild : columnsContainer.getChildren()) {
            if (containerChild.getId() != null && containerChild.getId().equals(columnUUID)) {
                return (VBox) ((ScrollPane) containerChild).getContent();
            }
        }
        return null;
    }

    /**
     * Finds the Label representing the column name UI within a column VBox.
     *
     * @param columnUI The VBox representing the column UI.
     * @return The Label representing the column name UI, or null if not found.
     */
    public Label getColumnNameUI(VBox columnUI) {
        Label columnNameUI = null;

        for (Node item : columnUI.getChildren()) {
            if (item.getId().equals(columnHeaderID)) {
                columnNameUI = (Label) ((HBox) item).getChildren().get(0);
                break;
            }
        }

        return columnNameUI;
    }

    /**
     * Removes a column UI based on the column UUID.
     *
     * @param columnUUID The UUID of the column to remove.
     */
    public void removeColumnUI(String columnUUID) {
        if (scene == null) {
            return;
        }

        ScrollPane scrollPaneContainer = findScrollPaneContainer();
        if (scrollPaneContainer == null) {
            return;
        }

        HBox columnsContainer = (HBox) scrollPaneContainer.getContent();
        removeChildColumn(columnsContainer, columnUUID);
    }

    /**
     * Finds the ScrollPane container within the scene.
     *
     * @return The ScrollPane container if found, otherwise returns null.
     */
    private ScrollPane findScrollPaneContainer() {
        Node scrollPaneContainer = findNodeById(scrollPaneContainerID);
        return (scrollPaneContainer instanceof ScrollPane) ? (ScrollPane) scrollPaneContainer : null;
    }

    /**
     * Removes a child column from the specified columns container based on its UUID.
     *
     * @param columnsContainer The HBox containing multiple column UIs.
     * @param columnUUID The UUID of the column to be removed.
     */
    private void removeChildColumn(HBox columnsContainer, String columnUUID) {
        Iterator<Node> iterator = columnsContainer.getChildren().iterator();
        while (iterator.hasNext()) {
            Node containerChild = iterator.next();
            if (containerChild.getId().equals(columnUUID)) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Finds a JavaFX Node in the scene by its ID.
     *
     * @param id The ID of the node to be found.
     * @return The Node with the specified ID if found, otherwise returns null.
     */
    private Node findNodeById(String id) {
        if (scene != null) {
            for (Node node : scene.getRoot().getChildrenUnmodifiable()) {
                if (node.getId().equals(id)) {
                    return node;
                }
            }
        }
        return null;
    }

    /**
     * Finds the container for columns within the scene and returns it.
     *
     * @return The HBox containing column UIs, or null if not found.
     */
    public HBox findColumnsContainer() {
        if (scene != null) {
            Node scrollPaneContainer = findNodeById(scrollPaneContainerID);
            // clears scrollpane style
            scrollPaneContainer.getStyleClass().clear();

            if (scrollPaneContainer instanceof ScrollPane) {
                HBox columnsContainer = (HBox) ((ScrollPane) scrollPaneContainer).getContent();

                // set spacing between each item
                columnsContainer.setSpacing(10);
                return columnsContainer;
            }
        }

        return null;
    }

    public HBox findTaskUI(UUID taskID, VBox columnUI) {
        for (Node nodeInColumnUI : columnUI.getChildren()) {
            HBox taskUI = findTaskUIInNode(nodeInColumnUI, taskID.toString());
            if (taskUI != null) {
                return taskUI;
            }
        }
        return null;
    }

    private HBox findTaskUIInNode(Node node, String taskID) {
        if (node.getId() != null && node.getId().equals(taskID)) {
            if (node instanceof HBox) {
                return (HBox) node;
            }
        }
        return null;
    }

    /**
     * Finds the Text element representing a task name within the specified column.
     *
     * @param taskID The UUID of the task to be found.
     * @param columnID The UUID of the column containing the task.
     * @return The Text element representing the task name, or null if not found.
     */
    public Text findTaskName(UUID taskID, UUID columnID) {
        VBox columnUI = getColumnUI(String.valueOf(columnID));
        for (Node nodeInColumnUI : columnUI.getChildren()) {
            Text taskName = findTaskNameInNode(nodeInColumnUI, taskID.toString());
            if (taskName != null) {
                return taskName;
            }
        }
        return null;
    }

    /**
     * Finds the Text element representing a task name within the given Node.
     *
     * @param node The Node to search within.
     * @param taskID The UUID of the task to be found.
     * @return The Text element representing the task name, or null if not found.
     */
    private Text findTaskNameInNode(Node node, String taskID) {
        if (node.getId() != null && node.getId().equals(taskID)) {
            if (node instanceof HBox) {
                return findTaskNameInHBox((HBox) node);
            }
        }
        return null;
    }

    /**
     * Finds the Text element representing a task name within the given HBox.
     *
     * @param hbox The HBox to search within.
     * @return The Text element representing the task name, or null if not found.
     */

    private Text findTaskNameInHBox(HBox hbox) {
        for (Node nodeInHBox : hbox.getChildren()) {
            if (nodeInHBox instanceof StackPane) {
                Text taskName = findTaskNameInStackPane((StackPane) nodeInHBox);
                if (taskName != null) {
                    return taskName;
                }
            }
        }
        return null;
    }

    /**
     * Finds the Text element representing a task name within the given StackPane.
     *
     * @param stackPane The StackPane to search within.
     * @return The Text element representing the task name, or null if not found.
     */
    private Text findTaskNameInStackPane(StackPane stackPane) {
        for (Node nodeInStackPane : stackPane.getChildren()) {
            if (nodeInStackPane.getId() != null && nodeInStackPane.getId().equals(taskNameID)) {
                if (nodeInStackPane instanceof Text) {
                    return (Text) nodeInStackPane;
                }
            }
        }
        return null;
    }

    /**
     * Finds the project name UI element in the scene.
     *
     * @return The label element representing the project name.
     */
    public Label findProjectDescriptionUI() {
        if (scene != null) {
            // Find the HBox that corresponds to the provided projectUUID
            for (Node node : scene.getRoot().getChildrenUnmodifiable()) {
                if (node.getId().equals(projectDescriptionID)) {
                    return (Label) node;
                }
            }
        }
        return null;
    }

    /**
     * Finds the project name UI element in the scene.
     *
     * @return The label element representing the project name.
     */
    public Label findProjectNameUI() {
        if (scene != null) {
            // Find the HBox that corresponds to the provided projectUUID
            for (Node node : scene.getRoot().getChildrenUnmodifiable()) {
                if (node.getId().equals(projectNameID)) {
                    return (Label) node;
                }
            }
        }
        return null;
    }

    /**
     * This method finds the GridPane (JavaFX) projectsGrid from the current scene. It is used to populate the Grid
     * Pane with
     * Project UI.
     *
     * @return The GridPane object which will hold the Project UI.
     */
    public GridPane findGridPane() {
        Scene currentScene = ProjectSelectionPresenter.stage.getScene();
        if (currentScene != null) {
            for (Node node : currentScene.getRoot().getChildrenUnmodifiable()) {
                if (node instanceof ScrollPane) {
                    node.getStyleClass().clear();
                    Node projectsGrid = ((ScrollPane) node).getContent();
                    if (projectsGrid.getId().equals(projectsGridID)) {
                        return ((GridPane) projectsGrid);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Finds the HBox in the provided GridPane that has the specified UUID as its ID.
     *
     * @param projectsGrid The GridPane containing the HBoxes representing projects.
     * @param projectUUID  The UUID of the project to be found.
     * @return The HBox representing the project with the specified UUID, or null if not found.
     */
    HBox findHBoxWithId(GridPane projectsGrid, String projectUUID) {
        for (Node node : projectsGrid.getChildren()) {
            if (node instanceof HBox && projectUUID.equals(node.getId())) {
                return (HBox) node;
            }
        }
        return null;
    }

    /**
     * Finds a Button element among the children of the provided HBox.
     *
     * @param hbox The HBox containing the child nodes to search.
     * @return The Button element found among the children, or null if not found.
     */
    Button findButtonInChildren(HBox hbox) {
        for (Node hboxChild : hbox.getChildren()) {
            if (hboxChild instanceof Button) {
                return (Button) hboxChild;
            }
        }
        return null;
    }

    /**
     * Finds a Button element in the project selection.
     *
     */
    static Button findCreateProjectButton() {
        Scene currentScene = ProjectSelectionPresenter.stage.getScene();
        if (currentScene != null) {
            for (Node node : currentScene.getRoot().getChildrenUnmodifiable()) {
                if (node instanceof Button) {
                    return (Button) node;

                }
            }
        }
        return null;
    }

}
