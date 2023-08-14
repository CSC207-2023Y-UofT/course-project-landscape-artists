package c_interface_adapters;

import c_interface_adapters.view_models.ProjectViewModel;

public interface ProjectSelectionViewModelIterator {
    ProjectViewModel next();
    boolean hasNext();
}
