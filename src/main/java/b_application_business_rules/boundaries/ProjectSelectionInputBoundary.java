package b_application_business_rules.boundaries;

import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.entity_models.ProjectModel;

public interface ProjectSelectionInputBoundary {
    void setCurrentProject(ProjectModel project);
    void createProject(String name, String description);
}