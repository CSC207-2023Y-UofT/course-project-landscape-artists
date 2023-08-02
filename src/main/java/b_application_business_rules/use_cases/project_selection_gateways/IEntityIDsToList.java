package b_application_business_rules.use_cases.project_selection_gateways;
import a_enterprise_business_rules.entities.*;
import b_application_business_rules.entity_models.*;

public interface IEntityIDsToList {
    public String EntityIDsToList(ProjectModel project);
    public String EntityIDsToList(ColumnModel columns);
}
