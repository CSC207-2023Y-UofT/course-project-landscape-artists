package b_application_business_rules.boundaries;//this boundary will be the input boundary when we are vieweing and modifying a single project.

//
//this boundary will be responsable for getting the information from the outer layers and doing the work (running the right use cases, etc.)

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Task;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;

public interface ProjectViewingAndModificationInputBoundary {
    public void removeCurrentProject();

    void addNewTask(VBox columnBox, String taskName, String taskDescription,
            LocalDateTime dueDate);

    void deleteColumn(Column column, VBox columnBox);

    void renameColumn(Column column, VBox columnBox);

    void deleteTask(Task task, HBox hbox);

    void changeTaskDetails(Task task, HBox hbox);

    void renameTask(Task task, HBox hbox);
}