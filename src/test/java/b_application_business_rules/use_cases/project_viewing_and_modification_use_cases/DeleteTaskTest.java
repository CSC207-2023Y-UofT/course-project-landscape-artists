package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.*;
import b_application_business_rules.entity_models.TaskModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeleteTaskTest {

    private Project currentProject;
    private DeleteTask deleteTask;

    @BeforeEach
    public void setup() {
        // Initialize test data
        currentProject = new Project("", UUID.randomUUID(), "", new ArrayList<>());
        deleteTask = new DeleteTask(currentProject);
    }

    @Test
    public void testDeleteTask() {
        // Create a test column and task
        UUID columnID = UUID.randomUUID();
        UUID taskID = UUID.randomUUID();
        Task task = new Task("", taskID, "", true, LocalDateTime.now());
        TaskModel taskModel = new TaskModel(task);

        // Create mock objects for Column and Task
        Column testColumn = mock(Column.class);
        Task testTask = mock(Task.class);

        when(testColumn.getID()).thenReturn(columnID);
        when(testColumn.getTasks()).thenReturn(new ArrayList<>(List.of(testTask)));
        when(testTask.getID()).thenReturn(taskID);

        // Populate the project's columns and tasks
        currentProject.addColumn(testColumn);
    }
}
