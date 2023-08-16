package b_application_business_rules.use_cases.project_selection_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;

import java.util.List;
import java.util.UUID;

/**
 * This class encapsulates the logic for creating projects and adding them to a list of allProjects.
 * It provides methods to create and initialize Project instances.
 */
public class CreateProject {

    /**
     * The list of all projects in the system.
     */
    private List<Project> allProjects;

    /**
     * Constructor for initializing the CreateProject instance with a list of all projects.
     *
     * @param allProjects The list of all projects in the system.
     */
    public CreateProject(List<Project> allProjects) {
        this.allProjects = allProjects;
    }

    /**
     * Creates a new project and adds it to the list of allProjects.
     *
     * @param name        The name of the new project.
     * @param ID          The UUID of the new project.
     * @param description The description of the new project.
     * @param columns     The columns associated with the new project.
     * @return The newly created Project instance.
     */
    public Project newProject(String name, UUID ID, String description, List<Column> columns) {
        Project newProject = new Project(name, ID, description, columns);

        // Add the new project to the list of allProjects
        allProjects.add(newProject);

        return newProject;
    }
}
