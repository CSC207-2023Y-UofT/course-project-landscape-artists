package b_application_business_rules.entity_models;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A class to test the TaskModels entity.
 */
public class TaskModelTest {

    @Test
    /**
     * Tests getName
     */
    void getName() {
        TaskModel t = new TaskModel("test TaskModel name", UUID.randomUUID(), "test TaskModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertEquals(t.getName(), "test TaskModel name");
    }

    @Test
    /**
     * Tests setName
     */
    void setName() {
        TaskModel t = new TaskModel("test TaskModel name", UUID.randomUUID(), "test TaskModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.setName("New testing name!");
        Assertions.assertEquals(t.getName(), "New testing name!");
    }

    @Test
    /**
     * Tests getID
     */
    void getID() {
        UUID TaskModelUUID = UUID.randomUUID();
        TaskModel t = new TaskModel("test TaskModel name", TaskModelUUID, "test TaskModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertEquals(t.getID(), TaskModelUUID);
    }

    @Test
    /**
     * Tests setID
     */
    void setID() {
        UUID originalUUID = UUID.randomUUID();
        TaskModel t = new TaskModel("test TaskModel name", originalUUID, "test TaskModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));

        Assertions.assertEquals(originalUUID, t.getID());
    }

    @Test
    /**
     * Tests getDescription
     */
    void getDescription() {
        TaskModel t = new TaskModel("test TaskModel name", UUID.randomUUID(), "test TaskModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertEquals(t.getDescription(), "test TaskModel description");
    }

    @Test
    /**
     * Tests setDescription
     */
    void setDescription() {
        TaskModel t = new TaskModel("test TaskModel name", UUID.randomUUID(), "test TaskModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.setDescription("newerrr test TaskModel description");
        Assertions.assertEquals(t.getDescription(), "newerrr test TaskModel description");
    }

    @Test
    /**
     * Tests getCompletionStatus
     */
    void getCompletionStatus() {
        TaskModel t = new TaskModel("test TaskModel name", UUID.randomUUID(), "test TaskModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertFalse(t.getCompletionStatus());
    }

    @Test
    /**
     * Tests completeTaskModel
     */
    void completeTaskModel() {
        TaskModel t = new TaskModel("test TaskModel name", UUID.randomUUID(), "test TaskModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.completeTaskModel();
        Assertions.assertTrue(t.getCompletionStatus());

        TaskModel s = new TaskModel("test TaskModel name", UUID.randomUUID(), "test TaskModel description", true,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        s.completeTaskModel();
        Assertions.assertTrue(s.getCompletionStatus());
    }

    @Test
    /**
     * Tests incompleteTaskModel
     */
    void incompleteTaskModel() {
        TaskModel t = new TaskModel("test TaskModel name", UUID.randomUUID(), "test TaskModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.incompleteTaskModel();
        Assertions.assertFalse(t.getCompletionStatus());

        TaskModel s = new TaskModel("test TaskModel name", UUID.randomUUID(), "test TaskModel description", true,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        s.incompleteTaskModel();
        Assertions.assertFalse(s.getCompletionStatus());
    }

    /**
     * NOT TESETED: NOT USED
     */
//    void negateCompletionStatus() {
//        TaskModel t = new TaskModel("test TaskModel name", UUID.randomUUID(), "test TaskModel description", false,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        t.incompleteTaskModel();
//        Assertions.assertTrue(t.getCompletionStatus());
//
//        TaskModel s = new TaskModel("test TaskModel name", UUID.randomUUID(), "test TaskModel description", true,
//                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
//        s.incompleteTaskModel();
//        Assertions.assertFalse(s.getCompletionStatus());
//    }

    @Test
    /**
     * Tests getDueDateTime
     */
    void getDueDateTime() {
        TaskModel t = new TaskModel("test TaskModel name", UUID.randomUUID(), "test TaskModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertEquals(t.getDueDateTime(), LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
    }

    @Test
    /**
     * Tests setDueDateTime
     */
    void setDueDateTime() {
        TaskModel t = new TaskModel("test TaskModel name", UUID.randomUUID(), "test TaskModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        t.setDueDateTime(LocalDateTime.of(4024, 03, 28, 14, 2, 11, 1110));
        Assertions.assertEquals(t.getDueDateTime(), LocalDateTime.of(4024, 03, 28, 14, 2, 11, 1110));
    }

    @Test
    /**
     * Tests toString
     */
    void testToString() {
        TaskModel t = new TaskModel("test TaskModel name", UUID.randomUUID(), "test TaskModel description", false,
                LocalDateTime.of(2024, 03, 28, 14, 33, 48, 0));
        Assertions.assertEquals(
                t.toString(), "[TaskModel Name: test TaskModel name, TaskModel Completed: false, Due Date: 2024-03-28T14:33:48]");

    }



    /**
     * Tests ITToTaskModel NOT USED
     */
//    void TestIDToTaskModel() {
//        UUID u1 = UUID.randomUUID();
//        UUID u2 = UUID.randomUUID();
//        UUID u3 = UUID.randomUUID();
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
//        TaskModel output = TaskModel.IDToTaskModel(u2, TaskModels1);
//        Assertions.assertEquals(output, t2);
//
//    }

}