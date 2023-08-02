package b_application_business_rules.factories;

import b_application_business_rules.entity_models.*;


import java.util.List;
import java.util.UUID;

/**
 * A Column factory to create ColumnModels,
 * Each ColumnModel has a name, list of TaskModels and ID
 */
public class ColumnModelFactory {

    /**
     * Creates a ColumnModel
     * @param name
     * @param taskModels
     * @param ID
     * @return a ColumnModel
     */
    public static ColumnModel create(String name, List<TaskModel> taskModels, UUID ID) {
        return new ColumnModel(name, taskModels, ID);
    }
}
