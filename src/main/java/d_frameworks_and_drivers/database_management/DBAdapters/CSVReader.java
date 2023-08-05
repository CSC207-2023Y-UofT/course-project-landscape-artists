package d_frameworks_and_drivers.database_management.DBAdapters;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *  CSVReader frameworks and driver class.
 */
public class CSVReader implements AutoCloseable {
    private File csvFile;
    private CSVParser csvParser;

    /**
     * Creates a new CSVReader given the filePath to csv to read.
     * @param filePath
     */
    public CSVReader(String filePath) throws FileNotFoundException, IOException {
        this.csvFile = new File(filePath);
    }

    /**
     * Opens the resources and initializes the CSV parser.
     *
     * @throws IOException If an I/O error occurs.
     */
    private void openResources() throws IOException {
        FileReader fileReader = new FileReader(this.csvFile);
        csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader().withNullString(""));
    }

    /**
     * Returns a mapping from the UUID IDs to CSV records/rows.
     *
     * @return Mapping from the UUID IDs to CSV records/rows
     */
    public Map<UUID, CSVRecord> getUUIDToRecordMap() {
        // Declare output map
        Map<UUID, CSVRecord> outputMap = new HashMap<>();
        // Try with resource: create FileWriter, CSVParser object as resources - closes automatically
        try (FileReader fileReader = new FileReader(this.csvFile);
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader())){


            // Iterate through each CSV record/row and append Map<UUID, CSVRecord>
            for (CSVRecord csvRecord : csvParser) {
                outputMap.put(UUID.fromString(csvRecord.get(0)), csvRecord);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException("Error getting UUID-to-recored map from CSV file: " + e.getMessage(), e);
        }
        return outputMap;
    }

    /**
     * Returns a Mapping from the string of key column field to the string of the value column field.
     * @param keyColumn index of csv key column
     * @param valueColumn index of csv value column
     * @return Mapping from the string of key column field to the string of the value column field.
     */
    public Map<String, String> getStringToStringMap(int keyColumn, int valueColumn){
        Map<String, String> outputMap = new HashMap<>();
        // Try with resource: create FileWriter, CSVParser object as resources - closes automatically
        try (FileReader fileReader = new FileReader(csvFile);
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT)){
            // Iterate through each CSV record/row and append outputMap
            for (CSVRecord csvRecord : csvParser) {
                String key = csvRecord.get(keyColumn);
                String value = csvRecord.get(valueColumn);
                outputMap.put(key, value);
            }

        } catch (IOException e){
            throw new RuntimeException("Error getting String-to-String map from CSV file: " + e.getMessage(), e);
        }
        return outputMap;
    }

    /**
     * Close the CSVParser when the CSVReader is no longer needed.
     */
    @Override
    public void close() {
        try {
            if (this.csvParser != null) {
                this.csvParser.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
// Old version
//    public static void main(String[] args) {
//        String csvFilePath = "DatabaseFiles/UniqueIDs/UniqueIDs.csv"; // Replace with the path to your CSV file
//        try {
//            Map<String, String> csvDataMap = convertCsvToHashMap(csvFilePath);
//
//            // Print the HashMap
//            for (Map.Entry<String, String> entry : csvDataMap.entrySet()) {
//                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


