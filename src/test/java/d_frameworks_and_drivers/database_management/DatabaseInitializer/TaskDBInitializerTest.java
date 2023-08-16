package d_frameworks_and_drivers.database_management.DatabaseInitializer;


import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests if TaskDBInitializer initializes Tasks.csv file with correct format.
 */


public class TaskDBInitializerTest {
    @Test
    public void testTaskDBInitializer() {
        // Create an instance of TaskDBInitializer to initialize the CSV file
        TaskDBInitializer initializer = new TaskDBInitializer();

        // Check if the CSV file was created and has the expected content
        String csvFilePath = "src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Tasks/Tasks.csv";
        File file = new File(csvFilePath);
        assertTrue(file.exists(), "CSV file should exist");

        // Check if the CSV file has the expected header
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] header = reader.readNext();
            assertArrayEquals(new String[]{"TaskID", "Name", "Description", "Completion Status", "Due Date"}, header);

            // Add more assertions if necessary
        } catch (IOException e) {
            fail("Exception occurred: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}