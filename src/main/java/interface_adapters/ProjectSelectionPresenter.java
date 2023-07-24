package interface_adapters;

import application_business_rules.boundaries.ProjectSelectionOutputBoundary;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ProjectSelectionPresenter extends Application implements ProjectSelectionOutputBoundary {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        setStage(stage);
        initializeScene(stage);
    }

    @Override
    public void displayCurrentProject() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ProjectViewingAndModification.fxml"));
            stage.setTitle("scene 2");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initializeScene(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectSelectionPresenter.class.getResource(
                    "ProjectSelection.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage1 = stage;
            stage1.setTitle("Choose project");
            stage1.setScene(scene);
            stage1.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
