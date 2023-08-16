package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import static javafx.beans.binding.Bindings.when;
import static org.junit.jupiter.api.Assertions.*;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.*;
import b_application_business_rules.entity_models.TaskModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class AddTaskTest {



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
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
        assertTrue(mockProject.getColumns().size() == 0);

        // Create an instance of AddTask with the mock Project
        mockProject.addColumn(mockColumn);
        AddTask addTask = new AddTask(mockProject);
        addTask.addTask(columnId, taskModel);

        // Verify that the task was added to the column's list of tasks
        assertTrue(mockColumn.getTasks().size() == 1);
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
