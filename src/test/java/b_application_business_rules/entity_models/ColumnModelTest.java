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
class ColumnModelTest {

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
        ColumnModel c = new ColumnModel("testing ColumnModel name", sampleTaskModels1, UUID.randomUUID());

        Assertions.assertEquals(c.getName(), "testing ColumnModel name");
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
        ColumnModel c = new ColumnModel("testing ColumnModel name", sampleTaskModels1, UUID.randomUUID());

        c.setName("new test ColumnModel name");

        Assertions.assertEquals(c.getName(), "new test ColumnModel name");
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
        ColumnModel c = new ColumnModel("testing ColumnModel name", sampleTaskModels1, currID);

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
        ColumnModel c = new ColumnModel("testing ColumnModel name", sampleTaskModels1, UUID.randomUUID());

        UUID currID = UUID.randomUUID();

        Assertions.assertEquals(c.getID(), currID);
    }

    @Test
    /**
     * Tests getTaskModels
     */
    void getTaskModels() {
        List<TaskModel> sampleTaskModels1 = new ArrayList<TaskModel>();
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        sampleTaskModels1.add(new TaskModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskModels1.add(new TaskModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskModels1.add(new TaskModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnModel c = new ColumnModel("testing ColumnModel name", sampleTaskModels1, UUID.randomUUID());

        List<TaskModel> sampleTaskModels2 = new ArrayList<TaskModel>();
        sampleTaskModels2.add(new TaskModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskModels2.add(new TaskModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskModels2.add(new TaskModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));

        Assertions.assertEquals(c.getTaskModels(), sampleTaskModels2);
    }

    @Test
    /**
     * Tests setTaskModels
     */
    void setTaskModels() {
        List<TaskModel> sampleTaskModels1 = new ArrayList<TaskModel>();
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        sampleTaskModels1.add(new TaskModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskModels1.add(new TaskModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskModels1.add(new TaskModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnModel c = new ColumnModel("testing ColumnModel name", sampleTaskModels1, UUID.randomUUID());

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

        List<TaskModel> sampleTaskModels3 = new ArrayList<TaskModel>();
        sampleTaskModels3.add(new TaskModel("new t1 name", newu1, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskModels3.add(new TaskModel("new t2 name", newu2, "new t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskModels3.add(new TaskModel("new t3 name", newu3, "new t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));

        Assertions.assertEquals(c.getTaskModels(), sampleTaskModels3);
    }

    @Test
    /**
     * Tests addTaskModel
     */
    void addTaskModel() {
        List<TaskModel> sampleTaskModels1 = new ArrayList<TaskModel>();
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        sampleTaskModels1.add(new TaskModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskModels1.add(new TaskModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskModels1.add(new TaskModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnModel c = new ColumnModel("testing ColumnModel name", sampleTaskModels1, UUID.randomUUID());

        UUID newUUID = UUID.randomUUID();

        TaskModel newTaskModel = new TaskModel("new t1 name", newUUID, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel newTaskModelCopy = new TaskModel("new t1 name", newUUID, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        c.addTaskModel(newTaskModel);

        List<TaskModel> sampleTaskModels2 = new ArrayList<TaskModel>();
        sampleTaskModels2.add(new TaskModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskModels2.add(new TaskModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskModels2.add(new TaskModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        sampleTaskModels2.add(newTaskModelCopy);

        Assertions.assertEquals(c.getTaskModels(), sampleTaskModels2);
    }

    @Test
    /**
     * Tests addTaskModelToPosition
     */
    void addTaskModelToPosition() {
        List<TaskModel> sampleTaskModels1 = new ArrayList<TaskModel>();
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        sampleTaskModels1.add(new TaskModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskModels1.add(new TaskModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskModels1.add(new TaskModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnModel c = new ColumnModel("testing ColumnModel name", sampleTaskModels1, UUID.randomUUID());

        UUID newUUID = UUID.randomUUID();

        TaskModel newTaskModel = new TaskModel("new t1 name", newUUID, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel newTaskModelCopy = new TaskModel("new t1 name", newUUID, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        c.addTaskModelToPosition(newTaskModel, 0);

        List<TaskModel> sampleTaskModels2 = new ArrayList<TaskModel>();
        sampleTaskModels2.add(new TaskModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskModels2.add(new TaskModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskModels2.add(new TaskModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        sampleTaskModels2.add(0, newTaskModelCopy);

        Assertions.assertEquals(c.getTaskModels(), sampleTaskModels2);
    }

    @Test
    /**
     * Tests removeTaskModel
     */
    void removeTaskModel() {
        List<TaskModel> sampleTaskModels1 = new ArrayList<TaskModel>();
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        TaskModel TaskModelToRemove = new TaskModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2));
        sampleTaskModels1.add(new TaskModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskModels1.add(TaskModelToRemove);
        sampleTaskModels1.add(new TaskModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnModel c = new ColumnModel("testing ColumnModel name", sampleTaskModels1, UUID.randomUUID());

        c.removeTaskModel(TaskModelToRemove);

        List<TaskModel> sampleTaskModels2 = new ArrayList<TaskModel>();
        sampleTaskModels2.add(new TaskModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskModels2.add(new TaskModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));

        Assertions.assertEquals(c.getTaskModels(), sampleTaskModels2);
    }

    @Test
    /**
     * Tests swapTaskModelOrder
     */
    void swapTaskModelOrder() {
        List<TaskModel> sampleTaskModels1 = new ArrayList<TaskModel>();
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        TaskModel TaskModel1 = new TaskModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel TaskModel2 = new TaskModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2));
        sampleTaskModels1.add(TaskModel1);
        sampleTaskModels1.add(TaskModel2);
        sampleTaskModels1.add(new TaskModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnModel c = new ColumnModel("testing ColumnModel name", sampleTaskModels1, UUID.randomUUID());

        c.swapTaskModelOrder(TaskModel1, TaskModel2);

        List<TaskModel> sampleTaskModels2 = new ArrayList<TaskModel>();
        sampleTaskModels2.add(TaskModel2);
        sampleTaskModels2.add(TaskModel1);
        sampleTaskModels2.add(new TaskModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));

        Assertions.assertEquals(c.getTaskModels(), sampleTaskModels2);
    }

    @Test
    /**
     * Tests moveTaskModelToPosition
     */
    void moveTaskModelToPosition() {
        List<TaskModel> sampleTaskModels1 = new ArrayList<TaskModel>();
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        TaskModel t1 = new TaskModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        sampleTaskModels1.add(t1);
        sampleTaskModels1.add(new TaskModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskModels1.add(new TaskModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnModel c = new ColumnModel("testing ColumnModel name", sampleTaskModels1, UUID.randomUUID());

        c.moveTaskModelToPosition(t1, 2);

        TaskModel t1Dupe = new TaskModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        List<TaskModel> sampleTaskModels2 = new ArrayList<TaskModel>();
        sampleTaskModels2.add(new TaskModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskModels2.add(new TaskModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        sampleTaskModels2.add(t1Dupe);

        Assertions.assertEquals(c.getTaskModels(), sampleTaskModels2);
    }

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
        ColumnModel c = new ColumnModel("testing ColumnModel name", sampleTaskModels1, UUID.randomUUID());

        String ColumnModelStringRepresentation = "[ColumnModel Name: testing ColumnModel name, "
                + "TaskModels: {[TaskModel Name: t1 name, TaskModel Completed: false, Due Date: 2024-03-28T14:33:48], "
                + "[TaskModel Name: t2 name, TaskModel Completed: true, Due Date: 2023-02-18T11:10:05.000000002], "
                + "[TaskModel Name: t3 name, TaskModel Completed: false, Due Date: 1985-10-01T19:13:09.000000006]}]";

        Assertions.assertEquals(c.toString(), ColumnModelStringRepresentation);
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
        TaskModel t1 = new TaskModel("test TaskModel name", u1, "test TaskModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel t2 = new TaskModel("test TaskModel name", u2, "test TaskModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel t3 = new TaskModel("test TaskModel name", u3, "test TaskModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskModel> TaskModels1 = new ArrayList<TaskModel>();
        TaskModels1.add(t1);
        TaskModels1.add(t2);
        TaskModels1.add(t3);

        TaskModel tt1 = new TaskModel("test TaskModel name", u1, "test TaskModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel tt2 = new TaskModel("test TaskModel name", u2, "test TaskModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel tt3 = new TaskModel("test TaskModel name", u3, "test TaskModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<TaskModel> TaskModels2 = new ArrayList<TaskModel>();
        TaskModels2.add(tt1);
        TaskModels2.add(tt2);
        TaskModels2.add(tt3);

        TaskModel ttt1 = new TaskModel("test TaskModel name", u1, "test TaskModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel ttt2 = new TaskModel("test TaskModel name", u2, "test TaskModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel ttt3 = new TaskModel("test TaskModel name", u3, "test TaskModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<TaskModel> TaskModels3 = new ArrayList<TaskModel>();
        TaskModels3.add(ttt1);
        TaskModels3.add(ttt2);
        TaskModels3.add(ttt3);

        ColumnModel c1 = new ColumnModel("test ColumnModel", TaskModels1, cu);
        ColumnModel c2 = new ColumnModel("test ColumnModel", TaskModels2, cu);
        ColumnModel c3 = new ColumnModel("test ColumnModel", TaskModels3, cu);

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



    /**
     * Tests IDToColumnModel NOT USED
     */
//    void TestIDToColumnModel() {
//        UUID u1 = UUID.randomUUID();
//        UUID u2 = UUID.randomUUID();
//        UUID u3 = UUID.randomUUID();
//
//        UUID cu1 = UUID.randomUUID();
//        UUID cu2 = UUID.randomUUID();
//        UUID cu3 = UUID.randomUUID();
//
//
//        TaskModel t1 = new TaskModel("test TaskModel name", u1, "test TaskModel description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskModel t2 = new TaskModel("test TaskModel name", u2, "test TaskModel description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskModel t3 = new TaskModel("test TaskModel name", u3, "test TaskModel description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        ArrayList<TaskModel> TaskModels1 = new ArrayList<TaskModel>();
//        TaskModels1.add(t1);
//        TaskModels1.add(t2);
//        TaskModels1.add(t3);
//
//        TaskModel tt1 = new TaskModel("test TaskModel name", u1, "test TaskModel description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskModel tt2 = new TaskModel("test TaskModel name", u2, "test TaskModel description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskModel tt3 = new TaskModel("test TaskModel name", u3, "test TaskModel description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//
//        ArrayList<TaskModel> TaskModels2 = new ArrayList<TaskModel>();
//        TaskModels2.add(tt1);
//        TaskModels2.add(tt2);
//        TaskModels2.add(tt3);
//
//        TaskModel ttt1 = new TaskModel("test TaskModel name", u1, "test TaskModel description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskModel ttt2 = new TaskModel("test TaskModel name", u2, "test TaskModel description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskModel ttt3 = new TaskModel("test TaskModel name", u3, "test TaskModel description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//
//        ArrayList<TaskModel> TaskModels3 = new ArrayList<TaskModel>();
//        TaskModels3.add(ttt1);
//        TaskModels3.add(ttt2);
//        TaskModels3.add(ttt3);
//
//        ColumnModel c1 = new ColumnModel("test ColumnModel", TaskModels1, cu1);
//        ColumnModel c2 = new ColumnModel("test ColumnModel", TaskModels2, cu2);
//        ColumnModel c3 = new ColumnModel("test ColumnModel", TaskModels3, cu3);
//
//        ArrayList<ColumnModel> col = new ArrayList<>();
//        col.add(c1);
//        col.add(c2);
//        col.add(c3);
//
//        ColumnModel output = ColumnModel.IDToColumnModel(cu2, col);
//        Assertions.assertEquals(c2, output);
//
//    }

    /**
     * Tests getColumnEntity NOT USED
     */
//    void getColumnEntity(){
//
//    }
}