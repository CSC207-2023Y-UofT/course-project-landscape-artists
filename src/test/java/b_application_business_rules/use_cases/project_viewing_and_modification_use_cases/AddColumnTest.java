package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.TaskModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AddColumnTest {
    private Project p;

    @BeforeEach
    public void setUp() {
        UUID projectID = UUID.randomUUID();
        ArrayList<Column> listOfColumns = new ArrayList<Column>();
        p = new Project("p1", projectID, "", listOfColumns); //Initialize new project
    }

    @Test
    public void testAddColumn() {
        UUID taskID  = UUID.randomUUID();

        UUID columnID  = UUID.randomUUID();
        ColumnModel c = new ColumnModel("c1", new ArrayList<>(), columnID ); // Initialize new column

        AddColumn addColumnUseCase = new AddColumn(p);

        addColumnUseCase.addColumn(c);

        assertEquals("c1", p.getColumns().get(0).getName());
    }
}