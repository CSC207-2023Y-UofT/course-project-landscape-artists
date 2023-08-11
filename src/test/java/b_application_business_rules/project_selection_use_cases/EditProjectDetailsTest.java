package b_application_business_rules.project_selection_use_cases;
import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.factories.ProjectModelFactory;
import b_application_business_rules.use_cases.project_selection_use_cases.EditProjectDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class EditProjectDetailsTest {
    private ProjectModel p;
    private ColumnModel c;
    private TaskModel t1;

    //private ProjectModel uP;

    @BeforeEach
    public void setUp() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        //UUID id4 = UUID.randomUUID();
        t1 = new TaskModel("t1", id3, "", false, LocalDateTime.now());
        ArrayList<TaskModel> listOfTasks = new ArrayList<>();
        listOfTasks.add(t1);
        c = new ColumnModel("c1", listOfTasks, id2);
        ArrayList<ColumnModel> listOfColumns = new ArrayList<>();
        listOfColumns.add(c);
        //p = new Project("p1", id1, "", listOfColumns);
        p = ProjectModelFactory.create("p1", id1, "", listOfColumns);
        //uP = ProjectModelFactory.create("p1", id1, "", listOfColumns);

    }

    @Test
    public void testSetName() {
        //uP.setName("p2");
        EditProjectDetails useCase = new EditProjectDetails(p);
        useCase.setName("p2");
        assertTrue(p.getName().equals("p2"));

    }
    @Test
    public void testSetDescription() {
        //uP.setName("p2");
        EditProjectDetails useCase = new EditProjectDetails(p);
        useCase.setDescription("Hello");
        assertTrue(p.getName().equals("Hello"));

    }
    }

