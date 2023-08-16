package b_application_business_rules.use_cases.project_selection_gateways;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;

import java.util.List;

/**
 * This interface defines methods for mapping lists of database IDs to lists of corresponding model objects.
 * It provides methods to retrieve lists of ColumnModel, TaskModel, and ProjectModel objects based on lists of their IDs.
 */
public interface IDbIdToModelList {

    /**
     * Retrieves a list of ColumnModel objects based on the provided list of IDs.
     *
     * @param IDlist The list of unique identifiers of ColumnModels to retrieve.
     * @return The corresponding list of ColumnModel objects. The order of objects in the list may vary.
     */
    List<ColumnModel> IdToColumnModelList(List<String> IDlist);

    /**
     * Retrieves a list of TaskModel objects based on the provided list of IDs.
     *
     * @param IDlist The list of unique identifiers of TaskModels to retrieve.
     * @return The corresponding list of TaskModel objects. The order of objects in the list may vary.
     */
    List<TaskModel> IdToTaskModelList(List<String> IDlist);

    /**
     * Retrieves a list of ProjectModel objects based on the provided list of IDs.
     *
     * @param IDlist The list of unique identifiers of ProjectModels to retrieve.
     * @return The corresponding list of ProjectModel objects. The order of objects in the list may vary.
     */
    List<ProjectModel> IdToProjectModelList(List<String> IDlist);

}
