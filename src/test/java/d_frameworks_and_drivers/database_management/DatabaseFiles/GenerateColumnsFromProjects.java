//package d_frameworks_and_drivers.database_management.DatabaseFiles;
//
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVParser;
//import org.apache.commons.csv.CSVPrinter;
//import org.apache.commons.csv.CSVRecord;
//
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Random;
//import java.util.Set;
//import java.util.UUID;
//
//public class GenerateColumnsFromProjects {
//
//    public static void main(String[] args) {
//        String projectsFilePath = "src/test/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Projects/ProjectsTestInput.csv";
//        String columnsFilePath = "src/test/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Columns/ColumnsTestInput.csv";
//
//        generateColumnsFromProjects(projectsFilePath, columnsFilePath);
//    }
//
//    private static void generateColumnsFromProjects(String projectsFilePath, String columnsFilePath) {
//        List<String[]> columnsData = new ArrayList<>();
//        Set<String> usedColumnNames = new HashSet<>();
//        Random random = new Random();
//
//        try (CSVParser projectsParser = new CSVParser(new FileReader(projectsFilePath), CSVFormat.DEFAULT.withHeader())) {
//            try (CSVPrinter columnsPrinter = new CSVPrinter(new FileWriter(columnsFilePath, false), CSVFormat.DEFAULT
//                    .withHeader("ColumnID", "Name", "Task ID's")
//                    .withNullString(""))) {
//
//                for (CSVRecord projectRecord : projectsParser) {
//                    String projectID = projectRecord.get("ProjectID");
//                    String projectName = projectRecord.get("Name");
//                    String projectDescription = projectRecord.get("Description");
//                    String columnIDs = projectRecord.get("Column ID's");
//
//                    int numColumns = random.nextInt(3) + 1; // Random number of columns (1 to 3)
//                    for (int i = 1; i <= numColumns; i++) {
//                        String columnName = generateUniqueColumnName(usedColumnNames);
//                        String columnID = generateUUID();
//                        String taskIDs = generateRandomTaskIDs(random.nextInt(4) + 1); // Random number of task IDs (1 to 4)
//
//                        String[] column = {columnID, columnName, taskIDs};
//                        columnsData.add(column);
//                    }
//                }
//
//                columnsPrinter.printRecords(columnsData);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static String generateUUID() {
//        return UUID.randomUUID().toString();
//    }
//
//    private static String generateRandomTaskIDs(int count) {
//        List<String> taskIDs = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//            taskIDs.add(generateUUID());
//        }
//        return String.join(",", taskIDs);
//    }
//
//    private static String generateUniqueColumnName(Set<String> usedColumnNames) {
//        String columnName;
//        do {
//            columnName = "Column-" + (usedColumnNames.size() + 1) + " Name";
//        } while (usedColumnNames.contains(columnName));
//
//        usedColumnNames.add(columnName);
//        return columnName;
//    }
//}
