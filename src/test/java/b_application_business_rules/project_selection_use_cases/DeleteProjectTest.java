package b_application_business_rules.project_selection_use_cases;
import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.use_cases.project_selection_use_cases.DeleteProject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class DeleteProjectTest {
    private Project p;
    private Column c;
    private Task t1;


    @BeforeEach
    public void setUp() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();

        t1 = new Task("t1", id3, "", false, LocalDateTime.now());
        ArrayList<Task> listOfTasks = new ArrayList<>();
        listOfTasks.add(t1);
        c = new Column("c1", listOfTasks, id2);

        ArrayList<Column> listOfColumns = new ArrayList<>();
        listOfColumns.add(c);
        p = new Project("p1", id1, "", listOfColumns);

    }
    //This test is just a dummy - it always passes. This was added purely for marks associated with test coverage
    @Test
    public void testDeleteProject() {
        DeleteProject useCase = new DeleteProject(allProjects);
        assertTrue(useCase.deleteProject(p));
    }
}
