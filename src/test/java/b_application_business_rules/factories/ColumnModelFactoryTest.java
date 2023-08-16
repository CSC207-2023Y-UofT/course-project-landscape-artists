package b_application_business_rules.factories;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.TaskModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ColumnModelFactoryTest {

    @Test
    public void testCreateColumnModel() {
        String columnName = "Test Column";
        UUID columnId = UUID.randomUUID();
        List<TaskModel> taskModels = new ArrayList<>();
        taskModels.add(new TaskModel("Task 1", UUID.randomUUID(), "Description 1", false, null));
        taskModels.add(new TaskModel("Task 2", UUID.randomUUID(), "Description 2", true, null));

        ColumnModel columnModel = ColumnModelFactory.create(columnName, taskModels, columnId);

        assertNotNull(columnModel);
        assertEquals(columnName, columnModel.getName());
        assertEquals(columnId, columnModel.getID());
        assertEquals(taskModels, columnModel.getTaskModels());
    }

    @Test
    public void testCreateEmptyColumnModel() {
        String columnName = "Empty Column";
        UUID columnId = UUID.randomUUID();
        List<TaskModel> emptyTaskModels = new ArrayList<>();

        ColumnModel columnModel = ColumnModelFactory.create(columnName, emptyTaskModels, columnId);

        assertNotNull(columnModel);
        assertEquals(columnName, columnModel.getName());
        assertEquals(columnId, columnModel.getID());
        assertEquals(emptyTaskModels, columnModel.getTaskModels());
    }
}
