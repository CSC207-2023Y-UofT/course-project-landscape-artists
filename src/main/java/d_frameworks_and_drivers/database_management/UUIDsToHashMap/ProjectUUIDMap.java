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
    public static ArrayList<ArrayList<String>> convertCsvToHashMap() {
        String csvFilePath = "DatabaseFiles/Projects/Projects.csv";
        Map<String, String> csvDataMap = new HashMap<>();

        // Create a CSVParser to read the CSV file
        try (FileReader fileReader = new FileReader(csvFilePath);
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader())) {

            // Iterate through each CSV record and map the headers to the HashMap
            for (CSVRecord csvRecord : csvParser) {
                // Use the first header as the key and the second header as the value in the HashMap
                String firstHeaderValue = csvRecord.get(0); // Assuming the first header is in the 0th column
                String secondHeaderValue = csvRecord.get(1); // Assuming the second header is in the 1st column

                csvDataMap.put(firstHeaderValue, secondHeaderValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return csvDataMap;
    }
