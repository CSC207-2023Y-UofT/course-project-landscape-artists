package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;

import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.entity_models.TaskModel;

import java.util.List;
import java.util.UUID;


/**
 * A use case class for editing task details (name and description)
 */
public class EditTask {

    private final Project currentProject;
    private final List<Column> columns;

    public EditTask(Project currentProject) {

        this.currentProject = currentProject;
        this.columns = currentProject.getColumns();
    }

    /**
     * Edits the task's details
     *
     * @return Task that was edited
     */
    public Task editTask(UUID columnID, TaskModel taskModel) {
        for (Column column: columns) {
            if (column.getID().equals(columnID)) {
                Column currentColumn = column;
                for (Task task: currentColumn.getTasks()) {
                    if (task.getID().equals(taskModel.getID())) {
                        task.setName(taskModel.getName());
                        task.setDescription(taskModel.getDescription());
                        task.setDueDateTime(taskModel.getDueDateTime());
                        return task;
                    }
                }
            }
        }
        return null;
    }
}