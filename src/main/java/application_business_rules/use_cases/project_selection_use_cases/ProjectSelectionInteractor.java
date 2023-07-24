package application_business_rules.use_cases.project_selection_use_cases;

import application_business_rules.boundaries.ProjectSelectionInputBoundary;
import application_business_rules.use_cases.CurrentProjectRepository;
import enterprise_business_rules.entities.Project;

public class ProjectSelectionInteractor implements ProjectSelectionInputBoundary {

    CurrentProjectRepository currentProjectRepository =
            CurrentProjectRepository.getInstance();
    @Override
    public void setCurrentProject(Project project) {
        currentProjectRepository.setCurrentProject(project);
    }

    @Override
    public void createProject(Project project) {
        System.out.println("PROJECT WILL BE CREATED INTERNALLY HERE");
    }

}
