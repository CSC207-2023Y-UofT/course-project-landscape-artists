package d_frameworks_and_drivers.database_management.DBControllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.factories.ColumnModelFactory;
import b_application_business_rules.factories.ProjectModelFactory;
import b_application_business_rules.factories.TaskModelFactory;
import d_frameworks_and_drivers.database_management.ProjectUUIDArray;
import c_interface_adapters.DBAdapterInterface;

import java.time.LocalDateTime;
import java.util.*;

class EntityIDsToModelControllerTest {

    private EntityIDstoModelController controller;

    @BeforeEach
    public void setUp() {
        controller = new EntityIDstoModelController();
    }

    @AfterEach
    public void tearDown() {
        controller = null;
    }

    @Test
    public void testIDstoProjectModelList() {
        // Prepare a sample list of project UUIDs (You may need to mock ProjectUUIDArray if needed)
        ArrayList<ArrayList<String>> projectListString = new ArrayList<>();
        ArrayList<String> projectInfo1 = new ArrayList<>(Arrays.asList(
                "a123e456-cd78-9abc-1d23-48264301b44a",
                "Project 1",
                "This is project 1 description",
                "b123e456-cd78-9abc-1d23-48264301b44b,c123e456-cd78-9abc-1d23-48264301b44c"
        ));
        projectListString.add(projectInfo1);



        // Call the method to get the result
        List<ProjectModel> result = controller.IDstoProjectModelList();

        // Expected ProjectModel
        UUID projectID = UUID.fromString("a123e456-cd78-9abc-1d23-48264301b44a");
        String projectName = "Project 1";
        String projectDescription = "This is project 1 description";
        UUID column1ID = UUID.fromString("b123e456-cd78-9abc-1d23-48264301b44b");
        UUID column2ID = UUID.fromString("c123e456-cd78-9abc-1d23-48264301b44c");

        // Create expected ColumnModel objects
        ColumnModel columnModel1 = ColumnModelFactory.create("Column 1", Collections.emptyList(), column1ID);
        ColumnModel columnModel2 = ColumnModelFactory.create("Column 2", Collections.emptyList(), column2ID);

        // Create expected ProjectModel object
        List<ColumnModel> columnModelList = new ArrayList<>(Arrays.asList(columnModel1, columnModel2));
        ProjectModel expectedProjectModel = ProjectModelFactory.create(projectName, projectID, projectDescription, columnModelList);

        // Assert the result
        assertEquals(1, result.size());
        assertEquals(expectedProjectModel, result.get(0));
    }

    @Test
    void IDstoProjectModelList() {
    }
}