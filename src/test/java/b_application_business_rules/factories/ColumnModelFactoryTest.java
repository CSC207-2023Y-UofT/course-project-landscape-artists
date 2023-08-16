package b_application_business_rules.factories;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.TaskModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ColumnModelFactoryTest {

    @Test
    void create() {
        TaskModel task = TaskModelFactory.create("silly little task", UUID.randomUUID(), "silly little description", false, LocalDateTime.now());
        ArrayList<TaskModel> tasks = new ArrayList<TaskModel>();
        tasks.add(task);
        ColumnModel c = ColumnModelFactory.create("test name", tasks, UUID.randomUUID());

        assertEquals(c.getName(), "test name");
    }
}