package b_application_business_rules.use_cases.project_selection_gateways;
import a_enterprise_business_rules.entities.*;

public interface IEntityIDsToList {
    public String EntityIDsToList(Project project);
    public String EntityIDsToList(Column columns);
}
