package b_application_business_rules.factories;

import b_application_business_rules.entity_models.*;


import java.util.List;
import java.util.UUID;

public class ColumnFactory {

    public ColumnModel create(String name, List<TaskModel> taskModels, UUID ID) {
        return new ColumnModel(name, taskModels, ID);
    }
}
