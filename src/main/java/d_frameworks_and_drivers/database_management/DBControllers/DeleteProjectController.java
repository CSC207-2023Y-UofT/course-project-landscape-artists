package d_frameworks_and_drivers.database_management.DBControllers;

import c_interface_adapters.ProjectSelectionPresenter;

import java.util.UUID;

public class DeleteProjectController {
    private ProjectSelectionPresenter presenter;

    public DeleteProjectController(ProjectSelectionPresenter presenter) {
        this.presenter = presenter;
    }

    public void handleDeleteProject(UUID projectId) {
        presenter.handleDeleteProject(projectId);
    }
}
