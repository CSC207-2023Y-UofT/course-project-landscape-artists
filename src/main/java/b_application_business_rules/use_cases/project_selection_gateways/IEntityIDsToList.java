package b_application_business_rules.use_cases.project_selection_gateways;
import b_application_business_rules.entity_models.*;

public interface IEntityIDsToList {
    /**
     * This returns a concatenated string of column IDs of the given  model.
     */
    String EntityIDsToList(ProjectModel projectModel);
    /**
     * This returns a concatenated string of Task IDs of the given column model.
     */
    String EntityIDsToList(ColumnModel columnModel);
}
