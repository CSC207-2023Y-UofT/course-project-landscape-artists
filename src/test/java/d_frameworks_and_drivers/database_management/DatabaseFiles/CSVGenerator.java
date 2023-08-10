//package d_frameworks_and_drivers.database_management.DatabaseFiles;
//
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVPrinter;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//import java.util.UUID;
//
//public class CSVGenerator {
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter the number of projects to generate: ");
//        int projectCount = scanner.nextInt();
//        System.out.print("Enter the number of columns per project: ");
//        int columnsPerProject = scanner.nextInt();
//        System.out.print("Enter the number of tasks per column: ");
//        int tasksPerColumn = scanner.nextInt();
//
//        scanner.close();
//
//        generateProjects(projectCount, columnsPerProject, tasksPerColumn);
//    }
//
//    private static void generateProjects(int projectCount, int columnsPerProject, int tasksPerColumn) {
//        List<String[]> projectsData = new ArrayList<>();
//        List<String> projectUUIDs = new ArrayList<>();
//
//        try (CSVPrinter projectsPrinter = new CSVPrinter(new FileWriter("ProjectsTestInput.csv"), CSVFormat.DEFAULT)) {
//            for (int i = 1; i <= projectCount; i++) {
//                String projectUUID = generateUUID();
//                projectUUIDs.add(projectUUID);
//
//                String[] project = {
//                        projectUUID,
//                        "Project-" + i + " Name",
//                        "Project-" + i + " Description",
//                        ""
//                };
//
//                projectsData.add(project);
//            }
//
//            projectsPrinter.printRecords(projectsData);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        generateColumns("ProjectsTestInput.csv", projectUUIDs);
//    }
//
//    private static void generateColumns(String projectsFilePath, List<String> projectUUIDs) {
//        List<String[]> columnsData = new ArrayList<>();
//        List<String> columnUUIDs = new ArrayList<>();
//
//        try (CSVPrinter columnsPrinter = new CSVPrinter(new FileWriter("ColumnsTestInput.csv"), CSVFormat.DEFAULT)) {
//            // Read the projects file and generate columns
//            for (String projectUUID : projectUUIDs) {
//                for (int i = 1; i <= 2; i++) { // Generating 2 columns per project
//                    String columnUUID = generateUUID();
//                    columnUUIDs.add(columnUUID);
//
//                    String[] column = {
//                            columnUUID,
//                            "Column-" + i + " Name",
//                            projectUUID + "," + generateUUID(),
//                            ""
//                    };
//
//                    columnsData.add(column);
//                }
//            }
//
//            columnsPrinter.printRecords(columnsData);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        generateTasks("ColumnsTestInput.csv", columnUUIDs);
//    }
//
//    private static void generateTasks(String columnsFilePath, List<String> columnUUIDs) {
//        List<String[]> tasksData = new ArrayList<>();
//
//        try (CSVPrinter tasksPrinter = new CSVPrinter(new FileWriter("TasksTestInput.csv"), CSVFormat.DEFAULT)) {
//            // Read the columns file and generate tasks
//            for (String columnUUID : columnUUIDs) {
//                for (int i = 1; i <= 3; i++) { // Generating 3 tasks per column
//                    String[] task = {
//                            generateUUID(),
//                            "Task-" + i + " Name",
//                            "Task-" + i + " Description",
//                            "false",
//                            generateLocalDateTime()
//                    };
//
//                    tasksData.add(task);
//                }
//            }
//
//            tasksPrinter.printRecords(tasksData);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static String generateUUID() {
//        return UUID.randomUUID().toString();
//    }
//
//    private static String generateLocalDateTime() {
//        return LocalDateTime.now().toString();
//    }
//}
