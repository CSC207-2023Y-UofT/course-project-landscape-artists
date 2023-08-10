package c_interface_adapters.view_models;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A class to test the ColumnViewModel entity.
 */
class ColumnViewModelTest {

    @Test
    /**
     * Tests getName
     */
    void getName() {
        List<TaskViewModel> sampleTaskViewModels1 = new ArrayList<TaskViewModel>();
        sampleTaskViewModels1.add(new TaskViewModel("t1 name", UUID.randomUUID(), "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskViewModels1.add(new TaskViewModel("t2 name", UUID.randomUUID(), "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskViewModels1.add(new TaskViewModel("t3 name", UUID.randomUUID(), "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnViewModel c = new ColumnViewModel("testing ColumnViewModel name", sampleTaskViewModels1, UUID.randomUUID());

        Assertions.assertEquals(c.getName(), "testing ColumnViewModel name");
    }

    @Test
    /**
     * Tests setName
     */
    void setName() {
        List<TaskViewModel> sampleTaskViewModels1 = new ArrayList<TaskViewModel>();
        sampleTaskViewModels1.add(new TaskViewModel("t1 name", UUID.randomUUID(), "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskViewModels1.add(new TaskViewModel("t2 name", UUID.randomUUID(), "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskViewModels1.add(new TaskViewModel("t3 name", UUID.randomUUID(), "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnViewModel c = new ColumnViewModel("testing ColumnViewModel name", sampleTaskViewModels1, UUID.randomUUID());

        c.setName("new test ColumnViewModel name");

        Assertions.assertEquals(c.getName(), "new test ColumnViewModel name");
    }

    @Test
    /**
     * Tests getID
     */
    void getID() {
        UUID currID = UUID.randomUUID();
        List<TaskViewModel> sampleTaskViewModels1 = new ArrayList<TaskViewModel>();
        sampleTaskViewModels1.add(new TaskViewModel("t1 name", UUID.randomUUID(), "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskViewModels1.add(new TaskViewModel("t2 name", UUID.randomUUID(), "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskViewModels1.add(new TaskViewModel("t3 name", UUID.randomUUID(), "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnViewModel c = new ColumnViewModel("testing ColumnViewModel name", sampleTaskViewModels1, currID);

        Assertions.assertEquals(c.getID(), currID);
    }

    @Test
    /**
     * Tests setID
     */
    void setID() {
        List<TaskViewModel> sampleTaskViewModels1 = new ArrayList<TaskViewModel>();
        sampleTaskViewModels1.add(new TaskViewModel("t1 name", UUID.randomUUID(), "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskViewModels1.add(new TaskViewModel("t2 name", UUID.randomUUID(), "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskViewModels1.add(new TaskViewModel("t3 name", UUID.randomUUID(), "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnViewModel c = new ColumnViewModel("testing ColumnViewModel name", sampleTaskViewModels1, UUID.randomUUID());

        UUID currID = UUID.randomUUID();

        Assertions.assertEquals(c.getID(), currID);
    }

    @Test
    /**
     * Tests getTaskViewModels
     */
    void getTaskViewModels() {
        List<TaskViewModel> sampleTaskViewModels1 = new ArrayList<TaskViewModel>();
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        sampleTaskViewModels1.add(new TaskViewModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskViewModels1.add(new TaskViewModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskViewModels1.add(new TaskViewModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnViewModel c = new ColumnViewModel("testing ColumnViewModel name", sampleTaskViewModels1, UUID.randomUUID());

        List<TaskViewModel> sampleTaskViewModels2 = new ArrayList<TaskViewModel>();
        sampleTaskViewModels2.add(new TaskViewModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskViewModels2.add(new TaskViewModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskViewModels2.add(new TaskViewModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));

        Assertions.assertEquals(c.getTaskViewModels(), sampleTaskViewModels2);
    }

    @Test
    /**
     * Tests setTaskViewModels
     */
    void setTaskViewModels() {
        List<TaskViewModel> sampleTaskViewModels1 = new ArrayList<TaskViewModel>();
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        sampleTaskViewModels1.add(new TaskViewModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskViewModels1.add(new TaskViewModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskViewModels1.add(new TaskViewModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnViewModel c = new ColumnViewModel("testing ColumnViewModel name", sampleTaskViewModels1, UUID.randomUUID());

        UUID newu1 = UUID.randomUUID();
        UUID newu2 = UUID.randomUUID();
        UUID newu3 = UUID.randomUUID();
        List<TaskViewModel> sampleTaskViewModels2 = new ArrayList<TaskViewModel>();
        sampleTaskViewModels2.add(new TaskViewModel("new t1 name", newu1, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskViewModels2.add(new TaskViewModel("new t2 name", newu2, "new t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskViewModels2.add(new TaskViewModel("new t3 name", newu3, "new t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));

        c.setTaskViewModels(sampleTaskViewModels2);

        List<TaskViewModel> sampleTaskViewModels3 = new ArrayList<TaskViewModel>();
        sampleTaskViewModels3.add(new TaskViewModel("new t1 name", newu1, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskViewModels3.add(new TaskViewModel("new t2 name", newu2, "new t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskViewModels3.add(new TaskViewModel("new t3 name", newu3, "new t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));

        Assertions.assertEquals(c.getTaskViewModels(), sampleTaskViewModels3);
    }

    @Test
    /**
     * Tests addTaskViewModel
     */
    void addTaskViewModel() {
        List<TaskViewModel> sampleTaskViewModels1 = new ArrayList<TaskViewModel>();
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        sampleTaskViewModels1.add(new TaskViewModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskViewModels1.add(new TaskViewModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskViewModels1.add(new TaskViewModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnViewModel c = new ColumnViewModel("testing ColumnViewModel name", sampleTaskViewModels1, UUID.randomUUID());

        UUID newUUID = UUID.randomUUID();

        TaskViewModel newTaskViewModel = new TaskViewModel("new t1 name", newUUID, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel newTaskViewModelCopy = new TaskViewModel("new t1 name", newUUID, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        c.addTaskViewModel(newTaskViewModel);

        List<TaskViewModel> sampleTaskViewModels2 = new ArrayList<TaskViewModel>();
        sampleTaskViewModels2.add(new TaskViewModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskViewModels2.add(new TaskViewModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskViewModels2.add(new TaskViewModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        sampleTaskViewModels2.add(newTaskViewModelCopy);

        Assertions.assertEquals(c.getTaskViewModels(), sampleTaskViewModels2);
    }

    @Test
    /**
     * Tests addTaskViewModelToPosition
     */
    void addTaskViewModelToPosition() {
        List<TaskViewModel> sampleTaskViewModels1 = new ArrayList<TaskViewModel>();
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        sampleTaskViewModels1.add(new TaskViewModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskViewModels1.add(new TaskViewModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskViewModels1.add(new TaskViewModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnViewModel c = new ColumnViewModel("testing ColumnViewModel name", sampleTaskViewModels1, UUID.randomUUID());

        UUID newUUID = UUID.randomUUID();

        TaskViewModel newTaskViewModel = new TaskViewModel("new t1 name", newUUID, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel newTaskViewModelCopy = new TaskViewModel("new t1 name", newUUID, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        c.addTaskViewModelToPosition(newTaskViewModel, 0);

        List<TaskViewModel> sampleTaskViewModels2 = new ArrayList<TaskViewModel>();
        sampleTaskViewModels2.add(new TaskViewModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskViewModels2.add(new TaskViewModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskViewModels2.add(new TaskViewModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        sampleTaskViewModels2.add(0, newTaskViewModelCopy);

        Assertions.assertEquals(c.getTaskViewModels(), sampleTaskViewModels2);
    }

    @Test
    /**
     * Tests removeTaskViewModel
     */
    void removeTaskViewModel() {
        List<TaskViewModel> sampleTaskViewModels1 = new ArrayList<TaskViewModel>();
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        TaskViewModel TaskViewModelToRemove = new TaskViewModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2));
        sampleTaskViewModels1.add(new TaskViewModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskViewModels1.add(TaskViewModelToRemove);
        sampleTaskViewModels1.add(new TaskViewModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnViewModel c = new ColumnViewModel("testing ColumnViewModel name", sampleTaskViewModels1, UUID.randomUUID());

        c.removeTaskViewModel(TaskViewModelToRemove);

        List<TaskViewModel> sampleTaskViewModels2 = new ArrayList<TaskViewModel>();
        sampleTaskViewModels2.add(new TaskViewModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskViewModels2.add(new TaskViewModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));

        Assertions.assertEquals(c.getTaskViewModels(), sampleTaskViewModels2);
    }

    @Test
    /**
     * Tests swapTaskViewModelOrder
     */
    void swapTaskViewModelOrder() {
        List<TaskViewModel> sampleTaskViewModels1 = new ArrayList<TaskViewModel>();
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        TaskViewModel TaskViewModel1 = new TaskViewModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel TaskViewModel2 = new TaskViewModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2));
        sampleTaskViewModels1.add(TaskViewModel1);
        sampleTaskViewModels1.add(TaskViewModel2);
        sampleTaskViewModels1.add(new TaskViewModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnViewModel c = new ColumnViewModel("testing ColumnViewModel name", sampleTaskViewModels1, UUID.randomUUID());

        c.swapTaskViewModelOrder(TaskViewModel1, TaskViewModel2);

        List<TaskViewModel> sampleTaskViewModels2 = new ArrayList<TaskViewModel>();
        sampleTaskViewModels2.add(TaskViewModel2);
        sampleTaskViewModels2.add(TaskViewModel1);
        sampleTaskViewModels2.add(new TaskViewModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));

        Assertions.assertEquals(c.getTaskViewModels(), sampleTaskViewModels2);
    }

    @Test
    /**
     * Tests moveTaskViewModelToPosition
     */
    void moveTaskViewModelToPosition() {
        List<TaskViewModel> sampleTaskViewModels1 = new ArrayList<TaskViewModel>();
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        TaskViewModel t1 = new TaskViewModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        sampleTaskViewModels1.add(t1);
        sampleTaskViewModels1.add(new TaskViewModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskViewModels1.add(new TaskViewModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnViewModel c = new ColumnViewModel("testing ColumnViewModel name", sampleTaskViewModels1, UUID.randomUUID());

        c.moveTaskViewModelToPosition(t1, 2);

        TaskViewModel t1Dupe = new TaskViewModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        List<TaskViewModel> sampleTaskViewModels2 = new ArrayList<TaskViewModel>();
        sampleTaskViewModels2.add(new TaskViewModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskViewModels2.add(new TaskViewModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        sampleTaskViewModels2.add(t1Dupe);

        Assertions.assertEquals(c.getTaskViewModels(), sampleTaskViewModels2);
    }

    @Test
    /**
     * Test toString
     */
    void testToString() {
        List<TaskViewModel> sampleTaskViewModels1 = new ArrayList<TaskViewModel>();
        sampleTaskViewModels1.add(new TaskViewModel("t1 name", UUID.randomUUID(), "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskViewModels1.add(new TaskViewModel("t2 name", UUID.randomUUID(), "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskViewModels1.add(new TaskViewModel("t3 name", UUID.randomUUID(), "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnViewModel c = new ColumnViewModel("testing ColumnViewModel name", sampleTaskViewModels1, UUID.randomUUID());

        String ColumnViewModelStringRepresentation = "[ColumnViewModel Name: testing ColumnViewModel name, "
                + "TaskViewModels: {[TaskViewModel Name: t1 name, TaskViewModel Completed: false, Due Date: 2024-03-28T14:33:48], "
                + "[TaskViewModel Name: t2 name, TaskViewModel Completed: true, Due Date: 2023-02-18T11:10:05.000000002], "
                + "[TaskViewModel Name: t3 name, TaskViewModel Completed: false, Due Date: 1985-10-01T19:13:09.000000006]}]";

        Assertions.assertEquals(c.toString(), ColumnViewModelStringRepresentation);
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

        ColumnViewModel c1 = new ColumnViewModel("test ColumnViewModel", TaskViewModels1, cu);
        ColumnViewModel c2 = new ColumnViewModel("test ColumnViewModel", TaskViewModels2, cu);
        ColumnViewModel c3 = new ColumnViewModel("test ColumnViewModel", TaskViewModels3, cu);

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
     * Tests IDToColumnViewModel NOT USED
     */
//    void TestIDToColumnViewModel() {
//        UUID u1 = UUID.randomUUID();
//        UUID u2 = UUID.randomUUID();
//        UUID u3 = UUID.randomUUID();
//
//        UUID cu1 = UUID.randomUUID();
//        UUID cu2 = UUID.randomUUID();
//        UUID cu3 = UUID.randomUUID();
//
//
//        TaskViewModel t1 = new TaskViewModel("test TaskViewModel name", u1, "test TaskViewModel description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskViewModel t2 = new TaskViewModel("test TaskViewModel name", u2, "test TaskViewModel description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskViewModel t3 = new TaskViewModel("test TaskViewModel name", u3, "test TaskViewModel description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        ArrayList<TaskViewModel> TaskViewModels1 = new ArrayList<TaskViewModel>();
//        TaskViewModels1.add(t1);
//        TaskViewModels1.add(t2);
//        TaskViewModels1.add(t3);
//
//        TaskViewModel tt1 = new TaskViewModel("test TaskViewModel name", u1, "test TaskViewModel description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskViewModel tt2 = new TaskViewModel("test TaskViewModel name", u2, "test TaskViewModel description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskViewModel tt3 = new TaskViewModel("test TaskViewModel name", u3, "test TaskViewModel description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//
//        ArrayList<TaskViewModel> TaskViewModels2 = new ArrayList<TaskViewModel>();
//        TaskViewModels2.add(tt1);
//        TaskViewModels2.add(tt2);
//        TaskViewModels2.add(tt3);
//
//        TaskViewModel ttt1 = new TaskViewModel("test TaskViewModel name", u1, "test TaskViewModel description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskViewModel ttt2 = new TaskViewModel("test TaskViewModel name", u2, "test TaskViewModel description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        TaskViewModel ttt3 = new TaskViewModel("test TaskViewModel name", u3, "test TaskViewModel description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//
//        ArrayList<TaskViewModel> TaskViewModels3 = new ArrayList<TaskViewModel>();
//        TaskViewModels3.add(ttt1);
//        TaskViewModels3.add(ttt2);
//        TaskViewModels3.add(ttt3);
//
//        ColumnViewModel c1 = new ColumnViewModel("test ColumnViewModel", TaskViewModels1, cu1);
//        ColumnViewModel c2 = new ColumnViewModel("test ColumnViewModel", TaskViewModels2, cu2);
//        ColumnViewModel c3 = new ColumnViewModel("test ColumnViewModel", TaskViewModels3, cu3);
//
//        ArrayList<ColumnViewModel> col = new ArrayList<>();
//        col.add(c1);
//        col.add(c2);
//        col.add(c3);
//
//        ColumnViewModel output = ColumnViewModel.IDToColumnViewModel(cu2, col);
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