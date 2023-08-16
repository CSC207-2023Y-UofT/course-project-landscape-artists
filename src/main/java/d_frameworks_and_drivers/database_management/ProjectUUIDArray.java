package d_frameworks_and_drivers.database_management;

import com.opencsv.CSVReader;


import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
        String csvFilePath = "src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Projects/Projects.csv";
        ArrayList<ArrayList<String>> resultList = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath))) {
            String[] line;
            boolean skipHeader = true; // Flag to skip the first line (header)
            while ((line = csvReader.readNext()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                ArrayList<String> row = new ArrayList<>();
                for (String cell : line) {
                    row.add(cell);
                }
                resultList.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }
}
