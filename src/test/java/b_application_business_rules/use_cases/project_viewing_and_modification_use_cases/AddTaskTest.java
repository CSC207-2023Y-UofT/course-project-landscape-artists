package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.entity_models.TaskModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTaskTest {



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddTaskToColumn() {
        Project mockProject = new Project("", UUID.randomUUID(), "", new ArrayList<>());
        // Create a sample column and task
        UUID columnId = UUID.randomUUID();
        TaskModel taskModel = new TaskModel("Test Task", UUID.randomUUID(), "Description", false, LocalDateTime.now());

        // Mock the behavior of the Project and Column classes
        Column mockColumn = new Column("Test Column", new ArrayList<>(), columnId);
//        when(mockProject.getColumns()).thenReturn(Collections.singletonList(mockColumn));
        assertEquals(0, mockProject.getColumns().size());

        // Create an instance of AddTask with the mock Project
        mockProject.addColumn(mockColumn);
        AddTask addTask = new AddTask(mockProject);
        addTask.addTask(columnId, taskModel);

        // Verify that the task was added to the column's list of tasks
        assertEquals(1, mockColumn.getTasks().size());
    }

    @Test
    public void testCreateTaskEntity() {
        // Create a sample TaskModel
        UUID taskId = UUID.randomUUID();
        TaskModel taskModel = new TaskModel("Test Task", taskId, "Description", false, LocalDateTime.now());

        // Create a task entity using the createTaskEntity method
        Task task = AddTask.createTaskEntity(taskModel);

        // Verify that the task entity's properties match the TaskModel's properties
        assertEquals(taskModel.getName(), task.getName());
        assertEquals(taskModel.getID(), task.getID());
        assertEquals(taskModel.getDescription(), task.getDescription());
        assertEquals(taskModel.getCompletionStatus(), task.getCompletionStatus());
        assertEquals(taskModel.getDueDateTime(), task.getDueDateTime());
    }
}
