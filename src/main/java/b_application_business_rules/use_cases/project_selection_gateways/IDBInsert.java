package b_application_business_rules.use_cases.project_selection_gateways;

import a_enterprise_business_rules.entities.*;
import b_application_business_rules.entity_models.*;

import java.util.UUID;

public interface IDBInsert {
    public void DBInsert(ProjectModel projectModel);

    public void DBInsert(ColumnModel columnModel);

    public void DBInsert(TaskModel taskModel);

    public void DBInsert(UUID uuid);

}