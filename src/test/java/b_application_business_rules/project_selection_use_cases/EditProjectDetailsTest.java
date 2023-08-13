package b_application_business_rules.project_selection_use_cases;
import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.use_cases.project_selection_use_cases.EditProjectDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class EditProjectDetailsTest {
    private Project p;
    private Column c;
    private Task t1;

    //private ProjectModel uP;

    @BeforeEach
    public void setUp() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        //UUID id4 = UUID.randomUUID();
        t1 = new Task("t1", id3, "", false, LocalDateTime.now());
        ArrayList<Task> listOfTasks = new ArrayList<>();
        listOfTasks.add(t1);
        c = new Column("c1", listOfTasks, id2);
        ArrayList<Column> listOfColumns = new ArrayList<>();
        listOfColumns.add(c);
        //p = new Project("p1", id1, "", listOfColumns);
        p = new Project("p1", id1, "", listOfColumns);
        //uP = ProjectModelFactory.create("p1", id1, "", listOfColumns);

    }

    @Test
    public void testSetName() {
        //uP.setName("p2");
        UUID idOfP = p.getID();
        List<Project> allProjects = new ArrayList<>();
        allProjects.add(p);
        EditProjectDetails useCase = new EditProjectDetails(allProjects, idOfP);
        useCase.setNameAndDescription("p2", "Hello");
        assertTrue(p.getName().equals("p2"));
        assertTrue(p.getDescription().equals("Hello"));

    }
    }

