package b_application_business_rules.use_cases.project_selection_gateways;

import a_enterprise_business_rules.entities.*;

import java.util.UUID;

public interface IDBInsert {
    public void DBInsert(Project project);

    public void DBInsert(Column column);

    public void DBInsert(Task task);

    public void DBInsert(UUID uuid);

}
