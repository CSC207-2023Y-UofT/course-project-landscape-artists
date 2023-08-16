package b_application_business_rules.factories;

import b_application_business_rules.entity_models.TaskModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the task model factory.
 */
class TaskModelFactoryTest {
    /**
     * Tests the create method
     */
    @Test
    void create() {
        TaskModel task = TaskModelFactory.create("silly little task", UUID.randomUUID(), "silly little description", false, LocalDateTime.now());
        assertEquals(task.getName(), "silly little task");
    }
}