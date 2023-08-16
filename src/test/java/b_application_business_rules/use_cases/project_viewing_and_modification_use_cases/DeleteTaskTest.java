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
//import static org.junit.jupiter.api.Assertions.assertEquals;
//public class DeleteTaskTest {
//    private Project p;
//    private Column c;
//    private Task t;
//
//    @BeforeEach
//    public void setUp() {
//        UUID projectID = UUID.randomUUID();
//        UUID columnID  = UUID.randomUUID();
//        UUID taskID  = UUID.randomUUID();
//        t = new Task("t1", taskID, "", false, LocalDateTime.now()); // Initialize new task
//        c = new Column("c1", new ArrayList<Task>(), columnID ); // Initialize new column
//        c.getTasks().add(t); // Add task to column
//        p = new Project("p1", projectID, "", new ArrayList<Column>()); // Initialize new project
//        p.getColumns().add(c); // Add column to project
//    }
//
//    @Test
//    public void testDeleteTask() {
//        TaskModel taskModel = new TaskModel(t);
//
//        DeleteTask deleteTaskUseCase = new DeleteTask(p);
//
//        deleteTaskUseCase.deleteTask(p.getColumns().get(0).getID(), taskModel);
//
//        assertEquals(0, p.getColumns().get(0).getTasks().size());
//    }
//
//}