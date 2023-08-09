package b_application_business_rules.boundaries;
import b_application_business_rules.entity_models.ProjectModel;

import b_application_business_rules.entity_models.ProjectModel;

import java.io.IOException;

public interface ProjectSelectionOutputBoundary {

    void displayCurrentProject();
  
    void projectCreated(ProjectModel projectModel);
    void projectCreationFailed(String errorMessage);

    void displayCurrentProject(ProjectModel projectModel);

    void displayRenamedProject(ProjectModel projectModel);

    void displayDeletedProject(ProjectModel projectModel);

}