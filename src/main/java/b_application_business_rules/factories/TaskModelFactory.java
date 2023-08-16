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
     * Creates a new TaskModel instance.
     *
     * @param name The name of the task.
     * @param ID The unique ID for the task.
     * @param description The description of the task.
     * @param isCompleted The completion status of the task.
     * @param dueDateTime The due date and time for the task.
     * @return A newly created TaskModel instance.
     */
    public static TaskModel create(String name, UUID ID, String description, boolean isCompleted, LocalDateTime dueDateTime) {
        return new TaskModel(name, ID, description, isCompleted, dueDateTime);
    }
}