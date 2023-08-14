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
     * @param name the name of the ColumnModel
     * @param taskModels the list of TaskModels the ColumnModel should contain
     * @param ID the UUID of the ColumnModel
     * @return a ColumnModel
     */
    public static ColumnModel create(String name, List<TaskModel> taskModels, UUID ID) {
        return new ColumnModel(name, taskModels, ID);
    }
}
