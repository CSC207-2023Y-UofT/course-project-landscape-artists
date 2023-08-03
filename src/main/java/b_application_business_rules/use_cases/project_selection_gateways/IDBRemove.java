package b_application_business_rules.use_cases.project_selection_gateways;


import a_enterprise_business_rules.entities.*;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;



import java.util.UUID;

public interface IDBRemove {
    public void DBRemove(ProjectModel projectModel, UUID uuid);
    public void DBRemove(TaskModel taskModel, UUID uuid);
    public void DBRemove(ColumnModel columnModel, UUID uuid);
}