package b_application_business_rules;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class AdapterConvertEntity {
    /**
     * Returns a String list representation of the column entity.
     * @return String list representation of the column entity.
     */
    public static List<String> toStringList(Column input) {
        List<String> columnList = new ArrayList<>();
        columnList.add(input.getID().toString());
        columnList.add(input.getName());

        // task IDs
        List<Task> taskList = input.getTasks();
        List<String> taskIDs = new ArrayList<>();

        for (Task cur : taskList) {
            taskIDs.add(cur.getID().toString());
        }
        String columnListStr = String.join(", ", taskIDs);
        columnList.add(columnListStr);

        return columnList;
    }

    /**
     * Returns a String list representation of the Project entity.
     * @return String list representation of the Project entity.
     */
    public static List<String> toStringList(Project input) {
        List<String> projectList = new ArrayList<>();
        projectList.add(input.getID().toString());
        projectList.add(input.getName());
        projectList.add(input.getDescription());

        // column IDs
        List<Column> columnList = input.getColumns();
        List<String> columnIDs = new ArrayList<>();

        for (Column cur : columnList) {
            columnIDs.add(cur.getID().toString());
        }
        String columnListStr = String.join(", ", columnIDs);
        projectList.add(columnListStr);

        return projectList;
    }

    /**
     *  Returns a list of strings representation of the task entity.
     */
    public static List<String> toStringList(Task input) {
        List<String> taskList = new ArrayList<>();
        taskList.add(input.getID().toString());
        taskList.add(input.getName());
        taskList.add(input.getDescription());
        taskList.add(String.valueOf(input.getCompletionStatus()));
        taskList.add(input.getDueDateTime().toString());
        return taskList;
    }

    /**
     * Returns string list representation of the project model
     * @return string list representation of the project model
     */
    public static List<String> toStringList(ProjectModel input) {
        List<String> output = new ArrayList<>();
        output.add(input.getID().toString());
        output.add(input.getName());
        output.add(input.getDescription());

        // column IDs
        List<ColumnModel> columnModelList = input.getColumnModels();
        List<String> columnModelIDs = new ArrayList<>();

        for (ColumnModel cur : columnModelList) {
            columnModelIDs.add(cur.getID().toString());
        }
        String columnListStr = String.join(", ", columnModelIDs);
        output.add(columnListStr);

        return output;
    }
    /**
     * Returns string list representation of the column model
     * @return string list representation of the column model
     */
    public static List<String> toStringList(ColumnModel input) {
        List<String> output = new ArrayList<>();
        output.add(input.getID().toString());
        output.add(input.getName());

        // task IDs
        List<TaskModel> taskModelList = input.getTaskModels();
        List<String> taskModelIDs = new ArrayList<>();

        for (TaskModel cur : taskModelList) {
            taskModelIDs.add(cur.getID().toString());
        }
        String columnListStr = String.join(", ", taskModelIDs);
        output.add(columnListStr);

        return output;
    }

    /**
     * Returns string list representation of the task model
     * @return string list representation of the task model
     */
    public static List<String> toStringList(TaskModel input) {
        List<String> output = new ArrayList<>();
        output.add(input.getID().toString());
        output.add(input.getName());
        output.add(input.getDescription());
        output.add(String.valueOf(input.getCompletionStatus()));
        output.add(input.getDueDateTime().toString());
        return output;
    }
}

