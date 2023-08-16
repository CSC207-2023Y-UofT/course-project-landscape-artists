package d_frameworks_and_drivers.database_management.DatabaseInitializer;


import com.opencsv.CSVReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests if ProjectDBInitializer initializes Projects.csv file with correct format.
 */
public class ProjectDBInitializerTest {
    private static final String CSV_FILE_PATH = "src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Projects/Projects.csv";


    @Test
    public void testProjectDBInitializer() {
        // Check if the CSV file was created and has the expected format
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            String[] header = reader.readNext();
            assertArrayEquals(new String[]{"ProjectID", "Name", "Description", "Column ID's"}, header);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

