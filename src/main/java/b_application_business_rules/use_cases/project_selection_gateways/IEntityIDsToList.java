package b_application_business_rules.use_cases.project_selection_gateways;
import a_enterprise_business_rules.entities.*;
import b_application_business_rules.entity_models.*;

public interface IEntityIDsToList {
    String EntityIDsToList(ProjectModel projectModel);
    String EntityIDsToList(ColumnModel columnModel);
}
