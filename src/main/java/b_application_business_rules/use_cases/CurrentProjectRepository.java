package b_application_business_rules.use_cases;

import a_enterprise_business_rules.entities.Project;

import java.util.UUID;

public class CurrentProjectRepository {
    private static final CurrentProjectRepository instance =
            new CurrentProjectRepository();

    private Project currentProject;

    public static CurrentProjectRepository getInstance() {
        return instance;
    }

    private CurrentProjectRepository(){}

    public Project getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(Project project) {
        currentProject = project;
    }

    public void removeCurrentProject() {
        currentProject = null;
    }

    public void deleteProject(UUID projectID) {}

    public Project getProjectByID(UUID projectID) {}
}
