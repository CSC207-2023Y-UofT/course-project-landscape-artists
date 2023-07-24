package c_interface_adapters;

import b_application_business_rules.boundaries.ProjectViewingAndModificationOutputBoundary;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import a_enterprise_business_rules.entities.Project;
import a_enterprise_business_rules.entities.Task;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
public class ProjectViewingAndModificationPresenter extends Application implements ProjectViewingAndModificationOutputBoundary {
    private Stage stage;
    CurrentProjectRepository currentProjectRepository =
            CurrentProjectRepository.getInstance();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProjectViewingAndModificationPresenter.class.getResource("ProjectViewingAndModification.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Current project");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Project getCurrentProject() {
        return currentProjectRepository.getCurrentProject();
    }

    @Override
    public void displayAllProjects() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(
                    "ProjectSelection" +
                    ".fxml"));
            stage.setTitle("scene 1");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void displayNewTask(VBox columnBox, Task newTask) {
        HBox hbox = new HBox();
        Label taskName = new Label(newTask.getName());
        Button taskOptionsButton = new Button("...");
        taskOptionsButton.setStyle("-fx-font-size: 8px;");
        hbox.getChildren().add(taskName);
        hbox.getChildren().add(taskOptionsButton);


        int buttonIndex = columnBox.getChildren().size() - 1;
        columnBox.getChildren().add(buttonIndex, hbox);
    }
}

