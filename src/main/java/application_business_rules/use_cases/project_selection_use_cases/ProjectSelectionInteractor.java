package application_business_rules.use_cases.project_selection_use_cases;

import application_business_rules.boundaries.ProjectSelectionInputBoundary;
import application_business_rules.boundaries.ProjectSelectionOutputBoundary;
import application_business_rules.use_cases.CurrentProjectRepository;
import enterprise_business_rules.entities.Project;

public class ProjectSelectionInteractor implements ProjectSelectionInputBoundary {

    private CurrentProjectRepository currentProjectRepository =
            CurrentProjectRepository.getInstance();
    private ProjectSelectionOutputBoundary presenter;

    public ProjectSelectionInteractor(ProjectSelectionOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setCurrentProject(Project project) {
        currentProjectRepository.setCurrentProject(project);
        presenter.displayCurrentProject();
    }

    @Override
    public void createProject(Project project) {
        System.out.println("PROJECT WILL BE CREATED INTERNALLY HERE");
    }

}
