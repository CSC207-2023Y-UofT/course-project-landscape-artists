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

    @BeforeEach
    public void setUp() {
        UUID projectID = UUID.randomUUID();
        UUID columnID  = UUID.randomUUID();
        c = new Column("c1", new ArrayList<Task>(), columnID ); // Initialize new column
        ArrayList<Column> listOfColumns = new ArrayList<Column>();
        listOfColumns.add(c); // Add column to list of columns
        p = new Project("p1", projectID, "", listOfColumns); //Initialize new project
    }

    @Test
    public void testAddTask() {
        UUID taskID  = UUID.randomUUID();

        TaskModel t = new TaskModel("t1", taskID, "", false, LocalDateTime.now()); // Initialize TaskModel

        AddTask addTaskUseCase = new AddTask(p);

        addTaskUseCase.addTask(c.getID(), t);

        assertEquals("t1", c.getTasks().get(0).getName());
    }

}