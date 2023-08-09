package b_application_business_rules.use_cases.project_selection_gateways;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;

import java.util.UUID;

public interface IDBRemove {
    void DBRemove(ProjectModel projectModel, UUID uuid);
    void DBRemove(TaskModel taskModel, UUID uuid);
    void DBRemove(ColumnModel columnModel, UUID uuid);
}
