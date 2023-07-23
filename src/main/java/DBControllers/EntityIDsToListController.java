package DBControllers;

import Entities.Column;
import Entities.Project;
import Entities.Task;
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
    public String EntityIDsToList(Project project) {
        List<Column> columnList = project.getColumns();
        String columnListString = "";

        for ( Column col: columnList ) {
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
    public String EntityIDsToList(Column columns) {
        List<Task> taskList = columns.getTasks();
        String taskListString = "";

        for ( Task task: taskList ) {
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