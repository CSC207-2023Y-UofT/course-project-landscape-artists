package d_frameworks_and_drivers.database_management.DBControllers;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;

import b_application_business_rules.use_cases.project_selection_gateways.IEntityIDsToList;

import java.util.ArrayList;
import java.util.List;

public class EntityIDsToListController implements IEntityIDsToList {
    /**
     * This returns a concatenated string of column IDs
     * which makes it able to be put into the Database of projects
     * for the individual project's Column IDs field as one csv value.
     *
     * @param projectModel Project model from which column IDs are retrieved
     * @return String of Concatenated Column IDs
     */
    public String EntityIDsToList(ProjectModel projectModel) {
        List<ColumnModel> columnModelList = projectModel.getColumnModels();
        String columnModelListString = "";

        List<String> ids = new ArrayList<>();
        for ( ColumnModel col: columnModelList ) {

            ids.add(col.getID().toString());


        }
        columnModelListString = String.join(", ", ids);

        return columnModelListString;
    }

    /**
     * This returns a concatenated string of Task IDs
     * which makes it able to be put into the Database of columns
     * for the individual column's Task IDs field as one csv value.
     *
     * @param columnModel Column model from which task IDs are retrieved
     * @return String of Concatenated Task IDs
     */
    public String EntityIDsToList(ColumnModel columnModel) {
        List<TaskModel> taskModelList = columnModel.getTaskModels();
        String taskModelListString = "";
        if (taskModelList == null) {
            return null;

        } else {
            List<String> ids = new ArrayList<>();
            for (TaskModel task : taskModelList) {
                ids.add(task.getID().toString());

            }
            taskModelListString = String.join(", ", ids);

            return taskModelListString;
        }

    }

}