package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.entity_models.ColumnModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class EditColumnTest {
    private Project p;
    private Column c;
    @BeforeEach
    void setUp() {
        UUID projectID = UUID.randomUUID();

        p = new Project("p1", projectID, "", new ArrayList<>()); //Initialize new project
    }

    @Test
    public void testEditColumn() {

        UUID columnID = UUID.randomUUID();

        c = new Column("c1", new ArrayList<>(), columnID); // Initialize new column
        p.getColumns().add(c);

        UUID taskID = UUID.randomUUID();
        Task t = new Task("t1", taskID, "", false, LocalDateTime.now()); // Initialize TaskModel
        c.getTasks().add(t); // Add task to list of tasks

        Assertions.assertEquals("c1", p.getColumns().get(0).getName());
        Assertions.assertEquals("t1", p.getColumns().get(0).getTasks().get(0).getName());

        ColumnModel editedColumn = new ColumnModel("edited name", new ArrayList<>(), c.getID());

        EditColumn editColumnUseCase = new EditColumn(p);
        editColumnUseCase.setColumnName(editedColumn);

        Assertions.assertEquals("edited name", p.getColumns().get(0).getName());

        Assertions.assertEquals(1, p.getColumns().get(0).getTasks().size());
        Assertions.assertEquals("t1", p.getColumns().get(0).getTasks().get(0).getName());
    }
}