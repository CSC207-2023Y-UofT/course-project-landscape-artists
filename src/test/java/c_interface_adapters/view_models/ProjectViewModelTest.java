package c_interface_adapters.view_models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import c_interface_adapters.view_models.ColumnViewModel;
import c_interface_adapters.view_models.ProjectViewModel;
import c_interface_adapters.view_models.TaskViewModel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A class to test the ProjectViewModel entity.
 */
class ProjectViewModelTest {


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


        TaskViewModel t1 = new TaskViewModel("test TaskViewModel name", u1, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel t2 = new TaskViewModel("test TaskViewModel name", u2, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel t3 = new TaskViewModel("test TaskViewModel name", u3, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskViewModel> TaskViewModels1 = new ArrayList<TaskViewModel>();
        TaskViewModels1.add(t1);
        TaskViewModels1.add(t2);
        TaskViewModels1.add(t3);

        TaskViewModel tt1 = new TaskViewModel("test TaskViewModel name", u1, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel tt2 = new TaskViewModel("test TaskViewModel name", u2, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel tt3 = new TaskViewModel("test TaskViewModel name", u3, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<TaskViewModel> TaskViewModels2 = new ArrayList<TaskViewModel>();
        TaskViewModels2.add(tt1);
        TaskViewModels2.add(tt2);
        TaskViewModels2.add(tt3);

        TaskViewModel ttt1 = new TaskViewModel("test TaskViewModel name", u1, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel ttt2 = new TaskViewModel("test TaskViewModel name", u2, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel ttt3 = new TaskViewModel("test TaskViewModel name", u3, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<TaskViewModel> TaskViewModels3 = new ArrayList<TaskViewModel>();
        TaskViewModels3.add(ttt1);
        TaskViewModels3.add(ttt2);
        TaskViewModels3.add(ttt3);

        ColumnViewModel c1 = new ColumnViewModel("test ColumnViewModel", TaskViewModels1, cu1);
        ColumnViewModel c2 = new ColumnViewModel("test ColumnViewModel", TaskViewModels2, cu2);
        ColumnViewModel c3 = new ColumnViewModel("test ColumnViewModel", TaskViewModels3, cu3);

        ArrayList<ColumnViewModel> colResult = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);
        colResult.add(c3);

        ArrayList<ColumnViewModel> col = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);
        colResult.add(c3);

        ProjectViewModel p = new ProjectViewModel(null, null, null, col);


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


        TaskViewModel t1 = new TaskViewModel("test TaskViewModel name", u1, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel t2 = new TaskViewModel("test TaskViewModel name", u2, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel t3 = new TaskViewModel("test TaskViewModel name", u3, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskViewModel> TaskViewModels1 = new ArrayList<TaskViewModel>();
        TaskViewModels1.add(t1);
        TaskViewModels1.add(t2);
        TaskViewModels1.add(t3);

        TaskViewModel tt1 = new TaskViewModel("test TaskViewModel name", u1, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel tt2 = new TaskViewModel("test TaskViewModel name", u2, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel tt3 = new TaskViewModel("test TaskViewModel name", u3, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<TaskViewModel> TaskViewModels2 = new ArrayList<TaskViewModel>();
        TaskViewModels2.add(tt1);
        TaskViewModels2.add(tt2);
        TaskViewModels2.add(tt3);

        TaskViewModel ttt1 = new TaskViewModel("test TaskViewModel name", u1, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel ttt2 = new TaskViewModel("test TaskViewModel name", u2, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel ttt3 = new TaskViewModel("test TaskViewModel name", u3, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<TaskViewModel> TaskViewModels3 = new ArrayList<TaskViewModel>();
        TaskViewModels3.add(ttt1);
        TaskViewModels3.add(ttt2);
        TaskViewModels3.add(ttt3);

        ColumnViewModel c1 = new ColumnViewModel("test ColumnViewModel", TaskViewModels1, cu1);
        ColumnViewModel c2 = new ColumnViewModel("test ColumnViewModel", TaskViewModels2, cu2);
        ColumnViewModel c3 = new ColumnViewModel("test ColumnViewModel", TaskViewModels3, cu3);

        ArrayList<ColumnViewModel> colResult = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);
        colResult.add(c3);

        ArrayList<ColumnViewModel> input = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);
        colResult.add(c3);

        ArrayList<ColumnViewModel> col = new ArrayList<>();
        colResult.add(c2);

        ProjectViewModel p = new ProjectViewModel(null, null, null, col);
        p.setColumnViewModels(input);

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


        TaskViewModel t1 = new TaskViewModel("test TaskViewModel name", u1, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel t2 = new TaskViewModel("test TaskViewModel name", u2, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel t3 = new TaskViewModel("test TaskViewModel name", u3, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskViewModel> TaskViewModels1 = new ArrayList<TaskViewModel>();
        TaskViewModels1.add(t1);
        TaskViewModels1.add(t2);
        TaskViewModels1.add(t3);

        TaskViewModel tt1 = new TaskViewModel("test TaskViewModel name", u1, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel tt2 = new TaskViewModel("test TaskViewModel name", u2, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel tt3 = new TaskViewModel("test TaskViewModel name", u3, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<TaskViewModel> TaskViewModels2 = new ArrayList<TaskViewModel>();
        TaskViewModels2.add(tt1);
        TaskViewModels2.add(tt2);
        TaskViewModels2.add(tt3);

        TaskViewModel ttt1 = new TaskViewModel("test TaskViewModel name", u1, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel ttt2 = new TaskViewModel("test TaskViewModel name", u2, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel ttt3 = new TaskViewModel("test TaskViewModel name", u3, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<TaskViewModel> TaskViewModels3 = new ArrayList<TaskViewModel>();
        TaskViewModels3.add(ttt1);
        TaskViewModels3.add(ttt2);
        TaskViewModels3.add(ttt3);

        ColumnViewModel c1 = new ColumnViewModel("test ColumnViewModel", TaskViewModels1, cu1);
        ColumnViewModel c2 = new ColumnViewModel("test ColumnViewModel", TaskViewModels2, cu2);
        ColumnViewModel c3 = new ColumnViewModel("test ColumnViewModel", TaskViewModels3, cu3);

        ArrayList<ColumnViewModel> colResult = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);
        colResult.add(c3);

        ArrayList<ColumnViewModel> col = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);



        ProjectViewModel p = new ProjectViewModel(null, null, null, col);
        p.addColumnViewModel(c3);

        Assertions.assertEquals(p.getColumnViewModels(), colResult);
    }

    @Test
    /**
     * Tests addColumnViewModelToPosition
     */
    void addColumnViewModelToPosition() {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();

        UUID cu1 = UUID.randomUUID();
        UUID cu2 = UUID.randomUUID();
        UUID cu3 = UUID.randomUUID();


        TaskViewModel t1 = new TaskViewModel("test TaskViewModel name", u1, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel t2 = new TaskViewModel("test TaskViewModel name", u2, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel t3 = new TaskViewModel("test TaskViewModel name", u3, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskViewModel> TaskViewModels1 = new ArrayList<TaskViewModel>();
        TaskViewModels1.add(t1);
        TaskViewModels1.add(t2);
        TaskViewModels1.add(t3);

        TaskViewModel tt1 = new TaskViewModel("test TaskViewModel name", u1, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel tt2 = new TaskViewModel("test TaskViewModel name", u2, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel tt3 = new TaskViewModel("test TaskViewModel name", u3, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<TaskViewModel> TaskViewModels2 = new ArrayList<TaskViewModel>();
        TaskViewModels2.add(tt1);
        TaskViewModels2.add(tt2);
        TaskViewModels2.add(tt3);

        TaskViewModel ttt1 = new TaskViewModel("test TaskViewModel name", u1, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel ttt2 = new TaskViewModel("test TaskViewModel name", u2, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel ttt3 = new TaskViewModel("test TaskViewModel name", u3, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<TaskViewModel> TaskViewModels3 = new ArrayList<TaskViewModel>();
        TaskViewModels3.add(ttt1);
        TaskViewModels3.add(ttt2);
        TaskViewModels3.add(ttt3);

        ColumnViewModel c1 = new ColumnViewModel("test ColumnViewModel", TaskViewModels1, cu1);
        ColumnViewModel c2 = new ColumnViewModel("test ColumnViewModel", TaskViewModels2, cu2);
        ColumnViewModel c3 = new ColumnViewModel("test ColumnViewModel", TaskViewModels3, cu3);

        ArrayList<ColumnViewModel> colResult = new ArrayList<>();
        colResult.add(c1);
        colResult.add(c2);
        colResult.add(c3);

        ArrayList<ColumnViewModel> col = new ArrayList<>();
        colResult.add(c2);



        ProjectViewModel p = new ProjectViewModel(null, null, null, col);
        p.addColumnViewModelToPosition(c1, 0);
        p.addColumnViewModelToPosition(c3, 2);


        Assertions.assertEquals(p.getColumnViewModels(), colResult);
    }

    /**
     * Tests testMoveColumnViewModelToPosition NOT USED
     */
//    void testMoveColumnViewModelToPosition() {
//        ColumnViewModelModel c1 = new ColumnViewModelModel(null, null, null);
//        ColumnViewModelModel c2 = new ColumnViewModelModel(null, null, null);
//        ColumnViewModelModel c3 = new ColumnViewModelModel(null, null, null);
//        ArrayList<ColumnViewModelModel> ColumnViewModels = new ArrayList<ColumnViewModelModel>();
//        ColumnViewModels.add(c1);
//        ColumnViewModels.add(c2);
//        ColumnViewModels.add(c3);
//
//        ProjectViewModel p = new ProjectViewModel(null, null, null, ColumnViewModels);
//
//        p.moveColumnViewModelToPosition(c3, 0);
//
//        ArrayList<ColumnViewModelModel> ColumnViewModels2 = new ArrayList<ColumnViewModelModel>();
//        ColumnViewModels2.add(c3);
//        ColumnViewModels2.add(c1);
//        ColumnViewModels2.add(c2);
//
//        Assertions.assertEquals(p.getColumnViewModels(), ColumnViewModels2);
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


        TaskViewModel t1 = new TaskViewModel("test TaskViewModel name", u1, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel t2 = new TaskViewModel("test TaskViewModel name", u2, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel t3 = new TaskViewModel("test TaskViewModel name", u3, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskViewModel> TaskViewModels1 = new ArrayList<TaskViewModel>();
        TaskViewModels1.add(t1);
        TaskViewModels1.add(t2);
        TaskViewModels1.add(t3);

        TaskViewModel tt1 = new TaskViewModel("test TaskViewModel name", u1, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel tt2 = new TaskViewModel("test TaskViewModel name", u2, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel tt3 = new TaskViewModel("test TaskViewModel name", u3, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<TaskViewModel> TaskViewModels2 = new ArrayList<TaskViewModel>();
        TaskViewModels2.add(tt1);
        TaskViewModels2.add(tt2);
        TaskViewModels2.add(tt3);

        TaskViewModel ttt1 = new TaskViewModel("test TaskViewModel name", u1, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel ttt2 = new TaskViewModel("test TaskViewModel name", u2, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel ttt3 = new TaskViewModel("test TaskViewModel name", u3, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<TaskViewModel> TaskViewModels3 = new ArrayList<TaskViewModel>();
        TaskViewModels3.add(ttt1);
        TaskViewModels3.add(ttt2);
        TaskViewModels3.add(ttt3);

        ColumnViewModel c1 = new ColumnViewModel("test ColumnViewModel", TaskViewModels1, cu1);
        ColumnViewModel c2 = new ColumnViewModel("test ColumnViewModel", TaskViewModels2, cu2);
        ColumnViewModel c3 = new ColumnViewModel("test ColumnViewModel", TaskViewModels3, cu3);

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
//        ArrayList<ColumnViewModelModel> ColumnViewModels = new ArrayList<ColumnViewModelModel>();
//        ColumnViewModels.add(c1);
//        ColumnViewModels.add(c2);
//
//        ArrayList<ColumnViewModelModel> ColumnViewModels2 = new ArrayList<ColumnViewModelModel>();
//        ColumnViewModels.add(c2);
//        ColumnViewModels.add(c1);
//
//        ProjectViewModel p = new ProjectViewModel(null, null, null, ColumnViewModels);
//        p.swapColumnViewModelOrder(c2.getColumnViewModelEntity(), c1.getColumnViewModelEntity());
//
//        Assertions.assertEquals(p.getColumnViewModels(), ColumnViewModels2);
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
        String name = "Test ProjectViewModel";
        String description = "Description";

        TaskViewModel t1 = new TaskViewModel("test TaskViewModel name", u1, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel t2 = new TaskViewModel("test TaskViewModel name", u2, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel t3 = new TaskViewModel("test TaskViewModel name", u3, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        ArrayList<TaskViewModel> TaskViewModels1 = new ArrayList<TaskViewModel>();
        TaskViewModels1.add(t1);
        TaskViewModels1.add(t2);
        TaskViewModels1.add(t3);

        TaskViewModel tt1 = new TaskViewModel("test TaskViewModel name", u1, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel tt2 = new TaskViewModel("test TaskViewModel name", u2, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel tt3 = new TaskViewModel("test TaskViewModel name", u3, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<TaskViewModel> TaskViewModels2 = new ArrayList<TaskViewModel>();
        TaskViewModels2.add(tt1);
        TaskViewModels2.add(tt2);
        TaskViewModels2.add(tt3);

        TaskViewModel ttt1 = new TaskViewModel("test TaskViewModel name", u1, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel ttt2 = new TaskViewModel("test TaskViewModel name", u2, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel ttt3 = new TaskViewModel("test TaskViewModel name", u3, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        ArrayList<TaskViewModel> TaskViewModels3 = new ArrayList<TaskViewModel>();
        TaskViewModels3.add(ttt1);
        TaskViewModels3.add(ttt2);
        TaskViewModels3.add(ttt3);

        ColumnViewModel c1 = new ColumnViewModel("test ColumnViewModel", TaskViewModels1, cu1);
        ColumnViewModel c2 = new ColumnViewModel("test ColumnViewModel", TaskViewModels2, cu2);
        ColumnViewModel c3 = new ColumnViewModel("test ColumnViewModel", TaskViewModels3, cu3);

        ArrayList<ColumnViewModel> col = new ArrayList<>();
        col.add(c1);
        col.add(c2);
        col.add(c3);

        ProjectViewModel p1 = new ProjectViewModel(name, pu, description, col);
        ProjectViewModel p2 = new ProjectViewModel(name, pu, description, col);
        ProjectViewModel p3 = new ProjectViewModel(name, pu, description, col);

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