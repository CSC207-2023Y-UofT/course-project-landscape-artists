package b_application_business_rules.boundaries;

import a_enterprise_business_rules.entities.Project;

public interface ProjectSelectionInputBoundary {
    void setCurrentProject(Project project);

    void createProject(Project project);
}