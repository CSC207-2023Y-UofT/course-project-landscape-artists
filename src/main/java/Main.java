import c_interface_adapters.ProjectSelectionPresenter;
import d_frameworks_and_drivers.database_management.DatabaseInitializer.DBInitializer;
import javafx.application.Application;

public class  Main {
    public static void main(String[] args) {
        Application.launch(ProjectSelectionPresenter.class, args);
    }
}
