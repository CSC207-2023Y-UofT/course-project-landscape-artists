package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;

import java.util.UUID;

/**
 * This class represents a use case for changing the completion status of a task within a project.
 * It provides methods to modify the completion status of a specific task within the context of a given project.
 *
 */
public class ChangeTaskCompletionStatus {
    private final Project currentProject;

    /**
     * Constructor for the ChangeTaskCompletionStatus class.
     *
     * @param currentProject The project within which the task completion status will be modified.
     */
    public ChangeTaskCompletionStatus(Project currentProject) {
        this.currentProject = currentProject;
    }

    /**
     * Changes the completion status of a specific task within the project.
     *
     * @param taskID The unique identifier of the task to be modified.
     * @return task the task that got updated.
     */
    public Task changeCompletionStatus(UUID taskID) {
        for (Column column: currentProject.getColumns()) {
            for (Task task: column.getTasks()) {
                if (task.getID().equals(taskID)) {
                    task.negateCompletionStatus();
                    return task;
                }
            }
        }
        return null;
    }
}
