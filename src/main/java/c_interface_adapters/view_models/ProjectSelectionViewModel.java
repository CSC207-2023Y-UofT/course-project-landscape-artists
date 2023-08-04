package c_interface_adapters.view_models;

import c_interface_adapters.ProjectSelectionViewModelIterator;

import java.util.List;
import java.util.HashMap;

import java.util.UUID;

import java.util.NoSuchElementException;


/**
 * This model will be given passed between the Interface Adapters layer
 * and the Frameworks and Drivers layer to tell the View (in the Frameworks and
 * Drivers layer) what to be viewed.
 * 
 * This will be used when selecting a project.
 */
public class ProjectSelectionViewModel implements ProjectSelectionViewModelIterator {
    /**
     * The list of all projects to be viewed during project selection
     */
    private List<ProjectViewModel> allProjectViewModels;
    private int currentIndex = 0;

    /**
     * Constructs a <code>ProjectSelectionViewModel</code> instance
     * based on the inputted <code>List</code> of <code>ProjectViewModel</code>s.
     * 
     * @param allProjectViewModels
     */
    public ProjectSelectionViewModel(List<ProjectViewModel> allProjectViewModels) {
        this.allProjectViewModels = allProjectViewModels;
    }

    /**
     * Gets all the projects that need be shown during project selection.
     * 
     * @return a <code>List</code> of <code>ProjectViewModel</code>s
     */
    public List<ProjectViewModel> getAllProjectViewModels() {
        return this.allProjectViewModels;
    }

    /**
     * Sets the projects that need be shown during project selection.
     * 
     * @param allProjectViewModels The <code>List</code> of
     *                             <code>ProjectViewModel</code>s
     *                             that need be shown during project selection.
     */
    public void setAllProjectViewModels(List<ProjectViewModel> allProjectViewModels) {
        this.allProjectViewModels = allProjectViewModels;
    }

    /**
     * Gets a <code>ProjectViewModel</code> from the output <code>List</code> from
     * getAllProjects based on the inputted index.
     * 
     * @param index The index of the <code>ProjectViewModel</code> to retrieve.
     * 
     * @return A particular <code>ProjectViewModel</code>
     */
    public ProjectViewModel getProjectViewModel(int index) {
        return this.getAllProjectViewModels().get(index);
    }

    /**
     * Gets a <code>ProjectViewModel</code> from the output <code>List</code> from
     * getAllProjects based on the inputted ID.
     * 
     * @param ID The identifier (id) of the <code>ProjectViewModel</code> to
     *           get.
     * 
     * @return A particular <code>ProjectViewModel</code>
     */
    public ProjectViewModel getProjectViewModel(UUID ID) {
        // Creates a mapping between IDs and ProjectViewModels
        HashMap<UUID, ProjectViewModel> ProjectViewModelIDToProjectViewModel = new HashMap<UUID, ProjectViewModel>();
        for (ProjectViewModel currProjectViewModel : this.getAllProjectViewModels()) {
            ProjectViewModelIDToProjectViewModel.put(
                    currProjectViewModel.getID(), currProjectViewModel);
        }

        // Retrives the ProjectViewModel, based on the inputted ID
        return ProjectViewModelIDToProjectViewModel.get(ID);
    }

    /**
     * Removes a <code>ProjectViewModel</code> from the output <code>List</code>
     * from
     * getAllProjects based on the inputted index.
     * 
     * @param index The index of the <code>ProjectViewModel</code> to remove.
     * 
     * @return A particular <code>ProjectViewModel</code>
     */
    public void removeProjectViewModel(int index) {
        List<ProjectViewModel> allProjectViewModelsReference = this.getAllProjectViewModels();
        allProjectViewModelsReference.remove(index);

        this.setAllProjectViewModels(allProjectViewModelsReference);
    }

    /**
     * Removes a <code>ProjectViewModel</code> from the output <code>List</code>
     * from
     * getAllProjects based on the inputted ID.
     * 
     * @param ID The identifier (id) of the <code>ProjectViewModel</code> to
     *           remove.
     * 
     * @return A particular <code>ProjectViewModel</code>
     */
    public void removeProjectViewModel(UUID ID) {
        // Creates a mapping between IDs and ProjectViewModels
        HashMap<UUID, ProjectViewModel> ProjectViewModelIDToProjectViewModel = new HashMap<UUID, ProjectViewModel>();
        for (ProjectViewModel currProjectViewModel : this.getAllProjectViewModels()) {
            ProjectViewModelIDToProjectViewModel.put(
                    currProjectViewModel.getID(), currProjectViewModel);
        }

        // Removes the ProjectViewModel with the inputted ID (gets the ProjectViewModel
        // based on the inputted ID and the HashMap created)
        List<ProjectViewModel> allProjectViewModelsReference = this.getAllProjectViewModels();
        allProjectViewModelsReference.remove(
                ProjectViewModelIDToProjectViewModel.get(ID));
    }


    @Override
    public boolean hasNext() {
        return currentIndex < allProjectViewModels.size();
    }

    @Override
    public ProjectViewModel next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return allProjectViewModels.get(currentIndex++);
    }
}
