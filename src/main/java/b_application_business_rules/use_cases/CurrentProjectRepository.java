package b_application_business_rules.use_cases;

import b_application_business_rules.entity_models.ProjectModel;

public class CurrentProjectRepository {
    private static final CurrentProjectRepository instance =
            new CurrentProjectRepository();

    private ProjectModel currentProject;

    public static CurrentProjectRepository getInstance() {
        return instance;
    }

    private CurrentProjectRepository(){}

    public ProjectModel getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(ProjectModel project) {
        currentProject = project;
    }

    public void removeCurrentProject() {
        currentProject = null;
    }
}
