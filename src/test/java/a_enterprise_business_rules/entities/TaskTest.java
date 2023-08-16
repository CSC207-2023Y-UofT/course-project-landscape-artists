package a_enterprise_business_rules.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
        List<Task> sampleTasks1 = new ArrayList<Task>();
        sampleTasks1.add(new Task("t1 name", UUID.randomUUID(), "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTasks1.add(new Task("t2 name", UUID.randomUUID(), "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTasks1.add(new Task("t3 name", UUID.randomUUID(), "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));

        UUID currID = UUID.randomUUID();

        Column c = new Column("testing column name", sampleTasks1, currID); // Set the UUID of the Column

        Assertions.assertEquals(c.getID(), currID);
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

    /**
     * NOT TESTED: NOT USED
     */
//    @Test
//
//    void negateCompletionStatus() {
//        Task t = new Task("test task name", UUID.randomUUID(), "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        t.incompleteTask();
//        Assertions.assertTrue(t.getCompletionStatus());
//
//        Task s = new Task("test task name", UUID.randomUUID(), "test task description", true,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        s.incompleteTask();
//        Assertions.assertFalse(s.getCompletionStatus());
//    }

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