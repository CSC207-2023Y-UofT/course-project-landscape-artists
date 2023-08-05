package c_interface_adapters;

import b_application_business_rules.boundaries.ProjectSelectionInputBoundary;
import b_application_business_rules.boundaries.ProjectSelectionOutputBoundary;
import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;
import b_application_business_rules.use_cases.CurrentProjectRepository;
import b_application_business_rules.use_cases.project_selection_use_cases.ProjectSelectionInteractor;
import a_enterprise_business_rules.entities.Project;
import c_interface_adapters.view_models.ColumnViewModel;
import c_interface_adapters.view_models.ProjectSelectionViewModel;
import c_interface_adapters.view_models.ProjectViewModel;
import c_interface_adapters.view_models.TaskViewModel;
import d_frameworks_and_drivers.database_management.DBControllers.EntityIDstoModelController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import static javafx.scene.control.PopupControl.USE_PREF_SIZE;

/**
 * ProjectSelectionController class handles the user interface for project selection and creation.
 * It implements the Initializable interface to initialize the controller.
 */
public class ProjectSelectionController implements Initializable {
    // FXML reference to the GridPane that holds the projects
    @FXML
    private GridPane projectsGrid;

    // The interactor for project selection and creation
    private ProjectSelectionInputBoundary interactor;

    // The ProjectSelectionViewModel to pass data to the view.
    ProjectSelectionViewModel projectSelectionViewModel;

    // The ProjectSelectionPresenter
    ProjectSelectionPresenter presenter;

    List<ProjectModel> AllProjectsList = new ArrayList<>();

    /**
     * Initializes the controller after its root element has been completely processed.
     * Populates the project selection UI with the projects from the database.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//         Grab data from database and display it in the scene. An example
//         would be something like below (Currently don't know which layer to
//         get all projects):
//         Gateway gateway = new Gateway();
//         List<Project> allProjectsInSystem = gateway.getAllProjects();
//        TODO: TEMPORARY IMPLEMENTATION FOR TESTING PURPOSES ------------------

        List<TaskViewModel> TaskList = new ArrayList<>();
        TaskList.add(new TaskViewModel("Task1", UUID.randomUUID(), "Task1", true,
                LocalDateTime.now()));
        TaskList.add(new TaskViewModel("Task2", UUID.randomUUID(), "Task2", true,
                LocalDateTime.now()));

        List<ColumnViewModel> ColumnsList = new ArrayList<>();
        ColumnsList.add(new ColumnViewModel("COLUMN 1", TaskList, UUID.randomUUID()));
        ColumnsList.add(new ColumnViewModel("COLUMN 2", new ArrayList<>(), UUID.randomUUID()));


        ProjectViewModel p1 = new ProjectViewModel(
                "Project 111111", UUID.randomUUID(),"P1 description",  ColumnsList
                );

        ProjectViewModel p2 = new ProjectViewModel(
                "Project 111111", UUID.randomUUID(),"P2 description",  ColumnsList
        );

        ProjectViewModel p3 = new ProjectViewModel(
                "Project 111111", UUID.randomUUID(),"P2 description",  ColumnsList
        );


        List<ProjectViewModel> projectsInSystem = new ArrayList<>();
        projectsInSystem.add(p1);
        projectsInSystem.add(p2);
        projectsInSystem.add(p3);
//        TODO: END ------------------------------------------------------------

        DBAdapterInterface dbAdapterInterface = new EntityIDstoModelController();
        AllProjectsList = dbAdapterInterface.IDstoProjectModelList();

        for (ProjectModel proj :AllProjectsList) {
            if(proj != null){
                projectsInSystem.add(new ProjectViewModel(proj));
            } else {
                continue;
            }

            List<ColumnModel> colsFromProject = proj.getColumnModels();
            for (ColumnModel column : colsFromProject) {
                if(column != null){
                    ColumnsList.add(new ColumnViewModel(column));
                } else {
                    continue;
                }

                List<TaskModel> tasksFromColumn = column.getTaskModels();

                for (TaskModel tasks: tasksFromColumn) {
                    if(tasks != null){
                        TaskList.add(new TaskViewModel(tasks));
                    }
                }

            }

        }

        projectSelectionViewModel = new ProjectSelectionViewModel(projectsInSystem);
//        System.out.println(projectsInSystem);
//        System.out.println(dbAdapterInterface.IDstoProjectModelList());

        // Populate the project selection UI with the projects
        populateProjectSelectionUI();
    }

    /**
     * Sets up the output boundary of the interactor.
     */
    private void setPresenter() {
        // This had to be separate since presenter needs to have a stage.
        // This is not accessible upon initialization.
        ProjectSelectionOutputBoundary presenter =
                new ProjectSelectionPresenter();
        Stage stage = (Stage) projectsGrid.getScene().getWindow();
        ((ProjectSelectionPresenter) presenter).setStage(stage);
        ((ProjectSelectionPresenter) presenter).setViewModel(projectSelectionViewModel);
        this.presenter = (ProjectSelectionPresenter) presenter;

        interactor = new ProjectSelectionInteractor(presenter);
    }

    /**
     * Populates the project selection UI with the list of projects retrieved from the ViewModel.
     * Projects are displayed in a GridPane, with each project represented by a button.
     * Each button allows the user to open the corresponding project or perform actions on it,
     * such as renaming or deleting the project.
     */
    private void populateProjectSelectionUI() {
        projectsGrid.setHgap(20);
        projectsGrid.setVgap(100);

        for (int col = 0; col < 2; col++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setHgrow(Priority.ALWAYS);
            columnConstraints.setFillWidth(true);
        }

        int row = 0;
        int col = 0;

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.ALWAYS);
        rowConstraints.setFillHeight(true);
        projectsGrid.getRowConstraints().add(rowConstraints);

        while (projectSelectionViewModel.hasNext()) {
            ProjectViewModel project = projectSelectionViewModel.next();

            // Create the currentProjectButton

            Label projectName = new Label(project.getName());
            Label projectDescription = new Label(project.getDescription());

            projectName.setId("projectName");


            projectName.setFont(Font.font("Arial", FontWeight.BOLD, 15));

            VBox nameAndDescriptionContainer = new VBox(projectName, projectDescription);

            nameAndDescriptionContainer.setAlignment(Pos.CENTER);
            nameAndDescriptionContainer.setPadding(new Insets(10));
            nameAndDescriptionContainer.setSpacing(5);

            projectName.setMaxWidth(150);
            projectDescription.setMaxWidth(150);
            projectName.setWrapText(true);
            projectDescription.setWrapText(true);

            // Center the text in each label
            projectName.setAlignment(Pos.CENTER);
            projectDescription.setAlignment(Pos.CENTER);

            Button currentProjectButton = new Button();

            currentProjectButton.setGraphic(nameAndDescriptionContainer);
            
            currentProjectButton.getStyleClass().add("current-project-button");


            currentProjectButton.setUserData(project.getID());
            currentProjectButton.setOnAction(this::handleChosenProjectButton);
            currentProjectButton.setWrapText(true); // Allow the button to wrap its text and show the whole content
            currentProjectButton.setMinWidth(USE_PREF_SIZE); // Allow the button to resize based on its content
            currentProjectButton.setMaxWidth(Double.MAX_VALUE); // Allow the button to take up available space

            // Create the MenuButton
            MenuButton menuButton = new MenuButton();
            MenuItem renameProjectMenuItem = new MenuItem("Rename Project");
            MenuItem deleteProjectMenuItem = new MenuItem("Delete Project");

            menuButton.getStyleClass().add("menu-button-custom");

            // Add event handlers for the MenuItems
            renameProjectMenuItem.setOnAction(event -> handleRenameProject(project.getID()));
            deleteProjectMenuItem.setOnAction(event -> handleDeleteProject(project.getID()));

            // Add MenuItems to the MenuButton
            menuButton.getItems().addAll(renameProjectMenuItem, deleteProjectMenuItem);


            // Add currentProjectButton and menuButton to a container (HBox) for better layout control
            HBox buttonContainer = new HBox(currentProjectButton, menuButton);
            buttonContainer.setId(project.getID().toString());
            projectsGrid.add(buttonContainer, col, row);

            col++;
            if (col >= 2) {
                col = 0;
                row++;
            }
        }

        addCreateProjectButton(col, row);
    }

    /**
     * Handles the action of renaming a project. When the user selects the "Rename Project" option from
     * the menu associated with a project button, this method is called. It sets up the presenter and
     * calls the interactor to initiate the renaming process for the specified project.
     *
     * @param projectUUID The UUID of the project to be renamed.
     */
    private void handleRenameProject(UUID projectUUID) {
        setPresenter();
        interactor.renameProject(projectUUID);
    }

    /**
     * Handles the action of renaming a project. When the user selects the "Rename Project" option from
     * the menu associated with a project button, this method is called. It sets up the presenter and
     * calls the interactor to initiate the renaming process for the specified project.
     *
     * @param projectUUID The UUID of the project to be renamed.
     */
    private void handleDeleteProject(UUID projectUUID) {
        setPresenter();
        interactor.deleteProject(projectUUID);
    }

    /**
     * Adds the "Create Project" button to the project selection UI.
     */
    private void addCreateProjectButton(int col, int row) {
        Button createProjectButton = new Button("+");
        createProjectButton.setOnAction(this::handleCreateProjectPopup);

        createProjectButton.getStyleClass().add("create-project-button-style");

        projectsGrid.add(createProjectButton, col, row);
    }

    /**
     * Handles the "Create Project" button action by showing a dialog to create a new project.
     */
    private void handleCreateProjectPopup(ActionEvent actionEvent) {
        setPresenter();
        // Create a new Dialog
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Create Project");

        // Set the button types (OK and Cancel)
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Create labels and text fields for project name and description
        Label nameLabel = new Label("Project Name:");
        Label descLabel = new Label("Description:");
        TextField nameTextField = new TextField();
        TextField descTextField = new TextField();

        // Create a GridPane to layout the labels and text fields
        GridPane gridPane = new GridPane();
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameTextField, 1, 0);
        gridPane.add(descLabel, 0, 1);
        gridPane.add(descTextField, 1, 1);

        // Set the content of the dialog to the GridPane
        dialog.getDialogPane().setContent(gridPane);

        // Convert the result to a Pair object when the OK button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String projectName = nameTextField.getText();
                String projectDescription = descTextField.getText();
                return new Pair<>(projectName, projectDescription);
            }
            return null;
        });

        // Show the dialog and wait for the user to close it
        dialog.showAndWait().ifPresent(project -> {
            if (project != null) {
                // Handle the newly created project here
                createProject(project.getKey(), project.getValue());
            }
        });
    }

    /**
     * Creates a new project using the provided Project object and delegates the task to the interactor
     * for processing the project creation.
     *
     * @param name The name of project.
     * @param description Description of project.
     */
    private void createProject(String name, String description) {
        interactor.createProject(name, description);
    }





    /**
     * Handles the action of selecting a project button from the UI.
     */
    private void handleChosenProjectButton(ActionEvent actionEvent) {
        setPresenter();
        Button buttonClicked = (Button) actionEvent.getSource();
        UUID currentProjectID = (UUID) buttonClicked.getUserData();
        interactor.openProject(currentProjectID);
    }
}