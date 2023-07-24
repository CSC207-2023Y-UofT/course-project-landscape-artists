package application_business_rules.boundaries;//this boundary will be the input boundary when we are vieweing and modifying a single project.
//
//this boundary will be responsable for getting the information from the outer layers and doing the work (running the right use cases, etc.)

import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ProjectViewingAndModificationInputBoundary {
    public void removeCurrentProject();

    void addNewTask(VBox columnBox, String taskName, String taskDescription,
                    LocalDateTime dueDate);
}