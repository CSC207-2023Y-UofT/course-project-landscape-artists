package d_frameworks_and_drivers.database_management.DBControllers;

import b_application_business_rules.use_cases.project_selection_gateways.IDBSearch;

import com.opencsv.CSVReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * The DBManagerSearchController class provides methods to search for specific records in the database.
 */
public class DBManagerSearchController implements IDBSearch {

    /**
     * Searches Column.csv for the entry with the UUID matching the id parameter
     * then reads that line and converts it to an arraylist, with first element containing UUID,
     * second element containing name and third containing list of task UUIDs
     * @param id
     * @return columnInfo
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
     * Searches for project information using the given ID.
     *
     * @param id The ID of the project to search for.
     * @return An ArrayList containing project information.
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
     * Searches Task.csv for the entry with the UUID matching the id parameter
     * then reads that line and converts it to an arraylist, with first element containing UUID,
     * second element containing name and third containing description, fourth containing completion status
     * and fifth having due date.
     * @param id
     * @return
     */
    public ArrayList<String> DBTaskSearch(String id) {

        ArrayList<String> taskInfo = new ArrayList<>();
        String csvFilePath = "src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Tasks/Tasks.csv";

        try  {
            FileReader fileReader = new FileReader(csvFilePath);
            CSVReader csvReader = new CSVReader(fileReader);
            System.out.println("CSV READER " + csvReader);
            List<String[]> records = csvReader.readAll();
            System.out.println("RECORD RECEIVED FROM CSV READER " + records.toString() );
            // If this is empty, then CSVREADER IS THE ISSUE OR FILE READER OF FILEPATH.

            for(String[] record : records) {
                 System.out.println("CSV RECORD " + record.toString()); // Print the record if needed
                 String recordId = record[0]; // Assuming the ID is in the first column

                System.out.println("recordId.equals(id.trim() " + recordId.equals(id.trim()));
                System.out.println("recordId " + recordId);
                System.out.println("ID Given " + id);


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
        System.out.println(taskInfo);
        return taskInfo;
    }

}
