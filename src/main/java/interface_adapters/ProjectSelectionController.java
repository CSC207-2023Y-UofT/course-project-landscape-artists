package interface_adapters;

import enterprise_business_rules.entities.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;

public class ProjectSelectionController implements Initializable {
    @FXML
    GridPane projectsGrid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//         Grab data from database and display it in the scene. An example
//         would be something like below (Currently don't know which layer to
//         get all projects):
//         Gateway gateway = new Gateway();
//         List<Project> allProjectsInSystem = gateway.getAllProjects();
//        TODO: TEMPORARY IMPLEMENTATION FOR TESTING PURPOSES ------------------
        Project p1 = new Project("P1", new ArrayList<>(),
                "P1 description");
        Project p2 = new Project("P2", new ArrayList<>(),
                "P2 description");
        Project p3 = new Project("P3", new ArrayList<>(),
                "P3 description");
        Project p4 = new Project("p4", new ArrayList<>(),
                "P3 description");
        Project p5 = new Project("p5", new ArrayList<>(),
                "P3 description");
        Project p6 = new Project("p6", new ArrayList<>(),
                "P3 description");
        Project p7 = new Project("p7", new ArrayList<>(),
                "P3 description");
        Project p8 = new Project("p8", new ArrayList<>(),
                "P3 description");

        List<Project> allProjectsInSystem = Arrays.asList(p1, p2, p3, p4, p5,
                p6, p7, p8);
//        TODO: END ------------------------------------------------------------

        populateProjectSelectionUI(allProjectsInSystem);
    }

    private void populateProjectSelectionUI(List<Project> allProjectsInSystem) {
        projectsGrid.setHgap(10);
        projectsGrid.setVgap(10);

        for (int col = 0; col < 4; col++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setHgrow(Priority.ALWAYS);
            columnConstraints.setFillWidth(true);
            projectsGrid.getColumnConstraints().add(columnConstraints);
        }

        int row = 0;
        int col = 0;
        for (Project project : allProjectsInSystem) {
            Button button = new Button(project.getName());
            button.setUserData(project);
            button.setOnAction(this::handleChosenProjectButton);
            projectsGrid.add(button, col, row);
            col++;
            if (col >= 4) {
                col = 0;
                row++;
            }
        }
    }

    private void handleChosenProjectButton(ActionEvent actionEvent) {
        Button buttonClicked = (Button) actionEvent.getSource();
        Project currentProject = (Project) buttonClicked.getUserData();
        System.out.println(currentProject);

    }


}