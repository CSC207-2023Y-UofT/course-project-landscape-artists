package d_frameworks_and_drivers.database_management.DBControllers;

import b_application_business_rules.use_cases.project_selection_gateways.IDBSearch;

import com.opencsv.CSVReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


import java.io.FileReader;
import java.util.*;

/**
 * The DBManagerSearchController class implements the IDBSearch interface and is responsible for searching the database
 * for specific entries based on their IDs and returning relevant information.
 */
public class DBManagerSearchController implements IDBSearch {

    /**
     * Searches the "Columns.csv" file for the entry with the UUID matching the provided id parameter.
     * Reads the corresponding line and converts it to an ArrayList, where the first element contains the UUID,
     * the second element contains the name, and the third element contains a list of task UUIDs.
     *
     * @param id The UUID to search for.
     * @return An ArrayList containing the UUID, name, and task UUIDs of the found column entry.
     */
    public ArrayList<String> DBColumnSearch(String id) {
        EntityIDsToListController entityIDsToListController = new EntityIDsToListController();
        ArrayList<String> columnInfo = new ArrayList<>();
        String csvFilePath = "src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Columns/Columns.csv";

        // Opening and reading through the Column.csv file
        try (FileReader fileReader = new FileReader(csvFilePath);
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader())) {

            // Iterate through each CSV record until the matching ID is found
            for (CSVRecord csvRecord : csvParser) {

                ArrayList result = new ArrayList<>(csvRecord.toList());
                String firstHeaderValue = result.get(0).toString();

                // Once matching ID is found, column attributes are saved and exit loop
                if (firstHeaderValue.equals(id.trim())) {
                    columnInfo.add(result.get(0).toString());
                    columnInfo.add(result.get(1).toString());
                    columnInfo.add(result.get(2).toString());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return columnInfo;
    }

    /**
     * Searches the "Projects.csv" file for the entry with the UUID matching the provided id parameter.
     * Reads the corresponding line and converts it to an ArrayList, where the elements contain project-related information.
     *
     * @param id The UUID to search for.
     * @return An ArrayList containing project-related information of the found project entry.
     */
    public ArrayList<String> DBProjectSearch(String id) {
        ArrayList<String> projectInfo = new ArrayList<>();
        String csvFilePath = "src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Projects/Projects.csv";

        // Opening and reading through the Column.csv file
        try (FileReader fileReader = new FileReader(csvFilePath);
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader())) {

            // Iterate through each CSV record until the matching ID is found
            for (CSVRecord csvRecord : csvParser) {
                ArrayList result = new ArrayList<>(List.of(csvRecord.values()));
                String firstHeaderValue = result.get(0).toString();
                // Once matching ID is found, column attributes are saved and exit loop
                if (firstHeaderValue.equals(id)) {
                    projectInfo.add(result.get(0).toString());
                    projectInfo.add(result.get(1).toString());
                    projectInfo.add(result.get(2).toString());
                    projectInfo.add(result.get(3).toString());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return projectInfo;
    }

    /**
     * Searches the "Tasks.csv" file for the entry with the UUID matching the provided id parameter.
     * Reads the corresponding line and converts it to an ArrayList, where the elements contain task-related information.
     *
     * @param id The UUID to search for.
     * @return An ArrayList containing task-related information of the found task entry.
     */
    public ArrayList<String> DBTaskSearch(String id) {

        ArrayList<String> taskInfo = new ArrayList<>();
        String csvFilePath = "src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Tasks/Tasks.csv";

        try  {
            FileReader fileReader = new FileReader(csvFilePath);
            CSVReader csvReader = new CSVReader(fileReader);
            List<String[]> records = csvReader.readAll();

            for(String[] record : records) {
                 String recordId = record[0]; // Assuming the ID is in the first column

                 if (recordId.equals(id.trim())) {
                     taskInfo.add(record[0]);
                     taskInfo.add(record[1]);
                     taskInfo.add(record[2]);
                     taskInfo.add(record[3]);
                     taskInfo.add(record[4]);
                     break; // Exit loop once matching ID is found
                 }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskInfo;
    }

}
