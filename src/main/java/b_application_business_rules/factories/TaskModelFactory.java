package b_application_business_rules.factories;

import b_application_business_rules.entity_models.*;


import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A Task factory to create TaskModels,
 * Each TaskModel has a name, ID, description, completion status and date
 */
public class TaskModelFactory {

    /**
     * Creates a TaskModel
     * @param name
     * @param ID
     * @param description
     * @param isCompleted
     * @param dueDateTime
     * @return a TaskModel
     */
    public static TaskModel create(String name, UUID ID, String description, boolean isCompleted, LocalDateTime dueDateTime) {
        return new TaskModel(name, ID, description, isCompleted, dueDateTime);
    }
}