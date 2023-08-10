package c_interface_adapters;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * The DragAndDropImplementation class is responsible for encapsulating the logic and behavior associated
 * with implementing drag-and-drop functionality within the user interface (UI). It provides methods to configure
 * the drag-and-drop behavior of UI components and handles state changes that occur during the drag-and-drop
 * interactions.
 *
 * This class serves as a modular solution for incorporating drag-and-drop interactions into UI elements,
 * promoting reusability and maintainability across different parts of the application. By managing the
 * complexity of drag-and-drop interactions, the DragAndDropImplementation class allows presenter classes to
 * focus on higher-level application logic and user experience.
 */
public class DragAndDropImplementation {
    /**
     * Configures drag-and-drop handling for the given column box.
     *
     * @param columnBox                              The VBox representing the column.
     * @param projectViewingAndModificationPresenter
     */
    void configureDragAndDropHandling(VBox columnBox, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        columnBox.setOnDragOver(event -> {
            projectViewingAndModificationPresenter.dragDestination = columnBox;
            event.consume();
        });
    }

    /**
     * Configures drag-and-drop behavior for an HBox.
     *
     * @param hbox                                   The HBox to configure drag-and-drop behavior for.
     * @param projectViewingAndModificationPresenter
     */
    void configureDragAndDropBehavior(HBox hbox, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
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
            new DragAndDropImplementation().handleDragDone(hbox, event, projectViewingAndModificationPresenter);
            event.consume();
        });
    }

    /**
     * Handles the completion of a drag-and-drop operation.
     *
     * @param hbox                                   The source HBox being dragged.
     * @param event                                  The DragEvent associated with the operation.
     * @param projectViewingAndModificationPresenter
     */
    void handleDragDone(HBox hbox, DragEvent event, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        Dragboard dragboard = event.getDragboard();
        boolean success = false;
        if (dragboard.hasString()) {
            HBox sourceHBox = new DragAndDropImplementation().findHBoxById(dragboard.getString(), projectViewingAndModificationPresenter);
            if (sourceHBox != null && projectViewingAndModificationPresenter.dragDestination != null) {
                new DragAndDropImplementation().moveHBoxToDestination(sourceHBox, hbox, projectViewingAndModificationPresenter);
                success = true;
            }
        }
        event.setDropCompleted(success);
        // ... Perform additional tasks after drag-and-drop
    }

    /**
     * Moves an HBox to a specified destination HBox within a VBox.
     *
     * @param sourceHBox                             The source HBox being moved.
     * @param destinationHBox                        The destination HBox where the source HBox should be placed.
     * @param projectViewingAndModificationPresenter
     */
    void moveHBoxToDestination(HBox sourceHBox, HBox destinationHBox, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        VBox sourceColumnBox = (VBox) sourceHBox.getParent();
        sourceColumnBox.getChildren().remove(sourceHBox);

        TranslateTransition transition = new TranslateTransition(Duration.millis(100), sourceHBox);
        transition.setToX(projectViewingAndModificationPresenter.dragDestination.getLayoutX() - destinationHBox.getLayoutX());
        transition.play();

        transition.setOnFinished(event -> {
            projectViewingAndModificationPresenter.dragDestination.getChildren().add(sourceHBox);
        });
    }

    /**
     * Finds an HBox by its unique identifier within the set of VBox containers.
     *
     * @param id                                     The unique identifier of the HBox to find.
     * @param projectViewingAndModificationPresenter
     * @return The found HBox, or null if not found.
     */
    HBox findHBoxById(String id, ProjectViewingAndModificationPresenter projectViewingAndModificationPresenter) {
        System.out.println("this.VBoxContainer " + projectViewingAndModificationPresenter.VBoxContainer);
        for (VBox vBox : projectViewingAndModificationPresenter.VBoxContainer) {
            for (Node node2 : vBox.getChildren() ) {
                if (node2 instanceof HBox && node2.getId().equals(id)) {
                    return (HBox) node2;
                }
            }
        }
        return null;
    }
}