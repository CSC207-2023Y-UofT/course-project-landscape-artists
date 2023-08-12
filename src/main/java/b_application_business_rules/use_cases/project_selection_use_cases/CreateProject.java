package b_application_business_rules.use_cases.project_selection_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;

import java.util.List;
import java.util.UUID;

public class CreateProject {
    private List<Project> allProjects;

    /**
     * Empty constructor (would be autogenerated by Java if not included anyways)
     */
    public CreateProject(List<Project> allProjects) {
        this.allProjects = allProjects;
    }

    /**
     * Creates a project in the database based on the inputted <code>Project</code>.
     * 
     * @param project The project to insert into the database.
     */
   // public void createProject(Project project) {
       // IDBInsert databaseInserter = new DBManagerInsertController();
       // ProjectModel projectModel = new ProjectModel(project);
        //databaseInserter.DBInsert(projectModel);
    //}

    /**
     * Creates a project in the database based on the inputted project's attributes.
     * 
     * @param name        The project's name.
     * @param ID          The project's UUID.
     * @param description The project's description.
     * @param columns     The project's columns.
     */
    public Project newProject(String name, UUID ID, String description, List<Column> columns) {
        Project newProject = new Project(name, ID, description, columns);
        // update the list of entities to have the new project.
        allProjects.add(newProject);

        return newProject;

    }
}
