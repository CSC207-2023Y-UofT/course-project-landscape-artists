package c_interface_adapters.view_models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A class to test the ProjectViewModel entity.
 */
public class ProjectViewModelTest {


    @Test
    /**
     * Tests the getName method
     */
    void getName() {
        ProjectViewModel p = new ProjectViewModel("test proj", null, null, null);
        Assertions.assertEquals(p.getName(), "test proj");
    }

    @Test
    /**
     * Tests the setName method
     */
    void setName() {
        ProjectViewModel p = new ProjectViewModel("test proj", null, null, null);
        p.setName("new test proj");
        Assertions.assertEquals(p.getName(), "new test proj");
    }

    @Test
    /**
     * Tests the getID method
     */
    void getID() {
        UUID u = UUID.randomUUID();
        ProjectViewModel p = new ProjectViewModel(null, u, null, null);
        Assertions.assertEquals(p.getID(), u);
    }

    @Test
    /**
     * Tests the setID method
     */
    void setID() {
        UUID u = UUID.randomUUID();
        ProjectViewModel p = new ProjectViewModel(null, UUID.randomUUID(), null, null);
        p.setID(u);
        Assertions.assertEquals(p.getID(), u);
    }

    @Test
    /**
     * Tests the getDescription method
     */
    void getDescription() {
        ProjectViewModel p = new ProjectViewModel(null, null, "skippidybopboombadabada", null);
        Assertions.assertEquals(p.getDescription(), "skippidybopboombadabada");
    }

    @Test
    /**
     * Tests the setDescription method
     */
    void setDescription() {
        ProjectViewModel p = new ProjectViewModel(null, null, "skippidybopboombadabada", null);
        p.setDescription("LALALALALAskippidybopboombadabada");
        Assertions.assertEquals(p.getDescription(), "LALALALALAskippidybopboombadabada");
    }

    @Test
    /**
     * Tests getColumnViewModels
     */
    void getColumnViewModels() {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();

        UUID cu1 = UUID.randomUUID();
        UUID cu2 = UUID.randomUUID();
        UUID cu3 = UUID.randomUUID();

        TaskViewModel t1 = new TaskViewModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel t2 = new TaskViewModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel t3 = new TaskViewModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskViewModel> tasks1 = new ArrayList<>();
        tasks1.add(t1);
        tasks1.add(t2);
        tasks1.add(t3);

        TaskViewModel tt1 = new TaskViewModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel tt2 = new TaskViewModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel tt3 = new TaskViewModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskViewModel> tasks2 = new ArrayList<>();
        tasks2.add(tt1);
        tasks2.add(tt2);
        tasks2.add(tt3);

        TaskViewModel ttt1 = new TaskViewModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel ttt2 = new TaskViewModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel ttt3 = new TaskViewModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskViewModel> tasks3 = new ArrayList<>();
        tasks3.add(ttt1);
        tasks3.add(ttt2);
        tasks3.add(ttt3);

        ColumnViewModel c1 = new ColumnViewModel("test column", tasks1, cu1);
        ColumnViewModel c2 = new ColumnViewModel("test column", tasks2, cu2);
        ColumnViewModel c3 = new ColumnViewModel("test column", tasks3, cu3);

        ArrayList<ColumnViewModel> colResult = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);
        colResult.add(c3);

        ProjectViewModel p = new ProjectViewModel(null, null, null, colResult);

        Assertions.assertEquals(p.getColumnViewModels(), colResult);
    }

    @Test
    /**
     * Tests setColumnViewModels
     */
    void setColumnViewModels() {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();

        UUID cu1 = UUID.randomUUID();
        UUID cu2 = UUID.randomUUID();
        UUID cu3 = UUID.randomUUID();

        TaskViewModel t1 = new TaskViewModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel t2 = new TaskViewModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel t3 = new TaskViewModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskViewModel> tasks1 = new ArrayList<>();
        tasks1.add(t1);
        tasks1.add(t2);
        tasks1.add(t3);

        TaskViewModel tt1 = new TaskViewModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel tt2 = new TaskViewModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel tt3 = new TaskViewModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskViewModel> tasks2 = new ArrayList<>();
        tasks2.add(tt1);
        tasks2.add(tt2);
        tasks2.add(tt3);

        TaskViewModel ttt1 = new TaskViewModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel ttt2 = new TaskViewModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel ttt3 = new TaskViewModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskViewModel> tasks3 = new ArrayList<>();
        tasks3.add(ttt1);
        tasks3.add(ttt2);
        tasks3.add(ttt3);

        ColumnViewModel c1 = new ColumnViewModel("test column", tasks1, cu1);
        ColumnViewModel c2 = new ColumnViewModel("test column", tasks2, cu2);
        ColumnViewModel c3 = new ColumnViewModel("test column", tasks3, cu3);

        ArrayList<ColumnViewModel> colResult = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);
        colResult.add(c3);

        ArrayList<ColumnViewModel> input = new ArrayList<>();
        input.add(c1);
        input.add(c2);
        input.add(c3); // Add c3 to input

        ArrayList<ColumnViewModel> col = new ArrayList<>();
        col.add(c2); // Add c2 to col

        ProjectViewModel p = new ProjectViewModel(null, null, null, col);
        p.setColumnViewModels(input); // Set columns using input

        Assertions.assertEquals(p.getColumnViewModels(), colResult);
    }

    @Test
    /**
     * Tests addColumnViewModel
     */
    void addColumnViewModel() {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();

        UUID cu1 = UUID.randomUUID();
        UUID cu2 = UUID.randomUUID();
        UUID cu3 = UUID.randomUUID();

        TaskViewModel t1 = new TaskViewModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel t2 = new TaskViewModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel t3 = new TaskViewModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskViewModel> tasks1 = new ArrayList<>();
        tasks1.add(t1);
        tasks1.add(t2);
        tasks1.add(t3);

        TaskViewModel tt1 = new TaskViewModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel tt2 = new TaskViewModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel tt3 = new TaskViewModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskViewModel> tasks2 = new ArrayList<>();
        tasks2.add(tt1);
        tasks2.add(tt2);
        tasks2.add(tt3);

        TaskViewModel ttt1 = new TaskViewModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel ttt2 = new TaskViewModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel ttt3 = new TaskViewModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskViewModel> tasks3 = new ArrayList<>();
        tasks3.add(ttt1);
        tasks3.add(ttt2);
        tasks3.add(ttt3);

        ColumnViewModel c1 = new ColumnViewModel("test column", tasks1, cu1);
        ColumnViewModel c2 = new ColumnViewModel("test column", tasks2, cu2);
        ColumnViewModel c3 = new ColumnViewModel("test column", tasks3, cu3);

        ArrayList<ColumnViewModel> colResult = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);
        colResult.add(c3);

        ArrayList<ColumnViewModel> col = new ArrayList<>();
        col.add(c1);
        col.add(c2); // Add c2 to col

        ProjectViewModel p = new ProjectViewModel(null, null, null, col);
        p.addColumnViewModel(c3);

        Assertions.assertEquals(p.getColumnViewModels(), colResult);
    }

    /**
     * NOT TESTED: NOT USED
     */
//    @Test
//
//    void addColumnViewModelToPosition() {
//        UUID u1 = UUID.randomUUID();
//        UUID u2 = UUID.randomUUID();
//        UUID u3 = UUID.randomUUID();
//
//        UUID cu1 = UUID.randomUUID();
//        UUID cu2 = UUID.randomUUID();
//        UUID cu3 = UUID.randomUUID();
//
//        TaskViewModel t1 = new TaskViewModel("test task name", u1, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskViewModel t2 = new TaskViewModel("test task name", u2, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskViewModel t3 = new TaskViewModel("test task name", u3, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        ArrayList<TaskViewModel> tasks1 = new ArrayList<>();
//        tasks1.add(t1);
//        tasks1.add(t2);
//        tasks1.add(t3);
//
//        TaskViewModel tt1 = new TaskViewModel("test task name", u1, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskViewModel tt2 = new TaskViewModel("test task name", u2, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskViewModel tt3 = new TaskViewModel("test task name", u3, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        ArrayList<TaskViewModel> tasks2 = new ArrayList<>();
//        tasks2.add(tt1);
//        tasks2.add(tt2);
//        tasks2.add(tt3);
//
//        TaskViewModel ttt1 = new TaskViewModel("test task name", u1, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskViewModel ttt2 = new TaskViewModel("test task name", u2, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskViewModel ttt3 = new TaskViewModel("test task name", u3, "test task description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        ArrayList<TaskViewModel> tasks3 = new ArrayList<>();
//        tasks3.add(ttt1);
//        tasks3.add(ttt2);
//        tasks3.add(ttt3);
//
//        ColumnViewModel c1 = new ColumnViewModel("test column", tasks1, cu1);
//        ColumnViewModel c2 = new ColumnViewModel("test column", tasks2, cu2);
//        ColumnViewModel c3 = new ColumnViewModel("test column", tasks3, cu3);
//
//        ArrayList<ColumnViewModel> colResult = new ArrayList<>();
//        colResult.add(c1);
//        colResult.add(c2);
//        colResult.add(c3);
//
//        ArrayList<ColumnViewModel> col = new ArrayList<>();
//        col.add(c2); // Add c2 to col
//
//        ProjectViewModel p = new ProjectViewModel(null, null, null, col);
//        p.addColumnViewModelToPosition(c1, 0); // Add c1 to position 0
//        p.addColumnViewModelToPosition(c3, 1); // Add c3 to position 1
//
//        colResult.add(0, c1); // Add c1 at position 0
//        colResult.add(1, c3); // Add c3 at position 1
//
//        Assertions.assertEquals(p.getColumnViewModels(), colResult);
//    }

    /**
     * Tests testMoveColumnViewModelToPosition NOT USED
     */
//    void testMoveColumnViewModelToPosition() {
//        ColumnViewModelModel c1 = new ColumnViewModelModel(null, null, null);
//        ColumnViewModelModel c2 = new ColumnViewModelModel(null, null, null);
//        ColumnViewModelModel c3 = new ColumnViewModelModel(null, null, null);
//        ArrayList<ColumnViewModelModel> columns = new ArrayList<ColumnViewModelModel>();
//        columns.add(c1);
//        columns.add(c2);
//        columns.add(c3);
//
//        ProjectViewModel p = new ProjectViewModel(null, null, null, columns);
//
//        p.moveColumnViewModelToPosition(c3, 0);
//
//        ArrayList<ColumnViewModelModel> columns2 = new ArrayList<ColumnViewModelModel>();
//        columns2.add(c3);
//        columns2.add(c1);
//        columns2.add(c2);
//
//        Assertions.assertEquals(p.getColumnViewModels(), columns2);
//    }

    @Test
    /**
     * Tests removeColumnViewModel
     */
    void removeColumnViewModel() {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();

        UUID cu1 = UUID.randomUUID();
        UUID cu2 = UUID.randomUUID();
        UUID cu3 = UUID.randomUUID();


        TaskViewModel t1 = new TaskViewModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel t2 = new TaskViewModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel t3 = new TaskViewModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskViewModel> tasks1 = new ArrayList<TaskViewModel>();
        tasks1.add(t1);
        tasks1.add(t2);
        tasks1.add(t3);

        TaskViewModel tt1 = new TaskViewModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel tt2 = new TaskViewModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel tt3 = new TaskViewModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<TaskViewModel> tasks2 = new ArrayList<TaskViewModel>();
        tasks2.add(tt1);
        tasks2.add(tt2);
        tasks2.add(tt3);

        TaskViewModel ttt1 = new TaskViewModel("test task name", u1, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel ttt2 = new TaskViewModel("test task name", u2, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel ttt3 = new TaskViewModel("test task name", u3, "test task description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<TaskViewModel> tasks3 = new ArrayList<TaskViewModel>();
        tasks3.add(ttt1);
        tasks3.add(ttt2);
        tasks3.add(ttt3);

        ColumnViewModel c1 = new ColumnViewModel("test column", tasks1, cu1);
        ColumnViewModel c2 = new ColumnViewModel("test column", tasks2, cu2);
        ColumnViewModel c3 = new ColumnViewModel("test column", tasks3, cu3);

        ArrayList<ColumnViewModel> col = new ArrayList<>();
        col.add(c1);
        col.add(c2);
        col.add(c3);

        ArrayList<ColumnViewModel> colResult = new ArrayList<ColumnViewModel>();
        colResult.add(c1);
        colResult.add(c3);

        ProjectViewModel p = new ProjectViewModel(null, null, null, col);
        p.removeColumnViewModel(c2);

        Assertions.assertEquals(p.getColumnViewModels(), colResult);
    }

    /**
     * Tests swapColumnViewModelOrder. NOT USED.
     */
//    void swapColumnViewModelOrder() {
//        ColumnViewModelModel c1 = new ColumnViewModelModel(null, null, null);
//        ColumnViewModelModel c2 = new ColumnViewModelModel(null, null, null);
//        ArrayList<ColumnViewModelModel> columns = new ArrayList<ColumnViewModelModel>();
//        columns.add(c1);
//        columns.add(c2);
//
//        ArrayList<ColumnViewModelModel> columns2 = new ArrayList<ColumnViewModelModel>();
//        columns.add(c2);
//        columns.add(c1);
//
//        ProjectViewModel p = new ProjectViewModel(null, null, null, columns);
//        p.swapColumnViewModelOrder(c2.getColumnViewModelEntity(), c1.getColumnViewModelEntity());
//
//        Assertions.assertEquals(p.getColumnViewModels(), columns2);
//    }


}