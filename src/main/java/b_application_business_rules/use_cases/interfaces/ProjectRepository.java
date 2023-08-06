package b_application_business_rules.use_cases.interfaces;

import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.entity_models.ProjectModel;

import java.util.List;
import java.util.UUID;
/**
 * first draft
 */
public interface ProjectRepository {
    List<ProjectModel> getAllProjects();
    ProjectModel getProjectById(UUID projectId);
    void deleteProject(ProjectModel projectModel);
}

