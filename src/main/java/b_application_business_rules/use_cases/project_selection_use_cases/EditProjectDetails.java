package b_application_business_rules.use_cases.project_selection_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import b_application_business_rules.use_cases.project_selection_gateways.IDbIdToModel;
import d_frameworks_and_drivers.database_management.DBControllers.DBManagerInsertController;
import d_frameworks_and_drivers.database_management.DBControllers.DbIDToModel;

import java.util.*;
/**
 * A use case to edit the project details, during the mode of the application
 * where we are selecting projects.
 */
public class EditProjectDetails {

    /** Project to be edited */
    //ProjectModel originalProjectModel;
    private Project projectToBeEdited;


    //private final CurrentProjectRepository currentProjectRepository = CurrentProjectRepository
            //.getCurrentprojectrepository();

    /**
     * Constructs an instance of the use case, given the inputted Project Model.
     *
     * @param allProjects All projects in the system.
     * @param projectUUID
     */
    public EditProjectDetails(List<Project> allProjects, UUID projectUUID) {
        // iterate over the list of all projects, find the project to be edited.
        for (Project project : allProjects) {
            if (project.getID().equals(projectUUID)) {
                projectToBeEdited = project;
            }
        }
        System.err.println("Project not found");
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

        //This is added in so that it "updates" the currentProject. This is still very problematic



        //updatedProjectModel = ProjectModelFactory.create(newName, originalProjectModel.getID(),
                //originalProjectModel.getDescription(), originalProjectModel.getColumnModels());
        IDbIdToModel iDbIdToModel = new DbIDToModel();
        ProjectModel originalProjectModel = iDbIdToModel.IdToProjectModel(projectToBeEdited.getID().toString());

        DeleteProject deleteProject = new DeleteProject();
        deleteProject.deleteProject(originalProjectModel, projectToBeEdited.getID());



        projectToBeEdited.setName(newName);
        ProjectModel updatedProjectModel = iDbIdToModel.IdToProjectModel(projectToBeEdited.getID().toString());


        IDBInsert databaseInserter = new DBManagerInsertController();
        databaseInserter.DBInsert(updatedProjectModel);
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

        ///currentProjectRepository.getCurrentProject().getProjectEntity().setDescription(newDescription);


        IDbIdToModel iDbIdToModel = new DbIDToModel();
        ProjectModel originalProjectModel = iDbIdToModel.IdToProjectModel(projectToBeEdited.getID().toString());

        DeleteProject deleteProject = new DeleteProject();
        deleteProject.deleteProject(originalProjectModel, projectToBeEdited.getID());


        //This is added in so that it "updates" currentProject
        projectToBeEdited.setDescription(newDescription);
        ProjectModel updatedProjectModel = iDbIdToModel.IdToProjectModel(projectToBeEdited.getID().toString());


        IDBInsert databaseInserter = new DBManagerInsertController();
        databaseInserter.DBInsert(updatedProjectModel);
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
