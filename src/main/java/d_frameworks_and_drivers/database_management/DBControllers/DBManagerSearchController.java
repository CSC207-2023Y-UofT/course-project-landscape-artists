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


public class DBManagerSearchController implements IDBSearch {

    /**
     * Searches Column.csv for the entry with the UUID matching the id parameter
     * then reads that line and converts it to an arraylist, with first element containing UUID,
     * second element containing name and third containing list of task UUIDs
     * @param id
     * @return columnInfo
     */
    public ArrayList<String> DBColumnSearch(String id) {
        System.out.println("Search id");
        System.out.println(id);
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
                System.out.println("-----------");
                System.out.println(result);
                System.out.println(id.trim());
                System.out.println(firstHeaderValue.equals(id.trim()));
                System.out.println("-----------");

                // Once matching ID is found, column attributes are saved and exit loop
                if (firstHeaderValue.equals(id.trim())) {
                    System.out.println("INSIDE IF FOR COLUMN SEARCH");

                    columnInfo.add(result.get(0).toString());
                    columnInfo.add(result.get(1).toString());
                    columnInfo.add(result.get(2).toString());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(columnInfo);
        return columnInfo;
    }

    /**
     *
     * @param id
     * @return
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
        System.out.println(projectInfo);

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
//        String csvFilePath = "src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Tasks/Tasks.csv";
//        System.out.println("DBTaskSearch");
//        System.out.println(id);
//
//        // Opening and reading through the Column.csv file
//        try (FileReader fileReader = new FileReader(csvFilePath);
//             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader())) {
//
//            // Iterate through each CSV record until the matching ID is found
//            for (CSVRecord csvRecord : csvParser.getRecords()) {
//
//                ArrayList result = new ArrayList<>(List.of(csvRecord.values()));
//                String firstHeaderValue = result.get(0).toString();
//                System.out.println("result result result result result");
//                System.out.println(result);
//                // Once matching ID is found, column attributes are saved and exit loop
//                System.out.println("firstHeaderValue " + firstHeaderValue);
//                System.out.println("id " + id);
//                System.out.println("firstHeaderValue.equals(id) " + firstHeaderValue.equals(id));
//                if (firstHeaderValue.equals(id)) {
//                    System.out.println("TASK INFO ADD WAS RAN");
//                    taskInfo.add(csvRecord.get(0));
//                    taskInfo.add(csvRecord.get(1));
//                    taskInfo.add(csvRecord.get(2));
//                    taskInfo.add(csvRecord.get(3));
//                    taskInfo.add(csvRecord.get(4));
//                    break;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(taskInfo);
//        return taskInfo;
//    }
//        ArrayList<String> taskInfo = new ArrayList<>();
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

//        ArrayList<String> matchingEntry = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] columns = line.split(",");
//                if (columns.length > 0 && columns[0].equals(id)) {
//                    System.out.println("CSV ENTRY "+ columns);
//                    Collections.addAll(matchingEntry, columns);
//                    break;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Task Search Return "+matchingEntry);
//        return matchingEntry;

    }

}
