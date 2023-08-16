package c_interface_adapters.view_models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import c_interface_adapters.view_models.ColumnViewModel;
import c_interface_adapters.view_models.TaskViewModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A class to test the ColumnViewModel entity.
 */
public class ColumnViewModelTest {

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
        ColumnViewModel c = new ColumnViewModel("testing column name", sampleTaskViewModels1, UUID.randomUUID());

        Assertions.assertEquals(c.getName(), "testing column name");
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
        ColumnViewModel c = new ColumnViewModel("testing column name", sampleTaskViewModels1, UUID.randomUUID());

        c.setName("new test column name");

        Assertions.assertEquals(c.getName(), "new test column name");
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
        ColumnViewModel c = new ColumnViewModel("testing column name", sampleTaskViewModels1, currID);

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

        UUID currID = UUID.randomUUID();

        ColumnViewModel c = new ColumnViewModel("testing column name", sampleTaskViewModels1, currID); // Set the UUID of the ColumnViewModel

        Assertions.assertEquals(c.getID(), currID);
    }

    @Test
    /**
     * Tests getTaskViewModels
     */
    void getTaskViewModels() {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();

        List<TaskViewModel> sampleTaskViewModels1 = new ArrayList<TaskViewModel>();
        sampleTaskViewModels1.add(new TaskViewModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskViewModels1.add(new TaskViewModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskViewModels1.add(new TaskViewModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));

        ColumnViewModel c = new ColumnViewModel("testing column name", sampleTaskViewModels1, UUID.randomUUID());

        List<TaskViewModel> actualTaskViewModels = c.getTaskViewModels();

        Assertions.assertEquals(actualTaskViewModels, sampleTaskViewModels1);
    }

    @Test
    /**
     * Tests setTaskViewModels
     */
    void setTaskViewModels() {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();

        List<TaskViewModel> sampleTaskViewModels1 = new ArrayList<TaskViewModel>();
        sampleTaskViewModels1.add(new TaskViewModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskViewModels1.add(new TaskViewModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskViewModels1.add(new TaskViewModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));

        ColumnViewModel c = new ColumnViewModel("testing column name", sampleTaskViewModels1, UUID.randomUUID());

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

        Assertions.assertEquals(c.getTaskViewModels(), sampleTaskViewModels2);
    }

    @Test
    /**
     * Tests addTaskViewModel
     */
    void addTaskViewModel() {
        UUID u1 = UUID.randomUUID();
        UUID u2 = UUID.randomUUID();
        UUID u3 = UUID.randomUUID();
        List<TaskViewModel> sampleTaskViewModels1 = new ArrayList<>();
        sampleTaskViewModels1.add(new TaskViewModel("t1 name", u1, "t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0)));
        sampleTaskViewModels1.add(new TaskViewModel("t2 name", u2, "t2 desc", true,
                LocalDateTime.of(2023, 02, 18, 11, 10, 5, 2)));
        sampleTaskViewModels1.add(new TaskViewModel("t3 name", u3, "t3 desc", false,
                LocalDateTime.of(1985, 10, 1, 19, 13, 9, 6)));
        ColumnViewModel c = new ColumnViewModel("testing column name", sampleTaskViewModels1, UUID.randomUUID());

        UUID newUUID = UUID.randomUUID();
        TaskViewModel newTaskViewModel = new TaskViewModel("new t1 name", newUUID, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        TaskViewModel newTaskViewModelCopy = new TaskViewModel("new t1 name", newUUID, "new t1 desc", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        c.addTaskViewModel(newTaskViewModel);

        sampleTaskViewModels1.add(newTaskViewModelCopy);

        Assertions.assertEquals(c.getTaskViewModels(), sampleTaskViewModels1);
    }

    /**
     * NOT TESTED: NOT USED
     */
//    void addTaskViewModelToPosition() {
//
//    }

    /**
     * NOT TESTED: NOT USED
     */
//    void removeTaskViewModel() {
//
//    }

    /**
     * NOT TESTED: NOT USED
     */
//    void swapTaskViewModelOrder() {
//
//    }

    /**
     * NOT TESTED: NOT USED
     */
//    void moveTaskViewModelToPosition() {
//
//    }

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
        ColumnViewModel c = new ColumnViewModel("testing column name", sampleTaskViewModels1, UUID.randomUUID());

        String columnStringRepresentation = "[ColumnViewModel Name: testing column name, "
                + "TaskViewModels: {[TaskViewModel Name: t1 name, TaskViewModel Completed: false, Due Date: 2024-03-28T14:33:48], "
                + "[TaskViewModel Name: t2 name, TaskViewModel Completed: true, Due Date: 2023-02-18T11:10:05.000000002], "
                + "[TaskViewModel Name: t3 name, TaskViewModel Completed: false, Due Date: 1985-10-01T19:13:09.000000006]}]";

        Assertions.assertEquals(c.toString(), columnStringRepresentation);
    }


}