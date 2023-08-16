//
//import a_enterprise_business_rules.entities.*;
//import b_application_business_rules.entity_models.*;
//import b_application_business_rules.use_cases.project_selection_use_cases.CreateProjectTest;
//import b_application_business_rules.use_cases.project_selection_use_cases.DeleteProjectTest;
//import b_application_business_rules.use_cases.project_selection_use_cases.EditProjectDetailsTest;
//import b_application_business_rules.use_cases.project_viewing_and_modification_use_cases.*;
//import c_interface_adapters.view_models.*;
//import d_frameworks_and_drivers.database_management.DatabaseInitializer.*;
//import d_frameworks_and_drivers.database_management.DBControllers.*;
//import d_frameworks_and_drivers.database_management.*;
//import d_frameworks_and_drivers.database_management.DBAdapters.*;
//
//
//
//import org.junit.platform.launcher.Launcher;
//import org.junit.platform.launcher.LauncherDiscoveryRequest;
//import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
//import org.junit.platform.launcher.core.LauncherFactory;
//import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
//
//import org.junit.platform.launcher.listeners.TestExecutionSummary.Failure;
//
//import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
//
///**
// * Runs all tests at once.
// */
//public class TestRunner {
//    public static void main(String[] args) {
//        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
//                .selectors(
//                        // entities
//                        selectClass(ProjectTest.class),
//                        selectClass(ColumnTest.class),
//                        selectClass(TaskTest.class),
//                        // entity models
//                        selectClass(ProjectModelTest.class),
//                        selectClass(ColumnModelTest.class),
//                        selectClass(TaskModelTest.class),
//                        // entity view models
//                        selectClass(ProjectViewModelTest.class),
//                        selectClass(ColumnViewModelTest.class),
//                        selectClass(TaskViewModelTest.class),
//                        // project use cases
//                        selectClass(CreateProjectTest.class),
//                        selectClass(DeleteProjectTest.class),
//                        selectClass(EditProjectDetailsTest.class),
//                        // column use cases
//                        selectClass(AddColumnTest.class),
//                        selectClass(DeleteColumnTest.class),
//                        selectClass(EditColumnTest.class),
//                        // task use cases
//                        selectClass(AddTaskTest.class),
//                        selectClass(DeleteTaskTest.class),
//                        selectClass(EditTaskTest.class),
//                        // initializers
//                        selectClass(ProjectDBInitializerTest.class),
//                        selectClass(ColumnDBInitializerTest.class),
//                        selectClass(TaskDBInitializerTest.class),
//                        selectClass(UniqueIDsInitializerTest.class),
//                        // database adapters and controllers
//                        selectClass(DBMapperTest.class),
//                        selectClass(DBSearcherTest.class),
//                        selectClass(DbIDToModelTest.class),
//                        selectClass(DBManagerInsertControllerTest.class),
//                        selectClass(DBManagerSearchControllerTest.class),
//                        selectClass(DBManagerRemoveControllerTest.class),
//                        selectClass(EntityIDsToListControllerTest.class),
//                        selectClass(EntityIDstoModelControllerTest.class),
//                        selectClass(IDListsToModelListTest.class),
//                        selectClass(UniqueIDsReaderTest.class),
//                        selectClass(ProjectUUIDArrayTest.class)
//                        ).build();
//
//        Launcher launcher = LauncherFactory.create();
//        SummaryGeneratingListener listener = new SummaryGeneratingListener();
//
//        launcher.registerTestExecutionListeners(listener);
//        launcher.execute(request);
//
//        // Print test results
//        for (Failure failure : listener.getSummary().getFailures()) {
//            System.out.println("FAILED: " + failure.getTestIdentifier().getDisplayName());
//            System.out.println("Reason: " + failure.getException().getMessage());
//            System.out.println();
//        }
//
//        System.out.println("Test execution finished.");
//    }
//
//}
//
//
//
//
//
