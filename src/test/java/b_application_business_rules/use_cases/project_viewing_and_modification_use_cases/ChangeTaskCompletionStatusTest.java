package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import b_application_business_rules.use_cases.project_viewing_and_modification_use_cases.ChangeTaskCompletionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChangeTaskCompletionStatusTest {

    private Project project;
    private ChangeTaskCompletionStatus changeTaskCompletionStatus;

    @BeforeEach
    public void setUp() {
        // Set up your test environment here, including creating a sample project and tasks
        // For example:
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task 1", UUID.randomUUID(), "", false, null));
        tasks.add(new Task("Task 2", UUID.randomUUID(), "", true, null));

        List<Column> columns = new ArrayList<>();
        columns.add(new Column("Column 1", tasks, UUID.randomUUID()));

        project = new Project("Sample Project",UUID.randomUUID(),"", columns);

        changeTaskCompletionStatus = new ChangeTaskCompletionStatus(project);
    }

    @Test
    public void testChangeCompletionStatus() {
        // Test changing the completion status of an existing task
        Task taskToUpdate = project.getColumns().get(0).getTasks().get(0);
        UUID taskID = taskToUpdate.getID();

        Task updatedTask = changeTaskCompletionStatus.changeCompletionStatus(taskID);

        assertEquals(taskToUpdate.getCompletionStatus(), updatedTask.getCompletionStatus(), "Completion status should be toggled");
    }

    @Test
    public void testChangeCompletionStatusNonExistentTask() {
        // Test changing the completion status of a non-existent task
        UUID nonExistentTaskID = UUID.randomUUID();

        Task updatedTask = changeTaskCompletionStatus.changeCompletionStatus(nonExistentTaskID);

        assertNull(updatedTask, "No task should be updated for a non-existent task ID");
    }
}
