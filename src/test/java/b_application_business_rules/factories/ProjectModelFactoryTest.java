package b_application_business_rules.factories;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.factories.ProjectModelFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProjectModelFactoryTest {

    @Test
    public void testCreateProjectModel() {
        String projectName = "Test Project";
        UUID projectId = UUID.randomUUID();
        String projectDescription = "Project Description";

        List<ColumnModel> columnModels = new ArrayList<>();
        ColumnModel column1 = new ColumnModel("Column 1", new ArrayList<>(), UUID.randomUUID());
        ColumnModel column2 = new ColumnModel("Column 2", new ArrayList<>(), UUID.randomUUID());
        columnModels.add(column1);
        columnModels.add(column2);

        ProjectModel projectModel = ProjectModelFactory.create(projectName, projectId, projectDescription, columnModels);

        assertNotNull(projectModel);
        assertEquals(projectName, projectModel.getName());
        assertEquals(projectId, projectModel.getID());
        assertEquals(projectDescription, projectModel.getDescription());
        assertEquals(columnModels, projectModel.getColumnModels());
    }

    @Test
    public void testCreateEmptyProjectModel() {
        String projectName = "Empty Project";
        UUID projectId = UUID.randomUUID();
        String projectDescription = "Empty Project Description";

        List<ColumnModel> emptyColumnModels = new ArrayList<>();

        ProjectModel projectModel = ProjectModelFactory.create(projectName, projectId, projectDescription, emptyColumnModels);

        assertNotNull(projectModel);
        assertEquals(projectName, projectModel.getName());
        assertEquals(projectId, projectModel.getID());
        assertEquals(projectDescription, projectModel.getDescription());
        assertEquals(emptyColumnModels, projectModel.getColumnModels());
    }
}
