package application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import application_business_rules.boundaries.ProjectViewingAndModificationInputBoundary;
import application_business_rules.use_cases.CurrentProjectRepository;

public class ProjectViewingAndModificationInteractor implements ProjectViewingAndModificationInputBoundary {
    CurrentProjectRepository currentProjectRepository =
            CurrentProjectRepository.getInstance();
    @Override
    public void removeCurrentProject() {
        currentProjectRepository.removeCurrentProject();
    }
}
