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
     * @param name the name of the TaskModel
     * @param ID the UUID of the TaskModel
     * @param description the description of the TaskModel
     * @param isCompleted the Completion status of the TaskModel
     * @param dueDateTime the due date of the TaskModel
     * @return a TaskModel
     */
    public static TaskModel create(String name, UUID ID, String description, boolean isCompleted, LocalDateTime dueDateTime) {
        return new TaskModel(name, ID, description, isCompleted, dueDateTime);
    }
}