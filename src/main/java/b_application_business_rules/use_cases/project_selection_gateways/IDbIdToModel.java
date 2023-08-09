package b_application_business_rules.use_cases.project_selection_gateways;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;

public interface IDbIdToModel {
    public ColumnModel IdToColumnModel(String Id);
    public ProjectModel IdToProjectModel(String Id);
    public TaskModel IdToTaskModel(String Id);

}
