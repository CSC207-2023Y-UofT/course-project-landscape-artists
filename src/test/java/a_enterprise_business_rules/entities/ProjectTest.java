package a_enterprise_business_rules.entities;

import java.util.ArrayList;
import java.util.UUID;

import a_enterprise_business_rules.entities.entities.Column;
import a_enterprise_business_rules.entities.entities.Project;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A class to test the Project entity.
 */
class ProjectTest {

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
        Column c1 = new Column(null, null, null);
        Column c2 = new Column(null, null, null);
        Column c3 = new Column(null, null, null);
        ArrayList<Column> columns = new ArrayList<Column>();
        columns.add(c1);
        columns.add(c2);
        columns.add(c3);

        Project p = new Project(null, null, null, columns);

        ArrayList<Column> columns2 = new ArrayList<Column>();
        columns2.add(c1);
        columns2.add(c2);
        columns2.add(c3);

        Assertions.assertEquals(p.getColumns(), columns2);
    }

    @Test
    /**
     * Tests setColumns
     */
    void setColumns() {
        Column c1 = new Column(null, null, null);
        Column c2 = new Column(null, null, null);
        Column c3 = new Column(null, null, null);
        ArrayList<Column> columns = new ArrayList<Column>();
        columns.add(c1);
        columns.add(c2);
        columns.add(c3);

        Project p = new Project(null, null, null, columns);

        ArrayList<Column> columns2 = new ArrayList<Column>();
        columns2.add(c1);
        columns2.add(c3);
        columns2.add(c1);
        p.setColumns(columns2);

        Assertions.assertEquals(p.getColumns(), columns2);
    }

    @Test
    /**
     * Tests addColumn
     */
    void addColumn() {
        Column c1 = new Column(null, null, null);
        Column c2 = new Column(null, null, null);
        Column c3 = new Column(null, null, null);
        Column c4 = new Column(null, null, null);
        ArrayList<Column> columns = new ArrayList<Column>();
        columns.add(c1);
        columns.add(c2);
        columns.add(c3);

        ArrayList<Column> columns2 = new ArrayList<Column>();
        columns.add(c1);
        columns.add(c2);
        columns.add(c3);
        columns.add(c4);

        Project p = new Project(null, null, null, columns);
        p.addColumn(c4);

        Assertions.assertEquals(p.getColumns(), columns2);
    }

    @Test
    /**
     * Tests addColumnToPosition
     */
    void addColumnToPosition() {
        Column c1 = new Column(null, null, null);
        Column c2 = new Column(null, null, null);
        Column c3 = new Column(null, null, null);
        Column c4 = new Column(null, null, null);
        ArrayList<Column> columns = new ArrayList<Column>();
        columns.add(c1);
        columns.add(c2);
        columns.add(c3);

        ArrayList<Column> columns2 = new ArrayList<Column>();
        columns.add(c4);
        columns.add(c1);
        columns.add(c2);
        columns.add(c3);

        Project p = new Project(null, null, null, columns);
        p.addColumnToPosition(c4, 0);

        Assertions.assertEquals(p.getColumns(), columns2);
    }

    @Test
    /**
     * Tests testMoveColumnToPosition
     */
    void testMoveColumnToPosition() {
        Column c1 = new Column(null, null, null);
        Column c2 = new Column(null, null, null);
        Column c3 = new Column(null, null, null);
        ArrayList<Column> columns = new ArrayList<Column>();
        columns.add(c1);
        columns.add(c2);
        columns.add(c3);

        Project p = new Project(null, null, null, columns);

        p.moveColumnToPosition(c3, 0);

        ArrayList<Column> columns2 = new ArrayList<Column>();
        columns2.add(c3);
        columns2.add(c1);
        columns2.add(c2);

        Assertions.assertEquals(p.getColumns(), columns2);
    }

    @Test
    /**
     * Tests removeColumn
     */
    void removeColumn() {
        Column c1 = new Column(null, null, null);
        Column c2 = new Column(null, null, null);
        ArrayList<Column> columns = new ArrayList<Column>();
        columns.add(c1);
        columns.add(c2);

        ArrayList<Column> columns2 = new ArrayList<Column>();
        columns.add(c1);

        Project p = new Project(null, null, null, columns);
        p.removeColumn(c2);

        Assertions.assertEquals(p.getColumns(), columns2);
    }

    @Test
    /**
     * Tests swapColumnOrder
     */
    void swapColumnOrder() {
        Column c1 = new Column(null, null, null);
        Column c2 = new Column(null, null, null);
        ArrayList<Column> columns = new ArrayList<Column>();
        columns.add(c1);
        columns.add(c2);

        ArrayList<Column> columns2 = new ArrayList<Column>();
        columns.add(c2);
        columns.add(c1);

        Project p = new Project(null, null, null, columns);
        p.swapColumnOrder(c2, c1);

        Assertions.assertEquals(p.getColumns(), columns2);
    }

    @Test
    /**
     * Tests equals
     */
    void testEquals() {
        Project p1 = new Project(null, null, null, null);
        Project p2 = new Project(null, null, null, null);
        Project p3 = new Project(null, null, null, null);

        // Reflexive Property
        Assertions.assertTrue(
                p1.equals(p1));

        // Symmetric Property
        Assertions.assertTrue(
                p1.equals(p2));
        Assertions.assertTrue(
                p2.equals(p1));

        // Transitive Property
        if (p1.equals(p2) && p2.equals(p3)) {
            Assertions.assertTrue(p1.equals(p3));
        }
    }
}