//package b_application_business_rules.use_cases;
//
//import a_enterprise_business_rules.entities.Project;
//import b_application_business_rules.use_cases.interfaces.ProjectRepository;
//
//import java.util.List;
//import java.util.UUID;
///**
// * first draft
// */
//public class ProjectManager {
//    private ProjectRepository projectRepository;
//
//    public ProjectManager(ProjectRepository projectRepository) {
//        this.projectRepository = projectRepository;
//    }
//
//    public Project getProjectById(UUID projectId) {
//        return projectRepository.getProjectById(projectId);
//    }
//
//    public List<Project> getAllProjects() {
//        return projectRepository.getAllProjects();
//    }
//
//    public void saveProject(Project project) {
//        // Perform any business logic validation before saving the project
//        projectRepository.saveProject(project);
//    }
//
//    // Additional use cases for project manipulation
//}
