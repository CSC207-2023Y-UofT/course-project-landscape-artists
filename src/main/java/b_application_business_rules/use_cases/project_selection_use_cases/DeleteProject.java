package b_application_business_rules.use_cases.project_selection_use_cases;
import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.boundaries.ProjectSelectionInputBoundary;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.use_cases.CurrentProjectRepository;

import java.util.UUID;

public class DeleteProject implements ProjectSelectionInputBoundary{
    private final ProjectSelectionInputBoundary outputBoundary;
    private final CurrentProjectRepository projectRepository;

    public DeleteProject(ProjectSelectionInputBoundary outputBoundary, CurrentProjectRepository projectRepository){
        this.outputBoundary = outputBoundary;
        this.projectRepository = projectRepository;
    }

    /**
     * @param project
     */
    @Override
    public void setCurrentProject(ProjectModel project) {

    }

    @Override
    public void setCurrentProject(Project project) {

    }

    @Override
    public void createProject(String name, String description) {

    }

    /**
     *
     */
    @Override
    public void createProject() {

    }

    @Override
    public void createProject(ProjectModel projectModel) {

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
    public void deleteProject(UUID projectID) {
        try {
            // Check if the project exists first
            Project project = projectRepository.getProjectByID(projectID);
            if (project == null){
                outputBoundary.projectDeletionFailed("Project with the ID " + projectID + " was not found.");
                return;
            }

            // Delete the project
            projectRepository.deleteProject(projectID);

            // Notify via the output boundary if deletion is successful
            outputBoundary.projectDeleted(projectID);
        } catch (Exception e){
            // Notify via output boundary if the deletion failed
            outputBoundary.projectDeletionFailed(e.getMessage());
        }
    }
}
