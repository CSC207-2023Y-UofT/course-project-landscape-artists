package b_application_business_rules.use_cases.project_selection_use_cases;
import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class CreateProjectTest {
    private ArrayList<Column> listOfColumns = new ArrayList<>();
    private Column c;
    private Task t1;

    //private ProjectModel uP;

    @BeforeEach
    public void setUp() {
        //UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        //UUID id4 = UUID.randomUUID();
        t1 = new Task("t1", id3, "", false, LocalDateTime.now());
        ArrayList<Task> listOfTasks = new ArrayList<>();
        listOfTasks.add(t1);
        c = new Column("c1", listOfTasks, id2);

        listOfColumns.add(c);

    }

    @Test
    public void testNewProject() {
        //uP.setName("p2");
        List<Project> allProjects = new ArrayList<>();
        CreateProject useCase = new CreateProject(allProjects);
        Project p1 =
                useCase.newProject("p1", UUID.randomUUID(), "", listOfColumns);
        assertTrue(p1.getName().equals("p1"));
        assertTrue(p1.getDescription().equals(""));

        assertTrue(allProjects.size() == 1);

    }
}
