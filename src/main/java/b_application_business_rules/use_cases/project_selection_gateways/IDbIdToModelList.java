package b_application_business_rules.use_cases.project_selection_gateways;

import b_application_business_rules.entity_models.ColumnModel;
import b_application_business_rules.entity_models.ProjectModel;
import b_application_business_rules.entity_models.TaskModel;

import java.util.List;

public interface IDbIdToModelList {
    //public List<ProjectModel> IdToProjectModel(List<String> IDlist);
    public List<ColumnModel> IdToColumnModelList(List<String> IDlist);
    public List<TaskModel> IdToTaskModelList(List<String> IDlist);
    public List<ProjectModel> IdToProjectModelList(List<String> IDlist);

}
