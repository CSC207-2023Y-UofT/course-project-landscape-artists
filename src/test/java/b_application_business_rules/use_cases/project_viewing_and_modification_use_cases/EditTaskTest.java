package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.entity_models.TaskModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.UUID;

public class EditTaskTest {

    private Project mockProject;
    private Column mockColumn;
    private Task mockTask;
    private TaskModel updatedTaskModel;

    @BeforeEach
    public void setUp() {
        mockProject = mock(Project.class);
        mockColumn = mock(Column.class);
        mockTask = mock(Task.class);

        // Create a mock TaskModel with updated data
        UUID taskId = UUID.randomUUID();
        updatedTaskModel = new TaskModel("Updated Task Name", taskId, "Updated Description", false, null);
    }

    @Test
    public void testEditTask() {
        // Mock the behavior of Project, Column, and Task classes
        when(mockColumn.getID()).thenReturn(UUID.randomUUID());
        when(mockTask.getID()).thenReturn(updatedTaskModel.getID());
        when(mockColumn.getTasks()).thenReturn(Collections.singletonList(mockTask));
        when(mockProject.getColumns()).thenReturn(Collections.singletonList(mockColumn));

        // Create an instance of EditTask with the mock Project
        EditTask editTask = new EditTask(mockProject);
        Task editedTask = editTask.editTask(mockColumn.getID(), updatedTaskModel);

        // Verify that the task's details were edited
        verify(mockTask).setName(updatedTaskModel.getName());
        verify(mockTask).setDescription(updatedTaskModel.getDescription());
        verify(mockTask).setDueDateTime(updatedTaskModel.getDueDateTime());

        // Assert that the edited task returned matches the mock task
        assertEquals(mockTask, editedTask);
    }
}
