//package d_frameworks_and_drivers.database_management.DatabaseFiles;
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVPrinter;
//
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.UUID;
//
//public class GenerateTasksFromColumns {
//
//    public static void main(String[] args) {
//        String columnsFilePath = "src/test/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Columns/ColumnsTestInput.csv";
//        String tasksFilePath = "src/test/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Tasks/TasksTestInput.csv";
//
//        generateTasksFromColumns(columnsFilePath, tasksFilePath);
//    }
//
//    private static void generateTasksFromColumns(String columnsFilePath, String tasksFilePath) {
//        List<String[]> tasksData = new ArrayList<>();
//        Random random = new Random();
//
//        try (CSVPrinter tasksPrinter = new CSVPrinter(new FileWriter(tasksFilePath, false), CSVFormat.DEFAULT
//                .withHeader("TaskID", "Name", "Description", "Completion Status", "Due Date")
//                .withNullString(""))) {
//
//            org.apache.commons.csv.CSVParser columnsParser = null;
//            try {
//                columnsParser = new org.apache.commons.csv.CSVParser(new FileReader(columnsFilePath), CSVFormat.DEFAULT.withHeader());
//
//                for (org.apache.commons.csv.CSVRecord record : columnsParser) {
//                    String columnID = record.get("ColumnID");
//                    String columnName = record.get("Name");
//
//                    int numTasks = random.nextInt(4) + 1; // Random number of tasks (1 to 4)
//                    for (int i = 1; i <= numTasks; i++) {
//                        String taskID = generateUUID();
//                        String taskName = "Task-" + i + " Name";
//                        String taskDescription = "Task-" + i + " Description";
//                        String completionStatus = random.nextBoolean() ? "true" : "false";
//                        String dueDate = LocalDateTime.now().plusDays(random.nextInt(30)).format(DateTimeFormatter.ISO_DATE_TIME);
//
//                        String[] task = {taskID, taskName, taskDescription, completionStatus, dueDate};
//                        tasksData.add(task);
//                    }
//                }
//
//                tasksPrinter.printRecords(tasksData);
//            } finally {
//                if (columnsParser != null) {
//                    columnsParser.close();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static String generateUUID() {
//        return UUID.randomUUID().toString();
//    }
//}
//
