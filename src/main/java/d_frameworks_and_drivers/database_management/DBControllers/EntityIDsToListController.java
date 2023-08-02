package d_frameworks_and_drivers.database_management.DBControllers;

import a_enterprise_business_rules.entities.*;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.project_selection_gateways.IEntityIDsToList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
public class EntityIDsToListController implements IEntityIDsToList {
    /**
     * This returns a concatenated string of column IDs
     * which makes it able to be put into the Database
     * @param project
     * @return String of Concatenated Column IDs
     */
    public String EntityIDsToList(ProjectModel project) {
        List<ColumnModel> columnList = project.getColumnModels();
        String columnListString = "";

        for ( ColumnModel col: columnList ) {
            columnListString.join(", ", col.getID().toString());
        }
        return columnListString;
    }

    /**
     * This returns a concatenated string of Task IDs
     * which makes it able to be put into the Database
     * @param columns
     * @return String of Concatenated Task IDs
     */
    public String EntityIDsToList(ColumnModel columns) {
        List<TaskModel> taskList = columns.getTaskModels();
        String taskListString = "";

        for ( TaskModel task: taskList ) {
            taskListString.join(", ", task.getID().toString());
        }
        return taskListString;
    }
}

/*
String csvFilePath = "DatabaseFiles/Projects/Projects.csv"; // Replace with the path to your CSV file

        // Sample list to store in the CSV column
        List<String> columnIdList = project.getColumns();
        String columnIdListString = "";
        try (FileWriter fileWriter = new FileWriter(csvFilePath);
             CSVPrinter csvPrinter = new CSVPrinter(
                     fileWriter,
                     CSVFormat.RFC4180.
                             withQuoteMode(CSVFormat.
                                     DEFAULT.getQuoteMode()))) {

            columnIdListString.join(", ", columnIdList);
            // Write the list to the CSV column as a single entry
            csvPrinter.printRecord("","","", columnIdListString);

            // Flush and close the CSVPrinter
            csvPrinter.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return columnIdListString;
 */