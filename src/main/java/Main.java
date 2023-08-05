import c_interface_adapters.ProjectSelectionPresenter;
import d_frameworks_and_drivers.database_management.DatabaseInitializer.DBInitializer;
import d_frameworks_and_drivers.database_management.ProjectUUIDArray;
import javafx.application.Application;

import java.util.UUID;

public class  Main {
    public static void main(String[] args) {
        System.out.println(ProjectUUIDArray.convertCsvToArrayList());
        System.out.println("Main Above");
        Application.launch(ProjectSelectionPresenter.class, args);
    }
}
