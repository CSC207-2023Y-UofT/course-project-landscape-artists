package b_application_business_rules.use_cases.project_selection_gateways;



import a_enterprise_business_rules.entities.*;

import java.util.UUID;

public interface IDBRemove {
    public void DBRemove(Project project, UUID uuid);
    public void DBRemove(Task task, UUID uuid);
    public void DBRemove(Column column, UUID uuid);
}
