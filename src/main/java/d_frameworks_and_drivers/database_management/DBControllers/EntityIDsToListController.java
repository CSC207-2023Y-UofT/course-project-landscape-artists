package d_frameworks_and_drivers.database_management.DBControllers;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;

import b_application_business_rules.use_cases.project_selection_gateways.IEntityIDsToList;

import java.util.List;

public class EntityIDsToListController implements IEntityIDsToList {
    /**
     * This returns a concatenated string of column IDs
     * which makes it able to be put into the Database of projects
     * for the individual project's Column IDs field as one csv value.
     * @param projectModel Project model from which column IDs are retrieved
     * @return String of Concatenated Column IDs
     */
    public String EntityIDsToList(ProjectModel projectModel) {
        List<ColumnModel> columnModelList = projectModel.getColumnModels();
        String columnModelListString = "";

        for ( ColumnModel col: columnModelList ) {
            String.join(", ", col.getID().toString());
        }
        return columnModelListString;
    }

    /**
     * This returns a concatenated string of Task IDs
     * which makes it able to be put into the Database of columns
     * for the individual column's Task IDs field as one csv value.
     * @param columnModel Column model from which task IDs are retrieved
     * @return String of Concatenated Task IDs
     */
    public String EntityIDsToList(ColumnModel columnModel) {
        List<TaskModel> taskModelList = columnModel.getTaskModels();
        String taskModelListString = "";

        for (TaskModel task: taskModelList ) {
            String.join(", ", task.getID().toString());
        }
        return taskModelListString;
    }



}

// Old version
//String csvFilePath = "DatabaseFiles/Projects/Projects.csv"; // Replace with the path to your CSV file
//
//        // Sample list to store in the CSV column
//        List<String> columnIdList = project.getColumns();
//        String columnIdListString = "";
//        try (FileWriter fileWriter = new FileWriter(csvFilePath);
//             CSVPrinter csvPrinter = new CSVPrinter(
//                     fileWriter,
//                     CSVFormat.RFC4180.
//                             withQuoteMode(CSVFormat.
//                                     DEFAULT.getQuoteMode()))) {
//
//            columnIdListString.join(", ", columnIdList);
//            // Write the list to the CSV column as a single entry
//            csvPrinter.printRecord("","","", columnIdListString);
//
//            // Flush and close the CSVPrinter
//            csvPrinter.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return columnIdListString;
