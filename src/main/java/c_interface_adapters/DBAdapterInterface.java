package c_interface_adapters;

import b_application_business_rules.entity_models.*;
import java.util.*;


/**
 * This interface is to be used by the presenter to obtain a list of ProjectModels
 * presenter can use IDstoProjectModelList, which has been implemented in EntityIDstoModelController
 */
public interface DBAdapterInterface {

public List<ProjectModel> IDstoProjectModelList();

}
