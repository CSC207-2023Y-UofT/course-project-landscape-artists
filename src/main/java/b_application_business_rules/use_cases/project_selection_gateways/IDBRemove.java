package b_application_business_rules.use_cases.project_selection_gateways;

import java.util.UUID;

public interface IDBRemove {
    void DBRemoveProject(UUID uuid);
    void DBRemoveTask(UUID uuid);
    void DBRemoveColumn(UUID uuid);
}
