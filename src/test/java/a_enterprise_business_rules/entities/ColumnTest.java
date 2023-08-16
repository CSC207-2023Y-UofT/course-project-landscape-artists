package a_enterprise_business_rules.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A class to test the Column entity.
 */
public class ColumnTest {

    @Test
    /**
     * Tests getName
     */
    void getName() {
        List<Task> sampleTasks1 = new ArrayList<Task>();
        sampleTasks1.add(new Task("t1 name", UUID.randomUUID(), "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTasks1.add(new Task("t2 name", UUID.randomUUID(), "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTasks1.add(new Task("t3 name", UUID.randomUUID(), "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        Column c = new Column("testing column name", sampleTasks1, UUID.randomUUID());

        Assertions.assertEquals(c.getName(), "testing column name");
    }

    @Test
    /**
     * Tests setName
     */
    void setName() {
        List<Task> sampleTasks1 = new ArrayList<Task>();
        sampleTasks1.add(new Task("t1 name", UUID.randomUUID(), "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTasks1.add(new Task("t2 name", UUID.randomUUID(), "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTasks1.add(new Task("t3 name", UUID.randomUUID(), "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        Column c = new Column("testing column name", sampleTasks1, UUID.randomUUID());

        c.setName("new test column name");

        Assertions.assertEquals(c.getName(), "new test column name");
    }

    @Test
    /**
     * Tests getID
     */
    void getID() {
        UUID currID = UUID.randomUUID();
        List<Task> sampleTasks1 = new ArrayList<Task>();
        sampleTasks1.add(new Task("t1 name", UUID.randomUUID(), "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTasks1.add(new Task("t2 name", UUID.randomUUID(), "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTasks1.add(new Task("t3 name", UUID.randomUUID(), "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        Column c = new Column("testing column name", sampleTasks1, currID);

        Assertions.assertEquals(c.getID(), currID);
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
     * Tests getTasks
     */
    void getTasks() {
        List<Task> sampleTasks1 = new ArrayList<Task>();
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        sampleTasks1.add(new Task("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTasks1.add(new Task("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTasks1.add(new Task("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        Column c = new Column("testing column name", sampleTasks1, UUID.randomUUID());

        List<Task> sampleTasks2 = new ArrayList<Task>();
        sampleTasks2.add(new Task("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTasks2.add(new Task("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTasks2.add(new Task("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));

        Assertions.assertEquals(c.getTasks(), sampleTasks2);
    }

    @Test
    /**
     * Tests setTasks
     */
    void setTasks() {
        List<Task> sampleTasks1 = new ArrayList<Task>();
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        sampleTasks1.add(new Task("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTasks1.add(new Task("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTasks1.add(new Task("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        Column c = new Column("testing column name", sampleTasks1, UUID.randomUUID());

        UUID newu1 = UUID.randomUUID();
        UUID newu2 = UUID.randomUUID();
        UUID newu3 = UUID.randomUUID();
        List<Task> sampleTasks2 = new ArrayList<Task>();
        sampleTasks2.add(new Task("new t1 name", newu1, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTasks2.add(new Task("new t2 name", newu2, "new t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTasks2.add(new Task("new t3 name", newu3, "new t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));

        c.setTasks(sampleTasks2);

        List<Task> sampleTasks3 = new ArrayList<Task>();
        sampleTasks3.add(new Task("new t1 name", newu1, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTasks3.add(new Task("new t2 name", newu2, "new t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTasks3.add(new Task("new t3 name", newu3, "new t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));

        Assertions.assertEquals(c.getTasks(), sampleTasks3);
    }

    @Test
    /**
     * Tests addTask
     */
    void addTask() {
        List<Task> sampleTasks1 = new ArrayList<Task>();
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        sampleTasks1.add(new Task("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTasks1.add(new Task("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTasks1.add(new Task("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        Column c = new Column("testing column name", sampleTasks1, UUID.randomUUID());

        UUID newUUID = UUID.randomUUID();

        Task newTask = new Task("new t1 name", newUUID, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task newTaskCopy = new Task("new t1 name", newUUID, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        c.addTask(newTask);

        List<Task> sampleTasks2 = new ArrayList<Task>();
        sampleTasks2.add(new Task("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTasks2.add(new Task("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTasks2.add(new Task("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        sampleTasks2.add(newTaskCopy);

        Assertions.assertEquals(c.getTasks(), sampleTasks2);
    }

    @Test
    /**
     * Tests addTaskToPosition
     */
    void addTaskToPosition() {
        List<Task> sampleTasks1 = new ArrayList<Task>();
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        sampleTasks1.add(new Task("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTasks1.add(new Task("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTasks1.add(new Task("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        Column c = new Column("testing column name", sampleTasks1, UUID.randomUUID());

        UUID newUUID = UUID.randomUUID();

        Task newTask = new Task("new t1 name", newUUID, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task newTaskCopy = new Task("new t1 name", newUUID, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        c.addTaskToPosition(newTask, 0);

        List<Task> sampleTasks2 = new ArrayList<Task>();
        sampleTasks2.add(new Task("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTasks2.add(new Task("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTasks2.add(new Task("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        sampleTasks2.add(0, newTaskCopy);

        Assertions.assertEquals(c.getTasks(), sampleTasks2);
    }

    @Test
    /**
     * Tests removeTask
     */
    void removeTask() {
        List<Task> sampleTasks1 = new ArrayList<Task>();
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        Task taskToRemove = new Task("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2));
        sampleTasks1.add(new Task("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTasks1.add(taskToRemove);
        sampleTasks1.add(new Task("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        Column c = new Column("testing column name", sampleTasks1, UUID.randomUUID());

        c.removeTask(taskToRemove);

        List<Task> sampleTasks2 = new ArrayList<Task>();
        sampleTasks2.add(new Task("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTasks2.add(new Task("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));

        Assertions.assertEquals(c.getTasks(), sampleTasks2);
    }

    @Test
    /**
     * Tests swapTaskOrder
     */
    void swapTaskOrder() {
        List<Task> sampleTasks1 = new ArrayList<Task>();
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        Task task1 = new Task("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task task2 = new Task("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2));
        sampleTasks1.add(task1);
        sampleTasks1.add(task2);
        sampleTasks1.add(new Task("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        Column c = new Column("testing column name", sampleTasks1, UUID.randomUUID());

        c.swapTaskOrder(task1, task2);

        List<Task> sampleTasks2 = new ArrayList<Task>();
        sampleTasks2.add(task2);
        sampleTasks2.add(task1);
        sampleTasks2.add(new Task("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));

        Assertions.assertEquals(c.getTasks(), sampleTasks2);
    }

    @Test
    /**
     * Tests moveTaskToPosition
     */
    void moveTaskToPosition() {
        List<Task> sampleTasks1 = new ArrayList<Task>();
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        Task t1 = new Task("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        sampleTasks1.add(t1);
        sampleTasks1.add(new Task("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTasks1.add(new Task("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        Column c = new Column("testing column name", sampleTasks1, UUID.randomUUID());

        c.moveTaskToPosition(t1, 2);

        Task t1Dupe = new Task("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        List<Task> sampleTasks2 = new ArrayList<Task>();
        sampleTasks2.add(new Task("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTasks2.add(new Task("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        sampleTasks2.add(t1Dupe);

        Assertions.assertEquals(c.getTasks(), sampleTasks2);
    }

    @Test
    /**
     * Test toString
     */
    void testToString() {
        List<Task> sampleTasks1 = new ArrayList<Task>();
        sampleTasks1.add(new Task("t1 name", UUID.randomUUID(), "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTasks1.add(new Task("t2 name", UUID.randomUUID(), "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTasks1.add(new Task("t3 name", UUID.randomUUID(), "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        Column c = new Column("testing column name", sampleTasks1, UUID.randomUUID());

        String columnStringRepresentation = "[Column Name: testing column name, "
                + "Tasks: {[Task Name: t1 name, Task Completed: false, Due Date: 2024-03-28T14:33:48], "
                + "[Task Name: t2 name, Task Completed: true, Due Date: 2023-02-18T11:10:05.000000002], "
                + "[Task Name: t3 name, Task Completed: false, Due Date: 1985-10-01T19:13:09.000000006]}]";

        Assertions.assertEquals(c.toString(), columnStringRepresentation);
    }

    @Test
    /**
     * Tests equals
     */
    void testEquals() {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();

        UUID cu = UUID.randomUUID();
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

        Task tt1 = new Task("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task tt2 = new Task("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task tt3 = new Task("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<Task> tasks2 = new ArrayList<Task>();
        tasks2.add(tt1);
        tasks2.add(tt2);
        tasks2.add(tt3);

        Task ttt1 = new Task("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task ttt2 = new Task("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task ttt3 = new Task("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<Task> tasks3 = new ArrayList<Task>();
        tasks3.add(ttt1);
        tasks3.add(ttt2);
        tasks3.add(ttt3);

        Column c1 = new Column("test column", tasks1, cu);
        Column c2 = new Column("test column", tasks2, cu);
        Column c3 = new Column("test column", tasks3, cu);

        // Reflexive Property
        Assertions.assertEquals(c1, c1);

        // Symmetric Property
        Assertions.assertEquals(c1, c2);
        Assertions.assertEquals(c2, c1);

        // Transitive Property
        if (c1.equals(c2) && c2.equals(c3)) {
            Assertions.assertEquals(c1, c3);
        }
    }


    @Test
    /**
     * Tests IDToColumn
     */
    void TestIDToColumn() {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();

        UUID cu1 = UUID.randomUUID();
        UUID cu2 = UUID.randomUUID();
        UUID cu3 = UUID.randomUUID();


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

        Task tt1 = new Task("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task tt2 = new Task("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task tt3 = new Task("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<Task> tasks2 = new ArrayList<Task>();
        tasks2.add(tt1);
        tasks2.add(tt2);
        tasks2.add(tt3);

        Task ttt1 = new Task("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task ttt2 = new Task("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task ttt3 = new Task("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<Task> tasks3 = new ArrayList<Task>();
        tasks3.add(ttt1);
        tasks3.add(ttt2);
        tasks3.add(ttt3);

        Column c1 = new Column("test column", tasks1, cu1);
        Column c2 = new Column("test column", tasks2, cu2);
        Column c3 = new Column("test column", tasks3, cu3);

        ArrayList<Column> col = new ArrayList<>();
        col.add(c1);
        col.add(c2);
        col.add(c3);

        Column output = Column.IDToColumn(cu2, col);
        Assertions.assertEquals(c2, output);

    }
}