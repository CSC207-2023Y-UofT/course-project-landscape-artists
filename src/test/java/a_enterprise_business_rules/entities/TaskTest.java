package a_enterprise_business_rules.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A class to test the Tasks entity.
 */
public class TaskTest {

    @Test
    /**
     * Tests getName
     */
    void getName() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertEquals(t.getName(), "test task name");
    }

    @Test
    /**
     * Tests setName
     */
    void setName() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.setName("New testing name!");
        Assertions.assertEquals(t.getName(), "New testing name!");
    }

    @Test
    /**
     * Tests getID
     */
    void getID() {
        UUID taskUUID = UUID.randomUUID();
        Task t = new Task("test task name", taskUUID, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertEquals(t.getID(), taskUUID);
    }

    @Test
    /**
     * Tests setID
     */
    void setID() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        UUID newTaskUUID = UUID.randomUUID();
        Assertions.assertEquals(t.getID(), newTaskUUID);
    }

    @Test
    /**
     * Tests getDescription
     */
    void getDescription() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertEquals(t.getDescription(), "test task description");
    }

    @Test
    /**
     * Tests setDescription
     */
    void setDescription() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.setDescription("newerrr test task description");
        Assertions.assertEquals(t.getDescription(), "newerrr test task description");
    }

    @Test
    /**
     * Tests getCompletionStatus
     */
    void getCompletionStatus() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertFalse(t.getCompletionStatus());
    }

    @Test
    /**
     * Tests completeTask
     */
    void completeTask() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.completeTask();
        Assertions.assertTrue(t.getCompletionStatus());

        Task s = new Task("test task name", UUID.randomUUID(), "test task description", true,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        s.completeTask();
        Assertions.assertTrue(s.getCompletionStatus());
    }

    @Test
    /**
     * Tests incompleteTask
     */
    void incompleteTask() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.incompleteTask();
        Assertions.assertFalse(t.getCompletionStatus());

        Task s = new Task("test task name", UUID.randomUUID(), "test task description", true,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        s.incompleteTask();
        Assertions.assertFalse(s.getCompletionStatus());
    }

    @Test
    /**
     * Test negateCompletionStatus
     */
    void negateCompletionStatus() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.incompleteTask();
        Assertions.assertTrue(t.getCompletionStatus());

        Task s = new Task("test task name", UUID.randomUUID(), "test task description", true,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        s.incompleteTask();
        Assertions.assertFalse(s.getCompletionStatus());
    }

    @Test
    /**
     * Tests getDueDateTime
     */
    void getDueDateTime() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertEquals(t.getDueDateTime(), LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
    }

    @Test
    /**
     * Tests setDueDateTime
     */
    void setDueDateTime() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.setDueDateTime(LocalDateTime.of(4024, 03, 28, 14, 2, 11, 1110));
        Assertions.assertEquals(t.getDueDateTime(), LocalDateTime.of(4024, 03, 28, 14, 2, 11, 1110));
    }

    @Test
    /**
     * Tests toString
     */
    void testToString() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertEquals(
                t.toString(), "[Task Name: test task name, Task Completed: false, Due Date: 2024-03-28T14:33:48]");

    }

    @Test
    /**
     * Tests equals
     */
    void testEquals() {
        Task t1 = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task t2 = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task t3 = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        // Reflexive Property
        Assertions.assertEquals(t1, t1);

        // Symmetric Property
        Assertions.assertEquals(t1, t2);
        Assertions.assertEquals(t2, t1);

        // Transitive Property
        if (t1.equals(t2) && t2.equals(t3)) {
            Assertions.assertEquals(t1, t3);
        }
    }


    @Test
    /**
     * Tests ITToTask
     */
    void TestIDToTask() {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();


        Task t1 = new Task("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task t2 = new Task("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task t3 = new Task("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<Task> tasks1 = new ArrayList<Task>();
        tasks1.add(t1);
        tasks1.add(t2);
        tasks1.add(t3);

        Task output = Task.IDToTask(u2, tasks1);
        Assertions.assertEquals(output, t2);

    }

}