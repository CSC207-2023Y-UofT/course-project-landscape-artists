package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;
/**
 * This TEST NEEDS TO SORT OUR THE CURRENT PROJECT DECLARATION IN THE ADDTASK USECASE
 */
import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.entity_models.TaskModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddTaskTest {
    private Project p;
    private Column c;
    private TaskModel t;

    @BeforeEach
    public void setUp() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();

        //set up new project to act as current project
        c = new Column("c", new ArrayList<Task>(), id2);

        ArrayList<Column> listOfColumns = new ArrayList<Column>();
        p = new Project("p", id1, "", listOfColumns);
        listOfColumns.add(c);

        t = new TaskModel("t", id3, "", false, LocalDateTime.now());
    }

    @Test
    public void testAddTask() {

        AddTask addTaskUseCase = new AddTask(c.getID(), t, p);

        addTaskUseCase.addTask();

        assertEquals("t", c.getTasks().get(0).getName());
    }

}
