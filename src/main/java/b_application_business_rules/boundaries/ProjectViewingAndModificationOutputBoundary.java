
package b_application_business_rules.boundaries;//this boundary will be the output boundary when we are vieweing and modifying a single project.
//
//this boundary will be responsable for telling the outer classes what to do and what to show

import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import javafx.scene.layout.VBox;

public interface ProjectViewingAndModificationOutputBoundary {
    Project getCurrentProject();
    void displayAllProjects();

    void displayNewTask(VBox columnBox, Task newTask);
}