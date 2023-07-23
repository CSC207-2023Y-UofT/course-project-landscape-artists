package interface_adapters;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProjectSelectionPresenter extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ProjectSelectionPresenter.class.getResource(
                "ProjectSelection.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Choose project");
        stage.setScene(scene);
        stage.show();
    }
}