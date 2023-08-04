package b_application_business_rules.use_cases.project_selection_gateways;

import a_enterprise_business_rules.entities.*;
import b_application_business_rules.entity_models.*;

import java.util.UUID;

public interface IDBInsert {
    void DBInsert(ProjectModel projectModel);

    void DBInsert(ColumnModel columnModel);

    void DBInsert(TaskModel taskModel);

    void DBInsert(UUID uuid);

}
