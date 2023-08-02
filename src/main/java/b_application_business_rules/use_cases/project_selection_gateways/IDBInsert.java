package b_application_business_rules.use_cases.project_selection_gateways;

import b_application_business_rules.entity_models.*;

import java.util.UUID;

public interface IDBInsert {
    public void DBInsert(ProjectModel project);

    public void DBInsert(ColumnModel column);

    public void DBInsert(TaskModel task);

    public void DBInsert(UUID uuid);

}
