package b_application_business_rules.boundaries;

import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.entity_models.ProjectModel;

import java.util.UUID;

public interface ProjectSelectionInputBoundary {
    void setCurrentProject(Project project);

    void setCurrentProject(ProjectModel project);

    void setCurrentProject(Project project);

    void createProject(String name, String description);

    void createProject();

    void createProject(ProjectModel projectModel);

    void deleteProject(UUID projectID);

    void projectDeletionFailed(String message);

    void projectDeleted(UUID projectID);

    void openProject(UUID currentProjectID);

    void renameProject(UUID projectUUID);

}