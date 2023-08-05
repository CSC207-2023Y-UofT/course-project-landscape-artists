package d_frameworks_and_drivers.database_management.DBControllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import java.util.ArrayList;
import java.util.List;

class EntityIDsToListControllerTest {

    private EntityIDsToListController controller;

    @BeforeEach
    public void setUp() {
        controller = new EntityIDsToListController();
    }

    @AfterEach
    public void tearDown() {
        // Clean up any resources, if needed
        controller = null;
    }

    @Test
    public void testEntityIDsToListWithProjectModel() {
        // Create a sample project model with column models
        List<ColumnModel> columnModelList = new ArrayList<>();
//        columnModelList.add(new ColumnModel("Column1", "1"));
//        columnModelList.add(new ColumnModel("Column2", "2"));
//        columnModelList.add(new ColumnModel("Column3", "3"));
//        ProjectModel projectModel = new ProjectModel("Project1", columnModelList);

        // Expected concatenated column IDs
        String expected = "1, 2, 3";

        // Call the method to get the result
        String result = controller.EntityIDsToList(projectModel);

        // Assert the result
        assertEquals(expected, result);
    }

    @Test
    public void testEntityIDsToListWithColumnModel() {
        // Create a sample column model with task models
        List<TaskModel> taskModelList = new ArrayList<>();
//        taskModelList.add(new TaskModel("Task1", "100"));
//        taskModelList.add(new TaskModel("Task2", "101"));
//        taskModelList.add(new TaskModel("Task3", "102"));
//        ColumnModel columnModel = new ColumnModel("Column1", taskModelList);

        // Expected concatenated task IDs
        String expected = "100, 101, 102";

        // Call the method to get the result
        String result = controller.EntityIDsToList(columnModel);

        // Assert the result
        assertEquals(expected, result);
    }

    @Test
    public void testEntityIDsToListWithEmptyList() {
        // Create an empty column model
//        ColumnModel emptyColumnModel = new ColumnModel("Empty Column", new ArrayList<>());

        // Expected empty string for an empty list
        String expected = "";

        // Call the method to get the result
        String result = controller.EntityIDsToList(emptyColumnModel);

        // Assert the result
        assertEquals(expected, result);
    }

    @Test
    void testEntityIDsToList() {
    }
}