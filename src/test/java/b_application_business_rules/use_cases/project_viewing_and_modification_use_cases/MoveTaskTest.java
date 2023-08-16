package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.entity_models.TaskModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class MoveTaskTest {

    @Test
    public void testMoveTask() {
        // Create tasks
        Task taskToMove = new Task("Task to move",UUID.randomUUID(), "Description", false, LocalDateTime.now());
        TaskModel taskModel = new TaskModel("Task to move", taskToMove.getID(), "Description", false,  LocalDateTime.now());

        // Create columns with tasks
        Column sourceColumn = new Column("Source Column", new ArrayList<>(List.of(taskToMove)), UUID.randomUUID());
        Column targetColumn = new Column("Target Column", new ArrayList<>(), UUID.randomUUID());

        // Create a project with columns
        List<Column> columns = new ArrayList<>(List.of(sourceColumn, targetColumn));
        // Create a project with columns
        Project project = new Project("Test Project", UUID.randomUUID(), "Project description", columns);


        // Create an instance of MoveTask
        MoveTask moveTask = new MoveTask(sourceColumn.getID().toString(), targetColumn.getID().toString(), project);

        // Perform the task move
        moveTask.moveTask(taskModel.getID());

        // Verify that the task was moved from source to target column
        assertFalse(sourceColumn.getTasks().contains(taskToMove));
        assertTrue(targetColumn.getTasks().contains(taskToMove));
    }
}
