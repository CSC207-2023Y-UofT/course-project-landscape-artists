package b_application_business_rules.use_cases.project_selection_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import b_application_business_rules.use_cases.project_selection_gateways.IDBRemove;
import b_application_business_rules.use_cases.project_selection_use_cases.EditProjectDetails;
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

public class EditProjectDetailsTest {

    @Mock
    private IDBRemove databaseRemover;

    @Mock
    private IDBInsert databaseInserter;

    private List<Project> allProjects;
    private EditProjectDetails editProjectDetails;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        allProjects = new ArrayList<>();

    }

    @Test
    public void testSetNameAndDescription() {
        UUID projectUUID = UUID.randomUUID();
        Project project = new Project("Test Project", projectUUID, "Old Description", new ArrayList<>());
        allProjects.add(project);

        editProjectDetails = new EditProjectDetails(allProjects, projectUUID);

        editProjectDetails.setNameAndDescription("New Project Name", "New Project Description");

        assertEquals("New Project Name", project.getName());
        assertEquals("New Project Description", project.getDescription());
    }

    @Test
    public void testCreateProjectEntity() {
        UUID taskUUID = UUID.randomUUID();
        Task taskModel = new Task("Test Task", taskUUID, "Task Description",
                false, LocalDateTime.now());
        Column columnModel = new Column("Test Column", List.of(taskModel), UUID.randomUUID());
        Project projectEntity = new Project("Test Project", UUID.randomUUID(), "Project Description",
                List.of(columnModel));


        assertEquals("Test Project", projectEntity.getName());
        assertEquals("Project Description", projectEntity.getDescription());

        List<Column> columns = projectEntity.getColumns();
        assertEquals(1, columns.size());

        Column column = columns.get(0);
        assertEquals("Test Column", column.getName());

        List<Task> tasks = column.getTasks();
        assertEquals(1, tasks.size());

        Task task = tasks.get(0);
        assertEquals("Test Task", task.getName());
        assertEquals("Task Description", task.getDescription());
        assertFalse(task.getCompletionStatus());
        assertNotNull(task.getDueDateTime());
    }
}
