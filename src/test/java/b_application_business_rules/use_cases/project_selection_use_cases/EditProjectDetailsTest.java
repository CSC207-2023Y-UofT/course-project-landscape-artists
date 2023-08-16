package b_application_business_rules.use_cases.project_selection_use_cases;
import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.use_cases.project_selection_use_cases.EditProjectDetails;
import org.junit.jupiter.api.Assertions;
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

        // implementation problems
//    @Test
//    public void testSetName() {
//        UUID uuid = UUID.randomUUID();
//        Project p = new Project("p1", uuid, "D1", null );
//
//        UUID idOfP = p.getID();
//
//        List<Project> allProjects = new ArrayList<>();
//        allProjects.add(p);
//
//        EditProjectDetails useCase = new EditProjectDetails(allProjects, idOfP);
//
//        useCase.setNameAndDescription("p2", "Hello");
//
//        Assertions.assertEquals("p2", p.getName());
//        Assertions.assertEquals("Hello", p.getDescription());
//
//    }
}

