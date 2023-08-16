package c_interface_adapters;

import c_interface_adapters.view_models.ProjectViewModel;

/**
 * This interface defines the methods for iterating through a collection of ProjectViewModels
 * within the context of project selection.
 */
public interface ProjectSelectionViewModelIterator {

    /**
     * Retrieves the next ProjectViewModel in the iteration.
     *
     * @return The next ProjectViewModel in the iteration.
     */
    ProjectViewModel next();

    /**
     * Checks if there is a next ProjectViewModel available in the iteration.
     *
     * @return True if a next ProjectViewModel exists, false otherwise.
     */
    boolean hasNext();
}
