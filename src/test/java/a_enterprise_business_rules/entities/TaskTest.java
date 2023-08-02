package a_enterprise_business_rules.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void getName() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertEquals(t.getName(), "test task name");
    }

    @Test
    void setName() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.setName("New testing name!");
        Assertions.assertEquals(t.getName(), "New testing name!");
    }

    @Test
    void getID() {
        UUID taskUUID = UUID.randomUUID();
        Task t = new Task("test task name", taskUUID, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertEquals(t.getID(), taskUUID);
    }

    @Test
    void setID() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        UUID newTaskUUID = UUID.randomUUID();
        Assertions.assertEquals(t.getID(), newTaskUUID);
    }

    @Test
    void getDescription() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertEquals(t.getDescription(), "test task description");
    }

    @Test
    void setDescription() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.setDescription("newerrr test task description");
        Assertions.assertEquals(t.getDescription(), "newerrr test task description");
    }

    @Test
    void getCompletionStatus() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertEquals(t.getCompletionStatus(), false);
    }

    @Test
    void completeTask() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.completeTask();
        Assertions.assertEquals(t.getCompletionStatus(), true);

        Task s = new Task("test task name", UUID.randomUUID(), "test task description", true,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        s.completeTask();
        Assertions.assertEquals(s.getCompletionStatus(), true);
    }

    @Test
    void incompleteTask() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.incompleteTask();
        Assertions.assertEquals(t.getCompletionStatus(), false);

        Task s = new Task("test task name", UUID.randomUUID(), "test task description", true,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        s.incompleteTask();
        Assertions.assertEquals(s.getCompletionStatus(), false);
    }

    @Test
    void negateCompletionStatus() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.incompleteTask();
        Assertions.assertEquals(t.getCompletionStatus(), true);

        Task s = new Task("test task name", UUID.randomUUID(), "test task description", true,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        s.incompleteTask();
        Assertions.assertEquals(s.getCompletionStatus(), false);
    }

    @Test
    void getDueDateTime() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertEquals(t.getDueDateTime(), LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
    }

    @Test
    void setDueDateTime() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.setDueDateTime(LocalDateTime.of(4024, 03, 28, 14, 2, 11, 1110));
        Assertions.assertEquals(t.getDueDateTime(), LocalDateTime.of(4024, 03, 28, 14, 2, 11, 1110));
    }

    @Test
    void testToString() {
        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertEquals(
                t.toString(), "[Task Name: test task name, Task Completed: false, Due Date: 2024-03-28T14:33:48]");

    }

    @Test
    void testEquals() {
        Task t1 = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task t2 = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task t3 = new Task("test task name", UUID.randomUUID(), "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        // Reflexive Property
        Assertions.assertTrue(
                t1.equals(t1));

        // Symmetric Property
        Assertions.assertTrue(
                t1.equals(t2));
        Assertions.assertTrue(
                t2.equals(t1));

        // Transitive Property
        if (t1.equals(t2) && t2.equals(t3)) {
            Assertions.assertTrue(t1.equals(t3));
        }
    }
}