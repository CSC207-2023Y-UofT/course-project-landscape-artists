package b_application_business_rules.factories;

import b_application_business_rules.entity_models.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TaskFactory {

    public static TaskModel create(String name, UUID ID, String description, boolean isCompleted, LocalDateTime dueDateTime) {
        return new TaskModel(name, ID, description, isCompleted, dueDateTime);
    }
}