package b_application_business_rules.use_cases.project_selection_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.factories.ProjectModelFactory;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerRemoveController;
import java.util.*;
/**
 * A use case to edit the project details, during the mode of the application
 * where we are selecting projects.
 */
public class EditProjectDetails {

    /** The current project we are editing */
    ProjectModel originalProjectModel;
    private final Project currentProject;

    //This is just added here so that it helps when working with entities
    private final CurrentProjectRepository currentProjectRepository = CurrentProjectRepository
            .getCurrentprojectrepository();

    /**
     * Constructs an instance of the use case, given the inputted Project Model.
     * 
     * @param originalProjectModel The project model to be editing.
     */
    public EditProjectDetails(ProjectModel originalProjectModel) {
        //this.updatedProjectModel = updatedProjectModel;
        this.originalProjectModel = originalProjectModel;
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

        //This is added in so that it updates the repository (which it didn't do before)
        currentProjectRepository.getCurrentProject().getProjectEntity().setName(newName);


        //updatedProjectModel = ProjectModelFactory.create(newName, originalProjectModel.getID(),
                //originalProjectModel.getDescription(), originalProjectModel.getColumnModels());



        IDBRemove databaseRemover = new DBManagerRemoveController();
        databaseRemover.DBRemove(this.originalProjectModel, this.originalProjectModel.getID());

        this.originalProjectModel.setName(newName);
        //Added for the sake of testing
        Project oldProject = createProjectEntity(originalProjectModel);
        System.out.println(oldProject.getName());

        IDBInsert databaseInserter = new DBManagerInsertController();
        databaseInserter.DBInsert(this.originalProjectModel);
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
        //This is added in so that it updates the repository (which it didn't do before)
        currentProjectRepository.getCurrentProject().getProjectEntity().setDescription(newDescription);

        IDBRemove databaseRemover = new DBManagerRemoveController();
        databaseRemover.DBRemove(this.originalProjectModel, this.originalProjectModel.getID());

        this.originalProjectModel.setDescription(newDescription);
        //Added for the sake of testing
        Project oldProject = createProjectEntity(originalProjectModel);
        System.out.println(oldProject.getDescription());

        IDBInsert databaseInserter = new DBManagerInsertController();
        databaseInserter.DBInsert(this.originalProjectModel);
    }
    /** Purpose of this method is to convert a projectModel into a Project entity*/
    public static Project createProjectEntity(ProjectModel projectModel) {
        List<ColumnModel> columnModels = projectModel.getColumnModels();
        List<Column> columns = new ArrayList<>();
        for (ColumnModel columnModel : columnModels) {
            List<TaskModel> taskModels = columnModel.getTaskModels();
            List<Task> tasks = new ArrayList<>();
            for (TaskModel taskModel : taskModels) {
                tasks.add(new Task(taskModel.getName(), taskModel.getID(),taskModel.getDescription(),
                        taskModel.getCompletionStatus(), taskModel.getDueDateTime()));
            }
            columns.add(new Column(columnModel.getName(), tasks, columnModel.getID()));
        }
        return new Project(projectModel.getName(), projectModel.getID(), projectModel.getDescription(),
                columns);
    }

}
