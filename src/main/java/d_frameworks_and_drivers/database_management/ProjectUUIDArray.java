package d_frameworks_and_drivers.database_management;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.util.ArrayList;

/**
 * This class reads the Projects.csv file and returns a 2D arrayList containing the
 * String attributes of each project.
 *
 */
public class ProjectUUIDArray {
    /**
     * Converts the csv format of projects to a 2D arraylist
     * @return a 2D arraylist
     */
    public static ArrayList<ArrayList<String>> convertCsvToArrayList() {
        String csvFilePath = "DatabaseFiles/Projects/Projects.csv";
        ArrayList<ArrayList<String>> csvDataArray = new ArrayList<>();

        // Create a CSVParser to read the CSV file
        // Prevent fileReader, csvParser resource leaks by closing after try block
        try (FileReader fileReader = new FileReader(csvFilePath);
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader().withNullString(""))) {

            // Iterate through each CSV line
            for (CSVRecord csvRecord : csvParser) {
                ArrayList<String> projectInfo = new ArrayList<>();

                //Add in the Project attributes a new Arraylist  // Extend with isMapped(String) and getHeaderMap()
                projectInfo.add(csvRecord.get(0)); // "ProjectID",
                projectInfo.add(csvRecord.get(1));  // "Name",
                projectInfo.add(csvRecord.get(2)); // "Description",
                projectInfo.add(csvRecord.get(3)); // "Column ID's"


                //Place the newly made arrayList into another ArrayList --> 2D arraylist
                csvDataArray.add(projectInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return csvDataArray;
    }
}
