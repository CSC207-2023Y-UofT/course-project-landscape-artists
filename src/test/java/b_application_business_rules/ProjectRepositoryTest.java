package b_application_business_rules;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.use_cases.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectRepositoryTest {

    private ProjectRepository projectRepository;
    private Project testProject;

    @BeforeEach
    public void setup() {
        UUID projectID = UUID.randomUUID();
        String projectName = "Test Project";
        String projectDescription = "This is a test project";
        List<Column> projectColumns = new ArrayList<>();
        testProject = new Project(projectName, projectID, projectDescription, projectColumns);
        projectRepository = new ProjectRepository(null);
    }

    @Test
    public void testGetCurrentProject() {
        assertNull(projectRepository.getCurrentProject());
    }

    @Test
    public void testSetCurrentProject() {
        projectRepository.setCurrentProject(testProject);
        assertEquals(testProject, projectRepository.getCurrentProject());
    }

    @Test
    public void testRemoveCurrentProject() {
        projectRepository.setCurrentProject(testProject);
        projectRepository.removeCurrentProject();
        assertNull(projectRepository.getCurrentProject());
    }

    @Test
    public void testGetAllProjects() {
        List<Project> projects = new ArrayList<>();
        projects.add(testProject);

        projectRepository.setAllProjects(projects);
        assertEquals(projects, projectRepository.getAllProjects());
    }

    @Test
    public void testAddProject() {
        projectRepository.addProject(testProject);
        assertTrue(projectRepository.getAllProjects().contains(testProject));
    }

    @Test
    public void testRemoveProject() {
        projectRepository.addProject(testProject);
        projectRepository.removeProject(testProject);
        assertFalse(projectRepository.getAllProjects().contains(testProject));
    }
}
