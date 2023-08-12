package b_application_business_rules.use_cases;

import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.entity_models.ProjectModel;


public class CurrentProjectRepository {
    /** The single instance of this singleton class */
    private static final CurrentProjectRepository currentProjectRepository = new CurrentProjectRepository(null);

    /** The current project that this repository is holding */
    private Project currentProject;

    /** Constructor for the singleton class */
    public CurrentProjectRepository(Project currentProject) {
        this.currentProject = currentProject;
    }

    /** Gets the singleton instance of CurrentProjectRepository */
    public static CurrentProjectRepository getCurrentprojectrepository() {
        return currentProjectRepository;
    }

    /** Gets the project that the repository holds */
    public Project getCurrentProject() {
        return currentProject;
    }

    /** Sets the project that the repository holds */
    public void setCurrentProject(Project project) {
        currentProject = project;
    }

    /** Sets the project that the repository holds to a null reference. */
    public void removeCurrentProject() {
        this.setCurrentProject(null);
    }

}
