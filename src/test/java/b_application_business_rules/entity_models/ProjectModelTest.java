package b_application_business_rules.entity_models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A class to test the ProjectModel entity.
 */
class ProjectModelTest {


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

        ColumnModel c1 = new ColumnModel("test ColumnModel", TaskModels1, cu1);
        ColumnModel c2 = new ColumnModel("test ColumnModel", TaskModels2, cu2);
        ColumnModel c3 = new ColumnModel("test ColumnModel", TaskModels3, cu3);

        ArrayList<ColumnModel> colResult = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);
        colResult.add(c3);

        ArrayList<ColumnModel> col = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);
        colResult.add(c3);

        ProjectModel p = new ProjectModel(null, null, null, col);


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

        ColumnModel c1 = new ColumnModel("test ColumnModel", TaskModels1, cu1);
        ColumnModel c2 = new ColumnModel("test ColumnModel", TaskModels2, cu2);
        ColumnModel c3 = new ColumnModel("test ColumnModel", TaskModels3, cu3);

        ArrayList<ColumnModel> colResult = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);
        colResult.add(c3);

        ArrayList<ColumnModel> input = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);
        colResult.add(c3);

        ArrayList<ColumnModel> col = new ArrayList<>();
        colResult.add(c2);

        ProjectModel p = new ProjectModel(null, null, null, col);
        p.setColumnModels(input);

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

        ColumnModel c1 = new ColumnModel("test ColumnModel", TaskModels1, cu1);
        ColumnModel c2 = new ColumnModel("test ColumnModel", TaskModels2, cu2);
        ColumnModel c3 = new ColumnModel("test ColumnModel", TaskModels3, cu3);

        ArrayList<ColumnModel> colResult = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);
        colResult.add(c3);

        ArrayList<ColumnModel> col = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);



        ProjectModel p = new ProjectModel(null, null, null, col);
        p.addColumnModel(c3);

        Assertions.assertEquals(p.getColumnModels(), colResult);
    }

    @Test
    /**
     * Tests addColumnModelToPosition
     */
    void addColumnModelToPosition() {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();

        UUID cu1 = UUID.randomUUID();
        UUID cu2 = UUID.randomUUID();
        UUID cu3 = UUID.randomUUID();


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

        ColumnModel c1 = new ColumnModel("test ColumnModel", TaskModels1, cu1);
        ColumnModel c2 = new ColumnModel("test ColumnModel", TaskModels2, cu2);
        ColumnModel c3 = new ColumnModel("test ColumnModel", TaskModels3, cu3);

        ArrayList<ColumnModel> colResult = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);
        colResult.add(c3);

        ArrayList<ColumnModel> col = new ArrayList<>();
        colResult.add(c2);



        ProjectModel p = new ProjectModel(null, null, null, col);
        p.addColumnModelToPosition(c1, 0);
        p.addColumnModelToPosition(c3, 2);


        Assertions.assertEquals(p.getColumnModels(), colResult);
    }

    /**
     * Tests testMoveColumnModelToPosition NOT USED
     */
//    void testMoveColumnModelToPosition() {
//        ColumnModelModel c1 = new ColumnModelModel(null, null, null);
//        ColumnModelModel c2 = new ColumnModelModel(null, null, null);
//        ColumnModelModel c3 = new ColumnModelModel(null, null, null);
//        ArrayList<ColumnModelModel> ColumnModels = new ArrayList<ColumnModelModel>();
//        ColumnModels.add(c1);
//        ColumnModels.add(c2);
//        ColumnModels.add(c3);
//
//        ProjectModel p = new ProjectModel(null, null, null, ColumnModels);
//
//        p.moveColumnModelToPosition(c3, 0);
//
//        ArrayList<ColumnModelModel> ColumnModels2 = new ArrayList<ColumnModelModel>();
//        ColumnModels2.add(c3);
//        ColumnModels2.add(c1);
//        ColumnModels2.add(c2);
//
//        Assertions.assertEquals(p.getColumnModels(), ColumnModels2);
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

        ColumnModel c1 = new ColumnModel("test ColumnModel", TaskModels1, cu1);
        ColumnModel c2 = new ColumnModel("test ColumnModel", TaskModels2, cu2);
        ColumnModel c3 = new ColumnModel("test ColumnModel", TaskModels3, cu3);

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
//        ColumnModelModel c1 = new ColumnModelModel(null, null, null);
//        ColumnModelModel c2 = new ColumnModelModel(null, null, null);
//        ArrayList<ColumnModelModel> ColumnModels = new ArrayList<ColumnModelModel>();
//        ColumnModels.add(c1);
//        ColumnModels.add(c2);
//
//        ArrayList<ColumnModelModel> ColumnModels2 = new ArrayList<ColumnModelModel>();
//        ColumnModels.add(c2);
//        ColumnModels.add(c1);
//
//        ProjectModel p = new ProjectModel(null, null, null, ColumnModels);
//        p.swapColumnModelOrder(c2.getColumnModelEntity(), c1.getColumnModelEntity());
//
//        Assertions.assertEquals(p.getColumnModels(), ColumnModels2);
//    }

    @Test
    /**
     * Tests equals
     */
    void testEquals() {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();

        UUID cu1 = UUID.randomUUID();
        UUID cu2 = UUID.randomUUID();
        UUID cu3 = UUID.randomUUID();

        UUID pu = UUID.randomUUID();
        String name = "Test ProjectModel";
        String description = "Description";

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

        ColumnModel c1 = new ColumnModel("test ColumnModel", TaskModels1, cu1);
        ColumnModel c2 = new ColumnModel("test ColumnModel", TaskModels2, cu2);
        ColumnModel c3 = new ColumnModel("test ColumnModel", TaskModels3, cu3);

        ArrayList<ColumnModel> col = new ArrayList<>();
        col.add(c1);
        col.add(c2);
        col.add(c3);

        ProjectModel p1 = new ProjectModel(name, pu, description, col);
        ProjectModel p2 = new ProjectModel(name, pu, description, col);
        ProjectModel p3 = new ProjectModel(name, pu, description, col);

        // Reflexive Property
        Assertions.assertEquals(p1, p1);

        // Symmetric Property
        Assertions.assertEquals(p1, p2);
        Assertions.assertEquals(p2, p1);

        // Transitive Property
        if (p1.equals(p2) && p2.equals(p3)) {
            Assertions.assertEquals(p1, p3);
        }
    }
}