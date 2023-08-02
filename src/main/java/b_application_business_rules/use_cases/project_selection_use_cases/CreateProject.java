package b_application_business_rules.use_cases.project_selection_use_cases;
import a_enterprise_business_rules.entities.entities.Project;
import b_application_business_rules.boundaries.ProjectSelectionInputBoundary;
import b_application_business_rules.boundaries.ProjectSelectionOutputBoundary;
import b_application_business_rules.entity_models.ProjectModel;

import java.util.UUID;

public class CreateProject implements ProjectSelectionInputBoundary {
    private ProjectSelectionOutputBoundary outputBoundary;

    public CreateProject(ProjectSelectionOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void setCurrentProject(Project project) {

    }

    @Override
    public void createProject(String name, String description) {

    }

    @Override
    public void createProject(ProjectModel projectModel){
        try{
            // Create the new Project entity
            Project project = createProjectEntity(projectModel);
            Project project = new Project(projectModel.getName(), projectModel.getID(), projectModel.getDescription(),
                    projectModel.getColumnModels());

            // Notify via the output boundary if created successfully
            outputBoundary.projectCreated(new ProjectModel(project.getName(), project.getID(), project.getDescription(),
                    project.getColumns())); // ???
        } catch (Exception e){
            // Notify via the output boundary if cretion failed
            outputBoundary.projectCreationFailed(e.getMessage());
        }
    }

    @Override
    public void projectDeletionFailed(String message) {

    }

    @Override
    public void projectDeleted(UUID projectID) {

    }

    @Override
    public void openProject(UUID currentProjectID) {

    }

    @Override
    public void renameProject(UUID projectUUID) {

    }

    @Override
    public void deleteProject(UUID projectUUID) {

    }

    // Validate projectModel data
    private void validateProjectModel(ProjectModel projectModel) {
        if (projectModel.getName() == null || projectModel.getName().isEmpty()){
            throw new IllegalArgumentException("Project name cannot be empty.");
        }

        // to be continued
    }

    // Create the Project entity from the ProjectModel (Project factory ?)
    private Project createProjectEntity(ProjectModel projectModel) {
        return new Project(projectModel.getName(), projectModel.getID(), projectModel.getDescription(),
                projectModel.getColumnModels()); // ???
    }

}