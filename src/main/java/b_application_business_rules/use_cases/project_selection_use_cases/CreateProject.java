package b_application_business_rules.use_cases.project_selection_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;
import java.util.List;
import java.util.UUID;

public class CreateProject {

    public CreateProject() {
    }

    /**
     * Creates a project in the database based on the inputted <code>Project</code>.
     * 
     * @param project The project to insert into the database.
     */
    public void createProject(Project project) {
        IDBInsert databaseInserter = new DBManagerInsertController();
        ProjectModel projectModel = new ProjectModel(project);
        databaseInserter.DBInsert(projectModel);
    }

    /**
     * Creates a project in the database based on the inputted project's attributes.
     * 
     * @param name        The project's name.
     * @param ID          The project's UUID.
     * @param description The project's description.
     * @param columns     The project's columns.
     */
    public void createProject(String name, UUID ID, String description, List<Column> columns) {
        IDBInsert databaseInserter = new DBManagerInsertController();
        Project project = new Project(name, ID, description, columns);
        ProjectModel projectModel = new ProjectModel(project);
        databaseInserter.DBInsert(projectModel);
    }
}
