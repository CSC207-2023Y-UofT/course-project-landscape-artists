package b_application_business_rules.factories;

import a_enterprise_business_rules.entities.*;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;

import java.util.List;
import java.util.UUID;

public class ProjectFactory {

    public ProjectModel create(String name, UUID id, String description, List<ColumnModel> columnModels) {
        return new ProjectModel(name, id, description, columnModels);
}
}
