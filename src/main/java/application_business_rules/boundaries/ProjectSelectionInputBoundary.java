package application_business_rules.boundaries;

import enterprise_business_rules.entities.Project;

public interface ProjectSelectionInputBoundary {
    void setCurrentProject(Project project);

}