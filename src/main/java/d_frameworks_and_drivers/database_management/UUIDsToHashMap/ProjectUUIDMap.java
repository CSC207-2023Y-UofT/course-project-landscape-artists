package d_frameworks_and_drivers.database_management.UUIDsToHashMap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class reads the Projects.csv file and returns a 2D arrayList containing the
 * String attributes of each project.
 *
 */

public class ProjectUUIDArray {
    public static ArrayList<ArrayList<String>> convertCsvToArrayList() {
        String csvFilePath = "DatabaseFiles/Projects/Projects.csv";
        ArrayList<ArrayList<String>> csvDataArray = new ArrayList<>();

        // Create a CSVParser to read the CSV file
        try (FileReader fileReader = new FileReader(csvFilePath);
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader())) {

            // Iterate through each CSV record and map the headers to the HashMap
            for (CSVRecord csvRecord : csvParser) {
                ArrayList<String> projectInfo = new ArrayList<>();
                //

                projectInfo.add(csvRecord.get(0));
                projectInfo.add(csvRecord.get(1));
                projectInfo.add(csvRecord.get(2));
                projectInfo.add(csvRecord.get(3));
                projectInfo.add(csvRecord.get(4));// Assuming the second header is in the 1st column

                csvDataArray.add(projectInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return csvDataArray;
    }
