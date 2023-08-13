//import org.junit.platform.launcher.Launcher;
//import org.junit.platform.launcher.LauncherDiscoveryRequest;
//import org.junit.platform.launcher.TestExecutionListener;
//import org.junit.platform.launcher.TestIdentifier;
//import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
//import org.junit.platform.launcher.core.LauncherFactory;
//import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
//import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
//
//public class TestRunner {
//    public static void main(String[] args) {
//        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
//                .selectors(
//                        // Add your test classes here
//                        selectClass(ProjectDBInitializerTest.class),
//                        selectClass(TaskDBInitializerTest.class)
//                        // Add more test classes as needed
//                )
//                .build();
//
//        Launcher launcher = LauncherFactory.create();
//        TestExecutionListener listener = new SummaryGeneratingListener();
//
//        launcher.registerTestExecutionListeners(listener);
//        launcher.execute(request);
//
//        // Display test execution summary
//        listener.getSummary().printTo(System.out);
//    }
//}
//
//
//
//
//
