package b_application_business_rules.use_cases;

import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.entity_models.ProjectModel;

import java.util.UUID;

public class CurrentProjectRepository {
    /** The single instance of this singleton class */
    private static final CurrentProjectRepository currentProjectRepository = new CurrentProjectRepository(null);

    /** The current project that this repository is holding */
    private ProjectModel currentProject;

    /** Constructor for the singleton class */
    public CurrentProjectRepository(ProjectModel currentProject) {
        this.currentProject = currentProject;
    }

    /** Gets the singleton instance of CurrentProjectRepository */
    public static CurrentProjectRepository getCurrentprojectrepository() {
        return currentProjectRepository;
    }

    /** Gets the project that the repository holds */
    public ProjectModel getCurrentProject() {
        return currentProject;
    }

    /** Sets the project that the repository holds */
    public void setCurrentProject(ProjectModel project) {
        currentProject = project;
    }

    /** Sets the project that the repository holds to a null reference. */
    public void removeCurrentProject() {
        this.setCurrentProject(null);
    }

    public void deleteProject(UUID projectID) {}

    public Project getProjectByID(UUID projectID) {
        return null;
    }
}
