package b_application_business_rules.use_cases.project_selection_use_cases;

import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerRemoveController;

/**
 * A use case to edit the project details, during the mode of the application
 * where we are selecting projects.
 */
public class EditProjectDetails {

    /** The current project we are editing */
    ProjectModel currentProjectModel;

    /**
     * Constructs an instance of the use case, given the inputted Project Model.
     * 
     * @param currentProjectModel The project model to be editing.
     */
    public EditProjectDetails(ProjectModel currentProjectModel) {
        this.currentProjectModel = currentProjectModel;
    }

    /**
     * Sets a new name for the project in the database.
     * 
     * @param newName The new name to give to the project
     */
    public void setName(String newName) {
        // TODO -- once the database implements some sort of way to actually edit data,
        // this should change to that
        // until that, this will be removing an entire project, and then reinserting it
        // lmao.

        // Also, shouldn't I be having a getter and setter when accessing the
        // currentProject model...?

        IDBRemove databaseRemover = new DBManagerRemoveController();
        databaseRemover.DBRemoveProject(this.currentProjectModel.getID());

        this.currentProjectModel.setName(newName);

        IDBInsert databaseInserter = new DBManagerInsertController();
        databaseInserter.DBInsert(this.currentProjectModel);
    }

    /**
     * Sets a new description for the project in the database.
     * 
     * @param newDescription The new description to give to the project
     */
    public void setDescription(String newDescription) {
        // TODO -- once the database implements some sort of way to actually edit data,
        // this should change to that
        // until that, this will be removing an entire project, and then reinserting it
        // lmao.

        // Also, shouldn't I be having a getter and setter when accessing the
        // currentProject model...?

        IDBRemove databaseRemover = new DBManagerRemoveController();
        databaseRemover.DBRemoveProject(this.currentProjectModel.getID());

        this.currentProjectModel.setName(newDescription);

        IDBInsert databaseInserter = new DBManagerInsertController();
        databaseInserter.DBInsert(this.currentProjectModel);
    }

}
