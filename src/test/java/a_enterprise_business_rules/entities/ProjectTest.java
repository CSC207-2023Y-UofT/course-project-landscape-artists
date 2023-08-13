package a_enterprise_business_rules.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A class to test the Project entity.
 */
public class ProjectTest {


    @Test
    /**
     * Tests the getName method
     */
    void getName() {
        Project p = new Project("test proj", null, null, null);
        Assertions.assertEquals(p.getName(), "test proj");
    }

    @Test
    /**
     * Tests the setName method
     */
    void setName() {
        Project p = new Project("test proj", null, null, null);
        p.setName("new test proj");
        Assertions.assertEquals(p.getName(), "new test proj");
    }

    @Test
    /**
     * Tests the getID method
     */
    void getID() {
        UUID u = UUID.randomUUID();
        Project p = new Project(null, u, null, null);
        Assertions.assertEquals(p.getID(), u);
    }

    @Test
    /**
     * Tests the setID method
     */
    void setID() {
        UUID u = UUID.randomUUID();
        Project p = new Project(null, UUID.randomUUID(), null, null);
        p.setID(u);
        Assertions.assertEquals(p.getID(), u);
    }

    @Test
    /**
     * Tests the getDescription method
     */
    void getDescription() {
        Project p = new Project(null, null, "skippidybopboombadabada", null);
        Assertions.assertEquals(p.getDescription(), "skippidybopboombadabada");
    }

    @Test
    /**
     * Tests the setDescription method
     */
    void setDescription() {
        Project p = new Project(null, null, "skippidybopboombadabada", null);
        p.setDescription("LALALALALAskippidybopboombadabada");
        Assertions.assertEquals(p.getDescription(), "LALALALALAskippidybopboombadabada");
    }

    @Test
    /**
     * Tests getColumns
     */
    void getColumns() {
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
        ArrayList<Task> tasks1 = new ArrayList<>();
        tasks1.add(t1);
        tasks1.add(t2);
        tasks1.add(t3);

        Task tt1 = new Task("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task tt2 = new Task("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task tt3 = new Task("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<Task> tasks2 = new ArrayList<>();
        tasks2.add(tt1);
        tasks2.add(tt2);
        tasks2.add(tt3);

        Task ttt1 = new Task("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task ttt2 = new Task("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task ttt3 = new Task("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<Task> tasks3 = new ArrayList<>();
        tasks3.add(ttt1);
        tasks3.add(ttt2);
        tasks3.add(ttt3);

        Column c1 = new Column("test column", tasks1, cu1);
        Column c2 = new Column("test column", tasks2, cu2);
        Column c3 = new Column("test column", tasks3, cu3);

        ArrayList<Column> colResult = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);
        colResult.add(c3);

        Project p = new Project(null, null, null, colResult);

        Assertions.assertEquals(p.getColumns(), colResult);
    }

    @Test
    /**
     * Tests setColumns
     */
    void setColumns() {
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
        ArrayList<Task> tasks1 = new ArrayList<>();
        tasks1.add(t1);
        tasks1.add(t2);
        tasks1.add(t3);

        Task tt1 = new Task("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task tt2 = new Task("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task tt3 = new Task("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<Task> tasks2 = new ArrayList<>();
        tasks2.add(tt1);
        tasks2.add(tt2);
        tasks2.add(tt3);

        Task ttt1 = new Task("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task ttt2 = new Task("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task ttt3 = new Task("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<Task> tasks3 = new ArrayList<>();
        tasks3.add(ttt1);
        tasks3.add(ttt2);
        tasks3.add(ttt3);

        Column c1 = new Column("test column", tasks1, cu1);
        Column c2 = new Column("test column", tasks2, cu2);
        Column c3 = new Column("test column", tasks3, cu3);

        ArrayList<Column> colResult = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);
        colResult.add(c3);

        ArrayList<Column> input = new ArrayList<>();
        input.add(c1);
        input.add(c2);
        input.add(c3); // Add c3 to input

        ArrayList<Column> col = new ArrayList<>();
        col.add(c2); // Add c2 to col

        Project p = new Project(null, null, null, col);
        p.setColumns(input); // Set columns using input

        Assertions.assertEquals(p.getColumns(), colResult);
    }

    @Test
    /**
     * Tests addColumn
     */
    void addColumn() {
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
        ArrayList<Task> tasks1 = new ArrayList<>();
        tasks1.add(t1);
        tasks1.add(t2);
        tasks1.add(t3);

        Task tt1 = new Task("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task tt2 = new Task("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task tt3 = new Task("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<Task> tasks2 = new ArrayList<>();
        tasks2.add(tt1);
        tasks2.add(tt2);
        tasks2.add(tt3);

        Task ttt1 = new Task("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task ttt2 = new Task("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Task ttt3 = new Task("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<Task> tasks3 = new ArrayList<>();
        tasks3.add(ttt1);
        tasks3.add(ttt2);
        tasks3.add(ttt3);

        Column c1 = new Column("test column", tasks1, cu1);
        Column c2 = new Column("test column", tasks2, cu2);
        Column c3 = new Column("test column", tasks3, cu3);

        ArrayList<Column> colResult = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);
        colResult.add(c3);

        ArrayList<Column> col = new ArrayList<>();
        col.add(c1);
        col.add(c2); // Add c2 to col

        Project p = new Project(null, null, null, col);
        p.addColumn(c3);

        Assertions.assertEquals(p.getColumns(), colResult);
    }

    /**
     * NOT TESTED: NOT USED
     */
//    @Test
//
//    void addColumnToPosition() {
//        UUID u1 = UUID.randomUUID();
//        UUID u2 = UUID.randomUUID();
//        UUID u3 = UUID.randomUUID();
//
//        UUID cu1 = UUID.randomUUID();
//        UUID cu2 = UUID.randomUUID();
//        UUID cu3 = UUID.randomUUID();
//
//        Task t1 = new Task("test task name", u1, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        Task t2 = new Task("test task name", u2, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        Task t3 = new Task("test task name", u3, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        ArrayList<Task> tasks1 = new ArrayList<>();
//        tasks1.add(t1);
//        tasks1.add(t2);
//        tasks1.add(t3);
//
//        Task tt1 = new Task("test task name", u1, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        Task tt2 = new Task("test task name", u2, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        Task tt3 = new Task("test task name", u3, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        ArrayList<Task> tasks2 = new ArrayList<>();
//        tasks2.add(tt1);
//        tasks2.add(tt2);
//        tasks2.add(tt3);
//
//        Task ttt1 = new Task("test task name", u1, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        Task ttt2 = new Task("test task name", u2, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        Task ttt3 = new Task("test task name", u3, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        ArrayList<Task> tasks3 = new ArrayList<>();
//        tasks3.add(ttt1);
//        tasks3.add(ttt2);
//        tasks3.add(ttt3);
//
//        Column c1 = new Column("test column", tasks1, cu1);
//        Column c2 = new Column("test column", tasks2, cu2);
//        Column c3 = new Column("test column", tasks3, cu3);
//
//        ArrayList<Column> colResult = new ArrayList<>();
//        colResult.add(c1);
//        colResult.add(c2);
//        colResult.add(c3);
//
//        ArrayList<Column> col = new ArrayList<>();
//        col.add(c2); // Add c2 to col
//
//        Project p = new Project(null, null, null, col);
//        p.addColumnToPosition(c1, 0); // Add c1 to position 0
//        p.addColumnToPosition(c3, 1); // Add c3 to position 1
//
//        colResult.add(0, c1); // Add c1 at position 0
//        colResult.add(1, c3); // Add c3 at position 1
//
//        Assertions.assertEquals(p.getColumns(), colResult);
//    }

    /**
     * Tests testMoveColumnToPosition NOT USED
     */
//    void testMoveColumnToPosition() {
//        ColumnModel c1 = new ColumnModel(null, null, null);
//        ColumnModel c2 = new ColumnModel(null, null, null);
//        ColumnModel c3 = new ColumnModel(null, null, null);
//        ArrayList<ColumnModel> columns = new ArrayList<ColumnModel>();
//        columns.add(c1);
//        columns.add(c2);
//        columns.add(c3);
//
//        Project p = new Project(null, null, null, columns);
//
//        p.moveColumnToPosition(c3, 0);
//
//        ArrayList<ColumnModel> columns2 = new ArrayList<ColumnModel>();
//        columns2.add(c3);
//        columns2.add(c1);
//        columns2.add(c2);
//
//        Assertions.assertEquals(p.getColumns(), columns2);
//    }

    @Test
    /**
     * Tests removeColumn
     */
    void removeColumn() {
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

        ArrayList<Column> colResult = new ArrayList<Column>();
        colResult.add(c1);
        colResult.add(c3);

        Project p = new Project(null, null, null, col);
        p.removeColumn(c2);

        Assertions.assertEquals(p.getColumns(), colResult);
    }

    /**
     * Tests swapColumnOrder. NOT USED.
     */
//    void swapColumnOrder() {
//        ColumnModel c1 = new ColumnModel(null, null, null);
//        ColumnModel c2 = new ColumnModel(null, null, null);
//        ArrayList<ColumnModel> columns = new ArrayList<ColumnModel>();
//        columns.add(c1);
//        columns.add(c2);
//
//        ArrayList<ColumnModel> columns2 = new ArrayList<ColumnModel>();
//        columns.add(c2);
//        columns.add(c1);
//
//        Project p = new Project(null, null, null, columns);
//        p.swapColumnOrder(c2.getColumnEntity(), c1.getColumnEntity());
//
//        Assertions.assertEquals(p.getColumns(), columns2);
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
        String name = "Test project";
        String description = "Description";

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

        Project p1 = new Project(name, pu, description, col);
        Project p2 = new Project(name, pu, description, col);
        Project p3 = new Project(name, pu, description, col);

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