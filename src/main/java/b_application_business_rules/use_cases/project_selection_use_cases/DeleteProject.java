package b_application_business_rules.use_cases.project_selection_use_cases;

import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerRemoveController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import a_enterprise_business_rules.entities.*;

/**
 * This class defines the logic for deleting projects and their associated columns and tasks.
 * It interacts with the database through the IDBRemove interface.
 */
public class DeleteProject {

    private IDBRemove databaseRemover = new DBManagerRemoveController();
    private List<Project> allProjects;
    private Project projectToBeRemoved;

    /**
     * Constructor to initialize the DeleteProject instance with a list of all projects.
     *
     * @param allProjects The list of all projects in the system.
     */
    public DeleteProject(List<Project> allProjects) {
        this.allProjects = allProjects;
    }

    /**
     * Constructor to initialize the DeleteProject instance with a list of all projects.
     *
     * @param allProjects The list of all projects in the system.
     */
    public DeleteProject(List<Project> allProjects, IDBRemove dbImplementation) {
        this.allProjects = allProjects;
        this.databaseRemover = dbImplementation;
    }

    /**
     * Deletes a project from the database based on the provided project UUID.
     *
     * @param projectUUID The UUID of the project to be deleted.
     */
    public void deleteProject(UUID projectUUID) {
        for (Project projectToBeRemoved : allProjects) {
            if (projectToBeRemoved.getID().equals(projectUUID)) {
                deleteColumnFromDB(projectToBeRemoved);
                deleteProjectInDatabase(projectUUID);
                this.projectToBeRemoved = projectToBeRemoved;
            }
        }

        allProjects.remove(projectToBeRemoved);
    }

    /**
     * Deletes the associated columns and tasks of a project from the database.
     *
     * @param projectToBeRemoved The project to be removed from the database.
     */
    private void deleteColumnFromDB(Project projectToBeRemoved) {
        for (Column column: projectToBeRemoved.getColumns() ) {
            deleteTaskFromDB(column);
            databaseRemover.DBRemoveColumn(column.getID());
        }
    }

    /**
     * Deletes tasks associated with a column from the database.
     *
     * @param column The column whose tasks are to be removed from the database.
     */
    private void deleteTaskFromDB(Column column) {
        for (Task task : column.getTasks()) {
            databaseRemover.DBRemoveTask(task.getID());
        }
    }

    /**
     * Deletes a project from the database based on its UUID.
     *
     * @param projectUUID The UUID of the project to be deleted from the database.
     */
    public void deleteProjectInDatabase(UUID projectUUID) {
        databaseRemover.DBRemoveProject(projectUUID);
    }
}
