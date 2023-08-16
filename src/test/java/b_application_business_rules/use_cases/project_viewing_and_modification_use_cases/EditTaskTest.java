//package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;
//
//import a_enterprise_business_rules.entities.Column;
//import a_enterprise_business_rules.entities.Project;
//import a_enterprise_business_rules.entities.Task;
//import b_application_business_rules.entity_models.TaskModel;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class EditTaskTest {
//    private Project p;
//    private Column c;
//    @BeforeEach
//    void setUp() {
//        UUID projectID = UUID.randomUUID();
//        UUID columnID  = UUID.randomUUID();
//
//        c = new Column("c1", new ArrayList<Task>(), columnID); // Initialize new column
//        ArrayList<Column> listOfColumns = new ArrayList<Column>();
//        listOfColumns.add(c); // Add column to list of columns
//        p = new Project("p1", projectID, "", listOfColumns); //Initialize new project
//    }
//
//    @Test
//    public void testEditTask() {
//
//        UUID taskID  = UUID.randomUUID();
//        Task t = new Task("t1", taskID, "", false, LocalDateTime.now()); // Initialize TaskModel
//        c.getTasks().add(t); // Add task to list of tasks
//
//        assertEquals("t1", c.getTasks().get(0).getName());
//        assertEquals(1, c.getTasks().size());
//
//        TaskModel editedTask = new TaskModel("changed name", taskID, "changed description", false, LocalDateTime.now());
//        EditTask editTaskUseCase = new EditTask(p);
//        editTaskUseCase.editTask(c.getID(), editedTask);
//
//        assertEquals(1, c.getTasks().size());
//        assertEquals("changed name", c.getTasks().get(0).getName());
//        assertEquals("changed description", c.getTasks().get(0).getDescription());
//
//    }
//}