package c_interface_adapters.view_models;

import java.util.*;

/**
 * This model will be given passed between the Interface Adapters layer
 * and the Frameworks and Drivers layer to tell the View (in the Frameworks and
 * Drivers layer) what to be viewed.
 * 
 * This will be used when selecting a project.
 */
public class ProjectSelectionViewModel {
    /**
     * The list of all projects to be viewed during project selection
     */
    private List<ProjectViewModel> allProjects;

    /**
     * Constructs a <code>ProjectSelectionViewModel</code> instance
     * based on the inputted <code>List</code> of <code>ProjectViewModel</code>s.
     * 
     * @param allProjects
     */
    public ProjectSelectionViewModel(List<ProjectViewModel> allProjects) {
        this.allProjects = allProjects;
    }

    /**
     * Gets all the projects that need be shown during project selection.
     * 
     * @return a <code>List</code> of <code>ProjectViewModel</code>s
     */
    public List<ProjectViewModel> getAllProjects() {
        return this.allProjects;
    }

    /**
     * Sets the projects that need be shown during project selection.
     * 
     * @param allProjects The <code>List</code> of <code>ProjectViewModel</code>s
     *                    that need be shown during project selection.
     */
    public void setAllProjects(List<ProjectViewModel> allProjects) {
        this.allProjects = allProjects;
    }

}
