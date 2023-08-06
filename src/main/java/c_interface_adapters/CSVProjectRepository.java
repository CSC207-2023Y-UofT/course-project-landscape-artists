//package c_interface_adapters;
//
//import a_enterprise_business_rules.entities.Project;
//import b_application_business_rules.use_cases.interfaces.ProjectRepository;
//import d_frameworks_and_drivers.database_management.DBAdapters.CSVMapper;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//
///**
// * first draft
// */
//public class CSVProjectRepository implements ProjectRepository {
//    private CSVMapper csvMapper;
//
//    public CSVProjectRepository(CSVMapper csvMapper) {
//        this.csvMapper = csvMapper;
//    }
//
//    @Override
//    public Project getProjectById(UUID projectId) {
//        // Retrieve the CSV record using the projectId and convert it to a Project object
//        // Implement the logic using the csvMapper.getUUIDToRecordMap() method
//        return null;
//    }
//
//    @Override
//    public List<Project> getAllProjects() {
//        // Retrieve all CSV records and convert them to a list of Project objects
//        // Implement the logic using the csvMapper.getUUIDToRecordMap() method
//        return new ArrayList<>();
//    }
//
//    @Override
//    public void saveProject(Project project) {
//        // Convert the Project object to a CSV record and save it to the CSV file
//        // Implement the logic using the csvMapper.getStringToStringMap() method
//    }
//
//    // Additional methods for managing projects in CSV file
//}
