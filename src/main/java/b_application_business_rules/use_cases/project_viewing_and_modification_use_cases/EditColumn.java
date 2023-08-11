package b_application_business_rules.use_cases.project_viewing_and_modification_use_cases;

import a_enterprise_business_rules.entities.Column;
import a_enterprise_business_rules.entities.Project;
import b_application_business_rules.entity_models.ColumnModel;

/**
 * The EditColumnDetails class is responsible for editing a column in the
 * currently
 * opened project in the database and the project entity.
 */
public class EditColumn {

    private final Project currentProject;

    /**
     * Constructs an EditColumnDetails object with the currentProject entity.
     *
     * @param currentProject
     */
    public EditColumn(Project currentProject) {
        this.currentProject = currentProject;
    }

    /**
     * Edits the column in the currently opened project entity.
     */
    public void setColumnName(ColumnModel updatedColumnModel) {
        // Get the corresponding column entity
        Column column = Column.IDToColumn(updatedColumnModel.getID(), currentProject.getColumns());

        // Create the column entity
        column.setName(updatedColumnModel.getName());
    }

}
