package b_application_business_rules.use_cases;

import a_enterprise_business_rules.entities.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing the current project and a list of projects.
 */
public class ProjectRepository {
    /** The single instance of this singleton class */
    private static final ProjectRepository PROJECT_REPOSITORY = new ProjectRepository(null);

    /** The current project that this repository is holding */
    private Project currentProject;

    /** The list of all projects in the system */
    private List<Project> allProjects;

    /**
     * Constructs a new instance of CurrentProjectRepository.
     *
     * @param currentProject The initial current project.
     */
    public ProjectRepository(Project currentProject) {
        this.currentProject = currentProject;
        this.allProjects = new ArrayList<>();
    }

    /**
     * Gets the singleton instance of CurrentProjectRepository.
     *
     * @return The singleton instance.
     */
    public static ProjectRepository getProjectRepository() {
        return PROJECT_REPOSITORY;
    }

    /**
     * Gets the project that the repository holds.
     *
     * @return The current project.
     */
    public Project getCurrentProject() {
        return currentProject;
    }

    /**
     * Sets the project that the repository holds.
     *
     * @param project The project to set as the current project.
     */
    public void setCurrentProject(Project project) {
        currentProject = project;
    }

    /**
     * Sets the project that the repository holds.
     */
    public void removeCurrentProject() {
        this.setCurrentProject(null);
    }

    /**
     * Returns all the projects in the class.
     *
     * @return List<Project> held in class.
     */
    public List<Project> getAllProjects() {
        return allProjects;
    }

    /**
     * Gets the list of all projects.
     *
     * @return The list of all projects.
     */
    public void setAllProjects(List<Project> projects) {

        for (Project project: projects) {
            allProjects.add(project);
        }
    }

    /**
     * Adds a project to the list of all projects.
     *
     * @param project The project to add.
     */
    public void addProject(Project project) {
        allProjects.add(project);
    }

    /**
     * Removes a project from the list of all projects.
     * If the removed project is the current project, the current project reference is also removed.
     *
     * @param project The project to remove.
     */
    public void removeProject(Project project) {
        allProjects.remove(project);
        if (currentProject == project) {
            removeCurrentProject();
        }
    }

}
