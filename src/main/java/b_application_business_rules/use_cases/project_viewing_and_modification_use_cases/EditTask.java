package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Project;

import b_application_business_rules.entity_models.TaskModel;

import java.util.UUID;

/**
 * A use case class for editing task details (name and description)
 */
public class EditTask {

    private final Project currentProject;

    public EditTask(Project currentProject) {
        this.currentProject = currentProject;
    }

    /**
     * Edits the task's details
     */
    public void editTask(UUID columnID, TaskModel taskModel) {
        // This feature initializes calls other use cases to avoid duplicate code
        DeleteTask deleteTaskUseCase = new DeleteTask(currentProject);
        deleteTaskUseCase.deleteTask(columnID, taskModel);
        AddTask addTaskUseCase = new AddTask(currentProject);
        addTaskUseCase.addTask(columnID, taskModel);

    }



}