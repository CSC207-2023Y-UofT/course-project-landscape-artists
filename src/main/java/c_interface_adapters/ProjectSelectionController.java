package c_interface_adapters;

import b_application_business_rules.boundaries.ProjectSelectionInputBoundary;
import b_application_business_rules.boundaries.ProjectSelectionOutputBoundary;
import b_application_business_rules.boundaries.ProjectViewingAndModificationOutputBoundary;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import b_application_business_rules.use_cases.project_selection_use_cases.ProjectSelectionInteractor;
import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.use_cases.project_viewing_and_modification_use_cases.ProjectViewingAndModificationInteractor;
import c_interface_adapters.view_models.ColumnViewModel;
import c_interface_adapters.view_models.ProjectSelectionViewModel;
import c_interface_adapters.view_models.ProjectViewModel;
import c_interface_adapters.view_models.TaskViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import static javafx.scene.control.PopupControl.USE_PREF_SIZE;

/**
 * ProjectSelectionController class handles the event handling and controlling of creating and opening a project
 */
public class ProjectSelectionController {
    // FXML reference to the GridPane that holds the projects in the UI.
    @FXML
    private GridPane projectsGrid;

    // The interactor for project selection and creation. It implements ProjectSelectionInputBoundary.
    private ProjectSelectionInputBoundary interactor;

    // The ProjectSelectionViewModel to pass data to the view.
    ProjectSelectionViewModel projectSelectionViewModel;

    // The presenter associated with the controller in Project Selection UI.
    ProjectSelectionPresenter presenter;

    /**
     * Sets up the Controller's Presenter and Interactor.
     */
    private void setPresenterAndInteractor() {
        // This had to be separate since presenter needs to have a stage.
        ProjectSelectionOutputBoundary presenter =
                new ProjectSelectionPresenter(this);
        // Grabs the stage currently used.
        Stage stage = (Stage) projectsGrid.getScene().getWindow();

        // Sets the stage of the Presenter so methods in it changes the same stage.
//        ((ProjectSelectionPresenter) presenter).setStage(stage);

        // Set the Presenter class in the Controller.
        this.presenter = (ProjectSelectionPresenter) presenter;

        interactor = new ProjectSelectionInteractor(presenter);
    }

    /**
     * Handles the action of renaming a project. When the user selects the "Rename Project" option from
     * the menu associated with a project button, this method is called. It sets up the presenter and
     * calls the interactor to initiate the renaming process for the specified project.
     *
     * @param projectUUID The UUID of the project to be renamed.
     */
    void handleRenameProject(UUID projectUUID) {
        // invoking setPresenterAndInteractor ensures that the Controller's Presenter and Interactor is updated.
        // Otherwise, if this is the first action by the user, then interactor and presenter is null;
        setPresenterAndInteractor();
        String[] newNameAndNewDescription = presenter.displayRenameProjectPopup();
        if (newNameAndNewDescription != null) {
            String newName = newNameAndNewDescription[0];
            String newDescription = newNameAndNewDescription[1];
            interactor.renameProject(projectUUID, newName, newDescription);
        }
    }

    /**
     * Handles the action of deleting a project. When the user clicks "delete" project, the event handler
     * automatically passes the projectUUID associated to the deleted.
     *
     * @param projectUUID The UUID of the project to be deleted.
     */
    void handleDeleteProject(UUID projectUUID) {
        // invoking setPresenterAndInteractor ensures that the Controller's Presenter and Interactor is updated.
        // Otherwise, if this is the first action by the user, then interactor and presenter is null;
        setPresenterAndInteractor();
        interactor.deleteProject(projectUUID);
    }


    /**
     * Creates a new project using the provided Project object and delegates the task to the interactor
     * for processing the project creation.
     *
     * @param name The name of project.
     * @param description Description of project.
     */
    void createProject(String name, String description) {
        // invoking setPresenterAndInteractor ensures that the Controller's Presenter and Interactor is updated.
        // Otherwise, if this is the first action by the user, then interactor and presenter is null;
        setPresenterAndInteractor();
        interactor.createProject(name, description);
    }

    /**
     * Handles the action of selecting a project button from the UI.
     */
    public void handleChosenProjectButton(ActionEvent actionEvent) {
        setPresenterAndInteractor();
        Button buttonClicked = (Button) actionEvent.getSource();
        UUID currentProjectID = (UUID) buttonClicked.getUserData();
        interactor.openProject(currentProjectID);
    }
}