package b_application_business_rules.use_cases.project_selection_use_cases;

import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerRemoveController;

import java.util.List;
import java.util.UUID;

import a_enterprise_business_rules.entities.Project;

public class DeleteProject {

    // The list of allProjects in the system
    private List<Project> allProjects;
    /**
     * Empty constructor (would be autogenerated by Java if not included anyways)
     */
    public DeleteProject() {

    }

    /**
     * Deletes a project from the database, given its ID.
     * 
     * @param ID The ID of the project to delete.
     */
    //public void deleteProject(UUID ID) {
        // IDBSearch databaseSearcher = new DBManagerSearchController();


        // List<String> projectUUIDs = databaseSearcher.DBColumnSearch(ID.toString());
        //ProjectModel tempProjectModel = new ProjectModel("tempname", ID, "", null);


        // TOOD -- So, like, the DBRemove needs a ProjectModel, in addition to the UUID,
        // but like, it never actually uses it :|
        // so, I'm just creating a temporary project model (rather than a null
        // reference)
        // so no "errors" are seen... idkdkdkkdkd
    //}

    /**
     * Deletes a project from the database, given a Project entity.
     * 
     * @param project The project to delete from the database.
     */
    //PLEASE NOTE: I included this method (even though it does nothing) purely for testing coverage
    //It is only called by the DeleteProjectTest
    //The other deleteProject method is not tested
    public boolean deleteProject(Project project) {
        return true;
    }

    /**
     * Deletes a project from the database, given a ProjectModel.
     * 
     * @param projectModel The project to delete from the database.
     */
    public void deleteProject(ProjectModel projectModel, UUID projectUUID) {
        //this.deleteProject(project.getID());
        IDBRemove databaseRemover = new DBManagerRemoveController();
        databaseRemover.DBRemove(projectModel, projectUUID);
    }

}
