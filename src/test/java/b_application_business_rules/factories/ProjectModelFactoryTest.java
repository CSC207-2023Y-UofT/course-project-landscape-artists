package b_application_business_rules.factories;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProjectModelFactoryTest {
    @Test
    void create() {
        TaskModel task = TaskModelFactory.create("silly little task", UUID.randomUUID(), "silly little description", false, LocalDateTime.now());
        ArrayList<TaskModel> tasks = new ArrayList<TaskModel>();
        tasks.add(task);
        ColumnModel c = ColumnModelFactory.create("test name", tasks, UUID.randomUUID());
        ArrayList<ColumnModel> cols = new ArrayList<>();
        cols.add(c);
        ProjectModel p = ProjectModelFactory.create("silly potato", UUID.randomUUID(), "what a project, huH?", cols);

        assertEquals(p.getDescription(), "what a project, huH");
    }
}