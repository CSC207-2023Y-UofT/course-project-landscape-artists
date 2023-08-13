package c_interface_adapters.view_models;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A class to test the TaskViewModels entity.
 */
public class TaskViewModelTest {

    @Test
    /**
     * Tests getName
     */
    void getName() {
        TaskViewModel t = new TaskViewModel("test TaskViewModel name", UUID.randomUUID(), "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertEquals(t.getName(), "test TaskViewModel name");
    }

    @Test
    /**
     * Tests setName
     */
    void setName() {
        TaskViewModel t = new TaskViewModel("test TaskViewModel name", UUID.randomUUID(), "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.setName("New testing name!");
        Assertions.assertEquals(t.getName(), "New testing name!");
    }

    @Test
    /**
     * Tests getID
     */
    void getID() {
        UUID TaskViewModelUUID = UUID.randomUUID();
        TaskViewModel t = new TaskViewModel("test TaskViewModel name", TaskViewModelUUID, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertEquals(t.getID(), TaskViewModelUUID);
    }

    @Test
    /**
     * Tests setID
     */
    void setID() {
        UUID originalUUID = UUID.randomUUID();
        TaskViewModel t = new TaskViewModel("test TaskViewModel name", originalUUID, "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        Assertions.assertEquals(originalUUID, t.getID());
    }

    @Test
    /**
     * Tests getDescription
     */
    void getDescription() {
        TaskViewModel t = new TaskViewModel("test TaskViewModel name", UUID.randomUUID(), "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertEquals(t.getDescription(), "test TaskViewModel description");
    }

    @Test
    /**
     * Tests setDescription
     */
    void setDescription() {
        TaskViewModel t = new TaskViewModel("test TaskViewModel name", UUID.randomUUID(), "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.setDescription("newerrr test TaskViewModel description");
        Assertions.assertEquals(t.getDescription(), "newerrr test TaskViewModel description");
    }

    @Test
    /**
     * Tests getCompletionStatus
     */
    void getCompletionStatus() {
        TaskViewModel t = new TaskViewModel("test TaskViewModel name", UUID.randomUUID(), "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertFalse(t.getCompletionStatus());
    }

    @Test
    /**
     * Tests completeTaskViewModel
     */
    void completeTaskViewModel() {
        TaskViewModel t = new TaskViewModel("test TaskViewModel name", UUID.randomUUID(), "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.completeTaskViewModel();
        Assertions.assertTrue(t.getCompletionStatus());

        TaskViewModel s = new TaskViewModel("test TaskViewModel name", UUID.randomUUID(), "test TaskViewModel description", true,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        s.completeTaskViewModel();
        Assertions.assertTrue(s.getCompletionStatus());
    }

    @Test
    /**
     * Tests incompleteTaskViewModel
     */
    void incompleteTaskViewModel() {
        TaskViewModel t = new TaskViewModel("test TaskViewModel name", UUID.randomUUID(), "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.incompleteTaskViewModel();
        Assertions.assertFalse(t.getCompletionStatus());

        TaskViewModel s = new TaskViewModel("test TaskViewModel name", UUID.randomUUID(), "test TaskViewModel description", true,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        s.incompleteTaskViewModel();
        Assertions.assertFalse(s.getCompletionStatus());
    }

    /**
     * NOT TESETED: NOT USED
     */
//    void negateCompletionStatus() {
//        TaskViewModel t = new TaskViewModel("test TaskViewModel name", UUID.randomUUID(), "test TaskViewModel description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        t.incompleteTaskViewModel();
//        Assertions.assertTrue(t.getCompletionStatus());
//
//        TaskViewModel s = new TaskViewModel("test TaskViewModel name", UUID.randomUUID(), "test TaskViewModel description", true,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        s.incompleteTaskViewModel();
//        Assertions.assertFalse(s.getCompletionStatus());
//    }

    @Test
    /**
     * Tests getDueDateTime
     */
    void getDueDateTime() {
        TaskViewModel t = new TaskViewModel("test TaskViewModel name", UUID.randomUUID(), "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertEquals(t.getDueDateTime(), LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
    }

    @Test
    /**
     * Tests setDueDateTime
     */
    void setDueDateTime() {
        TaskViewModel t = new TaskViewModel("test TaskViewModel name", UUID.randomUUID(), "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.setDueDateTime(LocalDateTime.of(4024, 03, 28, 14, 2, 11, 1110));
        Assertions.assertEquals(t.getDueDateTime(), LocalDateTime.of(4024, 03, 28, 14, 2, 11, 1110));
    }

    @Test
    /**
     * Tests toString
     */
    void testToString() {
        TaskViewModel t = new TaskViewModel("test TaskViewModel name", UUID.randomUUID(), "test TaskViewModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertEquals(
                t.toString(), "[TaskViewModel Name: test TaskViewModel name, TaskViewModel Completed: false, Due Date: 2024-03-28T14:33:48]");

    }



    /**
     * Tests ITToTaskViewModel NOT USED
     */
//    void TestIDToTaskViewModel() {
//        UUID u1 = UUID.randomUUID();
//        UUID u2 = UUID.randomUUID();
//        UUID u3 = UUID.randomUUID();
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
//        TaskViewModel output = TaskViewModel.IDToTaskViewModel(u2, TaskViewModels1);
//        Assertions.assertEquals(output, t2);
//
//    }

}