package c_interface_adapters;

import b_application_business_rules.boundaries.ProjectSelectionInputBoundary;
import b_application_business_rules.use_cases.project_selection_use_cases.ProjectSelectionInteractor;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;

/**
 * ProjectSelectionController class handles the event handling and controlling of creating and opening a project
 */
public class ProjectSelectionController implements Initializable {
    // The interactor for project selection and creation. It implements ProjectSelectionInputBoundary.
    private static ProjectSelectionInputBoundary interactor;

    // The presenter associated with the controller in Project Selection UI.
    ProjectSelectionPresenter presenter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        presenter =
                new ProjectSelectionPresenter(this);
        interactor =
                new ProjectSelectionInteractor(presenter);
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
        // Otherwise, if this is the first action by the user, then interactor and presenter is null
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
        interactor.createProject(name, description);
    }

    /**
     * Handles the action of selecting a project button from the UI.
     */
    public void handleChosenProjectButton(ActionEvent actionEvent) {
        Button buttonClicked = (Button) actionEvent.getSource();
        UUID currentProjectID = (UUID) buttonClicked.getUserData();
        interactor.openProject(currentProjectID);
    }
}