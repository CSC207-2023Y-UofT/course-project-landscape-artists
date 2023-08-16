package b_application_business_rules.factories;

import b_application_business_rules.entity_models.TaskModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskModelFactoryTest {

    @Test
    public void testCreateTaskModel() {
        String taskName = "Test Task";
        UUID taskId = UUID.randomUUID();
        String taskDescription = "Task Description";
        boolean isCompleted = false;
        LocalDateTime dueDateTime = LocalDateTime.of(2023, 8, 31, 12, 0);

        TaskModel taskModel = TaskModelFactory.create(taskName, taskId, taskDescription, isCompleted, dueDateTime);

        assertNotNull(taskModel);
        assertEquals(taskName, taskModel.getName());
        assertEquals(taskId, taskModel.getID());
        assertEquals(taskDescription, taskModel.getDescription());
        assertEquals(isCompleted, taskModel.getCompletionStatus());
        assertEquals(dueDateTime, taskModel.getDueDateTime());
    }
}
