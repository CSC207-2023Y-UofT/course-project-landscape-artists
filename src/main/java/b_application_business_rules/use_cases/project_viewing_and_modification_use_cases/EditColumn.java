package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.entity_models.ColumnModel;

/**
 * A use case class responsible for editing column details in a project.
 */
public class EditColumn {

    private final Project currentProject;

    /**
     * Constructs an EditColumn instance associated with the current project.
     *
     * @param currentProject The project for which column details will be edited.
     */
    public EditColumn(Project currentProject) {
        this.currentProject = currentProject;
    }

    /**
     * Edits the name of the specified column in the current project.
     *
     * @param updatedColumnModel The model representing the updated column details.
     */
    public void setColumnName(ColumnModel updatedColumnModel) {
        // Get the corresponding column entity
        Column column = Column.IDToColumn(updatedColumnModel.getID(), currentProject.getColumns());

        // Create the column entity
        column.setName(updatedColumnModel.getName());
    }

}
