package interface_adapters;

import application_business_rules.use_cases.CurrentProjectRepository;
import enterprise_business_rules.entities.Project;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ProjectViewingAndModificationController implements Initializable {
    @FXML
    Label projectName;
    @FXML
    HBox columnsContainer;
    CurrentProjectRepository currentProjectRepository =
            CurrentProjectRepository.getInstance();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Project currentProject = currentProjectRepository.getCurrentProject();
//        Populate project details
        populateProjectDetails(currentProject);
        populateColumns(currentProject);

//       Implement methods


    }

    private void populateColumns(Project project) {
    }

    private void populateProjectDetails(Project project) {
        projectName.setText(project.getName());
    }
}