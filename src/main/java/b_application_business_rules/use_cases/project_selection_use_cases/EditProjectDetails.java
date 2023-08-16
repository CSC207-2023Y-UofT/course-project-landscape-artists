package b_application_business_rules.use_cases.project_selection_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import b_application_business_rules.use_cases.project_selection_gateways.IDbIdToModel;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerRemoveController;
import d_frameworks_and_drivers.database_management.DBControllers.DbIDToModel;

import java.util.*;
/**
 * A use case to edit the project details, during the mode of the application
 * where projects are being selected.
 */
public class EditProjectDetails {

    private Project projectToBeEdited;
    private List<Project> allProjects;

    /**
     * Constructs an instance of the use case to edit project details.
     *
     * @param allProjects  The list of all projects in the system.
     * @param projectUUID  The UUID of the project to be edited.
     */
    public EditProjectDetails(List<Project> allProjects, UUID projectUUID) {
        this.allProjects = allProjects;
        for (Project project : allProjects) {
            if (project.getID().equals(projectUUID)) {
                projectToBeEdited = project;
                break;
            }
        }
        if (projectToBeEdited == null) {
            System.err.println("Project not found");
        }
    }

    /**
     * Sets a new name and description for the project in the database.
     *
     * @param newName        The new name for the project.
     * @param newDescription The new description for the project.
     */
    public void setNameAndDescription(String newName, String newDescription) {
        IDBRemove databaseRemover = new DBManagerRemoveController();
        databaseRemover.DBRemoveProject(projectToBeEdited.getID());

        projectToBeEdited.setName(newName);
        projectToBeEdited.setDescription(newDescription);

        IDBInsert databaseInserter = new DBManagerInsertController();
        databaseInserter.DBInsert(new ProjectModel(projectToBeEdited));
    }
}