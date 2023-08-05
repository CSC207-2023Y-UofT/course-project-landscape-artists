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
     * Creates a ProjectModel
     * @param name
     * @param id
     * @param description
     * @param columnModels
     * @return a ProjectModel
     */
    public static ProjectModel create(String name, UUID id, String description, List<ColumnModel> columnModels) {
        return new ProjectModel(name, id, description, columnModels);
}
}
