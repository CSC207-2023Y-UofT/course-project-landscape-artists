package b_application_business_rules.use_cases.project_selection_gateways;

import b_application_business_rules.entity_models.*;

import java.util.*;
public interface IDBSearch {

    public ArrayList<String> DBColumnSearch(String id);

    public ArrayList<String> DBTaskSearch(String id);
}