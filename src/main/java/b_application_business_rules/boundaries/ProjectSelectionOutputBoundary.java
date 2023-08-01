package b_application_business_rules.boundaries;

import b_application_business_rules.entity_models.ProjectModel;

public interface ProjectSelectionOutputBoundary {
    void displayCurrentProject(ProjectModel projectModel);

    void displayRenamedProject(ProjectModel projectModel);

    void displayDeletedProject(ProjectModel projectModel);
}