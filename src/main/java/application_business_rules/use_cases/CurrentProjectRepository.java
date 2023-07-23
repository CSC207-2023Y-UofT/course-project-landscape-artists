package application_business_rules.use_cases;

import enterprise_business_rules.entities.Project;

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
}
