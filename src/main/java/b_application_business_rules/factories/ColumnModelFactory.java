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
     * Creates a new ColumnModel instance.
     *
     * @param name The name of the column.
     * @param taskModels The list of TaskModels associated with the column.
     * @param ID The unique ID for the column.
     * @return A newly created ColumnModel instance.
     */
    public static ColumnModel create(String name, List<TaskModel> taskModels, UUID ID) {
        return new ColumnModel(name, taskModels, ID);
    }
}
