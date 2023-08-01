package c_interface_adapters;

import a_enterprise_business_rules.entities.Project;
import c_interface_adapters.view_models.ProjectViewModel;

public interface ProjectSelectionViewModelIterator {
    ProjectViewModel next();
    boolean hasNext();
}
