package b_application_business_rules.use_cases.project_selection_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeleteProjectTest {

    @Mock
    private IDBRemove databaseRemover;

    private List<Project> allProjects;
    private DeleteProject deleteProject;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        allProjects = new ArrayList<>();
        deleteProject = new DeleteProject(allProjects, databaseRemover);
    }

    @Test
    public void testDeleteProject() {
        UUID projectUUID = UUID.randomUUID();
        Project projectToBeDeleted = new Project("Test Project", projectUUID, "Project Description", new ArrayList<>());
        allProjects.add(projectToBeDeleted);

        deleteProject.deleteProject(projectUUID);

        verify(databaseRemover, times(1)).DBRemoveProject(projectUUID);
        assertTrue(allProjects.isEmpty());
    }

    @Test
    public void testDeleteProjectWithColumnsAndTasks() {
        UUID projectUUID = UUID.randomUUID();
        UUID columnUUID = UUID.randomUUID();
        UUID taskUUID = UUID.randomUUID();

        Task task = new Task("Test Task", taskUUID, "Task Description", false, LocalDateTime.now());
        Column column = new Column("Test Column", new ArrayList<>(), columnUUID);
        column.addTask(task);
        Project projectToBeDeleted = new Project("Test Project", projectUUID, "Project Description", new ArrayList<>());
        projectToBeDeleted.addColumn(column);
        allProjects.add(projectToBeDeleted);

        deleteProject.deleteProject(projectUUID);

        verify(databaseRemover, times(1)).DBRemoveProject(projectUUID);
        verify(databaseRemover, times(1)).DBRemoveColumn(columnUUID);
        verify(databaseRemover, times(1)).DBRemoveTask(taskUUID);
        assertTrue(allProjects.isEmpty());
    }

    // More tests for other methods can be added similarly
}
