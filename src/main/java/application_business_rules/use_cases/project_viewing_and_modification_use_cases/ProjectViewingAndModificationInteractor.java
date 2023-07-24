package application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import application_business_rules.boundaries.ProjectSelectionOutputBoundary;
import application_business_rules.boundaries.ProjectViewingAndModificationInputBoundary;
import application_business_rules.boundaries.ProjectViewingAndModificationOutputBoundary;
import application_business_rules.use_cases.CurrentProjectRepository;
import enterprise_business_rules.entities.Task;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProjectViewingAndModificationInteractor implements ProjectViewingAndModificationInputBoundary {
    CurrentProjectRepository currentProjectRepository =
            CurrentProjectRepository.getInstance();

    private ProjectViewingAndModificationOutputBoundary presenter;

    public ProjectViewingAndModificationInteractor(ProjectViewingAndModificationOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void removeCurrentProject() {
        currentProjectRepository.removeCurrentProject();
    }

    @Override
    public void addNewTask(VBox columnBox, String taskName,
                           String taskDescription, LocalDateTime dueDate) {
        System.out.println("CREATE NEW TASK HEREEE!!!!!!");
        Task newTask = new Task(taskName, taskDescription, false, dueDate);
        System.out.println("VBOX IN ADDNEWTASK " + columnBox);
        presenter.displayNewTask(columnBox, newTask);
    }
}
