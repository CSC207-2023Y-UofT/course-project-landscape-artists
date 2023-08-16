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

public class DeleteColumnTest {
    private Project p;

    @BeforeEach
    public void setUp() {
        UUID projectID = UUID.randomUUID();
        p = new Project("p1", projectID, "", new ArrayList<>()); //Initialize new project
    }

    @Test
    public void testDeleteColumn() {

        UUID columnID  = UUID.randomUUID();
        ColumnModel c = new ColumnModel("c1", new ArrayList<>(), columnID ); // Initialize new column

        AddColumn addColumnUseCase = new AddColumn(p);
        addColumnUseCase.addColumn(c);

        assertEquals(columnID, p.getColumns().get(0).getID());

        DeleteColumn deleteColumnUseCase = new DeleteColumn(p);
        deleteColumnUseCase.deleteColumn(columnID);

        assertEquals(0, p.getColumns().size());
    }

}