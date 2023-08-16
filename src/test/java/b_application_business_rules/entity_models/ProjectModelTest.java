package b_application_business_rules.entity_models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.entity_models.ColumnModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A class to test the ProjectModel entity.
 */
public class ProjectModelTest {


    @Test
    /**
     * Tests the getName method
     */
    void getName() {
        ProjectModel p = new ProjectModel("test proj", null, null, null);
        Assertions.assertEquals(p.getName(), "test proj");
    }

    @Test
    /**
     * Tests the setName method
     */
    void setName() {
        ProjectModel p = new ProjectModel("test proj", null, null, null);
        p.setName("new test proj");
        Assertions.assertEquals(p.getName(), "new test proj");
    }

    @Test
    /**
     * Tests the getID method
     */
    void getID() {
        UUID u = UUID.randomUUID();
        ProjectModel p = new ProjectModel(null, u, null, null);
        Assertions.assertEquals(p.getID(), u);
    }

    @Test
    /**
     * Tests the setID method
     */
    void setID() {
        UUID u = UUID.randomUUID();
        ProjectModel p = new ProjectModel(null, UUID.randomUUID(), null, null);
        p.setID(u);
        Assertions.assertEquals(p.getID(), u);
    }

    @Test
    /**
     * Tests the getDescription method
     */
    void getDescription() {
        ProjectModel p = new ProjectModel(null, null, "skippidybopboombadabada", null);
        Assertions.assertEquals(p.getDescription(), "skippidybopboombadabada");
    }

    @Test
    /**
     * Tests the setDescription method
     */
    void setDescription() {
        ProjectModel p = new ProjectModel(null, null, "skippidybopboombadabada", null);
        p.setDescription("LALALALALAskippidybopboombadabada");
        Assertions.assertEquals(p.getDescription(), "LALALALALAskippidybopboombadabada");
    }

    @Test
    /**
     * Tests getColumnModels
     */
    void getColumnModels() {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();

        UUID cu1 = UUID.randomUUID();
        UUID cu2 = UUID.randomUUID();
        UUID cu3 = UUID.randomUUID();

        TaskModel t1 = new TaskModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel t2 = new TaskModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel t3 = new TaskModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskModel> tasks1 = new ArrayList<>();
        tasks1.add(t1);
        tasks1.add(t2);
        tasks1.add(t3);

        TaskModel tt1 = new TaskModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel tt2 = new TaskModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel tt3 = new TaskModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskModel> tasks2 = new ArrayList<>();
        tasks2.add(tt1);
        tasks2.add(tt2);
        tasks2.add(tt3);

        TaskModel ttt1 = new TaskModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel ttt2 = new TaskModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel ttt3 = new TaskModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskModel> tasks3 = new ArrayList<>();
        tasks3.add(ttt1);
        tasks3.add(ttt2);
        tasks3.add(ttt3);

        ColumnModel c1 = new ColumnModel("test column", tasks1, cu1);
        ColumnModel c2 = new ColumnModel("test column", tasks2, cu2);
        ColumnModel c3 = new ColumnModel("test column", tasks3, cu3);

        ArrayList<ColumnModel> colResult = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);
        colResult.add(c3);

        ProjectModel p = new ProjectModel(null, null, null, colResult);

        Assertions.assertEquals(p.getColumnModels(), colResult);
    }

    @Test
    /**
     * Tests setColumnModels
     */
    void setColumnModels() {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();

        UUID cu1 = UUID.randomUUID();
        UUID cu2 = UUID.randomUUID();
        UUID cu3 = UUID.randomUUID();

        TaskModel t1 = new TaskModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel t2 = new TaskModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel t3 = new TaskModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskModel> tasks1 = new ArrayList<>();
        tasks1.add(t1);
        tasks1.add(t2);
        tasks1.add(t3);

        TaskModel tt1 = new TaskModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel tt2 = new TaskModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel tt3 = new TaskModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskModel> tasks2 = new ArrayList<>();
        tasks2.add(tt1);
        tasks2.add(tt2);
        tasks2.add(tt3);

        TaskModel ttt1 = new TaskModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel ttt2 = new TaskModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel ttt3 = new TaskModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskModel> tasks3 = new ArrayList<>();
        tasks3.add(ttt1);
        tasks3.add(ttt2);
        tasks3.add(ttt3);

        ColumnModel c1 = new ColumnModel("test column", tasks1, cu1);
        ColumnModel c2 = new ColumnModel("test column", tasks2, cu2);
        ColumnModel c3 = new ColumnModel("test column", tasks3, cu3);

        ArrayList<ColumnModel> colResult = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);
        colResult.add(c3);

        ArrayList<ColumnModel> input = new ArrayList<>();
        input.add(c1);
        input.add(c2);
        input.add(c3); // Add c3 to input

        ArrayList<ColumnModel> col = new ArrayList<>();
        col.add(c2); // Add c2 to col

        ProjectModel p = new ProjectModel(null, null, null, col);
        p.setColumnModels(input); // Set columns using input

        Assertions.assertEquals(p.getColumnModels(), colResult);
    }

    @Test
    /**
     * Tests addColumnModel
     */
    void addColumnModel() {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();

        UUID cu1 = UUID.randomUUID();
        UUID cu2 = UUID.randomUUID();
        UUID cu3 = UUID.randomUUID();

        TaskModel t1 = new TaskModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel t2 = new TaskModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel t3 = new TaskModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskModel> tasks1 = new ArrayList<>();
        tasks1.add(t1);
        tasks1.add(t2);
        tasks1.add(t3);

        TaskModel tt1 = new TaskModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel tt2 = new TaskModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel tt3 = new TaskModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskModel> tasks2 = new ArrayList<>();
        tasks2.add(tt1);
        tasks2.add(tt2);
        tasks2.add(tt3);

        TaskModel ttt1 = new TaskModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel ttt2 = new TaskModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel ttt3 = new TaskModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskModel> tasks3 = new ArrayList<>();
        tasks3.add(ttt1);
        tasks3.add(ttt2);
        tasks3.add(ttt3);

        ColumnModel c1 = new ColumnModel("test column", tasks1, cu1);
        ColumnModel c2 = new ColumnModel("test column", tasks2, cu2);
        ColumnModel c3 = new ColumnModel("test column", tasks3, cu3);

        ArrayList<ColumnModel> colResult = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);
        colResult.add(c3);

        ArrayList<ColumnModel> col = new ArrayList<>();
        col.add(c1);
        col.add(c2); // Add c2 to col

        ProjectModel p = new ProjectModel(null, null, null, col);
        p.addColumnModel(c3);

        Assertions.assertEquals(p.getColumnModels(), colResult);
    }

    /**
     * NOT TESTED: NOT USED
     */
//    @Test
//
//    void addColumnModelToPosition() {
//        UUID u1 = UUID.randomUUID();
//        UUID u2 = UUID.randomUUID();
//        UUID u3 = UUID.randomUUID();
//
//        UUID cu1 = UUID.randomUUID();
//        UUID cu2 = UUID.randomUUID();
//        UUID cu3 = UUID.randomUUID();
//
//        TaskModel t1 = new TaskModel("test task name", u1, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskModel t2 = new TaskModel("test task name", u2, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskModel t3 = new TaskModel("test task name", u3, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        ArrayList<TaskModel> tasks1 = new ArrayList<>();
//        tasks1.add(t1);
//        tasks1.add(t2);
//        tasks1.add(t3);
//
//        TaskModel tt1 = new TaskModel("test task name", u1, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskModel tt2 = new TaskModel("test task name", u2, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskModel tt3 = new TaskModel("test task name", u3, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        ArrayList<TaskModel> tasks2 = new ArrayList<>();
//        tasks2.add(tt1);
//        tasks2.add(tt2);
//        tasks2.add(tt3);
//
//        TaskModel ttt1 = new TaskModel("test task name", u1, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskModel ttt2 = new TaskModel("test task name", u2, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskModel ttt3 = new TaskModel("test task name", u3, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        ArrayList<TaskModel> tasks3 = new ArrayList<>();
//        tasks3.add(ttt1);
//        tasks3.add(ttt2);
//        tasks3.add(ttt3);
//
//        ColumnModel c1 = new ColumnModel("test column", tasks1, cu1);
//        ColumnModel c2 = new ColumnModel("test column", tasks2, cu2);
//        ColumnModel c3 = new ColumnModel("test column", tasks3, cu3);
//
//        ArrayList<ColumnModel> colResult = new ArrayList<>();
//        colResult.add(c1);
//        colResult.add(c2);
//        colResult.add(c3);
//
//        ArrayList<ColumnModel> col = new ArrayList<>();
//        col.add(c2); // Add c2 to col
//
//        ProjectModel p = new ProjectModel(null, null, null, col);
//        p.addColumnModelToPosition(c1, 0); // Add c1 to position 0
//        p.addColumnModelToPosition(c3, 1); // Add c3 to position 1
//
//        colResult.add(0, c1); // Add c1 at position 0
//        colResult.add(1, c3); // Add c3 at position 1
//
//        Assertions.assertEquals(p.getColumnModels(), colResult);
//    }

    /**
     * Tests testMoveColumnModelToPosition NOT USED
     */
//    void testMoveColumnModelToPosition() {
//        ColumnModelModel c1 = new ColumnModelModel(null, null, null);
//        ColumnModelModel c2 = new ColumnModelModel(null, null, null);
//        ColumnModelModel c3 = new ColumnModelModel(null, null, null);
//        ArrayList<ColumnModelModel> columns = new ArrayList<ColumnModelModel>();
//        columns.add(c1);
//        columns.add(c2);
//        columns.add(c3);
//
//        ProjectModel p = new ProjectModel(null, null, null, columns);
//
//        p.moveColumnModelToPosition(c3, 0);
//
//        ArrayList<ColumnModelModel> columns2 = new ArrayList<ColumnModelModel>();
//        columns2.add(c3);
//        columns2.add(c1);
//        columns2.add(c2);
//
//        Assertions.assertEquals(p.getColumnModels(), columns2);
//    }

    @Test
    /**
     * Tests removeColumnModel
     */
    void removeColumnModel() {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();

        UUID cu1 = UUID.randomUUID();
        UUID cu2 = UUID.randomUUID();
        UUID cu3 = UUID.randomUUID();


        TaskModel t1 = new TaskModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel t2 = new TaskModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel t3 = new TaskModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskModel> tasks1 = new ArrayList<TaskModel>();
        tasks1.add(t1);
        tasks1.add(t2);
        tasks1.add(t3);

        TaskModel tt1 = new TaskModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel tt2 = new TaskModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel tt3 = new TaskModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<TaskModel> tasks2 = new ArrayList<TaskModel>();
        tasks2.add(tt1);
        tasks2.add(tt2);
        tasks2.add(tt3);

        TaskModel ttt1 = new TaskModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel ttt2 = new TaskModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskModel ttt3 = new TaskModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<TaskModel> tasks3 = new ArrayList<TaskModel>();
        tasks3.add(ttt1);
        tasks3.add(ttt2);
        tasks3.add(ttt3);

        ColumnModel c1 = new ColumnModel("test column", tasks1, cu1);
        ColumnModel c2 = new ColumnModel("test column", tasks2, cu2);
        ColumnModel c3 = new ColumnModel("test column", tasks3, cu3);

        ArrayList<ColumnModel> col = new ArrayList<>();
        col.add(c1);
        col.add(c2);
        col.add(c3);

        ArrayList<ColumnModel> colResult = new ArrayList<ColumnModel>();
        colResult.add(c1);
        colResult.add(c3);

        ProjectModel p = new ProjectModel(null, null, null, col);
        p.removeColumnModel(c2);

        Assertions.assertEquals(p.getColumnModels(), colResult);
    }

    /**
     * Tests swapColumnModelOrder. NOT USED.
     */
//    void swapColumnModelOrder() {
//    }


}