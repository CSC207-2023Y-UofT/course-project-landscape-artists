package b_application_business_rules.entity_models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A class to test the ColumnModel entity.
 */
public class ColumnModelTest {

    @Test
    /**
     * Tests getName
     */
    void getName() {
        List<TaskModel> sampleTaskModels1 = new ArrayList<TaskModel>();
        sampleTaskModels1.add(new TaskModel("t1 name", UUID.randomUUID(), "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskModels1.add(new TaskModel("t2 name", UUID.randomUUID(), "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskModels1.add(new TaskModel("t3 name", UUID.randomUUID(), "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnModel c = new ColumnModel("testing column name", sampleTaskModels1, UUID.randomUUID());

        Assertions.assertEquals(c.getName(), "testing column name");
    }

    @Test
    /**
     * Tests setName
     */
    void setName() {
        List<TaskModel> sampleTaskModels1 = new ArrayList<TaskModel>();
        sampleTaskModels1.add(new TaskModel("t1 name", UUID.randomUUID(), "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskModels1.add(new TaskModel("t2 name", UUID.randomUUID(), "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskModels1.add(new TaskModel("t3 name", UUID.randomUUID(), "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnModel c = new ColumnModel("testing column name", sampleTaskModels1, UUID.randomUUID());

        c.setName("new test column name");

        Assertions.assertEquals(c.getName(), "new test column name");
    }

    @Test
    /**
     * Tests getID
     */
    void getID() {
        UUID currID = UUID.randomUUID();
        List<TaskModel> sampleTaskModels1 = new ArrayList<TaskModel>();
        sampleTaskModels1.add(new TaskModel("t1 name", UUID.randomUUID(), "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskModels1.add(new TaskModel("t2 name", UUID.randomUUID(), "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskModels1.add(new TaskModel("t3 name", UUID.randomUUID(), "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnModel c = new ColumnModel("testing column name", sampleTaskModels1, currID);

        Assertions.assertEquals(c.getID(), currID);
    }

    @Test
    /**
     * Tests setID
     */
    void setID() {
        List<TaskModel> sampleTaskModels1 = new ArrayList<TaskModel>();
        sampleTaskModels1.add(new TaskModel("t1 name", UUID.randomUUID(), "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskModels1.add(new TaskModel("t2 name", UUID.randomUUID(), "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskModels1.add(new TaskModel("t3 name", UUID.randomUUID(), "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));

        UUID currID = UUID.randomUUID();

        ColumnModel c = new ColumnModel("testing column name", sampleTaskModels1, currID); // Set the UUID of the ColumnModel

        Assertions.assertEquals(c.getID(), currID);
    }

    @Test
    /**
     * Tests getTaskModels
     */
    void getTaskModels() {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();

        List<TaskModel> sampleTaskModels1 = new ArrayList<TaskModel>();
        sampleTaskModels1.add(new TaskModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskModels1.add(new TaskModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskModels1.add(new TaskModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));

        ColumnModel c = new ColumnModel("testing column name", sampleTaskModels1, UUID.randomUUID());

        List<TaskModel> actualTaskModels = c.getTaskModels();

        Assertions.assertEquals(actualTaskModels, sampleTaskModels1);
    }

    @Test
    /**
     * Tests setTaskModels
     */
    void setTaskModels() {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();

        List<TaskModel> sampleTaskModels1 = new ArrayList<TaskModel>();
        sampleTaskModels1.add(new TaskModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskModels1.add(new TaskModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskModels1.add(new TaskModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));

        ColumnModel c = new ColumnModel("testing column name", sampleTaskModels1, UUID.randomUUID());

        UUID newu1 = UUID.randomUUID();
        UUID newu2 = UUID.randomUUID();
        UUID newu3 = UUID.randomUUID();

        List<TaskModel> sampleTaskModels2 = new ArrayList<TaskModel>();
        sampleTaskModels2.add(new TaskModel("new t1 name", newu1, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskModels2.add(new TaskModel("new t2 name", newu2, "new t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskModels2.add(new TaskModel("new t3 name", newu3, "new t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));

        c.setTaskModels(sampleTaskModels2);

        Assertions.assertEquals(c.getTaskModels(), sampleTaskModels2);
    }

    @Test
    /**
     * Tests addTaskModel
     */
    void addTaskModel() {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        List<TaskModel> sampleTaskModels1 = new ArrayList<>();
        sampleTaskModels1.add(new TaskModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskModels1.add(new TaskModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskModels1.add(new TaskModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnModel c = new ColumnModel("testing column name", sampleTaskModels1, UUID.randomUUID());

        UUID newUUID = UUID.randomUUID();
        TaskModel newTaskModel = new TaskModel("new t1 name", newUUID, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel newTaskModelCopy = new TaskModel("new t1 name", newUUID, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        c.addTaskModel(newTaskModel);

        sampleTaskModels1.add(newTaskModelCopy);

        Assertions.assertEquals(c.getTaskModels(), sampleTaskModels1);
    }

    /**
     * NOT TESTED: NOT USED
     */
//    void addTaskModelToPosition() {
//
//    }

    /**
     * NOT TESTED: NOT USED
     */
//    void removeTaskModel() {
//
//    }

    /**
     * NOT TESTED: NOT USED
     */
//    void swapTaskModelOrder() {
//
//    }

    /**
     * NOT TESTED: NOT USED
     */
//    void moveTaskModelToPosition() {
//
//    }

    @Test
    /**
     * Test toString
     */
    void testToString() {
        List<TaskModel> sampleTaskModels1 = new ArrayList<TaskModel>();
        sampleTaskModels1.add(new TaskModel("t1 name", UUID.randomUUID(), "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskModels1.add(new TaskModel("t2 name", UUID.randomUUID(), "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskModels1.add(new TaskModel("t3 name", UUID.randomUUID(), "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnModel c = new ColumnModel("testing column name", sampleTaskModels1, UUID.randomUUID());

        String columnStringRepresentation = "[ColumnModel Name: testing column name, "
                + "TaskModels: {[TaskModel Name: t1 name, TaskModel Completed: false, Due Date: 2024-03-28T14:33:48], "
                + "[TaskModel Name: t2 name, TaskModel Completed: true, Due Date: 2023-02-18T11:10:05.000000002], "
                + "[TaskModel Name: t3 name, TaskModel Completed: false, Due Date: 1985-10-01T19:13:09.000000006]}]";

        Assertions.assertEquals(c.toString(), columnStringRepresentation);
    }


}