package c_interface_adapters.view_models;

/**
 * This model will be given passed between the Interface Adapters layer
 * and the Frameworks and Drivers layer to tell the View (in the Frameworks and
 * Drivers layer) what to be viewed.
 * 
 * This will be used when viewing and modifying a project.
 */
public class ProjectViewingAndModificationViewModel {
    /**
     * The project that's currently being viewed and modified.
     */
    ProjectViewModel currProjectViewModel;

    /**
     * Constructs a <code>ProjectViewingAndModificationModel</code> based on the
     * inputted <code>ProjectViewModel</code>.
     * 
     * @param currProjectViewModel
     */
    public ProjectViewingAndModificationViewModel(ProjectViewModel currProjectViewModel) {
        this.currProjectViewModel = currProjectViewModel;
    }

    /**
     * Gets the current ProjectViewModel.
     * 
     * @return the current ProjectViewModel.
     */
    public ProjectViewModel getCurrProjectViewModel() {
        return this.currProjectViewModel;
    }

    /**
     * Sets the current ProjectViewModel to the inputted ProjectViewModel.
     * 
     * @param currProjectViewModel the new ProjectViewModel.
     */
    public void setCurrProjectViewModel(ProjectViewModel currProjectViewModel) {
        this.currProjectViewModel = currProjectViewModel;
    }

}
