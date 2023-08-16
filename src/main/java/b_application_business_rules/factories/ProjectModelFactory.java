package b_application_business_rules.factories;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;

import java.util.List;
import java.util.UUID;
/**
 * A Project factory to create ProjectModels.
 * Each ProjectModel has a name, ID, description and a list of ColumnModels
 */
public class ProjectModelFactory {

    /**
     * Creates a new ProjectModel instance.
     *
     * @param name The name of the project.
     * @param id The unique ID for the project.
     * @param description The description of the project.
     * @param columnModels The list of ColumnModels associated with the project.
     * @return A newly created ProjectModel instance.
     */
    public static ProjectModel create(String name, UUID id, String description, List<ColumnModel> columnModels) {
        return new ProjectModel(name, id, description, columnModels);
}
}
