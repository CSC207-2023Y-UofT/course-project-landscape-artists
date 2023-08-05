package d_frameworks_and_drivers.database_management.DBControllers;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.project_selection_gateways.IDBInsert;
import b_application_business_rules.use_cases.project_selection_gateways.IDBSearch;
import com.opencsv.CSVWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.UUID;

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

                String firstHeaderValue = csvRecord.get(0);
                // Once matching ID is found, column attributes are saved and exit loop
                if (firstHeaderValue == id) {
                    columnInfo.add(csvRecord.get(0));
                    columnInfo.add(csvRecord.get(1));
                    columnInfo.add(csvRecord.get(2));
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return columnInfo;
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
        String csvFilePath = "DatabaseFiles/Task.csv";

        // Opening and reading through the Column.csv file
        try (FileReader fileReader = new FileReader(csvFilePath);
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader())) {

            // Iterate through each CSV record until the matching ID is found
            for (CSVRecord csvRecord : csvParser) {

                String firstHeaderValue = csvRecord.get(0);
                // Once matching ID is found, column attributes are saved and exit loop
                if (firstHeaderValue == id) {
                    taskInfo.add(csvRecord.get(0));
                    taskInfo.add(csvRecord.get(1));
                    taskInfo.add(csvRecord.get(2));
                    taskInfo.add(csvRecord.get(3));
                    taskInfo.add(csvRecord.get(4));
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return taskInfo;
    }
}
