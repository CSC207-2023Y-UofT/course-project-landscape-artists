package d_frameworks_and_drivers.database_management;

import com.opencsv.CSVReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class reads the Projects.csv file and returns a 2D arrayList containing the
 * String attributes of each project.
 *
 */

public class ProjectUUIDArray {
    /**
     * Converts the csv format of projects to a 2D arraylist
     * @return a 2D arraylist
     */
    public static ArrayList<ArrayList<String>> convertCsvToArrayList() {
        String csvFilePath = "src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/Projects/Projects.csv";
        ArrayList<ArrayList<String>> resultList = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath))) {
            String[] line;
            boolean skipHeader = true; // Flag to skip the first line (header)
            while ((line = csvReader.readNext()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                ArrayList<String> row = new ArrayList<>();
                for (String cell : line) {
                    row.add(cell);
                }
                resultList.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }
}
        //        ArrayList<ArrayList<String>> csvDataArray = new ArrayList<>();
//
//        // Create a CSVParser to read the CSV file
//        try (FileReader fileReader = new FileReader(csvFilePath);
//             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader())) {
//
//            // Iterate through each CSV line
//            for (CSVRecord csvRecord : csvParser) {
//                ArrayList<String> projectInfo = new ArrayList<>();
//
//                //Add in the Project attributes a new Arraylist
//                projectInfo.add(csvRecord.values()[0]);
//                projectInfo.add(csvRecord.values()[1]);
//                projectInfo.add(csvRecord.values()[2]);
//                projectInfo.add(csvRecord.values()[3]);
//                projectInfo.add(csvRecord.values()[4]);
//
//
//                //Place the newly made arrayList into another ArrayList --> 2D arraylist
//                csvDataArray.add(projectInfo);
//                System.out.println("PROJECT INFO");
//                System.out.println(projectInfo);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(csvDataArray);
//        return csvDataArray;

