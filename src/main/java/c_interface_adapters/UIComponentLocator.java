package c_interface_adapters;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Iterator;

public class UIComponentLocator {
    private Scene scene;

    public UIComponentLocator(Scene scene) {
        this.scene = scene;
    }

    public VBox getColumnUI(String columnUUID) {
        VBox columnUI = null;

        if (scene != null) {
            Node scrollPaneContainer = findNodeById("scrollPaneContainer");
            if (scrollPaneContainer instanceof ScrollPane) {
                HBox columnsContainer = (HBox) ((ScrollPane) scrollPaneContainer).getContent();

                for (Node containerChild : columnsContainer.getChildren()) {
                    if (containerChild.getId().equals(columnUUID)) {
                        columnUI = (VBox) ((ScrollPane) containerChild).getContent();
                        break;
                    }
                }
            }
        }

        return columnUI;
    }

    public Label getColumnNameUI(VBox columnUI) {
        Label columnNameUI = null;

        for (Node item : columnUI.getChildren()) {
            System.out.println("IN getColumnNameUI" + item);
            if (item.getId().equals("columnHeader")) {
                columnNameUI = (Label) ((HBox) item).getChildren().get(0);
                break;
            }
        }

        return columnNameUI;
    }

    public void removeColumnUI(String columnUUID) {
        if (scene != null) {
            Node scrollPaneContainer = findNodeById("scrollPaneContainer");
            if (scrollPaneContainer instanceof ScrollPane) {
                HBox columnsContainer = (HBox) ((ScrollPane) scrollPaneContainer).getContent();

                Iterator<Node> iterator = columnsContainer.getChildren().iterator();
                while (iterator.hasNext()) {
                    Node containerChild = iterator.next();
                    if (containerChild.getId().equals(columnUUID)) {
                        columnsContainer.getChildren().remove(containerChild);
                        break;
                    }
                }
            }
        }
    }

    private Node findNodeById(String id) {
        for (Node node : scene.getRoot().getChildrenUnmodifiable()) {
            if (node.getId().equals(id)) {
                return node;
            }
        }
        return null;
    }

    public HBox findColumnsContainer() {
        if (scene != null) {
            Node scrollPaneContainer = findNodeById("scrollPaneContainer");
            if (scrollPaneContainer instanceof ScrollPane) {
                HBox columnsContainer = (HBox) ((ScrollPane) scrollPaneContainer).getContent();
                return columnsContainer;
            }
        }

        return null;
    }
}
